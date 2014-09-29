package com.xsc.lottery.service.business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
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
import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.MatchHistory;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.entity.business.Odds;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.MatchArrangeService;

@Service("matchArrangeService")
@Transactional
public class MatchArrangeServiceImpl implements MatchArrangeService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<MatchArrange, Long> matchArrangeDao;
    
    private PagerHibernateTemplate<MatchHistory, Long> matchHistoryDao;
    
    private PagerHibernateTemplate<Odd, Long> oddDao;
    
    private PagerHibernateTemplate<Odds, Long> oddsDao;
    
    private PagerHibernateTemplate<Company, Long> companyDao;
    
    @Autowired
    private LotteryOrderService orderService;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.matchArrangeDao = new PagerHibernateTemplate<MatchArrange, Long>(
                sessionfactory, MatchArrange.class);
        this.matchHistoryDao = new PagerHibernateTemplate<MatchHistory, Long>(
                sessionfactory, MatchHistory.class);
        this.oddDao= new PagerHibernateTemplate<Odd, Long>(
                sessionfactory, Odd.class);
        this.oddsDao= new PagerHibernateTemplate<Odds, Long>(
                sessionfactory, Odds.class);
        this.companyDao= new PagerHibernateTemplate<Company, Long>(
                sessionfactory, Company.class);
    }

    public void delete(MatchArrange entity)
    {
        logger.debug("delete MatchArrange");
        matchArrangeDao.delete(entity);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange findById(Long id)
    {
        logger.debug("findById MatchArrange");
        return (MatchArrange) matchArrangeDao.getSession().get(MatchArrange.class, id);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange getUniqueMatchByMatchNo(String value)
    {
    	logger.debug("findByBoutIndex MatchArrange");
    	return matchArrangeDao.findUniqueByProperty("boutIndex", value); 
    }

    public MatchArrange load(Long id)
    {
        logger.debug("load MatchArrange");
        return matchArrangeDao.get(id);
    }

    public MatchArrange save(MatchArrange entity)
    {
        logger.debug("save MatchArrange");
        this.matchArrangeDao.save(entity);
        return entity;
    }
    
    //add 2011
    
    public void saveAllMatches(List<MatchArrange> matches) 
    {
        logger.debug("save MatchArranges");
        for(int i = 0; i < matches.size(); i++) {
            this.matchArrangeDao.save(matches.get(i));
        }
    }
    
    
    public void saveMatchHistory(List<MatchHistory> matches) 
    {
        logger.debug("save MatchHistory");
        for(int i = 0; i < matches.size(); i++) {
            this.matchHistoryDao.save(matches.get(i));
        }
    }
    
    public MatchArrange update(MatchArrange entity)
    {
        logger.debug("update MatchArrange");
        save(entity);
        return null;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getMatchArrangeByTerm(LotteryTerm term) 
    {
        logger.debug("按期次得到对阵列表");
        String hql = "select o from MatchArrange o where term=:term order by id asc";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("term", term);
        List<MatchArrange> list = query.list();
        return list;
    }

    /**
     * 根据期号查找改期最后一场赛事时间
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Calendar findLastMatchByTermno(String termno)
    {
        String hql = "select max(o.matchTime) from MatchArrange o where termno=?";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter(0, termno);
        List<String> list = query.list();
        if (null != list.get(0)) {
            System.out.println(list.get(0));
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange findLastMatchByTermAndIndex(String index, LotteryTerm term, LotteryType type)
    {
          logger.debug("按彩期和彩种类，比赛场次获得比赛");
          String hql = "select o from MatchArrange o where term=:term and lotteryType=:type and boutIndex=:index";
          Query query = matchArrangeDao.getSession().createQuery(hql);
          query.setParameter("term", term);
          query.setParameter("type", type);
          query.setParameter("index", index);
          List<MatchArrange> list = query.list();
          if(!list.isEmpty()) {
              return list.get(0);
          }
          else {
              return null;
          }
    }
    
    /** 竞彩足球 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getMatchArrangeByDate(Calendar date, LotteryType type)
    {
        logger.debug("按日期得到对阵列表");
        Criteria cri = matchArrangeDao.createCriteria();
        cri.add(Restrictions.eq("lotteryType", type));
    	cri.add(Restrictions.eq("matchTime", date));
        return cri.list();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<MatchArrange> getMatchArrangeByDate(String date, LotteryType type)
    {
    	String tmpstr;
    	tmpstr = date.substring(2);
       logger.debug("按日期得到对阵列表");
       String sql="select o from MatchArrange o where substring(boutIndex,1,6)=? and o.lotteryType=? ";
       List<MatchArrange> list =matchArrangeDao.getSession().createQuery(sql)
       .setParameter(0, tmpstr).setParameter(1, type).list();
       return list;
    }
    
    public List<MatchArrange> getMatchArrangeBySshc(String date)
    {	 
    	logger.debug("将赛事回查中的数字换成胜负");
    	String sfResultOther;
    	String rfsfResultOther;
    	String dxfResultOther;
    	String sfcResultOther;
    	List<MatchArrange> list = this.getMatchArrangeByDate(date, LotteryType.竞彩篮球);
    	for(int i = 0;i<list.size();i++){
    		String sfResult = list.get(i).getSfResult();
    		if(sfResult.equals("1"))
    		{
    			sfResultOther = "主胜";
    		}
    		else 
    		{
    			sfResultOther = "主负";
    		}
    		list.get(i).setSfResultOther(sfResultOther);
    		String rfsfResult = list.get(i).getRfsfResult();
    		if(rfsfResult.equals("1"))
    		{
    			rfsfResultOther = "主胜";
    		}
    		else 
    		{
    			rfsfResultOther = "主负";
    		}
    		list.get(i).setRfsfResultOther(rfsfResultOther);
    		String dxfResult = list.get(i).getDxfResult();
    		if(dxfResult.equals("1"))
    		{
    			dxfResultOther = "大";
    		}
    		else 
    		{
    			dxfResultOther = "小";
    		}
    		list.get(i).setDxfResultOther(dxfResultOther);
    		String sfcResult = list.get(i).getSfcResult();
    		if(sfcResult.equals("01"))
    		{
    			sfcResultOther = "主胜1-5分";
    		}
    		else if(sfcResult.equals("02"))
    		{
    			sfcResultOther = "主胜6-10分";
    		}
    		else if(sfcResult.equals("03"))
    		{
    			sfcResultOther = "主胜11-15分";
    		}
    		else if(sfcResult.equals("04"))
    		{
    			sfcResultOther = "主胜16-20分";
    		}
    		else if(sfcResult.equals("05"))
    		{
    			sfcResultOther = "主胜21-25分";
    		}
    		else if(sfcResult.equals("06"))
    		{
    			sfcResultOther = "主胜26+分";
    		}
    		else if(sfcResult.equals("11"))
    		{
    			sfcResultOther = "客胜1-5分";
    		}
    		else if(sfcResult.equals("12"))
    		{
    			sfcResultOther = "客胜6-10分";
    		}
    		else if(sfcResult.equals("13"))
    		{
    			sfcResultOther = "客胜11-15分";
    		}
    		else if(sfcResult.equals("14"))
    		{
    			sfcResultOther = "客胜16-20分";
    		}
    		else if(sfcResult.equals("15"))
    		{
    			sfcResultOther = "客胜21-25分";
    		}
    		else 
    		{
    			sfcResultOther = "客胜26+分";
    		}
    		list.get(i).setSfcResultOther(sfcResultOther);

    	}
       
    	return list;
        
    }
    
    
    /** 根据场次编号获取对阵 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange getMatchInfoByMatchNo(String matchNo, LotteryType type)
    {
        logger.debug("根据场次编号获取对阵信息");
    	Criteria cri = matchArrangeDao.createCriteria();
    	cri.add(Restrictions.eq("lotteryType", type));
    	cri.add(Restrictions.eq("boutIndex", matchNo));
    	List<MatchArrange> list = cri.list();
    	if (list.isEmpty()) {
    		return null;
    	}
    	return (MatchArrange) list.get(0);
    }
    
    /** 根据场次编号获取竟彩篮球对阵 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange getMatchInfoForJCLQByMatchNoAndTpye(String matchNo)
    {
        logger.debug("根据场次编号获取竟彩篮球对阵信息");
    	Criteria cri = matchArrangeDao.createCriteria();
    	cri.add(Restrictions.eq("lotteryType", LotteryType.竞彩篮球));
    	cri.add(Restrictions.eq("boutIndex", matchNo));
    	List<MatchArrange> list = cri.list();
    	if (list.isEmpty()) {
    		return null;
    	}
    	return (MatchArrange) list.get(0);
    }
    
    /** 根据场次编号获取对阵 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getMatchesForJczqTask()
    {
        logger.debug("获取需要定时的比赛");
    	Criteria cri = matchArrangeDao.createCriteria();
    	cri.add(Restrictions.eq("lotteryType", LotteryType.竞彩足球));
    	cri.add(Restrictions.eq("status", RaceStatus.销售中));
    	List<MatchArrange> list = cri.list();
    	if (list.isEmpty()) {
    		return null;
    	}
    	return list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getCurrentMatchArrangeForJCZQ()
    {
        logger.debug("获取竞彩足球的当前对阵列表");
        LotteryType type = LotteryType.竞彩足球;
        RaceStatus status = RaceStatus.销售中;
        
        String hql = "select o from MatchArrange o where lotteryType=:type and status=:status order by boutIndex";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("status", status);
        List<MatchArrange> list = query.list();
        return list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getCurrentMatchArrangeForJCLQ()
    {
        logger.debug("获取竞彩足球的当前对阵列表");
        LotteryType type = LotteryType.竞彩篮球;
        RaceStatus status = RaceStatus.销售中;
        
        String hql = "select o from MatchArrange o where lotteryType=:type and status=:status order by boutIndex";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("status", status);
        List<MatchArrange> list = query.list();
        return list;
    }
    
    /** (分页)根据查询条件获得竞彩对阵分页列表 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<MatchArrange> getMatchPage(Page<MatchArrange> page,
    		Calendar date, RaceStatus raceStatus,  String no, LotteryType type)
    {
        logger.info("根据查询条件获得竞彩对阵分页列表");
    	Criteria criteria = matchArrangeDao.createCriteria();
//        if (date != null && !date.equals("")) {
//            criteria.add(Restrictions.eq("saleDate", date));
//        }
        if (date != null && !date.equals("")) {
            criteria.add(Restrictions.sqlRestriction("Date(sale_date)=?", date, Hibernate.CALENDAR));
        }
        
        if (raceStatus != null) {
            criteria.add(Restrictions.eq("status", raceStatus));
        }
        
        if (no != null && !no.equals("")) {
            criteria.add(Restrictions.eq("boutIndex", no));
        }
        
        if (type != null) {
        	criteria.add(Restrictions.eq("lotteryType", type));
        }
        
        criteria.addOrder(Order.asc("boutIndex"));
        page = matchArrangeDao.findByCriteria(page, criteria);
        return page;
    }
    
    /** 根据比赛编号获取销售状况 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public RaceStatus getStatusByMatchNo(String matchNo)
    {
        logger.debug("获取竞彩某场销售状态");
        LotteryType type = LotteryType.竞彩足球;
        
        String hql = "select o from MatchArrange o where lotteryType=:type and boutIndex=:matchNo";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("matchNo", matchNo);
        List<MatchArrange> list = query.list();
        return list.get(0).getStatus();
    }
    
    /**根据彩种类型和比赛场次编号获得比赛*/
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange getMatchMatchByNoAndType(String matchNo,LotteryType type)
    {
        logger.debug("获取竞彩某场销售状态"); 
        String hql = "select o from MatchArrange o where lotteryType=:type and boutIndex=:matchNo";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("matchNo", matchNo);
        List<MatchArrange> list = query.list();
        if(list == null) {
        	return null;
        }
        else {
        	return list.get(0);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getCurrentMatchArrangeForJCLQ(String szType)
    {
        logger.debug("获取竞彩篮球的当前对阵列表");
        LotteryType type = LotteryType.竞彩篮球;
        RaceStatus status = RaceStatus.销售中;
        
        String hql = "select o from MatchArrange o where lotteryType=:type and play_types like:playtype and status=:status order by boutIndex";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("status", status);
        query.setParameter("playtype", "%" + szType + "%");
        List<MatchArrange> list = query.list();
        return list;
    }
    /** 按照日期获取竞彩篮球的比赛 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MatchArrange> getLQMatchArrangeByDate(Calendar date)
    {
        logger.debug("按日期得到篮球对阵列表");
        Criteria cri = matchArrangeDao.createCriteria();
        cri.add(Restrictions.eq("lotteryType", LotteryType.竞彩篮球));
    	cri.add(Restrictions.eq("matchTime", date));
        return cri.list();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<MatchArrange> getLQMatchArrangeByDate(String date)
    {
    	String tmpstr;
    	tmpstr = date.substring(2);
       logger.debug("按日期得到篮球对阵列表");
       LotteryType type = LotteryType.竞彩篮球;
       String sql="select o from MatchArrange o where substring(boutIndex,1,6)=? and o.lotteryType=? ";
       List<MatchArrange> list =matchArrangeDao.getSession().createQuery(sql)
       .setParameter(0, tmpstr).setParameter(1, type).list();
       return list;
    }    
    
    public List<com.xsc.lottery.entity.business.Order> stopToSaleCreateHm(MatchArrange match)
    {
    	match.setStatus(RaceStatus.已停售);
    	save(match);
    	return orderService.stopTogegerSale(match);
    }
    
    /** 获取订单中最早一场比赛 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange getFirstMatchInOrder(String codes)
    {
        logger.debug("获取订单中最早一场比赛");
        String earliest = "999999999";
        String[] bets = codes.split("\\^");
        for (String bet : bets)
        {
        	String content = bet.split("\\|")[1];
        	String[] matches = content.split(",");
        	for (String match : matches)
        	{
        		String matchNo = match.split("=")[0];
        		if (matchNo.compareTo(earliest) < 0)
        			earliest = matchNo;       		
        	}        	
        }
        return matchArrangeDao.findUniqueByProperty("boutIndex", earliest);
    }
    
    /** 获取订单中最晚一场比赛 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MatchArrange getLastMatchInOrder(String codes, LotteryType type)
    {
        logger.debug("获取订单中最晚一场比赛");
        String last = "000000000";
        String[] bets = codes.split("\\^");
        for (String bet : bets)
        {
        	String content = bet.split("\\|")[1];
        	String[] matches = content.split(",");
        	for (String match : matches)
        	{
        		String matchNo = match.split("=")[0];
        		if (matchNo.compareTo(last) > 0)
        			last = matchNo;        		
        	}        	
        }
//        return matchArrangeDao.findUniqueByProperty("boutIndex", last);
        Criteria c = matchArrangeDao.createCriteria();
        c.add(Restrictions.eq("lotteryType", type));
        c.add(Restrictions.eq("boutIndex", last));
        return (MatchArrange) c.list().get(0);
    }
    
    /** 根据 比赛编号+玩法 获取比赛结果 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String getResultByMatchNo(String matchNo, PlayType playType, LotteryType type)
    {
    	logger.debug("获取竞彩某场比赛结果");
        String hql = "select o from MatchArrange o where lotteryType=:type and boutIndex=:matchNo";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("matchNo", matchNo);
        List<MatchArrange> list = query.list();
        MatchArrange match = list.get(0);
        String result = String.valueOf(match.isSpecial());
    	result += "|";
    	 if (playType.equals(PlayType.RQSPF)) {
      	   result += match.getRfsfpResult();
      	   result += "|";
      	   result += match.getSpRangfenSfp();
         }
       if (playType.equals(PlayType.SPF)) {
    	   result += match.getMatchResult();
    	   result += "|";
    	   result += match.getSpSfp();
       }
	   else if (playType.equals(PlayType.JQS)) {
		   result += match.getGoals();
		   result += "|";
		   result += match.getSpJqs();
	   }
	   else if (playType.equals(PlayType.CBF)) {
		   result += match.getWholeScore();
		   result += "|";
		   result += match.getSpBf();
	   }
	   else if (playType.equals(PlayType.BQC)) {
		   result += match.getHalfResult();
		   result += "|";
		   result += match.getSpBcsfp();
	   }
	   else if (playType.equals(PlayType.SF))
	   {
		   result += match.getSfResult();
		   result += "|";
		   result += match.getSpSf();
	   }
	   else if (playType.equals(PlayType.RFSF))
	   {
		   result += match.getRfsfResult();
		   result += "|";
		   result += match.getSpRangfenSf();
	   }
	   else if (playType.equals(PlayType.SFC))
	   {
		   result += match.getSfcResult();
		   result += "|";
		   result += match.getSpSfc();
	   }
	   else if (playType.equals(PlayType.DXF))
	   {
		   result += match.getDxfResult();
		   result += "|";
		   result += match.getSpDxf();
	   }
       return result;
    }
    
    public Page<MatchArrange> getMatchPageForPrize(Page<MatchArrange> page, LotteryType type)
	{
    	Criteria criteria = matchArrangeDao.createCriteria();
    	criteria.add(Restrictions.or(Restrictions.eq("status", RaceStatus.已停售), 
    			Restrictions.eq("status", RaceStatus.已开奖)));
    	criteria.add(Restrictions.eq("lotteryType", type));
    	criteria.addOrder(Order.asc("boutIndex"));
    	page = matchArrangeDao.findByCriteria(page, criteria);
    	return page;
	}
 public void fetchpeilv(){
    	oddsDao.getSession().createSQLQuery("Insert into business_company(id,company_name) select distinct(company_id),en_company_name from odds , business_company where en_company_name not in(company_name)").executeUpdate();
    	oddDao.getSession().createSQLQuery("Insert into business_odd(id,match_id,company_id,pankou_id,pankou_type,sheng,ping,fu) select id,match_id,company_id,t,p,o,h,u from odds where t='ep'").executeUpdate();
    	oddDao.getSession().createSQLQuery("Insert into business_odd(id,match_id,company_id,pankou_id,pankou_type,sheng,fu,rang) select id,match_id,company_id,t,p,o,u,h from odds where t='an'").executeUpdate();
    	oddDao.getSession().createSQLQuery("Insert into business_odd(id,match_id,company_id,pankou_id,pankou_type) select id,match_id,company_id,t,p from odds where t='ou'").executeUpdate();
    }
    
    public Page<Odd> findOdd(Page<Odd> page)
    {
    	Criteria criteria = oddDao.createCriteria(); 
        criteria.addOrder(Order.desc("id"));
        page = oddDao.findByCriteria(page, criteria);
        return page;
    	
    }
	public List<Odd> getOdd(String matchId) {
		Criteria criteria = oddDao.createCriteria(); 
		criteria.add(Restrictions.eq("matchId", matchId));
		List<Odd> list=criteria.list();
		return list;
	}

    /**取得竟彩的当前期的3场比赛*/
    public List<MatchArrange> getCurrentMatch()
    {
    	List<MatchArrange> listTemp = new ArrayList<MatchArrange>();
    	List<MatchArrange> list = new ArrayList<MatchArrange>();
    	Criteria criteria = matchArrangeDao.createCriteria();
    	criteria.add(Restrictions.eq("lotteryType", LotteryType.竞彩足球));
    	criteria.add(Restrictions.eq("status", RaceStatus.销售中));
    	listTemp = criteria.list();
    	Random random = new Random();
    	int num = -1;
    	int size = listTemp.size();
    	int index = 0;
    	if(size <=3)
    	{
    		num = size;
    	}
    	else
    	{
    		num = 3;
        	index = random.nextInt(listTemp.size()-3);
    	}
    	for(int i=0; i<num; i++)
    	{
    		MatchArrange temp = listTemp.get(index);
    		list.add(temp);
    		index++;
    	}   	
    	return list;
    }
    
    /**获取14场相对应的比赛 参数为 term_id和场次*/
    public MatchArrange getMatchArrangeBySome(String termid, String i) {
        String hql = "select o from MatchArrange o where term_id=:term_id and bout_index=:i order by id asc";
        Query query = matchArrangeDao.getSession().createQuery(hql);
        query.setParameter("term_id", termid);
        query.setParameter("i", i);
        return (MatchArrange) query.list().get(0);
	}
}
