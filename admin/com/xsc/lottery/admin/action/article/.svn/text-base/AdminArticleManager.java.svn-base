package com.xsc.lottery.admin.action.article;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.entity.enumerate.ArticleType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.util.DateUtil;


@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.ArticleManagement")
public class AdminArticleManager extends AdminBaseAction
{
	@Autowired
	private ArticleService articleService;
	
	private String title;
	private String date;
	private String content;
	private String author;
	private String type;
	private LotteryType lotteryType;
	private boolean top = false;
	private String keywords;
	private String shortTitle;
	private String relation;
	private String jumpUrl;
	private String description;
	private ArticleCategory category;
	private Long id;
	private Article article;
	private ArticleType[] types = ArticleType.values();
	private LotteryType[] lotteryTypes = LotteryType.values();
	
	private Page<Article> page;
	private List<Article> articleList;
	private static List<ArticleCategory> categoryList = new ArrayList<ArticleCategory>();
	
	private Calendar startTime;
	private Calendar endTime;
	
	private int pageNo = 1;
    private int pageSize = 10;
    
	private String message;

	/**
	 * @return
	 */
	public String index()
    {
		
		categoryList = articleService.findAllCategory();
		page = new Page<Article>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
		page = articleService.getArticlePage(page, startTime, endTime, type, lotteryType,category,title);
        return SUCCESS;
    }
	
	public String save() {
		try {
			if(article == null) {
				article = new Article();
				article.setReadNum(0);
			}
			article.setTitle(title);
			article.setPublishTime(DateUtil.parseTimeStamp(date));
			article.setContent(content);
			article.setLotteryType(lotteryType);
			article.setAuthor(author);
			article.setTop(top);
			article.setShortTitle(shortTitle);
			article.setKeywords(keywords);
			article.setDescription(description);
			article.setRelation(relation);
			article.setJumpUrl(jumpUrl);
			article.setCategory(category);
			ArticleCategory curCategory = articleService.getCategory(category.getId());
			article.setType(curCategory.getName());
			articleService.save(article);
			addActionMessage("保存成功!");
		}
		catch(Exception e) {
			addActionMessage("保存失败!");
		}
		return index();
	}
	
	public String delete() {
		try {
			Article article = articleService.findById(id);
			articleService.delete(article);
			addActionMessage("删除成功!");
		}
		catch(Exception e) {
			addActionMessage("删除失败!");
		}
		return index();
	}

	public String detail()
	{
		
		categoryList = articleService.findAllCategory();
		if(id != null)
		{
			article = articleService.findById(id);
			type = article.getType();
			lotteryType = article.getLotteryType();
			category = article.getCategory();
		}
		return "edit";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArticleCategory getCategory() {
		return category;
	}

	public void setCategory(ArticleCategory category) {
		this.category = category;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArticleType[] getTypes() {
		return types;
	}
	
	public String getMessage() {
		return message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public List<ArticleCategory> getCategoryList() {
		return categoryList;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Page<Article> getPage() {
		return page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public LotteryType getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(LotteryType lotteryType) {
		this.lotteryType = lotteryType;
	}

	public LotteryType[] getLotteryTypes() {
		return lotteryTypes;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}
}
