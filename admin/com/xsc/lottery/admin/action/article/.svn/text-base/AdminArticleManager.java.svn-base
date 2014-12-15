package com.xsc.lottery.admin.action.article;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.entity.business.ArticleInLink;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.entity.enumerate.ArticleType;
import com.xsc.lottery.entity.enumerate.CustomerType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.web.action.json.JsonMsgBean;


@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.ArticleManagement")
public class AdminArticleManager extends AdminBaseAction
{
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PartnerService partnerService;
	
	public SimpleHibernateTemplate<Partner, Long> partnerDao;
    
	private String title;
	private String date;
	private String content;
	private String author;
	private String type;
	private LotteryType lotteryType;
	private boolean top = false;
	private boolean needLittlePicture = false;
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
	
	private  List<Customer> customerList = new ArrayList<Customer>();
	private  Customer customer = null;
	
	private  List<Partner> partnerList = new ArrayList<Partner>();
	private  Partner partner = null;
	
	private Calendar startTime;
	private Calendar endTime;
	
	private int pageNo = 1;
    private int pageSize = 10;
    
	private String message;
	
	private String littlePictureCode;
	
	/*是否图片管理页面的请求*/
	private boolean pictureReuqest = false;
 	
	public SimpleHibernateTemplate<ArticleCategory, Long> categoryDao;
	
	public SimpleHibernateTemplate<Article, Long> articleDao;

	private Boolean isPicture = false;
	
	private String partnerIdss;
	
	private Map<Long, Object> mapPartnerSelected = new HashMap<Long, Object>();

	private Boolean isPublic;
	
	@Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.categoryDao = new SimpleHibernateTemplate<ArticleCategory, Long>(
                sessionfactory, ArticleCategory.class);
        
        this.partnerDao = new SimpleHibernateTemplate<Partner, Long>(sessionfactory,
                Partner.class);
        
        this.articleDao = new SimpleHibernateTemplate<Article, Long>(sessionfactory,
        		Article.class);
    }

	/**
	 * @return
	 */
	public String index()
    {
		if(pictureReuqest){//图片查询
			categoryList = categoryDao.createCriteria().addOrder(Order.desc("id")).list();
//			customerList =  customerService.getCustomerByProperty("customerType", CustomerType.AgentCustomer);
			partnerList = partnerDao.createCriteria().addOrder(Order.desc("id")).list();
			
			page = new Page<Article>();
	        page.setPageNo(pageNo);
	        page.setPageSize(pageSize);
	        page.setAutoCount(true);
			page = articleService.getPicturePage(page, startTime, endTime, type, lotteryType,category,title,partner);
		}else{//新闻查询
			categoryList = articleService.findAllCategory();
			page = new Page<Article>();
	        page.setPageNo(pageNo);
	        page.setPageSize(pageSize);
	        page.setAutoCount(true);
			page = articleService.getArticlePage(page, startTime, endTime, type, lotteryType,category,title);
		}
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
//			article.setPartner(partner);
			article.setIsPicture(isPicture);
			article.setIsPublic(isPublic);
			article.setTop(top);
			article.setShortTitle(shortTitle);
			article.setKeywords(keywords);
			article.setDescription(description);
			article.setRelation(relation);
			article.setJumpUrl(jumpUrl);
			article.setCategory(category);
			ArticleCategory curCategory = articleService.getCategory(category.getId());
			article.setType(curCategory.getName());
			
			if(partnerIdss!=null&&!"".equals(partnerIdss)){
				String[] partnerIds = partnerIdss.split(",");
				Set<Partner> pS = new HashSet<Partner>();
				for(String partnerId:partnerIds){
					Partner p = partnerService.findById(Long.parseLong(partnerId));
					pS.add(p);
				}
				article.setPartners(pS);
			}
			
			articleService.save(article);
			
			if(needLittlePicture){
				
				Article pict = new Article();
				pict.setContent(littlePictureCode);
				pict.setJumpUrl("/actinfo/news_"+article.getId()+".html");
				pict.setTitle(title+"的缩略图");
				pict.setShortTitle(title);
				pict.setPublishTime(DateUtil.parseTimeStamp(date));
				pict.setCategory(category);
				pict.setIsPicture(true);
				articleService.save(pict);
			}
			
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
		if(pictureReuqest){//图片管理页面来的请求，将类型倒序 查询代理商账号
			categoryList = categoryDao.createCriteria().addOrder(Order.desc("id")).list();
//			customerList =  customerService.getCustomerByProperty("customerType", CustomerType.AgentCustomer);
//			partnerList = partnerDao.createCriteria().addOrder(Order.desc("id")).list();
		}else{
			categoryList = articleService.findAllCategory();
		}
		
		partnerList = partnerDao.createCriteria().addOrder(Order.desc("id")).list();
		
		if(id != null)
		{
			article = articleService.findById(id);
			type = article.getType();
			lotteryType = article.getLotteryType();
			category = article.getCategory();
//			partner = article.getPartner();
			Set<Partner> pS = article.getPartners();
			if(pS!=null){
				for(Partner p:pS){
					mapPartnerSelected.put(p.getId(), p.getId());
				}
			}
		}
		return "edit";
		
	}
	
	public String getPicture()
	{
		Map map = new HashMap();		
		
		Article a = articleService.findById(id);
		
		map.put("content", a.getContent());
		setJsonString(JsonMsgBean.MapToJsonString(map));
		
		return AJAXJSON;
		
	}

	public Boolean getIsPublic()
	{
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic)
	{
		this.isPublic = isPublic;
	}

	public Map<Long, Object> getMapPartnerSelected()
	{
		return mapPartnerSelected;
	}

	public void setMapPartnerSelected(Map<Long, Object> mapPartnerSelected)
	{
		this.mapPartnerSelected = mapPartnerSelected;
	}

	public String getPartnerIdss()
	{
		return partnerIdss;
	}

	public void setPartnerIdss(String partnerIdss)
	{
		this.partnerIdss = partnerIdss;
	}

	public String getLittlePictureCode()
	{
		return littlePictureCode;
	}

	public void setLittlePictureCode(String littlePictureCode)
	{
		this.littlePictureCode = littlePictureCode;
	}

	public boolean isNeedLittlePicture()
	{
		return needLittlePicture;
	}

	public void setNeedLittlePicture(boolean needLittlePicture)
	{
		this.needLittlePicture = needLittlePicture;
	}

	public List<Partner> getPartnerList()
	{
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList)
	{
		this.partnerList = partnerList;
	}

	public Partner getPartner()
	{
		return partner;
	}

	public void setPartner(Partner partner)
	{
		this.partner = partner;
	}

	public Boolean getIsPicture()
	{
		return isPicture;
	}

	public void setIsPicture(Boolean isPicture)
	{
		this.isPicture = isPicture;
	}

	public boolean isPictureReuqest()
	{
		return pictureReuqest;
	}

	public void setPictureReuqest(boolean pictureReuqest)
	{
		this.pictureReuqest = pictureReuqest;
	}

	public List<Customer> getCustomerList()
	{
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList)
	{
		this.customerList = customerList;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
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
