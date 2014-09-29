package com.xsc.lottery.admin.action.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.Md5Util;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.partner")
public class PartnerAction extends AdminBaseAction
{
    @Autowired
    private PartnerService partnerService;
    
    private Page<Partner> page;
    
    private int pageNo = 1;

    private int pageSize = 10;
    
    private UserType[] userTypes = UserType.values();
    
    private UserType  userType;
    
    private String nickName;
    
    private String password;
    
    private boolean active;
    
    private String phone;
    
    private long id;
    
    private String message;
    
    public String index()
    {
        page = new Page<Partner>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = partnerService.getPartnerPage(page);
        return "list";
    }
    
    public String addNew()
    {
        if(nickName == null || password == null ||
                userType == null) {
            message = "用户名或密码或用户类型不能为空!";
            return index();
        }
        Partner partner = new Partner();
        partner.setNickName(nickName);
        partner.setPassword(Md5Util.getMD5ofStr(password));
        partner.setPhone(phone);
        partner.setUserType(userType);
        partnerService.save(partner);
        return index();
    }
    
    public String delete()
    {
        Partner partner = partnerService.findById(id);
        if(partner != null) {
            partnerService.delete(partner);
        }
        return index();
    }
    
    public String operate()
    {
        Partner partner = partnerService.findById(id);
        if(partner != null) {
            partner.setActive(active);
            partnerService.save(partner);
        }
        return index();
    }
    
    public UserType[] getUserTypes()
    {
        return userTypes;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public String getNickName()
    {
        return nickName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getMessage()
    {
        return message;
    }

    public Page<Partner> getPage()
    {
        return page;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}
