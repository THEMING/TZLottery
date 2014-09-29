package com.xsc.lottery.action.partner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.business.LotteryOrderService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("partnerManageAct")
public class PartnerManageAction extends PartnerBaseAction
{
    @Autowired
    private LotteryOrderService orderService;
    
    private int pageNo = 1;

    private int pageSize = 20;
    
    private OrderStatus[] statuses = OrderStatus.values();
    
    private LotteryType[] lotteryTypes = LotteryType.values();
    
    private Page<Order> page;
    
    private String buyType;
    
    private String status;
    
    private Calendar buyStartTime;

    private Calendar buyEndTime;
    
    private String lotteryType;
    
    private UserType userType;
    
    private BigDecimal successOutMoney = new BigDecimal(0); //成功出票金额
    private long successPlanNum = 0;
    private long totalPlanNum = 0;
    private BigDecimal failOutMoney = new BigDecimal(0);;    //失败出票金额
    private long customerNum = 0;
    
    public String index()
    {
        Partner partner = this.getCurClient();
        if(partner == null) {
            return "login";
        }
        
        userType = partner.getUserType();
        page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getOrderPage(page, null, null, lotteryType,
                status, null, buyType, buyStartTime, buyEndTime, null,
                null, null, null, null,
                null, null, null, userType,null);
        logger.info("page size " + page.getTotalCount());
        logger.info("page result size " + page.getResult().size());
        calculate(page.getResult());
        return SUCCESS;
    }

    private void calculate(List<Order> list)
    {
        Map<String, String> nameMap = new HashMap<String, String>();
        for (Order o : list) {
            if (o.getStatus().equals(OrderStatus.出票成功) || 
                    o.getStatus().equals(OrderStatus.部分出票成功)) {
                successOutMoney = successOutMoney.add(o.getOutAmount());
                successPlanNum ++;
            }
            
            if (o.getStatus().equals(OrderStatus.出票失败)) {
                failOutMoney = failOutMoney.add(o.getAmount());
            }
            
            totalPlanNum ++;
            
            nameMap.put(o.getCustomer().getNickName(), o.getCustomer()
                    .getNickName());
            customerNum = nameMap.size();
        }
    }
    public Calendar getBuyStartTime()
    {
        return buyStartTime;
    }

    public void setBuyStartTime(Calendar buyStartTime)
    {
        this.buyStartTime = buyStartTime;
    }

    public Calendar getBuyEndTime()
    {
        return buyEndTime;
    }

    public void setBuyEndTime(Calendar buyEndTime)
    {
        this.buyEndTime = buyEndTime;
    }
    
    public UserType getUserType()
    {
        return userType;
    }

    public String getBuyType()
    {
        return buyType;
    }

    public void setBuyType(String buyType)
    {
        this.buyType = buyType;
    }

    public OrderStatus[] getStatuses()
    {
        return statuses;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getLotteryType()
    {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType)
    {
        this.lotteryType = lotteryType;
    }

    public LotteryType[] getLotteryTypes()
    {
        return lotteryTypes;
    }

    public Page<Order> getPage()
    {
        return page;
    }

    public BigDecimal getSuccessOutMoney()
    {
        return successOutMoney;
    }

    public long getSuccessPlanNum()
    {
        return successPlanNum;
    }

    public long getTotalPlanNum()
    {
        return totalPlanNum;
    }

    public BigDecimal getFailOutMoney()
    {
        return failOutMoney;
    }

    public long getCustomerNum()
    {
        return customerNum;
    }
}
