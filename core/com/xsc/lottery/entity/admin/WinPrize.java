package com.xsc.lottery.entity.admin;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin_winprize")
@SequenceGenerator(name = "S_winprize", allocationSize = 1, initialValue = 1, sequenceName = "S_winprize")
public class WinPrize extends BaseObject {

	@Id
	@GeneratedValue
	private Long id;
    
	private String userName;
    
	private String type;
    
	private Calendar winTime;
	
	private BigDecimal bonus;
	
	private String rankingType;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getWinTime() {
		return winTime;
	}

	public void setWinTime(Calendar winTime) {
		this.winTime = winTime;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getRankingType() {
		return rankingType;
	}

	public void setRankingType(String rankingType) {
		this.rankingType = rankingType;
	}

	@Override
	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
