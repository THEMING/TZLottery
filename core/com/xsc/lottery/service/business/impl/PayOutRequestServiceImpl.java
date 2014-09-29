package com.xsc.lottery.service.business.impl;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.PayOutRequest;
import com.xsc.lottery.service.business.PayOutRequestService;


@Service("payOutRequestService")
@Transactional
public class PayOutRequestServiceImpl implements PayOutRequestService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<PayOutRequest, Long> payOutRequestDao;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.payOutRequestDao = new PagerHibernateTemplate<PayOutRequest, Long>(
                sessionfactory, PayOutRequest.class);
    }
    
    public PayOutRequest findById(Long id)
    {
    	return (PayOutRequest) payOutRequestDao.getSession().get(PayOutRequest.class, id);
    }
    
    public PayOutRequest load(Long id)
    {
    	return  null;
    }

    public PayOutRequest save(PayOutRequest entity) 
    {
    	payOutRequestDao.save(entity);
    	return entity;
    }

    public PayOutRequest update(PayOutRequest entity)
    {
    	payOutRequestDao.save(entity);
        return entity;
    }

    public void delete(PayOutRequest entity) 
    {
    	payOutRequestDao.delete(entity);
    }
    
    /**得到符合条件的全部信息记录*/
    public List<PayOutRequest> getRecordsByRestrications(Calendar stime, Calendar etime, String cardNo, String realName, String openSpace, String city, String tradeNo, BigDecimal money, int status, int flag, Calendar order_time)
    {
    	Criteria criteria = payOutRequestDao.createCriteria();
    	criteria.createAlias("backMoneyRequest", "backMoneyRequest");
    	if (stime != null) {
			criteria.add(Restrictions.ge("time", stime));
		}
    	if (etime != null) {
    		criteria.add(Restrictions.le("time", etime));
		}
    	if (StringUtils.isNotBlank(cardNo)) {
			criteria.add(Restrictions.eq("backMoneyRequest.bankCard", cardNo));
		}
    	if (StringUtils.isNotBlank(realName)) {
			criteria.add(Restrictions.eq("backMoneyRequest.realName", realName));
		}
    	if (StringUtils.isNotBlank(openSpace)) {
    		criteria.add(Restrictions.like("CRTBNK", "%"+openSpace+"%"));
		}
    	if (StringUtils.isNotBlank(city)) {
			criteria.add(Restrictions.like("CRTPVC", "%"+city+"%"));
		}
    	if (StringUtils.isNotBlank(tradeNo)) {
    		criteria.add(Restrictions.eq("YURREF", tradeNo));
		}
    	if (money.compareTo(BigDecimal.ZERO) > 0) {
			criteria.add(Restrictions.eq("money", money));
		}
    	if (order_time != null) {
			criteria.add(Restrictions.le("time", order_time));
		}
    	criteria.add(Restrictions.eq("State", status));
    	criteria.add(Restrictions.eq("progressFlag", flag));
    	criteria.addOrder(Order.asc("time"));
    	
    	return criteria.list();
    }
    
    /**分页得到符合条件的全部信息记录*/
    public List<PayOutRequest> getRecordsByRestrications(Calendar stime, Calendar etime, String cardNo, String realName, String openSpace, String city, String tradeNo, BigDecimal money, int index, int size, int status, int flag, Calendar order_time)
    {
    	Criteria criteria = payOutRequestDao.createCriteria();
    	criteria.createAlias("backMoneyRequest", "backMoneyRequest");
    	if (stime != null) {
			criteria.add(Restrictions.ge("time", stime));
		}
    	if (etime != null) {
    		criteria.add(Restrictions.le("time", etime));
		}
    	if (StringUtils.isNotBlank(cardNo)) {
			criteria.add(Restrictions.eq("backMoneyRequest.bankCard", cardNo));
		}
    	if (StringUtils.isNotBlank(realName)) {
			criteria.add(Restrictions.eq("backMoneyRequest.realName", realName));
		}
    	if (StringUtils.isNotBlank(openSpace)) {
			criteria.add(Restrictions.like("CRTBNK", "%"+openSpace+"%"));
		}
    	if (StringUtils.isNotBlank(city)) {
			criteria.add(Restrictions.like("CRTPVC", "%"+city+"%"));
		}
    	if (StringUtils.isNotBlank(tradeNo)) {
    		criteria.add(Restrictions.eq("YURREF", tradeNo));
		}
    	if (money.compareTo(BigDecimal.ZERO) > 0) {
			criteria.add(Restrictions.eq("money", money));
		}
    	if (order_time != null) {
			criteria.add(Restrictions.le("time", order_time));
		}
    	criteria.add(Restrictions.eq("State", status));
    	criteria.add(Restrictions.eq("progressFlag", flag));
    	int startPosition = (index - 1) * size;//实际开始取数据的下标位置
    	criteria.setFirstResult(startPosition);
    	criteria.setMaxResults(size);
    	criteria.addOrder(Order.asc("time"));
    	return criteria.list();
    }
    
    /**根据流水号找到相应的数据详情*/
    public PayOutRequest getRecordByTradeNo(String tradeNo)
    {
    	PayOutRequest payOutRequest = null;
    	if (StringUtils.isNotBlank(tradeNo)) {
    		payOutRequest = this.payOutRequestDao.findUniqueByProperty("YURREF", tradeNo);
		}
    	return payOutRequest;
    }
    
    /**更新记录的状态*/
    public void updateStatus(String tradeNo, int state, int progressFlag, String memo)
    {
   //  	String sql = "UPDATE business_pay_out_request SET state = ?, progress_flag= ?, state_desc = ? WHERE trade_no in ? ";
    	PayOutRequest payOutRequest = this.getRecordByTradeNo(tradeNo);
    	payOutRequest.setProgressFlag(progressFlag);
    	payOutRequest.setState(state);
    	payOutRequest.setStateDesc(memo);
    	payOutRequest.setStateTime(Calendar.getInstance());
    	payOutRequestDao.save(payOutRequest);
    	/*
    	SQLQuery query = payOutRequestDao.getSession().createSQLQuery(sql);
    	query.setParameter(0, state);
    	query.setParameter(1, progressFlag);
    	query.setParameter(2, memo);
    	query.setParameter(3, tradeNo);
    	System.out.println(sql);
    	query.executeUpdate();*/
    }

	public Page<PayOutRequest> getDatapage(Page<PayOutRequest> page, Calendar stime, Calendar etime, String name, String realname, int processFlag) {
		Criteria criteria = payOutRequestDao.createCriteria();
		criteria.createAlias("backMoneyRequest", "backMoneyRequest");
		criteria.createAlias("backMoneyRequest.customer", "customer");
		if (stime != null) {
			criteria.add(Restrictions.ge("time", stime));
		}
    	if (etime != null) {
    		criteria.add(Restrictions.le("time", etime));
		}
    	if (StringUtils.isNotBlank(name)) {
			criteria.add(Restrictions.eq("customer.nickName", name));
		}
    	if (StringUtils.isNotBlank(realname)) {
    		criteria.add(Restrictions.eq("backMoneyRequest.realName", realname));
    	}
    	if (processFlag != 0) {
    		criteria.add(Restrictions.eq("progressFlag", processFlag));
		}
        page = payOutRequestDao.findByCriteria(page, criteria);
        return page;
	}

	public Page<PayOutRequest> getAllDate(Page<PayOutRequest> page) {
		Criteria criteria = payOutRequestDao.createCriteria();
		criteria.addOrder(Order.desc("time"));
		criteria.addOrder(Order.desc("id"));
		return page;
	}

	public PayOutRequest getPayOutRequestByBMR(BackMoneyRequest backMoneyRequest) {
		Criteria criteria = payOutRequestDao.createCriteria();
		criteria.add(Restrictions.eq("backMoneyRequest", backMoneyRequest));
		return (PayOutRequest) criteria.list().get(0);
	}
}
