package com.xsc.lottery.admin.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.CustomerCommission;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.service.business.CpsReportService;
import com.xsc.lottery.service.business.CustomerService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.customerCommission")
public class CustomerCommissionAction extends AdminBaseAction
{
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private CpsReportService cpsReportService;
	
	private Page<CustomerCommission> page;
	private Page<WalletLog> walletLogPage;
	private Page<CustomerCommission> commissionPage;
	private Page<Customer> customerPage;
    private CustomerCommission customerCommission;
    private List<CustomerCommission> commissionNames;
    private int pageNo = 1;
    private int pageSize = 15;
    
    private boolean open1 = true;
    private boolean open2 = true;
    private String name;
    private String nickName;
    private String ratio1;
    private String ratio2;
    private String schemeName;
    private String message;
    private Long idTemp;
    private String selectVal;
    private Calendar startTime;
    private Calendar endTime;
//    private BigDecimal sumMoney1 = new BigDecimal(0);
//    private BigDecimal sumMoney2 = new BigDecimal(0);
    
    private BigDecimal totalSumMoney1 = new BigDecimal(0);
    private BigDecimal totalSumMoney2 = new BigDecimal(0);

    
	public String index()
    {
		page = new Page<CustomerCommission>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getCommissionPage(page);
        return SUCCESS;
    }
	
	public String list()
	{
		customerPage = new Page<Customer>();
		customerPage.setPageNo(pageNo);
		customerPage.setPageSize(pageSize);
		customerPage.setAutoCount(true);
		customerPage = customerService.getCustomerPageByNickName(customerPage, nickName);
		commissionNames = customerService.getAllCommissionNames();
        return "list";
	}
	
	public String save()
    {
		if(name == null || ratio1 == null) {
			message = "请输入必要的参数";
			return index();
		}
		CustomerCommission newCommission = new CustomerCommission();
		newCommission.setName(name);
		newCommission.setOpen1(open1);
		//newCommission.setOpen2(open2);
		newCommission.setRatio1(new BigDecimal(ratio1));
		//newCommission.setRatio2(new BigDecimal(ratio2));
		customerService.saveCommssion(newCommission);
        return index();
    }
	
	public String update()
    {
		try{
			customerService.updateCusCommissionName(idTemp, selectVal);
			this.addActionMessage("更新成功!");
		} catch(Exception e)
		{
			this.addActionMessage("更新失败！");
		}
        return "ok";
    }
	
	//查出每个人所有未结算的佣金
	public String calculate()
	{
		customerPage = new Page<Customer>();
		//calculateAllDetail();
		customerPage.setPageNo(pageNo);
		customerPage.setPageSize(pageSize);
		customerPage.setAutoCount(true);
        customerPage = customerService.getCustomerPageByNickName(customerPage, nickName);
        cpsReportService.calculate(startTime, endTime, false, customerPage.getResult());
		return "calculate";
	}
	
	private void calculateAllDetail()
	{
		customerPage = new Page<Customer>();
		List<Customer> customer = customerService.getCustomerPageByNickName(customerPage, nickName).getResult();
		for(int i=0; i<customer.size(); i++)
		{
			totalSumMoney1 = new BigDecimal(0);
			totalSumMoney2 = new BigDecimal(0);
			Customer customerTemp = customer.get(i);
			/*
			BigDecimal sratio=customerTemp.getSuperRatio(); 
			BigDecimal sratio2=customerTemp.getSsuperRatio();
			List<WalletLog> list = customerService.getRecommendorsList(customerTemp, 
					"", startTime, endTime);
			for (WalletLog wl : list) {
				BigDecimal money = BigDecimal.ZERO;
	            if (WalletLogType.LOTTERY_IN.contains(wl.getType())) {
	            	money = money.add(wl.getOutMoney());
	            }
	            else if (WalletLogType.LOTTERY_OUT.contains(wl.getType())) {
	            	money = money.subtract(wl.getInMoney());
	            }
	            
	            if(wl.getWallet().getCustomer().getSuperior() == customerTemp) {
	            	totalSumMoney1 = totalSumMoney1.add(money.multiply(sratio));
	            }
	            else if(wl.getWallet().getCustomer().getSsuperior() == customerTemp) {
	            	totalSumMoney2 = totalSumMoney2.add(money.multiply(sratio2));
	            }
	        }
			customerTemp.setSuperCommission(totalSumMoney1);
			customerTemp.setSsuperCommission(totalSumMoney2);
			*/
			customerService.save(customerTemp);
		}
	}
	
