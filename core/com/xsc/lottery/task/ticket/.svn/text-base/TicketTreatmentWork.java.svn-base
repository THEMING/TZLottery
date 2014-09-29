package com.xsc.lottery.task.ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;

import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.OrderQueue;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.TermTypeConfig;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TicketSPStatus;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;
import com.xsc.lottery.service.business.OrderQueueService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.service.business.TermConfigService;
import com.xsc.lottery.task.email.Email369TaskExcutor;
import com.xsc.lottery.task.message.MessageTaskExcutor;
import com.xsc.lottery.util.DateUtil;

public abstract class TicketTreatmentWork implements ApplicationListener
{
    // 用于计时，避免重复发送短信 至少5分钟一次
    private Calendar xtTime = Calendar.getInstance();
    
    @Autowired
    private LotteryOrderService orderService;
    
    @Autowired
	private SmsLogService smsLogService;

    @Autowired
    private LotteryPlanService planService;

    @Autowired
    private TicketBusinessFactory ticketBusinessFactory;

    @Autowired
    private TermConfigService termConfigService;
    
    @Autowired
    private Email369TaskExcutor email369TaskExcutor;
    
    @Autowired
    private OrderQueueService orderQueueService;
    
    @Autowired
    private MessageTaskExcutor messageTaskExcutor;
    
    @Autowired
    private AdminMobileService adminMobileService;
    
    /** 待拆票队列 */
    protected LinkedBlockingQueue<Order> takerQueue = new LinkedBlockingQueue<Order>();

    /** 待送票队列 */
    protected LinkedBlockingQueue<Order> deliveQueue = new LinkedBlockingQueue<Order>();

    /** 待送票二级队列 */
    protected LinkedBlockingQueue<Order> deliveSecondLevelQueue = new LinkedBlockingQueue<Order>();
    
    /** 待检票队列 */
    protected LinkedBlockingQueue<Order> checkQueue = new LinkedBlockingQueue<Order>();

    /** 待查SP的ticket队列 */
    protected LinkedBlockingQueue<Ticket> checkSPTicketQueue = new LinkedBlockingQueue<Ticket>();
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected boolean start = false;

    protected TicketCheckBucket bucket = new TicketCheckBucket();
    
    public abstract SendTicketPlat getTicketPlat();
    public abstract void putOrderToQueue(Order order);

    /** 拆票 */
    protected abstract List<Ticket> takeTicket(Order order,
            List<PlanItem> planItems) throws Exception;
    
    /** 到orderqueue取出相应的order */
    protected abstract List<OrderQueue> getOrderQueue() throws Exception;
     
    /** 删除orderqueue中status=1的值 */
    protected abstract void deleteOrderQueue(List<OrderQueue> allList) throws Exception;

    /** 检查中奖奖金比对 */
    public abstract String getJiangjin(Ticket ticket);

    /** 获取开奖结果 */
    public abstract void getOpenResult(LotteryTerm term);

    /** 获取当期中奖订单 */
    public abstract List<winTicketDis> getWinTicketByTerm(LotteryTerm term);

    /** 送票 */
    protected abstract void deliveTicket(Ticket ticket);
    
    /** 批量送票 */
    protected abstract void deliveTicket(List<Ticket> tickets);

    /** 检票 */
    protected abstract void checkTicket(Ticket ticket);
    
    /** 批量检票 */
    protected abstract void checkTicket(List<Ticket> tickets);

    /** 判断是否可以往对方送票 */
    protected abstract boolean allowed(LotteryTerm term);
    
    /** 检查成功出票的SP值 */
    protected abstract void checkTicketSP(Ticket ticket);
    
    /** 注册 */
    @SuppressWarnings("unused")
    @PostConstruct
    private void register()
    {
        ticketBusinessFactory.registerTreatmentTicketInMap(this);
    }

    protected TermTypeConfig getTermTypeConfig(LotteryType lotteryType)
    {
        return termConfigService.getConfigByType(lotteryType);
    }

