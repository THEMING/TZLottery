package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.alipay.util.MapUtil;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.CustomerCommission;
import com.xsc.lottery.entity.business.NewlyWinPrize;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.enumerate.BackMoneyStatus;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.CustomerType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.CpsReportService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.service.listener.active.ActivityListener;
import com.xsc.lottery.task.message.MessageTaskExcutor;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.MD5;
import com.xsc.lottery.util.SmsUtil;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService
{
    public transient final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PagerHibernateTemplate<Customer, Long> customerDao;
    public SimpleHibernateTemplate<Wallet, Long> walletDao;
    public PagerHibernateTemplate<BackMoneyRequest, Long> backMoneyRequestDao;
    public PagerHibernateTemplate<PaymentRequest, Long> paymentRequestDao;
    public PagerHibernateTemplate<WalletLog, Long> walletLogDao;
    public PagerHibernateTemplate<NewlyWinPrize, Long> newlyWinPrizeDao;
    
    public PagerHibernateTemplate<CustomerCommission, Long> customerCommissionDao;
    @Autowired
    public MessageTaskExcutor messageTaskExcutor;
    @Autowired
    private ActivityListener activityListener;
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        customerDao = new PagerHibernateTemplate<Customer, Long>(
                sessionfactory, Customer.class);
        walletDao = new SimpleHibernateTemplate<Wallet, Long>(sessionfactory,
                Wallet.class);
        walletLogDao = new PagerHibernateTemplate<WalletLog, Long>(
                sessionfactory, WalletLog.class);
        backMoneyRequestDao = new PagerHibernateTemplate<BackMoneyRequest, Long>(
                sessionfactory, BackMoneyRequest.class);
        paymentRequestDao = new PagerHibernateTemplate<PaymentRequest, Long>(
                sessionfactory, PaymentRequest.class);
        newlyWinPrizeDao = new PagerHibernateTemplate<NewlyWinPrize, Long>(
                sessionfactory, NewlyWinPrize.class);
        
        customerCommissionDao = new PagerHibernateTemplate<CustomerCommission, Long>(
                sessionfactory, CustomerCommission.class);

    }

    @Autowired
    public LotteryOrderService lotteryOrderService;
    
    @Autowired
    public SmsLogService smsLogService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<WalletLog> findWalletLogPagerBy(Page<WalletLog> page,
            BusinessType businessType, Calendar startTime, Calendar endTime,
            Wallet wallet)
    {
        List<Criterion> list = new ArrayList<Criterion>();
        if (businessType != null)
            list.add(Restrictions.eq("businessType", businessType));
        if (wallet != null)
            list.add(Restrictions.eq("wallet", wallet));
        if (startTime != null)
            list.add(Restrictions.ge("time", startTime));
        if (endTime != null)
            list.add(Restrictions.lt("time", DateUtil
                    .getTheDayMidnight(endTime)));
        Criterion[] c = new Criterion[list.size()];
        list.toArray(c);
        return walletLogDao.findByCriteria(page, c);
    }

    /** 增加用户的钱包流水事务 */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addWalletLog(Long walletId, WalletLog walletLog)
    {
        synchronized (String.valueOf(walletLog.hashCode())) {
            Wallet wallet = (Wallet) walletDao.getSession().get(Wallet.class,
                    walletId, LockMode.UPGRADE);
            Customer customer = (Customer) customerDao.findUniqueByProperty(
                    "wallet", wallet);
            if (!checkWalletSummary(wallet)) {
                String description = customer.getNickName()
                        + "的用户钱包数据出现问题,签名发现不对称,请检查其帐户";
                logger.error(description);
                SystemWarningNotify.addWarningDescription(description);
                throw new HibernateException(description);
            }
            wallet.setBalance(wallet.getBalance().add(walletLog.getInMoney()));
            wallet.setBalance(wallet.getBalance().subtract(
                    walletLog.getOutMoney()));
            wallet.setFreezeMoney(wallet.getFreezeMoney().add(
                    walletLog.getInFreezeMoney()));
            wallet.setFreezeMoney(wallet.getFreezeMoney().subtract(
                    walletLog.getOutFreezeMoney()));
            wallet.setSummary(countWalletSummary(wallet.getId(), wallet
                    .getBalance(), wallet.getFreezeMoney()));
            walletLog.setCurrentMoney(wallet.getBalance());
            walletLog.setCurrentFreezeMoney(wallet.getFreezeMoney());
            walletLog.setWallet(wallet);
            if (wallet.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                throw new HibernateException("用户余额不足");
            }
            if (wallet.getFreezeMoney().compareTo(BigDecimal.ZERO) < 0) {
                throw new HibernateException("用户冻结余额不足");
            }
            walletDao.save(wallet);
            walletLogDao.save(walletLog);
            if (walletLog.getType().equals(WalletLogType.账户返奖)) {// 更新用户累计中奖金额
                customer.setAllWinMoney(customer.getAllWinMoney().add(
                        walletLog.getInMoney()));
            }
        }
    }

    /** 增加用户的钱包流水事务 */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addWalletLog(Long walletId1, Long walletId2, WalletLog walletLog1, WalletLog walletLog2)
    {
        synchronized (String.valueOf(walletLog1.hashCode())) {
            Wallet wallet1 = (Wallet) walletDao.getSession().get(Wallet.class,
                    walletId1, LockMode.UPGRADE);
            Wallet wallet2 = (Wallet) walletDao.getSession().get(Wallet.class,
                    walletId2, LockMode.UPGRADE);
            Customer customer1 = (Customer) customerDao.findUniqueByProperty(
                    "wallet", wallet1);
            Customer customer2 = (Customer) customerDao.findUniqueByProperty(
                    "wallet", wallet2);
            if (!checkWalletSummary(wallet1)) {
                String description = customer1.getNickName()
                        + "的用户钱包数据出现问题,签名发现不对称,请检查其帐户";
                logger.error(description);
                SystemWarningNotify.addWarningDescription(description);
                throw new HibernateException(description);
            }
            if (!checkWalletSummary(wallet2)) {
                String description = customer2.getNickName()
                        + "的用户钱包数据出现问题,签名发现不对称,请检查其帐户";
                logger.error(description);
                SystemWarningNotify.addWarningDescription(description);
                throw new HibernateException(description);
            }
            wallet1.setBalance(wallet1.getBalance().add(walletLog1.getInMoney()));
            wallet1.setBalance(wallet1.getBalance().subtract(
                    walletLog1.getOutMoney()));
            wallet1.setFreezeMoney(wallet1.getFreezeMoney().add(
                    walletLog1.getInFreezeMoney()));
            wallet1.setFreezeMoney(wallet1.getFreezeMoney().subtract(
                    walletLog1.getOutFreezeMoney()));
            wallet1.setSummary(countWalletSummary(wallet1.getId(), wallet1
                    .getBalance(), wallet1.getFreezeMoney()));
            
            wallet2.setBalance(wallet2.getBalance().add(walletLog2.getInMoney()));
            wallet2.setBalance(wallet2.getBalance().subtract(
                    walletLog2.getOutMoney()));
            wallet2.setFreezeMoney(wallet2.getFreezeMoney().add(
                    walletLog2.getInFreezeMoney()));
            wallet2.setFreezeMoney(wallet2.getFreezeMoney().subtract(
                    walletLog2.getOutFreezeMoney()));
            wallet2.setSummary(countWalletSummary(wallet2.getId(), wallet2
                    .getBalance(), wallet2.getFreezeMoney()));
            
            walletLog1.setCurrentMoney(wallet1.getBalance());
            walletLog1.setCurrentFreezeMoney(wallet1.getFreezeMoney());
            walletLog1.setWallet(wallet1);
            
            walletLog2.setCurrentMoney(wallet2.getBalance());
            walletLog2.setCurrentFreezeMoney(wallet2.getFreezeMoney());
            walletLog2.setWallet(wallet2);
            
            if (wallet1.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                throw new HibernateException("用户余额不足");
            }
            if (wallet1.getFreezeMoney().compareTo(BigDecimal.ZERO) < 0) {
                throw new HibernateException("用户冻结余额不足");
            }
            walletDao.save(wallet1);
            walletDao.save(wallet2);
            walletLogDao.save(walletLog1);
            walletLogDao.save(walletLog2);
            if (walletLog1.getType().equals(WalletLogType.账户返奖)) {// 更新用户累计中奖金额
                customer1.setAllWinMoney(customer1.getAllWinMoney().add(
                        walletLog1.getInMoney()));
            }
        }
    }

    
    /**
     * @see 手动补单
     */
    public void addHandMoney(PaymentRequest payment, WalletLog walletLog)
    {
        paymentRequestDao.save(payment);
        this.addWalletLog(payment.getCustomer().getWallet().getId(), walletLog);
    }

    public void delete(Customer entity)
    {
        customerDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Customer findById(Long id)
    {
        return (Customer) customerDao.getSession().get(Customer.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Customer load(Long id)
    {
        return customerDao.get(id);
    }

    public Customer save(Customer entity)
    {
        customerDao.save(entity);
        return entity;
    }

    public void saveWalletSummary(Wallet wallet)
    {
        String summary = MD5.digest("" + wallet.getId()
                + wallet.getBalance().longValue()
                + wallet.getFreezeMoney().longValue());
        wallet.setSummary(summary);
        walletDao.save(wallet);
    }

    public Customer update(Customer entity)
    {
        customerDao.save(entity);
        return entity;
    }
    
    public CustomerCommission saveCommssion(CustomerCommission entity)
    {
    	customerCommissionDao.save(entity);
    	return entity;
    }
    /**
     * 检查用户钱包签名 合法返回true 非法返回false
     * */
    public boolean checkWalletSummary(Wallet wallet)
    {
        String summary = countWalletSummary(wallet.getId(),
                wallet.getBalance(), wallet.getFreezeMoney());

        if(summary.equals(wallet.getSummary())) {
            return true;
        }
        
        return false;
    }

    /**
     * 提款请求
     * */
    public void saveBackMoneyRequest(BackMoneyRequest entity)
    {
        WalletLog walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO,
                entity.getMoney(), entity.getMoney(), BigDecimal.ZERO,
                "个人账户提款", WalletLogType.提款冻结, "");
        addWalletLog(entity.getCustomer().getWallet().getId(), walletLog);
        if (entity.getFeeMoney().intValue() > 0) {
            WalletLog walletLog1 = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, entity.getFeeMoney(),
                    entity.getFeeMoney(), BigDecimal.ZERO, "个人提款手续费",
                    WalletLogType.手续费冻结, "");
            addWalletLog(entity.getCustomer().getWallet().getId(), walletLog1);
        }
        backMoneyRequestDao.save(entity);
    }

    /**
     * @see 保存充值请求
     * **/
    public void savePaymentRequest(PaymentRequest entity)
    {
        paymentRequestDao.save(entity);
    }

    /** 计算钱包签名 */
    public String countWalletSummary(Long walletID, BigDecimal balance,
            BigDecimal freezeMoney)
    {
        return MD5.digest("" + walletID + balance.longValue()
                + freezeMoney.longValue());
    }

    public Wallet getWallet(Long Id)
    {
        return (Wallet) walletDao.getSession().get(Wallet.class, Id);
    }
    
    public static void main(String[] args)
	{
    	String[] contextPaths = new String[] { "applicationContext.xml" };
		ApplicationContext springContext = new ClassPathXmlApplicationContext(contextPaths);
		PagerHibernateTemplate<Customer, Long> customerDao = (PagerHibernateTemplate<Customer, Long>) springContext.getBean("customerDao");
		StringBuffer hql = new StringBuffer("update business_customer bc set bc.adminUser_id=:adminUser_id where 1=1 and bc.id in (:customerIds)");
		SQLQuery query = customerDao.getSession().createSQLQuery(hql.toString());
		
		query.setParameter("adminUser_id", 11);
		query.setParameter("customerIds", 11);
		
		int i = query.executeUpdate();
	}
    
    public void updateCustomers(Map map){
    	StringBuffer hql = new StringBuffer("update business_customer set admin_user_id=:adminUser_id where 1=1");
    	if (MapUtil.checkKey(map, "customerIds")) {
        	hql.append(" and id in(:customerIds)");
        }    	
    	
    	if (MapUtil.checkKey(map, "f_serch")) {
            if (MapUtils.getString(map, "f_serch").equals("用户名")) {
            	hql.append(" and nick_name like :f_serchName");
            }
            if (MapUtils.getString(map, "f_serch").equals("真实姓名")) {
            	hql.append(" and real_name like :f_serchName");
            }
            if (MapUtils.getString(map, "f_serch").equals("邮箱")) {
            	hql.append(" and email like :f_serchName");
            }
        }
        if (MapUtil.checkKey(map, "f_starTime")) {
        	hql.append(" and register_time >=:f_starTime");
        }
        if (MapUtil.checkKey(map, "f_endTime")) {
        	hql.append(" and register_time <=:f_endTime");
        }
        if (MapUtil.checkKey(map, "f_orderserch")
                && (MapUtils.getString(map, "f_orderserch").equals("购彩用户") || MapUtils.getString(map, "f_orderserch").equals("未购彩用户"))) {
            if (MapUtils.getString(map, "f_orderserch").equals("购彩用户")) {
                if (MapUtil.checkKey(map, "stratTime")|| MapUtil.checkKey(map, "endTime")) {
                    List<Long> list = lotteryOrderService.getCustomerId(
                    		(Calendar)MapUtils.getObject(map,"stratTime"), (Calendar)MapUtils.getObject(map,"endTime"));
                    hql.append(" and id in (:idList)");
                }
            }
            if (MapUtils.getString(map, "f_orderserch").equals("未购彩用户")) {
                if (MapUtil.checkKey(map, "stratTime")|| MapUtil.checkKey(map, "endTime")) {
                    List<Long> list = lotteryOrderService.getCustomerId(
                    		(Calendar)MapUtils.getObject(map,"stratTime"), (Calendar)MapUtils.getObject(map,"endTime"));
                    if (list != null && list.size() > 0){
                    	hql.append(" and id not in (:idList)");
                    }

                }
            }
        }
        
        if(MapUtil.checkKey(map, "usrType")) {
        	hql.append(" and usr_type =:usrType");
        }
        if(MapUtil.checkKey(map, "isApply"))
        {
        	hql.append(" and is_apply =:isApply");
        }
        if(MapUtil.checkKey(map, "isPass"))
        {
        	hql.append(" and is_pass =:isPass");
        }
        
        
        
        
        if(MapUtil.checkKey(map, "sBalance")||MapUtil.checkKey(map, "eBalance")){
        	hql.append(" and wallet_id in (select id from business_wallet w where 1=1 ");
        	
        	
        	if(MapUtil.checkKey(map, "sBalance")){
        	
        		hql.append(" and w.balance>=:sBalance");
               }
               
            if(MapUtil.checkKey(map, "eBalance")){
            	
            	hql.append(" and w.balance<=:eBalance");
               }
            
            hql.append(")");
        }
        
        if(MapUtil.checkKey(map, "havenotDispath")){
        	hql.append(" and admin_user_id is null");
        }
        
        
        SQLQuery query = customerDao.getSession().createSQLQuery(hql.toString());//customerDao.getSession().createSQLQuery(hql.toString());
    	
        
        //================================sql与变量的分割========================================
        
        query.setParameter("adminUser_id", MapUtils.getLong(map,"adminUser_id"));
        
        if (MapUtil.checkKey(map, "customerIds")) {
//        	query.setString("customerIds", MapUtils.getString(map,"customerIds"));
        	query.setParameterList("customerIds", (List)MapUtils.getObject(map,"customerIds"));
        }    	
    	
    	if (MapUtil.checkKey(map, "f_serch")) {
            if (MapUtils.getString(map, "f_serch").equals("用户名")) {
            	query.setString("f_serchName", MapUtils.getString(map,"f_serchName")+"%");
            }
            if (MapUtils.getString(map, "f_serch").equals("真实姓名")) {
            	query.setString("f_serchName", MapUtils.getString(map,"f_serchName")+"%");
            }
            if (MapUtils.getString(map, "f_serch").equals("邮箱")) {
            	query.setString("f_serchName", MapUtils.getString(map,"f_serchName")+"%");
            }
        }
        if (MapUtil.checkKey(map, "f_starTime")) {
        	query.setCalendar("f_starTime", (Calendar)MapUtils.getObject(map,"f_starTime"));
        }
        if (MapUtil.checkKey(map, "f_endTime")) {
        	query.setCalendar("f_endTime", (Calendar)MapUtils.getObject(map,"f_endTime"));
        }
        if (MapUtil.checkKey(map, "f_orderserch")
                && (MapUtils.getString(map, "f_orderserch").equals("购彩用户") || MapUtils.getString(map, "f_orderserch").equals("未购彩用户"))) {
            if (MapUtils.getString(map, "f_orderserch").equals("购彩用户")) {
                if (MapUtil.checkKey(map, "stratTime")|| MapUtil.checkKey(map, "endTime")) {
                    List<Long> list = lotteryOrderService.getCustomerId(
                    		(Calendar)MapUtils.getObject(map,"stratTime"), (Calendar)MapUtils.getObject(map,"endTime"));
                    query.setParameterList("idList", list);
                }
            }
            if (MapUtils.getString(map, "f_orderserch").equals("未购彩用户")) {
                if (MapUtil.checkKey(map, "stratTime")|| MapUtil.checkKey(map, "endTime")) {
                    List<Long> list = lotteryOrderService.getCustomerId(
                    		(Calendar)MapUtils.getObject(map,"stratTime"), (Calendar)MapUtils.getObject(map,"endTime"));
                    if (list != null && list.size() > 0){
                        query.setParameterList("idList", list);
                    }

                }
            }
        }
        
        if(MapUtil.checkKey(map, "usrType")) {
        	query.setParameter("usrType",MapUtils.getObject(map,"usrType"));
        }
        if(MapUtil.checkKey(map, "isApply"))
        {
        	query.setBoolean("isApply",MapUtils.getBooleanValue(map,"isApply"));
        }
        if(MapUtil.checkKey(map, "isPass"))
        {
        	query.setBoolean("isPass",MapUtils.getBooleanValue(map,"isPass"));
        }
        
        
        
        
        if(MapUtil.checkKey(map, "sBalance")||MapUtil.checkKey(map, "eBalance")){
        	
        	
        	if(MapUtil.checkKey(map, "sBalance")){
        	
        		query.setBigDecimal("sBalance",(BigDecimal)MapUtils.getObject(map, "sBalance"));
               }
               
            if(MapUtil.checkKey(map, "eBalance")){
            	
        		query.setBigDecimal("eBalance",(BigDecimal)MapUtils.getObject(map, "eBalance"));
               }
            
        }
        
        int i = query.executeUpdate();
        
        
    	
//    	String hql1 = "SELECT sum(o.money) FROM business_payment_request o where o.customer_id=:customerId and o.finish=1";
//        SQLQuery query = paymentRequestDao.getSession().createSQLQuery(hql);
//        query.setParameter("customerId", customer.getId());
//        if (query.list().get(0) != null) {
//            BigDecimal sum = new BigDecimal(query.list().get(0) + "");
//            return sum;
//        } 
//        else {
//            return new BigDecimal(0);
//        }
    }

    /** 分页获得客户信息 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Customer> getLotteryCustomerPage(Page<Customer> page,
            Calendar stratTime, Calendar endTime, String f_orderserch,
            String f_serch, Calendar f_starTime, Calendar f_endTime,
            String f_serchName, UserType type,Boolean isApply,Integer isPass,Map queryMap)
    {
        logger.debug("根据查询条件获得分页期次列表");
        Criteria criteria = customerDao.createCriteria();
        if (f_serch != null) {
            if (f_serch.equals("用户名")) {
                criteria.add(Restrictions.like("nickName", f_serchName + "%"));
            }
            if (f_serch.equals("真实姓名")) {
                criteria.add(Restrictions.like("realName", f_serchName + "%"));
            }
            if (f_serch.equals("邮箱")) {
                criteria.add(Restrictions.eq("email", f_serchName));
            }
        }
        if (f_starTime != null) {
            criteria.add(Restrictions.ge("registerTime", f_starTime));
        }
        if (f_endTime != null) {
            criteria.add(Restrictions.le("registerTime", f_endTime));
        }
        if (f_orderserch != null
                && (f_orderserch.equals("购彩用户") || f_orderserch.equals("未购彩用户"))) {
            if (f_orderserch.equals("购彩用户")) {
                if (stratTime != null || endTime != null) {
                    List<Long> list = lotteryOrderService.getCustomerId(
                            stratTime, endTime);
                    criteria.add(Restrictions.in("id", list.toArray()));
                }
            }
            if (f_orderserch.equals("未购彩用户")) {
                if (stratTime != null || endTime != null) {
                    List<Long> list = lotteryOrderService.getCustomerId(
                            stratTime, endTime);
                    if (list != null && list.size() > 0)
                        criteria.add(Restrictions.not(Restrictions.in("id",
                                list.toArray())));

                }
            }
        }
        
        if(type != null) {
        	criteria.add(Restrictions.eq("usrType", type));
        }
        if(isApply != null)
        {
        	criteria.add(Restrictions.eq("isApply", isApply));
        }
        if(isPass != null)
        {
        	criteria.add(Restrictions.eq("isPass", isPass));
        }
        
        if(MapUtil.checkKey(queryMap, "adminUser")){
        	criteria.add(Restrictions.eq("adminUser", (AdminUser)MapUtils.getObject(queryMap, "adminUser")));
        }
        
        if(MapUtil.checkKey(queryMap, "sBalance")||MapUtil.checkKey(queryMap, "eBalance")){
        	criteria.createAlias("wallet", "wallet");
        	
        	if(MapUtil.checkKey(queryMap, "sBalance")){
               	criteria.add(Restrictions.ge("wallet.balance", (BigDecimal)MapUtils.getObject(queryMap, "sBalance")));
               }
               
            if(MapUtil.checkKey(queryMap, "eBalance")){
               	criteria.add(Restrictions.le("wallet.balance", (BigDecimal)MapUtils.getObject(queryMap, "eBalance")));
               }
        }
        
        if(MapUtil.checkKey(queryMap, "havenotDispath")){
        	criteria.add(Restrictions.isNull("adminUser"));
        }
        
        
        criteria.addOrder(Order.desc("id"));
        page = customerDao.findByCriteria(page, criteria);
        return page;
    }

    /** 分页获得客户流水金额信息 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<WalletLog> getWalletLogPage(Page<WalletLog> page, int inORout,
            Calendar beginTime, Calendar endTime, Wallet wallet)
    {
        logger.debug("分页获得客户流水金额信息");
        Criteria criteria = walletLogDao.createCriteria();
        criteria.add(Restrictions.eq("wallet", wallet));
        if (inORout == 1) {
            criteria.add(Restrictions.gt("inMoney", new BigDecimal("0")));
        }
        if (inORout == 2) {
            criteria.add(Restrictions.gt("inMoney", new BigDecimal("0")));
        }
        if (beginTime != null) {
            criteria.add(Restrictions.ge("buildTime", beginTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        criteria.addOrder(Order.desc("id"));
        page = walletLogDao.findByCriteria(page, criteria);
        return page;
    }

    /** 获得客户流水金额信息 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<WalletLog> getWalletLogByCustomer(Wallet wallet)
    {
        logger.debug("获得客户流水金额信息");
        Criteria criteria = walletLogDao.createCriteria();
        criteria.add(Restrictions.eq("wallet", wallet));
        criteria.addOrder(Order.desc("id"));
        List<WalletLog> list = criteria.list();
        if (list == null) {
            return new ArrayList<WalletLog>();
        }
        return list;
    }

    /** 根据用户名得到客户数据 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Customer getCustomerOrName(String name)
    {
        List<Customer> list = (List<Customer>) customerDao.find("from Customer where nickName=?", new Object[] { name });
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    /** 根据属性得到客户对象 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Customer> getCustomerByProperty(String name, Object value)
    {
        return customerDao.findByProperty(name, value);
    }
    
    /** 根据用户类型查询用户 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Customer> getCustomerByCustomerType(CustomerType type)
    {
    	return customerDao.createCriteria().add(Restrictions.eq("customerType", type)).list();
    }

    /** 根据唯一属性得到客户对象 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Customer getUniqueCustomerByProperty(String name, Object value)
    {
        return customerDao.findUniqueByProperty(name, value);
    }

    /**
     * 查询最近num个月的数据 num 以月为单位
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<BackMoneyRequest> getBackMoneyRequestByRecently(
            Customer customer, int num)
    {
        // String
        // hql="select * from moneyRequest as mr where DATEDIFF(Day,mr.applyTime,getdate())<= 30 and DATEDIFF(Day,mr.applyTime,getdate())>= 0";
        // return (List<BackMoneyRequest>)
        // this.backMoneyRequestDao.getSession().createSQLQuery(hql).list();
        Calendar now = DateUtil.now();
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) - num);
        Criteria criteria = this.backMoneyRequestDao.createCriteria();
        criteria.add(Restrictions.eq("customer", customer));
        criteria.add(Restrictions.ge("applyTime", now));
        return criteria.list();
    }

    /*
     * 根据查询条件分页获得客户充值流水信息
     * 
     * @see
     * com.xsc.lottery.service.business.CustomerService#getPaymentRequestPage
     * (org.springside.modules.orm.hibernate.Page, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<PaymentRequest> getPaymentRequestPage(
            Page<PaymentRequest> page, String f_moneyChannel, String f_name,
            String f_numNo, Calendar f_starTime, Calendar f_endTime,
            String f_statu, String f_user, String fUserTypes, Calendar fSTime,
            Calendar fETime)
    {
        logger.debug("根据查询条件分页获得客户充值流水信息");
        Criteria criteria = paymentRequestDao.createCriteria();
        if (!StringUtils.isEmpty(f_moneyChannel)) {
            criteria.add(Restrictions.eq("channel", MoneyChannel
                    .valueOf(f_moneyChannel)));
        }
        criteria.createAlias("customer", "customer");
        if (!StringUtils.isEmpty(f_name)) {
            criteria.add(Restrictions.eq("customer.nickName", f_name));
        }
        if (!StringUtils.isEmpty(fUserTypes)) {
            criteria.add(Restrictions.eq("customer.usrType", UserType
                    .valueOf(fUserTypes)));
        }

        if (fSTime != null) {
            criteria.add(Restrictions.ge("customer.registerTime", fSTime));
        }
        if (fETime != null) {
            criteria.add(Restrictions.le("customer.registerTime", fETime));
        }

        if (!StringUtils.isEmpty(f_numNo)) {
            criteria.add(Restrictions.eq("serialNumber", f_numNo));
        }
        if (f_starTime != null) {
            criteria.add(Restrictions.ge("payTime", f_starTime));
        }
        if (f_endTime != null) {
            criteria.add(Restrictions.le("payTime", f_endTime));
        }
        if (!StringUtils.isEmpty(f_statu)) {
            criteria.add(Restrictions.eq("finish", Boolean
                    .parseBoolean(f_statu)));
        }
        if (!StringUtils.isEmpty(f_user)) {
            criteria.createAlias("user", "user");
            criteria.add(Restrictions.eq("user.adminName", f_user));
        }

        criteria.addOrder(Order.desc("id"));
        page = paymentRequestDao.findByCriteria(page, criteria);
        return page;
    }
    
    /*
     * 根据查询条件获得客户充值流水信息总和
     * 
     * @see
     * com.xsc.lottery.service.business.CustomerService#getPaymentRequestPage
     * (org.springside.modules.orm.hibernate.Page, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BigDecimal getPaymentRequestSum(
             String f_moneyChannel, String f_name,
            String f_numNo, Calendar f_starTime, Calendar f_endTime,
            String f_statu, String f_user, String fUserTypes, Calendar fSTime,
            Calendar fETime)
    {
        logger.debug("根据查询条件获得客户充值流水信息总和");
        Criteria criteria = paymentRequestDao.createCriteria();
        if (!StringUtils.isEmpty(f_moneyChannel)) {
            criteria.add(Restrictions.eq("channel", MoneyChannel
                    .valueOf(f_moneyChannel)));
        }
        criteria.createAlias("customer", "customer");
        if (!StringUtils.isEmpty(f_name)) {
            criteria.add(Restrictions.eq("customer.nickName", f_name));
        }
        if (!StringUtils.isEmpty(fUserTypes)) {
            criteria.add(Restrictions.eq("customer.usrType", UserType
                    .valueOf(fUserTypes)));
        }

        if (fSTime != null) {
            criteria.add(Restrictions.ge("customer.registerTime", fSTime));
        }
        if (fETime != null) {
            criteria.add(Restrictions.le("customer.registerTime", fETime));
        }

        if (!StringUtils.isEmpty(f_numNo)) {
            criteria.add(Restrictions.eq("serialNumber", f_numNo));
        }
        if (f_starTime != null) {
            criteria.add(Restrictions.ge("payTime", f_starTime));
        }
        if (f_endTime != null) {
            criteria.add(Restrictions.le("payTime", f_endTime));
        }
        if (!StringUtils.isEmpty(f_statu)) {
            criteria.add(Restrictions.eq("finish", Boolean
                    .parseBoolean(f_statu)));
        }
        if (!StringUtils.isEmpty(f_user)) {
            criteria.createAlias("user", "user");
            criteria.add(Restrictions.eq("user.adminName", f_user));
        }
        Object o = criteria.setProjection(Projections.sum("money")).uniqueResult();
        BigDecimal oo = new BigDecimal(0);
        if(o!=null){
        	oo = (BigDecimal) o;
        }
        return oo;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<BackMoneyRequest> getBackMoneyRequestPage(
            Page<BackMoneyRequest> page, String fStatu, String fNikname,
            String fRaelname, String fTimeName, Calendar fSTime,
            Calendar fETime, String fBank, String fBankCard, String fOpenSpace)
    {
        logger.debug("根据查询条件分页获得客户提款流水信息");
        Criteria criteria = backMoneyRequestDao.createCriteria();

        if (!StringUtils.isEmpty(fStatu)) {
            criteria.add(Restrictions.eq("status", BackMoneyStatus
                    .valueOf(fStatu)));
        }
        if (!StringUtils.isEmpty(fNikname)) {
            criteria.createAlias("customer", "customer");
            criteria.add(Restrictions.eq("customer.nickName", fNikname));
        }
        if (!StringUtils.isEmpty(fRaelname)) {
            criteria.createAlias("customer", "customer");
            criteria.add(Restrictions.or(Restrictions.eq("customer.realName",
                    fRaelname), Restrictions.eq("realName", fRaelname)));
        }
        if (fTimeName != null) {
            if (fTimeName.equals("申请时间")) {
                if (fSTime != null) {
                    criteria.add(Restrictions.ge("applyTime", fSTime));
                }
                if (fETime != null) {
                    criteria.add(Restrictions.le("applyTime", fETime));
                }
            }
            if (fTimeName.equals("返款时间")) {
                if (fSTime != null) {
                    criteria.add(Restrictions.ge("sendTime", fSTime));
                }
                if (fETime != null) {
                    criteria.add(Restrictions.le("sendTime", fETime));
                }
            }
        }

        if (!StringUtils.isEmpty(fBank)) {
            criteria.add(Restrictions.eq("bank", Bank.valueOf(fBank)));
        }

        if (!StringUtils.isEmpty(fBankCard)) {
            criteria.add(Restrictions.eq("bankCard", fBankCard));
        }

        if (!StringUtils.isEmpty(fOpenSpace)) {
            criteria.add(Restrictions.eq("openSpace", fOpenSpace));
        }

        criteria.addOrder(Order.desc("id"));
        page = backMoneyRequestDao.findByCriteria(page, criteria);
        return page;
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BigDecimal getBackMoneyRequestSum(
             String fStatu, String fNikname,
            String fRaelname, String fTimeName, Calendar fSTime,
            Calendar fETime, String fBank, String fBankCard, String fOpenSpace)
    {
        logger.debug("根据查询条件获得客户提款流水信息总和");
        Criteria criteria = backMoneyRequestDao.createCriteria();

        if (!StringUtils.isEmpty(fStatu)) {
            criteria.add(Restrictions.eq("status", BackMoneyStatus
                    .valueOf(fStatu)));
        }
        if (!StringUtils.isEmpty(fNikname)) {
            criteria.createAlias("customer", "customer");
            criteria.add(Restrictions.eq("customer.nickName", fNikname));
        }
        if (!StringUtils.isEmpty(fRaelname)) {
            criteria.createAlias("customer", "customer");
            criteria.add(Restrictions.or(Restrictions.eq("customer.realName",
                    fRaelname), Restrictions.eq("realName", fRaelname)));
        }
        if (fTimeName != null) {
            if (fTimeName.equals("申请时间")) {
                if (fSTime != null) {
                    criteria.add(Restrictions.ge("applyTime", fSTime));
                }
                if (fETime != null) {
                    criteria.add(Restrictions.le("applyTime", fETime));
                }
            }
            if (fTimeName.equals("返款时间")) {
                if (fSTime != null) {
                    criteria.add(Restrictions.ge("sendTime", fSTime));
                }
                if (fETime != null) {
                    criteria.add(Restrictions.le("sendTime", fETime));
                }
            }
        }

        if (!StringUtils.isEmpty(fBank)) {
            criteria.add(Restrictions.eq("bank", Bank.valueOf(fBank)));
        }

        if (!StringUtils.isEmpty(fBankCard)) {
            criteria.add(Restrictions.eq("bankCard", fBankCard));
        }

        if (!StringUtils.isEmpty(fOpenSpace)) {
            criteria.add(Restrictions.eq("openSpace", fOpenSpace));
        }
        Object o = criteria.setProjection(Projections.sum("money")).uniqueResult();
        BigDecimal oo = new BigDecimal(0);
        if(o!=null){
        	oo = (BigDecimal)o;
        }
        
        return oo;
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<BackMoneyRequest> getBackMoneyRequestList(
    		String fStatu, String fNikname,
            String fRaelname, String fTimeName, Calendar fSTime,
            Calendar fETime, String fBank, String fBankCard, String fOpenSpace)
    {
        logger.debug("根据查询条件分页获得客户提款流水信息");
        Criteria criteria = backMoneyRequestDao.createCriteria();

        if (!StringUtils.isEmpty(fStatu)) {
            criteria.add(Restrictions.eq("status", BackMoneyStatus
                    .valueOf(fStatu)));
        }
        if (!StringUtils.isEmpty(fNikname)) {
            criteria.createAlias("customer", "customer");
            criteria.add(Restrictions.eq("customer.nickName", fNikname));
        }
        if (!StringUtils.isEmpty(fRaelname)) {
            criteria.createAlias("customer", "customer");
            criteria.add(Restrictions.or(Restrictions.eq("customer.realName",
                    fRaelname), Restrictions.eq("realName", fRaelname)));
        }
        if (fTimeName != null) {
            if (fTimeName.equals("申请时间")) {
                if (fSTime != null) {
                    criteria.add(Restrictions.ge("applyTime", fSTime));
                }
                if (fETime != null) {
                    criteria.add(Restrictions.le("applyTime", fETime));
                }
            }
            if (fTimeName.equals("返款时间")) {
                if (fSTime != null) {
                    criteria.add(Restrictions.ge("sendTime", fSTime));
                }
                if (fETime != null) {
                    criteria.add(Restrictions.le("sendTime", fETime));
                }
            }
        }

        if (!StringUtils.isEmpty(fBank)) {
            criteria.add(Restrictions.eq("bank", Bank.valueOf(fBank)));
        }

        if (!StringUtils.isEmpty(fBankCard)) {
            criteria.add(Restrictions.eq("bankCard", fBankCard));
        }

        if (!StringUtils.isEmpty(fOpenSpace)) {
            criteria.add(Restrictions.eq("openSpace", fOpenSpace));
        }

        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    /**
     * @see 获取最新中奖排行榜
     * @param maxRet
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<NewlyWinPrize> getWinList(int maxRet)
    {
        Criteria criteria = newlyWinPrizeDao.createCriteria();
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(maxRet);
        return criteria.list();
    }

    /**
     * @see 获取历史最高中奖排行榜
     * @param maxRet
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Customer> getHistoryWinList(int maxRet)
    {
        Criteria criteria = customerDao.createCriteria();
        criteria.addOrder(Order.desc("allWinMoney"));
        criteria.setMaxResults(maxRet);
        return criteria.list();
    }

    /*** 保存最新开奖 **/
    public NewlyWinPrize saveNewlyWinPrize(NewlyWinPrize entity)
    {
        this.newlyWinPrizeDao.save(entity);
        return entity;

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BackMoneyRequest findBackMoneyRequest(Long Id)
    {
        return (BackMoneyRequest) backMoneyRequestDao.getSession().get(
                BackMoneyRequest.class, Id);
    }

    public void updateBackMoneyRequest(BackMoneyRequest entity)
    {
        backMoneyRequestDao.save(entity);
    }

    public void updateBackMoneyRequestBackMoney(BackMoneyRequest entity)
    {
        WalletLog walletLog = new WalletLog(BusinessType.收入, entity.getMoney(),
                BigDecimal.ZERO, BigDecimal.ZERO, entity.getMoney(),
                "解冻提款失败返款", WalletLogType.提款失败返款, "");
        addWalletLog(entity.getCustomer().getWallet().getId(), walletLog);
/*        if (entity.getFeeMoney().intValue() > 0) {
            WalletLog walletLog1 = new WalletLog(BusinessType.收入, entity
                    .getFeeMoney(), BigDecimal.ZERO, BigDecimal.ZERO, entity
                    .getFeeMoney(), "个人提款手续费返款", WalletLogType.提款失败返款, "");
            addWalletLog(entity.getCustomer().getWallet().getId(), walletLog1);
        }
        */
        backMoneyRequestDao.save(entity);
    }

    public void updateBackMoneyRequestMoney(BackMoneyRequest entity)
    {
        WalletLog walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, entity.getMoney(), "解冻提款扣钱",
                WalletLogType.提款扣款, "");
        addWalletLog(entity.getCustomer().getWallet().getId(), walletLog);
/*        if (entity.getFeeMoney().intValue() > 0) {
            WalletLog walletLog1 = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, entity
                            .getFeeMoney(), "个人提款手续费扣款", WalletLogType.手续费扣款,
                    "");
            addWalletLog(entity.getCustomer().getWallet().getId(), walletLog1);
        }*/
        backMoneyRequestDao.save(entity);
    }

    public void updateWallet(Wallet wallet)
    {
        walletDao.save(wallet);

    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PaymentRequest> getPaymentRequestList(Customer customer,
            Calendar fStime, Calendar fEtime)
    {
        Criteria criteria = paymentRequestDao.createCriteria(Restrictions.eq(
                "finish", true));

        if (customer != null) {
            criteria.add(Restrictions.eq("customer", customer));
        }
        if (fStime != null) {
            criteria.add(Restrictions.ge("payTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("payTime", fEtime));
        }
        List<PaymentRequest> list = criteria.list();
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<BackMoneyRequest> getBackMoneyRequestList(Customer customer)
    {
        List<BackMoneyRequest> list = backMoneyRequestDao.findByProperty(
                "customer", customer);
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<WalletLog> getWalletLogPage(Page<WalletLog> page, String fName,
            String numberNo, Calendar fSTime, Calendar fETime, String fType)
    {
        logger.debug("后台分页获得客户提款流水信息");
        Criteria criteria = walletLogDao.createCriteria();
        if (!StringUtils.isEmpty(fName)) {
            criteria.createAlias("wallet", "wallet").createAlias(
                    "wallet.customer", "customer");
            criteria.add(Restrictions.eq("customer.nickName", fName));
        }
        if (fSTime != null) {
            criteria.add(Restrictions.ge("time", fSTime));
        }
        if (fETime != null) {
            criteria.add(Restrictions.le("time", fETime));
        }
        if (!StringUtils.isEmpty(fType)) {
            criteria.add(Restrictions.eq("type", WalletLogType.valueOf(fType)));
        }
        if (!StringUtils.isEmpty(numberNo)) {
            criteria.add(Restrictions.eq("serialNumber", numberNo));
        }
        criteria.addOrder(Order.desc("time"));
        criteria.addOrder(Order.desc("id"));
        page = walletLogDao.findByCriteria(page, criteria);
        return page;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<WalletLog> getWalletLogList(String fName, Calendar fStime,
            Calendar fEtime, String fType)
    {
        Criteria criteria = walletLogDao.createCriteria();
        if (!StringUtils.isEmpty(fName)) {
            criteria.createAlias("wallet", "wallet").createAlias(
                    "wallet.customer", "customer");
            criteria.add(Restrictions.eq("customer.nickName", fName));
        }
        if (fStime != null) {
            criteria.add(Restrictions.ge("time", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("time", fEtime));
        }
        if (!StringUtils.isEmpty(fType)) {
            criteria.add(Restrictions.eq("type", WalletLogType.valueOf(fType)));
        }
        criteria.addOrder(Order.desc("id"));
        List<WalletLog> list = criteria.list();
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaymentRequest getPaymentRequestByNumber(String serialNumber)
    {
        List<PaymentRequest> list = paymentRequestDao.findByProperty(
                "serialNumber", serialNumber);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PaymentRequest> getPaymentRequestList(String fMoneyChannel,
            String fName, String fNumNo, Calendar fStarTime, Calendar fEndTime,
            String fStatu, String fUser, String fUserTypes, Calendar fSTime,
            Calendar fETime)
    {
        logger.debug("询统计成功与失败充值金额 ");
        Criteria criteria = paymentRequestDao.createCriteria();
        if (!StringUtils.isEmpty(fMoneyChannel)) {
            criteria.add(Restrictions.eq("channel", MoneyChannel
                    .valueOf(fMoneyChannel)));
        }
        criteria.createAlias("customer", "customer");
        if (!StringUtils.isEmpty(fName)) {
            criteria.add(Restrictions.eq("customer.nickName", fName));
        }
        if (!StringUtils.isEmpty(fUserTypes)) {
            criteria.add(Restrictions.eq("customer.usrType", UserType
                    .valueOf(fUserTypes)));
        }

        if (fSTime != null) {
            criteria.add(Restrictions.ge("customer.registerTime", fSTime));
        }
        if (fETime != null) {
            criteria.add(Restrictions.le("customer.registerTime", fETime));
        }
        if (!StringUtils.isEmpty(fNumNo)) {
            criteria.add(Restrictions.eq("serialNumber", fNumNo));
        }
        if (fStarTime != null) {
            criteria.add(Restrictions.ge("payTime", fStarTime));
        }
        if (fEndTime != null) {
            criteria.add(Restrictions.le("payTime", fEndTime));
        }
        if (!StringUtils.isEmpty(fStatu)) {
            criteria.add(Restrictions
                    .eq("finish", Boolean.parseBoolean(fStatu)));
        }
        if (!StringUtils.isEmpty(fUser)) {
            criteria.createAlias("user", "user");
            criteria.add(Restrictions.eq("user.adminName", fUser));
        }

        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    /** */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BigDecimal getChongZhiSum(Customer customer)
    {
        String hql = "SELECT sum(o.money) FROM business_payment_request o where o.customer_id=:customerId and o.finish=1";
        SQLQuery query = paymentRequestDao.getSession().createSQLQuery(hql);
        query.setParameter("customerId", customer.getId());
        if (query.list().get(0) != null) {
            BigDecimal sum = new BigDecimal(query.list().get(0) + "");
            return sum;
        } 
        else {
            return new BigDecimal(0);
        }
    }
    
    /** 得到推荐人列表 index表示搜索深度*/
    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<WalletLog> getRecommendorsPage(Page<WalletLog> page, Customer customer, 
    		String recommendName, Calendar startTime, Calendar endTime)
	{
    	Criteria criteria = walletLogDao.createCriteria();
    	criteria.createAlias("wallet", "wallet").createAlias(
                "wallet.customer", "customer");
        criteria.add(Restrictions.or(Restrictions.eq("customer.superior", customer), 
    			Restrictions.eq("customer.ssuperior", customer)));
        
        if (!StringUtils.isEmpty(recommendName)) {
            criteria.add(Restrictions.eq("customer.nickName", recommendName));
        }

        if (startTime != null) {
            criteria.add(Restrictions.ge("time", startTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("time", endTime));
        }
        
        // 钱包状态过滤
        criteria.add(Restrictions.or(Restrictions.eq("type", WalletLogType.代购费用), 
    			Restrictions.or(Restrictions.eq("type", WalletLogType.合买保底费用), 
    			Restrictions.or(Restrictions.eq("type", WalletLogType.合买参与费用),
				Restrictions.or(Restrictions.eq("type", WalletLogType.合买发起费用),
    			Restrictions.or(Restrictions.eq("type", WalletLogType.追号费用),
    			Restrictions.or(Restrictions.eq("type", WalletLogType.代购退款),
				Restrictions.or(Restrictions.eq("type", WalletLogType.代购部分退款),
				Restrictions.or(Restrictions.eq("type", WalletLogType.合买退款),
				Restrictions.or(Restrictions.eq("type", WalletLogType.合买部分退款),
				Restrictions.eq("type", WalletLogType.追号退款)))))))))));
        
        criteria.addOrder(Order.desc("time"));
        criteria.addOrder(Order.desc("customer.id"));
        page = walletLogDao.findByCriteria(page, criteria);
        return page;
	}
    
    /** 得到推荐人列表 index表示搜索深度*/
    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<WalletLog> getRecommendorsList(Customer customer, 
    		String recommendName, Calendar startTime, Calendar endTime)
	{
    	Criteria criteria = walletLogDao.createCriteria();
    	criteria.createAlias("wallet", "wallet").createAlias(
                "wallet.customer", "customer");
        criteria.add(Restrictions.or(Restrictions.eq("customer.superior", customer), 
    			Restrictions.eq("customer.ssuperior", customer)));
        
        if (!StringUtils.isEmpty(recommendName)) {
            criteria.add(Restrictions.eq("customer.nickName", recommendName));
        }

        if (startTime != null) {
            criteria.add(Restrictions.ge("time", startTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("time", endTime));
        }
        
        // 钱包状态过滤
        criteria.add(Restrictions.or(Restrictions.eq("type", WalletLogType.代购费用), 
    			Restrictions.or(Restrictions.eq("type", WalletLogType.合买保底费用), 
    			Restrictions.or(Restrictions.eq("type", WalletLogType.合买参与费用),
				Restrictions.or(Restrictions.eq("type", WalletLogType.合买发起费用),
    			Restrictions.or(Restrictions.eq("type", WalletLogType.追号费用),
    			Restrictions.or(Restrictions.eq("type", WalletLogType.代购退款),
				Restrictions.or(Restrictions.eq("type", WalletLogType.代购部分退款),
				Restrictions.or(Restrictions.eq("type", WalletLogType.合买退款),
				Restrictions.or(Restrictions.eq("type", WalletLogType.合买部分退款),
				Restrictions.eq("type", WalletLogType.追号退款)))))))))));
        
        criteria.addOrder(Order.desc("time"));
        criteria.addOrder(Order.desc("customer.id"));
        return criteria.list();
	}
    
    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<CustomerCommission> getCommissionPage(Page<CustomerCommission> page)
	{
    	Criteria criteria = customerCommissionDao.createCriteria();
    	page = customerCommissionDao.findByCriteria(page, criteria);
    	return page;
	}
    
    /** 得到被推荐人注册数 根据推荐人，开始，结束时间作为条件*/
    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Long getRecommendorsPageNum(Page<Customer> page, Customer customer,Calendar startTime, Calendar endTime)
	{
    	Criteria criteria = customerDao.createCriteria();
    	criteria.add(Restrictions.eq("superior", customer));
        
        if (startTime != null) {
            criteria.add(Restrictions.ge("registerTime", startTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("registerTime", endTime));
        }
        criteria.setProjection(Projections.rowCount());
        Long regNumb = null;
        Object o = criteria.uniqueResult();
        if(o!=null){
        	regNumb = Long.parseLong(o.toString());
        }
        return regNumb;
	}
    
//    public static void main(String[] args)
//	{
//    	CustomerService customerService = new CustomerServiceImpl();
//    	Calendar curReportDate = Calendar.getInstance();
//    	Calendar startTime = Calendar.getInstance();
//		Calendar overTime = Calendar.getInstance();
//		startTime.set(curReportDate.get(Calendar.YEAR), curReportDate.get(Calendar.MONTH), curReportDate.get(Calendar.DATE), 0, 0, 0);
//		overTime.set(curReportDate.get(Calendar.YEAR), curReportDate.get(Calendar.MONTH), curReportDate.get(Calendar.DATE), 23, 59, 59);
//		Customer customer = new Customer();
//		customer.setId(new Long(2988));
////    	Long regNum = customerService.getRecommendorsPage2(null,customer,startTime, overTime);	
////    	System.out.println("============================="+regNum);
//	}
    
    /** 得到被推荐人列表*/
    @SuppressWarnings("unchecked")
        @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Customer> getRecommendorsPage2(Page<Customer> page, Customer customer,Calendar stime, Calendar etime)
	{
    	Criteria criteria = customerDao.createCriteria();

        criteria.add(Restrictions.or(Restrictions.eq("superior", customer), 
    			Restrictions.eq("ssuperior", customer)));
        
        if (stime != null) {
            criteria.add(Restrictions.ge("registerTime", stime));
        }
        if (etime != null) {
            criteria.add(Restrictions.le("registerTime", etime));
        }
        criteria.addOrder(Order.desc("id"));
        page = customerDao.findByCriteria(page, criteria);
        return page;
	}
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Customer> getCustomerPageByNickName(Page<Customer> page, String nickName)
	{
    	Criteria criteria = customerDao.createCriteria();
    	if(nickName != null && !nickName.equals(""))
    	{
    		criteria.add(Restrictions.eq("nickName", nickName));
    	}   
    	criteria.add(Restrictions.eq("isApply", true));
    	criteria.add(Restrictions.eq("isPass", 1));
    	page = customerDao.findByCriteria(page, criteria);
    	return page;
	}
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<CustomerCommission> getCustomerCommissionPageByName(String name)
	{
    	Criteria criteria = customerCommissionDao.createCriteria();
    	if(name != null && !name.equals(""))
    	{
    		criteria.add(Restrictions.eq("Name", name));
    	}   	
    	return criteria.list();
	}
    
    /** 根据ID找到CusCommssion的值更改Name*/
    public void updateCusCommissionName(Long id, String newName)
    {
    	Customer customer = (Customer)customerDao.getSession().get(Customer.class, id);
    	CustomerCommission cusComm = this.getCustomerCommissionPageByName(newName).get(0);
    	BigDecimal ratio1 = cusComm.getRatio1();
    	BigDecimal ratio2 = cusComm.getRatio2();
    	customer.setCommission(cusComm);
    	customer.setSuperRatio(ratio1);
    	customer.setSsuperRatio(ratio2);
    	customerDao.save(customer);
    }
    
    /*得到所有的CommissionName*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<CustomerCommission> getAllCommissionNames()
    {
    	Criteria criteria = customerCommissionDao.createCriteria();
    	List<CustomerCommission> list = criteria.list();
    	//String hql = "select cc from CustomerCommission cc";
    	//Query query = customerCommissionDao.getSession().createQuery(hql);
        //List<CustomerCommission> list = query.list();
    	return list;
    }
    
    /**根据方案名称和新的比率更新提成比率*/
    public void updateCustomerRation(String name, BigDecimal ration1, BigDecimal ration2)
    {
    	Criteria criteria = customerCommissionDao.createCriteria();
    	criteria.add(Restrictions.eq("Name", name));
    	CustomerCommission commission = (CustomerCommission)criteria.list().get(0);
    	Criteria criteriaCus = customerDao.createCriteria();
    	criteriaCus.add(Restrictions.eq("commission", commission));
    	Customer customer;
    	commission.setRatio1(ration1);
    	commission.setRatio2(ration2);
    	this.saveCommssion(commission);
		for(int i=0;i<criteriaCus.list().size();i++)
		{
			customer = (Customer)criteriaCus.list().get(i);
    		customer.setSuperRatio(ration1);
    		customer.setSsuperRatio(ration2);
    		this.save(customer);
		}
    }
    
    /**取得用户推荐的客户总人数（一级推荐人）*/
    public int count(Customer customer)
    {
    	int num = 0;
    	Criteria criteria = customerDao.createCriteria();
    	criteria.add(Restrictions.eq("superior", customer));
    	List list = criteria.list();
    	for(int i=0; i<list.size(); i++)
    	{
    		num++;
    	}
    	return num;
    }
	
	public Customer boundPhone(Customer customer) {
		String uuid=UUID.randomUUID().toString();
		//String question =""+new Random().nextInt(10)+new Random().nextInt(10);
		customer.setYanzhenma(uuid.substring(1,5));
		//customer.setQuestion(question);
		
		String content ="【一彩票网】欢迎使用一彩票，一彩票手机验证码为"+customer.getYanzhenma();
		Map result = SmsUtil.sendSms(customer.getMobileNo(), new String[]{customer.getYanzhenma()},Configuration.getInstance().getValue("phoneVilTemplateIDYUN"));
		if("000000".equals(result.get("statusCode"))){//发送成功
			smsLogService.saveSmsLogAndSendState(customer.getMobileNo(), content, customer.getId(),SmsLogType.VALID,SmsLogState.SENDED,"");
		}else{
			smsLogService.saveSmsLogAndSendState(customer.getMobileNo(), content, customer.getId(),SmsLogType.VALID,SmsLogState.FAILURE,"错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
//		smsLogService.saveSmsLog(customer.getMobileNo(), content, customer.getId(),SmsLogType.VALID);
		customerDao.save(customer);
		//messageTaskExcutor.addNotifyCustomer(customer);
		return  customer;
	}

	public Customer boundPhone1(Customer customer) {
//		customer.setBound("bound");
		activityListener.addPhoneCustomer(customer);
		return customer;
	}
	
	/**根据电话号码得到绑定的customer*/
	public Customer getCustomerByMobileNo(String mobileNo)
	{
		logger.debug("根据电话号码得到绑定的customer");
		Criteria criteria = this.customerDao.createCriteria();
		criteria.add(Restrictions.eq("bound", "bound"));
		criteria.add(Restrictions.eq("mobileNo", mobileNo));
		return (Customer)criteria.uniqueResult();
	}
	
	/**根据openId得到用户信息*/
	@SuppressWarnings("unchecked")
	public Customer getCustomerByOpenId(String openId) {
		List<Customer> list = (List<Customer>) customerDao.find(
                "from Customer where openId=?", new Object[] { openId });
        if (list.isEmpty())
            return null;
        return list.get(0);
	}
	
	/**根据类型取出所有的wallert_log所有记录*/
	public List<WalletLog> getWalletLogOfLSCZ(Calendar stime, Calendar etime, String nickName, int type)
	{
		Criteria criteria = this.walletLogDao.createCriteria();
		WalletLogType wlt = this.changeToWalerLogType(type);
		criteria.add(Restrictions.eq("type", wlt));
		if (stime != null) {
			criteria.add(Restrictions.ge("time", stime));
		}
		if (etime != null) {
			criteria.add(Restrictions.le("time", etime));
		}
		if (!nickName.isEmpty()) {
			criteria.createAlias("wallet", "wallet").createAlias("wallet.customer", "customer").add(Restrictions.eq("customer.nickName", nickName));
		}
		return criteria.list();
	}
	
	private WalletLogType changeToWalerLogType(int num)
	{
		WalletLogType wlt = WalletLogType.代购费用;
		if (num == 1) {
			wlt = WalletLogType.合买发起费用;
		}
		else if (num == 2) {
			wlt = WalletLogType.合买参与费用;
		}
		else if (num == 3) {
			wlt = WalletLogType.追号冻结;
		}
		else if (num == 4) {
			wlt = WalletLogType.追号费用;
		}
		else if (num == 5) {
			wlt = WalletLogType.合买保底冻结;
		}
		else if (num == 6) {
			wlt = WalletLogType.合买保底费用;
		}
		else if (num == 7) {
			wlt = WalletLogType.提款冻结;
		}
		else if (num == 8) {
			wlt = WalletLogType.提款扣款;
		}
		else if (num == 9) {
			wlt = WalletLogType.手续费冻结;
		}
		else if (num == 10) {
			wlt = WalletLogType.手续费扣款;
		}
		else if (num == 11) {
			wlt = WalletLogType.账户充值;
		}
		else if (num == 12) {
			wlt = WalletLogType.账户返奖;
		}
		else if (num == 13) {
			wlt = WalletLogType.直接充值;
		}
		else if (num == 14) {
			wlt = WalletLogType.代购退款;
		}
		else if (num == 15) {
			wlt = WalletLogType.合买退款;
		}
		else if (num == 16) {
			wlt = WalletLogType.追号退款;
		}
		else if (num == 17) {
			wlt = WalletLogType.合买撤单;
		}
		else if (num == 18) {
			wlt = WalletLogType.合买保底解冻;
		}
		else if (num == 19) {
			wlt = WalletLogType.合买保底部分解冻;
		}
		else if (num == 20) {
			wlt = WalletLogType.代购部分退款;
		}
		else if (num == 21) {
			wlt = WalletLogType.合买部分退款;
		}
		else if (num == 22) {
			wlt = WalletLogType.活动赠送;
		}
		else if (num == 23) {
			wlt = WalletLogType.提款失败返款;
		}
		else if (num == 24) {
			wlt = WalletLogType.合买提成;
		}
		else if (num == 25) {
			wlt = WalletLogType.系统扣款;
		}
		else if (num == 26) {
			wlt = WalletLogType.套餐消费;
		}
		else if (num == 27) {
			wlt = WalletLogType.拉手余额充值;
		}
		else if (num == 28) {
			wlt = WalletLogType.为朋友充值;
		}
		else if (num == 29) {
			wlt = WalletLogType.被朋友充值;
		}
		return wlt;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCpsCustomerList()
	{
		List<Customer> list = (List<Customer>) customerDao.find("from Customer where isApply=? and isPass = ?", new Object[] { true,1 });
        if (list.isEmpty())
            return null;
        return list;
	}
	
}
