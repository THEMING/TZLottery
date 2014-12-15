package com.xsc.lottery.service.business;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

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
import com.xsc.lottery.entity.business.WinDescribeOrder;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.PloyType;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.LotteryBaseService;

public interface LotteryOrderService extends LotteryBaseService<Order>
{
    /** 根据状态类型 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByStatus(OrderStatus status);

    /** 根据彩期合买截止 */
    public List<Order> stopTogegerSale(LotteryTerm term);
    
    /** 单独把发邮件作为一个事务处理*/
    public void sendOrderDetailEmail(Order order);
    
    /** 根据比赛合买截止 */
    public List<Order> stopTogegerSale(MatchArrange match);

    /** 获得注单的所有票 */
    public List<Ticket> getTicketByOrder(Order order);

    /** 获得彩期下的所有注单 */
    public List<Order> getOrderByTerm(LotteryTerm term);

    /** 彩期全部注单兑奖 */
    public void checkAllOrderWin(LotteryTerm term);

    /** 保存拆票成功结果 */
    public void saveSuccessTakeTicket(Order order, List<Ticket> tickets,
            String name);

    public void saveTicket(Ticket ticket);

    /** 完成第三方票务交易后调用 */
    public void finishTicketBusiness(Order order, List<Ticket> returnTickets);

    /** 根据订单状态及票务点查找 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByStatusAndTicketBusinessName(
            OrderStatus status, String ticketThirdName);

    /** 代购生成订单 */
    public Order createBetOrder(Customer customer, LotteryType type, Plan plan,
            List<PlanItem> items, LotteryTerm term, int multiple);

    /** 按条件搜索分页注单列表 */
    public Page<Order> getOrderPage(Page<Order> page, LotteryType type,
            String customer, LotteryTerm term);

    /** 本期开始追号 */
    public List<Order> chaseItem(LotteryTerm term);

    /** 根据客户彩种查方案 */
    public List<Order> getOrder(Customer customer, LotteryType type);

    /** 中奖查询 */
    public Page<NewlyWinPrize> getWinPrize(Page<NewlyWinPrize> page,
            LotteryType type, Calendar beginTime, Calendar endTime,
            Customer customer, String playStatus);

    /** 我的代购查询 */
    public Page<Order> getToBuy(Page<Order> page, LotteryType type,
            String status, Calendar beginTime, Calendar endTime,
            Customer customer);

    /** 我的追号查询 */
    public Page<ChaseItem> getChaseItemSale(Page<ChaseItem> page,
            LotteryType type, Customer customer, String status, String termNo);

    /** 我参与的合买查询 */
    public Page<Part> getParticipateSale(Page<Part> page, LotteryType type,
            String status, Calendar beginTime, Calendar endTime,
            Customer customer);

    public void updateOrder(Long id, BigDecimal winMoney, BigDecimal winTaxMoney);

    /**
     * @param communityId
     * @param customer
     * @param partNum
     * @return 参与合买,满员的话产生的注单
     * @throws CommunityException
     *             超出合买总份数
     * @throws NotEnoughBalanceException
     *             用户余额不足
     */
    public Order joinCommunity(Long communityId, Customer customer, int partNum)
            throws Exception;
    
    public Order joinCommunity(Long communityId, Customer customer, int partNum,Cookie cookie)
    throws Exception;

    public Ticket findByIdTicket(Long id);

    public ChaseItem findByIdChaseItem(Long id);

    public WinDescribeTicket saveWinDescribeTicket(WinDescribeTicket entity);

    public WinDescribeOrder saveWinDescribeOrder(WinDescribeOrder entity);

    /**
     * 根据合买方案得到Order方案
     * 
     * @param community
     * @return
     */
    public Order getOrderByCommunity(Community community);

    /**
     * 根据追号方案得到Order方案
     * 
     * @param community
     * @return
     */
    public Order getOrderByChaseItem(ChaseItem chaseItem);

    /**
     * 根据方案得到追号列表
     * 
     * @param community
     * @return
     */
    public List<ChaseItem> getChaseItemByPlan(Plan plan);

    /**
     * 更新ChaseItem
     * 
     * @param chaseItem
     * @return
     */
    public ChaseItem updateChaseItem(ChaseItem chaseItem);

    /**
     * 得到用户ID集合
     * 
     * @param stratTime
     * @param endTime
     * @return
     */
    public List<Long> getCustomerId(Calendar stratTime, Calendar endTime);

