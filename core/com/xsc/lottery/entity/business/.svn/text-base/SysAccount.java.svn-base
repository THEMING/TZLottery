/**
 * <pre>
 * Title: 		SystemAccount.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-26 下午12:06:11
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/**
 * <pre>
 * TODO 系统账户（活动赠送总账户）
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-26
 */
@Entity
@Table(name = "business_sys_account")
public class SysAccount  extends BaseObject
{
	private static final long serialVersionUID = -1709878464009544079L;

	@Id
    @GeneratedValue
    private Long id;
	
	/*
	 * 余额
	 */
    private BigDecimal balance = BigDecimal.ZERO;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}
}
