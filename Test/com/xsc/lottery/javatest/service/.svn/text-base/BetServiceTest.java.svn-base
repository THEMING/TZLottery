package com.xsc.lottery.javatest.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dyj.sendticket.DyjTicketTreatmentWork;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.junitTest.tools.SuperTest;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.web.validate.BetValidate;

@SuppressWarnings("unused")
public class BetServiceTest extends SuperTest
{
    @SuppressWarnings("static-access")
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void selfbet()   /** 应该是个事务 */
    {
        LotteryType lotteryType = LotteryType.足彩14场;
        LotteryTermService termServic = (LotteryTermService) this.springContext
                .getBean("lotteryTermService");
        LotteryTerm lotteryTerm = termServic.getCurrentTerm(lotteryType);
        DyjTicketTreatmentWork work = (DyjTicketTreatmentWork) this.springContext
                .getBean("dyjTicketTreatmentWork");
        
        BetValidate betValidate = (BetValidate) this.springContext
                .getBean("betValidate");
        
        CustomerService customerService = (CustomerService) this.springContext
                .getBean("customerService");
        
        LotteryOrderService lotteryOrderService = (LotteryOrderService) this.springContext
                .getBean("lotteryOrderService");
        
        Customer customer = customerService.findById(2506L);
        System.out.println(customer.getNickName() + " 投注...");
        
        String codes = "fs-3,3,3,1,1,1,3,0,0,0,3,1,13";  //足球14
        //String codes = "fs-1,2,3,4,5,6|12";    //双色球
        
        BigDecimal money = BigDecimal.valueOf(4);
        BigDecimal oneMoney = BigDecimal.valueOf(2);
        
        int multiple = 1;
        List<PlanItem> planItemList = null;
        
        try {
            planItemList = betValidate.validateReckon(lotteryType, 
                    codes.split("\\^"), money, null, multiple, oneMoney);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
        Plan plan = new Plan(lotteryType, new BigDecimal(money.intValue() / multiple), oneMoney);
        Order order = null;
        
        if(lotteryTerm == null) {
            System.out.println(lotteryType + " 当前期不可用");
            return;
        }
        
        //下面是个事务，得测试
        try {
            WalletLog walletLog = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, plan.getMoney().multiply(
                            new BigDecimal(multiple)), BigDecimal.ZERO,
                    BigDecimal.ZERO, plan.getType().name(), WalletLogType.代购费用,
                    plan.getNumberNo());
            
            customerService.addWalletLog(customer.getWallet().getId(), walletLog);
            
            order = lotteryOrderService.createBetOrder(customer, lotteryType, plan, 
                    planItemList, lotteryTerm, multiple);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
        if(order != null) {
            work.addTaker(order);
        }
    }
}
