package com.xsc.lottery.task.message;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.util.SDKClient;

@Component
public class SmsTaskExcutor implements ApplicationListener
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected boolean start = false;
	
	@Autowired
	private SmsLogService smsLogService; 
	
	public void onApplicationEvent(ApplicationEvent event)
	{
		 if (event instanceof ContextRefreshedEvent && !start) {
	            logger.info("一彩票短信服务启动...");
//	            CommonScheduledThreadPoolExecutor.getInstance().execute(createSmsSendTask());
	            start = true;
	        }
	}
	
	public Runnable createSmsSendTask()
	{
		
		return new Runnable()
		{
			public void run()
			{

                logger.info("短信发送队列启动!");
                while (true) {
                	try {
	                	List<SmsLog> smsLogList = smsLogService.findSmsLogByState(SmsLogState.WAITING);
	                	if(smsLogList!=null&&smsLogList.size()!=0)
	                	{
	                		for (SmsLog smsLog : smsLogList)
	                		{
	                             int result = SDKClient.getClient().sendSMS(new String[]{smsLog.getMobile()}, smsLog.getContent(), 5);
	                             if(result == 0)
	                             {
	                             	smsLog.setState(SmsLogState.SENDED);
	                             	smsLog.setSuccessTime(Calendar.getInstance());
	                             	smsLog.setMark("发送成功");
	                             	smsLogService.updateSmsLog(smsLog);
	                             }
	                             else
	                             {
	                            	 smsLog.setState(SmsLogState.FAILURE);
	                            	 smsLog.setMark("发送失败,错误码："+result);
		                             smsLogService.updateSmsLog(smsLog);
	                             }
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
                        String description = "短信队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
			}
		};
	}

}
