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
import com.xsc.lottery.entity.business.Company;
import com.xsc.lottery.service.business.CompanyService;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PagerHibernateTemplate<Company, Long> companyDao;
    
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.companyDao = new PagerHibernateTemplate<Company, Long>(
                sessionfactory, Company.class);
    }

    public void delete(Company entity)
    {
        logger.debug("delete company");
        companyDao.delete(entity);
    }

    public Company findById(Long id)
    {
        logger.debug("findById company");
        Company company = (Company) companyDao.getSession().get(Company.class, id);
        return company;
    }
    

    public Company load(Long id)
    {
        logger.debug("load company");
        return companyDao.get(id);
    }

    public Company save(Company entity)
    {
        logger.debug("save company");
        this.companyDao.save(entity);
        return entity;
    }
    
    
    public Company update(Company entity)
    {
        logger.debug("update company");
        save(entity);
        return null;
    }
    
    //得到所有的company
    public List<Company> getAllCompanyList()
    {
    	Criteria criteria = this.companyDao.createCriteria();
    	return criteria.list();
    }
    
    /**根据名字得到相应的company*/
    public Company getCompanyByName(String companyName)
    {
    	Criteria criteria = this.companyDao.createCriteria();
    	criteria.add(Restrictions.eq("companyName", companyName));
    	return (Company)criteria.uniqueResult();
    }
}
