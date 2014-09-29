package com.xsc.lottery.admin.action.football;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.util.NetWorkUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.football14")
public class AdminFootball14 extends AdminBaseAction
{
    @Autowired
    private MatchArrangeService matchArrangeService;

    @Autowired
    private LotteryTermService lotteryTermService;
    private String termNo;
    // name@time@homeTeam@awayTeam^name@time@homeTeam@awayTeam...
    private String allMatches;
    private List<LotteryTerm> currentTermList;
    private List<MatchArrange> matchArrangelist;

    public String index()
    {

        currentTermList = lotteryTermService
                .getCurrentTermArray(LotteryType.足彩14场);
        getmatchList();
        return SUCCESS;
    }

    public String getmatchList()
    {
        LotteryTerm term = lotteryTermService.getByTypeAndTermNo(termNo,
                LotteryType.足彩14场);
        matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term);
        return SUCCESS;
    }

    public String getCurrentmatchList()
    {
        LotteryTerm term = lotteryTermService.getCurrentTerm(LotteryType.足彩14场);
        matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term);
        return SUCCESS;
    }

    public String saveMathces()
    {
    	System.out.println(allMatches);
        LotteryTerm term = lotteryTermService.getByTypeAndTermNo(termNo,
                LotteryType.足彩14场);
        // LotteryTerm termR9 = lotteryTermService.getByTypeAndTermNo(termNo,
        // LotteryType.足彩任9);
        String[] matches = allMatches.split("\\^");
        List<MatchArrange> list = new ArrayList<MatchArrange>();
        matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term);
        if (matchArrangelist.size() == 0) {
            for (String match : matches) {
                try {
                    String[] b = match.split("\\|");
                    MatchArrange newmatch = new MatchArrange();
                    newmatch.setBoutIndex(b[0]);
                    newmatch.setMatchName(b[1]);
                    newmatch.setMatchTime(this.stringToCalendar(b[2]));
                    newmatch.setHomeTeam(b[3]);
                    newmatch.setAwaryTeam(b[4]);
                    /*
        			String sop = b[5];
        			String pop = b[6];
        			String fop = b[7];
        			newmatch.setSop(sop);
        			newmatch.setPop(pop);
        			newmatch.setFop(fop);
        			
        			NumberFormat nf = NumberFormat.getNumberInstance();
                	nf.setMaximumFractionDigits(0);
        			Double fm = Double.parseDouble(sop) * Double.parseDouble(pop) + Double.parseDouble(sop) * Double.parseDouble(fop) + Double.parseDouble(pop) * Double.parseDouble(fop);
        			
        			String stzb = nf.format(Double.parseDouble(pop)*Double.parseDouble(fop)*100/fm);
        			String ptzb = nf.format(Double.parseDouble(sop)*Double.parseDouble(fop)*100/fm);
        			String ftzb = nf.format(Double.parseDouble(pop)*Double.parseDouble(sop)*100/fm);
        			newmatch.setStzb(stzb);
        			newmatch.setPtzb(ptzb);
        			newmatch.setFtzb(ftzb);
                    */
                    newmatch.setTerm(term);
                    newmatch.setLotteryType(LotteryType.足彩14场);
                    list.add(newmatch);
                }
                catch (Exception e) {
                    logger.info("getduizhen",e);
                }
            }
            matchArrangeService.saveAllMatches(list);
        }
        else {
            for (String match : matches) {
                try {
                    String[] b = match.split("\\|");
                    MatchArrange siglematch = matchArrangeService
                            .findLastMatchByTermAndIndex(b[0], term,
                                    LotteryType.足彩14场);
                    if(siglematch != null)
                    {
                    	matchArrangeService.delete(siglematch);
                    	
                    }
                    
                    siglematch = new MatchArrange();
                	siglematch.setBoutIndex(b[0]);
                	siglematch.setTerm(term);
                	siglematch.setLotteryType(LotteryType.足彩14场);
                	
                    siglematch.setMatchName(b[1]);
                    siglematch.setMatchTime(this.stringToCalendar(b[2]));
                    siglematch.setHomeTeam(b[3]);
                    siglematch.setAwaryTeam(b[4]);
/*
        			String sop = b[5];
        			String pop = b[6];
        			String fop = b[7];
        			siglematch.setSop(sop);
        			siglematch.setPop(pop);
        			siglematch.setFop(fop);
        			
        			
        			NumberFormat nf = NumberFormat.getNumberInstance();
                	nf.setMaximumFractionDigits(0);
        			Double fm = Double.parseDouble(sop) * Double.parseDouble(pop) + Double.parseDouble(sop) * Double.parseDouble(fop) + Double.parseDouble(pop) * Double.parseDouble(fop);
        			
        			String stzb = nf.format(Double.parseDouble(pop)*Double.parseDouble(fop)*100/fm);
        			String ptzb = nf.format(Double.parseDouble(sop)*Double.parseDouble(fop)*100/fm);
        			String ftzb = nf.format(Double.parseDouble(pop)*Double.parseDouble(sop)*100/fm);
        			siglematch.setStzb(stzb);
        			siglematch.setPtzb(ptzb);
        			siglematch.setFtzb(ftzb);
        			*/

                    matchArrangeService.save(siglematch);

                }
                catch (Exception e) {
                    // e.printStackTrace();
                	logger.info("getduizhen2",e);
                }
            }
        }

        currentTermList = lotteryTermService
                .getCurrentTermArray(LotteryType.足彩14场);
        return SUCCESS;
    }
    
    
    public static void main(String[] args) throws Exception {
		
    	AdminFootball14 adminFootball14 = new AdminFootball14();
    	adminFootball14.setTermNo("2013163");
    	adminFootball14.fetchMatchList();
	}
    
    /**从大赢家获取对阵*/
    public String fetchMatchList()throws Exception {

        matchArrangelist = new ArrayList<MatchArrange>();
        if (termNo == "") {
            return NONE;
        }
        String urlString = "http://dc.zs310.com/dz/zcsfc/"
                + termNo.substring(2) + ".html";
        String result = NetWorkUtil.getHttpUrl(urlString, "", "");
        System.out.println(result);
        
        JSONArray jsonArray = JSONArray.fromObject(result);
		JSONObject jsonObject;
		for ( int i = 0 ; i<jsonArray.size(); i++){
//			{ "rs" : "" , "score" : "" , "matchkey" : "12120_1" , "issue" : "12120" , "st" : "0" , "id" : 1 , "dt" : "2012-09-21 01
//				:00:00" , "name" : "欧洲联赛" , "home" : "年轻人" , "awary" : "利物浦" , "oh" : "2.71" , "od" : "3.17" , "oa" : "2.50"}, 
			jsonObject = jsonArray.getJSONObject(i);
			MatchArrange match = new MatchArrange();
			match.setBoutIndex(jsonObject.getString("id"));
			match.setMatchName(jsonObject.getString("name"));
			match.setMatchTime(stringToCalendar(jsonObject.getString("dt")));
			match.setHomeTeam(jsonObject.getString("home"));
			match.setAwaryTeam(jsonObject.getString("awary"));
			
			String sop = jsonObject.getString("oh");
			String pop = jsonObject.getString("od");
			String fop = jsonObject.getString("oa");
			match.setSop(sop);
			match.setPop(pop);
			match.setFop(fop);

            matchArrangelist.add(i, match);
		}
		currentTermList = lotteryTermService
        .getCurrentTermArray(LotteryType.足彩14场);
        if (matchArrangelist == null) {
            index();
        }
        return SUCCESS;
    }
    
    /**从500万获取对阵*/
