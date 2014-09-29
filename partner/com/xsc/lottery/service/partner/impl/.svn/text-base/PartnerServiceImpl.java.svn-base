package com.xsc.lottery.service.partner.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.partner.PartnerService;

@Service("partnerService")
@Transactional
public class PartnerServiceImpl implements PartnerService
{
    public transient final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public PagerHibernateTemplate<Partner, Long> partnerDao;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        partnerDao = new PagerHibernateTemplate<Partner, Long>(sessionfactory,
                Partner.class);
    }
    
    public Partner getUniquePartnerByProperty(String name, Object value)
    {
        return partnerDao.findUniqueByProperty(name, value);
    }
    
    public void delete(Partner entity)
    {
        partnerDao.delete(entity);
    }

    public Partner findById(Long id)
    {
        return (Partner) partnerDao.getSession().get(Partner.class, id);
    }

    public Partner load(Long id)
    {
        return partnerDao.get(id);
    }

    public Partner save(Partner entity)
    {
        partnerDao.save(entity);
        return entity;
    }

    public Partner update(Partner entity)
    {
        partnerDao.save(entity);
        return entity;
    }

    public Page<Partner> getPartnerPage(Page<Partner> page)
    {
        logger.debug("根据查询条件获得分页期次列表");
        Criteria criteria = partnerDao.createCriteria();
        criteria.addOrder(Order.desc("id"));
        page = partnerDao.findByCriteria(page, criteria);
        return page;
    }
}
