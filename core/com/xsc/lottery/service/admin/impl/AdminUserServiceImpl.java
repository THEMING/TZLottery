package com.xsc.lottery.service.admin.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.admin.AdminPermissions;
import com.xsc.lottery.entity.admin.AdminRole;
import com.xsc.lottery.entity.admin.AdminRoleFunction;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.service.admin.AdminUserService;

@SuppressWarnings("unchecked")
@Service("adminUserService")
@Transactional
public class AdminUserServiceImpl implements AdminUserService
{
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SimpleHibernateTemplate<AdminRole, Long> roleDao;

    public SimpleHibernateTemplate<AdminRoleFunction, Long> functionDao;

    public PagerHibernateTemplate<AdminUser, Long> userDaoPage;

    public SimpleHibernateTemplate<AdminUser, Long> userDao;

    public SimpleHibernateTemplate<AdminPermissions, Long> permissionsDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.roleDao = new PagerHibernateTemplate<AdminRole, Long>(
                sessionfactory, AdminRole.class);
        this.functionDao = new PagerHibernateTemplate<AdminRoleFunction, Long>(
                sessionfactory, AdminRoleFunction.class);
        this.userDaoPage = new PagerHibernateTemplate<AdminUser, Long>(
                sessionfactory, AdminUser.class);
        this.userDao = new SimpleHibernateTemplate<AdminUser, Long>(
                sessionfactory, AdminUser.class);
        this.permissionsDao = new SimpleHibernateTemplate<AdminPermissions, Long>(
                sessionfactory, AdminPermissions.class);
    }

    public void delete(AdminUser entity)
    {
        userDao.delete(entity);
    }

    public AdminUser findById(Long id)
    {
        return (AdminUser) userDao.getSession().get(AdminUser.class, id);
    }

    public AdminUser load(Long id)
    {
        return userDao.get(id);
    }

    public AdminUser save(AdminUser entity)
    {
        userDao.save(entity);
        return entity;
    }

    public AdminUser update(AdminUser entity)
    {
        userDao.save(entity);
        return entity;
    }

    public AdminUser getAdminUser(String adminName)
    {
        List<AdminUser> list = (List<AdminUser>) functionDao.find(
                "from AdminUser where adminName=?", new Object[] { adminName });
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // 获得角色列表
    public List<AdminRole> getAdminRoleList()
    {
        Criteria criteria = roleDao.createCriteria();
        criteria.addOrder(Order.asc("id"));
        List<AdminRole> list = criteria.list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    // 保存角色
    public AdminRole saveRole(AdminRole entity)
    {
        roleDao.save(entity);
        return entity;
    }

    // 得到角色ID数据
    public AdminRole getRole(Long Id)
    {
        return (AdminRole) roleDao.getSession().get(AdminRole.class, Id);
    }

    // 保存角色模块中间表
    public AdminRoleFunction saveFunction(AdminRoleFunction entity)
    {
        functionDao.save(entity);
        return entity;
    }

    // 得到角色中间表数据
    public List<AdminRoleFunction> getFunctionList(Long roleId)
    {

        List<AdminRoleFunction> list = (List<AdminRoleFunction>) functionDao
                .find("from AdminRoleFunction where role.id=?",
                        new Object[] { roleId });
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    // 删除角色中间表数据
    public void delFunctionList(Long roleId)
    {
        Query query = functionDao
                .createQuery("delete from AdminRoleFunction where role.id=:id");
        query.setParameter("id", roleId);
        query.executeUpdate();
    }

    // 删除角色
    public void delRole(Long Id)
    {
        Query query = roleDao.createQuery("delete from AdminRole where id=:id");
        query.setParameter("id", Id);
        query.executeUpdate();

    }

    public Page<AdminUser> getAdminUserListPage(Page<AdminUser> page, String value)
    {
        /*
         * Criteria criteria = userDao.createCriteria();
         * criteria.add(Restrictions.eq("id", value));
         * criteria.setFirstResult(page.getPageNo());
         * criteria.setMaxResults(page.getPageSize()); page =
         * userDao.findByCriteria(page, criteria);
         */
        // Criterion[] c = new Criterion[1];
        // Criteria criteria = userDao.createCriteria();
        // c[0] = Restrictions.eq("id", value);
        // page = userDao.findByCriteria(page,c);
        page = userDaoPage.findAll(page);
        return page;
    }

    public List<AdminUser> getAdminUserList()
    {
        Criteria criteria = userDao.createCriteria();
        criteria.addOrder(Order.asc("id"));
        List<AdminUser> list = criteria.list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public void delPermissions(Long userId)
    {
        Query query = permissionsDao.createQuery("delete from AdminPermissions where adminUser.id=:id");
        query.setParameter("id", userId);
        query.executeUpdate();
    }

    public List<AdminPermissions> getPermissionsList(Long roleId)
    {
        List<AdminPermissions> list = (List<AdminPermissions>) permissionsDao
                .find("from AdminPermissions where adminRole.id=?",
                        new Object[] { roleId });
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public String getPermissions(Long roleId, Long channelId)
    {
        List<AdminPermissions> list = (List<AdminPermissions>) permissionsDao
                .find("from AdminPermissions where adminRole.id=? and channel.id=?",
                        new Object[] { roleId, channelId });
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0).getOperate_code();
    }

    public AdminPermissions getPermissionsRoleIdorChannelId(
            AdminPermissions permissions)
    {
        List<AdminPermissions> list = (List<AdminPermissions>) permissionsDao
                .find("from AdminPermissions where adminRole.id=? and channel.id=?",
                        new Object[] { permissions.getAdminRole().getId(),
                                permissions.getChannel().getId() });
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public AdminPermissions savePermissions(AdminPermissions entity)
    {
        permissionsDao.save(entity);
        return entity;
    }

    public List<AdminRoleFunction> getRoleFunctionList(Long roleId,
            Long parentId)
    {
        List<AdminRoleFunction> list = (List<AdminRoleFunction>) functionDao
                .find("from AdminRoleFunction where role.id=? and channel.parentId=? order by channel.id desc",
                        new Object[] { roleId, parentId });
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public List<AdminRoleFunction> getRoleFunctionList(Long roleId)
    {
        List<AdminRoleFunction> list = (List<AdminRoleFunction>) functionDao
                .find("from AdminRoleFunction where role.id=? and channel.parentId>0 order by channel.id desc",
                        new Object[] { roleId });
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public List<AdminPermissions> getPermissionsUrlList(Long userId)
    {
        List<AdminPermissions> list = (List<AdminPermissions>) permissionsDao
                .find("from AdminPermissions where adminRole.id=? ",
                        new Object[] { userId });
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public List<AdminUser> getAdminUserRolId(Long rolId)
    {
        List<AdminUser> list = (List<AdminUser>) userDao.find(
                "from AdminUser where role.id=? ", new Object[] { rolId });
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public void delPermissions(String chid, Long roleId)
    {
        Query query = permissionsDao.createQuery(
                "delete from AdminPermissions where adminRole.id=:id and channel.id not in("
                        + chid + ")");
        query.setParameter("id", roleId);
        query.executeUpdate();
    }
}
