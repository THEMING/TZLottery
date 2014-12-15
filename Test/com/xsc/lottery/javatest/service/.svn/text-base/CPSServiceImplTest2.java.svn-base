package com.xsc.lottery.javatest.service;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.CredentType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.junitTest.tools.SuperTest;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.WalletService;
import com.xsc.lottery.service.business.impl.CustomerServiceImpl;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.Md5Util;

public class CPSServiceImplTest2 extends SuperTest
{
    //@Test
    public void save()
    {
//        CustomerService customerService = (CustomerService) springContext.getBean("customerService");
//        Customer cut = new Customer();
//        //Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//        cut.setNickName("testMoney");
//        //cut.setPassword(encoder.encodePassword("1205208887", null));
//        cut.setPassword(Md5Util.getMD5ofStr("369369"));
//        cut.setCredentNo("610231109876654533");
//        cut.setCredentType(CredentType.IDCard);
//        cut.setEmail("test@369cai.com");
//        cut.setMobileNo("13456789078");
//        cut.setRealName("test369");
//        cut.setRegisterTime(Calendar.getInstance());
//
//        Wallet wallet = new Wallet();
//        wallet.setCustomer(cut);
//        wallet.setBalance(new BigDecimal(100));
//        cut.setWallet(wallet);
//        customerService.save(cut);
//        //必须设置
//        customerService.saveWalletSummary(wallet);
    }
    
    @Test
    public void saveP()
    {
    	CustomerService customerService = (CustomerService) springContext.getBean("customerService");
    	LotteryOrderService lotteryOrderService = (LotteryOrderService) springContext.getBean("lotteryOrderService");
    	WalletService walletLogService = (WalletService) springContext.getBean("walletService");
    	
    	Calendar curReportDate = Calendar.getInstance();
    	Calendar startTime = Calendar.getInstance();
		Calendar overTime = Calendar.getInstance();
		startTime.set(curReportDate.get(Calendar.YEAR), curReportDate.get(Calendar.MONTH), curReportDate.get(Calendar.DATE), 0, 0, 0);
		overTime.set(curReportDate.get(Calendar.YEAR), curReportDate.get(Calendar.MONTH), curReportDate.get(Calendar.DATE), 23, 59, 59);
		Customer customer = new Customer();
		customer.setId(new Long(2988));
    	
    	//总注册数
//    	Long regNum = customerService.getRecommendorsPage2(null,customer,startTime, overTime);	
//    	System.out.println("==========总注册数==================="+regNum);
//    	
//    	//消费人数
//		Long payNum = lotteryOrderService.getSumPayByCustomer(startTime, overTime, customer);
//		System.out.println("==========消费人数==================="+payNum);
		
		//充值人数
		Long rechargeNum = walletLogService.getRechargeNum(startTime, overTime, customer);
		System.out.println("==========充值人数==================="+rechargeNum);
		
		//充值金额
		BigDecimal rechargeMon = walletLogService.getRechargeMon(startTime, overTime, customer);
		System.out.println("==========充值金额==================="+rechargeMon);
    			
//        PartnerService partnerService = (PartnerService) springContext.getBean("partnerService");
//        Partner partner = new Partner();
//        partner.setNickName("testp");
//        partner.setPassword(Md5Util.getMD5ofStr("123456"));
//        
//        try {
//            partnerService.save(partner);
//        }
//        catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
    
    public void payHome()
    {
    	CustomerService customerService = (CustomerService) springContext.getBean("customerService");
    	Customer cut = customerService.getCustomerOrName("test0810");
    	if(cut == null) {
    		return;
    	}
    	WalletLog walletLog = new WalletLog(BusinessType.收入,
    			BigDecimal.valueOf(1000), BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, "测试充值", WalletLogType.直接充值, "");
        customerService.addWalletLog(cut.getWallet().getId(), walletLog);
    }
    
    public static void main(String[] args)
    {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        System.out.println(encoder.encodePassword("123313131", null));
    }
}
