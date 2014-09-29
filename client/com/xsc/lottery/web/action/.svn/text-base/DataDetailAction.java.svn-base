package com.xsc.lottery.web.action;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.googlecode.jsonplugin.JSONException;
import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.entity.business.LeagueRank;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.MatchMapping;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.CompanyService;
import com.xsc.lottery.service.business.LeagueRankService;
import com.xsc.lottery.service.business.LeagueService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.service.business.MatchHistoryService;
import com.xsc.lottery.service.business.MatchMappingService;
import com.xsc.lottery.service.business.OddService;
import com.xsc.lottery.service.business.TeamService;


@SuppressWarnings("serial")
@Scope("prototype")
@Controller("detail.datedetail")
public class DataDetailAction extends LotteryClientBaseAction
{
	@Autowired
    private TeamService teamService; 
	
	@Autowired
    private LeagueService leagueService; 
	
	@Autowired
    private LotteryTermService lotteryTermService;
	
	@Autowired
    private MatchMappingService matchMappingService; 
	
	@Autowired
    private MatchHistoryService matchHistoryService; 
	
	@Autowired
    private MatchArrangeService matchArrangeService;
	
	@Autowired
    private LeagueRankService leagueRankService;
	
	@Autowired
    private OddService oddService;
	
	@Autowired
    private CompanyService companyService;
	
	private String type;
	
	private String no;
	
	private String leagueName;
    
    private String hostName;

    private String visitName;
    
    private Team host;
    private Team visit;
    
    private String winPeiLv;
    
    private String drowPeiLv;
    
    private String losePeiLv;
    
    private MatchArrange matchArrange;
    
    private LeagueRank hostLeagueRank;
    
    private LeagueRank visitLeagueRank;
    
    private List<MatchHistory> hostTenOverList = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> hostTenOverListFix = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> visitTenOverList = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> visitTenOverListFix = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> hostSixOverList = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> hostSixOverListFix = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> visitSixOverList = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> visitSixOverListFix = new ArrayList<MatchHistory>();
    
    private List<MatchHistory> battleList;
    
    private List<MatchHistory> fixedBattleList;
    
    private List<MatchHistory> hostFeatureList;
    
    private List<MatchHistory> visitFeatureList;
    
    private List<Map> battleListMapList = new ArrayList<Map>();
    
    private List<Map> hostTenOverListTempMapList = new ArrayList<Map>();
    
    private List<Map> hostTenOverListFixTempMapList = new ArrayList<Map>();
    
    private List<Map> visitTenOverListTempMapList = new ArrayList<Map>();
    
    private List<Map> visitTenOverListFixTempMapList = new ArrayList<Map>();
    
    private List<Map> hostSixOverListTempMapList = new ArrayList<Map>();
    
    private List<Map> hostSixOverListFixTempMapList = new ArrayList<Map>();
    
    private List<Map> visitSixOverListTempMapList = new ArrayList<Map>();
    
    private List<Map> visitSixOverListFixTempMapList = new ArrayList<Map>();
    
    private List<Map> fixedBattleListMapList = new ArrayList<Map>();
    
    private List<Map> hostTenOverListTempNoMapList = new ArrayList<Map>();
    
    private List<Map> hostTenOverListFixTempNoMapList = new ArrayList<Map>();
    
    private List<Map> visitTenOverListTempNoMapList = new ArrayList<Map>();
    
    private List<Map> visitTenOverListFixTempNoMapList = new ArrayList<Map>();
    
    private List<Map> hostSixOverListTempNoMapList = new ArrayList<Map>();
    
    private List<Map> hostSixOverListFixNoTempMapList = new ArrayList<Map>();
    
    private List<Map> visitSixOverListTempNoMapList = new ArrayList<Map>();
    
    private List<Map> visitSixOverListFixTempNoMapList = new ArrayList<Map>();
    
    private int hmax;
    
    private int hhmax;
    
    private int hamax;
    
    private int vmax;
    
    private int vhmax;
    
    private int vamax;
    
    private List<Odd> anLastOddList;
    
    private List<Odd> anFirstOddList;
    
    private List<Odd> epLastOddList;
    
    private List<Odd> epFirstOddList;
    
    //最后的概率
    private List<String> shengLv = new ArrayList<String>();
    
    private List<String> pingLv = new ArrayList<String>();
    
    private List<String> fuLv = new ArrayList<String>();
    
    private List<String> back = new ArrayList<String>();
    
    //开始的概率
    private List<String> shengLv0 = new ArrayList<String>();

    private List<String> pingLv0 = new ArrayList<String>();
    
    private List<String> fuLv0 = new ArrayList<String>();
    
    private List<String> back0 = new ArrayList<String>();
    
    //走势图相关   每场走势的结果
    private List<String> hostTenResult = new ArrayList<String>();
    
    private List<String> hostTenFixResult = new ArrayList<String>();
    
    private List<String> hostSixResult = new ArrayList<String>();
    
    private List<String> hostSixFixResult = new ArrayList<String>();
    
    private List<String> visitTenResult = new ArrayList<String>();
    
    private List<String> visitTenFixResult = new ArrayList<String>();
    
    private List<String> visitSixResult = new ArrayList<String>();
    
    private List<String> visitSixFixResult = new ArrayList<String>();
    
    //胜平负相关   每场胜平负的结果
    private List<String> hostTenNoResult = new ArrayList<String>();
    
    private List<String> hostTenNoFixResult = new ArrayList<String>();
    
    private List<String> hostSixNoResult = new ArrayList<String>();
    
    private List<String> hostSixNoFixResult = new ArrayList<String>();
    
    private List<String> visitTenNoResult = new ArrayList<String>();
    
    private List<String> visitTenNoFixResult = new ArrayList<String>();
    
    private List<String> visitSixNoResult = new ArrayList<String>();
    
    private List<String> visitSixNoFixResult = new ArrayList<String>();
    
    private List<String> battleListNoResult = new ArrayList<String>();
    
    private List<String> fixedBattleListNoFixResult = new ArrayList<String>();
    
    private String companyName;
    
    private List<Odd> companyOddList = new ArrayList<Odd>();
    
