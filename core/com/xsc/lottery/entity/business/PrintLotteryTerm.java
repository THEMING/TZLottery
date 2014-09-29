package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TermStatus;

/** 彩期 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_print_term", uniqueConstraints = { @UniqueConstraint(columnNames = { "type", "term_no","outPoint" }) })
public class PrintLotteryTerm extends BaseObject
{
    @Id
    @GeneratedValue
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

    /** 开奖时间 */
    @Column(name = "open_prize_time", nullable = false)
    private Calendar openPrizeTime;

    /** 彩期状态 */
    @Column(name = "term_status")
    private TermStatus termStatus = TermStatus.销售中;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private SendTicketPlat outPoint;

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

    public TermStatus getTermStatus()
    {
        return termStatus;
    }

    public void setTermStatus(TermStatus termStatus)
    {
        this.termStatus = termStatus;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public SendTicketPlat getOutPoint()
    {
        return outPoint;
    }

    public void setOutPoint(SendTicketPlat outPoint)
    {
        this.outPoint = outPoint;
    }

}
