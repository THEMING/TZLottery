package com.xsc.lottery.entity.business;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_league_rank")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_League_rank", allocationSize = 1, initialValue = 1, sequenceName = "S_League_rank")
public class LeagueRank  extends BaseObject
{
	@Id
	@GeneratedValue
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "league_id", nullable = false)
    private League league;
	
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
    private Team team;
	
	//联赛排名
	private Integer rank;
	
	//总的比赛场次
	private Integer allRound;
	
	//赢的场次
	private Integer winRound;
	
	//平的场次
	private Integer drowRound;
	
	//负的场次
	private Integer loseRound;
	
	//进球数
	private Integer winNum;
	
	//失球数
	private Integer loseNum;
	
	//得分
	private Integer score;
	
	//红牌数
	private Integer redNum;
	
	//黄牌数
	private Integer yellowNum;
	
	//积分
	private Integer points;
	
	//主榜联赛排名
	private Integer hrank;
	
	//总的比赛场次
	private Integer hallRound;
	
	//赢的场次
	private Integer hwinRound;
	
	//平的场次
	private Integer hdrowRound;
	
	//负的场次
	private Integer hloseRound;
	
	//进球数
	private Integer hwinNum;
	
	//失球数
	private Integer hloseNum;
	
	//得分
	private Integer hscore;
	
	//红牌数
	private Integer hredNum;
	
	//黄牌数
	private Integer hyellowNum;
	
	//积分
	private Integer hpoints;
	
	//客榜联赛排名
	private Integer arank;
	
	//总的比赛场次
	private Integer aallRound;
	
	//赢的场次
	private Integer awinRound;
	
	//平的场次
	private Integer adrowRound;
	
	//负的场次
	private Integer aloseRound;
	
	//进球数
	private Integer awinNum;
	
	//失球数
	private Integer aloseNum;
	
	//得分
	private Integer ascore;
	
	//红牌数
	private Integer aredNum;
	
	//黄牌数
	private Integer ayellowNum;
	
	//积分
	private Integer apoints;

	//6榜联赛排名
	private Integer srank;
	
	//总的比赛场次
	private Integer sallRound;
	
	//赢的场次
	private Integer swinRound;
	
	//平的场次
	private Integer sdrowRound;
	
	//负的场次
	private Integer sloseRound;
	
	//进球数
	private Integer swinNum;
	
	//失球数
	private Integer sloseNum;
	
	//得分
	private Integer sscore;
	
	//红牌数
	private Integer sredNum;
	
	//黄牌数
	private Integer syellowNum;
	
	//积分
	private Integer spoints;
	
	private Integer shrank;
	
	//总的比赛场次
	private Integer shallRound;
	
	//赢的场次
	private Integer shwinRound;
	
	//平的场次
	private Integer shdrowRound;
	
	//负的场次
	private Integer shloseRound;
	
	//进球数
	private Integer shwinNum;
	
	//失球数
	private Integer shloseNum;
	
	//得分
	private Integer shscore;
	
	//红牌数
	private Integer shredNum;
	
	//黄牌数
	private Integer shyellowNum;
	
	//积分
	private Integer shpoints;

private Integer sarank;
	
	public Integer getShrank() {
	return shrank;
}

public void setShrank(Integer shrank) {
	this.shrank = shrank;
}

public Integer getShallRound() {
	return shallRound;
}

public void setShallRound(Integer shallRound) {
	this.shallRound = shallRound;
}

public Integer getShwinRound() {
	return shwinRound;
}

public void setShwinRound(Integer shwinRound) {
	this.shwinRound = shwinRound;
}

public Integer getShdrowRound() {
	return shdrowRound;
}

public void setShdrowRound(Integer shdrowRound) {
	this.shdrowRound = shdrowRound;
}

public Integer getShloseRound() {
	return shloseRound;
}

public void setShloseRound(Integer shloseRound) {
	this.shloseRound = shloseRound;
}

public Integer getShwinNum() {
	return shwinNum;
}

public void setShwinNum(Integer shwinNum) {
	this.shwinNum = shwinNum;
}

public Integer getShloseNum() {
	return shloseNum;
}

public void setShloseNum(Integer shloseNum) {
	this.shloseNum = shloseNum;
}

public Integer getShscore() {
	return shscore;
}

public void setShscore(Integer shscore) {
	this.shscore = shscore;
}

public Integer getShredNum() {
	return shredNum;
}

