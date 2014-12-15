package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xsc.lottery.common.Constants;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.handle.xml._jczqXmlBean;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.wzl.bean.LotteryIDType;

@Component
public class _jczqHandle extends BaseLotteryHandle 
{
    @Autowired
    private _jczqXmlBean xmlBean;
    
    @Autowired
    private SysParamService sysParamService;
	
    @Override
	public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception 
	{
		return null;
	}

	@Override
	public LotteryType getLotteryType() 
	{
		return LotteryType.竞彩足球;
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

	@Override
	public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
			throws Exception 
	{
		PlanItem item = new PlanItem();
		try {
			if (validataBetNum(code)) {
				throw new Exception("竞彩足球投注号码格式错误!");
			}
		} 
		catch (Exception e) {
			throw new Exception("竞彩足球投注号码格式错误!");
		}

		item.setBetCount(validataBetCount(code));
		item.setContent(code);
		item.setPlayType(PlayType.valueOf(code.split("\\|")[0]));
		item.setOneMoney(oneMoney);
		return item;
	}

	@Override
	protected List<Ticket> unpackTicket(Order order, PlanItem item)
	{
		Integer maxMultple = Integer.parseInt(sysParamService.getSysParamByName(Constants.MAX_TICKET_MULTIPLE).getValue());
		List<Ticket> ticketList = new ArrayList<Ticket>();
		if(order.getMultiple()>maxMultple)
		{
			//按照倍数拆的票数
			int tmpNum = (order.getMultiple()/maxMultple)+1;
			//拆出的票最后一张的倍数
			int lastNum = order.getMultiple()%maxMultple;
			for (int i = 0; i < tmpNum; i++)
			{
				Ticket ticket = new Ticket();
				if(i<tmpNum-1)
				{
					ticket.setCount(item.getBetCount());
					ticket.setMoney(item.getOneMoney().multiply(new BigDecimal(item.getBetCount())).multiply(BigDecimal.valueOf(maxMultple)));
					ticket.setMultiple(maxMultple);
				}
				else
				{
					ticket.setCount(item.getBetCount());
					ticket.setMoney(item.getOneMoney().multiply(new BigDecimal(item.getBetCount())).multiply(BigDecimal.valueOf(lastNum)));
					ticket.setMultiple(lastNum);
				}
				
				ticket.setItem(item);
				ticket.setOrder(order);
				ticket.setSendTicketTime(Calendar.getInstance());
				ticket.setTermNo(order.getTerm().getTermNo());
				ticket.setType(order.getType());
				ticket.setContent(item.getContent());
				ticket.setPlayType(item.getPlayType());
				ticket.setType(order.getType());
				ticketList.add(ticket);
			}
		}
		else
		{
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
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
		}
		return ticketList;
	}
	
	//RQSPF|140520002=3/1,140519003=1|2*1
	//SPF|140521009=1,140521006=1,140521004=1,140521003=3,140521002=3|5*1
	//201102035001:[1,0]/ 201102036002:[3]/ 201102036003:[1,3] 
	//BQC|140524003=1-3,140524001=1-3,140523001=0-0,140523002=3-3|4*1
	//201102035001:[33,31]/ 201102036002:[30]/ 201102036003:[10,00] 
	//CBF|140521016=1:0/0:1,140521009=1:0/5:1,140521008=2:0,140521007=1:0|4*1
	//201102035001:[10,02]/ 201102036002:[12]/ 201102036003:[23,09] 
	//JQS|140522004=2/3/4/5/6/7,140522003=3/4/5/6/7,140522002=4/5/6/7,140522001=5/6/7|4*1
	//混合过关 301=201102035001:[3,0]/304=201102035001:[33,31] /302=201102036003:[23,09] 
	//HH|SPF>121210301=3/0,JQS>121210302=3/0,CBF>121210303=1:1/1:2,BQC>121210304=3-3/0-0|4*1
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
			String code = "";
			if(item.getPlayType() == PlayType.JZ_HH)
			{
				code = "["+codeArr[1].replaceAll("\\:", "").replaceAll("\\-", "").replaceAll("\\/", "\\,")+"]";
			}
			else if(item.getPlayType() == PlayType.CBF)
			{
				code = "["+codeArr[1].replaceAll("\\:", "").replaceAll("\\/", "\\,")+"]";
			}
			else if(item.getPlayType() == PlayType.BQC)
			{
				code = "["+codeArr[1].replaceAll("\\-", "").replaceAll("\\/", "\\,")+"]";
			}else
			{
				code = "["+codeArr[1].replaceAll("\\/", "\\,")+"]";
			}
			teamId = codes[i].split("=")[0];
			String date = "";
			String result = "";
			if(item.getPlayType() == PlayType.JZ_HH)
			{
				String[] hhs = teamId.split("\\>");
				date = "20"+hhs[1].substring(0, hhs[1].length()-3);
				result = hhs[1].substring(hhs[1].length()-3);
				Calendar calendar = DateUtil.parseYYYYMMDD(date);
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
	public boolean validataBetNum(String result) 
	{
		boolean evalRes;
		String[] codestr = result.split("\\|");
		if (codestr.length != 3) {
			return true;
		}

		String[] betCode = codestr[1].split(",");
		int former = Integer.parseInt(codestr[2].split("\\*")[0]);
		int latter = Integer.parseInt(codestr[2].split("\\*")[1]);

		if (PlayType.SPF.equals(PlayType.valueOf(codestr[0]))) {
			for (int i = 0; i < betCode.length; i++) {
				evalRes = betCode[i]
						.matches("[0-9]{2}[01][0-9][0-3][0-9][0-9]{3}=[013](/[01]){0,2}");
				if (!evalRes) {
					System.out.println("投注部分有问题！");
					return true;
				}
			}
			return !validateChuan(betCode.length, former, latter);
		} 
		else if (PlayType.JQS.equals(PlayType.valueOf(codestr[0]))) {
			for (int i = 0; i < betCode.length; i++) {
				evalRes = betCode[i]
						.matches("[0-9]{2}[01][0-9][0-3][0-9][0-9]{3}=[0-7](/[0-7]){0,7}");
				if (!evalRes)
					return true;
			}
			return !validateChuan(betCode.length, former, latter);
		} 
		else if (PlayType.CBF.equals(PlayType.valueOf(codestr[0]))) {
			for (int i = 0; i < betCode.length; i++) {
				evalRes = betCode[i]
						.matches("[0-9]{2}[01][0-9][0-3][0-9][0-9]{3}=[0-9]:[0-9](/[0-9]:[0-9]){0,30}");
				if (!evalRes)
					return true;
			}
			return !validateChuan(betCode.length, former, latter);
		} 
		else if (PlayType.BQC.equals(PlayType.valueOf(codestr[0]))) {
			for (int i = 0; i < betCode.length; i++) {
				evalRes = betCode[i]
						.matches("[0-9]{2}[01][0-9][0-3][0-9][0-9]{3}=[013]-[013](/[013]-[013]){0,8}");
				if (!evalRes)
					return true;
			}
			return !validateChuan(betCode.length, former, latter);
		} 
		else {
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
	public int validataBetCount(String code) 
	{
		String[] codestr = code.split("\\|");
		String[] betCode = codestr[1].split(",");
		ArrayList<Integer> bet = new ArrayList<Integer>();

		for (int i = 0; i < betCode.length; i++) {
			bet.add(betCode[i].split("/").length);
		}
		int former = Integer.parseInt(codestr[2].split("\\*")[0]);
		int latter = Integer.parseInt(codestr[2].split("\\*")[1]);

		/*
		 * System.out.print("Before calculation, bet array: "); for (int i=0; i<bet.size();
		 * i++) { System.out.print(bet.get(i)+" "); } System.out.print(" "+
		 * former +"串"+latter);
		 */

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
		}
		return 0;
	}

	// 计算在投注方案 bet 的m串1的投注数
	public int calculateBetCount(ArrayList<Integer> bet, int m, int begin)
	{
		if (m == 0) {
			return 1;
		} 
		else if (bet.size() - begin == m) {
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
			throws Exception 
	{
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
	public void parseXML(Element root) throws Exception
    {
	    try {
	        xmlBean.parseXML(root);	        
	    }
	    catch(Exception e) {
	        throw e;
	    }
	}

	static public void main(String argv[]) {
		String conent = "RQSPF|140520002=3/1,140519003=1|2*1".split("\\|")[1];
		System.out.println("RQSPF|140520002=3/1,140519003=1|2*1".split("\\|")[2].split("\\*")[0]);
		String[] codes = conent.split("\\,");
		StringBuffer strbuf = new StringBuffer();
		String teamId = "";
		for (int i = 0; i < codes.length; i++)
		{
			String[] codeArr = codes[i].split("\\=");
			String code = "["+codeArr[1].replaceAll("\\/", "\\,")+"]";
			teamId = codes[i].split("=")[0];
			String date = "20"+teamId.substring(0, teamId.length()-3);
			String result = teamId.substring(teamId.length()-3);
			Calendar calendar = DateUtil.parseYYYYMMDD(date);
			strbuf.append(date)
			.append(DateUtil.getDayOfWeek(calendar))
			.append(result)
			.append(":")
			.append(code);
			if(i != codes.length-1)
			{
				strbuf.append("/ ");	
			}
		}
		System.out.println(strbuf.toString());
		/*
		 * _jczqHandle h = new _jczqHandle(); String test0 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*1";
		 * String test1 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*6";
		 * String test2 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*7";
		 * String test3 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*15";
		 * String test4 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*20";
		 * String test5 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*22";
		 * String test6 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*35";
		 * String test7 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*42";
		 * String test8 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*50";
		 * String test9 =
		 * "CBF|100608001=0-1/1-3/0-1,100608001=0-1/1-3,100608001=1-3,100608001=0-1/1-3/0-9,100608001=0-1/1-3,100608001=1-3|6*57";
		 */
		// System.out.println(h.validataBetCount(test));
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
