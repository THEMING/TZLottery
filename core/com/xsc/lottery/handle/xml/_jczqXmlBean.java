package com.xsc.lottery.handle.xml;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.entity.business.League;
import com.xsc.lottery.entity.business.LeagueRank;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.MatchMapping;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.service.business.CompanyService;
import com.xsc.lottery.service.business.LeagueRankService;
import com.xsc.lottery.service.business.LeagueService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.service.business.MatchHistoryService;
import com.xsc.lottery.service.business.MatchMappingService;
import com.xsc.lottery.service.business.OddService;
import com.xsc.lottery.service.business.TeamMappingService;
import com.xsc.lottery.service.business.TeamService;
import com.xsc.lottery.task.jclq.JclqTaskExcutor;
import com.xsc.lottery.task.jczq.JczqTaskExcutor;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;

@Component
public class _jczqXmlBean
{
    @Autowired
    private MatchArrangeService matchArrangeService;
    
    @Autowired
    private MatchHistoryService matchHistoryeService;
    
    @Autowired
    private MatchMappingService matchMappingService;
    
    @Autowired
    private LeagueService leagueService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private LotteryTermService lotteryTermService;
    
    @Autowired
	private JczqTaskExcutor jczqTaskExcutor;
    
    @Autowired
	private JclqTaskExcutor jclqTaskExcutor;
    
	@Autowired
	private TeamMappingService teamMappingService;
	
	@Autowired
	private LeagueRankService leagueRankService;
	
	@Autowired
	private OddService oddService;
	
	@Autowired
	private CompanyService companyService;

    
    @SuppressWarnings("unchecked")
	public Element getElement(Element root, String name)
	{
		List elements = root.elements(); 
		for (Iterator it = elements.iterator(); it.hasNext();) {
			Element elem = (Element) it.next(); 
			if(elem.getName().equals(name)) {
				return elem;
			}
		}
		return null;
	}
    
//    @SuppressWarnings("unchecked")
//    public void parseXML(Element root) throws Exception
//    {
//        if(root == null) {
//            return;
//        }
//        
//        Element subtype = getElement(getElement(root, "head"), "subtype");
//        
//        if(subtype == null) {
//        	throw new RuntimeException("head中缺少subtype");
//        }
//        Element body = getElement(root, "body");
//        String subTypeStr = subtype.getTextTrim();
//        if(subTypeStr.equals("openPrize")) {
//        	parseOpenPrize(body);
//        }
//        else if(subTypeStr.equals("matchArrange")) {
//        	parseMatchArrages(body);
//        	//System.out.println(body);
//        } else if(subTypeStr.equals("jczqHistory")) {
//        	body = getElement(body, "timeIndex");
//        	parseMatchHistory(body);
//        	//System.out.println(body);
//        }
//    }
    	
      
    
   
    
    
    @SuppressWarnings("unchecked")
	  public void parseXML(Element root) throws Exception
	  {
	      if(root == null) {
	          return;
	      }
	      
	      String admin = Configuration.getInstance().getValue("file.admin");
	      String key = Configuration.getInstance().getValue("file.key");
	      
	      Element fileadmin;
    	  Element filekey;

          fileadmin = getElement(getElement(root, "head"), "admin");
		  filekey = getElement(getElement(root, "head"), "key");
    	  
    	  if (fileadmin == null || filekey == null) {
    		  throw new RuntimeException("admin或者key不存在。");
		  } 
    	  
    	  String fadmin = fileadmin.getTextTrim();
    	  String fkey = filekey.getText();
    	  
    	  if (!fadmin.equals(admin) || !fkey.equals(key)) {
			  throw new RuntimeException("admin或者key错误。");	
		  }
		  
	      Element subtype = getElement(getElement(root, "head"), "subtype");
	      
	      if(subtype == null) {
	      	throw new RuntimeException("head中缺少subtype");
	      }
	      String subTypeStr = subtype.getTextTrim();
	      //获取比分（我中啦）
	      if(subTypeStr.equals("MatchArrangeAndResult"))
	      {
	    	  if(root.getName() != "matchlist")
	    	  {
	    		  throw new RuntimeException("没有matchlist实体，上传解析的文件错误。");
	    	  }
	    	  else
	    	  {
	    		  parseEveryDayMatchHistory(root);
	    	  }  
	      }
	       //场次对应（我中啦-jzspf）
	      else if(subTypeStr.equals("jzspf") || subTypeStr.equals("sf14") || subTypeStr.equals("bq6") || subTypeStr.equals("jq4"))
	      {
	    	  parseRecordToMatchMapping(root);
	      }
	      //联赛排名（我中啦）
	      else if(subTypeStr.equals("leagueRank"))
	      {
	    	  parseRecordToLeagueRank(root);
	      }
	      //赌博公司开出赔率（我中啦）
	      else if(subTypeStr.equals("odd"))
	      {
	    	  parseRecordToOddAndCompany(root);
	      }
	      else
	      {
		      Element body = getElement(root, "body");
		      //竞彩足球开奖（500）
		      if(subTypeStr.equals("openPrize")) {
		      	parseOpenPrize(body);
		      }
		      	//竞彩赔率抓取（竞彩网-500欧赔）
		      else if(subTypeStr.equals("matchArrange")) {
		      	parseMatchArrages(body);
		      	//System.out.println(body);
		      } else if(subTypeStr.equals("jczqHistory")) {
		     	body = getElement(body, "timeIndex");
		     	parseMatchHistory(body);
		     	//System.out.println(body);竞彩篮球开奖（500）
		     } else if(subTypeStr.equals("openPrize_JCbasketball"))
		     {
		    	 parseJCLQResultToMatchArray(body);
		     }
		      //竞彩篮球赔率（竞彩网）
		     else if(subTypeStr.equals("matchArrangeForJCLQ"))
		     {
		    	 parseJCLQToMatchArrange(body);
		     }
		      //竞彩足球历史盘口（雪缘园）
		     else if(subTypeStr.equals("jczqHistoryPankou"))
		     {
		    	 parsePankouToMatchHistory(body);
		     }
	     }
        
	 }
    
    
    
    /**解析赛程赛果到MatchHistory*/
	  @SuppressWarnings("unchecked")
	  public void parseEveryDayMatchHistory(Element parseList)
	  {
		  if(parseList == null) {
	            return;
	        }
	        List elements = parseList.elements();
	        for (Iterator it = elements.iterator(); it.hasNext();) {
	            Element elem = (Element) it.next(); 
		        MatchHistory match = new MatchHistory();
		        League league = new League();
		        Team homeTeam = new Team();
		        Team visitTeam = new Team();
		        if(elem.getName().equals("m"))
		        {
		        	long matchId = Long.parseLong(elem.attributeValue("id"));
		        	long leagueId = Long.parseLong(elem.attributeValue("lid"));
		        	String[] ln = elem.attributeValue("ln").split("\\|");
		        	String leagueName = ln[0];
		        	League checkLeague = leagueService.findById(leagueId);
		        	if(checkLeague != null)
		        	{
		        		//checkLeague.setName(leagueName);
		        		//leagueService.save(checkLeague);
		        		match.setLeague(checkLeague);
		        	}
		        	else
		        	{
			        	league.setId(leagueId);		        	
			        	league.setName(leagueName);
			        	leagueService.save(league);
			        	match.setLeague(league);
		        	}
		        	long homeId = Long.parseLong(elem.attributeValue("hid"));
		        	String[] hn = elem.attributeValue("hn").split("\\|");
		        	String homeName = hn[0];
		        	Team checkHomeTeam = teamService.findById(homeId);
		        	if(checkHomeTeam != null)
		        	{
		        	//	checkHomeTeam.setName(homeName);
		        	//	teamService.save(checkHomeTeam);
		        		match.setHostTeam(checkHomeTeam); 
		        	}
		        	else
		        	{
			        	homeTeam.setId(homeId);
			        	homeTeam.setName(homeName);
			        	teamService.save(homeTeam);
			        	match.setHostTeam(homeTeam);
		        	}
		        	long visitId = Long.parseLong(elem.attributeValue("aid"));
		        	String[] an = elem.attributeValue("an").split("\\|");
		        	String visitName = an[0];
		        	Team checkVisitTeam = teamService.findById(visitId);
		        	if(checkVisitTeam != null)
		        	{
		        		//checkVisitTeam.setName(visitName);
		        		//teamService.save(checkVisitTeam);
		        		match.setVisitTeam(checkVisitTeam);
		        	}
		        	else
		        	{
		        		visitTeam.setId(visitId);
		        		visitTeam.setName(visitName);	
		        		teamService.save(visitTeam);
		        		match.setVisitTeam(visitTeam);
		        	}
		        	String dt = elem.attributeValue("dt");
		        	String status = elem.attributeValue("st");
		        	String[] hs = elem.attributeValue("hs").split("\\|"); 
		        	int hostHalfScore = Integer.parseInt(hs[0]);
		        	int hostScore = Integer.parseInt(hs[1]);
		        	String[] as = elem.attributeValue("as").split("\\|");
		        	int visitHalfScore = Integer.parseInt(as[0]);
		        	int visitScore = Integer.parseInt(as[1]);
		        	MatchHistory checkMatch = matchHistoryeService.findById(matchId);
		        	if(checkMatch != null)
		        	{
		        		saveMatch(checkMatch, dt, status, hostHalfScore, hostScore, visitHalfScore, visitScore);
		        	}
		        	else
		        	{
			        	match.setId(matchId);
			        	saveMatch(match, dt, status, hostHalfScore, hostScore, visitHalfScore, visitScore);
		        	}
		        }		        
	        }
	  }
	  
