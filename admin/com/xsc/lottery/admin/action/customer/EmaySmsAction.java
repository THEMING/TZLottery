package com.xsc.lottery.admin.action.customer;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.business.SmsLog.SmsSendState;
import com.xsc.lottery.service.business.SmsLogService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("emay.sms")
public class EmaySmsAction extends AdminBaseAction
{
	
	@Autowired
	private SmsLogService smsLogService;
	/**
	 * 默认页
	 * @return
	 */
	public String index()
	{
	  return SUCCESS;	
	}
	
	/**
	 * 序列号激活
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smsRegist()
	{
		 try {
			 boolean result = smsLogService.smsRegistEx();
			 JSONObject obj = new JSONObject();   
			 obj.put("result", result);
			 ServletActionContext.getResponse().getWriter().print(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 企业信息注册
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smsRegistCompanyInfo()
	{
		 try {
			 boolean result = smsLogService.smsRegistDetail();
			 JSONObject obj = new JSONObject();   
			 obj.put("result", result);
			 ServletActionContext.getResponse().getWriter().print(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 注销序列号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smsLogout()
	{
		 try {
			 boolean result = smsLogService.smsLogout();
			 JSONObject obj = new JSONObject();   
			 obj.put("result", result);
			 ServletActionContext.getResponse().getWriter().print(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改短信密码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smsPwdUpdate()
	{
		 try {
			 boolean result = smsLogService.smsPwdUpdate();
			 JSONObject obj = new JSONObject();   
			 obj.put("result", result);
			 ServletActionContext.getResponse().getWriter().print(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询账户余额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smsBalance()
	{
		 try {
			 double result = smsLogService.smsBalance();
			 JSONObject obj = new JSONObject();   
			 obj.put("result", result);
			 ServletActionContext.getResponse().getWriter().print(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 短信发送
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smsTextSend()
	{
		
		String test_mobile = ServletActionContext.getRequest().getParameter("test_mobile");
		 try {
			 SmsLog sms=new SmsLog();
			 sms.setContent("【一彩票网】 fabulous,恭喜您！您购买的双色球第2014888期中5元。继续投注请猛点http://m.yicp.com");
			 sms.setMobile(test_mobile);
			 sms.setSendState(SmsSendState.IMMEDIATELY);
			 sms.setState(SmsLogState.SENDING);
			 sms.setType(SmsLogType.COMMON);
			 sms.setSendPriority(3);
			 int result = smsLogService.smsImmediatelySend(sms,0);
			 JSONObject obj = new JSONObject();   
			 obj.put("result", result);
			 ServletActionContext.getResponse().getWriter().print(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
