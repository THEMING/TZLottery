/**
 * <pre>
 * Title: 		SysParamServiceImpl.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-24 下午04:48:07
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.service.business.SysParamService;

/**
 * <pre>
 * TODO 系统参数服务接口实现
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-24
 */
@Service("sysParamService")
@Transactional
public class SysParamServiceImpl implements SysParamService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public SimpleHibernateTemplate<SystemParam, Long> systemParamDao;
    
    @Autowired
    public void setSessionFactory(@Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.systemParamDao = new SimpleHibernateTemplate<SystemParam, Long>( sessionfactory, SystemParam.class);
    }

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.SysParamService#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<SystemParam> findAll()
	{
		Criteria criteria = systemParamDao.createCriteria(); 
		return criteria.list();
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.SysParamService#getSysParamByName(java.lang.String)
	 */
	public SystemParam getSysParamByName(String name)
	{
		Criteria criteria = systemParamDao.createCriteria(); 
		criteria.add(Restrictions.eq("name", name));
		return (SystemParam)criteria.uniqueResult();
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#delete(java.io.Serializable)
	 */
	public void delete(SystemParam entity)
	{
		systemParamDao.delete(entity);
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#findById(java.lang.Long)
	 */
	public SystemParam findById(Long id)
	{
		return (SystemParam)systemParamDao.getSession().get(SystemParam.class, id);
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#load(java.lang.Long)
	 */
	public SystemParam load(Long id)
	{
		return (SystemParam)systemParamDao.getSession().load(SystemParam.class, id);
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#save(java.io.Serializable)
	 */
	public SystemParam save(SystemParam entity)
	{
		systemParamDao.save(entity);
		return entity;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#update(java.io.Serializable)
	 */
	public SystemParam update(SystemParam entity)
	{
		systemParamDao.save(entity);
		return entity;
	}

}
