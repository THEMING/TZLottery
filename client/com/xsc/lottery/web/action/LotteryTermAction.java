package com.xsc.lottery.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.handle.BaseLotteryHandle;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.StringUtil;

@Scope("prototype")
@Controller("lotteryterm.TermIndeAct")
@SuppressWarnings( { "unused", "serial" })
public class LotteryTermAction extends LotteryClientBaseAction
{
	public String getType()
	{
		return type;
	}

	// 追加多少期
	private int num = 10;
	// 期数
	private String term;
	// 彩种类型
	private String lotteryType;// todo
	// 玩法类型playType
	private String playType;
	// 追加彩种类型
	private String type;

	private String Qday;
	// 追加彩期列表
	private List<String> termList;
	// 中奖号码列表
	private List<LotteryTerm> lotteryResult;
	// 走势图总期数
	private int termAll;

	@Autowired
	private LotteryTermService lotteryTermService;

	@Autowired
	private LotteryHandleFactory handleFactory;

	private LotteryTerm result;

	private Map<String, LotteryTerm> allOpenTerm;

	private Page<LotteryTerm> page;

	private int pageNo = 1;

	private int pageSize = 10;

	private List<String> termArray; // 期次列表30期

	// FIXME
	private List<MatchArrange> matchResultList;

	private List<MatchArrange> matchResultList1;

  private List<MatchArrange> matchResultList4CJQ;
	private List<LotteryTerm> list;

	private List<LotteryTerm> list1;
private List<LotteryTerm> list4CJQ;

	//手机分页查询开奖历史时所需要的NUM
	private int begin;

	public String index()
	{
		LotteryTerm lotteryTerm = lotteryTermService.getCurrentTerm(LotteryType
				.enToType(type));
		Map<String, String> resultMap = new HashMap<String, String>();
		if (lotteryTerm != null) {
			resultMap.put("resultTime", String.valueOf(DateUtil.compareSecond(
					lotteryTerm.getStopSaleTime(), Calendar.getInstance())));
			resultMap.put("stopTime", DateUtil.toTimeStampFm(lotteryTerm
					.getStopSaleTime()));
			if (null != lotteryTerm.getStopTogetherSaleTime()) {
				resultMap.put("termStopTime", DateUtil
						.toTimeStampFm(lotteryTerm.getStopTogetherSaleTime()));
			}
			resultMap.put("termStatus", lotteryTerm.getTermStatus().toString());
			resultMap.put("term", lotteryTerm.getTermNo());
			resultMap.put("type", lotteryTerm.getType().name());
			resultMap.put("deadLine", String.valueOf((lotteryTerm
					.getStopSaleTime().getTimeInMillis() - System
					.currentTimeMillis()) / 1000));
		}
		else {
			resultMap.put("termStatus", TermStatus.暂停销售.name());
		}

		setJsonString(JsonMsgBean.MapToJsonString(resultMap));
		return AJAXJSON;
	}

	public String ajaxGetUpTermInfo()
	{
		LotteryTerm upTerm = lotteryTermService.getLotteryTermByLtSta(
				LotteryType.enToType(type), TermStatus.已开奖);
		LotteryTerm oddTerm = lotteryTermService
				.getLastOpenPrizeResult(LotteryType.enToType(type));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Long l = (upTerm.getOpenPrizeTime().getTimeInMillis() - System
				.currentTimeMillis()) / 1000;
		resultMap.put("oddTerm", oddTerm.getTermNo());
		resultMap.put("oddcord", oddTerm.getResult());
		resultMap.put("prizePool", oddTerm.getPrizePool());
		resultMap.put("upTerm", upTerm.getTermNo());
		resultMap.put("openprize", l.toString());
		lotteryResult = lotteryTermService.getTermByZSMap(LotteryType
				.enToType(type), 10);
		JSONArray jsonarray = new JSONArray();
		for (LotteryTerm term : lotteryResult) {
			JSONObject dd = new JSONObject();
			dd.put("term", term.getTermNo());
			if (LotteryType.KuaiKaiTypeMap.containsKey(type)) {
				dd.put("time", DateUtil.toHHmm(term.getOpenPrizeTime()));
			}
			else {
				dd.put("time", DateUtil.toYYYY_MM_DD(term.getOpenPrizeTime()));
			}
			dd.put("result", term.getResult());
			jsonarray.add(dd);
		}
		resultMap.put("hoistList", jsonarray);
		setJsonString(JsonMsgBean.MapToJsonString(resultMap));
		return AJAXJSON;
	}

