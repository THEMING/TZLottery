package com.xsc.lottery.entity.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "odds")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Odd", allocationSize = 1, initialValue = 1, sequenceName = "S_Odd")
public class Odds extends BaseObject
{
	@Id
    @GeneratedValue
	private Long id;
	
	private String  matchId;
	
	private String  companyId;
	
	private String  enCompanyName;
	
	private String  cnCompanyName;
	
	private String  trCompanyName;
	
	private String  et;
	
	private String  p;
	
	private String  t;
	
	private String  h;
	
	private String  o;
	
	private String  u;
	
	private String  time;
	
	private String  vst;
	
	private String  ped;
	
	private String  creatdt;
	
	private String  updatedt;
	
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEnCompanyName() {
		return enCompanyName;
	}

	public void setEnCompanyName(String enCompanyName) {
		this.enCompanyName = enCompanyName;
	}

	public String getCnCompanyName() {
		return cnCompanyName;
	}

	public void setCnCompanyName(String cnCompanyName) {
		this.cnCompanyName = cnCompanyName;
	}

	public String getTrCompanyName() {
		return trCompanyName;
	}

	public void setTrCompanyName(String trCompanyName) {
		this.trCompanyName = trCompanyName;
	}

	public String getEt() {
		return et;
	}

	public void setEt(String et) {
		this.et = et;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVst() {
		return vst;
	}

	public void setVst(String vst) {
		this.vst = vst;
	}

	public String getPed() {
		return ped;
	}

	public void setPed(String ped) {
		this.ped = ped;
	}

	public String getUpdatedt() {
		return updatedt;
	}

	public void setUpdatedt(String updatedt) {
		this.updatedt = updatedt;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
