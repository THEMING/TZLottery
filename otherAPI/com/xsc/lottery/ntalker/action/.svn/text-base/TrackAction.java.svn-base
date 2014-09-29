package com.xsc.lottery.ntalker.action;

import javax.servlet.http.Cookie;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings( { "serial" })
@Scope("prototype")
@Controller("Other.trackAct")
public class TrackAction extends LotteryClientBaseAction
{
	private String src;
	private String adid;
	private String pid;
	private String url = "/";
	
	public String index()
	{
		if(adid==null) return SUCCESS;
		if(pid==null) return SUCCESS;
		Cookie cookie = new Cookie("from","nTalker"+"!"+pid+"!"+adid);
		//设置Cookie存在30天
		cookie.setMaxAge(60*60*24*30);
		cookie.setPath("/");
		getResponse().addCookie(cookie);
		return SUCCESS;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

	public String getAdid()
	{
		return adid;
	}

	public void setAdid(String adid)
	{
		this.adid = adid;
	}

	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
