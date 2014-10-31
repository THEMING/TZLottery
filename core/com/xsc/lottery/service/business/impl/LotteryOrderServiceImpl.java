package com.xsc.lottery.service.business.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
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
import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.NewlyWinPrize;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.business.WinDescribeOrder;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.ChaseItermStatus;
import com.xsc.lottery.entity.enumerate.ChaseStatus;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderResult;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.PlayStatus;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.PloyType;
import com.xsc.lottery.entity.enumerate.TicketSPStatus;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.ntalker.action.TrackTaskExecutor;
import com.xsc.lottery.service.active.ActivityService;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.service.listener.active.ActivityListener;
import com.xsc.lottery.task.email.Email369TaskExcutor;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.SmsUtil;

@Service("lotteryOrderService")
@Transactional
@SuppressWarnings("unchecked")
public class LotteryOrderServiceImpl implements LotteryOrderService
{
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    public PagerHibernateTemplate<Order, Long> orderDao;
    public PagerHibernateTemplate<Customer, Long> customerDao;
    public PagerHibernateTemplate<Ticket, Long> ticketDao;
    public SimpleHibernateTemplate<WinDescribeTicket, Long> winDescribeTicketDao;
    public SimpleHibernateTemplate<WinDescribeOrder, Long> winDescribeOrderDao;
    public SimpleHibernateTemplate<Plan, Long> planDao;
    public SimpleHibernateTemplate<PlanItem, Long> planItemDao;
    public PagerHibernateTemplate<Part, Long> partDao;
    public PagerHibernateTemplate<ChaseItem, Long> chaseItemDao;
    public PagerHibernateTemplate<Chase, Long> chaseDao;
    public PagerHibernateTemplate<NewlyWinPrize, Long> newlyWinPrizeDao;
    public PagerHibernateTemplate<WalletLog, Long> walletLogDao;
    
    // 用于计时，避免重复发送短信 至少5分钟一次
    private Calendar xtTime = Calendar.getInstance();
    @Autowired
    public CustomerService customerService;
    @Autowired
	private SmsLogService smsLogService;
    @Autowired
    public CommunityService communityService;
    @Autowired
    public ChaseService chaseService;
    @Autowired
    public LotteryHandleFactory handleFactory;
    
    @Autowired
    private TrackTaskExecutor trackTaskExecutor;

    @Autowired
    public LotteryTermService termService;

    @Autowired
    public TicketBusinessFactory ticketBusinessFactory;
    
    @Autowired
    private ActivityListener activityListener;
    
    @Autowired
    private Email369TaskExcutor email369TaskExcutor;
    