    /**
     *按条件 分页获得订单数据
     * 
     * @param page
     * @param fSerch
     * @param fValue
     * @param fType
     * @param fsStatu
     * @param frStatu
     * @param fStyle
     * @param fStime
     * @param fEtime
     * @param fSerchTerm
     * @param fSerchTerm1
     * @param fWinTaxMoney1
     * @param fWinTaxMoney2
     * @param fAmount1
     * @param fAmount2
     * @param regEtime
     * @param regStime
     * @return
     */
    public Page<Order> getOrderPage(Page<Order> page, String fSerch,
            String fValue, String fType, String fsStatu, String frStatu,
            String fStyle, Calendar fStime, Calendar fEtime, String fSerchTerm,
            String fSerchTerm1, String fWinTaxMoney1, String fWinTaxMoney2,
            String fAmount1, String fAmount2, Calendar regStime,
            Calendar regEtime, UserType userType,String ticketThirdName);

    /**
     * 按条件获得订单数据
     * 
     * @param fSerch
     * @param fValue
     * @param fType
     * @param fSstatu
     * @param fRstatu
     * @param fStyle
     * @param fStime
     * @param fEtime
     * @param fSerchTerm
     * @param fSerchTerm1
     * @param fWinTaxMoney1
     * @param fWinTaxMoney2
     * @param fAmount1
     * @param fAmount2
     * @param regEtime
     * @param regStime
     * @return
     */
    public List<Order> getOrder(String fSerch, String fValue, String fType,
            String fSstatu, String fRstatu, String fStyle, Calendar fStime,
            Calendar fEtime, String fSerchTerm, String fSerchTerm1,
            String fWinTaxMoney1, String fWinTaxMoney2, String fAmount1,
            String fAmount2, Calendar regStime, Calendar regEtime,UserType userType,String ticketThirdName);

    /**
     * 删除中奖明细
     * 
     * @param order
     */
    public void delWinDescribeOrder(Order order);

    /**
     * 分页获得参与合买数据
     * 
     * @param page
     * @param fSerch
     * @param fValue
     * @param fSerchTerm
     * @param fSerchTerm1
     * @param fStime
     * @param fEtime
     * @param fOkPart1
     * @param fOkPart2
     * @param fSstatu
     * @param fType
     * @return
     */
    public Page<Part> getParticipateSale(Page<Part> page, String fSerch,
            String fValue, String fSerchTerm, String fSerchTerm1,
            Calendar fStime, Calendar fEtime, String fOkPart1, String fOkPart2,
            String fSstatu, String fType);

    public List<Part> getPartList(String fSerch, String fValue,
            String fSerchTerm, String fSerchTerm1, Calendar fStime,
            Calendar fEtime, String fOkPart1, String fOkPart2, String fSstatu,
            String fType);

    public Page<ChaseItem> getChaseItemPage(Page<ChaseItem> page, Chase chase);

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ChaseItem> getChaseItemList(String fName, String fType,
            Calendar fStime, Calendar fEtime, String fSerchTerm,
            String fSerchTerm1, String fStatu);

    /** 按订单注单兑奖 */
    public void checkOrderWin(Order order);

    /**
     * 根据方案号 获得订单
     * 
     * @param plan
     * @return
     */
    public Order getOrderByPlan(Plan plan);

    /**
     * 分页获得追号方案数据
     * 
     * @param page
     * @param fName
     * @param fType
     * @param fStime
     * @param fEtime
     * @param fSerchTerm
     * @param fSerchTerm1
     * @param fStatu
     * @return
     */
    public Page<Chase> getChasePage(Page<Chase> page, String fName,
            String fType, Calendar fStime, Calendar fEtime, String fStatu,
            PloyType ployType);

    public Page<Ticket> getTicketPage(Page<Ticket> page, String fType,
            String fTerm, String fPlayType, String fStatu, String fNumberNo);

    /**
     * 统计追号方案数据
     * 
     * @param fName
     * @param fType
     * @param fStime
     * @param fEtime
     * @param fSerchTerm
     * @param fSerchTerm1
     * @param fStatu
     * @return
     */
    public List<Chase> getChaseList(String fName, String fType,
            Calendar fStime, Calendar fEtime, String fSerchTerm,
            String fSerchTerm1, String fStatu);

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getOrderByNotCustomer(Customer customer);

    /**
     * 派送赠送金额
     * 注单实际支付金额(即获赠金额)
     */
    public Plan createPlanAndItem(Plan plan, List<PlanItem> items);

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Order> getCoollect(LotteryType type, Calendar stratTime,
            Calendar endTime);

    public Order findUniqueTicketByProperty(String name, Object value);

    /**
     * 计算大赢家返回奖金
     */
    public boolean isJiangjin(Order order);

    public Chase getChasebyPlanId(Plan plan);

    public void merge(Order entity);

