package com.xsc.lottery.service.admin.impl;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.admin.AdminChannel;
import com.xsc.lottery.entity.admin.AdminChannelMode;
import com.xsc.lottery.service.admin.AdminChannelService;

@SuppressWarnings("unused")
@Service
@Transactional
public class AdminChannelServiceImpl implements AdminChannelService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public SimpleHibernateTemplate<AdminChannel, Long> channelDao;
    public SimpleHibernateTemplate<AdminChannelMode, Long> modeDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.channelDao = new PagerHibernateTemplate<AdminChannel, Long>(
                sessionfactory, AdminChannel.class);
        this.modeDao = new PagerHibernateTemplate<AdminChannelMode, Long>(
                sessionfactory, AdminChannelMode.class);
    }

    /** 获得倒序结点 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AdminChannel> getAdminChannelDepthDesc(Long parentId)
    {
        Criteria criteria = channelDao.createCriteria(Restrictions.eq(
                "parentId", parentId));
        criteria.addOrder(Order.desc("id"));
        List<AdminChannel> list = criteria.list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    /** 获得正序结点 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AdminChannel> getAdminChannelDepthAsc(Long parentId)
    {
        Criteria criteria = channelDao.createCriteria(Restrictions.eq(
                "parentId", parentId));
        criteria.addOrder(Order.desc("priority"));
        criteria.addOrder(Order.asc("id"));
        List<AdminChannel> list = criteria.list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public void delete(AdminChannel entity)
    {
    }

    public AdminChannel findById(Long id)
    {
        return (AdminChannel) channelDao.getSession().get(AdminChannel.class, id);
    }

    public AdminChannel load(Long id)
    {
        return channelDao.get(id);
    }

    public AdminChannel save(AdminChannel entity)
    {
        channelDao.save(entity);
        return entity;
    }

    public AdminChannel update(AdminChannel entity)
    {
        channelDao.save(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AdminChannelMode> getModeList(Long chnId)
    {
        Criteria criteria = modeDao.createCriteria(Restrictions.eq("chId", chnId));
        List<AdminChannelMode> list = criteria.list();
        return list;
    }

    public void saveMode(AdminChannelMode mode)
    {
        modeDao.save(mode);
    }

    public void delMode(AdminChannelMode entity)
    {
        modeDao.delete(entity);
    }

    public AdminChannelMode getMode(Long modeId)
    {
        return (AdminChannelMode) modeDao.getSession().get(AdminChannelMode.class, modeId);
    }
}
