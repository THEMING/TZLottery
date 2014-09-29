package com.xsc.lottery.service.business.impl;




import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
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
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.TeamMapping;
import com.xsc.lottery.service.business.TeamMappingService;


@Service("teamMappingService")
@Transactional
public class TeamMappingServiceImpl implements TeamMappingService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<TeamMapping, Long> TeamMappingDao;
    public PagerHibernateTemplate<MatchHistory, Long> MatchHistoryDao;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.TeamMappingDao = new PagerHibernateTemplate<TeamMapping, Long>(
                sessionfactory, TeamMapping.class);
        this.MatchHistoryDao = new PagerHibernateTemplate<MatchHistory, Long>(
                sessionfactory, MatchHistory.class);
    }
    
    public TeamMapping findById(Long id)
    {
    	return (TeamMapping) TeamMappingDao.getSession().get(TeamMapping.class, id);
    }

    public TeamMapping load(Long id)
    {
    	return  null;
    }
    
    public TeamMapping save(TeamMapping entity) 
    {
    	TeamMappingDao.save(entity);
    	return entity;
    }

    public TeamMapping update(TeamMapping entity)
    {
    	TeamMappingDao.save(entity);
        return entity;
    }
    
    public void delete(TeamMapping entity) 
    {
    	TeamMappingDao.delete(entity);
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TeamMapping> getTeamMappingByMatchHistoryName(String matchHistoryName) 
	{
    	Criteria criteria = TeamMappingDao.createCriteria();
    	criteria.add(Restrictions.eq("matchHistoryName", matchHistoryName));
        return  criteria.list();
	}
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TeamMapping> getTeamMappingByMatchJCZQName(String matchJCZQName) 
	{
    	Criteria criteria = TeamMappingDao.createCriteria();
    	criteria.add(Restrictions.eq("matchJCZQName", matchJCZQName));
        return  criteria.list();
	}
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TeamMapping> getTeamMappingByMatchJCLQName(String matchJCZQName) 
	{
    	Criteria criteria = TeamMappingDao.createCriteria();
    	criteria.add(Restrictions.eq("matchJCLQName", matchJCZQName));
        return  criteria.list();
	}
    
   /* @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchHistory> getMatchHistoryByHomeTeamAndAwaryTeam(String homeTeam, String awaryTeam) {
    	String matchHistoryHomeTeam = this.getTeamMappingByMatchArrayName(homeTeam).get(0).getMatchHistoryName();
    	String matchHistoryAwaryTeam = this.getTeamMappingByMatchArrayName(awaryTeam).get(0).getMatchHistoryName();
    	Criteria criteria = MatchHistoryDao.createCriteria();
    	criteria.add(Restrictions.eq("homeTeam", matchHistoryHomeTeam));
    	criteria.add(Restrictions.eq("awaryTeam", matchHistoryAwaryTeam));
    	return  criteria.list();
    }*/
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String getMatchHistoryTeamByMatchJCZQName(String matchJCZQ) {
    	String matchHistoryTeam = "";
    	if(this.getTeamMappingByMatchJCZQName(matchJCZQ).size() != 0) {
    		matchHistoryTeam = this.getTeamMappingByMatchJCZQName(matchJCZQ).get(0).getMatchHistoryName();
    	} else {
    		matchHistoryTeam = "待添加";
    	}
    	return matchHistoryTeam;
    }
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String getMatchHistoryTeamByMatchJCLQName(String matchJCLQ) {
    	String matchHistoryTeam = "";
    	if(this.getTeamMappingByMatchJCLQName(matchJCLQ).size() != 0) {
    		matchHistoryTeam = this.getTeamMappingByMatchJCLQName(matchJCLQ).get(0).getMatchHistoryName();
    	} else {
    		matchHistoryTeam = "待添加";
    	}
    	return matchHistoryTeam;
    }
 
    public void updateOriginalTeamName(String id, String ids, String standardTeamName, String originalTeamName)
    {
    	Long teamMappingId = Long.parseLong(id);
    	String[] tempIds = ids.split("\\|");
    	String allNames = standardTeamName + "|" + originalTeamName;
    	TeamMapping tempMapping = this.findById(teamMappingId);
    	tempMapping.setOriginalTeamName(allNames);
    	this.save(tempMapping);
    	for(int i=0; i<tempIds.length; i++) 
    	{
    		Long otherId = Long.parseLong(tempIds[i]);
    		TeamMapping otherMapping = this.findById(otherId);
    		this.delete(otherMapping);	
    	}
    }
    
    public Page<TeamMapping> getTeamMappingByKeyWordAndType(Page<TeamMapping> page, String s, String leagueType)
    {
    	Criteria criteria = TeamMappingDao.createCriteria();
    	if (leagueType != null && !leagueType.equals("")) {
            criteria.add(Restrictions.eq("matchName", leagueType));
        }
    	if (s != null && !s.equals("")) {
    		criteria.add(Restrictions.like("matchHistoryName", "%" +s + "%"));
    	}
    	page = TeamMappingDao.findByCriteria(page, criteria);
        return page;
    }
    
    public List<TeamMapping> getTeamMappingByKeyWordOrType(String s, String leagueType)
    {
    	Criteria criteria = TeamMappingDao.createCriteria();
    	if (leagueType != null && !leagueType.equals("")) {
            criteria.add(Restrictions.eq("matchName", leagueType));
        }
    	if (s != null && !s.equals("")) {
    		criteria.add(Restrictions.like("matchHistoryName", "%" +s + "%"));
    	}
    	List<TeamMapping> list = criteria.list();
    	return list;
    }
}
