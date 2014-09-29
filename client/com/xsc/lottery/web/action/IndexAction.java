package com.xsc.lottery.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.NewlyWinPrize;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("indexAct")
public class IndexAction extends LotteryClientBaseAction
{
    private int top = 10;
    private List<Customer> custList;
    private List<NewlyWinPrize> winPrizeList;
    private Map<String, LotteryTerm> allOpenWin;
    private LotteryTerm ssqCurrTerm;
    private LotteryTerm dltCurrTerm;
    
    private LotteryTerm ssqLastTerm;
    private LotteryTerm dltLastTerm;
    
    private Date nowdate = new Date();
    private List<LotteryTerm> toDayList;
    private List<LotteryTerm> mtDayList;
    
    private List<Article> newsList;
    private List<Article> expertList;
    private List<Article> authorList1;
    private List<Article> authorList2;
    private List<Article> publicList;
    private List<Article> ssqtzcllist;
    private List<Article> fc3dtzcllist;
    private List<Article> jczqzjtjlist;
    private List<Article> jcList;
    private List<Article> zcList;
    private List<Article> szcList;
    private List<Article> ssqList;
    private List<Article> fc3dList;
    private Page<WinPrize> page;
    private Page<WinPrize> page1;
    private List<MatchArrange> focusMatchs;
    private Integer todayMatchNum;
    private Integer finishMatchNum;
    public Page<WinPrize> getPage1() {
		return page1;
	}

	public void setPage1(Page<WinPrize> page1) {
		this.page1 = page1;
	}

	public List<MatchArrange> getFocusMatchs() {
		return focusMatchs;
	}

	public void setFocusMatchs(List<MatchArrange> focusMatchs) {
		this.focusMatchs = focusMatchs;
	}

	public Integer getTodayMatchNum() {
		return todayMatchNum;
	}

	public void setTodayMatchNum(Integer todayMatchNum) {
		this.todayMatchNum = todayMatchNum;
	}

	public Integer getFinishMatchNum() {
		return finishMatchNum;
	}

	public void setFinishMatchNum(Integer finishMatchNum) {
		this.finishMatchNum = finishMatchNum;
	}

	private int pageNo=1;
    private int pageSize=10;

	@Autowired
    private CustomerService customerService;

    @Autowired
    private LotteryTermService termService;
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private MatchArrangeService matchArrangeService;

    public String index()
    {
    	focusMatchs = matchArrangeService.getCurrentMatch();
    	page = new Page<WinPrize>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page1 = new Page<WinPrize>();
        page1.setPageNo(pageNo);
        page1.setPageSize(pageSize);
        page1.setAutoCount(true);
        articleService.find(page, null, null, null,"近期中奖");
        articleService.find(page1, null, null, null,"合买名人");
    	// 排名
        custList = customerService.getHistoryWinList(top);
        winPrizeList = customerService.getWinList(top);
        
        // 最新开奖
        allOpenWin = new HashMap<String, LotteryTerm>();
        for (final LotteryType type : LotteryType.values()) {
            if (!LotteryType.全部.equals(type)) {
                allOpenWin.put(type.getName_EN(), termService
                        .getLastOpenPrizeResult(type));
            }
        }

        ssqCurrTerm = termService.getCurrentTerm(LotteryType.双色球);
        dltCurrTerm = termService.getCurrentTerm(LotteryType.大乐透);
        
        ssqLastTerm = termService.getLastOpenPrizeResult(LotteryType.双色球);
        dltLastTerm = termService.getLastOpenPrizeResult(LotteryType.大乐透);
        
        Calendar now = Calendar.getInstance();
        toDayList = termService.getOpenPrizeByDay(now);
        now.add(Calendar.DAY_OF_MONTH, 1);
        mtDayList = termService.getOpenPrizeByDay(now);
        
        //专家推荐  网站公告  彩票资讯
        newsList = articleService.getArticlesByTypeAndNum("彩票新闻", 6);
        expertList = articleService.getArticlesByTypeAndNum("专家推荐", 6);
        publicList = articleService.getArticlesByTypeAndNum("网站公告", 6);
        zcList = articleService.getArticlesByLotteryTypeAndNum(LotteryType.足彩14场, 6);        
        jcList = articleService.getArticlesByLotteryTypeAndNum(LotteryType.竞彩足球, 6);        
        szcList = articleService.getArticlesByTypeAndNum("彩票新闻", 6);      
        ssqtzcllist= articleService.getArticlesBy2TypeAndNum("投注策略",LotteryType.双色球,10);
        fc3dtzcllist= articleService.getArticlesBy2TypeAndNum("投注策略",LotteryType.福彩3d,10);
        jczqzjtjlist= articleService.getArticlesBy2TypeAndNum("专家推荐",LotteryType.竞彩足球,10);
        ssqList = articleService.getArticlesByLotteryTypeAndNum(LotteryType.双色球, 6);
        fc3dList = articleService.getArticlesByLotteryTypeAndNum(LotteryType.福彩3d, 6);
        authorList1 = articleService.getArticlesByAuthorAndNum("小豆丁", 2);
        authorList2 = articleService.getArticlesByAuthorAndNum("张路", 2);
                
        logger.info(""+newsList.size());
        return SUCCESS;
    }

