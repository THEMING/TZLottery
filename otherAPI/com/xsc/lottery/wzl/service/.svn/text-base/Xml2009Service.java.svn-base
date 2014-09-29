package com.xsc.lottery.wzl.service;

import com.xsc.lottery.wzl.bean.TwoThousandNineBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;
import com.xsc.lottery.wzl.util.UrlUtil;

/**
 * 销售中奖核对查询接口
 */

public class Xml2009Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandNineBean twoThousandNineBean = new TwoThousandNineBean();

	public String getXml() {
		xmlBean.setAgentId("800006");
		xmlBean.setAgentPwd("ljdysc7ens8f");
		xmlBean.setCmd("2009");
		xmlBean.setV("1.0");
		xmlBean.setId("201010091515419041");
		twoThousandNineBean.setLotoid("201");
		twoThousandNineBean.setIssue("10108");
		twoThousandNineBean.setMerchantid("800006");
		xmlBean.setBody(twoThousandNineBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}

	public String getResponse() {
		String content = this.getXml();
		System.out.println(content);
		// 119.57.5.66
		// 127.0.0.1
		String ret = UrlUtil.httpClientUtils(
				"http://116.213.75.179:8080/billservice/sltAPI", xmlBean
						.getCmd(), content);
		System.out.println(ret);
		return ret;
	}

	public static void main(String[] args) {
		new Xml2009Service().getResponse();
	}

}
