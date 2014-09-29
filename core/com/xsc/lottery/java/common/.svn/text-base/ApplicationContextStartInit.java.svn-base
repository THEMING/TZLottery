package com.xsc.lottery.java.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.task.term.LotteryTermTaskFactory;

/**
 * @description  global initialization
 */

@Component
@SuppressWarnings("unused")
public class ApplicationContextStartInit implements ApplicationListener
{
	@Autowired
	private LotteryTermTaskFactory termTaskFactory;

	public void onApplicationEvent(ApplicationEvent event) 
	{
		if(event instanceof ContextRefreshedEvent) {
			//init thread pool
			CommonScheduledThreadPoolExecutor.getInstance(50);
			
			//watching lottery terms 
			//termTaskFactory.startAllLottery();
			termTaskFactory.startLotteryTermByType(LotteryType.双色球);
			termTaskFactory.startLotteryTermByType(LotteryType.福彩3d);
			termTaskFactory.startLotteryTermByType(LotteryType.排列三);
			termTaskFactory.startLotteryTermByType(LotteryType.排列五);
			termTaskFactory.startLotteryTermByType(LotteryType.大乐透);
			
			termTaskFactory.startLotteryTermByType(LotteryType.七乐彩);
			termTaskFactory.startLotteryTermByType(LotteryType.七星彩);
			termTaskFactory.startLotteryTermByType(LotteryType.老11选5);
			
			termTaskFactory.startMultiTermTypes(LotteryType.足彩14场);
			termTaskFactory.startMultiTermTypes(LotteryType.足彩任9);
			termTaskFactory.startMultiTermTypes(LotteryType.四场进球);
			termTaskFactory.startMultiTermTypes(LotteryType.足彩6场半);
			
			termTaskFactory.startLotteryTermByType(LotteryType.快乐扑克3);
			termTaskFactory.startLotteryTermByType(LotteryType.上海11选5);
			termTaskFactory.startLotteryTermByType(LotteryType.十一运夺金);
			termTaskFactory.startLotteryTermByType(LotteryType.重庆时时彩);
			termTaskFactory.startLotteryTermByType(LotteryType.广西快3);
		}
	}
}
