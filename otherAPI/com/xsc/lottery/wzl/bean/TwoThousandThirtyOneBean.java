package com.xsc.lottery.wzl.bean;

public class TwoThousandThirtyOneBean {
	public String lotoid;
	public String issue;
	public String prefix;
	public String getLotoid() {
		return lotoid;
	}
	public void setLotoid(String lotoid) {
		this.lotoid = lotoid;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}


	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String toXml(){
		String xml="<body><loto lotoid=\""+this.lotoid+"\" issue=\""+this.issue+"\" prefix=\""+this.prefix+"\"/></body>";
		
		return xml;
	}

}
