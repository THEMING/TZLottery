package com.xsc.lottery.admin.action.term;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.dyj.sendticket.DyjTicketTreatmentWork;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.wzl.sendticket.WzlTicketTreatmentWork;

/** 期次后台管理action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.orderUpdate")
public class AdminOrderUpdateAction extends AdminBaseAction
{

	@Autowired
	private LotteryOrderService lotteryOrderService;
	
	private String size = "a";
	
	private String orderid;
	
	private String ticketid;
	
	private String statue;
	
	private List<Ticket> list;
	
    public String index()
    {
        return SUCCESS;
    }

    public String search() {
		
    	System.out.println("进search啦");
    	if (orderid != "") {
			list = lotteryOrderService.findTicketsbyOrderIdAndStatus(orderid, TicketStatus.出票中);
			System.out.println(list.size() + ".....................................");
			size = list.size() + "";
		}
    	return index();
	}
    
    public String edit() {
    	System.out.println("进edit啦");
    	Ticket ticket = lotteryOrderService.findByIdTicket(Long.valueOf(ticketid));
    	if (ticket != null) {
    		if (statue.equals("0")) {
    			ticket.setStatus(TicketStatus.出票失败);
			} 
    		else if (statue.equals("1")) {
				ticket.setStatus(TicketStatus.已出票);
			}
			lotteryOrderService.saveTicket(ticket);
		}
		return search();
	}
    
    public String updateOrder() {
    	System.out.println("进update啦");
    	Order order = lotteryOrderService.findById(Long.valueOf(orderid));
    	List<Ticket> tickets = lotteryOrderService.getTicketByOrder(order);
    	List<Ticket> returnTickets = new ArrayList<Ticket>();
    	for (Ticket ticket : tickets) {
    		if (ticket.getStatus().equals(TicketStatus.出票中)) {
				return index();
			}
            if (ticket.getStatus().equals(TicketStatus.出票失败)) {
                returnTickets.add(ticket);
            }
        }
    	
    	TicketTreatmentWork ticketTreatmentWork = null;
    	if (order.getTicketThirdName().equals("大赢家")) {
			ticketTreatmentWork = new DyjTicketTreatmentWork();
		}
    	else if (order.getTicketThirdName().equals("我中啦")) {
			ticketTreatmentWork = new WzlTicketTreatmentWork();
		}
    	ticketTreatmentWork.finishTicketBusiness(order, returnTickets);
		return index();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public List<Ticket> getList() {
		return list;
	}

	public void setList(List<Ticket> list) {
		this.list = list;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}


}