    private List<Odd> allAnList = new ArrayList<Odd>();
    
    private List<Odd> allEpList = new ArrayList<Odd>();
    
    private Odd aomenOdd;
    
    public String index()
    {
    	MatchMapping matchMapping = matchMappingService.getMatchMappingByTypeNoAndTermNo(type, no, "999999");
    	if(matchMapping==null)
    	{
    		return SUCCESS;
    	}
    	Long matchNo = Long.parseLong(matchMapping.getMatchNo());
    	LotteryTerm lotteryTerm = lotteryTermService.getByTypeAndTermNo("999999", LotteryType.竞彩足球);
    	MatchHistory matchHistory = matchHistoryService.findById(matchNo);
    	matchArrange = matchArrangeService.findLastMatchByTermAndIndex(no, lotteryTerm, LotteryType.竞彩足球);
    	String[] ratios = matchArrange.getCurrentRatios().split("\\|")[0].split(";")[1].split(",");
    	winPeiLv = ratios[0];
    	drowPeiLv = ratios[1];
    	losePeiLv = ratios[2];
    	Team hostTeam = matchHistory.getHostTeam();
    	Team visitTeam = matchHistory.getVisitTeam();
    	this.hostName = hostTeam.getName();
    	this.visitName = visitTeam.getName();
    	this.host=hostTeam;
    	this.visit=visitTeam;
    	List<MatchHistory> hostTenOverListTemp = matchHistoryService.getLatestMatchByStatus(hostTeam, "完", 10, false, 0);
    	this.getPanshiAndPankou(hostTenOverListTemp,hostTenOverList,hostTenOverListTempNoMapList,hostName,hostTenResult);
    	hostTenOverListTempMapList = this.countSPFNum(hostTenOverListTemp, hostTenOverListTempMapList,hostTenNoResult,hostName);
    	List<MatchHistory> hostTenOverListFixTemp = matchHistoryService.getLatestMatchByStatus(hostTeam, "完", 10, true, 0);
    	this.getPanshiAndPankou(hostTenOverListFixTemp,hostTenOverListFix,hostTenOverListFixTempNoMapList,hostName,hostTenFixResult);
    	hostTenOverListFixTempMapList = this.countSPFNum(hostTenOverListFixTemp, hostTenOverListFixTempMapList,hostTenNoFixResult,hostName);
    	List<MatchHistory> visitTenOverListTemp = matchHistoryService.getLatestMatchByStatus(visitTeam, "完", 10, false, 0);
    	this.getPanshiAndPankou(visitTenOverListTemp,visitTenOverList,visitTenOverListTempNoMapList,visitName,visitTenResult);
    	visitTenOverListTempMapList = this.countSPFNum(visitTenOverListTemp, visitTenOverListTempMapList,visitTenNoResult,visitName);
    	List<MatchHistory> visitTenOverListFixTemp = matchHistoryService.getLatestMatchByStatus(visitTeam, "完", 10, true, 1);
    	this.getPanshiAndPankou(visitTenOverListFixTemp,visitTenOverListFix,visitTenOverListFixTempNoMapList,visitName,visitTenFixResult);
    	visitTenOverListFixTempMapList = this.countSPFNum(visitTenOverListFixTemp, visitTenOverListFixTempMapList,visitTenNoFixResult,visitName);
    	List<MatchHistory> hostSixOverListTemp = matchHistoryService.getLatestMatchByStatus(hostTeam, "完", 20, false, 0);
    	this.getPanshiAndPankou(hostSixOverListTemp,hostSixOverList,hostSixOverListTempNoMapList,hostName,hostSixResult);
    	hostSixOverListTempMapList = this.countSPFNum(hostSixOverListTemp, hostSixOverListTempMapList,hostSixNoResult,hostName);
    	List<MatchHistory> hostSixOverListFixTemp = matchHistoryService.getLatestMatchByStatus(hostTeam, "完", 20, true, 0);
    	this.getPanshiAndPankou(hostSixOverListFixTemp,hostSixOverListFix,hostSixOverListFixNoTempMapList,hostName,hostSixFixResult);
    	hostSixOverListFixTempMapList = this.countSPFNum(hostSixOverListFixTemp, hostSixOverListFixTempMapList,hostSixNoFixResult,hostName);
    	List<MatchHistory> visitSixOverListTemp = matchHistoryService.getLatestMatchByStatus(visitTeam, "完", 20, false, 0);
    	this.getPanshiAndPankou(visitSixOverListTemp,visitSixOverList,visitSixOverListTempNoMapList,visitName,visitSixResult);
    	visitSixOverListTempMapList = this.countSPFNum(visitSixOverListTemp, visitSixOverListTempMapList,visitSixNoResult,visitName);
    	List<MatchHistory> visitSixOverListFixTemp = matchHistoryService.getLatestMatchByStatus(visitTeam, "完", 20, true, 1);
    	this.getPanshiAndPankou(visitSixOverListFixTemp,visitSixOverListFix,visitSixOverListFixTempNoMapList,visitName,visitSixFixResult);
    	visitSixOverListFixTempMapList = this.countSPFNum(visitSixOverListFixTemp, visitSixOverListFixTempMapList,visitSixNoFixResult,visitName);
    	hostFeatureList = matchHistoryService.getLatestMatchByStatus(hostTeam, "未", 3, false, 0);
    	visitFeatureList = matchHistoryService.getLatestMatchByStatus(visitTeam, "未", 3, false, 0);
    	battleList = matchHistoryService.getTwoTeamsBattles(hostTeam, visitTeam, false);
    	battleListMapList = this.countSPFNum(battleList, battleListMapList,battleListNoResult,hostName);
    	fixedBattleList = matchHistoryService.getTwoTeamsBattles(hostTeam, visitTeam, true);
    	fixedBattleListMapList = this.countSPFNum(fixedBattleList, fixedBattleListMapList,fixedBattleListNoFixResult,hostName);
    	hostLeagueRank = leagueRankService.getLeagueRankByTeamNameAndLeagueName(hostName, matchHistory.getLeague().getName());
    	hmax = this.getMaxNum(hostLeagueRank,0);
    	hhmax = this.getMaxNum(hostLeagueRank,1);
    	hamax = this.getMaxNum(hostLeagueRank,2);
    	visitLeagueRank = leagueRankService.getLeagueRankByTeamNameAndLeagueName(visitName, matchHistory.getLeague().getName());
    	vmax = this.getMaxNum(visitLeagueRank,0);
    	vhmax = this.getMaxNum(visitLeagueRank,1);
    	vamax = this.getMaxNum(visitLeagueRank,2);
    	Company company = companyService.getCompanyByName("澳门");
    	List<Odd> oddList = oddService.getOddListByMatchIdCompanyAndType(matchNo+"", company, "an", "");
    	if(!oddList.isEmpty())
    	{
    		aomenOdd = oddList.get(0);
    	}
        return SUCCESS;
    }
    
    
    public String getOdd()
    {
    	MatchMapping matchMapping = matchMappingService.getMatchMappingByTypeNoAndTermNo(type, no, "999999");
    	Long matchNo = Long.parseLong(matchMapping.getMatchNo());
    	LotteryTerm lotteryTerm = lotteryTermService.getByTypeAndTermNo("999999", LotteryType.竞彩足球);
    	MatchHistory matchHistory = matchHistoryService.findById(matchNo);
    	matchArrange = matchArrangeService.findLastMatchByTermAndIndex(no, lotteryTerm, LotteryType.竞彩足球);
    	String[] ratios = matchArrange.getCurrentRatios().split("\\|")[0].split(";")[1].split(",");
    	winPeiLv = ratios[0];
    	drowPeiLv = ratios[1];
    	losePeiLv = ratios[2];
    	Team hostTeam = matchHistory.getHostTeam();
    	Team visitTeam = matchHistory.getVisitTeam();
    	this.hostName = hostTeam.getName();
    	this.visitName = visitTeam.getName();
    	this.host=hostTeam;
    	this.visit=visitTeam;
    	String matchId = matchNo + "";
//    	List<Odd> anFirstOddListTemp = oddService.getOddFirstListByMatchId(matchId, "an");
//    	this.changRangToRightType(anFirstOddListTemp);
//    	anFirstOddList = oddService.getOddFirstListByMatchId(matchId, "an");
//    	List<Odd> anLastOddListTemp = oddService.getOddLastListByMatchId(matchId, "an");
//    	this.changRangToRightType(anLastOddListTemp);
//    	anLastOddList = oddService.getOddLastListByMatchId(matchId, "an");
//    	epFirstOddList = oddService.getOddFirstListByMatchId(matchId, "ep");
//    	this.getAllGaiLv(epFirstOddList, shengLv0, pingLv0, fuLv0, back0);
//    	epLastOddList = oddService.getOddLastListByMatchId(matchId, "ep");
//    	this.getAllGaiLv(epLastOddList, shengLv, pingLv, fuLv, back);
    	List<Odd> allAnListTemp = oddService.getAllOddList(matchId, "an");
    	this.changRangToRightType(allAnListTemp);
    	allAnList = oddService.getAllOddList(matchId, "an");
    	allEpList = oddService.getAllOddList(matchId, "ep");
    	this.getAllGaiLv(allEpList, shengLv0, pingLv0, fuLv0, back0);
    	Company company = companyService.getCompanyByName("澳门");
    	List<Odd> oddList = oddService.getOddListByMatchIdCompanyAndType(matchId, company, "an", "");
    	if(!oddList.isEmpty())
    	{
    		aomenOdd = oddList.get(0);
    	}	
    	return "odd";
    }
    
