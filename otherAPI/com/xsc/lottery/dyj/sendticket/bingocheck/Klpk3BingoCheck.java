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

public class Klpk3BingoCheck implements BingoCheck{

    public Ticket ticket;
    public StringBuffer bingoContent;
    private HashMap<String, BigDecimal> bingoHashMap;
    private List<WinDescribeTicket> winDescribeTicket;
    private boolean isBingo;

    private int[] zhudan = { 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0 };
    public static  void main(String[] args)
    {
        Klpk3BingoCheck check = new Klpk3BingoCheck();
        Ticket ticket = new Ticket();
        ticket.setOneMoney(new BigDecimal(2));
        ticket.setMultiple(1);
        // ticket.setAddAttribute(AddAttribute.ZJ);

        ticket.setBetContent("01-02-02,03,05,04,06,07,01,08,09");
        // ticket.setMultiple(new Long(1));
        // ticket.setAmount(new Double(3));
        ticket.setPlayType(PlayType.rx_8);

        HashMap<String, PrizeLevel> openResultMap = new HashMap<String, PrizeLevel>();
        PrizeLevel leve1 = new PrizeLevel();
        leve1.setLevel(1);
        leve1.setBetNum(2);
        leve1.setName("任一");
        leve1.setPrize(new BigDecimal(5));
        openResultMap.put("任一", leve1);
        PrizeLevel leve2 = new PrizeLevel();
        leve2.setLevel(2);
        leve2.setBetNum(2);
        leve2.setName("任二");
        leve2.setPrize(new BigDecimal(33));
        openResultMap.put("任二", leve2);
        PrizeLevel leve3 = new PrizeLevel();
        leve3.setLevel(3);
        leve3.setBetNum(3);
        leve3.setName("任三");
        leve3.setPrize(new BigDecimal(116));
        openResultMap.put("任三", leve3);
        PrizeLevel leve4 = new PrizeLevel();
        leve4.setLevel(4);
        leve4.setBetNum(4);
        leve4.setName("任四");
        leve4.setPrize(new BigDecimal(46));
        openResultMap.put("任四", leve4);
        PrizeLevel leve5 = new PrizeLevel();
        leve5.setLevel(5);
        leve5.setBetNum(5);
        leve5.setName("任五");
        leve5.setPrize(new BigDecimal(22));
        openResultMap.put("任五", leve5);
        PrizeLevel leve6 = new PrizeLevel();
        leve6.setLevel(6);
        leve6.setBetNum(6);
        leve6.setName("任六");
        leve6.setPrize(new BigDecimal(12));
        openResultMap.put("任六", leve6);
        PrizeLevel leve7 = new PrizeLevel();
        leve7.setLevel(7);
        leve7.setBetNum(7);
        leve7.setName("同花");
        leve7.setPrize(new BigDecimal(90));
        openResultMap.put("同花", leve7);
        PrizeLevel leve8 = new PrizeLevel();
        leve8.setLevel(8);
        leve8.setBetNum(8);
        leve8.setName("同花顺");
        leve8.setPrize(new BigDecimal(2150));
        openResultMap.put("同花顺", leve8);
        PrizeLevel leve9 = new PrizeLevel();
        leve9.setLevel(9);
        leve9.setBetNum(9);
        leve9.setName("顺子");
        leve9.setPrize(new BigDecimal(400));
        openResultMap.put("顺子", leve9);
        PrizeLevel leve10 = new PrizeLevel();
        leve10.setLevel(10);
        leve10.setBetNum(10);
        leve10.setName("豹子");
        leve10.setPrize(new BigDecimal(6400));
        openResultMap.put("豹子", leve10);
        PrizeLevel leve11 = new PrizeLevel();
        leve11.setLevel(11);
        leve11.setBetNum(11);
        leve11.setName("对子");
        leve11.setPrize(new BigDecimal(88));
        openResultMap.put("对子", leve11);
        PrizeLevel leve12 = new PrizeLevel();
        leve12.setLevel(12);
        leve12.setBetNum(12);
        leve12.setName("同花包选");
        leve12.setPrize(new BigDecimal(22));
        openResultMap.put("同花包选", leve12);
        PrizeLevel leve13 = new PrizeLevel();
        leve13.setLevel(13);
        leve13.setBetNum(13);
        leve13.setName("同花顺包选");
        leve13.setPrize(new BigDecimal(535));
        openResultMap.put("同花顺包选", leve13);
        PrizeLevel leve14 = new PrizeLevel();
        leve14.setLevel(14);
        leve14.setBetNum(14);
        leve14.setName("顺子包选");
        leve14.setPrize(new BigDecimal(33));
        openResultMap.put("顺子包选", leve14);
        PrizeLevel leve15 = new PrizeLevel();
        leve15.setLevel(15);
        leve15.setBetNum(15);
        leve15.setName("豹子包选");
        leve15.setPrize(new BigDecimal(500));
        openResultMap.put("豹子包选", leve15);
        PrizeLevel leve16 = new PrizeLevel();
        leve16.setLevel(16);
        leve16.setBetNum(16);
        leve16.setName("对子包选");
        leve16.setPrize(new BigDecimal(7));
        openResultMap.put("对子包选", leve16);

        List<WinDescribeTicket> winDesc = check.execute(ticket,  openResultMap, "101,201,301");
        System.out.println("winDesc.size() = " + winDesc.size());
        for (int i = 0; i < winDesc.size(); i++) {
     	   System.out.println("====================");
           System.out.println(winDesc.get(i).getMoney());
           System.out.println(winDesc.get(i).getPrizeLevel().getName());
           System.out.println("中奖注数："+winDesc.get(i).getNumber());
        }
        
    }
    
