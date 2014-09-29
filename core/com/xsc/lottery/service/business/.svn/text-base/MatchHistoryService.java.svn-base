/**
 * 
 */
package com.xsc.lottery.service.business;

import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.service.LotteryBaseService;

public interface MatchHistoryService extends LotteryBaseService<MatchHistory>
{
	public List<MatchHistory> getHistoryByKeyWord(String s);
	public Page<MatchHistory> getHistoryByKeyWordTimesAndType(Page<MatchHistory> page, String s,String startTime, String overTime, String leagueType);
	public void updateAndSave(String standardTeam, String originalTeam);
	
	/**得到一个球队的近num场比赛which=0代表主队=1代表客队*/
	public List<MatchHistory> getLatestMatchByStatus(Team team, String status, int num, boolean fixed, int which);
    
    /**得到两只球队的近num场的对阵情况(赛完的)*/
	public List<MatchHistory> getTwoTeamsBattles(Team team1, Team team2, boolean fixed);
}
