/**
 * 
 */
package com.xsc.lottery.service.business;

import java.util.Calendar;
import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.service.LotteryBaseService;

public interface MatchArrangeService extends LotteryBaseService<MatchArrange>
{
    public List<MatchArrange> getMatchArrangeByTerm(LotteryTerm term);

    public MatchArrange getUniqueMatchByMatchNo(String value);
    
    /**
     * 根据期号查找改期最后一场赛事时间
     */
    public Calendar findLastMatchByTermno(String termno);
    
    public void saveAllMatches(List<MatchArrange> matches);
    
    public MatchArrange findLastMatchByTermAndIndex(String index, LotteryTerm term, LotteryType type);
    
    /** 竞彩足球 */
    public List<MatchArrange> getMatchArrangeByDate(Calendar date, LotteryType type);
    public List<MatchArrange> getMatchArrangeByDate(String date, LotteryType type);
    
    public List<MatchArrange> getMatchArrangeBySshc(String date);
    
    public List<MatchArrange> getCurrentMatchArrangeForJCZQ();
    
    public List<MatchArrange> getCurrentMatchArrangeForJCLQ();
    
    public Page<MatchArrange> getMatchPage(Page<MatchArrange> page,
    		Calendar date, RaceStatus raceStatus,  String no, LotteryType type);
    
    public RaceStatus getStatusByMatchNo(String matchNo);
    /** 竞彩篮球 */
    public List<MatchArrange> getCurrentMatchArrangeForJCLQ(String szType);
    public List<MatchArrange> getLQMatchArrangeByDate(Calendar date);
    public List<MatchArrange> getLQMatchArrangeByDate(String date);
    
    /**根据彩种类型和比赛场次编号获得比赛*/
    public MatchArrange getMatchMatchByNoAndType(String matchNo, LotteryType type);
    
    public List<com.xsc.lottery.entity.business.Order> stopToSaleCreateHm(MatchArrange match);
    
    public MatchArrange getFirstMatchInOrder(String codes);
    
    public MatchArrange getLastMatchInOrder(String codes, LotteryType type);
    
    public String getResultByMatchNo(String matchNo, PlayType playType, LotteryType type);
    
    public MatchArrange getMatchInfoByMatchNo(String matchNo, LotteryType type);
    
    public List<MatchArrange> getMatchesForJczqTask();
    
    public void saveMatchHistory(List<MatchHistory> matches);
    
    /** 获取可以开奖和兑奖的比赛*/
    public Page<MatchArrange> getMatchPageForPrize(Page<MatchArrange> page, LotteryType type);
    
    /**取得竟彩的当前期的几场比赛*/
    public void fetchpeilv();
    
    public Page<Odd> findOdd(Page<Odd> page);
    
    public List<Odd> getOdd(String matchId);
    public List<MatchArrange> getCurrentMatch();

    /** 根据场次编号获取竟彩篮球对阵 */
    public MatchArrange getMatchInfoForJCLQByMatchNoAndTpye(String matchNo);
    
    public MatchArrange getMatchArrangeBySome(String termid, String i);
}
