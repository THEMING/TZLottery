package com.xsc.lottery.entity.business;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
@SuppressWarnings("serial")
@Entity
@Table(name = "matchhistory")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_matchhistory", allocationSize = 1, initialValue = 1, sequenceName = "S_matchhistory")
public class MatchHistoryDetail  extends BaseObject
{
	@Id
	@Column(name = "id")
    private Long id;
	
	@Column(name = "lid")
    private String leagueId;
	
	@Column(name = "dt")
	private String startTime;
	
	@Column(name = "hid")
	private String hostId;
	
	@Column(name = "aid")
	private String visitId;
	
	@Column(name = "st")
	private String status;
	
	@Column(name = "hs")
	private String hostGoal;
	
	@Column(name = "as")
	private String visitGoal;
	
	@Column(name = "ln")
	private String leagueName;
	
	@Column(name = "hn")
	private String hostName;
	
	@Column(name = "an")
	private String visitName;

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHostGoal() {
		return hostGoal;
	}

	public void setHostGoal(String hostGoal) {
		this.hostGoal = hostGoal;
	}

	public String getVisitGoal() {
		return visitGoal;
	}

	public void setVisitGoal(String visitGoal) {
		this.visitGoal = visitGoal;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}	
}
