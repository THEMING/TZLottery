package com.xsc.lottery.wzl.bean;

import java.util.List;

public class OrderBean {

	public String username;
	public String lotoid;
	public String issue;
	public String areaid;
	public String orderno;
	public UserInfoBean userInfoBean;
	public List<TicketBean> tickets;

	public String toOrder() 
	{
		StringBuffer sb = new StringBuffer();
		String orderInfo = "<order username=\"" + this.username
				+ "\" lotoid=\"" + this.lotoid + "\" issue=\"" + this.issue
				+ "\" areaid=\"" + this.areaid + "\" orderno=\"" + this.orderno
				+ "\">";
		String userInfo = userInfoBean.toUserInfo();
		sb.append(orderInfo);
		sb.append(userInfo);
		for (TicketBean ticketBean : this.tickets) {
			sb.append(ticketBean.toTicketContents());
		}
		sb.append("</order>");
		return sb.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public UserInfoBean getUserInfoBean() {
		return userInfoBean;
	}

	public void setUserInfoBean(UserInfoBean userInfoBean) {
		this.userInfoBean = userInfoBean;
	}

	public List<TicketBean> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketBean> tickets) {
		this.tickets = tickets;
	}

}
