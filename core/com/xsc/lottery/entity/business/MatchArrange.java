package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.JCLQTypes;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.RaceStatus;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_match_arrange")
@SequenceGenerator(name = "S_matchArrange", allocationSize = 1, initialValue = 1, sequenceName = "S_matchArrange")
public class MatchArrange extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_newlyWinPrize")
    private Long id;

    /** 彩种类型 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private LotteryType lotteryType;

    /** 场次 */
    @Column(nullable = false)
    private String boutIndex;

    /** 彩期 */
    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    private LotteryTerm term;

    /** 赛事 */
    @Column(nullable = false)
    private String matchName;

    /** 开赛时间 具体比赛时间 */
    private Calendar matchTime;
    
    /** 销售日期 */
    private Calendar saleDate;
    
    /** 停止销售时间 */
    private Calendar stopSaleTime;
    
    /** 开奖时间 */
    private Calendar openPrizeTime;
    
    /** 主队 */
    @Column(nullable = false)
    private String homeTeam;// 主队
    
    @Transient
    private String historyHomeTeam;

    /** 让球 */
    private String concede;

    /** 客队 */
    @Column(nullable = false)
    private String awaryTeam;
    
    @Transient
    private String historyAwaryTeam;

    /** 比赛状态 */
    private RaceStatus status = RaceStatus.未销售;
    
    /** 全场比分 */
    private String wholeScore;
    
    /** 半全场结果 */
    private String halfResult;
    
    /** 赛果 */
    private String matchResult;
    
    /** 进球数 */
    @Column
    private Integer goals;
    
    /** 是否为特殊情况 */
    private boolean special;
    
    /** 半场比分 */
    private String halfScore;

    /** 开奖胜负彩SP值 */
    private String spSfp;// 开奖胜负彩SP值
    
    /**开奖让分胜负sp*/
    private String spRangfenSfp;//开奖让分胜平负SP值

    /** 开奖上下单双SP值 */
    private String spSxds;

    /** 开奖进球彩SP值 */
    private String spJqs;

    /** 开奖比分SP值 */
    private String spBf;//
    
    /** 开奖半全彩SP值 */
    private String spBcsfp;

    /** 胜平均欧赔 */
    private String sop;

    /** 平平均欧赔 */
    private String pop;

    /** 负平均欧赔 */
    private String fop;

    /** 胜投注比 */
    private String stzb;

    /** 平投注比 */
    private String ptzb;

    /** 负投注比 */
    private String ftzb;
    
    /** 官方即时参考赔率 */
    @Column(columnDefinition="TEXT", nullable = true)
    //足球格式： 胜平负单关;胜平负多关 | 进球数单关;进球数多关 | 猜比分单关;猜比分多关 | 半全场单关;半全场多关 (胜平负单关内部以英文逗号","隔开)
    //篮球格式： 胜负单关；胜负过关|让分胜负单关；让分胜负过关|胜分差单关；胜分差过关|大小分单关；大小分过关     主负在前
    //例如：1,2,3;2,3,4|3,4,5;5,6,7|
    private String currentRatios;
    

    /** 外部链接 */
    private String otherUrlId;
    
    /**竞猜篮球单关让分*/
    private String danguanRangFen;
    
    /**竞猜篮球多关让分*/
    private String duoguanRangFen;
    
    /**竞猜篮球多关大小球*/
    private String duoguanDaXiaoQiu;
    
    /**竞猜篮球单关大小球*/
    private String danguanDaXiaoQiu;
    
    //sp仅对单关有用,用于计算单场最后的钱数
    /**篮球胜负sp*/
    private String spSf;
    
    /**篮球让分胜负sp*/
    private String spRangfenSf;
    
    /**篮球胜分差sp*/
    private String spSfc;
    
    /**篮球大小分sp*/
    private String spDxf;
    /**竟彩篮球有那些玩法有这场比赛*/
    //单关：SGCSF － 胜负，SGRFSF － 让分胜负SGSFC － 胜分差SGDXF － 大小分
    //多关：MGCSF － 胜负，MGRFSF － 让分胜负MGSFC － 胜分差MGDXF － 大小分
    //详细内容见JCLQypes.java
    @Column(name = "playTypes")
    private String playTypes;
    
    private String sfResult;
    
    @Transient
    private String sfResultOther;
    
    private String rfsfResult;
    
    private String rfsfpResult;//让球胜负平彩果
    
    @Transient
    private String rfsfResultOther;
    
    private String dxfResult;
    
    @Transient
    private String dxfResultOther;
    
    private String sfcResult;
    
    @Transient
    private String sfcResultOther;
    
    
    
    
    public String getPlayTypes() {
		return playTypes;
	}

	public void setPlayTypes(String playTypes) {
		this.playTypes = playTypes;
	}

	private String boutIndexOther;
    
	public RaceStatus getStatus()
    {
        return status;
    }

    public void setStatus(RaceStatus status)
    {
        this.status = status;
    }

    public String getOtherUrlId()
    {
        return otherUrlId;
    }

    public void setOtherUrlId(String otherUrlId)
    {
        this.otherUrlId = otherUrlId;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public LotteryType getLotteryType()
    {
        return lotteryType;
    }

    public void setLotteryType(LotteryType lotteryType)
    {
        this.lotteryType = lotteryType;
    }

    public String getBoutIndex()
    {
        return boutIndex;
    }

    public void setBoutIndex(String boutIndex)
    {
        this.boutIndex = boutIndex;
    }

    public LotteryTerm getTerm()
    {
        return term;
    }

    public void setTerm(LotteryTerm term)
    {
        this.term = term;
    }

    public String getMatchName()
    {
        return matchName;
    }

    public void setMatchName(String matchName)
    {
        this.matchName = matchName;
    }

    public Calendar getMatchTime()
    {
        return matchTime;
    }

    public void setMatchTime(Calendar matchTime)
    {
        this.matchTime = matchTime;
    }

    public String getHomeTeam()
    {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public String getConcede()
    {
        return concede;
    }

    public void setConcede(String concede)
    {
        this.concede = concede;
    }

    public String getAwaryTeam()
    {
        return awaryTeam;
    }

    public void setAwaryTeam(String awaryTeam)
    {
        this.awaryTeam = awaryTeam;
    }

    public String getWholeScore()
    {
        return wholeScore;
    }

    public void setWholeScore(String wholeScore)
    {
        this.wholeScore = wholeScore;
    }

    public String getMatchResult()
    {
        return matchResult;
    }

    public void setMatchResult(String matchResult)
    {
        this.matchResult = matchResult;
    }

    public String getHalfScore()
    {
        return halfScore;
    }

    public void setHalfScore(String halfScore)
    {
        this.halfScore = halfScore;
    }

    public String getSpSfp()
    {
        return spSfp;
    }

    public void setSpSfp(String spSfp)
    {
        this.spSfp = spSfp;
    }

    public String getSpRangfenSfp() {
		return spRangfenSfp;
	}

	public void setSpRangfenSfp(String spRangfenSfp) {
		this.spRangfenSfp = spRangfenSfp;
	}

	public String getSpSxds()
    {
        return spSxds;
    }

    public void setSpSxds(String spSxds)
    {
        this.spSxds = spSxds;
    }

    public String getSpJqs()
    {
        return spJqs;
    }

    public void setSpJqs(String spJqs)
    {
        this.spJqs = spJqs;
    }

    public String getSpBf()
    {
        return spBf;
    }

    public void setSpBf(String spBf)
    {
        this.spBf = spBf;
    }

    public String getSpBcsfp()
    {
        return spBcsfp;
    }

    public void setSpBcsfp(String spBcsfp)
    {
        this.spBcsfp = spBcsfp;
    }

    public String getSop()
    {
        return sop;
    }

    public void setSop(String sop)
    {
        this.sop = sop;
    }

    public String getPop()
    {
        return pop;
    }

    public void setPop(String pop)
    {
        this.pop = pop;
    }

    public String getFop()
    {
        return fop;
    }

    public void setFop(String fop)
    {
        this.fop = fop;
    }

    public String getStzb()
    {
        return stzb;
    }

    public void setStzb(String stzb)
    {
        this.stzb = stzb;
    }

    public String getPtzb()
    {
        return ptzb;
    }

    public void setPtzb(String ptzb)
    {
        this.ptzb = ptzb;
    }

    public String getFtzb()
    {
        return ftzb;
    }

    public void setFtzb(String ftzb)
    {
        this.ftzb = ftzb;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Calendar getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Calendar saleDate) {
		this.saleDate = saleDate;
	}

	public Calendar getStopSaleTime() {
		return stopSaleTime;
	}

	public void setStopSaleTime(Calendar stopSaleTime) {
		this.stopSaleTime = stopSaleTime;
	}
	
	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public Integer getGoals() {
		return goals;
	}

	public void setGoals(Integer goals) {
		this.goals = goals;
	}

	public Calendar getOpenPrizeTime() {
		return openPrizeTime;
	}

	public void setOpenPrizeTime(Calendar openPrizeTime) {
		this.openPrizeTime = openPrizeTime;
	}
	
	public String getCurrentRatios() {
		return currentRatios;
	}

	public void setCurrentRatios(String currentRatios) {
		this.currentRatios = currentRatios;
	}

	public String getHalfResult() {
		return halfResult;
	}

	public void setHalfResult(String halfResult) {
		this.halfResult = halfResult;
	}

	public String getHistoryHomeTeam() {
		return historyHomeTeam;
	}

	public void setHistoryHomeTeam(String historyHomeTeam) {
		this.historyHomeTeam = historyHomeTeam;
	}

	public String getHistoryAwaryTeam() {
		return historyAwaryTeam;
	}

	public void setHistoryAwaryTeam(String historyAwaryTeam) {
		this.historyAwaryTeam = historyAwaryTeam;
	}

	public String getDanguanRangFen() {
		return danguanRangFen;
	}

	public void setDanguanRangFen(String danguanRangFen) {
		this.danguanRangFen = danguanRangFen;
	}

	public String getDuoguanRangFen() {
		return duoguanRangFen;
	}

	public void setDuoguanRangFen(String duoguanRangFen) {
		this.duoguanRangFen = duoguanRangFen;
	}

	public String getDuoguanDaXiaoQiu() {
		return duoguanDaXiaoQiu;
	}

	public void setDuoguanDaXiaoQiu(String duoguanDaXiaoQiu) {
		this.duoguanDaXiaoQiu = duoguanDaXiaoQiu;
	}

	public String getDanguanDaXiaoQiu() {
		return danguanDaXiaoQiu;
	}

	public void setDanguanDaXiaoQiu(String danguanDaXiaoQiu) {
		this.danguanDaXiaoQiu = danguanDaXiaoQiu;
	}

	public String getSpSf() {
		return spSf;
	}

	public void setSpSf(String spSf) {
		this.spSf = spSf;
	}

	public String getSpRangfenSf() {
		return spRangfenSf;
	}

	public void setSpRangfenSf(String spRangfenSf) {
		this.spRangfenSf = spRangfenSf;
	}

	public String getSpSfc() {
		return spSfc;
	}

	public void setSpSfc(String spSfc) {
		this.spSfc = spSfc;
	}

	public String getSpDxf() {
		return spDxf;
	}

	public void setSpDxf(String spDxf) {
		this.spDxf = spDxf;
	}

	public void setBoutIndexOther(String szValue)
	{
		boutIndexOther = szValue;
	}
	
	public String getBoutIndexOther()
	{
		return boutIndexOther;
	}
	
	public boolean allowSingleSF()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.单关胜负);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowSingleRSF()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.单关让分胜负);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowSingleSFC()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.单关胜分差);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowSingleDXF()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.单关大小分);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowMultipleSF()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.多关胜负);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowMultipleRSF()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.多关让分胜负);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowMultipleSFC()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.多关胜分差);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	public boolean allowMultipleDXF()
	{
		String szValue = JCLQTypes.typeToEn(JCLQTypes.多关大小分);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}
	
	static public String getJCLQPlayTypes(int nType, String danduo)
	{
		if(danduo == null || danduo == "")
			danduo = "2";		
		int nDanduo = Integer.parseInt(danduo);
		if(nDanduo < 1)	nDanduo = 1;
		else if(nDanduo > 2)	nDanduo = 2;
		int nPlayType = (nDanduo - 1) * 4 + (nType - 1);
		String szPlayType = JCLQTypes.typeToEn(JCLQTypes.values()[nPlayType]);
		return szPlayType;
	}
		
	static public String getJCLQPlayTypes(String szType, String danduo)
	{
		if(danduo == null || danduo == "")
			danduo = "2";		
		int nDanduo = Integer.parseInt(danduo);
		if(nDanduo < 1)	nDanduo = 1;
		else if(nDanduo > 2)	nDanduo = 2;
		
		String szPlayType = JCLQTypes.getJCLQTypeString(szType, danduo);	
		return szPlayType;
	}
	
	public boolean allowJCLQTypes(String szType, String danduo)
	{
		String szValue = getJCLQPlayTypes(szType, danduo);
		int nPos = playTypes.indexOf(szValue);
		return (nPos >= 0);
	}

	public String getSfResult() {
		return sfResult;
	}

	public void setSfResult(String sfResult) {
		this.sfResult = sfResult;
	}

	public String getRfsfResult() {
		return rfsfResult;
	}

	public void setRfsfResult(String rfsfResult) {
		this.rfsfResult = rfsfResult;
	}

	public String getRfsfpResult() {
		return rfsfpResult;
	}

	public void setRfsfpResult(String rfsfpResult) {
		this.rfsfpResult = rfsfpResult;
	}

	public String getDxfResult() {
		return dxfResult;
	}

	public void setDxfResult(String dxfResult) {
		this.dxfResult = dxfResult;
	}

	public String getSfcResult() {
		return sfcResult;
	}

	public void setSfcResult(String sfcResult) {
		this.sfcResult = sfcResult;
	}

	public String getSfResultOther() {
		return sfResultOther;
	}

	public void setSfResultOther(String sfResultOther) {
		this.sfResultOther = sfResultOther;
	}

	public String getRfsfResultOther() {
		return rfsfResultOther;
	}

	public void setRfsfResultOther(String rfsfResultOther) {
		this.rfsfResultOther = rfsfResultOther;
	}

	public String getDxfResultOther() {
		return dxfResultOther;
	}

	public void setDxfResultOther(String dxfResultOther) {
		this.dxfResultOther = dxfResultOther;
	}

	public String getSfcResultOther() {
		return sfcResultOther;
	}

	public void setSfcResultOther(String sfcResultOther) {
		this.sfcResultOther = sfcResultOther;
	}
}
