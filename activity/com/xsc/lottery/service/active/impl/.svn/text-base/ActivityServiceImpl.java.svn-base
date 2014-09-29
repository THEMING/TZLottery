package com.xsc.lottery.service.active.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.active.ActivityDetail;
import com.xsc.lottery.entity.active.ActivityDetailType;
import com.xsc.lottery.entity.active.ActivityStatus;
import com.xsc.lottery.entity.active.ActivityType;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.service.active.ActivityService;

@Service("activityService")
@Transactional
public class ActivityServiceImpl implements ActivityService
{
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<Activity, Long> activitydao;
    public SimpleHibernateTemplate<Order, Long> orderDao;
    public PagerHibernateTemplate<ActivityDetail, Long> activityDetaildao;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.activitydao = new PagerHibernateTemplate<Activity, Long>(
                sessionfactory, Activity.class);
        
        this.orderDao = new SimpleHibernateTemplate<Order, Long>(sessionfactory,
                Order.class);
        
        this.activityDetaildao = new PagerHibernateTemplate<ActivityDetail, Long>(
                sessionfactory, ActivityDetail.class);
    }

    public void delete(Activity entity)
    {
        activitydao.delete(entity);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Activity findById(Long id)
    {
        return (Activity)activitydao.getSession().get(Activity.class, id);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public ActivityDetail findActivityDetailById(Long id)
    {
        return (ActivityDetail)activityDetaildao.getSession().get(ActivityDetail.class, id);
    }
    
    public Activity load(Long id)
    {
        return null;
    }

    public Activity save(Activity entity)
    {
        activitydao.save(entity);
        return entity;
    }

    public Activity update(Activity entity)
    {
        activitydao.save(entity);
        return entity;
    }
    
    public ActivityDetail saveActivityDetail(ActivityDetail entity)
    {
        activityDetaildao.save(entity);
        return entity;
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Activity> getActivityPage(Page<Activity> page, ActivityType activeType, 
            ActivityStatus status, LotteryType lotteryType)
    {
        Criteria criteria = activitydao.createCriteria();
        
        if (activeType != null) {
            criteria.add(Restrictions.eq("type", activeType));
        }
        
        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }
        
        if (lotteryType != null) {
            criteria.add(Restrictions.eq("lotteryType", lotteryType));
        }
        
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = activitydao.findByCriteria(page, criteria);
        return page;
    }

    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Activity> getCurrentActivities()
    {
        Criteria criteria = activitydao.createCriteria();
        criteria.add(Restrictions.eq("status", ActivityStatus.进行中));
        List<Activity> list = criteria.list();
        return list;
    }
    
    /** 判断是否为用户第一次购彩*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public boolean isFirstOrder(Order order)
    {
        Criteria criteria = orderDao.createCriteria();
        criteria.add(Restrictions.le("status", OrderStatus.部分出票成功));
        criteria.add(Restrictions.eq("customer", order.getCustomer()));
        criteria.setMaxResults(2);
        return criteria.list().size() == 1;
    }
    
    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ActivityDetail> getActivityDetails(ActivityDetailType type, boolean finished)
    {
        Criteria criteria = activityDetaildao.createCriteria();
        if(type != null) criteria.add(Restrictions.eq("actDetailType", type));
        criteria.add(Restrictions.eq("finished", finished));
        return criteria.list();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<ActivityDetail> getActivityDetailPage(
            Page<ActivityDetail> page, ActivityDetailType type, boolean finished)
    {
        Criteria criteria = activityDetaildao.createCriteria();
        if(type != null) criteria.add(Restrictions.eq("actDetailType", type));
        criteria.add(Restrictions.eq("finished", finished));
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = activityDetaildao.findByCriteria(page, criteria);
        return page;
    }
}