	  public void saveMatch(MatchHistory match, String dt, String status, int hostHalfScore, int hostScore,
			  int visitHalfScore, int visitScore)
	  {
		  match.setMatchStartTime(DateUtil.parse(dt.substring(0, dt.length()-3), "yyyy-MM-dd HH:mm"));
      	  match.setStatus(status);
      	  match.setHostHalfScore(hostHalfScore);
      	  match.setHostScore(hostScore);
      	  match.setVisitHalfScore(visitHalfScore);
      	  match.setVisitScore(visitScore);  	  
      	  matchHistoryeService.save(match);
	  }
    
    
	  /**解析竟彩到MatchMapping*/
	  @SuppressWarnings("unchecked")
	  public void parseRecordToMatchMapping(Element root)
	  {
		  if(root == null)
		  {
			  return;
		  }
		  
		  Element no = getElement(getElement(root, "head"), "termNo");
		  Element subtype = getElement(getElement(root, "head"), "subtype");
		  String termNo = no.getTextTrim();
		  String type = subtype.getTextTrim();
		  List<Element> elements = root.elements();
		  for(Iterator it = elements.iterator(); it.hasNext();)
		  {
			  MatchMapping matchMapping = new MatchMapping();
			  Element elem = (Element) it.next();
			  if(elem.getName().equals("match"))
			  {
				  String id = elem.attributeValue("id");
				  MatchMapping checkMatchMapping = matchMappingService.getMatchMappingByTypeAndMatchNo(type,id);
				  if(checkMatchMapping != null)
				  {
					  setTypeAndTermNo(checkMatchMapping, type, termNo);
					  checkMatchMapping.setNo(elem.attributeValue("no"));
					  matchMappingService.save(checkMatchMapping);
				  }
				  else
				  {
					  matchMapping.setMatchNo(id);
					  matchMapping.setNo(elem.attributeValue("no"));
					  setTypeAndTermNo(matchMapping,type,termNo);
					  matchMappingService.save(matchMapping);
				  }
			  }
		  }
	  }
    
	  public void setTypeAndTermNo(MatchMapping matchMapping, String type, String no)
	  {
		  if(type.equals("sf14"))
		  {
			  matchMapping.setType(LotteryType.足彩14场);
			  matchMapping.setTermNo(no);
		  }
		  else if(type.equals("jq4"))
		  {
			  matchMapping.setType(LotteryType.四场进球);
			  matchMapping.setTermNo(no);
		  }
		  else if(type.equals("bq6"))
		  {
			  matchMapping.setType(LotteryType.足彩6场半);
			  matchMapping.setTermNo(no);
		  }
		  else
		  {
			  matchMapping.setType(LotteryType.竞彩足球);
			  matchMapping.setTermNo("999999");
		  }
	  }
    
