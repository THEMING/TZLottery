package com.xsc.lottery.admin.action.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.ArticleInLink;
import com.xsc.lottery.service.business.ArticleService;


@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.InLinkManagement")
public class AdminInLinkManager extends AdminBaseAction
{
	@Autowired
	private ArticleService articleService;
	
	private Long id;
	private String name;
	private String url;
	private Integer priority;
	private Integer category;
	
	
	private ArticleInLink articleInLink;

	private List<ArticleInLink> inLinkList;
    
	private String message;

	public String index()
    {
		inLinkList = articleService.findAllInLink();
        return SUCCESS;
    }
	
	public String save()
	{
		try {
			if(articleInLink == null)
			{
				articleInLink = new ArticleInLink();
			}
			articleInLink.setName(name);
			articleInLink.setUrl(url);
			articleInLink.setPriority(priority);
			articleInLink.setCategory(category);
			articleService.saveInLink(articleInLink);
			addActionMessage("保存成功!");
		}
		catch(Exception e) {
			addActionMessage("保存失败!");
		}
		return index();
	}
	
	 public String edit()
	 {
		if(id != null)
			articleInLink = articleService.getInLink(id);
        return "edit";
	  }
	
	public String delete() {
		try {
			articleService.deleteInLink(id);
			addActionMessage("删除成功!");
		}
		catch(Exception e) {
			addActionMessage("删除失败!");
		}
		return index();
	}

	public ArticleInLink getArticleInLink() {
		return articleInLink;
	}

	public void setArticleInLink(ArticleInLink articleInLink) {
		this.articleInLink = articleInLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public List<ArticleInLink> getInLinkList() {
		return inLinkList;
	}

	public String getMessage() {
		return message;
	}
}
