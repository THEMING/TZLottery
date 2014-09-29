package com.xsc.lottery.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryTermService;

/**
 * 开奖数据
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.prizeInfoIndexAct")
public class PrizeInfoIndexAction extends LotteryClientBaseAction
{
	private int navIndex = 6;
	
	private String type = "ssq";
	private String termNo; 
	
	@Autowired
    private LotteryTermService lotteryTermService;
	
	public String index()
    {
		//得到最新一期开奖数据
		LotteryTerm term = lotteryTermService.getLastOpenPrizeResult(LotteryType.双色球);
		termNo = term.getTermNo();
        
        return SUCCESS;
    }
	
	public String getType() {
		return type;
	}
	
	public String getTermNo() {
		return termNo;
	}
	
	public int getNavIndex() {
		return navIndex;
	}

	public void setNavIndex(int navIndex) {
		this.navIndex = navIndex;
	}
	
	
}
