package com.xsc.lottery.alipay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.alipay.util.Payment;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings( { "serial" })
@Scope("prototype")
@Controller("Alipay.LoginRequestAct")
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

    /** 密钥 */
    private String key = Configuration.getInstance().getValue("alipay.key");

    /** 网关url地址 */
    private String url = Configuration.getInstance().getValue("alipay.url");

    /** 商户ID */
    private String partner = Configuration.getInstance().getValue("alipay.partnerID");
    private String charset = Configuration.getInstance().getValue("alipay.CharSet");

    private String useOtherId;
    private String name;
    private String credentNo;
    private String mobile;
    private String email;
    private UserType userType = UserType.支付宝用户;
    private Customer customer;

    @Autowired
    private CustomerService customerService;

    public String index() throws UnsupportedEncodingException
    {
        System.out.println("支付宝========>" + this.getRequest().getQueryString());
        String alipay_rtnurl = this.getRequest().getQueryString();
        if (StringUtils.isNotBlank(alipay_rtnurl)) {
            if ("T".equals(this.getRequest().getParameter("is_success"))) {
                useOtherId = this.getRequest().getParameter("user_id");
                customer = customerService.getUniqueCustomerByProperty(
                        "user3_id", useOtherId);
                
                if(null != customer) {
                    this.saveCurCustomer(customer);
                    customer.setLastLoginTime(Calendar.getInstance());
                    customer.setCustomerIp(this.getRemoteIp());
                    customer.addLoginNum(1);
                    customerService.update(customer);
                    return INPUT;
                }
                
                name = this.getRequest().getParameter("real_name");
                credentNo = this.getRequest().getParameter("cert_no");
                mobile = this.getRequest().getParameter("mobile");
                email = this.getRequest().getParameter("email");
                return SUCCESS;
            }
            else {
                throw new RuntimeException("支付宝登陆验证失败");
            }
        }
        return null;
    }

    public String sendRed() throws IOException
    {
        String return_url = "http://" + this.getRequest().getHeader("Host")
                + "/alipayLogin.htm";
        String requestUrl = Payment.CreateUrl(url, "user_authentication",
                "MD5", partner, key, return_url, charset);
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
}
