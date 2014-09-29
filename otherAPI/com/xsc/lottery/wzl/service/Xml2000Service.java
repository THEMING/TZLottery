package com.xsc.lottery.wzl.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xsc.lottery.wzl.bean.TwoThousandBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;

public class Xml2000Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandBean twoThousandBean = new TwoThousandBean();

	public String getXml(String agentId, String agentPwd, String lotoid,
			String issue) {
		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);
		xmlBean.setCmd("2000");
		xmlBean.setV("1.0");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));
		twoThousandBean.setLotoid(lotoid);
		if (null == issue) {
			issue = "";
		}
		twoThousandBean.setIssue(issue);

		xmlBean.setBody(twoThousandBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}
	
	public Map<String, String> parseResponse(String xml)
    {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Element rootElem = DocumentHelper.parseText(xml).getRootElement();
            Element bodyElem = getChildElement(rootElem, "body");
            if(bodyElem != null)
            {
            	Element responseElem = getChildElement(bodyElem, "response");
            	if(responseElem != null)
            	{
    				String szErrCode = responseElem.attributeValue("errorcode");
		            map.put("errorcode", szErrCode);
		            if(szErrCode.equals("0"))
		            {
		            	Element issuequeryElem = getChildElement(responseElem, "issuequery");
		            	if(issuequeryElem != null)
		            	{
		            		Element issueElem = getChildElement(issuequeryElem, "issue");
		            		String lotoid = issueElem.attributeValue("lotoid");
		            		map.put("lotoid", lotoid);
		            		String issue = issueElem.attributeValue("issue");
		            		map.put("issue", issue);
		            		String starttime = issueElem.attributeValue("starttime");
		            		map.put("starttime", starttime);
		            		String endtime = issueElem.attributeValue("endtime");
		            		map.put("endtime", endtime);
		            		String bonuscode = issueElem.attributeValue("bonuscode");
		            		map.put("bonuscode", bonuscode);
		            		String status = issueElem.attributeValue("status");
		            		map.put("status", status);
		            	}
		            }
            	}
            }
        }
        catch (Exception e) {
            map.put("errorcode", e.getMessage());
        }
        return map;
    }
	
	public static void main(String[] args) {
		String xml = "<?xml version='1.0' encoding='UTF-8'?><msg v='1.0' id='1346141052750'><ctrl><agentID>800040</agentID><cmd>2000</cmd><timestamp>1346141056273</timestamp><md>c45a59c38c49d82024b70d742d048ce1</md></ctrl><body><response errorcode='0'><issuequery><issue lotoid='107' issue='12082842' starttime='20120828154520' endtime='20120828155520' status='3'/></issuequery></response></body></msg>";
		Xml2000Service service = new Xml2000Service();
		Map<String, String> map = service.parseResponse(xml);
		System.out.println("status = " + map.get("status"));
		System.out.println("errorcode = " + map.get("errorcode"));
	}
	public Element getChildElement(Element rootElem, String szChildName)
	{
        List ElemList = rootElem.elements();
		for (Iterator it = ElemList.iterator(); it.hasNext();) {
			Element elem = (Element) it.next(); 
			if(elem.getName().equals(szChildName)) {
				return elem;
			}
		}
		return null;		
	}

}
