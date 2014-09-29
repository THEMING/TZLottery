package com.xsc.lottery.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryOrderService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("buyLottery.betPlan")
public class LotteryPlanActin extends LotteryClientBaseAction
{
    @Autowired
    private LotteryOrderService lotteryOrderService;

    private List<Order> list;

    private String type;

    public String index()
    {
        Customer customer = this.getCurCustomer();
        if(null != customer) {
            list = lotteryOrderService.getOrder(customer, LotteryType.enToType(type));
        }
        
        if(list != null && list.size() >= 10) {
            list = list.subList(0, 9);
        }
        
        return "result";
    }

    public List<Order> getList()
    {
        return list;
    }

    public void setLotteryOrderService(LotteryOrderService lotteryOrderService)
    {
        this.lotteryOrderService = lotteryOrderService;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
}
