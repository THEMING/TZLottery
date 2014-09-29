package com.xsc.lottery.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.ArticleService;

/**
 * 彩票软件
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("software.indexAct")
public class SoftwareIndexAction extends LotteryClientBaseAction
{
	@Autowired
	private ArticleService articleService;
	
	private List<Article> lectureList;
	
	private List<Article> ssqList;
	
	private List<Article> ssqZJTJList;
	
	private List<Article> sanDList;
	
	private List<Article> zucai14List;
	
	private List<Article> zucair9List;
	
	private List<Article> qxcList;
	
	private List<Article> dltList;
	
	private List<Article> jczqList;
	
	private List<Article> jczqZJTJList;
	
	private List<Article> jclqList;
	
	private Page<WinPrize> page;
	
	private int pageNo=1;
	
    private int pageSize=10;
	
	public String index()
    {
		lectureList = articleService.getArticlesByTypeAndNum("缩水讲堂", 6);
		ssqList = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.双色球, 4);
		ssqZJTJList = articleService.getArticlesByTypeLotteryTypeAndNum("专家推荐", LotteryType.双色球, 6);
		sanDList = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.福彩3d, 4);
		zucai14List = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.足彩14场, 4);
		zucair9List = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.足彩任9, 4);
		qxcList = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.七星彩, 4);
		dltList = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.大乐透, 4);
		jczqList = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.竞彩足球, 4);
		jczqZJTJList = articleService.getArticlesByTypeLotteryTypeAndNum("专家推荐", LotteryType.竞彩足球, 6);
		jclqList = articleService.getArticlesByTypeLotteryTypeAndNum("玩法技巧", LotteryType.竞彩篮球, 4);
		
		page = new Page<WinPrize>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        articleService.find(page, null, null, null,"近期中奖");
        return SUCCESS;
    }
	
	public String moreWin()
	{
		return "moreWin";
	}
	
	public List<Article> getLectureList() {
		return lectureList;
	}

	public List<Article> getSsqList() {
		return ssqList;
	}

	public void setSsqList(List<Article> ssqList) {
		this.ssqList = ssqList;
	}

	public List<Article> getSanDList() {
		return sanDList;
	}

	public void setSanDList(List<Article> sanDList) {
		this.sanDList = sanDList;
	}

	public List<Article> getZucai14List() {
		return zucai14List;
	}

	public void setZucai14List(List<Article> zucai14List) {
		this.zucai14List = zucai14List;
	}

	public List<Article> getZucair9List() {
		return zucair9List;
	}

	public void setZucair9List(List<Article> zucair9List) {
		this.zucair9List = zucair9List;
	}

	public List<Article> getQxcList() {
		return qxcList;
	}

	public void setQxcList(List<Article> qxcList) {
		this.qxcList = qxcList;
	}

	public List<Article> getDltList() {
		return dltList;
	}

	public void setDltList(List<Article> dltList) {
		this.dltList = dltList;
	}

	public void setLectureList(List<Article> lectureList) {
		this.lectureList = lectureList;
	}

	public List<Article> getJczqList() {
		return jczqList;
	}

	public void setJczqList(List<Article> jczqList) {
		this.jczqList = jczqList;
	}

	public List<Article> getJclqList() {
		return jclqList;
	}

	public void setJclqList(List<Article> jclqList) {
		this.jclqList = jclqList;
	}

	public List<Article> getSsqZJTJList() {
		return ssqZJTJList;
	}

	public void setSsqZJTJList(List<Article> ssqZJTJList) {
		this.ssqZJTJList = ssqZJTJList;
	}

	public List<Article> getJczqZJTJList() {
		return jczqZJTJList;
	}

	public void setJczqZJTJList(List<Article> jczqZJTJList) {
		this.jczqZJTJList = jczqZJTJList;
	}

	public Page<WinPrize> getPage() {
		return page;
	}

	public void setPage(Page<WinPrize> page) {
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
}
