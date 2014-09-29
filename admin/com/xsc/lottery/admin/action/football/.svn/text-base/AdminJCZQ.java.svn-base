package com.xsc.lottery.admin.action.football;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.dyj.DyjJCZQDuiZhen;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.TeamMapping;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.service.business.TeamMappingService;
import com.xsc.lottery.task.jczq.JczqTaskExcutor;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.JCZQ")
public class AdminJCZQ extends AdminBaseAction
{
	@Autowired
	private MatchArrangeService matchArrangeService;

	@Autowired
	private LotteryTermService lotteryTermService;
	
	@Autowired
	private JczqTaskExcutor jczqTaskExcutor;
	
	@Autowired
	private TeamMappingService teamMappingService;
	
	@Autowired
	private DyjJCZQDuiZhen dyjJCZQDuiZhen;
	
	private String match;
	private  List<MatchArrange> matchArrangelist;	
	private Page<MatchArrange> page;
	private int pageNo = 1;
    private int pageSize = 10;
    private int totalPages;    
    private RaceStatus[] raceStatus = RaceStatus.values();
	private RaceStatus status;
    private String saleDate;
    private String matchIndex;
    private String matchNo;
    private String teamList;
    
    public String index()
    {
    	matchArrangelist = matchArrangeService.getCurrentMatchArrangeForJCZQ();
    	
    	page = new Page<MatchArrange>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = matchArrangeService.getMatchPage(page, stringToDate(saleDate), RaceStatus.销售中, matchIndex, LotteryType.竞彩足球);
        
    	return SUCCESS;
    }    
    
    public String list()
    {
        page = new Page<MatchArrange>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = matchArrangeService.getMatchPage(page, stringToDate(saleDate), status, matchIndex, LotteryType.竞彩足球);
        String homeTeam = "";
        String awaryTeam = "";
        for(int i=0;i<page.getResult().size();i++) {
        	homeTeam = page.getResult().get(i).getHomeTeam();
        	String home = teamMappingService.getMatchHistoryTeamByMatchJCZQName(homeTeam);
        	if(home != null) {
        		page.getResult().get(i).setHistoryHomeTeam(home);
        	} else {
        		page.getResult().get(i).setHistoryHomeTeam("待添加");
        	}
        	
        }
        for(int i=0;i<page.getResult().size();i++) {
        	awaryTeam = page.getResult().get(i).getAwaryTeam();
        	String awary = teamMappingService.getMatchHistoryTeamByMatchJCZQName(awaryTeam);
        	if(awary != null) {
        		page.getResult().get(i).setHistoryAwaryTeam(awary);
        	} else {
        		page.getResult().get(i).setHistoryAwaryTeam("待添加");
        	}
        	
        }
        page = matchArrangeService.getMatchPage(page, stringToDate(saleDate), status, matchIndex, LotteryType.竞彩足球);
        return "list";
    }
    
    public String saveMatch()
    {
		try {
			LotteryTerm term = lotteryTermService.getCurrentTerm((LotteryType.竞彩足球));
			String[] b = match.split("\\|");
			
        	MatchArrange newmatch = new MatchArrange();
        	newmatch.setLotteryType(LotteryType.竞彩足球);
        	newmatch.setTerm(term);
        	newmatch.setSaleDate(stringToDate(b[0]));
        	newmatch.setBoutIndex(b[1]);
        	newmatch.setMatchName(b[2]);
        	newmatch.setHomeTeam(b[3]);
        	newmatch.setConcede(b[4]);
        	newmatch.setAwaryTeam(b[5]);
        	newmatch.setMatchTime(stringToCalendar(b[6]));
        	newmatch.setStatus(RaceStatus.valueOf(b[7]));
        	newmatch.setStopSaleTime(stringToCalendar(b[8]));
        	newmatch.setOpenPrizeTime(stringToCalendar(b[9]));
        	newmatch.setMatchResult(b[10].trim());
        	newmatch.setWholeScore(b[11].trim());
        	newmatch.setHalfScore(b[12].trim());
        	newmatch.setSop(b[13].trim());
        	newmatch.setPop(b[14].trim());
        	newmatch.setFop(b[15].trim());
        	newmatch.setStzb(b[16].trim());
        	newmatch.setPtzb(b[17].trim());
        	newmatch.setFtzb(b[18].trim());
        	matchArrangeService.save(newmatch);
        	
        	if (newmatch.getStatus().equals(RaceStatus.销售中)) {
        		jczqTaskExcutor.addNewMatch(newmatch);        	
        	}
		}
        catch (Exception e) {
        	e.printStackTrace();
        	logger.info(e.getMessage());
        }
        
        return SUCCESS;
    }
    
