package com.xsc.lottery.admin.action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.AdminPermissions;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.service.admin.AdminUserService;
import com.xsc.lottery.util.Md5Util;
import com.xsc.lottery.web.security.jcaptcha.CaptchaServiceSingleton;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("admLogin")
public class AdminLoginAction extends AdminBaseAction
{
    @Autowired
    private AdminUserService userService;

    private String adminName;

    private String adminPwd;

    private String password;

    private String validnum;// 验证码

    private AdminUser adminUser;

    public String index()
    {
        if (adminName != null && !adminName.trim().equals("")) {
            adminUser = userService.getAdminUser(adminName);
            if (adminUser != null) {
                if (Md5Util.getMD5ofStr(adminPwd).equals(
                        adminUser.getPassword())) {
                    if (validnum == null) {
                        this.addActionError("验证码不能为空！");
                        return "input";
                    }
                    try {
                        if (!CaptchaServiceSingleton.getInstance()
                                .validateResponseForID(
                                        getRequest().getSession().getId(),
                                        validnum)) {
                            this.addActionError("验证码不正确!");
                            return "input";
                        }
                    }
                    catch (Exception e) {
                    }
                    getSession().put(SESSION_ADMINUSER_KEY, adminUser);
                    Map<String, Object> mapUrl = new HashMap<String, Object>();
                    System.out.println(adminUser.getRole().getId());
                    System.out.println(adminUser.getRole().getName());
                    List<AdminPermissions> permissions = userService
                            .getPermissionsList(adminUser.getRole().getId());
                    
                    if (permissions != null) {
                        for (AdminPermissions ap : permissions) {
                            String[] str = ap.getChannel().getRighturl().split(
                                    "\\.");
                            if (str[0].lastIndexOf("/") > 0) {
                                str[0] = str[0].substring(str[0]
                                        .lastIndexOf("/") + 1);
                            }
                            mapUrl.put(str[0], ap.getChannel().getId());
                        }
                        getSession().put(SESSION_PERMITURL_KEY, mapUrl);
                    }
                }
                else {
                    this.addActionError("用户名或密码错误！");
                    return "input";
                }
            }
            else {
                this.addActionError("用户名或密码错误！");
                return "input";
            }
            if (getSession().get(SESSION_ADMINUSER_KEY) != null) {
                return "succes";
            }
        }
        return "login";
    }

    public String index2()
    {
        return "login";
    }

    public String exit()
    {
        getSession().remove(SESSION_ADMINUSER_KEY);
        getSession().remove(SESSION_PERMITURL_KEY);
        return "login";
    }

    public String revise()
    {
        return "revise";
    }

    public String savepass()
    {
        AdminUser adminUser = (AdminUser) getSession().get(
                SESSION_ADMINUSER_KEY);
        adminUser.setPassword(password);
        userService.save(adminUser);
        this.addActionMessage("密码修改成功");
        return "pass";
    }

    public void setValidnum(String validnum)
    {
        this.validnum = validnum;
    }

    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }

    public void setAdminPwd(String adminPwd)
    {
        this.adminPwd = adminPwd;
    }

    public void setUserService(AdminUserService userService)
    {
        this.userService = userService;
    }

    public AdminUser getAdminUser()
    {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser)
    {
        this.adminUser = adminUser;
    }

    public String getAdminName()
    {
        return adminName;
    }

    public String getAdminPwd()
    {
        return adminPwd;
    }

    public String getValidnum()
    {
        return validnum;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public static void main(String[] args)
    {
        System.out.println(Md5Util.getMD5ofStr("aaaaaa"));
    }
}
