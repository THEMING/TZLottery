package com.xsc.lottery.admin.action.term;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.entity.business.SupplierPay;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.SupplierPayService;
import com.xsc.lottery.service.business.SupplierService;

/** 出票商充值记录查询action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.supplierPayLogManager")
public class AdminSupplierPayLogManager extends AdminBaseAction
{
	@Autowired
    private SupplierPayService supplierPayService;
	
	@Autowired
    private SupplierService supplierService;
	
	@Autowired
    private LotteryOrderService orderService;
    
    private Calendar stime;
    
    private Calendar etime;
    
    private LotteryType type;
    
    private String name;
    
    private List<String> names;
    
    private BigDecimal changeMoney;
    
    private BigDecimal spendMoney;
    
    private BigDecimal winMoney;
    
    private List<Order> orderList = new ArrayList<Order>();
    
    private List<SupplierPay> supplierPayList = new ArrayList<SupplierPay>();
    
    private Page<Order> page;
    
    private Page<SupplierPay> page2;
    
    private int pageNo = 1;

    private int pageSize = 10;
    
    public String index()
    {
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
    	return SUCCESS;
    }
    
    public String query()
    {
    	orderList = orderService.getOrdersBySendTicketPlant(name, stime, etime);
    	supplierPayList = supplierPayService.getRecordsByName(name, stime, etime);
    	changeMoney = BigDecimal.ZERO;
    	spendMoney = BigDecimal.ZERO;
    	winMoney = BigDecimal.ZERO;
    	Order order = null;
    	for (int i = 0; i < orderList.size(); i++) {
    		order = orderList.get(i);
    		spendMoney = spendMoney.add(order.getAmount());
    		winMoney = winMoney.add(order.getWinTaxMoney());
		}
    	for (int i = 0; i < supplierPayList.size(); i++) {
    		changeMoney = changeMoney.add(supplierPayList.get(i).getMoney());
		}
    	return index();
    }
    
    public String querySupplier()
    {
    	page2 = new Page<SupplierPay>();
        page2.setPageNo(pageNo);
        page2.setPageSize(pageSize);
        page2.setAutoCount(true);
    	page2 = supplierPayService.getRecordsPageByName(page2, name, stime, etime);
    	return "supplier";
    }
    
    public String queryOrder()
    {
    	page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
    	page = orderService.getOrdersPageBySendTicketPlant(page, name, stime, etime);
    	return "order";
    }
    
    public String queryWinOrder()
    {
    	page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
    	page = orderService.getWinOrdersPageBySendTicketPlant(page, name, stime, etime);
    	return "order";
    }

	public Calendar getStime() {
		return stime;
	}

	public void setStime(Calendar stime) {
		this.stime = stime;
	}

	public Calendar getEtime() {
		return etime;
	}

	public void setEtime(Calendar etime) {
		this.etime = etime;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public BigDecimal getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}

	public BigDecimal getSpendMoney() {
		return spendMoney;
	}

	public void setSpendMoney(BigDecimal spendMoney) {
		this.spendMoney = spendMoney;
	}

	public BigDecimal getWinMoney() {
		return winMoney;
	}

	public void setWinMoney(BigDecimal winMoney) {
		this.winMoney = winMoney;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<SupplierPay> getSupplierPayList() {
		return supplierPayList;
	}

	public void setSupplierPayList(List<SupplierPay> supplierPayList) {
		this.supplierPayList = supplierPayList;
	}

	public Page<Order> getPage() {
		return page;
	}

	public void setPage(Page<Order> page) {
		this.page = page;
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

	public Page<SupplierPay> getPage2() {
		return page2;
	}

	public void setPage2(Page<SupplierPay> page2) {
		this.page2 = page2;
	}
}
