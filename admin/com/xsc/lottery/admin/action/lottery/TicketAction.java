package com.xsc.lottery.admin.action.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.service.business.LotteryOrderService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.ticketCx")
public class TicketAction extends AdminBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    private Page<Ticket> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String f_type;

    private String f_playType;

    private String f_term;

    private String f_numberNo;

    private String f_statu;

    private LotteryType[] type = LotteryType.values();

    private TicketStatus[] status = TicketStatus.values();

    private PlayType[] playType = PlayType.values();

    public String index()
    {
        page = new Page<Ticket>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getTicketPage(page, f_type, f_term, f_playType,
                f_statu, f_numberNo);
        return SUCCESS;
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

    public String getF_type()
    {
        return f_type;
    }

    public void setF_type(String fType)
    {
        f_type = fType;
    }

    public String getF_playType()
    {
        return f_playType;
    }

    public void setF_playType(String fPlayType)
    {
        f_playType = fPlayType;
    }

    public String getF_term()
    {
        return f_term;
    }

    public void setF_term(String fTerm)
    {
        f_term = fTerm;
    }

    public String getF_numberNo()
    {
        return f_numberNo;
    }

    public void setF_numberNo(String fNumberNo)
    {
        f_numberNo = fNumberNo;
    }

    public String getF_statu()
    {
        return f_statu;
    }

    public void setF_statu(String fStatu)
    {
        f_statu = fStatu;
    }

    public Page<Ticket> getPage()
    {
        return page;
    }

    public LotteryType[] getType()
    {
        return type;
    }

    public TicketStatus[] getStatus()
    {
        return status;
    }

    public PlayType[] getPlayType()
    {
        return playType;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

}
