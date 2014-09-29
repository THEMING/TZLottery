package com.xsc.lottery.admin.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.util.Md5Util;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.handaddMoney")
public class HandAddMoneyAction extends AdminBaseAction
{
    @Autowired
    private CustomerService customerService;

    private String cusname;

    private String name;

    private String deposit_money;

    private MoneyChannel[] ostatu = MoneyChannel.values();

    private String status;

    private String serialNumber;

    private String pwd;

    public String index()
    {
        return "input";
    }

    public String chongz()
    {
        AdminUser usr = this.getCurAdminUser();
        PaymentRequest payment = new PaymentRequest();
        Customer customer = customerService.getCustomerOrName(cusname);
        String p2_Order = MathUtil.getSerialNumber(16);
        boolean bool = false;
        if (!status.equals("手动补单") && !status.equals("测试")) {
            if (StringUtils.isEmpty(serialNumber)) {
                this.addActionMessage("订单号不能为空，请核实!");
                bool = true;
            }
            else {
                payment = customerService
                        .getPaymentRequestByNumber(serialNumber);
                if (payment == null) {
                    this.addActionMessage("此订单不存在，请检查!");
                    bool = true;
                }
                else {
                	if(status.equals("支付宝") || status.equals("易宝"))
                	{
                		deposit_money = String.valueOf(payment.getMoney());
                	}
                    payment.setUser(usr);
                    payment.setOptionTime(Calendar.getInstance());
                }
            }
        }
        else {
            if (customer != null) {
                if (!customer.getRealName().equals(name)) {
                    this.addActionMessage("真实姓名错误!");
                    bool = true;
                }
            }
            else {
                this.addActionMessage("用户错误!");
                bool = true;
            }
            if (StringUtils.isEmpty(deposit_money)
                    || Integer.parseInt(deposit_money) < 1) {
                this.addActionMessage("冲值金额不少于1元!");
                bool = true;
            }
            if (StringUtils.isEmpty(pwd)
                    || !Md5Util.getMD5ofStr(pwd).equals(usr.getPassword())) {
                this.addActionMessage("密钥错误!");
                bool = true;
            }

            if (!bool) {
                payment = new PaymentRequest(p2_Order, customer,
                        new BigDecimal(deposit_money), MoneyChannel
                                .valueOf(status), usr);
                payment.setOptionTime(Calendar.getInstance());
            }
        }
        if (bool) {
            return "input";
        }
        try {
        	String desc = "手动补单充值";
        	WalletLogType logType = WalletLogType.直接充值;
        	if(status.equals("支付宝"))
        	{
        		desc = "支付宝充值";
        		logType = WalletLogType.账户充值;
        	}
        	else if(status.equals("易宝"))
        	{
        		desc = "易宝充值";
        		logType = WalletLogType.账户充值;
        	}
            WalletLog walletLog = new WalletLog(BusinessType.收入,
                    new BigDecimal(deposit_money), BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, desc,
                    logType, payment.getSerialNumber());
            payment.setFinish(true);
            customerService.addHandMoney(payment, walletLog);
            logger.info("手动补单通知冲值成功!");
            this.addActionMessage("手动补单通知冲值成功!");
        }
        catch (Exception e) {
            logger.error("手动补单通知冲值失败!" + e);
        }
        return "ok";
    }

    public String getCusname()
    {
        return cusname;
    }

    public void setCusname(String cusname)
    {
        this.cusname = cusname;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDeposit_money()
    {
        return deposit_money;
    }

    public void setDeposit_money(String depositMoney)
    {
        deposit_money = depositMoney;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public MoneyChannel[] getOstatu()
    {
        return ostatu;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public String getStatus()
    {
        return status;
    }

}
