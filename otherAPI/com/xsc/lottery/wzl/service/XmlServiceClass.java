package com.xsc.lottery.wzl.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlServiceClass {
	public static Element getChildElement(Element rootElem, String szChildName)
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
	public static Map<String, String> parseResponse(String xml)
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
            	}
            }
        }
        catch (Exception e) {
            map.put("errorcode", e.getMessage());
        }
        return map;
    }
}
