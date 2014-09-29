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

import com.xsc.lottery.dyj.sendticket.bingocheck.JqBingoCheck;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.util.FetchlDataUtil;
import com.xsc.lottery.util.FileUtils;


@Component
public class _4cjqHandle extends BaseLotteryHandle {

	@Override
	public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
			List<PrizeLevel> prizeLevels) {
		  Map<String, PrizeLevel> bingoMap = new HashMap<String, PrizeLevel>();
	        for (PrizeLevel pr : prizeLevels) {
	            bingoMap.put(pr.getName(), pr);
	        }
	        String result = prizeLevels.get(0).getTerm().getResult();
	        JqBingoCheck dbc = new JqBingoCheck();
	        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
	        return list;
	}

	@Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
    	//String prizeStopdate;
        String fetchURl = "http://kaijiang.500wan.com/shtml/jq4/"
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
                for (int i = 0; i < 8; i++) {
                    resultStr = result.next();
                    if (i == 7) {
                        openResult = openResult + resultStr;
                    }
                    else {
                        openResult = openResult + resultStr + ",";
                    }
                }
                openResult+="-";
                for (int i = 0; i < 8; i++) {
                    resultStr = result.next();
                    if (i == 7) {
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
	public LotteryType getLotteryType() {
		
		return LotteryType.四场进球;
	}

	@Override
	public LotteryTerm getNextTerm(LotteryTerm term) {
		
		return null;
	}

	@Override
	public List<String> getTermCode(LotteryTerm term, int number) {
		 List<String> list = new ArrayList<String>();
	        for (int i = 0; i < number; i++) {
	            list.add((Integer.valueOf(term.getTermNo()) + 1) + "");
	        }
	        return list;
	}

	@Override
	public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
			throws Exception {
		 PlanItem item = new PlanItem();
	        try {
	        	//System.out.println(code+"liang");
	            if (!validataBetNum(code)) {
	                throw new Exception("4场进球投注号码格式错误!");
	            }
	        }
	        catch (Exception e) {
	            throw new Exception("4场进球投注号码格式错误!");
	        }
	        item.setBetCount(validataBetCount(code));
	        item.setContent(code);
	        item.setPlayType(PlayType.valueOf(code.split("-")[0]));
	        item.setOneMoney(oneMoney);
	        return item;
	}

	@Override
	protected List<Ticket> unpackTicket(Order order, PlanItem item) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";
        if (item.getPlayType().equals(PlayType.ds)
                || item.getPlayType().equals(PlayType.fs)) {
            formatNum = item.getContent();
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
        //ticket.setContent(formatNum.split("-")[1]);
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticketList.add(ticket);
        return ticketList;
	}

	@Override
	protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item) {
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
	
	@Override
	public int validataBetCount(String code) throws Exception {
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
	public boolean validataBetNum(String result) throws Exception 
	{
		 String[] codestr = result.split("-");
         if(codestr.length < 2) {
             return true;
         }
		return !codestr[1].matches("([0123]{1,4}){8}");
	}

	@Override
	public boolean validataRepeat(String code) throws Exception {
		
		return false;
	}

	@Override
	public List<PlanItem> validateUploadFile(String filePath,
			BigDecimal oneMoney, int multiple, BigDecimal totalMoney)
			throws Exception 
	{
		
		int sumAmount = 0;
    	List<PlanItem> list = new ArrayList<PlanItem>();
    	
    	try {
        	String [] file_codes = FileUtils.readFile(filePath).split("\n");
        	String [] firstSentence = file_codes[0].split("\\s+");
        	
        	//一彩票软件生成的文件格式验证
        	if(firstSentence[0].equals("369cai") && firstSentence[1].equals("jq") && 
        	    firstSentence[2].equals("ver1"))  
        	{
        		int fileBetNum = Integer.parseInt(firstSentence[3]);
        		int fileRandomN = Integer.parseInt(firstSentence[4]);
        		
        		if(fileBetNum != file_codes.length-1) {
        			throw new RuntimeException("文件格式验证失败，首行注数与文件中注数不一致，请联系客服");
        		}
        		
        		for (int i = 1; i <= fileBetNum; i++) {
        			String code = "fs-";
        			file_codes[i] = file_codes[i].trim();
        			String [] myBetStr = file_codes[i].split("\\s+");
        			
        			if(myBetStr.length != 8) {
        				throw new RuntimeException("文件格式验证失败");
        			}
        			
        			for (int j = 0; j < myBetStr.length - 1; j++) {
        				code += Integer.parseInt(myBetStr[j]) - fileRandomN + ",";
        			}
        			code += Integer.parseInt(myBetStr[myBetStr.length-1]) - fileRandomN;
    				
        			try {
	                    PlanItem planitem = perseCodeStringTOPlanItem(code, oneMoney);
	                    sumAmount += planitem.getBetCount();
	                    list.add(planitem);
        			}
                    catch (Exception e) {
                        throw e;
                    }
                }
        	}
        	//普通文件格式验证
        	else {
            	for (String code : file_codes) {
            		code = "fs-" + code;
                    try {
                        PlanItem planitem = perseCodeStringTOPlanItem(code, oneMoney);
                        sumAmount += planitem.getBetCount();
                        list.add(planitem);
                    }
                    catch (Exception e) {
                        throw e;
                    }
                }
        	}
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "\n文件上传失败,请联系客服!");
        }
        
        if (oneMoney.intValue() * multiple * sumAmount != totalMoney.intValue()
                || totalMoney.intValue() <= 0) {
            throw new RuntimeException("方案金额错误, 文件内容金额与您填写金额不符!\n文件上传失败,请联系客服!");
        }
        
        return list;
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
   
    public static void main(String arges[])
    {
    	String string = "05,08,19,20,33,23#03,02";
    	_ssqHandle sd = new _ssqHandle();
    	String sString = sd.changeToWZLContent(string);
    	System.out.println(sString);
    }

	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}

}
