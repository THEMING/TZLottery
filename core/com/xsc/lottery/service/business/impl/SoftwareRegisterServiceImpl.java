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
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.RechargeGift;
import com.xsc.lottery.service.business.SoftwareRegisterService;


@Service("softwareRegisterService")
@Transactional
public class SoftwareRegisterServiceImpl implements SoftwareRegisterService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<RechargeGift, Long> rechargeGiftDao;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.rechargeGiftDao = new PagerHibernateTemplate<RechargeGift, Long>(
                sessionfactory, RechargeGift.class);
    }


    @SuppressWarnings("unchecked")
	public List<RechargeGift> getInfoTab(Customer customer)
    {
    	Criteria criteria = rechargeGiftDao.createCriteria();
        criteria.add(Restrictions.eq("customer", customer));
        criteria.add(Restrictions.eq("prize", true));
        return criteria.list();
    }
    
    public int GetRemainGiftNum (Customer customer)
    {
    	Criteria criteria = rechargeGiftDao.createCriteria();
        criteria.add(Restrictions.eq("customer", customer));
        criteria.add(Restrictions.eq("prize", true));
    	criteria.add(Restrictions.eq("receive", false));
    	return criteria.list().size();
    }
    
    public RechargeGift findById(Long id)
    {
    	return (RechargeGift) rechargeGiftDao.getSession().get(RechargeGift.class, id);
    }

    public RechargeGift load(Long id)
    {
    	return  null;
    }

    public RechargeGift save(RechargeGift entity) {
    	rechargeGiftDao.save(entity);
    	return entity;
    }

    public RechargeGift update(RechargeGift entity)
    {
    	return null;
    }

    public void delete(RechargeGift entity) {
    	
    }
}
