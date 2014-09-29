package com.xsc.lottery.wzl.bean;

public class TwoThousandTwoBean {
	public String merchantid;
	public String orderno;

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String toXml(){
		String xml="<body><order merchantid=\""+this.merchantid+"\" orderno=\""+this.orderno+"\"/></body>";
		return xml;
	}
}
