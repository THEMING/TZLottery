package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
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
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.ChaseItermStatus;
import com.xsc.lottery.entity.enumerate.ChaseStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.CustomerService;

@SuppressWarnings("unused")
@Service("chaseService")
@Transactional
public class ChaseServiceImpl implements ChaseService
{
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    public SimpleHibernateTemplate<ChaseItem, Long> chaseItemdao;
    public SimpleHibernateTemplate<Chase, Long> chasedao;

    @Autowired
    public CustomerService customerService;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.chaseItemdao = new SimpleHibernateTemplate<ChaseItem, Long>(
                sessionfactory, ChaseItem.class);
        this.chasedao = new SimpleHibernateTemplate<Chase, Long>(
                sessionfactory, Chase.class);
    }

    public void delete(Chase entity)
    {

    }

    public Chase findById(Long id)
    {
        return (Chase) chasedao.getSession().get(Chase.class, id);
    }

    public Chase load(Long id)
    {
        return null;
    }

    public ChaseItem saveChaseItem(ChaseItem entity)
    {
        chaseItemdao.save(entity);
        return entity;
    }

    //
    // public ChaseItem update(ChaseItem entity) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    // TODO 停止追号..1.中奖金额达到一定数额 2.用户钱包没钱
    // 停止追号..1.中奖金额达到一定数额 2.用户钱包没钱
    public void stopChaseItem(Plan plan, LotteryTerm term)
    {
        String hql = "update chase set ";
        Criteria criteria = chaseItemdao.createCriteria(Restrictions.eq("plan",
                plan));
        criteria.add(Restrictions.gt("termNo", term.getTermNo()));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ChaseItem> getChaseItemByCustomer(Customer customer)
    {
        return chaseItemdao.findByProperty("customer", customer);
    }

    /**
     * 根据期号,彩种,追号列表状态,追号总表状态查询
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ChaseItem> getChaseItemByTypeAndTerm(LotteryTerm term,
            ChaseItermStatus status, ChaseStatus chasestatus)
    {
        Criteria criteria = chaseItemdao.createCriteria();
        criteria.add(Restrictions.eq("lotteryType", term.getType()));
        criteria.add(Restrictions.eq("termNo", term.getTermNo()));
        if (status != null)
            criteria.add(Restrictions.eq("status", status));
        if (null != chasestatus)
            criteria.createAlias("chase", "chase").add(
                    Restrictions.eq("chase.status", chasestatus));
        return criteria.list();
    }

    public Chase saveChaseAndIterm(Chase entity)
    {
        if (entity.getItems().get(0).getOrder() != null)
            entity.addOktermNum(1);
        chasedao.save(entity);
        for (ChaseItem iterm : entity.getItems()) {
            iterm.setChase(entity);
            this.saveChaseItem(iterm);
        }
        return entity;
    }

    public Chase save(Chase entity)
    {
        chasedao.save(entity);
        return entity;
    }

    public Chase update(Chase entity)
    {
        chasedao.save(entity);
        return entity;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Chase getChaseByPlan(Plan plan)
    {
        logger.debug("根据方案查追号表（Chase）");
        List<Chase> list = chasedao.findByProperty("plan", plan);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ChaseItem> getChaseItemByChase(Chase chase)
    {
        List<ChaseItem> list = chaseItemdao.findByProperty("chase", chase);
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public ChaseItem getChaseItem(Long itemId)
    {

        return (ChaseItem) chaseItemdao.getSession().get(ChaseItem.class,
                itemId);
    }

    public void stopChse(Chase backChase)
    {
        BigDecimal money = new BigDecimal(0);
        for (ChaseItem item : backChase.getItems()) {
            if (item.getStatus().equals(ChaseItermStatus.待追号)) {
                item.setStatus(ChaseItermStatus.已撤销);
                this.saveChaseItem(item);
                money = money.add(item.getMoney());
            }
        }
        WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                BigDecimal.ZERO, BigDecimal.ZERO, money, backChase.getPlan()
                        .getType().name(), WalletLogType.追号退款, backChase
                        .getPlan().getNumberNo());
        customerService.addWalletLog(backChase.getCustomer().getWallet()
                .getId(), walletLog);
        update(backChase);
    }

    // 根据彩种 获取大于等于次期次的ChaseItem
    @SuppressWarnings("unchecked")
	public List<ChaseItem> getChaseItemByTermNO(String termNo, LotteryType type)
    {
    	Criteria criteria = chaseItemdao.createCriteria();
    	criteria.add(Restrictions.ge("termNo", termNo));
    	criteria.add(Restrictions.eq("lotteryType", type));
        return criteria.list();
    }
    
}
