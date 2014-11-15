package com.xsc.lottery.service.business.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jmx.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import cn.emay.sdk.client.api.MO;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsMoLog;
import com.xsc.lottery.entity.business.SmsPartner;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.business.SmsLog.SmsSendState;
import com.xsc.lottery.entity.business.SmsLog.SmsSendType;
import com.xsc.lottery.entity.business.SmsMoLog.SmsMoLogState;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.util.CalendarUtil;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.MapUtil;
import com.xsc.lottery.util.SDKClient;
import com.xsc.lottery.util.SignatureHelper;

/**
 * TODO 短信日志服务接口实现
 * @author pengfangliang
 */

@Transactional
@Service
public class SmsLogServiceImpl implements SmsLogService
{

	private PagerHibernateTemplate<SmsLog, Long> smsLogDao;
	
	private PagerHibernateTemplate<SmsMoLog, Long> smsMoLogDao;
	
	private PagerHibernateTemplate<SmsPartner, Long> smsPartnerDao;
	
	private SimpleHibernateTemplate<Customer, Long> customerDao;

	private static final Log logger = LogFactory.getLog(SmsLogServiceImpl.class);
	
	 @Autowired
	 public void setSessionFactory(
	 @Qualifier("sessionFactory") SessionFactory sessionfactory)
	 {
	     this.smsLogDao = new PagerHibernateTemplate<SmsLog, Long>(sessionfactory, SmsLog.class);
	     this.smsMoLogDao = new PagerHibernateTemplate<SmsMoLog, Long>(sessionfactory, SmsMoLog.class);
	     this.smsPartnerDao = new PagerHibernateTemplate<SmsPartner, Long>(sessionfactory, SmsPartner.class);
	     this.customerDao = new SimpleHibernateTemplate<Customer, Long>(sessionfactory, Customer.class);
	 }
	 
	 //根据条件查询短信日志记录
	 @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	    public Page<SmsLog> getSmsLogPage(Page<SmsLog> page,String mobile,SmsLogState state,Long id,Map map)
		{
	    	Criteria criteria = smsLogDao.createCriteria();
	    	if(mobile != null && !mobile.equals(""))
	    	{
	    		criteria.add(Restrictions.eq("mobile", mobile));
	    	}   
	    	if(state != null && !state.equals(""))
	    	{
	    		criteria.add(Restrictions.eq("state", state));
	    	} 
	    	if(MapUtil.checkKey(map, "sender"))
	    	{
	    		criteria.add(Restrictions.like("sendUserName", "%"+MapUtils.getString(map, "sender")+"%"));
	    	} 
	    	criteria.addOrder(Order.desc("id"));
	    	page = smsLogDao.findByCriteria(page, criteria);
	    	return page;
		}
	 
