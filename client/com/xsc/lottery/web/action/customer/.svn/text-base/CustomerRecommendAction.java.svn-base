package com.xsc.lottery.web.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.recommend")
public class CustomerRecommendAction extends LotteryClientBaseAction
{
	@Autowired
    private CustomerService customerService;
	
	private Page<WalletLog> page;
    private int pageNo = 1;
    private int pageSize = 20;
    
	private Page<Customer> page2;
    public Page<Customer> getPage2() {
		return page2;
	}

	public void setPage2(Page<Customer> page2) {
		this.page2 = page2;
	}

	public int getPageNo2() {
		return pageNo2;
	}

	public void setPageNo2(int pageNo2) {
		this.pageNo2 = pageNo2;
	}

	public int getPageSize2() {
		return pageSize2;
	}

	public void setPageSize2(int pageSize2) {
		this.pageSize2 = pageSize2;
	}

	private int pageNo2 = 1;
    private int pageSize2 = 20;
    
    private Calendar startTime;
    private Calendar endTime;
    
    private Customer customer;
    private String recommendName;
    
    private BigDecimal sumMoney1 = new BigDecimal(0);
    private BigDecimal sumMoney2 = new BigDecimal(0);
    
    private BigDecimal totalSumMoney1 = new BigDecimal(0);
    private BigDecimal totalSumMoney2 = new BigDecimal(0);
    
	public BigDecimal getTotalSumMoney1()
	{
		return totalSumMoney1;
	}

	public BigDecimal getTotalSumMoney2()
	{
		return totalSumMoney2;
	}

	public String index()
    {
    	customer = this.getCurCustomer();
    	if(customer == null) {
    		return "login";
    	}
    	
        page = new Page<WalletLog>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getRecommendorsPage(page, customer, 
        		recommendName, startTime, endTime);
        
        page2 = new Page<Customer>();
        page2.setPageNo(pageNo2);
        page2.setPageSize(pageSize2);
        page2.setAutoCount(true);
        page2 = customerService.getRecommendorsPage2(page2, customer,null,null);
        
       
        //calculateCurrentPageDetail();
        calculateAllDetail();
    	return SUCCESS;
    }
	
	private void calculateCurrentPageDetail()
	{
		List<WalletLog> list = page.getResult();
		for (WalletLog wl : list) {
			BigDecimal money = BigDecimal.ZERO;
            if (WalletLogType.LOTTERY_IN.contains(wl.getType())) {
            	money = money.add(wl.getOutMoney());
            }
            else if (WalletLogType.LOTTERY_OUT.contains(wl.getType())) {
            	money = money.subtract(wl.getInMoney());
            }
            
            if(wl.getWallet().getCustomer().getSuperior() == customer) {
            	sumMoney1 = sumMoney1.add(money.multiply(customer.getSuperRatio()));
            }
            else if(wl.getWallet().getCustomer().getSsuperior() == customer) {
            	sumMoney2 = sumMoney2.add(money.multiply(customer.getSsuperRatio()));
            }
        }
	}
	
	private void calculateAllDetail()
	{
		List<WalletLog> list = customerService.getRecommendorsList(customer, 
					recommendName, startTime, endTime);
		for (WalletLog wl : list) {
			BigDecimal money = BigDecimal.ZERO;
            if (WalletLogType.LOTTERY_IN.contains(wl.getType())) {
            	money = money.add(wl.getOutMoney());
            }
            else if (WalletLogType.LOTTERY_OUT.contains(wl.getType())) {
            	money = money.subtract(wl.getInMoney());
            }
            
            if(wl.getWallet().getCustomer().getSuperior() == customer) {
            	totalSumMoney1 = totalSumMoney1.add(money.multiply(customer.getSuperRatio()));
            }
            else if(wl.getWallet().getCustomer().getSsuperior() == customer) {
            	totalSumMoney2 = totalSumMoney2.add(money.multiply(customer.getSsuperRatio()));
            }
        }
	}
	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public Calendar getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Calendar startTime)
	{
		this.startTime = startTime;
	}

	public Calendar getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Calendar endTime)
	{
		this.endTime = endTime;
	}

	public String getRecommendName()
	{
		return recommendName;
	}

	public void setRecommendName(String recommendName)
	{
		this.recommendName = recommendName;
	}

	public Page<WalletLog> getPage()
	{
		return page;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public BigDecimal getSumMoney1()
	{
		return sumMoney1;
	}

	public BigDecimal getSumMoney2()
	{
		return sumMoney2;
	}
	
	public String getFriends()
	{
		customer = this.getCurCustomer();
    	if(customer == null) {
    		return "login";
    	}
    	
        
        page2 = new Page<Customer>();
        page2.setPageNo(pageNo);
        page2.setPageSize(pageSize);
        page2.setAutoCount(true);
        page2 = customerService.getRecommendorsPage2(page2, customer,null,null);
        
      
    	return SUCCESS;
	}
}
