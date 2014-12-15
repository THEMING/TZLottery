package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.CpsDayReport;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.service.LotteryBaseService;

public interface CpsReportService extends LotteryBaseService<CpsDayReport>
{
	/**
	 * 统计佣金
	 * @throws ParseException
	 */
	public void doCpsDayReport() throws ParseException;
	
	/**
	 * 查询是否已统计
	 * @param reportDate
	 * @param customerId
	 * @return
	 */
	public CpsDayReport getCpsDayReportByDateAndCustomerId(String reportDate,Customer customer);
	
	/**
	 * 分组查询未计算的佣金
	 * @param stime
	 * @param etime
	 * @param isPay
	 * @param customerId
	 * @return
	 */
	public BigDecimal sumMoneyByStateAndCustomerId(Calendar stime, Calendar etime,Boolean isPay,Customer customer);
	
	/**
	 * 查询
	 * @param stime 开始日期
	 * @param etime 截止日期
	 * @param isPay 是否已结算
	 * @param customer cps用户
	 * @return
	 */
	public List<CpsDayReport> findReportByStateAndCustomerId(Calendar stime,Calendar etime, Boolean isPay, Customer customer);
	
	/**
	 * 结算
	 * @param stime 开始日期
	 * @param etime 截止日期
	 * @param isPay 是否已结算
	 * @param customerList
	 */
	public  void calculate(Calendar stime,Calendar etime, Boolean isPay,List<Customer> customerList);
	
	/** 获取提成情况 */
	public Map getCommissionPage(Page<CpsDayReport> page,String stime,String etime,Customer customer,String isPay);
	
}
