package com.xsc.lottery.task.customer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.EmailLogService;
import com.xsc.lottery.util.TemplateUtil;

@Component
public class CustomerMonthlyBillTaskExecutor implements ApplicationListener
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor.getInstance().newSingleThreadScheduledExecutor();
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmailLogService emailLogService;

	public void onApplicationEvent(ApplicationEvent event)
	{
		logger.info("一彩票月度账单服务启动...");
        CommonScheduledThreadPoolExecutor.getInstance().execute(createMonthlyBillTask());
	}
	
	public Runnable createMonthlyBillTask()
	{
		return new Runnable()
		{
			public void run()
			{
				Calendar curCalendar = Calendar.getInstance();
				//计划是每月1号1点执行
				if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)==1){//判断是否1号
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 1){//判断是否1点之前，是的话设定今天1点执行一次
						curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), curCalendar.get(Calendar.DATE), 1, 0, 0);
						
					}else{//否则下个月1号1点执行
						curCalendar.add(Calendar.MONTH, 1);
						curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), 1, 1, 0, 0);
					}
				}else{//下个月1号1点执行
					curCalendar.add(Calendar.MONTH, 1);
					curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), 1, 1, 0, 0);
					curCalendar.set(Calendar.DATE, 1);
				}
				long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				threadExec.schedule(doCreateBillTask(), delay/1000, TimeUnit.SECONDS);
//				threadExec.schedule(doCreateBillTask(), 10, TimeUnit.SECONDS);
				
			}
		};
	}
	
	public Runnable doCreateBillTask()
	{
		return new Runnable()
		{
			public void run()
			{
				Map map = new HashMap();
				
				Calendar curCalendarS = Calendar.getInstance();
				curCalendarS.add(Calendar.MONTH, -1);
				curCalendarS.set(curCalendarS.get(Calendar.YEAR), curCalendarS.get(Calendar.MONTH), 1, 0, 0, 0);
				curCalendarS.set(Calendar.DATE, 1);
				
				Calendar curCalendarE = Calendar.getInstance();
				curCalendarE.set(curCalendarE.get(Calendar.YEAR), curCalendarE.get(Calendar.MONTH), 1, 0, 0, 0);
				curCalendarE.set(Calendar.DATE, 1);
				
				map.put("stime",curCalendarS);
				map.put("etime",curCalendarE);
				
				List<Object[]> li = customerService.getBillCustomer(map);
				for(int i=0;i<li.size();i++){
					Object[] oo = (Object[])li.get(i);//oo[0]代表用户id,oo[1]代表总花费,oo[2]代表总中奖,oo[3]表示用户名,oo[4]表示邮箱,oo[5]表示真实姓名
					EmailLog el = new EmailLog();
					el.setContent(TemplateUtil.getMonthlyBill(oo[0].toString(), curCalendarS.get(Calendar.YEAR)+"", (curCalendarS.get(Calendar.MONTH)+1)+""));
//					Order o = new Order();
//					o.setStatus(OrderStatus.出票成功);
//					o.setNumberNo("122222223333445555");
//					o.setId(new Long(3654));
//					Customer c = new Customer();
//					c.setRealName("ming");
//					c.setId(new Long(123456));
//					o.setCustomer(c);
//					el.setContent(TemplateUtil.getOrderDetailEmailContent(o));
					el.setEmail(oo[4].toString());
					el.setTitle("您的"+curCalendarS.get(Calendar.YEAR)+"年"+(curCalendarS.get(Calendar.MONTH)+1)+"月账单");
					el.setUsername(oo[3].toString());
					el.setSendUserName("一彩票");
					el.setState(EmailState.NOTSEND);
					el.setSendTime(new Date());
					emailLogService.saveOrUpdate(el);
				}
				
				//布置下一个月1号凌晨1点的任务
				Calendar curCalendar = Calendar.getInstance();
				curCalendar.add(Calendar.MONTH, 1);
				curCalendar.set(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH), 1, 1, 0, 0);
				long delay = curCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				threadExec.schedule(doCreateBillTask(), delay/1000, TimeUnit.SECONDS);
			}
		};
	}


}