    /** 最新开奖 */
    public String getBulletin()
    {
        allOpenWin = new HashMap<String, LotteryTerm>();
        for (final LotteryType type : LotteryType.values()) {
            if (!LotteryType.全部.equals(type)) {
                allOpenWin.put(type.getName_EN(), termService
                        .getLastOpenPrizeResult(type));
            }
        }
        return "bulletin";
    }

    public List<NewlyWinPrize> getWinPrizeList()
    {
        return winPrizeList;
    }

    public Map<String, LotteryTerm> getAllOpenWin()
    {
        return allOpenWin;
    }

    public List<Customer> getCustList()
    {
        return custList;
    }

    public Date getNowdate()
    {
        return nowdate;
    }

    public LotteryTerm getSsqCurrTerm()
    {
        return ssqCurrTerm;
    }

    public List<LotteryTerm> getToDayList()
    {
        return toDayList;
    }

    public List<LotteryTerm> getMtDayList()
    {
        return mtDayList;
    }

    public LotteryTerm getDltCurrTerm()
    {
        return dltCurrTerm;
    }

	public List<Article> getNewsList() {
		return newsList;
	}

	public List<Article> getExpertList() {
		return expertList;
	}

	public List<Article> getPublicList() {
		return publicList;
	}

	public LotteryTerm getSsqLastTerm() {
		return ssqLastTerm;
	}

	public LotteryTerm getDltLastTerm() {
		return dltLastTerm;
	}

	public List<Article> getAuthorList1() {
		return authorList1;
	}

	public void setAuthorList1(List<Article> authorList1) {
		this.authorList1 = authorList1;
	}

	public List<Article> getAuthorList2() {
		return authorList2;
	}

	public void setAuthorList2(List<Article> authorList2) {
		this.authorList2 = authorList2;
	}

	public List<Article> getJcList() {
		return jcList;
	}

	public void setJcList(List<Article> jcList) {
		this.jcList = jcList;
	}

	public List<Article> getZcList() {
		return zcList;
	}

	public void setZcList(List<Article> zcList) {
		this.zcList = zcList;
	}

	public List<Article> getSzcList() {
		return szcList;
	}

	public void setSzcList(List<Article> szcList) {
		this.szcList = szcList;
	}

	public List<Article> getSsqList() {
		return ssqList;
	}

	public void setSsqList(List<Article> ssqList) {
		this.ssqList = ssqList;
	}

	public List<Article> getFc3dList() {
		return fc3dList;
	}

	public void setFc3dList(List<Article> fc3dList) {
		this.fc3dList = fc3dList;
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

	public Page<WinPrize> getPage() {
		return page;
	}

	public void setPage(Page<WinPrize> page) {
		this.page = page;
	}

	public List<Article> getSsqtzcllist() {
		return ssqtzcllist;
	}

	public void setSsqtzcllist(List<Article> ssqtzcllist) {
		this.ssqtzcllist = ssqtzcllist;
	}

	public List<Article> getFc3dtzcllist() {
		return fc3dtzcllist;
	}

	public void setFc3dtzcllist(List<Article> fc3dtzcllist) {
		this.fc3dtzcllist = fc3dtzcllist;
	}

	public List<Article> getJczqzjtjlist() {
		return jczqzjtjlist;
	}

	public void setJczqzjtjlist(List<Article> jczqzjtjlist) {
		this.jczqzjtjlist = jczqzjtjlist;
	}
}
