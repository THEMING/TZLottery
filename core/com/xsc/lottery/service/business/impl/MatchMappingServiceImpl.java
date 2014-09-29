package com.xsc.lottery.service.business.impl;



import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.MatchMapping;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.MatchMappingService;

@Service("matchMappingService")
@Transactional
public class MatchMappingServiceImpl implements MatchMappingService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<MatchMapping, Long> matchMappingDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.matchMappingDao = new PagerHibernateTemplate<MatchMapping, Long>(
                sessionfactory, MatchMapping.class);
    }

    public void delete(MatchMapping entity)
    {
        logger.debug("delete matchMapping");
        matchMappingDao.delete(entity);
    }

    public MatchMapping findById(Long id)
    {
        logger.debug("findById matchMapping");
        MatchMapping matchMapping = (MatchMapping) matchMappingDao.getSession().get(MatchMapping.class, id);
        return matchMapping;
    }
    

    public MatchMapping load(Long id)
    {
        logger.debug("load matchMapping");
        return matchMappingDao.get(id);
    }

    public MatchMapping save(MatchMapping entity)
    {
        logger.debug("save matchMapping");
        this.matchMappingDao.save(entity);
        return entity;
    }
    
    
    public MatchMapping update(MatchMapping entity)
    {
        logger.debug("update matchMapping");
        save(entity);
        return null;
    }
    
    /**根据类型，场次得到相应的MatchMapping*/
    public MatchMapping getMatchMappingByTypeAndMatchNo(String type, String matchNo)
    {
    	Criteria criteria = matchMappingDao.createCriteria();
    	LotteryType lotteryType;
    	if(type.equals("sf14"))
		  {
			  lotteryType = LotteryType.足彩14场;
		  }
		  else if(type.equals("jq4"))
		  {
			  lotteryType = LotteryType.四场进球;
		  }
		  else if(type.equals("bq6"))
		  {
			  lotteryType = LotteryType.足彩6场半;
		  }
		  else
		  {
			  lotteryType = LotteryType.竞彩足球;
		  }
    	criteria.add(Restrictions.eq("type", lotteryType));
    	criteria.add(Restrictions.eq("matchNo", matchNo));
    	MatchMapping matchMapping = (MatchMapping) criteria.uniqueResult();
    	return matchMapping;
    }
    
    /**根据类型，期此，场次得到相应的MatchMapping*/
    public MatchMapping getMatchMappingByTypeNoAndTermNo(String type, String no, String termNo)
    {
    	Criteria criteria = matchMappingDao.createCriteria();
    	LotteryType lotteryType;
    	if(type.equals("sf14"))
		  {
			  lotteryType = LotteryType.足彩14场;
		  }
		  else if(type.equals("jq4"))
		  {
			  lotteryType = LotteryType.四场进球;
		  }
		  else if(type.equals("bq6"))
		  {
			  lotteryType = LotteryType.足彩6场半;
		  }
		  else
		  {
			  lotteryType = LotteryType.竞彩足球;
		  }
    	criteria.add(Restrictions.eq("type", lotteryType));
    	criteria.add(Restrictions.eq("no", no));
    	criteria.add(Restrictions.eq("termNo", termNo));
    	MatchMapping matchMapping = (MatchMapping) criteria.uniqueResult();
    	return matchMapping;
    }
}
