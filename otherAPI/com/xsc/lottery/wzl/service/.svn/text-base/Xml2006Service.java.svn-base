package com.xsc.lottery.wzl.service;

import com.xsc.lottery.wzl.bean.OrderBean;
import com.xsc.lottery.wzl.bean.TicketBean;
import com.xsc.lottery.wzl.bean.TicketContentBean;
import com.xsc.lottery.wzl.bean.UserInfoBean;
import com.xsc.lottery.wzl.bean.XmlUtilBean;

public class Xml2006Service 
{
	public OrderBean orderBean = new OrderBean();
	public UserInfoBean userInfoBean = new UserInfoBean();
	public TicketBean ticketBean = new TicketBean();
	public TicketContentBean ticketContentBean = new TicketContentBean();
	public XmlUtilBean xmlBean = new XmlUtilBean();

	public String getXml(String lotoid, String cardno, String cardtype,
			String email, String mobile, String realName, String agentId,
			String agentPwd, String sub, String betSum, String multiple,
			String type, String content, String issue, String seq,
			String areaId, String username) {
		userInfoBean.setCardno(cardno);
		userInfoBean.setCardType(cardtype);
		userInfoBean.setEmail(email);
		userInfoBean.setMobile(mobile);
		userInfoBean.setRealName(realName);
		xmlBean.setAgentId(agentId);
		xmlBean.setAgentPwd(agentPwd);
		xmlBean.setCmd("2006");
		xmlBean.setV("1.0");
		xmlBean.setId("201010091515419041");
		/*
		ticketContentBean.setSub(sub);
		ticketContentBean.setBetSum(betSum);
		ticketContentBean.setMultiple(multiple);
		ticketContentBean.setType(type);
		List<String> list = new ArrayList<String>();
		// list.add("5:[ʤ,ƽ,��]/9:[ʤ,��]"); ��Ҫ��
		list.add(content);
		/*
		ticketContentBean.setBetInfo(list);
		List<TicketBean> tickets = new ArrayList<TicketBean>();
		ticketBean.setSeq(seq);
		ticketBean.setTicketContentBean(ticketContentBean);
		tickets.add(ticketBean);
		orderBean.setAreaid(areaId);
		orderBean.setIssue(issue);
		orderBean.setLotoid(lotoid);
		orderBean.setOrderno(String.valueOf(System.currentTimeMillis()));
		orderBean.setTickets(tickets);
		orderBean.setUserInfoBean(userInfoBean);
		orderBean.setUsername(username);
		xmlBean.setBody("<body>" + orderBean.toOrder() + "</body>");
		System.out.println(xmlBean.getAgentId() + xmlBean.getAgentPwd()
				+ xmlBean.getBody());
		xmlBean.setMd(SecurityUtil.md5(xmlBean.getAgentId()
				+ xmlBean.getAgentPwd() + xmlBean.getBody()));
		*/
		return xmlBean.toXml();
	}

}
