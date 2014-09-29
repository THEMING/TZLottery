package com.xsc.lottery.wzl.bean;

public class TicketBean
{
	public TicketContentBean ticketContentBean;
	public String seq;

	public String toTicketContents() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<ticket seq=\"" + this.seq + "\">");
		sb.append(ticketContentBean.toTicketContent());
		sb.append("</ticket>");
		return sb.toString();
	}

	public TicketContentBean getTicketContentBean() {
		return ticketContentBean;
	}

	public void setTicketContentBean(TicketContentBean ticketContentBean) {
		this.ticketContentBean = ticketContentBean;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
}
