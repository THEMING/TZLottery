package com.xsc.lottery.admin.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.service.admin.AdminUserService;
import com.xsc.lottery.web.action.LotteryBaseAction;

@SuppressWarnings("serial")
public class AdminBaseAction extends LotteryBaseAction
{
    private String permit;// 权限

    @Autowired
    private AdminUserService userService;

    public static final String SESSION_ADMINUSER_KEY = "adminUser";
    public static final String SESSION_PERMITURL_KEY = "permitUrl";

    @SuppressWarnings("unchecked")
    public Map<String, Object> getMapUrl()
    {
        Map<String, Object> mapUrl = (Map<String, Object>) getSession().get(
                SESSION_PERMITURL_KEY);
        if (null != mapUrl) {
            saveMapUrl(mapUrl);
        }
        return mapUrl;
    }

    protected void saveMapUrl(Map<String, Object> mapUrl)
    {
        getSession().put(SESSION_PERMITURL_KEY, mapUrl);
    }

    public AdminUser getCurAdminUser()
    {
        AdminUser adminUser = (AdminUser) getSession().get(
                SESSION_ADMINUSER_KEY);
        if (null != adminUser) {
            adminUser = userService.findById(adminUser.getId());
            saveCurAdminUser(adminUser);
        }
        return adminUser;
    }

    protected void saveCurAdminUser(AdminUser adminUser)
    {
        getSession().put(SESSION_ADMINUSER_KEY, adminUser);
    }

    public String getPermit()
    {
        return permit;
    }

    public void setPermit(String permit)
    {
        this.permit = permit;
    }

}
