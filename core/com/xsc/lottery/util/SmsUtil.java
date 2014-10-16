package com.xsc.lottery.util;

import java.util.Map;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

public class SmsUtil
{
	static CCPRestSmsSDK sms;
	
	static Map m;
	
	public synchronized static void initSmsUtil(){
		
	}
	
	public synchronized static Map sendSms(String phoneNum,String[] content,String templateId){
		if(sms==null){
			sms = new CCPRestSmsSDK();
			sms.init(Configuration.getInstance().getValue("serverAddressYUN"), Configuration.getInstance().getValue("portYUN"));
			sms.setAccount(Configuration.getInstance().getValue("accountSIDYUN"), Configuration.getInstance().getValue("authTokenYUN"));
			sms.setAppId(Configuration.getInstance().getValue("appIDYUN"));
		}
		
		m = sms.sendTemplateSMS(phoneNum,templateId ,content);
		return m;
	}
	
	public static void main(String[] args)
	{
		Map result = SmsUtil.sendSms("18923069316", new String[]{"11111"},"5529");
//		SmsUtil.sendSms("18923069316",new String[]{"11"});Configuration.getInstance().getValue("payfailTemplateIDYUN")
		System.out.println("SDKTestGetSubAccounts result=" + result);
	}

}