	public String zhTerm()
	{
		List<String> list = new ArrayList<String>();
		if (LotteryType.KuaiKaiTypeMap.containsKey(type)) {
			BaseLotteryHandle handle = handleFactory
					.getLotteryHandleFactory(LotteryType.enToType(type));
			LotteryTerm term = this.lotteryTermService
					.getCurrentTerm(LotteryType.enToType(type));
			int number = 0;
			int ycm = 0;
			Calendar stop = DateUtil.getTheDayZero(term.getStopSaleTime());
			LotteryTerm mterm = new LotteryTerm();
			if (LotteryType.重庆时时彩.getName_EN().equals(type)) {
				number = 120 - Integer.parseInt(term.getTermNo()
						.substring(6, 9));
				ycm = (60 - term.getStopSaleTime().get(Calendar.MINUTE)) % 10;
				stop.add(Calendar.MINUTE, 5);
				stop.add(Calendar.MINUTE, -ycm);
			}
			if (LotteryType.上海时时乐.getName_EN().equals(type)) {
				number = 23 - Integer.parseInt(term.getTermNo()
						.substring(8, 10));
				ycm = (60 - term.getStopSaleTime().get(Calendar.MINUTE)) % 10;
				stop.set(Calendar.HOUR_OF_DAY, 10);
				stop.set(Calendar.MINUTE, 30);
				stop.add(Calendar.MINUTE, -ycm);

			}
			switch (num) {
			case 1000:// 今天
				list = handle.getTermCode(term, number);
				break;
			case 1001:// 明天
				stop.add(Calendar.DAY_OF_MONTH, 1);
				mterm.setStopSaleTime(stop);
				if (LotteryType.重庆时时彩.getName_EN().equals(type)) {
					mterm.setTermNo(DateUtil.toYYMMdd(stop) + "001");
					number = 119;
				}
				if (LotteryType.上海时时乐.getName_EN().equals(type)) {
					mterm.setTermNo(DateUtil.toYYYYMMDD(stop) + "01");
					number = 22;
				}
				list = handle.getTermCode(mterm, number);
				break;
			case 1002:// 后天
				stop.add(Calendar.DAY_OF_MONTH, 2);
				mterm.setStopSaleTime(stop);
				if (LotteryType.重庆时时彩.getName_EN().equals(type)) {
					mterm.setTermNo(DateUtil.toYYMMdd(stop) + "001");
					number = 119;
				}
				if (LotteryType.上海时时乐.getName_EN().equals(type)) {
					mterm.setTermNo(DateUtil.toYYYYMMDD(stop) + "01");
					number = 22;
				}
				list = handle.getTermCode(mterm, number);
				break;
			default:
				break;
			}
			setJsonString(JsonMsgBean.ListToJsonString(list));
			return AJAXJSON;
		}

		LotteryTerm lotteryTerm = lotteryTermService.getCurrentTerm(LotteryType
				.enToType(type));
		termList = new ArrayList<String>();
		if (term.equals(lotteryTerm.getTermNo())) {
			Long termlong = Long.parseLong(term);
			termList.add(term);
			for (int i = 0; i < num - 1; i++) {
				String str = new String();
				termlong = termlong + 1;
				if (term.length() > termlong.toString().length()) {
					str = term.substring(0, term.length()
							- termlong.toString().length())
							+ termlong.toString();
				}
				else {
					str = termlong.toString();
				}
				termList.add(str);
			}
		}
		// System.out.println(termList.size());
		setJsonString(JsonMsgBean.ListToJsonString(termList));
		return AJAXJSON;
	}

