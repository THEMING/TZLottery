package com.xsc.lottery.admin.action.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.AdminChannel;
import com.xsc.lottery.entity.admin.AdminPermissions;
import com.xsc.lottery.entity.admin.AdminRole;
import com.xsc.lottery.entity.admin.AdminRoleFunction;
import com.xsc.lottery.service.admin.AdminChannelService;
import com.xsc.lottery.service.admin.AdminUserService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("manageRole")
public class AdminRoleAction extends AdminBaseAction
{
    @Autowired
    private AdminUserService userService;

    @Autowired
    private AdminChannelService channelService;

    private List<AdminChannel> chnTreeList;

    private List<AdminRole> roleList;

    private List<AdminPermissions> permissionsList;

    private AdminRole role;

    private Long roleId;

    private String[] arryid;

    private Map<Long, Object> mapRc = new HashMap<Long, Object>();

    // 模版页面主体
    public String index()
    {
        roleList = userService.getAdminRoleList();
        return "listrole";
    }

    //
    public String sav()
    {
        System.out.println(arryid);
        return "addrole";
    }

    // 编辑角色
    public String addRole()
    {
        if (roleId != null) {
            List<AdminRoleFunction> functionList = userService
                    .getFunctionList(roleId);
            if (functionList != null) {
                for (AdminRoleFunction arf : functionList) {
                    mapRc.put(arf.getChannel().getId(), arf.getId());
                }
            }
            role = userService.getRole(roleId);
        }
        List<AdminChannel> list = new ArrayList<AdminChannel>();
        List<AdminChannel> depth = channelService.getAdminChannelDepthAsc(0l);
        for (AdminChannel chn : depth) {
            list.add(chn);
            List<AdminChannel> parent = channelService
                    .getAdminChannelDepthAsc(chn.getId());
            if (parent != null) {
                for (AdminChannel chnParent : parent) {
                    list.add(chnParent);
                }
            }
        }
        chnTreeList = list;

        return "addrole";
    }

    // 保存角色
    public String saveRole()
    {
        AdminRole adrole = new AdminRole();
        if (role.getId() == null || role.getId() <= 0) {
            adrole = userService.saveRole(role);
        }
        else {
            adrole = role;
        }
        List<AdminPermissions> permissionsList = new ArrayList<AdminPermissions>();
        if (adrole != null) {
            userService.delFunctionList(adrole.getId());
            for (int i = 0; i < arryid.length; i++) {
                AdminRoleFunction function = new AdminRoleFunction();
                function.setChannel(channelService.findById(Long
                        .parseLong(arryid[i])));
                function.setRole(adrole);
                userService.saveFunction(function);
                AdminPermissions permissions = new AdminPermissions();
                permissions.setAdminRole(userService.getRole(adrole.getId()));
                permissions.setChannel(channelService.findById(Long
                        .parseLong(arryid[i])));
                if (userService.getPermissionsRoleIdorChannelId(permissions) == null) {
                    userService.savePermissions(permissions);
                }
                permissionsList.add(permissions);
            }
            String str = java.util.Arrays.toString(arryid);
            String chidlist = str.substring(str.indexOf("[") + 1, str
                    .lastIndexOf("]"));
            userService.delPermissions(chidlist, adrole.getId());
        }
        return index();
    }

    public String delRole()
    {

        userService.delFunctionList(roleId); // 先删除中间表
        userService.delRole(roleId); // 删除角色表
        return index();
    }

    // 管理员权限
    public String permlist()
    {
        if (roleId != null && roleId > 0) {
            permissionsList = userService.getPermissionsList(roleId);
        }
        return "purview";
    }

    // 管理员权限保存
    public String saveperm()
    {
        if (roleId != null && roleId > 0) {
            permissionsList = userService.getPermissionsList(roleId);
            for (AdminPermissions ap : permissionsList) {
                String[] qx = super.getRequest().getParameterValues(
                        ap.getChannel().getId().toString());
                String strQx = Arrays.toString(qx);
                ap.setOperate_code(strQx);
                userService.savePermissions(ap);
            }
        }
        return index();
    }

    public void setUserService(AdminUserService userService)
    {
        this.userService = userService;
    }

    public void setChannelService(AdminChannelService channelService)
    {
        this.channelService = channelService;
    }

    public List<AdminChannel> getChnTreeList()
    {
        return chnTreeList;
    }

    public AdminRole getRole()
    {
        return role;
    }

    public void setRole(AdminRole role)
    {
        this.role = role;
    }

    public String[] getArryid()
    {
        return arryid;
    }

    public void setArryid(String[] arryid)
    {
        this.arryid = arryid;
    }

    public List<AdminRole> getRoleList()
    {
        return roleList;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Map<Long, Object> getMapRc()
    {
        return mapRc;
    }

    public List<AdminPermissions> getPermissionsList()
    {
        return permissionsList;
    }

    public static void main(String[] args)
    {
        Long[] str = { 1l, 2l, 3l, 4l };
        String s = java.util.Arrays.toString(str);
        String ss = s.substring(s.indexOf("[") + 1, s.lastIndexOf("]"));
        System.out.println(ss);
    }
}
