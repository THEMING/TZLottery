package com.xsc.lottery.ntalker.action;

import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.MD5;
import com.xsc.lottery.util.NetWorkUtil;

@Component
public class TrackTaskExecutor implements ApplicationListener
{
	private String nTalkerURL = Configuration.getInstance().getValue("nTalker.url");
	private String nTalkerKey = Configuration.getInstance().getValue("nTalker.key");
	
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected boolean start = false;
	
	/** 邮件通知订单队列 */
    protected LinkedBlockingQueue<WalletLog> trackWalletLogQueue = new LinkedBlockingQueue<WalletLog>();
    
	public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("track服务启动...");
            CommonScheduledThreadPoolExecutor.getInstance().execute(
                    createTrackOrderTask());
            start = true;
        }
    }
	
	public void addTrack(WalletLog log)
    {
		trackWalletLogQueue.offer(log);
    }
	
	private Runnable createTrackOrderTask()
    {
		return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                        WalletLog log = trackWalletLogQueue.take();
                        trackOrder(log);
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
	
	private void trackOrder(WalletLog log)
    {
		
		
		
		
			trackOrderToNTalker(log);
		
		
    }
	
	private void trackOrderToNTalker(WalletLog log)
	{
		/** format */
		/* http://adapi.ntalker.com/interface.php?
		 * q=cps&adid=123&pid=22&on=&pn=&pna=&ct=&ta=&pp=&sd=&encoding=&authcode=*/
	
		String timestamp = DateUtil.getTimestamp(log.getTime());
	//	timestamp=timestamp.substring(0,11);
		//String sign = MD5.digest(timestamp + nTalkerKey);
		String sign = MD5.digest(timestamp + "560faeed92c4ee9def698e9ee3817e5a");
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("q=").append("cps").append("&adid="+log.getAdid())
		.append("&pid="+log.getPid()).append("&on="+log.getSerialNumber())
		.append("&pn=1").append("&pna="+"caipiao")
		.append("&ct=1").append("&ta=1")
		.append("&pp="+log.getOutMoney()).append("&sd="+DateUtil.toYYYY_MM_DD_HH_MM_SS(log.getTime()))
		.append("&encoding=utf8").append("&authcode="+sign);

		
		String result = NetWorkUtil.getHttpUrlByGetMethod(nTalkerURL, sendParam.toString());
		if(result.equals("0")) {
			logger.info("Email order to ntalker ok!");
		}
		else if(result.equals("1")) {
			logger.info("Email order to ntalker failed 1!");
		}
		else if(result.equals("2")) {
			logger.info("Email order to ntalker failed 2!");
		}
	}
	
	public static void main(String argv[])
	{
		//test
	    Calendar now = Calendar.getInstance();
	    String sd = DateUtil.getTimestamp(now);
        String sign = MD5.digest(sd + "560faeed92c4ee9def698e9ee3817e5a");
        
	    String url = "http://adapi.ntalker.com/interface.php";
	    
	    StringBuilder sendParam = new StringBuilder();
        sendParam.append("q=cps").append("&adid="+123)
        .append("&pid="+22).append("&on="+123456)
        .append("&pn="+9).append("&pna="+"ssq")
        .append("&ct="+"ssq").append("&ta="+2)
        .append("&pp=2").append("&sd="+DateUtil.toYYYY_MM_DD_HH_MM_SS(now))
        .append("&encoding=utf8").append("&authcode="+sign).append("&isnewuser=1");
        
	    String result = NetWorkUtil.getHttpUrlByGetMethod(url, sendParam.toString());
	    System.out.println(sendParam.toString());
	    System.out.println(sd);
	    System.out.println(DateUtil.toYYYY_MM_DD_HH_MM_SS(now));
	    System.out.println(result);
	}
}