    public String getTheCompanyAllList() throws JSONException
    {
    	MatchMapping matchMapping = matchMappingService.getMatchMappingByTypeNoAndTermNo("jczq", no, "999999");
    	Long matchNo = Long.parseLong(matchMapping.getMatchNo());
    	Company company = companyService.getCompanyByName(companyName);
    	companyOddList = oddService.getOddListByMatchIdCompanyAndType(matchNo+"", company, "an", "");
 	    JSONArray resultArray = new JSONArray();
    	JSONObject jsonObject = new JSONObject();
    	for (int i = 0; i < companyOddList.size(); i++)
    	{  
    		jsonObject = this.getDomainJSON(companyOddList.get(i)); 
    		resultArray.add(jsonObject);
    	}
    	setJsonString(resultArray.toString());
    	return AJAXJSON;
    }
    
    @SuppressWarnings("unused")
	private JSONObject getDomainJSON(Odd odd) throws JSONException 
    { 
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	JSONObject jsonObject = new JSONObject(); 
    	jsonObject.put("shuiwei1", odd.getSheng());
    	jsonObject.put("rangqiu", odd.getRang());
    	jsonObject.put("shuiwei2", odd.getFu());
    	jsonObject.put("time", df.format(odd.getTime().getTime()));
    	return jsonObject;
    }
    
    public String transitionPankou(String s)
    {
    	if(s.trim().equals("0"))
    	{
    		s = "平手";
    	}
    	else if(s.trim().equals("0.5"))
    	{
    		s = "半球";
    	}
    	else if(s.trim().equals("1"))
    	{
    		s = "一球";
    	}
    	else if(s.trim().equals("1.5"))
    	{
    		s = "球半";
    	}
    	else if(s.trim().equals("2"))
    	{
    		s = "两球";
    	}
    	else if(s.trim().equals("2.5"))
    	{
    		s = "两球半";
    	}
    	else if(s.trim().equals("3"))
    	{
    		s = "三球";
    	}
    	else if(s.trim().equals("3.5"))
    	{
    		s = "三球半";
    	}
    	else if(s.trim().equals("4"))
    	{
    		s = "四球";
    	}
    	else if(s.trim().equals("4.5"))
    	{
    		s = "四球半";
    	}
    	else if(s.trim().equals("5"))
    	{
    		s = "五球";
    	}
    	else if(s.trim().equals("5.5"))
    	{
    		s = "五球半";
    	}
    	return s;
    }
    
