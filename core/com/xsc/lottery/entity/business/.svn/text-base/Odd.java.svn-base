package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_odd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Odd", allocationSize = 1, initialValue = 1, sequenceName = "S_Odd")
public class Odd extends BaseObject
{

	@Id
    @GeneratedValue
	private Long id;
	
	private String matchId;
	
	private String pankouType;
	
	private String pankouId;
	
	private String rang;
	
	private String sheng;
	
	private String ping;
	
	private String fu;
	
	private Calendar time;
	
    @ManyToOne
	@JoinColumn(name = "company_id" )
	private Company company;
	 
	private String rangStatus;
	
	private String shengStatus;
	
	private String fuStatus;
	
	private String pingStatus;
	
	@Transient
	private String rangOther;
	
	@Override
	public Long getId() {
		
		return id;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public String getPankouType() {
		return pankouType;
	}
	public void setPankouType(String pankouType) {
		this.pankouType = pankouType;
	}
	public String getPankouId() {
		return pankouId;
	}
	public void setPankouId(String pankouId) {
		this.pankouId = pankouId;
	}
	public String getRang() {
		return rang;
	}
	public void setRang(String rang) {
		this.rang = rang;
	}
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getPing() {
		return ping;
	}
	public void setPing(String ping) {
		this.ping = ping;
	}
	public String getFu() {
		return fu;
	}
	public void setFu(String fu) {
		this.fu = fu;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getRangStatus() {
		return rangStatus;
	}
	public void setRangStatus(String rangStatus) {
		this.rangStatus = rangStatus;
	}
	public String getShengStatus() {
		return shengStatus;
	}
	public void setShengStatus(String shengStatus) {
		this.shengStatus = shengStatus;
	}
	public String getFuStatus() {
		return fuStatus;
	}
	public void setFuStatus(String fuStatus) {
		this.fuStatus = fuStatus;
	}
	public String getPingStatus() {
		return pingStatus;
	}
	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	public String getRangOther() {
		return rangOther;
	}
	public void setRangOther(String rangOther) {
		this.rangOther = rangOther;
	}
}
