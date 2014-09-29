package com.xsc.lottery.task.term;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;

@Component
public class AllTermJobExecutor
{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BlockingQueue<LotteryTerm> termStopSaleQueue = new LinkedBlockingQueue<LotteryTerm>();
    private final BlockingQueue<LotteryTerm> termStartSaleQueue = new LinkedBlockingQueue<LotteryTerm>();

    @Autowired
    private TicketBusinessFactory ticketBusinessFactory;
    
    @Autowired
    private LotteryTermService termService;

    public AllTermJobExecutor()
    {
        CommonScheduledThreadPoolExecutor.getInstance().execute(termStartSale());
        CommonScheduledThreadPoolExecutor.getInstance().execute(termStopSale());
    }

    public void addStopSaleJop(LotteryTerm term)
    {
        termStopSaleQueue.offer(term);
    }

    public void addStartSaleJop(LotteryTerm term)
    {
        termStartSaleQueue.offer(term);
    }
    
    //彩期销售，处理追号
    private Runnable termStartSale()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        LotteryTerm term = termStartSaleQueue.take();
                        List<Order> list = termService.startChase(term);
                      /* use orderqueue instead TicketTreatmentWork ttw = ticketBusinessFactory
                                .getTreatmentTicketByType(term.getOutPoint());
                        for(Order order : list) {
                            ttw.addTaker(order);
                        }*/
                    }
                    catch (Exception e) {
                        logger.warn("彩期开始销售任务出现异常：", e);
                        SystemWarningNotify.addWarningDescription("彩期开始销售任务出现异常");
                        e.printStackTrace();
                    }
                }
            }
        };
    }

	//彩期截止，处理合买
    private Runnable termStopSale()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        LotteryTerm term = termStopSaleQueue.take();
                        try {
                            List<Order> list = termService.stopToSaleCreateHm(term);
                            TicketTreatmentWork ttw = ticketBusinessFactory
                                    .getTreatmentTicketByType(term.getOutPoint());
                            for(Order order : list) {
                                //ttw.addTaker(order);
                            	ttw.putOrderToQueue(order);
                            }
                        }
                        catch (Exception e) {
                            if (e instanceof HibernateOptimisticLockingFailureException
                                    || e.getClass().getName().equals(
                                            StaleObjectStateException.class
                                                    .getName())) {
                                String description = term.toString()
                                        + " 期销截止用户钱包退款出现异常";
                                logger.warn(description, e);
                                SystemWarningNotify
                                        .addWarningDescription(description);
                                addStopSaleJop(term);
                                e.printStackTrace();
                            }
                            else {
                                throw e;
                            }

                        }
                        Thread.sleep(10);
                    } 
                    catch (Exception e) {
                        logger.warn("彩期销售截止任务出现异常：", e);
                        SystemWarningNotify.addWarningDescription("彩期销售截止任务出现异常");
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
