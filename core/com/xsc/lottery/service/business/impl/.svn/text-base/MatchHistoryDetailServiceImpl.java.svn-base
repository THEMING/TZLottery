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
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.League;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.MatchHistoryDetail;
import com.xsc.lottery.entity.business.Team;
import com.xsc.lottery.service.business.LeagueService;
import com.xsc.lottery.service.business.MatchHistoryDetailService;
import com.xsc.lottery.service.business.MatchHistoryService;
import com.xsc.lottery.service.business.TeamService;
import com.xsc.lottery.util.DateUtil;


@Service("matchHistoryDetailService")
@Transactional
public class MatchHistoryDetailServiceImpl implements MatchHistoryDetailService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<MatchHistoryDetail, Long> matchHistoryDetailDao;
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.matchHistoryDetailDao = new PagerHibernateTemplate<MatchHistoryDetail, Long>(
                sessionfactory, MatchHistoryDetail.class);
    }
    
    @Autowired
    public LeagueService leagueService;
    
    @Autowired
    public TeamService teamService;
    
    @Autowired
    public MatchHistoryService matchHistoryService;
    
    public MatchHistoryDetail findById(Long id)
    {
    	return (MatchHistoryDetail) matchHistoryDetailDao.getSession().get(MatchHistoryDetail.class, id);
    }

    public MatchHistoryDetail load(Long id)
    {
    	return  null;
    }

    public MatchHistoryDetail save(MatchHistoryDetail entity) 
    {
    	matchHistoryDetailDao.save(entity);
    	return entity;
    }

    public MatchHistoryDetail update(MatchHistoryDetail entity)
    {
    	matchHistoryDetailDao.save(entity);
        return entity;
    }

    public void delete(MatchHistoryDetail entity) 
    {
    	matchHistoryDetailDao.delete(entity);
    }
    
    @SuppressWarnings("unchecked")
	public List<MatchHistoryDetail> getAllMatch() 
	{
		Criteria criteria = matchHistoryDetailDao.createCriteria();
		criteria.add(Restrictions.like("startTime", "2012%"));
		return  criteria.list();
	}
    
    /**把matchhistorydetail中的数据导入到league matchHistory team表中*/
    public void saveToDateBase()
    {
    	List<MatchHistoryDetail> detailList = this.getAllMatch();
    	for(int i=0; i<detailList.size(); i++)
    	{
    		League league = new League();
        	Team hostTeam = new Team();
        	Team visitTeam = new Team(); 
        	MatchHistory match = new MatchHistory();
        	MatchHistoryDetail matchHistoryDetail = detailList.get(i);
    		Long leagueId = Long.parseLong(matchHistoryDetail.getLeagueId());
    		League checkLeague = leagueService.findById(leagueId);
    		if(checkLeague == null)
    		{
    			league.setId(leagueId);
        		league.setName(matchHistoryDetail.getLeagueName().split("\\|")[0]);
        		leagueService.save(league);
        		match.setLeague(league);
    		} 
    		else
    		{
    			match.setLeague(checkLeague);
    		}
    		Long hostTeamId = Long.parseLong(matchHistoryDetail.getHostId());
    		Team checkHTeam = teamService.findById(hostTeamId);
    		if(checkHTeam == null)
    		{
        		hostTeam.setId(hostTeamId);
        		String[] hstr = matchHistoryDetail.getHostName().split("\\|");
        		if(hstr.length == 0)
        		{
        			hostTeam.setName("miss");
        		}
        		else
        		{
        			hostTeam.setName(hstr[0]);
        		}
        		teamService.save(hostTeam);
        		match.setHostTeam(hostTeam);
    		}
    		else
    		{
    			match.setHostTeam(checkHTeam);
    		}
    		Long visitTeamId = Long.parseLong(matchHistoryDetail.getVisitId());
    		Team checkVTeam = teamService.findById(visitTeamId);
    		if(checkVTeam == null)
    		{
        		visitTeam.setId(visitTeamId);
        		String[] vstr = matchHistoryDetail.getVisitName().split("\\|");
        		if(vstr.length == 0)
        		{
        			visitTeam.setName("miss");
        		}
        		else
        		{
        			visitTeam.setName(vstr[0]);
        		}      		
        		teamService.save(visitTeam);
        		match.setVisitTeam(visitTeam);
    		}
    		else
    		{
    			match.setVisitTeam(checkVTeam);
    		}
    		match.setId(matchHistoryDetail.getId());
    		String[] hscores = matchHistoryDetail.getHostGoal().split("\\|");
    		if(hscores.length == 0)
    		{
    			match.setHostHalfScore(111);
    			match.setHostScore(111);
    		}
    		else if(hscores.length == 1)
    		{
    			int hs = Integer.parseInt(hscores[0]);
        		match.setHostScore(hs);
        		match.setHostHalfScore(111);
    		}
    		else
    		{
    			if(hscores[0].equals(""))
    			{
    				hscores[0] = "111";
    			}
    			int hs = Integer.parseInt(hscores[0]);
    			if(hscores[1].equals(""))
    			{
    				hscores[1] = "111";
    			}
    			int hhs = Integer.parseInt(hscores[1]);
    			match.setHostHalfScore(hhs);
    			match.setHostScore(hs);
    		}
    		String[] vscores = matchHistoryDetail.getVisitGoal().split("\\|");
    		if(vscores.length == 0)
    		{
    			match.setVisitHalfScore(111);
    			match.setVisitScore(111);
    		}
    		else if(vscores.length == 1)
    		{
    			int vs = Integer.parseInt(vscores[0]);
        		match.setVisitScore(vs);
        		match.setVisitHalfScore(111);
    		}
    		else
    		{
    			if(vscores[0].equals(""))
    			{
    				vscores[0] = "111";
    			}
    			int vs = Integer.parseInt(vscores[0]); 
    			if(vscores[0].equals(""))
    			{
    				vscores[1] = "111";
    			}
    			int vhs = Integer.parseInt(vscores[1]);
    			match.setVisitHalfScore(vhs);
    			match.setVisitScore(vs);
    		}
    		match.setStatus(matchHistoryDetail.getStatus());
    		String datestr = matchHistoryDetail.getStartTime();
//    		if(datestr.length() < 16)
//    		{
//    			datestr = "1111-11-11 11:11";
//    		}
    		match.setMatchStartTime(DateUtil.parse(datestr, "yyyy-MM-dd HH:mm"));
    		match.setId(matchHistoryDetail.getId());
    		matchHistoryService.save(match);
    	}
    }
}
