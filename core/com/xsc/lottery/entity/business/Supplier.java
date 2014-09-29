package com.xsc.lottery.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.util.LotteryTypeUtil;

/** 供应商列表 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_supplier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Supplier extends BaseObject {
	@Id
    @GeneratedValue
    private Long id;
	
	//出票商的名字
	@Column(nullable = false)
    private String name;
	
	//出票商的编号
	@Column(nullable = false)
    private int code;
	
	//可以出票的彩种类型
	@Column(nullable = false)
    private int type;
	
	//优先级
	@Column(nullable = false)
    private int priority;
	
	//状态
	@Column(nullable = false)
    private int status;
	
	//修改的人
	@Column(nullable = false)
    private String changeby;
	
	//说明
	@Column(nullable = false)
    private String memo;
	
	public static int[] errcount = new int[100];
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getChangeby() {
		return changeby;
	}

	public void setChangeby(String changeby) {
		this.changeby = changeby;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	};
	
	public static void setSuccess(LotteryType lotteryType)
	{
		int nType = LotteryTypeUtil.changeLotteryTypeToNum(lotteryType);
		errcount[nType] = 0;
	}
	
	public static void setFalse(LotteryType lotteryType)
	{
		int nType = LotteryTypeUtil.changeLotteryTypeToNum(lotteryType);
		errcount[nType]++;
		if(errcount[nType] >= 5)
		{	
			errcount[nType] = 0;
		}
	}
}