    public void getPanshiAndPankou(List<MatchHistory> list, List<MatchHistory> newList, List<Map> noList, String teamName, List<String> resultList)
    {
    	int winNo = 0;
    	int drowNo = 0;
    	int loseNo = 0;
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(int i=0; i<list.size(); i++)
    	{
    		String pankou1 = "";
    		MatchHistory matchHistory = list.get(i);
    		String homeTeamName = matchHistory.getHostTeam().getName();
    		double host = matchHistory.getHostScore();
    		double visit = matchHistory.getVisitScore();
    		double b = -1;
    		String pankou = matchHistory.getPankou();
    		if(pankou == null || pankou.equals(""))
    		{
    			pankou = "-";
    		}
    		if(pankou.indexOf("-") > -1)
    		{
    			if(pankou.length() == 1)
    			{
    				matchHistory.setPanshi("--");
    			}
    			if(pankou.indexOf("/") > -1)
    			{
    				b = Double.parseDouble(pankou.split("/")[1]);
    				if((host - b) >= visit)
    				{
    					if(homeTeamName.equals(teamName))
    					{
    						matchHistory.setPanshi("赢");
    						resultList.add("3");
    						winNo++;
    					}
    					
    					else
    					{
    						matchHistory.setPanshi("输");
    						resultList.add("0");
    						loseNo++;
    					}
    				}
    				else
    				{
    					
    					if(homeTeamName.equals(teamName))
    					{
    						matchHistory.setPanshi("输");
    						resultList.add("0");
    						loseNo++;
    					}
    					else
    					{
    						matchHistory.setPanshi("赢"); 
    						resultList.add("3");
    						winNo++;
    					}
    				}
    			}
    			else
    			{
    				if(pankou.replace("-", "").length() == 0)
    				{
    					matchHistory.setPanshi("--");
    					resultList.add("");
    				}
    				else
    				{
    					b = Double.parseDouble(pankou.replace("-", ""));
    				}
    				if(b != -1)
    				{
    					if((host - b) > visit)
        				{
        					if(homeTeamName.equals(teamName))
        					{
        						matchHistory.setPanshi("赢");
        						resultList.add("3");
        						winNo++;
        					}
        					else
        					{
        						matchHistory.setPanshi("输");
        						resultList.add("0");
        						loseNo++;
        					}
        				}
        				else if((host - b) == visit)
        				{
        					matchHistory.setPanshi("平");
        					resultList.add("1");
        					drowNo++;
        				}
        				else
        				{
        					if(homeTeamName.equals(teamName))
        					{
        						matchHistory.setPanshi("输");
        						resultList.add("0");
        						loseNo++;
        					}
        					else
        					{
        						matchHistory.setPanshi("赢");
        						resultList.add("3");
        						winNo++;
        					}
        				}
    				}		
    			}
    		}
    		else
    		{
    			if(pankou.indexOf("/") > -1)
    			{
    				b = Double.parseDouble(pankou.split("/")[1]);
    				if((host + b) > visit)
    				{
    					if(homeTeamName.equals(teamName))
    					{
    						matchHistory.setPanshi("赢");
							resultList.add("3");
    						winNo++;
    					} else
    					{
    						matchHistory.setPanshi("输");
								resultList.add("0");
        					loseNo++;
    					}
    				}
    				else
    				{
    					if(homeTeamName.equals(teamName))
    					{
    				
    						matchHistory.setPanshi("输");
								resultList.add("0");
    						loseNo++;
    					} else
    					{
    						matchHistory.setPanshi("赢");
							resultList.add("3");
    						winNo++;
    					}
    				}
    			}
    			else
    			{
    				b = Double.parseDouble(pankou);
    				if((host + b) > visit)
    				{
    					
    					if(homeTeamName.equals(teamName))
    					{
    						matchHistory.setPanshi("赢");
							resultList.add("3");
    						winNo++;
    					} else
    					{
    						matchHistory.setPanshi("输");
							resultList.add("0");
        					loseNo++;
    					}
    				}
    				else if((host + b) == visit)
    				{
    					matchHistory.setPanshi("平");
						resultList.add("1");
    					drowNo++;
    				}
    				else
    				{
    					if(homeTeamName.equals(teamName))
    					{
    				
    						matchHistory.setPanshi("输");
							resultList.add("0");
    						loseNo++;
    					} else
    					{
    						matchHistory.setPanshi("赢");
							resultList.add("3");
    						winNo++;
    					}
    				}
    			}
    		}
    		
    		if(pankou.indexOf("/") > -1)
        	{
        		String[] temp = pankou.split("/");
        		if(temp[0].indexOf("-") > -1)
        		{
        			pankou1 = this.transitionPankou(temp[0].replace("-", "")) + "/" + this.transitionPankou(temp[1]);
        		}
        		else
        		{
        			String str = this.transitionPankou(temp[0]);
        			String sstr = this.transitionPankou(temp[1]);
        			if(!str.equals("平手"))
        			{
        				pankou1 = "受" + str + "/" + sstr;
        			}
        			else
        			{
        				pankou1 = str + "/" + "受" + sstr;
        			}
        		}
        	}
        	else
        	{
        		if(pankou.equals("") || pankou == null)
        		{
        			pankou = "-";
        		}
        		if(pankou.indexOf("-") > -1)
        		{
        			if(pankou.length() == 1)
        			{
        				pankou1 = "--";
        			}
        			else
        			{
        				pankou1 = this.transitionPankou(pankou.replace("-", ""));
        			}       			
        		}
        		else
        		{
        			String str = this.transitionPankou(pankou);
        			if(!str.equals("平手"))
        			{
        				pankou1 = "受" + str;
        			}
        			else
        			{
        				pankou1 = str;
        			}
        		}
        	}
    		matchHistory.setPankouOther(pankou1);
    		matchHistoryService.save(matchHistory);
    		newList.add(matchHistory);
    	}
    	map.put("winNo", winNo);
		map.put("drowNo", drowNo);
		map.put("loseNo", loseNo);
		noList.add(map);
    }
    
