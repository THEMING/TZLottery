package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.service.LotteryBaseService;

public interface BackMoneyRequestService extends LotteryBaseService<BackMoneyRequest> {
	 @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
     public BigDecimal getSumMoneyByApplyTime(Calendar f_stime,Calendar f_etime);
}