	//根据条件查询短信日志记录
		 @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
		    public Page<SmsLog> getSmsLogPageByMap(Page<SmsLog> page,Map map)
			{
		    	Criteria criteria = smsLogDao.createCriteria();
		    	if(MapUtil.checkKey(map, "userId"))
		    	{
		    		criteria.add(Restrictions.eq("userId", MapUtils.getString(map, "userId")));
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
		    	
		    	if(MapUtil.checkKey(map, "acceptCust"))
		    	{
		    		criteria.createAlias("customer", "customer");
		    		criteria.add(Restrictions.like("customer.nickName", MapUtils.getObject(map, "acceptCust")+"%"));
		    	} 
		    	
		    	criteria.addOrder(Order.desc("id"));
		    	page = smsLogDao.findByCriteria(page, criteria);
		    	return page;
			}

	public void smsMoFromServer()
	{
		try
		{
			logger.debug("开始获取服务器上的上行短信信息。");
			List<MO> molist = SDKClient.getClient().getMO();
			SmsMoLog smsMoLog;
			if (molist != null && molist.size() > 0)
			{
				logger.info("上行短信条数[" + molist.size() + "]条,正在往数据库保存记录");
				for (MO mo : molist)
				{
					smsMoLog = new SmsMoLog();
					smsMoLog.setContent(mo.getSmsContent());
					smsMoLog.setMobile(mo.getMobileNumber());
					smsMoLog.setChannelNumber(mo.getChannelnumber());
					smsMoLog.setSendTime(mo.getSentTime());
					smsMoLog.setAddSerial(mo.getAddSerial());
					smsMoLog.setAddSerialRev(mo.getAddSerialRev());
					smsMoLog.setReceiveTime(Calendar.getInstance());
					smsMoLog.setState(SmsMoLogState.NODO);
					smsMoLogDao.save(smsMoLog);
				}
			}
			else
			{
				logger.info("服务器上没有上行短信。");
			}
		}
		catch (Exception e)
		{
			logger.error("获取服务器的上行短息出现异常", e);
			e.printStackTrace();
		}
	}

	/**
	 * type 0 表示发送  1表示重发
	 */
	public int smsImmediatelySend(SmsLog sms, int type)
	{
		String[] mobiles = { sms.getMobile() };
		String smsContent = sms.getContent();
		int smsPriority = sms.getSendPriority();// 短信发送优先级
		int results = 0;
		try
		{
			results = SDKClient.getClient().sendSMS(mobiles, smsContent, smsPriority);
			// System.out.println("测试发送短信结果代码:"+results);
			switch (results)
			{
				case 17:
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。发送信息失败");
					break;
				case 101:
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。客户端网络故障");
					break;
				case 305:
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。服务器端返回错误，错误的返回值");
					break;
				case 307:
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。目标电话号码不符合规则，电话号码必须是以0、1开头");
					break;
				case 997:
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。平台返回找不到超时的短信，该信息是否成功无法确定");
					break;
				case 998:
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定");
					break;
				default:
				{
					sms.setSuccessTime(Calendar.getInstance());
					sms.setState(SmsLogState.SENDED);
					if (type == 1)
					{
						smsLogDao.save(sms);
					}
					else
					{
						smsLogDao.save(sms);
					}
					logger.info("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。发送信息成功");
					break;
				}
			}
			if (results == 0)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		catch (Exception e)
		{
			logger.error("短信优先通道:向手机号[" + sms.getMobile() + "]的短信。发生异常");
			e.printStackTrace();
			return 1;
		}
	}

	public boolean smsRegistEx()
	{
		String password = Configuration.getInstance().getValue("password");
		int results = 0;
		boolean returnType = true;
		if (password != null && password.trim().length() > 0)
		{
			try
			{
				results = SDKClient.getClient().registEx(password);
				if (results == 0)
				{
					logger.info("客户端注册成功");
					return returnType;
				}
				else
				{
					switch (results)
					{
						case 10:
							logger.info("客户端注册失败");
							returnType = false;
							break;
						case 101:
							logger.info("客户端网络故障");
							returnType = false;
							break;
						case 305:
							logger.info("服务器端返回错误，错误的返回值");
							returnType = false;
							break;
						case 999:
							logger.info("操作频繁");
							returnType = false;
							break;
					}
					return returnType;
				}
			}
			catch (Exception e)
			{
				logger.error("客户端注册出现异常");
				returnType = false;
				return returnType;
			}
		}
		else
		{
			returnType = false;
			return returnType;
		}
	}

	public boolean smsRegistDetail()
	{
		String eName = Configuration.getInstance().getValue("eName");
		String linkMan = Configuration.getInstance().getValue("linkMan");
		String phoneNum = Configuration.getInstance().getValue("phoneNum");
		String mobile = Configuration.getInstance().getValue("mobile");
		String email = Configuration.getInstance().getValue("email");
		String fax = Configuration.getInstance().getValue("fax");
		String address = Configuration.getInstance().getValue("address");
		String postcode = Configuration.getInstance().getValue("postcode");
		int results = 0;
		boolean returnType = true;
		try
		{
			results = SDKClient.getClient().registDetailInfo(eName, linkMan, phoneNum, mobile, email, fax, address,
					postcode);
			if (results == 0)
			{
				logger.info("企业详细信息注册成功");
				return returnType;
			}
			else
			{
				switch (results)
				{
					case -1:
						logger.info("注册企业信息不符合要求");
						returnType = false;
						break;
					case 11:
						logger.info("注册企业信息失败");
						returnType = false;
						break;
					case 101:
						logger.info("客户端网络故障");
						returnType = false;
						break;
					case 305:
						logger.info("服务器端返回错误，错误的返回值");
						returnType = false;
						break;
					case 307:
						logger.info("目标电话号码不符合规则，电话号码必须是以0、1开头");
						returnType = false;
						break;
					case 999:
						logger.info("操作频繁");
						returnType = false;
						break;
				}
				return returnType;
			}
		}
		catch (Exception e)
		{
			logger.error("企业详细信息注册出现异常");
			returnType = false;
			return returnType;
		}
	}

	public boolean smsLogout()
	{
		int results = 0;
		boolean returnType = true;
		try
		{
			results = SDKClient.getClient().logout();
			if (results == 0)
			{
				logger.info("注销成功");
				return returnType;
			}
			else
			{
				switch (results)
				{
					case 22:
						logger.info("注销失败");
						returnType = false;
						break;
					case 101:
						logger.info("客户端网络故障");
						returnType = false;
						break;
					case 305:
						logger.info("服务器端返回错误，错误的返回值");
						returnType = false;
						break;
					case 999:
						logger.info("操作频繁");
						returnType = false;
						break;
				}
				return returnType;
			}
		}
		catch (Exception e)
		{
			logger.error("注销软件出现异常");
			returnType = false;
			return returnType;
		}
	}

	public double smsBalance()
	{
		double money = 0;
		try
		{
			money = Double.parseDouble(SDKClient.getClient().getBalance());
		}
		catch (Exception e)
		{
			logger.error("获取短信剩余金额出现异常");
			e.printStackTrace();
		}
		return money;
	}

	public boolean smsPwdUpdate()
	{
		String oldpassword = Configuration.getInstance().getValue("password");
		String newpassword = Configuration.getInstance().getValue("newpassword");
		int results = 0;
		boolean returnType = true;
		try
		{
			results = SDKClient.getClient().serialPwdUpd(oldpassword, newpassword);
			if (results == 0)
			{
				logger.info("密码修改成功");
				return returnType;
			}
			else
			{
				switch (results)
				{
					case -1:
						logger.info("新密码长度不能大于6");
						returnType = false;
						break;
					case 101:
						logger.info("客户端网络故障");
						returnType = false;
						break;
					case 305:
						logger.info("服务器端返回错误，错误的返回值");
						returnType = false;
						break;
					case 308:
						logger.info("新密码不是数字，必须是数字");
						returnType = false;
						break;
					case 999:
						logger.info("操作频繁");
						returnType = false;
						break;
				}
				return returnType;
			}
		}
		catch (Exception e)
		{
			logger.error("密码修改出现异常");
			returnType = false;
			return returnType;
		}
	}

	@SuppressWarnings("unchecked")
	public int receiveSms(String digest, String isfrist, String mobile, String content, String priority,
			String sendstate, String smsType, String scheduledTime, String userId)
	{
		SmsLog smslog;
		SmsPartner partner = null;
		Criteria criteria = smsPartnerDao.createCriteria(Restrictions.eq("status", "OPEN"), Restrictions.eq("id", Long.valueOf(userId)));
	    criteria.setMaxResults(1).addOrder(Order.desc("id"));
	    List<SmsPartner> list = criteria.list();
	    if (!list.isEmpty())
	    	partner = list.get(0);
		if (partner != null)
		{
			if (checkDigest(digest, partner.getKeyPass(), mobile, content, priority, sendstate, smsType))
			{
				int sign = 0;
				smslog = new SmsLog();
				smslog.setMobile(mobile);
				smslog.setContent(content);
				smslog.setSendPriority(Integer.valueOf(priority));
				smslog.setUserId(userId);
				smslog.setUserPriority(partner.getPriority());
				smslog.setSendTime(Calendar.getInstance());
				switch (Integer.valueOf(smsType))
				{
					case 1:
						smslog.setType(SmsLogType.COMMON);
						break;
					case 2:
						smslog.setType(SmsLogType.NOTICE);
						break;
					case 3:
						smslog.setType(SmsLogType.PROMOTION);
						break;
					case 4:
						smslog.setType(SmsLogType.WARN);
						break;
				}
				if (Integer.valueOf(isfrist) == 0)
				{// 调用快速通道
					smslog.setSendState(SmsSendState.IMMEDIATELY);
					sign = smsImmediatelySend(smslog, 0);
				}
				else
				{
					smslog.setState(SmsLogState.WAITING);
					switch (Integer.valueOf(sendstate))
					{
						case 1:
							smslog.setSendState(SmsSendState.IMMEDIATELY);
							break;
						case 2:
							smslog.setSendState(SmsSendState.SCHEDULED);
							Date date = null;
							try
							{
								date = CalendarUtil.LONG_DATE_FORMAT_NO_SPLIT.parse(scheduledTime);
							}
							catch (ParseException e)
							{
								e.printStackTrace();
							}
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date);
							smslog.setScheduledTime(calendar);
							break;
					}
					smsLogDao.save(smslog);
				}
				return sign;
			}
			else
			{
				return 1;
			}
		}
		else
		{
			return 1;
		}
	}

	/**
	 * 验证短信的完整性
	 * @param digest
	 * @param mobile
	 * @param content
	 * @param priority
	 * @param sendstate
	 * @param smsType
	 * @param password
	 * @return
	 */
	private boolean checkDigest(String digest, String password, String mobile, String content, String priority,
			String sendstate, String smsType)
	{
		boolean checkResult = true;
		String digestSource = password + mobile + content + priority + sendstate + smsType;
		String signValue = SignatureHelper.generateMD5Signture(digestSource, "UTF-8");
		checkResult = signValue.toLowerCase().equals(digest.toLowerCase());
		return checkResult;
	}

	public List<SmsLog> findSmsLogByState(SmsLogState state)
	{
		 return smsLogDao.findByCriteria(Restrictions.eq("state", state));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SmsLog saveSmsLog(String mobile,String content,Long customerId,SmsLogType logType)
	{
		SmsLog smsLog = new SmsLog();
    	smsLog.setContent(content);
    	smsLog.setMobile(mobile);
    	smsLog.setRetryCount(0);
    	smsLog.setCustomerId(customerId);
    	smsLog.setSendPriority(5);
    	smsLog.setSendState(SmsSendState.IMMEDIATELY);
    	smsLog.setSmsSendType(SmsSendType.EMAY);
    	smsLog.setState(SmsLogState.WAITING);
    	smsLog.setSendTime(Calendar.getInstance());
    	smsLog.setType(logType);
    	smsLog.setUserId("1");
    	smsLog.setUserPriority(3);
		smsLogDao.save(smsLog);
		return smsLog;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SmsLog saveSmsLog(SmsLog smsLog)
	{
		if(smsLog!=null){
			smsLogDao.save(smsLog);
		}
		return smsLog;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SmsLog saveSmsLogAndSendState(String mobile,String content,Long customerId,SmsLogType logType,SmsLogState smsLogState,String mark)
	{
		SmsLog smsLog = new SmsLog();
    	smsLog.setContent(content);
    	smsLog.setMobile(mobile);
    	smsLog.setRetryCount(0);
    	smsLog.setCustomerId(customerId);
    	smsLog.setSendPriority(5);
    	smsLog.setSendState(SmsSendState.IMMEDIATELY);
    	smsLog.setSmsSendType(SmsSendType.YUN);
    	smsLog.setState(smsLogState);
    	smsLog.setSendTime(Calendar.getInstance());
    	smsLog.setType(logType);
    	smsLog.setUserId("1");
    	smsLog.setUserPriority(3);
    	smsLog.setMark(mark);
		smsLogDao.save(smsLog);
		return smsLog;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SmsLog updateSmsLog(SmsLog smsLog)
	{
		smsLogDao.save(smsLog);
		return smsLog;
	}

	public SmsLog getSmsLogById(Long id) {
		
		return smsLogDao.get(id);
	}
	
	
}
