package com.xsc.lottery.service.business;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.service.LotteryBaseService;

public interface LotteryPlanService extends LotteryBaseService<Plan>
{
    /**
     * 描述 根据方案ID查找方案内容
     */
    public List<PlanItem> getPlanItemByPlanID(Long id);

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Plan getPlanBynumberNo(String numberNo);

}
