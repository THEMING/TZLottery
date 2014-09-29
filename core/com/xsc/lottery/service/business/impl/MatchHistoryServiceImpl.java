package com.xsc.lottery.service.business.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.service.business.MatchHistoryService;

@Service("matchHistoryService")
@Transactional
public class MatchHistoryServiceImpl implements MatchHistoryService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<MatchHistory, Long> matchHistoryDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.matchHistoryDao = new PagerHibernateTemplate<MatchHistory, Long>(
                sessionfactory, MatchHistory.class);
    }

    public void delete(MatchHistory entity)
    {
        logger.debug("delete MatchHistory");
        matchHistoryDao.delete(entity);
    }

    public MatchHistory findById(Long id)
    {
        logger.debug("findById MatchHistory");
        MatchHistory matchHistory = (MatchHistory) matchHistoryDao.getSession().get(MatchHistory.class, id);
        return matchHistory;
    }
    

    public MatchHistory load(Long id)
    {
        logger.debug("load MatchHistory");
        return matchHistoryDao.get(id);
    }

    public MatchHistory save(MatchHistory entity)
    {
        logger.debug("save MatchHistory");
        this.matchHistoryDao.save(entity);
        return entity;
    }
    
    
    public MatchHistory update(MatchHistory entity)
    {
        logger.debug("update MatchHistory");
        save(entity);
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public List<MatchHistory> getHistoryByKeyWord(String s) {
    	logger.debug("根据关键字得到相应的历史队名");
    	String sql="select o from MatchHistory o where o.homeTeam like '%" + s + "%' or o.awaryTeam like '%" + s + "%' ";
    	List<MatchHistory> list =matchHistoryDao.getSession().createQuery(sql).list();
    	return list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchHistory> getHistoryByTeamName(String teamName) {
    	logger.debug("根据队名得到相应的记录");
    	Criteria criteria = matchHistoryDao.createCriteria();
    	criteria.add(Restrictions.or(Restrictions.eq("homeTeam", teamName), Restrictions.eq("awaryTeam", teamName)));
    	return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public void updateAndSave(String standardTeam, String originalTeam) 
    {
    /*	logger.debug("修改重复的队名");
    	String allTeamName = standardTeam + "|" + originalTeam;
    	String[] allTeamNames = allTeamName.split("\\|");
    	for(int i=0; i<allTeamNames.length; i++)
    	{
    		List<MatchHistory> list = this.getHistoryByTeamName(allTeamNames[i]);
    		if(list.size() > 0)
    		{
    			for(int j=0; j<list.size(); j++)
    			{
    				if(list.get(j).getHomeTeam().equals(allTeamNames[i]))
    				{
    					list.get(j).setHomeTeam(standardTeam);
    					this.save(list.get(j));
    				} 
    				else 
    				{
    					list.get(j).setAwaryTeam(standardTeam);
    					this.save(list.get(j));
    				}
    			}
    		}
    		else
    		{
    			System.out.println("MatchHistory中不存在这样的队名");
    		}
    	}*/
    }
    
    public Page<MatchHistory> getHistoryByKeyWordTimesAndType(Page<MatchHistory> page, String s,String startTime, String overTime, String leagueType)
    {
    	Criteria criteria = matchHistoryDao.createCriteria();
    	//String sql="select o from MatchHistory o where o.homeTeam like '%" + s + "%' or o.awaryTeam like '%" + s + "%' ";
    	if (leagueType != null && !leagueType.equals("")) {
            criteria.add(Restrictions.eq("matchName", leagueType));
        }
    	if (startTime != null && !startTime.equals("")) {
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date sdate = null;
    		try {
				sdate = (Date) sf.parse(startTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Calendar sTime = Calendar.getInstance();
            sTime.setTime(sdate);
            criteria.add(Restrictions.ge("matchOverTime",sTime));
    	}
    	if (overTime != null && !overTime.equals("")) {
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date odate = null;
    		try {
				odate = (Date) sf.parse(overTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Calendar oTime = Calendar.getInstance();
            oTime.setTime(odate);
            criteria.add(Restrictions.le("matchOverTime",oTime));
    	}
    	if (s != null && !s.equals("")) {
    		criteria.add(Restrictions.or(Restrictions.like("homeTeam", "%" +s + "%"), Restrictions.like("awaryTeam", "%" +s + "%")));
    		//criteria.add(Restrictions.sqlRestriction(sql));
    	}
    	criteria.addOrder(Order.desc("matchOverTime"));
    	page = matchHistoryDao.findByCriteria(page, criteria);
        return page;
    }
    
    /**得到一个球队的近num场比赛which=0代表主队=1代表客队*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchHistory> getLatestMatchByStatus(Team team, String status, int num, boolean fixed, int which)
    {
    	Criteria criteria = matchHistoryDao.createCriteria();
    	if(fixed)
    	{
    		if(which == 0)
    		{
    			criteria.add(Restrictions.eq("hostTeam", team));
    		}
    		else
    		{
    			criteria.add(Restrictions.eq("visitTeam", team));
    		}
    	}
    	else
    	{
    		criteria.add(Restrictions.or(Restrictions.eq("hostTeam", team), Restrictions.eq("visitTeam", team)));
    	}
    	criteria.add(Restrictions.eq("status", status));
    	if(status.equals("完"))
    	{
    		criteria.setMaxResults(num);
    		criteria.addOrder(Order.desc("matchStartTime"));
    	}
    	else if(status.equals("未"))
    	{
    		criteria.setMaxResults(num+1);
    		criteria.addOrder(Order.asc("matchStartTime"));
    	}
    	List<MatchHistory> list = criteria.list();
    	if(list == null || list.size() == 0)
    	{
    		return null;
    	}
    	else
    	{
    		return list;
    	}	
    }
    
    /**得到两只球队的近10场的对阵情况(赛完的)*/
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchHistory> getTwoTeamsBattles(Team team1, Team team2, boolean fixed)
    {
    	Criteria criteria = matchHistoryDao.createCriteria();
    	if(fixed)
    	{
    		criteria.add(Restrictions.and(Restrictions.eq("hostTeam", team1), Restrictions.eq("visitTeam", team2)));
    	}
    	else
    	{
    		criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("hostTeam", team1), Restrictions.eq("visitTeam", team2)), 
					 Restrictions.and(Restrictions.eq("hostTeam", team2), Restrictions.eq("visitTeam", team1))));
    	}
    	criteria.add(Restrictions.eq("status", "完"));
    	criteria.setMaxResults(10);
    	criteria.addOrder(Order.desc("matchStartTime"));
    	List<MatchHistory> list = criteria.list();
    	if(list == null || list.size() == 0)
    	{
    		return null;
    	}
    	else
    	{
        	return list;
    	}
    }
}
