package com.xsc.lottery.admin.action.football;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.util.FetchlDataUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.Admin4cjq")
public class Admin4cjq extends AdminBaseAction
{
    @Autowired
    private MatchArrangeService matchArrangeService;

    @Autowired
    private LotteryTermService lotteryTermService;
    private String termNo;
    // name@time@homeTeam@awayTeam^name@time@homeTeam@awayTeam...
    private String allMatches;
    private List<LotteryTerm> currentTermList;
    private List<MatchArrange> matchArrangelist = new ArrayList<MatchArrange>();

    public String index()
    {
        currentTermList = lotteryTermService
                .getCurrentTermArray(LotteryType.四场进球);
        getmatchList();
        return SUCCESS;
    }

    public String getmatchList()
    {
        LotteryTerm term = lotteryTermService.getByTypeAndTermNo(termNo,
                LotteryType.四场进球);
        matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term);
        return SUCCESS;
    }

    public String getCurrentmatchList()
    {
        LotteryTerm term = lotteryTermService.getCurrentTerm(LotteryType.四场进球);
        matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term);
        return SUCCESS;
    }

    public String kj()
    {
        currentTermList = lotteryTermService
                .getCurrentTermArray(LotteryType.四场进球);
        return SUCCESS;
    }

    public String saveMathces()
    {
        LotteryTerm term = lotteryTermService.getByTypeAndTermNo(termNo,
                LotteryType.四场进球);
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
                    newmatch.setTerm(term);
                    newmatch.setLotteryType(LotteryType.四场进球);
                    list.add(newmatch);
                }
                catch (Exception e) {
                    ;
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
                                    LotteryType.四场进球);
                    siglematch.setMatchName(b[1]);
                    siglematch.setMatchTime(this.stringToCalendar(b[2]));
                    siglematch.setHomeTeam(b[3]);
                    siglematch.setAwaryTeam(b[4]);
                    matchArrangeService.save(siglematch);

                }
                catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }

        currentTermList = lotteryTermService
                .getCurrentTermArray(LotteryType.四场进球);
        return SUCCESS;
    }

    public String fetchMatchList() throws Exception
    {
        if (termNo == null) {
            return NONE;
        }

        String term_no = termNo;
        if (term_no.indexOf("20") != -1) {
        	term_no = term_no.substring(2);
        }

        String fetchURl = "http://zc.trade.500wan.com/jq4/index.php?expect="
                + term_no;
        String resultStr = null;
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> result = null;
        try {
            result = fetchl.parserHtml(fetchURl, HTML.Tag.TR).iterator();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String previousStr;
        while (result.hasNext()) {
            resultStr = result.next();
            if (resultStr.trim().indexOf("投注比率") > -1) {
                for (int j = 0; j < 4; j++) {
                    resultStr = result.next();
                }
                for (int i = 0; i < 4; i++) {
                    MatchArrange match = new MatchArrange();
                    match.setBoutIndex(String.valueOf(i+1));
                    resultStr = result.next();
                    match.setMatchName(result.next());
                    match.setMatchTime(stringToCalendar(Calendar.getInstance().get(Calendar.YEAR)+"-"+result.next()));
                    
                    resultStr = result.next();
                    
                    while (resultStr.indexOf("主") == -1 && result.hasNext()) {
                        resultStr = result.next();
                    }
                    resultStr = result.next();

                    match.setHomeTeam(resultStr);

                    for (int j = 0; j < 5; j++) {
                        resultStr = result.next();
                    }
                    match.setSop(result.next());
                    match.setPop(result.next());
                    match.setFop(result.next());

                    while (resultStr.indexOf("客") == -1) {
                        resultStr = result.next();
                    }
                    resultStr = result.next();
                    match.setAwaryTeam(resultStr);

//                    for (int j = 0; j < 4; j++) {
//                        resultStr = result.next();
//                    }
//                    matchArrangelist.add(match);
                    resultStr = result.next();
                    while(resultStr.trim().indexOf("%")==-1)
                    {
                    	previousStr=resultStr;
                    	resultStr=result.next();
                    }
                    
                    resultStr=result.next();
                    resultStr=result.next();
                    resultStr=result.next();
                    matchArrangelist.add(i,match);
                }

            }
        }
        currentTermList = lotteryTermService
                .getCurrentTermArray(LotteryType.四场进球);
        if (matchArrangelist == null) {
            index();
        }
        return SUCCESS;
    }

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
