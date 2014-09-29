package com.xsc.lottery.service.listener.active;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.active.ActivityDetail;
import com.xsc.lottery.entity.active.ActivityDetailType;
import com.xsc.lottery.entity.active.ActivityOrderType;
import com.xsc.lottery.entity.active.ActivityStatus;
import com.xsc.lottery.entity.active.ActivityType;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.OrderResult;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.active.ActivityService;
import com.xsc.lottery.service.business.CustomerService;

@Component
public class ActivityListener implements ApplicationListener
{
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    public CustomerService customerService;
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected boolean start = false;
    
    /** 参与活动的订单队列 */
    protected LinkedBlockingQueue<Order> activeOrderQueue = new LinkedBlockingQueue<Order>();
    
    /** 参与活动的用户队列 -- 为首次注册等...*/
    protected LinkedBlockingQueue<Customer> activeCustomerQueue = new LinkedBlockingQueue<Customer>();
    
    /** 参与活动的充值队列 -- */
    protected LinkedBlockingQueue<PaymentRequest> activePaymentQueue = new LinkedBlockingQueue<PaymentRequest>();
    
    /** 手机绑定活动的充值队列 -- */
    protected LinkedBlockingQueue<Customer> activePhoneQueue = new LinkedBlockingQueue<Customer>();
    
    protected Map<Long, Activity> currentActivities = new HashMap<Long, Activity>();
    
    private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor
                .getInstance().newSingleThreadScheduledExecutor();
    
    public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("一彩票活动监听器启动...");
            
