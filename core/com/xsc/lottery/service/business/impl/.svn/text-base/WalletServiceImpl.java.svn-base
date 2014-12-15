package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.CpsDayReport;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.WalletService;
@Service("walletService")
@Transactional
public class WalletServiceImpl implements WalletService{
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

	 private PagerHibernateTemplate<Wallet, Long> walletDao;
	 public SimpleHibernateTemplate<WalletLog, Long> walletLogDao;
	 public PagerHibernateTemplate<WalletLog, Long> walletLogPageDao;
	 @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.walletDao = new PagerHibernateTemplate<Wallet, Long>(
                sessionfactory, Wallet.class);
        this.walletLogDao = new PagerHibernateTemplate<WalletLog, Long>(
                sessionfactory, WalletLog.class);
        this.walletLogPageDao = new PagerHibernateTemplate<WalletLog, Long>(
                sessionfactory, WalletLog.class);
    }
	 
	public void delete(Wallet entity) {
		// TODO Auto-generated method stub
		logger.debug("delete Wallet");
		walletDao.delete(entity);
	}

	public Wallet findById(Long id) {
		 logger.debug("findById Wallet");
		 Wallet wallet = (Wallet) walletDao.getSession().get(Wallet.class, id);
	      return wallet;
	}

	public Wallet load(Long id) {
		logger.debug("load Wallet");
        return walletDao.get(id);
	}

	public Wallet save(Wallet entity) {
		logger.debug("save Wallet");
        this.walletDao.save(entity);
        return entity;
	}

	public Wallet update(Wallet entity) {
		logger.debug("update Wallet");
        save(entity);
        return entity;
	}
	
	/**
	 * 统计余额
	 * @return
	 */
      public BigDecimal getSumBalance(){
    	  Criteria criteria=walletDao.createCriteria();
    	  criteria.setProjection(Projections.projectionList().add(Projections.sum("balance")));
    	  List list= criteria.list();
    	  BigDecimal sumBalance=new BigDecimal(0.00);
    	  Object obj=list.get(0);
    	  if(obj!=null){
    	      sumBalance=(BigDecimal)obj;
    	  }
    	  return sumBalance;
      }
	 
	  /**
	   * 统计被冻结金额
	   * @return
	   */
	 public BigDecimal getSumFreezeMoney(){
		 Criteria criteria=walletDao.createCriteria();
	   	  criteria.setProjection(Projections.projectionList().add(Projections.sum("freezeMoney")));
	   	  List list= criteria.list();
	   	  BigDecimal sumFreezeMoney=new BigDecimal(0.00);
	   	  Object obj=list.get(0);
	   	  if(obj!=null){
	   		sumFreezeMoney=(BigDecimal)obj;
	   	  }
		 return sumFreezeMoney;
	 }

	public Long getRechargeNum(Calendar startTime, Calendar endTime, Customer customer)
	{
		Criteria criteria = walletLogDao.createCriteria();
		 criteria.createAlias("wallet", "wallet");
		 criteria.createAlias("wallet.customer", "customer");
		 
		 if(customer!=null){
			 criteria.add(Restrictions.eq("customer.superior", customer));
		 }
		 
	     criteria.add(Restrictions.or(Restrictions.eq("type", WalletLogType.直接充值), Restrictions.eq("type", WalletLogType.账户充值)));
	     
	     if(startTime != null)
	     {
	        criteria.add(Restrictions.ge("time", startTime));
	     }
	     if(endTime != null)
	     {
	        criteria.add(Restrictions.le("time", endTime));
	     }
	     criteria.setProjection(Projections.rowCount());
	     criteria.setProjection(Projections.groupProperty("customer.id"));
	     int num = criteria.list().size();
		return new Long(num);
	}
	
	public BigDecimal getRechargeMon(Calendar startTime, Calendar endTime, Customer customer){	
		
		Criteria criteria = walletLogDao.createCriteria();
		criteria.createAlias("wallet", "wallet");
		 criteria.createAlias("wallet.customer", "customer");
		 
		 if(customer!=null){
			 criteria.add(Restrictions.eq("customer.superior", customer));
		 }
		 
		 criteria.setProjection(Projections.projectionList().add(Projections.sum("inMoney")));
	     criteria.add(Restrictions.or(Restrictions.eq("type", WalletLogType.直接充值), Restrictions.eq("type", WalletLogType.账户充值)));
	     if(startTime != null)
	     {
	        criteria.add(Restrictions.ge("time", startTime));
	     }
	     if(endTime != null)
	     {
	        criteria.add(Restrictions.le("time", endTime));
	     }
	     Object payNum = criteria.uniqueResult();
	     BigDecimal sumNum=new BigDecimal(0.00);
	     if(payNum!=null&&!"".equals(payNum)){
	    	 sumNum = (BigDecimal)payNum;
	     }
		return sumNum;
	}
	
	/*获取被推荐人的充值情况*/
	public Page<WalletLog> getRechargeDetail(Page<WalletLog> wlPage,Calendar startTime, Calendar endTime, Customer customer){	
		
		Criteria criteria = walletLogPageDao.createCriteria();
		criteria.createAlias("wallet", "wallet");
		 criteria.createAlias("wallet.customer", "customer");
		 criteria.add(Restrictions.eq("customer.superior", customer));
	     criteria.add(Restrictions.or(Restrictions.eq("type", WalletLogType.直接充值), Restrictions.eq("type", WalletLogType.账户充值)));
	     if(startTime != null)
	     {
	        criteria.add(Restrictions.ge("time", startTime));
	     }
	     if(endTime != null)
	     {
	        criteria.add(Restrictions.le("time", endTime));
	     }
	     criteria.addOrder(Order.desc("id"));
	     
	     Page<WalletLog> page = walletLogPageDao.findByCriteria(wlPage, criteria);
	        return page;
	}
}
