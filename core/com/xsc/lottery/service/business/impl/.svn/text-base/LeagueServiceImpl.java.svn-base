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
import com.xsc.lottery.entity.business.League;
import com.xsc.lottery.service.business.LeagueService;

@Service("leagueService")
@Transactional
public class LeagueServiceImpl implements LeagueService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<League, Long> leagueDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.leagueDao = new PagerHibernateTemplate<League, Long>(
                sessionfactory, League.class);
    }

    public void delete(League entity)
    {
        logger.debug("delete league");
        leagueDao.delete(entity);
    }

    public League findById(Long id)
    {
        logger.debug("findById league");
        League league = (League) leagueDao.getSession().get(League.class, id);
        return league;
    }
    

    public League load(Long id)
    {
        logger.debug("load league");
        return leagueDao.get(id);
    }

    public League save(League entity)
    {
        logger.debug("save League");
        this.leagueDao.save(entity);
        return entity;
    }
    
    
    public League update(League entity)
    {
        logger.debug("update League");
        save(entity);
        return null;
    }
    
    /**根据联赛名称的到相应的League*/
    public League getLeagueByName(String name)
    {
    	Criteria criteria = leagueDao.createCriteria();
    	criteria.add(Restrictions.eq("name", name));
    	League league = (League) criteria.list().get(0);
    	return league;
    }
}
