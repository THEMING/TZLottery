package com.xsc.lottery.wzl.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xsc.lottery.wzl.bean.TwoThousandTwoBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;

public class Xml2012Service 
{
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandTwoBean twoThousandTwoBean = new TwoThousandTwoBean();

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
		            	Element orderElem = getChildElement(responseElem, "order");
		            	if(orderElem != null)
		            	{
		            		String szOrderStatus = orderElem.attributeValue("status");
		            		map.put("OrderStatus", szOrderStatus);
		            		/* 多个Ticket 的情况
		                    List ElemList = orderElem.elements();
		            		for (Iterator it = ElemList.iterator(); it.hasNext();) {
		            			Element ticketelem = (Element) it.next(); 
		            			String szTicketID = ticketElem.attributeValue("ticketid");
			            		String szTicketStatus = ticketElem.attributeValue("status");
			            		map.put("Ticket" + szTicketID, szTicketStatus);
		            		}
		            		*/
		            		//单个Ticket的情况
		            		Element ticketElem = getChildElement(orderElem, "ticked");
			            	if(ticketElem != null)
			            	{
			            		String szTicketStatus = ticketElem.attributeValue("status");
			            		map.put("TicketStatus", szTicketStatus);
			            	}
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
	
	public String getXml(String agentId, String agentPwd, String orderno) {
		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);
		xmlBean.setCmd("2012");
		xmlBean.setV("1.0");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));
		twoThousandTwoBean.setMerchantid(agentId);
		twoThousandTwoBean.setOrderno(orderno);
		xmlBean.setBody(twoThousandTwoBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}

}
