package com.xsc.lottery.alllife.action;

import java.io.PrintWriter;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.alllife.util.AllLifePayment;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

/**
 * 新生支付
 */
@SuppressWarnings({"serial"})
@Scope("prototype")
@Controller("AllLifePay.RequestAct")
public class AllLifePayRequestAct extends LotteryClientBaseAction
{
    public AllLifePayment getAllLifePayment()
    {
        return allLifePayment;
    }

    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    private BigDecimal deposit_money;
    private Bank bank;
    private String payType;
    private AllLifePayment allLifePayment;

    @Autowired
    private PaymentRequestService paymentRequestService;

    @Autowired
    public CustomerService customerService;
    private Customer customer;

    private String actionmsg;

    /**
     * 新生支付网银充值
     */
    public String index() throws Exception
    {
        customer = getCurCustomer();
        if (deposit_money.intValue() < 10) {
            actionmsg = "充值金额不能小于10元";
            return INPUT;
        }
        else {
            logger.info("用户:" + customer.getNickName() + " 发起{}充值,金额为"
                    + deposit_money, bank);
            String return_url = "http://" + getRequest().getHeader("Host")
                    + "/customer/allLifreponse.htm";
            String notify_url = "http://" + getRequest().getHeader("Host")
                    + "/customer/allLifSsynResponse.htm";
            if(StringUtils.isEmpty(payType)) {
                payType = "BANK_B2C";
            }
            
            allLifePayment = new AllLifePayment();
            allLifePayment.requestInit(String.valueOf(deposit_money
                    .scaleByPowerOfTen(2).intValue()), payType, bank,
                    return_url, notify_url, "");
            PaymentRequest paymentpo = new PaymentRequest(allLifePayment
                    .getOrderID(), customer, deposit_money, bank,
                    MoneyChannel.新生支付, BigDecimal.ZERO);
            customerService.savePaymentRequest(paymentpo);
            return REDIRECT;
        }
    }

    public String allLifreponse()
    {
        return SUCCESS;
    }

    public String allLifSsynreponse()
    {
        try {
            AllLifePayment payment = new AllLifePayment();
            payment.responseInit(this.getRequest());
            PaymentRequest payreuqest = paymentRequestService.findByProperty(
                    "serialNumber", payment.getOrderID()).get(0);
            customer = payreuqest.getCustomer();
            if (payment.verifyCallback()) {// 支付成功
                deposit_money = new BigDecimal(payment.getOrderAmount())
                        .divide(BigDecimal.valueOf(100));
                if (!payreuqest.isFinish()) {
                    payreuqest.setFinish(true);
                    WalletLog walletLog = new WalletLog(BusinessType.收入,
                            deposit_money, BigDecimal.ZERO, BigDecimal.ZERO,
                            BigDecimal.ZERO, "新生支付" + "充值", WalletLogType.账户充值,
                            "");
                    customerService.addWalletLog(payreuqest.getCustomer()
                            .getWallet().getId(), walletLog);
                    paymentRequestService.update(payreuqest);
                    // 页面提示用户冲值成功
                    logger.info("用户:" + customer.getNickName()
                            + "新生支付重定向通知冲值成功!");
                }
            }
            PrintWriter out;
            out = getResponse().getWriter();
            out.write("200");
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getDeposit_money()
    {
        return deposit_money;
    }

    public void setDeposit_money(BigDecimal depositMoney)
    {
        deposit_money = depositMoney;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public String getActionmsg()
    {
        return actionmsg;
    }
}
