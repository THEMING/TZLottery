package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.cache.LotteryTermCache;
import com.xsc.lottery.common.ComponentContextLoader;
import com.xsc.lottery.common.Constants;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.active.ActivityDetail;
import com.xsc.lottery.entity.active.ActivityDetailType;
import com.xsc.lottery.entity.active.ActivityType;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.TermTypeConfig;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeOrder;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderResult;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.handle.BaseLotteryHandle;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.service.active.ActivityService;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.task.ticket.TicketTreatmentWork.winTicketDis;
import com.xsc.lottery.util.DateUtil;

@Service("lotteryTermService")
@Transactional
@SuppressWarnings("unused")
public class LotteryTermServiceImpl implements LotteryTermService
{
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    public PagerHibernateTemplate<LotteryTerm, Long> lotteryTermDao;
    public PagerHibernateTemplate<PrizeLevel, Long> prizeLevelDao;
    private PagerHibernateTemplate<TermTypeConfig, Long> termConfigDao;
    public PagerHibernateTemplate<PlanItem, Long> planItemDao;
    public PagerHibernateTemplate<Order, Long> orderDao;
    public PagerHibernateTemplate<MatchArrange, Long> matchArrangeDao;
    public PagerHibernateTemplate<Ticket, Long> ticketDao;

    @Autowired
    private LotteryHandleFactory handleFactory;
    
    @Autowired
    public LotteryOrderService orderservice;

    @Autowired
    public ChaseService chaseservice;

    @Autowired
    private LotteryTermCache<LotteryTerm> termCache;

    @Autowired
    public TicketBusinessFactory ticketBusinessFactory;
    
    @Autowired
    private SysParamService sysParamService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private void setSessionFactory(final SessionFactory sessionfactory)
    {
        this.orderDao = new PagerHibernateTemplate<Order, Long>(sessionfactory,
                Order.class);
        this.lotteryTermDao = new PagerHibernateTemplate<LotteryTerm, Long>(
                sessionfactory, LotteryTerm.class);
        this.prizeLevelDao = new PagerHibernateTemplate<PrizeLevel, Long>(
                sessionfactory, PrizeLevel.class);
        this.termConfigDao = new PagerHibernateTemplate<TermTypeConfig, Long>(
                sessionfactory, TermTypeConfig.class);
        this.planItemDao = new PagerHibernateTemplate<PlanItem, Long>(
                sessionfactory, PlanItem.class);
        this.matchArrangeDao = new PagerHibernateTemplate<MatchArrange, Long>(
                sessionfactory, MatchArrange.class);
        this.ticketDao = new PagerHibernateTemplate<Ticket, Long>(
                sessionfactory, Ticket.class);
    }

    /** 获取最后截止的彩期 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm getLastStopSaleTerm(LotteryType type)
    {
        logger.debug("获取最后截止的彩期");
        Criteria criteria = lotteryTermDao.createCriteria(Restrictions.eq(
                "current", false), Restrictions.eq("type", type));
        criteria.setMaxResults(1).addOrder(Order.desc("id"));
        List<LotteryTerm> list = criteria.list();
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    /** 获取最后有开奖结果期数 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm getLastOpenPrizeResult(LotteryType type)
    {
        logger.debug("获取最后有开奖结果期数");
        Criteria criteria = lotteryTermDao.createCriteria(Restrictions.eq(
                "current", false), Restrictions.eq("type", type));
        criteria.add(Property.forName("result").isNotNull());
        criteria.setMaxResults(1).addOrder(Order.desc("id"));
        List<LotteryTerm> list = criteria.list();
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    /** 获取当前期 */
    @SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm getCurrentTerm(LotteryType type)
    {
        logger.debug("获取当前期");
        LotteryTerm curr = termCache.get(type);
        if(null == curr) {
        	if(type == LotteryType.足彩14场 || type == LotteryType.四场进球 || type == LotteryType.足彩6场半 || type == LotteryType.足彩任9)
        	{
        		List<LotteryTerm> currs = (List<LotteryTerm>) lotteryTermDao.createCriteria(
                        Restrictions.eq("current", true),
                        Restrictions.eq("type", type)).list();
        		if(currs.isEmpty())
        		{
        			return null;
        		}
        		else
        		{
        			curr = currs.get(0);
        		}
        	}
        	else
        	{
        		curr = (LotteryTerm) lotteryTermDao.createCriteria(
                        Restrictions.eq("current", true),
                        Restrictions.eq("type", type)).uniqueResult();
                termCache.put(type, curr);
        	}
        }
        return curr;
    }
    
