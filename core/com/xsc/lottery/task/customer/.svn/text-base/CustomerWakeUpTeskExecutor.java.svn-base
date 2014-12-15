package com.xsc.lottery.task.customer;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.common.Constants;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.CpsDayReport;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailType;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.entity.enumerate.CustomerStatus;
import com.xsc.lottery.entity.enumerate.RegSource;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.EmailLogService;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.util.TemplateUtil;

@Component
public class CustomerWakeUpTeskExecutor implements ApplicationListener
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmailLogService emailLogService;
	@Autowired
	private SysParamService sysParamService;
	
	private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor.getInstance().newSingleThreadScheduledExecutor();
	
	public void onApplicationEvent(ApplicationEvent event)
	{
		logger.info("一彩票唤醒客户服务启动...");
        CommonScheduledThreadPoolExecutor.getInstance().execute(createWakeUpTask());
	}
	
	public Runnable createWakeUpTask()
	{
		return new Runnable()
		{
			public void run()
			{
				//第一次布置凌晨4点启动任务
				Calendar curCalendar = Calendar.getInstance();
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) <= 4)
				{
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 4, 0, 0);
				}
				else
				{
					curCalendar.add(Calendar.DATE, 1);
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 4, 0, 0);
				}
				long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				threadExec.schedule(doWakeUpTask(), delay/1000, TimeUnit.SECONDS);
				threadExec.schedule(doWakeUpTask(), 10, TimeUnit.SECONDS);
			}
		};
	}
	
	public Runnable doWakeUpTask()
	{
		return new Runnable()
		{
			public void run()
			{
					Map map = new HashMap();
					map.put("status", CustomerStatus.未激活.ordinal());
					map.put("regSource", RegSource.导入.ordinal());
					map.put("someHavntAcceptWakeUp", true);
					map.put("pageNo",1);
//					SystemParam systemParam = (SystemParam) systemParamDao.createCriteria().add(Restrictions.eq("name", Constants.WAKE_UP_EMAIL_NUM)).list().get(0);
					SystemParam systemParam = sysParamService.getSysParamByName(Constants.WAKE_UP_EMAIL_NUM);
					SystemParam perSendNum = sysParamService.getSysParamByName(Constants.WAKE_UP_EMAIL_PER_SEND_NUM);
					Double sendZong = new Double(2000);
					Double perSend = new Double(2000);
					if(systemParam!=null||!"".equals(systemParam.getValue())){
						sendZong = Double.parseDouble(systemParam.getValue());
					}
					if(perSendNum!=null||!"".equals(perSendNum.getValue())){
						perSend = Double.parseDouble(perSendNum.getValue());
					}
					map.put("pageSize", perSend);
					int cycleNum = (int) Math.ceil(sendZong/perSend);
					for(int ii=0;ii<cycleNum;ii++){
						Map customersMap = customerService.getLotteryCustomerPageForBusinessMan(null,null,null,null,null,null,null,null,null,null,map);
						if(customersMap!=null){
							List li = (List) MapUtils.getObject(customersMap, "list");
							for(int i=0;i<li.size();i++){
								Object[] o = ((Object[])li.get(i));
								EmailLog el = new EmailLog();
								el.setContent(TemplateUtil.getWakeUpEmailTemplate(o[0].toString()));
								el.setEmail(o[3].toString());
								el.setTitle("一彩票给你送大礼");
								el.setUsername("["+o[1].toString()+"]");
								el.setSendUserName("一彩票");
								el.setState(EmailState.NOTSEND);
								el.setSendTime(new Date());
								el.setType(EmailType.BUSINESS);
								emailLogService.saveOrUpdate(el);
								
								Customer c = customerService.findById(Long.parseLong(o[0].toString()));
								if(c!=null){
									c.setWakeUpEmailNum(c.getWakeUpEmailNum()==null?1:(c.getWakeUpEmailNum()+1));
								}
								customerService.save(c);
							}
						}
					}
					//布置第二天凌晨4点的任务
					Calendar curCalendar = Calendar.getInstance();
					curCalendar.add(Calendar.DATE, 1);
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 4, 0, 0);
					long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
					threadExec.schedule(doWakeUpTask(), delay/1000, TimeUnit.SECONDS);
				
			}
		};
	}

}
