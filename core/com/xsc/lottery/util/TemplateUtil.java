package com.xsc.lottery.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xsc.lottery.admin.action.system.SystemParamManagerAction;
import com.xsc.lottery.common.Constants;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.service.business.CpsReportService;
import com.xsc.lottery.service.business.SysParamService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil
{
	private static Configuration cfg = new Configuration();

	//获取订单内容的url
	private static String ORDER_DETAIL_EMAIL_CONTENT_URL = "http://www.yicp.com/customer/mailcontent/order_detail.php";
	//获取开奖内容的url
	private static String LOTTERY_DETAIL_EMAIL_CONTENT_URL = "http://www.baidu.com";
	
	private static String privateKey = "560faeed92c4bb9def698e9ee3817e5a";
	
	/**
	 * <pre>
	 * 生成模板信息
	 * </pre>
	 * @param username 用户名
	 * @param activeUrl url
	 * @param siteName 站点名称
	 * @param domain 域名
	 * @param reserved 预留字段
	 * @param templateFile 模板名称
	 * @return
	 * @throws Exception
	 */
	public static String makeTemplateContent(String username, String storeId, String activeUrl, String siteName, String domain,
			String reserved, String templateFile) throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("username", username);
		root.put("activeUrl", activeUrl);
		root.put("siteName", siteName);
		root.put("domain", domain);
		root.put("storeId", storeId);
		root.put("reserved", reserved);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	/**
	 * <pre>
	 * 生成短信模板信息
	 * </pre>
	 * @param validCode 验证码
	 * @param siteName 站点名称
	 * @param reserved 预留字段
	 * @param templateFile 模板名称
	 * @return
	 * @throws Exception
	 */
	public static String makeSMSTemplateContent(String validCode, String siteName, String reserved, String templateFile)
			throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("validCode", validCode);
		root.put("siteName", siteName);
		root.put("reserved", reserved);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}
	
	public static String getContentFromUrl(String url,Map map){
		HttpClient client = new HttpClient(); 
	      // 设置代理服务器地址和端口      
		StringBuffer params = new StringBuffer("");
		if(map!=null&&map.size()!=0){
			;
			Object[] s = map.keySet().toArray();
			params.append("?"+s[0].toString()+"="+MapUtils.getString(map,s[0]));
			for(int i = 1; i < map.size(); i++) {
				params.append("&"+s[i].toString()+"="+MapUtils.getString(map,s[i]));
			}
		}
		
	      //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
	      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
	         HttpMethod method=new GetMethod(url+params.toString()); 
	      //使用POST方法 
	      //HttpMethod method = new PostMethod("http://java.sun.com"); 
	      try
		{
			client.executeMethod(method);
			//打印服务器返回的状态 
		       if(method.getStatusCode()==200){
		    	 //打印返回的信息 
				      String content = new String(method.getResponseBody(),"UTF-8");
				      //释放连接 
				      method.releaseConnection(); 
				      return content;
		       }else{
		    	   throw new HttpException();
		       }
		      
		}
		catch (HttpException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		} 

	      
	}
	
	public static String getWinEmailContent(Order order){
		try
		{
			if(order==null){
				return "";
			}
			
			cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
			Map<String, String> root = new HashMap<String, String>();
			Template t = cfg.getTemplate("winOrderEmail.ftl", "utf-8");
			StringWriter writer = new StringWriter();
			root.put("realName",order.getCustomer().getRealName());
			root.put("orderNo",order.getNumberNo());
			root.put("winMoney",order.getWinMoney()+"");
			Map m = new HashMap();
			m.put("orderid", order.getId());
			m.put("customerid", order.getCustomer().getId());
			m.put("sign", MD5.digest(order.getId().toString()+order.getCustomer().getId().toString()+privateKey));
			
			root.put("orderDetail",getContentFromUrl(ORDER_DETAIL_EMAIL_CONTENT_URL,m));
			Map mm = new HashMap();
			mm.put("lotteryType", order.getType().ordinal());
			mm.put("termNo", order.getTerm().getTermNo());
			root.put("lotteryDetail",getContentFromUrl(LOTTERY_DETAIL_EMAIL_CONTENT_URL,mm));
			
			t.process(root, writer);
			writer.flush();
			writer.close();
			return writer.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (TemplateException e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getOrderDetailEmailContent(Order order){
		try
		{
			if(order==null){
				return "";
			}
			String ftlFile = "";
			if(order.getStatus()!=null&&!"".equals(order.getStatus())){
				if(OrderStatus.出票成功.equals(order.getStatus())){
					ftlFile = "paySuccess.ftl";
				}else if(OrderStatus.出票失败.equals(order.getStatus())){
					ftlFile = "payFail.ftl";
				}else if(OrderStatus.部分出票成功.equals(order.getStatus())){
					ftlFile = "paySuccessSome.ftl";
				}
			}
			
			cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
			Map<String, String> root = new HashMap<String, String>();
			Template t = cfg.getTemplate(ftlFile, "utf-8");
			StringWriter writer = new StringWriter();
			root.put("realName",order.getCustomer().getRealName());
			root.put("orderNo",order.getNumberNo());
			Map m = new HashMap();
			m.put("orderid", order.getId());
			m.put("customerid", order.getCustomer().getId());
			m.put("sign", MD5.digest(order.getId().toString()+order.getCustomer().getId().toString()+privateKey));
			
			
			
			root.put("orderDetail",getContentFromUrl(ORDER_DETAIL_EMAIL_CONTENT_URL,m));
//			root.put("orderDetail",getContentFromUrl("http://www.yicp.com/customer/mailcontent/order_detail.php",m));
			
			t.process(root, writer);
			writer.flush();
			writer.close();
			return writer.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (TemplateException e)
		{
			e.printStackTrace();
			return "";
		}
		
	}

	/**
	 * <pre>
	 *  生成出票和中奖模版
	 * </pre>
	 * @param domain
	 * @param storeId
	 * @param username
	 * @param sitename
	 * @param gameName
	 * @param gameId
	 * @param issueName
	 * @param anteCode
	 * @param bonusMoney
	 * @param amount
	 * @param money
	 * @param successNum
	 * @param totalNum
	 * @param multiple
	 * @param bonusFollow
	 * @param drawTime
	 * @param winCode
	 * @param state
	 * @param autoIsServerRandom
	 * @param templateFile
	 * @return
	 * @throws Exception
	 */
	public static String makeBonusAndPrintTicketTemplateContent(String domain, String storeId, String username,
			String sitename, String gameName, String gameId, String issueName, String anteCode, String bonusMoney,
			String buy306Name, String money, String successNum, String totalNum, String multiple, String bonusFollow,
			String drawTime, String winCode, String state, String autoIsServerRandom, String templateFile)
			throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/subscribe/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("domain", domain);
		root.put("sitename", sitename);
		root.put("storeId", storeId);
		root.put("username", username);
		root.put("gameName", gameName);
		root.put("gameId", gameId);
		root.put("issueName", issueName);
		root.put("bonusMoney", bonusMoney);
		root.put("buy306Name", buy306Name);
		root.put("money", money);
		root.put("successNum", successNum);
		root.put("totalNum", totalNum);
		root.put("multiple", multiple);
		root.put("bonusFollow", bonusFollow);
		root.put("drawTime", drawTime);
		root.put("winCode", winCode);
		root.put("anteCode", anteCode);
		root.put("state", state);
		root.put("autoIsServerRandom", autoIsServerRandom);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	/**
	 * <pre>
	 *  生成金额不足模版
	 * </pre>
	 * @param domain
	 * @param storeId
	 * @param username
	 * @param sitename
	 * @param money
	 * @param list
	 * @param templateFile
	 * @return
	 * @throws Exception
	 */
	public static String makeNotEnoughMoneyTemplateContent(String domain, String storeId, String username,
			String sitename, String money, List<Plan> list, String templateFile) throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/subscribe/");
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("domain", domain);
		root.put("sitename", sitename);
		root.put("storeId", storeId);
		root.put("username", username);
		root.put("money", money);
		root.put("list", list);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	public static void main(String args[]) throws Exception
	{
		
		
		
//		Map m = new HashMap();
//		m.put("orderid","2010");
//		m.put("customerid","1603");
//		m.put("sign","33");
//		//http://www.yicp.com/customer/mailcontent/order_detail.php?orderid=2010&customerid=1603&sign=
//		String content = getContentFromUrl("http://www.yicp.com/customer/mailcontent/order_detail.php",m);
//		System.out.println(content);
		// System.out.print(makeTemplateContent("zjt","www.baidu.com","registerActive.ftl"));
//		System.out.println(makeBonusAndPrintTicketTemplateContent("www.", "100", "100", "100", "100", "11", "100",
//				"01,02,03,04,05,06#07", "100", "100", "100", "100", "100", "100", "100", "100", "01,02,03,04,05,06#07",
//				"100", "是", "followPrintTicket.ftl"));
		// List<Plan> list = new ArrayList<Plan>();
		// for (int i = 0; i < 2; i++)
		// {
		// Plan plan = new Plan();
		// plan.setGameId(11);
		// plan.setSuccessCount(10);
		// plan.setTotalIssueCount(13);
		// plan.setBetMoney(2);
		// plan.setAutoLastIssueName("2012001");
		// plan.setIssueName("2012002");
		// plan.setStatusNote("2010-10-18 19：30（星期四）");
		// list.add(plan);
		//			
		// }
		// System.out.println(makeNotEnoughMoneyTemplateContent("www.happycp.com", "100", "test", "开心彩票", "1.26", list,
		// "not_enough_money.ftl"));
	}

}
