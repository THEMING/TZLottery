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


public class Xml2002Service
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
	//出票赔率（errmsg）格式说明：串关及单关固定赔率玩法的彩种（301、302、303、304、312）
	//示例：周三001:[负,胜]/周三003:[负,胜] 投注两个场次，每个场次两个选项，返回的errmsg就是“1.350A2.230-1.170A1.700”。
	//含义：投注周三001[负] 赔率是1.350，投注周三001[胜] 赔率是2.230，投注周三003[负] 赔率是1.170，投注周三003[胜]赔率是1.700。
	//说明：返回的赔率是与投注项对应的，A隔开多个投注项；带@的是有让分/预设总分的；B隔开多个场次。
	//1.350A2.230-1.170A1.700 (此处有的矛盾，要分清是"B"还是"-"
	public String getFixPL(String szRatio)
	{
        String szPL = szRatio.replace("A", "|");
        szPL = szPL.replace("-", "|");
		return szPL;
	}
	//errmsg="1.28-3.85-3.55"
	public String[] getUse(String szRatio)
	{
		String[] temp = szRatio.split("@");
        String szPL = temp[0].replace("-", "|").replace("A", "/");
		if(temp.length >= 2)
		{
        	String specila = temp[1].replace("B", "|");
        	String use[] = {szPL, specila};
        	return use;
		}
		else
		{
        	String use[] = {szPL};
        	return use;
		}
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
		            		Element ticketElem = getChildElement(orderElem, "ticket");
			            	if(ticketElem != null)
			            	{
			            		String szTicketStatus = ticketElem.attributeValue("status");
			            		String szErrorMsg = ticketElem.attributeValue("errmsg");
			            		map.put("TicketStatus", szTicketStatus);
			            		map.put("errmsg", szErrorMsg);
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
		xmlBean.setCmd("2002");
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
