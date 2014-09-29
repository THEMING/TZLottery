/**
 * 
 */
package com.xsc.lottery.web.action.football;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.googlecode.jsonplugin.JSONException;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings({"serial", "unchecked"})
@Scope("prototype")
@Controller("fb.JCZQ")
public class JCZQAction extends LotteryClientBaseAction
{
    @Autowired
    private MatchArrangeService matchArrangeService;
	private Set saishi = new HashSet();
    private  List<MatchArrange> matchArrangelist;
    private String date;
    private String danduo;

	public String getDanduo() {
		return danduo;
	}


	public void setDanduo(String danduo) {
		this.danduo = danduo;
	}


	public String index()
    {
    	matchArrangelist = matchArrangeService.getCurrentMatchArrangeForJCZQ(); 
    	for( MatchArrange matchArrange:  matchArrangelist){
    		saishi.add(matchArrange.getMatchName());
    	}
    	ServletActionContext.getRequest().setAttribute("hh", saishi);
    	return SUCCESS;
    }


    public String getMatch()
    {
        if(date == null) {
        	date = getdate();
        }
        
		matchArrangelist = matchArrangeService.getMatchArrangeByDate(date, LotteryType.竞彩足球);
		
		for( MatchArrange matchArrange:  matchArrangelist){
    		saishi.add(matchArrange.getMatchName());
    	}
    	
		getRequest().setAttribute("hh", saishi);
		//ServletActionContext.getRequest().setAttribute("hh", saishi);
    	
    	return SUCCESS;
    }
    public String getMatchList()throws JSONException 
    { 
    	matchArrangelist = matchArrangeService.getCurrentMatchArrangeForJCZQ();
 	    JSONArray resultArray = new JSONArray();
    	JSONObject jsonObject = new JSONObject();
    	for (int i = 0; i < matchArrangelist.size(); i++)
    	{  
    		jsonObject = this.getDomainJSON(matchArrangelist.get(i)); 
    		resultArray.add(jsonObject);
    	}
    	
    	setJsonString(resultArray.toString());
    	return AJAXJSON;
    }
    
    @SuppressWarnings("unused")
	private JSONObject getDomainJSON(MatchArrange match) throws JSONException 
    { 
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	JSONObject jsonObject = new JSONObject(); 
    	jsonObject.put("matchNo", match.getBoutIndex());
    	jsonObject.put("matchName", match.getMatchName());
    	jsonObject.put("homeTeam", match.getHomeTeam());
    	jsonObject.put("rang", match.getConcede());
    	jsonObject.put("awaryTeam", match.getAwaryTeam()); 
    	jsonObject.put("matchTime", df.format(match.getMatchTime().getTime()));    	
    	jsonObject.put("saleDate",(new SimpleDateFormat( "yyyy-MM-dd")).format(match.getSaleDate().getTime()));
    	jsonObject.put("remainTime",String.valueOf((match.getStopSaleTime().getTimeInMillis() 
                - System.currentTimeMillis()) / 1000));
    	jsonObject.put("sop", match.getSop());
    	jsonObject.put("pop", match.getPop());
    	jsonObject.put("fop", match.getFop());
    	jsonObject.put("stzb", match.getStzb());
    	jsonObject.put("ptzb", match.getPtzb());
    	jsonObject.put("ftzb", match.getFtzb());
    	jsonObject.put("currentRatios", match.getCurrentRatios());
    	return jsonObject;
    }
    
    public String getdate() 
    {
		String str;
		Calendar date = Calendar.getInstance();
		str = "" + date.get(Calendar.YEAR);
		
		int ii = date.get(Calendar.MONTH) + 1;
		if (ii < 10) {
			str = str + "0" + ii;
		}
		else {
			str = str + ii;
		}
		
		int m = date.get(Calendar.DAY_OF_MONTH);
		if (m < 10) {
			str = str + "0" + m;
		}
		else {
			str = str + m;
		}
		return str;
	}
    
	public MatchArrangeService getMatchArrangeService() {
		return matchArrangeService;
	}

	public void setMatchArrangeService(MatchArrangeService matchArrangeService) {
		this.matchArrangeService = matchArrangeService;
	}

	public List<MatchArrange> getMatchArrangelist() {
		return matchArrangelist;
	}

	public void setMatchArrangelist(List<MatchArrange> matchArrangelist) {
		this.matchArrangelist = matchArrangelist;
	}

	public Set getSaishi() {
		return saishi;
	}

	public void setSaishi(Set saishi) { 
		this.saishi = saishi;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
}