    /**解析leagueRank.xml到数据库LeagueRank*/
	  public void parseRecordToLeagueRank(Element root)
	  {
		  if(root == null)
		  {
			  return;
		  }
		  List<Element> teamList = root.elements();
		  Long leagueId = Long.parseLong(root.attributeValue("id"));
		  for(Iterator it = teamList.iterator(); it.hasNext();)
		  {
			  Element elem = (Element) it.next();			  
			  if(elem.getName().equals("team"))
			  {
				  Long teamId = Long.parseLong(elem.attributeValue("id"));
				  List<Element> overallList = elem.elements();
				  for(Iterator ite = overallList.iterator(); ite.hasNext();)
				  {
					  Team team = teamService.findById(teamId);
					  League league = leagueService.findById(leagueId);
					  Element ele = (Element) ite.next();
					  LeagueRank checkLeagueRank = leagueRankService.getLeagueRankByTeamIdAndLeagueId(teamId, leagueId);
					  if(ele.getName().equals("overall"))
					  {
						  if(checkLeagueRank == null)
						  {
							  LeagueRank leagueRank = new LeagueRank();
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							  team.setRank(ele.attributeValue("rank"));
							  teamService.save(team);
							  leagueRank.setTeam(team);
							  leagueRank.setLeague(league);
							  leagueRank.setRank(rank);
							  leagueRank.setAllRound(Integer.parseInt(ele.attributeValue("gp")));
							  leagueRank.setWinRound(Integer.parseInt(ele.attributeValue("w")));
							  leagueRank.setDrowRound(Integer.parseInt(ele.attributeValue("d")));
							  leagueRank.setLoseRound(Integer.parseInt(ele.attributeValue("l")));
							  leagueRank.setWinNum(Integer.parseInt(ele.attributeValue("gs")));
							  leagueRank.setLoseNum(Integer.parseInt(ele.attributeValue("ga")));
							  leagueRank.setScore(Integer.parseInt(ele.attributeValue("p")));
							  leagueRank.setRedNum(Integer.parseInt(ele.attributeValue("rc")));
							  leagueRank.setYellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  leagueRank.setPoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(leagueRank);
						  }
						  else
						  {
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							  team.setRank(ele.attributeValue("rank"));
							  teamService.save(team);
							  checkLeagueRank.setTeam(team);
							  checkLeagueRank.setRank(rank);
							  checkLeagueRank.setAllRound(Integer.parseInt(ele.attributeValue("gp")));
							  checkLeagueRank.setWinRound(Integer.parseInt(ele.attributeValue("w")));
							  checkLeagueRank.setDrowRound(Integer.parseInt(ele.attributeValue("d")));
							  checkLeagueRank.setLoseRound(Integer.parseInt(ele.attributeValue("l")));
							  checkLeagueRank.setWinNum(Integer.parseInt(ele.attributeValue("gs")));
							  checkLeagueRank.setLoseNum(Integer.parseInt(ele.attributeValue("ga")));
							  checkLeagueRank.setScore(Integer.parseInt(ele.attributeValue("p")));
							  checkLeagueRank.setRedNum(Integer.parseInt(ele.attributeValue("rc")));
							  checkLeagueRank.setYellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  checkLeagueRank.setPoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(checkLeagueRank);
						  }
					  }
					  
					  if(ele.getName().equals("host"))
					  {
						  if(checkLeagueRank == null)
						  {
							  LeagueRank leagueRank = new LeagueRank();
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							  team.setHrank(ele.attributeValue("rank"));
							  teamService.save(team);
							  leagueRank.setTeam(team);
							  leagueRank.setLeague(league);
							  leagueRank.setHrank(rank);
							  leagueRank.setHallRound(Integer.parseInt(ele.attributeValue("gp")));
							  leagueRank.setHwinRound(Integer.parseInt(ele.attributeValue("w")));
							  leagueRank.setHdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  leagueRank.setHloseRound(Integer.parseInt(ele.attributeValue("l")));
							  leagueRank.setHwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  leagueRank.setHloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  leagueRank.setHscore(Integer.parseInt(ele.attributeValue("p")));
							  leagueRank.setHredNum(Integer.parseInt(ele.attributeValue("rc")));
							  leagueRank.setHyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  leagueRank.setHpoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(leagueRank);
						  }
						  else
						  {
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							  team.setHrank(ele.attributeValue("rank"));
							  teamService.save(team);
							  checkLeagueRank.setTeam(team);
							  checkLeagueRank.setHrank(rank);
							  checkLeagueRank.setHallRound(Integer.parseInt(ele.attributeValue("gp")));
							  checkLeagueRank.setHwinRound(Integer.parseInt(ele.attributeValue("w")));
							  checkLeagueRank.setHdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  checkLeagueRank.setHloseRound(Integer.parseInt(ele.attributeValue("l")));
							  checkLeagueRank.setHwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  checkLeagueRank.setHloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  checkLeagueRank.setHscore(Integer.parseInt(ele.attributeValue("p")));
							  checkLeagueRank.setHredNum(Integer.parseInt(ele.attributeValue("rc")));
							  checkLeagueRank.setHyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  checkLeagueRank.setHpoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(checkLeagueRank);
						  }
					  }

					  
					  if(ele.getName().equals("away"))
					  {
						  if(checkLeagueRank == null)
						  {
							  LeagueRank leagueRank = new LeagueRank();
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							  team.setArank(ele.attributeValue("rank"));
							  teamService.save(team);
							  leagueRank.setTeam(team);
							  leagueRank.setLeague(league);
							  leagueRank.setArank(rank);
							  leagueRank.setAallRound(Integer.parseInt(ele.attributeValue("gp")));
							  leagueRank.setAwinRound(Integer.parseInt(ele.attributeValue("w")));
							  leagueRank.setAdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  leagueRank.setAloseRound(Integer.parseInt(ele.attributeValue("l")));
							  leagueRank.setAwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  leagueRank.setAloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  leagueRank.setAscore(Integer.parseInt(ele.attributeValue("p")));
							  leagueRank.setAredNum(Integer.parseInt(ele.attributeValue("rc")));
							  leagueRank.setAyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  leagueRank.setApoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(leagueRank);
						  }
						  else
						  {
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							  team.setArank(ele.attributeValue("rank"));
							  teamService.save(team);
							  checkLeagueRank.setTeam(team);
							  checkLeagueRank.setArank(rank);
							  checkLeagueRank.setAallRound(Integer.parseInt(ele.attributeValue("gp")));
							  checkLeagueRank.setAwinRound(Integer.parseInt(ele.attributeValue("w")));
							  checkLeagueRank.setAdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  checkLeagueRank.setAloseRound(Integer.parseInt(ele.attributeValue("l")));
							  checkLeagueRank.setAwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  checkLeagueRank.setAloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  checkLeagueRank.setAscore(Integer.parseInt(ele.attributeValue("p")));
							  checkLeagueRank.setAredNum(Integer.parseInt(ele.attributeValue("rc")));
							  checkLeagueRank.setAyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  checkLeagueRank.setApoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(checkLeagueRank);
						  }
					  }
					  
					  if(ele.getName().equals("round6"))
					  {
						  if(checkLeagueRank == null)
						  {
							  LeagueRank leagueRank = new LeagueRank();
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							 // team.setArank(ele.attributeValue("rank"));
							 // teamService.save(team);
							  leagueRank.setTeam(team);
							  leagueRank.setLeague(league);
							  leagueRank.setSrank(rank);
							  leagueRank.setSallRound(Integer.parseInt(ele.attributeValue("gp")));
							  leagueRank.setSwinRound(Integer.parseInt(ele.attributeValue("w")));
							  leagueRank.setSdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  leagueRank.setSloseRound(Integer.parseInt(ele.attributeValue("l")));
							  leagueRank.setSwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  leagueRank.setSloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  leagueRank.setSscore(Integer.parseInt(ele.attributeValue("p")));
							  leagueRank.setSredNum(Integer.parseInt(ele.attributeValue("rc")));
							  leagueRank.setSyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  leagueRank.setSpoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(leagueRank);
						  }
						  else
						  {
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							//  team.setArank(ele.attributeValue("rank"));
							  //teamService.save(team);
							  checkLeagueRank.setTeam(team);
							  checkLeagueRank.setSrank(rank);
							  checkLeagueRank.setSallRound(Integer.parseInt(ele.attributeValue("gp")));
							  checkLeagueRank.setSwinRound(Integer.parseInt(ele.attributeValue("w")));
							  checkLeagueRank.setSdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  checkLeagueRank.setSloseRound(Integer.parseInt(ele.attributeValue("l")));
							  checkLeagueRank.setSwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  checkLeagueRank.setSloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  checkLeagueRank.setSscore(Integer.parseInt(ele.attributeValue("p")));
							  checkLeagueRank.setSredNum(Integer.parseInt(ele.attributeValue("rc")));
							  checkLeagueRank.setSyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  checkLeagueRank.setSpoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(checkLeagueRank);
						  }
					  }

					  if(ele.getName().equals("round6Host"))
					  {
						  if(checkLeagueRank == null)
						  {
							  LeagueRank leagueRank = new LeagueRank();
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							 // team.setArank(ele.attributeValue("rank"));
							 // teamService.save(team);
							  leagueRank.setTeam(team);
							  leagueRank.setLeague(league);
							  leagueRank.setShrank(rank);
							  leagueRank.setShallRound(Integer.parseInt(ele.attributeValue("gp")));
							  leagueRank.setShwinRound(Integer.parseInt(ele.attributeValue("w")));
							  leagueRank.setShdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  leagueRank.setShloseRound(Integer.parseInt(ele.attributeValue("l")));
							  leagueRank.setShwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  leagueRank.setShloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  leagueRank.setShscore(Integer.parseInt(ele.attributeValue("p")));
							  leagueRank.setShredNum(Integer.parseInt(ele.attributeValue("rc")));
							  leagueRank.setShyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  leagueRank.setShpoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(leagueRank);
						  }
						  else
						  {
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							//  team.setArank(ele.attributeValue("rank"));
							  //teamService.save(team);
							  checkLeagueRank.setTeam(team);
							  checkLeagueRank.setShrank(rank);
							  checkLeagueRank.setShallRound(Integer.parseInt(ele.attributeValue("gp")));
							  checkLeagueRank.setShwinRound(Integer.parseInt(ele.attributeValue("w")));
							  checkLeagueRank.setShdrowRound(Integer.parseInt(ele.attributeValue("d")));
							  checkLeagueRank.setShloseRound(Integer.parseInt(ele.attributeValue("l")));
							  checkLeagueRank.setShwinNum(Integer.parseInt(ele.attributeValue("gs")));
							  checkLeagueRank.setShloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  checkLeagueRank.setShscore(Integer.parseInt(ele.attributeValue("p")));
							  checkLeagueRank.setShredNum(Integer.parseInt(ele.attributeValue("rc")));
							  checkLeagueRank.setShyellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  checkLeagueRank.setShpoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(checkLeagueRank);
						  }
					  }
					  
					  if(ele.getName().equals("round6Visit"))
					  {
						  if(checkLeagueRank == null)
						  {
							  LeagueRank leagueRank = new LeagueRank();
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							 // team.setArank(ele.attributeValue("rank"));
							 // teamService.save(team);
							  leagueRank.setTeam(team);
							  leagueRank.setLeague(league);
							  leagueRank.setSarank(rank);
							  leagueRank.setSaallRound(Integer.parseInt(ele.attributeValue("gp")));
							  leagueRank.setSawinRound(Integer.parseInt(ele.attributeValue("w")));
							  leagueRank.setSadrowRound(Integer.parseInt(ele.attributeValue("d")));
							  leagueRank.setSaloseRound(Integer.parseInt(ele.attributeValue("l")));
							  leagueRank.setSawinNum(Integer.parseInt(ele.attributeValue("gs")));
							  leagueRank.setSaloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  leagueRank.setSascore(Integer.parseInt(ele.attributeValue("p")));
							  leagueRank.setSaredNum(Integer.parseInt(ele.attributeValue("rc")));
							  leagueRank.setSayellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  leagueRank.setSapoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(leagueRank);
						  }
						  else
						  {
							  int rank = Integer.parseInt(ele.attributeValue("rank"));
							//  team.setArank(ele.attributeValue("rank"));
							  //teamService.save(team);
							  checkLeagueRank.setTeam(team);
							  checkLeagueRank.setSarank(rank);
							  checkLeagueRank.setSaallRound(Integer.parseInt(ele.attributeValue("gp")));
							  checkLeagueRank.setSawinRound(Integer.parseInt(ele.attributeValue("w")));
							  checkLeagueRank.setSadrowRound(Integer.parseInt(ele.attributeValue("d")));
							  checkLeagueRank.setSaloseRound(Integer.parseInt(ele.attributeValue("l")));
							  checkLeagueRank.setSawinNum(Integer.parseInt(ele.attributeValue("gs")));
							  checkLeagueRank.setSaloseNum(Integer.parseInt(ele.attributeValue("ga")));
							  checkLeagueRank.setSascore(Integer.parseInt(ele.attributeValue("p")));
							  checkLeagueRank.setSaredNum(Integer.parseInt(ele.attributeValue("rc")));
							  checkLeagueRank.setSayellowNum(Integer.parseInt(ele.attributeValue("yc")));
							  checkLeagueRank.setSapoints(Integer.parseInt(ele.attributeValue("points")));
							  leagueRankService.save(checkLeagueRank);
						  }
					  }
				  }
			  }
		  }
	  }
    
    
//把数据解析到odd数据库和company数据库
	  public void parseRecordToOddAndCompany(Element root)
	  {
		  if(root == null)
		  {
			  return;
		  }
		  List<Element> elements = root.elements();
		  for(Iterator it = elements.iterator(); it.hasNext();)
		  {  
			  Element elem = (Element) it.next();
			  if(elem.getName().equals("match"))
			  {
				  String matchIdStr = elem.attributeValue("id");
				  List<Element> elems = elem.elements();
				  for(Iterator ite = elems.iterator(); ite.hasNext();)
				  {
					  Company company = new Company();
					  Element element = (Element) ite.next();	
					  String companyIdStr = element.attributeValue("id");
					  Long companyId = Long.parseLong(companyIdStr);
					  Company checkCompany = companyService.findById(companyId);
					  if(checkCompany == null)
					  {
						  company.setId(companyId);
						  String companyName = element.attributeValue("cn");
						  company.setCompanyName(companyName);
						  companyService.save(company);
					  }
					  List<Element> elemen = element.elements();
					  for(Iterator iter = elemen.iterator(); iter.hasNext();)
					  {
						  Element ele = (Element) iter.next();
						  if(ele.attributeValue("et").equals("1"))
						  {
							  continue;
						  }
						  String pankouType = ele.attributeValue("t");
						  if(pankouType.equals("ou"))
						  {
							  continue;
						  }
						  String matchId = elem.attributeValue("id");
						  String pankouId = ele.attributeValue("p");
						  if(checkCompany == null)
						  {
							  List<Odd> checkOdd = oddService.getOddListByMatchIdCompanyAndType(matchId, company, pankouType, pankouId);
							  if(!checkOdd.isEmpty())
							  {
								  continue;
							  }
						  }
						  else
						  {
							  List<Odd> checkOdd = oddService.getOddListByMatchIdCompanyAndType(matchId, checkCompany, pankouType, pankouId);
							  if(!checkOdd.isEmpty())
							  {
								  continue;
							  }
						  }
						  Odd odd = new Odd();
						  odd.setMatchId(matchId);
						  if(checkCompany == null)
						  {
							  odd.setCompany(company);
						  }
						  else
						  {
							  odd.setCompany(checkCompany);
						  }
						  
						  odd.setPankouType(pankouType);
						  if(pankouType.equals("ep"))
						  {
							  odd.setPing(ele.attributeValue("h"));
						  }
						  odd.setSheng(ele.attributeValue("o"));
						  odd.setFu(ele.attributeValue("u"));
						  odd.setPankouId(pankouId);
						  if(pankouType.equals("an"))
						  {
							  String rang = ele.attributeValue("vst");
							  String temp = this.changeToRightString(ele.attributeValue("h"));
							  if(rang.equals("0"))
							  {							  
								  odd.setRang("-" + temp);
							  }
							  else if(rang.equals("1"))
							  {
								  odd.setRang(temp);
							  }
						  }
						  String time = ele.attributeValue("time");
						  odd.setTime(DateUtil.parse(time.substring(0, time.length()-3), "yyyy-MM-dd HH:mm"));
						  oddService.save(odd);
					  }
				  }
			  }
		  }
	  }
    
	  
	  //转换成相应的字符串存贮到odd中
	  public String changeToRightString(String s)
	  {
		  if(s.equals("0.0"))
		  {
			  s = "0";
		  }
		  else if(s.equals("1.0"))
		  {
			  s = "0/0.5";
		  }
		  else if(s.equals("2.0"))
		  {
			  s = "0.5";
		  }
		  else if(s.equals("3.0"))
		  {
			  s = "0.5/1";
		  }
		  else if(s.equals("4.0"))
		  {
			  s = "1";
		  }
		  else if(s.equals("5.0"))
		  {
			  s = "1/1.5";
		  }
		  else if(s.equals("6.0"))
		  {
			  s = "1.5";
		  }
		  else if(s.equals("7.0"))
		  {
			  s = "1.5/2";
		  }
		  else if(s.equals("8.0"))
		  {
			  s = "2";
		  }
		  else if(s.equals("9.0"))
		  {
			  s = "2/2.5";
		  }
		  else if(s.equals("10.0"))
		  {
			  s = "2.5";
		  }
		  else if(s.equals("11.0"))
		  {
			  s = "2.5/3";
		  }
		  else if(s.equals("12.0"))
		  {	  
			  s = "3";
		  }
		  else if(s.equals("13.0"))
		  {	  
			  s = "3/3.5";
		  }
		  else if(s.equals("14.0"))
		  {	  
			  s = "3.5";
		  }
		  else if(s.equals("15.0"))
		  {
			  s = "3.5/4";
		  }
		  else if(s.equals("16.0"))
		  {
			  s = "4";
		  }
		  else if(s.equals("17.0"))
		  {
			  s = "4/4.5";
		  }
		  else if(s.equals("18.0"))
		  {
			  s = "4.5";
		  }
		  else if(s.equals("19.0"))
		  {
			  s = "4.5/5";
		  }
		  else if(s.equals("20.0"))
		  {
			  s = "5";
		  }
		  else
		  {
			  s = "5/5.5";
		  }
		  return s;
	  }
    
