package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 出票商的充值记录 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_pay")
@SequenceGenerator(name = "S_Pay", allocationSize = 1, initialValue = 1, sequenceName = "S_Pay")
public class SupplierPay extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;
    
    /**出票商名称*/
    @Column(nullable = true)
    private String supplierName;

    /** 充值金额 */
    @Column(nullable = true)
    private BigDecimal money = BigDecimal.ZERO;

    /** 充值时间 */
    @Column(nullable = true)
    private Calendar time;

    /** 付款人姓名 */
    @Column(nullable = true)
    private String payer;

    /** 记录人姓名 **/
    @Column(nullable = true)
    private String recorder;

    /** 状态 */
    @Column(nullable = true)
    private int status;
    
    /**说明*/
    private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
