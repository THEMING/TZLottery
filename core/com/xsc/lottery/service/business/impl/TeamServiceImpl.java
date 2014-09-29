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
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.service.business.TeamService;

@Service("teamService")
@Transactional
public class TeamServiceImpl implements TeamService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<Team, Long> teamDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.teamDao = new PagerHibernateTemplate<Team, Long>(
                sessionfactory, Team.class);
    }

    public void delete(Team entity)
    {
        logger.debug("delete team");
        teamDao.delete(entity);
    }

    public Team findById(Long id)
    {
        logger.debug("findById team");
        Team team = (Team) teamDao.getSession().get(Team.class, id);
        return team;
    }
    

    public Team load(Long id)
    {
        logger.debug("load team");
        return teamDao.get(id);
    }

    public Team save(Team entity)
    {
        logger.debug("save Team");
        this.teamDao.save(entity);
        return entity;
    }
    
    
    public Team update(Team entity)
    {
        logger.debug("update Team");
        save(entity);
        return null;
    }
    
    /**根据队名找到相应的Team*/
    public Team getTeamByTeamName(String name)
    {
    	Criteria criteria = teamDao.createCriteria();
    	//criteria.add(Restrictions.eq("name", name));
    	criteria.add(Restrictions.like("name", "%" + name + "%"));
    	Team team = (Team) criteria.list().get(0);
    	return team;
    }
}
