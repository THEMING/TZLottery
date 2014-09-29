package com.xsc.lottery.service.business.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.business.YouHuiMa;
import com.xsc.lottery.entity.business.YouHuiMa.YouHuiMaType;
import com.xsc.lottery.service.business.YouHuiMaService;

@Service("youHuiMaService")
@Transactional
public class YouHuiMaServiceImpl implements YouHuiMaService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private PagerHibernateTemplate<YouHuiMa, Long> youHuiMaDao;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
    	 this.youHuiMaDao = new PagerHibernateTemplate<YouHuiMa, Long>(
                 sessionfactory, YouHuiMa.class);
        
    }


	@SuppressWarnings("unchecked")
	public List<YouHuiMa> getYouHuiMaByNumber(String number) {
		
		logger.debug("按优惠码获得记录");
        String hql = "select o from YouHuiMa o where number=:number order by id asc";
        Query query = youHuiMaDao.getSession().createQuery(hql);
        query.setParameter("number", number);
        List<YouHuiMa> list = query.list();
        return list;
	}

	public void delete(YouHuiMa entity) {
		
		logger.debug("delete YouHuiMaDao");
		youHuiMaDao.delete(entity);

	}

	public YouHuiMa findById(Long id) {
		
		logger.debug("findById YouHuiMaDao");
        return (YouHuiMa) youHuiMaDao.getSession().get(YouHuiMa.class, id);
	}

	public YouHuiMa load(Long id) {
		
		logger.debug("load YouHuiMaDao");
        return youHuiMaDao.get(id);
	}

	public YouHuiMa save(YouHuiMa entity) {
		
	    logger.debug("save YouHuiMaDao");
        this.youHuiMaDao.save(entity);
        return entity;
	}

	public YouHuiMa update(YouHuiMa entity) {
		
		logger.debug("update YouHuiMaDao");
        save(entity);
        return null;
	}


	@SuppressWarnings("unchecked")
	public List<YouHuiMa> getYouHuiMaAll() {
		
		logger.debug("获得所有优惠码。");
        String hql = "select o from YouHuiMa o order by id asc";
        Query query = youHuiMaDao.getSession().createQuery(hql);
        List<YouHuiMa> list = query.list();
        return list;
	}


	@SuppressWarnings("unchecked")
	public List<YouHuiMa> getYouHuiMaByType(YouHuiMaType type) {
		
		logger.debug("获得type类型的优惠码。");
        String hql = "select o from YouHuiMa o where type=:type order by id asc";
        Query query = youHuiMaDao.getSession().createQuery(hql);
        query.setParameter("type", type);
        List<YouHuiMa> list = query.list();
        return list;
	}


	@SuppressWarnings("unchecked")
	public List<YouHuiMa> getYouHuiMaByFanWei(String zxz, String zdz) {
		
		logger.debug("获得该范围内的优惠码。");
		Criteria criteria = youHuiMaDao.createCriteria();
		criteria.add(Restrictions.between("number", zxz+'%', zdz+'%'));
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	public List<YouHuiMa> findYouHuiMa(YouHuiMaType type, Activity activity) {
		Criteria criteria = youHuiMaDao.createCriteria();
		if(activity != null && activity.getId() != null)
			criteria.add(Restrictions.eq("activity", activity));
		if(type != null)
			criteria.add(Restrictions.eq("type", type));
		return criteria.list();
	}

}
