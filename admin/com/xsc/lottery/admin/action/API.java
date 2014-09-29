package com.xsc.lottery.admin.action;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.web.action.LotteryBaseAction;

@Scope("prototype")
@Controller("admin.api")
public class API extends LotteryBaseAction {

	private static final long serialVersionUID = 1L;
	
	public transient final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String INTERFACE_IP = Configuration.getInstance().getValue("interface.ip");
	
	private final static String APP_DOWNLOAD_RUL = Configuration.getInstance().getValue("app.download.url");
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SmsLogService smsLogService;
	
	/**
	 *  发送手机验证码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sendPhoneCheckCode()
	{
		String code = "_0000";
    	String message = "成功";
    	String nickname = ServletActionContext.getRequest().getParameter("nickname");
		String mobileNo = ServletActionContext.getRequest().getParameter("mobileNo");
		String type = ServletActionContext.getRequest().getParameter("type");
    	if(isVerifyIp())
    	{
    		if(StringUtils.isEmpty(nickname)||StringUtils.isEmpty(mobileNo)||StringUtils.isEmpty(type))
    		{
    			code = "_0009";
    	    	message = "参数为空";
    		}
    		else
    		{
    			Customer customer = customerService.getCustomerOrName(nickname);
    			Customer mobileCustomer = customerService.getCustomerByMobileNo(mobileNo);
    			if(	customer == null)
    	    	{
    	    		
    				code = "_0001";
    			    message = "用户不存在";
    	    	}
        		else
        		{
        			if("bound".equals(type))
        			{
        				if(mobileCustomer != null)
        		    	{
        		    		if(mobileCustomer.getNickName() != customer.getNickName() && mobileCustomer.getNickName() != null && mobileCustomer.getBound().equals("bound"))
        		        	{
        		    			code = "_0002";
        		    			message = "对不起，此号码已经被绑定！";
        		        	}
        		    	}
        		    	if((customer.getBound()== null)||(!customer.getBound().equals("bound")))
        		    	{
        		    		Random random = new Random();
        		    		int checkCode= random.nextInt(100000)+100000;
        		    		customer.setYanzhenma(String.valueOf(checkCode));
        		    		String content ="【一彩票网】手机绑定验证码："+customer.getYanzhenma()+"，请勿将验证码告知他人。";
        		    		smsLogService.saveSmsLog(mobileNo, content, customer.getId(),SmsLogType.VALID);
        		    		customerService.update(customer);
        		    	}
        		    	else
        		    	{
        		    		code = "_0004";
    		    			message = "对不起，此账户已经被绑定手机号！";
        		    	}
        			}
        			else if("findpwd".equals(type))
        			{
        				Random random = new Random();
    		    		int checkCode= random.nextInt(100000)+100000;
    		    		customer.setYanzhenma(String.valueOf(checkCode));
    		    		String content ="【一彩票网】找回密码的验证码："+customer.getYanzhenma()+"，请勿将验证码告知他人。如果您没有申请找回密码，请忽略此消息。";
    		    		smsLogService.saveSmsLog(mobileNo, content, customer.getId(),SmsLogType.VALID);
    		    		customerService.update(customer);
        			}
        			else
        			{
        				code = "_0003";
        				message = "type参数值不正确";
        			}
        		}
    		}
    	}else
    	{
    		code = "_9999";
			message = "无访问权限";
    	}
	    try {
		    JSONObject obj = new JSONObject();   
			obj.put("code", code);
			obj.put("message", message);
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e)
		{
			logger.error("用户"+nickname+"验证手机号码异常...",e);
			e.printStackTrace();
		}
	   return null;
    }
	
	/**
	 *  绑定手机号
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public String phoneBanding()
    {
		String nickname = ServletActionContext.getRequest().getParameter("nickname");
		String checkCode = ServletActionContext.getRequest().getParameter("checkCode");
		String type = ServletActionContext.getRequest().getParameter("type");
		String code = "_0000";
    	String message = "成功";
    	if(isVerifyIp())
    	{
    		if(StringUtils.isEmpty(nickname)||StringUtils.isEmpty(checkCode)||StringUtils.isEmpty(type))
    		{
    			code = "_0009";
    	    	message = "参数为空";
    		}
    		else
    		{
    			Customer customer = customerService.getCustomerOrName(nickname);
    	    	boolean bool = true;
    	    	if (StringUtils.isEmpty(checkCode)||!checkCode.equals(customer.getYanzhenma()))
    	    	{
    	    		code = "_0001";
    	    		message = "验证码错误!";
    	    		bool = false;
    	        }
    	    	if(bool)
    	    	{
    	    		if("bound".equals(type))
    	    		{
    	    			customer=customerService.boundPhone1(customer);
    	    		}
    	    	}
    		}
    	}
    	else
    	{
    		code = "_9999";
			message = "无访问权限";
    	}
	    try {
		    JSONObject obj = new JSONObject();   
			obj.put("code", code);
			obj.put("message", message);
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			logger.error("用户"+nickname+"绑定手机号码异常...",e);
			e.printStackTrace();
		}
	   return null;
    }
    
    /**
     * 发送App下载短信
     * @return
     */
    @SuppressWarnings("unchecked")
	public String sendAppSms()
    {
    	String mobileNo = ServletActionContext.getRequest().getParameter("mobileNo");
    	//String content = ServletActionContext.getRequest().getParameter("content");
    	String content = "【一彩票网】尊敬的会员,您申请下载手机客户端的地址,请点击"+APP_DOWNLOAD_RUL;
		String code = "_0000";
    	String message = "成功";
    	if(isVerifyIp())
    	{
    		if(StringUtils.isEmpty(mobileNo)||StringUtils.isEmpty(content))
    		{
    			code = "_0009";
    	    	message = "参数为空";
    		}
    		smsLogService.saveSmsLog(mobileNo, content, null, SmsLogType.COMMON);
    		//SDKClient.getClient().sendSMS(new String[]{mobileNo}, content, 5);
    	}
    	else
    	{
    		code = "_9999";
			message = "无访问权限";
    	}
		try {
			JSONObject obj = new JSONObject();   
			obj.put("code", code);
			obj.put("message", message);
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (Exception e) {
			logger.error("发送短信到"+mobileNo+"异常");
			e.printStackTrace();
		}
    	return null;
    }
    
    private boolean isVerifyIp()
    {
    	boolean flag = false;
    	String[] ips = INTERFACE_IP.split(",");
    	for (int i = 0; i < ips.length; i++)
    	{
			if(ips[i].equals(this.getRemoteIp()))
			{
				flag = true;
				break;
			}
		}
    	return flag;
    }

}
