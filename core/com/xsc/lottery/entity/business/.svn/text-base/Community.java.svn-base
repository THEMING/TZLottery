package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
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

import org.hibernate.annotations.Formula;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.SecretType;

/** 合买 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_community")
@SequenceGenerator(name = "S_Community", allocationSize = 1, initialValue = 1, sequenceName = "S_Community")
public class Community extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Community")
    private Long id;

    /** 方案 */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    /** 方案描述 */
    private String description;
    /** 倍数 */
    @Column(nullable = false)
    private int multiple;

    /** 彩期 */
    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    private LotteryTerm term;
    
    /** 最近一场比赛对阵 */
    @ManyToOne
    @JoinColumn(name = "match_id")
    private MatchArrange firstMatch;
    
    /** 最后一场比赛对阵 */
    @ManyToOne
    @JoinColumn(name = "lastMatch_id")
    private MatchArrange lastMatch;
    
    /** 发起人 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 每份金额 */
    private BigDecimal perMoney = BigDecimal.valueOf(1);

    /** 总份数 */
    private int totalPart;

    /** 已完成份数 */
    private int okPart = 0;

    /** 已完成购买金额 */
    @Transient
    public BigDecimal okBuyMoney;

    /** 所保份数 */
    private int insurePart = 0;

    /** 中奖佣金 实际是百分比 */
    private int commision;

    /** 发起人购买的份数 */
    private int buyPart;

    /** 生成时间 **/
    private Calendar createTimer;

    /** 合买类型 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "communityType", nullable = false)
    private CommunityType communityType = CommunityType.未满员;

    /** 方案进度 */
    @Formula("ok_part*100/total_part")
    public float schedule;

    /** 保底百分比 */
    @Transient
    public float insurepace;

    /** 方案总额 */
    @Transient
    public BigDecimal sumMoney;

    /** 购买金额 */
    @Transient
    public BigDecimal buyMoney;

    @Transient
    public Order order;

    /** 保密状态 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "secretType", nullable = false)
    private SecretType secretType = SecretType.公开;

    public Community()
    {
    };

    public Community(String desc, Customer customer, Plan plan,
            LotteryTerm term, int totalPart, int buyPart, int insurePart,
            int commision, int multiple, BigDecimal perMoney,
            SecretType secretType)
    {

        this.description = desc;
        this.customer = customer;
        this.plan = plan;
        this.term = term;
        this.totalPart = totalPart;
        this.buyPart = buyPart;
        this.insurePart = insurePart;
        this.okPart += buyPart;
        this.createTimer = Calendar.getInstance();
        this.commision = commision;
        this.multiple = multiple;
        this.perMoney = perMoney;
        this.secretType = secretType;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public Plan getPlan()
    {
        return plan;
    }

    public BigDecimal getInsureMoney()
    {
        return perMoney.multiply(BigDecimal.valueOf(insurePart));
    }

    public LotteryTerm getTerm()
    {
        return term;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public BigDecimal getPerMoney()
    {
        return perMoney;
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

    public int getTotalPart()
    {
        return totalPart;
    }

    public int getOkPart()
    {
        return okPart;
    }

    public int getInsurePart()
    {
        return insurePart;
    }

    public Calendar getCreateTimer()
    {
        return createTimer;
    }

    public void setCreateTimer(Calendar createTimer)
    {
        this.createTimer = createTimer;
    }

    public int getBuyPart()
    {
        return buyPart;
    }

    public void setBuyPart(int buyPart)
    {
        this.buyPart = buyPart;
    }

    public void setPlan(Plan plan)
    {
        this.plan = plan;
    }

    public void setTerm(LotteryTerm term)
    {
        this.term = term;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setPerMoney(BigDecimal perMoney)
    {
        this.perMoney = perMoney;
    }

    public void setTotalPart(int totalPart)
    {
        this.totalPart = totalPart;
    }

    public void addOkPart(int okPart)
    {
        this.okPart += okPart;
    }

    public void setInsurePart(int insurePart)
    {
        this.insurePart = insurePart;
    }

    public int getCommision()
    {
        return commision;
    }

    public void setCommision(int commision)
    {
        this.commision = commision;
    }

    public CommunityType getCommunityType()
    {
        return communityType;
    }

    public void setCommunityType(CommunityType communityType)
    {
        this.communityType = communityType;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public SecretType getSecretType()
    {
        return secretType;
    }

    public void setSecretType(SecretType secretType)
    {
        this.secretType = secretType;
    }

    public float getSchedule()
    {
        return schedule;
    }

    public BigDecimal getSumMoney()
    {
        return perMoney.multiply(new BigDecimal(totalPart));
    }

    public void setSumMoney(BigDecimal sumMoney)
    {
        this.sumMoney = perMoney.multiply(new BigDecimal(totalPart));
    }

    public BigDecimal getBuyMoney()
    {
        return perMoney.multiply(new BigDecimal(buyPart));
    }

    public void setBuyMoney(BigDecimal buyMoney)
    {
        this.buyMoney = perMoney.multiply(new BigDecimal(buyPart));
    }

    public BigDecimal getOkBuyMoney()
    {
        return perMoney.multiply(new BigDecimal(okPart));
    }

    public void setOkBuyMoney(BigDecimal okBuyMoney)
    {
        this.okBuyMoney = perMoney.multiply(new BigDecimal(okPart));
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public float getInsurepace()
    {
        return insurePart * 100 / totalPart;
    }

	public MatchArrange getFirstMatch() {
		return firstMatch;
	}

	public void setFirstMatch(MatchArrange firstMatch) {
		this.firstMatch = firstMatch;
	}

	public MatchArrange getLastMatch() {
		return lastMatch;
	}

	public void setLastMatch(MatchArrange lastMatch) {
		this.lastMatch = lastMatch;
	}
}