    public String getMatchInfoByMatchIndex()
    {
    	MatchArrange match = matchArrangeService.getMatchInfoByMatchNo(matchNo, LotteryType.竞彩足球);
    	Map<String, String> resultMap = new HashMap<String, String>();
    	if (match != null) {
    		resultMap.put("have", "Yes");
    		resultMap.put("matchName", match.getMatchName());
            resultMap.put("homeTeam", match.getHomeTeam());
            resultMap.put("guestTeam", match.getAwaryTeam());
            if(match.getConcede()!=null)
            {
            	resultMap.put("rang", match.getConcede().toString());
            } else
            {
            	resultMap.put("rang","");
            };
            
            resultMap.put("status", match.getStatus().toString());
            
            if(match.getMatchResult()!=null)
            {
            	resultMap.put("matchResult", match.getMatchResult());
            } else
            {
            	resultMap.put("matchResult","");
            };
            
    		if(match.getWholeScore()!=null)
    		{
    			resultMap.put("wholeScore", match.getWholeScore());
    		} else
    		{
    			resultMap.put("wholeScore","");
    		};
    		if(match.getHalfScore()!=null)
    		{
    			resultMap.put("halfScore", match.getHalfScore());
    		} else
    		{
    			resultMap.put("halfScore","");
    		};
    		if(match.getSpRangfenSfp()!=null)
    		{
    			resultMap.put("rfsfp_sp", match.getSpRangfenSfp());
    		} else
    		{
    			resultMap.put("rfsfp_sp","");
    		};
    		if(match.getSpSfp()!=null)
    		{
    			resultMap.put("sfp_sp", match.getSpSfp());
    		} else
    		{
    			resultMap.put("sfp_sp","");
    		};
    		if(match.getSpBf()!=null)
    		{
    			resultMap.put("bf_sp", match.getSpBf());
    		} else
    		{
    			resultMap.put("bf_sp","");
    		};
    		if(match.getSpJqs()!=null)
    		{
    			resultMap.put("jq_sp", match.getSpJqs());
    		} else
    		{
    			resultMap.put("jq_sp","");
    		};
    		if(match.getSpBcsfp()!=null)
    		{
    			resultMap.put("bqc_sp", match.getSpBcsfp());
    		} else
    		{
    			resultMap.put("bqc_sp","");
    		};
        }
    	else
    	{
    		resultMap.put("have", "No");
    	}
    	setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }
    
    /**大赢家根据日期手动开奖*/
    public String open() {
    	
    	logger.info("进来了................");
    	Calendar date = stringToDate(saleDate);
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	String dateString = format.format(date.getTime());
    	
    	dyjJCZQDuiZhen.getJCZQDZBYDate(dateString);
    	return list();
    }
    
    public Calendar stringToDate(String dt)
	{
		if (dt == null || dt.equals("")) {
			return null;
		}
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); 
		Date date=new Date();
		try {
			date =sdf.parse(dt);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		return calendar;
	}
    
	public Calendar stringToCalendar(String dt)
	{
		if (dt == null || dt.equals("")) {
			return null;
		}
		System.out.println(dt);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date date=new Date();
		try {
			date =sdf.parse(dt);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		return calendar;
	}

	public List<MatchArrange> getMatchArrangelist() {
		return matchArrangelist;
	}
	
	public void setMatchArrangelist(List<MatchArrange> matchArrangelist) {
		this.matchArrangelist = matchArrangelist;
	}
	
	public String getMatch() {
		return match;
	}
	
	public void setMatch(String match) {
		this.match = match;
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
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public RaceStatus[] getRaceStatus() {
		return raceStatus;
	}

	public void setRaceStatus(RaceStatus[] raceStatus) {
		this.raceStatus = raceStatus;
	}

	public String getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public Page<MatchArrange> getPage() {
		return page;
	}

	public void setPage(Page<MatchArrange> page) {
		this.page = page;
	}

	public RaceStatus getStatus() {
		return status;
	}

	public void setStatus(RaceStatus status) {
		this.status = status;
	}

	public String getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}
	
	public String saveMapping() {
		String[] team = teamList.split("\\|");
		List<TeamMapping> mappingList1 = teamMappingService.getTeamMappingByMatchJCZQName(team[1]);
		List<TeamMapping> mappingList2 = teamMappingService.getTeamMappingByMatchJCZQName(team[3]);
		
		if(mappingList1.size() == 0) {
			//Must have a history name
			setJsonString("<span style=\"color:green\">历史球队不存在!</span>");
		} else {
			mappingList1.get(0).setMatchJCZQName(team[1]);
			teamMappingService.save(mappingList1.get(0));
			setJsonString("<span style=\"color:green\">保存成功!</span>");
		}
		
		if(mappingList2.size() == 0) {
			//Must have a history name
			setJsonString("<span style=\"color:green\">历史球队不存在!</span>");
		} else {
			mappingList2.get(0).setMatchJCZQName(team[3]);
			teamMappingService.save(mappingList2.get(0));
			setJsonString("<span style=\"color:green\">保存成功!</span>");
		}
		
		return AJAXJSON;
	}

	public String getTeamList() {
		return teamList;
	}

	public void setTeamList(String teamList) {
		this.teamList = teamList;
	}
}
