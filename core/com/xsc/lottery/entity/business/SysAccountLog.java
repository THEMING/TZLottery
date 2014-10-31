/**
 * <pre>
 * Title: 		SysAccountLog.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-26 下午04:38:57
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xsc.lottery.entity.BaseObject;

/**
 * <pre>
 * TODO 系统日志记录
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-26
 */
@Entity
@Table(name = "business_sys_account_log")
public class SysAccountLog extends BaseObject
{

	private static final long serialVersionUID = 1L;

	/* （非 Javadoc）
	 * @see com.xsc.lottery.entity.BaseObject#getId()
	 */
	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_account_id", nullable = false)
    private SysAccount sysAccount;
	
	/*
	 *交易后余额 
	 */
    private BigDecimal currentMoney;
	
    /*
     * 收入
     */
    private BigDecimal inMoney;
    
    /*
     * 支出
     */
    private BigDecimal outMoney;
    
    /*
     * 交易时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Calendar time;
    
    /*
     * 描述
     */
    private String description;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public SysAccount getSysAccount()
	{
		return sysAccount;
	}

	public void setSysAccount(SysAccount sysAccount)
	{
		this.sysAccount = sysAccount;
	}

	public BigDecimal getCurrentMoney()
	{
		return currentMoney;
	}

	public void setCurrentMoney(BigDecimal currentMoney)
	{
		this.currentMoney = currentMoney;
	}

	public BigDecimal getInMoney()
	{
		return inMoney;
	}

	public void setInMoney(BigDecimal inMoney)
	{
		this.inMoney = inMoney;
	}

	public BigDecimal getOutMoney()
	{
		return outMoney;
	}

	public void setOutMoney(BigDecimal outMoney)
	{
		this.outMoney = outMoney;
	}

	public Calendar getTime()
	{
		return time;
	}

	public void setTime(Calendar time)
	{
		this.time = time;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	

}
