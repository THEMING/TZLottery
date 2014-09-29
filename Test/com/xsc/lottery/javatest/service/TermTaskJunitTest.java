package com.xsc.lottery.javatest.service;

import java.util.Calendar;

import org.junit.Test;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.junitTest.tools.SuperTest;
import com.xsc.lottery.task.term.LotteryTermTaskFactory;
import com.xsc.lottery.util.DateUtil;

@SuppressWarnings("unused")
public class TermTaskJunitTest extends SuperTest
{
    @Test
    public void term()
    {
        LotteryTerm nextTerm = new LotteryTerm();
        nextTerm.setCurrent(true);
        nextTerm.setTermNo("201001");
        // nextTerm.setType(LotteryType.时时乐);

        // Calendar now = DateUtil.parse("2010-08-20 16:10:00",
        // "yyyy-MM-dd HH:mm:ss");
        Calendar now = DateUtil.now();
        Calendar StopTogether = DateUtil.now();
        Calendar StopSale = DateUtil.now();
        Calendar OpenPrize = DateUtil.now();
        Calendar SendPrize = DateUtil.now();

        nextTerm.setStartSaleTime(now);

        StopTogether.setTime(now.getTime());
        StopTogether.add(Calendar.MINUTE, 5);
        nextTerm.setStopTogetherSaleTime(StopTogether);

        StopSale.setTime(StopTogether.getTime());
        StopSale.add(Calendar.MINUTE, 6);
        nextTerm.setStopSaleTime(StopSale);

        OpenPrize.setTime(StopSale.getTime());
        OpenPrize.add(Calendar.MINUTE, 7);
        nextTerm.setOpenPrizeTime(OpenPrize);

        SendPrize.setTime(OpenPrize.getTime());
        SendPrize.add(Calendar.MINUTE, 8);
        nextTerm.setSendPrizeTime(SendPrize);
        nextTerm.setOutPoint(SendTicketPlat.大赢家);

        // nex
        /*
         * LotteryTermService termservice =
         * (LotteryTermService)springContext.getBean("lotteryTermService");
         * termservice.save(nextTerm);
         */
        // System.out.println("----------"+nextTerm.getId());
        // termservice.stopSale(nextTerm);
        LotteryTermTaskFactory factory = (LotteryTermTaskFactory) springContext
                .getBean("lotteryTermTaskFactory");
        // factory.startLotteryTermByType(LotteryType.时时乐);
    }
}
