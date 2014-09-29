package com.xsc.lottery.entity.business;

import javax.persistence.Column;
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
@Table(name = "business_article_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_ArticleCategory", allocationSize = 1, initialValue = 1, sequenceName = "S_ArticleCategory")
public class ArticleCategory  extends BaseObject
{
	@Id
    @GeneratedValue
    private Long id;
	
	/*
	 * 分类名称
	 */
	private String name;
	
	/*
	 * 分类标题
	 */
	@Column(nullable = false)
    private String title;
	
	/*
	 * 关键词
	 */
	private String keywords;
	
	/*
	 * 分类描述
	 */
	private String description;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
