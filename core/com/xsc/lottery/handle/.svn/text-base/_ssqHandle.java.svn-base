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

import com.xsc.lottery.dyj.sendticket.bingocheck.SsqBingoCheck;
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
import com.xsc.lottery.util.MathUtil;

@Component
public class _ssqHandle extends BaseLotteryHandle
{
    @Override
    public LotteryType getLotteryType()
    {
        return LotteryType.双色球;
    }

    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
        String fetchURl = "http://kaijiang.500wan.com/shtml/ssq/"
                + term.getTermNo().substring(2) + ".shtml";
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> fetchResutl = fetchl.parserHtml(fetchURl, HTML.Tag.TR)
                .iterator();
        String str_0, result = "";
        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
        while (fetchResutl.hasNext()) {
            str_0 = fetchResutl.next().trim();
            if (str_0.indexOf("开奖号码") > -1) {
                for (int i = 0; i < 6; i++) {
                    str_0 = fetchResutl.next().trim();
                    if (i == 5) {
                        result = result + str_0;
                    }
                    else {
                        result = result + str_0 + ",";
                    }
                }
                term.setResult(result + "|" + fetchResutl.next().trim());
            }
            else if (str_0.indexOf("出球顺序") > -1) {
                term.setOrderResult(fetchResutl.next().replaceAll(" ", ","));
            }
            else if (str_0.indexOf("本期销量") > -1) {
                term.setTotalSale(new BigDecimal(fetchResutl.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            }
            else if (str_0.indexOf("奖池滚存") > -1) {
                term.setPrizePool(new BigDecimal(fetchResutl.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            }
            else if (str_0.indexOf("一等奖") > -1) {
                prizeLevels.add(new PrizeLevel(1, term, Integer
                        .parseInt(fetchResutl.next()), "一等奖", new BigDecimal(
                        fetchResutl.next().replaceAll(",", "")),
                        BigDecimal.ZERO));
                String levelName;
                for (int i = 2; i < 7; i++) {
//                	levelName = changeEncoding(fetchResutl.next().trim());
                	levelName = fetchResutl.next().trim();
                    prizeLevels.add(new PrizeLevel(i, term, Integer
                            .parseInt(fetchResutl.next()), levelName,
                            new BigDecimal(fetchResutl.next().replaceAll(",",
                                    "")), BigDecimal.ZERO));
                }
            }
        }
        term.setPrizeLevels(prizeLevels);
        return term;
    }

    public List<String> getTermCode(LotteryTerm term, int number)
    {
        List<String> list = new ArrayList<String>(number);
        String termno = term.getTermNo();
        for (int i = 0; i < number; i++) {
            list.add((Integer.valueOf(termno) + 1) + "");
            termno = list.get(i);
        }
        return list;
    }

    private int getSsq(int dayOfWeek)
    {
        switch (dayOfWeek) 
        {
        case 1:
            return 2;
        case 3:
            return 2;
        default:
            return 3;
        }
    }

    @Override
    public LotteryTerm getNextTerm(LotteryTerm term)
    {
        LotteryTerm nextTerm = new LotteryTerm();
        nextTerm.setTermNo((Integer.valueOf(term.getTermNo()) + 1) + "");
        nextTerm.setType(term.getType());
        Calendar start = term.getStopSaleTime();
        Calendar stop = Calendar.getInstance();
        Calendar stopTo = Calendar.getInstance();
        Calendar open = Calendar.getInstance();
        Calendar sendPri = Calendar.getInstance();

        stop.setTime(start.getTime());
        // 截止打票时间
        int dayOfWeek = stop.get(Calendar.DAY_OF_WEEK);
        stop.add(Calendar.DAY_OF_MONTH, getSsq(dayOfWeek));
        stop.set(Calendar.HOUR_OF_DAY, 19);
        stop.set(Calendar.MINUTE, 30);

        // 合买截止时间
        stopTo.setTime(stop.getTime());
        stopTo.set(Calendar.HOUR_OF_DAY, 19);
        stopTo.set(Calendar.MINUTE, 30);

        // 开奖时间
        open.setTime(stop.getTime());
        open.set(Calendar.HOUR_OF_DAY, 21);
        open.set(Calendar.MINUTE, 30);

        // 兑奖时间
        sendPri.setTime(stop.getTime());
        sendPri.set(Calendar.HOUR_OF_DAY, 23);
        sendPri.set(Calendar.MINUTE, 0);

        nextTerm.setStartSaleTime(start);
        nextTerm.setStopSaleTime(stop);
        nextTerm.setOpenPrizeTime(open);
        nextTerm.setStopTogetherSaleTime(stopTo);
        nextTerm.setSendPrizeTime(sendPri);

        return nextTerm;
    }
    
    @Override
    public List<PlanItem> validateUploadFile(String filePath, 
    		BigDecimal oneMoney, int multiple, BigDecimal totalMoney) throws Exception
    {
    	//上传只能是标准格式  "fs-"
    	int sumAmount = 0;
    	List<PlanItem> list = new ArrayList<PlanItem>();
    	
    	try {
        	String [] file_codes = FileUtils.readFile(filePath).split("\n");
        	String [] firstSentence = file_codes[0].split("\\s+");
        	
        	//一彩票软件生成的文件格式验证
        	if(firstSentence[0].equals("369cai") && firstSentence[1].equals("ssq") && 
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
        			
        			for (int j = 0; j < myBetStr.length-2; j++) {
        				code += number2String(Integer.parseInt(myBetStr[j]) - fileRandomN) + ",";
        			}
        			code += number2String(Integer.parseInt(myBetStr[myBetStr.length-2]) - fileRandomN) + "|";
        			code += number2String(Integer.parseInt(myBetStr[myBetStr.length-1]) - fileRandomN);
    				
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
    public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
            throws Exception
    {
        PlanItem item = new PlanItem();
        
        try {
            if (validataBetNum(code) || validataRepeat(code)) {
                throw new Exception("双色球投注号码格式错误!");
            }
        }
        catch (Exception e) {
            throw new Exception("双色球投注号码格式错误!");
        }
        
        item.setBetCount(validataBetCount(code));
        item.setContent(code.split("-")[1]);
        item.setPlayType(PlayType.valueOf(code.split("-")[0]));
        item.setOneMoney(oneMoney);
        return item;
    }

    @Override
    protected List<Ticket> unpackTicket(Order order, PlanItem item)
    {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";
        
        if(item.getPlayType().equals(PlayType.dt)) {
            formatNum = item.getContent().replace(",", " ").replace("|", "-")
                    .replace("#", "$");
        }
        else {
            formatNum = item.getContent().replace(",", " ").replace("|", "-");
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
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticketList.add(ticket);
        return ticketList;
    }
    
    @Override
    protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item)
    {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";//大赢家的投注格式
        String formatNumForWZL = "";//我中了的投注格式
        String string = "";
        if(item.getPlayType().equals(PlayType.dt)) {
            formatNum = item.getContent().replace(",", " ").replace("|", "-")
                    .replace("#", "$");
        }
        else {
            formatNum = item.getContent().replace(",", " ").replace("|", "-");
            string = this.changeToWZLContent(item.getContent());
            if (item.getBetCount() == 1) {
            	
                formatNumForWZL = "00-01-" + string;
			}
            else {
            	formatNumForWZL = "00-02-" + string;
			}
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
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticket.setBetContent(formatNumForWZL);
        ticket.setIssue(order.getTerm().getTermNo());
        ticketList.add(ticket);
        return ticketList;
    }
    
    @Override
    public boolean validataBetNum(String result)
    {
        String[] codestr = result.split("-");
        if (PlayType.fs.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^([0-3][0-9],){5,34}[0-3][0-9]\\|[0-1][0-9](,[0-1][0-9]){0,15}");
        }
        
        // 05,17,18,30#06|06#07
        if (PlayType.dt.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-3][0-9](,[0-3][0-9]){0,5}#[0-3][0-9](,[0-3][0-9]){0,31}\\|[0-1][0-9](,[0-1][0-9]){0,15}");
        }
        
        return true;
    }

    @Override
    public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
            List<PrizeLevel> prizeLevels)
    {
        Map<String, PrizeLevel> bingoMap = new HashMap<String, PrizeLevel>();
        for (PrizeLevel pr : prizeLevels) {
            bingoMap.put(pr.getName(), pr);
        }
        String result = prizeLevels.get(0).getTerm().getResult();
        SsqBingoCheck dbc = new SsqBingoCheck();
        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
        return list;
    }

    @Override
    public int validataBetCount(String code)
    {
        int count = 0;
        if(PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\|");
            int nr = codestr[0].split("\\,").length;
            int nb = codestr[1].split("\\,").length;
            count = MathUtil.getCombinationCount(nr, 6)
                    * MathUtil.getCombinationCount(nb, 1);
        }
        
        // 05,17,18,30#06|06#07
        if(PlayType.dt.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\|");
            String[] coderdtr = codestr[0].split("#");
            int nrd = coderdtr[0].split("\\,").length;
            int nrt = coderdtr[1].split("\\,").length;
            int nbd = codestr[1].split("\\,").length;
            count = MathUtil.getCombinationCount(nrt, 6 - nrd)
                    * MathUtil.getCombinationCount(nbd, 1);
        }

        return count;
    }

    @Override
    public boolean validataRepeat(String code)
    {
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codeStr = code.split("-")[1].split("\\|");
            if (MathUtil.repeatString(codeStr[0], 1, 33)
                    && MathUtil.repeatString(codeStr[1], 1, 16))
                return true;
        }

        if (PlayType.dt.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\|");
            String coderdtr = codestr[0].replace("#", ",");
            String coderdtb = codestr[1].replace("#", ",");
            if (MathUtil.repeatString(coderdtr, 1, 33)
                    && MathUtil.repeatString(coderdtb, 1, 16)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
	public void parseXML(Element element) throws Exception
    {
    	throw new RuntimeException("暂不提供");
	}
    
    @Override
    public String changeToWZLContent(String content)
    {
    	String[] temp0 = content.split("\\|");//,分割生成的字符串组
    	String string = "";
    	for (int i = 0; i < temp0.length; i++) {
        	String sortString = sort(temp0[i]);
        	if (i == 0) {
				string += sortString; 
			}
        	else {
    			string = string + "#" + sortString;
			}
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
		 List<Ticket> ticketList = new ArrayList<Ticket>();
	        Ticket ticket = new Ticket();
	        String formatNum = "";//大赢家的投注格式
	        if(item.getPlayType().equals(PlayType.dt)) {
	            formatNum = item.getContent().replace(",", " ").replace("|", "-")
	                    .replace("#", "$");
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
	        ticket.setContent(formatNum);
	        ticket.setPlayType(item.getPlayType());
	        ticket.setType(order.getType());
	        ticket.setBetContent(item.getContent());
	        ticket.setIssue(order.getTerm().getTermNo());
	        ticketList.add(ticket);
	        return ticketList;
	}
}