    /**
     * @param page
     * @param fWinStr
     * @param fPloyType
     * @param fStratTerm
     * @param fEndTerm
     * @param fStratTime
     * @param fEndTime
     * @return
     */
    public Page<Order> getActiveMealOrderPage(Page<Order> page, String fWinStr,
            String ftype, String f_term, String f_addwin, Calendar fStratTime,
            Calendar fEndTime);
    
    /** 根据比赛获取订单 */
    public List<Order> getOrderByLastMatch(MatchArrange match);
    
    public List<Ticket> getTicketsNeedCheckSP();
    
    /** 给订单派奖 */
    public void paijiang(LotteryTerm term, Order order);
    
    /** 第三方接口查询*/
    public List<Order> getOrdersByOther(String otherFrom, 
    		Calendar startTime, Calendar endTime);
    
    /** 第三方接口查询*/
    public List<Order> getOrdersBySendTicketPlant(String plant, 
    		Calendar startTime, Calendar endTime);
    
    /** 第三方接口查询*/
    public Page<Order> getOrdersPageBySendTicketPlant(Page<Order> page, String plant, 
    		Calendar startTime, Calendar endTime);
    
    /** 第三方接口查询(中奖记录)*/
    public Page<Order> getWinOrdersPageBySendTicketPlant(Page<Order> page, String plant, 
    		Calendar startTime, Calendar endTime);
    
    /** 竟彩足球对应列表*/
    public Page<Order> getJCOrderPage(LotteryType type, Page<Order> page, Calendar startTime, Calendar overTime, String status);
    public List finddWalletLog(String pid ,String date,String Staus);
    public List finddWalletLog1(String pid ,String date,String Staus);
    public List getWinPrize();

    public List get_out_amount_by_time(Calendar stime, Calendar etime, String name);    
    
    /**统计彩种的中奖金额的一些数据 cbj*/ 
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List getOrderOtherSum(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source);
    
    /**统计所有彩种的中奖金额的一些数据 cbj*/ 
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List getAllOrderOtherSum(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source);
    
    /**统计拉手充值余额 cbj*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BigDecimal getLaShouSum(Calendar f_stime,Calendar f_etime);
    
    /**统计淘宝手机充值余额 cbj*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BigDecimal getZhiFuBaoSum(Calendar f_stime,Calendar f_etime);
    
    /**统计快钱手机充值余额 cbj*/
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BigDecimal getKuaiQianSum(Calendar f_stime,Calendar f_etime);
    
    /** 获取某时间段内被推荐人的消费人数 根据推荐人，开始结束时间 
     * @param lotteryType */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long getSumPayByCustomer(Calendar startTime, Calendar endTime,Customer customer, LotteryType lotteryType);
    
    /**
     * 日报统计
     * @param f_stime
     * @param f_etime
     * @return
     */
    public InputStream getDayInputStream(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source);
    /**
     * 周报统计
     * @param f_stime
     * @param f_etime
     * @return
     */
	public InputStream getWeekInputStream(Calendar f_stime,Calendar f_etime,String ticketThirdName,String source);
	/**
	 * 兑奖管理彩种已中奖统计
	 * @param type
	 * @param customer
	 * @param term
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BigDecimal getSumWinMoney(LotteryType type,String customer, LotteryTerm term);
	
	
	/**
	 * 系统扣款统计
	 * @param f_stime
	 * @param f_etime
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BigDecimal getSumTypeWalleLogXitongkoukuan(Calendar f_stime,Calendar f_etime);
	
	/**
	 * 直接充值统计
	 * @param f_stime
	 * @param f_etime
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BigDecimal getSumTypeWalleLogZhijiechongzhi(Calendar f_stime,Calendar f_etime);
	
    /**
     * 统计已中奖 未兑奖所有用户中奖的总金额
     * @param type
     * @param startTime
     * @param overTime
     * @return
     */
	public BigDecimal getSumMoney(LotteryType type, Calendar startTime, Calendar overTime, String statue);
	
	/**
	 * 根据orderid查询出票中的ticket
	 * 
	 * */
	public List<Ticket> findTicketsbyOrderIdAndStatus(String orderid, TicketStatus status);
	
	/**
     * 统计CPS已出票总金额
     * @param type
     * @param startTime
     * @param overTime
     * @return
     */
	public BigDecimal getSumMoneyByCustomer(Calendar startTime, Calendar overTime, Customer customer, LotteryType lotteryType);
	
	/*获取某时间段内被推荐人的购彩情况*/
	public Page<Order> getOrderDetailByCustomer(Page<Order> page,Calendar startTime, Calendar overTime,Customer customer, LotteryType lotteryType);
	
	/**
	 * <pre>
	 *  通过第三方订单ID查询票
	 * </pre>
	 * @param otherOrderId  第三方订单Id
	 * @return
	 */
	public Ticket getTicketByOtherOrderId(String otherOrderId);
}
