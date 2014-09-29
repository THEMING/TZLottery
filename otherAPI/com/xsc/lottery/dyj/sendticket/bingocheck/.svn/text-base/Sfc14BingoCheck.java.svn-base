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

public class Sfc14BingoCheck implements BingoCheck
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

    public List<WinDescribeTicket> execute(Ticket ticket,
            Map<String, PrizeLevel> bingoMap, String result)
    {
        String resultContent = result;
        bingoContent = new StringBuffer();
        isBingo = false;
        this.ticket = ticket;
        //int resultTotal = BingoUtil.getTotalByContent(result, "\\,");// 计算开奖结果的合值
        String[] sels = ticket.getContent().split("\\|"); // 将投注号码区分解成每注
        int r14,r13;
        try {
        	 if (ticket.getPlayType() == PlayType.fs) {// 直选复式
                 String content = sels[0];
                 r14=check14right(content,resultContent);
                 r13=check13right(content,resultContent);
                 
                 if (r14 >0) {
                     this.dealBingoCount(1, r14 * ticket.getMultiple());
                 }
                 if (r13 > 0) {
                     this.dealBingoCount(2, r13 * ticket.getMultiple());
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
 
        return winDescribeTicket;
    }
    
    public static int check14right(String content, String result)
    {
    	String[] areas = content.split("\\,");
        int bingoNumCount = 0,fsnum=1;
        String[] bingoNumbers = result.split("\\,");
        for (int i = 0; i < areas.length; i++) {
            String[] coder = areas[i].split("");
            if(bingoNumbers[i].equals("*"))
        	{
        		bingoNumCount++;
        		fsnum*=coder.length-1;
        	} else
        	{
        		for (int j = 1; j < coder.length; j++) {          	
        			if (coder[j].equals(bingoNumbers[i])) 
        			{
        				bingoNumCount++;
        			}
            
        		}
        	}
        }
        if(bingoNumCount==14) return fsnum;
        else return 0;
    }
    
    public static int check13right(String content, String result)
    {
    	String[] areas = content.split("\\,");
        int bingoNumCount = 0,mul=1,selnum=0;
        String[] bingoNumbers = result.split("\\,");
        for (int i = 0; i < areas.length; i++) {
            String[] coder = areas[i].split("");
           
            if(bingoNumbers[i].equals("*"))
        	{
        		bingoNumCount++;
        		mul*=coder.length-1;
        		selnum+=1;
        	} else
        	{
        		 selnum+=coder.length-1;
        		for (int j = 1; j < coder.length; j++) {          	
        			if (coder[j].equals(bingoNumbers[i])) 
        			{
        				bingoNumCount++;
        			}
            
        		}
        	}
        }
        if(bingoNumCount==14)
        {
        	return (selnum-14)*mul;        	
        } else
        {
        	if(bingoNumCount==13) return mul;
        	else return 0;
        }
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
    static public void main(String[] args)
    {
    	Sfc14BingoCheck check = new Sfc14BingoCheck();
         Ticket ticket = new Ticket();
         ticket.setOneMoney(new BigDecimal(2));
         ticket.setMultiple(1);
         // ticket.setAddAttribute(AddAttribute.ZJ);
        
         ticket.setContent("30,0,1,1,3,1,0,0,013,0,0,1,3,1");
         // ticket.setMultiple(new Long(1));
       //  ticket.setAmount(new Double(3));
          ticket.setPlayType(PlayType.fs);
        
         HashMap<String, PrizeLevel> openResultMap = new HashMap<String,
         PrizeLevel>();
         PrizeLevel leve1=new PrizeLevel();
         leve1.setLevel(1);
         leve1.setBetNum(2);
         leve1.setName("一等奖");
         leve1.setPrize(new BigDecimal(1001));
         openResultMap.put("一等奖", leve1);
         PrizeLevel leve2=new PrizeLevel();
         leve2.setLevel(2);
         leve2.setBetNum(2);
         leve2.setName("二等奖");
         leve2.setPrize(new BigDecimal(511));
         openResultMap.put("二等奖", leve2);
        // openResultMap.put("一等奖", "1");
       //  openResultMap.put("二等奖", "2");
//         openResultMap.put("prize3", "3");
//         openResultMap.put("prize4", "4");
//         openResultMap.put("prize5", "5");
//         openResultMap.put("prize6", "6");
//         openResultMap.put("prize7", "3000");
//         openResultMap.put("prize8", "8");
//         openResultMap.put("prize9", "9");
//         openResultMap.put("prize10", "10");
//         openResultMap.put("prize11", "11");
//         openResultMap.put("prize12", "12");
//         openResultMap.put("prize13", "13");
//         openResultMap.put("prize14", "14");
//         openResultMap.put("prize15", "15");
//         openResultMap.put("prize16", "16");
       //  openResultMap.put("result", "3,0,1,1,3,1,0,0,3,0,0,1,3,1");
         List<WinDescribeTicket> winDesc =check.execute(ticket, openResultMap, "310,0,1,1,3,1,0,0,3,0,0,1,3,1");
         //check14right(ticket.getContent(),"3,0,1,1,3,1,0,0,3,0,0,1,3,1");
         //check13right(ticket.getContent(),"3,0,1,1,3,1,0,0,3,0,0,1,3,1");
      //   System.out.println(winDesc.get(0).getMoney());
    	
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
