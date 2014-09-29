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
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.Md5Util;

public class CustomerServiceImplTest extends SuperTest
{
    //@Test
    public void save()
    {
        CustomerService customerService = (CustomerService) springContext.getBean("customerService");
        Customer cut = new Customer();
        //Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        cut.setNickName("testMoney");
        //cut.setPassword(encoder.encodePassword("1205208887", null));
        cut.setPassword(Md5Util.getMD5ofStr("369369"));
        cut.setCredentNo("610231109876654533");
        cut.setCredentType(CredentType.IDCard);
        cut.setEmail("test@369cai.com");
        cut.setMobileNo("13456789078");
        cut.setRealName("test369");
        cut.setRegisterTime(Calendar.getInstance());

        Wallet wallet = new Wallet();
        wallet.setCustomer(cut);
        wallet.setBalance(new BigDecimal(100));
        cut.setWallet(wallet);
        customerService.save(cut);
        //必须设置
        customerService.saveWalletSummary(wallet);
    }
    
    @Test
    public void saveP()
    {
        PartnerService partnerService = (PartnerService) springContext.getBean("partnerService");
        Partner partner = new Partner();
        partner.setNickName("testp");
        partner.setPassword(Md5Util.getMD5ofStr("123456"));
        
        try {
            partnerService.save(partner);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
