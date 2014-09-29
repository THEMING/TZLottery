package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.util.MathUtil;

/** 方案 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_plan")
@SequenceGenerator(name = "S_Plan", allocationSize = 1, initialValue = 1, sequenceName = "S_Plan")
public class Plan extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Plan")
    private Long id;

    /** 彩种 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private LotteryType type;

    /** 方案需花的银两 */
    @Column(nullable = false)
    private BigDecimal money;

    /** 生成时间 */
    private Calendar buildTime;

    /** 方案号 */
    public String numberNo = MathUtil.getSerialNumber(16);

    /** 单注金额---默认每注2元乐,透菜种追加3元.开乐彩普通投注为1元 */
    private BigDecimal oneMoney = new BigDecimal(2);

    /** 方案内容 */
    @OneToMany(mappedBy = "plan", cascade = { CascadeType.ALL })
    private List<PlanItem> items;

    public Plan()
    {
    };

    public Plan(LotteryType type, BigDecimal money, BigDecimal oneMoney)
    {
        this.type = type;
        this.money = money;
        this.oneMoney = oneMoney;
        this.buildTime = Calendar.getInstance();
    }

    public Calendar getBuildTime()
    {
        return buildTime;
    }

    public void setBuildTime(Calendar buildTime)
    {
        this.buildTime = buildTime;
    }

    public Long getId()
    {
        return id;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public void setItems(List<PlanItem> items)
    {
        this.items = items;
    }

    public List<PlanItem> getItems()
    {
        return items;
    }

    public String getNumberNo()
    {
        return numberNo;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = numberNo;
    }

    public BigDecimal getOneMoney()
    {
        return oneMoney;
    }

    public void setOneMoney(BigDecimal oneMoney)
    {
        this.oneMoney = oneMoney;
    }

}
