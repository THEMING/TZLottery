package com.xsc.lottery.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.task.term.LotteryTermTaskFactory;
import com.xsc.lottery.util.DateUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("lottery.indexAct")
public class LotteryIndexAction extends LotteryClientBaseAction
{
    @Autowired
    private LotteryTermService lotteryTermService;
    
    @Autowired
	private MatchArrangeService matchArrangeService;
    
    @Autowired
	private ArticleService articleService;
    
    @Autowired
    private LotteryTermTaskFactory lotteryTermTaskFactory;
    
    private LotteryTerm lotteryTerm = null;
    private String lotteryType = "ssq";
    
    private List<LotteryTerm> termList;
    private List<Article> articleList;
    private List<Article> articleExportList;
    private List<Article> articleExportList1;
    private List<Article> articleExportList2;
    private List<MatchArrange> matchArrangelist;
    private String termNo;
    private String tt;
    private String typeName;
    private String Author1;
    private String Author2;
    
	public String index()
    {
		LotteryType type = LotteryType.enToType(lotteryType);
		//typeName = type.toString();
		articleList = articleService.getxgzxArticlesByLotteryTypeAndNum(type, 4);
		articleExportList = articleService.getExportArticlesByLotteryTypeAndNum(type);
		String author = null;
		
		String temp = null;
		
		if(null!=tt)
		{
			type=LotteryType.valueOf(tt);
		}
		
		if(articleExportList.size()>0)
		{
			author=articleExportList.get(0).getAuthor();
			
        	for(int i=1;i<articleExportList.size();i++) {
        		temp = articleExportList.get(i).getAuthor();
        		if(temp.equals(author)) {
        			temp = null;
        		} else {
        			break;
        		}
        	}
        	
        	if(temp==null) temp="老黄";
		} else
		{
			author="豆丁";
			temp="老黄";
		}
		
		Author1=author;
		Author2=temp;
		
        articleExportList1 = articleService.getArticlesByAuthorTypeAndNum(author, type, 3);
        articleExportList2 = articleService.getArticlesByAuthorTypeAndNum(temp, type, 3);
        
        if(lotteryTermTaskFactory.canStartMultiTaskOneType(type)) {
        	doMultiTaskOneType(type);
        }
        else {
        	lotteryTerm = lotteryTermService.getCurrentTerm(type);
        }
        return lotteryType;
    }
	
	public void doMultiTaskOneType(LotteryType type)
	{
		termList = lotteryTermService.getCurrentTermArray(type);
    	if(termNo == null || termNo.equals("")) {
    		if(termList.size() > 0) {
    			lotteryTerm = termList.get(0);
    			termNo = lotteryTerm.getTermNo();
    			
    			if(type.equals(LotteryType.足彩任9)) {
    				LotteryTerm _term = lotteryTermService.getByTypeAndTermNo(termNo, LotteryType.足彩14场);
    				if(_term != null) {
    					//任9和14场的比赛相同
    					matchArrangelist = matchArrangeService.getMatchArrangeByTerm(_term); 
    				}
    			}
    			else {
    				matchArrangelist = matchArrangeService.getMatchArrangeByTerm(termList.get(0)); 
    			}
    		}
    	}
    	else { //查询期次
        	for(LotteryTerm term : termList) {
        		if(term.getTermNo().equals(termNo)) {
        			lotteryTerm = term;
        			termNo = lotteryTerm.getTermNo();
        			
        			if(type.equals(LotteryType.足彩任9)) {
        				LotteryTerm _term = lotteryTermService.getByTypeAndTermNo(termNo, LotteryType.足彩14场);
        				if(_term != null) {
        					//任9和14场的比赛相同
        					matchArrangelist = matchArrangeService.getMatchArrangeByTerm(_term); 
        				}
        			}
        			else {
        				matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term); 
        			}
        			break;
        		}
        	}
    	}
	}
	
	public String listAllMatchArrageForZCDZ()
	{
		termList = lotteryTermService.getCurrentTermArray(LotteryType.足彩14场);
		if(termNo == null || termNo.equals("")) {
			for(LotteryTerm term : termList) {
				if(termList.size() > 0) {
					lotteryTerm = termList.get(0);
					termNo = lotteryTerm.getTermNo();
					matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term); 
	    			break;
				}
			}
		}
		else {
			for(LotteryTerm term : termList) {
        		if(term.getTermNo().equals(termNo)) {
        			lotteryTerm = term;
        			matchArrangelist = matchArrangeService.getMatchArrangeByTerm(term); 
        			break;
        		}
        	}
		}
		return "listAllMatchArrageForZCDZ";
	}
    public String getLotteryType()
    {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType)
    {
        this.lotteryType = lotteryType;
    }

    public LotteryTerm getLotteryTerm()
    {
        return lotteryTerm;
    }
    
    public List<MatchArrange> getMatchArrangelist() 
    {
		return matchArrangelist;
	}

	public List<LotteryTerm> getTermList() {
		return termList;
	}
	
	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	
	public String getStopSaleTime() {
		if(lotteryTerm != null) {
			return DateUtil.toTimeStampFm(lotteryTerm.getStopSaleTime());
		}
		return null;
	}
	
	public String getDeadLine() {
		if(lotteryTerm != null) {
			return String.valueOf((lotteryTerm.getStopSaleTime().getTimeInMillis() 
                - System.currentTimeMillis()) / 1000);
		}
		return null;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public List<Article> getArticleExportList() {
		return articleExportList;
	}

	public void setArticleExportList(List<Article> articleExportList) {
		this.articleExportList = articleExportList;
	}

	public List<Article> getArticleExportList1() {
		return articleExportList1;
	}

	public void setArticleExportList1(List<Article> articleExportList1) {
		this.articleExportList1 = articleExportList1;
	}

	public List<Article> getArticleExportList2() {
		return articleExportList2;
	}

	public void setArticleExportList2(List<Article> articleExportList2) {
		this.articleExportList2 = articleExportList2;
	}

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}
}
