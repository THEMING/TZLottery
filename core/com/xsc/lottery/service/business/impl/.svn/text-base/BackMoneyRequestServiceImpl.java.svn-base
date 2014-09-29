package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.enumerate.BackMoneyStatus;
import com.xsc.lottery.service.business.BackMoneyRequestService;
@Service("backMoneyRequestService")
@Transactional
public class BackMoneyRequestServiceImpl implements BackMoneyRequestService {
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

	 private PagerHibernateTemplate<BackMoneyRequest, Long> backMoneyRequestDao;
	  
	 @Autowired
	    public void setSessionFactory(
	            @Qualifier("sessionFactory") SessionFactory sessionfactory)
	    {
	        this.backMoneyRequestDao = new PagerHibernateTemplate<BackMoneyRequest, Long>(
	                sessionfactory, BackMoneyRequest.class);
	    } 
	
	public void delete(BackMoneyRequest entity) {
		// TODO Auto-generated method stub
		logger.debug("delete BackMoneyRequest");
		backMoneyRequestDao.delete(entity);
	}

	public BackMoneyRequest findById(Long id) {
		// TODO Auto-generated method stub
		logger.debug("findById BackMoneyRequest");
		BackMoneyRequest backMoneyRequest=(BackMoneyRequest)backMoneyRequestDao.getSession().get(BackMoneyRequest.class, id);
		return backMoneyRequest;
	}

	public BackMoneyRequest load(Long id) {
		// TODO Auto-generated method stub
		logger.debug("load BackMoneyRequest");
		BackMoneyRequest backMoneyRequest=backMoneyRequestDao.get(id);
		return backMoneyRequest;
	}

	public BackMoneyRequest save(BackMoneyRequest entity) {
		// TODO Auto-generated method stub
		logger.debug("save BackMoneyRequest");
		backMoneyRequestDao.save(entity);
		return entity;
	}

	public BackMoneyRequest update(BackMoneyRequest entity) {
		// TODO Auto-generated method stub
		logger.debug("update BackMoneyRequest");
		backMoneyRequestDao.getSession().update(entity);
		return entity;
	}
    /**
     *提现金额统计
     */
	public BigDecimal getSumMoneyByApplyTime(Calendar f_stime, Calendar f_etime) {
		// TODO Auto-generated method stub
		Criteria criteria=backMoneyRequestDao.createCriteria();
		criteria.setProjection(Projections.projectionList()
	    .add(Projections.sum("money")));
		if(null!=f_stime){
			criteria.add(Restrictions.ge("applyTime", f_stime));
		}
		if(null!=f_etime){
			criteria.add(Restrictions.le("applyTime", f_etime));
		}
		
		List list= criteria.add(Restrictions.eq("status", BackMoneyStatus.已成功)).list();
		BigDecimal money=new BigDecimal(0.00);
		Object obj= list.get(0);
		if(obj!=null){
			money=(BigDecimal)obj;
		}
		return money;
	}

}
