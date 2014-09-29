package com.xsc.lottery.admin.action.customer;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.service.business.BackMoneyRequestService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.SupplierPayService;
import com.xsc.lottery.service.business.SupplierService;
import com.xsc.lottery.service.business.WalletService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.financialCollect")
public class FinancialCollectAction extends AdminBaseAction
{

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SupplierPayService supplierPayService;
    
    @Autowired
    private BackMoneyRequestService backMoneyRequestService;
    
    @Autowired
    private WalletService walletService;

    @Autowired
    private LotteryOrderService orderService;
    
    @Autowired
    private SupplierService supplierService; 
    
    private Calendar f_stime;//查询开始时间

    private Calendar f_etime;//查询结束时间
    
    private List orders;//各种彩种统计
    
    private List sumMoney;//所有彩种统计汇总
    
    private BigDecimal lashouMoney;//拉手余额统计
    
    private BigDecimal zhifubaoMoney;//支付宝手机充值统计
    
    private BigDecimal kuaiqianMoney;//快钱充值统计
    
    private BigDecimal allMoney=new BigDecimal(0.00);//总充值金额统计
    
    private BigDecimal prePayMoney;//预付款金额统计
    
    private BigDecimal tixianMoney;//提现金额统计
    
    private BigDecimal balance;//用户余额统计
    
    private BigDecimal freezeMoney;//用户冻结金额统计
    
    private BigDecimal consumptionMoney;//消耗金额统计
    
    private BigDecimal xitongkoukuanMoney;//系统扣款统计
    
    private BigDecimal zhijiechongzhiMoney;//直接充值统计
    
    private List<String> names;//所有出票商
    
    private List<String> sources;//所有购彩类型
    
    private String ticketThirdName;//出票商
    
