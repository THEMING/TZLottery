package com.xsc.lottery.wzl.bean;

import java.util.List;

public class TwoThousandSixBean {
	public List<OrderBean> orderBeans;

	
	public String toBody(){
		StringBuffer sb = new StringBuffer();
		sb.append("<body>");
		for (OrderBean orderBean : this.orderBeans) {
			sb.append(orderBean.toOrder()+"\r\n");
		}
		sb.append("</body>");
		return sb.toString();
	}
	public static void main(String[] args) {
		
	}
}
