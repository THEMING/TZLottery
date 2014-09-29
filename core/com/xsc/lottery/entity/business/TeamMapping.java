package com.xsc.lottery.entity.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_match_teamMapping")
@SequenceGenerator(name = "S_teamMapping", allocationSize = 1, initialValue = 1, sequenceName = "S_teamMapping")
public class TeamMapping extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_newlyWinPrize")
    private Long id;
    
    private String matchHistoryName;
    
    private String matchJCZQName;
    
    private String match14CName;
    
    private String match6CBName;
    
    private String match4CJQName;
    
    private String matchName;
    
    private String originalTeamName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatchHistoryName() {
		return matchHistoryName;
	}

	public void setMatchHistoryName(String matchHistoryName) {
		this.matchHistoryName = matchHistoryName;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getMatchJCZQName() {
		return matchJCZQName;
	}

	public void setMatchJCZQName(String matchJCZQName) {
		this.matchJCZQName = matchJCZQName;
	}

	public String getMatch14CName() {
		return match14CName;
	}

	public void setMatch14CName(String match14CName) {
		this.match14CName = match14CName;
	}

	public String getMatch6CBName() {
		return match6CBName;
	}

	public void setMatch6CBName(String match6CBName) {
		this.match6CBName = match6CBName;
	}

	public String getMatch4CJQName() {
		return match4CJQName;
	}

	public void setMatch4CJQName(String match4CJQName) {
		this.match4CJQName = match4CJQName;
	}

	public String getOriginalTeamName() {
		return originalTeamName;
	}

	public void setOriginalTeamName(String originalTeamName) {
		this.originalTeamName = originalTeamName;
	}
}
