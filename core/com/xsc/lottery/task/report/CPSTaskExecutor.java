package com.xsc.lottery.task.report;

import java.text.ParseException;
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
import com.xsc.lottery.service.business.CpsReportService;

/**
 * 佣金统计
 * @author caipiao
 *
 */
@Component
public class CPSTaskExecutor implements ApplicationListener
{
	private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor.getInstance().newSingleThreadScheduledExecutor();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CpsReportService cpsReportService;
	
	public void onApplicationEvent(ApplicationEvent event)
	{
		 CommonScheduledThreadPoolExecutor.getInstance().execute(createCpsReportTask());
	}
	
	public Runnable createCpsReportTask()
	{
		return new Runnable()
		{
			public void run()
			{
				//第一次布置凌晨3点启动任务
				Calendar curCalendar = Calendar.getInstance();
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) <= 3)
				{
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 3, 0, 0);
				}
				else
				{
					curCalendar.add(Calendar.DATE, 1);
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 3, 0, 0);
				}
				long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				threadExec.schedule(doCpsReportTask(), delay/1000, TimeUnit.SECONDS);
				//threadExec.schedule(doCpsReportTask(), 10, TimeUnit.SECONDS);
			}
		};
	}
	
	public Runnable doCpsReportTask()
	{
		return new Runnable()
		{
			public void run()
			{
				try
				{
					cpsReportService.doCpsDayReport();
					//布置第二天凌晨3点的任务
					Calendar curCalendar = Calendar.getInstance();
					curCalendar.add(Calendar.DATE, 1);
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 3, 0, 0);
					long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
					threadExec.schedule(doCpsReportTask(), delay/1000, TimeUnit.SECONDS);
					//threadExec.schedule(doCpsReportTask(), 10, TimeUnit.SECONDS);
				} catch (ParseException e)
				{
					logger.error("统计报表任务异常",e);
					e.printStackTrace();
				}
			}
		};
	}

}
