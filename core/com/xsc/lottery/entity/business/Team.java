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
@Table(name = "business_team")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Team", allocationSize = 1, initialValue = 1, sequenceName = "S_Team")
public class Team  extends BaseObject
{
	@Id
    private Long id;
	
	@Column(nullable = false)
    private String name;
	
	//常规联赛的排名(如：英超，德甲，西甲等)
	private String rank;
	
	private String hrank;
	
	private String arank;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getHrank() {
		return hrank;
	}

	public void setHrank(String hrank) {
		this.hrank = hrank;
	}

	public String getArank() {
		return arank;
	}

	public void setArank(String arank) {
		this.arank = arank;
	}
}
