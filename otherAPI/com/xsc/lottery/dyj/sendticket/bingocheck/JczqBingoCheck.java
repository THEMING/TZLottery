package com.xsc.lottery.dyj.sendticket.bingocheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.service.business.MatchArrangeService;


@Service("JczqBingoCheckService")
@Transactional
public class JczqBingoCheck
{
    @Autowired
    private MatchArrangeService matchArrangeService;

    public WinDescribeTicket execute(Ticket ticket) throws Exception
    {
        PlayType playType = ticket.getPlayType();
        String[] matchBets = ticket.getContent().split("\\|")[1].split(",");
        
        //FIXME
        int m = Integer.parseInt(ticket.getContent().split("\\|")[2].split("\\*")[0]);  // 过关方式 m*n
        int n = Integer.parseInt(ticket.getContent().split("\\|")[2].split("\\*")[1]);
        
        List<WinDesc> winList = new ArrayList<WinDesc>();
        
        WinDescribeTicket winDescribeTicket = new WinDescribeTicket();
        
        for (int i=0; i<matchBets.length; i++)
        {
        	String matchNo = matchBets[i].split("=")[0];
        	String result = "";
        	//加入竞彩足球混合过关的开奖
        	if(ticket.getPlayType() == PlayType.JZ_HH)
        	{
        		//SPF>140601001
        		playType = PlayType.valueOf(matchNo.split("\\>")[0]);//投注方式
        		matchNo = matchNo.split("\\>")[1]; //场次编号
        		result = matchArrangeService.getResultByMatchNo(matchNo, playType, LotteryType.竞彩足球);
        		
        	}
        	else
        	{
        		/** result example: 0|3|1.72 */
        		result = matchArrangeService.getResultByMatchNo(matchNo, playType, LotteryType.竞彩足球);
        	}
        	boolean special = Boolean.valueOf(result.split("\\|")[0]);
        	String specificRes = result.split("\\|")[1];
        	
        	//String ratio = result.split("\\|")[2];  单串固定奖  不需要取浮动奖赔率
        	
    		if (true == special)  // 该场比赛作废
    		{
    			String[] betResults = matchBets[i].split("=")[1].split("/");
    			WinDesc newWinMatch = new WinDesc(matchNo);
    			for(int k=0; k<betResults.length; k++)
    				newWinMatch.ratioList.add("1"); // SP值均置为1
    			winList.add(newWinMatch);
    		}
    		else
    		{
    			String[] betResults = matchBets[i].split("=")[1].split("/");
    			//遍历号码数组{"3","1"}
    			for (int j=0; j<betResults.length; j++)
    			{
    				if (betResults[j].equals(specificRes))
    				{
    					WinDesc newWinMatch = new WinDesc(matchNo);
    					/*
    					if ( 1==m && 1==n) // 单串过关
    						if(ticket.getContent().split("\\|")[0].equals("CBF"))
    						{
    							String personalRatio = ticket.getRatio().split("\\|")[i].split("/")[j];
        						newWinMatch.ratioList.add(personalRatio); 
    						}else
    						{
    						    newWinMatch.ratioList.add(ratio);
    						}  // ratio 来自对阵表，即为官方最终赔率
    					else // 多串过关
    					{
    						String personalRatio = ticket.getRatio().split("\\|")[i].split("/")[j];
    						newWinMatch.ratioList.add(personalRatio);  // ratio 来自ticket，即为个人即时赔率
    					}
    					*/
    					//单串和多串都是固定奖（赔率）
    					String personalRatio = ticket.getRatio().split("\\|")[i].split("/")[j];
						newWinMatch.ratioList.add(personalRatio);  // ratio 来自ticket，即为个人即时赔率
            			winList.add(newWinMatch);
            			break;
    				}
    			}
    		}
        }
        BigDecimal money = calculateMcN(winList, m, n);
        BigDecimal thetax=new BigDecimal(0);
        
        if(money.compareTo(new BigDecimal(10000)) > 0)
        {
        	thetax=calculateMcNTax(winList, m, n);
        }
        BigDecimal taxmoney = money.subtract(thetax);
        
        money=money.multiply(new BigDecimal(ticket.getMultiple()));
        taxmoney=taxmoney.multiply(new BigDecimal(ticket.getMultiple()));
        //.compareTo(new BigDecimal(10000)) > 0 ? money.multiply(new BigDecimal(0.8)) : money;;
        
        if(taxmoney.compareTo(new BigDecimal(0))<=0)
        	return null;
        else
        {
	        winDescribeTicket.setMoney(money);
	        winDescribeTicket.setTaxmoney(taxmoney);
	        winDescribeTicket.setNumber(1);  //该处为中奖注数，由于计算复杂，不能直接套用已有算法，故暂时未作计算，以后可以酌情加上
	        return winDescribeTicket;
        }
    }
    
