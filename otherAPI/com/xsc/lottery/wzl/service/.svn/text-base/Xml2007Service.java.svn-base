package com.xsc.lottery.wzl.service;

import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;
import com.xsc.lottery.wzl.util.UrlUtil;

public class Xml2007Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public String getXml() 
	{
		xmlBean.setAgentId("800006");
		xmlBean.setAgentPwd("ljdysc7ens8f");
		xmlBean.setCmd("2007");
		xmlBean.setV("1.0");
		xmlBean.setId("201010091515419041");

		xmlBean.setBody("<body></body>");
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}

	public String getResponse()
	{
		String content = this.getXml();
		System.out.println(content);
		// 119.57.5.66
		String ret = UrlUtil.httpClientUtils(
				"http://119.57.5.66:8080/billservice/sltAPI", xmlBean.getCmd(),
				content);
		System.out.println(ret);
		return ret;
	}

	public static void main(String[] args) {
		new Xml2007Service().getResponse();
	}
}
