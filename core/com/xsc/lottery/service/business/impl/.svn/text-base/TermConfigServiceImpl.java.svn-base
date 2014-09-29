package com.xsc.lottery.service.business.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.TermTypeConfig;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.TermConfigService;

@Transactional
@Service("termConfigService")
public class TermConfigServiceImpl implements TermConfigService
{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PagerHibernateTemplate<TermTypeConfig, Long> termConfigDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.termConfigDao = new PagerHibernateTemplate<TermTypeConfig, Long>(
                sessionfactory, TermTypeConfig.class);
    }

    /**
     * 根据彩种查找配置
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TermTypeConfig getConfigByType(LotteryType type)
    {
        logger.debug("find TermTypeConfig by type");
        return termConfigDao.findUniqueByProperty("type", type);
    }

    public void delete(TermTypeConfig entity)
    {
        logger.debug("delete TermTypeConfig ");
        termConfigDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TermTypeConfig findById(Long id)
    {
        return (TermTypeConfig) termConfigDao.getSession().get(
                TermTypeConfig.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TermTypeConfig load(Long id)
    {
        return termConfigDao.get(id);
    }

    public TermTypeConfig save(TermTypeConfig entity)
    {
        termConfigDao.save(entity);
        return entity;
    }

    public TermTypeConfig update(TermTypeConfig entity)
    {
        termConfigDao.save(entity);
        return entity;
    }
}
