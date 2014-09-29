package com.xsc.lottery.wzl.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xsc.lottery.util.StringUtil;
import com.xsc.lottery.wzl.bean.TwoThousandBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;
import com.xsc.lottery.wzl.util.UrlUtil;
/**
 * 2003的升级接口
 * @author fabulous
 *
 */
public class Xml2018Service {
	public XmlUtilBean xmlBean = new XmlUtilBean();
	public TwoThousandBean twoThousandThreeBean = new TwoThousandBean();

	public String getXml(String agentId, String agentPwd,String lotoid,
			String issue) {

		xmlBean.setAgentId(agentId);// 商户ID
		xmlBean.setAgentPwd(agentPwd);// 商户密码
		xmlBean.setCmd("2018");
		xmlBean.setV("1.0");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));
		twoThousandThreeBean.setLotoid(lotoid);// 彩种ID
		twoThousandThreeBean.setIssue(issue);// 期次号
		// twoThousandThreeBean.setLotoid("001");
		// twoThousandThreeBean.setIssue("2010615");

		xmlBean.setBody(twoThousandThreeBean.toXml());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		return xmlBean.toXml();
	}
	
	public static void main(String[] args) {
		String xml = "<?xml version='1.0' encoding='UTF-8'?><msg v='1.0' id='1346204255992'><ctrl><agentID>800001</agentID><cmd>2003</cmd><timestamp>1346204263542</timestamp><md>5f50959b8ac020e39690d2954f7adc1a</md></ctrl><body><response errorcode='0'><bonusquery><issue lotoid='001' issue='2012097'/><bonusItem money='5.0' bonuscls='1' ticketid='00212012081700000923'/><bonusItem money='10.0' bonuscls='1' ticketid='00212012081700000967'/><bonusItem money='5.0' bonuscls='1' ticketid='00212012081700000972'/><bonusItem money='5.0' bonuscls='1' ticketid='00212012081700001033'/><bonusItem money='15.0' bonuscls='1' ticketid='00232012081900010889'/><bonusItem money='10.0' bonuscls='1' ticketid='00232012081900010915'/><bonusItem money='5.0' bonuscls='1' ticketid='00232012081900010936'/><bonusItem money='5.0' bonuscls='1' ticketid='00232012081900011037'/><bonusItem money='5.0' bonuscls='1' ticketid='00232012081900011168'/></bonusquery></response></body></msg>";
		Xml2018Service service = new Xml2018Service();
		Map<String, String> map = service.parseResponse(xml);
		System.out.println(map.get("tickets"));
	}

	public Map<String, String> parseResponse(String xml) {
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
		            	Element bonusqueryElem = getChildElement(responseElem, "bonusquery");
		            	if(bonusqueryElem != null)
		            	{
		            		List ElemList = bonusqueryElem.elements();
		            		String string = "";
		            		for (Iterator it = ElemList.iterator(); it.hasNext();) {
		            			Element elem = (Element) it.next(); 
		            			System.out.println(elem.getName());
		            			if (elem.getName().equals("issue")) {
									String lotoid = elem.attributeValue("lotoid");
									map.put("lotoid", lotoid);
									String issue = elem.attributeValue("issue");
									map.put("issue", issue);
								}else if (elem.getName().equals("bonusItem")) {
									String money = elem.attributeValue("money");
									String ticketid = elem.attributeValue("orderno");
									string += ticketid + "-" + money + ",";
								}
		            		}
		            		if(StringUtil.isEmpty(string))
		            		{
		            			map.put("tickets", "");
		            		}
		            		else
		            		{
		            			map.put("tickets", string.substring(0, string.length() - 1));
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
	
	public String getResponse() {
//		String content = this.getXml("800040", "euwpk34mh12f", "107", "12070201");
//		System.out.println(content);
		String content = "cmd=2003&msg=<?xml version='1.0' encoding='UTF-8'?><msg v='1.0' id='1346146734816'><ctrl><agentID>800058</agentID><cmd>2003</cmd><timestamp>1346146734819</timestamp><md>df482aff010eb23a054392f0c00f10c9</md></ctrl><body><loto lotoid='107' issue='1207'/></body></msg>";
		// 119.57.5.66
		// 116.213.75.172
		// String
		// ret=UrlUtil2.httpClientUtils("http://116.213.75.172:8080/billservice/sltAPI",xmlBean.getCmd(),content);

		String ret = UrlUtil.httpClientUtils(
				"http://221.123.166.226:7070/billservice/sltAPI",
				xmlBean.getCmd(), content);
		System.out.println(ret);
		return ret;
	}

//	public static void main(String[] args) {
//		new Xml2003Service().getResponse();
//	}

}
