package com.xsc.lottery.service.business.impl;


import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.entity.business.ArticleInLink;
import com.xsc.lottery.entity.business.FriendlyLink;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.business.ArticleService;


@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<Article, Long> ArticleDao;
    public PagerHibernateTemplate<WinPrize, Long> WinPrizeDao;
    public PagerHibernateTemplate<ArticleCategory, Long> categoryDao;
    public PagerHibernateTemplate<ArticleInLink, Long> inLinkDao;
    public PagerHibernateTemplate<FriendlyLink, Long> friendlyLinkDao;
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.ArticleDao = new PagerHibernateTemplate<Article, Long>(
                sessionfactory, Article.class);
        this.WinPrizeDao = new PagerHibernateTemplate<WinPrize, Long>(
                sessionfactory, WinPrize.class);
        this.categoryDao = new PagerHibernateTemplate<ArticleCategory, Long>(
                sessionfactory, ArticleCategory.class);
        this.inLinkDao = new PagerHibernateTemplate<ArticleInLink, Long>(
                sessionfactory, ArticleInLink.class);
        this.friendlyLinkDao = new PagerHibernateTemplate<FriendlyLink,Long>(
        		sessionfactory, FriendlyLink.class);
    }
    
    public Article findById(Long id)
    {
    	return (Article) ArticleDao.getSession().get(Article.class, id);
    }
    
    public Page<WinPrize> find(Page<WinPrize> page,Long id,Calendar time,String type,String rankingType)
    {
    	Criteria criteria = WinPrizeDao.createCriteria(); 
    	if(id!=null){
    		criteria.add(Restrictions.eq("id", id));
    	}
    	if(time!=null){
    		criteria.add(Restrictions.ge("winTime", time));
    	}
    	if(type!=null){
    		criteria.add(Restrictions.eq("type", type));
    	}
    	if(rankingType!=null){
    		criteria.add(Restrictions.eq("rankingType",rankingType ));
    	}
        criteria.addOrder(Order.desc("id"));
        page = WinPrizeDao.findByCriteria(page, criteria);
        return page;
    	
    }
   public WinPrize updateWinPrize(WinPrize winPrize){
	   WinPrizeDao.save(winPrize);
	   return winPrize;
   }
   public WinPrize saveWinPrize(WinPrize winPrize){
	   WinPrizeDao.save(winPrize);
	   return winPrize;
   }
    public Article load(Long id)
    {
    	return  null;
    }

    public Article save(Article entity) 
    {
    	ArticleDao.save(entity);
    	return entity;
    }

    public Article update(Article entity)
    {
    	ArticleDao.save(entity);
        return entity;
    }

    public void delete(Article entity) 
    {
    	ArticleDao.delete(entity);
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesByTypeAndNum(String type, int num) 
	{
    	Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("type", type));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}
    
    /**为手机资讯做的分页*/
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesByTypeAndNumForPhone(String type, int num, int begin) //begin从1开始
	{
    	Criteria criteria = ArticleDao.createCriteria();
    	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    	criteria.add(Restrictions.eq("type", type));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        begin = (begin - 1) * 20;
        criteria.setFirstResult(begin);
        criteria.setMaxResults(num);
        return  criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Article> getAllArticles() 
	{
		Criteria criteria = ArticleDao.createCriteria();
		return  criteria.list();
	}
	
	public Page<Article> getArticlePage(Page<Article> page, Calendar startTime,
			Calendar endTime, String articleType, LotteryType lotteryType,ArticleCategory category,String title)
	{
		Criteria criteria = ArticleDao.createCriteria();
		if(startTime != null) {
			criteria.add(Restrictions.ge("publishTime", startTime));
		}
		
		if(endTime != null) {
			criteria.add(Restrictions.le("publishTime", endTime));
		}
		
		if(articleType != null) {
			criteria.add(Restrictions.eq("type", articleType));
		}
		
		if(lotteryType != null) {
			criteria.add(Restrictions.eq("lotteryType", lotteryType));
		}
		
		if(category != null&&category.getId()!=-1){
			criteria.add(Restrictions.eq("category", category));
		}
		
		if(!StringUtils.isEmpty(title))
		{
			criteria.add(Restrictions.like("title", "%"+title+"%"));
		}
		criteria.addOrder(Order.desc("publishTime"));
		
		return ArticleDao.findByCriteria(page, criteria);
	}
	
	public Page<Article> getPicturePage(Page<Article> page, Calendar startTime,
			Calendar endTime, String articleType, LotteryType lotteryType,ArticleCategory category,String title,Partner partner)
	{
		Criteria criteria = ArticleDao.createCriteria();
		if(startTime != null) {
			criteria.add(Restrictions.ge("publishTime", startTime));
		}
		
		if(endTime != null) {
			criteria.add(Restrictions.le("publishTime", endTime));
		}
		
		if(articleType != null) {
			criteria.add(Restrictions.eq("type", articleType));
		}
		
		if(lotteryType != null) {
			criteria.add(Restrictions.eq("lotteryType", lotteryType));
		}
		
		if(partner != null&&partner.getId()!=null&&partner.getId()!=-1) {
			criteria.add(Restrictions.in("partners", new Object[]{partner}));
		}
		
		if(category != null&&category.getId()!=-1){
			criteria.add(Restrictions.eq("category", category));
		}
		
		if(!StringUtils.isEmpty(title))
		{
			criteria.add(Restrictions.like("title", "%"+title+"%"));
		}
		criteria.add(Restrictions.eq("isPicture", true));
		criteria.addOrder(Order.desc("id"));
		
		return ArticleDao.findByCriteria(page, criteria);
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesByAuthorAndNum(String author, int num) 
	{
    	Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("author", author));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesByLotteryTypeAndNum(LotteryType type, int num)
	{
		Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", type));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getxgzxArticlesByLotteryTypeAndNum(LotteryType type, int num)
	{
		Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", type));
    	criteria.add(Restrictions.eq("type", "相关资讯"));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getExportArticlesByLotteryTypeAndNum(LotteryType type)
	{
		Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", type));
    	criteria.add(Restrictions.eq("type", "专家推荐"));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        List<Article> list = criteria.list();
        return  list;
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesByAuthorTypeAndNum(String author, LotteryType type, int num)
	{
		Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", type));
    	criteria.add(Restrictions.eq("author", author));
    	criteria.add(Restrictions.eq("type", "专家推荐"));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesBy2TypeAndNum(String ntype, LotteryType type, int num)
	{
		Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", type));
    	criteria.add(Restrictions.eq("type", ntype));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}


	public WinPrize getWinPrize(Long id) {
		WinPrize winPrize=WinPrizeDao.get(id);
		return winPrize;
	}

	public void deleteWinPrize(Long id) {
		WinPrizeDao.delete(id);
	}
	
	/**根据彩种，玩法类型，数量来获得article*/
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> getArticlesByTypeLotteryTypeAndNum(String type, LotteryType lotteryType, int num)
	{
		Criteria criteria = ArticleDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", lotteryType));
    	criteria.add(Restrictions.eq("type", type));
    	criteria.addOrder(Order.desc("top"));
        criteria.addOrder(Order.desc("publishTime"));
        criteria.setMaxResults(num);
        return  criteria.list();
	}

	public void deleteCategory(Long id)
	{
		categoryDao.delete(id);
	}

	public void deleteInLink(Long id)
	{
		inLinkDao.delete(id);
		
	}

	public List<ArticleCategory> findAllCategory()
	{
		return  categoryDao.findAll();
	}

	public List<ArticleInLink> findAllInLink()
	{
		return  inLinkDao.findAll();
	}

	public ArticleCategory getCategory(Long id)
	{
		ArticleCategory articleCategory=categoryDao.get(id);
		return articleCategory;
	}

	public ArticleInLink getInLink(Long id)
	{
		ArticleInLink articleInLink=inLinkDao.get(id);
		return articleInLink;
	}

	public ArticleCategory saveCategory(ArticleCategory articleCategory) {
		categoryDao.save(articleCategory);
		return articleCategory;
	}

	public ArticleInLink saveInLink(ArticleInLink articleInLink)
	{
		inLinkDao.save(articleInLink);
		return articleInLink;
	}

	public ArticleCategory updateCategory(ArticleCategory articleCategory)
	{
		categoryDao.save(articleCategory);
		return articleCategory;
	}

	public ArticleInLink updateInLink(ArticleInLink articleInLink)
	{
		inLinkDao.save(articleInLink);
		return articleInLink;
	}
	
	public void deleteFriendlyLink(Long id)
	  {
	    friendlyLinkDao.delete(id);
	  }

	  public List<FriendlyLink> findAllFriendlyLink()
	  {
	    return friendlyLinkDao.findAll();
	  }

	  public FriendlyLink getFriendlyLink(Long id)
	  {
	    FriendlyLink friendlyLink = (FriendlyLink)friendlyLinkDao.get(id);
	    return friendlyLink;
	  }

	  public FriendlyLink saveFriendlyLink(FriendlyLink friendlyLink)
	  {
	    friendlyLinkDao.save(friendlyLink);
	    return friendlyLink;
	  }

	  public FriendlyLink updateFriendlyLink(FriendlyLink friendlyLink)
	  {
	    friendlyLinkDao.save(friendlyLink);
	    return friendlyLink;
	  }
}
