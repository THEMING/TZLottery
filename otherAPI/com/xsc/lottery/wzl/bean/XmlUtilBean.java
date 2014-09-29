package com.xsc.lottery.wzl.bean;

import com.xsc.lottery.wzl.util.XmlUtil;

public class XmlUtilBean extends XmlUtil 
{
	//协议版本
	public String szVersion;
	//消息序列号
	public String id;
	//销售商代码
	public String agentId;
	//销售商密码
	public String agentPwd;
	//md 码
	public String md;
	//命令码
	public String cmd;
	//时间戳
	public String timestamp;
	//body
	public String body;

	public String getV() {
		return szVersion;
	}

	public void setV(String v) {
		this.szVersion = v;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentPwd() {
		return agentPwd;
	}

	public void setAgentPwd(String agentPwd) {
		this.agentPwd = agentPwd;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public String toXml()
	{
		String xml = "<msg v=\"" + this.szVersion + "\" id=\"" + this.id
				+ "\"><ctrl><agentID>" + this.agentId + "</agentID><cmd>"
				+ this.cmd + "</cmd><timestamp>"
				+ String.valueOf(System.currentTimeMillis())
				+ "</timestamp><md>" + this.md + "</md></ctrl>" + this.body
				+ "</msg>";
		return this.getXml() + xml;
	}

	public static void main(String[] args) 
	{		
	}
}
