package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 票中奖情况 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_win_describe_ticket")
@SequenceGenerator(name = "S_WinDescribeTicket", allocationSize = 1, initialValue = 1, sequenceName = "S_WinDescribeTicket")
public class WinDescribeTicket extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;

    /** 奖级 */
	// FIXME nullable = false
    @ManyToOne
    @JoinColumn(name = "prize_level_id")
    private PrizeLevel prizeLevel;

    /** 方案内容 */
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private PlanItem item;

    /** 中奖金额 */
    private BigDecimal money;

    /** 税后中奖金额 */
    private BigDecimal Taxmoney;

    /** 中奖注数 */
    @Column(name = "number_count")
    private int number;

    public WinDescribeTicket()
    {
        super();
    }

    public WinDescribeTicket(PrizeLevel prizeLevel, PlanItem item, int number)
    {
        super();
        this.prizeLevel = prizeLevel;
        this.item = item;
        this.number = number;
        this.money = prizeLevel.getAllPrize().multiply(
                BigDecimal.valueOf(number));
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public PrizeLevel getPrizeLevel()
    {
        return prizeLevel;
    }

    public void setPrizeLevel(PrizeLevel prizeLevel)
    {
        this.prizeLevel = prizeLevel;
    }

    public PlanItem getItem()
    {
        return item;
    }

    public void setItem(PlanItem item)
    {
        this.item = item;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public BigDecimal getTaxmoney()
    {
        return Taxmoney;
    }

    public void setTaxmoney(BigDecimal taxmoney)
    {
        Taxmoney = taxmoney;
    }

    @Override
    public Long getId()
    {
        return id;
    }
}
