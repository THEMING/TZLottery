/**
 * 
 */
package com.xsc.lottery.service.business;

import com.xsc.lottery.entity.business.LeagueRank;
import com.xsc.lottery.service.LotteryBaseService;

public interface LeagueRankService extends LotteryBaseService<LeagueRank>
{
    /**根据队名,联赛名得到相应的LeagueRank*/
    public LeagueRank getLeagueRankByTeamNameAndLeagueName(String teamName, String leagueName);
    
    /**根据TeamId,LeagueId得到相应的LeagueRank*/
    public LeagueRank getLeagueRankByTeamIdAndLeagueId(Long teamId, Long leagueId);
}
