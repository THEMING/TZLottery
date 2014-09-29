package com.xsc.lottery.admin.action.term;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryTermService;

/** 后台开奖管理action */
@SuppressWarnings( { "serial", "unused" })
@Scope("prototype")
@Controller("Admin.openwin")
public class AdminOpenWinAction extends AdminBaseAction
{
    @Autowired
    private LotteryTermService termService;

    private Page<LotteryTerm> page;

    private int pageNo = 1;
    private int pageSize = 15;
    private int totalPages;
    private Long termId;
    private String f_term;

    private LotteryType[] types = LotteryType.values();
    private LotteryType type = LotteryType.全部;

    public String index()
    {
        page = new Page<LotteryTerm>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = termService.getOpenWinByTypePgae(page, type, f_term);
        return SUCCESS;
    }

    public String openwin()
    {
        if (termId != null && termId > 0) {
            LotteryTerm term = termService.findById(termId);
            termService.delWinDescribeOrder(term);
            if (LotteryType.KuaiKaiTypeMap.containsKey(term.getType()
                    .getName_EN())) {
                termService.openPrizeSyncTreatment(term);
            }
            else {
                termService.openPrize(term);
            }
            super.addActionMessage("成功开奖！");
        }
        return index();
    }

    public Long getTermId()
    {
        return termId;
    }

    public void setTermId(Long termId)
    {
        this.termId = termId;
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

    public void setTermService(LotteryTermService termService)
    {
        this.termService = termService;
    }

    public Page<LotteryTerm> getPage()
    {
        return page;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public void setTypes(LotteryType[] types)
    {
        this.types = types;
    }

    public String getF_term()
    {
        return f_term;
    }

    public void setF_term(String fTerm)
    {
        f_term = fTerm;
    }

}
