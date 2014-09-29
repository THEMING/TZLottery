package com.xsc.lottery.admin.action.article;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.util.DateUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.WinPrizeAction")
public class WinPrizeAction extends AdminBaseAction{
	
	
	@Autowired
	private ArticleService articleService;
	
	private Page<WinPrize> page;
	
    private int pageNo=1;

    private int pageSize=10;
    
    private String userName;
    
    private String type;
    
    private String wintime;
    
    private String bonus;
    
    private String rankingType;

    private WinPrize winPrize; 
    
    private String id;

	public String index(){
		page = new Page<WinPrize>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        articleService.find(page, null, null, null,null);		
		return SUCCESS;
	}
    public String save(){
    	
	    if(!id.equals("")){
	    	try{
	    	winPrize=articleService.getWinPrize(Long.parseLong(id));
	    	winPrize.setUserName(userName);
	    	winPrize.setType(type);
	    	winPrize.setWinTime(DateUtil.parseTimeStamp(wintime));
	    	winPrize.setBonus(new BigDecimal(bonus));
	    	winPrize.setRankingType(rankingType);
	    	articleService.updateWinPrize(winPrize);
	    	 addActionMessage("更新成功!");
	    	}
	    	catch(Exception e){
	    		 addActionMessage("更新失败!");
	    	}
		}else{
			try{
		   winPrize=new WinPrize();	
	       winPrize.setUserName(userName);
	       winPrize.setType(type);
	       winPrize.setWinTime(DateUtil.parseTimeStamp(wintime));
	       winPrize.setBonus(new BigDecimal(bonus));
	       winPrize.setRankingType(rankingType);
	       articleService.saveWinPrize(winPrize);
	       addActionMessage("保存成功!");
    	}
    	catch(Exception e){
    		addActionMessage("保存失败");
    	}
	    }
	    return index();
    }
    public String detail(){
    	winPrize=articleService.getWinPrize(Long.parseLong(id));
    	return index();
    }
  
    public String delete(){
    	try{
    	articleService.deleteWinPrize(Long.parseLong(id));
    	addActionMessage("删除成功");
    	}catch(Exception e){
    		addActionMessage("删除失败");	
    	}
    	return index();
    }
   public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public WinPrize getWinPrize() {
		return winPrize;
	}
	public void setWinPrize(WinPrize winPrize) {
		this.winPrize = winPrize;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getWintime() {
		return wintime;
	}

	public void setWintime(String wintime) {
		this.wintime = wintime;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getRankingType() {
		return rankingType;
	}

	public void setRankingType(String rankingType) {
		this.rankingType = rankingType;
	}


	public Page<WinPrize> getPage() {
		return page;
	}

	public void setPage(Page<WinPrize> page) {
		this.page = page;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
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
