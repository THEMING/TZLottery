package com.xsc.lottery.dyj.sendticket.bingocheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.handle.BingoCheck;
import com.xsc.lottery.util.MathUtil;

public class PlsBingoCheck implements BingoCheck
{
    public Ticket ticket;

    private List<WinDescribeTicket> winDescribeTicket;

    private boolean isBingo;

    private int[] zhudan = { 0, 0, 0 };

    public void addCheck(BingoCheck otherCheck)
    {
        if (!otherCheck.isBingo()) {
            return;
        }
    }

    @SuppressWarnings( { "unchecked" })
    public List<WinDescribeTicket> execute(Ticket ticket,
            Map<String, PrizeLevel> bingoMap, String result)
    {
        String resultContent = result;
        isBingo = false;
        this.ticket = ticket;
        boolean isZhu3 = false;
        boolean isBaozi = false;
        HashMap map = new HashMap();
        String[] codes = resultContent.split("\\,");
        for (int i = 0; i < codes.length; i++) {
            map.put(codes[i], codes[i]);
        }
        if (map.size() == 2) {
            isZhu3 = true;
        }
        if (map.size() == 1) {
            isBaozi = true;
        }
        int resultTotal = BingoUtil.getTotalByContent(result, "\\,");// 计算开奖结果的合值
        String[] sels = ticket.getContent().split("\\|"); // 将投注号码区分解成每注
        try {
            if (ticket.getPlayType() == PlayType.fs) {// 直选复式
                String content = sels[1];
                int bingoCount = BingoUtil.getBingoNumCountByAreas(content,
                        "\\,", resultContent, "\\,");
                if (bingoCount == 3) {
                    this.dealBingoCount(1, 1 * ticket.getMultiple());
                }
            }
            if (ticket.getPlayType() == PlayType.zx_hz) {
                if (resultTotal == BingoUtil.getTotalByContent(sels[1], "\\,")) {
                    this.dealBingoCount(1, 1 * ticket.getMultiple());
                }
            }
            if (ticket.getPlayType() == PlayType.zs_bh && isZhu3 && !isBaozi) {
                String content = sels[1];
                int bingoCount = BingoUtil.getBingoNumCountNotByAreas(content,
                        "", resultContent, "\\,");
                // 命中三个，或者两个，并且只投两个包号
                int n = MathUtil.getCombinationCount(bingoCount, 2);
                this.dealBingoCount(2, n * ticket.getMultiple());
            }
            if (ticket.getPlayType() == PlayType.zs_hz && isZhu3 && !isBaozi) {
                if (resultTotal == BingoUtil.getTotalByContent(sels[1], "\\,")) {
                    this.dealBingoCount(2, 1 * ticket.getMultiple());
                }
            }
            if (ticket.getPlayType() == PlayType.zl_bh && !isZhu3 && !isBaozi) {
                String content = sels[1];
                int bingoCount = BingoUtil.getBingoNumCountNotByAreas(content,
                        "", resultContent, "\\,");
                // 命中三个，或者两个，并且只投两个包号
                int n = MathUtil.getCombinationCount(bingoCount, 3);
                this.dealBingoCount(3, n * ticket.getMultiple());
            }
            if (ticket.getPlayType() == PlayType.zl_hz && !isZhu3 && !isBaozi) {
                if (resultTotal == BingoUtil.getTotalByContent(sels[1], "\\,")) {
                    this.dealBingoCount(3, 1 * ticket.getMultiple());
                }
            }

        }
        catch (Exception e) {
            // log.debug("Error:"+e.getMessage());
        }
        winDescribeTicket = new ArrayList<WinDescribeTicket>();
        // 开始计算奖金
        BigDecimal dxPrize = bingoMap.get("直选").getPrize();

        if (zhudan[0] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("直选"));
            wdt1.setMoney(dxPrize.multiply(new BigDecimal(zhudan[0])));
            wdt1.setTaxmoney(dxPrize.multiply(new BigDecimal(zhudan[0])));
            wdt1.setNumber(zhudan[0]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[1] > 0) {
            BigDecimal zsPrize = bingoMap.get("组三").getPrize();
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("组三"));
            wdt1.setMoney(zsPrize.multiply(new BigDecimal(zhudan[1])));
            wdt1.setTaxmoney(zsPrize.multiply(new BigDecimal(zhudan[1])));
            wdt1.setNumber(zhudan[1]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[2] > 0) {
            BigDecimal zlPrize = bingoMap.get("组六").getPrize();
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("组六"));
            wdt1.setMoney(zlPrize.multiply(new BigDecimal(zhudan[2])));
            wdt1.setTaxmoney(zlPrize.multiply(new BigDecimal(zhudan[2])));
            wdt1.setNumber(zhudan[2]);
            winDescribeTicket.add(wdt1);
        }

        return winDescribeTicket;
    }

    public void dealBingoCount(int prizeType, int value)
    {
        int count = 0;
        this.isBingo = true;// 执行该方法，则表示该票已经是中奖的，所以将该开奖器的是否中奖设为是
        if (prizeType == 1) {// 直选
            count += value;
            zhudan[0] = count;
            return;
        }
        if (prizeType == 2) {// 组选3
            count += value;
            zhudan[1] = count;
            return;
        }
        if (prizeType == 3) {// 组选6
            count += value;
            zhudan[2] = count;
            return;
        }
    }

    public BigDecimal getBingoPosttaxTotal()
    {
        return null;
    }

    //
    public BigDecimal getBingoPretaxTotal()
    {
        return null;
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

    public boolean isOpenAble()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public String getBingoContentTicket()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
