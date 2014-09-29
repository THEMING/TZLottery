package com.xsc.lottery.wzl.service;

import com.xsc.lottery.wzl.bean.TwoThousandBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;

public class Xml2004Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandBean twoThousandBean = new TwoThousandBean();

	public String getXml(String agentId, String agentPwd, String lotoid,
			String issue) {
		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);
		xmlBean.setCmd("2004");
		xmlBean.setV("1.0");
		xmlBean.setId("201010091515419041");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));
		twoThousandBean.setLotoid(lotoid);
		twoThousandBean.setIssue(issue);
		xmlBean.setBody(twoThousandBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}

}
