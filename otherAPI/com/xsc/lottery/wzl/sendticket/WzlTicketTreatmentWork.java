package com.xsc.lottery.wzl.sendticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.OrderQueue;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.handle._lsscHandle;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.OrderQueueService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.task.message.MessageTaskExcutor;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.MD5;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.util.NetWorkUtil;
import com.xsc.lottery.util.StringUtil;
import com.xsc.lottery.wzl.service.Xml2000Service;
import com.xsc.lottery.wzl.service.Xml2001Service;
import com.xsc.lottery.wzl.service.Xml2002Service;
import com.xsc.lottery.wzl.service.Xml2018Service;
import com.xsc.lottery.wzl.service.Xml2030Service;
import com.xsc.lottery.wzl.service.XmlServiceClass;

@Component("wzlTicketTreatmentWork")
public class WzlTicketTreatmentWork extends TicketTreatmentWork
{	
    private String wzlHttpUrl = Configuration.getInstance().getValue("wzl.url");
    private String cardno = Configuration.getInstance().getValue("wzl.cardno");
    private String cardtype = Configuration.getInstance().getValue("wzl.cardtype");
    private String email = Configuration.getInstance().getValue("wzl.email");
    private String mobile = Configuration.getInstance().getValue("wzl.mobile");
    private String realName = Configuration.getInstance().getValue("wzl.realName");
    private String agentId = Configuration.getInstance().getValue("wzl.agentId");
    private String agentPwd = Configuration.getInstance().getValue("wzl.agentPwd");
    private String username = Configuration.getInstance().getValue("wzl.username");
    
	@Autowired
    private LotteryHandleFactory lotteryHandleFactory;
	
//	@Autowired
//	private LotteryPlanService lotteryPlanService;
	
	@Autowired
    private LotteryOrderService orderService;
	
//	@Autowired
//    private SupplierService supplierService;
	
	@Autowired
	private SmsLogService smsLogService;
	
	@Autowired
    private OrderQueueService orderQueueService;
	
    @Autowired
    public MessageTaskExcutor messageTaskExcutor;
    
    @Autowired
    private AdminMobileService adminMobileService;
	
	@Override
	protected boolean allowed(LotteryTerm term) 
	{
		return true;
	}

	@Override
	protected List<Ticket> takeTicket(Order order, List<PlanItem> planItems)
			throws Exception 
	{
		return lotteryHandleFactory.getLotteryHandleFactory(order.getType())
        		.getTicketByPlanItems(order, planItems,getTicketPlat());
	}
	
	private String getErrorMsg(String errorcode)
	{
		String szErrMsg = "未知错误";
		if(errorcode.compareTo("0") == 0)
			szErrMsg = "成功";
		else if(errorcode.compareTo("100") == 0)
			szErrMsg = "请求CTRL格式不正确";
		else if(errorcode.compareTo("101") == 0)
			szErrMsg = "AgentID为空";
		else if(errorcode.compareTo("102") == 0)
			szErrMsg = "请求的cmd为空";
		else if(errorcode.compareTo("103") == 0)
			szErrMsg = "请求的cmd值不一致";
		else if(errorcode.compareTo("104") == 0)
			szErrMsg = "调用的请求方法不存在";
		else if(errorcode.compareTo("105") == 0)
			szErrMsg = "调用的请求方法不允许";
		else if(errorcode.compareTo("106") == 0)
			szErrMsg = "请求的版本不能为空";
		else if(errorcode.compareTo("107") == 0)
			szErrMsg = "请求的版本不正确";
		else if(errorcode.compareTo("108") == 0)
			szErrMsg = "签名不正确";
		else if(errorcode.compareTo("111") == 0)
			szErrMsg = "请求的方法禁用";
		else if(errorcode.compareTo("112") == 0)
			szErrMsg = "AgentID被禁用";
		else if(errorcode.compareTo("200") == 0)
			szErrMsg = "支付失败";
		else if(errorcode.compareTo("301") == 0)
			szErrMsg = "投注格式不正确或者玩法不支持";
		else if(errorcode.compareTo("302") == 0)
			szErrMsg = "订单投注倍数超过最大限制";
		else if(errorcode.compareTo("303") == 0)
			szErrMsg = "投注票金额不正确";
		else if(errorcode.compareTo("304") == 0)
			szErrMsg = "投注期次过期或者期次不存在";
		else if(errorcode.compareTo("305") == 0)
			szErrMsg = "订单号重复";
		else if(errorcode.compareTo("306") == 0)
			szErrMsg = "订单创建失败";
		else if(errorcode.compareTo("310") == 0)
			szErrMsg = "最近一场开赛时间错误";
		else if(errorcode.compareTo("350") == 0)
			szErrMsg = "查询的订单不存在";
		else if(errorcode.compareTo("360") == 0)
			szErrMsg = "查询的奖期不存在";
		else if(errorcode.compareTo("361") == 0)
			szErrMsg = "参数错误";
		else if(errorcode.compareTo("362") == 0)
			szErrMsg = "未结期";
		else if(errorcode.compareTo("400") == 0)
			szErrMsg = "请求的xml消息验证schema失败";
		else if(errorcode.compareTo("401") == 0)
			szErrMsg = "请求的xml消息验证schema失败";
		else if(errorcode.compareTo("402") == 0)
			szErrMsg = "IP限制";
		else
			szErrMsg = "未知错误";
		return szErrMsg;
	}
	 	
