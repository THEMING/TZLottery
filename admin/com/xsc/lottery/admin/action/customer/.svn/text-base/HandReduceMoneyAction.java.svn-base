package com.xsc.lottery.admin.action.customer;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;

/**
 * @author zcgs2
 * 
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.handreduceMoney")
public class HandReduceMoneyAction extends AdminBaseAction
{

    @Autowired
    private CustomerService customerService;

    private String cusname;

    private String name;

    private BigDecimal deposit_money;

    private BigDecimal freeMoney = BigDecimal.ZERO;

    public String index()
    {
        return INPUT;
    }

    /**
     * 对用户钱包扣款
     */
    public String reduce()
    {
        Customer customer = customerService.getCustomerOrName(cusname);
        if (null != customer) {
            WalletLog walletLog = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, deposit_money, BigDecimal.ZERO, freeMoney,
                    "管理员手动扣款", WalletLogType.系统扣款, "");
            try {
                customerService.addWalletLog(customer.getWallet().getId(),
                        walletLog);
                this.addActionMessage("扣款成功");
            }
            catch (Exception e) {
                this.addActionMessage("扣款失败：" + e.getMessage());
            }

        }
        else
            this.addActionMessage("没有该用户");
        return inputReduce();
    }

    /**
     * 扣款初始化
     * 
     * @return
     */
    public String inputReduce()
    {
        return INPUT;
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

    public void setDeposit_money(BigDecimal depositMoney)
    {
        deposit_money = depositMoney;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setFreeMoney(BigDecimal freeMoney)
    {
        this.freeMoney = freeMoney;
    }
}
