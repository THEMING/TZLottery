package com.xsc.lottery.admin.action.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.TeamMapping;
import com.xsc.lottery.service.business.MatchHistoryService;
import com.xsc.lottery.service.business.TeamMappingService;



@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.SearchMatch")
public class AdminSearchMatch extends AdminBaseAction
{
	@Autowired
	private MatchHistoryService matchHistoryService;
	
	@Autowired
	private TeamMappingService teamMappingService;
	
	private String keyWord;
	
	private List<MatchHistory> list;
	
	private List<TeamMapping> mappinglist;
	
	private Page<MatchHistory> historyPage;
	
	private Page<TeamMapping> mappingPage;
	
	private int pageNo = 1;

    private int pageSize = 15;
	
	private String id;
	
	private String idOriginal;
	
	private String standardTeam;
	
	private String originalTeam;
	
	private String startTime;
	private String overTime;
	private String leagueType;
	
	
	
	public String index()
    {
        return SUCCESS;
    }
	
	public String list() {
		mappinglist = teamMappingService.getTeamMappingByKeyWordOrType(keyWord, leagueType);
		return "list";
	}
	
	public String update() {
		try {
			matchHistoryService.updateAndSave(standardTeam, originalTeam);
			teamMappingService.updateOriginalTeamName(id, idOriginal, standardTeam, originalTeam);
			this.addActionMessage("更新成功");
		} catch(Exception e) {
			this.addActionMessage("更新失败");
			e.printStackTrace();
		}
		return "ok";
	}
	
	public String historyList() 
	{
		historyPage = new Page<MatchHistory>();
		historyPage.setPageNo(pageNo);
        historyPage.setPageSize(pageSize);
        historyPage.setAutoCount(true);
		historyPage = matchHistoryService.getHistoryByKeyWordTimesAndType(historyPage, keyWord, startTime, overTime, leagueType);
		return "histroy";
	}
	
	public String teamMappingList() 
	{
		mappingPage = new Page<TeamMapping>();
		mappingPage.setPageNo(pageNo);
		mappingPage.setPageSize(pageSize);
		mappingPage.setAutoCount(true);
		mappingPage = teamMappingService.getTeamMappingByKeyWordAndType(mappingPage, keyWord, leagueType);
		return "teamMapping";
	}
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public List<MatchHistory> getList() {
		return list;
	}

	public void setList(List<MatchHistory> list) {
		this.list = list;
	}

	public String getStandardTeam() {
		return standardTeam;
	}

	public void setStandardTeam(String standardTeam) {
		this.standardTeam = standardTeam;
	}

	public String getOriginalTeam() {
		return originalTeam;
	}

	public void setOriginalTeam(String originalTeam) {
		this.originalTeam = originalTeam;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(String idOriginal) {
		this.idOriginal = idOriginal;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getLeagueType() {
		return leagueType;
	}

	public void setLeagueType(String leagueType) {
		this.leagueType = leagueType;
	}

	public Page<MatchHistory> getHistoryPage() {
		return historyPage;
	}

	public void setHistoryPage(Page<MatchHistory> historyPage) {
		this.historyPage = historyPage;
	}

	public Page<TeamMapping> getMappingPage() {
		return mappingPage;
	}

	public void setMappingPage(Page<TeamMapping> mappingPage) {
		this.mappingPage = mappingPage;
	}

	public List<TeamMapping> getMappinglist() {
		return mappinglist;
	}

	public void setMappinglist(List<TeamMapping> mappinglist) {
		this.mappinglist = mappinglist;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
