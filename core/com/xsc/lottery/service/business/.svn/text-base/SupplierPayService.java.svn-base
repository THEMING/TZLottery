package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.SupplierPay;
import com.xsc.lottery.service.LotteryBaseService;

public interface SupplierPayService extends LotteryBaseService<SupplierPay> {
	
	/**得到这一段时间内这个出票商的所有充值记录*/
	public abstract List<SupplierPay> getRecordsByName(String name, Calendar stime, Calendar etime);
	
	/**得到这一段时间内这个出票商的所有充值记录*/
	public abstract Page<SupplierPay> getRecordsPageByName(Page<SupplierPay> page, String name, Calendar stime, Calendar etime);
	
	/**
	 * cbj
	 * @param page
	 * @param name
	 * @param payer
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<SupplierPay> getSupplierPayByElement(Page<SupplierPay> page,String name,String payer,Calendar beginTime,Calendar endTime);
	
	/**
	 * cbj
	 * @param name
	 * @param payer
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public BigDecimal getSumSupplierPay(String name,String payer,Calendar beginTime,Calendar endTime);
	
	/**
	 * cbj
	 * 删除充值记录
	 * @param id
	 */
	public void deleteSupplierPay(Long id);
}
