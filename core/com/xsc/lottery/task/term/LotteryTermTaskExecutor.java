package com.xsc.lottery.task.term;

import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.handle.BaseLotteryHandle;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.util.DateUtil;

@SuppressWarnings("unused")
public class LotteryTermTaskExecutor
{
    private ScheduledExecutorService threadExec = CommonScheduledThreadPoolExecutor
            .getInstance().newSingleThreadScheduledExecutor();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private LotteryTermService termService;
    private LotteryType lotteryType;
    private LotteryTerm currentTerm;
    private TicketBusinessFactory ticketBusinessFactory;
    private BaseLotteryHandle baseHandle;
    //private Map<LotteryType, LotteryTermTaskExecutor> lotteryTermTaskMap;
    private LotteryTermTaskFactory taskFactory;
    private AllTermJobExecutor termJopExecutor;

    /** 有彩期正在销售 */
    private boolean running = false;

    public LotteryTermTaskExecutor(LotteryTermService termService,
            LotteryType lotteryType,
            LotteryTerm currentTerm,
            TicketBusinessFactory ticketBusinessFactory,
            BaseLotteryHandle baseHandle,
            /*Map<LotteryType, LotteryTermTaskExecutor> lotteryTermTaskMap,*/
            LotteryTermTaskFactory taskFactory,
            AllTermJobExecutor termJopExecutor)
    {
        this.termService = termService;
        this.lotteryType = lotteryType;
        this.currentTerm = currentTerm;
        this.ticketBusinessFactory = ticketBusinessFactory;
        this.baseHandle = baseHandle;
        //this.lotteryTermTaskMap = lotteryTermTaskMap;
        this.taskFactory = taskFactory;
        this.termJopExecutor = termJopExecutor;
    }

    /** 初使化, 并启动任务 */
    public void start() throws Exception
    {
        LotteryTerm lastSaleStopTerm = termService.getLastStopSaleTerm(lotteryType);
        if(lastSaleStopTerm != null) {
            if(lastSaleStopTerm.getTermStatus().equals(TermStatus.销售截止)) {
            	lastSaleStopTerm.setTermStatus(TermStatus.未开奖);
            	termService.update(lastSaleStopTerm);
            }
            
            if(lastSaleStopTerm.getTermStatus().equals(TermStatus.已开奖)) {
                lastSaleStopTerm.setTermStatus(TermStatus.未兑奖);
                termService.update(lastSaleStopTerm);
            }
        }
        run();
    }

    /** 启动任务调度器 */
    public void run() throws Exception
    {
    	if(running) {
            throw new Exception(lotteryType.name() + " 彩期守护线程已经正常工作, 此次启动忽略!");
        }
        else {
        	if(currentTerm == null) {
        		currentTerm = termService.getCurrentTerm(lotteryType);
        	}
        	
            if (currentTerm == null) {
                throw new Exception(lotteryType.name() + " 无彩期, 无法启动守护线程");
            }
            logger.info(currentTerm.getType().name() + " 彩期守护启动...");
            running = true;
            CommonScheduledThreadPoolExecutor.getInstance().execute(getStartSaleTask(currentTerm));
        }
    }

    private Runnable getStartSaleTask(final LotteryTerm term)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                    logger.info("开始销售   " + term + " 了");
                    startSale(term);
                    
                    long delay = 0;
                    
                    //本期合买截止任务
                    if (term.getStopTogetherSaleTime() != null) {
                        delay = (term.getStopTogetherSaleTime()
                                .getTimeInMillis() - System.currentTimeMillis()) / 1000;
                        addTask(getStopTogegerSaleTask(term), delay, 
                                term.getStopTogetherSaleTime(), term, "合买截止任务");
                    }

                    //本期销售截止任务
                    if (term.getStopSaleTime() != null) {
                        delay = (term.getStopSaleTime().getTimeInMillis() - System
                                .currentTimeMillis()) / 1000;
                        addTask(getSaleStopTask(term), delay, 
                                term.getStopSaleTime(), term, "销售截止任务");
                    }

