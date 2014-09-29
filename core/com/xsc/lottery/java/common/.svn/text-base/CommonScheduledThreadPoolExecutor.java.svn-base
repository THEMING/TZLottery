package com.xsc.lottery.java.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**    
 * @description  global threads' pool
 */

@SuppressWarnings({ "unused"})
public final class CommonScheduledThreadPoolExecutor 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CommonScheduledThreadPoolExecutor commonExecutor;
	private  static int MaxPoolSize = 40;
	private Map<Integer,ScheduledExecutorService> SingleThreadExecutorMap = new HashMap<Integer, ScheduledExecutorService>();
	private ScheduledThreadPoolExecutor  threadPoolExecutor;
	
	private CommonScheduledThreadPoolExecutor()
	{
		threadPoolExecutor = new ScheduledThreadPoolExecutor(MaxPoolSize, 
				new RejectedExecutionHandler() {
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						logger.warn("-----------系统崩溃，线程池关闭不能接受 Runnable 任务-------------");
					}
				}
		);
	}

	//get pool instance with default size = MaxPoolSize = 20;
	public static CommonScheduledThreadPoolExecutor getInstance()
	{
		if(null == commonExecutor) {
			commonExecutor = new CommonScheduledThreadPoolExecutor();
		}
		return commonExecutor;
	}
	
	//get pool instance with specified size = corePoolSize;
	public static CommonScheduledThreadPoolExecutor getInstance(int corePoolSize)
	{
		if(null == commonExecutor) {
			MaxPoolSize = corePoolSize;
			commonExecutor = new CommonScheduledThreadPoolExecutor();
		}
		return commonExecutor;
	}
	
	public void schedule(Runnable command, long delay, TimeUnit unit)
	{
		threadPoolExecutor.schedule(command, delay, unit);
	}

	public void execute(Runnable command)
	{
		threadPoolExecutor.execute(command);
	}
	
	public Future<String> execute(Callable<String> command)
	{
		return threadPoolExecutor.submit(command);
	}
	
	public ScheduledExecutorService newSingleThreadScheduledExecutor()
	{
		ScheduledExecutorService newses = Executors.newSingleThreadScheduledExecutor();
		SingleThreadExecutorMap.put(newses.hashCode(),newses);
		logger.info("新的单例定时任务启动.......");
		return newses;
	}
	
	public Map<Integer,ScheduledExecutorService> getSingleThreadScheduledExecutorMap()
	{
		return SingleThreadExecutorMap;
	}
}
