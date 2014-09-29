package com.xsc.lottery.service.business;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.service.LotteryBaseService;

public interface PaymentRequestService extends LotteryBaseService<PaymentRequest>
{
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PaymentRequest> findByProperty(String propertyName, Object value);
}