    /**把竟彩篮球开奖结果解析到matchArrange*/
	  public void parseJCLQResultToMatchArray(Element body)
	  {
		  if(body == null)
		  {
			  return;
		  }
		  List<Element> elements = body.elements();
		  for(Iterator it = elements.iterator(); it.hasNext();)
		  {
			  Element element = (Element) it.next();
			  List<Element> elemens = element.elements();
			  MatchArrange matchArrange = new MatchArrange();
			  String matchId = "";
			  for(Iterator ite = elemens.iterator();ite.hasNext();)
			  {				  
				  Element elemen = (Element) ite.next();
				  if(elemen.getName().equals("mNo"))
				  {
					  matchId = elemen.getTextTrim();
				  }
				  if(matchId.equals(""))
				  {
					  break;
				  }
				  else
				  {
					  matchArrange  = matchArrangeService.getUniqueMatchByMatchNo(matchId);
					  if(matchArrange == null)
					  {
						  break;
					  }
					  if(elemen.getName().equals("score"))
					  {
						  matchArrange.setWholeScore(elemen.getTextTrim());
					  }
					  else if(elemen.getName().equals("SFdg"))
					  {
						  this.saveSp(matchArrange, elemen);
					  }
					  else if(elemen.getName().equals("RFSFdg"))
					  {
						  this.saveSp(matchArrange, elemen);
					  }
					  else if(elemen.getName().equals("DXFdg"))
					  {
						  this.saveSp(matchArrange, elemen);
					  }
					  else if(elemen.getName().equals("SFXdg"))
					  {
						  this.saveSp(matchArrange, elemen);
					  }
				  }				  
			  }
			  if(matchArrange != null)
			  {
				  matchArrangeService.save(matchArrange);
			  }	  
		  }
	  }
    
    /**存贮篮球sp调用的函数*/
	  public void saveSp(MatchArrange matchArrange, Element elemen)
	  {
		  List<Element> elemes = elemen.elements();
		  String tmpsp;
		  for(Iterator iter = elemes.iterator(); iter.hasNext();)
		  {
			  Element eleme = (Element) iter.next();
			  if(eleme.getName().equals("prize"))
			  {
				  //danguan peilv devide 2
				  if(eleme.getTextTrim().equals("--")) continue;
				  tmpsp=String.valueOf(Float.parseFloat(eleme.getTextTrim())/2);
				  if(elemen.getName().equals("DXFdg"))
				  {	  
					  matchArrange.setSpDxf(tmpsp);
				  }
				  else if(elemen.getName().equals("RFSFdg"))
				  {
					  matchArrange.setSpRangfenSf(tmpsp);
				  }
				  else if(elemen.getName().equals("SFdg"))
				  {
					  matchArrange.setSpSf(tmpsp);
				  }  
				  else if(elemen.getName().equals("SFXdg"))
				  {
					  matchArrange.setSpSfc(tmpsp);
				  }
			  }
			  else if(eleme.getName().equals("result"))
			  {
				  if(elemen.getName().equals("DXFdg"))
				  {
					  String type = eleme.getTextTrim();
					  if(type.equals("大"))
					  {
						  type = "1";
					  }
					  else if(type.equals("小"))
					  {
						  type = "2";
					  }
					  else 
					  {
						  type = "---";
					  }
					  matchArrange.setDxfResult(type);
				  }
				  else if(elemen.getName().equals("RFSFdg"))
				  {
					  String type = eleme.getTextTrim();
					  if(type.equals("主胜"))
					  {
						  type = "1";
					  }
					  else if(type.equals("主负"))
					  {
						  type = "2";
					  }
					  else 
					  {
						  type = "---";
					  }
					  matchArrange.setRfsfResult(type);
				  }
				  else if(elemen.getName().equals("SFdg"))
				  {
					  String type = eleme.getTextTrim();
					  if(type.equals("主胜"))
					  {
						  type = "1";
					  }
					  else if(type.equals("主负"))
					  {
						  type = "2";
					  }
					  else
					  {
						  type = "---";
					  }
					  matchArrange.setSfResult(type);
				  }
				  else if(elemen.getName().equals("SFXdg"))
				  {
					  String type = eleme.getTextTrim();
					  changeToRigteTypeForSFC(type);
					  matchArrange.setSfcResult(type);
				  }
			  }
		  }
	  }
    