    @SuppressWarnings("unchecked")
    public List<LotteryTerm> getCurrentTermArray(LotteryType type)
    {
        logger.debug("获取当前期列表");
        List<LotteryTerm> list = lotteryTermDao.createCriteria(
                    Restrictions.eq("current", true),
                    Restrictions.eq("type", type)).list();
        return list;
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm findUniqueByProperty(String name, Object value)
    {
        return lotteryTermDao.findUniqueByProperty(name, value);
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<LotteryTerm> getOpenPrizeByDay(Calendar day)
    {
        Criteria criteria = lotteryTermDao.createCriteria();
        criteria.add(Restrictions.gt("stopSaleTime", DateUtil
                .getTheDayZero(day)));
        criteria.add(Restrictions.lt("stopSaleTime", DateUtil
                .getTheDayMidnight(day)));
        criteria.add(Restrictions.not(Restrictions.in("type",
                LotteryType.KuaiKaiTypeMap.values())));
        return criteria.list();
    }

    /** 合买截止 */
    public void stopTogegerSale(LotteryTerm term)
    {
        logger.debug("合买截止");
        term.setTermStatus(TermStatus.合买截止);
        update(term);
    }

    /**
     * 合买截止 合买满员生成订单 1。为满员90%的订单自动补单 2。合买截止 3。加上保底未满员退款 4.撤单退款
     */
    public List<com.xsc.lottery.entity.business.Order> stopToSaleCreateHm(
            LotteryTerm term)
    {
        return orderservice.stopTogegerSale(term);
    }

    /** 开始追号 */
    public List<com.xsc.lottery.entity.business.Order> startChase(
            LotteryTerm term)
    {
        logger.debug(term.toString() + " 开始追号开始生成订单。");
        return orderservice.chaseItem(term);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm getByTypeAndTermNo(String termno, LotteryType type)
    {
        logger.debug("根据期号和彩种获取期数");
        //System.out.println(type);
        return (LotteryTerm) lotteryTermDao.createCriteria(
                Restrictions.eq("termNo", termno),
                Restrictions.eq("type", type)).uniqueResult();
    }

    /**
     * 销售截止 返回下一期
     * */
    public LotteryTerm stopSale(LotteryTerm term)
    {
        logger.debug(" 销售截止");
        LotteryTerm nextTerm = null;
        term.setTermStatus(TermStatus.销售截止);
        term.setCurrent(false);
        termCache.remove(term.getType());
        TermTypeConfig config = this.termConfigDao.findUniqueByProperty("type", term.getType());
        if(config != null && !config.isStop()) {//不停止销售
            // getLotterTermByTypeAndStat(term.getType(), TermStatus.预售中);
            BaseLotteryHandle handle = handleFactory
                    .getLotteryHandleFactory(term.getType());
            nextTerm = handle.getNextTerm(term);
            if (nextTerm != null) {
                nextTerm.setAutoCheckWin(config.isAutoCheckWin());
                nextTerm.setAutoOpen(config.isAutoOpen());
                term.setNextTerm(nextTerm);
                nextTerm.setOutPoint(config.getOutPoint());
                save(nextTerm);
                termCache.put(nextTerm.getType(), nextTerm);// 当前期放入缓存
            }
        }
        
        update(term);
        return nextTerm;
    }

    public void delWinDescribeOrder(LotteryTerm term)
    {
        List<com.xsc.lottery.entity.business.Order> orderList = orderservice
                .getOrderByTerm(term);
        for (com.xsc.lottery.entity.business.Order o : orderList) {
            orderservice.delWinDescribeOrder(o);
            o.setWinMoney(new BigDecimal(0));
            o.setWinTaxMoney(new BigDecimal(0));
            if (o.getOrderResult().equals(OrderResult.已中奖)
                    || o.getOrderResult().equals(OrderResult.未中奖)) {
                o.setOrderResult(OrderResult.未开奖);
            }
            orderservice.update(o);
        }
    }

    public List<PlanItem> getPlanItemList(Plan plan)
    {
        List<PlanItem> list = planItemDao.findByProperty("plan", plan);
        return list;
    }

    /** 本地开奖 */
    public void openPrize(LotteryTerm terms)
    {
        logger.debug("开奖");
        LotteryTerm term = lotteryTermDao.get(terms.getId());
        /*
         * Criteria criteria = orderDao.createCriteria();
         * criteria.add(Restrictions.eq("term", term));
         * criteria.add(Restrictions.or(Restrictions.eq("status",
         * OrderStatus.出票成功), Restrictions.eq("status", OrderStatus.部分出票成功)));
         * List<com.xsc.lottery.entity.business.Order>
         * orderList=criteria.list(); for(com.xsc.lottery.entity.business.Order
         * order:orderList){ List<PlanItem>
         * items=getPlanItemList(order.getPlan());
         * oppenPrizeItems(items,term,order); }
         */
        List<com.xsc.lottery.entity.business.Order> orderList = orderservice
                .getOrderByTerm(term);
        for (com.xsc.lottery.entity.business.Order order : orderList) {
            if (order.getStatus().equals(OrderStatus.出票中))
            {
            	//如果有出票中，不让开奖
               return;
            }
        }
        
        for (com.xsc.lottery.entity.business.Order order : orderList) {
            if (order.getStatus().equals(OrderStatus.出票成功)
                    || order.getStatus().equals(OrderStatus.部分出票成功)) {
                List<PlanItem> items = getPlanItemList(order.getPlan());
                oppenPrizeItems(items, term, order);
            }
        }
        if (term.getTermStatus().equals(TermStatus.未开奖)) {
            term.setTermStatus(TermStatus.已开奖);
        }
        lotteryTermDao.save(term);
    }

    /** 接口返回數據 开奖 */
    public void openPrizeSyncTreatment(LotteryTerm terms)
    {
        LotteryTerm term = lotteryTermDao.get(terms.getId());
        TicketTreatmentWork work = ticketBusinessFactory
                .getTreatmentTicketByType(term.getOutPoint());
		List<winTicketDis> winlist= work.getWinTicketByTerm(term);
        if ( winlist!= null) {
	
			openPrizeByTicketOtherId(winlist);
			String hql = "update Order set order_result="
				+ OrderResult.未中奖.ordinal() + " where term_id=" + term.getId()
				+ " and order_result=" + OrderResult.未开奖.ordinal()
				+ " and status in (" + OrderStatus.出票成功.ordinal() + ","
				+ OrderStatus.部分出票成功.ordinal() + ")";
			Query query = lotteryTermDao.getSession().createQuery(hql);
			query.executeUpdate();
			if (term.getTermStatus().equals(TermStatus.未开奖))
				term.setTermStatus(TermStatus.已开奖);
		} else
		{
			logger.info("nullwinlist");
		}
        lotteryTermDao.save(term);
    }

    public void openPrizeByTicketOtherId(List<winTicketDis> list)
    {
        Map<Long, BigDecimal> oMap = new HashMap<Long, BigDecimal>();
        Map<Long, BigDecimal> oTaxMap = new HashMap<Long, BigDecimal>();
        Map<Long, BigDecimal> addMap = new HashMap<Long, BigDecimal>();//加奖Map
        //是否有加奖活动
        Activity activity = activityService.getActivityByType(ActivityType.加奖);
        if (list != null) {
            for (winTicketDis ticketdis : list)
            {
                com.xsc.lottery.entity.business.Order order = orderservice.findUniqueTicketByProperty("otherOrderID", ticketdis.getOrderId());
                BigDecimal winMoney = ticketdis.getWinMoney();
                BigDecimal taxMoney = ticketdis.getTaxMoney();
                BigDecimal addMoney = new BigDecimal(0.00);//加奖金额
                if (taxMoney.intValue() > 0 && taxMoney.intValue() > 0)
                {
                	//判断是否有加奖活动
                	if(activity!=null)
                	{
                		//计算加奖金额  十一运夺金如下奖等加奖
                    	if(order.getType() == LotteryType.十一运夺金)
                    	{
                    		Ticket ticket = orderservice.getTicketByOtherOrderId(ticketdis.getOrderId());
                    		switch (ticket.getPlayType())
    						{
    							case rx_3:
    								if(winMoney.intValue()%22==0)
    								{
    									
    									int num = winMoney.intValue()/22;
    									addMoney = addBouns(order.getType(),winMoney.subtract(new BigDecimal(3*num)));
    								}
    								else
    								{
    									addMoney = addBouns(order.getType(),winMoney);
    								}
    								break;
    							case rx_4:
    								if(winMoney.intValue()%90==0)
    								{
    									
    									int num = winMoney.intValue()/90;
    									addMoney = addBouns(order.getType(),winMoney.subtract(new BigDecimal(12*num)));
    								}
    								else
    								{
    									addMoney = addBouns(order.getType(),winMoney);
    								}
    								break;
    							case rx_5:
    								if(winMoney.intValue()%600==0)
    								{
    									
    									int num = winMoney.intValue()/600;
    									addMoney = addBouns(order.getType(),winMoney.subtract(new BigDecimal(60*num)));
    								}
    								else
    								{
    									addMoney = addBouns(order.getType(),winMoney);
    								}
    								break;
    							case rx_7:
    								if(winMoney.intValue()%30==0)
    								{
    									
    									int num = winMoney.intValue()/30;
    									addMoney = addBouns(order.getType(),winMoney.subtract(new BigDecimal(4*num)));
    								}
    								else
    								{
    									addMoney = addBouns(order.getType(),winMoney);
    								}
    								break;
    							case q3_zhix:
    								if(winMoney.intValue()%1300==0)
    								{
    									
    									int num = winMoney.intValue()/1300;
    									addMoney = addBouns(order.getType(),winMoney.subtract(new BigDecimal(130*num)));
    								}
    								else
    								{
    									addMoney = addBouns(order.getType(),winMoney);
    								}
    								break;
    							case q3_zux:
    								if(winMoney.intValue()%220==0)
    								{
    									
    									int num = winMoney.intValue()/220;
    									addMoney = addBouns(order.getType(),winMoney.subtract(new BigDecimal(25*num)));
    								}
    								else
    								{
    									addMoney = addBouns(order.getType(),winMoney);
    								}
    								break;
    							default:
    								addMoney = addBouns(order.getType(),winMoney);
    								break;
    						}
                    	}else
                    	{
                    		addMoney = addBouns(order.getType(),winMoney);
                    	}
                    	taxMoney = taxMoney.add(addMoney);//税后金额加上加奖奖金
                	}
                    if (oMap.get(order.getId()) == null)
                    {
                        oMap.put(order.getId(), winMoney);
                        oTaxMap.put(order.getId(), taxMoney);
                        addMap.put(order.getId(), addMoney);
                    }
                    else
                    {
                        oMap.put(order.getId(), oMap.get(order.getId()).add(winMoney));
                        oTaxMap.put(order.getId(), oTaxMap.get(order.getId()).add(taxMoney));
                        addMap.put(order.getId(), addMap.get(order.getId()).add(addMoney));
                    }
                }
            }
            Set<Long> set = oMap.keySet();
            for (Long id : set)
            {
                orderservice.updateOrder(id, oMap.get(id), oTaxMap.get(id));
                //写活动明细
                if(activity!=null)
                {
                	ActivityDetail detail = new ActivityDetail();
                    detail.setActDetailType(ActivityDetailType.加奖);
                    detail.setActivity(activity);
                    detail.setAddmoney(addMap.get(id));
                    detail.setCreateTime(Calendar.getInstance());
                    com.xsc.lottery.entity.business.Order order = orderservice.findById(id);
                    detail.setCustomer(order.getCustomer());
                    detail.setFinished(true);
                    detail.setOrder(order);
                    detail.setPaymentRequest(null);
                    activityService.saveActivityDetail(detail);
                }
            }
        }
    }
    
    /**
     * <pre>
     *  计算加奖奖金(系统加奖)
     * </pre>
     * @param type       彩种
     * @param bounsMoney 奖金
     * @return
     */
    private BigDecimal addBouns(LotteryType type,BigDecimal bounsMoney)
    {
    	BigDecimal rate  = new BigDecimal(0);
    	switch (type)
		{
			case 广西快3:
				rate = BigDecimal.valueOf(Double.valueOf(sysParamService.getSysParamByName(Constants.GXK3_BOUNS_RATE).getValue()));
				break;
			case 十一运夺金:
				rate = BigDecimal.valueOf(Double.valueOf(sysParamService.getSysParamByName(Constants.SD11X5_BOUNS_RATE).getValue()));
				break;
			case 快乐扑克3:
				rate = BigDecimal.valueOf(Double.valueOf(sysParamService.getSysParamByName(Constants.SDKLPK3_BOUNS_RATE).getValue()));
				break;
			case 老11选5:
				rate = BigDecimal.valueOf(Double.valueOf(sysParamService.getSysParamByName(Constants.JX11X5_BOUNS_RATE).getValue()));
				break;
			case 重庆时时彩:
				rate = BigDecimal.valueOf(Double.valueOf(sysParamService.getSysParamByName(Constants.CQSSC_BOUNS_RATE).getValue()));
				break;
			case 上海11选5:
				rate = BigDecimal.valueOf(Double.valueOf(sysParamService.getSysParamByName(Constants.SH11X5_BOUNS_RATE).getValue()));
				break;
			default:
				break;
		}
    	if(rate == null)  rate = new BigDecimal(0);
    	return bounsMoney.multiply(rate);
    }

    public void oppenPrizeItems(List<PlanItem> items, LotteryTerm term,com.xsc.lottery.entity.business.Order order)
            
    {
        logger.debug("开奖(PlanItem List)");
        Map<String, WinDescribeOrder> winDescribeOrderMap = new HashMap<String, WinDescribeOrder>();
        BigDecimal winMoney = new BigDecimal(0);
        BigDecimal winTaxMoney = new BigDecimal(0);
        for (PlanItem item : items) {
            for (Ticket ticket : item.getTicket()) {
            	//add to avoid failed ticket
            	if(ticket.getStatus()== TicketStatus.未送票 || ticket.getStatus()== TicketStatus.出票中 || ticket.getStatus()== TicketStatus.出票失败) continue;
                if (ticket.getOrder().equals(order))
                {
                    List<WinDescribeTicket> winTicketList = handleFactory.getLotteryHandleFactory(term.getType()).checkAllOrderWin(ticket, term.getPrizeLevels());
                    if (winTicketList != null) {
                        for (WinDescribeTicket wticket : winTicketList) {
                            WinDescribeOrder winDescribeOrder = new WinDescribeOrder();
                            winMoney = winMoney.add(wticket.getMoney());
                            winTaxMoney = winTaxMoney.add(wticket.getTaxmoney());
                            wticket.setItem(item);
                            orderservice.saveWinDescribeTicket(wticket);
                            winDescribeOrder.setMoney(wticket.getTaxmoney());
                            winDescribeOrder.setNumber(wticket.getNumber());
                            winDescribeOrder.setOrder(order);
                            if(!LotteryType.KuaiKaiTypeMap.containsKey(term.getType().getName_EN()))
                            {
	                            winDescribeOrder.setPrizeLevel(wticket .getPrizeLevel());
	                            if (winDescribeOrderMap.get(wticket.getPrizeLevel().getName()) != null)
	                            {
	                                winDescribeOrderMap.get(wticket.getPrizeLevel().getName()).setMoney( winDescribeOrderMap.get(wticket.getPrizeLevel().getName()).getMoney().add(winDescribeOrder.getMoney()));
	                                winDescribeOrderMap.get(wticket.getPrizeLevel().getName()).setNumber(winDescribeOrderMap.get( wticket.getPrizeLevel().getName()).getNumber() + winDescribeOrder.getNumber());
	                            }
	                            else
	                            {
	                                winDescribeOrderMap.put(wticket.getPrizeLevel().getName(), winDescribeOrder);
	                                       
	                            }
                            } //ignore 11x5
                        }
                    }
                }
            }
        }
        if(!LotteryType.KuaiKaiTypeMap.containsKey(term.getType().getName_EN()))
        {
	        if (winDescribeOrderMap != null)
	        {
	            Set<String> set = winDescribeOrderMap.keySet();
	            for (String str : set)
	            {
	                orderservice.saveWinDescribeOrder(winDescribeOrderMap.get(str));
	            }
	        }
        } //ingore windescriber order for 11x5
        orderservice.updateOrder(order.getId(), winMoney, winTaxMoney);
    }

    /** 兑奖 */
    public void checkWin(LotteryTerm term)
    {
        logger.debug("兑奖");
        // do派奖
        term = this.findById(term.getId());
        orderservice.checkAllOrderWin(term);
        term.setTermStatus(TermStatus.已兑奖);
        update(term);
    }

    public void deletePrizeLevel(PrizeLevel entity)
    {
        prizeLevelDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm findById(Long id)
    {
        return (LotteryTerm) lotteryTermDao.getSession().get(LotteryTerm.class,
                id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm load(Long id)
    {
        return lotteryTermDao.get(id);
    }

    public LotteryTerm save(LotteryTerm entity)
    {
        lotteryTermDao.save(entity);
        return entity;
    }

    public LotteryTerm update(LotteryTerm entity)
    {
        lotteryTermDao.save(entity);
        return entity;
    }
    
    public void delete(LotteryTerm entity)
    {

    }

    /** （分页）根据查询条件获得分页期次列表——wwl */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<LotteryTerm> getLotteryTermPage(Page<LotteryTerm> page,
            LotteryType type, TermStatus termStatus, Calendar beginTime,
            String termNo)
    {
        logger.debug("根据查询条件获得分页期次列表");
        Criteria criteria = lotteryTermDao.createCriteria();
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (termStatus != null && !termStatus.equals("")) {
            criteria.add(Restrictions.eq("termStatus", termStatus));
        }
        if (beginTime != null && !beginTime.equals("")) {
            criteria.add(Restrictions.eq("openPrizeTime", beginTime));
        }
        if (termNo != null && !termNo.equals("")) {
            criteria.add(Restrictions.eq("termNo", termNo));
        }
        criteria.addOrder(Order.desc("id"));
        page = lotteryTermDao.findByCriteria(page, criteria);
        return page;
    }

    /**
     * @see 分页根据彩期彩种查找历史开奖期数
     * @param page
     * @param type
     * @param termStatus
     * @param beginTime
     * @param termNo
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<LotteryTerm> getHistoryLotteryTermPage(Page<LotteryTerm> page,
            LotteryType type)
    {
        logger.debug("根据查询条件获得分页期次列表");
        Criteria criteria = lotteryTermDao.createCriteria();
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        criteria.add(Restrictions.eq("current", false));
        criteria.add(Property.forName("result").isNotNull());
        criteria.addOrder(Order.desc("id"));
        
        page = lotteryTermDao.findByCriteria(page, criteria);
        return page;
    }

    /** 根据期次获得开奖明细——wwl */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PrizeLevel> getTermByPrizeLevel(LotteryTerm term)
    {
        logger.debug("开奖明细");
        Criteria criteria = prizeLevelDao.createCriteria(Restrictions.eq(
                "term", term));
        criteria.addOrder(Order.asc("id"));
        List<PrizeLevel> list = criteria.list();
        if (list.isEmpty())
            return null;
        return list;

    }

    /** 保存彩期开奖详情——wwl */
    public LotteryTerm saveLotteryTermOrPrizeLevel(LotteryTerm term)
    {
        if (!LotteryType.KuaiKaiTypeMap
                .containsKey(term.getType().getName_EN())) {
            if (term.getPrizeLevels().get(0).getId() == null) {
                List<PrizeLevel> list = getTermByPrizeLevel(term);
                if (null != list) {
                    for (PrizeLevel prize : list) {
                        deletePrizeLevel(prize);
                    }
                }
            }
            for (PrizeLevel prize : term.getPrizeLevels()) {
                prize.setTerm(term);
                prizeLevelDao.save(prize);
            }
        }
        return this.save(term);
    }

    /** 得到各彩种需要开奖期次列表——wwl */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<LotteryTerm> getOpenWinByTypePgae(Page<LotteryTerm> page,
            LotteryType type, String termNo)
    {
        logger.debug("开奖明细");
        Criteria criteria = lotteryTermDao.createCriteria();
        if (!type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (termNo != null && !termNo.trim().equals("")) {
            criteria.add(Restrictions.eq("termNo", termNo));
        }
        criteria.addOrder(Order.desc("type"));
        criteria.addOrder(Order.desc("id"));
        page = lotteryTermDao.findByCriteria(page, criteria);
        return page;
    }

    /** 根据彩种/期次数NUM得到走势图彩期 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<LotteryTerm> getTermByZSMap(LotteryType type, int num)
    {
        Criteria criteria = lotteryTermDao.createCriteria(Restrictions.eq(
                "current", false), Restrictions.eq("type", type));
        criteria.add(Property.forName("result").isNotNull());
        criteria.setMaxResults(num);
        criteria.addOrder(Order.desc("id"));
        List<LotteryTerm> list = criteria.list();
        return list;
    }
    
    /**为手机开奖历史写的分页查询*/
    @SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<LotteryTerm> getTermByZSMapForPhone(LotteryType type, int num, int begin)
    {
        Criteria criteria = lotteryTermDao.createCriteria(Restrictions.eq(
                "current", false), Restrictions.eq("type", type));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Property.forName("result").isNotNull());
        begin = (begin - 1) * num;
        criteria.setFirstResult(begin);
        criteria.setMaxResults(num);
        criteria.addOrder(Order.desc("id"));
        List<LotteryTerm> list = criteria.list();
        return list;
    }

    /** 根据彩种/期次得到走势图彩期 最多查100期 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<LotteryTerm> getTermByZSMap(String type, String startterm,
            String endterm)
    {
        Criteria criteria = lotteryTermDao.createCriteria(Restrictions.eq(
                "current", false), Restrictions.eq("type", LotteryType
                .valueOf(type)));

        if (!StringUtils.isEmpty(startterm)) {
            criteria.add(Restrictions.ge("termNo", startterm));
        }
        if (!StringUtils.isEmpty(startterm)) {
            criteria.add(Restrictions.le("termNo", endterm));
        }
        criteria.addOrder(Order.asc("termNo")).setMaxResults(100);
        List<LotteryTerm> list = criteria.list();
        return list;
    }

    /** 根据彩种/期次得到彩期 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm getLotteryTerm(String termNo, LotteryType type)
    {
        logger.debug("根据彩种/期次得到彩期");
        Criteria criteria = lotteryTermDao.createCriteria(Restrictions.eq(
                "termNo", termNo), Restrictions
                .eq("termStatus", TermStatus.未兑奖));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        List<LotteryTerm> list = criteria.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /** 根据彩种 彩期状态得到小于该状态的期数 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public LotteryTerm getLotteryTermByLtSta(LotteryType type,
            TermStatus termStatus)
    {
        String hql = "from LotteryTerm as model where  model.type=? and model.openPrizeTime>?";
        List<LotteryTerm> list = lotteryTermDao.find(hql, new Object[] { type,
                Calendar.getInstance() });
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<LotteryTerm> getLotteryTermPage(Page<LotteryTerm> page,
            LotteryType type, String fTerm, String status)
    {
        logger.debug("分页获得未兑奖期次");
        Criteria criteria = lotteryTermDao.createCriteria();
        if (!StringUtils.isEmpty(status)) {
            criteria.add(Restrictions.eq("termStatus", TermStatus
                    .valueOf(status)));
        }
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (!StringUtils.isEmpty(fTerm)) {
            criteria.add(Restrictions.eq("termNo", fTerm));
        }
        criteria.addOrder(Order.desc("id"));
        page = lotteryTermDao.findByCriteria(page, criteria);
        return page;
    }

    /**
     * 根据彩种和状态查询彩期
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<LotteryTerm> getLotterTermByTypeAndStat(LotteryType type,
            TermStatus termStatus)
    {
        Criteria criteria = lotteryTermDao.createCriteria();
        criteria.add(Restrictions.eq("type", type));
        criteria.add(Restrictions.eq("termStatus", termStatus));
        criteria.addOrder(Order.desc("termNo"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<String> getTermArray(LotteryType enToType, int num)
    {
        String hql = "select t.termNo from LotteryTerm t where t.type="
                + enToType.ordinal()
                + " and t.current=0 order by t.termNo desc  limit " + num;
        Query query = lotteryTermDao.getSession().createQuery(hql);
        List<String> list = query.list();
        return list;
    }
    
    //FIXME
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<MatchArrange> getMatchResultByTermNo(String termNo) 
	{
    	Criteria criteria = lotteryTermDao.createCriteria();
    	criteria.add(Restrictions.eq("termNo", termNo));
    	criteria.add(Restrictions.eq("type", LotteryType.足彩14场));
    	LotteryTerm lotteryterm = (LotteryTerm) criteria.list().get(0);
    	
    	Criteria criteria2 = matchArrangeDao.createCriteria();
    	criteria2.add(Restrictions.eq("term", lotteryterm));
    	List<MatchArrange> list = criteria2.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<LotteryTerm> getTermResultByTermNo(String termNo) 
	{
    	Criteria criteria = lotteryTermDao.createCriteria();
    	criteria.add(Restrictions.eq("termNo", termNo));
    	criteria.add(Restrictions.eq("type", LotteryType.足彩14场));
    	List<LotteryTerm> list =  criteria.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<MatchArrange> get6CBMatchResultByTermNo(String termNo) 
	{
    	Criteria criteria = lotteryTermDao.createCriteria();
    	criteria.add(Restrictions.eq("termNo", termNo));
    	criteria.add(Restrictions.eq("type", LotteryType.足彩6场半));
    	LotteryTerm lotteryterm = (LotteryTerm) criteria.list().get(0);
    	
    	Criteria criteria2 = matchArrangeDao.createCriteria();
    	criteria2.add(Restrictions.eq("term", lotteryterm));
    	List<MatchArrange> list = criteria2.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<LotteryTerm> getTerm6CBResultByTermNo(String termNo) 
	{
    	Criteria criteria = lotteryTermDao.createCriteria();
    	criteria.add(Restrictions.eq("termNo", termNo));
    	criteria.add(Restrictions.eq("type", LotteryType.足彩6场半));
    	List<LotteryTerm> list =  criteria.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<MatchArrange> get4CJQMatchResultByTermNo(String termNo) 
	{
    	Criteria criteria = lotteryTermDao.createCriteria();
    	criteria.add(Restrictions.eq("termNo", termNo));
    	criteria.add(Restrictions.eq("type", LotteryType.四场进球));
    	LotteryTerm lotteryterm = (LotteryTerm) criteria.list().get(0);
    	
    	Criteria criteria2 = matchArrangeDao.createCriteria();
    	criteria2.add(Restrictions.eq("term", lotteryterm));
    	List<MatchArrange> list = criteria2.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<LotteryTerm> getTerm4CJQResultByTermNo(String termNo) 
	{
    	Criteria criteria = lotteryTermDao.createCriteria();
    	criteria.add(Restrictions.eq("termNo", termNo));
    	criteria.add(Restrictions.eq("type", LotteryType.四场进球));
    	List<LotteryTerm> list =  criteria.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<MatchArrange> getMatchArrayByBoutIndex(String boutIndex) 
	{
    	Criteria criteria = matchArrangeDao.createCriteria();
    	criteria.add(Restrictions.eq("boutIndex", boutIndex));
    	List<MatchArrange> list =  criteria.list();
        return  list;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Ticket> getTicketById(Long ticketid) 
	{
    	Criteria criteria = ticketDao.createCriteria();
    	criteria.add(Restrictions.eq("id",ticketid ));
    	List<Ticket> list =  criteria.list();
        return  list;
    }
    
}