	@Override
	protected void deliveTicket(Ticket ticket) 
	{
		logger.info("我中啦 deliveTicket------->>>>>>>>>>");
    	String requestXML = createXMLByType(ticket);
		String xml = "";
		try {
            xml = NetWorkUtil.getHttpUrl(wzlHttpUrl, requestXML, "");
            logger.info("睿朗阳光投注请求信息："+requestXML.toString());
            logger.info("睿朗阳光投注请求的响应信息:"+xml);
            if (StringUtils.isBlank(xml)) {
            	logger.info("blankxml");
                return;
            }
            Map<String, String> returnMap = XmlServiceClass.parseResponse(xml);
            String xCode = returnMap.get("errorcode");
            if ("0".equals(xCode)) {
            	//我中啦已保存
                ticket.setStatus(TicketStatus.出票中);
            }
            else {
                ticket.setStatus(TicketStatus.出票失败);
                ticket.setOtherMsg(xCode);
                SystemWarningNotify.addWarningDescription("ID号："
                        + ticket.getId() + " 我中啦出票失败,返回失败结果："
                        + xCode + " , 原因:" + getErrorMsg(xCode));               
            }
		}
		catch (Exception e) {
			e.printStackTrace();
            logger.info("=======请求参数  ========"
                    + buildRequestParam("2030", requestXML.toString(), ticket
                            .getOtherOrderID()));
            logger.info("=======接口返回结果========" + xml);
            logger.warn("票编号：" + ticket.getId() + "的票在出票中出现异常.");
            SystemWarningNotify.addWarningDescription("我中啦出票异常：" + e);
		}
	}
	
	@Override
	protected void deliveTicket(List<Ticket> tickets) 
	{
		logger.info("deliveTickets with size " + tickets.size());
	}
	
	@Override
	protected void checkTicket(Ticket ticket) 
	{
		logger.info("我中啦 check ticket------->>>>>>>>>>");
		Xml2002Service service = new Xml2002Service();
		String requestXML = service.getXml(agentId, agentPwd, ticket.getOtherOrderID());
		requestXML = "cmd=2002&msg=" + requestXML;
        String xml = "";
        try {
            xml = NetWorkUtil.getHttpUrl(wzlHttpUrl, requestXML, "");
            logger.info("睿朗阳光票查询请求信息："+requestXML.toString());
            logger.info("睿朗阳光票查询请求的响应信息:"+xml);
            if (StringUtils.isBlank(xml)) {
                return;
            }
            Map<String, String> returnMap = service.parseResponse(xml);
            String xCode = returnMap.get("errorcode");
            if ("0".equals(xCode)) {
            	String xStatus = returnMap.get("TicketStatus");
            	if(xStatus.equals("W"))
            	{
            		ticket.setStatus(TicketStatus.出票中);
            	}
            	else if(xStatus.equals("Y"))
            	{
                    ticket.setStatus(TicketStatus.已出票);
            	}
            	else// if(xStatus.equals("N"))
            	{
                    ticket.setStatus(TicketStatus.出票失败);
                    ticket.setOtherMsg(returnMap.get("xMesg"));
            	}
            }
            else {// 接口查询信息失败
                ticket.setStatus(TicketStatus.出票失败);
                SystemWarningNotify.addWarningDescription("ID号："
                        + ticket.getId() + " 我中啦检票失败,返回失败结果："
                        + xCode + " , 原因:" + getErrorMsg(xCode));
            }           
        }
        catch (Exception e) {
            ticket.setStatus(TicketStatus.出票中);
            System.out.println("=======请求参数  ========"
                    + buildRequestParam("2012", requestXML.toString(), ticket
                            .getOtherOrderID()));
            System.out.println("=======接口返回结果========" + xml);
            logger.warn("票编号：" + ticket.getId() + "的票在检票中出现异常.");
            SystemWarningNotify.addWarningDescription("我中啦检票异常：" + e);
        }
 	}
	
