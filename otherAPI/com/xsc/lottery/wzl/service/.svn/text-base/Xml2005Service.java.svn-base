package com.xsc.lottery.wzl.service;

import com.xsc.lottery.wzl.bean.TwoThousandBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;
import com.xsc.lottery.wzl.util.UrlUtil;

public class Xml2005Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandBean twoThousandBean = new TwoThousandBean();

	public String getXml(String agentId, String agentPwd, String lotoid,
			String issue) {
		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);
		xmlBean.setCmd("2005");
		xmlBean.setV("1.0");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));
		twoThousandBean.setLotoid(lotoid);
		twoThousandBean.setIssue(issue);
		xmlBean.setBody(twoThousandBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}

	public String getResponse() {
		String content = this.getXml("agentId", "agentPwd", "lotoid", "issue");

		String ret = UrlUtil.httpClientUtils(
				"http://119.57.5.66:8080/billservice/sltAPI", xmlBean.getCmd(),
				content);
		System.out.println(ret);
		return ret;
	}

	public static void main(String[] args) {
		new Xml2005Service().getResponse();
	}

}
