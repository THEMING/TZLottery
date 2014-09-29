package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/**
 * cps日报表统计
 * @author caipiao
 *
 */
@Entity
@Table(name = "business_cps_day_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CpsDayReport extends BaseObject
{

	private static final long serialVersionUID = -2153989973634085028L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	/*
	 * 报表日期 
	 */
	private String reportDate;
	
	/*
	 * 推广用户消费 
	 */
	private BigDecimal total;
	
	/*
	 * 佣金
	 */
	private BigDecimal commission;
	
	/*
	 * 推广人员 
	 */
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	/*
	 * 注册数
	 */
	private Long registerNum;
	
	/*
	 * 消费人数 
	 */
	private Long salesNum;
	
	/*
	 * 创建时间 
	 */
	private Calendar createTime;
	
	/*
	 * 是否已结算
	 */
	private Boolean isPay;
	
	/*
	 * 结算时间 
	 */
	private Calendar payTime;
	
	/*
	 * 充值用户数
	 */
	private Long rechargeNum;

	/*
	 * 充值金额
	 */
	private BigDecimal rechargeMon;

	public Long getRechargeNum()
	{
		return rechargeNum;
	}

	public void setRechargeNum(Long rechargeNum)
	{
		this.rechargeNum = rechargeNum;
	}

	public BigDecimal getRechargeMon()
	{
		return rechargeMon;
	}

	public void setRechargeMon(BigDecimal rechargeMon)
	{
		this.rechargeMon = rechargeMon;
	}
	
	public String getReportDate()
	{
		return reportDate;
	}

	public void setReportDate(String reportDate)
	{
		this.reportDate = reportDate;
	}

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomerId(Customer customer)
	{
		this.customer = customer;
	}

	public Long getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(Long registerNum) {
		this.registerNum = registerNum;
	}

	public Long getSalesNum()
	{
		return salesNum;
	}

	public void setSalesNum(Long salesNum)
	{
		this.salesNum = salesNum;
	}

	public Calendar getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Calendar createTime)
	{
		this.createTime = createTime;
	}

	public Boolean getIsPay()
	{
		return isPay;
	}

	public void setIsPay(Boolean isPay)
	{
		this.isPay = isPay;
	}

	public Calendar getPayTime()
	{
		return payTime;
	}

	public void setPayTime(Calendar payTime)
	{
		this.payTime = payTime;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

}
