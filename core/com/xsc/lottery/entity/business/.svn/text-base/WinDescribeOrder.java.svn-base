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

/** 注单中奖情况 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_win_describe_order")
@SequenceGenerator(name = "S_WinDescribeOrder", allocationSize = 1, initialValue = 1, sequenceName = "S_WinDescribeOrder")
public class WinDescribeOrder extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;

    /** 奖级 */
    // FIXME nullable = false
    @ManyToOne
    @JoinColumn(name = "prize_level_id")
    private PrizeLevel prizeLevel;

    /** 对应的票 */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /** 中奖金额 */
    private BigDecimal money;

    /** 中奖注数 */
    @Column(name = "number_count")
    private int number;

    public WinDescribeOrder(PrizeLevel prizeLevel, Order order, int number)
    {
        super();
        this.prizeLevel = prizeLevel;
        this.order = order;
        this.money = prizeLevel.getAllPrize().multiply(
                BigDecimal.valueOf(number));
        this.number = number;
    }

    public WinDescribeOrder()
    {
        super();
    }

    public Long getId()
    {
        return id;
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

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

}
