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
import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.task.message.MessageTaskExcutor;


@Service("AdminMobileService")
@Transactional
public class AdminMobileServiceImpl implements AdminMobileService {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<AdminMobile, Long> adminmobileDao;

    @Autowired
    public MessageTaskExcutor messageTaskExcutor;
   
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.adminmobileDao = new PagerHibernateTemplate<AdminMobile, Long>(
                sessionfactory, AdminMobile.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<AdminMobile> getAllAdminMobile()
	{
    	Criteria criteria = this.adminmobileDao.createCriteria();
    	return criteria.list();
	}
    
	@SuppressWarnings("unchecked")
	public List<AdminMobile> getAllActiveAdminMobile()
	{
    	Criteria criteria = this.adminmobileDao.createCriteria();
    	criteria.add(Restrictions.eq("active", 1));
    	return criteria.list();
	}

	public int AddMobileNotify(String szContent, int nType)
	{
		int nCount = 0;
		List<AdminMobile> list = getAllActiveAdminMobile();
		for(int i = 0; i < list.size(); i++)
		{
			AdminMobile mobile = list.get(i);
			mobile.setContent(szContent);
			//messageTaskExcutor.addNotifyAdmin(mobile, nType);
			nCount++;
		}
		return nCount;
	}
	
	public void delete(AdminMobile entity) {
        logger.debug("delete AdminMobile");
        adminmobileDao.delete(entity);
	}

	public AdminMobile findById(Long id) {
        logger.debug("findById AdminMobile");
        AdminMobile mobile = (AdminMobile) adminmobileDao.getSession().get(AdminMobile.class, id);
        return mobile;
	}

	public AdminMobile load(Long id) {
        logger.debug("load AdminMobile");
        return adminmobileDao.get(id);
	}

	public AdminMobile save(AdminMobile entity) {
        logger.debug("save AdminMobile");
        this.adminmobileDao.save(entity);
        return entity;
	}

	public AdminMobile update(AdminMobile entity) {
        logger.debug("update AdminMobile");
        save(entity);
        return entity;
	}

}
