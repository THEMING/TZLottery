package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTML;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.xsc.lottery.dyj.sendticket.bingocheck.Zc6cbBingoCheck;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.util.FetchlDataUtil;

@Component
public class _6cbHandle extends BaseLotteryHandle
{
    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
    	//String prizeStopdate;
        String fetchURl = "http://kaijiang.500wan.com/shtml/zc6/"
                + term.getTermNo().substring(2) + ".shtml";
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> result = fetchl.parserHtml(fetchURl, HTML.Tag.TR)
                .iterator();
        String resultStr, openResult = "";
        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
        
        while (result.hasNext()) {
            resultStr = result.next();
            if(resultStr.trim().indexOf("兑奖截止日期：")>-1)
            {
                for (int i = 0; i < 6; i++) {
                    resultStr = result.next();
                    if (i == 11) {
                        openResult = openResult + resultStr;
                    }
                    else {
                        openResult = openResult + resultStr + ",";
                    }
                }
                openResult+="-";
                for (int i = 0; i < 12; i++) {
                    resultStr = result.next();
                    if (i == 11) {
                        openResult = openResult + resultStr;
                    }
                    else {
                        openResult = openResult + resultStr + ",";
                    }
                }
                openResult+="-";
                for (int i = 0; i < 12; i++) {
                    resultStr = result.next();
                    if (i == 11) {
                        openResult = openResult + resultStr;
                    }
                    else {
                        openResult = openResult + resultStr + ",";
                    }
                }
                term.setResult(openResult);
            }
            else if (resultStr.trim().indexOf("本期销量") > -1) {
                term.setTotalSale(new BigDecimal(result.next().replaceAll("元",
                        "").replaceAll(",", "").replace(":", "")));
               
            }
            else if (resultStr.trim().indexOf("奖池滚存：") > -1) {
                term.setPrizePool(new BigDecimal(result.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            }
            else if (resultStr.indexOf("一等奖") > -1) {
                prizeLevels.add(new PrizeLevel(1, term, Integer
                        .parseInt(result.next()), "一等奖", new BigDecimal(
                        		result.next().replaceAll(",", "")),
                        BigDecimal.ZERO));
               
            }
        }
        term.setPrizeLevels(prizeLevels);
        return term;
    }
    @Override
    public LotteryType getLotteryType()
    {
        return LotteryType.足彩6场半;
    }
    
    
    
    public List<String> getTermCode(LotteryTerm term, int number)
    {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < number; i++) {
            list.add((Integer.valueOf(term.getTermNo()) + 1) + "");
        }
        return list;
    }

    @Override
    public LotteryTerm getNextTerm(LotteryTerm term)
    {
        //需要手工输入
        LotteryTerm nextTerm = null;
        return nextTerm;
    }

    @Override
    public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
            throws Exception
    {
        PlanItem item = new PlanItem();
        try {
        	//System.out.println(code+"liang");
            if (validataBetNum(code)) {
                throw new Exception("足彩6场半投注号码格式错误!");
            }
        }
        catch (Exception e) {
            throw new Exception("足彩6场半投注号码格式错误!");
        }
        item.setBetCount(validataBetCount(code));
        item.setContent(code.split("-")[1]);
        item.setPlayType(PlayType.valueOf(code.split("-")[0]));
        item.setOneMoney(oneMoney);
        return item;
    }
    
    
    /*
     * 胜负彩拆票（大赢家）
     */
    @Override
    protected List<Ticket> unpackTicket(Order order, PlanItem item)
    {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        
        ticket.setCount(item.getBetCount());
        ticket.setMoney(item.getOneMoney().multiply(
                new BigDecimal(item.getBetCount())).multiply(
                BigDecimal.valueOf(order.getMultiple())));
        ticket.setMultiple(order.getMultiple());
        ticket.setItem(item);
        ticket.setOrder(order);
        ticket.setSendTicketTime(Calendar.getInstance());
        ticket.setTermNo(order.getTerm().getTermNo());
        ticket.setType(order.getType());
        ticket.setContent(item.getContent());
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticketList.add(ticket);
        return ticketList;
    }
    
