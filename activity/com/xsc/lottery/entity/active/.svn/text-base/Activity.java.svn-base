package com.xsc.lottery.entity.active;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "activity_activities")
public class Activity extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;
    
    /** 活动名称 */
    private String name;

    /** 活动简称 */
    private String shortName;

    /** 活动开始时间 */
    @Column(nullable = false)
    private Calendar startTime;

    /** 活动结束时间 */
    @Column(nullable = false)
    private Calendar endTime;

    /** 活动状态 */
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatus status = ActivityStatus.待进行;
    
    /** 活动类型 */
    @Enumerated(EnumType.ORDINAL)
    private ActivityType type = ActivityType.普通;
    
    /** 活动时的订单类型 */
    @Enumerated(EnumType.ORDINAL)
    private ActivityOrderType orderType = ActivityOrderType.全部;
    
    /** 加奖 */
    private BigDecimal givenMoney = BigDecimal.ZERO;
    
    /** 限额 */
    private BigDecimal threshold = BigDecimal.ZERO;
    
    public BigDecimal getGivenMoney()
    {
        return givenMoney;
    }

    public void setGivenMoney(BigDecimal givenMoney)
    {
        this.givenMoney = givenMoney;
    }

    public BigDecimal getThreshold()
    {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold)
    {
        this.threshold = threshold;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public Calendar getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Calendar startTime)
    {
        this.startTime = startTime;
    }

    public Calendar getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Calendar endTime)
    {
        this.endTime = endTime;
    }

    public ActivityStatus getStatus()
    {
        return status;
    }

    public void setStatus(ActivityStatus status)
    {
        this.status = status;
    }

    public ActivityType getType()
    {
        return type;
    }

    public void setType(ActivityType type)
    {
        this.type = type;
    }

    public ActivityOrderType getOrderType()
    {
        return orderType;
    }

    public void setOrderType(ActivityOrderType orderType)
    {
        this.orderType = orderType;
    }

}
