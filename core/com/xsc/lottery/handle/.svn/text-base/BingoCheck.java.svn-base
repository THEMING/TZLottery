package com.xsc.lottery.handle;

import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;

public interface BingoCheck extends java.lang.Cloneable
{
    /**
     * 执行开奖处理
     */
    public List<WinDescribeTicket> execute(Ticket ticket,
            Map<String, PrizeLevel> bingoMap, String result);

    /**
     * 是否中奖
     */
    public boolean isBingo();

    /**
     * 合并结果,开奖的时候先开票，再把票的结果合并后传给LotteryPlan
     */
    public void addCheck(BingoCheck otherCheck);

    public BingoCheck clone() throws CloneNotSupportedException;

}
