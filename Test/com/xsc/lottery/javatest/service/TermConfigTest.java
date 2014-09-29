package com.xsc.lottery.javatest.service;

import org.junit.Test;

import com.xsc.lottery.entity.business.TermTypeConfig;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.junitTest.tools.SuperTest;
import com.xsc.lottery.service.business.TermConfigService;

public class TermConfigTest extends SuperTest
{
    @Test
    public void term()
    {
        TermConfigService termconfig = (TermConfigService) springContext
                .getBean("termConfigService");
        TermTypeConfig config = termconfig.getConfigByType(LotteryType.大乐透);
        System.out.println(config.getOutPoint().name());
    }
}
