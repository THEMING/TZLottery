package com.xsc.lottery.javatest.service;

import java.util.Calendar;

import org.junit.Test;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.junitTest.tools.SuperTest;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;

public class MatchArrangeServiceTest extends SuperTest
{
    @Test
    public void term()
    {
        LotteryType type = LotteryType.足彩14场;
        MatchArrangeService matchArrangeService = (MatchArrangeService) springContext
                .getBean("matchArrangeService");
        LotteryTermService termservic = (LotteryTermService) springContext
                .getBean("lotteryTermService");
        
        LotteryTerm term = termservic.getCurrentTerm(type);
        
        if(term == null) {
            term = createCurrentTerm(type);
        }
        
        MatchArrange arrange = new MatchArrange();
        arrange.setLotteryType(type);
        arrange.setMatchTime(Calendar.getInstance());
        arrange.setMatchName("match1");
        arrange.setHomeTeam("homeTeam");
        arrange.setAwaryTeam("awaryTeam");
        arrange.setBoutIndex("1");
        arrange.setTerm(term);
        
        try {
            matchArrangeService.save(arrange);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private LotteryTerm createCurrentTerm(LotteryType type) 
    {
        LotteryTermService termservic = (LotteryTermService) springContext
                .getBean("lotteryTermService");
        LotteryTerm term = new LotteryTerm();
        term.setTermNo("20110000");
        term.setType(type);
        term.setOpenPrizeTime(Calendar.getInstance());
        term.setStartSaleTime(Calendar.getInstance());
        term.setStopSaleTime(Calendar.getInstance());
        term.setStopTogetherSaleTime(Calendar.getInstance());
        term.setSendPrizeTime(Calendar.getInstance());
        term.setOutPoint(SendTicketPlat.大赢家);
        
        try {
            termservic.save(term);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return term;
    }
}
