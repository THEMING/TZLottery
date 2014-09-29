package com.xsc.lottery.wzl.service;

import java.util.ArrayList;
import java.util.List;

import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.wzl.bean.OrderBean;
import com.xsc.lottery.wzl.bean.TicketBean;
import com.xsc.lottery.wzl.bean.TicketContentBean;
import com.xsc.lottery.wzl.bean.UserInfoBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;
import com.xsc.lottery.wzl.util.SecurityUtil;

public class Xml2001Service {
	public OrderBean orderBean = new OrderBean();
	public UserInfoBean userInfoBean = new UserInfoBean();
	public TicketBean ticketBean = new TicketBean();
	public TicketContentBean ticketContentBean = new TicketContentBean();
	public XmlUtilBean xmlBean = new XmlUtilBean();

	public String getXml(String lotoid, String cardno, String cardtype,
			String email, String mobile, String realName, String agentId,
			String agentPwd, Ticket ticket, String username) 
	{
		userInfoBean.setCardno(cardno);
		userInfoBean.setCardType(cardtype);
		userInfoBean.setEmail(email);
		userInfoBean.setMobile(mobile);
		userInfoBean.setRealName(realName);

		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);

		xmlBean.setCmd("2001");
		xmlBean.setV("1.0");
		xmlBean.setId(String.valueOf(System.currentTimeMillis()));

		ticketContentBean.setBetContent(ticket.getBetContent());
		ticketContentBean.setMultiple(ticket.getMultiple());
		ticketContentBean.setMoney(ticket.getMoney());
		List<TicketBean> tickets = new ArrayList<TicketBean>();
		ticketBean.setSeq("1");

		ticketBean.setTicketContentBean(ticketContentBean);
		tickets.add(ticketBean);
		StringBuffer sb = new StringBuffer();

		orderBean.setAreaid("00");
		orderBean.setIssue(ticket.getIssue());
		orderBean.setLotoid(lotoid);
		orderBean.setOrderno(ticket.getOtherOrderID());
		orderBean.setTickets(tickets);
		orderBean.setUserInfoBean(userInfoBean);
		orderBean.setUsername(username);
		sb.append(orderBean.toOrder());

		xmlBean.setBody("<body>" + sb.toString() + "</body>");
		System.out.println(xmlBean.getBody());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		System.out.println("加密原始串:" + xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody());
		System.out.println(("加密后结果:" + xmlBean.getMd()));
		
		return xmlBean.toXml();
	}
}