            startAllActivities();
            
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createActiveOrderTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createCheckOrderTask());
            
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createActivePaymentTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createActiveCustomerTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createActivePhoneTask());
            start = true;
        }
    }
    
    public void addPhoneCustomer(Customer customer)
    {
    	//activePhoneQueue.add(customer);
    } 
    
    public void addNewRegCustomer(Customer customer)
    {
      //  activeCustomerQueue.add(customer);
    }
    
    public void addNewOrder(Order order)
    {
     //   activeOrderQueue.add(order);
    }
    
    public void addNewPayment(PaymentRequest payment)
    {
       // activePaymentQueue.add(payment);
    }
    
    public void addNewActivity(Activity act)
    {
        Activity activity = currentActivities.get(act.getId());
        if(activity != null) {
            //FIXME 取消定时器后更改时间
            //activity.setEndTime(act.getEndTime());
            //activity.setStartTime(act.getStartTime());
            activity.setName(act.getName());
            activity.setShortName(act.getShortName());
            activity.setType(act.getType());
            activity.setGivenMoney(act.getGivenMoney());
            activity.setThreshold(act.getThreshold());
            activity.setOrderType(act.getOrderType());
        }
        else {
            activity = act;
        }
        
        if(activity.getStatus().equals(ActivityStatus.进行中)) {
            currentActivities.put(activity.getId(), activity);
            long delay = (activity.getEndTime().getTimeInMillis() - 
                    System.currentTimeMillis()) / 1000;
            addTask(stopActivity(activity), delay);
        }
        else if(activity.getStatus().equals(ActivityStatus.待进行) &&
                activity.getEndTime().after(Calendar.getInstance())) {
            long delay = (activity.getStartTime().getTimeInMillis() - 
                    System.currentTimeMillis()) / 1000;
            addTask(startActivity(activity), delay);
            
            delay = (activity.getEndTime().getTimeInMillis() - 
                    System.currentTimeMillis()) / 1000;
            addTask(stopActivity(activity), delay);
        }
    }
    
    /** 添加定时任务 */
    private void addTask(Runnable task, long delay)
    {
        threadExec.schedule(task, delay, TimeUnit.SECONDS);
    }
    
    /** 启动数据库中存留的所有服务 */
    private void startAllActivities()
    {
        currentActivities.clear();
        
        List<Activity> activities = activityService.getCurrentActivities();
        for(Activity activity : activities) {
            if(activity.getEndTime().after(Calendar.getInstance())) {
                //启动活动线程
                addNewActivity(activity);
            }
            else {
                activity.setStatus(ActivityStatus.已停止);
                activityService.save(activity);
            }
        }
    }
    
    private Runnable createActiveOrderTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        Order order = activeOrderQueue.take();
                        activeOrder(order);
                    }
                    catch (Exception e) {
                        String description = "队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    
    private Runnable createActivePhoneTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                       Customer Customer = activePhoneQueue.take();
                        activePhone(Customer);
                    }
                    catch (Exception e) {
                        String description = "队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }

        };
    }
    
    private void activePhone(Customer customer) {
    	 for(Activity activity : currentActivities.values()) {
             if(!activity.getStatus().equals(ActivityStatus.进行中)) continue;
             if(activity.getType().equals(ActivityType.手机绑定)) {            
                     ActivityDetail activityDetail = new ActivityDetail(
                             ActivityDetailType.手机绑定, activity);           
                     addMoney(customer, activity.getGivenMoney(), activity.getName());
                     activityDetail.setFinished(true); 
                     activityService.saveActivityDetail(activityDetail);
             }
         }
		
	}
    private Runnable createActiveCustomerTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        Customer customer = activeCustomerQueue.take();
                        activeCustomer(customer);
                    }
                    catch (Exception e) {
                        String description = "异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private Runnable createActivePaymentTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        PaymentRequest payReuqest = activePaymentQueue.take();
                        activePayReuqest(payReuqest);
                    }
                    catch (Exception e) {
                        String description = "队列异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private Runnable startActivity(final Activity activity)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                    logger.info("开始活动 " + activity.getName() + " ！");
                    activity.setStatus(ActivityStatus.进行中);
                    currentActivities.put(activity.getId(), activity);
                    activityService.save(activity);
                    logger.info("剩余 " + currentActivities.size() + " 活动！");
                }
                catch (Exception e) {
                    logger.info("开始活动 " + activity.getName() + " 异常 ！", e);
                }
            }
        };
    }
    
    private Runnable stopActivity(final Activity activity)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                    logger.info("停止活动 " + activity.getName() + " ！");
                    activity.setStatus(ActivityStatus.已停止);
                    currentActivities.remove(activity.getId());
                    activityService.save(activity);
                    logger.info("剩余 " + currentActivities.size() + " 活动！");
                }
                catch (Exception e) {
                    logger.info("停止活动 " + activity.getName() + " 异常 ！", e);
                }
            }
        };
    }
    
    //可以立即执行
    private void activeCustomer(Customer customer)
    {
        for(Activity activity : currentActivities.values()) {
            //FIXME 按活动时间判断
            if(!activity.getStatus().equals(ActivityStatus.进行中)) continue;
            
            if(activity.getType().equals(ActivityType.首次注册)) {
                ActivityDetail activityDetail = new ActivityDetail(
                        ActivityDetailType.注册活动, activity);
                activityDetail.setCustomer(customer);
                
                //立即执行
                addMoney(customer, activity.getGivenMoney(), activity.getName());
                activityDetail.setFinished(true);
                
                activityService.saveActivityDetail(activityDetail);
            }
        }
    }
    
    //可以立即执行
    private void activePayReuqest(PaymentRequest payReuqest)
    {
        for(Activity activity : currentActivities.values()) {
            //FIXME 按活动时间判断
            if(!activity.getStatus().equals(ActivityStatus.进行中)) continue;
            
            if(activity.getType().equals(ActivityType.充值额度)) {
                if(payReuqest.getMoney().compareTo(activity.getThreshold()) >= 0) {
                    ActivityDetail activityDetail = new ActivityDetail(
                            ActivityDetailType.充值活动, activity);
                    activityDetail.setPaymentRequest(payReuqest);
                    
                    //立即执行
                    addMoney(payReuqest.getCustomer(), activity.getGivenMoney(), activity.getName());
                    activityDetail.setFinished(true);
                    
                    activityService.saveActivityDetail(activityDetail);
                }
            }
        }
    }
    
    //不能立即执行, 由线程在规定的时间内扫描order
    private void activeOrder(Order order)
    {
        for(Activity activity : currentActivities.values()) {
            //FIXME 按活动时间判断
            if(!activity.getStatus().equals(ActivityStatus.进行中)) continue;
            
            ActivityType type = activity.getType();
            if(type.equals(ActivityType.首次购彩)) {
                if(!activityService.isFirstOrder(order)) continue;

                ActivityDetail activityDetail = new ActivityDetail(
                        ActivityDetailType.购彩活动, activity);
                activityDetail.setOrder(order);
                activityService.saveActivityDetail(activityDetail);
            }
            else if(type.equals(ActivityType.购彩额度)) {
                if(order.getCommunity() == null &&
                        order.getAmount().compareTo(activity.getThreshold()) >= 0) {
                    ActivityDetail activityDetail = new ActivityDetail(
                            ActivityDetailType.购彩活动, activity);
                    activityDetail.setOrder(order);
                    activityService.saveActivityDetail(activityDetail);
                }
            } else if(type.equals(ActivityType.普通)) {
                ActivityDetail activityDetail = new ActivityDetail(
                        ActivityDetailType.购彩活动, activity);
                activityDetail.setOrder(order);
                activityService.saveActivityDetail(activityDetail);
            }
            
        }
    }
    
    private Runnable createCheckOrderTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        Thread.sleep(1000*6000); //1分钟
                        logger.info("开始扫描购彩活动...");
                        List<ActivityDetail> activityDetails = activityService.
                            getActivityDetails(ActivityDetailType.购彩活动, false);
                        for(ActivityDetail activityDetail : activityDetails) {
                            execute(activityDetail);
                        }
                    }
                    catch (Exception e) {
                        String description = "CheckOrder异常,请查看日志";
                        logger.info(description, e);
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private void execute(ActivityDetail activityDetail)
    {
        boolean pass = false;
        ActivityDetailType type = activityDetail.getActDetailType();
        
       
        
        if(type.equals(ActivityDetailType.购彩活动)) {
        	
        	 OrderStatus orderStatus = activityDetail.getOrder().getStatus();
             OrderResult orderResult = activityDetail.getOrder().getOrderResult();
             
             if(orderStatus.equals(OrderStatus.出票失败)) {
                 activityDetail.setFinished(true);
                 activityService.saveActivityDetail(activityDetail);
                 return;
             }
            
            ActivityType activityType = activityDetail.getActivity().getType();
            
            
            
            if(activityType.equals(ActivityType.购彩额度)) { //未考虑好
            	activityDetail.setFinished(true);
                activityService.saveActivityDetail(activityDetail);
                return;
            }
            else if(activityType.equals(ActivityType.首次购彩)) {
                if(activityDetail.getActivity().getOrderType().equals(ActivityOrderType.中奖时))
                {
                       if (orderResult.equals(OrderResult.已兑奖)) {
                    	   if(activityDetail.getOrder().getWinMoney().compareTo(
                    			   activityDetail.getActivity().getThreshold()) >= 0) {
                    		   pass = true;
                    		   activityDetail.setAddmoney( activityDetail.getActivity().getGivenMoney());
                    	   } else
                    	   {
                    		   activityDetail.setFinished(true);
                    		   activityService.saveActivityDetail(activityDetail);
                
                    	   }
                        } 
                       
                       if(orderResult.equals(OrderResult.未中奖)) {
                    	   activityDetail.setFinished(true);
                		   activityService.saveActivityDetail(activityDetail);
                       }
                    
                }
                if(activityDetail.getActivity().getOrderType().equals(ActivityOrderType.未中奖时))
                {
                        if(orderResult.equals(OrderResult.未中奖)) {
                        	pass = true;
                        	activityDetail.setAddmoney( activityDetail.getActivity().getGivenMoney());
                        }
                        if (orderResult.equals(OrderResult.已兑奖)) {
                       	 activityDetail.setFinished(true);
                         activityService.saveActivityDetail(activityDetail);
                    
                        }
                }
                
            }
            else if(activityType.equals(ActivityType.普通)) {
                if(activityDetail.getActivity().getOrderType().equals(ActivityOrderType.中奖时))
                {
                	  if (orderResult.equals(OrderResult.已兑奖))
                	  {
                    		if(activityDetail.getOrder().getWinMoney().compareTo(
                    				activityDetail.getActivity().getThreshold()) >= 0) {
                    				pass = true;
                    				BigDecimal mul=activityDetail.getOrder().getWinMoney().divide(activityDetail.getActivity().getThreshold());
                    				mul=mul.setScale(0,BigDecimal.ROUND_DOWN);
                    				if(mul.compareTo(new BigDecimal(10))>=0) mul=new BigDecimal(10.0);
                    				activityDetail.setAddmoney( activityDetail.getActivity().getGivenMoney().multiply(mul));
                    		} else
                    		{
                    			activityDetail.setFinished(true);
                      		   activityService.saveActivityDetail(activityDetail);
                    		}
                        }
                        
                        if(orderResult.equals(OrderResult.未中奖)) {
                     	   activityDetail.setFinished(true);
                 		   activityService.saveActivityDetail(activityDetail);
                        }
                } else  //不中奖暂时无活动
                {
                	activityDetail.setFinished(true);
          		   	activityService.saveActivityDetail(activityDetail);
                }
            }
            
            
            
            if(pass) {
                addMoney(activityDetail.getOrder().getCustomer(), 
                        activityDetail.getAddmoney(), 
                        activityDetail.getActivity().getName());
                activityDetail.setFinished(true);
                activityService.saveActivityDetail(activityDetail);
            } 
            
        }
        else if(type.equals(ActivityDetailType.充值活动)) {
            ;//已经立即处理
        }
        else if(type.equals(ActivityDetailType.注册活动)) {
            ;//已经立即处理
        }
    }
    
    private void addMoney(Customer customer, BigDecimal money, String desc)
    {
        if(money.compareTo(BigDecimal.ZERO) <= 0) 
            return;
        
        WalletLog walletLog = new WalletLog(BusinessType.收入,
                money, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, desc,
                WalletLogType.活动赠送, "");
        customerService.addWalletLog(customer.getWallet().getId(), 
                walletLog);
    }
}
