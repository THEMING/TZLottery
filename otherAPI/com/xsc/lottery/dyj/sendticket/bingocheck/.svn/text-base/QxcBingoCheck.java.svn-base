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

public class QxcBingoCheck implements BingoCheck
{
    public Ticket ticket;
    public StringBuffer bingoContent;
    private HashMap<String, BigDecimal> bingoHashMap;
    private List<WinDescribeTicket> winDescribeTicket;
    private boolean isBingo;

    private int[] zhudan = { 0, 0, 0, 0, 0, 0 };

    public void addCheck(BingoCheck otherCheck)
    {
        if (!otherCheck.isBingo()) {
            return;
        }
    }

    public Boolean selected(int weizhi, String shuzi, String code) {
    	String[] codes = code.split(",");
    	if (codes[weizhi].contains(shuzi)) {
    		return true;
		}else {
			return false;
		}
    }
    
    public List<WinDescribeTicket> execute(Ticket ticket,
            Map<String, PrizeLevel> bingoMap, String result)
    {
        String resultContent = result;
        bingoContent = new StringBuffer();
        isBingo = false;
        this.ticket = ticket;
        //int resultTotal = BingoUtil.getTotalByContent(result, "\\,");// 计算开奖结果的合值
        String[] sels = ticket.getContent().split("\\^"); // 将投注号码区分解成每注
        try {
        	 if (ticket.getPlayType() == PlayType.fs) {// 直选复式
                String content = sels[0];
                String con = "";
                 for (int i1 = 0; i1 < 10; i1++) {
                	 if (!selected(0, String.valueOf(i1), content))
                		 continue;
                	 for (int i2 = 0; i2 < 10; i2++) {
                		 if (!selected(1, String.valueOf(i2), content))
                			 continue;
                		 for (int i3 = 0; i3 < 10; i3++) {
                			 if (!selected(2, String.valueOf(i3), content))
                				 continue;
                			 for (int i4 = 0; i4 < 10; i4++) {
                				 if (!selected(3, String.valueOf(i4), content))
                					 continue;
                				 for (int i5 = 0; i5 < 10; i5++) {
                					 if (!selected(4, String.valueOf(i5), content))
                						 continue;
                					 for (int i6 = 0; i6 < 10; i6++) {
                						 if (!selected(5, String.valueOf(i6), content))
                							 continue;
                						 for (int i7 = 0; i7 < 10; i7++) {
                							 if (!selected(6, String.valueOf(i7), content))
                								 continue;
                							 con = String.valueOf(i1) + "," + String.valueOf(i2) + "," + String.valueOf(i3) + "," + String.valueOf(i4) + "," + String.valueOf(i5) + "," + String.valueOf(i6) + "," + String.valueOf(i7);
                							 // eck count
                						 
                				               
                			                 int bingoCount = BingoUtil.getBingoNumCountByQxc(con,
                			                         "\\,", resultContent, "\\,");
                			                 if (bingoCount == 7) {
                			                     this.dealBingoCount(1, 1 * ticket.getMultiple());
                			                 }
                			                 else if (bingoCount == 6) {
                			                     this.dealBingoCount(2, 1 * ticket.getMultiple());
                			                 }
                			                 else if (bingoCount == 5) {
                			                     this.dealBingoCount(3, 1 * ticket.getMultiple());
                			                 }
                			                 else if (bingoCount == 4) {
                			                     this.dealBingoCount(4, 1 * ticket.getMultiple());
                			                 }
                			                 else if (bingoCount == 3) {
                			                     this.dealBingoCount(5, 1 * ticket.getMultiple());
                			                 }
                			                 else if (bingoCount == 2) {
                			                     this.dealBingoCount(6, 1 * ticket.getMultiple());
                			                 }
                						 
                						 
                						 }
                					 }
                				 }
                			 }
                		 }
                	 }
                 }
             }
        }
        catch (Exception e) {
            // log.debug("Error:"+e.getMessage());
        }

        // 开始计算奖金
        BigDecimal firstPrize = bingoMap.get("一等奖").getPrize();
        BigDecimal secPrize = bingoMap.get("二等奖").getPrize();
        // 一等奖税后奖金
        BigDecimal firstPrize_ = firstPrize.compareTo(new BigDecimal(10000)) > 0 ? firstPrize
                .multiply(new BigDecimal(0.8))
                : firstPrize;
        // 二等奖税后奖金
        BigDecimal secPrize_ = secPrize.compareTo(new BigDecimal(10000)) > 0 ? secPrize
                .multiply(new BigDecimal(0.8))
                : secPrize;
        BigDecimal thirdPrize = bingoMap.get("三等奖").getPrize();
        BigDecimal fourthPrize = bingoMap.get("四等奖").getPrize();
        BigDecimal fifthPrize = bingoMap.get("五等奖").getPrize();
        BigDecimal sixthPrize = bingoMap.get("六等奖").getPrize();
        winDescribeTicket = new ArrayList<WinDescribeTicket>();
        if (zhudan[0] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("一等奖"));
            wdt1.setMoney(firstPrize.multiply(new BigDecimal(zhudan[0])));
            wdt1.setTaxmoney(firstPrize_.multiply(new BigDecimal(zhudan[0])));
            wdt1.setNumber(zhudan[0]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[1] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("二等奖"));
            wdt1.setMoney(secPrize.multiply(new BigDecimal(zhudan[1])));
            wdt1.setTaxmoney(secPrize_.multiply(new BigDecimal(zhudan[1])));
            wdt1.setNumber(zhudan[1]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[2] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("三等奖"));
            wdt1.setMoney(thirdPrize.multiply(new BigDecimal(zhudan[2])));
            wdt1.setTaxmoney(thirdPrize.multiply(new BigDecimal(zhudan[2])));
            wdt1.setNumber(zhudan[2]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[3] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("四等奖"));
            wdt1.setMoney(fourthPrize.multiply(new BigDecimal(zhudan[3])));
            wdt1.setTaxmoney(fourthPrize.multiply(new BigDecimal(zhudan[3])));
            wdt1.setNumber(zhudan[3]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[4] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("五等奖"));
            wdt1.setMoney(fifthPrize.multiply(new BigDecimal(zhudan[4])));
            wdt1.setTaxmoney(fifthPrize.multiply(new BigDecimal(zhudan[4])));
            wdt1.setNumber(zhudan[4]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[5] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("六等奖"));
            wdt1.setMoney(sixthPrize.multiply(new BigDecimal(zhudan[5])));
            wdt1.setTaxmoney(sixthPrize.multiply(new BigDecimal(zhudan[5])));
            wdt1.setNumber(zhudan[5]);
            winDescribeTicket.add(wdt1);
        }
        return winDescribeTicket;
    }
    public void dealBingoCount(int prizeLevel, int value)
    {
        int count = 0;
        this.isBingo = true;// 执行该方法，则表示该票已经是中奖的，所以将该开奖器的是否中奖设为是
        if (prizeLevel == 1) {// 一等奖
            count += value;
            zhudan[0] = count;
            return;
        }
        if (prizeLevel == 2) {// 二等奖
            count += value;
            zhudan[1] = count;
            return;
        }
        if (prizeLevel == 3) {// 三等奖
            count += value;
            zhudan[2] = count;
            return;
        }
        if (prizeLevel == 4) {// 四等奖
            count += value;
            zhudan[3] = count;
            return;
        }
        if (prizeLevel == 5) {// 五等奖
            count += value;
            zhudan[4] = count;
            return;
        }
        if (prizeLevel == 6) {// 六等奖
            count += value;
            zhudan[5] = count;
            return;
        }
    }

    public String getBingoContent()
    {
        return this.bingoContent.toString();
    }

    @SuppressWarnings("unchecked")
    public HashMap getBingoHashMap()
    {
        return this.bingoHashMap;
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

    /**
     * @param args
     */
    public static  void main(String[] args)
    {
        QxcBingoCheck check = new QxcBingoCheck();
        Ticket ticket = new Ticket();
        ticket.setOneMoney(new BigDecimal(2));
        ticket.setMultiple(1);
        // ticket.setAddAttribute(AddAttribute.ZJ);

        ticket.setContent("1,12,13,78,0,61,0");
        // ticket.setMultiple(new Long(1));
        // ticket.setAmount(new Double(3));
        ticket.setPlayType(PlayType.fs);

        HashMap<String, PrizeLevel> openResultMap = new HashMap<String, PrizeLevel>();
        PrizeLevel leve1 = new PrizeLevel();
        leve1.setLevel(1);
        leve1.setBetNum(2);
        leve1.setName("一等奖");
        leve1.setPrize(new BigDecimal(100000));
        openResultMap.put("一等奖", leve1);
        PrizeLevel leve2 = new PrizeLevel();
        leve2.setLevel(2);
        leve2.setBetNum(2);
        leve2.setName("二等奖");
        leve2.setPrize(new BigDecimal(20000));
        openResultMap.put("二等奖", leve2);
        PrizeLevel leve3 = new PrizeLevel();
        leve3.setLevel(3);
        leve3.setBetNum(3);
        leve3.setName("三等奖");
        leve3.setPrize(new BigDecimal(10000));
        openResultMap.put("三等奖", leve3);
        PrizeLevel leve4 = new PrizeLevel();
        leve4.setLevel(4);
        leve4.setBetNum(4);
        leve4.setName("四等奖");
        leve4.setPrize(new BigDecimal(4000));
        openResultMap.put("四等奖", leve4);
        PrizeLevel leve5 = new PrizeLevel();
        leve5.setLevel(5);
        leve5.setBetNum(5);
        leve5.setName("五等奖");
        leve5.setPrize(new BigDecimal(5000));
        openResultMap.put("五等奖", leve5);
        PrizeLevel leve6 = new PrizeLevel();
        leve6.setLevel(6);
        leve6.setBetNum(6);
        leve6.setName("六等奖");
        leve6.setPrize(new BigDecimal(6000));
        openResultMap.put("六等奖", leve6);

        // openResultMap.put("一等奖", "1");
        // openResultMap.put("二等奖", "2");
        // openResultMap.put("prize3", "3");
        // openResultMap.put("prize4", "4");
        // openResultMap.put("prize5", "5");
        // openResultMap.put("prize6", "6");
        // openResultMap.put("prize7", "3000");
        // openResultMap.put("prize8", "8");
        // openResultMap.put("prize9", "9");
        // openResultMap.put("prize10", "10");
        // openResultMap.put("prize11", "11");
        // openResultMap.put("prize12", "12");
        // openResultMap.put("prize13", "13");
        // openResultMap.put("prize14", "14");
        // openResultMap.put("prize15", "15");
        // openResultMap.put("prize16", "16");
        // openResultMap.put("result", "3,0,1,1,3,1,0,0,3,0,0,1,3,1");
        List<WinDescribeTicket> winDesc = check.execute(ticket, 
                openResultMap,
                "1,2,3,4,5,6,7");
        System.out.println(winDesc.size());
        for (int i = 0; i < winDesc.size(); i++) {
            System.out.println(winDesc.get(i).getMoney());
        }
    }

    public boolean isOpenAble()
    {
        return false;
    }

    public String getBingoContentTicket()
    {
        return null;
    }
}