    /** 计算 winList 描述的命中场次的 m串n 中奖金额 */
    private BigDecimal calculateMcN(List<WinDesc> winList, int m, int n) throws Exception
    {    		
		switch (m) {
			case 1 : 
				return calculateMc1(winList, 1, 0);  // 单场过关(1*1)
			case 2 :
	    		return calculateMc1(winList, 2, 0);  // 2串1
	    	case 3 :
	    		if (1 == n) // 3串1
	    			return calculateMc1(winList, 3, 0); 
	    		else if (3 == n)  // 3串3
	    			return calculateMc1(winList, 2, 0);
	    		else  // 3串4
	    			return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0));
	    	case 4 :
	    		switch (n) {
	    			case 1:  // 4串1
	    				return calculateMc1(winList, 4, 0);
	    			case 4:  // 4串4
	    				return calculateMc1(winList, 3, 0);
	    			case 5:  // 4串5
	    				return calculateMc1(winList, 3, 0).add(calculateMc1(winList, 4, 0));
	    			case 6:  // 4串6
	    				return calculateMc1(winList, 2, 0);
	    			case 11:  // 4串11
	    				return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0)).add(calculateMc1(winList, 4, 0));
	    			default:
	    				throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
	    		}
	    	case 5 :
	    		switch (n) {
				case 1:  // 5串1
					return calculateMc1(winList, 5, 0);
				case 5:  // 5串5
					return calculateMc1(winList, 4, 0);
				case 6:  // 5串6
					return calculateMc1(winList, 4, 0).add(calculateMc1(winList, 5, 0));
				case 10:  // 5串10
					return calculateMc1(winList, 2, 0);
				case 16:  // 5串16
					return calculateMc1(winList, 3, 0).add(calculateMc1(winList, 4, 0)).add(calculateMc1(winList, 5, 0));
				case 20:  // 5串20
					return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0));
				case 26:  // 5串26
					return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0)).add(calculateMc1(winList, 4, 0)).add(calculateMc1(winList, 5, 0));
				default:
					throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
			}
	    	case 6 :
	    		switch (n) {
				case 1:  // 6串1
					return calculateMc1(winList, 6, 0);
				case 6:  // 6串6
					return calculateMc1(winList, 5, 0);
				case 7:  // 6串7
					return calculateMc1(winList, 5, 0).add(calculateMc1(winList, 6, 0));
				case 15:  // 6串15
					return calculateMc1(winList, 2, 0);
				case 20:  // 6串20
					return calculateMc1(winList, 3, 0);
				case 22:  // 6串22
					return calculateMc1(winList, 4, 0).add(calculateMc1(winList, 5, 0)).add(calculateMc1(winList, 6, 0));
				case 35:  // 6串35
					return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0));
				case 42:  // 6串42
					return calculateMc1(winList, 3, 0).add(calculateMc1(winList, 4, 0)).add(calculateMc1(winList, 5, 0)).add(calculateMc1(winList, 6, 0));
				case 50:  // 6串50
					return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0)).add(calculateMc1(winList, 4, 0));
				case 57:  // 6串57
					return calculateMc1(winList, 2, 0).add(calculateMc1(winList, 3, 0)).add(calculateMc1(winList, 4, 0)).add(calculateMc1(winList, 5, 0)).add(calculateMc1(winList, 6, 0));
				default:
					throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
			}
	    	case 7 :
	    		switch (n) {
				case 1:  // 7串1
					return calculateMc1(winList, 7, 0);
	    		
	    		default:
	    			throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
	    		}
	    	case 8 :
	    		switch (n) {
	    		case 1:  // 8串1
	    			return calculateMc1(winList, 8, 0);
	    		
	    		default:
	    			throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
	    		}
	    	default:
	    		throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
		}
    }
    
    /** 计算 winList 描述的命中场次的 m串n 中奖金额需缴纳税 */
    private BigDecimal calculateMcNTax(List<WinDesc> winList, int m, int n) throws Exception
    {    		
		switch (m) {
			case 1 : 
				return calculateMc1Tax(winList, 1, 0);  // 单场过关(1*1)
			case 2 :
	    		return calculateMc1Tax(winList, 2, 0);  // 2串1
	    	case 3 :
	    		if (1 == n) // 3串1
	    			return calculateMc1Tax(winList, 3, 0); 
	    		else if (3 == n)  // 3串3
	    			return calculateMc1Tax(winList, 2, 0);
	    		else  // 3串4
	    			return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0));
	    	case 4 :
	    		switch (n) {
	    			case 1:  // 4串1
	    				return calculateMc1Tax(winList, 4, 0);
	    			case 4:  // 4串4
	    				return calculateMc1Tax(winList, 3, 0);
	    			case 5:  // 4串5
	    				return calculateMc1Tax(winList, 3, 0).add(calculateMc1Tax(winList, 4, 0));
	    			case 6:  // 4串6
	    				return calculateMc1Tax(winList, 2, 0);
	    			case 11:  // 4串11
	    				return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0)).add(calculateMc1Tax(winList, 4, 0));
	    			default:
	    				throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
	    		}
	    	case 5 :
	    		switch (n) {
				case 1:  // 5串1
					return calculateMc1Tax(winList, 5, 0);
				case 5:  // 5串5
					return calculateMc1Tax(winList, 4, 0);
				case 6:  // 5串6
					return calculateMc1Tax(winList, 4, 0).add(calculateMc1Tax(winList, 5, 0));
				case 10:  // 5串10
					return calculateMc1Tax(winList, 2, 0);
				case 16:  // 5串16
					return calculateMc1Tax(winList, 3, 0).add(calculateMc1Tax(winList, 4, 0)).add(calculateMc1Tax(winList, 5, 0));
				case 20:  // 5串20
					return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0));
				case 26:  // 5串26
					return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0)).add(calculateMc1Tax(winList, 4, 0)).add(calculateMc1Tax(winList, 5, 0));
				default:
					throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
			}
	    	case 6 :
	    		switch (n) {
				case 1:  // 6串1
					return calculateMc1Tax(winList, 6, 0);
				case 6:  // 6串6
					return calculateMc1Tax(winList, 5, 0);
				case 7:  // 6串7
					return calculateMc1Tax(winList, 5, 0).add(calculateMc1Tax(winList, 6, 0));
				case 15:  // 6串15
					return calculateMc1Tax(winList, 2, 0);
				case 20:  // 6串20
					return calculateMc1Tax(winList, 3, 0);
				case 22:  // 6串22
					return calculateMc1Tax(winList, 4, 0).add(calculateMc1Tax(winList, 5, 0)).add(calculateMc1Tax(winList, 6, 0));
				case 35:  // 6串35
					return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0));
				case 42:  // 6串42
					return calculateMc1Tax(winList, 3, 0).add(calculateMc1Tax(winList, 4, 0)).add(calculateMc1Tax(winList, 5, 0)).add(calculateMc1Tax(winList, 6, 0));
				case 50:  // 6串50
					return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0)).add(calculateMc1Tax(winList, 4, 0));
				case 57:  // 6串57
					return calculateMc1Tax(winList, 2, 0).add(calculateMc1Tax(winList, 3, 0)).add(calculateMc1Tax(winList, 4, 0)).add(calculateMc1Tax(winList, 5, 0)).add(calculateMc1Tax(winList, 6, 0));
				default:
					throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
			}
	    		
	    	case 7 :
	    		switch (n) {
				case 1:  // 7串1
					return calculateMc1Tax(winList, 7, 0);
				default:
					throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
			}
	    	case 8 :
	    		switch (n) {
				case 1:  // 8串1
					return calculateMc1Tax(winList, 8, 0);
				default:
					throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
			}
	    	default:
	    		throw new Exception("竞彩开奖计算奖金时发现 m*n 格式出错！");
		}
    }
    
    /** 计算 winList 描述的命中场次的 m串1 中奖金额 */
    /*
    private BigDecimal calculateMc1(List<WinDesc> winList, int m, int index)
    {
    	if (winList.size()-index < m)
    		return new BigDecimal(0);
    	
    	if (1 == m)  // 终止条件
    	{
    		BigDecimal winSum = new BigDecimal(0);
    		for (int i=index; i<winList.size(); i++)
    		{
    			for(int j=0; j<winList.get(i).ratioList.size(); j++)
    				winSum = winSum.add(new BigDecimal(String.valueOf(winList.get(i).ratioList.get(j))));
    		}
    		return winSum.multiply(new BigDecimal(2));
    	}
    	
    	BigDecimal include;
    	if (1 == winList.get(index).ratioList.size())
    	{
    		include = new BigDecimal(String.valueOf(winList.get(index).ratioList.get(0)));
    	}
    	else
    	{
    		include = new BigDecimal(String.valueOf(winList.get(index).ratioList.size()));  // 作废比赛，SP值均为1
    	}
		include = include.multiply(calculateMc1(winList, m-1, index+1));
		BigDecimal notInclude = calculateMc1(winList, m, index+1);
		return include.add(notInclude);
    }
    */

    int nextselected(int max,int now,Boolean p[])
    {
    	int i;
    	for(i=0;i<max;i++) {
    		if(i<=now)
    			continue;
    		if(p[i])
    			return (i);
    	}
    	return -1;
    }

    Boolean movepos(int max,int n,Boolean p[])
    {
    	int pre,i;
    	pre=0;
    	for(i=0;i<n;i++)
    		if(p[i])
    			pre++;

    	if(n>=(max-1))
    		return false;
    	if(p[n+1]==false) {
    		p[n]=false;
    		p[n+1]=true;
    		for(i=0;i<n;i++) {
    			if(i<pre)
    				p[i]=true;
    			else
    				p[i]=false;
    		}
    			
    		return true;
    	} else
    		return false;
    }
    Boolean nextpos(int max,int unit,Boolean pos[])
    {
    	
    	int i,j,n;
    	
    	Boolean iscontinue=true;

    	n=-1;
    	while(iscontinue) {
    		n=nextselected(max,n,pos);
    		if(n==-1)
    			return false;
    		if(movepos(max,n,pos))
    			return true;
    	}
    	return true;
    }
    