public void setShredNum(Integer shredNum) {
	this.shredNum = shredNum;
}

public Integer getShyellowNum() {
	return shyellowNum;
}

public void setShyellowNum(Integer shyellowNum) {
	this.shyellowNum = shyellowNum;
}

public Integer getShpoints() {
	return shpoints;
}

public void setShpoints(Integer shpoints) {
	this.shpoints = shpoints;
}

public Integer getSarank() {
	return sarank;
}

public void setSarank(Integer sarank) {
	this.sarank = sarank;
}

public Integer getSaallRound() {
	return saallRound;
}

public void setSaallRound(Integer saallRound) {
	this.saallRound = saallRound;
}

public Integer getSawinRound() {
	return sawinRound;
}

public void setSawinRound(Integer sawinRound) {
	this.sawinRound = sawinRound;
}

public Integer getSadrowRound() {
	return sadrowRound;
}

public void setSadrowRound(Integer sadrowRound) {
	this.sadrowRound = sadrowRound;
}

public Integer getSaloseRound() {
	return saloseRound;
}

public void setSaloseRound(Integer saloseRound) {
	this.saloseRound = saloseRound;
}

public Integer getSawinNum() {
	return sawinNum;
}

public void setSawinNum(Integer sawinNum) {
	this.sawinNum = sawinNum;
}

public Integer getSaloseNum() {
	return saloseNum;
}

public void setSaloseNum(Integer saloseNum) {
	this.saloseNum = saloseNum;
}

public Integer getSascore() {
	return sascore;
}

public void setSascore(Integer sascore) {
	this.sascore = sascore;
}

public Integer getSaredNum() {
	return saredNum;
}

public void setSaredNum(Integer saredNum) {
	this.saredNum = saredNum;
}

public Integer getSayellowNum() {
	return sayellowNum;
}

public void setSayellowNum(Integer sayellowNum) {
	this.sayellowNum = sayellowNum;
}

public Integer getSapoints() {
	return sapoints;
}

