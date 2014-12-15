package com.xsc.lottery.service.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
import com.xsc.lottery.entity.business.AdminSendSomeThingTemplate;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.service.admin.AdminUserService;
import com.xsc.lottery.service.business.AdminSendSomeThingTemplateService;
import com.xsc.lottery.util.MapUtil;

@SuppressWarnings("unchecked")
@Service("adminSendSomeThingTemplateService")
@Transactional
public class AdminSendSomeThingTemplateServiceImpl implements AdminSendSomeThingTemplateService
{
	public PagerHibernateTemplate<AdminSendSomeThingTemplate, Long> adminSendSomeThingTemplateDao;
	
	@Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {        
        this.adminSendSomeThingTemplateDao = new PagerHibernateTemplate<AdminSendSomeThingTemplate, Long>(sessionfactory,
        		AdminSendSomeThingTemplate.class);
    }

	public AdminSendSomeThingTemplate findById(Long id)
	{
		return null;
	}

	public AdminSendSomeThingTemplate load(Long id)
	{
		return null;
	}

	public AdminSendSomeThingTemplate save(AdminSendSomeThingTemplate entity)
	{
		return null;
	}

	public AdminSendSomeThingTemplate update(AdminSendSomeThingTemplate entity)
	{
		return null;
	}

	public void delete(AdminSendSomeThingTemplate entity)
	{
	}

	public Page<AdminSendSomeThingTemplate> getAdminSendSomeThingTemplatePage(Page<AdminSendSomeThingTemplate> page,
			Map map)
	{
		Criteria criteria = adminSendSomeThingTemplateDao.createCriteria();
		if(MapUtil.checkKey(map, "t_titleQuer")){
        	criteria.add(Restrictions.like("title","%"+MapUtils.getString(map,"t_titleQuer")+"%" ));
        }
        if(MapUtil.checkKey(map, "sendTemplateType")){
        	criteria.add(Restrictions.eq("sendTemplateType",MapUtils.getObject(map,"sendTemplateType") ));
        }
		
		criteria.addOrder(Order.desc("id"));
		return adminSendSomeThingTemplateDao.findByCriteria(page, criteria);
	}

	public void deleteMany(Map m)
	{
		StringBuffer hql = new StringBuffer("delete from admin_sendsomething_template where 1=1");
    	if (MapUtil.checkKey(m, "ids")) {
        	hql.append(" and id in(:ids)");
        }else{//保护数据
        	return ;
        }    	
    	
    	SQLQuery query = adminSendSomeThingTemplateDao.getSession().createSQLQuery(hql.toString());
    	
    	if (MapUtil.checkKey(m, "ids")) {
        	query.setParameterList("ids", (List)MapUtils.getObject(m,"ids"));
        } 
    	
    	int i = query.executeUpdate();
	}
	
}