	public String zhKK()
	{
		BaseLotteryHandle handle = handleFactory
				.getLotteryHandleFactory(LotteryType.enToType(type));
		LotteryTerm term = this.lotteryTermService.getCurrentTerm(LotteryType
				.enToType(type));
		int ind = Integer.parseInt(term.getTermNo().substring(6, 9));
		List<String> list = new ArrayList<String>();
		// 今天
		if (Qday.equals("d")) {
			list = handle.getTermCode(term, 120 - ind);
		}
		else {

			Calendar now = Calendar.getInstance();
			LotteryTerm mterm = new LotteryTerm();
			Calendar stop = DateUtil.getTheDayZero(now);
			// 明天
			if (Qday.equals("m")) {
				now.add(Calendar.DAY_OF_MONTH, 1);
				mterm.setTermNo(DateUtil.toYYMMdd(Calendar.getInstance())
						+ "001");
				stop = DateUtil.getTheDayZero(now);
				stop.add(Calendar.MINUTE, 5);
				mterm.setStopSaleTime(stop);
				list = handle.getTermCode(mterm, 119);
			}
			if (Qday.equals("h")) {
				now.add(Calendar.DAY_OF_MONTH, 1);
				mterm.setTermNo(DateUtil.toYYMMdd(Calendar.getInstance())
						+ "001");
				stop = DateUtil.getTheDayZero(now);
				stop.add(Calendar.MINUTE, 5);
				mterm.setStopSaleTime(stop);
				list = handle.getTermCode(mterm, 119);
			}
		}
		setJsonString(JsonMsgBean.ListToJsonString(list));
		return AJAXJSON;
	}

	public String zstResult()
	{
		lotteryResult = lotteryTermService.getTermByZSMap(LotteryType
				.enToType(type), num);
		Collections.reverse(lotteryResult);
		return SUCCESS;
	}

	public String allLotteryResult()
	{
		allOpenTerm = new HashMap<String, LotteryTerm>();
		for (LotteryType type : LotteryType.values()) {
			if (type != LotteryType.全部)
				allOpenTerm.put(type.getName_EN(), lotteryTermService
						.getLastOpenPrizeResult(type));
		}
		return SUCCESS;
	}

	public String result()
	{
		result = lotteryTermService.getLastOpenPrizeResult(LotteryType
				.enToType(type));
		Map<String, String> resultMap = new HashMap<String, String>();
		if (result != null) {
			resultMap.put("term", result.getTermNo());
			resultMap.put("cord", result.getResult());
			resultMap.put("type", result.getType().name());
		}
		setJsonString(JsonMsgBean.MapToJsonString(resultMap));
		return AJAXJSON;
	}

	public String getTermPageList()
	{
		page = new Page<LotteryTerm>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setAutoCount(true);
		page = lotteryTermService.getHistoryLotteryTermPage(page, LotteryType
				.enToType(type));
		
		for(LotteryTerm term : page.getResult()) {
			sortPrize(term);
		}

		return SUCCESS;
	}

	public String ajaxRequesTermInfo()
	{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		result = lotteryTermService.getLastOpenPrizeResult(LotteryType
				.enToType(type));
		if (result != null) {
			resultMap.put("term", result.getTermNo());
			resultMap.put("cord", result.getResult());
			resultMap.put("opendate", DateUtil.toyyyy_MM_dd_HH_mm(result
					.getOpenPrizeTime()));
			JSONArray jsonarray = new JSONArray();
			for (PrizeLevel pzl : result.getPrizeLevels()) {
				JSONObject dd = new JSONObject();
				dd.put("name", pzl.getName());
				dd.put("betnum", pzl.getBetNum());
				dd.put("prize", pzl.getPrize());
				jsonarray.add(dd);
			}
			resultMap.put("prizeLevel", jsonarray);
			resultMap.put("prizepool", result.getPrizePool());
			resultMap.put("totalSale", result.getTotalSale());
		}
		sortPrize(result);
		lotteryResult = lotteryTermService.getTermByZSMap(LotteryType
				.enToType(type), 10);
		JSONArray jsonarray = new JSONArray();
		for (LotteryTerm term : lotteryResult) {
			JSONObject dd = new JSONObject();
			dd.put("term", term.getTermNo());
			if (LotteryType.KuaiKaiTypeMap.containsKey(type))
				dd.put("time", DateUtil.toHHmm(term.getOpenPrizeTime()));
			else
				dd.put("time", DateUtil.toYYYY_MM_DD(term.getOpenPrizeTime()));
			dd.put("result", term.getResult());
			jsonarray.add(dd);
		}
		resultMap.put("hoistList", jsonarray);
		setJsonString(JsonMsgBean.MapToJsonString(resultMap));
		return AJAXJSON;
	}