    //把odd中的让球转换成相应的文字
    public void changRangToRightType(List<Odd> list)
    {
    	for(int i=0; i<list.size(); i++)
    	{
    		Odd odd = list.get(i);
    		String pankou = odd.getRang();
    		String pankou1 = "";
    		if(pankou.indexOf("/") > -1)
        	{
        		String[] temp = pankou.split("/");
        		if(temp[0].indexOf("-") > -1)
        		{
        			pankou1 = this.transitionPankou(temp[0].replace("-", "")) + "/" + this.transitionPankou(temp[1]);
        		}
        		else
        		{
        			String str = this.transitionPankou(temp[0]);
        			String sstr = this.transitionPankou(temp[1]);
        			if(!str.equals("平手"))
        			{
        				pankou1 = "受" + str + "/" + sstr;
        			}
        			else
        			{
        				pankou1 = str + "/" + "受" + sstr;
        			}
        		}
        	}
        	else
        	{
        		if(pankou.equals("") || pankou == null)
        		{
        			pankou = "-";
        		}
        		if(pankou.indexOf("-") > -1)
        		{
        			if(pankou.length() == 1)
        			{
        				pankou1 = "--";
        			}
        			else
        			{
        				pankou1 = this.transitionPankou(pankou.replace("-", ""));
        			}       			
        		}
        		else
        		{
        			String str = this.transitionPankou(pankou);
        			if(!str.equals("平手"))
        			{
        				pankou1 = "受" + str;
        			}
        			else
        			{
        				pankou1 = str;
        			}
        		}
        	}
    		odd.setRangOther(pankou1);
    		oddService.save(odd);
    	}
    }
    
    //得到双方对阵的胜平负的场次
    public List<Map> countSPFNum(List<MatchHistory> list, List<Map> numList, List<String> strList, String teamName)
    {
    	if(list == null)
    	{
    		return null;
    	}
    	int winNum = 0;
    	int drowNum = 0;
    	int loseNum = 0;
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(int i=0; i<list.size(); i++)
    	{
    		MatchHistory matchHistory = list.get(i);
    		int hostScore = matchHistory.getHostScore();
    		int visitScore = matchHistory.getVisitScore();
    		String homeName = matchHistory.getHostTeam().getName();
    		if((hostScore - visitScore) > 0)
    		{
    			if(homeName.equals(teamName))
    			{
    				winNum++;
    				strList.add("3");
    			}
    			else
    			{
    				loseNum++;
    				strList.add("0");
    			}
    		}
    		else if((hostScore - visitScore) < 0)
    		{
    			if(homeName.equals(teamName))
    			{
    				loseNum++;
    				strList.add("0");
    			}
    			else
    			{
    				winNum++;
    				strList.add("3");
    			}
    		}
    		else
    		{
    			drowNum++;
    			strList.add("1");
    		}
    	}
    	map.put("winNum", winNum);
    	map.put("drowNum", drowNum);
    	map.put("loseNum", loseNum);
    	numList.add(map);
    	return numList;
    }
    
    public int getMaxNum(LeagueRank lr, int which)
    {
    	if(lr == null)
    	{
    		return 5;
    	}
    	int win = 0;
    	int drow = 0;
    	int lose = 0;
    	if(which == 0)
    	{
    		Integer wr = lr.getWinRound();
    		Integer lrs = lr.getLoseRound();
    		Integer dr = lr.getDrowRound();
    		if(wr == null)
    		{
    			win = 0;
    		}
    		else
    		{
    			win = wr;
    		}
    		if(lrs == null)
    		{
    			lose = 0;
    		}
    		else
    		{
    			lose = lrs;
    		}
    		if(dr == null)
    		{
    			drow = 0;
    		}
    		else
    		{
    			drow = dr;
    		}
        	
    	}	
    	else if(which == 1)
    	{	
        	Integer wr = lr.getHwinRound();
    		Integer lrs = lr.getHloseRound();
    		Integer dr = lr.getHdrowRound();
    		if(wr == null)
    		{
    			win = 0;
    		}
    		else
    		{
    			win = wr;
    		}
    		if(lrs == null)
    		{
    			lose = 0;
    		}
    		else
    		{
    			lose = lrs;
    		}
    		if(dr == null)
    		{
    			drow = 0;
    		}
    		else
    		{
    			drow = dr;
    		}
    	}
    	else
    	{
        	Integer wr = lr.getAwinRound();
    		Integer lrs = lr.getAloseRound();
    		Integer dr = lr.getAdrowRound();
    		if(wr == null)
    		{
    			win = 0;
    		}
    		else
    		{
    			win = wr;
    		}
    		if(lrs == null)
    		{
    			lose = 0;
    		}
    		else
    		{
    			lose = lrs;
    		}
    		if(dr == null)
    		{
    			drow = 0;
    		}
    		else
    		{
    			drow = dr;
    		}
    	}
    	String str = "";
    	if(win >= drow && win >= lose)
    	{
    		str += win;
    		win = this.disposeNum(str, win);
    		return win; 
    	}
    	else if(drow >= win && drow > lose)
    	{
    		str += drow;
    		drow = this.disposeNum(str, drow);
    		return drow;
    	}
    	else
    	{
    		str += lose;
    		lose = this.disposeNum(str, lose);
    		return lose;
    	}
    }
    
    public int disposeNum(String str, int i)
    {
    	if(str.length() == 1)
		{
			char c = str.charAt(0);
			String temp = c + "";
			int cfirst = Integer.parseInt(temp);
			if(cfirst <= 5)
			{
				cfirst = 5;
			}
			else
			{
				cfirst = 10;
			}
			str = cfirst + "";
			i = Integer.parseInt(str.trim());
		}
		else
		{
			char c = str.charAt(1);
    		char cc = str.charAt(0);
    		String temp1 = c + "";
    		String temp2 = cc + "";
    		int clast = Integer.parseInt(temp1);
    		int cfirst = Integer.parseInt(temp2);
    		if(clast != 0 && clast != 5)
    		{
    			//数字过百会出问题
    			if(clast > 5)
    			{
    				cfirst += 1;
    				clast = 0;
    			}
    			else
    			{
    				clast = 5;
    			}
    			str = cfirst*10 + clast + "";
    			i = Integer.parseInt(str.trim());
    		}
		}
    	return i;
    }
    
