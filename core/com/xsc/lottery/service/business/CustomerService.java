package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.CustomerCommission;
import com.xsc.lottery.entity.business.NewlyWinPrize;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.CustomerType;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.LotteryBaseService;

public interface CustomerService extends LotteryBaseService<Customer>{

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<WalletLog> findWalletLogPagerBy(Page<WalletLog> page, BusinessType businessType, Calendar startTime,
			Calendar endTime, Wallet wallet);

	/**增加用户的钱包流水事务*/
	public void addWalletLog(Long customerId, WalletLog walletLog);
	
    public void addWalletLog(Long walletId1, Long walletId2, WalletLog walletLog1, WalletLog walletLog2);
	
	/**按ID得到钱包数据*/
	public Wallet getWallet(Long id);
	
	/**根据用户名得到客户数据*/
	public Customer getCustomerOrName(String name);

	/**根据openId得到客户数据*/
	public Customer getCustomerByOpenId(String openId);
	
	/**每月重置客户接受过的来自业务员的邮件和短信的数目*/
	public void clearCustomerEmailAndSmsAcceptNum(Map map);

	/**分页获得客户流水金额信息*/
	public Page<WalletLog> getWalletLogPage(Page<WalletLog> page, int inORout, Calendar beginTime,
			Calendar endTime,Wallet wallet);

	/**获得客户流水金额信息*/
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<WalletLog> getWalletLogByCustomer(Wallet wallet);

	/**
	 * 保存充值请求
	 */
	public void savePaymentRequest(PaymentRequest entity);
	
	/**
	 * 用hql批量更新客户
	 */
	public void updateCustomers(Map map);
	
	/**
	 * 根据用户类型查询用户  
	 *  */
	public List<Customer> getCustomerByCustomerType(CustomerType type);
	
	/**
	 * 分页获得客户充值流水信息
	 */
	public Page<PaymentRequest> getPaymentRequestPage(Page<PaymentRequest> page,String f_moneyChannel,String f_name,String f_numNo,Calendar f_starTime,Calendar f_endTime,String f_statu,String f_user, String fUserTypes, Calendar fSTime, Calendar fETime);
	
	/**
	 * 分页获得客户提款流水信息
	 */
	public Page<BackMoneyRequest> getBackMoneyRequestPage(Page<BackMoneyRequest> page,String fStatu,String fTimeName, String fNikname, String fRaelname, Calendar fSTime, Calendar fETime, String fBank, String fBankCard, String fOpenSpace);

	/*根据条件获取总金额*/
	public BigDecimal getPaymentRequestSum(
            String f_moneyChannel, String f_name,
           String f_numNo, Calendar f_starTime, Calendar f_endTime,
           String f_statu, String f_user, String fUserTypes, Calendar fSTime,
           Calendar fETime);
	
	/**
	 * 获得符合条件的客户提款信息
	 */
    public List<BackMoneyRequest> getBackMoneyRequestList(
    		String fStatu, String fNikname,
            String fRaelname, String fTimeName, Calendar fSTime,
            Calendar fETime, String fBank, String fBankCard, String fOpenSpace);
	
	/**
	 * 提款请求
	 * **/
	public void saveBackMoneyRequest(BackMoneyRequest entity);
	
	/**
	 * 冻结/解冻
	 */
	public void updateWallet(Wallet wallet);
	
	/*根据条件获取提款总金额*/
	public BigDecimal getBackMoneyRequestSum(
             String fStatu, String fNikname,
            String fRaelname, String fTimeName, Calendar fSTime,
            Calendar fETime, String fBank, String fBankCard, String fOpenSpace);
	
	/**
	 * 提款请求
	 */
	public void updateBackMoneyRequest(BackMoneyRequest entity);
	
	public BackMoneyRequest findBackMoneyRequest(Long Id);
	
	/**
	 * 手动补单
	 * **/
	public void addHandMoney(PaymentRequest payment,WalletLog walletLog);

	/**分页获得客户信息*/
	public Page<Customer> getLotteryCustomerPage(Page<Customer> page, Calendar stratTime,
			Calendar endTime, String f_orderserch, String f_serch, Calendar f_starTime, Calendar f_endTime,
			String f_serchName, UserType type,Boolean isApply,Integer isPass,Map queryMap);
	
