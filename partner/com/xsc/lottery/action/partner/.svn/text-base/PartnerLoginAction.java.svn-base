package com.xsc.lottery.action.partner;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.Md5Util;
import com.xsc.lottery.web.security.jcaptcha.CaptchaServiceSingleton;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("partnerLoginAct")
public class PartnerLoginAction extends PartnerBaseAction
{
    @Autowired
    private PartnerService partnerService;
    
    private String nickname;
    private String password;
    private String mngunm;
    private String message = "";
    private Partner partner = null;
    
    private String oldpassword;
    private String repassword;
    
    public void setOldpassword(String oldpassword)
    {
        this.oldpassword = oldpassword;
    }

    public void setRepassword(String repassword)
    {
        this.repassword = repassword;
    }

    public String getMessage()
    {
        return message;
    }

    public String index()
    {
        partner = this.getCurClient();
        if(partner == null) {
            return "login";
        }
        return SUCCESS;
    }
    
    public String login()
    {
        partner = partnerService.getUniquePartnerByProperty("nickName", nickname);
        
        if(StringUtils.isBlank(mngunm)) {
            message = "验证码不能为空";
            return "login";
        }
        
        if(!CaptchaServiceSingleton.getInstance().validateResponseForID(
                getRequest().getSession().getId(), mngunm.toLowerCase())) {
            message = "验证码不正确!";
            return "login";
        }
        
        if(partner != null) {
            if (Md5Util.getMD5ofStr(password).equals(partner.getPassword())) {
                getSession().put(SESSION_PARTNER_KEY, partner);
                partner.setLastLoginTime(Calendar.getInstance());
                partner.addLoginNum(1);
                partnerService.update(partner);
            }
            else {
                message = "用户名或密码错误";
                return "login";
            }
        }
        else {
            message = "用户名或密码错误";
            return "login";
        }
        
        if(!partner.isActive()) {
            message = "您的账号被冻结";
            return "login";
        }
        return SUCCESS;
    }
    
    public String password()
    {
        partner = this.getCurClient();
        if(partner == null) {
            return "login";
        }
        return "password";
    }
    
    public String savepwd()
    {
        message = "";
        partner = this.getCurClient();
        if(Md5Util.getMD5ofStr(oldpassword).equals(partner.getPassword())) {
            if(password.equals(repassword)) {
                if (StringUtils.isEmpty(password)
                        || StringUtils.isEmpty(repassword)) {
                    message = "输入新密码错误!";
                }
                else {
                    partner.setPassword(Md5Util.getMD5ofStr(password));
                    partnerService.update(partner);
                    message = "修改成功!";
                }
            }
            else {
                message = "新密码不一致!";
            }
        } 
        else {
            message = "原密码错误!";
        }
        return "password";
    }
    
    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMngunm()
    {
        return mngunm;
    }

    public void setMngunm(String mngunm)
    {
        this.mngunm = mngunm;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