	public String queryComm()
	{
		customerPage = new Page<Customer>();
		customerPage.setPageNo(pageNo);
		customerPage.setPageSize(pageSize);
		customerPage.setAutoCount(true);
        customerPage = customerService.getCustomerPageByNickName(customerPage, nickName);
        for(Customer customer:customerPage.getResult())
        {
        	customer.setSuperCommission(cpsReportService.sumMoneyByStateAndCustomerId(startTime, endTime, false, customer));
        }
		return "queryFinished";
	}
	
	public String amend()
	{
		try{
			BigDecimal r1 = new BigDecimal(ratio1);
			BigDecimal r2 = new BigDecimal(ratio2);
			customerService.updateCustomerRation(schemeName, r1, r2);
			message = "修改成功";
		}
		catch(Exception e)
		{
			message = "修改失败";
		}
		return "amend";
	}

	public CustomerCommission getCustomerCommission()
	{
		return customerCommission;
	}

	public void setCustomerCommission(CustomerCommission customerCommission)
	{
		this.customerCommission = customerCommission;
	}

	public boolean isOpen1()
	{
		return open1;
	}

	public void setOpen1(boolean open1)
	{
		this.open1 = open1;
	}

	public boolean isOpen2()
	{
		return open2;
	}

	public void setOpen2(boolean open2)
	{
		this.open2 = open2;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRatio1()
	{
		return ratio1;
	}

	public void setRatio1(String ratio1)
	{
		this.ratio1 = ratio1;
	}

	public String getRatio2()
	{
		return ratio2;
	}

	public void setRatio2(String ratio2)
	{
		this.ratio2 = ratio2;
	}

	public Page<CustomerCommission> getPage()
	{
		return page;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Page<Customer> getCustomerPage() {
		return customerPage;
	}

	public void setCustomerPage(Page<Customer> customerPage) {
		this.customerPage = customerPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPage(Page<CustomerCommission> page) {
		this.page = page;
	}

	public Page<CustomerCommission> getCommissionPage() {
		return commissionPage;
	}

	public void setCommissionPage(Page<CustomerCommission> commissionPage) {
		this.commissionPage = commissionPage;
	}

	public Long getIdTemp() {
		return idTemp;
	}

	public void setIdTemp(Long idTemp) {
		this.idTemp = idTemp;
	}

	public String getSelectVal() {
		return selectVal;
	}

	public void setSelectVal(String selectVal) {
		this.selectVal = selectVal;
	}

	public List<CustomerCommission> getCommissionNames() {
		return commissionNames;
	}

	public void setCommissionNames(List<CustomerCommission> commissionNames) {
		this.commissionNames = commissionNames;
	}

	public Page<WalletLog> getWalletLogPage() {
		return walletLogPage;
	}

	public void setWalletLogPage(Page<WalletLog> walletLogPage) {
		this.walletLogPage = walletLogPage;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

//	public BigDecimal getSumMoney1() {
//		return sumMoney1;
//	}
//
//	public BigDecimal getSumMoney2() {
//		return sumMoney2;
//	}

	public BigDecimal getTotalSumMoney1() {
		return totalSumMoney1;
	}

	public BigDecimal getTotalSumMoney2() {
		return totalSumMoney2;
	}

	public void setTotalSumMoney1(BigDecimal totalSumMoney1) {
		this.totalSumMoney1 = totalSumMoney1;
	}

	public void setTotalSumMoney2(BigDecimal totalSumMoney2) {
		this.totalSumMoney2 = totalSumMoney2;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
}
