package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.NewlyWinPrize;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayStatus;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CustomerService;

@Service("communityService")
@Transactional
public class CommunityServiceImpl implements CommunityService
{
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<Community, Long> communityDao;

    public SimpleHibernateTemplate<Part, Long> partDao;

    public SimpleHibernateTemplate<Plan, Long> planDao;

    public SimpleHibernateTemplate<PlanItem, Long> planItemDao;
    
    @Autowired
    public CustomerService customerService;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.communityDao = new PagerHibernateTemplate<Community, Long>(
                sessionfactory, Community.class);

        this.partDao = new SimpleHibernateTemplate<Part, Long>(sessionfactory,
                Part.class);
        this.planDao = new SimpleHibernateTemplate<Plan, Long>(sessionfactory,
                Plan.class);
        this.planItemDao = new SimpleHibernateTemplate<PlanItem, Long>(
                sessionfactory, PlanItem.class);
    }

    /***
     * 
     * @throws NotEnoughBalanceException
     * @see 保存合买单并生成发起人的合买份额
     * */
    public void saveAndCreatePart(Community community, List<PlanItem> items)
    {
        logger.debug("保存合买单并生成发起人的合买份额!");
        planDao.save(community.getPlan());
        for (PlanItem planItem : items) {
            planItem.setPlan(community.getPlan());
            planItemDao.save(planItem);
        }
        communityDao.save(community);
        Part part = new Part(community, community.getCustomer(), community
                .getBuyPart());
        partDao.save(part);
        WalletLog walletLog;
        if (community.getInsurePart() > 0) {
            BigDecimal djmoney = community.getPerMoney().multiply(
                    BigDecimal.valueOf(community.getInsurePart()));
            walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO,
                    djmoney, djmoney, BigDecimal.ZERO, community.getPlan()
                            .getType().name(), WalletLogType.合买保底冻结, community
                            .getPlan().getNumberNo());
            customerService.addWalletLog(community.getCustomer().getWallet()
                    .getId(), walletLog);
        }
        BigDecimal outMoney = part.getMoney();
        walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO, outMoney,
                BigDecimal.ZERO, BigDecimal.ZERO, community.getPlan().getType()
                        .name(), WalletLogType.合买发起费用, community.getPlan()
                        .getNumberNo());
        customerService.addWalletLog(community.getCustomer().getWallet()
                .getId(), walletLog);
    }
    public void saveAndCreatePart(Community community, List<PlanItem> items,Cookie cookie)
    {
        logger.debug("保存合买单并生成发起人的合买份额!");
        planDao.save(community.getPlan());
        for (PlanItem planItem : items) {
            planItem.setPlan(community.getPlan());
            planItemDao.save(planItem);
        }
        communityDao.save(community);
        Part part = new Part(community, community.getCustomer(), community
                .getBuyPart());
        partDao.save(part);
        WalletLog walletLog;
        if (community.getInsurePart() > 0) {
            BigDecimal djmoney = community.getPerMoney().multiply(
                    BigDecimal.valueOf(community.getInsurePart()));
            walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO,
                    djmoney, djmoney, BigDecimal.ZERO, community.getPlan()
                            .getType().name(), WalletLogType.合买保底冻结, community
                            .getPlan().getNumberNo());
           
            customerService.addWalletLog(community.getCustomer().getWallet()
                    .getId(), walletLog);
        }
        BigDecimal outMoney = part.getMoney();
        walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO, outMoney,
                BigDecimal.ZERO, BigDecimal.ZERO, community.getPlan().getType()
                        .name(), WalletLogType.合买发起费用, community.getPlan()
                        .getNumberNo());
	   if(null!=cookie){
            	String pid=cookie.getValue().split("!")[1];
            	if(pid!=null)
            		walletLog.setPid(pid);
            	String adid=cookie.getValue().split("!")[2];
            	if(adid!=null)
            		walletLog.setAdid(adid);
       }					
        customerService.addWalletLog(community.getCustomer().getWallet()
                .getId(), walletLog);
    }

    /**
     * @param communityId
     * @param customer
     * @param partNum
     */
    public Community addPart(Long communityId, Customer customer, int partNum)
            throws Exception
    {
        Community community = findById(communityId);
        if(community.getCommunityType().equals(CommunityType.已撤单)) {
            throw new Exception("不能参与该合买：发起人已撤单!");
        }
        if (community.getOkPart() + partNum > community.getTotalPart()) {
            throw new Exception("超出合买总份数");
        }
        Part part = new Part(community, customer, partNum);
        if(customer.getWallet().getStatus() == 1) {
        	throw new Exception("您的钱包已被冻结，请联系管理员!");
        }
        if (customer.getWallet().getBalance().compareTo(part.getMoney()) < 0) {
            throw new Exception("用户余额不足");
        }
        community.addOkPart(part.getPartNum());
        communityDao.save(community);
        savePart(part);
        WalletLog walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO,
                part.getMoney(), BigDecimal.ZERO, BigDecimal.ZERO, community
                        .getPlan().getType().name(), WalletLogType.合买参与费用,
                community.getPlan().getNumberNo());
        customerService.addWalletLog(part.getCustomer().getWallet().getId(),
                walletLog);
        // 合买满员
        if (community.getOkPart() == community.getTotalPart()) {
            community.setCommunityType(CommunityType.已满员);
            save(community);
            // 合买保底解冻
            if (community.getInsurePart() > 0) {
                BigDecimal money = new BigDecimal(community.getInsurePart())
                        .multiply(community.getPerMoney());
                WalletLog returnWalletLog = new WalletLog(BusinessType.收入,
                        money, BigDecimal.ZERO, BigDecimal.ZERO, money,
                        community.getPlan().getType().name(),
                        WalletLogType.合买保底解冻, community.getPlan().getNumberNo());
                customerService.addWalletLog(community.getCustomer()
                        .getWallet().getId(), returnWalletLog);
            }
        }
        return community;
    }
    
    public Community addPart(Long communityId, Customer customer, int partNum, Cookie cookie)
    throws Exception
{
    	Community community = findById(communityId);
    	if(community.getCommunityType().equals(CommunityType.已撤单)) {
    		throw new Exception("不能参与该合买：发起人已撤单!");
    	}
    	if (community.getOkPart() + partNum > community.getTotalPart()) {
    		throw new Exception("超出合买总份数");
    	}
    	Part part = new Part(community, customer, partNum);
    	if(customer.getWallet().getStatus() == 1) {
    		throw new Exception("您的钱包已被冻结，请联系管理员!");
    	}
    	if (customer.getWallet().getBalance().compareTo(part.getMoney()) < 0) {
    		throw new Exception("用户余额不足");
    	}
    	community.addOkPart(part.getPartNum());
    	communityDao.save(community);
    	if(partNum > 0)
    	{
	    	savePart(part);
	    	WalletLog walletLog = new WalletLog(BusinessType.支出, BigDecimal.ZERO,
	        part.getMoney(), BigDecimal.ZERO, BigDecimal.ZERO, community
	                .getPlan().getType().name(), WalletLogType.合买参与费用,
	        community.getPlan().getNumberNo());
	    	if(null!=cookie){
	    		String pid=cookie.getValue().split("!")[1];
	    		if(pid!=null)
	    			walletLog.setPid(pid);
	    		String adid=cookie.getValue().split("!")[2];
	    		if(adid!=null)
	    			walletLog.setAdid(adid);
	    	}					
	    	customerService.addWalletLog(part.getCustomer().getWallet().getId(),
	        walletLog);
    	}
// 合买满员
    	if (community.getOkPart() == community.getTotalPart()) {
    		community.setCommunityType(CommunityType.已满员);
    		save(community);
    // 合买保底解冻
    		if (community.getInsurePart() > 0) {
    			BigDecimal money = new BigDecimal(community.getInsurePart())
                .multiply(community.getPerMoney());
    			WalletLog returnWalletLog = new WalletLog(BusinessType.收入,
                money, BigDecimal.ZERO, BigDecimal.ZERO, money,
                community.getPlan().getType().name(),
                WalletLogType.合买保底解冻, community.getPlan().getNumberNo());
    			customerService.addWalletLog(community.getCustomer()
                .getWallet().getId(), returnWalletLog);
    		}
    	}
    	return community;
	}

    /** 根据合买查找合买参与 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Part> getPartByCommunity(Community community)
    {
        return partDao.findByProperty("community", community);
    }

    /** 合买生成注单后,参与者跟据比例分钱 */
    public boolean returnMoney(Community community, BigDecimal money,
            WalletLogType walletLogType, String description)
    {
    	BigDecimal payOff = BigDecimal.valueOf(0);
    	BigDecimal benjin;
    	benjin = community.getPerMoney().multiply(BigDecimal.valueOf(community.getTotalPart()));

        synchronized (community.getId()) {
            List<Part> parts = getPartByCommunity(community);
            
            //当奖金低于投注本金时，发起人是不会得到佣金的。（只有奖金大于投注本金有盈利的情况下才可得到佣金的）
            if(money.compareTo(benjin) == 1) { //money > buymoney 奖金比本金多
            	//剩余奖金=总的奖金-佣金【（奖金-本金）*比例】
            	//佣金
            	payOff = money.subtract(benjin).multiply(BigDecimal.valueOf(
            			community.getCommision()).divide(BigDecimal.valueOf(100)));
            	//剩余奖金
            	money = money.subtract(payOff);
            	logger.info( "本金"+benjin);
            	logger.info( "可以分配的奖金"+money.toString());
            	logger.info("佣金："+payOff.toString() );
            }

            for (Part part : parts) {
                BigDecimal returnMoney = money.multiply(
                        BigDecimal.valueOf(part.getPartNum())).divide(
                        BigDecimal.valueOf(community.getTotalPart()), 2,
                        RoundingMode.HALF_UP);
                
                //2011-08-10 -- 修改“我的奖金”
                if (WalletLogType.账户返奖.equals(walletLogType)) {
                	part.setWinTaxMoney(returnMoney);
                	partDao.save(part);
                }
                /*
                	part.setWinTaxMoney(returnMoney);
            		partDao.save(part);
            	*/
                WalletLog walletLog = new WalletLog(BusinessType.收入,
                        returnMoney, BigDecimal.ZERO, BigDecimal.ZERO,
                        BigDecimal.ZERO, description, walletLogType, community
                                .getPlan().getNumberNo());
                customerService.addWalletLog(part.getCustomer().getWallet()
                        .getId(), walletLog);
                if (WalletLogType.账户返奖.equals(walletLogType)) {
                    NewlyWinPrize newwin = new NewlyWinPrize(community
                            .getTerm().getTermNo(), community.getPlan()
                            .getType(), part.getCustomer().getNickName(),
                            returnMoney, community.getPlan().getNumberNo(),
                            PlayStatus.合买, community.getCreateTimer(), part
                                    .getMoney());
                    customerService.saveNewlyWinPrize(newwin);
                }
            }
            
            //将佣金打入发起人账户
            WalletLog walletLog2 = new WalletLog(BusinessType.收入,
            		payOff, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, description, WalletLogType.合买提成, community
                            .getPlan().getNumberNo());
            customerService.addWalletLog(community.getCustomer().getWallet().getId(),walletLog2);
            /*if (WalletLogType.合买提成.equals(walletLogType)) {
				NewlyWinPrize newwin2 = new NewlyWinPrize(community
                    .getTerm().getTermNo(), community.getPlan()
                    .getType(), community.getCustomer().getNickName(),
                    payOff, community.getPlan().getNumberNo(),
                    PlayStatus.合买, community.getCreateTimer(),BigDecimal.valueOf(community.getBuyPart()));
         		customerService.saveNewlyWinPrize(newwin2);
        	}*/
        }
        return true;
    }

    /** 根据彩期合买截止 */
    public List<Community> stopTogegerSale(LotteryTerm term)
    {
        List<Community> list = getCommunityByTermIdAndCommunityType(term,
                CommunityType.未满员);
        return stopCommunity(list);
    }
    
    /** 根据彩期合买截止 */
    public List<Community> stopTogegerSale(MatchArrange match)
    {
    	List<Community> list = getCommunityByMatchAndCommunityType(match,
                CommunityType.未满员);
    	return stopCommunity(list);
    }

    /** 全款退款 */
    private void returnMoney(Community community)
    {
        List<Part> parts = getPartByCommunity(community);
        for (Part part : parts) {
            BigDecimal money = part.getMoney();
            WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    community.getPlan().getType().name(), WalletLogType.合买退款,
                    community.getPlan().getNumberNo());
            customerService.addWalletLog(
                    part.getCustomer().getWallet().getId(), walletLog);
        }
    }

    /**
     * 根据合买状态及期数ID查询
     * */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Community> getCommunityByTermIdAndCommunityType(
            LotteryTerm term, CommunityType communityType)
    {
        return communityDao.createCriteria(Restrictions.eq("term", term),
                Restrictions.eq("communityType", communityType)).list();
    }
    
    /**
     * 根据合买状态及期数ID查询
     * */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Community> getCommunityByMatchAndCommunityType(
            MatchArrange match, CommunityType communityType)
    {
        return communityDao.createCriteria(Restrictions.eq("firstMatch", match),
                Restrictions.eq("communityType", communityType)).list();
    }

    public void delete(Community entity)
    {
        communityDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Community findById(Long id)
    {
        return (Community) communityDao.getSession().get(Community.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Community load(Long id)
    {
        return communityDao.get(id);
    }

    public Community save(Community entity)
    {
        communityDao.save(entity);
        return entity;
    }

    public Community update(Community entity)
    {
        communityDao.save(entity);
        return entity;
    }

    public void savePart(Part part)
    {
        partDao.save(part);
    }

    /** 分页获得客户合买信息 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Community> getCommunityPage(Page<Community> page,
            LotteryType type, CommunityType communityType, Customer customer,
            LotteryTerm term)
    {
        Criteria criteria = communityDao.createCriteria();
        criteria.createAlias("term", "term");
        criteria.add(Restrictions.eq("term.current", true));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -9);

        criteria.add(Restrictions.gt("createTimer", c));
  
        if (null != type) {
            // 取别名
            criteria.add(Restrictions.eq("term.type", type));
        }
        if (communityType != null) {
            criteria.add(Restrictions.eq("communityType", communityType));
        }
        else {
            //默认不显示已撤单
        	 criteria.add(Restrictions.ne("communityType", CommunityType.已流产));
            criteria.add(Restrictions.ne("communityType", CommunityType.已撤单)); 
        }
        if (customer != null) {
            criteria.add(Restrictions.eq("customer", customer));
        }
        if (null != term) {
            criteria.add(Restrictions.eq("term", term));
        }
        
        criteria.addOrder(Order.asc("communityType"));
        criteria.addOrder(Order.desc("totalPart"));
        criteria.addOrder(Order.desc("createTimer"));
        page = communityDao.findByCriteria(page, criteria);
        return page;
    }

    /** 我发起的合买查询 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Community> getTogetherSale(Page<Community> page,
            LotteryType type, Calendar beginTime, Calendar endTime,
            Customer customer)
    {
        logger.debug("我的合买查询!");
        Criteria criteria = communityDao.createCriteria(Restrictions.eq(
                "customer", customer));

        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.createAlias("term", "term").add(
                    Restrictions.eq("term.type", type));
        }
        if (beginTime != null) {
            criteria.add(Restrictions.ge("createTimer", beginTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("createTimer", endTime));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = communityDao.findByCriteria(page, criteria);
        return page;
    }

    /** 我所有的合买查询 */
    /*
     * 要查询的参与和发起人 模糊查询
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Community> getTogetherSaleAndJoin(Page<Community> page,
            Customer customer, LotteryType type, LotteryTerm term,
            CommunityType communityType, String fqr)
    {
        Criteria criteria = communityDao.createCriteria();
        if (null != customer) {
            List<String> idList = partDao
                    .find("select part.community.id from Part as part where part.customer.id="
                            + customer.getId());
            criteria.add(Restrictions.in("id", idList));
        }
        if (null != communityType) {
            criteria.add(Restrictions.eq("communityType", communityType));
        }
        else {
            //默认不显示已撤单
            criteria.add(Restrictions.ne("communityType", CommunityType.已撤单)); 
        }
        if (StringUtils.isNotBlank(fqr)) {
            List idList = partDao
                    .find("from Customer as cus where cus.nickName like '"
                            + fqr + "%'");
            criteria.add(Restrictions.in("customer", idList));
        }
        criteria.createAlias("term", "term");
        if (null != type) {
            criteria.add(Restrictions.eq("term.type", type));
        }
        if (null != term) {
            criteria.add(Restrictions.eq("term", term));
        }
        else {
            //默认查找当前期的合买信息
            criteria.add(Restrictions.eq("term.current", true)); 
        }
        criteria.addOrder(Order.asc("communityType"));
        criteria.addOrder(Order.desc("schedule"));
        criteria.addOrder(Order.desc("createTimer"));
        return communityDao.findByCriteria(page, criteria);
    }

    //服务器端查询
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Community> getCommunityPage(Page<Community> page,
            String fSerch, String fValue, String fSerchTerm,
            String fSerchTerm1, Calendar fStime, Calendar fEtime,
            String fAmount1, String fAmount2, String fOkPart1, String fOkPart2,
            String fSstatu, String fType)
    {
        logger.debug("后台合买查询!");
        Criteria criteria = communityDao.createCriteria();
        criteria.createAlias("plan", "plan");
        if (!StringUtils.isEmpty(fSerch)) {
            if (fSerch.equals("发起人")) {
                criteria.createAlias("customer", "customer").add(
                        Restrictions.eq("customer.nickName", fValue));
            }
            if (fSerch.equals("方案号")) {
                criteria.add(Restrictions.eq("plan.numberNo", fValue));
            }
        }
        criteria.createAlias("term", "term");
        if (!StringUtils.isEmpty(fSerchTerm)) {
            criteria.add(Restrictions.ge("term.termNo", fSerchTerm));
        }
        if (!StringUtils.isEmpty(fSerchTerm1)) {
            criteria.add(Restrictions.le("term.termNo", fSerchTerm1));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("term.type", LotteryType
                    .valueOf(fType)));
        }
        if (fStime != null) {
            criteria.add(Restrictions.ge("createTimer", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("createTimer", fEtime));
        }
        if (!StringUtils.isEmpty(fAmount1)) {
            criteria.add(Restrictions.ge("totalPart", Integer
                    .parseInt(fAmount1)));
        }
        if (!StringUtils.isEmpty(fAmount2)) {
            criteria.add(Restrictions.le("totalPart", Integer
                    .parseInt(fAmount2)));
        }

        if (!StringUtils.isEmpty(fOkPart1)) {
            criteria.add(Restrictions.sqlRestriction(
                    "ok_part/total_part*100>=?", Long.parseLong(fOkPart1),
                    Hibernate.LONG));
        }
        if (!StringUtils.isEmpty(fOkPart2)) {
            criteria.add(Restrictions.sqlRestriction(
                    "ok_part/total_part*100<=?", Long.parseLong(fOkPart2),
                    Hibernate.LONG));
        }
        if (!StringUtils.isEmpty(fSstatu)) {
            criteria.add(Restrictions.eq("communityType", CommunityType
                    .valueOf(fSstatu)));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = communityDao.findByCriteria(page, criteria);
        return page;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Community> getCommunityList(String fSerch, String fValue,
            String fSerchTerm, String fSerchTerm1, Calendar fStime,
            Calendar fEtime, String fAmount1, String fAmount2, String fOkPart1,
            String fOkPart2, String fSstatu, String fType)
    {
        logger.debug("后台合买统计!");
        Criteria criteria = communityDao.createCriteria();
        criteria.createAlias("plan", "plan");
        if (!StringUtils.isEmpty(fSerch)) {
            if (fSerch.equals("发起人")) {
                criteria.createAlias("customer", "customer").add(
                        Restrictions.eq("customer.nickName", fValue));

            }
            if (fSerch.equals("方案号")) {
                criteria.add(Restrictions.eq("plan.numberNo", fValue));
            }
        }
        criteria.createAlias("term", "term");
        if (!StringUtils.isEmpty(fSerchTerm)) {
            criteria.add(Restrictions.ge("term.termNo", fSerchTerm));
        }
        if (!StringUtils.isEmpty(fSerchTerm1)) {
            criteria.add(Restrictions.le("term.termNo", fSerchTerm1));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("term.type", LotteryType
                    .valueOf(fType)));
        }
        if (fStime != null) {
            criteria.add(Restrictions.ge("createTimer", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("createTimer", fEtime));
        }
        if (!StringUtils.isEmpty(fAmount1)) {
            criteria.add(Restrictions.ge("totalPart", Integer
                    .parseInt(fAmount1)));
        }
        if (!StringUtils.isEmpty(fAmount2)) {
            criteria.add(Restrictions.le("totalPart", Integer
                    .parseInt(fAmount2)));
        }

        if (!StringUtils.isEmpty(fOkPart1)) {
            criteria.add(Restrictions.sqlRestriction(
                    "ok_part/total_part*100>=?", Long.parseLong(fOkPart1),
                    Hibernate.LONG));
        }
        if (!StringUtils.isEmpty(fOkPart2)) {
            criteria.add(Restrictions.sqlRestriction(
                    "ok_part/total_part*100<=?", Long.parseLong(fOkPart2),
                    Hibernate.LONG));
        }
        if (!StringUtils.isEmpty(fSstatu)) {
            criteria.add(Restrictions.eq("communityType", CommunityType
                    .valueOf(fSstatu)));
        }
        List<Community> list = criteria.list();
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Community getCommunityByPlan(Plan plan)
    {
        logger.debug("根据方案查找合买数据!");
        List<Community> list = communityDao.findByProperty("plan", plan);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return new Community();
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Part> getPartList(Community c, Customer cu)
    {
        logger.debug("根据合买ID和用户得到用户参与合买金额!");
        Criteria criteria = partDao.createCriteria(Restrictions.eq("community",
                c), Restrictions.eq("customer", cu));
        List<Part> list = criteria.list();
        return list;
    }
    
    public void backoutCommunity(Community community)
    {
        if(community.getCommunityType().equals(CommunityType.未满员)) {
            community.setCommunityType(CommunityType.已撤单);
            save(community);
            returnMoney(community);
            // 退还保底部分
            if (community.getInsurePart() > 0) {
                BigDecimal money = new BigDecimal(community.getInsurePart())
                        .multiply(community.getPerMoney());
                WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                        BigDecimal.ZERO, BigDecimal.ZERO, money, community
                                .getPlan().getType().name(),
                        WalletLogType.合买保底解冻, community.getPlan()
                                .getNumberNo());
                customerService.addWalletLog(community.getCustomer()
                        .getWallet().getId(), walletLog);
            }
        }
    }
    
    private List<Community> stopCommunity(List<Community> list)
    {
    	List<Community> result = new ArrayList<Community>();
    	for (Community community : list) {
            if (community.getOkPart() + community.getInsurePart() < community
                    .getTotalPart()) {
                community.setCommunityType(CommunityType.已流产);
                // 退还冻结
                returnMoney(community);
                save(community);
                // 退还保底部分
                if (community.getInsurePart() > 0) {
                    BigDecimal money = new BigDecimal(community.getInsurePart())
                            .multiply(community.getPerMoney());
                    WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                            BigDecimal.ZERO, BigDecimal.ZERO, money, community
                                    .getPlan().getType().name(),
                            WalletLogType.合买保底解冻, community.getPlan()
                                    .getNumberNo());
                    customerService.addWalletLog(community.getCustomer()
                            .getWallet().getId(), walletLog);
                }
                continue;
            }
            int partNum = community.getTotalPart() - community.getOkPart();// 实保份数
            int morePart = community.getInsurePart() - partNum;// 保底多出份数
            if (morePart > 0) {
                BigDecimal money = new BigDecimal(morePart).multiply(community
                        .getPerMoney());
                WalletLog walletLog = new WalletLog(BusinessType.收入, money,
                        BigDecimal.ZERO, BigDecimal.ZERO, money, community
                                .getPlan().getType().name(),
                        WalletLogType.合买保底部分解冻, community.getPlan()
                                .getNumberNo());
                customerService.addWalletLog(community.getCustomer()
                        .getWallet().getId(), walletLog);
            }
            // 多保部分生成一份
            Part part = new Part(community, community.getCustomer(), partNum);
            savePart(part);
            WalletLog walletLog = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, part
                            .getMoney(), community.getPlan().getType().name(),
                    WalletLogType.合买保底费用, community.getPlan().getNumberNo());
            customerService.addWalletLog(community.getCustomer().getWallet()
                    .getId(), walletLog);
            community.addOkPart(partNum);
            community.setCommunityType(CommunityType.已满员);
            save(community);
            result.add(community);
        }
    	return result;
    }
}
