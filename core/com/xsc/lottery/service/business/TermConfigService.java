package com.xsc.lottery.service.business;

import com.xsc.lottery.entity.business.TermTypeConfig;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.LotteryBaseService;

public interface TermConfigService extends LotteryBaseService<TermTypeConfig>
{
    public TermTypeConfig getConfigByType(LotteryType type);
}
