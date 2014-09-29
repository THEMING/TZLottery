package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.Restrictedstatus;

/** 彩种限售列表 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_restricted")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Restricted", allocationSize = 1, initialValue = 1, sequenceName = "S_Restricted")
public class Restricted extends BaseObject {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "lottery_type", nullable = false)
	private LotteryType type;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "restricted_status", nullable = false)
	private Restrictedstatus restrictedstatus;

	@Column(name = "start_time")
	private Calendar startTime;

	@Column(name = "end_time")
	private Calendar endTime;

	@Column(name = "why_desc")
	private String desc;

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public Restrictedstatus getRestrictedstatus() {
		return restrictedstatus;
	}

	public void setRestrictedstatus(Restrictedstatus restrictedstatus) {
		this.restrictedstatus = restrictedstatus;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
