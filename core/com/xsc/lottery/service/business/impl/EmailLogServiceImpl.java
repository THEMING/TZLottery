/**
 * <pre>
 * Title: 		EmailLogServiceImpl.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-18 下午04:26:17
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.alipay.util.MapUtil;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.entity.business.EmailLog.EmailType;
import com.xsc.lottery.service.business.EmailLogService;
import com.xsc.lottery.util.Base64;

/**
 * <pre>
 * TODO 邮件日志服务接口实现
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-18
 */
@Service
@Transactional
public class EmailLogServiceImpl implements EmailLogService
{
	
	private PagerHibernateTemplate<EmailLog, Long> emailLogDao;
	
	@Autowired
	 public void setSessionFactory(
	 @Qualifier("sessionFactory") SessionFactory sessionfactory)
	 {
	     this.emailLogDao = new PagerHibernateTemplate<EmailLog, Long>(sessionfactory, EmailLog.class);
	 }

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.EmailLogService#findEmailForNotSend(int)
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<EmailLog> findEmailForNotSend(int count)
	{
		Criteria criteria = emailLogDao.createCriteria();
		criteria.add(Restrictions.eq("state", EmailState.NOTSEND));
		criteria.setMaxResults(count);
		criteria.setFirstResult(0);
		List<EmailLog> list = criteria.list();
		List<EmailLog> newList = new ArrayList<EmailLog>();
		for (EmailLog email : list)
		{
			email.setState(EmailState.SENDING);
			newList.add(saveOrUpdate(email));
		}
		return newList;
	}
	
	public Page<EmailLog> findEmailByMap(Page<EmailLog> page,Map map){
		Criteria criteria = emailLogDao.createCriteria();
    	if(MapUtil.checkKey(map, "storeId"))
    	{
    		criteria.add(Restrictions.eq("storeId", MapUtils.getString(map, "storeId")));
    	}   
    	
    	if(MapUtil.checkKey(map, "state"))
    	{
    		criteria.add(Restrictions.eq("state", MapUtils.getObject(map, "state")));
    	}   
    	
    	if(MapUtil.checkKey(map, "sendSTime"))
    	{
    		criteria.add(Restrictions.ge("sendTime", MapUtils.getObject(map, "sendSTime")));
    	}   
    	
    	if(MapUtil.checkKey(map, "sendETime"))
    	{
    		criteria.add(Restrictions.le("sendTime", MapUtils.getObject(map, "sendETime")));
    	}   
    	
    	if(MapUtil.checkKey(map, "sendUserNameQuery"))
    	{
    		criteria.add(Restrictions.like("sendUserName", "%"+MapUtils.getString(map, "sendUserNameQuery")+"%"));
    	}  
    	
    	criteria.addOrder(Order.desc("id"));
    	page = emailLogDao.findByCriteria(page, criteria);
    	return page;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.EmailLogService#resetAllEmailForSending()
	 */
	public int resetAllEmailForSending()
	{
		Query query = emailLogDao.getSession().getNamedQuery("email.resetForSending");
		return query.executeUpdate();
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.EmailLogService#sendEmail(com.xsc.lottery.entity.business.EmailLog.EmailType, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean sendEmail(EmailType type, int sendLevel, String storeId, String toEmail, String title, String content)
	{
		
		try
		{
			EmailLog emailLog = new EmailLog();
			emailLog.setType(type);
			emailLog.setSendLevel(sendLevel);// 设置级别(值越大越优先)
			emailLog.setStoreId(storeId);
			emailLog.setEmail(toEmail);
			emailLog.setTitle(title);
			emailLog.setContent(content);
			emailLog.setState(EmailState.NOTSEND);
			emailLog.setSendTime(new Date());
			emailLog.setRetryCount(0);

			// 记录到邮件列表
			emailLogDao.save(emailLog);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.EmailLogService#saveOrUpdate(com.xsc.lottery.entity.business.EmailLog)
	 */
	public EmailLog saveOrUpdate(EmailLog emailLog)
	{
		emailLogDao.save(emailLog);
		return emailLog;
	}
	
	public static void main(String[] args)
	{
		/*
		EmailLogService service = ComponentContextLoader.getBean(EmailLogService.class);
		service.sendEmail(EmailType.NORMAL, 5, "100", "pfl@yicp.com", "邮件测试", "邮件测试");
		*/
		Long id = 1000l;
		byte bb[] = {};
		long2Byte(bb,id);
		String code = new String(Base64.encode(bb));
		System.out.println(code);

	}
	public static void long2Byte(byte[] bb, long x) { 
        bb[ 0] = (byte) (x >> 56); 
        bb[ 1] = (byte) (x >> 48); 
        bb[ 2] = (byte) (x >> 40); 
        bb[ 3] = (byte) (x >> 32); 
        bb[ 4] = (byte) (x >> 24); 
        bb[ 5] = (byte) (x >> 16); 
        bb[ 6] = (byte) (x >> 8); 
        bb[ 7] = (byte) (x >> 0); 
  } 

}