    /**
     * 胜负彩拆票（我中了）
     */
    @Override
    protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item)
    {
    	 List<Ticket> ticketList = new ArrayList<Ticket>();
         Ticket ticket = new Ticket();
         String formatNumForWZL = "";//我中了的投注格式
         String string = "";
         string = changeToWZLContent(item.getContent());
         if(item.getPlayType() == PlayType.ds || item.getBetCount() == 1)
         {
         	formatNumForWZL = "00-01-" + string;
         }
         else
         {
         	formatNumForWZL = "00-02-" + string;
         }
         ticket.setCount(item.getBetCount());
         ticket.setMoney(item.getOneMoney().multiply(
                 new BigDecimal(item.getBetCount())).multiply(
                 BigDecimal.valueOf(order.getMultiple())));
         ticket.setMultiple(order.getMultiple());
         ticket.setItem(item);
         ticket.setOrder(order);
         ticket.setSendTicketTime(Calendar.getInstance());
         ticket.setTermNo(order.getTerm().getTermNo());
         ticket.setType(order.getType());
         ticket.setContent(item.getContent());
         ticket.setBetContent(formatNumForWZL);
         ticket.setPlayType(item.getPlayType());
         ticket.setType(order.getType());
         ticket.setIssue(order.getTerm().getTermNo().substring(2));
         ticketList.add(ticket);
         return ticketList;
    }

    //ds-1,0,1,1,1,3,1,1,3,1,3,1,1      1注
    //fs-13,03,1,1,1,3,1,1,3,1,3,1,1    4注
    //默认都用fs
    //true 表示不合格
    @Override
    public boolean validataBetNum(String result)
    {
        String[] codestr = result.split("-");
        if(codestr.length < 2) {
            return true;
        }
        if (PlayType.fs.equals(PlayType.valueOf(codestr[0]))) {
            //System.out.println(codestr[1]);
            return !codestr[1].matches("^([310]{1,3},){11}[310]{1,3}");
        }
        return true;
    }
    
    @Override
    public boolean validataRepeat(String code)
    {
        return false;
    }
    
    @Override
    public int validataBetCount(String code)
    {
        int count = 1;
        String codestr = code.split("-")[1];
        String codes[] = codestr.split("\\,");
        for(int i = 0; i < codes.length; i++) {
            if(codes[i].length() > 1) {
                count = count * codes[i].length();
            }
        }
        return count;
    }
    
    @Override
    public List<PlanItem> validateUploadFile(String filePath, 
    		BigDecimal oneMoney, int multiple, BigDecimal totalMoney) throws Exception
    {
         throw new Exception("暂不提供!");
    }

    /**
     * 开奖
     */
    @Override
    public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
            List<PrizeLevel> prizeLevels)
    {
        Map<String, PrizeLevel> bingoMap = new HashMap<String, PrizeLevel>();
        for (PrizeLevel pr : prizeLevels) {
            bingoMap.put(pr.getName(), pr);
        }
        String result = prizeLevels.get(0).getTerm().getResult();
        Zc6cbBingoCheck dbc = new Zc6cbBingoCheck();
        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
        return list;
    }
    
    static public void main(String argv[])
    {
        @SuppressWarnings("unused")
		List<String> list = new ArrayList<String>();
 
        //System.out.println(list.size());
        
        _6cbHandle h = new _6cbHandle();
        String tes1="fs-310,310,310,0,0,0,0,0,0,0,1,1";
        String test="fs-310,310,310,1,1,1,3,0,0,0,1,1";
        System.out.println(h.validataBetNum(tes1));
        System.out.println(h.validataBetNum(test));
        System.out.println(h.validataBetCount(test));
        
    }
	@Override
	public void parseXML(Element element) throws Exception {
		// TODO Auto-generated method stub
		
	}

    
    @Override
    public String changeToWZLContent(String content)
    {
    	String[] temp0 = content.split(",");//,分割生成的字符串组
    	String string = "";
    	for (int i = 0; i < temp0.length; i++) {
        	StringBuilder sb = new StringBuilder();
        	//String sortString = sort(temp0[i]);
        	String[] temp00 = temp0[i].split("");
			for (int j = 0; j < temp00.length; j++) {
				sb.append(temp00[j]);
				if (j < temp00.length - 1) {
					sb.append(" ");	
				}
			}
			if (i < temp0.length - 1) {
				sb.append(",");
			}
			string += sb.toString().trim();
		}
    	return string;
    }
    
    @Override
    public String sort(String content)
    {
    	String[] s = content.split(",");
    	int[] nums = new int[s.length];
    	int temp = 0;
    	String use = "";
    	String ss = "";
    	for (int i = 0; i < s.length; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
    	for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
			if (nums[i] < 10) {
				ss = "0" + nums[i];
			}
			else {
				ss = "" + nums[i];
			}
			if (i == nums.length - 1) {
				use = use + ss;
			}
			else {
				use = use + ss + ",";
			}
		}
    	return use;
    }
	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}
}
