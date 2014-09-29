package com.xsc.lottery.service.business.impl;

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
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.service.business.SupplierService;

@Service("supplierService")
@Transactional

public class SupplierServiceImpl implements SupplierService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<Supplier, Long> supplierDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.supplierDao = new PagerHibernateTemplate<Supplier, Long>(
                sessionfactory, Supplier.class);
    }
    
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllActiveSupplierList() {
    	Criteria criteria = this.supplierDao.createCriteria();
        criteria.add(Restrictions.eq("status", 1));
        criteria.addOrder(Order.asc("priority"));
    	return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> getAllActiveSupplierList(int type) {
    	Criteria criteria = this.supplierDao.createCriteria();
        criteria.add(Restrictions.eq("type", type));
        criteria.add(Restrictions.eq("status", 1));
       	return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSupplierList() {
    	Criteria criteria = this.supplierDao.createCriteria();
    	return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Supplier getSupplierByCode(int code) {
    	Criteria criteria = this.supplierDao.createCriteria();
        criteria.add(Restrictions.eq("code", code));
        List<Supplier> list = criteria.list();
        if(list.size() > 0)	return list.get(0);
        else	return null;
	}

	@SuppressWarnings("unchecked")
	public Supplier getSupplierByName(String name) {
    	Criteria criteria = this.supplierDao.createCriteria();
        criteria.add(Restrictions.eq("name", name));
        List<Supplier> list = criteria.list();
        if(list.size() > 0)	return list.get(0);
        else	return null;
	}

	public void delete(Supplier entity) {
        logger.debug("delete supplier");
        supplierDao.delete(entity);
	}

	public Supplier findById(Long id) {
        logger.debug("findById supplier");
        Supplier supplier = (Supplier) supplierDao.getSession().get(Supplier.class, id);
        return supplier;
	}

	public Supplier load(Long id) {
        logger.debug("load supplier");
        return supplierDao.get(id);
	}

	public Supplier save(Supplier entity) {
        logger.debug("save supplier");
        this.supplierDao.save(entity);
        return entity;
	}

	public Supplier update(Supplier entity) {
        logger.debug("update supplier");
        save(entity);
        return entity;
	}
	/**根据彩种和出票商编号来找到相应的出票商列表的数据*/
	public Supplier getSupplierByTypeAndCode(int type, int code)
	{
		logger.debug("根据彩种和出票商编号来找到相应的出票商列表的数据");
		Criteria criteria = this.supplierDao.createCriteria();
		criteria.add(Restrictions.eq("type", type));
		criteria.add(Restrictions.eq("code", code));
		List<Supplier> list = criteria.list();
		if (list != null) {
			return list.get(0);
		}
		else {
			return null;
		}
	}

	/**根据彩种和出票商名称来找到相应的出票商列表的数据*/
	public Supplier getSupplierByTypeAndName(int type, String name)
	{
		logger.debug("根据彩种和出票商编号来找到相应的出票商列表的数据");
		Criteria criteria = this.supplierDao.createCriteria();
		criteria.add(Restrictions.eq("type", type));
		criteria.add(Restrictions.eq("name", name));
		List<Supplier> list = criteria.list();
		if (list.size() != 0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	/**得到所有的数据*/
	public List<Supplier> getAllRecords()
	{
		logger.debug("得到所有的记录");
		Criteria criteria = this.supplierDao.createCriteria();
		return criteria.list();
	}
	
	/**根据彩种得到相应的Supplier*/
	public List<Supplier> getSupplierByType(int type)
	{
		logger.debug("根据彩种得到相应的Supplier");
		Criteria criteria = supplierDao.createCriteria();
		if (type != 0) {
			criteria.add(Restrictions.eq("type", type));
		}
		criteria.addOrder(Order.asc("type"));
		criteria.addOrder(Order.asc("priority"));
		return criteria.list();
	}
}
