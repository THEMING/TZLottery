package com.xsc.lottery.web.action.customer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.enumerate.ChaseStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PloyType;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerChaseSale")
public class CustomerChaseAction extends LotteryClientBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private ChaseService chaseService;

    private Page<Chase> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private Long id;

    private String fid;

    private String f_type;

    private LotteryType[] types = LotteryType.values();

    private Calendar f_stime;// 时间查询（开始时间）

    private Calendar f_etime;// 时间查询（结束时间）

    private String f_term;// 期次范围

    private String ployType = "all";

    private ChaseStatus[] status = ChaseStatus.values();

    private String f_statu;// 追号状态

    public String index()
    {
        page = new Page<Chase>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getChasePage(page, this.getCurCustomer()
                .getNickName(), f_type, f_stime, f_etime, f_statu, PloyType
                .enToType(ployType));
        return SUCCESS;
    }

    public String back()
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Chase backChase = chaseService.findById(Long.valueOf(fid));
            backChase.setStatus(ChaseStatus.追号终止);
            chaseService.stopChse(backChase);
            resultMap.put("sysmsg", "成功终止追号!");
        }
        catch (Exception e) {
            resultMap.put("sysmsg", "终止追号失败!");
        }
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }

    public void setPloyType(String ployType)
    {
        this.ployType = ployType;
    }

    public void setFid(String fid)
    {
        this.fid = fid;
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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getF_type()
    {
        return f_type;
    }

    public void setF_type(String fType)
    {
        f_type = fType;
    }

    public Calendar getF_stime()
    {
        return f_stime;
    }

    public void setF_stime(Calendar fStime)
    {
        f_stime = fStime;
    }

    public Calendar getF_etime()
    {
        return f_etime;
    }

    public void setF_etime(Calendar fEtime)
    {
        f_etime = fEtime;
    }

    public String getF_term()
    {
        return f_term;
    }

    public void setF_term(String fTerm)
    {
        f_term = fTerm;
    }

    public String getF_statu()
    {
        return f_statu;
    }

    public void setF_statu(String fStatu)
    {
        f_statu = fStatu;
    }

    public Page<Chase> getPage()
    {
        return page;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public ChaseStatus[] getStatus()
    {
        return status;
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