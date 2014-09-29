package com.xsc.lottery.dyj.sendticket.bingocheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.handle.BingoCheck;

public class PlwBingoCheck implements BingoCheck
{

    public Ticket ticket;

    private List<WinDescribeTicket> winDescribeTicket;

    private boolean isBingo;

    public void addCheck(BingoCheck otherCheck)
    {

    }

    public List<WinDescribeTicket> execute(Ticket ticket,
            Map<String, PrizeLevel> bingoMap, String result)
    {
        int dealBingoCount = 0;
        isBingo = false;
        this.ticket = ticket;
        String selas = ticket.getContent(); // 将投注号码区分解

        if (ticket.getPlayType() == PlayType.fs) {// 直选复式
            int bingoCount = BingoUtil.getBingoNumCountByAreas(selas, "\\,",
                    result, "\\,");
            if (bingoCount == 5) {
                dealBingoCount = 1 * ticket.getMultiple();
            }
        }
        winDescribeTicket = new ArrayList<WinDescribeTicket>();
        BigDecimal prize = bingoMap.get("直选").getPrize();
        if (dealBingoCount > 0) {
            WinDescribeTicket wdt = new WinDescribeTicket();
            wdt.setPrizeLevel(bingoMap.get("直选"));
            wdt.setMoney(prize.multiply(new BigDecimal(dealBingoCount)));
            wdt.setTaxmoney(prize.multiply(new BigDecimal(dealBingoCount))
                    .multiply(new BigDecimal(0.8)));
            wdt.setNumber(dealBingoCount);
            winDescribeTicket.add(wdt);
        }
        return winDescribeTicket;
    }

    public boolean isBingo()
    {
        return this.isBingo;
    }

    // 复制对象
    public BingoCheck clone() throws CloneNotSupportedException
    {
        return (BingoCheck) super.clone();
    }

}
