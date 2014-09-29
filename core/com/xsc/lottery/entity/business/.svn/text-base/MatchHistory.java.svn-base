package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_match_history")
@SequenceGenerator(name = "S_matchArrange", allocationSize = 1, initialValue = 1, sequenceName = "S_matchArrange")
public class MatchHistory extends BaseObject
{
    @Id
    // (strategy=GenerationType.SEQUENCE, generator="S_newlyWinPrize")
    private Long id;
    
    /**比赛开始时间*/
    private Calendar matchStartTime;
    
    /** 比赛状态 */
    private String status;
    
    
    /**主队半场进球数*/
    private int hostHalfScore;
    
    /**客队半场进球数*/
    private int visitHalfScore;
    
    /**主队全场进球数*/
    private int hostScore;
    
    /**客队全场进球数*/
    private int visitScore;
    
    /**盘口   */
    private String pankou;
    
    /**联赛名称*/
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;
    
    private String leagueName;
    
    /**主队名*/
    @ManyToOne
    @JoinColumn(name = "hostTeam_id")
    private Team hostTeam;
    
    /**客队名*/
    @ManyToOne
    @JoinColumn(name = "visitTeam_id")
    private Team visitTeam;
    
    @Transient
    private String pankouOther;
    
    @Transient
    private String panshi;
    
    @Transient
    private Integer winNo;
    
    @Transient
    private Integer drowNo;
    
    @Transient
    private Integer loseNo;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getPankou() {
		return pankou;
	}

	public void setPankou(String pankou) {
		this.pankou = pankou;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Calendar getMatchStartTime() {
		return matchStartTime;
	}

	public void setMatchStartTime(Calendar matchStartTime) {
		this.matchStartTime = matchStartTime;
	}

	public int getHostHalfScore() {
		return hostHalfScore;
	}

	public void setHostHalfScore(int hostHalfScore) {
		this.hostHalfScore = hostHalfScore;
	}

	public int getVisitHalfScore() {
		return visitHalfScore;
	}

	public void setVisitHalfScore(int visitHalfScore) {
		this.visitHalfScore = visitHalfScore;
	}

	public int getHostScore() {
		return hostScore;
	}

	public void setHostScore(int hostScore) {
		this.hostScore = hostScore;
	}

	public int getVisitScore() {
		return visitScore;
	}

	public void setVisitScore(int visitScore) {
		this.visitScore = visitScore;
	}

	public Team getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(Team hostTeam) {
		this.hostTeam = hostTeam;
	}

	public Team getVisitTeam() {
		return visitTeam;
	}

	public void setVisitTeam(Team visitTeam) {
		this.visitTeam = visitTeam;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getPankouOther() {
		return pankouOther;
	}

	public void setPankouOther(String pankouOther) {
		this.pankouOther = pankouOther;
	}

	public String getPanshi() {
		return panshi;
	}

	public void setPanshi(String panshi) {
		this.panshi = panshi;
	}

	public Integer getWinNo() {
		return winNo;
	}

	public void setWinNo(Integer winNo) {
		this.winNo = winNo;
	}

	public Integer getDrowNo() {
		return drowNo;
	}

	public void setDrowNo(Integer drowNo) {
		this.drowNo = drowNo;
	}

	public Integer getLoseNo() {
		return loseNo;
	}

	public void setLoseNo(Integer loseNo) {
		this.loseNo = loseNo;
	}
}
