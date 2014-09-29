package com.xsc.lottery.handle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.enumerate.LotteryType;

/**
 * factory for all lotteries to be registered
 */
@Component
public class LotteryHandleFactory
{
    private Map<LotteryType, BaseLotteryHandle> lotteryHandleMap = 
        new HashMap<LotteryType, BaseLotteryHandle>(2, 1);

    public void registerHandleInMap(BaseLotteryHandle lotteryHandle)
    {
        lotteryHandleMap.put(lotteryHandle.getLotteryType(), lotteryHandle);
    }

    public BaseLotteryHandle getLotteryHandleFactory(LotteryType lotteryType)
    {
        BaseLotteryHandle lotteryHandle = lotteryHandleMap.get(lotteryType);
        return lotteryHandle;
    }
}
