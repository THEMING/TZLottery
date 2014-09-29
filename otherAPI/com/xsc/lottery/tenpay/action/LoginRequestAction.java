package com.xsc.lottery.tenpay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.tenpay.util.TenpayShare;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings( { "serial" })
@Scope("prototype")
@Controller("TenPay.LoginRequestAct")
public class LoginRequestAction extends LotteryClientBaseAction
{
    public String getName()
    {
        return name;
    }

    public String getCredentNo()
    {
        return credentNo;
    }

    private UserType userType = UserType.财付通用户;
    private String useOtherId;
    private String name;
    private String credentNo;
    private String mobile;
    private String email;
    private Customer customer;
    
    @Autowired
    private CustomerService customerService;

    public String index() throws UnsupportedEncodingException
    {
        TenpayShare tenpayShare = new TenpayShare();
        tenpayShare.setResponseAndUriEncoding(this.getRequest()
                .getParameterMap(), "ISO-8859-1");
        // 判断签名
        if (tenpayShare.isTenpaySign()) {
            useOtherId = tenpayShare.getParameter("id");
            customer = customerService.getUniqueCustomerByProperty("user3_id",
                    useOtherId);
        }
        else {
            throw new RuntimeException("财付通登陆验证失败");
        }
        
        if (null == customer) {
            name = tenpayShare.getParameter("tenName");
            credentNo = tenpayShare.getParameter("credid");
            mobile = tenpayShare.getParameter("mobile");
            email = tenpayShare.getParameter("email");
            return SUCCESS;
        }
        
        this.saveCurCustomer(customer);
        customer.setLastLoginTime(Calendar.getInstance());
        customer.setCustomerIp(this.getRemoteIp());
        customer.addLoginNum(1);
        customerService.update(customer);
        return INPUT;
    }

    public String sendRed() throws UnsupportedEncodingException, IOException
    {
        String redirect_url = "http://" + getRequest().getHeader("Host")
                + "/tenPayLogin.htm";
        TenpayShare tenpayShare = new TenpayShare();
        // 初始化
        tenpayShare.setRequestParam(redirect_url);
        String requestUrl = tenpayShare.getRequestURL();
        // 获取请求带参数的url
        this.getResponse().sendRedirect(requestUrl);
        return null;
    }

    public String getUseOtherId()
    {
        return useOtherId;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public String getMobile()
    {
        return mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }
}
