package com.xsc.lottery.entity.business;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.partner.Partner;
@SuppressWarnings("serial")
@Entity
@Table(name = "business_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Article", allocationSize = 1, initialValue = 1, sequenceName = "S_Article")
public class Article  extends BaseObject
{
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
    private String title;
	
	/*标记是否图片*/
	private Boolean isPicture;
	
	/*关联的代理商id
	@ManyToOne
	@JoinColumn(name = "partner_id")
	private Partner partner;*/
	
	@Cascade(value=CascadeType.SAVE_UPDATE)
    @ManyToMany(fetch = FetchType.LAZY)  
    @JoinTable(name = "business_article_partner",
    joinColumns =  @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false) ,
    inverseJoinColumns =  @JoinColumn(name = "partner_id", referencedColumnName = "id", nullable = false) )  
    Set<Partner> partners;  
	
	/*是否公开被代理商使用*/
	private Boolean isPublic;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ArticleCategory category;
	
	@Column(nullable = false, columnDefinition="LONGTEXT")
    private String content;
	
	@Column
	private String type;
	
	@Column
	private String author;
	
	@Column(nullable = false)
    private Calendar publishTime;
	
	private Integer readNum;
	
	private LotteryType lotteryType;
	
	//置顶
	private boolean top = false;
	
	/*
	 * 短标题
	 */
	private String shortTitle;
	
	/*
	 * 关键词
	 */
	private String keywords;
	
	/*
	 * 描述
	 */
	private String description;
	
	/*
	 * 关联关键词
	 */
	private String relation;
	
	/*
	 * 跳转地址
	 */
	private String jumpUrl;
	
	public Boolean getIsPublic()
	{
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic)
	{
		this.isPublic = isPublic;
	}

	public Set<Partner> getPartners()
	{
		return partners;
	}

	public void setPartners(Set<Partner> partners)
	{
		this.partners = partners;
	}

	/*public Partner getPartner()
	{
		return partner;
	}

	public void setPartner(Partner partner)
	{
		this.partner = partner;
	}*/

	public Boolean getIsPicture()
	{
		return isPicture;
	}

	public void setIsPicture(Boolean isPicture)
	{
		this.isPicture = isPicture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArticleCategory getCategory() {
		return category;
	}

	public void setCategory(ArticleCategory category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Calendar publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
        this.id = id;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LotteryType getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(LotteryType lotteryType) {
		this.lotteryType = lotteryType;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
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
}
