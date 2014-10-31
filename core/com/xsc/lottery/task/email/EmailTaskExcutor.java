/**
 * <pre>
 * Title: 		EmailTaskExcutor.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-18 下午05:22:30
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.task.email;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.EmailLogService;
import com.xsc.lottery.util.EmailUtil;

/**
 * <pre>
 * TODO 发送邮件任务
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-18
 */
@Component
public class EmailTaskExcutor implements ApplicationListener
{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final int FETCH_COUNT = 1000;
	
	protected boolean start = false;
	
	@Autowired
	private EmailLogService emailLogService;
	
	/* （非 Javadoc）
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(ApplicationEvent event)
	{
		 if (event instanceof ContextRefreshedEvent && !start) {
	            logger.info("一彩票邮件服务启动...");
	            CommonScheduledThreadPoolExecutor.getInstance().execute(createEmailSendTask());
	            start = true;
	        }
	}
	
	public Runnable createEmailSendTask()
	{
		
		return new Runnable()
		{
			public void run()
			{

                logger.info("邮件发送队列启动!");
                while (true) {
                	try {
	                	List<EmailLog> emailLogList = emailLogService.findEmailForNotSend(FETCH_COUNT);
	                	if(emailLogList!=null&&emailLogList.size()!=0)
	                	{
	                		for (EmailLog emailLog : emailLogList)
	                		{
	                			EmailUtil.sendEmail(emailLog.getEmail(), emailLog.getTitle(), emailLog.getContent());
	                			emailLog.setState(EmailState.SENDED);
	                			emailLog.setSuccessTime(new Date());
	                			emailLogService.saveOrUpdate(emailLog);
	                		}
	                	}
	                	else
	                	{
	                		try { 
	                			Thread.sleep(5000);
	                		} catch (InterruptedException e) { 
	                            e.printStackTrace(); 
	                		}          
	                	}
                	}
                    catch (Exception e) {
                        String description = "邮件队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
			}
		};
	}

}
