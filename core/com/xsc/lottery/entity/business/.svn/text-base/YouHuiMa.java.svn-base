package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.active.Activity;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_you_hui_ma")
@SequenceGenerator(name = "S_youHuiMa", allocationSize = 1, initialValue = 1, sequenceName = "S_youHuiMa")
public class YouHuiMa extends BaseObject {
	
	public enum YouHuiMaType{
		未激活,已激活,已兑换,已过期
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	@Column(nullable = false)
	private BigDecimal money;

	@Column(nullable = false)
	@Enumerated(javax.persistence.EnumType.ORDINAL)
	private YouHuiMaType type; // 未激活 已激活 已兑换
	
	/**
	 * 优惠券密码
	 */
	private String numberPwd;
	
	/**
	 * 优惠券用户
	 */
    @ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
    
    /**
     * 创建时间
     */
    private Calendar createTime;
    
    /**
     * 使用时间
     */
    private Calendar useTime;
    
    /**
     * 有效时间
     */
    private Calendar effectiveDate;
    
    /**
     * 活动ID
     */
    @ManyToOne
	@JoinColumn(name = "activity_id")
    private Activity activity;
    
    /**
     * 批次号
     */
    private Long batchId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public YouHuiMaType getType() {
		return type;
	}

	public void setType(YouHuiMaType type) {
		this.type = type;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getNumberPwd() {
		return numberPwd;
	}

	public void setNumberPwd(String numberPwd) {
		this.numberPwd = numberPwd;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUseTime() {
		return useTime;
	}

	public void setUseTime(Calendar useTime) {
		this.useTime = useTime;
	}

	public Calendar getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Calendar effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

}
