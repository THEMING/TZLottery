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
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.entity.business.SupplierPay;
import com.xsc.lottery.service.business.SupplierPayService;
import com.xsc.lottery.service.business.SupplierService;
import com.xsc.lottery.util.LotteryTypeUtil;

/** 出票商充值记录管理action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.supplierPayManager")
public class AdminSupplierPayManager extends AdminBaseAction
{
	@Autowired
    private SupplierPayService supplierPayService;
	
	@Autowired
    private SupplierService supplierService;

    private BigDecimal money;
    
    private Calendar time;
    
    private String payer;
    
    private String recorder;
    
    private String status;
    
    private String memo;
    
    private String name;
    
    private List<String> names;
    
    private Calendar beginTime;//开始日期
    
    private Calendar endTime;//结束日期
    
    private Page<SupplierPay> page;

    private int pageNo = 1;

    private int pageSize = 10;
    
    private BigDecimal sumMoney;
    
    private Long id;
    
    private String msg;
    
    public String index()
    {
    	getAllNames();
    	page=new Page<SupplierPay>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page=supplierPayService.getSupplierPayByElement(page, name, payer, beginTime, endTime);
        sumMoney=supplierPayService.getSumSupplierPay(name, payer, beginTime, endTime);
    	return SUCCESS;
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
    /**
     * 充值金额
     * @return
     */
    public String add()
    {
    	SupplierPay supplierPay = new SupplierPay();
    	supplierPay.setMemo(memo);
    	supplierPay.setMoney(money);
    	supplierPay.setPayer(payer);
    	supplierPay.setRecorder(recorder);
    	int supplierPayStatus = LotteryTypeUtil.changeSupplierPayStatusToNum(status);
    	supplierPay.setStatus(supplierPayStatus);
    	supplierPay.setSupplierName(name);
    	supplierPay.setTime(time);
    	supplierPayService.save(supplierPay);
    	return index();
    }
    
    public String deleteSupplierPay(){
    	try{
    	   supplierPayService.deleteSupplierPay(id);
    	   msg="删除成功！";
    	}catch(Exception e){
    		msg="删除失败！";
    	}
    	return index();
    }

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
	public Calendar getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}
	public Calendar getEndTime() {
		return endTime;
	}
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	public Page<SupplierPay> getPage() {
		return page;
	}
	public void setPage(Page<SupplierPay> page) {
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

	public BigDecimal getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
