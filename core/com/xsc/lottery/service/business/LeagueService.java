/**
 * 
 */
package com.xsc.lottery.service.business;

import com.xsc.lottery.entity.business.League;
import com.xsc.lottery.service.LotteryBaseService;

public interface LeagueService extends LotteryBaseService<League>
{
    /**根据联赛名称的到相应的League*/
    public League getLeagueByName(String name);
}
