package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.service.LotteryBaseService;

public interface WalletService extends LotteryBaseService<Wallet>{
	/**
	 * 统计余额
	 * @return
	 */
	 @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
      public BigDecimal getSumBalance();
	 
	  /**
	   * 统计被冻结金额
	   * @return
	   */
	 @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	 public BigDecimal getSumFreezeMoney();
	 
	 /**
	   * 统计某时间段内充值人数 根据推荐人，开始结束时间
	   * @return
	   */
	 @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	 public Long getRechargeNum(Calendar startTime, Calendar endTime,Customer customer);
	 
	 /**
	   * 统计某时间段内充值总额 根据推荐人，开始结束时间
	   * @return
	   */
	 @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	 public BigDecimal getRechargeMon(Calendar startTime, Calendar endTime, Customer customer);
}