    protected void take(Order order)
    {
        try {
            order = orderService.findById(order.getId());
            List<PlanItem> planItems = planService.getPlanItemByPlanID(order.getPlan().getId());
            List<Ticket> tickets = takeTicket(order, planItems);
            orderService.saveSuccessTakeTicket(order, tickets, getTicketPlat().name());// 方案状态 ------ 委托中
            addDelive(order);// 拆完票放入一级拆票队列
        }
        catch (Exception e) {
            String description = getTicketPlat().name() + " 队列拆票异常, 请查看日志";
            logger.info(description, e);
            SystemWarningNotify.addWarningDescription(description);
        }
    }

    private void delive(Order order)
    {
        // 快开奖了,没送出去的不送了,立即交给检票队列
    	if (order.getType().equals(LotteryType.竞彩足球) || order.getType().equals(LotteryType.竞彩篮球)) {
    		if(order.getLastMatch().getOpenPrizeTime() == null ||
    		        order.getLastMatch().getOpenPrizeTime().before(DateUtil.now())) {
    			addCheck(order, 0);
            	return;
    		}
    	}
    	else if (order.getTerm().getOpenPrizeTime().before(DateUtil.now())) {
            addCheck(order, 0);
            return;
        }
        // 不受的话,延时一会后放到二级出票队列再试
        if (!allowed(order.getTerm())) {
            addSecondDelive(order, 90);
            return;
        }
        List<Ticket> tickets = orderService.getTicketByOrder(order);
        int i = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getStatus().equals(TicketStatus.未送票)) {
            	
         
            	if (i < 500) {
            		deliveTicket(ticket);
                	if (!ticket.getStatus().equals(TicketStatus.未送票)) {
                		orderService.saveTicket(ticket);
                	}
				} else
					break;
				
				i++;
            }
        }
        // 查一下所有票,是不是都出票了
        if (isAllDelived(tickets)) {// 全部出票,则去检票
            order.setStatus(OrderStatus.出票中);
            orderService.save(order);
            if (isAllChecked(tickets)) {
                addCheck(order, 0);
            }
            else {
                addCheck(order, 30);
            }
        }
        else {
        	// 否则,放到二级出票队列再出票
            addSecondDelive(order, 20);
        }
    }

    /**
     * 检查票 到了开奖时间还在出票的票置为出票失败
     */
    private void check(Order order)
    {
    	List<Ticket> tickets = orderService.getTicketByOrder(order);
        List<Ticket> returnTickets = new ArrayList<Ticket>();
        boolean timeout = false;
        if (order.getType().equals(LotteryType.竞彩足球) || order.getType().equals(LotteryType.竞彩篮球)) {
            if(order.getLastMatch().getOpenPrizeTime() == null ||
                    order.getLastMatch().getOpenPrizeTime().before(DateUtil.now())) {
                timeout = true;
            }
        }
        else if(order.getTerm().getOpenPrizeTime().before(DateUtil.now())) {
        	timeout = true;
        }
        if (timeout) {
            logger.info("方案编号为：" + order.getPlan().getNumberNo() + "的票出票时间超于开奖时间");
            
    		xtTime.add(Calendar.MINUTE, 5);
			Calendar c = Calendar.getInstance();
			if (xtTime.compareTo(c) < 0) {
				
				String str = "【一彩票网】用户名为：" + order.getCustomer().getNickName() + "购买的" + order.getTerm().getTermNo() + "期" + order.getType() + "，订单ID为："+ order.getId() +"，订单号为：" + order.getPlan().getNumberNo() + "的订单，开奖时间到仍在出票中！";
				System.out.println("5分钟一次：" + c.getTime());
				List<AdminMobile> adminMobiles = adminMobileService.getAllAdminMobile();
				for (AdminMobile adminMobile : adminMobiles) {
					smsLogService.saveSmsLog(adminMobile.getMobile(), str, null,SmsLogType.WARN);
					//messageTaskExcutor.addNotifySM(adminMobile.getMobile(), str);
				}
				xtTime = c;
				xtTime.add(Calendar.MINUTE, 5);
			}
			return;
           
            
            // 可以看是不是有退票了
            /*
            for (Ticket ticket : tickets) {
            	if(!ticket.getStatus().equals(TicketStatus.已出票)) {
            		
	                ticket.setStatus(TicketStatus.出票失败);
	                ticket.setOtherMsg("出票超过开奖时间");
	                orderService.saveTicket(ticket);
	                returnTickets.add(ticket);
	                
            	}
            }
            */
         
        }
        else {
			int times = tickets.size() / 50;
			for(int i = 0; i < times; i++) {
				List<Ticket> tics = tickets.subList(50*i, 50*(i+1));
				checkTicket(tics);
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					logger.info("check sleep exception");
				}
			}
			
			List<Ticket> tics = tickets.subList(50*times, tickets.size());
			checkTicket(tics);
			
			for (Ticket ticket : tickets) {
                if (!ticket.getStatus().equals(TicketStatus.出票中)) {
    				//（1）如果已经出票，并且需要检查赔率，设置检查赔率状态
                	boolean bNeedToCheckSP = false;
    				if (ticket.getStatus().equals(TicketStatus.已出票)) {
    					if(needToCheckSP(ticket))
    					{
    						bNeedToCheckSP = true;
    						ticket.setSpstatus(TicketSPStatus.需要检查SP);
    					}
    				}
    				//（2）出票状态改变，需要保存
                    orderService.saveTicket(ticket);
                    //（3）已经出票，并且需要检查赔率，加入赔率检查列表，去检查赔率。
                    //该段不能和（1）合并，以防检查赔率比保存Ticket更快。
    				if(bNeedToCheckSP)
    				{
    					addCheckSP(ticket, 0);
    				}
    			}
           }
			
            if (!isAllChecked(tickets)) {// 如果存在没检票的,过一会再检
                addCheck(order, 30);
                return;
            }
            // 可以看是不是有退票了
            for (Ticket ticket : tickets) {
                if (!ticket.getStatus().equals(TicketStatus.已出票)) {
                    returnTickets.add(ticket);
                }
            }
        }
        finishTicketBusiness(order, returnTickets);
        OrderStatus oStatus = order.getStatus();
        if(oStatus == OrderStatus.出票失败 || oStatus == OrderStatus.部分出票成功)
        {
            email369TaskExcutor.addNotifyOrder(order);
        }
    }

    // 完成注单的票务业务
    public void finishTicketBusiness(Order order, List<Ticket> returnTickets)
    {
        try {
            orderService.finishTicketBusiness(order, returnTickets);
        }
        catch (Exception e) {
            if (e instanceof HibernateOptimisticLockingFailureException
                    || e.getClass().getName().equals(
                       StaleObjectStateException.class.getName())) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e1) {
                }
                finishTicketBusiness(order, returnTickets);
                e.printStackTrace();
            }
        }

    }

    public void addTaker(Order order)
    {
        takerQueue.offer(order);
    }

    private void addDelive(Order order)
    {
        deliveQueue.offer(order);
    }

    private boolean isAllDelived(List<Ticket> tickets)
    {
        for (Ticket t : tickets) {
            if (t.getStatus().equals(TicketStatus.未送票)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllChecked(List<Ticket> tickets)
    {
        for (Ticket t : tickets) {
            if (t.getStatus().equals(TicketStatus.出票中)) {
                return false;
            }
        }
        return true;
    }

    private void addSecondDelive(final Order order, int delay)
    {
        Runnable task = new Runnable()
        {
            public void run()
            {
                deliveSecondLevelQueue.offer(order);
            }
        };
        CommonScheduledThreadPoolExecutor.getInstance().schedule(task, delay,
                TimeUnit.SECONDS);
    }

    private void addCheck(final Order order, int delay)
    {
        Runnable task = new Runnable()
        {
            public void run()
            {
                checkQueue.offer(order);
            }
        };
        CommonScheduledThreadPoolExecutor.getInstance().schedule(task, delay,
                TimeUnit.SECONDS);
    }

    public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("{} 处理票进程启动........", this.getTicketPlat().name());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createTakerTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createDeliveTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createCheckTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createSecondLevelDeliveTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createCheckSPTask());
            start = true;
        }
    }

    /** 拆票队列线程 */
    private Runnable createTakerTask()
    {
        return new Runnable()
        {
            public void run()
            {
                logger.info(getTicketPlat().name() + " 拆票队列启动!");
                while (true) {
                    try {
                    	Calendar thetime=Calendar.getInstance();
                    	List<OrderQueue> allList=null;
                    	//6-7 delay to send ticket
                    	if((thetime.get(Calendar.HOUR_OF_DAY)!=6)&&(thetime.get(Calendar.HOUR_OF_DAY)!=7))
                    	{
                    		allList = getOrderQueue(); 
                    	}
                    	if(allList == null || allList.size() == 0)
                    	{
                    		try { 
                    			Thread.sleep(5000);
                    		} catch (InterruptedException e) { 
                                e.printStackTrace(); 
                    		}                   		
                    	}
                    	else
                    	{
                    		deleteOrderQueue(allList);
                    	}                   	
                    }
                    catch (Exception e) {
                        String description = getTicketPlat().name() + " 队列拆票异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }

    /** 送票队列线程 */
    private Runnable createDeliveTask()
    {
        List<Order> orders = orderService.getOrderByStatusAndTicketBusinessName(
        		OrderStatus.待出票, getTicketPlat().name());
        
        for (Order order : orders) {
            deliveQueue.offer(order);
        }
        
        return new Runnable()
        {
            public void run()
            {
                logger.info(getTicketPlat().name() + " 送票队列启动!");
                while (true) {
                	Order order = null;
                    try {
                        order = deliveQueue.take();
                        delive(order);
                    }
                    catch (Exception e) {
                    	if(order.getStatus().equals(OrderStatus.待出票)) {
                    		addSecondDelive(order, 90);
                    	}
                        String description = getTicketPlat().name()
                                + " 送票队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }

    /** 送票队列线程 */
    private Runnable createSecondLevelDeliveTask()
    {
        return new Runnable()
        {
            public void run()
            {
                logger.info(getTicketPlat().name() + " 二级送票队列启动!");
                while (true) {
                    Order order = null;
                    try {
                        order = deliveSecondLevelQueue.take();
                        delive(order);
                    }
                    catch (Exception e) {
                        if(order.getStatus().equals(OrderStatus.待出票)) {
                            addSecondDelive(order, 90);
                        }
                        String description = getTicketPlat().name()
                                + " 二级送队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }

    /** 检票队列线程 */
    private Runnable createCheckTask()
    {
        List<Order> orders = orderService.getOrderByStatusAndTicketBusinessName(
                OrderStatus.出票中, getTicketPlat().name());
        
        for (Order order : orders) {
            checkQueue.offer(order);
        }
        
        return new Runnable()
        {
            public void run()
            {
                logger.info(getTicketPlat().name() + " 检票队列启动!");
                while (true) {
                    try {
                        Order order = checkQueue.take();
                        check(order);
                    }
                    catch (Exception e) {
                        String description = getTicketPlat().name()+ " 检票队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    /** 竞彩检查即时SP线程 */
    private Runnable createCheckSPTask()
    {
    	List<Ticket> tickets = orderService.getTicketsNeedCheckSP();
    	
    	for (Ticket ticket : tickets) {
    		checkSPTicketQueue.offer(ticket);
        }
    	
        return new Runnable()
        {
            public void run()
            {
                logger.info(getTicketPlat().name() + " 检查赔率线程启动!");
                while (true) {
                    try {
                        Ticket ticket = checkSPTicketQueue.take();
                        checkSP(ticket);
                    }
                    catch (Exception e) {
                        String description = getTicketPlat().name()
                                + " 二级送队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private void checkSP(Ticket ticket)
    {
    	checkTicketSP(ticket);
    	if (ticket.getRatio() != null) {
    		ticket.setSpstatus(TicketSPStatus.获取SP成功);
    		orderService.saveTicket(ticket);
    	}
    	else{
    		addCheckSP(ticket, 30);
    	}
    }
    private boolean needToCheckSP(final Ticket ticket)
    {
    	if (ticket.getType().equals(LotteryType.竞彩足球) && 
    			ticket.getRatio() == null &&
    			!ticket.getContent().split("\\|")[2].split("\\*")[0].equals("1")) { // 非单场过关
    		return true;
    	}
    	if (ticket.getType().equals(LotteryType.竞彩足球) && 
    			ticket.getRatio() == null &&
    			ticket.getContent().split("\\|")[0].equals("CBF")&&
    			ticket.getContent().split("\\|")[2].split("\\*")[0].equals("1")) { // 单场过关
    		return true;
    	}    	
    	if (ticket.getType().equals(LotteryType.竞彩篮球) && 
    			ticket.getRatio() == null &&
    			!ticket.getContent().split("\\|")[2].split("\\*")[0].equals("1")) { // 非单场过关
    		return true;
    	}
    	if (ticket.getType().equals(LotteryType.竞彩篮球) && 
    			ticket.getRatio() == null &&
    			ticket.getContent().split("\\|")[0].equals("SFC")&&
    			ticket.getContent().split("\\|")[2].split("\\*")[0].equals("1")) { // 单场过关
    		return true;
    	}
    	return false;
    }
    
    private void addCheckSP(final Ticket ticket, int delay)
    {
        Runnable task = new Runnable()
        {
            public void run()
            {
            	if(needToCheckSP(ticket))
            	{
            		checkSPTicketQueue.offer(ticket);
            	}
            }
        };
        CommonScheduledThreadPoolExecutor.getInstance().schedule(task, delay,
                TimeUnit.SECONDS);
    }
    
    public class winTicketDis
    {
        public String getOrderId()
        {
            return orderId;
        }

        public void setOrderId(String orderId)
        {
            this.orderId = orderId;
        }

        public BigDecimal getWinMoney()
        {
            return winMoney;
        }

        public void setWinMoney(BigDecimal winMoney)
        {
            this.winMoney = winMoney;
        }

        public BigDecimal getTaxMoney()
        {
            return taxMoney;
        }

        public void setTaxMoney(BigDecimal taxMoney)
        {
            this.taxMoney = taxMoney;
        }

        public winTicketDis(String orderId, BigDecimal winMoney,
                BigDecimal taxMoney)
        {
            this.orderId = orderId;// 订单表ID
            this.winMoney = winMoney;
            this.taxMoney = taxMoney;
        }

        private String orderId;
        private BigDecimal winMoney = BigDecimal.ZERO;;
        private BigDecimal taxMoney = BigDecimal.ZERO;;
    }
    
    //从把order放进orderqueue中
    /*
    public void putOrderToQueue(Order order)
    {
    	OrderQueue orderQueue = new OrderQueue();
    	orderQueue.setOrderId(order.getId());
    	orderQueue.setStatus(0);
    	orderQueue.setSendTicketPlat(0);
    	orderQueueService.save(orderQueue);
    }
    */
    //修改orderqueue的相应的状态
    public void changeStatus(Long orderId, int status)
    {
    	OrderQueue orderQueue = orderQueueService.getOrderQueueByOrderId(orderId);
    	if(orderQueue == null)
    	{
    		return;
    	}
    	else
    	{
    		orderQueue.setStatus(status);
        	orderQueueService.save(orderQueue);
    	}
    }
    
    /**
     * <pre>
     *   期次查询
     * </pre>
     * @param type  玩法
     */
    protected abstract void queryTerm(LotteryType type);
}
