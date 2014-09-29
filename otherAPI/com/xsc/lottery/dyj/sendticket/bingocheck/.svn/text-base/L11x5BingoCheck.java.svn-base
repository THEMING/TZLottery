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

public class L11x5BingoCheck implements BingoCheck{

    public Ticket ticket;
    public StringBuffer bingoContent;
    private HashMap<String, BigDecimal> bingoHashMap;
    private List<WinDescribeTicket> winDescribeTicket;
    private boolean isBingo;

    private int[] zhudan = { 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0 };
    public static  void main(String[] args)
    {
        L11x5BingoCheck check = new L11x5BingoCheck();
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
        leve1.setName("前一");
        leve1.setPrize(new BigDecimal(13));
        openResultMap.put("前一", leve1);
        PrizeLevel leve2 = new PrizeLevel();
        leve2.setLevel(2);
        leve2.setBetNum(2);
        leve2.setName("任二");
        leve2.setPrize(new BigDecimal(6));
        openResultMap.put("任二", leve2);
        PrizeLevel leve3 = new PrizeLevel();
        leve3.setLevel(3);
        leve3.setBetNum(3);
        leve3.setName("任三");
        leve3.setPrize(new BigDecimal(19));
        openResultMap.put("任三", leve3);
        PrizeLevel leve4 = new PrizeLevel();
        leve4.setLevel(4);
        leve4.setBetNum(4);
        leve4.setName("任四");
        leve4.setPrize(new BigDecimal(78));
        openResultMap.put("任四", leve4);
        PrizeLevel leve5 = new PrizeLevel();
        leve5.setLevel(5);
        leve5.setBetNum(5);
        leve5.setName("任五");
        leve5.setPrize(new BigDecimal(540));
        openResultMap.put("任五", leve5);
        PrizeLevel leve6 = new PrizeLevel();
        leve6.setLevel(6);
        leve6.setBetNum(6);
        leve6.setName("任六");
        leve6.setPrize(new BigDecimal(90));
        openResultMap.put("任六", leve6);
        PrizeLevel leve7 = new PrizeLevel();
        leve7.setLevel(6);
        leve7.setBetNum(6);
        leve7.setName("任七");
        leve7.setPrize(new BigDecimal(26));
        openResultMap.put("任七", leve7);
        PrizeLevel leve8 = new PrizeLevel();
        leve8.setLevel(6);
        leve8.setBetNum(6);
        leve8.setName("任八");
        leve8.setPrize(new BigDecimal(9));
        openResultMap.put("任八", leve8);
        PrizeLevel leve9 = new PrizeLevel();
        leve9.setLevel(6);
        leve9.setBetNum(6);
        leve9.setName("前二直选");
        leve9.setPrize(new BigDecimal(130));
        openResultMap.put("前二直选", leve9);
        PrizeLevel leve10 = new PrizeLevel();
        leve10.setLevel(6);
        leve10.setBetNum(6);
        leve10.setName("前三直选");
        leve10.setPrize(new BigDecimal(1170));
        openResultMap.put("前三直选", leve10);
        PrizeLevel leve11 = new PrizeLevel();
        leve11.setLevel(6);
        leve11.setBetNum(6);
        leve11.setName("前三组选");
        leve11.setPrize(new BigDecimal(195));
        openResultMap.put("前三组选", leve11);
        PrizeLevel leve12 = new PrizeLevel();
        leve12.setLevel(6);
        leve12.setBetNum(6);
        leve12.setName("前二组选");
        leve12.setPrize(new BigDecimal(65));
        openResultMap.put("前二组选", leve12);

        List<WinDescribeTicket> winDesc = check.execute(ticket, 
                openResultMap,
                "01,02,03,04,05");
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
		String string = ticket.getBetContent().substring(6, ticket.getBetContent().length());
		String[] strings = string.split(",");
		System.out.println(string);
		try {
			if (ticket.getPlayType().equals(PlayType.q1_zhix)) {
				if (string.contains(resultContent.substring(0, 2))) {
					zhudan[0] += 1;
				}
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
				zhudan[3] += MathUtil.getCombinationCount(bingonum, 4);
			}
			else if (ticket.getPlayType().equals(PlayType.rx_5)) {
				bingonum = checkPre(strings, resultContent);
				zhudan[4] += MathUtil.getCombinationCount(bingonum, 5);
			}
			else if (ticket.getPlayType().equals(PlayType.rx_6)) {
				bingonum = checkPre(strings, resultContent);
				if (bingonum == 5) {
					zhudan[5] += MathUtil.getCombinationCount(strings.length - 5, 6-5);
				}
			}
			else if (ticket.getPlayType().equals(PlayType.rx_7)) {
				bingonum = checkPre(strings, resultContent);
				if (bingonum == 5) {
					zhudan[6] += MathUtil.getCombinationCount(strings.length - 5, 7-5);
				}
			}
			else if (ticket.getPlayType().equals(PlayType.rx_8)) {
				bingonum = checkPre(strings, resultContent);
				if (bingonum == 5) {
					zhudan[7] += MathUtil.getCombinationCount(strings.length - 5, 8-5);
				}
			}
			
			BigDecimal q1_zhix = new BigDecimal(13);
			BigDecimal rx_2 = new BigDecimal(6);
			BigDecimal rx_3 = new BigDecimal(19);
			BigDecimal rx_4 = new BigDecimal(78);
			BigDecimal rx_5 = new BigDecimal(540);
			BigDecimal rx_6 = new BigDecimal(90);
			BigDecimal rx_7 = new BigDecimal(26);
			BigDecimal rx_8 = new BigDecimal(9);
			BigDecimal q2_zhix = new BigDecimal(130);
			BigDecimal q2_zux = new BigDecimal(65);
			BigDecimal q3_zhix = new BigDecimal(1170);
			BigDecimal q3_zux = new BigDecimal(195);
			
			
//			BigDecimal q1_zhix = bingoMap.get("前一").getPrize();
//			BigDecimal rx_2 = bingoMap.get("任二").getPrize();
//			BigDecimal rx_3 = bingoMap.get("任三").getPrize();
//			BigDecimal rx_4 = bingoMap.get("任四").getPrize();
//			BigDecimal rx_5 = bingoMap.get("任五").getPrize();
//			BigDecimal rx_6 = bingoMap.get("任六").getPrize();
//			BigDecimal rx_7 = bingoMap.get("任七").getPrize();
//			BigDecimal rx_8 = bingoMap.get("任八").getPrize();
//			BigDecimal q2_zhix = bingoMap.get("前二直选").getPrize();
//			BigDecimal q2_zux = bingoMap.get("前二组选").getPrize();
//			BigDecimal q3_zhix = bingoMap.get("前三直选").getPrize();
//			BigDecimal q3_zux = bingoMap.get("前三组选").getPrize();
//			
			
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
	        if (zhudan[6] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任七"));
	        	wdt1.setMoney(rx_7.multiply(new BigDecimal(zhudan[6])));
	        	wdt1.setTaxmoney(rx_7.multiply(new BigDecimal(zhudan[6])));
	        	wdt1.setNumber(zhudan[6]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[7] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("任八"));
	        	wdt1.setMoney(rx_8.multiply(new BigDecimal(zhudan[7])));
	        	wdt1.setTaxmoney(rx_8.multiply(new BigDecimal(zhudan[7])));
	        	wdt1.setNumber(zhudan[7]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[8] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("前二直选"));
	        	wdt1.setMoney(q2_zhix.multiply(new BigDecimal(zhudan[8])));
	        	wdt1.setTaxmoney(q2_zhix.multiply(new BigDecimal(zhudan[8])));
	        	wdt1.setNumber(zhudan[8]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[9] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("前二组选"));
	        	wdt1.setMoney(q2_zux.multiply(new BigDecimal(zhudan[9])));
	        	wdt1.setTaxmoney(q2_zux.multiply(new BigDecimal(zhudan[9])));
	        	wdt1.setNumber(zhudan[9]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[10] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("前三直选"));
	        	wdt1.setMoney(q3_zhix.multiply(new BigDecimal(zhudan[10])));
	        	wdt1.setTaxmoney(q3_zhix.multiply(new BigDecimal(zhudan[10])));
	        	wdt1.setNumber(zhudan[10]);
	        	winDescribeTicket.add(wdt1);
	        }
	        if (zhudan[11] > 0) {
	        	WinDescribeTicket wdt1 = new WinDescribeTicket();
//	        	wdt1.setPrizeLevel(bingoMap.get("前三组选"));
	        	wdt1.setMoney(q3_zux.multiply(new BigDecimal(zhudan[11])));
	        	wdt1.setTaxmoney(q3_zux.multiply(new BigDecimal(zhudan[11])));
	        	wdt1.setNumber(zhudan[11]);
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
