package com.xsc.lottery.admin.action.term;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.service.business.LotteryTermService;

/** 派奖后台管理 */
@SuppressWarnings( { "serial", "unused" })
@Scope("prototype")
@Controller("Admin.sendPrize")
public class AdminSendPrizeActin extends AdminBaseAction
{
    @Autowired
    private LotteryTermService termService;

    private Long termId;

    private String f_term;

    private String f_orderResults;

    private LotteryType[] types = LotteryType.values();

    private LotteryType type;

    private TermStatus[] status = TermStatus.values();

    private String f_status;

    private Page<LotteryTerm> page;

    private int pageNo = 1;

    private int pageSize = 15;

    private int totalPages;

    public String index()
    {
        page = new Page<LotteryTerm>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = termService.getLotteryTermPage(page, type, f_term, f_status);
        return SUCCESS;
    }

    public String sendWin()
    {
        if (termId != null && termId > 0) {
            LotteryTerm term = termService.findById(termId);
            termService.checkWin(term);// 按期次彩种派奖
        }
        return index();
    }

    public String getF_status()
    {
        return f_status;
    }

    public void setF_status(String fStatus)
    {
        f_status = fStatus;
    }

    public TermStatus[] getStatus()
    {
        return status;
    }

    public void setTermService(LotteryTermService termService)
    {
        this.termService = termService;
    }

    public Long getTermId()
    {
        return termId;
    }

    public void setTermId(Long termId)
    {
        this.termId = termId;
    }

    public String getF_term()
    {
        return f_term;
    }

    public void setF_term(String fTerm)
    {
        f_term = fTerm;
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

    public Page<LotteryTerm> getPage()
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

}