	public List<PaymentRequest> getPaymentRequestList(
			Customer customer, Calendar fStime, Calendar fEtime);
	
	/**统计充值总额*/
	public BigDecimal getChongZhiSum(Customer customer);
	
	public List<BackMoneyRequest> getBackMoneyRequestList(Customer customer);

	/**
	 * 获取最新中奖排行榜
	 */
	public List<NewlyWinPrize> getWinList(int maxRet);

	/**
	 * 后台资金明细查询
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<WalletLog> getWalletLogPage(Page<WalletLog> page, String fName,
			String numberNo, Calendar fSTime, Calendar fETime, String fType);

	
	/**
	 * 获取历史中奖排行榜
	 */
	public List<Customer> getHistoryWinList(int maxRet);

	/***保存最新开奖 **/
	public NewlyWinPrize saveNewlyWinPrize(NewlyWinPrize entity);

	/**
	 * 查询最近num个月的数据
	 */
	public List<BackMoneyRequest> getBackMoneyRequestByRecently(Customer customer,int num);

	/**
	 * 统计明细收入与支出
	 */
	public List<WalletLog> getWalletLogList(String fName, Calendar fStime,
			Calendar fEtime, String fType);

	/**
	 * 根据充值订单号得到充值数据
	 */
	public PaymentRequest getPaymentRequestByNumber(String serialNumber);
    
	/**
	 * 更新提款申请失败返款
	 */
	public void updateBackMoneyRequestBackMoney(BackMoneyRequest entity);

	/**
	 * 更新提款申请
	 */
	public void updateBackMoneyRequestMoney(BackMoneyRequest entity);

	public void saveWalletSummary(Wallet wallet);


	/** 
	 * 查询统计成功与失败充值金额  
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<PaymentRequest> getPaymentRequestList(String fMoneyChannel,
			String fName, String fNumNo, Calendar fStarTime, Calendar fEndTime,
			String fStatu, String fUser, String fUserTypes, Calendar fSTime, Calendar fETime);

	/** 
	 * 根据属性得到客户对象
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Customer> getCustomerByProperty(String name, Object value);

	/** 根据唯一属性得到客户对象 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Customer getUniqueCustomerByProperty(String name, Object value);
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<WalletLog> getRecommendorsList(Customer customer, 
    		String recommendName, Calendar startTime, Calendar endTime);
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<WalletLog> getRecommendorsPage(Page<WalletLog> page, Customer customer, 
    		String recommendName, Calendar startTime, Calendar endTime);
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Customer> getRecommendorsPage2(Page<Customer> page,Customer customer, Calendar startTime, Calendar endTime);
	
	/** 根据推荐人，开始结束时间 查出被推荐人注册数 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long getRecommendorsPageNum(Page<Customer> page,Customer customer, Calendar startTime, Calendar endTime);

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = false)
	public CustomerCommission saveCommssion(CustomerCommission entity);
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<CustomerCommission> getCommissionPage(Page<CustomerCommission> page);
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Customer> getCustomerPageByNickName(Page<Customer> page, String nickName);
	
	/**更新客户提成的名字（类型）*/
	public void updateCusCommissionName(Long id, String newName);
	
	/**得到所有的Commission的名字*/
	public List<CustomerCommission> getAllCommissionNames();
	
	/**更新提成比率*/
	public void updateCustomerRation(String name, BigDecimal ration1, BigDecimal ration2);
	
	public Customer boundPhone(Customer customer);
	
	public Customer boundPhone1(Customer customer);
	
	public int count(Customer customer);
	
	/**根据电话号码得到绑定的customer*/
	public Customer getCustomerByMobileNo(String mobileNo);
	
	/**根据类型取出所有的wallert_log所有记录*/
	public List<WalletLog> getWalletLogOfLSCZ(Calendar stime, Calendar etime, String nickName, int type);
	
	/**
	 *  查出所有申请CPS并通过审核的用户
	 * @return
	 */
	public List<Customer> getCpsCustomerList();
	
	public List getThePerformance(Map m);
	
}
