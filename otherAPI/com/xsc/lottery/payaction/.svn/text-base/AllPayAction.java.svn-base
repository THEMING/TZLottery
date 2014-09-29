package com.xsc.lottery.payaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Allpay.RequestAct")
public class AllPayAction extends LotteryClientBaseAction
{

    private BigDecimal deposit_money;
    private String orderId;
    private Customer customer;

    @Autowired
    private PaymentRequestService paymentRequestService;

    public String index()
    {
        customer = getCurCustomer();
        PaymentRequest payreuqest = paymentRequestService.findByProperty(
                "serialNumber", orderId).get(0);
        
        deposit_money = payreuqest.getMoney();
        if (payreuqest.isFinish()) {
            return SUCCESS;
        }
        
        return ERROR;
    }

    public BigDecimal getDeposit_money()
    {
        return deposit_money;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public Customer getCustomer()
    {
        return customer;
    }
}
