package com.xsc.lottery.yeepay.action;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.web.action.LotteryClientBaseAction;
import com.xsc.lottery.yeepay.util.PaymentForOnlineService;
import com.xsc.lottery.yeepay.util.YeeBankToStr;

/**
 * 易宝支付
 */
@Scope("prototype")
@Controller("Yeepay.RequestAct")
@SuppressWarnings("serial")
public class YiBaoPayAction extends LotteryClientBaseAction
{
    private Bank bank;
    private String deposit_money = "10";
    private String nodeAuthorizationURL;
    private Customer customer;
    private String actionmsg;

    // 请求
    private String p0_Cmd = "Buy";
    private String p2_Order;// 商家订单号
    private String p3_Amt;// 支付金额
    private String p4_Cur = "CNY";// 币种
    private String p5_Pid = "";// 商品名称
    private String p6_Pcat = "";// 商品种类
    private String p7_Pdesc = "";// 商品描述
    private String p8_Url = "";// 回调地址
    private String p9_SAF = "0";// 是否要送货地址0:不要 1:要
    private String pa_MP = "";// 商家扩展信息
    private String pd_FrpId;// 支付通道 银行代号
    private String pr_NeedResponse = "1";

    // 响应
    private String r0_Cmd; // 业务类型
    private String r1_Code;// 支付结果
    private String r2_TrxId;// 易宝支付交易流水号
    private String r3_Amt;// 支付金额
    private String r4_Cur;// 交易币种
    private String r5_Pid;// 商品名称
    private String r6_Order;// 商户订单号
    private String r7_Uid;// 易宝支付会员ID
    private String r8_MP;// 商户扩展信息
    private String r9_BType;// 交易结果返回类型
    private String hmac;
    private String p1_MerId;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentRequestService paymentRequestService;

    public String index()
    {
        customer = getCurCustomer();
        if (Integer.parseInt(deposit_money) < 10) {
            actionmsg = "充值金额不能小于10元";
            return INPUT;
        }
        else {
            logger.info("用户:" + customer.getNickName() + " 发起易宝" + bank.name()
                    + "充值,金额为" + deposit_money);
            pd_FrpId = YeeBankToStr.getBankCode(bank);
            p2_Order = MathUtil.getSerialNumber(16);
            p3_Amt = deposit_money;
            p8_Url = "http://" + getRequest().getHeader("Host")
                    + "/customer/yeepayresponse.htm";
            PaymentRequest payment = new PaymentRequest(p2_Order, customer,
                    new BigDecimal(deposit_money), bank, MoneyChannel.易宝,
                    BigDecimal.ZERO);
            customerService.savePaymentRequest(payment);
            nodeAuthorizationURL = Configuration.getInstance().getValue(
                    "yeepay.yeepayCommonReqURL")
                    + PaymentForOnlineService.getHttpParamForOnlinePayment(
                            p0_Cmd, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat,
                            p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
                            pr_NeedResponse);
            return "redirect";
        }
    }

    public String response()
    {
        boolean isOK = false;
        // 校验返回数据包
        /*isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, PaymentForOnlineService.keyValue);*/
        if (isOK) {
            BigDecimal money = new BigDecimal(r3_Amt);
            PaymentRequest payreuqest = paymentRequestService.findByProperty(
                    "serialNumber", r6_Order).get(0);
            deposit_money = payreuqest.getMoney().toString();
            customer = payreuqest.getCustomer();
            if (!payreuqest.isFinish()) {
                if (r9_BType.equals("1")) {// 产品通用接口支付成功返回-浏览器重定向\
                    payreuqest.setFinish(true);
                    WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                            BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                            payreuqest.getBank().name() + "充值",
                            WalletLogType.账户充值, "");
                    customerService.addWalletLog(payreuqest.getCustomer()
                            .getWallet().getId(), walletLog);
                    paymentRequestService.update(payreuqest);
                    // 页面提示用户冲值成功
                    logger.info("用户:" + customer.getNickName()
                                    + "易宝重定向通知冲值成功!");
                    return SUCCESS;
                }
                else if (r9_BType.equals("2")) {// 产品通用接口支付成功返回-服务器点对点通讯
                    payreuqest.setFinish(true);
                    // 持久化处理
                    WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                            BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                            payreuqest.getBank().name() + "充值",
                            WalletLogType.账户充值, "");
                    customerService.addWalletLog(payreuqest.getCustomer()
                            .getWallet().getId(), walletLog);
                    paymentRequestService.update(payreuqest);
                    try {
                        getResponse().getOutputStream().println("SUCCESS");
                        getResponse().getOutputStream().close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    logger.info("用户:" + customer.getNickName()
                            + "易宝服务器点对点通知冲值成功!");
                    return null;
                }
            }
            else {
                return SUCCESS;
            }
        }
        actionmsg = "充值失败";
        customer = getCurCustomer();
        return ERROR;
    }

    public void setDeposit_money(String depositMoney)
    {
        deposit_money = depositMoney;
    }

    public String getNodeAuthorizationURL()
    {
        return nodeAuthorizationURL;
    }

    public void setR0_Cmd(String r0Cmd)
    {
        r0_Cmd = r0Cmd;
    }

    public void setR1_Code(String r1Code)
    {
        r1_Code = r1Code;
    }

    public void setR2_TrxId(String r2TrxId)
    {
        r2_TrxId = r2TrxId;
    }

    public void setR3_Amt(String r3Amt)
    {
        r3_Amt = r3Amt;
    }

    public void setR4_Cur(String r4Cur)
    {
        r4_Cur = r4Cur;
    }

    public void setR5_Pid(String r5Pid)
    {
        r5_Pid = r5Pid;
    }

    public void setR6_Order(String r6Order)
    {
        r6_Order = r6Order;
    }

    public void setR7_Uid(String r7Uid)
    {
        r7_Uid = r7Uid;
    }

    public void setR8_MP(String r8MP)
    {
        r8_MP = r8MP;
    }

    public void setR9_BType(String r9BType)
    {
        r9_BType = r9BType;
    }

    public void setP1_MerId(String p1MerId)
    {
        p1_MerId = p1MerId;
    }

    public void setHmac(String hmac)
    {
        this.hmac = hmac;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    public String getDeposit_money()
    {
        return deposit_money;
    }

    public String getActionmsg()
    {
        return actionmsg;
    }
}
