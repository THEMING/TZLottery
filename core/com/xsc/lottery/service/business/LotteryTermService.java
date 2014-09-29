package com.xsc.lottery.service.business;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.service.LotteryBaseService;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;

public interface LotteryTermService extends LotteryBaseService<LotteryTerm>
{
    /** 获取最后截止的彩期 */
    public LotteryTerm getLastStopSaleTerm(LotteryType type);

    public LotteryTerm getCurrentTerm(LotteryType type);
    
    public List<LotteryTerm> getCurrentTermArray(LotteryType type);
    /** 合买截止 */
    public void stopTogegerSale(LotteryTerm term);

    /**
     * 销售截止 返回下一期
     * */
    public LotteryTerm stopSale(LotteryTerm term);

    /** 开奖 */
    public void openPrize(LotteryTerm term);

    /** 兑奖 */
    public void checkWin(LotteryTerm term);

    public LotteryTerm getByTypeAndTermNo(String termno, LotteryType pyte);

    /** 获取最后有开奖结果期数 */
    public LotteryTerm getLastOpenPrizeResult(LotteryType type);

    /** 根据查询条件获得期次列表——wwl */
    public Page<LotteryTerm> getLotteryTermPage(Page<LotteryTerm> page,
            LotteryType type, TermStatus termStatus, Calendar beginTime,
            String term);

    /** 根据期次获得开奖明细——wwl */
    public List<PrizeLevel> getTermByPrizeLevel(LotteryTerm term);

    /** 保存LotteryTerm修改数据（包括明细数据）——wwl */
    public LotteryTerm saveLotteryTermOrPrizeLevel(LotteryTerm term);

    /** 得到各彩种需要开奖期次列表——wwl */
    public Page<LotteryTerm> getOpenWinByTypePgae(Page<LotteryTerm> page,
            LotteryType type, String termNO);

    public List<com.xsc.lottery.entity.business.Order> startChase(
            LotteryTerm term);

    /** 根据彩种/期次数NUM得到走势图彩期 */
    public List<LotteryTerm> getTermByZSMap(LotteryType type, int num);

    /**
     * @see 分页根据彩期彩种查找历史开奖期数
     * @param page
     * @param type
     * @param termStatus
     * @param beginTime
     * @param termNo
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<LotteryTerm> getHistoryLotteryTermPage(Page<LotteryTerm> page,
            LotteryType type);

    public void delWinDescribeOrder(LotteryTerm term);

    public LotteryTerm getLotteryTerm(String termNo, LotteryType type);

    /**
     * 分页获得未兑奖期次
     * 
     * @param page
     * @param type
     * @param fTerm
     * @return
     */
    public Page<LotteryTerm> getLotteryTermPage(Page<LotteryTerm> page,
            LotteryType type, String fTerm, String status);

    public List<PlanItem> getPlanItemList(Plan plan);

    /**
     * 根据已中奖的票的otherOrderId进行开奖
     */
    public void openPrizeByTicketOtherId(
            List<TicketTreatmentWork.winTicketDis> list);

    /**
     * @see 合买截止 合买满员生成订单 1。为满员90%的订单自动补单 2。合买截止 3。加上保底未满员退款 4.撤单退款
     * @param term
     * @return
     */

    public List<com.xsc.lottery.entity.business.Order> stopToSaleCreateHm(
            LotteryTerm term);

    public List<LotteryTerm> getOpenPrizeByDay(Calendar day);

    /**
     * 获得30期号
     * 
     * @param enToType
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getTermArray(LotteryType enToType, int num);

    /** 接口返回數據 开奖 */
    public void openPrizeSyncTreatment(LotteryTerm terms);

    public LotteryTerm findUniqueByProperty(String name, Object value);

    /** 根据彩种 彩期状态得到小于该状态的期数 */
    public LotteryTerm getLotteryTermByLtSta(LotteryType type,
            TermStatus termStatus);

    /**
     * @Title: getLotterTermByTypeAndStat
     * @param @param type
     * @param @param termStatus
     * @param @return
     * @return List<LotteryTerm>
     * @Description: TODO 根据彩种和状态查询彩期
     * @throws
     */
    public List<LotteryTerm> getLotterTermByTypeAndStat(LotteryType type,
            TermStatus termStatus);
    
    public List<MatchArrange> getMatchResultByTermNo(String termNo);
    
    public List<MatchArrange> get6CBMatchResultByTermNo(String termNo);
    
    public List<MatchArrange> get4CJQMatchResultByTermNo(String termNo);
    
    public List<LotteryTerm> getTermResultByTermNo(String termNo);
    
    public List<LotteryTerm> getTerm6CBResultByTermNo(String termNo);
    
    public List<LotteryTerm> getTerm4CJQResultByTermNo(String termNo);
    
    public List<MatchArrange> getMatchArrayByBoutIndex(String boutIndex);
    
    public List<Ticket> getTicketById(Long ticketid);
    
    /**为手机开奖历史写的分页查询*/
    public List<LotteryTerm> getTermByZSMapForPhone(LotteryType type, int num, int begin);

}
