package com.xsc.lottery.task.term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.handle.BaseLotteryHandle;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;

@Component
public class LotteryTermTaskFactory
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LotteryTermService termService;
    @Autowired
    private LotteryHandleFactory handleFactory;
    @Autowired
    private TicketBusinessFactory ticketBusinessFactory;
    @Autowired
    private AllTermJobExecutor termJopExecutor;

    //一次只允许一个彩期运行map
    private Map<LotteryType, LotteryTermTaskExecutor> map = 
        new HashMap<LotteryType, LotteryTermTaskExecutor>();
    
	//一次允许多个彩期运行map2
    private Map<LotteryType, Map<String, LotteryTermTaskExecutor> > map2 = 
        new HashMap<LotteryType, Map<String, LotteryTermTaskExecutor> >();
    
    private static Map<LotteryType, String> multiTaskPerType = new HashMap<LotteryType, String>();
    static {
    	multiTaskPerType.put(LotteryType.足彩14场, "14sfc");
    	multiTaskPerType.put(LotteryType.足彩任9, "r9");
    	multiTaskPerType.put(LotteryType.四场进球, "4cjq");
    	multiTaskPerType.put(LotteryType.足彩6场半, "6cb");
    }
    
    public boolean canStartMultiTaskOneType(LotteryType type) {
    	return multiTaskPerType.containsKey(type);
    }
    
    /** 启动全部彩种的彩期 */
    public void startAllLottery()
    {
        LotteryType[] types = LotteryType.values();
        for (LotteryType type : types) {
            if(!canStartMultiTaskOneType(type)) {
            	startLotteryTermByType(type);
            }
            else {
            	startMultiTermTypes(type);
            }
        }
    }

    /** 启动彩种的彩期 */
    public void startLotteryTermByType(LotteryType type)
    {	
        BaseLotteryHandle handle = handleFactory.getLotteryHandleFactory(type);
        if(handle == null) {
        	return;
        }
        
        try {
            LotteryTermTaskExecutor taskExecutor;
            if (map.containsKey(type)) {
                taskExecutor = map.get(type);
            }
            else {
                taskExecutor = new LotteryTermTaskExecutor(
                        termService,
                        type,
                        null, //不指定彩期，从数据库中取当前期
                        ticketBusinessFactory,
                        handle,
                        this,
                        termJopExecutor);
            }
            taskExecutor.start();
            map.put(type, taskExecutor);
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }
    
    public void startMultiTermTypes(LotteryType type)
    {
    	List<LotteryTerm> termLists = termService.getCurrentTermArray(type);
    	for(LotteryTerm term : termLists) {
    		startLotteryTermByTypeAndTerm(type, term);
    	}
    }
    
    /** 启动彩种的彩期 多期*/
    public void startLotteryTermByTypeAndTerm(LotteryType type, LotteryTerm currentTerm)
    {
    	//一个彩种可以有多个当前期
    	BaseLotteryHandle handle = handleFactory.getLotteryHandleFactory(type);
    	if(handle == null) {
    		return;
    	}
    	
    	try {
            LotteryTermTaskExecutor taskExecutor = null;
            if (map2.containsKey(type)) {
            	Map<String, LotteryTermTaskExecutor> ll = map2.get(type);
            	if(ll.containsKey(currentTerm.getTermNo())) {
            		taskExecutor = ll.get(type);
            	}
            	else {
            		taskExecutor = new LotteryTermTaskExecutor(
                            termService,
                            type,
                            currentTerm,
                            ticketBusinessFactory,
                            handle,
                            this,
                            termJopExecutor);
            		ll.put(currentTerm.getTermNo(), taskExecutor);
            	}
            }
            else {
                taskExecutor = new LotteryTermTaskExecutor(
                        termService,
                        type,
                        currentTerm,
                        ticketBusinessFactory,
                        handle,
                        this,
                        termJopExecutor);
                Map<String, LotteryTermTaskExecutor> ll = 
                	new HashMap<String, LotteryTermTaskExecutor>();
                ll.put(currentTerm.getTermNo(), taskExecutor);
                map2.put(type, ll);
            }
            taskExecutor.start();
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }
    
    /** 获取存储彩期进程的Map */
    public Map<LotteryType, LotteryTermTaskExecutor> getTermTaskExecutorMap()
    {
        return map;
    }

    /** 停止当前彩种的彩期切换 */
    public void shutdownByType(LotteryType type, Boolean isNowShutdown)
    {
        if (map.containsKey(type)) {
            ScheduledExecutorService ses = CommonScheduledThreadPoolExecutor
                    .getInstance().getSingleThreadScheduledExecutorMap().get(
                            map.get(type).getThreadExec().hashCode());
            if (null != isNowShutdown || isNowShutdown) {
                ses.shutdownNow();
            }
            else {
                ses.shutdown();
            }
            map.remove(type);
        }
    }
    
    /** 停止当前彩种的彩期切换 */
    public void shutdownByTypeAndTerm(LotteryType type, LotteryTerm term, Boolean isNowShutdown)
    {
        if (map2.containsKey(type) && map2.get(type).containsKey(term.getTermNo())) {
            ScheduledExecutorService ses = CommonScheduledThreadPoolExecutor
                    .getInstance().getSingleThreadScheduledExecutorMap().get(
                		map2.get(type).get(term.getTermNo()).getThreadExec().hashCode());
            if (null != isNowShutdown || isNowShutdown) {
                ses.shutdownNow();
            }
            else {
                ses.shutdown();
            }
            map2.get(type).remove(term.getTermNo());
        }
    }
    
    public boolean hasTask(LotteryType type) 
    {
    	return map.containsKey(type) && map.get(type).isRunning();
    }
    
    public boolean hasTask(LotteryTerm term) 
    {
    	logger.info(map2.toString());
    	LotteryType type = term.getType();
    	if(canStartMultiTaskOneType(type)) {
    		if(map2.containsKey(type) && map2.get(type).containsKey(term.getTermNo())
    				&& map2.get(type).get(term.getTermNo()).isRunning()) {
    			return true;
    		}
    		return false;
    	}
    	else {
    		return hasTask(type);
    	}
    }
    
    public void removeTask(LotteryTerm term)
    {
    	LotteryType type = term.getType();
    	try {
	    	if(canStartMultiTaskOneType(type)) {
	    		if(map2.containsKey(type) && map2.get(type).containsKey(term.getTermNo())) {
	    			map2.get(type).remove(term.getTermNo());
	    		}
	    	}
	    	else {
	    		map.remove(type);
	    	}
    	}
    	catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }
}
