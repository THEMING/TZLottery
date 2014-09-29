package com.xsc.lottery.entity.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
@SuppressWarnings("serial")
@Entity
@Table(name = "business_article_inlink")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_ArticleInLink", allocationSize = 1, initialValue = 1, sequenceName = "S_ArticleInLink")
public class ArticleInLink  extends BaseObject
{
	@Id
    @GeneratedValue
    private Long id;
	
	/*
	 * 内链名称
	 */
	private String name;
	
	/*
	 * 链接
	 */
	private String url;
	
	/*
	 * 优先级  值越大优先级越高
	 */
	private Integer priority;
	
	/*
	 * 分类 （0-网站 1-wap）
	 */
	private Integer category;

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

}
