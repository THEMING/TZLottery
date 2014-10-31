package com.xsc.lottery.task.jczq;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.util.DateUtil;

/*
 * 竞彩足球任务
 */
@Component
public class JczqTaskExcutor implements ApplicationListener
{
	@Autowired
	private MatchArrangeService matchArrangeService;
	
	@Autowired
    public LotteryOrderService orderService;
	
	@Autowired
    public LotteryTermService termService;
	
	@Autowired
    private TicketBusinessFactory ticketBusinessFactory;
	
	protected boolean start = false;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor
    			.getInstance().newSingleThreadScheduledExecutor();
	
	/** 新增加的比赛 */
    protected LinkedBlockingQueue<MatchArrange> newMatchQueue = new LinkedBlockingQueue<MatchArrange>();
    
	public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("竞彩足球守护进程启动......");
            CommonScheduledThreadPoolExecutor.getInstance().execute(createWatchNewMatchTask());
            start = true;
        }
    }
	
	private Runnable createWatchNewMatchTask()
	{
		//FIXME
		List<MatchArrange> matches = matchArrangeService.getCurrentMatchArrangeForJCZQ();
        
        for (MatchArrange match : matches) {
        	newMatchQueue.offer(match);
        }
        
		return new Runnable()
        {
            public void run()
            {
                logger.info("竞彩足球 -- 新增比赛监视启动!");
                while(true) {
	                try {
	                	long delay = 0;
						MatchArrange match = newMatchQueue.take();
						
						delay = (match.getStopSaleTime().getTimeInMillis() - 
								System.currentTimeMillis()) / 1000;
						addTask(getStopTogetherBuyTask(match), delay);
						logger.info(DateUtil.toMM_DD_HH_mm_ss(match.getStopSaleTime()) + " 执行以场次" 
								+ match.getBoutIndex() + " 为最早时间的合买截止任务");
					}
	                catch (Exception e) {
	                	logger.info("添加竞彩足球的合买截止任务异常", e);
					}
                }
            }
        };
	}
	
	public void addNewMatch(MatchArrange match)
    {
		newMatchQueue.offer(match);
    }
    
    /** 为每一场比赛添加新的定时任务 */
    private void  addTask(Runnable task, long delay)
    {
        threadExec.schedule(task, delay, TimeUnit.SECONDS);
    }
    
    /** 布置合买截止任务 */
    private Runnable getStopTogetherBuyTask(final MatchArrange match)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                	Calendar stopTime = Calendar.getInstance();
            		stopTime.setTime(match.getStopSaleTime().getTime());
            		Calendar currTime = Calendar.getInstance();
            		stopTime.add(Calendar.MINUTE, -10);
            		if(stopTime.after(currTime)) //stop time changed to late time
            		{
            			logger.info("match" + match.getBoutIndex() + "changed to late！");
            			return;
            		}
            		
            		if(match.getStatus()!=RaceStatus.销售中) { //already stopped
            			logger.info("match" + match.getBoutIndex() + "already stopped！");
            			return;
            		}
                	logger.info("开始执行场次编号为" + match.getBoutIndex() + "的合买截止任务！");
                    LotteryTerm currentTerm = termService.getCurrentTerm(LotteryType.竞彩足球);
                    //根据该比赛产生订单
                    List<Order> orders = matchArrangeService.stopToSaleCreateHm(match);
                    TicketTreatmentWork ttw = ticketBusinessFactory.getTreatmentTicketByType(currentTerm.getOutPoint());
	                for(Order order : orders) {
	                	//解决竞彩合买保底不出票的问题
                        //ttw.addTaker(order);
	                    ttw.putOrderToQueue(order);
	                }
                }
                catch (Exception e) {
                	logger.info("场次编号" + match.getBoutIndex() + "的销售/合买截止任务因异常挂掉", e);
                }
            }
        };
    }
}
