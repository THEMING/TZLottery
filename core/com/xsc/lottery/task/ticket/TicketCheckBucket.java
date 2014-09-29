package com.xsc.lottery.task.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Ticket;

public class TicketCheckBucket 
{
	//
	public static final int MAX_TICKET_NUM = 50;
	
	//
	private int curTicketNum = 0;
	
	//
	private Map<Order, List<Ticket> > ticketBucket = new HashMap<Order, List<Ticket> >();
	
	public boolean isFull()
	{
		return curTicketNum >= MAX_TICKET_NUM;
	}
	
	// 加入一个order的多张ticket
	public void addTicket(Order order, List<Ticket> newTickets) 
	{
		List<Ticket> tickets = null;
		if(ticketBucket.containsKey(order)) {
			tickets = ticketBucket.get(order);
		}
		else {
			tickets = new ArrayList<Ticket>();
		}
		tickets.addAll(newTickets);
		ticketBucket.put(order, tickets);
		
		curTicketNum += newTickets.size();
	}
	
	// 加入一个order的一张ticket
	public void addTicket(Order order, Ticket ticket) 
	{
		List<Ticket> tickets = null;
		if(ticketBucket.containsKey(order)) {
			tickets = ticketBucket.get(order);
		}
		else {
			tickets = new ArrayList<Ticket>();
		}
		tickets.add(ticket);
		ticketBucket.put(order, tickets);
		
		curTicketNum ++;
	}
}
