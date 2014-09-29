package com.xsc.lottery.admin.action.term;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.dyj.sendticket.DyjTicketTreatmentWork;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.shxg.sendticket.ShxgTicketTreatmentWork;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.wzl.sendticket.WzlTicketTreatmentWork;

@SuppressWarnings( { "serial", "unused" })
@Scope("prototype")
@Controller("Admin.kuaipin")
public class AdminKuaiPin extends AdminBaseAction{

    private LotteryType[] types = LotteryType.values();
    
    private LotteryType type = LotteryType.全部;
    
    private String f_term;
    
    private Page<LotteryTerm> page;
    
    private Long termId;

    private int pageNo = 1;
    private int pageSize = 15;
    private int totalPages;
    
    @Autowired
    private LotteryTermService termService;
    
    public String index()
    {
       return SUCCESS;
    }
    
    public String  list() {
        page = new Page<LotteryTerm>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = termService.getOpenWinByTypePgae(page, type, f_term);
    	return SUCCESS;
	}
    
    public String open() {
    	if ((type != LotteryType.老11选5 && type != LotteryType.快乐扑克3 && type != LotteryType.广西快3 && type != LotteryType.上海11选5 && type != LotteryType.十一运夺金 && type != LotteryType.重庆时时彩) || termId == null) {
			return list();
		}
    	System.out.println("===================开奖======================");
    	
    	LotteryTerm term = termService.findById(termId);
    	System.out.println(term.getTermNo());

        if (term.getTermStatus().equals(TermStatus.未开奖) || term.getTermStatus().equals(TermStatus.销售截止)) {
            try {
                if(LotteryType.KuaiKaiTypeMap.containsKey(term.getType().getName_EN())) {
                    //快开抓取开奖结果不同
                	TicketTreatmentWork work = null;
                	if (term.getOutPoint().equals(SendTicketPlat.大赢家)) {
                		work = new DyjTicketTreatmentWork();
					}
                	else if (term.getOutPoint().equals(SendTicketPlat.我中啦)) {
						work = new WzlTicketTreatmentWork();
					}
                	else if(term.getOutPoint().equals(SendTicketPlat.新冠)){
                		work = new ShxgTicketTreatmentWork();
                	}
                    work.getOpenResult(term);
                }
               
                if (StringUtils.isEmpty(term.getResult())) {
                    throw new Exception("开奖结果抓取失败");
                }
                term = termService.saveLotteryTermOrPrizeLevel(term);
                
                if (LotteryType.KuaiKaiTypeMap.containsKey(term.getType().getName_EN())) {
                	//Thread.sleep(20000);
                    termService.openPrizeSyncTreatment(term);

                    
                }
               
            }
            catch (Exception e) {
                logger.warn(term.toString() + " 开奖时有异常情况", e);
                SystemWarningNotify.addWarningDescription(term
                        + " 开奖时有异常情况,请查看日志");
                term.setTermStatus(TermStatus.未开奖);
                termService.update(term);
            }
        }

        System.out.println("==============兑奖=============");

        if(term.getTermStatus().equals(TermStatus.已开奖)) {
            try {
                termService.checkWin(term);
            }
            catch (Exception e) {
                SystemWarningNotify.addWarningDescription(term + "兑奖时有异常情况");
                logger.warn(term + "兑奖时有异常情况", e);
                term.setTermStatus(TermStatus.未兑奖);
                termService.update(term);
            }
        }
    	System.out.println("==============结束=============");
		return list();
	}
	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public String getF_term() {
		return f_term;
	}

	public void setF_term(String f_term) {
		this.f_term = f_term;
	}

	public LotteryType[] getTypes() {
		return types;
	}

	public void setTypes(LotteryType[] types) {
		this.types = types;
	}

	public Page<LotteryTerm> getPage() {
		return page;
	}

	public void setPage(Page<LotteryTerm> page) {
		this.page = page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public LotteryTermService getTermService() {
		return termService;
	}

	public void setTermService(LotteryTermService termService) {
		this.termService = termService;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	
}
