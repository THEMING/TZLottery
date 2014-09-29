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
import com.xsc.lottery.entity.business.OrderQueue;
import com.xsc.lottery.service.business.OrderQueueService;


@Service("orderQueueService")
@Transactional
public class OrderQueueServiceImpl implements OrderQueueService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<OrderQueue, Long> OrderQueueDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.OrderQueueDao = new PagerHibernateTemplate<OrderQueue, Long>(
                sessionfactory, OrderQueue.class);
    }
    
    public OrderQueue findById(Long id)
    {
    	return (OrderQueue) OrderQueueDao.getSession().get(OrderQueue.class, id);
    }
    
    public OrderQueue load(Long id)
    {
    	return  null;
    }

    public OrderQueue save(OrderQueue entity) 
    {
    	OrderQueueDao.save(entity);
    	return entity;
    }

    public OrderQueue update(OrderQueue entity)
    {
    	OrderQueueDao.save(entity);
        return entity;
    }

    public void delete(OrderQueue entity) 
    {
    	OrderQueueDao.delete(entity);
    }
    
    /**遍历出所有的status=0的orderqueue*/
    public List<OrderQueue> getAllOrderQueueListByPlat(int plat)
    {
    	logger.debug("遍历出所有的orderqueue");
    	Criteria criteria = OrderQueueDao.createCriteria();
    	criteria.add(Restrictions.eq("status", 0));
    	criteria.add(Restrictions.eq("sendTicketPlat", plat));
    	List<OrderQueue> allList = criteria.list();
    	return allList;
    }
    
    /**根据order取出相应的orderqueue*/
    public OrderQueue getOrderQueueByOrderId(Long orderId)
    {
    	logger.debug("根据order取出相应的orderqueue");
    	Criteria criteria = OrderQueueDao.createCriteria();
    	criteria.add(Restrictions.eq("orderId", orderId));
    	List<OrderQueue> list = criteria.list();
    	if(list.size() > 0)
    	{
    		return list.get(0);
    	}
    	else
    	{
    		return null;
    	}
    }
    
    /**根据status取出相应的orderqueue*/
    public List<OrderQueue> getOrderQueueByStatus(int status)
    {
    	logger.debug("根据status取出相应的orderqueue");
    	Criteria criteria = OrderQueueDao.createCriteria();
    	criteria.add(Restrictions.eq("status", status));
    	List<OrderQueue> list = criteria.list();
    	return list;
    }
    
    /**删除掉所有状态是1的数据*/
    public void deleteAllStatus1(int plat)
    {
    	logger.debug("删除掉所有状态是1的数据");
    	String sql = "delete from business_order_queue where status = 1 and send_ticket_plat = " + plat;
    	OrderQueueDao.getSession().createSQLQuery(sql).executeUpdate();
    	OrderQueueDao.getSession().flush();
    }
}
