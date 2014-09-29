package com.xsc.lottery.admin.action.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.service.business.ArticleService;

/**
 * 资讯类别管理
 * @author pengfangliang
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.CategoryManagement")
public class AdminCategoryManager extends AdminBaseAction
{
	@Autowired
	private ArticleService articleService;
	
	private Long id;
	private String name;
	private String title;
	private String keywords;
	private String description;
	private ArticleCategory articleCategory;
	
	private List<ArticleCategory> categoryList;
    
	private String message;

	public String index()
    {

		categoryList = articleService.findAllCategory();
        return SUCCESS;
    }
	
	public String save() {
		try {
			if(articleCategory == null) {
				articleCategory = new ArticleCategory();
			}
			articleCategory.setName(name);
			articleCategory.setTitle(title);
			articleCategory.setKeywords(keywords);
			articleCategory.setDescription(description);
			articleService.saveCategory(articleCategory);
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
			 articleCategory = articleService.getCategory(id);
         return "edit";
	  }
	public String delete() {
		try {
			articleService.deleteCategory(id);
			addActionMessage("删除成功!");
		}
		catch(Exception e) {
			addActionMessage("删除失败!");
		}
		return index();
	}
	
	public List<ArticleCategory> getCategoryList() {
		return categoryList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
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
	
	public String getMessage() {
		return message;
	}




}
