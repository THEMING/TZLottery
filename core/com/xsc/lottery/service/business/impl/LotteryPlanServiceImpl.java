package com.xsc.lottery.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.service.business.LotteryPlanService;

@Service("lotteryPlanService")
@Transactional
public class LotteryPlanServiceImpl implements LotteryPlanService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PagerHibernateTemplate<Plan, Long> planDao;
    private SimpleHibernateTemplate<PlanItem, Long> planItemDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.planDao = new PagerHibernateTemplate<Plan, Long>(sessionfactory,
                Plan.class);
        this.planItemDao = new SimpleHibernateTemplate<PlanItem, Long>(
                sessionfactory, PlanItem.class);
    }

    public void delete(Plan entity)
    {
        planDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Plan findById(Long id)
    {
        return (Plan) planDao.getSession().get(Plan.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Plan load(Long id)
    {
        return planDao.get(id);
    }

    public Plan save(Plan entity)
    {
        planDao.save(entity);
        return entity;
    }

    public Plan update(Plan entity)
    {
        planDao.save(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Plan getPlanBynumberNo(String numberNo)
    {
        logger.debug("根据方案号查找方案!");
        String hql = "select o from Plan o where numberNo=:numberNo";
        Query query = planDao.getSession().createQuery(hql);
        query.setParameter("numberNo", numberNo);
        List<Plan> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return new Plan();
    }

    /**
     * @param id
     * @描述 根据方案ID查找方案内容
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PlanItem> getPlanItemByPlanID(Long planID)
    {
        logger.debug("find  List<PlanItem> by planid");
        List<PlanItem> result = new ArrayList<PlanItem>();
        result = planItemDao.findByProperty("plan.id", planID);
        return result;
    }
}