	  /**把篮球胜分差结果转化成相应的格式*/
	  public void changeToRigteTypeForSFC(String type)
	  {
		  if(type.equals("客胜1-5"))
		  {
			  type = "11";
		  }
		  else if(type.equals("客胜6-10"))
		  {
			  type = "12";
		  }
		  else if(type.equals("客胜11-15"))
		  {
			  type = "13";
		  }
		  else if(type.equals("客胜16-20"))
		  {
			  type = "14";
		  }
		  else if(type.equals("客胜21-25"))
		  {
			  type = "15";
		  }
		  else if(type.equals("客胜26+"))
		  {
			  type = "16";
		  }
		  else if(type.equals("主胜1-5"))
		  {
			  type = "01";
		  }
		  else if(type.equals("主胜6-10"))
		  {
			  type = "02";
		  }
		  else if(type.equals("主胜11-15"))
		  {
			  type = "03";
		  }
		  else if(type.equals("主胜16-20"))
		  {
			  type = "04";
		  }
		  else if(type.equals("主胜21-25"))
		  {
			  type = "05";
		  }
		  else if(type.equals("主胜26+"))
		  {
			  type = "06";
		  }
		  else if(StringUtils.isBlank(type))
		  {
			  type = "---";
		  }
		  else
		  {
			  throw new RuntimeException("竞彩篮球胜分差类型错误，请查看抓取页面相应类型！");
		  }
	  }
	  
