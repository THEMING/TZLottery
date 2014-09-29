package com.xsc.lottery.web.action.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerLottery")
public class CustomerLotteryAction extends LotteryClientBaseAction
{
    @Autowired
    private LotteryOrderService orderService;
    
    @Autowired
    private LotteryTermService lotteryTermService;

    @Autowired
    private LotteryPlanService planService;

    private Page<Order> page;

    private Long id;

    private Order order;
    
    private List<Ticket> tickets;
    
    private String numberNo;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private LotteryType type;

    private LotteryType[] typeList = LotteryType.values();

    private OrderStatus[] statusList = OrderStatus.values();

    private Calendar beginTime;

    private Calendar endTime;

    private String status;
    
    private String content;

    // 代购查询
    public String index()
    {
        Customer customer = this.getCurCustomer();
        page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getToBuy(page, type, status, beginTime, endTime, customer);
        return SUCCESS;
    }

    public String detail()
    {   
    	Map<String,String> map = new HashMap<String,String>();
    	List<Map> haveRecordList = new ArrayList<Map>();
    	String result="",touzhu="";
		String ratio = "";
		Long ticketid;
		 List<MatchArrange> matchlist;
		Plan plan = planService.getPlanBynumberNo(numberNo);
		order = orderService.getOrderByPlan(plan);
        if(plan.getType().equals(LotteryType.竞彩足球) || plan.getType().equals(LotteryType.竞彩篮球)) {	
        	for(int j=0;j<plan.getItems().size();j++) {
        		Ticket ticket = plan.getItems().get(j).getTicket().get(0);
        		result="<table width=\"100%\">";
        		content = plan.getItems().get(j).getContent();
        		
        		
        		String[] s = content.split("\\|");
        		String[] s1 = s[1].split(",");
        		
        		String a="";
        		for(int i=0;i<s1.length;i++) {
        			String[] s11 = s1[i].split("=");
        			a = s11[0];
        			MatchArrange matchArrange = lotteryTermService.getMatchArrayByBoutIndex(a).get(0);
        			String right = "";
        			String special = "";
        			String[] resultsForTouZhu = s11[1].split("/");
        			for(int k=0; k<resultsForTouZhu.length; k++)
        			{
        				String rightTemp = changeJCLQToRightString(s[0], resultsForTouZhu[k]);
        				if(k != resultsForTouZhu.length-1)
        				{
        					right += rightTemp + "/"; 
        				}
        				else
        				{
        					right += rightTemp;
        				}
        			} 
        			if(plan.getType().equals(LotteryType.竞彩足球))
        			{
        				right = s11[1];
        			}
        			result+="<tr><td>投注:"+ right +" </td>";
        			if(haveRecordList.size() != 0)
        			{
        				int count = 0;
        				for(int k=0; k<haveRecordList.size(); k++)
            			{     
        					count++;
        					Map<String,String> tempMap = haveRecordList.get(k);
        					if(tempMap.containsKey(a))
        					{
//        						result += tempMap.get(a);
        						result = changeToUse(a, s11, s, result, map, haveRecordList, matchArrange, ticket, i, true, tempMap.get(a));
        						break;
        					}
            			}
        				if(count == haveRecordList.size())
        				{
        					result = changeToUse(a, s11, s, result, map, haveRecordList, matchArrange, ticket, i, false, "");
        				}			
        			}  
        			else
        			{
        				result = changeToUse(a, s11, s, result, map, haveRecordList, matchArrange, ticket, i, false, "");
        			}	
        			result=result+"</tr>";
        		}
        		
        		/*if(s[2].equals("2*1")) touzhu="2串1";
        		if(s[2].equals("3*1")) touzhu="3串1";
        		if(s[2].equals("4*1")) touzhu="4串1";
        		if(s[2].equals("5*1")) touzhu="5串1";
        		if(s[2].equals("6*1")) touzhu="6串1";
        		if(s[2].equals("7*1")) touzhu="7串1";
        		if(s[2].equals("8*1")) touzhu="8串1";
        		plan.getItems().get(j).setComments(touzhu + "<br>"+ result + "</table>");*/
        		plan.getItems().get(j).setComments(result + "</table>");
        	}
        	return "info";
        } else if(plan.getType().equals(LotteryType.足彩14场)) {
			String termNo = order.getTerm().getTermNo();
			 matchlist=lotteryTermService.getMatchResultByTermNo(termNo);
		
			for(int j=0;j<plan.getItems().size();j++) {
				content = plan.getItems().get(j).getContent();
				String[] contents = content.split(",");
				result="";
				for(int i=0;i<matchlist.size();i++) {
					String awaryTeam = matchlist.get(i).getAwaryTeam();
					String homeTeam = matchlist.get(i).getHomeTeam();
					String bifen=matchlist.get(i).getWholeScore();
					if(bifen==null) bifen="未开奖";
					result += homeTeam + "VS" + awaryTeam + " 投注:" + contents[i] +" 比分"+ bifen+"<br>";
					//System.out.println(reslut[0]);
				}
				plan.getItems().get(j).setComments(result);
			}	
			return "info";
		} else if(plan.getType().equals(LotteryType.足彩任9)) {
			String termNo = order.getTerm().getTermNo();
			matchlist=lotteryTermService.getMatchResultByTermNo(termNo);
			
			for(int j=0;j<plan.getItems().size();j++) {
				content = plan.getItems().get(j).getContent();
				String[] contents = content.split(",");
				result="";
				for(int i=0;i<matchlist.size();i++) {
					String awaryTeam = matchlist.get(i).getAwaryTeam();
					String homeTeam = matchlist.get(i).getHomeTeam();
					String bifen=matchlist.get(i).getWholeScore();
					if(bifen==null) bifen="未开奖";
					if(contents[i].equals("#")) {
						contents[i] = "未选";
					}
					result += homeTeam + "VS" + awaryTeam + " 投注：" + contents[i] +" 比分"+ bifen+"<br>";
					//System.out.println(reslut[0]);
				}
				plan.getItems().get(j).setComments(result);
			}	
			return "info";
		} else if(plan.getType().equals(LotteryType.足彩6场半)) {
			String termNo = order.getTerm().getTermNo();
			matchlist=lotteryTermService.getMatchResultByTermNo(termNo);
			
			for(int j=0;j<plan.getItems().size();j++) {
				content = plan.getItems().get(j).getContent();
				String[] contents = content.split(",");
				result="";
				for(int i=0;i<matchlist.size();i++) {
					String awaryTeam = matchlist.get(i).getAwaryTeam();
					String homeTeam = matchlist.get(i).getHomeTeam();
					String bifen=matchlist.get(i).getWholeScore();
					if(bifen==null) bifen="未开奖";
					String halfbifen=matchlist.get(i).getHalfScore();
					if(halfbifen==null) halfbifen="未开奖";
					result += homeTeam + "VS" + awaryTeam + " 投注: " + contents[i] + " " + contents[i+1] + "全场"+bifen+"半场"+halfbifen+"<br>";
					//System.out.println(reslut[0]);
				}
				plan.getItems().get(j).setComments(result);
			}
				
			return "info";
		} else if(plan.getType().equals(LotteryType.四场进球)) {
			String termNo = order.getTerm().getTermNo();
			matchlist=lotteryTermService.getMatchResultByTermNo(termNo);
			
			for(int j=0;j<plan.getItems().size();j++) {
				content = plan.getItems().get(j).getContent();
				String[] contents = content.split(",");
				result="";
				for(int i=0;i<matchlist.size();i++) {
					String awaryTeam = matchlist.get(i).getAwaryTeam();
					String homeTeam = matchlist.get(i).getHomeTeam();
					String bifen=matchlist.get(i).getWholeScore();
					if(bifen==null) bifen="未开奖";
					
					result += homeTeam + "VS" + awaryTeam + "          " + contents[i] + "          " + contents[i+1] +"比分"+bifen+ "<br>";
					//System.out.println(reslut[0]);
				}
				plan.getItems().get(j).setComments(result);
			}
				
			return "info";
		} else {
            for(int j=0;j<plan.getItems().size();j++) {
        		content = plan.getItems().get(j).getContent();
        		plan.getItems().get(j).setComments(content);
            }
            return "info";
        }
    }
    
