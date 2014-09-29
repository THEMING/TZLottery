package com.xsc.lottery.wzl.bean;

import java.math.BigDecimal;


public class TicketContentBean
{
	public String betContent;
	
	public int multiple;
	
	public BigDecimal money;

	public String toTicketContent()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(betContent);
		sb.append("-" + multiple);
		sb.append("-" + money);
		return sb.toString();
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getBetContent() {
		return betContent;
	}

	public void setBetContent(String betContent) {
		this.betContent = betContent;
	}

	public int getMultiple() {
		return multiple;
	}

	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}
}
