package com.xsc.lottery.wzl.service;

import com.xsc.lottery.wzl.bean.TwoThousandThirtyOneBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;

public class Xml2031Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandThirtyOneBean twoThousandThirtyOneBean = new TwoThousandThirtyOneBean();

	public String getXml(String agentId, String agentPwd, String lotoid,
			String issue, String prefix) {
		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);
		xmlBean.setCmd("2031");
		xmlBean.setV("1.0");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));
		twoThousandThirtyOneBean.setLotoid(lotoid);
		if (null == issue) {
			issue = "";
		}
		twoThousandThirtyOneBean.setIssue(issue);
		if (null == prefix) {
			prefix = "";
		}
		twoThousandThirtyOneBean.setPrefix(prefix);
		xmlBean.setBody(twoThousandThirtyOneBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}

}
