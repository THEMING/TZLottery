package com.xsc.lottery.service.business.impl;


import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.service.business.CompanyService;
import com.xsc.lottery.service.business.MatchHistoryService;
import com.xsc.lottery.service.business.OddService;

@Service("oddService")
@Transactional
public class OddServiceImpl implements OddService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<Odd, Long> oddDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.oddDao = new PagerHibernateTemplate<Odd, Long>(
                sessionfactory, Odd.class);
    }
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private MatchHistoryService matchHistoryService;
    
    public void delete(Odd entity)
    {
        logger.debug("delete odd");
        oddDao.delete(entity);
    }

    public Odd findById(Long id)
    {
        logger.debug("findById odd");
        Odd odd = (Odd) oddDao.getSession().get(Odd.class, id);
        return odd;
    }
    

    public Odd load(Long id)
    {
        logger.debug("load odd");
        return oddDao.get(id);
    }

    public Odd save(Odd entity)
    {
        logger.debug("save odd");
        this.oddDao.save(entity);
        return entity;
    }
    
    
    public Odd update(Odd entity)
    {
        logger.debug("update odd");
        save(entity);
        return null;
    }
    
    //得到这场比赛这个公司type类型的所有odd
    public List<Odd> getOddListByMatchIdCompanyAndType(String matchId, Company company, String type, String max)
    {
    	Criteria criteria = this.oddDao.createCriteria();
    	criteria.add(Restrictions.eq("matchId", matchId));
    	criteria.add(Restrictions.eq("company", company));
    	criteria.add(Restrictions.eq("pankouType", type));
    	if(max != null && !max.equals(""))
    	{
    		criteria.add(Restrictions.eq("pankouId", max));
    	}
    	criteria.addOrder(Order.desc("pankouId"));
    	return criteria.list(); 	
    }
    
    /**得到某场比赛的所有的odd*/
    public List<Odd> getAllOddList(String matchId, String pankouType)
    {
    	List<Odd> list = new ArrayList<Odd>();
    	List<Company> comapanyList = companyService.getAllCompanyList();
    	for(int i=0; i<comapanyList.size(); i++)
    	{
    		Company company = comapanyList.get(i);
        	List<Odd> odds = this.getOddListByMatchIdCompanyAndType(matchId, company, pankouType, "");
        	for(int j=0; j<odds.size(); j++)
        	{
        		Odd odd = odds.get(j);
        		list.add(odd);
        	}
    	}
    	return list;
    }
    
    //pankouId最大的odd
    public List<Odd> getOddLastListByMatchId(String matchId, String pankouType)
    {
    	List<Odd> list = new ArrayList<Odd>();
    	List<Company> comapanyList = companyService.getAllCompanyList();
    	for(int i=0; i<comapanyList.size(); i++)
    	{
    		List<String> pankouIdList = new ArrayList<String>();
    		Company company = comapanyList.get(i);
        	List<Odd> odds = this.getOddListByMatchIdCompanyAndType(matchId, company, pankouType, "");
        	for(int j=0; j<odds.size(); j++)
        	{
        		Odd odd = odds.get(j);
        		pankouIdList.add(odd.getPankouId());
        	}
        	String max = this.getMax(pankouIdList);
        	List<Odd> oddsTemp = this.getOddListByMatchIdCompanyAndType(matchId, company, pankouType, max);
        	if(oddsTemp.size() == 0)
        	{
        		continue;
        	}
        	else
        	{
        		Odd last = oddsTemp.get(0);
        		if(max.equals("1"))
            	{
        			last.setFuStatus("--");
        			last.setPingStatus("--");
        			last.setShengStatus("--");
        			last.setRangStatus("--");
        			this.save(last);
            	}
        		else
        		{
        			String secondMax = (Integer.parseInt(max) - 1) + "";
        			Odd secondLast = this.getOddListByMatchIdCompanyAndType(matchId, company, pankouType, secondMax).get(0);
        			this.compareTwoOdd(last, secondLast);
        		}
        		String pankou = last.getPankouType();
        		this.setPankouToMatchHistory(pankou, matchId);
        		list.add(last);
        	}   	
    	}
    	return list;
    }
    
    //得到pankouId=1的odd
    public List<Odd> getOddFirstListByMatchId(String matchId, String pankouType)
    {
    	List<Company> comapanyList = companyService.getAllCompanyList();
    	List<Odd> odds = new ArrayList<Odd>();
    	for(int i=0; i<comapanyList.size(); i++)
    	{
    		Company company = comapanyList.get(i);
    		Odd odd = new Odd();
    		List<Odd> oddTemp = this.getOddListByMatchIdCompanyAndType(matchId, company, pankouType, "1");
    		if(oddTemp.size() != 0)
    		{
    			odd = oddTemp.get(0);
    		}
    		else
    		{
    			continue;
    		}
    		odds.add(odd);
    	}
    	return odds;
    }
    
    public String getMax(List<String> list)
    {
    	int max = 0;
    	for(int i=0; i<list.size(); i++)
    	{
    		int temp = Integer.parseInt(list.get(i));
    		if(max < temp)
    		{
    			max = temp;
    		}
    	}
    	String maxStr = max+"";
    	return maxStr;
    }
    
    public void setPankouToMatchHistory(String pankou, String matchId)
    {
    	MatchHistory matchHistory = matchHistoryService.findById(Long.parseLong(matchId));
    	matchHistory.setPankou(pankou);
    	matchHistoryService.save(matchHistory);
    }
    
    //比较两个ODD得到赔率变化趋势
    public void compareTwoOdd(Odd oddLast, Odd oddSecond)
    {
    	String lrang = oddLast.getRang();
    	String srang = oddSecond.getRang();
    	if(lrang != null)
    	{
			if(lrang.indexOf("/") > -1)
			{
				Double bl = Double.parseDouble(lrang.split("/")[1]);
				if(srang.indexOf("/") > -1)
				{
					Double bs = Double.parseDouble(srang.split("/")[1]);
					if(bl > bs)
					{
						oddLast.setRangStatus("上升");
					}
					else if(bl < bs)
					{
						oddLast.setRangStatus("下降");
					}
					else
					{
						oddLast.setRangStatus("--");
					}
				}
				else
				{
					Double bs = Double.parseDouble(srang);
					if(bl > bs)
					{
						oddLast.setRangStatus("上升");
					}
					else if(bl < bs)
					{
						oddLast.setRangStatus("下降");
					}
					else
					{
						oddLast.setRangStatus("--");
					}
				}
			}
			else
			{
				Double bl = Double.parseDouble(lrang);
				if(srang.indexOf("/") > -1)
				{
					Double bs = Double.parseDouble(srang.split("/")[1]);
					if(bl > bs)
					{
						oddLast.setRangStatus("上升");
					}
					else if(bl < bs)
					{
						oddLast.setRangStatus("下降");
					}
					else
					{
						oddLast.setRangStatus("--");
					}
				}
				else
				{
					Double bs = Double.parseDouble(srang);
					if(bl > bs)
					{
						oddLast.setRangStatus("上升");
					}
					else if(bl < bs)
					{
						oddLast.setRangStatus("下降");
					}
					else
					{
						oddLast.setRangStatus("--");
					}
				}
			}
    	}
    	String lping = oddLast.getPing();
    	String sping = oddSecond.getPing();
    	if(lping != null)
    	{
    		Double lp = Double.parseDouble(lping);
    		Double sp = Double.parseDouble(sping);
    		if(lp < sp)
    		{
    			oddLast.setPingStatus("下降");
    		}
    		else if(lp > sp)
    		{
    			oddLast.setPingStatus("上升");
    		}
    		else
    		{
    			oddLast.setPingStatus("--");
    		}
    	}
    	String lsheng = oddLast.getSheng();
    	String ssheng = oddSecond.getSheng();
    	if(lsheng != null)
    	{
    		Double ls = Double.parseDouble(lsheng);
    		Double ss = Double.parseDouble(ssheng);
    		if(ls < ss)
    		{
    			oddLast.setShengStatus("下降");
    		}
    		else if(ls > ss)
    		{
    			oddLast.setShengStatus("上升");
    		}
    		else
    		{
    			oddLast.setShengStatus("--");
    		}
    	}	
    	String lfu = oddLast.getFu();
    	String sfu = oddSecond.getFu();
    	if(lfu != null)
    	{
    		Double lf = Double.parseDouble(lfu);
    		Double sf = Double.parseDouble(sfu);
    		if(lf < sf)
    		{
    			oddLast.setFuStatus("下降");
    		}
    		else if(lf > sf)
    		{
    			oddLast.setFuStatus("上升");
    		}
    		else
    		{
    			oddLast.setFuStatus("--");
    		}
    	}
    	this.save(oddLast);
    }
}
