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
import com.xsc.lottery.entity.business.LeagueRank;
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.service.business.LeagueRankService;
import com.xsc.lottery.service.business.LeagueService;
import com.xsc.lottery.service.business.TeamService;

@Service("leagueRankService")
@Transactional
public class LeagueRankServiceImpl implements LeagueRankService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<LeagueRank, Long> leagueRankDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.leagueRankDao = new PagerHibernateTemplate<LeagueRank, Long>(
                sessionfactory, LeagueRank.class);
    }
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private LeagueService leagueService;

    public void delete(LeagueRank entity)
    {
        logger.debug("delete leagueRank");
        leagueRankDao.delete(entity);
    }

    public LeagueRank findById(Long id)
    {
        logger.debug("findById leagueRank");
        LeagueRank leagueRank = (LeagueRank) leagueRankDao.getSession().get(LeagueRank.class, id);
        return leagueRank;
    }
    

    public LeagueRank load(Long id)
    {
        logger.debug("load leagueRank");
        return leagueRankDao.get(id);
    }

    public LeagueRank save(LeagueRank entity)
    {
        logger.debug("save leagueRank");
        this.leagueRankDao.save(entity);
        return entity;
    }
    
    
    public LeagueRank update(LeagueRank entity)
    {
        logger.debug("update leagueRank");
        save(entity);
        return null;
    }
    
    /**根据队名,联赛名得到相应的LeagueRank*/
    public LeagueRank getLeagueRankByTeamNameAndLeagueName(String teamName, String leagueName)
    {
    	logger.debug("根据队名,联赛名得到相应的LeagueRank");
    	Criteria criteria = this.leagueRankDao.createCriteria();
    	Team team = teamService.getTeamByTeamName(teamName);
    	criteria.add(Restrictions.eq("team", team));
    	if(leagueName != null && !leagueName.equals(""))
    	{
    		League league = leagueService.getLeagueByName(leagueName);   	
        	criteria.add(Restrictions.eq("league", league));
    	}
    	LeagueRank leagueRank = (LeagueRank) criteria.uniqueResult();
    	return leagueRank;
    }
    
    /**根据TeamId,LeagueId得到相应的LeagueRank*/
    public LeagueRank getLeagueRankByTeamIdAndLeagueId(Long teamId, Long leagueId)
    {
    	logger.debug("根据TeamId,LeagueId得到相应的LeagueRank");
    	Criteria criteria = this.leagueRankDao.createCriteria();
    	Team team = this.teamService.findById(teamId);
    	League league = this.leagueService.findById(leagueId);
    	criteria.add(Restrictions.eq("team", team));
    	criteria.add(Restrictions.eq("league", league));
    	LeagueRank leagueRank = (LeagueRank) criteria.uniqueResult();
    	return leagueRank;
    }
}
