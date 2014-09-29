package com.xsc.lottery.service.business.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.Restricted;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.RestrictedService;

@Service("restrictedService")
@Transactional
public class RestrictedServiceImpl implements RestrictedService{

	public PagerHibernateTemplate<Restricted, Long> restrictedDao;
	
	@Autowired
	public void setSessionFactory(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		
		this.restrictedDao = new PagerHibernateTemplate<Restricted, Long>(sessionFactory, Restricted.class);
	}
	
	public Restricted findById(Long id) {
		// TODO Auto-generated method stub
		return (Restricted) restrictedDao.getSession().get(Restricted.class, id);
	}

	public Restricted load(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Restricted save(Restricted entity) {
		// TODO Auto-generated method stub
		restrictedDao.save(entity);
		return entity;
	}

	public Restricted update(Restricted entity) {
		// TODO Auto-generated method stub
		restrictedDao.save(entity);
		return entity;
	}

	public void delete(Restricted entity) {
		// TODO Auto-generated method stub
		restrictedDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Restricted> getDataRestricteds(LotteryType type) {
		Criteria criteria = restrictedDao.createCriteria();
		if (!type.toString().equals("全部")) {
			System.out.println("不是全部......................");
			criteria.add(Restrictions.eq("type", type));
		}
		criteria.addOrder(Order.asc("type"));
		return criteria.list();
	}

	public Restricted getRestrictedByType(LotteryType type) {
		Criteria criteria = restrictedDao.createCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (Restricted) criteria.list().get(0);
	}

}
