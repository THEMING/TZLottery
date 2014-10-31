package com.xsc.lottery.entity.active;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.partner.Partner;

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
    
    @OneToOne
    @JoinColumn(name = "article_id",nullable = true)
    private Article article;
    
//    @ManyToOne
//    @JoinColumn(name = "partner_id",nullable = true)
//    private Partner partner;
    
    @Cascade(value=CascadeType.SAVE_UPDATE)
    @ManyToMany(fetch = FetchType.LAZY)  
    @JoinTable(name = "business_activity_partner",
    joinColumns =  @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false) ,
    inverseJoinColumns =  @JoinColumn(name = "partner_id", referencedColumnName = "id", nullable = false) )  
    Set<Partner> partners;  
    
    /*是否公开被代理商使用*/
	private Boolean isPublic;
    
    public Boolean getIsPublic()
	{
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic)
	{
		this.isPublic = isPublic;
	}

	public Set<Partner> getPartners()
	{
		return partners;
	}

	public void setPartners(Set<Partner> partners)
	{
		this.partners = partners;
	}

//	public Partner getPartner()
//	{
//		return partner;
//	}
//
//	public void setPartner(Partner partner)
//	{
//		this.partner = partner;
//	}

	public Article getArticle()
	{
		return article;
	}

	public void setArticle(Article article)
	{
		this.article = article;
	}

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
