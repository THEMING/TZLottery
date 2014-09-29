package com.xsc.lottery.web.action.customer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerChaseItemSale")
public class CustomerChaseItemSaleAction extends LotteryClientBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private ChaseService chaseService;

    @Autowired
    private LotteryPlanService planService;

    private Page<ChaseItem> page;

    private Long itemId;

    private String numberNo;

    private Long chaseId;

    private ChaseItem item;

    private Chase chase;

    private int pageNo = 1;

    private int pageSize = 50;

    private int totalPages;

    //追号详情
    public String index()
    {
        chase = chaseService.findById(chaseId);
        page = new Page<ChaseItem>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getChaseItemPage(page, chase);
        return SUCCESS;
    }

    public String list()
    {
        if(!StringUtils.isEmpty(numberNo)) {
            Plan plan = planService.getPlanBynumberNo(numberNo);
            chase = chaseService.getChaseByPlan(plan);
            page = new Page<ChaseItem>();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setAutoCount(true);
            page = orderService.getChaseItemPage(page, chase);
        }
        return SUCCESS;
    }

    public String info()
    {
        item = chaseService.getChaseItem(itemId);
        return "info";
    }

    public ChaseItem getItem()
    {
        return item;
    }

    public Chase getChase()
    {
        return chase;
    }

    public void setChaseService(ChaseService chaseService)
    {
        this.chaseService = chaseService;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public void setChaseId(Long chaseId)
    {
        this.chaseId = chaseId;
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

    public Page<ChaseItem> getPage()
    {
        return page;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

    public void setPlanService(LotteryPlanService planService)
    {
        this.planService = planService;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = numberNo;
    }

}