	@Override
	protected void checkTicket(List<Ticket> tickets) 
	{
		logger.info("我中啦 check tickets with size " + tickets.size());
		//todo bulk check 20111128
    	
    	if(tickets.size() < 1) {
    		return;
    	}
    	
    	for(int i = 0; i < tickets.size(); i++) {
    		Ticket ticket = tickets.get(i);
    		checkTicket(ticket);
    	}
 	}
    /**
     * 我中啦查询竞彩玩法出票成功的票的赔率
     */
	@Override
    protected void checkTicketSP(Ticket ticket)
    {
    	logger.info("我中啦 check ticket SP------------>>>>>>>>>>>>>");
		Xml2002Service service = new Xml2002Service();
		String requestXML = service.getXml(agentId, agentPwd, ticket.getOtherOrderID());
		requestXML = "cmd=2002&msg=" + requestXML;
        String xml = "";
        try {
            xml = NetWorkUtil.getHttpUrl(wzlHttpUrl, requestXML, "");
            if (StringUtils.isBlank(xml)) {
                return;
            }
            Map<String, String> returnMap = service.parseResponse(xml);
            String xCode = returnMap.get("errorcode");
            if ("0".equals(xCode)) {
            	String xStatus = returnMap.get("TicketStatus");
            	if(xStatus.equals("Y"))
            	{
                    String xValue = returnMap.get("errmsg"); //赔率用errmsg返回
                    // 参照 Ticket.java中定义的赔率字段的格式
                    String[] use = service.getUse(xValue);
                    String ratio = use[0];
                    String special = null;
                    if(use.length > 1)	special = use[1];//dxf rf
            		if(ratio != null && !ratio.equals("") && !ratio.equals("SUCCESS"))
            			ticket.setRatio(ratio);
            		if(special != null && !special.equals("")) {
            			ticket.setTicketSpecial(special);
            		}
            	}
            }
	        else {// 接口查询信息失败
	            SystemWarningNotify.addWarningDescription("ID号："
	                    + ticket.getId() + " 我中啦检查赔率失败,返回失败结果："
	                    + xCode + " , 原因:" + getErrorMsg(xCode));
	        }           
		}
		catch (Exception e) {
		    System.out.println("=======请求参数  ========"
		            + buildRequestParam("2002", requestXML.toString(), ticket
		                    .getOtherOrderID()));
		    System.out.println("=======接口返回结果========" + xml);
		    logger.warn("票编号：" + ticket.getId() + "的票在检查赔率中出现异常.");
		    SystemWarningNotify.addWarningDescription("我中啦检查赔率异常：" + e);
		}
    }
 	@Override
	public String getJiangjin(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getOpenResult(LotteryTerm term) {
		logger.info("我中啦获取开奖结果 getOpenResult------->>>>>>>>>>");
		System.out.println("=================获取开奖结果===========================");
		Xml2000Service service = new Xml2000Service();
		String issue = term.getTermNo();
		if(term.getType() == LotteryType.十一运夺金)
		{
			issue = issue.substring(2);
		}
		String requestXML = service.getXml(agentId, agentPwd, typeToWZLType(term.getType()), issue);
		requestXML = "cmd=2000&msg=" + requestXML;
		String xml = "";
        try {
            xml = NetWorkUtil.getHttpUrl(wzlHttpUrl, requestXML, "");
            if (StringUtils.isBlank(xml))
            	return;
            Map<String, String> returnMap = service.parseResponse(xml);
            String xCode = returnMap.get("errorcode");
            if ("0".equals(xCode)) {
            	if (Integer.valueOf(returnMap.get("status")) >= 4) {
            		String result = returnMap.get("bonuscode");
            		logger.info("睿朗阳光返回"+term.getType()+"第"+term.getTermNo()+"开奖号码:"+result);
            		//String string = "";
            		if(term.getType() == LotteryType.老11选5)
            		{
            			result = result.replaceAll(" ", ",");
            		}
            		/*
            		for (int i = 0; i < result.length(); i = i + 2) {
            			string += result.substring(i, i+2) + ","; 
					}
					*/
            		//string = string.substring(0, string.length() - 1);
            		term.setResult(result);
            		System.out.println("获取开奖结果成功 ：" + result);
            		_lsscHandle _lsscHandle = new _lsscHandle();
            		_lsscHandle.fetchPrizeLevel(term);
				}
            	else {// 接口查询信息失败
  	        	  throw new Exception(term + "期获取开奖结果数据失败,返回失败结果："
  	                        + returnMap.get("xCode"));
            	}
            }
	        else {// 接口查询信息失败
	        	  throw new Exception(term + "期获取开奖结果数据失败,返回失败结果："
	                        + returnMap.get("xCode"));
	        }           
		}
		catch (Exception e) {
			System.out.println("==============获取结果失败===========" + e);
			if (term.getType().equals(LotteryType.老11选5)||term.getType().equals(LotteryType.重庆时时彩)||term.getType().equals(LotteryType.十一运夺金)) {
				// 快频  间隔 10秒 
				System.out.println("=======接口返回结果========" + xml);
                logger.warn(term + "期获取开奖结果数据异常.==>" + e.getMessage());
                SystemWarningNotify.addWarningDescription("我中了取开奖结果异常：" + e);
				try {
	                Thread.sleep(10000);
	            }
	            catch (InterruptedException e1) {
	            }
	            Calendar time = (Calendar) term.getOpenPrizeTime().clone();
	            time.add(Calendar.MINUTE, 4);
	            if (time.compareTo(Calendar.getInstance()) > 0) {
	            	getOpenResult(term);
				}
	            else {
					logger.warn("获取结果连续失败，无法获取结果，获取结果结束");
					String str = "【一彩票网】第" + term.getTermNo() + "期  " + term.getType() + "自动开奖失败，没有获取到结果！" ;
					List<AdminMobile> adminMobiles = adminMobileService.getAllAdminMobile();
					for (AdminMobile adminMobile : adminMobiles) {
						smsLogService.saveSmsLog(adminMobile.getMobile(), str, null,SmsLogType.WARN);
						//messageTaskExcutor.addNotifySM(adminMobile.getMobile(), str);
					}
				}
			}
			else{
				// 重复抓取 规则 开奖时间 下期结束时间-1秒
	            Long s = lotteryHandleFactory.getLotteryHandleFactory(
	                    term.getType()).getNextTerm(term).getStopSaleTime()
	                    .getTimeInMillis();
	            s = s - 60000l;
	            if (System.currentTimeMillis() - s >= 0) {
	            	System.out.println("=======接口返回结果========" + xml);
	                logger.warn(term + "期获取开奖结果数据异常.==>" + e.getMessage());
	                SystemWarningNotify.addWarningDescription("我中了取开奖结果异常：" + e);
	                return;
	            }
	            try {
	                Thread.sleep(60000);
	            }
	            catch (InterruptedException e1) {
	            }
	            getOpenResult(term);
			}
		}
	}

	@Override
	public SendTicketPlat getTicketPlat() 
	{
		return SendTicketPlat.我中啦;
	}

 	public static void main(String[] args) {
//		WzlTicketTreatmentWork ticketTreatmentWork = new WzlTicketTreatmentWork();
//		LotteryTerm term = new LotteryTerm();
//		term.setType(LotteryType.老11选5);
//		term.setTermNo("12082930");
//		ticketTreatmentWork.getOpenResult(term);
// 		String s = "01 02 03 04 05";
// 		System.out.println(s.replaceAll(" ", ","));
	}
	
	@Override
	public List<winTicketDis> getWinTicketByTerm(LotteryTerm term) {
		logger.warn("========================获取中奖票==========================");
		Xml2018Service service = new Xml2018Service();
		String issue = term.getTermNo();
		if(term.getType() == LotteryType.十一运夺金)
		{
			issue = issue.substring(2);
		}
		String requestXML = service.getXml(agentId, agentPwd, typeToWZLType(term.getType()), issue);
		requestXML = "cmd=2018&msg=" + requestXML;
		System.err.println("requestXML = " + requestXML);
		String xml = "";
        try {
        	logger.info("向睿朗阳光发送奖期中奖查询请求：" + requestXML);
            xml = NetWorkUtil.getHttpUrl(wzlHttpUrl, requestXML, "");
            logger.info("睿朗阳光发送奖期中奖查询请求响应：" + xml);
           // System.out.println(xml);
//            xml = "<?xml version='1.0' encoding='UTF-8'?><msg v='1.0' id='1346204255992'><ctrl><agentID>800001</agentID><cmd>2003</cmd><timestamp>1346204263542</timestamp><md>5f50959b8ac020e39690d2954f7adc1a</md></ctrl><body><response errorcode='0'><bonusquery><issue lotoid='107' issue='2012097'/><bonusItem money='5.0' bonuscls='1' ticketid='00212012081700000923'/><bonusItem money='10.0' bonuscls='1' ticketid='00212012081700000967'/><bonusItem money='5.0' bonuscls='1' ticketid='00212012081700000972'/><bonusItem money='5.0' bonuscls='1' ticketid='00212012081700001033'/><bonusItem money='15.0' bonuscls='1' ticketid='00232012081900010889'/><bonusItem money='10.0' bonuscls='1' ticketid='00232012081900010915'/><bonusItem money='5.0' bonuscls='1' ticketid='00232012081900010936'/><bonusItem money='5.0' bonuscls='1' ticketid='00232012081900011037'/><bonusItem money='5.0' bonuscls='1' ticketid='00232012081900011168'/></bonusquery></response></body></msg>";
            if (StringUtils.isBlank(xml)) {
            	return null;
            }
            Map<String, String> returnMap = service.parseResponse(xml);
            String errorcode = returnMap.get("errorcode");
            if ("0".equals(errorcode)) {
            	if (returnMap.get("lotoid").equals(typeToWZLType(term.getType()))) {
            		List<winTicketDis> list = new ArrayList<winTicketDis>();
					String ticket = returnMap.get("tickets");
					logger.info("睿朗阳光返回"+term.getType()+"第"+term.getTermNo()+"中奖数据:"+ticket);
					if(!StringUtil.isEmpty(ticket))
					{
						String[] tickets = ticket.split(",");
						for (int i = 0; i < tickets.length; i++) {
							list.add(new winTicketDis(tickets[i].split("-")[0], new BigDecimal(tickets[i].split("-")[1]), new BigDecimal(tickets[i].split("-")[1])));
							logger.info("中奖id：" + tickets[i].split("-")[0]);
						}
					}
					return list;
				}           
            	else
	                throw new Exception(term + "期获取中奖数据失败,返回失败结果："
	                        + returnMap.get("xCode"));
            }
            else if (errorcode.equals("360")) {
            	logger.warn("彩种为：" + term.getType() + ", 期号为：" + term.getTermNo() + ", 没有用户中奖");
			}
	        else {// 接口查询信息失败
	        	  throw new Exception(term + "期获取开奖结果数据失败,返回失败结果："
	                        + returnMap.get("xCode"));
	        }   
        }catch (Exception e) {
            logger.warn(term + "期获取中奖数据异常.==>" + e.getMessage());
            SystemWarningNotify.addWarningDescription("我中啦获取中奖ticket异常：" + e);
            e.printStackTrace();
		}
		return null;
	}
	
	
	/**
     * 生成出票端的参数字符串
     */
    private String buildRequestParam(String wAction, String wParam, String msgID)
    {
        StringBuilder mysign = new StringBuilder();
        mysign.append(agentId).append(wAction).append(msgID).append(wParam)
                .append(agentPwd);
        StringBuilder param = new StringBuilder();
        param.append("wAgent=").append(agentId).append("&");
        param.append("wAction=").append(wAction).append("&");
        param.append("wMsgID=").append(msgID).append("&");
        param.append("wParam=").append(wParam).append("&");
        param.append("wSign=").append(MD5.digest(mysign.toString()));
        return param.toString();
    }
    
 
    
    public String typeToWZLType(String type, String guanshu)
    {
    	if(type.equals("RQSPF"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "311";
    		}
    		else
    		{
    			return "301";
    		}
    	}else if(type.equals("SPF"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "321";
    		}
    		else
    		{
    			return "320";
    		}
    	}else if(type.equals("CBF"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "312";
    		}
    		else
    		{
    			return "302";
    		}
    	}else if(type.equals("JQS"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "313";
    		}
    		else
    		{
    			return "303";
    		}
    	}else if(type.equals("BQC"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "314";
    		}
    		else
    		{
    			return "304";
    		}
    	}else if(type.equals("JZ_HH"))
    	{
    		return "305";
    		
    	}else if(type.equals("SF"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "317";
    		}
    		else
    		{
    			return "307";
    		}
    	}
    	
    	else if(type.equals("RFSF"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "316";
    		}
    		else
    		{
    			return "306";
    		}
    	}
    	else if(type.equals("SFC"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "318";
    		}
    		else
    		{
    			return "308";
    		}
    	}
    	else if(type.equals("DXF"))
    	{
    		if(guanshu.equals("1"))
    		{
    			return "319";
    		}
    		else
    		{
    			return "309";
    		}
    	}
    	else if(type.equals("JL_HH"))
    	{
    		return "310";
    		
    	}
    	else
    	{
    		throw new RuntimeException("彩票类型错误");
    	}
    }
    
    private String typeToWZLType(LotteryType lt)
    {
    	if (lt.equals(LotteryType.双色球)) {
			return "001";
		}
    	else if (lt.equals(LotteryType.福彩3d)) {
			return "002";
		}
		else if (lt.equals(LotteryType.大乐透)) {
			return "113";		
		}
		else if (lt.equals(LotteryType.七乐彩)) {
			return "003";
		}
		else if (lt.equals(LotteryType.排列三)) {
			return "108";
		}
		else if (lt.equals(LotteryType.排列五)) {
			return "109";
		}
		else if (lt.equals(LotteryType.七星彩)) {
			return "110";
		}
		else if (lt.equals(LotteryType.四场进球)) {
			return "116";
		}
		else if (lt.equals(LotteryType.足彩14场)) {
			return "117";
		}
		else if (lt.equals(LotteryType.足彩6场半)) {
			return "115";
		}
		else if (lt.equals(LotteryType.足彩任9)) {
			return "118";
		}
		else if (lt.equals(LotteryType.上海时时乐)) {
			return "007";
		}
		else if (lt.equals(LotteryType.重庆时时彩)) {
			return "018";
		}
		else if (lt.equals(LotteryType.老11选5)) {
			return "106";
		}
		else if(lt.equals(LotteryType.十一运夺金)){
			return "107";
		}
		else {
			return "对不起，目前不支持该彩种";
		}
    }

	@Override
	protected List<OrderQueue> getOrderQueue()
			throws Exception {
		// TODO Auto-generated method stub
		List<OrderQueue> allList = orderQueueService.getAllOrderQueueListByPlat(0);
		return allList;
	}
	
	@Override
	protected void deleteOrderQueue(List<OrderQueue> allList) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0; i<allList.size(); i++)
    	{
    		OrderQueue orderQueue = allList.get(i);
    		Order order = orderService.findById(orderQueue.getOrderId());
    		
    	/*
    		int typeNum = LotteryTypeUtil.changeLotteryTypeToNum(order.getTerm().getType());
			int nCode = orderQueue.getSendTicketPlat();
			
			List<Supplier> sl = supplierService.getAllActiveSupplierList(typeNum);
			
			if(sl.size()>0)
			{
				Supplier supplierActive = sl.get(0);
				if(nCode != supplierActive.getCode())
				{
					orderQueue.setSendTicketPlat(supplierActive.getCode());
					orderQueueService.save(orderQueue);
					continue;	
				}
			}
			*/
			
    		take(order);
//    		Long orderId = order.getId();
    		
    		orderQueue.setStatus(1);
    		orderQueueService.save(orderQueue);
    	}
		orderQueueService.deleteAllStatus1(0);	
	}
	
    private String createXMLByType(Ticket ticket)
    {
		
		if (StringUtils.isBlank(ticket.getOtherOrderID())) {
            ticket.setOtherOrderID(MathUtil.getSerialNumber(20));
        }
		orderService.saveTicket(ticket);
    	LotteryType lt = ticket.getType();
    	String requestXML = "";
    	if (lt.equals(LotteryType.竞彩足球) || lt.equals(LotteryType.竞彩篮球)) {
    		Xml2030Service service = new Xml2030Service();
    		String[] contents = ticket.getContent().split("\\|");
    		String guanshu = contents[2].split("\\*")[0];
    		String type = contents[0];
    		if("HH".equals(type)&&ticket.getType() == LotteryType.竞彩足球)
    		{
    			type = "JZ_HH";
    		}
    		else if("HH".equals(type)&&ticket.getType() == LotteryType.竞彩篮球)
    		{
    			type = "JL_HH";
    		}
    		String lotoid = this.typeToWZLType(type, guanshu);
    		requestXML = service.getXml(lotoid, cardno, cardtype, email, mobile, realName, agentId, agentPwd, ticket, username);
    		requestXML = "cmd=2030&msg=" + requestXML;
		}
    	else {
			Xml2001Service service = new Xml2001Service();
			String lotoid = this.typeToWZLType(lt);
			requestXML = service.getXml(lotoid, cardno, cardtype, email, mobile, realName, agentId, agentPwd, ticket, username);
			requestXML = "cmd=2001&msg=" + requestXML;
		}

    	return requestXML;
    }
    
	@Override
	 public void putOrderToQueue(Order order)
	    {
	    	OrderQueue orderQueue = new OrderQueue();
	    	orderQueue.setOrderId(order.getId());
	    	orderQueue.setStatus(0);
	    	orderQueue.setSendTicketPlat(0); 
	    	orderQueueService.save(orderQueue);
	    }
	/*
    public static void main(String argv[])
    {
		String lotoid = "301";
		String agentId = "800030";
		String agentPwd = "yt7u9dfj40qm";
		String issue = "issue";
		String cardtype = "1";
		String cardno = "420984198810206336";
		String email = "lizuowei@369cai.com";
		String mobile = "15510146031";
		String realName = "lizuowei";
		String sub = "sub";
		String betSum = "betSum";
		String multiple = "1";
		String type = "type";
		String ticket = "ticket";
		String seq = "seq";
		String areaId = "00";
		String username = "lizuowei";
		String timePre = "timePre";
		Xml2030Service xml2030Service = new Xml2030Service();
        String content = xml2030Service.getXml(lotoid, cardno, 
        		cardtype, email, mobile, 
        		realName, agentId, agentPwd, sub, betSum, 
        		multiple, type, ticket, issue, seq, areaId, 
        		username,timePre);
		String reContent = UrlUtil.httpClientUtils("http://119.57.74.210:7070/billservice/sltAPI", "2030", content);
		
		System.out.println(reContent);
    }
    */
    /*
    public void test()
    {
    	Ticket ticket = new Ticket();
    	ticket.setBetContent("00-01-01,02,03,04,05,06#02");
    	ticket.setMultiple(1);
    	ticket.setMoney(BigDecimal.valueOf(2));
    	ticket.setIssue("2012046");
    	ticket.setType(LotteryType.双色球);
    	ticket.setPlayType(PlayType.fs);
    	ticket.setOrder(order);
    	ticket.setItem(item);
    	ticket.setCount(1);
    	ticket.setContent("fs-01,02,03,04,05,06|02");
    	ticket.setStatus(TicketStatus.未送票);
    	ticket.setTermNo("2012046");
    	System.out.println(ticket);
    	orderService.saveTicket(ticket);
    	this.deliveTicket(ticket);
    }
    
    public static void main(String[] args)
    {
    	WzlTicketTreatmentWork wtw = new WzlTicketTreatmentWork();
    	wtw.test();
    }*/

	@Override
	protected void queryTerm(LotteryType type) {
		
	}
}
