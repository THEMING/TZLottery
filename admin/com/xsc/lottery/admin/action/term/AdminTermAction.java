package com.xsc.lottery.admin.action.term;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.handle._14sfcHandle;
import com.xsc.lottery.handle._r9Handle;
import com.xsc.lottery.service.business.LotteryTermService;

/** 期次后台管理action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.term")
public class AdminTermAction extends AdminBaseAction
{
    @Autowired
    private LotteryTermService termService;

    @Autowired
    private LotteryHandleFactory factory;
    
    //@Autowired
    //private LotteryTermTaskFactory lotteryTermTaskFactory;
    
    private List<LotteryTerm> termList;

    private Page<LotteryTerm> page;

    private LotteryTerm lotteryTerm;

    private List<PrizeLevel> prizelevel;

    private Long[] prizeLevelsid;

    private Long termId;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String f_term;

    private Calendar f_openWinTime;

    private TermStatus[] termStatus = TermStatus.values();

    private String statusStr;

    private LotteryType[] types = LotteryType.values();

    private LotteryType type;

    private boolean baozi;

    private String strPath;
    
    private String from;

    public String index()
    {
        return "list";
    }

    public String list()
    {
        TermStatus status = null;
        if (StringUtils.isNotBlank(statusStr))
            status = TermStatus.valueOf(statusStr);
        page = new Page<LotteryTerm>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = termService.getLotteryTermPage(page, type, status,
                f_openWinTime, f_term);
        return "list";
    }

    public String edit()
    {
        if (termId != null) {
            lotteryTerm = termService.findById(termId);
            if (lotteryTerm.getType().equals(LotteryType.排列三)
                    || lotteryTerm.getType().equals(LotteryType.福彩3d)) {
                if (!StringUtils.isEmpty(lotteryTerm.getResult()))
                    baozi = isbz(lotteryTerm.getResult());
            }
            strPath = lotteryTerm.getType().getName_EN();
        }
        return "edit";
    }

    /** 抓取期次数据 */
    public String zq()
    {
        try {
            LotteryTerm term = termService.findById(termId);
            if ((term.getType().equals(LotteryType.足彩14场))||(term.getType().equals(LotteryType.足彩任9)))
            {
            	if (from.equals("1")) {
            		System.out.println("从500万获取数据");
            		if (term.getType().equals(LotteryType.足彩14场)) {
            			_14sfcHandle handle = new _14sfcHandle();
            			lotteryTerm = handle.fetchPrizeLevel1(term);
            		}else if (term.getType().equals(LotteryType.足彩任9)) {
            			_r9Handle handle = new _r9Handle();
            			lotteryTerm = handle.fetchPrizeLevel1(term);
            		}
            	}else {
            		System.out.println("从大赢家获取数据");
            		lotteryTerm = factory.getLotteryHandleFactory(type).fetchPrizeLevel(term);
            	}
            } else
            {
            	lotteryTerm = factory.getLotteryHandleFactory(type).fetchPrizeLevel(term);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return edit();
    }

    public String save()
    {
        LotteryTerm lt = lotteryTerm;
        if (null != lotteryTerm.getId()) {
            lt = termService.findById(lotteryTerm.getId());
            //lt.setOtherTotalSale(lotteryTerm.getOtherTotalSale());
            lt.setPrizeLevels(lotteryTerm.getPrizeLevels());
            lt.setPrizePool(lotteryTerm.getPrizePool());
            lt.setResult(lotteryTerm.getResult());
            lt.setTermStatus(lotteryTerm.getTermStatus());
            lt.setTotalSale(lotteryTerm.getTotalSale());
            lt.setOrderResult(lotteryTerm.getOrderResult());
            // 要生成静态数据
        }
        lotteryTerm = termService.saveLotteryTermOrPrizeLevel(lt);
        termId = lotteryTerm.getId();
        this.addActionMessage("保存成功！");
        return edit();
    }

    public String view()
    {
        if (termId != null) {
            prizelevel = termService.getTermByPrizeLevel(termService.findById(termId));
        }
        return "view";
    }

    //FIXME
    public String suspendWin()
    {
        if (termId != null) {
            LotteryTerm lotteryTerm = termService.findById(termId);
            lotteryTerm.setTermStatus(TermStatus.暂停销售);
            termService.update(lotteryTerm);
            //lotteryTermTaskFactory.removeTask(lotteryTerm);
        }
        return list();
    }

    public String startWin()
    {
        if (termId != null) {
            LotteryTerm lotteryTerm = termService.findById(termId);
            lotteryTerm.setTermStatus(TermStatus.销售中);
            termService.update(lotteryTerm);
        }
        return list();
    }

    public String detailByTerm()
    {
        if (termId != null) {
            LotteryTerm lotteryTerm = termService.findById(termId);
            if (lotteryTerm != null) {
                prizelevel = termService.getTermByPrizeLevel(lotteryTerm);
                type = lotteryTerm.getType();
            }
        }
        return "detail";
    }

    private boolean isbz(String result)
    {
        String[] strNum = result.split("\\,");
        if (strNum[0].equals(strNum[1]) && strNum[0].equals(strNum[2])
                && strNum[2].equals(strNum[1])) {
            return true;
        }
        return false;

    }

    public String getF_term()
    {
        return f_term;
    }

    public void setF_term(String fTerm)
    {
        f_term = fTerm;
    }

    public Calendar getF_openWinTime()
    {
        return f_openWinTime;
    }

    public void setF_openWinTime(Calendar fOpenWinTime)
    {
        f_openWinTime = fOpenWinTime;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setTypes(LotteryType[] types)
    {
        this.types = types;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public String getStatusStr()
    {
        return statusStr;
    }

    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }

    public TermStatus[] getTermStatus()
    {
        return termStatus;
    }

    public void setTermService(LotteryTermService termService)
    {
        this.termService = termService;
    }

    public List<LotteryTerm> getTermList()
    {
        return termList;
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

    public Page<LotteryTerm> getPage()
    {
        return page;
    }

    public List<PrizeLevel> getPrizelevel()
    {
        return prizelevel;
    }

    public Long getTermId()
    {
        return termId;
    }

    public void setTermId(Long termId)
    {
        this.termId = termId;
    }

    public LotteryTerm getLotteryTerm()
    {
        return lotteryTerm;
    }

    public void setLotteryTerm(LotteryTerm lotteryTerm)
    {
        this.lotteryTerm = lotteryTerm;
    }

    public Long[] getPrizeLevelsid()
    {
        return prizeLevelsid;
    }

    public void setPrizeLevelsid(Long[] prizeLevelsid)
    {
        this.prizeLevelsid = prizeLevelsid;
    }

    public String getStrPath()
    {
        return strPath;
    }

    public boolean getBaozi()
    {
        return baozi;
    }

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
