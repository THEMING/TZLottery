package com.xsc.lottery.admin.action.user;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.AdminRole;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.service.admin.AdminUserService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("admUser")
public class AdminUserAction extends AdminBaseAction
{
    @Autowired
    private AdminUserService userService;

    private List<AdminUser> userList;

    private AdminUser adminUser;

    private List<AdminRole> roleList;

    private Long userId;

    private String[] arryid;

    private Page<AdminUser> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String chnId; // 模块ID

    private String password;

    // 模版页面主体
    public String index()
    {
        super.setPermit(userService.getPermissions(this.getCurAdminUser()
                .getRole().getId(), Long.parseLong(chnId)));
        page = new Page<AdminUser>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = userService.getAdminUserListPage(page, "");

        if (page.getResult().size() % pageSize == 0) {
            totalPages = page.getResult().size() / pageSize;
        }
        else {
            totalPages = page.getResult().size() / pageSize + 1;
        }
        return "list";
    }

    // 编辑管理员
    public String edit()
    {
        if (userId != null && userId > 0) {
            adminUser = userService.findById(userId);
        }
        else {
            adminUser = new AdminUser();
        }
        roleList = userService.getAdminRoleList();
        return "edit";
    }

    // 保存管理员
    public String saveUser()
    {
        if (userId != null && userId > 0) {
            AdminUser user = userService.load(userId);
            user.setAdminName(adminUser.getAdminName());
            user.setEmail(adminUser.getEmail());
            user.setNickName(adminUser.getNickName());
            user.setRole(adminUser.getRole());
        }
        else {
            adminUser.setRegtime(Calendar.getInstance());
            userService.save(adminUser);
        }
        return index();
    }

    // 管理员列表
    public String list()
    {
        page = new Page<AdminUser>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = userService.getAdminUserListPage(page, "");

        if (page.getResult().size() % pageSize == 0) {
            totalPages = page.getResult().size() / pageSize;
        }
        else {
            totalPages = page.getResult().size() / pageSize + 1;
        }
        return "list";
    }

    // 删除管理员
    public String delAdmin()
    {
        userService.delete(userService.findById(userId));
        return index();
    }

    public String chagPwd()
    {
        adminUser = userService.findById(userId);
        return "cpwd";
    }

    public String savePwd()
    {
        adminUser = userService.findById(userId);
        adminUser.setPassword(password);
        userService.save(adminUser);
        this.addActionMessage("密码修改成功");
        return "pass";
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserService(AdminUserService userService)
    {
        this.userService = userService;
    }

    public List<AdminRole> getRoleList()
    {
        return roleList;
    }

    public String[] getArryid()
    {
        return arryid;
    }

    public void setArryid(String[] arryid)
    {
        this.arryid = arryid;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public List<AdminUser> getUserList()
    {
        return userList;
    }

    public Page<AdminUser> getPage()
    {
        return page;
    }

    public void setPage(Page<AdminUser> page)
    {
        this.page = page;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public AdminUser getAdminUser()
    {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser)
    {
        this.adminUser = adminUser;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getChnId()
    {
        return chnId;
    }

    public void setChnId(String chnId)
    {
        this.chnId = chnId;
    }

    public static void main(String[] args)
    {
        String[] str = { "1", "2", "3" };
        String str1 = Arrays.toString(str);
        System.out.println(str1);
    }
}