//    public String fetchMatchList() throws Exception
//    {
//        matchArrangelist = new ArrayList<MatchArrange>();
//        if (termNo == "") {
//            return NONE;
//        }
//        String fetchURl = "http://zc.trade.500wan.com/sfc/project_fq_fsyt.php?lotid=1&playid=1&expect="
//                + termNo.substring(2);
//        FetchlDataUtil fetchl = new FetchlDataUtil();
//        Iterator<String> result = fetchl.parserHtml(fetchURl, HTML.Tag.TR)
//                .iterator();
//        String resultStr;
//        while (result.hasNext()) {
//            resultStr = result.next();
//            if (resultStr.trim().indexOf("投注比率") > -1) {
//                for (int i = 0; i < 3; i++) {
//                    resultStr = result.next();
//                }
//                for (int i = 0; i < 14; i++) {
//                    MatchArrange match = new MatchArrange();
//                    match.setBoutIndex(result.next());
//                    match.setMatchName(result.next());
//                    match.setMatchTime(stringToCalendar(Calendar.getInstance()
//                            .get(Calendar.YEAR)
//                            + "-" + result.next()));
//
//                    resultStr = result.next();
//                    if (resultStr.trim().indexOf("[") > -1) {
//                        resultStr = result.next();
//                    }
//                    match.setHomeTeam(resultStr);
//                    resultStr = result.next();
//                    match.setAwaryTeam(result.next());
//                    resultStr = result.next();
//                    if (resultStr.trim().indexOf("]") > -1) {
//                        System.out.println(resultStr);
//                        resultStr = result.next();
//                    }
//                    /*
//                     * for (int j = 0; j < 19; j++) { resultStr = result.next(); }
//                     */
//
//                    for (int j = 0; j < 4; j++) {
//                        resultStr = result.next();
//                    }
//
//                    match.setSop(result.next());
//                    match.setPop(result.next());
//                    match.setFop(result.next());
//                    resultStr = result.next();
//                    if (Double.parseDouble(resultStr) == 0)
//                        for (int j = 0; j < 7; j++) {
//                            resultStr = result.next();
//                        }
//                    else {
//                        for (int j = 0; j < 8; j++) {
//                            resultStr = result.next();
//                        }
//                    }
//                    match.setStzb(result.next());
//                    match.setPtzb(result.next());
//                    match.setFtzb(result.next());
//                    matchArrangelist.add(i, match);
//                }
//            }
//        }
//        currentTermList = lotteryTermService
//                .getCurrentTermArray(LotteryType.足彩14场);
//        /*
//         * for(int m=0;m<matchArrangelist.size();m++) {
//         * System.out.println("第"+matchArrangelist.get(m).getBoutIndex());
//         * System.out.println("欧赔胜："+matchArrangelist.get(m).getSop());
//         * System.out.println("欧赔平："+matchArrangelist.get(m).getPop());
//         * System.out.println("欧赔负："+matchArrangelist.get(m).getFop());
//         * System.out.println("欧赔投注比胜："+matchArrangelist.get(m).getStzb());
//         * System.out.println("欧赔投注比平："+matchArrangelist.get(m).getPtzb());
//         * System.out.println("欧赔投注比负："+matchArrangelist.get(m).getFtzb()); }
//         */
//        if (matchArrangelist == null) {
//            index();
//        }
//        return SUCCESS;
//    }

    protected Calendar stringToCalendar(String dt)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(dt);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar;
    }

    public String getAllMatches()
    {
        return allMatches;
    }

    public void setAllMatches(String allMatches)
    {
        this.allMatches = allMatches;
    }

    public String getTermNo()
    {
        return termNo;
    }

    public void setTermNo(String termNo)
    {
        this.termNo = termNo;
    }

    public List<LotteryTerm> getCurrentTermList()
    {
        return currentTermList;
    }

    public List<MatchArrange> getMatchArrangelist()
    {
        return matchArrangelist;
    }

    public void setMatchArrangelist(List<MatchArrange> matchArrangelist)
    {
        this.matchArrangelist = matchArrangelist;
    }
}
