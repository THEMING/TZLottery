package com.xsc.lottery.task.email;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.EmailUtil;

@Component
public class Email369TaskExcutor// implements ApplicationListener
{
    @Autowired
    public LotteryOrderService orderService;
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected boolean start = false;
    
    /** 邮件通知订单队列 */
    protected LinkedBlockingQueue<Order> notifyOrderQueue = new LinkedBlockingQueue<Order>();
    
    /** 邮件通知中奖队列 */
    protected LinkedBlockingQueue<Order> notifyWinQueue = new LinkedBlockingQueue<Order>();
    
    /** 邮件通知充值队列 */
    protected LinkedBlockingQueue<PaymentRequest> notifyPaymentQueue = new LinkedBlockingQueue<PaymentRequest>();
    
    public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("一彩票邮件服务启动...");
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createNotifyOrderTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createNotifyPaymentTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createNotifyWinTask());
            start = true;
        }
    }
    
    public void addNotifyOrder(Order order)
    {
    	notifyOrderQueue.offer(order);
    }
    public void addNotifyWin(Order order)
    {
    	notifyWinQueue.offer(order);
    }
    /** 邮件通知用户出票情况 */
    private Runnable createNotifyOrderTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        Order order = notifyOrderQueue.take();
                        notifyOrder(order);
                    }
                    catch (Exception e) {
                        String description = "用户订单邮件通知队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    private Runnable createNotifyWinTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        Order order = notifyWinQueue.take();
                        notifyWin(order);
                    }
                    catch (Exception e) {
                        String description = "用户订单邮件通知队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    private void notifyWin(Order order)
    {
        logger.info("Email order to " + order.getCustomer().getNickName());
        List<Ticket> tickets = orderService.getTicketByOrder(order);
        
        try {
            String title = "一彩票客服中心--";
            String content = order.getCustomer().getNickName() + "，&nbsp;您好： <br/><br/>&emsp;一彩票客服中心通知您: ";
            content += "&nbsp;----您的订单号为 " + order.getPlan().getNumberNo() + " 的订单 " + "中奖了"+ " ----";
            content += "&emsp;中奖金额为：<br/><br/>"+order.getWinMoney().longValue()+"元";
            
            content += "<br/><br/>一彩票祝您购彩愉快!";
            content += "<br/>";
            
            EmailUtil.sendEmail(order.getCustomer().getEmail(), title, content);
        }
        catch(Exception e) {
            logger.info("Email get exception : " + e.getMessage());
        }
    }
    
    //FIXME 根据用户的设置来发送邮件
    private void notifyOrder(Order order)
    {
        logger.info("Email order to " + order.getCustomer().getNickName());
        List<Ticket> tickets = orderService.getTicketByOrder(order);
        
        try {
            String title = "一彩票客服中心--" + order.getStatus();
            String content = order.getCustomer().getNickName() + "，&nbsp;您好： <br/><br/>&emsp;一彩票客服中心通知您: ";
            content += "&nbsp;----您的订单号为 " + order.getPlan().getNumberNo() + " 的订单 " + order.getStatus() + " ----";
            content += "&emsp;具体信息如下：<br/><br/>";
            
            for(Ticket t : tickets) {
                content += "&emsp;" + t.getContent() + " &emsp;";
                //if(t.getOtherMsg() != null) {
                  //  content += t.getOtherMsg() + "<br/>";
                //}
            }
            
            content += "<br/><br/>一彩票祝您购彩愉快!";
            content += "<br/>";
            
            EmailUtil.sendEmail(order.getCustomer().getEmail(), title, content);
        }
        catch(Exception e) {
            logger.info("Email get exception : " + e.getMessage());
        }
    }
    
    public void addNotifyPayment(PaymentRequest payReuqest)
    {
    	notifyPaymentQueue.offer(payReuqest);
    }
    
    /** 邮件通知用户出票情况 */
    private Runnable createNotifyPaymentTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                    	PaymentRequest payReuqest = notifyPaymentQueue.take();
                    	notifyPayment(payReuqest);
                    }
                    catch (Exception e) {
                        String description = "用户充值邮件通知队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private void notifyPayment(PaymentRequest payReuqest)
    {
    	if(!payReuqest.isFinish()) {
    		return;
    	}
    	
        logger.info("Email payment to " + payReuqest.getCustomer().getNickName());
        try {
            String title = "一彩票客服中心--充值成功";
            String content = payReuqest.getCustomer().getNickName() + "，&nbsp;您好： <br/><br/>&emsp;一彩票客服中心通知您: ";
            content += "<br/><br/>&emsp;";
            content += "您于 " + DateUtil.toCNyyyy_MM_dd_HH_mm(payReuqest.getPayTime()) + " 从 " + payReuqest.getBank();
            content += " 充值" + payReuqest.getMoney() + "元 成功!";
            content += "<br/>&emsp;您的账户目前总额为 ：" + payReuqest.getCustomer().getWallet().getTotalMoney() + "元!";
            content += "<br/><br/>一彩票祝您购彩愉快!";
            content += "<br/>";
            
            EmailUtil.sendEmail(payReuqest.getCustomer().getEmail(), title, content);
        }
        catch(Exception e) {
            logger.info("Email get exception : " + e.getMessage());
        }
    }
}
