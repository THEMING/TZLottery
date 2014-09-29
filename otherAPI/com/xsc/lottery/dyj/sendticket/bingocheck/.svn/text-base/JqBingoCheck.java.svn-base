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

@SuppressWarnings("unused")
public class JqBingoCheck implements BingoCheck 
{
	public Ticket ticket;
    public StringBuffer bingoContent;
    private HashMap<String, BigDecimal> bingoHashMap;
    private List<WinDescribeTicket> winDescribeTicket;
    
	private boolean isBingo;
    private int[] zhudan = { 0, 0, 0, 0, 0, 0 };
    

	

	public void addCheck(BingoCheck otherCheck) {
		  if (!otherCheck.isBingo()) {
	            return;
	        }

	}

	public List<WinDescribeTicket> execute(Ticket ticket,
			Map<String, PrizeLevel> bingoMap, String result) {
		    String resultContent = result;
	        bingoContent = new StringBuffer();
	        isBingo = false;
	        this.ticket = ticket;
	        String sels = ticket.getContent(); 
	        try {
	        	 if (ticket.getPlayType() == PlayType.fs) {
	                 String content = sels;
	                 int rjq;
	                 rjq=checkjqright(content,resultContent);
	                 if(rjq>0) {
	                     this.dealBingoCount(1, rjq * ticket.getMultiple());
	                 }
	             }
	        }
	        catch (Exception e) {
	          // log.debug("Error:"+e.getMessage());
	        }

	        // 开始计算奖金
	        BigDecimal firstPrize = bingoMap.get("一等奖").getPrize();
	        // 一等奖税后奖金
	        BigDecimal firstPrize_ = firstPrize.compareTo(new BigDecimal(10000)) > 0 ? firstPrize
	                .multiply(new BigDecimal(0.8))
	                : firstPrize;
	        

	        winDescribeTicket = new ArrayList<WinDescribeTicket>();
	        if (zhudan[0] > 0) {
	            WinDescribeTicket wdt1 = new WinDescribeTicket();
	            wdt1.setPrizeLevel(bingoMap.get("一等奖"));
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
	
	public static int checkjqright(String content, String result)
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
        if(bingoNumCount==8) return fsnum;
        else return 0;
    }
    
	
	public boolean isBingo() {
		
		return false;
	}
	 public BingoCheck clone() throws CloneNotSupportedException
	    {
	        return (BingoCheck) super.clone();
	    }
	 
	    public Ticket getTicket() {
			return ticket;
		}

		public void setTicket(Ticket ticket) {
			this.ticket = ticket;
		}

		public StringBuffer getBingoContent() {
			return bingoContent;
		}

		public void setBingoContent(StringBuffer bingoContent) {
			this.bingoContent = bingoContent;
		}

		public HashMap<String, BigDecimal> getBingoHashMap() {
			return bingoHashMap;
		}

		public void setBingoHashMap(HashMap<String, BigDecimal> bingoHashMap) {
			this.bingoHashMap = bingoHashMap;
		}

		public List<WinDescribeTicket> getWinDescribeTicket() {
			return winDescribeTicket;
		}

		public void setWinDescribeTicket(List<WinDescribeTicket> winDescribeTicket) {
			this.winDescribeTicket = winDescribeTicket;
		}

		public void setBingo(boolean isBingo) {
			this.isBingo = isBingo;
		}


}
