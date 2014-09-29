package com.xsc.lottery.service.business;

import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.TeamMapping;
import com.xsc.lottery.service.LotteryBaseService;

public interface TeamMappingService extends LotteryBaseService<TeamMapping>
{
	public List<TeamMapping> getTeamMappingByMatchHistoryName(String matchHistoryName);
	public List<TeamMapping> getTeamMappingByKeyWordOrType(String s, String leagueType);
	public String getMatchHistoryTeamByMatchJCZQName(String matchJCZQ);
	public List<TeamMapping> getTeamMappingByMatchJCZQName(String matchJCZQName);
	public String getMatchHistoryTeamByMatchJCLQName(String matchJCLQ);
	public List<TeamMapping> getTeamMappingByMatchJCLQName(String matchJCLQName);
	public Page<TeamMapping> getTeamMappingByKeyWordAndType(Page<TeamMapping> page, String s, String leagueType);
	public void updateOriginalTeamName(String id, String ids, String standardTeamName, String originalTeamName);
}
