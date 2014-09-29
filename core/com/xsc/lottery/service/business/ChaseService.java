package com.xsc.lottery.service.business;

import java.util.List;

import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.enumerate.ChaseItermStatus;
import com.xsc.lottery.entity.enumerate.ChaseStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.LotteryBaseService;

public interface ChaseService extends LotteryBaseService<Chase>
{

    public List<ChaseItem> getChaseItemByCustomer(Customer customer);

    public List<ChaseItem> getChaseItemByTypeAndTerm(LotteryTerm term,
            ChaseItermStatus status, ChaseStatus chasestatus);

    public ChaseItem saveChaseItem(ChaseItem entity);

    public Chase getChaseByPlan(Plan plan);

    public List<ChaseItem> getChaseItemByChase(Chase chase);

    public Chase saveChaseAndIterm(Chase entity);

    public ChaseItem getChaseItem(Long itemId);

    public void stopChse(Chase backChase);
    
    public List<ChaseItem> getChaseItemByTermNO(String termNo, LotteryType type);
}