    private String source;//购彩类型
    /**
     * 统计功能
     * @author cbj
     * @return
     */
    public String index()
    { 
    	try {
    		getAllNames();
        	getAllSources();
           	orders= orderService.getOrderOtherSum(f_stime,f_etime,ticketThirdName,source);//各种彩种统计
           	sumMoney=orderService.getAllOrderOtherSum(f_stime, f_etime,ticketThirdName,source);//所有彩种统计汇总
           	
           	lashouMoney=orderService.getLaShouSum(f_stime, f_etime);//拉手余额统计
           	zhifubaoMoney=orderService.getZhiFuBaoSum(f_stime, f_etime);//支付宝手机充值统计
           	kuaiqianMoney=orderService.getKuaiQianSum(f_stime, f_etime);//快钱充值统计
           	
           	xitongkoukuanMoney=orderService.getSumTypeWalleLogXitongkoukuan(f_stime, f_etime);//系统扣款统计
           	zhijiechongzhiMoney=orderService.getSumTypeWalleLogZhijiechongzhi(f_stime, f_etime);//直接充值统计
           	
           	//总充值金额统计
           	if(lashouMoney!=null){
           		allMoney= allMoney.add(lashouMoney);
           	}
           	if(zhifubaoMoney!=null){
           		allMoney= allMoney.add(zhifubaoMoney);
           	}
        	if(kuaiqianMoney!=null){
        		allMoney= allMoney.add(kuaiqianMoney);
           	}
        	
        	
        	prePayMoney=supplierPayService.getSumSupplierPay(null, null, f_stime, f_etime);//预付款金额统计
        	
        	tixianMoney=backMoneyRequestService.getSumMoneyByApplyTime(f_stime, f_etime);//提现金额统计
        	
        	balance=walletService.getSumBalance();//用户余额统计
        	
        	freezeMoney=walletService.getSumFreezeMoney();//用户冻结金额统计
        	
            Object[] objs=(Object[])sumMoney.get(0);
            BigDecimal m1=new BigDecimal(0.00);
            Object obj1=objs[1];
            if(obj1!=null){
            	m1=(BigDecimal)obj1;
            }
            BigDecimal m2=(BigDecimal)new BigDecimal(0.00);
            Object obj2=objs[2];
            if(obj2!=null){
            	m2=(BigDecimal)obj2;
            }
        	consumptionMoney=m1.subtract(m2);//消耗金额统计
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return SUCCESS;
    }
    
    public InputStream getDownloadFile() throws Exception{
    	if(super.getAction().equals("dayExportExcel")){
		   return orderService.getDayInputStream(f_stime,f_etime,ticketThirdName,source);
    	}else if(super.getAction().equals("weekExportExcel")){
		   return orderService.getWeekInputStream(f_stime,f_etime,ticketThirdName,source);
    	}
    	return null;
	}
	
	
    /**
     * 日报导出
     * @return
     * @throws Exception
     */
    public String dayExportExcel() throws Exception {
		return "excel";
	}
    
    /**
     * 周报导出
     * @return
     * @throws Exception
     */
    public String weekExportExcel() throws Exception{
    	return "excel";
    }
    
    
    private void getAllNames(){
    	List<Supplier> all = supplierService.getAllSupplierList();
    	names = new ArrayList<String>();
    	for (int i = 0; i < all.size(); i++) {
    		String tempNameString = all.get(i).getName();
			if (names.contains(tempNameString)) {
				continue;
			}
			else {
				names.add(tempNameString);
			}
		}
    }
    
    private void getAllSources(){
    	sources=new ArrayList<String>();
    	sources.add("web");
    	sources.add("android");
    	sources.add("iphone");
    }
    
    
    public Calendar getF_stime()
    {
        return f_stime;
    }

    public void setF_stime(Calendar fStime)
    {
        f_stime = fStime;
    }

    public Calendar getF_etime()
    {
        return f_etime;
    }

    public void setF_etime(Calendar fEtime)
    {
        f_etime = fEtime;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

	public List getOrders() {
		return orders;
	}

	public void setOrders(List orders) {
		this.orders = orders;
	}

	public List getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(List sumMoney) {
		this.sumMoney = sumMoney;
	}

	public BigDecimal getLashouMoney() {
		return lashouMoney;
	}

	public void setLashouMoney(BigDecimal lashouMoney) {
		this.lashouMoney = lashouMoney;
	}

	public BigDecimal getZhifubaoMoney() {
		return zhifubaoMoney;
	}

	public void setZhifubaoMoney(BigDecimal zhifubaoMoney) {
		this.zhifubaoMoney = zhifubaoMoney;
	}

	public BigDecimal getKuaiqianMoney() {
		return kuaiqianMoney;
	}

	public void setKuaiqianMoney(BigDecimal kuaiqianMoney) {
		this.kuaiqianMoney = kuaiqianMoney;
	}

	public BigDecimal getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(BigDecimal allMoney) {
		this.allMoney = allMoney;
	}

	public BigDecimal getPrePayMoney() {
		return prePayMoney;
	}

	public void setPrePayMoney(BigDecimal prePayMoney) {
		this.prePayMoney = prePayMoney;
	}

	public BigDecimal getTixianMoney() {
		return tixianMoney;
	}

	public void setTixianMoney(BigDecimal tixianMoney) {
		this.tixianMoney = tixianMoney;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	public BigDecimal getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public BigDecimal getXitongkoukuanMoney() {
		return xitongkoukuanMoney;
	}

	public void setXitongkoukuanMoney(BigDecimal xitongkoukuanMoney) {
		this.xitongkoukuanMoney = xitongkoukuanMoney;
	}

	public BigDecimal getZhijiechongzhiMoney() {
		return zhijiechongzhiMoney;
	}

	public void setZhijiechongzhiMoney(BigDecimal zhijiechongzhiMoney) {
		this.zhijiechongzhiMoney = zhijiechongzhiMoney;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getTicketThirdName() {
		return ticketThirdName;
	}

	public void setTicketThirdName(String ticketThirdName) {
		this.ticketThirdName = ticketThirdName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}
    
}
