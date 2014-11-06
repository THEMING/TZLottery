package com.xsc.lottery.task.customer;

import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.CustomerService;

@Component
public class CustomerClearEmailAndSmsAcceptExecutor implements ApplicationListener
{

	private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor.getInstance().newSingleThreadScheduledExecutor();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerService customerService;
	
	public void onApplicationEvent(ApplicationEvent arg0)
	{
		CommonScheduledThreadPoolExecutor.getInstance().execute(createClearTask());
	}
	
	public Runnable createClearTask()
	{
		return new Runnable()
		{
			public void run()
			{
				Calendar curCalendar = Calendar.getInstance();
				//计划是每月1号3点执行
				if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)==1){//判断是否1号
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 3){//判断是否3点之前，是的话设定今天3点执行一次
						curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 3, 0, 0);
					}else{//否则下个月1号3点执行
						curCalendar.add(Calendar.MONTH, 1);
						curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), 1, 3, 0, 0);
					}
				}else{//下个月1号3点执行
					curCalendar.add(Calendar.MONTH, 1);
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), 1, 3, 0, 0);
				}
				long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				threadExec.schedule(doClearTask(), delay/1000, TimeUnit.SECONDS);
				
			}
		};
	}
	
	public Runnable doClearTask()
	{
		return new Runnable()
		{
			public void run()
			{
				customerService.clearCustomerEmailAndSmsAcceptNum(null);
				
				//布置下一个月1号凌晨3点的任务
				Calendar curCalendar = Calendar.getInstance();
				curCalendar.add(Calendar.MONTH, 1);
				curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), 1, 3, 0, 0);
				long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				threadExec.schedule(doClearTask(), delay/1000, TimeUnit.SECONDS);
				//threadExec.schedule(doCpsReportTask(), 10, TimeUnit.SECONDS);
			}
		};
	}

}
