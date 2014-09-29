package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 合买参与 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_part")
@SequenceGenerator(name = "S_Part", allocationSize = 1, initialValue = 1, sequenceName = "S_Part")
public class Part extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Part")
    private Long id;

    /** 参与的合买 */
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    /** 客户 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 购买份数 */
    private int partNum;

    /** 购买金额 */
    private BigDecimal money;

    /** 认购时间 **/
    private Calendar joinTime;

    /** 中奖金额 */
    private BigDecimal winTaxMoney = new BigDecimal(0);

    /** 方案 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public Part()
    {
    };

    public Part(Community community, Customer customer, int partNum)
    {
        this.community = community;
        this.customer = customer;
        this.partNum = partNum;
        this.money = new BigDecimal(partNum).multiply(community.getPerMoney());
        this.joinTime = Calendar.getInstance();
        this.plan = community.getPlan();
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public Community getCommunity()
    {
        return community;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public int getPartNum()
    {
        return partNum;
    }

    public Calendar getJoinTime()
    {
        return joinTime;
    }

    public void setJoinTime(Calendar joinTime)
    {
        this.joinTime = joinTime;
    }

    public void setCommunity(Community community)
    {
        this.community = community;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setPartNum(int partNum)
    {
        this.partNum = partNum;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getWinTaxMoney()
    {
        return winTaxMoney;
    }

    public void setWinTaxMoney(BigDecimal winTaxMoney)
    {
        this.winTaxMoney = winTaxMoney;
    }

    public Plan getPlan()
    {
        return plan;
    }

    public void setPlan(Plan plan)
    {
        this.plan = plan;
    }
}