	public List<WinDescribeTicket> execute(Ticket ticket, Map<String, PrizeLevel> bingoMap, String result) {
        String resultContent = result;
        bingoContent = new StringBuffer();
        isBingo = false;
        this.ticket = ticket;
		int bingonum = 0;
		String string = ticket.getContent().substring(3, ticket.getContent().length());
		String[] strings = string.split(",");
		System.out.println(string);
		try {
			if (ticket.getPlayType().equals(PlayType.q1_zhix)) {
				bingonum = checkPre(strings, resultContent);
				zhudan[0] += MathUtil.getCombinationCount(bingonum, 1);
			}
			else if (ticket.getPlayType().equals(PlayType.rx_2)) {
				bingonum = checkPre(strings, resultContent);
				zhudan[1] += MathUtil.getCombinationCount(bingonum, 2);
			}
			else if (ticket.getPlayType().equals(PlayType.rx_3)) {
				bingonum = checkPre(strings, resultContent);
				zhudan[2] += MathUtil.getCombinationCount(bingonum, 3);
			}
			else if (ticket.getPlayType().equals(PlayType.rx_4)) {
				bingonum = checkPre(strings, resultContent);
				if(bingonum == 3)
				{
					zhudan[3] += MathUtil.getCombinationCount(strings.length-3, 4-3);
				}
			}
			else if (ticket.getPlayType().equals(PlayType.rx_5)) {
				bingonum = checkPre(strings, resultContent);
				if(bingonum == 3)
				zhudan[4] += MathUtil.getCombinationCount(strings.length-3, 5-3);
			}
			else if (ticket.getPlayType().equals(PlayType.rx_6)) {
				bingonum = checkPre(strings, resultContent);
				if (bingonum == 3) {
					zhudan[5] += MathUtil.getCombinationCount(strings.length - 3, 6-3);
				}
			}
			BigDecimal q1_zhix = new BigDecimal(5);
			BigDecimal rx_2 = new BigDecimal(33);
			BigDecimal rx_3 = new BigDecimal(116);
			BigDecimal rx_4 = new BigDecimal(46);
			BigDecimal rx_5 = new BigDecimal(22);
			BigDecimal rx_6 = new BigDecimal(12);
			BigDecimal th = new BigDecimal(90);
			BigDecimal hs = new BigDecimal(2150);
			BigDecimal sz = new BigDecimal(400);
			BigDecimal bz = new BigDecimal(6400);
			BigDecimal dz = new BigDecimal(88);
			BigDecimal thbx = new BigDecimal(22);
			BigDecimal hsbx = new BigDecimal(535);
			BigDecimal szbx = new BigDecimal(33);
			BigDecimal bzbx = new BigDecimal(500);
			BigDecimal dzbx = new BigDecimal(7);
			
			winDescribeTicket = new ArrayList<WinDescribeTicket>();
	        if (zhudan[0] > 0) {
	            WinDescribeTicket wdt1 = new WinDescribeTicket();
//	            wdt1.setPrizeLevel(bingoMap.get("前一"));
	            wdt1.setMoney(q1_zhix.multiply(new BigDecimal(zhudan[0])));
	            wdt1.setTaxmoney(q1_zhix.multiply(new BigDecimal(zhudan[0])));
	            wdt1.setNumber(zhudan[0]);
	            winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[1] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任二"));
	        	wdt1.setMoney(rx_2.multiply(new BigDecimal(zhudan[1])));
	        	wdt1.setTaxmoney(rx_2.multiply(new BigDecimal(zhudan[1])));
	        	wdt1.setNumber(zhudan[1]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[2] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任三"));
	        	wdt1.setMoney(rx_3.multiply(new BigDecimal(zhudan[2])));
	        	wdt1.setTaxmoney(rx_3.multiply(new BigDecimal(zhudan[2])));
	        	wdt1.setNumber(zhudan[2]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[3] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任四"));
	        	wdt1.setMoney(rx_4.multiply(new BigDecimal(zhudan[3])));
	        	wdt1.setTaxmoney(rx_4.multiply(new BigDecimal(zhudan[3])));
	        	wdt1.setNumber(zhudan[3]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[4] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任五"));
	        	wdt1.setMoney(rx_5.multiply(new BigDecimal(zhudan[4])));
	        	wdt1.setTaxmoney(rx_5.multiply(new BigDecimal(zhudan[4])));
	        	wdt1.setNumber(zhudan[4]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[5] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任六"));
	        	wdt1.setMoney(rx_6.multiply(new BigDecimal(zhudan[5])));
	        	wdt1.setTaxmoney(rx_6.multiply(new BigDecimal(zhudan[5])));
	        	wdt1.setNumber(zhudan[5]);
	        	winDescribeTicket.add(wdt1);
	        }
		} catch (Exception e) {
		
		}
		
		
		return winDescribeTicket;
	}

    /**
     *判断前区的中奖个数
     */
    private int checkPre(String[] preNums, String resultContent)
    {
        int preBingo = 0;// 前区命中号码个数

        for (int j = 0; j < preNums.length; j++) {
            if (resultContent.indexOf(preNums[j]) >= 0) {
                preBingo++;
            }
        }

        return preBingo;
    }

	public void addCheck(BingoCheck otherCheck) {
        if (!otherCheck.isBingo()) {
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
}
