package com.xsc.lottery.wzl.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.xsc.lottery.wzl.service.Xml2000Service;

public class UrlUtil 
{
	public static String getDecoder(String string,String coding)
	{
		String ret="";
		try {
			ret=URLDecoder.decode(string, coding);
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String getEncoder(String string,String coding)
	{
		String ret="";
		try {
			ret=URLEncoder.encode(string, coding);
		}
		catch (UnsupportedEncodingException e) {	
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String httpClientUtils(String url,String cmd,String content)
	{
		HttpClient httpClient;
        PostMethod postMethod = null;
        String reStr = null;
        try {
            httpClient = new HttpClient();
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            NameValuePair[] postData = new NameValuePair[2];
            postData[0]=new NameValuePair("cmd",cmd);
            postData[1]=new NameValuePair("msg",content);
            postMethod.addParameters(postData);
            httpClient.executeMethod(postMethod);
            reStr = postMethod.getResponseBodyAsString();
        } 
        catch (HttpException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (null != postMethod) {
            	postMethod.releaseConnection();
            }
        }
        return reStr;
    }
	
	public static String getDomain(HttpServletRequest request) 
	{
		try {
			String url = request.getHeader("Referer");
			url = url.replaceAll("http://", "");
			int index = url.indexOf("/");
			if (index != -1) {
				url = url.substring(0, index);
			}
			return "http://"+url;
		} 
		catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) 
	{
		/*try {
			System.out.println(java.net.URLEncoder.encode("֣��","utf-8"));
		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msg="<?xml version=\"1.0\" encoding=\"utf-8\"?><msg v=\"1.0\" id=\"1292320338787\"><ctrl><agentID>800010</agentID><cmd>2001</cmd><timestamp>201012141752</timestamp><md>0bcbc95476f2bb5dc5b7a74ab9545214</md></ctrl><body><order username=\"cailele\"  lotoid= \"016\" issue= \"543572\" areaid=\"00\" orderno= \"1292320338787\" ><userinfo realname=\"cailele\" mobile=\"13800000000\" email=\" \" cardtype=\"1\" cardno=\"12345678901234545\"/><ticket seq= \"88890\">01-01-01-1-2</ticket></order><order username=\"cailele\"  lotoid= \"016\" issue= \"543572\" areaid=\"00\" orderno= \"1292320338744\" ><userinfo realname=\"cailele\" mobile=\"13800000000\" email=\" \" cardtype=\"1\" cardno=\"123456789012345678\"/><ticket seq= \"88822\">01-01-01-1-2</ticket></order></body></msg>";
		String content =msg;
		System.out.println(content);*/
		Xml2000Service service = new Xml2000Service();
		String agentId = "800040";
		String agentPwd = "euwpk34mh12f";
		String lotoid = "301";//LotteryIDType.LQ_RFSFGG;
		String issue = "";
		String szContent = service.getXml(agentId, agentPwd, lotoid, issue);
		String ret = UrlUtil.httpClientUtils("http://221.123.166.226:7070/billservice/sltAPI","2000", szContent);
		System.out.println(ret);
	}
}
