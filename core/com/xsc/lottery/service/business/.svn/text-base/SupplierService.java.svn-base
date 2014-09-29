package com.xsc.lottery.service.business;

import java.util.List;

import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.service.LotteryBaseService;

public interface SupplierService extends LotteryBaseService<Supplier> {
    //得到所有的Supplier
    public List<Supplier> getAllSupplierList();
    //得到所有的可用的Supplier
    public List<Supplier> getAllActiveSupplierList();
    //得到某类彩种可用的Supplier
    public List<Supplier> getAllActiveSupplierList(int type);
    
    /**根据Code得到相应的Supplier*/
    public Supplier getSupplierByCode(int code);
    /**根据Name得到相应的Supplier*/
    public Supplier getSupplierByName(String name);
	/**根据彩种和出票商编号来找到相应的出票商列表的数据*/
	public Supplier getSupplierByTypeAndCode(int type, int code);
	/**根据彩种和出票商名称来找到相应的出票商列表的数据*/
	public Supplier getSupplierByTypeAndName(int type, String name);
    /**根据彩种得到相应的Supplier*/
	public List<Supplier> getSupplierByType(int type);
   
}