    public void getAllGaiLv(List<Odd> list, List<String> shenglv, List<String> pinglv, List<String> fulv, List<String> ba)
    {
    	for(int i=0; i<list.size(); i++)
    	{
    		Odd odd = list.get(i);
    		double sheng = Double.parseDouble(odd.getSheng());
    		double ping = Double.parseDouble(odd.getPing());
    		double fu = Double.parseDouble(odd.getFu());
    		double all = sheng*ping + ping*fu + sheng*fu;
    		double sl = ((ping*fu)/all)*100;
    		double pl = ((sheng*fu)/all)*100;
    		double fl = ((sheng*ping)/all)*100;
    		double bac = sheng*sl;
    		DecimalFormat formater = new DecimalFormat();
    		formater.setMaximumFractionDigits(0);
    		formater.setGroupingSize(0);
    		formater.setRoundingMode(RoundingMode.HALF_UP);	
    		shenglv.add(formater.format(sl) + "");
    		pinglv.add(formater.format(pl) + "");   		
    		fulv.add(formater.format(fl) + "");
    		ba.add(formater.format(bac) + "");
    	}
    }

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public List<MatchHistory> getBattleList() {
		return battleList;
	}

	public void setBattleList(List<MatchHistory> battleList) {
		this.battleList = battleList;
	}

	public List<MatchHistory> getHostFeatureList() {
		return hostFeatureList;
	}

	public void setHostFeatureList(List<MatchHistory> hostFeatureList) {
		this.hostFeatureList = hostFeatureList;
	}

	public List<MatchHistory> getVisitFeatureList() {
		return visitFeatureList;
	}

	public void setVisitFeatureList(List<MatchHistory> visitFeatureList) {
		this.visitFeatureList = visitFeatureList;
	}

	public MatchArrange getMatchArrange() {
		return matchArrange;
	}

	public void setMatchArrange(MatchArrange matchArrange) {
		this.matchArrange = matchArrange;
	}

	public List<MatchHistory> getFixedBattleList() {
		return fixedBattleList;
	}