    //第一次进循环或者haveRecordList中没有的时间调用的函数
    public String getResultString(String a, String[] s11, String[] s, String result, Map<String, String> map, List<Map> haveRecordList)
    {
    	MatchArrange matchArrange = lotteryTermService.getMatchArrayByBoutIndex(a).get(0);
    	String awaryTeam = matchArrange.getAwaryTeam();
		String homeTeam = matchArrange.getHomeTeam();
		String halfScore = matchArrange.getHalfScore();
		String rang = matchArrange.getConcede();
		if(halfScore==null) halfScore="未开奖";
		String wholeScore = matchArrange.getWholeScore();
		if(wholeScore==null) wholeScore="未开奖";
		
		String b = s11[1];
		if(s[0].equals("SPF"))
		{
			if(rang!=null)
			{
				String temp = "<td>" + homeTeam + "VS" + awaryTeam + "</td>" + "<td>让球:" + rang + "</td>  <td>赛果:全场" +wholeScore+ "</td> <td>半场"  +halfScore + "</td>";
				result = "<td>" + homeTeam + "VS" + awaryTeam + "</td>" + "<td>让球:"+ rang + "</td>  <td>赛果:全场" +wholeScore+ "</td> <td>半场"  +halfScore + "</td>" ;
				map.put(a, temp);
				haveRecordList.add(map);
			} else
			{
				String temp = "<td>" + homeTeam + "VS" + awaryTeam +"</td>  <td>赛果:全场" +wholeScore+ "</td> <td>半场"  +halfScore + "</td>" ;
				result = "<td>" + homeTeam + "VS" + awaryTeam +"</td>  <td>赛果:全场" +wholeScore+ "</td> <td>半场"  +halfScore + "</td>";
				map.put(a, temp);
				haveRecordList.add(map);
			}
		}
		else if(s[0].equals("CSF") || s[0].equals("RFSF") || s[0].equals("DXF") || s[0].equals("SFC"))
		{
			String temp = "<td>" + awaryTeam + "VS" + homeTeam +"</td>  <td>比分:" +wholeScore + "</td>";
			result = "<td>" + awaryTeam + "VS" + homeTeam +"</td>  <td>比分:" +wholeScore + "</td>";
			map.put(a, temp);
			haveRecordList.add(map);
		}
		else
		{
			String temp = "<td>" + homeTeam + "VS" + awaryTeam +"</td>  <td>赛果:全场" +wholeScore+ "</td>  <td>半场"  +halfScore + "</td>";
			result = "<td>" + homeTeam + "VS" + awaryTeam +"</td>  <td>赛果:全场" +wholeScore+ "</td>  <td>半场"  +halfScore + "</td>";
			map.put(a, temp);
			haveRecordList.add(map);
		}
		return result;
    }
  
    
    /**把篮球的投注结果显示成正确的形式*/
    public String changeJCLQToRightString(String type, String result)
    {
    	String right = "";
    	if(type.equals("CSF") || type.equals("RFSF"))
    	{
    		if(result.equals("1"))
    		{
    			right = "主胜";
    		}
    		else 
    		{
    			right = "主负";
    		}
    	}
    	else if(type.equals("DXF"))
    	{
    		if(result.equals("1"))
    		{
    			right = "大";
    		}
    		else 
    		{
    			right = "小";
    		}
    	}
    	else
    	{
    		if(result.equals("01"))
    		{
    			right = "主胜1-5分";
    		}
    		else if(result.equals("02"))
    		{
    			right = "主胜6-10分";
    		}
    		else if(result.equals("03"))
    		{
    			right = "主胜11-15分";
    		}
    		else if(result.equals("04"))
    		{
    			right = "主胜16-20分";
    		}
    		else if(result.equals("05"))
    		{
    			right = "主胜21-25分";
    		}
    		else if(result.equals("06"))
    		{
    			right = "主胜26+分";
    		}
    		else if(result.equals("11"))
    		{
    			right = "客胜1-5分";
    		}
    		else if(result.equals("12"))
    		{
    			right = "客胜6-10分";
    		}
    		else if(result.equals("13"))
    		{
    			right = "客胜11-15分";
    		}
    		else if(result.equals("14"))
    		{
    			right = "客胜16-20分";
    		}
    		else if(result.equals("15"))
    		{
    			right = "客胜21-25分";
    		}
    		else 
    		{
    			right = "客胜26+分";
    		}
    	}
    	return right;
    }
    
