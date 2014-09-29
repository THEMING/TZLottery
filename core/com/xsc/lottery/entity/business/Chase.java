package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.ChaseStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PloyType;

/** 追号 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_chase")
@SequenceGenerator(name = "S_Chase", allocationSize = 1, initialValue = 1, sequenceName = "S_Chase")
public class Chase extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Chase")
    private Long id;

    /** 追号的客户 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 方案 */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    /** 彩种 */
    @Enumerated(EnumType.STRING)
    @Column(name = "lottery_type", nullable = false)
    private LotteryType lotteryType;

    /** 总期数 */
    private int termNum = 0;

    /** 已完成期数 */
    private int oktermNum = 0;

    /** 追号状态 */
    private ChaseStatus status = ChaseStatus.追号中;

    /** 方案内容 */
    @OneToMany(mappedBy = "chase")
    private List<ChaseItem> items;

    /** 追号总金额 */
    private BigDecimal money;

    /** 停追金额 */
    @Column(name = "stop_money")
    private BigDecimal stopMoney;

    /** 是否中奖 */
    @Column(name = "is_winprize")
    private boolean winprize = false;

    /** 税后中奖金额 */
    private BigDecimal winTaxMoney = BigDecimal.ZERO;

    private PloyType ployType = PloyType.常规;

    public PloyType getPloyType()
    {
        return ployType;
    }

    public void setPloyType(PloyType ployType)
    {
        this.ployType = ployType;
    }

    public Chase()
    {
    }

    public Chase(Customer customer, LotteryType lotteryType, BigDecimal money,
            BigDecimal stopMoney, int termNum, Plan plan)
    {
        this.customer = customer;
        this.lotteryType = lotteryType;
        this.money = money;
        this.stopMoney = stopMoney;
        this.termNum = termNum;
        this.plan = plan;
    }

    public void addOktermNum(int num)
    {
        this.oktermNum += num;
        if (this.oktermNum >= termNum) {
            status = ChaseStatus.追号完成;
        }
    }

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

    public BigDecimal getStopMoney()
    {
        return stopMoney;
    }

    public void setStopMoney(BigDecimal stopMoney)
    {
        this.stopMoney = stopMoney;
    }

    public int getTermNum()
    {
        return termNum;
    }

    public void setTermNum(int termNum)
    {
        this.termNum = termNum;
    }

    public int getOktermNum()
    {
        return oktermNum;
    }

    public void setOktermNum(int oktermNum)
    {
        this.oktermNum = oktermNum;
    }

    public List<ChaseItem> getItems()
    {
        return items;
    }

    public void setItems(List<ChaseItem> items)
    {
        this.items = items;
    }

    public ChaseStatus getStatus()
    {
        return status;
    }

    public void setStatus(ChaseStatus status)
    {
        this.status = status;
    }

    public boolean getWinprize()
    {
        return winprize;
    }

    public void setWinprize(boolean winprize)
    {
        this.winprize = winprize;
    }

    public void addWinTaxMoney(BigDecimal winTaxMoney)
    {
        this.winTaxMoney = this.winTaxMoney.add(winTaxMoney);
    }

    public BigDecimal getWinTaxMoney()
    {
        return winTaxMoney;
    }

    public void setWinTaxMoney(BigDecimal winTaxMoney)
    {
        this.winTaxMoney = winTaxMoney;
    }
}