	  /**把篮球对阵解析到matchArrange*/
	  public void parseJCLQToMatchArrange(Element element)
	  {
		  if(element == null)
		  {
			  return;
		  }
		  List<Element> elements = element.elements();
		  for(Iterator it = elements.iterator(); it.hasNext();)
		  {
			  MatchArrange matchArrange = new MatchArrange();
			  MatchArrange checkMatchArrange = null;
			  Element eleme = (Element) it.next();
			  if(eleme.getName().equals("m"))
			  {
				  List<Element> elemens = eleme.elements();
				  String sfdg = "";
				  String sfgg = "";
				  String rfsfdg = "";
				  String rfsfgg = "";
				  String sfcdg = "";
				  String sfcgg = "";
				  String dxfdg = "";
				  String dxfgg = "";
				  String sfdgRatios = "";
				  String sfggRatios = "";
				  String rfsfdgRatios = "";
				  String rfsfggRatios = "";
				  String sfcdgRatios = "";
				  String sfcggRatios = "";
				  String dxfdgRatios = "";
				  String dxfggRatios = "";
				  for(Iterator ite = elemens.iterator(); ite.hasNext();)
				  {
					  Element elemen = (Element) ite.next();	
					  if(elemen.getName().equals("mNo"))
					  {
						  String boutIndex = elemen.getTextTrim();
						  checkMatchArrange = matchArrangeService.getMatchInfoForJCLQByMatchNoAndTpye(boutIndex);
						  if(checkMatchArrange == null)
						  {
							  matchArrange.setBoutIndex(boutIndex);
							  String sub = "20" + boutIndex.substring(0, 6);
							  DateFormat df = new SimpleDateFormat("yyyyMMdd");
							  Calendar c = Calendar.getInstance();  
							  try 
							  {
								  c.setTime(df.parse(sub));
							  } 
							  catch (Exception e) 
							  {
								  e.printStackTrace();
							  }
							  int weekday = c.get(Calendar.DAY_OF_WEEK)-1;
							  if(weekday == 0)
							  {
								  weekday = 7;
							  }
							  String boutIndexOther = "20" + boutIndex.substring(0, 6) + weekday + boutIndex.substring(6, 9);
							  matchArrange.setBoutIndexOther(boutIndexOther);					  
						  }
					  }
					  else if(elemen.getName().equals("Eodd"))
					  {
						  List<Element> eles =elemen.elements();
						  for(Iterator iterat = eles.iterator(); iterat.hasNext();)
						  {
							  Element elem = (Element) iterat.next();
							  if(elem.getName().equals("ZF"))
							  {
								  matchArrange.setFop(elem.getTextTrim());
							  }
							  else if(elem.getName().equals(""))
							  {
								  matchArrange.setSop(elem.getTextTrim());
							  }
						  }
					  }
					  else if(elemen.getName().equals("mName"))
					  {
						  matchArrange.setMatchName(elemen.getTextTrim());
					  }
					  else if(elemen.getName().equals("mTime"))
					  {
						  matchArrange.setMatchTime(DateUtil.parse(elemen.getTextTrim(), "yy-MM-dd HH:mm"));
					  }
					  else if(elemen.getName().equals("hT"))
					  {
						  matchArrange.setHomeTeam(elemen.getTextTrim());
					  }
					  else if(elemen.getName().equals("aT"))
					  {
						  matchArrange.setAwaryTeam(elemen.getTextTrim());
					  }
					  else if(elemen.getName().equals("sfdg"))
					  {
						  sfdg = "SGCSF";
						  sfdgRatios = this.saveCurrentRatios(elemen, sfdgRatios, "sfdg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("sfgg"))
					  {
						  sfgg = "MGCSF";
						  sfggRatios = this.saveCurrentRatios(elemen, sfggRatios, "sfgg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("rfsfdg"))
					  {
						  rfsfdg = "SGRFSF";
						  rfsfdgRatios = this.saveCurrentRatios(elemen, rfsfdgRatios, "rfsfdg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("rfsfgg"))
					  {
						  rfsfgg = "MGRFSF";
						  rfsfggRatios = this.saveCurrentRatios(elemen, rfsfggRatios, "rfsfgg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("sfxdg"))
					  {
						  sfcdg = "SGSFC";
						  sfcdgRatios = this.saveCurrentRatios(elemen, sfcdgRatios, "sfcdg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("sfxgg"))
					  {
						  sfcgg = "MGSFC";
						  sfcggRatios = this.saveCurrentRatios(elemen, sfcggRatios, "sfxgg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("dxfdg"))
					  {
						  dxfdg = "SGDXF";
						  dxfdgRatios = this.saveCurrentRatios(elemen, dxfdgRatios, "dxfdg", matchArrange, checkMatchArrange);
					  }
					  else if(elemen.getName().equals("dxfgg"))
					  {
						  dxfgg = "MGDXF";
						  dxfggRatios = this.saveCurrentRatios(elemen, dxfggRatios, "dxfgg", matchArrange, checkMatchArrange);
					  }
					  else
					  {
						  continue;
					  }
				  }
				  String playTypes = sfdg + "|" + sfgg + "|" + rfsfdg + "|" + rfsfgg + "|" + sfcdg + "|" + sfcgg + "|" + dxfdg + "|" + dxfgg;
				  String currentRatios = "";
				  if(!sfdgRatios.equals(""))
				  {
					  currentRatios = sfdgRatios;
				  }
				  else
				  {
					  currentRatios = "-1";
				  }
				  if(!sfggRatios.equals(""))
				  {
					  currentRatios = currentRatios + ";" +sfggRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + ";-1";
				  }
				  if(!rfsfdgRatios.equals(""))
				  {
					  currentRatios = currentRatios + "|" + rfsfdgRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + "|-1";
				  }
				  if(!rfsfggRatios.equals(""))
				  {
					  currentRatios = currentRatios + ";" + rfsfggRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + ";-1";
				  }
				  if(!sfcdgRatios.equals(""))
				  {
					  currentRatios = currentRatios + "|" + sfcdgRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + "|-1";
				  }
				  if(!sfcggRatios.equals(""))
				  {
					  currentRatios = currentRatios + ";" + sfcggRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + ";-1";
				  }
				  if(!dxfdgRatios.equals(""))
				  {
					  currentRatios = currentRatios + "|" + dxfdgRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + "|-1";
				  }
				  if(!dxfggRatios.equals(""))
				  {
					  currentRatios = currentRatios + ";" + dxfggRatios;
				  }
				  else
				  {
					  currentRatios = currentRatios + ";-1";
				  }
				  Calendar stopTime = Calendar.getInstance();
				  stopTime.setTime(matchArrange.getMatchTime().getTime());
				  stopTime=getStopTime(stopTime);
				  if(checkMatchArrange == null)
				  {  
					  matchArrange.setPlayTypes(playTypes);		  
					  matchArrange.setCurrentRatios(currentRatios);
					  matchArrange.setLotteryType(LotteryType.竞彩篮球);
					  LotteryTerm term = lotteryTermService.getCurrentTerm(LotteryType.竞彩篮球);
					  matchArrange.setTerm(term);
					  matchArrange.setSaleDate(Calendar.getInstance());
					  matchArrange.setStopSaleTime(stopTime);		
					  Calendar openPrizeTime = Calendar.getInstance();
				      openPrizeTime.setTime(matchArrange.getMatchTime().getTime());
					  openPrizeTime.add(Calendar.DATE, 1);
					  matchArrange.setOpenPrizeTime(openPrizeTime);
					  if(matchArrange.getStopSaleTime().before(Calendar.getInstance())) {
							//match.setStatus(RaceStatus.已停售);
					  }
					  else {
						  matchArrange.setStatus(RaceStatus.销售中);
					  }
					  jclqTaskExcutor.addNewMatch(matchArrange);
					  matchArrangeService.save(matchArrange);
				  }	
				  else
				  {
					  RaceStatus status = checkMatchArrange.getStatus();
					  if((status != RaceStatus.已停售) &&(status != RaceStatus.已开奖) &&(status != RaceStatus.已兑奖))
					  {
					  //todo add time 20111128
						  checkMatchArrange.setPlayTypes(playTypes);
						  checkMatchArrange.setCurrentRatios(currentRatios);
						  Calendar oldstopTime = Calendar.getInstance();
						  Calendar newstopTime = (Calendar)stopTime.clone();
						  oldstopTime.setTime(checkMatchArrange.getStopSaleTime().getTime());
						  newstopTime.add(Calendar.MINUTE, -2);
						  if(newstopTime.after(oldstopTime)){  //time changed, add again
								//System.out.print(""+stopTime.toString()+" "+oldstopTime.toString());
								jclqTaskExcutor.addNewMatch(checkMatchArrange);
						  }
							
						  newstopTime.add(Calendar.MINUTE, 4);
						  if(newstopTime.before(oldstopTime)){  //time changed, add again
								//System.out.print(""+stopTime.toString()+" "+oldstopTime.toString());
							   jclqTaskExcutor.addNewMatch(checkMatchArrange);
						  }
					  }
					  matchArrangeService.save(checkMatchArrange); 
				  }
			  }
		  }
	  }
	
	  /**存贮即时赔率时间调用的*/
	  public String saveCurrentRatios(Element elemen, String currentRatios, String type, MatchArrange matchArrange, MatchArrange checkMatchArrange)
	  {
		  List<Element> elemes = elemen.elements();
		  for(int i=0; i<elemes.size(); i++)
		  {
			  if(type.equals("rfsfdg"))
			  {
				  if(i == 0)
				  {
					  String dgrf = elemes.get(0).getTextTrim();
					  if(checkMatchArrange == null)
					  {
						  matchArrange.setDanguanRangFen(dgrf);
					  }
					  else
					  {
						  checkMatchArrange.setDanguanRangFen(dgrf);
					  }
				  }
				  else
				  {
					  if(i == 1)
					  {
						  currentRatios += elemes.get(1).getTextTrim();
					  }
					  else
					  {
						  currentRatios += "," + elemes.get(i).getTextTrim();
					  }	  
				  }
			  }
			  else if(type.equals("rfsfgg"))
			  {
				  if(i == 0)
				  {
					  String ggrf = elemes.get(0).getTextTrim();
					  if(checkMatchArrange == null)
					  {
						  matchArrange.setDuoguanRangFen(ggrf);
					  }
					  else
					  {
						  checkMatchArrange.setDuoguanRangFen(ggrf);
					  }
				  }
				  else
				  {
					  if(i == 1)
					  {
						  currentRatios += elemes.get(1).getTextTrim();
					  }
					  else
					  {
						  currentRatios += "," + elemes.get(i).getTextTrim();
					  }
				  }
			  }
			  else if(type.equals("dxfdg"))
			  {
				  if(i == 0)
				  {
					  String dgdxf = elemes.get(0).getTextTrim();
					  if(checkMatchArrange == null)
					  {
						  matchArrange.setDanguanDaXiaoQiu(dgdxf);
					  }
					  else
					  {
						  checkMatchArrange.setDanguanDaXiaoQiu(dgdxf);
					  }
				  }
				  else
				  {
					  if(i == 1)
					  {
						  currentRatios += elemes.get(1).getTextTrim();
					  }
					  else
					  {
						  currentRatios += "," + elemes.get(i).getTextTrim();
					  }
				  }
			  }
			  else if(type.equals("dxfgg"))
			  {
				  if(i == 0)
				  {
					  String ggdxf = elemes.get(0).getTextTrim();
					  if(checkMatchArrange == null)
					  {
						  matchArrange.setDuoguanDaXiaoQiu(ggdxf);
					  }
					  else
					  {
						  checkMatchArrange.setDuoguanDaXiaoQiu(ggdxf);
					  }
				  }
				  else
				  {
					  if(i == 1)
					  {
						  currentRatios += elemes.get(1).getTextTrim();
					  }
					  else
					  {
						  currentRatios += "," + elemes.get(i).getTextTrim();
					  }
				  }
			  }
			  else
			  {
				  if(i == 0)
				  {
					  currentRatios += elemes.get(0).getTextTrim();
				  }
				  else
				  {
					  currentRatios += "," + elemes.get(i).getTextTrim();
				  }
			  }
		  }
		  return currentRatios;
	  }
    
/**把盘口解析到matchHistory中*/
	  public void parsePankouToMatchHistory(Element body)
	  {
		  if(body == null)
		  {
			  return;
		  }
		  List<Element> elements = body.elements();
		  for(Iterator it = elements.iterator(); it.hasNext();)
		  {
			  MatchHistory matchHistory = new MatchHistory();
			  Element elemen = (Element) it.next();
			  if(elemen.getName().equals("m"))
			  {
				  List<Element> elemens = elemen.elements();
				  for(Iterator ite = elemens.iterator(); ite.hasNext();)
				  {
					  Element eleme = (Element) ite.next();
					  if(eleme.getName().equals("mid"))
					  {
						  matchHistory = matchHistoryeService.findById(Long.parseLong(eleme.getTextTrim()));
						  if(matchHistory == null)
						  {
							  break;
						  }
					  }
					  else if(eleme.getName().equals("pk"))
					  {
						  matchHistory.setPankou(eleme.getTextTrim());
					  }
				  }
				  if(matchHistory != null)
				  {
					  matchHistoryeService.save(matchHistory);
				  }  
			  }			  
		  }
	  }
    @SuppressWarnings("unchecked")
    private void parseMatchArrages(Element body) throws Exception
    {
    	LotteryTerm term = lotteryTermService.getCurrentTerm(LotteryType.竞彩足球);
        List elements = body.elements();
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next(); 
            if(elem.getName().equals("m")) {
                parseMatch(elem, term);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void parseMatchHistory(Element body) throws Exception
    { 
        List<MatchHistory> list = new ArrayList<MatchHistory>();
        List elements = body.elements();
        String year="";
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next();
            //System.out.println(elem.getName());
            if(elem.getName().equals("time")) {
            	year=elem.getTextTrim().substring(0, 4);
            }
            if(elem.getName().equals("m")) {
            	MatchHistory match = parseHistory(elem,year);
                if(null != match) {
                    
                    list.add(match);
                }
            }
        }
        matchArrangeService.saveMatchHistory(list);
    }
    
    private static Calendar  getStopTime(Calendar matchtime)
    {
    	Calendar stopTime;
    	Calendar matchtime2=Calendar.getInstance();
    	matchtime2=(Calendar)matchtime.clone();
    	matchtime2.add(Calendar.MINUTE, -15);
    	
		Calendar c=Calendar.getInstance();
		Calendar c1=Calendar.getInstance();
		
		Calendar c2=Calendar.getInstance();
		
		Calendar c3=Calendar.getInstance();
		Calendar c4=Calendar.getInstance();
		c.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 0, 0);
		c1.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 9, 30);
		c2.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 22,40);
		c3.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 0,40);
		
		c4.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
				matchtime.get(Calendar.DAY_OF_MONTH), 24,00);
		if(c.before(matchtime)&&c1.after(matchtime))
		{
			if(matchtime.get(Calendar.DAY_OF_WEEK)==1||matchtime.get(Calendar.DAY_OF_WEEK)==2){
				stopTime=matchtime2.compareTo(c3)>0?c3:matchtime2;
			}
			else{
				c2.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
						matchtime.get(Calendar.DAY_OF_MONTH)-1, 22,40);
				stopTime=matchtime2.compareTo(c2)>0?c2:matchtime2;
			}
			return stopTime;
		}

	    if(c2.before(matchtime)&&c4.after(matchtime))
	    {
			if(matchtime.get(Calendar.DAY_OF_WEEK)!=7&&matchtime.get(Calendar.DAY_OF_WEEK)!=1){
				stopTime=matchtime2.compareTo(c2)>0?c2:matchtime2;
				return stopTime;
			}
	    }
	    return matchtime2;
    }
    
    @SuppressWarnings("unchecked")
    private MatchArrange parseMatch(Element matchNode, LotteryTerm term) throws Exception
    {
        if(matchNode == null) {
            return null;
        }
        String rfspfdg = null;
        String rfspfgg = null;
        String spfdg = null;
        String spfgg = null;
        String zjqdg = null;
        String zjqgg = null;
        String bfdg = null;
        String bfgg = null;
        String bqcdg = null;
        String bqcgg = null;
        Float sop,pop,fop,sgl,pgl,fgl;
        
        MatchArrange match = new MatchArrange();
        List elements = matchNode.elements(); 
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next(); 
            if(elem.getName().equals("mNo")) {
                match.setBoutIndex(elem.getTextTrim());
            }
            else if(elem.getName().equals("mName")) {
                match.setMatchName(elem.getTextTrim());
            }
            else if(elem.getName().equals("mTime")) {  
            	match.setMatchTime(DateUtil.parse(elem.getTextTrim(), "yy-MM-dd HH:mm"));
            }
            else if(elem.getName().equals("Eodd")) {
            	match.setSop(getElement(elem, "So").getTextTrim());
            	match.setPop(getElement(elem, "Po").getTextTrim());
            	match.setFop(getElement(elem, "Fo").getTextTrim());
            	
            	if((match.getSop().length()>2)&&(match.getPop().length()>2)&&(match.getFop().length()>2))
            	{
            		sop=Float.parseFloat(match.getSop());
            		pop=Float.parseFloat(match.getPop());
            		fop=Float.parseFloat(match.getFop());
            		sgl=pop*fop*100/(sop*pop+sop*fop+pop*fop);
            		pgl=sop*fop*100/(sop*pop+sop*fop+pop*fop);
            		fgl=sop*pop*100/(sop*pop+sop*fop+pop*fop);
          
            		match.setStzb(Integer.toString(Math.round(sgl)));
            		match.setPtzb(Integer.toString(Math.round(pgl)));
            		match.setFtzb(Integer.toString(Math.round(fgl)));
            	
            	}
            }
            else if(elem.getName().equals("hT")) {
                match.setHomeTeam(elem.getTextTrim());
            }
            else if(elem.getName().equals("aT")) {
                match.setAwaryTeam(elem.getTextTrim());
            }
            else if(elem.getName().equals("conCede")) {
                match.setConcede(elem.getTextTrim()); 
            }
            else if(elem.getName().equals("sfcdg")) {
                spfdg = getOption(elem);
            } 
            else if(elem.getName().equals("sfcgg")) {
                spfgg = getOption(elem);
            }
            else if(elem.getName().equals("rfsfcdg")) {
                rfspfdg = getOption(elem);
            } 
            else if(elem.getName().equals("rfsfcgg")) {
                rfspfgg = getOption(elem);
            }
            else if(elem.getName().equals("zjqdg")) {
                zjqdg = getOption(elem);
            }
            else if(elem.getName().equals("zjqgg")) {
                zjqgg = getOption(elem);
            }
            else if(elem.getName().equals("bfdg")) {
                bfdg = getOption(elem);
            }
            else if(elem.getName().equals("bfgg")) {
                bfgg = getOption(elem);
            }
            else if(elem.getName().equals("bqcdg")) {
                bqcdg = getOption(elem);
            }
            else if(elem.getName().equals("bqcgg")) {
                bqcgg = getOption(elem);
            }
        }
        
        String currentRatios = null;
        if(spfdg != null || spfgg != null) {
        	currentRatios = spfdg + ";" + spfgg;
        }
        
		if(zjqdg != null || zjqgg != null) {
		    if(currentRatios != null) {
		    	currentRatios = currentRatios + "|" + zjqdg + ";" + zjqgg ;
		    }
		    else {
		    	currentRatios = currentRatios + "|";
		    }
        }
		
		if(bfdg != null || bfgg != null) {
			if(currentRatios != null) {
		    	currentRatios = currentRatios + "|" + bfdg + ";" + bfgg ;
		    }
		    else {
		    	currentRatios = currentRatios + "|";
		    }
		}
		
		if(bqcdg != null || bqcgg != null) {
			if(currentRatios != null) {
		    	currentRatios = currentRatios + "|" + bqcdg + ";" + bqcgg ;
		    }
		    else {
		    	currentRatios = currentRatios + "|";
		    }
		}
		
		match.setSaleDate(Calendar.getInstance());
		
		Calendar stopTime = Calendar.getInstance();
		stopTime.setTime(match.getMatchTime().getTime());
		stopTime=getStopTime(stopTime);
		
		match.setStopSaleTime(stopTime);
		
		Calendar openPrizeTime = Calendar.getInstance();
		openPrizeTime.setTime(match.getMatchTime().getTime());
		openPrizeTime.add(Calendar.DATE, 1);
		match.setOpenPrizeTime(openPrizeTime);
		
		match.setCurrentRatios(currentRatios);
		if(match.getStopSaleTime().before(Calendar.getInstance())) {
			//match.setStatus(RaceStatus.已停售);
		}
		else {
			match.setStatus(RaceStatus.销售中);
		}
		
		MatchArrange m = matchArrangeService.getUniqueMatchByMatchNo(match.getBoutIndex());
		if((m != null)) {
			if((m.getStatus() != RaceStatus.已停售))
			{
			Calendar oldstopTime = Calendar.getInstance();
			Calendar newstopTime = (Calendar)stopTime.clone();
			oldstopTime.setTime(m.getStopSaleTime().getTime());
			
            m.setBoutIndex(match.getBoutIndex());
            m.setMatchName(match.getMatchName());
            m.setMatchTime(match.getMatchTime());
            m.setHomeTeam(match.getHomeTeam());
            m.setAwaryTeam(match.getAwaryTeam());
            m.setConcede(match.getConcede());
            if(match.getSop().length()>2)
            	m.setSop(match.getSop());
            if(match.getPop().length()>2)
            	m.setPop(match.getPop());
            if(match.getFop().length()>2)
            	m.setFop(match.getFop());
            m.setStzb(match.getStzb());
            m.setPtzb(match.getPtzb());
            m.setFtzb(match.getFtzb());
			m.setCurrentRatios(match.getCurrentRatios());
			m.setSaleDate(Calendar.getInstance());
			m.setStopSaleTime(match.getStopSaleTime());
			m.setOpenPrizeTime(match.getOpenPrizeTime());
			if((m.getStatus() != RaceStatus.已停售) &&(m.getStatus() != RaceStatus.已开奖) &&(m.getStatus() != RaceStatus.已兑奖))
			{
				m.setStatus(match.getStatus());
			}
			newstopTime.add(Calendar.MINUTE, -2);
			if(newstopTime.after(oldstopTime)){  //time changed, add again
				//System.out.print(""+stopTime.toString()+" "+oldstopTime.toString());
				jczqTaskExcutor.addNewMatch(m);
			}
			
			newstopTime.add(Calendar.MINUTE, 4);
			if(newstopTime.before(oldstopTime)){  //time changed, add again
				//System.out.print(""+stopTime.toString()+" "+oldstopTime.toString());
				jczqTaskExcutor.addNewMatch(m);
			}
			}
			
		}
		else {
		
			m = match;
	
        	jczqTaskExcutor.addNewMatch(m);
	
		}
		
		if(null != m) {
            m.setLotteryType(LotteryType.竞彩足球);
            m.setTerm(term);
            matchArrangeService.save(m);
        }
		
        return m;
    }
    
    
    
    @SuppressWarnings("unchecked")
    private MatchHistory parseHistory(Element matchNode, String year) throws Exception
    {
        if(matchNode == null) {
            return null;
        }
        
        MatchHistory match = new MatchHistory();
        List elements = matchNode.elements(); 
        for (Iterator it = elements.iterator(); it.hasNext();) {
       /*     Element elem = (Element) it.next(); 
            if(elem.getName().equals("mName")) {
                match.setMatchName(elem.getTextTrim());
            }
            else if(elem.getName().equals("mTime")) {
            	String datestr;
            	datestr=year+"-"+elem.getTextTrim();
            	match.setMatchOverTime(DateUtil.parse(datestr, "yyyy-MM-dd HH:mm"));
            }
            else if(elem.getName().equals("mS")) {
                match.setStatus(elem.getTextTrim());
            }
            else if(elem.getName().equals("hT")) {
                match.setHomeTeam(elem.getTextTrim());
            }
            else if(elem.getName().equals("score")) {
                match.setWholeScore(elem.getTextTrim());
            }
            else if(elem.getName().equals("aT")) {
                match.setAwaryTeam(elem.getTextTrim());
            }
            else if(elem.getName().equals("half")) {
                match.setHalfScore(elem.getTextTrim());
            }
            else if(elem.getName().equals("pk")) {
                match.setPankou(elem.getTextTrim());
            }*/     
        }
        /*
        List<TeamMapping> htlist;
        htlist = teamMappingService.getTeamMappingByMatchHistoryName(match.getHomeTeam());
        if(htlist.size()==0)
        {
        	TeamMapping teamMapping = new TeamMapping();
			teamMapping.setMatchName(match.getMatchName());
			teamMapping.setMatchJCZQName("");
			teamMapping.setMatchHistoryName(match.getHomeTeam());
			teamMappingService.save(teamMapping);
        }
        
        htlist = teamMappingService.getTeamMappingByMatchHistoryName(match.getAwaryTeam());
        if(htlist.size()==0)
        {
        	TeamMapping teamMapping = new TeamMapping();
			teamMapping.setMatchName(match.getMatchName());
			teamMapping.setMatchJCZQName("");
			teamMapping.setMatchHistoryName(match.getAwaryTeam());
			teamMappingService.save(teamMapping);
        }
        
        */
        return match;
    }
    
    @SuppressWarnings("unchecked")
	private String getOption(Element opNode)
    {
    	String op = null;
    	List elements = opNode.elements(); 
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next(); 
            if(elem.getName().equals("op")) {
                if(op != null) {
                	op = op + "," + elem.getTextTrim();
                }
                else {
                	op = elem.getTextTrim();
                }
            }
        }
    	return op;
    }
    
    @SuppressWarnings("unchecked")
    private void parseOpenPrize(Element body) throws Exception
    {
    	LotteryTerm term = lotteryTermService.getCurrentTerm(LotteryType.竞彩足球);
        
        List<MatchArrange> list = new ArrayList<MatchArrange>();
        List elements = body.elements();
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next(); 
            if(elem.getName().equals("m")) {
                MatchArrange match = parseMatchResult(elem);
                if(null != match) {
                    match.setLotteryType(LotteryType.竞彩足球);
                    match.setTerm(term);
                    list.add(match);
                }
            }
        }
        
        matchArrangeService.saveAllMatches(list);
    }
    
    @SuppressWarnings("unchecked")
    private MatchArrange parseMatchResult(Element matchNode) throws Exception
    {
        if(matchNode == null) {
            return null;
        }
        
        //boolean special = false;
    	String op_matchResult = null;
    	String op_wholeScore = null;
    	String op_halfScore = null;
    	Integer goals = 0;
    	String halfRes = null;
    	String op_rfspfsp = null;
    	String op_spfsp = null;
    	String op_cbfsp = null;
    	String op_jqssp = null;
    	String op_bqcsp = null;
    	String op_jqs = null;
    	
        MatchArrange m = new MatchArrange();
        List elements = matchNode.elements(); 
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next(); 
            if(elem.getName().equals("mNo")) {
                m.setBoutIndex(elem.getTextTrim());
                
            }
            else if(elem.getName().equals("score")) {
            	String[] score = elem.getTextTrim().split("\\s+");
            	op_halfScore = score[0].substring(1, score[0].length() - 1);
            	op_wholeScore = score[1];
            }
            else if(elem.getName().equals("RFSPF")) {
            	
            	op_rfspfsp = getElement(elem, "prize").getTextTrim();
            }
            else if(elem.getName().equals("SPF")) {
            	op_matchResult = getElement(elem, "result").getTextTrim();
            	if(op_matchResult.equals("胜")) 
            	{
            		op_matchResult="3";
            	} else 
            	if(op_matchResult.equals("平"))
            	{
            		op_matchResult="1";
            	}
            	else
            	if(op_matchResult.equals("负")) 
            	{
            		op_matchResult="0";
            	}
            	
            	op_spfsp = getElement(elem, "prize").getTextTrim();
            }
            else if(elem.getName().equals("ZJQ")) {
            	op_jqs = getElement(elem, "result").getTextTrim();
            	if(op_jqs.equals("7 +")) op_jqs = "7";
            	goals = Integer.parseInt(op_jqs);
            	op_jqssp = getElement(elem, "prize").getTextTrim();
            }
            else if(elem.getName().equals("BF")) {
            	op_cbfsp = getElement(elem, "prize").getTextTrim();
            }
            else if(elem.getName().equals("BQC")) {
            	halfRes = tranBqc(getElement(elem, "result").getTextTrim());
            	op_bqcsp = getElement(elem, "prize").getTextTrim();
            }
        }
        
		MatchArrange match = matchArrangeService.getUniqueMatchByMatchNo(m.getBoutIndex());
		if(match != null) {
			match.setMatchResult(op_matchResult);
			match.setWholeScore(op_wholeScore);
			match.setHalfScore(op_halfScore);
			match.setHalfResult(halfRes);
			match.setGoals(goals);
			String tmprfspfsp,tmpspfsp,tmpcbfsp,tmpjqssp,tmpbqcsp;
			if(op_rfspfsp.equals("无胜出投注")) op_rfspfsp="0";
			if(op_spfsp.equals("无胜出投注")) op_spfsp="0";
			if(op_cbfsp.equals("无胜出投注")) op_cbfsp="0";
			if(op_jqssp.equals("无胜出投注")) op_jqssp="0";
			if(op_bqcsp.equals("无胜出投注")) op_bqcsp="0";
			
			tmprfspfsp=String.valueOf(Float.parseFloat(op_rfspfsp)/2);
			tmpspfsp=String.valueOf(Float.parseFloat(op_spfsp)/2);
			tmpcbfsp=String.valueOf(Float.parseFloat(op_cbfsp)/2);
			tmpjqssp=String.valueOf(Float.parseFloat(op_jqssp)/2);
			tmpbqcsp=String.valueOf(Float.parseFloat(op_bqcsp)/2);
			
			match.setSpRangfenSfp(tmprfspfsp);
			match.setSpSfp(tmpspfsp);
			match.setSpBf(tmpcbfsp);
			match.setSpJqs(tmpjqssp);
			match.setSpBcsfp(tmpbqcsp);
		}
        return match;
    }
    
    private String tranBqc(String half)
    {
    	if(half.equals("平平")) {
    		return "1-1";
    	}
    	else if(half.equals("平负")) {
    		return "1-0";
    	}
    	else if(half.equals("平胜")) {
    		return "1-3";
    	}
    	else if(half.equals("负平")) {
    		return "0-1";
    	}
    	else if(half.equals("负负")) {
    		return "0-0";
    	}
    	else if(half.equals("负胜")) {
    		return "0-3";
    	}
    	else if(half.equals("胜平")) {
    		return "3-1";
    	}
    	else if(half.equals("胜胜")) {
    		return "3-3";
    	}
    	else if(half.equals("胜负")) {
    		return "3-0";
    	}
    	return null;
    }
    public static void main(String arg[])
    {
    	
    	Calendar tt;
    	tt=Calendar.getInstance();
    	tt.set(2011,10, 7,22,40);
    	tt=getStopTime(tt);
    	System.out.println(""+tt.get(Calendar.YEAR)+" "+tt.get(Calendar.MONTH)+" "+tt.get(Calendar.DAY_OF_MONTH)+" "+tt.get(Calendar.HOUR_OF_DAY)+" "+tt.get(Calendar.MINUTE));
    }
}
