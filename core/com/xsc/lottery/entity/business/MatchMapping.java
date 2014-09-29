package com.xsc.lottery.entity.business;


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
@SuppressWarnings("serial")
@Entity
@Table(name = "business_match_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_MatchMapping", allocationSize = 1, initialValue = 1, sequenceName = "S_MatchMapping")
public class MatchMapping  extends BaseObject
{
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
    private String matchNo;
	
	/**比赛序号*/
	@Column(nullable = false)
	private String no;
	
	/**期号*/
	@Column(nullable = false)
	private String termNo;
	
	/**类型*/
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private LotteryType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}
}
