package com.xsc.lottery.admin.action.term;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.SupplierService;
import com.xsc.lottery.util.LotteryTypeUtil;

/** 出票商管理action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.supplierManager")
public class AdminSupplierManager extends AdminBaseAction
{
	@Autowired
    private SupplierService supplierService;
    
    private String name;
    
    private String id;
    
    private LotteryType type;
    
    private String status;
    
    private List<Supplier> supplierList;
    
    public String index()
    {
    	supplierList = new ArrayList<Supplier>();
    	int typeNum = LotteryTypeUtil.changeLotteryTypeToNum(type);
    	supplierList = supplierService.getSupplierByType(typeNum);
    	return SUCCESS;
    }

    public String change()
    {
    	Supplier supplier = supplierService.findById(Long.valueOf(id));
    	supplier.setName(name);
    	if (name.equals("我中啦")) {
			supplier.setCode(0);
		}
    	else if (name.equals("大赢家")){
			supplier.setCode(1);
		}
    	else {
			throw new RuntimeException("出票商名称错误");
		}
    	int statusNum = LotteryTypeUtil.changeSupplierStatusToNum(status);
    	supplier.setStatus(statusNum);
    	supplierService.save(supplier);
    	return index();
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Supplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
