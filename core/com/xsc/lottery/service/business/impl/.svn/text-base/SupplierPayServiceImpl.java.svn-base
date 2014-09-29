package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.SupplierPay;
import com.xsc.lottery.service.business.SupplierPayService;

@Service("supplierPayService")
@Transactional

public class SupplierPayServiceImpl implements SupplierPayService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<SupplierPay, Long> supplierPayDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.supplierPayDao = new PagerHibernateTemplate<SupplierPay, Long>(
                sessionfactory, SupplierPay.class);
    }

	public void delete(SupplierPay entity) {
        logger.debug("delete supplierPay");
        supplierPayDao.delete(entity);
	}

	public SupplierPay findById(Long id) {
        logger.debug("findById supplierPay");
        SupplierPay supplierPay = (SupplierPay) supplierPayDao.getSession().get(SupplierPay.class, id);
        return supplierPay;
	}

	public SupplierPay load(Long id) {
        logger.debug("load supplierPay");
        return supplierPayDao.get(id);
	}

	public SupplierPay save(SupplierPay entity) {
        logger.debug("save supplier");
        this.supplierPayDao.save(entity);
        return entity;
	}

	public SupplierPay update(SupplierPay entity) {
        logger.debug("update supplier");
        save(entity);
        return entity;
	}
	
	/**得到这一段时间内这个出票商的所有充值记录*/
	public List<SupplierPay> getRecordsByName(String name, Calendar stime, Calendar etime)
	{
		if (StringUtils.isBlank(name)) {
			return null;
		}
		Criteria criteria = this.supplierPayDao.createCriteria();
		criteria.add(Restrictions.eq("supplierName", name));
		if (stime != null) {
			criteria.add(Restrictions.ge("time", stime));
		}
		if (etime != null) {
			criteria.add(Restrictions.le("time", etime));
		}
		return criteria.list();
	}
	
	/**得到这一段时间内这个出票商的所有充值记录*/
	public Page<SupplierPay> getRecordsPageByName(Page<SupplierPay> page, String name, Calendar stime, Calendar etime)
	{
		if (StringUtils.isBlank(name)) {
			return null;
		}
		Criteria criteria = this.supplierPayDao.createCriteria();
		criteria.add(Restrictions.eq("supplierName", name));
		if (stime != null) {
			criteria.add(Restrictions.ge("time", stime));
		}
		if (etime != null) {
			criteria.add(Restrictions.le("time", etime));
		}
		page = this.supplierPayDao.findByCriteria(page, criteria);
		return page;
	}
	
	/**
	 * cbj
	 * @param page
	 * @param name
	 * @param payer
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Page<SupplierPay> getSupplierPayByElement(Page<SupplierPay> page,String name,String payer,Calendar beginTime,Calendar endTime){
		Criteria criteria=supplierPayDao.createCriteria();
		if(!StringUtils.isEmpty(name)){
			criteria.add(Restrictions.eq("supplierName", name));
		}
		if(!StringUtils.isEmpty(payer)){
			criteria.add(Restrictions.like("payer", payer,MatchMode.ANYWHERE));//,MatchMode.ANYWHERE
		}
		if(beginTime!=null){
			criteria.add(Restrictions.ge("time", beginTime));
		}
		if(endTime!=null){
			criteria.add(Restrictions.le("time", endTime));
		}
		criteria.addOrder(Order.desc("time"));
		page=supplierPayDao.findByCriteria(page, criteria);
		return page;
	}
	/**
	 * cbj
	 * 统计充值金额
	 */
	public BigDecimal getSumSupplierPay(String name,String payer,Calendar beginTime,Calendar endTime){
		Criteria criteria=supplierPayDao.getSession().createCriteria(SupplierPay.class)
			.setProjection(Projections.projectionList()
		    .add(Projections.sum("money"))
		);
		if(!StringUtils.isEmpty(name)){
			criteria.add(Restrictions.eq("supplierName", name));
		}
		if(!StringUtils.isEmpty(payer)){
			criteria.add(Restrictions.like("payer", payer,MatchMode.ANYWHERE));//,MatchMode.ANYWHERE
		}
		if(beginTime!=null){
			criteria.add(Restrictions.ge("time", beginTime));
		}
		if(endTime!=null){
			criteria.add(Restrictions.le("time", endTime));
		}
		List list= criteria.list();
		BigDecimal sumMoney=new BigDecimal(0.00);
		Object obj= list.get(0);
		if(obj!=null){
			sumMoney=(BigDecimal)obj;
		}
		return sumMoney;
	}
	/**
	 * cbj
	 * 删除充值记录
	 */
	public void deleteSupplierPay(Long id){
		supplierPayDao.delete(id);
	}
	 
	
}