                    //本期开奖任务
                    if (term.getOpenPrizeTime() != null) {
                        delay = (term.getOpenPrizeTime().getTimeInMillis() - System
                                .currentTimeMillis()) / 1000;
                        addTask(getAutoOpenPrizeTask(term), delay, 
                                term.getOpenPrizeTime(), term, "开奖任务");
                    }

                    //本期兑奖任务
                    if (term.getSendPrizeTime() != null) {
                        delay = (term.getSendPrizeTime().getTimeInMillis() - System
                                .currentTimeMillis()) / 1000;
                        //开奖后自动兑了addTask(getAutoCheckWinPrizeTask(term), delay, 
                          //      term.getSendPrizeTime(), term, "兑奖任务");
                    }
                }
                catch (Exception e) {
                    logger.info(term + " 的开始销售任务因异常挂掉", e);
                    SystemWarningNotify.addWarningDescription(term
                            + " 的开始销售任务因异常挂掉,请查看日志");
                }
            }
        };
    }

    /** 布置合买截止任务 */
    private Runnable getStopTogegerSaleTask(final LotteryTerm term)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                    logger.info("开始执行" + term + "的合买截止任务");
                    stopTogegerSale(term);
                }
                catch (Exception e) {
                    logger.info(term + " 的合买截止任务因异常挂掉", e);
                    SystemWarningNotify.addWarningDescription(term
                            + " 的合买截止任务因异常挂掉,请查看日志");
                }
            }
        };
    }

    /** 布置销售截止任务 */
    private Runnable getSaleStopTask(final LotteryTerm term)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                    logger.info("start " + term + " stopsale");
                    LotteryTerm newTerm = stopSale(term);
                    if (newTerm != null) {
                        threadExec.execute(getStartSaleTask(newTerm));
                    }
                    else {
                    	running = false;
                        CommonScheduledThreadPoolExecutor.getInstance()
                                .getSingleThreadScheduledExecutorMap().get(
                                        threadExec.hashCode()).shutdown();
                        //lotteryTermTaskMap.remove(term.getType());
                        taskFactory.removeTask(term);
                        logger.info(term + " no next task");
                        SystemWarningNotify.addWarningDescription(term
                                + " no next task");
                    }
                }
                catch (Exception e) {
                    logger.info(term + " exception occurred", e);
                    SystemWarningNotify.addWarningDescription(term
                            + " exception occurred,pls review log");
                }
            }
        };
    }

    /** 布置开奖任务 */
    private Runnable getAutoOpenPrizeTask(final LotteryTerm term)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                	
                    logger.info("开始执行" + term + "的开奖任务");
                    
                    openPrize(term);
                    if(term.getType()==LotteryType.老11选5 || term.getType() == LotteryType.快乐扑克3 || term.getType() == LotteryType.广西快3|| term.getType() == LotteryType.上海11选5 || term.getType() == LotteryType.十一运夺金 || term.getType() == LotteryType.重庆时时彩)
                    {
                    	checkWin(term);
                   
                    
                    	int counter = term.getCounter();
                    	LotteryTerm termnew = termService.findById(term.getId());
                    	termnew.setCounter(counter + 1);
                	
                    	if (termnew.getTermStatus() != TermStatus.已兑奖 && termnew.getCounter() < 200 ) {
                    		logger.info("第" + termnew.getCounter() + "循环！！！！！！！！！！！！！！！！！！！！！！");
                    	
                    		threadExec.schedule(getAutoOpenPrizeTask(termnew), 10, TimeUnit.SECONDS);
                    	}
                    }
                    
                }
                catch (Exception e) {
                    logger.info(term + " 的开奖任务因异常挂掉", e);
                    SystemWarningNotify.addWarningDescription(term
                            + " 的开奖任务因异常挂掉,请查看日志");
                }
            }
        };
    }

    /** 布置兑奖任务 */
    private Runnable getAutoCheckWinPrizeTask(final LotteryTerm term)
    {
        return new Runnable()
        {
            public void run()
            {
                try {
                    logger.info("开始执行" + term + "的兑奖任务");
                    checkWin(term);
                }
                catch (Exception e) {
                    logger.info(term + " 的兑奖任务因异常挂掉", e);
                    SystemWarningNotify.addWarningDescription(term
                            + " 的兑奖任务因异常挂掉,请查看日志");
                }
            }
        };
    }

    /** 添加新的延时任务 */
    private void addTask(Runnable task, long delay, Calendar time,
            LotteryTerm term, String description)
    {
        threadExec.schedule(task, delay, TimeUnit.SECONDS);
        logger.info(DateUtil.toMM_DD_HH_mm_ss(time) + "执行" + 
                lotteryType + "的{}", description);
    }

    public boolean isRunning()
    {
        return running;
    }

    private void openPrize(LotteryTerm term)
    {
        term = termService.findById(term.getId());
        if (term.getTermStatus().equals(TermStatus.销售截止) || term.getTermStatus().equals(TermStatus.未开奖)) {
            term.setTermStatus(TermStatus.未开奖);
            termService.update(term);
            if (term.isAutoOpen()) {
                try {
                    if(LotteryType.KuaiKaiTypeMap.containsKey(term.getType().getName_EN())) {
                        //快开抓取开奖结果不同
                        TicketTreatmentWork work = ticketBusinessFactory
                                .getTreatmentTicketByType(term.getOutPoint());
                        work.getOpenResult(term);
                    }
                    else {
                        try {
                            term = baseHandle.fetchPrizeLevel(term);
                        }
                        catch (Exception e) {
                            logger.warn(term + "开奖结果抓取有异常", e);
                            SystemWarningNotify.addWarningDescription(term
                                    + "开奖结果抓取有异常,请查看日志");
                            throw e;
                        }
                    }
                    if (StringUtils.isEmpty(term.getResult())) {
                       // throw new Exception("开奖结果抓取失败");
                    	return;
                    }
                    term = termService.saveLotteryTermOrPrizeLevel(term);
                    
                    if (LotteryType.KuaiKaiTypeMap.containsKey(term.getType().getName_EN())) {
                    	//Thread.sleep(20000);
                        termService.openPrizeSyncTreatment(term);
//                    	termService.openPrize(term); //自己开奖
                        
                    }
                    else {
                        termService.openPrize(term);
                    }
                }
                catch (Exception e) {
                    logger.warn(term.toString() + " 开奖时有异常情况", e);
                    SystemWarningNotify.addWarningDescription(term
                            + " 开奖时有异常情况,请查看日志");
                    term.setTermStatus(TermStatus.未开奖);
                    termService.update(term);
                }
            }
        }
    }

    /**
     * 开始销售私有方法
     */
    private void startSale(LotteryTerm term)
    {
        //追号处理 订单处理 票处理
        term = termService.findById(term.getId());
        
        //本线程维护的当前期
        currentTerm = term;
        
        termJopExecutor.addStartSaleJop(term);
    }

    //兑奖
    private void checkWin(LotteryTerm term)
    {
        term = termService.findById(term.getId());
        if(term.getTermStatus().equals(TermStatus.已开奖)
                && term.isAutoCheckWin()) {
            try {
                termService.checkWin(term);
            }
            catch (Exception e) {
                SystemWarningNotify.addWarningDescription(term + "兑奖时有异常情况");
                logger.warn(term + "兑奖时有异常情况", e);
                term.setTermStatus(TermStatus.未兑奖);
                termService.update(term);
            }
        }
        else if (term.getTermStatus().equals(TermStatus.已开奖)) {
            term.setTermStatus(TermStatus.未兑奖);
            termService.update(term);
        }
    }

    //销售截止私有
    private LotteryTerm stopSale(LotteryTerm term)
    {
        term = termService.findById(term.getId());
        return termService.stopSale(term);
    }

    //合买截止私有
    private void stopTogegerSale(LotteryTerm term)
    {
        term = termService.findById(term.getId());
        termService.stopTogegerSale(term);
        termJopExecutor.addStopSaleJop(term);
    }

    public ScheduledExecutorService getThreadExec()
    {
        return threadExec;
    }

	public LotteryTerm getCurrentTerm() {
		return currentTerm;
	}
}
