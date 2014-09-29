package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.ChaseItermStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;

/** 追号 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_chaseitem")
@SequenceGenerator(name = "S_ChaseItem", allocationSize = 1, initialValue = 1, sequenceName = "S_ChaseItem")
public class ChaseItem extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Chase")
    private Long id;

    /** 追号的客户 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 追号 */
    @ManyToOne
    @JoinColumn(name = "chase_id")
    private Chase chase;

    /** 方案 */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    /** 彩种 */
    @Enumerated(EnumType.STRING)
    @Column(name = "lottery_type", nullable = false)
    private LotteryType lotteryType;

    /** 期号 */
    private String termNo;

    /** 倍数 */
    @Column(nullable = false)
    private int multiple;

    /** 状态 */
    private ChaseItermStatus status = ChaseItermStatus.待追号;

    private BigDecimal money;

    /** 订单 */
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Long getId()
    {
        return id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public Plan getPlan()
    {
        return plan;
    }

    public LotteryType getLotteryType()
    {
        return lotteryType;
    }

    public String getTermNo()
    {
        return termNo;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setPlan(Plan plan)
    {
        this.plan = plan;
    }

    public void setLotteryType(LotteryType lotteryType)
    {
        this.lotteryType = lotteryType;
    }

    public void setTermNo(String termNo)
    {
        this.termNo = termNo;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public int getMultiple()
    {
        return multiple;
    }

    public void setMultiple(int multiple)
    {
        this.multiple = multiple;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public Chase getChase()
    {
        return chase;
    }

    public void setChase(Chase chase)
    {
        this.chase = chase;
    }

    public ChaseItermStatus getStatus()
    {
        return status;
    }

    public void setStatus(ChaseItermStatus status)
    {
        this.status = status;
    }
}
