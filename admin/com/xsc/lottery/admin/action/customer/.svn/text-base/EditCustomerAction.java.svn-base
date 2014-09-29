package com.xsc.lottery.admin.action.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.Md5Util;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.editCustomer")
public class EditCustomerAction extends AdminBaseAction
{
    @Autowired
    private CustomerService customerService;

    private Customer customer;

    private Long id;

    private String top;

    private String realName;

    private String sysmsg;

    private String credentNo;

    private String email;

    private Bank[] bank = Bank.values();

    private String banks;

    private String province;

    private String city;

    private String subbranch;

    private String bankNumber;

    private String rebankNumber;

    private String passwords;

    public String index()
    {
        customer = customerService.findById(id);
        /**
         * rname bank credentNo email
         */
        return top;
    }

    public String edit()
    {
        customer = customerService.findById(id);
        if (top.equals("rname")) {
            customer.setRealName(realName);
            customerService.update(customer);
            sysmsg = "真实姓名修改成功!";
        }
        if (top.equals("credentNo")) {
            customer.setCredentNo(credentNo);
            customerService.update(customer);
            sysmsg = "身份证号修改成功!";
        }
        if (top.equals("email")) {
            customer.setEmail(email);
            customerService.update(customer);
            sysmsg = "邮箱修改成功!";
        }
        if (top.equals("bank")) {
            customer.setProvince(province);
            customer.setCity(city);
            customer.setBankNumber(bankNumber);
            customer.setBank(Bank.valueOf(banks));
            customer.setSubbranch(subbranch);
            customerService.update(customer);
            sysmsg = "银行账号修改成功!";
        }
        if (top.equals("password")) {
            customer.setPassword(Md5Util.getMD5ofStr(passwords));
            customerService.update(customer);
            sysmsg = "密码修改成功!";
        }
        return top;
    }

    public String getsysmsg()
    {
        return sysmsg;
    }

    public void setCredentNo(String credentNo)
    {
        this.credentNo = credentNo;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTop()
    {
        return top;
    }

    public void setTop(String top)
    {
        this.top = top;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getBanks()
    {
        return banks;
    }

    public void setBanks(String banks)
    {
        this.banks = banks;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getSubbranch()
    {
        return subbranch;
    }

    public void setSubbranch(String subbranch)
    {
        this.subbranch = subbranch;
    }

    public String getBankNumber()
    {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber)
    {
        this.bankNumber = bankNumber;
    }

    public String getRebankNumber()
    {
        return rebankNumber;
    }

    public void setRebankNumber(String rebankNumber)
    {
        this.rebankNumber = rebankNumber;
    }

    public Bank[] getBank()
    {
        return bank;
    }

    public void setPasswords(String passwords)
    {
        this.passwords = passwords;
    }

}
