package com.xsc.lottery.admin.action.customer;

import java.io.IOException;

import org.apache.commons.lang.enums.EnumUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.business.SmsLog.SmsSendState;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.SmsLogService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("smsLog.manager")
public class SmsLogManagerAction extends AdminBaseAction
{
	
	@Autowired
	private SmsLogService smsLogService;
	
	private Page<SmsLog> smsLogPage;
	private int pageNo = 1;
    private int pageSize = 15;
    private String mobile;
    private Long id;
    private String state;
    private String content;
    private SmsLogState[] smsLogStateList = SmsLogState.values();
    private SmsLogState smsLogState;

	public SmsLogState getSmsLogState() {
		return smsLogState;
	}

	public void setSmsLogState(SmsLogState smsLogState) {
		this.smsLogState = smsLogState;
	}

	public SmsLogState[] getSmsLogStateList() {
		return smsLogStateList;
	}

	public void setSmsLogStateList(SmsLogState[] smsLogStateList) {
		this.smsLogStateList = smsLogStateList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<SmsLog> getSmsLogPage() {
		return smsLogPage;
	}

	public void setSmsLogPage(Page<SmsLog> smsLogPage) {
		this.smsLogPage = smsLogPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	
	/**
	 * 默认页
	 * @return
	 */
	public String index()
	{
		smsLogPage = new Page<SmsLog>();
		smsLogPage.setPageNo(pageNo);
		smsLogPage.setPageSize(pageSize);
		smsLogPage.setAutoCount(true);
		smsLogPage = smsLogService.getSmsLogPage(smsLogPage, mobile,smsLogState,null);
		return "queryFinished";
	}
	
	//查询短信日志记录
	public String queryComm()
	{
		smsLogPage = new Page<SmsLog>();
		smsLogPage.setPageNo(pageNo);
		smsLogPage.setPageSize(pageSize);
		smsLogPage.setAutoCount(true);
		smsLogPage = smsLogService.getSmsLogPage(smsLogPage, mobile,smsLogState,null);
		return "queryFinished";
	}
	
	//重新发送短信
	public String sendAgain()
	{
		
		SmsLog sms = smsLogService.getSmsLogById(id);
		if(sms!=null){
		 sms.setSendState(SmsSendState.IMMEDIATELY);
		 sms.setState(SmsLogState.SENDING);
		 sms.setSendPriority(5);
		 int result = smsLogService.smsImmediatelySend(sms,1);
		 JSONObject obj = new JSONObject();   
		 obj.put("result", result);
		 try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	    	return index();
	}
}
