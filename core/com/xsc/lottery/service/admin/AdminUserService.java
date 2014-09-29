package com.xsc.lottery.service.admin;

import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.admin.AdminPermissions;
import com.xsc.lottery.entity.admin.AdminRole;
import com.xsc.lottery.entity.admin.AdminRoleFunction;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.service.LotteryBaseService;

public interface AdminUserService extends LotteryBaseService<AdminUser>
{
    /** 得到角色列表 */
    public List<AdminRole> getAdminRoleList();

    /** 添加保存角色数据 */
    public AdminRole saveRole(AdminRole entity);

    /** 得到角色ID数据 */
    public AdminRole getRole(Long Id);

    /** 删除角色表 */
    public void delRole(Long Id);

    /** 保存中间表 */
    public AdminRoleFunction saveFunction(AdminRoleFunction entity);

    /** 按角色ID得到用户信息 */
    public List<AdminUser> getAdminUserRolId(Long rolId);

    /** 按角色ID得到中间表列表 */
    public List<AdminRoleFunction> getFunctionList(Long roleId);

    /** 删除中间表中不在CHID里的数据 */
    public void delPermissions(String chid, Long roleId);

    /** 按角色ID父类0得到模块列表 */
    public List<AdminRoleFunction> getRoleFunctionList(Long roleId,
            Long parentId);

    /** 按角色ID模块ID得到权限 */
    public String getPermissions(Long roleId, Long channelId);

    /** 按角色ID模块ID得到权限数据 */
    public AdminPermissions getPermissionsRoleIdorChannelId(
            AdminPermissions permissions);

    /** 按角色ID模块ID得到权限 */
    public List<AdminPermissions> getPermissionsUrlList(Long userId);

    /** 按角色ID父类大于0得到模块列表 */
    public List<AdminRoleFunction> getRoleFunctionList(Long roleId);

    /** 删除中间表数据 */
    public void delFunctionList(Long roleId);

    /** 得到管卡理员列表 */
    public List<AdminUser> getAdminUserList();

    /** 根据UserName得到管理员数据 */
    public AdminUser getAdminUser(String userName);

    /** 分页管理 */
    public Page<AdminUser> getAdminUserListPage(Page<AdminUser> page, String value);

    /** 管理员与角色中间表保存 */
    public AdminPermissions savePermissions(AdminPermissions entity);

    /** 删除管理员与角色中间表数据 */
    public void delPermissions(Long userId);

    /** 根据UserID得到管理员与角色中间表数据列表 */
    public List<AdminPermissions> getPermissionsList(Long roleId);

}
