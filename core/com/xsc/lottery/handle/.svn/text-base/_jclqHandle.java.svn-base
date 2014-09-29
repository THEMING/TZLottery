package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.wzl.bean.LotteryIDType;

@Component
public class _jclqHandle extends BaseLotteryHandle {
    @Autowired	
	private MatchArrangeService matchArrangeService;
	private String issue;
	
	@Override
	public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception 
	{
		return null;
	}

	@Override
	public LotteryType getLotteryType() 
	{
		return LotteryType.竞彩篮球;
	}

	public List<String> getTermCode(LotteryTerm term, int number) 
	{
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < number; i++) {
			list.add((Integer.valueOf(term.getTermNo()) + 1) + "");
		}
		return list;
	}

	@Override
	public LotteryTerm getNextTerm(LotteryTerm term) 
	{
		// 需要手工输入
		LotteryTerm nextTerm = null;
		return nextTerm;
	}

	private String composeBetContent(String code)
	{
		String betCodeString = "";
		String[] codestr = code.split("\\|");
		//1. 投注类型，例如CSF，RFSF，SFC，DXF
		//2. 投注的内容
		//3. 串
		if (codestr.length != 3) {
			return "";
		}

		int former = Integer.parseInt(codestr[2].split("\\*")[0]);

		if (PlayType.SF.equals(PlayType.valueOf(codestr[0]))
				|| PlayType.RFSF.equals(PlayType.valueOf(codestr[0]))
				|| PlayType.DXF.equals(PlayType.valueOf(codestr[0]))
				|| PlayType.SFC.equals(PlayType.valueOf(codestr[0]))) {
			if(former == 1)
			{
				betCodeString += "01-";	//单关
			}
			else
			{
				betCodeString += "02-";	//过关
			}
		} 
		String szClusterCode = LotteryIDType.GetClusterType(codestr[2]);
		betCodeString += szClusterCode + "-";
		String[] betCode = codestr[1].split(",");
		issue = "";
		String nearMatchTime = "";
		for(int i = 0; i < betCode.length; i++)
		{
			String matchNo = betCode[i].split("=")[0];
			String betMatch = betCode[i].split("=")[1];
			MatchArrange match = matchArrangeService.getMatchMatchByNoAndType(matchNo, LotteryType.竞彩篮球);
			String wzlMatchNo = match.getBoutIndexOther();
			if(wzlMatchNo.compareTo(issue) > 0)	issue = wzlMatchNo;
			Calendar matchTime = match.getMatchTime();
			String szMatchTime = convertCalendarToString(matchTime);
			if(nearMatchTime.compareTo("") == 0) nearMatchTime = szMatchTime;
			else
			{
				if(nearMatchTime.compareTo(szMatchTime) > 0) nearMatchTime = szMatchTime;
			}
			betMatch = betMatch.replace("/", ",");
			betCodeString += wzlMatchNo + ":[" + betMatch + "]";
			if(i == (betCode.length - 1))
				betCodeString += "|";
			else
				betCodeString += "/";
		}
		betCodeString += nearMatchTime;
		return betCodeString;
	}
	
	private String convertCalendarToString(Calendar cDate)
	{
		DateFormat df = new SimpleDateFormat("MMddHHmm");
		String szDate = df.format(cDate.getTime());
		return szDate;
	}
	@Override
	public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
			throws Exception 
	{
		PlanItem item = new PlanItem();
		try {
			if (validataBetNum(code)) {
				throw new Exception("竞彩篮球投注号码格式错误!");
			}
		} 
		catch (Exception e) {
			throw new Exception("竞彩篮球投注号码格式错误!");
		}

		int nBetCount = validataBetCount(code);
		item.setBetCount(nBetCount);
		item.setContent(code);
		String betCode = composeBetContent(code);
		item.setBetContent(betCode);
		item.setIssue(issue);
		item.setPlayType(PlayType.valueOf(code.split("\\|")[0]));
		item.setOneMoney(oneMoney);
		return item;
	}

	@Override
	protected List<Ticket> unpackTicket(Order order, PlanItem item)
	{
		List<Ticket> ticketList = new ArrayList<Ticket>();
		Ticket ticket = new Ticket();

		ticket.setCount(item.getBetCount());
		ticket.setMoney(item.getOneMoney().multiply(
					new BigDecimal(item.getBetCount())).multiply(
					BigDecimal.valueOf(order.getMultiple())));
		ticket.setMultiple(order.getMultiple());
		ticket.setItem(item);
		ticket.setOrder(order);
		ticket.setSendTicketTime(Calendar.getInstance());
		ticket.setTermNo(order.getTerm().getTermNo());
		ticket.setType(order.getType());
		ticket.setContent(item.getContent());
		ticket.setBetContent(item.getBetContent());
		ticket.setIssue(item.getIssue());
		ticket.setPlayType(item.getPlayType());
		ticket.setType(order.getType());
		ticketList.add(ticket);
		return ticketList;
	}
	
	@Override
	protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item)
	{
		List<Ticket> ticketList = new ArrayList<Ticket>();
		String formatNum = "";
		String conent = item.getContent().split("\\|")[1];
		String playType = item.getContent().split("\\|")[2];
		String[] codes = conent.split("\\,");
		StringBuffer strbuf = new StringBuffer();
		String teamId = "";
		String issue = "";
		String nearMatchTime = "66666666";
		
		for (int i = 0; i < codes.length; i++)
		{
			String[] codeArr = codes[i].split("\\=");
			String code = "["+codeArr[1].replaceAll("\\/", "\\,")+"]";
			teamId = codes[i].split("=")[0];
			String date = "";
			String result = "";
			if(item.getPlayType() == PlayType.JL_HH)
			{
				String[] hhs = teamId.split("\\>");
				date = "20"+hhs[1].substring(0, hhs[1].length()-3);
				result = hhs[1].substring(hhs[1].length()-3);
				Calendar calendar = DateUtil.parseYYYYMMDD(date);
				if(!"SFC".equals(hhs[0]))
				{
					code = code.replaceAll("3", "1").replaceAll("0", "2");
				}
				strbuf.append(typeToWZLType(hhs[0]))
					.append("=")
					.append(date)
					.append(DateUtil.getDayOfWeek(calendar))
					.append(result)
					.append(":")
					.append(code);
				String wzlTeamId = date +DateUtil.getDayOfWeek(calendar)+result;
				if(wzlTeamId.compareTo(issue) > 0)	issue = wzlTeamId;
			}
			else
			{
				 if(item.getPlayType() != PlayType.SFC)
				 {
					 code = code.replaceAll("3", "1").replaceAll("0", "2");
				 }
				 date = "20"+teamId.substring(0, teamId.length()-3);
				 result = teamId.substring(teamId.length()-3);
				 Calendar calendar = DateUtil.parseYYYYMMDD(date);
				 strbuf.append(date)
					.append(DateUtil.getDayOfWeek(calendar))
					.append(result)
					.append(":")
					.append(code);
				 String wzlTeamId = date +DateUtil.getDayOfWeek(calendar)+result;
				 if(wzlTeamId.compareTo(issue) > 0)	issue = wzlTeamId;
			}
			
			if(i != codes.length-1)
			{
				strbuf.append("/");	
			}
		}
	
		//投注方式转换
		if(conent.split("\\,").length == 1)
		{
			formatNum = "01-01-"+strbuf.toString()+"|"+nearMatchTime;//8位数字 不做校验
		}
		else
		{
			formatNum = "02-"+LotteryIDType.GetClusterType(playType)+"-"+strbuf.toString()+"|"+nearMatchTime;
		}
		
		Ticket ticket = new Ticket();
		ticket.setCount(item.getBetCount());
		ticket.setMoney(item.getOneMoney().multiply(
				new BigDecimal(item.getBetCount())).multiply(
				BigDecimal.valueOf(order.getMultiple())));
		ticket.setMultiple(order.getMultiple());
		ticket.setItem(item);
		ticket.setOrder(order);
		ticket.setSendTicketTime(Calendar.getInstance());
		ticket.setTermNo(order.getTerm().getTermNo());
		ticket.setType(order.getType());
		ticket.setContent(item.getContent());
		ticket.setBetContent(formatNum);
		ticket.setIssue(issue);//8位数字  我中啦不做校验
		ticket.setPlayType(item.getPlayType());
		ticket.setType(order.getType());
		ticketList.add(ticket);
		return ticketList;
	}

	// true 表示不合格
	@Override
	public boolean validataBetNum(String result) {
		boolean evalRes;
		String[] codestr = result.split("\\|");
		//1. 投注类型，例如CSF，RFSF，SFC，DXF
		//2. 投注的内容
		//3. 串
		if (codestr.length != 3) {
			return true;
		}

		String[] betCode = codestr[1].split(",");
		int former = Integer.parseInt(codestr[2].split("\\*")[0]);
		int latter = Integer.parseInt(codestr[2].split("\\*")[1]);

		if (PlayType.SF.equals(PlayType.valueOf(codestr[0])) 
				|| PlayType.RFSF.equals(PlayType.valueOf(codestr[0]))
				|| PlayType.DXF.equals(PlayType.valueOf(codestr[0]))) {
			for (int i = 0; i < betCode.length; i++) {
				evalRes = betCode[i]
						.matches("[0-9]{2}[01][0-9][0-3][0-9][0-9]{3}=[12](/[12]){0,1}");
				if (!evalRes) {
					System.out.println("投注部分有问题！");
					return true;
				}
			}
			return !validateChuan(betCode.length, former, latter);
		} else if (PlayType.SFC.equals(PlayType.valueOf(codestr[0]))) {
			for (int i = 0; i < betCode.length; i++) {
				evalRes = betCode[i]
						.matches("[0-9]{2}[01][0-9][0-3][0-9][0-9]{3}=[0-1][1-6](/[0-1][1-6]){0,12}");
				if (!evalRes)
					return true;
			}
			return !validateChuan(betCode.length, former, latter);
		} else {
			return true;
		}
	}

	// 合格则返回 true
	public boolean validateChuan(int len, int former, int latter) {
		if (1 == former)
			return (1 == latter);
		else if (len == former) {
			switch (former) {
			case 2:
				return (latter == 1);
			case 3:
				return (latter == 1 || latter == 3 || latter == 4);
			case 4:
				return (latter == 1 || latter == 4 || latter == 5
						|| latter == 6 || latter == 11);
			case 5:
				return (latter == 1 || latter == 5 || latter == 6
						|| latter == 10 || latter == 16 || latter == 20 || latter == 26);
			case 6:
				return (latter == 1 || latter == 6 || latter == 7
						|| latter == 15 || latter == 20 || latter == 22
						|| latter == 35 || latter == 42 || latter == 50 || latter == 57);
			case 7:
				return (latter == 1 || latter == 7 || latter == 8
						|| latter == 21 || latter == 35 || latter == 120);
			case 8:
				return (latter == 1 || latter == 8 || latter == 9
						|| latter == 28 || latter == 56 || latter == 70 || latter == 247);
			default:
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean validataRepeat(String code) {
		return false;
	}

	@Override
	public int validataBetCount(String code) {
		String[] codestr = code.split("\\|");
		String[] betCode = codestr[1].split(",");
		ArrayList<Integer> bet = new ArrayList<Integer>();

		for (int i = 0; i < betCode.length; i++) {
			bet.add(betCode[i].split("/").length);
		}
		int former = Integer.parseInt(codestr[2].split("\\*")[0]);
		int latter = Integer.parseInt(codestr[2].split("\\*")[1]);

		switch (former) {
		case 1:
			int sum = 0;
			for (int i = 0; i < bet.size(); i++)
				sum += bet.get(i);
			return sum;
		case 2:
			return calculateBetCount(bet, 2, 0); // 2串1
		case 3:
			if (1 == latter) // 3串1
				return calculateBetCount(bet, 3, 0);
			else if (3 == latter) // 3串3
				return calculateBetCount(bet, 2, 0);
			else
				// 3串4
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0);
		case 4:
			switch (latter) {
			case 1: // 4串1
				return calculateBetCount(bet, 4, 0);
			case 4: // 4串4
				return calculateBetCount(bet, 3, 0);
			case 5: // 4串5
				return calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0);
			case 6: // 4串6
				return calculateBetCount(bet, 2, 0);
			case 11: // 4串11
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0);
			default:
				return 0;
			}
		case 5:
			switch (latter) {
			case 1: // 5串1
				return calculateBetCount(bet, 5, 0);
			case 5: // 5串5
				return calculateBetCount(bet, 4, 0);
			case 6: // 5串6
				return calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0);
			case 10: // 5串10
				return calculateBetCount(bet, 2, 0);
			case 16: // 5串16
				return calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0);
			case 20: // 5串20
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0);
			case 26: // 5串26
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0);
			default:
				return 0;
			}
		case 6:
			switch (latter) {
			case 1: // 6串1
				return calculateBetCount(bet, 6, 0);
			case 6: // 6串6
				return calculateBetCount(bet, 5, 0);
			case 7: // 6串7
				return calculateBetCount(bet, 5, 0)
						+ calculateBetCount(bet, 6, 0);
			case 15: // 6串15
				return calculateBetCount(bet, 2, 0);
			case 20: // 6串20
				return calculateBetCount(bet, 3, 0);
			case 22: // 6串22
				return calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0)
						+ calculateBetCount(bet, 6, 0);
			case 35: // 6串35
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0);
			case 42: // 6串42
				return calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0)
						+ calculateBetCount(bet, 6, 0);
			case 50: // 6串50
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0);
			case 57: // 6串57
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0)
						+ calculateBetCount(bet, 6, 0);
			default:
				return 0;
			}
		case 7:
			switch (latter) {
			case 1: // 7串1
				return calculateBetCount(bet, 7, 0);
			case 7: // 7串7
				return calculateBetCount(bet, 6, 0);
			case 8: // 7串8
				return calculateBetCount(bet, 6, 0)
						+ calculateBetCount(bet, 7, 0);
			case 21: // 7串21
				return calculateBetCount(bet, 5, 0);
			case 35: // 7串35
				return calculateBetCount(bet, 4, 0);
			case 120: // 6串120
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0)
						+ calculateBetCount(bet, 6, 0)
						+ calculateBetCount(bet, 7, 0);
			default:
				return 0;
			}
		case 8:
			switch (latter) {
			case 1: // 8串1
				return calculateBetCount(bet, 8, 0);
			case 8: // 8串8
				return calculateBetCount(bet, 7, 0);
			case 9: // 8串9
				return calculateBetCount(bet, 7, 0)
						+ calculateBetCount(bet, 8, 0);
			case 28: // 8串28
				return calculateBetCount(bet, 6, 0);
			case 56: // 8串56
				return calculateBetCount(bet, 5, 0);
			case 70: // 8串56
				return calculateBetCount(bet, 4, 0);
			case 247: // 8串247
				return calculateBetCount(bet, 2, 0)
						+ calculateBetCount(bet, 3, 0)
						+ calculateBetCount(bet, 4, 0)
						+ calculateBetCount(bet, 5, 0)
						+ calculateBetCount(bet, 6, 0)
						+ calculateBetCount(bet, 7, 0)
						+ calculateBetCount(bet, 8, 0);
			default:
				return 0;
			}
		default:
			return 0;
		}
	}

	// 计算在投注方案 bet 的m串1的投注数
	public int calculateBetCount(ArrayList<Integer> bet, int m, int begin) {
		if (m == 0) {
			return 1;
		} else if (bet.size() - begin == m) {
			int mul = 1;
			for (int j = begin; j < bet.size(); j++) {
				mul *= bet.get(j);
			}
			return mul;
		}
		return bet.get(begin) * calculateBetCount(bet, m - 1, begin + 1)
				+ calculateBetCount(bet, m, begin + 1);
	}

	@Override
	public List<PlanItem> validateUploadFile(String filePath,
			BigDecimal oneMoney, int multiple, BigDecimal totalMoney)
			throws Exception {
		throw new Exception("暂不提供!");
	}

	/**
	 * 开奖
	 */

	@Override
	public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
			List<PrizeLevel> prizeLevels) {
		// throw new Exception("该函数不开奖，开奖操作由另一个类完成！");
		return null;
	}

	// 根据上传的XML文件保存比赛信息
	@Override
	public void parseXML(Element root) throws Exception {
	}

	static public void main(String argv[]) {
		
		/*
		 *  _jczqHandle h = new _jczqHandle(); String test0 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*1"
		 * ; String test1 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*6"
		 * ; String test2 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*7"
		 * ; String test3 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*15"
		 * ; String test4 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*20"
		 * ; String test5 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*22"
		 * ; String test6 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*35"
		 * ; String test7 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*42"
		 * ; String test8 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*50"
		 * ; String test9 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*57"
		 * ;
		 */
		 _jclqHandle h = new _jclqHandle(); 
		String test = "CSF|111103301=3/0,111103302=3/0,11110 3303=0,111103304=0|4*1";
		System.out.println(h.composeBetContent(test));
	}
	
    
    @Override
    public String changeToWZLContent(String content)
    {
    	String[] temp0 = content.split("#");//,分割生成的字符串组
    	String string = "";
    	for (int i = 0; i < temp0.length; i++) {
        	String sortString = sort(temp0[i]);
        	if (i == 0) {
				string += sortString; 
			}
        	else {
    			string = string + "#" + sortString;
			}
		}
    	return string;
    }
    
    @Override
    public String sort(String content)
    {
    	String[] s = content.split(",");
    	int[] nums = new int[s.length];
    	int temp = 0;
    	String use = "";
    	String ss = "";
    	for (int i = 0; i < s.length; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
    	for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
			if (nums[i] < 10) {
				ss = "0" + nums[i];
			}
			else {
				ss = "" + nums[i];
			}
			if (i == nums.length - 1) {
				use = use + ss;
			}
			else {
				use = use + ss + ",";
			}
		}
    	return use;
    }

	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}

}