	public void setFixedBattleList(List<MatchHistory> fixedBattleList) {
		this.fixedBattleList = fixedBattleList;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public LeagueRank getHostLeagueRank() {
		return hostLeagueRank;
	}

	public void setHostLeagueRank(LeagueRank hostLeagueRank) {
		this.hostLeagueRank = hostLeagueRank;
	}

	public LeagueRank getVisitLeagueRank() {
		return visitLeagueRank;
	}

	public void setVisitLeagueRank(LeagueRank visitLeagueRank) {
		this.visitLeagueRank = visitLeagueRank;
	}

	public Team getHost() {
		return host;
	}

	public void setHost(Team host) {
		this.host = host;
	}

	public Team getVisit() {
		return visit;
	}

	public void setVisit(Team visit) {
		this.visit = visit;
	}

	public List<MatchHistory> getHostTenOverList() {
		return hostTenOverList;
	}

	public void setHostTenOverList(List<MatchHistory> hostTenOverList) {
		this.hostTenOverList = hostTenOverList;
	}

	public List<MatchHistory> getVisitTenOverList() {
		return visitTenOverList;
	}

	public void setVisitTenOverList(List<MatchHistory> visitTenOverList) {
		this.visitTenOverList = visitTenOverList;
	}

	public List<MatchHistory> getHostSixOverList() {
		return hostSixOverList;
	}

	public void setHostSixOverList(List<MatchHistory> hostSixOverList) {
		this.hostSixOverList = hostSixOverList;
	}

	public List<MatchHistory> getVisitSixOverList() {
		return visitSixOverList;
	}

	public void setVisitSixOverList(List<MatchHistory> visitSixOverList) {
		this.visitSixOverList = visitSixOverList;
	}

	public String getWinPeiLv() {
		return winPeiLv;
	}

	public void setWinPeiLv(String winPeiLv) {
		this.winPeiLv = winPeiLv;
	}

	public String getDrowPeiLv() {
		return drowPeiLv;
	}

	public void setDrowPeiLv(String drowPeiLv) {
		this.drowPeiLv = drowPeiLv;
	}

	public String getLosePeiLv() {
		return losePeiLv;
	}

	public void setLosePeiLv(String losePeiLv) {
		this.losePeiLv = losePeiLv;
	}

	public List<MatchHistory> getHostTenOverListFix() {
		return hostTenOverListFix;
	}

	public void setHostTenOverListFix(List<MatchHistory> hostTenOverListFix) {
		this.hostTenOverListFix = hostTenOverListFix;
	}

	public List<MatchHistory> getVisitTenOverListFix() {
		return visitTenOverListFix;
	}

	public void setVisitTenOverListFix(List<MatchHistory> visitTenOverListFix) {
		this.visitTenOverListFix = visitTenOverListFix;
	}

	public List<MatchHistory> getHostSixOverListFix() {
		return hostSixOverListFix;
	}

	public void setHostSixOverListFix(List<MatchHistory> hostSixOverListFix) {
		this.hostSixOverListFix = hostSixOverListFix;
	}

	public List<MatchHistory> getVisitSixOverListFix() {
		return visitSixOverListFix;
	}

	public void setVisitSixOverListFix(List<MatchHistory> visitSixOverListFix) {
		this.visitSixOverListFix = visitSixOverListFix;
	}

	public List<Map> getBattleListMapList() {
		return battleListMapList;
	}

	public void setBattleListMapList(List<Map> battleListMapList) {
		this.battleListMapList = battleListMapList;
	}

	public List<Map> getFixedBattleListMapList() {
		return fixedBattleListMapList;
	}

	public void setFixedBattleListMapList(List<Map> fixedBattleListMapList) {
		this.fixedBattleListMapList = fixedBattleListMapList;
	}

	public List<Map> getHostTenOverListTempMapList() {
		return hostTenOverListTempMapList;
	}

	public void setHostTenOverListTempMapList(List<Map> hostTenOverListTempMapList) {
		this.hostTenOverListTempMapList = hostTenOverListTempMapList;
	}

	public List<Map> getHostTenOverListFixTempMapList() {
		return hostTenOverListFixTempMapList;
	}

	public void setHostTenOverListFixTempMapList(
			List<Map> hostTenOverListFixTempMapList) {
		this.hostTenOverListFixTempMapList = hostTenOverListFixTempMapList;
	}

	public List<Map> getVisitTenOverListTempMapList() {
		return visitTenOverListTempMapList;
	}

	public void setVisitTenOverListTempMapList(List<Map> visitTenOverListTempMapList) {
		this.visitTenOverListTempMapList = visitTenOverListTempMapList;
	}

	public List<Map> getVisitTenOverListFixTempMapList() {
		return visitTenOverListFixTempMapList;
	}

	public void setVisitTenOverListFixTempMapList(
			List<Map> visitTenOverListFixTempMapList) {
		this.visitTenOverListFixTempMapList = visitTenOverListFixTempMapList;
	}

	public List<Map> getHostSixOverListTempMapList() {
		return hostSixOverListTempMapList;
	}

	public void setHostSixOverListTempMapList(List<Map> hostSixOverListTempMapList) {
		this.hostSixOverListTempMapList = hostSixOverListTempMapList;
	}

	public List<Map> getHostSixOverListFixTempMapList() {
		return hostSixOverListFixTempMapList;
	}

	public void setHostSixOverListFixTempMapList(
			List<Map> hostSixOverListFixTempMapList) {
		this.hostSixOverListFixTempMapList = hostSixOverListFixTempMapList;
	}

	public List<Map> getVisitSixOverListTempMapList() {
		return visitSixOverListTempMapList;
	}

	public void setVisitSixOverListTempMapList(List<Map> visitSixOverListTempMapList) {
		this.visitSixOverListTempMapList = visitSixOverListTempMapList;
	}

	public List<Map> getVisitSixOverListFixTempMapList() {
		return visitSixOverListFixTempMapList;
	}

	public void setVisitSixOverListFixTempMapList(
			List<Map> visitSixOverListFixTempMapList) {
		this.visitSixOverListFixTempMapList = visitSixOverListFixTempMapList;
	}

	public List<Map> getHostTenOverListTempNoMapList() {
		return hostTenOverListTempNoMapList;
	}

	public void setHostTenOverListTempNoMapList(
			List<Map> hostTenOverListTempNoMapList) {
		this.hostTenOverListTempNoMapList = hostTenOverListTempNoMapList;
	}

	public List<Map> getHostTenOverListFixTempNoMapList() {
		return hostTenOverListFixTempNoMapList;
	}

	public void setHostTenOverListFixTempNoMapList(
			List<Map> hostTenOverListFixTempNoMapList) {
		this.hostTenOverListFixTempNoMapList = hostTenOverListFixTempNoMapList;
	}

	public List<Map> getVisitTenOverListTempNoMapList() {
		return visitTenOverListTempNoMapList;
	}

	public void setVisitTenOverListTempNoMapList(
			List<Map> visitTenOverListTempNoMapList) {
		this.visitTenOverListTempNoMapList = visitTenOverListTempNoMapList;
	}

	public List<Map> getVisitTenOverListFixTempNoMapList() {
		return visitTenOverListFixTempNoMapList;
	}

	public void setVisitTenOverListFixTempNoMapList(
			List<Map> visitTenOverListFixTempNoMapList) {
		this.visitTenOverListFixTempNoMapList = visitTenOverListFixTempNoMapList;
	}

	public List<Map> getHostSixOverListTempNoMapList() {
		return hostSixOverListTempNoMapList;
	}

	public void setHostSixOverListTempNoMapList(
			List<Map> hostSixOverListTempNoMapList) {
		this.hostSixOverListTempNoMapList = hostSixOverListTempNoMapList;
	}

	public List<Map> getHostSixOverListFixNoTempMapList() {
		return hostSixOverListFixNoTempMapList;
	}

	public void setHostSixOverListFixNoTempMapList(
			List<Map> hostSixOverListFixNoTempMapList) {
		this.hostSixOverListFixNoTempMapList = hostSixOverListFixNoTempMapList;
	}

	public List<Map> getVisitSixOverListTempNoMapList() {
		return visitSixOverListTempNoMapList;
	}

	public void setVisitSixOverListTempNoMapList(
			List<Map> visitSixOverListTempNoMapList) {
		this.visitSixOverListTempNoMapList = visitSixOverListTempNoMapList;
	}

	public List<Map> getVisitSixOverListFixTempNoMapList() {
		return visitSixOverListFixTempNoMapList;
	}

	public void setVisitSixOverListFixTempNoMapList(
			List<Map> visitSixOverListFixTempNoMapList) {
		this.visitSixOverListFixTempNoMapList = visitSixOverListFixTempNoMapList;
	}

	public int getHmax() {
		return hmax;
	}

	public void setHmax(int hmax) {
		this.hmax = hmax;
	}

	public int getVmax() {
		return vmax;
	}

	public void setVmax(int vmax) {
		this.vmax = vmax;
	}


	public List<Odd> getAnLastOddList() {
		return anLastOddList;
	}


	public void setAnLastOddList(List<Odd> anLastOddList) {
		this.anLastOddList = anLastOddList;
	}


	public List<Odd> getEpLastOddList() {
		return epLastOddList;
	}


	public void setEpLastOddList(List<Odd> epLastOddList) {
		this.epLastOddList = epLastOddList;
	}


	public List<Odd> getAnFirstOddList() {
		return anFirstOddList;
	}


	public void setAnFirstOddList(List<Odd> anFirstOddList) {
		this.anFirstOddList = anFirstOddList;
	}


	public List<Odd> getEpFirstOddList() {
		return epFirstOddList;
	}


	public void setEpFirstOddList(List<Odd> epFirstOddList) {
		this.epFirstOddList = epFirstOddList;
	}


	public List<String> getShengLv() {
		return shengLv;
	}


	public void setShengLv(List<String> shengLv) {
		this.shengLv = shengLv;
	}


	public List<String> getPingLv() {
		return pingLv;
	}


	public void setPingLv(List<String> pingLv) {
		this.pingLv = pingLv;
	}


	public List<String> getFuLv() {
		return fuLv;
	}


	public void setFuLv(List<String> fuLv) {
		this.fuLv = fuLv;
	}


	public List<String> getBack() {
		return back;
	}


	public void setBack(List<String> back) {
		this.back = back;
	}


	public List<String> getShengLv0() {
		return shengLv0;
	}


	public void setShengLv0(List<String> shengLv0) {
		this.shengLv0 = shengLv0;
	}


	public List<String> getPingLv0() {
		return pingLv0;
	}


	public void setPingLv0(List<String> pingLv0) {
		this.pingLv0 = pingLv0;
	}


	public List<String> getFuLv0() {
		return fuLv0;
	}


	public void setFuLv0(List<String> fuLv0) {
		this.fuLv0 = fuLv0;
	}


	public List<String> getBack0() {
		return back0;
	}


	public void setBack0(List<String> back0) {
		this.back0 = back0;
	}


	public List<String> getHostTenResult() {
		return hostTenResult;
	}


	public void setHostTenResult(List<String> hostTenResult) {
		this.hostTenResult = hostTenResult;
	}


	public List<String> getHostTenFixResult() {
		return hostTenFixResult;
	}


	public void setHostTenFixResult(List<String> hostTenFixResult) {
		this.hostTenFixResult = hostTenFixResult;
	}


	public List<String> getHostSixResult() {
		return hostSixResult;
	}


	public void setHostSixResult(List<String> hostSixResult) {
		this.hostSixResult = hostSixResult;
	}


	public List<String> getHostSixFixResult() {
		return hostSixFixResult;
	}


	public void setHostSixFixResult(List<String> hostSixFixResult) {
		this.hostSixFixResult = hostSixFixResult;
	}


	public List<String> getVisitTenResult() {
		return visitTenResult;
	}


	public void setVisitTenResult(List<String> visitTenResult) {
		this.visitTenResult = visitTenResult;
	}


	public List<String> getVisitTenFixResult() {
		return visitTenFixResult;
	}


	public void setVisitTenFixResult(List<String> visitTenFixResult) {
		this.visitTenFixResult = visitTenFixResult;
	}


	public List<String> getVisitSixResult() {
		return visitSixResult;
	}


	public void setVisitSixResult(List<String> visitSixResult) {
		this.visitSixResult = visitSixResult;
	}


	public List<String> getVisitSixFixResult() {
		return visitSixFixResult;
	}


	public void setVisitSixFixResult(List<String> visitSixFixResult) {
		this.visitSixFixResult = visitSixFixResult;
	}


	public List<String> getHostTenNoResult() {
		return hostTenNoResult;
	}


	public void setHostTenNoResult(List<String> hostTenNoResult) {
		this.hostTenNoResult = hostTenNoResult;
	}


	public List<String> getHostTenNoFixResult() {
		return hostTenNoFixResult;
	}


	public void setHostTenNoFixResult(List<String> hostTenNoFixResult) {
		this.hostTenNoFixResult = hostTenNoFixResult;
	}


	public List<String> getHostSixNoResult() {
		return hostSixNoResult;
	}


	public void setHostSixNoResult(List<String> hostSixNoResult) {
		this.hostSixNoResult = hostSixNoResult;
	}


	public List<String> getHostSixNoFixResult() {
		return hostSixNoFixResult;
	}


	public void setHostSixNoFixResult(List<String> hostSixNoFixResult) {
		this.hostSixNoFixResult = hostSixNoFixResult;
	}


	public List<String> getVisitTenNoResult() {
		return visitTenNoResult;
	}


	public void setVisitTenNoResult(List<String> visitTenNoResult) {
		this.visitTenNoResult = visitTenNoResult;
	}


	public List<String> getVisitTenNoFixResult() {
		return visitTenNoFixResult;
	}


	public void setVisitTenNoFixResult(List<String> visitTenNoFixResult) {
		this.visitTenNoFixResult = visitTenNoFixResult;
	}


	public List<String> getVisitSixNoResult() {
		return visitSixNoResult;
	}


	public void setVisitSixNoResult(List<String> visitSixNoResult) {
		this.visitSixNoResult = visitSixNoResult;
	}


	public List<String> getVisitSixNoFixResult() {
		return visitSixNoFixResult;
	}


	public void setVisitSixNoFixResult(List<String> visitSixNoFixResult) {
		this.visitSixNoFixResult = visitSixNoFixResult;
	}


	public List<String> getBattleListNoResult() {
		return battleListNoResult;
	}


	public void setBattleListNoResult(List<String> battleListNoResult) {
		this.battleListNoResult = battleListNoResult;
	}


	public List<String> getFixedBattleListNoFixResult() {
		return fixedBattleListNoFixResult;
	}


	public void setFixedBattleListNoFixResult(
			List<String> fixedBattleListNoFixResult) {
		this.fixedBattleListNoFixResult = fixedBattleListNoFixResult;
	}


	public int getHhmax() {
		return hhmax;
	}


	public void setHhmax(int hhmax) {
		this.hhmax = hhmax;
	}


	public int getHamax() {
		return hamax;
	}


	public void setHamax(int hamax) {
		this.hamax = hamax;
	}


	public int getVhmax() {
		return vhmax;
	}


	public void setVhmax(int vhmax) {
		this.vhmax = vhmax;
	}


	public int getVamax() {
		return vamax;
	}


	public void setVamax(int vamax) {
		this.vamax = vamax;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public List<Odd> getCompanyOddList() {
		return companyOddList;
	}


	public void setCompanyOddList(List<Odd> companyOddList) {
		this.companyOddList = companyOddList;
	}


	public List<Odd> getAllAnList() {
		return allAnList;
	}


	public void setAllAnList(List<Odd> allAnList) {
		this.allAnList = allAnList;
	}


	public List<Odd> getAllEpList() {
		return allEpList;
	}


	public void setAllEpList(List<Odd> allEpList) {
		this.allEpList = allEpList;
	}


	public Odd getAomenOdd() {
		return aomenOdd;
	}


	public void setAomenOdd(Odd aomenOdd) {
		this.aomenOdd = aomenOdd;
	}
}
