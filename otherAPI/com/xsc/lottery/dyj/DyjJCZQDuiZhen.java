package com.xsc.lottery.dyj;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.task.jczq.JczqTaskExcutor;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.NetWorkUtil;

import edu.emory.mathcs.backport.java.util.Collections;

@Component
public class DyjJCZQDuiZhen implements ApplicationListener{
	private static String urlString = "http://dc.zs310.com/dz/jczq/";
	//是否世界杯
	private static String isWordCup = Configuration.getInstance().getValue("is.world.cup");
	//正常比开赛时间提前时间
	private static String beforeTime = Configuration.getInstance().getValue("jczq.before");
	//世界杯比开赛时间提前时间
	private static String worldCupBeforeTime = Configuration.getInstance().getValue("world.cup.before");
	
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MatchArrangeService  matchArrangeService; 
	
    @Autowired
	private JczqTaskExcutor jczqTaskExcutor;
    
	@Autowired
	private LotteryTermService lotteryTermService;
	
    protected boolean start = false;
    
    public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("一彩票获取竞彩足球对阵服务启动...");
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createJCZQTask());
            start = true;
        }
    }
	
    private Runnable createJCZQTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                    	Thread.sleep(100000);
                    	getJCZQDZ();
                    	Thread.sleep(500000);
                    	
                    }
                    catch (Exception e) {
                        String description = "获取竞彩足球对阵异常,请查看日志";
                        logger.info(description);
                    }
                }
            }
        };
    }
    
	/** 获取竞彩足球对阵*/
	private String getresult(String date) {
		if (date.equals("")) {
			Date d = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.format(d);
		}
		logger.info(urlString+date+".html");
		String result = NetWorkUtil.getHttpUrl(urlString + date + ".html", "", "");
		return result;
	}
	//世界杯  时间跨度较长  ps 抓取赛果的部分往前5天 目前1天
	public void getJCZQDZ() {
		logger.info("开始获取竞彩足球对阵............");
		List<String> dates = new ArrayList<String>();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format.format(date); //今天
		dates.add(date1);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date cal = calendar.getTime();
		String date2 = format.format(cal); //昨天
		dates.add(date2);
		
		calendar.add(Calendar.DATE, -1);
		Date cal1 = calendar.getTime();
		String date3 = format.format(cal1); //前天
		dates.add(date3);
		
		calendar.add(Calendar.DATE, -1);
		Date cal2 = calendar.getTime();
		String date4 = format.format(cal2); //大前天
		dates.add(date4);
		
		Calendar calendar1 = Calendar.getInstance();
		for (int i = 0; i < 17; i++)
		{
			
			calendar1.add(Calendar.DATE, 1);
			Date cal_add = calendar1.getTime();
			dates.add(format.format(cal_add));
		}
		Collections.sort(dates);

		try {
			for (String dateStr : dates)
			{
				getJCZQDZBYDate(dateStr); //  获取某个日期的对阵
				Thread.sleep(50000);
			}
		
		} catch (Exception e) {
           logger.info("getdzfail---------",e);
        }
		
		System.out.println("获取对阵结束......................");
	}
	
	public void getJCZQDZBYDate(String date) {

		String result = getresult(date);
		JSONArray jsonArray = JSONArray.fromObject(result);
		JSONObject jsonObject;
		for ( int i = 0 ; i<jsonArray.size(); i++){
	    	String wholeScore = "";
	    	String halfScore = "";
	    	Integer goals = 0;
	    	String rfspfdg = "";
	    	String rfspfduog = "";
	    	String spfdg = "";
	    	String spfduog = "";
	    	String jqsdg = "";
	    	String jqsduog = "";
	    	String bqcdg = "";
	    	String bqcduog = "";
	    	String cbfdg = "";
	    	String cbfduog = "";
	    	String currentRatios = "";
	    	int rangqiu = 0;
	    	String rangfensaiguo = "";
 	    	String quanchangsaiguo = "";
	    	String baichangsaiguo = "";
			jsonObject = jsonArray.getJSONObject(i);
			MatchArrange match = new MatchArrange();
			match.setBoutIndex(jsonObject.getString("matchkey").substring(2, jsonObject.getString("matchkey").length()));
			match.setMatchName(jsonObject.getString("fullname"));
			match.setMatchTime(DateUtil.parse(jsonObject.getString("dt"), "yy-MM-dd HH:mm:ss"));
			
			String sop = jsonObject.getString("oh");
			String pop = jsonObject.getString("od");
			String fop = jsonObject.getString("oa");
			match.setSop(sop);
			match.setPop(pop);
			match.setFop(fop);
			
			NumberFormat nf = NumberFormat.getNumberInstance();
        	nf.setMaximumFractionDigits(2);
        	
        	
        	if(sop.isEmpty()||pop.isEmpty()||fop.isEmpty())
        	{
        		logger.info("empty soppopfop");
        	} else
        	{
			Double fm = Double.parseDouble(sop) * Double.parseDouble(pop) + Double.parseDouble(sop) * Double.parseDouble(fop) + Double.parseDouble(pop) * Double.parseDouble(fop);
			
			String stzb = nf.format(Double.parseDouble(pop)*Double.parseDouble(fop)/fm);
			String ptzb = nf.format(Double.parseDouble(sop)*Double.parseDouble(fop)/fm);
			String ftzb = nf.format(Double.parseDouble(pop)*Double.parseDouble(sop)/fm);
			nf.setMaximumFractionDigits(1);
            match.setStzb(nf.format(Double.valueOf(stzb) * 100));
            match.setPtzb(nf.format(Double.valueOf(ptzb) * 100));
            match.setFtzb(nf.format(Double.valueOf(ftzb) * 100));
        	}
			
			if (jsonObject.getString("let").equals("0")) {
				match.setConcede("");
			}else {
				match.setConcede(jsonObject.getString("let"));
			}
			
			//问题：全场比分  赛果区别  matchResult这个字段为空
			wholeScore = jsonObject.getString("score");
			halfScore = jsonObject.getString("score_half");
			if (!wholeScore.equals("") && !halfScore.equals("")) {
				match.setWholeScore(wholeScore);
				goals = Integer.parseInt(wholeScore.split(":")[0]) + Integer.parseInt(wholeScore.split(":")[1]);
				match.setGoals(goals);
				match.setHalfScore(halfScore);
				if (jsonObject.getString("let").equals("") || jsonObject.getString("let").equals("0") || jsonObject.getString("let").equals(null)) {
					rangqiu = 0;
				}else {
					rangqiu = Integer.parseInt(jsonObject.getString("let"));
				}
				if (Integer.parseInt(wholeScore.split(":")[0]) + rangqiu > Integer.parseInt(wholeScore.split(":")[1])) {
					rangfensaiguo = "3";
				}
				if (Integer.parseInt(wholeScore.split(":")[0]) + rangqiu == Integer.parseInt(wholeScore.split(":")[1])) {
					rangfensaiguo = "1";
				}
				if (Integer.parseInt(wholeScore.split(":")[0]) + rangqiu < Integer.parseInt(wholeScore.split(":")[1])) {
					rangfensaiguo = "0";
				}
				match.setRfsfpResult(rangfensaiguo);
				if (Integer.parseInt(wholeScore.split(":")[0]) > Integer.parseInt(wholeScore.split(":")[1])) {
					quanchangsaiguo = "3";
				}
				if (Integer.parseInt(wholeScore.split(":")[0]) == Integer.parseInt(wholeScore.split(":")[1])) {
					quanchangsaiguo = "1";
				}
				if (Integer.parseInt(wholeScore.split(":")[0]) < Integer.parseInt(wholeScore.split(":")[1])) {
					quanchangsaiguo = "0";
				}
				match.setMatchResult(quanchangsaiguo);
				
				if (Integer.parseInt(halfScore.split(":")[0]) > Integer.parseInt(halfScore.split(":")[1])) {
					baichangsaiguo = "3";
				}
				if (Integer.parseInt(halfScore.split(":")[0]) == Integer.parseInt(halfScore.split(":")[1])) {
					baichangsaiguo = "1";
				}
				if (Integer.parseInt(halfScore.split(":")[0]) < Integer.parseInt(halfScore.split(":")[1])) {
					baichangsaiguo = "0";
				}
				match.setHalfResult(baichangsaiguo + "-" + quanchangsaiguo);
				
			}
			match.setHomeTeam(jsonObject.getString("home"));
			match.setAwaryTeam(jsonObject.getString("awary"));
			//问题：状态对应不上 
			String status = jsonObject.getString("st");
			if (status.equals("0")) {
				
			}
		    //足球格式： 胜平负单关;胜平负多关 | 进球数单关;进球数多关 | 猜比分单关;猜比分多关 | 半全场单关;半全场多关 (胜平负单关内部以英文逗号","隔开)
			//例如：1,2,3;2,3,4|3,4,5;5,6,7|
			//浮动单关  固定多关
			
			//让球胜平负单关
			if (jsonObject.getString("fu_rqspf_sp3").equals("") || jsonObject.getString("fu_rqspf_sp3").equals("0")) {
				rfspfdg += "0000" + ",";
			}else {
				rfspfdg += jsonObject.getString("fu_rqspf_sp3") + ",";
			}
			if (jsonObject.getString("fu_rqspf_sp1").equals("") || jsonObject.getString("fu_rqspf_sp1").equals("0")) {
				rfspfdg += "0000" + ",";
			}else {
				rfspfdg += jsonObject.getString("fu_rqspf_sp1") + ",";
			}
			if (jsonObject.getString("fu_rqspf_sp0").equals("") || jsonObject.getString("fu_rqspf_sp0").equals("0")) {
				rfspfdg += "0000";
			}else {
				rfspfdg += jsonObject.getString("fu_rqspf_sp0");
			}
			
			//让球胜平负多关
			if (jsonObject.getString("rqspf_sp3").equals("") || jsonObject.getString("rqspf_sp3").equals("0")) {
				rfspfduog += "0000" + ",";
			}else {
				rfspfduog += jsonObject.getString("rqspf_sp3") + ",";
			}
			if (jsonObject.getString("rqspf_sp1").equals("") || jsonObject.getString("rqspf_sp1").equals("0")) {
				rfspfduog += "0000" + ",";
			}else {
				rfspfduog += jsonObject.getString("rqspf_sp1") + ",";
			}
			if (jsonObject.getString("rqspf_sp0").equals("") || jsonObject.getString("rqspf_sp0").equals("0")) {
				rfspfduog += "0000";
			}else {
				rfspfduog += jsonObject.getString("rqspf_sp0");
			}
			
			//胜平负单关
			if (jsonObject.getString("fu_spf_sp3").equals("") || jsonObject.getString("fu_spf_sp3").equals("0")) {
				spfdg += "0000" + ",";
			}else {
				spfdg += jsonObject.getString("fu_spf_sp3") + ",";
			}
			if (jsonObject.getString("fu_spf_sp1").equals("") || jsonObject.getString("fu_spf_sp1").equals("0")) {
				spfdg += "0000" + ",";
			}else {
				spfdg += jsonObject.getString("fu_spf_sp1") + ",";
			}
			if (jsonObject.getString("fu_spf_sp0").equals("") || jsonObject.getString("fu_spf_sp0").equals("0")) {
				spfdg += "0000";
			}else {
				spfdg += jsonObject.getString("fu_spf_sp0");
			}
			
			//胜平负多关
			if (jsonObject.getString("spf_sp3").equals("") || jsonObject.getString("spf_sp3").equals("0")) {
				spfduog += "0000" + ",";
			}else {
				spfduog += jsonObject.getString("spf_sp3") + ",";
			}
			if (jsonObject.getString("spf_sp1").equals("") || jsonObject.getString("spf_sp1").equals("0")) {
				spfduog += "0000" + ",";
			}else {
				spfduog += jsonObject.getString("spf_sp1") + ",";
			}
			if (jsonObject.getString("spf_sp0").equals("") || jsonObject.getString("spf_sp0").equals("0")) {
				spfduog += "0000";
			}else {
				spfduog += jsonObject.getString("spf_sp0");
			}
			
			//进球数多关
			if (jsonObject.getString("jqs_sp0").equals("") || jsonObject.getString("jqs_sp0").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp0") + ",";
			}
			if (jsonObject.getString("jqs_sp1").equals("") || jsonObject.getString("jqs_sp1").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp1") + ",";
			}
			if (jsonObject.getString("jqs_sp2").equals("") || jsonObject.getString("jqs_sp2").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp2") + ",";
			}
			if (jsonObject.getString("jqs_sp3").equals("") || jsonObject.getString("jqs_sp3").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp3") + ",";
			}
			if (jsonObject.getString("jqs_sp4").equals("") || jsonObject.getString("jqs_sp4").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp4") + ",";
			}
			if (jsonObject.getString("jqs_sp5").equals("") || jsonObject.getString("jqs_sp5").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp5") + ",";
			}
			if (jsonObject.getString("jqs_sp6").equals("") || jsonObject.getString("jqs_sp6").equals("0")) {
				jqsduog += "0000" + ",";
			}else {
				jqsduog += jsonObject.getString("jqs_sp6") + ",";
			}
			if (jsonObject.getString("jqs_sp7").equals("") || jsonObject.getString("jqs_sp7").equals("0")) {
				jqsduog += "0000";
			}else {
				jqsduog += jsonObject.getString("jqs_sp7");
			}
			
			//进球数单关
			if (jsonObject.getString("fu_jqs_sp0").equals("") || jsonObject.getString("fu_jqs_sp0").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp0") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp1").equals("") || jsonObject.getString("fu_jqs_sp1").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp1") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp2").equals("") || jsonObject.getString("fu_jqs_sp2").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp2") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp3").equals("") || jsonObject.getString("fu_jqs_sp3").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp3") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp4").equals("") || jsonObject.getString("fu_jqs_sp4").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp4") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp5").equals("") || jsonObject.getString("fu_jqs_sp5").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp5") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp6").equals("") || jsonObject.getString("fu_jqs_sp6").equals("0")) {
				jqsdg += "0000" + ",";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp6") + ",";
			}
			if (jsonObject.getString("fu_jqs_sp7").equals("") || jsonObject.getString("fu_jqs_sp7").equals("0")) {
				jqsdg += "0000";
			}else {
				jqsdg += jsonObject.getString("fu_jqs_sp7");
			}
			
			//fu_bqc_sp00 负负    0负 1平 3胜
			//半全场单关
			if (jsonObject.getString("fu_bqc_sp33").equals("") || jsonObject.getString("fu_bqc_sp33").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp33") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp31").equals("") || jsonObject.getString("fu_bqc_sp31").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp31") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp30").equals("") || jsonObject.getString("fu_bqc_sp30").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp30") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp13").equals("") || jsonObject.getString("fu_bqc_sp13").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp13") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp11").equals("") || jsonObject.getString("fu_bqc_sp11").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp11") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp10").equals("") || jsonObject.getString("fu_bqc_sp10").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp10") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp03").equals("") || jsonObject.getString("fu_bqc_sp03").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp03") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp01").equals("") || jsonObject.getString("fu_bqc_sp01").equals("0")) {
				bqcdg += "0000" + ",";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp01") + ",";
			}
			if (jsonObject.getString("fu_bqc_sp00").equals("") || jsonObject.getString("fu_bqc_sp00").equals("0")) {
				bqcdg += "0000";
			}else {
				bqcdg += jsonObject.getString("fu_bqc_sp00");
			}
			
			
			
			//半全场多关
			if (jsonObject.getString("bqc_sp33").equals("") || jsonObject.getString("bqc_sp33").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp33") + ",";
			}
			if (jsonObject.getString("bqc_sp31").equals("") || jsonObject.getString("bqc_sp31").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp31") + ",";
			}
			if (jsonObject.getString("bqc_sp30").equals("") || jsonObject.getString("bqc_sp30").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp30") + ",";
			}
			if (jsonObject.getString("bqc_sp13").equals("") || jsonObject.getString("bqc_sp13").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp13") + ",";
			}
			if (jsonObject.getString("bqc_sp11").equals("") || jsonObject.getString("bqc_sp11").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp11") + ",";
			}
			if (jsonObject.getString("bqc_sp10").equals("") || jsonObject.getString("bqc_sp10").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp10") + ",";
			}
			if (jsonObject.getString("bqc_sp03").equals("") || jsonObject.getString("bqc_sp03").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp03") + ",";
			}
			if (jsonObject.getString("bqc_sp01").equals("") || jsonObject.getString("bqc_sp01").equals("0")) {
				bqcduog += "0000" + ",";
			}else {
				bqcduog += jsonObject.getString("bqc_sp01") + ",";
			}
			if (jsonObject.getString("bqc_sp00").equals("") || jsonObject.getString("bqc_sp00").equals("0")) {
				bqcduog += "0000";
			}else {
				bqcduog += jsonObject.getString("bqc_sp00");
			}

			
			//猜比分单关
			if (jsonObject.getString("bf_sp10").equals("0") || jsonObject.getString("bf_sp10").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp10") + ",";
			}
			if (jsonObject.getString("bf_sp20").equals("0") || jsonObject.getString("bf_sp20").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp20") + ",";
			}
			if (jsonObject.getString("bf_sp21").equals("0") || jsonObject.getString("bf_sp21").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp21") + ",";
			}
			if (jsonObject.getString("bf_sp30").equals("0") || jsonObject.getString("bf_sp30").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp30") + ",";
			}
			if (jsonObject.getString("bf_sp31").equals("0") || jsonObject.getString("bf_sp31").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp31") + ",";
			}
			if (jsonObject.getString("bf_sp32").equals("0") || jsonObject.getString("bf_sp32").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp32") + ",";
			}
			if (jsonObject.getString("bf_sp40").equals("0") || jsonObject.getString("bf_sp40").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp40") + ",";
			}
			if (jsonObject.getString("bf_sp41").equals("0") || jsonObject.getString("bf_sp41").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp41") + ",";
			}
			if (jsonObject.getString("bf_sp42").equals("0") || jsonObject.getString("bf_sp42").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp42") + ",";
			}
			if (jsonObject.getString("bf_sp50").equals("") || jsonObject.getString("bf_sp50").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp50") + ",";
			}
			if (jsonObject.getString("bf_sp51").equals("0") || jsonObject.getString("bf_sp51").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp51") + ",";
			}
			if (jsonObject.getString("bf_sp52").equals("0") || jsonObject.getString("bf_sp52").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp52") + ",";
			}
			if (jsonObject.getString("bf_sp93").equals("0") || jsonObject.getString("bf_sp93").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp93") + ",";
			}
			if (jsonObject.getString("bf_sp00").equals("0") || jsonObject.getString("bf_sp00").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp00") + ",";
			}
			if (jsonObject.getString("bf_sp11").equals("0") || jsonObject.getString("bf_sp11").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp11") + ",";
			}
			if (jsonObject.getString("bf_sp22").equals("0") || jsonObject.getString("bf_sp22").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp22") + ",";
			}
			if (jsonObject.getString("bf_sp33").equals("0") || jsonObject.getString("bf_sp33").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp33") + ",";
			}
			if (jsonObject.getString("bf_sp91").equals("0") || jsonObject.getString("bf_sp91").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp91") + ",";
			}
			if (jsonObject.getString("bf_sp01").equals("0") || jsonObject.getString("bf_sp01").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp01") + ",";
			}
			if (jsonObject.getString("bf_sp02").equals("0") || jsonObject.getString("bf_sp02").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp02") + ",";
			}			
			if (jsonObject.getString("bf_sp12").equals("0") || jsonObject.getString("bf_sp12").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp12") + ",";
			}			
			if (jsonObject.getString("bf_sp03").equals("0") || jsonObject.getString("bf_sp03").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp03") + ",";
			}			
			if (jsonObject.getString("bf_sp13").equals("0") || jsonObject.getString("bf_sp13").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp13") + ",";
			}			
			if (jsonObject.getString("bf_sp23").equals("0") || jsonObject.getString("bf_sp23").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp23") + ",";
			}			
			if (jsonObject.getString("bf_sp04").equals("0") || jsonObject.getString("bf_sp04").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp04") + ",";
			}			
			if (jsonObject.getString("bf_sp14").equals("0") || jsonObject.getString("bf_sp14").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp14") + ",";
			}			
			if (jsonObject.getString("bf_sp24").equals("0") || jsonObject.getString("bf_sp24").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp24") + ",";
			}			
			if (jsonObject.getString("bf_sp05").equals("0") || jsonObject.getString("bf_sp05").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp05") + ",";
			}			
			if (jsonObject.getString("bf_sp15").equals("0") || jsonObject.getString("bf_sp15").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp15") + ",";
			}			
			if (jsonObject.getString("bf_sp25").equals("0") || jsonObject.getString("bf_sp25").equals("")) {
				cbfdg += "0000" + ",";
			}else {
				cbfdg += jsonObject.getString("bf_sp25") + ",";
			}			
			if (jsonObject.getString("bf_sp90").equals("0") || jsonObject.getString("bf_sp90").equals("")) {
				cbfdg += "0000";
			}else {
				cbfdg += jsonObject.getString("bf_sp90");
			}			
			
			
			//猜比分多关
			if (jsonObject.getString("bf_sp10").equals("0") || jsonObject.getString("bf_sp10").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp10") + ",";
			}
			if (jsonObject.getString("bf_sp20").equals("0") || jsonObject.getString("bf_sp20").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp20") + ",";
			}
			if (jsonObject.getString("bf_sp21").equals("0") || jsonObject.getString("bf_sp21").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp21") + ",";
			}
			if (jsonObject.getString("bf_sp30").equals("0") || jsonObject.getString("bf_sp30").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp30") + ",";
			}
			if (jsonObject.getString("bf_sp31").equals("0") || jsonObject.getString("bf_sp31").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp31") + ",";
			}
			if (jsonObject.getString("bf_sp32").equals("0") || jsonObject.getString("bf_sp32").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp32") + ",";
			}
			if (jsonObject.getString("bf_sp40").equals("0") || jsonObject.getString("bf_sp40").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp40") + ",";
			}
			if (jsonObject.getString("bf_sp41").equals("0") || jsonObject.getString("bf_sp41").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp41") + ",";
			}
			if (jsonObject.getString("bf_sp42").equals("0") || jsonObject.getString("bf_sp42").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp42") + ",";
			}
			if (jsonObject.getString("bf_sp50").equals("0") || jsonObject.getString("bf_sp50").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp50") + ",";
			}
			if (jsonObject.getString("bf_sp51").equals("0") || jsonObject.getString("bf_sp51").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp51") + ",";
			}
			if (jsonObject.getString("bf_sp52").equals("0") || jsonObject.getString("bf_sp52").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp52") + ",";
			}
			if (jsonObject.getString("bf_sp93").equals("0") || jsonObject.getString("bf_sp93").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp93") + ",";
			}
			if (jsonObject.getString("bf_sp00").equals("0") || jsonObject.getString("bf_sp00").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp00") + ",";
			}
			if (jsonObject.getString("bf_sp11").equals("0") || jsonObject.getString("bf_sp11").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp11") + ",";
			}
			if (jsonObject.getString("bf_sp22").equals("0") || jsonObject.getString("bf_sp22").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp22") + ",";
			}
			if (jsonObject.getString("bf_sp33").equals("0") || jsonObject.getString("bf_sp33").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp33") + ",";
			}
			if (jsonObject.getString("bf_sp91").equals("0") || jsonObject.getString("bf_sp91").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp91") + ",";
			}
			if (jsonObject.getString("bf_sp01").equals("0") || jsonObject.getString("bf_sp01").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp01") + ",";
			}
			if (jsonObject.getString("bf_sp02").equals("0") || jsonObject.getString("bf_sp02").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp02") + ",";
			}			
			if (jsonObject.getString("bf_sp12").equals("0") || jsonObject.getString("bf_sp12").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp12") + ",";
			}			
			if (jsonObject.getString("bf_sp03").equals("0") || jsonObject.getString("bf_sp03").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp03") + ",";
			}			
			if (jsonObject.getString("bf_sp13").equals("0") || jsonObject.getString("bf_sp13").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp13") + ",";
			}			
			if (jsonObject.getString("bf_sp23").equals("0") || jsonObject.getString("bf_sp23").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp23") + ",";
			}			
			if (jsonObject.getString("bf_sp04").equals("0") || jsonObject.getString("bf_sp04").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp04") + ",";
			}			
			if (jsonObject.getString("bf_sp14").equals("0") || jsonObject.getString("bf_sp14").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp14") + ",";
			}			
			if (jsonObject.getString("bf_sp24").equals("0") || jsonObject.getString("bf_sp24").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp24") + ",";
			}			
			if (jsonObject.getString("bf_sp05").equals("0") || jsonObject.getString("bf_sp05").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp05") + ",";
			}			
			if (jsonObject.getString("bf_sp15").equals("0") || jsonObject.getString("bf_sp15").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp15") + ",";
			}			
			if (jsonObject.getString("bf_sp25").equals("0") || jsonObject.getString("bf_sp25").equals("")) {
				cbfduog += "0000" + ",";
			}else {
				cbfduog += jsonObject.getString("bf_sp25") + ",";
			}			
			if (jsonObject.getString("bf_sp90").equals("0") || jsonObject.getString("bf_sp90").equals("")) {
				cbfduog += "0000";
			}else {
				cbfduog += jsonObject.getString("bf_sp90");
			}
			
			//足球格式：让球胜平负单关;让球胜平负多关| 胜平负单关;胜平负多关 | 进球数单关;进球数多关 | 猜比分单关;猜比分多关 | 半全场单关;半全场多关 (胜平负单关内部以英文逗号","隔开)
			currentRatios = rfspfdg+";"+rfspfduog+"|"+spfdg + ";" + spfduog + "|" + jqsdg + ";" + jqsduog + "|" + cbfdg + ";" + cbfduog + "|" + bqcdg + ";" + bqcduog; 
			match.setCurrentRatios(currentRatios);
			
			match.setSaleDate(Calendar.getInstance());
			Calendar stopTime = Calendar.getInstance();
			stopTime.setTime(match.getMatchTime().getTime());
			stopTime=getStopTime(stopTime);
			match.setStopSaleTime(stopTime);
			Calendar openPrizeTime = Calendar.getInstance();
			openPrizeTime.setTime(match.getMatchTime().getTime());
			openPrizeTime.add(Calendar.DATE, 1);
			match.setOpenPrizeTime(openPrizeTime);
			
			if(match.getStopSaleTime().before(Calendar.getInstance())) {
				match.setStatus(RaceStatus.已停售);
			}
			else {
				match.setStatus(RaceStatus.销售中);
			}
			//浮动奖金除以2得到赔率Sp
			if (!jsonObject.get("fu_bqc_dzjj").equals("") && !jsonObject.get("fu_bqc_dzjj").equals("0")) {
				match.setSpBcsfp(String.valueOf(Float.parseFloat(jsonObject.getString("fu_bqc_dzjj"))/2));
			}
			if (!jsonObject.get("fu_spf_dzjj").equals("") && !jsonObject.get("fu_spf_dzjj").equals("0")) {
				match.setSpSfp(String.valueOf(Float.parseFloat(jsonObject.getString("fu_spf_dzjj"))/2));
			}
			if (!jsonObject.get("fu_rqspf_dzjj").equals("") && !jsonObject.get("fu_rqspf_dzjj").equals("0")) {
				match.setSpRangfenSfp(String.valueOf(Float.parseFloat(jsonObject.getString("fu_rqspf_dzjj"))/2));
			}
			if (!jsonObject.get("fu_jqs_dzjj").equals("") && !jsonObject.get("fu_jqs_dzjj").equals("0")) {
				match.setSpJqs(String.valueOf(Float.parseFloat(jsonObject.getString("fu_jqs_dzjj"))/2));
			}
			String mathNo = match.getBoutIndex();
			MatchArrange m = matchArrangeService.getUniqueMatchByMatchNo(mathNo);
			if (m != null) {
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
						jczqTaskExcutor.addNewMatch(m);
					}
					
					newstopTime.add(Calendar.MINUTE, 4);
					if(newstopTime.before(oldstopTime)){  //time changed, add again
						jczqTaskExcutor.addNewMatch(m);
					}
				}
				else {
					m.setWholeScore(match.getWholeScore());
					m.setGoals(match.getGoals());
					m.setHalfScore(match.getHalfScore());
					m.setMatchResult(match.getMatchResult());
					m.setHalfResult(match.getHalfResult());
					m.setSpBcsfp(match.getSpBcsfp());
					m.setSpSfp(match.getSpSfp());
					m.setSpJqs(match.getSpJqs());
					m.setRfsfpResult(match.getRfsfpResult());
					m.setSpRangfenSfp(match.getSpRangfenSfp());
				}
				
			}
			else {
			
				m = match;
	        	jczqTaskExcutor.addNewMatch(m);
		
			}
			
			LotteryTerm term = lotteryTermService.getCurrentTerm(LotteryType.竞彩足球);
			if(null != m) {
				
	            m.setLotteryType(LotteryType.竞彩足球);
	            m.setTerm(term);
	            matchArrangeService.save(m);
	        } else
	        {
	        	logger.info("notfoundterm------");
	        }
			
		}
		
	}
	
    private static Calendar  getStopTime(Calendar matchtime)
    {
    	Calendar stopTime;
    	Calendar matchtime2=Calendar.getInstance();
    	matchtime2=(Calendar)matchtime.clone();
    	int before;
    	//matchtime2.add(Calendar.MINUTE, -25);
    	if("1".equals(isWordCup))
    	{
    		before = Integer.parseInt(worldCupBeforeTime);
    		matchtime2.add(Calendar.MINUTE, -before);
    		stopTime = matchtime2;
    		return stopTime;
    	}
    	else if("0".equals(isWordCup))
    	{
    		before =  Integer.parseInt(beforeTime);
    		matchtime2.add(Calendar.MINUTE, -before);
    		Calendar c=Calendar.getInstance();
    		Calendar c1=Calendar.getInstance();
    		
    		Calendar c2=Calendar.getInstance();
    		
    		Calendar c3=Calendar.getInstance();
    		Calendar c4=Calendar.getInstance();
    		//当天凌晨 00:00
    		c.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
    				matchtime.get(Calendar.DAY_OF_MONTH), 0, 0);
    		//当天早上9:30
    		c1.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
    				matchtime.get(Calendar.DAY_OF_MONTH), 9, 30);
    		//当天晚上23:25
    		c2.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
    				matchtime.get(Calendar.DAY_OF_MONTH), 23,25);
    		//当天凌晨00:25
    		c3.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
    				matchtime.get(Calendar.DAY_OF_MONTH), 0,25);
    		//当天晚上24:00
    		c4.set(matchtime.get(Calendar.YEAR), matchtime.get(Calendar.MONTH),
    				matchtime.get(Calendar.DAY_OF_MONTH), 24,00);
    		//如果比赛时间为时间段00:00~09:30
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
    		//如果比赛时间为时间段23:25~24:00
    	    if(c2.before(matchtime)&&c4.after(matchtime))
    	    {
    			if(matchtime.get(Calendar.DAY_OF_WEEK)!=7&&matchtime.get(Calendar.DAY_OF_WEEK)!=1){
    				stopTime=matchtime2.compareTo(c2)>0?c2:matchtime2;
    				return stopTime;
    			}
    	    }
    	}
	    return matchtime2;
    }
    
	public static void main(String[] args) {
		
		String s="0.32";
		s=String.valueOf(Double.valueOf(s) * 100);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date cal = calendar.getTime();
		String date2 = format.format(cal);
 		System.out.println(date2 + "|" + date1);
 		String a = "6.56";
 		Float f = Float.parseFloat(a);
 		System.out.println(f/2);
		
	}
}