    @Autowired
    private AdminMobileService adminMobileService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.orderDao = new PagerHibernateTemplate<Order, Long>(sessionfactory,
                Order.class);
        this.customerDao = new PagerHibernateTemplate<Customer, Long>(sessionfactory,
                        Customer.class);
        this.ticketDao = new PagerHibernateTemplate<Ticket, Long>(
                sessionfactory, Ticket.class);
        this.winDescribeTicketDao = new SimpleHibernateTemplate<WinDescribeTicket, Long>(
                sessionfactory, WinDescribeTicket.class);
        this.winDescribeOrderDao = new SimpleHibernateTemplate<WinDescribeOrder, Long>(
                sessionfactory, WinDescribeOrder.class);
        this.planDao = new SimpleHibernateTemplate<Plan, Long>(sessionfactory,
                Plan.class);
        this.planItemDao = new SimpleHibernateTemplate<PlanItem, Long>(
                sessionfactory, PlanItem.class);
        this.partDao = new PagerHibernateTemplate<Part, Long>(sessionfactory,
                Part.class);
        this.chaseItemDao = new PagerHibernateTemplate<ChaseItem, Long>(
                sessionfactory, ChaseItem.class);
        this.chaseDao = new PagerHibernateTemplate<Chase, Long>(sessionfactory,
                Chase.class);
        this.newlyWinPrizeDao = new PagerHibernateTemplate<NewlyWinPrize, Long>(
                sessionfactory, NewlyWinPrize.class);
        this.walletLogDao=new PagerHibernateTemplate<WalletLog, Long>(
                sessionfactory, WalletLog.class);
    }

    /** 根据状态类型 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByStatus(OrderStatus status)
    {
        logger.debug("根据状态类型查找方案");
        return orderDao.findByProperty("status", status);
    }

    /** 彩期合买截止 */
    public List<Order> stopTogegerSale(LotteryTerm term)
    {
        List<Order> resultList = new ArrayList<Order>();
        List<Community> list = communityService.stopTogegerSale(term);
        for (Community community : list) {
            Order order = new Order(community);
            order.setLastMatch(community.getLastMatch());
            orderDao.save(order);
            resultList.add(order);
            
           // activityListener.addNewOrder(order);
        }
        return resultList;
    }
    
    /** 竞彩合买截止 */
    public List<Order> stopTogegerSale(MatchArrange match)
    {
        List<Order> resultList = new ArrayList<Order>();
        List<Community> list = communityService.stopTogegerSale(match);
        for (Community community : list) {
            Order order = new Order(community);
            orderDao.save(order);
            resultList.add(order);
        }
        return resultList;
    }

    /**
     * @return 参与合买,满员的话产生的注单
     * @throws CommunityException
     *             超出合买总份数
     * @throws NotEnoughBalanceException
     *             用户余额不足
     */
    public Order joinCommunity(Long communityId, Customer customer, int partNum)
            throws Exception
    {
        synchronized (Long.valueOf(communityId)) {
            Community community = communityService.addPart(communityId,
                    customer, partNum);
            if (community.getCommunityType().equals(CommunityType.已满员)) {
                Order order = new Order(community);
                orderDao.save(order);
                
                activityListener.addNewOrder(order);
                return order;
            }
            return null;
        }
    }
   
    public Order joinCommunity(Long communityId, Customer customer, int partNum, Cookie cookie)
    throws Exception
	{
		synchronized (Long.valueOf(communityId)) {
	    	Community community = communityService.findById(communityId);
	    	if(!community.getCommunityType().equals(CommunityType.已满员))
	    	{
			    community = communityService.addPart(communityId,
			            customer, partNum, cookie);
	    	}
		    if (community.getCommunityType().equals(CommunityType.已满员)) {
		        Order order = new Order(community);
		        orderDao.save(order);
		        activityListener.addNewOrder(order);
		        return order;
		    }
		    return null;
		}
	}


    /** 本期开始追号 */
    public List<Order> chaseItem(LotteryTerm term)
    {
    	System.out.println("开始追号期："+term.getTermNo()+"-玩法："+term.getType().getName_EN());
        List<Order> resultList = new ArrayList<Order>();
        List<ChaseItem> list = chaseService.getChaseItemByTypeAndTerm(term, ChaseItermStatus.待追号, ChaseStatus.追号中);
        for (ChaseItem chaseItem : list) {
        	System.out.println("本期追号id："+chaseItem.getId());
            Chase chase = chaseItem.getChase();
            Order order = new Order(chaseItem, term);
            order.setPloyType(chase.getPloyType());
            orderDao.save(order);
            System.out.println("本期追号订单id："+order.getId());
          //  activityListener.addNewOrder(order);//为活动添加ORDER
            if (chase.getPloyType().equals(PloyType.常规)) {
                WalletLog walletLog = new WalletLog(BusinessType.支出,
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                        chaseItem.getMoney(), order.getPlan().getType().name(),
                        WalletLogType.追号费用, order.getPlan().getNumberNo());
                customerService.addWalletLog(chaseItem.getCustomer()
                        .getWallet().getId(), walletLog);
            }
            //add to orderqueue
            TicketTreatmentWork ttw = ticketBusinessFactory
        	.getTreatmentTicketByType(order.getTerm().getOutPoint());
            ttw.putOrderToQueue(order);
            
            chaseItem.setStatus(ChaseItermStatus.已追号);
            chaseItem.setOrder(order);
            chaseService.saveChaseItem(chaseItem);
            chase.addOktermNum(1);
            chaseService.save(chase);
            resultList.add(order);
        }
        return resultList;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Order findUniqueTicketByProperty(String name, Object value)
    {
        String hql = "select o from Order o,Ticket t where o.id=t.order.id and t.otherOrderID=:value";
        Query query = orderDao.getSession().createQuery(hql);
        query.setParameter("value", value);
        List<Order> list = query.list();
        return list.get(0);
    }

    public void delWinDescribeOrder(Order order)
    {
        Query query = winDescribeOrderDao.getSession().createQuery(
                "delete from WinDescribeOrder where order_id=" + order.getId());
        query.executeUpdate();
    }

    /** 获得注单的所有票 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Ticket> getTicketByOrder(Order order)
    {
        return ticketDao.findByProperty("order", order);
    }

    /** 根据彩种获得彩期下的所有注单 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByTerm(LotteryTerm term)
    {
        return orderDao.findByCriteria(Restrictions.eq("term", term),Restrictions.eq("type", term.getType()));
    }

    /** 彩期全部注单兑奖 */
    public void checkAllOrderWin(LotteryTerm term)
    {
        Criteria criteria = orderDao.createCriteria();
        criteria.add(Restrictions.eq("term", term));
        criteria.add(Restrictions.or(Restrictions.eq("status", OrderStatus.出票成功), Restrictions.eq("status",OrderStatus.部分出票成功)));
        criteria.add(Restrictions.eq("orderResult", OrderResult.已中奖));
        List<Order> orders = criteria.list();
        for (Order order : orders) {
        	if(order.getOrderResult().equals(OrderResult.已兑奖)) {
        		continue;
        	}
            if (order.getCommunity() == null)
                paijiang(term, order);
            else {
                communityService.returnMoney(order.getCommunity(), order.getWinTaxMoney(), WalletLogType.账户返奖, term.getType().name() + order.getTerm().getTermNo() + "期合买返奖");

            }
            order.setOrderResult(OrderResult.已兑奖);
            orderDao.save(order);
            
            if(order.getWinMoney().compareTo(new BigDecimal("10000"))<0 && order.getWinMoney().compareTo(new BigDecimal("6"))>=0)
            {
			        if (order.getCustomer().getMobileNo() != null && order.getCustomer().getMobileNo() != "") {
			        	String content ="【一彩票网】"+order.getCustomer().getNickName()+",恭喜您!";
			            content += "您的订单号为" + order.getPlan().getNumberNo() + "的订单喜中";
			            content += ""+order.getWinMoney().longValue()+"元。乘胜追击http://m.yicp.com";
			            smsLogService.saveSmsLog(order.getCustomer().getMobileNo(), content, order.getCustomer().getId(),SmsLogType.NOTICE);
            	    	//messageTaskExcutor.addWinCustomer(order);
				      }
            }
            email369TaskExcutor.addNotifyWin(order);
      
        }
    }

    /**
     * 兑奖 并 生成中奖排行榜
     */
    public void paijiang(LotteryTerm term, Order order)
    {
        String disinfo;
        NewlyWinPrize newwin;
        if (order.getChaseItem() != null) {
            disinfo = term.getType().name() + order.getTerm().getTermNo()+"期追号返奖";
            newwin = new NewlyWinPrize(order.getTerm().getTermNo(), term.getType(), order.getCustomer().getNickName(), order.getWinMoney(), order.getPlan().getNumberNo(),PlayStatus.追号, order.getBuildTime(), order.getOutAmount());
        } 
        else {
            disinfo = term.getType().name() + order.getTerm().getTermNo() + "期代购返奖";
            newwin = new NewlyWinPrize(order.getTerm().getTermNo(), term.getType(), order.getCustomer().getNickName(), order.getWinMoney(), order.getPlan().getNumberNo(),PlayStatus.代购, order.getBuildTime(), order.getOutAmount());
        }
        
        WalletLog walletLog = new WalletLog(BusinessType.收入, order.getWinTaxMoney(), BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO, disinfo, WalletLogType.账户返奖, order.getPlan().getNumberNo());
                        
        customerService.addWalletLog(order.getCustomer().getWallet().getId(), walletLog);
               
        customerService.saveNewlyWinPrize(newwin);
    }

    /**
     * 计算大赢家返回奖金
     */
    public boolean isJiangjin(Order order)
    {
        TicketTreatmentWork ttw = ticketBusinessFactory
                .getTreatmentTicketByType(order.getTerm().getOutPoint());
        BigDecimal money = new BigDecimal(0);
        BigDecimal txtMoney = new BigDecimal(0);
        List<Ticket> list = getTicketList(order);
        for (Ticket ticket : list) {
            String strMoney = ttw.getJiangjin(ticket);
            String[] arryMoney = strMoney.split("_");
            if (arryMoney.length > 1) {
                money = money.add(new BigDecimal(arryMoney[1]));
                txtMoney = txtMoney.add(new BigDecimal(arryMoney[2]));
            }
        }

        if (order.getWinMoney().equals(money)
                && order.getWinTaxMoney().equals(txtMoney)) {
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Ticket> getTicketList(Order order)
    {
        List<Ticket> list = ticketDao.findByCriteria(Restrictions.eq("status",
                TicketStatus.已出票), Restrictions.eq("order", order));
        return list;
    }

    /** 按订单注单兑奖 */
    public void checkOrderWin(Order order)
    {
    	if(order.getOrderResult().equals(OrderResult.已兑奖)) {
    		return;
    	}
        if (order.getStatus().equals(OrderStatus.出票成功)
                || order.getStatus().equals(OrderStatus.部分出票成功)) {
            if (order.getOrderResult().equals(OrderResult.已中奖)) {
                if (order.getCommunity() == null) {
                    paijiang(order.getTerm(), order);
                }
                else {
                    communityService.returnMoney(order.getCommunity(), order
                            .getWinTaxMoney(), WalletLogType.账户返奖, order
                            .getTerm().getType().name()
                            + order.getTerm().getTermNo() + "期合买返奖");
                }
                order.setOrderResult(OrderResult.已兑奖);
                orderDao.save(order);
            }
        }
    }

    public void delete(Order entity)
    {
        orderDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Order findById(Long id)
    {
        return (Order) orderDao.getSession().get(Order.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Order load(Long id)
    {
        return orderDao.get(id);
    }

    public void merge(Order entity)
    {
        orderDao.getSession().saveOrUpdate(entity);
    }

    public Order save(Order entity)
    {
        orderDao.save(entity);
        return entity;
    }

    public Order update(Order entity)
    {
        orderDao.save(entity);
        return entity;
    }

    public WinDescribeTicket saveWinDescribeTicket(WinDescribeTicket entity)
    {
        winDescribeTicketDao.save(entity);
        return entity;
    }

    public WinDescribeOrder saveWinDescribeOrder(WinDescribeOrder entity)
    {
        winDescribeOrderDao.save(entity);
        return entity;
    }

    /** 保存拆票成功结果 */
    /**
     * @param order
     * @param tickets
     *            所拆得的票
     * @param ticketThirdName
     *            出票点
     */
    public void saveSuccessTakeTicket(Order order, List<Ticket> tickets,
            String ticketThirdName)
    {
        for (Ticket t : tickets) {
            ticketDao.save(t);
        }
        order.setStatus(OrderStatus.待出票);
        order.setTicketThirdName(ticketThirdName);
        orderDao.save(order);
    }

    /** 根据订单状态及票务点查找 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByStatusAndTicketBusinessName(
            OrderStatus status, String ticketThirdName)
    {
        return orderDao.findByCriteria(Restrictions.eq("status", status),
                Restrictions.eq("ticketThirdName", ticketThirdName));
    }

    public void saveTicket(Ticket ticket)
    {
        ticketDao.save(ticket);        
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Ticket findByIdTicket(Long id)
    {
        return (Ticket) ticketDao.getSession().get(Ticket.class, id);
    }
	public List<WalletLog> findNtalkerWalletlogbyorder(Order order)
    {
    	 String plannumber=order.getPlan().getNumberNo();
    	 List<WalletLog> walletloglist;
    	 Criteria criteria =walletLogDao.createCriteria();
    	 criteria.add(Restrictions.eq("serialNumber", plannumber));
    	 criteria.add(Restrictions.isNotNull("pid"));
		 criteria.add(Restrictions.ne("cpsStaus", "finish"));
    	 walletloglist=criteria.list() ;
       	 return walletloglist;
    }
	public List<WalletLog> finddWalletLog(String pid ,String date,String Staus)
    {      //pid not used
    	   String sql="from WalletLog w  where date_format(w.time, '%Y-%m-%d')=? and w.adid=? and w.cpsStaus=?";
           List<WalletLog> walletloglist = walletLogDao.createQuery(sql)
           .setParameter(0, date).setParameter(1, pid).setParameter(2, Staus).list();
    	return walletloglist;
    }
	public List<WalletLog> finddWalletLog1(String pid ,String date,String Staus)
    {    
    	//pid not used
    	   String sql="from WalletLog w  where date_format(w.time, '%Y%m%d')=? and w.pid is not null and w.cpsStaus=?";
           List<WalletLog> walletloglist = walletLogDao.createQuery(sql)
           .setParameter(0, date).setParameter(1, Staus).list();
    	return walletloglist;
    }
    public void setNtalkerLogFinish(Order order){
	   List<WalletLog> walletlogList;
	   walletlogList=findNtalkerWalletlogbyorder(order);
	   if(walletlogList!=null){
		  for(WalletLog walletLog:walletlogList){
		  	
		  	trackTaskExecutor.addTrack(walletLog);//send to ntalker
		  	walletLog.setCpsStaus("finish");
	      }	
	   }	 
    }
    
   /** 完成第三方票务交易后调用 */
    public void finishTicketBusiness(Order order, List<Ticket> returnTickets)
    {
        if (returnTickets.isEmpty()) {
            order.setStatus(OrderStatus.出票成功);
            order.setOutAmount(order.getAmount());
            order.setSuccessTime(Calendar.getInstance());
			setNtalkerLogFinish(order);
        }
        else {
            BigDecimal returnMoney = BigDecimal.ZERO;
            for (Ticket ticket : returnTickets) {
                returnMoney = returnMoney.add(ticket.getMoney());
            }
            
            if (returnMoney.longValue() < order.getAmount().longValue()) {
                order.setStatus(OrderStatus.部分出票成功);
                order.setSuccessTime(Calendar.getInstance());
                BigDecimal outAmount = order.getAmount().subtract(returnMoney);
                order.setOutAmount(outAmount);
            } 
            else {
                order.setStatus(OrderStatus.出票失败);
                order.setOrderResult(OrderResult.作废);
                
            }
            // 以下为退款
            if (order.getCommunity() == null) {
                WalletLogType logtype = WalletLogType.代购退款;
                if (null != order.getChaseItem()) {
                    logtype = WalletLogType.追号退款;
                }
                WalletLog walletLog = new WalletLog(BusinessType.收入,
                        returnMoney, BigDecimal.ZERO, BigDecimal.ZERO,
                        BigDecimal.ZERO, order.getTerm() + "未出票失败退款", logtype,
                        order.getPlan().getNumberNo());
                customerService.addWalletLog(order.getCustomer().getWallet()
                        .getId(), walletLog);
            } 
            else {
                // 合买退款
                communityService.returnMoney(order.getCommunity(), returnMoney,
                        WalletLogType.合买退款, order.getTerm() + "合买未出票失败退款");
            }
        }
        orderDao.save(order);
        
        if((order.getStatus()== OrderStatus.出票失败)||(order.getStatus()==OrderStatus.部分出票成功))
        {
	        if (order.getCustomer().getMobileNo() != null && order.getCustomer().getMobileNo() != "") {
	        	String content = "【一彩票网】您好：";
		    	content += "您的购买的" + order.getTerm().getTermNo() + "期" + order.getType() + "，订单号为：" + order.getPlan().getNumberNo() + "的订单，出票失败！";
		    	content += "对此表示抱歉，为了避免您的损失，请重新购彩！";
//		    	smsLogService.saveSmsLog(order.getCustomer().getMobileNo(), content, order.getCustomer().getId(),SmsLogType.NOTICE);
		    	Map result = SmsUtil.sendSms(order.getCustomer().getMobileNo(), new String[]{order.getTerm().getTermNo(),order.getType().name(),order.getPlan().getNumberNo()},Configuration.getInstance().getValue("payfailTemplateIDYUN"));
	    		if("000000".equals(result.get("statusCode"))){//发送成功
	    			smsLogService.saveSmsLogAndSendState(order.getCustomer().getMobileNo(), content, order.getCustomer().getId(),SmsLogType.NOTICE,SmsLogState.SENDED,"");
	    		}else{
	    			smsLogService.saveSmsLogAndSendState(order.getCustomer().getMobileNo(), content, order.getCustomer().getId(),SmsLogType.NOTICE,SmsLogState.FAILURE,"错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
	    		}
//	        	messageTaskExcutor.adderror(order);
	        	
	    		xtTime.add(Calendar.MINUTE, 5);
				Calendar c = Calendar.getInstance();
				if (xtTime.compareTo(c) < 0) {
					
					String str = "【一彩票网】用户名为：" + order.getCustomer().getNickName() + "购买的" + order.getTerm().getTermNo() + "期" + order.getType() + "，订单号为：" + order.getNumberNo() + "的订单，出票失败！";
					System.out.println("5分钟一次：" + c.getTime());
					List<AdminMobile> adminMobiles = adminMobileService.getAllAdminMobile();
					for (AdminMobile adminMobile : adminMobiles) {
						smsLogService.saveSmsLog(adminMobile.getMobile(), str, null,SmsLogType.WARN);
						//messageTaskExcutor.addNotifySM(adminMobile.getMobile(), str);
					}
					xtTime = c;
					xtTime.add(Calendar.MINUTE, 5);
				}
	        }
        }  
    }

    public Plan createPlanAndItem(Plan plan, List<PlanItem> items)
    {
        logger.debug("生成方案!");
        planDao.save(plan);
        for (PlanItem planItem : items) {
            planItem.setPlan(plan);
            planItemDao.save(planItem);
        }
        return plan;
    }

    /**
     * 代购生成订单
     * **/
    public Order createBetOrder(Customer customer, LotteryType type, Plan plan,
            List<PlanItem> items, LotteryTerm term, int multiple)
    {
        logger.debug("代购生成订单!");
        Plan plans = createPlanAndItem(plan, items);
        Order order = new Order(plans, term, customer, multiple);
        order.setAmount(plan.getMoney().multiply(new BigDecimal(multiple)));
        order.setBuildTime(Calendar.getInstance());
        order.setType(type);
        orderDao.save(order);
       
       	activityListener.addNewOrder(order);
       
        return order;
    }

    /** 按条件搜索分页中奖注单列表 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Order> getOrderPage(Page<Order> page, LotteryType type,
            String customer, LotteryTerm term)
    {
        logger.debug("按条件搜索分页中奖注单列表!");
        Criteria criteria = orderDao.createCriteria();
        criteria.add(Restrictions.eq("orderResult", OrderResult.已中奖));
        criteria.add(Restrictions.eq("term", term));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (!StringUtils.isEmpty(customer)) {
            criteria.createAlias("customer", "customer").add(
                    Restrictions.eq("customer.nickName", customer));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = orderDao.findByCriteria(page, criteria);
        return page;
    }
    /**
     * 兑奖管理彩种已中奖统计
     */
	public BigDecimal getSumWinMoney(LotteryType type,String customer, LotteryTerm term){
    	logger.debug("兑奖管理，统计已中奖金额!");
    	 Criteria criteria= orderDao.getSession().createCriteria(Order.class)
          .setProjection(Projections.projectionList()
          .add(Projections.sum("winMoney"))
        );
        criteria.add(Restrictions.eq("orderResult", OrderResult.已中奖));
        criteria.add(Restrictions.eq("term", term));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (!StringUtils.isEmpty(customer)) {
            criteria.createAlias("customer", "customer").add(
                    Restrictions.eq("customer.nickName", customer));
        }
        List list=criteria.list();
        Object objs=(Object)list.get(0);
        BigDecimal bd=new BigDecimal(0.00);
        if(objs!=null){
        	bd=(BigDecimal)objs;
        }
    	return bd;
    }
    
    /** 按条件搜索分页竟彩注单列表*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Order> getJCOrderPage(LotteryType type, Page<Order> page, Calendar startTime, Calendar overTime, String status)
    {
        logger.debug("按条件搜索分页竟彩篮球注单列表!");
        Criteria criteria = orderDao.createCriteria();
        //System.out.println(OrderResult.enToType(status));
        OrderResult statusTemp = OrderResult.enToType(status);
        criteria.add(Restrictions.eq("orderResult", statusTemp));
        if(startTime != null)
        {
        	criteria.add(Restrictions.ge("buildTime", startTime));
        }
        if(overTime != null)
        {
        	criteria.add(Restrictions.le("buildTime", overTime));
        }
        criteria.add(Restrictions.eq("type", type));       
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = orderDao.findByCriteria(page, criteria);
        return page;
    }
    
    /** 中奖查询 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<NewlyWinPrize> getWinPrize(Page<NewlyWinPrize> page,
            LotteryType type, Calendar beginTime, Calendar endTime,
            Customer customer, String playStatus)
    {
        logger.debug("中奖查询列表!");
        Criteria criteria = newlyWinPrizeDao.createCriteria();
        criteria.add(Restrictions.eq("useName", customer.getNickName()));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (beginTime != null) {
            criteria.add(Restrictions.ge("winTime", beginTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("winTime", endTime));
        }
        if (!StringUtils.isEmpty(playStatus)) {
            criteria.add(Restrictions.eq("playStatus", PlayStatus
                    .valueOf(playStatus)));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("winTime"));
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = newlyWinPrizeDao.findByCriteria(page, criteria);
        return page;
    }

    /** 我参与的合买查询 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Part> getParticipateSale(Page<Part> page, LotteryType type,
            String status, Calendar beginTime, Calendar endTime,
            Customer customer)
    {
        logger.debug("我参与的合买查询!");
        Criteria criteria = partDao.createCriteria(Restrictions.eq("customer",
                customer));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.createAlias("community", "community").createAlias(
                    "community.term", "term").add(
                    Restrictions.eq("term.type", type));
        }
        if (status != null && !status.trim().equals("")) {
            criteria.createAlias("community", "community").add(
                    Restrictions.eq("community.communityType", CommunityType
                            .valueOf(status)));
        }
        if (beginTime != null) {
            criteria.add(Restrictions.ge("joinTime", beginTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("joinTime", endTime));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("joinTime"));
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        page = partDao.findByCriteria(page, criteria);
        return page;
    }

    /** 我的代购查询 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Order> getToBuy(Page<Order> page, LotteryType type,
            String status, Calendar beginTime, Calendar endTime,
            Customer customer)
    {
        logger.debug("我的代购查询!");
        Criteria criteria = orderDao.createCriteria(Restrictions
                .isNull("chaseItem"), Restrictions.isNull("community"),
                Restrictions.eq("customer", customer));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (status != null && !status.trim().equals("")) {
            criteria.add(Restrictions.eq("status", OrderStatus.valueOf(status)));
        }
        if (beginTime != null) {
            criteria.add(Restrictions.ge("buildTime", beginTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("buildTime"));
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        page = orderDao.findByCriteria(page, criteria);
        return page;
    }

    /** 我的追号查询 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<ChaseItem> getChaseItemSale(Page<ChaseItem> page,
            LotteryType type, Customer customer, String status, String termNo)
    {
        logger.debug("我的追号查询!");
        Criteria criteria = chaseItemDao.createCriteria(Restrictions.eq(
                "customer", customer));
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (!StringUtils.isEmpty(termNo)) {
            criteria.add(Restrictions.eq("termNo", termNo));
        }
        if (!StringUtils.isEmpty(status)) {
            criteria
                    .add(Restrictions.eq("status", ChaseStatus.valueOf(status)));
        }
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        page = chaseItemDao.findByCriteria(page, criteria);
        return page;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrder(Customer customer, LotteryType type)
    {
        logger.debug("按用户和彩种查询所有订单!");
        Criteria criteria = orderDao.createCriteria();
        if (customer != null) {
            criteria.add(Restrictions.eq("customer", customer));
        }
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        return criteria.list();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getCoollect(LotteryType type, Calendar stratTime,
            Calendar endTime)
    {
        logger.debug("按用户和彩种查询所有订单!");
        Criteria criteria = orderDao.createCriteria();
        if (type != null && !type.equals(LotteryType.全部)) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (stratTime != null) {
            criteria.add(Restrictions.ge("buildTime", stratTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        return criteria.list();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByNotCustomer(Customer customer)
    {
        logger.debug("查询不是该用户发起的合买订单!");
        Criteria criteria = orderDao.createCriteria();
        criteria.add(Restrictions.isNotNull("community"));
        if (customer != null) {
            criteria.add(Restrictions
                    .not(Restrictions.eq("customer", customer)));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        return criteria.list();
    }

    public void updateOrder(Long id, BigDecimal winMoney, BigDecimal winTaxMoney)
    {
        Order order = (Order) orderDao.getSession().get(Order.class, id);
        if (winMoney.intValue() > 0 && winTaxMoney.intValue() > 0) {
            order.setWinMoney(winMoney);
            order.setWinTaxMoney(winTaxMoney);
            order.setOrderResult(OrderResult.已中奖);
            if (order.getChaseItem() != null) {
                Chase chase = chaseService.findById(order.getChaseItem()
                        .getChase().getId());
                chase.setWinprize(true);
                //修复追号时Chase.addWinTaxMoney(winTaxMoney) this.winTaxMoney报空指针异常
                if(chase.getWinTaxMoney() == null)
                {
                	chase.setWinTaxMoney(winTaxMoney);
                }
                else
                {
                	chase.addWinTaxMoney(winTaxMoney);
                }
                chaseService.update(chase);
            }
        } else {
            order.setWinMoney(new BigDecimal(0));
            order.setWinTaxMoney(new BigDecimal(0));
            order.setOrderResult(OrderResult.未中奖);
        }
        orderDao.save(order);
    }

    /**
     * 根据合买方案得到Order方案
     * 
     * @param community
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Order getOrderByCommunity(Community community)
    {
        List<Order> orders = orderDao.findByCriteria(Restrictions.eq(
                "community", community));
        if (orders != null && orders.size() > 0) {
            return orders.get(0);
        }
        return new Order();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public ChaseItem findByIdChaseItem(Long id)
    {
        return (ChaseItem) chaseItemDao.getSession().get(ChaseItem.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Order getOrderByChaseItem(ChaseItem chase)
    {
        List<Order> orders = orderDao.findByCriteria(Restrictions.eq(
                "chaseItem", chase));
        if (orders != null && orders.size() > 0) {
            return orders.get(0);
        }
        return new Order();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ChaseItem> getChaseItemByPlan(Plan plan)
    {
        List<ChaseItem> list = chaseItemDao.findByCriteria(Restrictions.eq(
                "plan", plan));
        return list;
    }

    public ChaseItem updateChaseItem(ChaseItem chase)
    {
        chaseItemDao.save(chase);
        return chase;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Long> getCustomerId(Calendar stratTime, Calendar endTime)
    {
        String sql = "select o.customer.id from Order o where 1=1 ";
        if (stratTime != null) {
            sql += " and o.buildTime>='" + DateUtil.toTimeStampFm(stratTime)
                    + "'";
        }
        if (endTime != null) {
            sql += " and o.buildTime<='" + DateUtil.toTimeStampFm(endTime)
                    + "'";
        }
        sql += " group by o.customer.id";
        Query query = orderDao.getSession().createQuery(sql);
        List<Long> list = query.list();
        return list;
    }

    /**
     * 购彩查询
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Order> getOrderPage(Page<Order> page, String fSerch,
            String fValue, String fType, String fsStatu, String frStatu,
            String fStyle, Calendar fStime, Calendar fEtime, String fSerchTerm,
            String fSerchTerm1, String fWinTaxMoney1, String fWinTaxMoney2,
            String fAmount1, String fAmount2, Calendar regStime,
            Calendar regEtime, UserType userType,String ticketThirdName)
    {
        logger.debug("管理后台购彩查询!");
        Criteria criteria = orderDao.createCriteria();
        if (fStyle != null) {
            if (fStyle.equals("代购")) {
                criteria.add(Restrictions.and(Restrictions.isNull("community"),
                        Restrictions.isNull("chaseItem")));
            }
            if (fStyle.equals("追号")) {
                criteria.createAlias("chaseItem", "chaseItem").add(
                        Restrictions.isNotNull("chaseItem"));
            }
            if (fStyle.equals("合买")) {
                criteria.add(Restrictions.isNotNull("community"));
            }
        }

        if (!StringUtils.isEmpty(fSerch) || regStime != null
                || regEtime != null || userType != null) {
            criteria.createAlias("customer", "customer");
            criteria.createAlias("plan", "plan");
            if (regStime != null) {
                criteria.add(Restrictions.ge("customer.registerTime", regStime));
            }
            if (regEtime != null) {
                criteria.add(Restrictions.le("customer.registerTime", regEtime));
            }
            if (userType != null) {
            	criteria.add(Restrictions.eq("customer.usrType", userType));
            }
            if (fSerch != null && fSerch.equals("用户名")) {
                criteria.add(Restrictions.eq("customer.nickName", fValue));
            }
            if (fSerch != null && fSerch.equals("订单号")) {
                criteria.add(Restrictions.eq("plan.numberNo", fValue));
            }
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("type", LotteryType.valueOf(fType)));
        }

        if (!StringUtils.isEmpty(fsStatu)) {
            criteria.add(Restrictions.eq("status", OrderStatus.valueOf(fsStatu)));
        }

        if (!StringUtils.isEmpty(frStatu)) {
            criteria.add(Restrictions.eq("orderResult", OrderResult.valueOf(frStatu)));
        }
        if (!StringUtils.isEmpty(fSerchTerm)
                || !StringUtils.isEmpty(fSerchTerm1)) {
            criteria.createAlias("term", "term");
            if (!StringUtils.isEmpty(fSerchTerm)) {
                criteria.add(Restrictions.ge("term.termNo", fSerchTerm));
            }
            if (!StringUtils.isEmpty(fSerchTerm1)) {
                criteria.add(Restrictions.le("term.termNo", fSerchTerm1));
            }
        }
        if (!StringUtils.isEmpty(fWinTaxMoney1)) {
            criteria.add(Restrictions.ge("winTaxMoney", new BigDecimal(
                    fWinTaxMoney1)));
        }
        if (!StringUtils.isEmpty(fWinTaxMoney2)) {
            criteria.add(Restrictions.le("winTaxMoney", new BigDecimal(
                    fWinTaxMoney2)));
        }

        if (!StringUtils.isEmpty(fAmount1)) {
            criteria.add(Restrictions.ge("amount", new BigDecimal(fAmount1)));
        }
        if (!StringUtils.isEmpty(fAmount2)) {
            criteria.add(Restrictions.le("amount", new BigDecimal(fAmount2)));
        }

        if (fStime != null) {
            criteria.add(Restrictions.ge("buildTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("buildTime", fEtime));
        }
        
        if(!StringUtils.isEmpty(ticketThirdName)){
        	criteria.add(Restrictions.eq("ticketThirdName", ticketThirdName));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("buildTime"));
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = orderDao.findByCriteria(page, criteria);
        return page;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrder(String fSerch, String fValue, String fType,
            String fSstatu, String fRstatu, String fStyle, Calendar fStime,
            Calendar fEtime, String fSerchTerm, String fSerchTerm1,
            String fWinTaxMoney1, String fWinTaxMoney2, String fAmount1,
            String fAmount2, Calendar regStime, Calendar regEtime,UserType userType,String ticketThirdName)
    {
        logger.debug("管理后台购彩统计!");
        Criteria criteria = orderDao.createCriteria();
        if (fStyle != null) {
            if (fStyle.equals("代购")) {
                criteria.add(Restrictions.and(Restrictions.isNull("community"),
                        Restrictions.isNull("chaseItem")));
            }
            if (fStyle.equals("追号")) {
                criteria.createAlias("chaseItem", "chaseItem").add(
                        Restrictions.isNotNull("chaseItem"));
            }
            if (fStyle.equals("合买")) {
                criteria.add(Restrictions.isNotNull("community"));
            }
        }

        if (!StringUtils.isEmpty(fSerch) || regStime != null
                || regEtime != null || userType != null) {
            criteria.createAlias("customer", "customer");
            criteria.createAlias("plan", "plan");
            if (regStime != null) {
                criteria
                        .add(Restrictions.ge("customer.registerTime", regStime));
            }
            if (regEtime != null) {
                criteria
                        .add(Restrictions.le("customer.registerTime", regEtime));
            }
            if (userType != null) {
            	criteria.add(Restrictions.eq("customer.usrType", userType));
            }
            if (fSerch.equals("用户名")) {
                criteria.add(Restrictions.eq("customer.nickName", fValue));
            }
            if (fSerch.equals("订单号")) {
                criteria.add(Restrictions.eq("plan.numberNo", fValue));
            }
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("type", LotteryType.valueOf(fType)));
        }

        if (!StringUtils.isEmpty(fSstatu)) {
            criteria.add(Restrictions
                    .eq("status", OrderStatus.valueOf(fSstatu)));
        }

        if (!StringUtils.isEmpty(fRstatu)) {
            criteria.add(Restrictions.eq("orderResult", OrderResult
                    .valueOf(fRstatu)));
        }
        if (!StringUtils.isEmpty(fSerchTerm)
                || !StringUtils.isEmpty(fSerchTerm1)) {
            criteria.createAlias("term", "term");
            if (!StringUtils.isEmpty(fSerchTerm)) {
                criteria.add(Restrictions.ge("term.termNo", fSerchTerm));
            }
            if (!StringUtils.isEmpty(fSerchTerm1)) {
                criteria.add(Restrictions.le("term.termNo", fSerchTerm1));
            }
        }
        if (!StringUtils.isEmpty(fWinTaxMoney1)) {
            criteria.add(Restrictions.ge("winTaxMoney", new BigDecimal(
                    fWinTaxMoney1)));
        }
        if (!StringUtils.isEmpty(fWinTaxMoney2)) {
            criteria.add(Restrictions.le("winTaxMoney", new BigDecimal(
                    fWinTaxMoney2)));
        }

        if (!StringUtils.isEmpty(fAmount1)) {
            criteria.add(Restrictions.ge("amount", new BigDecimal(fAmount1)));
        }
        if (!StringUtils.isEmpty(fAmount2)) {
            criteria.add(Restrictions.le("amount", new BigDecimal(fAmount2)));
        }

        if (fStime != null) {
            criteria.add(Restrictions.ge("buildTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("buildTime", fEtime));
        }
        
        if(!StringUtils.isEmpty(ticketThirdName)){
        	criteria.add(Restrictions.eq("ticketThirdName", ticketThirdName));
        }
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        List<Order> list = criteria.list();
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Part> getParticipateSale(Page<Part> page, String fSerch,
            String fValue, String fSerchTerm, String fSerchTerm1,
            Calendar fStime, Calendar fEtime, String fOkPart1, String fOkPart2,
            String fSstatu, String fType)
    {
        logger.debug("后台参与合买查询!");
        Criteria criteria = partDao.createCriteria();
        criteria.createAlias("community", "community");
        criteria.createAlias("community.term", "term");
        if (!StringUtils.isEmpty(fSerch)) {
            if (fSerch.equals("参与人")) {
                criteria.createAlias("customer", "customer").add(
                        Restrictions.eq("customer.nickName", fValue));
            }
            if (fSerch.equals("发起人")) {
                criteria.createAlias("community.customer", "comcustomer").add(
                        Restrictions.eq("comcustomer.nickName", fValue));
            }
            if (fSerch.equals("方案号")) {
                criteria.createAlias("community.plan", "plan");
                criteria.add(Restrictions.eq("plan.numberNo", fValue));
            }
        }
        if (!StringUtils.isEmpty(fSerchTerm)) {
            criteria.add(Restrictions.ge("term.termNo", fSerchTerm));
        }
        if (!StringUtils.isEmpty(fSerchTerm1)) {
            criteria.add(Restrictions.le("term.termNo", fSerchTerm1));
        }
        if (fStime != null) {
            criteria.add(Restrictions.ge("joinTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("joinTime", fEtime));
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
            criteria.add(Restrictions.eq("community.communityType",
                    CommunityType.valueOf(fSstatu)));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("term.type", LotteryType
                    .valueOf(fType)));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = partDao.findByCriteria(page, criteria);
        return page;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Part> getPartList(String fSerch, String fValue,
            String fSerchTerm, String fSerchTerm1, Calendar fStime,
            Calendar fEtime, String fOkPart1, String fOkPart2, String fSstatu,
            String fType)
    {
        logger.debug("后台参与合买统计!");
        Criteria criteria = partDao.createCriteria();
        criteria.createAlias("community", "community");
        criteria.createAlias("community.term", "term");
        if (!StringUtils.isEmpty(fSerch)) {
            if (fSerch.equals("参与人")) {
                criteria.createAlias("customer", "customer").add(
                        Restrictions.eq("customer.nickName", fValue));
            }
            if (fSerch.equals("发起人")) {
                criteria.createAlias("community.customer", "comcustomer").add(
                        Restrictions.eq("comcustomer.nickName", fValue));
            }
            if (fSerch.equals("方案号")) {
                criteria.createAlias("community.plan", "plan");
                criteria.add(Restrictions.eq("plan.numberNo", fValue));
            }
        }
        if (!StringUtils.isEmpty(fSerchTerm)) {
            criteria.add(Restrictions.ge("term.termNo", fSerchTerm));
        }
        if (!StringUtils.isEmpty(fSerchTerm1)) {
            criteria.add(Restrictions.le("term.termNo", fSerchTerm1));
        }
        if (fStime != null) {
            criteria.add(Restrictions.ge("joinTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("joinTime", fEtime));
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
            criteria.add(Restrictions.eq("community.communityType",
                    CommunityType.valueOf(fSstatu)));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("term.type", LotteryType
                    .valueOf(fType)));
        }
        List<Part> list = criteria.list();
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<ChaseItem> getChaseItemPage(Page<ChaseItem> page, Chase chase)
    {
        logger.debug("后台追号查询!");
        Criteria criteria = chaseItemDao.createCriteria(Restrictions.eq(
                "chase", chase));
        criteria.addOrder(org.hibernate.criterion.Order.asc("termNo"));
        page = chaseItemDao.findByCriteria(page, criteria);
        return page;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ChaseItem> getChaseItemList(String fName, String fType,
            Calendar fStime, Calendar fEtime, String fSerchTerm,
            String fSerchTerm1, String fStatu)
    {
        logger.debug("后台追号查询!");
        Criteria criteria = chaseItemDao.createCriteria();
        if (!StringUtils.isEmpty(fName)) {
            criteria.createAlias("customer", "customer").add(
                    Restrictions.eq("customer.nickName", fName));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("lotteryType", LotteryType
                    .valueOf(fType)));
        }
        criteria.createAlias("plan", "plan");
        if (fStime != null) {
            criteria.add(Restrictions.ge("plan.buildTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("plan.buildTime", fEtime));
        }
        if (!StringUtils.isEmpty(fSerchTerm)) {
            criteria.add(Restrictions.ge("termNo", fSerchTerm));
        }
        if (!StringUtils.isEmpty(fSerchTerm1)) {
            criteria.add(Restrictions.le("termNo", fSerchTerm1));
        }
        if (!StringUtils.isEmpty(fStatu)) {
            criteria
                    .add(Restrictions.le("status", ChaseStatus.valueOf(fStatu)));
        }
        List<ChaseItem> list = criteria.list();
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Order getOrderByPlan(Plan plan)
    {
        List<Order> list = orderDao.findByProperty("plan", plan);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return new Order();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Chase> getChasePage(Page<Chase> page, String fName,
            String fType, Calendar fStime, Calendar fEtime, String fStatu,
            PloyType ployType)
    {
        logger.debug("分页获得追号方案数据");
        Criteria criteria = chaseDao.createCriteria();
        if (!StringUtils.isEmpty(fName)) {
            criteria.createAlias("customer", "customer").add(
                    Restrictions.eq("customer.nickName", fName));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("lotteryType", LotteryType
                    .valueOf(fType)));
        }
        criteria.createAlias("plan", "plan");
        if (fStime != null) {
            criteria.add(Restrictions.ge("plan.buildTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("plan.buildTime", fEtime));
        }
        if (!StringUtils.isEmpty(fStatu)) {
            criteria
                    .add(Restrictions.le("status", ChaseStatus.valueOf(fStatu)));
        }

        criteria.add(Restrictions.eq("ployType", ployType));

        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = chaseDao.findByCriteria(page, criteria);
        return page;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Chase> getChaseList(String fName, String fType,
            Calendar fStime, Calendar fEtime, String fSerchTerm,
            String fSerchTerm1, String fStatu)
    {
        logger.debug("分页获得追号方案数据");
        Criteria criteria = chaseDao.createCriteria();
        if (!StringUtils.isEmpty(fName)) {
            criteria.createAlias("customer", "customer").add(
                    Restrictions.eq("customer.nickName", fName));
        }
        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("lotteryType", LotteryType
                    .valueOf(fType)));
        }
        criteria.createAlias("plan", "plan");
        if (fStime != null) {
            criteria.add(Restrictions.ge("plan.buildTime", fStime));
        }
        if (fEtime != null) {
            criteria.add(Restrictions.le("plan.buildTime", fEtime));
        }
        if (!StringUtils.isEmpty(fStatu)) {
            criteria
                    .add(Restrictions.le("status", ChaseStatus.valueOf(fStatu)));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        return criteria.list();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Ticket> getTicketPage(Page<Ticket> page, String fType,
            String fTerm, String fPlayType, String fStatu, String fNumberNo)
    {
        Criteria criteria = ticketDao.createCriteria();

        if (!StringUtils.isEmpty(fType) && !fType.equals("全部")) {
            criteria.add(Restrictions.eq("type", LotteryType.valueOf(fType)));
        }
        if (!StringUtils.isEmpty(fTerm)) {
            criteria.add(Restrictions.eq("termNo", fTerm));
        }
        if (!StringUtils.isEmpty(fPlayType)) {
            criteria.add(Restrictions.eq("playType", PlayType
                    .neToType(fPlayType)));
        }
        if (!StringUtils.isEmpty(fStatu)) {
            criteria.add(Restrictions
                    .eq("status", TicketStatus.valueOf(fStatu)));
        }
        if (!StringUtils.isEmpty(fNumberNo)) {
            criteria.createAlias("item", "item").createAlias("item.plan",
                    "plan").add(Restrictions.eq("plan.numberNo", fNumberNo));
        }
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = ticketDao.findByCriteria(page, criteria);
        return page;
    }

    public Chase getChasebyPlanId(Plan plan)
    {
        List<Chase> list = chaseDao.findByProperty("plan", plan);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)获套餐中奖数据
     * 
     * @see
     * com.xsc.lottery.service.business.LotteryOrderService#getActiveMealOrderPage
     * (org.springside.modules.orm.hibernate.Page, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    public Page<Order> getActiveMealOrderPage(Page<Order> page, String fWinStr,
            String ftype, String f_term, String f_addwin, Calendar fStratTime,
            Calendar fEndTime)
    {
        Criteria criteria = orderDao.createCriteria();
        criteria.add(Restrictions.eq("ployType", PloyType.套餐活动));
        if (StringUtils.isNotEmpty(fWinStr))
            criteria.add(Restrictions.eq("orderResult", OrderResult
                    .valueOf(fWinStr)));

        if (StringUtils.isNotEmpty(ftype) && !ftype.equals("全部"))
            criteria.add(Restrictions.eq("type", LotteryType.valueOf(ftype)));

        criteria.createAlias("term", "term");
        if (StringUtils.isNotEmpty(f_term))
            criteria.add(Restrictions.eq("term.termNo", f_term));

        if (StringUtils.isNotEmpty(f_addwin)) {
            if (f_addwin.equals("已加奖"))
                criteria.add(Restrictions.gt("ployNumber", 0));
            if (f_addwin.equals("未加奖"))
                criteria.add(Restrictions.eq("ployNumber", 0));
        }

        if (fStratTime != null)
            criteria.add(Restrictions.ge("buildTime", fStratTime));

        if (fStratTime != null)
            criteria.add(Restrictions.le("buildTime", fEndTime));

        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        page = orderDao.findByCriteria(page, criteria);
        return page;
    }
    
    /** 根据比赛获取订单 */
    public List<Order> getOrderByLastMatch(MatchArrange match)
    {
    	List<Order> list = orderDao.findByProperty("lastMatch", match);
        return list;
    }

	public List<Ticket> getTicketsNeedCheckSP()
	{
    	Criteria criteria = ticketDao.createCriteria();
		criteria.add(Restrictions.eq("spstatus", TicketSPStatus.需要检查SP));
		List<Ticket> list = criteria.list();
		return list;
	}
    
    public boolean isFirstOrder(Order order)
    {
        Criteria criteria = this.orderDao.createCriteria();
        criteria.add(Restrictions.or(Restrictions.eq("status", OrderStatus.出票成功), 
                Restrictions.eq("status", OrderStatus.部分出票成功)));
        criteria.add(Restrictions.eq("customer", order.getCustomer()));
        criteria.setMaxResults(1);
        return criteria.list().size() == 0;
    }
    
    /** 第三方接口查询*/
	public List<Order> getOrdersByOther(String otherFrom, 
    		Calendar startTime, Calendar endTime)
    {
    	if(otherFrom == null) {
    		return null;
    	}
    	Criteria criteria = orderDao.createCriteria();
		criteria.add(Restrictions.eq("otherFrom", otherFrom));
		
		if (startTime != null) {
            criteria.add(Restrictions.ge("buildTime", startTime));
		}

        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        
		List<Order> list = criteria.list();
    	return list;
    }
    /** 第三方接口查询*/
	public List<Order> getOrdersBySendTicketPlant(String plant, 
    		Calendar startTime, Calendar endTime)
    {
    	if(plant == null) {
    		return null;
    	}
    	Criteria criteria = orderDao.createCriteria();
		criteria.add(Restrictions.eq("ticketThirdName", plant));
		
		if (startTime != null) {
            criteria.add(Restrictions.ge("buildTime", startTime));
		}

        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        
		List<Order> list = criteria.list();
    	return list;
    }
    /** 第三方接口查询*/
	public Page<Order> getOrdersPageBySendTicketPlant(Page<Order> page, String plant, 
    		Calendar startTime, Calendar endTime)
    {
    	if(plant == null) {
    		return null;
    	}
    	Criteria criteria = orderDao.createCriteria();
		criteria.add(Restrictions.eq("ticketThirdName", plant));
		
		if (startTime != null) {
            criteria.add(Restrictions.ge("buildTime", startTime));
		}

        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        
		page = orderDao.findByCriteria(page, criteria);
    	return page;
    }
    
    /** 第三方接口查询(中奖记录)*/
    public Page<Order> getWinOrdersPageBySendTicketPlant(Page<Order> page, String plant, 
    		Calendar startTime, Calendar endTime)
    {
    	if(plant == null) {
    		return null;
    	}
    	Criteria criteria = orderDao.createCriteria();
		criteria.add(Restrictions.eq("ticketThirdName", plant));
		
		if (startTime != null) {
            criteria.add(Restrictions.ge("buildTime", startTime));
		}

        if (endTime != null) {
            criteria.add(Restrictions.le("buildTime", endTime));
        }
        criteria.add(Restrictions.isNotEmpty("winTaxMoney"));
		page = orderDao.findByCriteria(page, criteria);
    	return page;
    }
    public List getWinPrize(){
    	  Criteria criteria = newlyWinPrizeDao.createCriteria();
          criteria.setMaxResults(20);
          criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
          List<LotteryTerm> list = criteria.list();
          return list;
    }

    /**统计彩种的中奖金额的一些数据 cbj*/ 
    @SuppressWarnings("unused")
    public List getOrderOtherSum(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source){
    	Criteria criteria= orderDao.getSession().createCriteria(Order.class)
           .setProjection(Projections.projectionList()
           .add(Projections.sum("amount"))
           .add(Projections.sum("outAmount"))
           .add(Projections.sum("winMoney"))
           .add(Projections.sum("winTaxMoney"))
           .add(Projections.groupProperty("type"))
        );
    	if(null!=f_stime){
    		criteria.add(Restrictions.ge("buildTime", f_stime));
    	}
    	if(null!=f_etime){
    		criteria.add(Restrictions.le("buildTime", f_etime));
    	}
    	if(null!=ticketThirdName && !"".equals(ticketThirdName)){
    		criteria.add(Restrictions.eq("ticketThirdName", ticketThirdName));
    	}
    	if(null!=source && !"".equals(source)){
    		criteria.add(Restrictions.eq("source", source));
    	}
    	List list=criteria.addOrder(org.hibernate.criterion.Order.asc("type")).list();
    	for(int i=0;i<list.size();i++){
			Object[] order=(Object[])list.get(i);
    		/*System.out.println("====================amount:"+order[0]);
    		System.out.println("====================outAmount:"+order[1]);
    		System.out.println("====================winMoney:"+order[2]);
    		System.out.println("====================winTaxMoney:"+order[3]);
    		System.out.println("====================type:"+order[4]);*/
    	}
    	return list;
    }
    /**统计所有彩种的中奖金额的一些数据 cbj*/ 
    @SuppressWarnings("unused")
    public List getAllOrderOtherSum(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source){
    	Criteria criteria= orderDao.getSession().createCriteria(Order.class)
        .setProjection(Projections.projectionList()
        .add(Projections.sum("amount"))
        .add(Projections.sum("outAmount"))
        .add(Projections.sum("winMoney"))
        .add(Projections.sum("winTaxMoney"))
	     );
	 	if(null!=f_stime){
	 		criteria.add(Restrictions.ge("buildTime", f_stime));
	 	}
	 	if(null!=f_etime){
	 		criteria.add(Restrictions.le("buildTime", f_etime));
	 	}
	 	if(null!=ticketThirdName && !"".equals(ticketThirdName)){
    		criteria.add(Restrictions.eq("ticketThirdName", ticketThirdName));
    	}
    	if(null!=source && !"".equals(source)){
    		criteria.add(Restrictions.eq("source", source));
    	}
	 	List list=criteria.list();
	 	for(int i=0;i<list.size();i++){
	 		Object[] order=(Object[])list.get(i);
	 		/*System.out.println("====================amount:"+order[0]);
	 		System.out.println("====================outAmount:"+order[1]);
	 		System.out.println("====================winMoney:"+order[2]);
	 		System.out.println("====================winTaxMoney:"+order[3]);*/
	 	}
	 	return list;
    }
    /**统计拉手充值余额 cbj*/
    public BigDecimal getLaShouSum(Calendar f_stime,Calendar f_etime){
    	Criteria criteria= walletLogDao.getSession().createCriteria(WalletLog.class)
        .setProjection(Projections.projectionList()
        .add(Projections.sum("inMoney"))
	     ).add(Restrictions.eq("type", WalletLogType.拉手余额充值));
	 	if(null!=f_stime){
	 		criteria.add(Restrictions.ge("time", f_stime));
	 	}
	 	if(null!=f_etime){
	 		criteria.add(Restrictions.le("time", f_etime));
	 	}
        List list=criteria.list();
        BigDecimal lashou=new BigDecimal(0.00);
        Object obj=list.get(0);
        if(obj!=null){
        	lashou=(BigDecimal)obj;
        }
    	return lashou;
    }
    /**统计淘宝手机充值余额 cbj*/
    public BigDecimal getZhiFuBaoSum(Calendar f_stime,Calendar f_etime){
    	Criteria criteria= walletLogDao.getSession().createCriteria(WalletLog.class)
        .setProjection(Projections.projectionList()
        .add(Projections.sum("inMoney"))
	     ).add(Restrictions.eq("type", WalletLogType.账户充值)).add(Restrictions.like("description", "%支付宝%"));
	 	if(null!=f_stime){
	 		criteria.add(Restrictions.ge("time", f_stime));
	 	}
	 	if(null!=f_etime){
	 		criteria.add(Restrictions.le("time", f_etime));
	 	}
	 	List list=criteria.list();
	 	BigDecimal zhifubao=new BigDecimal(0.00);
	 	Object obj=list.get(0);
        if(obj!=null){
        	zhifubao=(BigDecimal)obj;
        }
    	return zhifubao;
    }
    
    /**统计快钱手机充值余额 cbj*/
    public BigDecimal getKuaiQianSum(Calendar f_stime,Calendar f_etime){
    	Criteria criteria= walletLogDao.getSession().createCriteria(WalletLog.class)
        .setProjection(Projections.projectionList()
        .add(Projections.sum("inMoney"))
	     ).add(Restrictions.eq("type", WalletLogType.账户充值)).add(Restrictions.like("description", "%快钱%"));
	 	if(null!=f_stime){
	 		criteria.add(Restrictions.ge("time", f_stime));
	 	}
	 	if(null!=f_etime){
	 		criteria.add(Restrictions.le("time", f_etime));
	 	}
	 	List list=criteria.list();
	 	BigDecimal kuaiqian=new BigDecimal(0.00);
	 	Object obj=list.get(0);
        if(obj!=null){
        	kuaiqian=(BigDecimal)obj;
        }
    	return kuaiqian;
    }
    
    // 设置表格样式
    public HSSFCellStyle getExcelStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)10); // 字体高度
        font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
        font.setFontName( "宋体" ); 
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度
        //font.setItalic( true );   // 是否使用斜体
        //font.setStrikeout(true); // 是否使用划线
        
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
        cellStyle.setWrapText(false);
    	return cellStyle;
    }
    //设置表头
    @SuppressWarnings("deprecation")
	public Map getExcelHead(HSSFRow row,HSSFCellStyle cellStyle){
    	Map map=new HashMap();
    	List heads=new ArrayList();
    	HSSFCell cell=row.createCell((short)0);
    	cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("统计日期");
		heads.add("统计日期");
		
		//统计全部彩种的四个总统计 begin
		String headNameAll=(String)LotteryType.lotteryTypeList.get(0);
		cell=row.createCell((short)1);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue(headNameAll+"销售金额");
	    heads.add(headNameAll+"销售金额");
	    
	    cell=row.createCell((short)2);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue(headNameAll+"出票金额");
	    heads.add(headNameAll+"出票金额");
	    
	    cell=row.createCell((short)3);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue(headNameAll+"中奖金额");
	    heads.add(headNameAll+"中奖金额");
	    
	    cell=row.createCell((short)4);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue(headNameAll+"税后金额");
	    heads.add(headNameAll+"税后金额");
	    //统计全部彩种的四个总统计 end
		
		//统计充值金额的四个统计  begin
		cell=row.createCell((short)5);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("拉手余额充值金额");
	    heads.add("拉手余额充值金额");
		
		cell=row.createCell((short)6);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("支付宝手机充值金额");
		heads.add("支付宝手机充值金额");
		
		cell=row.createCell((short)7);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("快钱充值金额");
		heads.add("快钱充值金额");
		
		cell=row.createCell((short)8);
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("总充值金额");
		heads.add("总充值金额");
		//统计充值金额的四个统计  end
		
		int n=0;//单元格的位置
    	for(int i=1;i<LotteryType.lotteryTypeList.size();i++){
    		String headName=(String)LotteryType.lotteryTypeList.get(i);
    		for(int j=1;j<=4;j++){
    			//n=i*4-(4-j);//单元格的位置
    			n=i*4+(4+j);//单元格的位置
    			cell=row.createCell((short)n);
        		cell.setCellStyle(cellStyle); // 设置单元格样式
        		if(j==1){
        		    cell.setCellValue(headName+"销售金额");
        		    heads.add(headName+"销售金额");
        		}else if(j==2){
        			cell.setCellValue(headName+"出票金额");
        			heads.add(headName+"出票金额");
        		}else if(j==3){
        			cell.setCellValue(headName+"中奖金额");
        			heads.add(headName+"中奖金额");
        		}else if(j==4){
        			cell.setCellValue(headName+"税后金额");
        			heads.add(headName+"税后金额");
        		}
    		}   		
    	}
    	/*cell=row.createCell((short)(n+1));
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("拉手余额充值金额");
	    heads.add("拉手余额充值金额");
		
		cell=row.createCell((short)(n+2));
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("支付宝手机充值金额");
		heads.add("支付宝手机充值金额");
		
		cell=row.createCell((short)(n+3));
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("快钱充值金额");
		heads.add("快钱充值金额");
		
		cell=row.createCell((short)(n+4));
		cell.setCellStyle(cellStyle); // 设置单元格样式
		cell.setCellValue("总充值金额");
		heads.add("总充值金额");*/
		
		map.put("cell", cell);
		map.put("heads", heads);
		return map;
    }
    /**
     * 把取出的信息写入行信息
     * @author cbj
     * @param heads 表头信息
     * @param infos 彩种统计信息
     * @param countInfos 统计所有彩种统计信息
     * @param czs 统计充值信息
     * @param row 行对象
     * @param cell 单元格对象
     * @param sheet 工作薄对象
     * 
     */
    @SuppressWarnings("deprecation")
	public void getRowInfo(List heads,List infos,List countInfos,List czs,HSSFRow row,HSSFCell cell,HSSFSheet sheet){
        //取出统计所有彩种统计信息
 		Object[] countInfo=(Object[])countInfos.get(0);		
 		cell=row.createCell((short)1);
 		cell.setCellValue(getNullString(String.valueOf(countInfo[0])));
 		
 		cell=row.createCell((short)2);
 		cell.setCellValue(getNullString(String.valueOf(countInfo[1])));
 		
 		cell=row.createCell((short)3);
 		cell.setCellValue(getNullString(String.valueOf(countInfo[2])));
 		
 		cell=row.createCell((short)4);
 		cell.setCellValue(getNullString(String.valueOf(countInfo[3])));
 		
 		//取出充值金额
 		cell=row.createCell((short)5);
    	cell.setCellValue(String.valueOf(czs.get(0)));
    	
    	cell=row.createCell((short)6);
    	cell.setCellValue(String.valueOf(czs.get(1)));
    	
    	cell=row.createCell((short)7);
    	cell.setCellValue(String.valueOf(czs.get(2)));
    	
    	cell=row.createCell((short)8);
    	cell.setCellValue(String.valueOf(czs.get(3)));
    	
 		//取出彩种统计信息
    	for(int i=9;i<heads.size();i++){
    		String head=(String)heads.get(i);
    		for(int j=0;j<infos.size();j++){
    			Object[] info=(Object[])infos.get(j); 
    			String infoHead=String.valueOf(info[4]);
    			if(head.indexOf(infoHead)>-1){
    				cell=row.createCell((short)i);
    				if(head.indexOf("销售金额")>-1){
    					cell.setCellValue(String.valueOf(info[0]));
    					break;
    				}else if(head.indexOf("出票金额")>-1){
    					cell.setCellValue(String.valueOf(info[1]));
    					break;
    				}else if(head.indexOf("中奖金额")>-1){
    					cell.setCellValue(String.valueOf(info[2]));
    					break;
    				}else if(head.indexOf("税后金额")>-1){
    					cell.setCellValue(String.valueOf(info[3]));
    					break;
    				}
    				
    			}
    			
    		}
    	}
    	//取出充值金额
    	/*cell=row.createCell((short)(heads.size()-4));
    	cell.setCellValue(String.valueOf(czs.get(0)));
    	
    	cell=row.createCell((short)(heads.size()-3));
    	cell.setCellValue(String.valueOf(czs.get(1)));
    	
    	cell=row.createCell((short)(heads.size()-2));
    	cell.setCellValue(String.valueOf(czs.get(2)));
    	
    	cell=row.createCell((short)(heads.size()-1));
    	cell.setCellValue(String.valueOf(czs.get(3)));*/
    }
    /**
     * 封装所有的统计信息
     * @author cbj
     * @param heads 表头信息
     * @param dates 统计的时间段信息
     * @param sheet 工作薄对象
     * @param row  行对象
     * @param cell 单元格对象
     */
    @SuppressWarnings("deprecation")
    public void getAllCountInfo(List heads,List dates,HSSFSheet sheet,HSSFRow row,HSSFCell cell,String ticketThirdName,String source){
    	 for(int i=0;i<dates.size();i++){
         	List<Calendar> dar=(List<Calendar>)dates.get(i);
      		//彩种统计信息
             List infos= getOrderOtherSum(dar.get(0),dar.get(1),ticketThirdName,source);
             //统计所有彩种统计信息
             List countInfos=getAllOrderOtherSum(dar.get(0),dar.get(1),ticketThirdName,source);
             
             List czs=new ArrayList();
             BigDecimal allSum=new BigDecimal(0.00);
             BigDecimal lashou= getLaShouSum(dar.get(0),dar.get(1));
             BigDecimal zhifubo=getZhiFuBaoSum(dar.get(0),dar.get(1));
             BigDecimal kuaiqian=getKuaiQianSum(dar.get(0),dar.get(1));
             if(lashou!=null){
            	 allSum=allSum.add(lashou);
             }else{
            	 lashou=new BigDecimal(0.00);
             }
             if(zhifubo!=null){
            	 allSum=allSum.add(zhifubo);
             }else{
            	 zhifubo=new BigDecimal(0.00);
             }
             if(kuaiqian!=null){
            	 allSum=allSum.add(kuaiqian);
             }else{
            	 kuaiqian=new BigDecimal(0.00);
             }
            
             czs.add(lashou);
             czs.add(zhifubo);
             czs.add(kuaiqian);
             czs.add(allSum);
             
             row=sheet.createRow(i+1);     		
      		 cell=row.createCell((short)0);
      		 cell.setCellValue(getStrDate(dar.get(0).getTime())+"/"+getStrDate(dar.get(1).getTime()));
      		
      		this.getRowInfo(heads, infos, countInfos, czs, row, cell, sheet);
         }
         
    }
    
    /**
     * 日报统计
     */
    public InputStream getDayInputStream(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source) {
    	HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个工作薄
		HSSFSheet sheet=wb.createSheet("财务日报统计");	
		//设置表格的样式
		HSSFCellStyle cellStyle=this.getExcelStyle(wb);  
        //创建一行
        HSSFRow row=sheet.createRow(0);

		List dates=new ArrayList();
		try{
	    	if(f_stime==null || f_etime==null){
	    		Calendar s=Calendar.getInstance(); //开始日期
	    		Calendar e=Calendar.getInstance();//结束日期
	    		Calendar c=Calendar.getInstance();
	    		s.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	    		e.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	    		List<Calendar> dar=new ArrayList<Calendar>();
	    		dar.add(s);
	    		dar.add(e);
	    		dates.add(dar);
	    	}else if(f_stime!=null && f_etime!=null){
	    		List<String> lc= getTimeDay(f_stime,f_etime);
	    		
	    		for(int i=0;i<lc.size();i++){
	    			String strDate=lc.get(i);
	    			Calendar s_i=Calendar.getInstance(); //开始日期
	        		Calendar e_i=Calendar.getInstance();//结束日期
	    			Calendar c_i=Calendar.getInstance();
	    			c_i.setTime(getDate(strDate));
	    			s_i.set(c_i.get(Calendar.YEAR), c_i.get(Calendar.MONTH), c_i.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	        		e_i.set(c_i.get(Calendar.YEAR), c_i.get(Calendar.MONTH), c_i.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	        		List<Calendar> dar=new ArrayList<Calendar>();
	        		dar.add(s_i);
	        		dar.add(e_i);
	        		dates.add(dar);
	    		}
	    	}
	    	
	    	Map map=getExcelHead(row,cellStyle);
	    	
	        //创建设置表头
	        HSSFCell cell=(HSSFCell)map.get("cell");
	        //把表头的信息存在list中，好让取出的值与表头对应
	        List heads=(List)map.get("heads");
	        //封装并取出所有的统计信息放入excel表中
	        getAllCountInfo(heads, dates, sheet, row, cell,ticketThirdName,source);
    	}catch(Exception ee){
    		ee.printStackTrace();
    	}
			
		//String fileName=RandomStringUtils.randomAlphanumeric(10);
		String fileName="Export";
		fileName=new StringBuffer(fileName).append(".xls").toString();
		File file=new File(fileName);
		try {
			OutputStream os=new FileOutputStream(file);
			wb.write(os);
			os.close();
			InputStream is=new FileInputStream(file);
			return is;
		} catch (Exception es) {
			es.printStackTrace();
		}
		
		return null;
	}
    /**
     * 周报统计
     * @param f_stime
     * @param f_etime
     * @return
     */
	public InputStream getWeekInputStream(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source) {
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个工作薄
		HSSFSheet sheet=wb.createSheet("财务周报统计");	
		//设置表格的样式
		HSSFCellStyle cellStyle=this.getExcelStyle(wb);  
        //创建一行
        HSSFRow row=sheet.createRow(0);

		List dates=new ArrayList();
		try{
	    	if(f_stime==null || f_etime==null){
	    		Calendar cal=Calendar.getInstance();
	    		Map<String, String> datemap=getbeginDateAndendDate(cal);//调用传入某个日期返回本日所在周的开始日期 结束日期
	    		Calendar s=Calendar.getInstance(); //开始日期
	    		Calendar e=Calendar.getInstance();//结束日期
	    		//把开始日期和结束日期赋给s e
	    		s.setTime(getDate(datemap.get("beginDate")));	    		
	    		e.setTime(getDate(datemap.get("endDate")));
	    		
	    		s.set(s.get(Calendar.YEAR), s.get(Calendar.MONTH), s.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	    		e.set(e.get(Calendar.YEAR), e.get(Calendar.MONTH), e.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	    		List<Calendar> dar=new ArrayList<Calendar>();
	    		dar.add(s);
	    		dar.add(e);
	    		dates.add(dar);
	    	}else if(f_stime!=null && f_etime!=null){
	    		Map<String, String> datemap= getbeginDateAndendDate(f_stime,f_etime);
	    		for(int i=0;i<datemap.size()/2;i++){
	    			Calendar s_i=Calendar.getInstance(); //开始日期
		    		Calendar e_i=Calendar.getInstance();//结束日期
		    		//把开始日期和结束日期赋给s e
		    		s_i.setTime(getDate(datemap.get("beginDate"+i)));	    		
		    		e_i.setTime(getDate(datemap.get("endDate"+i)));
		    		
		    		s_i.set(s_i.get(Calendar.YEAR), s_i.get(Calendar.MONTH), s_i.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		    		e_i.set(e_i.get(Calendar.YEAR), e_i.get(Calendar.MONTH), e_i.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		    		List<Calendar> dar=new ArrayList<Calendar>();
		    		dar.add(s_i);
		    		dar.add(e_i);
		    		dates.add(dar);
	    		}
	    	}
	    	
	    	Map map=getExcelHead(row,cellStyle);
	    	
	        //创建设置表头
	        HSSFCell cell=(HSSFCell)map.get("cell");
	        //把表头的信息存在list中，好让取出的值与表头对应
	        List heads=(List)map.get("heads");
	        //封装并取出所有的统计信息放入excel表中
	        getAllCountInfo(heads, dates, sheet, row, cell,ticketThirdName,source);
    	}catch(Exception ee){
    		ee.printStackTrace();
    	}
			
		//String fileName=RandomStringUtils.randomAlphanumeric(10);
		String fileName="Export";
		fileName=new StringBuffer(fileName).append(".xls").toString();
		File file=new File(fileName);
		try {
			OutputStream os=new FileOutputStream(file);
			wb.write(os);
			os.close();
			InputStream is=new FileInputStream(file);
			return is;
		} catch (Exception es) {
			es.printStackTrace();
		}
		
		return null;
	}
    /**
     * 取得两两日期之间的所有日期（包括开始和结束日期）
     * @param startDay
     * @param endDay
     * @return
     */
    public List<String> getTimeDay(Calendar startDay, Calendar endDay) {
          List<String> list=new ArrayList<String>();
          list.add(getStrDate(startDay.getTime()));
    	  // 给出的日期开始日比终了日大则不执行打印
    	  if (startDay.compareTo(endDay) >= 0) {
    	    return null;
    	  }
    	 
    	  // 现在打印中的日期
    	  Calendar currentPrintDay = startDay;
    	  while (true) {
    	   // 日期加一
    	   currentPrintDay.add(Calendar.DATE, 1);
    	   // 日期加一后判断是否达到终了日，达到则终止打印
    	   if (currentPrintDay.compareTo(endDay) == 0) {
    	    break;
    	   }
    	    list.add(getStrDate(currentPrintDay.getTime()));
    	  }
    	     list.add(getStrDate(endDay.getTime()));
    	   return list;
    }
    /**
     * 把日期转换为字符串日期
     * @param date
     * @return
     */
    public String getStrDate(Date date){
    	SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return formate.format(date);
    }
    /**
     * 把字符串日期转换为日期
     * @param strDate
     * @return
     */
    public Date getDate(String strDate){
    	SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date=null;
    	try {
			date=formate.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
    }
    /**
     * 把null对象或空字符串转化为0
     * @param str
     * @return
     */
    public String getNullString(String str){
    	String s="";
    	if(null==str || "null".equals(str)){
    		s="0";
    	}else{
    		s=str;
    	}
    	return s;
    }
    public static void main(String[] args) {
    	LotteryOrderServiceImpl l=new LotteryOrderServiceImpl();
		/*Calendar  c=Calendar.getInstance();
        Calendar s=Calendar.getInstance();
      
		System.out.println("===============year:"+c.get(Calendar.YEAR));
		System.out.println("===============month:"+c.get(Calendar.MONTH));
		System.out.println("===============date:"+c.get(Calendar.DAY_OF_MONTH));
		s.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 16, 0, 0, 59);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		int i=s.compareTo(c);
		 String strDate2=l.getStrDate(s.getTime());
		 String strDate1=l.getStrDate(c.getTime());
	    System.out.println("============strDate1:"+strDate1+",strDate2:"+strDate2+",i:"+i);
	  
	    BigDecimal e=new BigDecimal(0.00);
	    
	    BigDecimal f=new BigDecimal(9.8);
	    BigDecimal g=new BigDecimal(23.8);
	    BigDecimal h=new BigDecimal(35.8);
	    
	    e=f.add(g).add(h);
	    System.out.println("=====================e:"+e+",f:"+f+",g:"+g+",h:"+h);
	    
	    Calendar cd=Calendar.getInstance();
	    Map<String, String> map=getbeginDateAndendDate(cd.getTime());
	    String st=map.get("beginDate");
	    String et=map.get("endDate");
	    System.out.println("===========================st:"+st+",et:"+et);*/
    	
    	
    		 
    	    /* Calendar c_begin = new GregorianCalendar();
    	     Calendar c_end = new GregorianCalendar();
    	     DateFormatSymbols dfs = new DateFormatSymbols();
    	     String[] weeks = dfs.getWeekdays();
    	    
    	     c_begin.set(2010, 3, 2); //Calendar的月从0-11，所以4月是3.
    	     c_end.set(2010, 4, 20); //Calendar的月从0-11，所以5月是4.

    	     int count = 1;
    	     c_end.add(Calendar.DAY_OF_YEAR, 1);  //结束日期下滚一天是为了包含最后一天
    	    
    	     while(c_begin.before(c_end)){
    	      // System.out.println("第"+count+"周  日期："+new java.sql.Date(c_begin.getTime().getTime())+","+weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
              
    	     if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
     	    	  System.out.println("============startDate:"+l.getStrDate(c_begin.getTime()));
     	      }
    	      if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
    	    	  System.out.println("============endDate:"+l.getStrDate(c_begin.getTime()));
    	      }
    	      c_begin.add(Calendar.DAY_OF_YEAR, 1);
    	     }*/
    	
    	Calendar  c=Calendar.getInstance();
        Calendar s=Calendar.getInstance();
        s.set(2012, 4, 1, 0, 0, 0);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	l.getbeginDateAndendDate(s,c);
    	   
    	
	}
    
    
    /**
	 * 传入某个日期返回本日所在周的开始日期 结束日期
	 * @param date
	 * @return 返回本日所在周的开始日期 结束日期
	 */
	public Map<String,String> getbeginDateAndendDate(Calendar cal)
	{
		 Map<String,String> datemap = new HashMap<String, String>();
		 int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		 cal.add(Calendar.DATE, -day_of_week);
	
		 datemap.put("beginDate", getStrDate(cal.getTime()));
		 cal.add(Calendar.DATE, 6);
		
		 datemap.put("endDate", getStrDate(cal.getTime()));
		 
		 return datemap;
	}
	/**
	 * 传入某两个日期
	 * @param c_begin
	 * @param c_end
	 * @return 返回两日期间隔所有所在周的开始日期 结束日期
	 */
	public Map<String,String> getbeginDateAndendDate(Calendar c_begin,Calendar c_end){
         Map<String, String> datemap=new HashMap<String, String>();
	     c_end.add(Calendar.DAY_OF_YEAR, 1);  //结束日期下滚一天是为了包含最后一天
	     int b=0;
	     int e=0;
	     if(c_begin.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY){
	    	 datemap.put("beginDate"+b, getStrDate(c_begin.getTime()));
	    	 b++;
	    	 System.out.println("======================startDate0:"+getStrDate(c_begin.getTime()));
	     }
	     while(c_begin.before(c_end)){
		     if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
		    	 datemap.put("beginDate"+b, getStrDate(c_begin.getTime()));
		    	 b++;
		    	  System.out.println("============startDate:"+getStrDate(c_begin.getTime()));
		      }
		      if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
		    	  datemap.put("endDate"+e, getStrDate(c_begin.getTime()));
		    	  e++;
		    	  System.out.println("============endDate:"+getStrDate(c_begin.getTime()));
		      }
	         c_begin.add(Calendar.DAY_OF_YEAR, 1);
	     }
	     c_end.add(Calendar.DAY_OF_YEAR, -1); 
	     if(c_end.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY){
	    	 datemap.put("endDate"+e, getStrDate(c_end.getTime()));
	    	 e++;
	    	 System.out.println("======================endDate0:"+getStrDate(c_end.getTime()));
	     }
	     return datemap;
	}

	public List get_out_amount_by_time(Calendar stime, Calendar etime, String name) {
		String sql="SELECT sum(o.out_amount), o.type FROM business_order o, business_term t ";
		sql += " where o.term_id = t.id and ticket_third_name = :name";

		if (etime != null) {
			if (stime != null) {
				sql += " and t.open_prize_time >=:stime and t.open_prize_time <=:etime";
			}else {
				sql += " and t.open_prize_time <=:etime";
			}
		}
		sql += " group by  type";
		SQLQuery query = orderDao.getSession().createSQLQuery(sql);
		query.setParameter("name", name);
		if (stime != null) {
			query.setParameter("stime", stime);
		}
		
		if (etime != null) {
			query.setParameter("etime", etime);
		}
       List<Object[]> list = query.list();
 	   return list;
	}
	/**
	 * 系统扣款统计
	 * @param f_stime
	 * @param f_etime
	 * @return
	 */
	public BigDecimal getSumTypeWalleLogXitongkoukuan(Calendar f_stime,Calendar f_etime){
		Criteria criteria= walletLogDao.getSession().createCriteria(WalletLog.class);
		criteria.setProjection(Projections.projectionList().add(Projections.sum("outMoney")));
		if(f_stime!=null){
			criteria.add(Restrictions.ge("time", f_stime));
		}
		if(f_etime!=null){
			criteria.add(Restrictions.le("time", f_etime));
		}
		List list= criteria.add(Restrictions.eq("type", WalletLogType.系统扣款)).list();
		BigDecimal sumNum=new BigDecimal(0.00);
		Object obj= list.get(0);
		if(obj!=null){
			sumNum=(BigDecimal)obj;
		}
		return sumNum;
	}
	/**
	 * 直接充值统计
	 * @param f_stime
	 * @param f_etime
	 * @return
	 */
	public BigDecimal getSumTypeWalleLogZhijiechongzhi(Calendar f_stime,Calendar f_etime){
		Criteria criteria= walletLogDao.getSession().createCriteria(WalletLog.class);
		criteria.setProjection(Projections.projectionList().add(Projections.sum("inMoney")));
		if(f_stime!=null){
			criteria.add(Restrictions.ge("time", f_stime));
		}
		if(f_etime!=null){
			criteria.add(Restrictions.le("time", f_etime));
		}
		List list= criteria.add(Restrictions.eq("type", WalletLogType.直接充值)).list();
		BigDecimal sumNum=new BigDecimal(0.00);
		Object obj= list.get(0);
		if(obj!=null){
			sumNum=(BigDecimal)obj;
		}
		return sumNum;
	}

	public BigDecimal getSumMoney(LotteryType type, Calendar startTime,
			Calendar overTime, String statue) {
		logger.debug("按条件搜索分页竟彩篮球注单列表!");
        Criteria criteria = orderDao.createCriteria();
        criteria.setProjection(Projections.projectionList().add(Projections.sum("winTaxMoney")));
        //System.out.println(OrderResult.enToType(status));
        OrderResult statusTemp = OrderResult.enToType(statue);
        criteria.add(Restrictions.eq("orderResult", statusTemp));
        if(startTime != null)
        {
        	criteria.add(Restrictions.ge("buildTime", startTime));
        }
        if(overTime != null)
        {
        	criteria.add(Restrictions.le("buildTime", overTime));
        }
        criteria.add(Restrictions.eq("type", type));       
        List<Object> list = criteria.list();
        BigDecimal sumNum=new BigDecimal(0.00);
		Object obj= list.get(0);
		if(obj!=null){
			sumNum=(BigDecimal)obj;
		}
		return sumNum;
	}
	
	public List<Ticket> findTicketsbyOrderIdAndStatus(String orderid, TicketStatus status) {
		System.out.println("进入server啦");
        Criteria criteria = ticketDao.createCriteria();
        criteria.createAlias("order", "order");
        criteria.add(Restrictions.eq("order.id", Long.valueOf(orderid)));
        criteria.add(Restrictions.eq("status", status));
		List<Ticket> list = criteria.list();
        
        System.out.println("正常......................");
		return list;
	}

	public BigDecimal getSumMoneyByCustomer(Calendar startTime, Calendar overTime,Customer customer,LotteryType lotteryType)
	{
		 Criteria criteria = orderDao.createCriteria();
		 criteria.createAlias("customer", "customer");
		 criteria.add(Restrictions.eq("customer.superior", customer));
	     criteria.setProjection(Projections.projectionList().add(Projections.sum("outAmount")));
	     criteria.add(Restrictions.or(Restrictions.eq("status", OrderStatus.出票成功), Restrictions.eq("status", OrderStatus.部分出票成功)));
	     if(startTime != null)
	     {
	        criteria.add(Restrictions.ge("successTime", startTime));
	     }
	     if(overTime != null)
	     {
	        criteria.add(Restrictions.le("successTime", overTime));
	     }
	     if(lotteryType!=null&&!LotteryType.全部.equals(lotteryType)){
	    	 criteria.add(Restrictions.eq("type", lotteryType));
	     }
	     List<Object> list = criteria.list();
	     BigDecimal sumNum=new BigDecimal(0.00);
	     Object obj= list.get(0);
		 if(obj!=null)
		 {
			sumNum=(BigDecimal)obj;
		 }
		return sumNum;
	     
	}
	
	@SuppressWarnings("unchecked")
	public Page<Order> getOrderDetailByCustomer(Page<Order> page,Calendar startTime, Calendar overTime,Customer customer,LotteryType lotteryType)
	{
		 Criteria criteria = orderDao.createCriteria();
		 criteria.createAlias("customer", "customer");
		 criteria.add(Restrictions.eq("customer.superior", customer));
	     criteria.add(Restrictions.or(Restrictions.eq("status", OrderStatus.出票成功), Restrictions.eq("status", OrderStatus.部分出票成功)));
	     if(startTime != null)
	     {
	        criteria.add(Restrictions.ge("successTime", startTime));
	     }
	     if(overTime != null)
	     {
	        criteria.add(Restrictions.le("successTime", overTime));
	     }
	     if(lotteryType!=null&&!LotteryType.全部.equals(lotteryType)){
	    	 criteria.add(Restrictions.eq("type", lotteryType));
	     }
	     criteria.addOrder(Property.forName("id").desc());
	     
	     Page<Order> p = orderDao.findByCriteria(page, criteria);
		return p;
	     
	}
	
	/* 获取某时间段被推荐人的消费人数 根据推荐人，开始结束时间*/
	@SuppressWarnings("unchecked")
	public Long getSumPayByCustomer(Calendar startTime, Calendar overTime,Customer customer, LotteryType lotteryType)
	{
		
		 Criteria criteria = orderDao.createCriteria();
		 criteria.createAlias("customer", "customer");
		 criteria.add(Restrictions.eq("customer.superior", customer));
	     criteria.add(Restrictions.or(Restrictions.eq("status", OrderStatus.出票成功), Restrictions.eq("status", OrderStatus.部分出票成功)));
	     
	     if(startTime != null)
	     {
	        criteria.add(Restrictions.ge("successTime", startTime));
	     }
	     if(overTime != null)
	     {
	        criteria.add(Restrictions.le("successTime", overTime));
	     }
	     if(lotteryType!=null&&!LotteryType.全部.equals(lotteryType)){
	    	 criteria.add(Restrictions.eq("type", lotteryType));
	     }
	     criteria.setProjection(Projections.rowCount());
	     criteria.setProjection(Projections.groupProperty("customer.id"));
		return new Long(criteria.list().size());
	     
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.LotteryOrderService#getTicketByOtherOrderId(java.lang.String)
	 */
	public Ticket getTicketByOtherOrderId(String otherOrderId)
	{
		 String hql = "select t from Ticket t where t.otherOrderID=:value";
	     Query query = ticketDao.getSession().createQuery(hql);
	     query.setParameter("value", otherOrderId);
	     List<Ticket> list = query.list();
	     return list.get(0);
	}
	
}
