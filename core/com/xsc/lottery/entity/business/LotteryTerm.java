package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TermStatus;

/** 彩期 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_term",uniqueConstraints = { @UniqueConstraint(columnNames = { "type", "term_no","outPoint" }) })
@SequenceGenerator(name = "S_LotteryTerm", allocationSize = 1, initialValue = 1, sequenceName = "S_LotteryTerm")
public class LotteryTerm extends BaseObject
{
    @Id
    @GeneratedValue
    //(strategy=GenerationType.SEQUENCE, generator="S_LotteryInfo")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private LotteryType type;

    /** 期号 */
    @Column(name = "term_no", nullable = false)
    private String termNo;

    /** 开售时间 */
    @Column(name = "start_sale_time", nullable = false)
    private Calendar startSaleTime;

    /** 停销时间 */
    @Column(name = "stop_sale_time", nullable = false)
    private Calendar stopSaleTime;

    /** 合买截止时间 */
    @Column(name = "stop_together_sale_time")
    private Calendar stopTogetherSaleTime;

    /** 开奖时间 */
    @Column(name = "open_prize_time", nullable = false)
    private Calendar openPrizeTime;

    /** 派奖时间 */
    @Column(name = "send_prize_time", nullable = false)
    private Calendar sendPrizeTime;

    /** 开奖号码字符串 */
    private String result;

    /** 出球顺序 */
    private String orderResult;

    /** 奖池 */
    private BigDecimal prizePool;

    /** 销售金额 */
    @Column(name = "total_sale")
    private BigDecimal totalSale;

    /** 销售金额 eg: 大乐透的11选2 */
    private BigDecimal otherTotalSale;

    /** 彩期状态 */
    @Column(name = "term_status")
    private TermStatus termStatus = TermStatus.销售中;

    /** 当前期 */
    @Column(name = "current_term")
    private boolean current = true;

    // /**下一期*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_id")
    private LotteryTerm nextTerm;

    /** 是否自动开奖 */
    private boolean autoOpen;
    /** 是否自动兑奖 */
    @Column(name = "auto_check_win")
    private boolean autoCheckWin;

    @OneToMany(mappedBy = "term")
    @OrderBy("level")
    private List<PrizeLevel> prizeLevels;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private SendTicketPlat outPoint;
    
    @Transient
    private Integer counter = 0;

    public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

    public boolean isAutoOpen()
    {
        return autoOpen;
    }

    public void setAutoOpen(boolean autoOpen)
    {
        this.autoOpen = autoOpen;
    }

    public boolean isAutoCheckWin()
    {
        return autoCheckWin;
    }

    public void setAutoCheckWin(boolean autoCheckWin)
    {
        this.autoCheckWin = autoCheckWin;
    }

    public Long getId()
    {
        return id;
    }

    public String getTermNo()
    {
        return termNo;
    }

    public Calendar getStartSaleTime()
    {
        return startSaleTime;
    }

    public Calendar getStopSaleTime()
    {
        return stopSaleTime;
    }

    public Calendar getOpenPrizeTime()
    {
        return openPrizeTime;
    }

    /** 获取开奖号码数组形式 */
    public String[] getResults()
    {
        if(!StringUtils.isBlank(result)) {
            return result.split(",");
        }
        return null;
    }

    public String getResult()
    {
        return result;
    }

    public boolean isCurrent()
    {
        return current;
    }

    public void setCurrent(boolean current)
    {
        this.current = current;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public void setTermNo(String termNo)
    {
        this.termNo = termNo;
    }

    public void setStartSaleTime(Calendar startSaleTime)
    {
        this.startSaleTime = startSaleTime;
    }

    public void setStopSaleTime(Calendar stopSaleTime)
    {
        this.stopSaleTime = stopSaleTime;
    }

    public void setOpenPrizeTime(Calendar openPrizeTime)
    {
        this.openPrizeTime = openPrizeTime;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public BigDecimal getPrizePool()
    {
        return prizePool;
    }

    public void setPrizePool(BigDecimal prizePool)
    {
        this.prizePool = prizePool;
    }

    public BigDecimal getTotalSale()
    {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale)
    {
        this.totalSale = totalSale;
    }

    public TermStatus getTermStatus()
    {
        return termStatus;
    }

    public void setTermStatus(TermStatus termStatus)
    {
        this.termStatus = termStatus;
    }

    public Calendar getSendPrizeTime()
    {
        return sendPrizeTime;
    }

    public void setSendPrizeTime(Calendar sendPrizeTime)
    {
        this.sendPrizeTime = sendPrizeTime;
    }

    public Calendar getStopTogetherSaleTime()
    {
        return stopTogetherSaleTime;
    }

    public void setStopTogetherSaleTime(Calendar stopTogetherSaleTime)
    {
        this.stopTogetherSaleTime = stopTogetherSaleTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LotteryTerm getNextTerm()
    {
        return nextTerm;
    }

    public void setNextTerm(LotteryTerm nextTerm)
    {
        this.nextTerm = nextTerm;
    }

    public List<PrizeLevel> getPrizeLevels()
    {
        return prizeLevels;
    }

    public void setPrizeLevels(List<PrizeLevel> prizeLevels)
    {
        this.prizeLevels = prizeLevels;
    }

    @Override
    public String toString()
    {
        return type.name() + "第" + termNo + "期";
    }

    public SendTicketPlat getOutPoint()
    {
        return outPoint;
    }

    public void setOutPoint(SendTicketPlat outPoint)
    {
        this.outPoint = outPoint;
    }

    public String getOrderResult()
    {
        return orderResult;
    }

    public void setOrderResult(String orderResult)
    {
        this.orderResult = orderResult;
    }

    public BigDecimal getOtherTotalSale()
    {
        return otherTotalSale;
    }

    public void setOtherTotalSale(BigDecimal otherTotalSale)
    {
        this.otherTotalSale = otherTotalSale;
    }
}
