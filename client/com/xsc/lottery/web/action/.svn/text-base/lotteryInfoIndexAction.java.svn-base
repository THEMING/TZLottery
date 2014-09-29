package com.xsc.lottery.web.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.util.DateUtil;

/**
 * 彩票资讯
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.lotteryInfoIndexAct")
public class lotteryInfoIndexAction extends LotteryClientBaseAction
{
	@Autowired
	private ArticleService articleService;
	
	private String title;
	private String date;
	private String content;
	private String type;
	private long id;
	private int begin;
	private Article article;
	private List<Article> newsList;
	private List<Article> expertList;
	private List<Article> lectureList;
	
	public String index()
    {
		newsList = articleService.getArticlesByTypeAndNum("彩票新闻", 15);
		expertList = articleService.getArticlesByTypeAndNum("专家推荐", 10);
		lectureList = articleService.getArticlesByTypeAndNum("缩水讲堂", 10);
		return SUCCESS;
    }
	
	public String detail()
	{
		article = articleService.findById(id);
		return "detail";
	}
	
	public String ajaxInfoForPhone()//传参数类型和第几次请求begin过来
	{
		List<Article> artList = new ArrayList<Article>();
		if (begin == 0) {
			begin = 1;
		}
		if (type.equals("cpxw")) {
			artList = articleService.getArticlesByTypeAndNumForPhone("彩票新闻", 20, begin);
		}
		else if (type.equals("zjtj")) {
			artList = articleService.getArticlesByTypeAndNumForPhone("专家推荐", 20, begin);
		}
		else {
			throw new RuntimeException("对不起没有这种类型");
		}
		JSONArray jsonArray = new JSONArray();
		for (Article art : artList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("title", art.getTitle());
			jsonObject.put("date", DateUtil.toYYYY_MM_DD(art.getPublishTime()));
			jsonObject.put("action", "ajaxArticleDetailForPhone");
			jsonObject.put("id", art.getId());
			jsonArray.add(jsonObject);
		}
		setJsonString(jsonArray.toString());
		return AJAXJSON;
	}
	
	public String ajaxArticleDetailForPhone()//需要传过来个ID
	{
		article = articleService.findById(id);
		JSONObject jObject = new JSONObject();
		jObject.put("author", article.getAuthor());
		jObject.put("content", article.getContent());
		setJsonString(jObject.toString());
		return AJAXJSON;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public List<Article> getNewsList() {
		return newsList;
	}

	public List<Article> getExpertList() {
		return expertList;
	}
	
	public List<Article> getLectureList() {
		return lectureList;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}
}
