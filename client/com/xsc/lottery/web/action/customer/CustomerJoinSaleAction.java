package com.xsc.lottery.web.action.customer;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerJoinSale")
public class CustomerJoinSaleAction extends LotteryClientBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    private Page<Part> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private LotteryType type;

    private LotteryType[] typeList = LotteryType.values();

    private CommunityType[] statusList = CommunityType.values();

    private Calendar beginTime;

    private Calendar endTime;

    private String status;

    // 我的参与合买查询
    public String index()
    {
        Customer customer = this.getCurCustomer();
        page = new Page<Part>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getParticipateSale(page, type, status, beginTime,
                endTime, customer);
        return "result";
    }

    public Page<Part> getPage()
    {
        return page;
    }

    public void setPage(Page<Part> page)
    {
        this.page = page;
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

    public LotteryType[] getTypeList()
    {
        return typeList;
    }

    public void setTypeList(LotteryType[] typeList)
    {
        this.typeList = typeList;
    }

    public CommunityType[] getStatusList()
    {
        return statusList;
    }

    public Calendar getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime)
    {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Calendar endTime)
    {
        this.endTime = endTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

}