	public String getTermInfo()
	{
		try {
			termArray = lotteryTermService.getTermArray(LotteryType.enToType(type), 30);
			if (StringUtil.isEmpty(term)) {
				term = lotteryTermService.getLastOpenPrizeResult(
						LotteryType.enToType(type)).getTermNo();
			}
			// FIXME
			this.getMatchList();
			this.get6CBMatchList();
			this.getFootballTermList();
			this.get6CBFootballTermList();
            this.get4CJQMatchList();
            this.get4CJQFootballTermList();
			result = lotteryTermService.getByTypeAndTermNo(term, LotteryType.enToType(type));
			sortPrize(result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String ajaxTermInfo()
	{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		LotteryTerm lotteryterm = lotteryTermService.getByTypeAndTermNo(term,
				LotteryType.enToType(type));
		resultMap.put("result", lotteryterm.getResult());
		resultMap.put("orderResult", lotteryterm.getOrderResult());
		resultMap.put("termno", lotteryterm.getTermNo());
		resultMap.put("openPrizeTime", DateUtil.toYYYY_MM_DDZH(lotteryterm
				.getOpenPrizeTime()));
		resultMap.put("totalSale", StringUtil
				.format(lotteryterm.getTotalSale()));
		resultMap.put("othertotalSale", StringUtil.format(lotteryterm
				.getOtherTotalSale()));
		resultMap.put("prizePool", StringUtil
				.format(lotteryterm.getPrizePool()));
		JSONArray jsonarray = new JSONArray();
		sortPrize(lotteryterm);
		for (PrizeLevel pzl : lotteryterm.getPrizeLevels()) {
			JSONObject dd = new JSONObject();
			dd.put("name", pzl.getName());
			dd.put("betnum", pzl.getBetNum());
			dd.put("prize", StringUtil.format(pzl.getPrize()));
			dd.put("addbetnum", pzl.getAddBetNum());
			dd.put("addprize", StringUtil.format(pzl.getAddPrize()));
			jsonarray.add(dd);
		}
		resultMap.put("prizeLevel", jsonarray);

		// FIXME
		JSONArray jsonarray2 = new JSONArray();
		if (type.equals("14sfc") || type.equals("r9")) {
			this.getMatchList();
			for (int i = 0; i < matchResultList.size(); i++) {
				JSONObject dd = new JSONObject();
				dd.put("homeTeam", matchResultList.get(i).getHomeTeam());
				dd.put("awaryTeam", matchResultList.get(i).getAwaryTeam());
				// dd.put("matchResult",
				// matchResultList.get(i).getMatchResult());
				jsonarray2.add(dd);
			}
			resultMap.put("matchResultList", jsonarray2);
		}
        JSONArray jsonarray3 = new JSONArray();
        if(type.equals("6cb")) {
        this.get6CBMatchList();
        	for (int i=0;i<matchResultList1.size();i++) {
        		JSONObject dd = new JSONObject();
        		dd.put("homeTeam", matchResultList1.get(i).getHomeTeam());
        		dd.put("awaryTeam", matchResultList1.get(i).getAwaryTeam());
        		//dd.put("matchResult", matchResultList.get(i).getMatchResult());
        		jsonarray3.add(dd);
        	}
        	resultMap.put("matchResultList1", jsonarray3);
        }
        
        JSONArray jsonarray4 = new JSONArray();
        if(type.equals("4cjq")) {
        this.get4CJQMatchList();
        	for (int i=0;i<matchResultList4CJQ.size();i++) {
        		JSONObject dd = new JSONObject();
        		dd.put("homeTeam", matchResultList4CJQ.get(i).getHomeTeam());
        		dd.put("awaryTeam", matchResultList4CJQ.get(i).getAwaryTeam());
        		//dd.put("matchResult", matchResultList.get(i).getMatchResult());
        		jsonarray4.add(dd);
        	}
        	resultMap.put("matchResultList4CJQ", jsonarray4);
        }
        
		setJsonString(JsonMsgBean.MapToJsonString(resultMap));
		return AJAXJSON;
	}

	public String getTodayHoist()
	{
		LotteryTerm currTerm = lotteryTermService.getCurrentTerm(LotteryType
				.enToType(type));
		if (currTerm != null) {
			int code = 0;
			if (type.equals("ssc_CQ"))
				code = Integer.parseInt(currTerm.getTermNo().substring(6, 9));
			else
				code = Integer.parseInt(currTerm.getTermNo().substring(8, 10));
			lotteryResult = lotteryTermService.getTermByZSMap(LotteryType
					.enToType(type), code);
		}
		return "todayhois";
	}
	
	public String ajaxKaiJiangForPhone()
	{
		JSONArray jsonArray = new JSONArray();
		for(int i=0; i<=9; i++)
		{
			String typeString = this.numberToTypeString(i);
			if (typeString != null) {
				result = lotteryTermService.getLastOpenPrizeResult(LotteryType
						.enToType(typeString));
				JSONObject jsonObject = new JSONObject();
				if (i == 3) {
					jsonObject.put("type", "福彩3D");
				}
				else {
					jsonObject.put("type", result.getType());
				}
				jsonObject.put("name", typeString);
				jsonObject.put("termNo", result.getTermNo());
				Calendar cal = result.getOpenPrizeTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = sdf.format(cal.getTime());
				jsonObject.put("date", dateStr);
				jsonObject.put("code", result.getResult());
				BigDecimal prizePool = result.getPrizePool();
				if (prizePool == null) {
					prizePool = BigDecimal.ZERO;
				}
				double num = prizePool.doubleValue();
				int num1 = (int)(num / 100000000);
				int num2 = (int)((num - num1 * 100000000.0)/10000);
				String numString = "";
				if (num1 != 0) {
					numString = num1 + "亿" + num2 + "万";
				} 
				else {
					numString = num2 + "万";
				}
				jsonObject.put("prizepool", numString);
				jsonObject.put("action", "ajaxKaiJiangDetailForPhone");
				jsonArray.add(jsonObject);
			}
		}
		setJsonString(jsonArray.toString());
		return AJAXJSON;
	}
	
	public String ajaxKaiJiangDetailForPhone()//此处需要传过来个type参数和term(第几期)参数
	{
		result = lotteryTermService.getByTypeAndTermNo(term, LotteryType
				.enToType(type));
		sortPrize(result);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Calendar openPrizeTime = result.getOpenPrizeTime();
		openPrizeTime.add(Calendar.MONTH, 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String openPrizeTimeStr = sdf.format(openPrizeTime.getTime());
		resultMap.put("stopSendPrizeTime", openPrizeTimeStr);
		JSONArray jsonarray = new JSONArray();
		for (PrizeLevel pzl : result.getPrizeLevels()) {
			JSONObject dd = new JSONObject();
			dd.put("name", pzl.getName());
			dd.put("betnum", pzl.getBetNum());
			dd.put("prize", pzl.getPrize());
			jsonarray.add(dd);
		}
		resultMap.put("prizeLevel", jsonarray);
		resultMap.put("action", "ajaxKaiJiangHistoryForPhone");
		setJsonString(JsonMsgBean.MapToJsonString(resultMap));
		return AJAXJSON;
	}
	
	//TODO应该是数据库分页的形式
	public String ajaxKaiJiangHistoryForPhone()////此处需要传过来个type参数和第几次请求begin过来(请求从1开始)
	{
		lotteryResult = lotteryTermService.getTermByZSMapForPhone(LotteryType
				.enToType(type), 20, begin);
		JSONArray jsonarray = new JSONArray();
		for (LotteryTerm term : lotteryResult) {
			JSONObject dd = new JSONObject();
			LotteryType type = term.getType();
			if (type.getName_EN().equals("3d")) {
				dd.put("type", "福彩3D");
			}
			else {
				dd.put("type", term.getType());
			}
			dd.put("term", term.getTermNo());
			if (LotteryType.KuaiKaiTypeMap.containsKey(type))
				dd.put("time", DateUtil.toHHmm(term.getOpenPrizeTime()));
			else
				dd.put("time", DateUtil.toYYYY_MM_DD(term.getOpenPrizeTime()));
			dd.put("result", term.getResult());
			jsonarray.add(dd);
		}
		setJsonString(jsonarray.toString());
		return AJAXJSON;
	}
	
	public String ajaxXuanHaoForPhone()
	{
		JSONArray jsonArray = new JSONArray();
		for(int i=0; i<=9; i++)
		{
			String typeString = this.numberToTypeString(i);
			if (typeString != null) {
				result = lotteryTermService.getCurrentTerm(LotteryType
						.enToType(typeString));
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("type", result.getType());
				jsonObject.put("termNo", result.getTermNo());
				putMaxPrizeToJson(i, jsonObject);
				//DateUtil.toYYYY_MM_DD(result.getOpenPrizeTime())
				jsonObject.put("openPrizeTime", result.getOpenPrizeTime().getTime().getTime());
				jsonObject.put("stopSaleTime", DateUtil.toYYYY_MM_DD_HH_MM_SS(result.getStopSaleTime()));
				jsonArray.add(jsonObject);
			}
		}
		setJsonString(jsonArray.toString());
		return AJAXJSON;
	}
	
	public String numberToTypeString(int i)
	{
		if (i == 1) {
			return "ssq";
		}
		else if (i == 2) {
			return "dlt";
		}
		else if (i == 3) {
			return "3d";
		}
		else if (i == 4) {
			return "pls";
		}
		else if (i == 5) {
			return "plw";
		}
		else if (i == 8) {
			return "qxc";
		}
		else if (i == 9) {
			return "qlc";
		}
		else {
			return null;
		}
	}
	
	private void putMaxPrizeToJson(int i, JSONObject jsonObject)
	{
		if (i == 1) {
			jsonObject.put("max", "500万");
		}
		else if (i == 2) {
			jsonObject.put("max", "500万");
		}
		else if (i == 3) {
			jsonObject.put("max", "1000");
		}
		else if (i == 4) {
			jsonObject.put("max", "1000");
		}
		else if (i == 5) {
			jsonObject.put("max", "10万");
		}
		else if (i == 8) {
			jsonObject.put("max", "500万");
		}
		else if (i == 9){
			jsonObject.put("max", "500万");
		}
	}
	
	public LotteryTerm getResult()
	{
		return result;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public void setTerm(String term)
	{
		this.term = term;
	}

	public void setLotteryType(String lotteryType)
	{
		this.lotteryType = lotteryType;
	}

	public void setPlayType(String playType)
	{
		this.playType = playType;
	}

	public void setTermAll(int termAll)
	{
		this.termAll = termAll;
	}

	public void setLotteryTermService(LotteryTermService lotteryTermService)
	{
		this.lotteryTermService = lotteryTermService;
	}

	public List<String> getTermList()
	{
		return termList;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public Map<String, LotteryTerm> getAllOpenTerm()
	{
		return allOpenTerm;
	}

	public Page<LotteryTerm> getPage()
	{
		return page;
	}

	public void setPage(Page<LotteryTerm> page)
	{
		this.page = page;
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public List<LotteryTerm> getLotteryResult()
	{
		return lotteryResult;
	}

	public List<String> getTermArray()
	{
		return termArray;
	}

	public void setQday(String qday)
	{
		Qday = qday;
	}

	public List<MatchArrange> getMatchList()
	{
		if (type.equals("14sfc") || type.equals("r9")) {
			matchResultList = lotteryTermService.getMatchResultByTermNo(term);
			return matchResultList;
		}
		else {
			return null;
		}
	}

	public List<LotteryTerm> getFootballTermList()
	{

		if (type.equals("14sfc") || type.equals("r9")) {
			list = lotteryTermService.getTermResultByTermNo(term);
			return list;
		}
		else {
			return null;
		}
	}

	public List<MatchArrange> get6CBMatchList()
	{
		if (type.equals("6cb")) {
			matchResultList1 = lotteryTermService
					.get6CBMatchResultByTermNo(term);
		}
		return matchResultList1;
	}

	public List<LotteryTerm> get6CBFootballTermList()
	{

		if (type.equals("6cb")) {
			list1 = lotteryTermService.getTerm6CBResultByTermNo(term);
			return list1;
    	} else {
    		return null;
    	}
	}
    
    public List<MatchArrange> get4CJQMatchList() {
    	if(type.equals("4cjq")) {
    		matchResultList4CJQ = lotteryTermService.get4CJQMatchResultByTermNo(term);
    	}
    	return matchResultList4CJQ;
	}
    
    public List<LotteryTerm> get4CJQFootballTermList() {
    	
    	if(type.equals("4cjq")) {
    		list4CJQ = lotteryTermService.getTerm4CJQResultByTermNo(term);
    		return list4CJQ;
    	} else {
    		return null;
    	}
	}

	public List<MatchArrange> getMatchResultList()
	{
		return matchResultList;
	}

	public void setMatchResultList(List<MatchArrange> matchResultList)
	{
		this.matchResultList = matchResultList;
	}

	public List<MatchArrange> getMatchResultList1()
	{
		return matchResultList1;
	}

	public void setMatchResultList1(List<MatchArrange> matchResultList1)
	{
		this.matchResultList1 = matchResultList1;
	}

	public List<LotteryTerm> getList()
	{
		return list;
	}

	public void setList(List<LotteryTerm> list)
	{
		this.list = list;
	}

	public List<LotteryTerm> getList1()
	{
		return list1;
	}

	public void setList1(List<LotteryTerm> list1)
	{
		this.list1 = list1;
	}
	public List<MatchArrange> getMatchResultList4CJQ() {
		return matchResultList4CJQ;
	}

	public void setMatchResultList4CJQ(List<MatchArrange> matchResultList4CJQ) {
		this.matchResultList4CJQ = matchResultList4CJQ;
	}

	public List<LotteryTerm> getList4CJQ() {
		return list4CJQ;
	}

	public void setList4CJQ(List<LotteryTerm> list4CJQ) {
		this.list4CJQ = list4CJQ;
	}
	private void sortPrize(LotteryTerm term)
	{
		List<PrizeLevel> prizeLevels = term.getPrizeLevels();
		Map<String, PrizeLevel> prizeMap = new HashMap<String, PrizeLevel>();
		for(PrizeLevel level : prizeLevels) {
			prizeMap.put(level.getName(), level);
		}
		
		if(term.getType().equals(LotteryType.双色球) || term.getType().equals(LotteryType.七星彩)) {
			prizeLevels.set(0, prizeMap.get("一等奖"));
			prizeLevels.set(1, prizeMap.get("二等奖"));
			prizeLevels.set(2, prizeMap.get("三等奖"));
			prizeLevels.set(3, prizeMap.get("四等奖"));
			prizeLevels.set(4, prizeMap.get("五等奖"));
			prizeLevels.set(5, prizeMap.get("六等奖"));
		}
		else if(term.getType().equals(LotteryType.福彩3d)) {
			prizeLevels.set(0, prizeMap.get("单选"));
			if(prizeMap.get("组三") != null) {
				prizeLevels.set(1, prizeMap.get("组三"));
			}
			else {
				prizeLevels.set(1, prizeMap.get("组六"));
			}
		} else if( term.getType().equals(LotteryType.排列三)) {
			prizeLevels.set(0, prizeMap.get("直选"));
			if(prizeMap.get("组三") != null) {
				prizeLevels.set(1, prizeMap.get("组三"));
			}
			else {
				prizeLevels.set(1, prizeMap.get("组六"));
			}
		} 
		else if(term.getType().equals(LotteryType.七乐彩)) {
			prizeLevels.set(0, prizeMap.get("一等奖"));
			prizeLevels.set(1, prizeMap.get("二等奖"));
			prizeLevels.set(2, prizeMap.get("三等奖"));
			prizeLevels.set(3, prizeMap.get("四等奖"));
			prizeLevels.set(4, prizeMap.get("五等奖"));
			prizeLevels.set(5, prizeMap.get("六等奖"));
			prizeLevels.set(6, prizeMap.get("七等奖"));
		}
		else if(term.getType().equals(LotteryType.排列五)) {
			prizeLevels.set(0, prizeMap.get("直选"));
		}
		else if(term.getType().equals(LotteryType.足彩14场)) {
			prizeLevels.set(0, prizeMap.get("一等奖"));
			prizeLevels.set(1, prizeMap.get("二等奖"));
		}
		else if(term.getType().equals(LotteryType.足彩6场半) || term.getType().equals(LotteryType.四场进球)) {
			prizeLevels.set(0, prizeMap.get("一等奖"));
		} else if(term.getType().equals(LotteryType.足彩任9))
		{
			prizeLevels.set(0, prizeMap.get("任九"));
		}
		else if(term.getType().equals(LotteryType.大乐透)) {
			prizeLevels.set(0, prizeMap.get("一等奖"));
			prizeLevels.set(1, prizeMap.get("二等奖"));
			prizeLevels.set(2, prizeMap.get("三等奖"));
			prizeLevels.set(3, prizeMap.get("四等奖"));
			prizeLevels.set(4, prizeMap.get("五等奖"));
			prizeLevels.set(5, prizeMap.get("六等奖"));
			prizeLevels.set(6, prizeMap.get("七等奖"));
			prizeLevels.set(7, prizeMap.get("八等奖"));
			prizeLevels.set(8, prizeMap.get("12选2"));
		}
		//FIXME
		term.setPrizeLevels(prizeLevels);
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}
}
