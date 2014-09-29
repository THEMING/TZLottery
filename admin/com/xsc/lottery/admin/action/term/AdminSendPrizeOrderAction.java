package com.xsc.lottery.admin.action.term;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;

@SuppressWarnings( { "serial"})
@Scope("prototype")
@Controller("Admin.sendPrizeOrder")
public class AdminSendPrizeOrderAction extends AdminBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private LotteryTermService termService;

    private Long orderId;

    private Long termId;

    private String f_customer;

    private String f_orderResults;

    private LotteryType[] types = LotteryType.values();

    private LotteryType type;

    private Page<Order> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;
    
    private Calendar startTime;
    
    private Calendar overTime;
    
    private String stat;
    
    private BigDecimal sumWinMoney;
    
    //已中奖 未兑奖所有用户中奖的总金额
    private BigDecimal sumBigDecimal;

    public String index()
    {
        LotteryTerm term = termService.findById(termId);
        page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getOrderPage(page, type, f_customer, term);
        
        sumWinMoney=orderService.getSumWinMoney(type, f_customer, term);
        return SUCCESS;
    }

    public String sendWin()
    {
        if (orderId != null && orderId > 0) {
            Order order = orderService.findById(orderId);
            orderService.checkOrderWin(order);// 单派奖
        }
        return index();
    }
    
    public String list()
    {
    	page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getJCOrderPage(LotteryType.竞彩足球, page, startTime, overTime, stat);
        sumBigDecimal = orderService.getSumMoney(LotteryType.竞彩足球, startTime, overTime, stat);
    	return "list";
    }
    
    public String listJCLQ()
    {
    	page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getJCOrderPage(LotteryType.竞彩篮球, page, startTime, overTime, stat);
    	return "list";
    }
    public void setTermService(LotteryTermService termService)
    {
        this.termService = termService;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getTermId()
    {
        return termId;
    }

    public void setTermId(Long termId)
    {
        this.termId = termId;
    }

    public String getF_customer()
    {
        return f_customer;
    }

    public void setF_customer(String fCustomer)
    {
        f_customer = fCustomer;
    }

    public String getF_orderResults()
    {
        return f_orderResults;
    }

    public void setF_orderResults(String fOrderResults)
    {
        f_orderResults = fOrderResults;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public Page<Order> getPage()
    {
        return page;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getOverTime() {
		return overTime;
	}

	public void setOverTime(Calendar overTime) {
		this.overTime = overTime;
	}

	public BigDecimal getSumWinMoney() {
		return sumWinMoney;
	}

	public void setSumWinMoney(BigDecimal sumWinMoney) {
		this.sumWinMoney = sumWinMoney;
	}

	public BigDecimal getSumBigDecimal() {
		return sumBigDecimal;
	}

	public void setSumBigDecimal(BigDecimal sumBigDecimal) {
		this.sumBigDecimal = sumBigDecimal;
	}
    
}
