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


public class R9BingoCheck implements BingoCheck
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
        try {
        	 if (ticket.getPlayType() == PlayType.fs) {// 直选复式
                 String content = sels[0];
                 int r9r;
                 r9r=checkr9right(content,resultContent);
                 if(r9r>0) {
                     this.dealBingoCount(1, r9r * ticket.getMultiple());
                 }
             }
        }
        catch (Exception e) {
            // log.debug("Error:"+e.getMessage());
        }

        // 开始计算奖金
        BigDecimal firstPrize = bingoMap.get("任九").getPrize();
        // 一等奖税后奖金
        BigDecimal firstPrize_ = firstPrize.compareTo(new BigDecimal(10000)) > 0 ? firstPrize
                .multiply(new BigDecimal(0.8))
                : firstPrize;
        

        winDescribeTicket = new ArrayList<WinDescribeTicket>();
        if (zhudan[0] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("任九"));
            wdt1.setMoney(firstPrize.multiply(new BigDecimal(zhudan[0])));
            wdt1.setTaxmoney(firstPrize_.multiply(new BigDecimal(zhudan[0])));
            wdt1.setNumber(zhudan[0]);
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
 
    }
    
    public static int checkr9right(String content, String result)
    {
    	String[] areas = content.split("\\,");
        int bingoNumCount = 0,fsnum=1;
        String[] bingoNumbers = result.split("\\,");
        for (int i = 0; i < areas.length; i++) {
            String[] coder = areas[i].split("");
            if(bingoNumbers[i].equals("*")&&(!coder[1].equals("#")))
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
        if(bingoNumCount==9) return fsnum;
        else return 0;
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
    	R9BingoCheck check = new R9BingoCheck();
         Ticket ticket = new Ticket();
         ticket.setOneMoney(new BigDecimal(2));
         ticket.setMultiple(1);
         // ticket.setAddAttribute(AddAttribute.ZJ);
        
         ticket.setContent("#,31,#,#,#,#,0,0,3,0,0,1,3,1");
         // ticket.setMultiple(new Long(1));
       //  ticket.setAmount(new Double(3));
          ticket.setPlayType(PlayType.fs);
        
         HashMap<String, PrizeLevel> openResultMap = new HashMap<String,
         PrizeLevel>();
         PrizeLevel leve1=new PrizeLevel();
         leve1.setLevel(1);
         leve1.setName("任九");
         leve1.setBetNum(2);
         leve1.setPrize(new  BigDecimal(100000));
         openResultMap.put("任九", leve1);
         List<WinDescribeTicket> winDesc =check.execute(ticket, openResultMap, "*,*,0,1,3,1,0,0,3,0,0,1,3,1" );
        //W System.out.println(winDesc.get(0).getTaxmoney());
    	
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
