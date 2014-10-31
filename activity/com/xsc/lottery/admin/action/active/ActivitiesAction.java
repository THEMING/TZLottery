package com.xsc.lottery.admin.action.active;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.active.ActivityOrderType;
import com.xsc.lottery.entity.active.ActivityStatus;
import com.xsc.lottery.entity.active.ActivityType;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.active.ActivityService;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.service.listener.active.ActivityListener;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.DateUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("activity.activityManager")
public class ActivitiesAction extends AdminBaseAction
{
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ActivityListener activityListener;
    
    @Autowired
	private PartnerService partnerService;
    
    private Page<Activity> page;

    private Long id;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String name;

    private String shortName;

    private String startTime;

    private String endTime;
    
    private BigDecimal givenMoney;
    
    private BigDecimal threshold;

    private ActivityType[] activityTypes = ActivityType.values();

    private ActivityType activityType;
    
    private ActivityStatus[] activityStatuses = ActivityStatus.values();
    
    private ActivityStatus activityStatus;
    
    private ActivityOrderType[] activityOrderTypes = ActivityOrderType.values();
    
    private ActivityOrderType activityOrderType;
    
    private String sysmsg;
    
    private  List<Partner> partnerList = new ArrayList<Partner>();
	private  Partner partner = null;
	
	private  List<Article> articleList = new ArrayList<Article>();
	private  Article article = null;
	
	public SimpleHibernateTemplate<Partner, Long> partnerDao;
	
	public SimpleHibernateTemplate<Article, Long> ArticleDao;
	
	private Activity activity;
	
	private String partnerIdss;
	
	private Map<Long, Object> mapPartnerSelected = new HashMap<Long, Object>();

	private Boolean isPublic;
	
	@Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {        
        this.partnerDao = new SimpleHibernateTemplate<Partner, Long>(sessionfactory,
                Partner.class);
        
        this.ArticleDao = new SimpleHibernateTemplate<Article, Long>(sessionfactory,
        		Article.class);
    }

    public String index()
    {
        page = new Page<Activity>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = activityService.getActivityPage(page, activityType, activityStatus, null);
        return SUCCESS;
    }

    public String edit()
    {
        if (id != null && id > 0) {
            activity = activityService.findById(id);
            this.name = activity.getName();
            this.shortName = activity.getShortName();
            this.activityType = activity.getType();
            this.startTime = DateUtil.toyyyy_MM_dd_HH_mm(activity.getStartTime());
            this.endTime = DateUtil.toyyyy_MM_dd_HH_mm(activity.getEndTime());
            this.givenMoney = activity.getGivenMoney();
            this.threshold = activity.getThreshold();
            this.activityOrderType = activity.getOrderType();
            article = activity.getArticle();
//            partner = activity.getPartner();
            
            Set<Partner> pS = activity.getPartners();
			if(pS!=null){
				for(Partner p:pS){
					mapPartnerSelected.put(p.getId(), p.getId());
				}
			}
        }
        
        partnerList = partnerDao.createCriteria().addOrder(Order.desc("id")).list();
        
        articleList = ArticleDao.createCriteria().add(Restrictions.eq("isPicture", true)).list();
        
        return "edit";
    }

    public String save()
    {
        Activity activity = new Activity();
        if (id != null && id > 0) {
            activity = activityService.findById(id);
            activity.setPartners(null);
        }
        activity.setName(name);
        activity.setShortName(shortName);
        activity.setStartTime(DateUtil.parse(startTime, "yyyy-MM-dd HH:mm"));
        activity.setEndTime(DateUtil.parse(endTime, "yyyy-MM-dd HH:mm"));
        activity.setType(activityType);
        activity.setGivenMoney(givenMoney);
        activity.setThreshold(threshold);
        activity.setIsPublic(isPublic);
        activity.setOrderType(activityOrderType);
        if(article.getId()!=null){
        	article = ArticleDao.get(article.getId());
        	activity.setArticle(article);
        }else{
        	activity.setArticle(null);
        }
        
//        if(partner.getId()!=null){
//        	partner = partnerDao.get(partner.getId());
//        	activity.setPartner(partner);
//        }else{
//        	activity.setPartner(null);
//        }
        
        if(partnerIdss!=null&&!"".equals(partnerIdss)){
			String[] partnerIds = partnerIdss.split(",");
			Set<Partner> pS = new HashSet<Partner>();
			for(String partnerId:partnerIds){
				Partner p = partnerService.findById(Long.parseLong(partnerId));
				pS.add(p);
			}
			activity.setPartners(pS);
		}
        
        
        activityService.save(activity);
        
        activityListener.addNewActivity(activity);
        sysmsg = "保存成功";
        return "save";
    }

    public String getPartnerIdss()
	{
		return partnerIdss;
	}

	public void setPartnerIdss(String partnerIdss)
	{
		this.partnerIdss = partnerIdss;
	}

	public Map<Long, Object> getMapPartnerSelected()
	{
		return mapPartnerSelected;
	}

	public void setMapPartnerSelected(Map<Long, Object> mapPartnerSelected)
	{
		this.mapPartnerSelected = mapPartnerSelected;
	}

	public Boolean getIsPublic()
	{
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic)
	{
		this.isPublic = isPublic;
	}

	public Activity getActivity()
	{
		return activity;
	}

	public void setActivity(Activity activity)
	{
		this.activity = activity;
	}

	public List<Article> getArticleList()
	{
		return articleList;
	}

	public void setArticleList(List<Article> articleList)
	{
		this.articleList = articleList;
	}

	public Article getArticle()
	{
		return article;
	}

	public void setArticle(Article article)
	{
		this.article = article;
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

	public Page<Activity> getPage()
    {
        return page;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public ActivityType getActivityType()
    {
        return activityType;
    }

    public void setActivityType(ActivityType activityType)
    {
        this.activityType = activityType;
    }

    public ActivityType[] getActivityTypes()
    {
        return activityTypes;
    }

    public void setPage(Page<Activity> page)
    {
        this.page = page;
    }

    public ActivityStatus getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus)
    {
        this.activityStatus = activityStatus;
    }

    public ActivityStatus[] getActivityStatuses()
    {
        return activityStatuses;
    }

    public ActivityOrderType getActivityOrderType()
    {
        return activityOrderType;
    }

    public void setActivityOrderType(ActivityOrderType activityOrderType)
    {
        this.activityOrderType = activityOrderType;
    }

    public ActivityOrderType[] getActivityOrderTypes()
    {
        return activityOrderTypes;
    }

    public BigDecimal getGivenMoney()
    {
        return givenMoney;
    }

    public void setGivenMoney(BigDecimal givenMoney)
    {
        this.givenMoney = givenMoney;
    }

    public BigDecimal getThreshold()
    {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold)
    {
        this.threshold = threshold;
    }
    
    public String getSysmsg()
    {
        return sysmsg;
    }
}
