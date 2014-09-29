package com.xsc.lottery.admin.action.lottery;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.chaseItemCx")
public class ChaseItemCxAction extends AdminBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private ChaseService chaseService;

    @Autowired
    private LotteryPlanService planService;

    private Page<ChaseItem> page;

    private List<ChaseItem> itemList;

    private Long chaseId;

    private Long itemId;

    private ChaseItem item;

    private Chase chase;

    private String numberNo;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    public String index()
    {
        chase = chaseService.findById(chaseId);
        page = new Page<ChaseItem>();
        page.setPageNo(pageNo);
        page.setPageSize(50);
        page.setAutoCount(true);
        page = orderService.getChaseItemPage(page, chase);
        return SUCCESS;
    }

    public String list()
    {
        if (!StringUtils.isEmpty(numberNo)) {
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

    public String detail()
    {
        item = chaseService.getChaseItem(itemId);
        return "view";
    }

    public ChaseItem getItem()
    {
        return item;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public String getNumberNo()
    {
        return numberNo;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = numberNo;
    }

    public List<ChaseItem> getItemList()
    {
        return itemList;
    }

    public void setPlanService(LotteryPlanService planService)
    {
        this.planService = planService;
    }

    public Long getChaseId()
    {
        return chaseId;
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

    public Chase getChase()
    {
        return chase;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

    public void setChaseService(ChaseService chaseService)
    {
        this.chaseService = chaseService;
    }

}
