package com.xsc.lottery.javatest.service;

import java.math.BigDecimal;

import org.junit.Test;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.RechargeGift;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.junitTest.tools.SuperTest;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.service.business.SoftwareRegisterService;
import com.xsc.lottery.task.email.Email369TaskExcutor;
import com.xsc.lottery.util.MathUtil;

public class Payment extends SuperTest
{
    private PaymentRequestService paymentRequestService;
    private CustomerService customerService;
    private SoftwareRegisterService softwareRegisterService;
    private Email369TaskExcutor email369TaskExcutor;
    
	@Test
	public void test()
	{
		customerService = (CustomerService) springContext.getBean("customerService");
		paymentRequestService = (PaymentRequestService) springContext.getBean("paymentRequestService");
		softwareRegisterService = (SoftwareRegisterService) springContext.getBean("softwareRegisterService");
		email369TaskExcutor = (Email369TaskExcutor) springContext.getBean("email369TaskExcutor");
		
		Customer customer = customerService.getCustomerOrName("test369");
		
		BigDecimal deposit_money = BigDecimal.valueOf(10);
		PaymentRequest payreuqest = new PaymentRequest(MathUtil
                .getSerialNumber(20), customer, deposit_money, Bank.支付宝,
                MoneyChannel.支付宝, BigDecimal.ZERO);
        paymentRequestService.save(payreuqest);
        
        WalletLog walletLog = new WalletLog(BusinessType.收入,
        		deposit_money, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, "用户支付宝充值",
                WalletLogType.账户充值, "");
        customerService.addWalletLog(payreuqest.getCustomer()
                .getWallet().getId(), walletLog);
        payreuqest.setFinish(true);
        paymentRequestService.update(payreuqest);
        
        
        email369TaskExcutor.addNotifyPayment(payreuqest);
        
        RechargeGift gift = new RechargeGift();
        gift.setCustomer(customer);
        gift.setPrize(true);
        gift.setPaymentrequest(payreuqest);
        gift.setReceive(false);
        try {
        	softwareRegisterService.save(gift);
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
	}
}