public void setSapoints(Integer sapoints) {
	this.sapoints = sapoints;
}

	//总的比赛场次
	private Integer saallRound;
	
	//赢的场次
	private Integer sawinRound;
	
	//平的场次
	private Integer sadrowRound;
	
	//负的场次
	private Integer saloseRound;
	
	//进球数
	private Integer sawinNum;
	
	//失球数
	private Integer saloseNum;
	
	//得分
	private Integer sascore;
	
	//红牌数
	private Integer saredNum;
	
	//黄牌数
	private Integer sayellowNum;
	
	//积分
	private Integer sapoints;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getAllRound() {
		return allRound;
	}

	public void setAllRound(Integer allRound) {
		this.allRound = allRound;
	}

	public Integer getWinRound() {
		return winRound;
	}

	public void setWinRound(Integer winRound) {
		this.winRound = winRound;
	}

	public Integer getDrowRound() {
		return drowRound;
	}

	public void setDrowRound(Integer drowRound) {
		this.drowRound = drowRound;
	}

	public Integer getLoseRound() {
		return loseRound;
	}

	public void setLoseRound(Integer loseRound) {
		this.loseRound = loseRound;
	}

	public Integer getWinNum() {
		return winNum;
	}

	public void setWinNum(Integer winNum) {
		this.winNum = winNum;
	}

	public Integer getLoseNum() {
		return loseNum;
	}

	public void setLoseNum(Integer loseNum) {
		this.loseNum = loseNum;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getRedNum() {
		return redNum;
	}

	public void setRedNum(Integer redNum) {
		this.redNum = redNum;
	}

	public Integer getYellowNum() {
		return yellowNum;
	}

	public void setYellowNum(Integer yellowNum) {
		this.yellowNum = yellowNum;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getHrank() {
		return hrank;
	}

	public void setHrank(Integer hrank) {
		this.hrank = hrank;
	}

	public Integer getHallRound() {
		return hallRound;
	}

	public void setHallRound(Integer hallRound) {
		this.hallRound = hallRound;
	}

	public Integer getHwinRound() {
		return hwinRound;
	}

	public void setHwinRound(Integer hwinRound) {
		this.hwinRound = hwinRound;
	}

	public Integer getHdrowRound() {
		return hdrowRound;
	}

	public void setHdrowRound(Integer hdrowRound) {
		this.hdrowRound = hdrowRound;
	}

	public Integer getHloseRound() {
		return hloseRound;
	}

	public void setHloseRound(Integer hloseRound) {
		this.hloseRound = hloseRound;
	}

	public Integer getHwinNum() {
		return hwinNum;
	}

	public void setHwinNum(Integer hwinNum) {
		this.hwinNum = hwinNum;
	}

	public Integer getHloseNum() {
		return hloseNum;
	}

	public void setHloseNum(Integer hloseNum) {
		this.hloseNum = hloseNum;
	}

	public Integer getHscore() {
		return hscore;
	}

	public void setHscore(Integer hscore) {
		this.hscore = hscore;
	}

	public Integer getHredNum() {
		return hredNum;
	}

	public void setHredNum(Integer hredNum) {
		this.hredNum = hredNum;
	}

	public Integer getHyellowNum() {
		return hyellowNum;
	}

	public void setHyellowNum(Integer hyellowNum) {
		this.hyellowNum = hyellowNum;
	}

	public Integer getHpoints() {
		return hpoints;
	}

	public void setHpoints(Integer hpoints) {
		this.hpoints = hpoints;
	}

	public Integer getArank() {
		return arank;
	}

	public void setArank(Integer arank) {
		this.arank = arank;
	}

	public Integer getAallRound() {
		return aallRound;
	}

	public void setAallRound(Integer aallRound) {
		this.aallRound = aallRound;
	}

	public Integer getAwinRound() {
		return awinRound;
	}

	public void setAwinRound(Integer awinRound) {
		this.awinRound = awinRound;
	}

	public Integer getAdrowRound() {
		return adrowRound;
	}

	public void setAdrowRound(Integer adrowRound) {
		this.adrowRound = adrowRound;
	}

	public Integer getAloseRound() {
		return aloseRound;
	}

	public void setAloseRound(Integer aloseRound) {
		this.aloseRound = aloseRound;
	}

	public Integer getAwinNum() {
		return awinNum;
	}

	public void setAwinNum(Integer awinNum) {
		this.awinNum = awinNum;
	}

	public Integer getAloseNum() {
		return aloseNum;
	}

	public void setAloseNum(Integer aloseNum) {
		this.aloseNum = aloseNum;
	}

	public Integer getAscore() {
		return ascore;
	}

	public void setAscore(Integer ascore) {
		this.ascore = ascore;
	}

	public Integer getAredNum() {
		return aredNum;
	}

	public void setAredNum(Integer aredNum) {
		this.aredNum = aredNum;
	}

	public Integer getAyellowNum() {
		return ayellowNum;
	}

	public void setAyellowNum(Integer ayellowNum) {
		this.ayellowNum = ayellowNum;
	}

	public Integer getApoints() {
		return apoints;
	}

	public void setApoints(Integer apoints) {
		this.apoints = apoints;
	}

	public Integer getSrank() {
		return srank;
	}

	public void setSrank(Integer srank) {
		this.srank = srank;
	}

	public Integer getSallRound() {
		return sallRound;
	}

	public void setSallRound(Integer sallRound) {
		this.sallRound = sallRound;
	}

	public Integer getSwinRound() {
		return swinRound;
	}

	public void setSwinRound(Integer swinRound) {
		this.swinRound = swinRound;
	}

	public Integer getSdrowRound() {
		return sdrowRound;
	}

	public void setSdrowRound(Integer sdrowRound) {
		this.sdrowRound = sdrowRound;
	}

	public Integer getSloseRound() {
		return sloseRound;
	}

	public void setSloseRound(Integer sloseRound) {
		this.sloseRound = sloseRound;
	}

	public Integer getSwinNum() {
		return swinNum;
	}

	public void setSwinNum(Integer swinNum) {
		this.swinNum = swinNum;
	}

	public Integer getSloseNum() {
		return sloseNum;
	}

	public void setSloseNum(Integer sloseNum) {
		this.sloseNum = sloseNum;
	}

	public Integer getSscore() {
		return sscore;
	}

	public void setSscore(Integer sscore) {
		this.sscore = sscore;
	}

	public Integer getSredNum() {
		return sredNum;
	}

	public void setSredNum(Integer sredNum) {
		this.sredNum = sredNum;
	}

	public Integer getSyellowNum() {
		return syellowNum;
	}

	public void setSyellowNum(Integer syellowNum) {
		this.syellowNum = syellowNum;
	}

	public Integer getSpoints() {
		return spoints;
	}

	public void setSpoints(Integer spoints) {
		this.spoints = spoints;
	}
}
