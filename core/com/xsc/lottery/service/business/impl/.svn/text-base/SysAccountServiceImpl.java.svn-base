/**
 * <pre>
 * Title: 		SysAccountServiceImpl.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-26 下午05:23:46
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.SysAccount;
import com.xsc.lottery.entity.business.SysAccountLog;
import com.xsc.lottery.service.business.SysAccountService;

/**
 * <pre>
 * TODO 系统账户服务接口实现
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-26
 */
public class SysAccountServiceImpl implements SysAccountService
{
	 public transient final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 public SimpleHibernateTemplate<SysAccount, Long> sysAccountDao;
	 
	 public SimpleHibernateTemplate<SysAccountLog, Long> sysAccountLogDao;
	 
	 @Autowired
	 public void setSessionFactory(@Qualifier("sessionFactory") SessionFactory sessionfactory)
	    {
	        sysAccountDao = new SimpleHibernateTemplate<SysAccount, Long>(sessionfactory,
	        		SysAccount.class);
	        sysAccountLogDao = new PagerHibernateTemplate<SysAccountLog, Long>(
	                sessionfactory, SysAccountLog.class);
	    }

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#delete(java.io.Serializable)
	 */
	public void delete(SysAccount entity)
	{
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#findById(java.lang.Long)
	 */
	public SysAccount findById(Long id)
	{
		return null;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#load(java.lang.Long)
	 */
	public SysAccount load(Long id)
	{
		return null;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#save(java.io.Serializable)
	 */
	public SysAccount save(SysAccount entity)
	{
		return null;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.LotteryBaseService#update(java.io.Serializable)
	 */
	public SysAccount update(SysAccount entity)
	{
		return null;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.SysAccountService#addSysAccountLog(java.lang.Long, com.xsc.lottery.entity.business.SysAccountLog)
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addSysAccountLog(Long id, SysAccountLog sysAccountLog)
	{
		synchronized (String.valueOf(sysAccountLog.hashCode()))
		{
			SysAccount sysAccount = (SysAccount) sysAccountDao.getSession().get(SysAccount.class,id, LockMode.UPGRADE);
			sysAccount.setBalance(sysAccount.getBalance().add(sysAccountLog.getInMoney()));
			sysAccount.setBalance(sysAccount.getBalance().subtract(sysAccountLog.getOutMoney()));
			sysAccountLog.setCurrentMoney(sysAccount.getBalance());
			sysAccountLog.setSysAccount(sysAccount);
			if (sysAccount.getBalance().compareTo(BigDecimal.ZERO) < 0)
			{
	           throw new HibernateException("系统账户余额不足");
	        }
			sysAccountDao.save(sysAccount);
			sysAccountLogDao.save(sysAccountLog);
		}
	}

}