/** 计算 winList 描述的命中场次的 m串1 中奖金额纳税 ,index always0*/
private BigDecimal calculateMc1(List<WinDesc> winList, int m, int index)
{
	Boolean pos[]=new Boolean[20];
	int length;
	BigDecimal money,tax,taxsum,moneysum,currentratio,nowratio;
	int special;
	
	
	length=winList.size()-index;
	
	
	taxsum=new BigDecimal(0);
	
	for(int i=0;i<length;i++) pos[i]=false;
	for(int i=0;i<m;i++) pos[i]=true;
	
	moneysum=new BigDecimal(0);
	
	if(winList.size()<m) return moneysum;
	
	do
	{
		money=new BigDecimal(2);
		special=1;
		for (int i=0; i<winList.size(); i++)
		{

			if(pos[i]==true)
			{
				special*=winList.get(i).ratioList.size();
				currentratio=new BigDecimal(String.valueOf(winList.get(i).ratioList.get(0)));
				
				
				money=money.multiply(currentratio);
			}
				
		}
		money=money.setScale(2, BigDecimal.ROUND_HALF_UP);
		//money=money.multiply(new BigDecimal(2));
		
		if(special!=1)
		{
			money=money.multiply(new BigDecimal(special));
			money=money.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		
		
		moneysum=moneysum.add(money);
	
	}
	while(nextpos(length,m,pos)==true);
	
	return moneysum;
	
}


private BigDecimal calculateMc1Tax(List<WinDesc> winList, int m, int index)
{
	Boolean pos[]=new Boolean[20];
	int length;
	BigDecimal money,tax,taxsum,currentratio,nowratio;
	int special;
	
	
	length=winList.size()-index;
	
	
	taxsum=new BigDecimal(0);
	
	for(int i=0;i<length;i++) pos[i]=false;
	for(int i=0;i<m;i++) pos[i]=true;
	
	if(winList.size()<m) return taxsum;
	
	do
	{
		money=new BigDecimal(2);
		special=1;
		for (int i=0; i<winList.size(); i++)
		{

			if(pos[i]==true)
			{
				special*=winList.get(i).ratioList.size();
				currentratio=new BigDecimal(String.valueOf(winList.get(i).ratioList.get(0)));
				for(int j=1; j<winList.get(i).ratioList.size(); j++)
				{
					nowratio=new BigDecimal(String.valueOf(winList.get(i).ratioList.get(j)));
					currentratio=currentratio.add(nowratio);
				}
				//currentratio=new BigDecimal(String.valueOf(winList.get(i).ratioList.get(0)));
				money=money.multiply(currentratio);
			}
				
		}
		
		money=money.setScale(2, BigDecimal.ROUND_HALF_UP);
		//money=money.multiply(new BigDecimal(2));
		
		if(special!=1)
		{
			money=money.divide(new BigDecimal(special));
		}
		 if(money.compareTo(new BigDecimal(10000)) > 0)
		 {
			 money=money.multiply(new BigDecimal(0.2));
			 money=money.multiply(new BigDecimal(special));
			 taxsum=taxsum.add(money);
		 }
	
	}
	while(nextpos(length,m,pos)==true);
	
	return taxsum;
	
}
public static void main(String[] args) throws Exception
{
	JczqBingoCheck check = new JczqBingoCheck();
	Ticket ticket = new Ticket();
	ticket.setType(LotteryType.竞彩足球);
	ticket.setPlayType(PlayType.SPF);
	//ticket.setContent("SPF|140623007=3|1*1");
	ticket.setContent("SPF|140623007=3,140623006=0|2*1");
	ticket.setRatio("2.420|1.310");
	ticket.setMultiple(5);
	WinDescribeTicket winTicket = check.execute(ticket);
	System.out.println(winTicket.getTaxmoney());
}
}



class WinDesc
{
	public String matchNo;
	public List<String> ratioList = new ArrayList<String>();
	public WinDesc(String matchNo)
	{
		this.matchNo = matchNo;
	}
}