    /**把篮球的结果显示成正确的格式调用的函数*/
    // i表示现在是第几次循环   B表示现在是否在haveRecordList中取值
    public String changeToUse(String a, String[] s11, String[] s, String result, Map<String, String> map, List<Map> haveRecordList, MatchArrange matchArrange, Ticket ticket, int i, boolean b, String mapStr)
    {    
    	String score = matchArrange.getWholeScore();
    	if(StringUtils.isBlank(score) || score.indexOf(":") == -1 || score.split(":").length == 1)
    	{	
    		if(s[0].equals("RFSF"))
    		{
    			String rangfen = "";
    			String ratio = "";
    			if(s[2].equals("1*1"))
    			{
    				rangfen = matchArrange.getDanguanRangFen();
    				ratio = matchArrange.getSpRangfenSf();
    				if(ratio==null) ratio="未开奖";
    			}
    			else
    			{
    				rangfen = ticket.getTicketSpecial();
    				if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
        				if(ratioTemp.length > i)
        					ratio = ratioTemp[i];
        				else
        					ratio = "";
        			}
    			}
    			if(b)
    			{
    				if(rangfen == null)
    				{
    					result += "<td>让分: </td><td>赔率：" + ratio + "</td>" + mapStr;
    				}
    				else
    				{
    					result += "<td>让分： " + rangfen.split("\\|")[i] + "   </td><td>赔率：" + ratio + "</td>" + mapStr;
    				}	
    			}
    			else
    			{
    				if(rangfen == null)
    				{
    					result += "<td>让分：</td><td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    				}
    				else
    				{
    					result += "<td>让分： " + rangfen.split("\\|")[i] + "   </td><td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    				}
    				
    			}		
    		}
    		else if(s[0].equals("DXF"))
    		{
    			String dxf = "";
    			String ratio = "";
    			if(s[2].equals("1*1"))
    			{
    				dxf = lotteryTermService.getMatchArrayByBoutIndex(a).get(0).getDanguanDaXiaoQiu();
    				ratio = matchArrange.getSpDxf();
    				if(ratio==null) ratio="未开奖";
    			}
    			else
    			{
    				dxf = ticket.getTicketSpecial();
    				if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
    			}
    			if(b)
    			{
    				if(dxf == null)
    				{
    					result += "<td>大小分：</td><td>赔率：" + ratio + "</td>" + mapStr;
    				}
    				else
    				{
    					result += "<td>大小分：" + dxf.split("\\|")[i] + "   </td><td>赔率：" + ratio + "</td>" + mapStr;
    				}
    			}
    			else
    			{
    				if(dxf == null)
    				{
    					result += "<td>大小分：</td><td>赔率：" + ratio + "</td>" +this.getResultString(a, s11, s, result, map, haveRecordList);
    				}
    				else
    				{
    					result += "<td>大小分：" + dxf.split("\\|")[i] + "   </td><td>赔率：" + ratio + "</td>" +this.getResultString(a, s11, s, result, map, haveRecordList);
    				}	
    			}		
    		}
        	else if(s[0].equals("SFC"))
        	{
        		String ratio = "";
        		if(ticket.getRatio() == null)
    			{
    				ratio = "未开奖";
    			}
    			else
    			{
    				String[] ratioTemp = ticket.getRatio().split("\\|"); 
        			ratio = ratioTemp[i];
    			}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else if(s[0].equals("CSF"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpSf();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else if(s[0].equals("SPF"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpSfp();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else if(s[0].equals("JQS"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpJqs();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        	}
        	else if(s[0].equals("BQC"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpBcsfp();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        	}
        	else
        	{
        		String ratio = "";
        		if(ticket.getRatio() == null)
    			{
    				ratio = "未开奖";
    			}
    			else
    			{
    				String[] ratioTemp = ticket.getRatio().split("\\|"); 
        			ratio = ratioTemp[i];
    			}
    			if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        	}
    	}
    	else
    	{
    		String[] bifen = matchArrange.getWholeScore().split(":");
    		double hfenshu = Double.parseDouble(bifen[1]);
    		double afenshu = Double.parseDouble(bifen[0]);
    		String wanfajieguo = "";
        	if(s[0].equals("RFSF"))
    		{
    			String rangfen = "";
    			String ratio = "";
    			if(s[2].equals("1*1"))
    			{
    				rangfen = matchArrange.getDanguanRangFen();
    				ratio = matchArrange.getSpRangfenSf();
    				if(ratio==null) ratio="未开奖";
    			}
    			else
    			{
    				if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
    				rangfen = ticket.getTicketSpecial();
    			}
    			if(rangfen.contains("|")) 
    			{
    				String[] temp = rangfen.split("\\|");
    				rangfen = temp[i];
    			}
    			double num = Double.parseDouble(rangfen);
    			if(hfenshu+num > afenshu)
    			{
    				wanfajieguo = "主胜";
    			}
    			else
    			{
    				wanfajieguo = "主负";
    			}
    			if(b)
    			{
    				result += "<td>让分： " + rangfen + "</td>   <td>结果：" + wanfajieguo + "</td>   <td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>让分： " + rangfen + "</td>   <td>结果：" + wanfajieguo + "</td>   <td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
    			
    		}
    		else if(s[0].equals("DXF"))
    		{
    			String dxf = "";
    			String ratio = "";
    			if(s[2].equals("1*1"))
    			{
    				dxf = lotteryTermService.getMatchArrayByBoutIndex(a).get(0).getDanguanDaXiaoQiu();
    				ratio = matchArrange.getSpDxf();
    				if(ratio==null) ratio="未开奖";
    			}
    			else
    			{
    				dxf = ticket.getTicketSpecial();
    				if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
    			}
    			if(dxf.contains("|")) 
    			{
    				String[] temp = dxf.split("\\|");
    				dxf = temp[i];
    			}
    			double num = Double.parseDouble(dxf);
    			if(hfenshu+afenshu > num)
    			{
    				wanfajieguo = "大";
    			}
    			else
    			{
    				wanfajieguo = "小";
    			}
    			if(b)
    			{
    				result += "<td>大小分：" + dxf + "</td><td>结果：" + wanfajieguo + "</td><td>赔率：" + ratio +"</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>大小分：" + dxf + "</td><td>结果：" + wanfajieguo + "</td><td>赔率：" + ratio +"</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
    			
    		}
    		else if(s[0].equals("CSF"))
    		{
    			if(hfenshu > afenshu)
    			{
    				wanfajieguo = "主胜";
    			}
    			else
    			{
    				wanfajieguo = "主负";
    			}
    			String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpSf();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>结果：" + wanfajieguo + "</td>   <td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>结果：" + wanfajieguo + "</td>   <td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
    			
    		}
        	else if(s[0].equals("SFC"))
        	{
        		double cha = hfenshu - afenshu;
        		wanfajieguo = changeToSFCType(cha);
        		String ratio = "";
        		if(ticket.getRatio() == null)
    			{
    				ratio = "未开奖";
    			}
    			else
    			{
    				String[] ratioTemp = ticket.getRatio().split("\\|"); 
        			ratio = ratioTemp[i];
    			}
        		if(b)
    			{
        			result += "<td>结果：" + wanfajieguo + "</td><td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>结果：" + wanfajieguo + "</td><td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else if(s[0].equals("SPF"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpSfp();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else if(s[0].equals("JQS"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpJqs();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else if(s[0].equals("BQC"))
        	{
        		String ratio = "";
        		if(s[2].equals("1*1"))
        		{
        			ratio = matchArrange.getSpBcsfp();
        			if(ratio==null) ratio="未开奖";
        		}
        		else
        		{
        			if(ticket.getRatio() == null)
        			{
        				ratio = "未开奖";
        			}
        			else
        			{
        				String[] ratioTemp = ticket.getRatio().split("\\|"); 
            			ratio = ratioTemp[i];
        			}
        		}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
        	else
        	{
        		String ratio = "";
        		if(ticket.getRatio() == null)
    			{
    				ratio = "未开奖";
    			}
    			else
    			{
    				String[] ratioTemp = ticket.getRatio().split("\\|"); 
        			ratio = ratioTemp[i];
    			}
        		if(b)
    			{
        			result += "<td>赔率：" + ratio + "</td>" + mapStr;
    			}
    			else
    			{
    				result += "<td>赔率：" + ratio + "</td>" + this.getResultString(a, s11, s, result, map, haveRecordList);
    			}
        		
        	}
    	}
    	return result;
    }
    
    /**判断比赛结果的胜分差类型*/
    public String changeToSFCType(Double cha)
    {
    	String wanfajieguo = "";
    	if(cha >= 26)
    	{
    		wanfajieguo = "主胜26+分";
    	}
    	else if(cha >= 21)
    	{
    		wanfajieguo = "主胜21-25分";
    	}
    	else if(cha >= 16)
    	{
    		wanfajieguo = "主胜16-20分";
    	}
    	else if(cha >= 11)
    	{
    		wanfajieguo = "主胜11-15分";
    	}
    	else if(cha >= 6)
    	{
    		wanfajieguo = "主胜6-10分";
    	}
    	else if(cha >= 1)
    	{
    		wanfajieguo = "主胜1-5分";
    	}
    	else if(cha >= -5)
    	{
    		wanfajieguo = "客胜1-5分";
    	}
    	else if(cha >= -10)
    	{
    		wanfajieguo = "客胜6-10分";
    	}
    	else if(cha >= -15)
    	{
    		wanfajieguo = "客胜11-15分";
    	}
    	else if(cha >= -20)
    	{
    		wanfajieguo = "客胜16-20分";
    	}
    	else if(cha >= 25)
    	{
    		wanfajieguo = "客胜21-25分";
    	}
    	else
    	{
    		wanfajieguo = "客胜26+分";
    	}
    	return wanfajieguo;
    }
    
    public String info()
    {
        order = orderService.findById(id);
        //tickets = orderService.getTicketByOrder(order);
        return "info";
    }

    public void setPlanService(LotteryPlanService planService)
    {
        this.planService = planService;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = numberNo;
    }

    public Page<Order> getPage()
    {
        return page;
    }

    public void setPage(Page<Order> page)
    {
        this.page = page;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public Calendar getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime)
    {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Calendar endTime)
    {
        this.endTime = endTime;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public LotteryType[] getTypeList()
    {
        return typeList;
    }

    public OrderStatus[] getStatusList()
    {
        return statusList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Order getOrder()
    {
        return order;
    }

	public List<Ticket> getTickets() {
		return tickets;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
