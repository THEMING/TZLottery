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

import com.xsc.lottery.dyj.sendticket.bingocheck.QxcBingoCheck;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.util.FetchlDataUtil;
import com.xsc.lottery.util.MathUtil;

@Component
public class _qxcHandle extends BaseLotteryHandle
{
    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
        String fetchURl = "http://kaijiang.500wan.com/shtml/qxc/"
            + term.getTermNo().substring(2) + ".shtml";
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> result = fetchl.parserHtml(fetchURl, HTML.Tag.TR)
                .iterator();
        String resultStr, openResult = "";
        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
        while (result.hasNext()) {
            resultStr = result.next();
            if (resultStr.trim().indexOf("开奖号码") > -1) {
                for (int i = 0; i < 7; i++) {
                    resultStr = result.next();
                    if (i == 6) {
                        openResult = openResult + resultStr;
                    }
                    else {
                        openResult = openResult + resultStr + ",";
                    }
                }
                term.setResult(openResult);
            }
            else if (resultStr.indexOf("本期销量") > -1) {
                term.setTotalSale(new BigDecimal(result.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            }
            else if (resultStr.indexOf("奖池滚存") > -1) {
                term.setPrizePool(new BigDecimal(result.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            }
            else if (resultStr.indexOf("一等奖") > -1) {
                prizeLevels.add(new PrizeLevel(1, term, Integer
                        .parseInt(result.next()), "一等奖", new BigDecimal(
                                result.next().replaceAll(",", "")),
                        BigDecimal.ZERO));
                String levelName;
                for (int i = 2; i < 7; i++) {
//                    levelName = new String(result.next().trim().getBytes("gb2312"), "utf8");
                	levelName = result.next().trim();
                    prizeLevels.add(new PrizeLevel(i, term, Integer
                            .parseInt(result.next()), levelName,
                            new BigDecimal(result.next().replaceAll(",",
                                    "")), BigDecimal.ZERO));
                }
            }
            else if(resultStr.indexOf(">>") > -1)
            {
            	break;
            }
        }
        term.setPrizeLevels(prizeLevels);
        return term;
    }

    @Override
    public LotteryType getLotteryType()
    {
        return LotteryType.七星彩;
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

    private int getQxc(int dayOfWeek)
    {
        switch (dayOfWeek) 
        {
        case 3:
            return 3;
        default:
            return 2;
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
        stop.add(Calendar.DAY_OF_MONTH, getQxc(dayOfWeek));
        stop.set(Calendar.HOUR_OF_DAY, 19);
        stop.set(Calendar.MINUTE, 30);

        // 合买截止时间
        stopTo.setTime(stop.getTime());
        stopTo.set(Calendar.HOUR_OF_DAY, 19);
        stopTo.set(Calendar.MINUTE, 30);

        // 开奖时间
        open.setTime(stop.getTime());
        open.set(Calendar.HOUR_OF_DAY, 20);
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
    public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
            throws Exception
    {
        PlanItem item = new PlanItem();
        try {
            if (validataBetNum(code) || validataRepeat(code)) {
                throw new Exception("七星彩投注号码格式错误!");
            }
        } catch (Exception e) {
            throw new Exception("七星彩投注号码格式错误!");
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
        if (item.getPlayType().equals(PlayType.ds)
                || item.getPlayType().equals(PlayType.fs)) {
            formatNum = item.getContent();
        }
        
        string = changeToWZLContent(item.getContent());
        
        if (item.getPlayType().equals(PlayType.ds) || item.getBetCount() == 1) {
			formatNumForWZL = "00-01-" + string;
		}else {
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
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticket.setBetContent(formatNumForWZL);
        ticket.setIssue(order.getTerm().getTermNo().substring(2).trim()); 
        //ticket.setIssue(order.getTerm().getTermNo().trim());
        ticketList.add(ticket);
        return ticketList;
    }

    
    @Override
    public boolean validataBetNum(String result)
    {
        String[] codestr = result.split("-");

        if (PlayType.fs.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1]
                    .matches("^[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}");
        }
        return true;
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
        QxcBingoCheck dbc = new QxcBingoCheck();
        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
        return list;
    }

    @Override
    public int validataBetCount(String code)
    {
        int count = 0;
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codeStr = code.split("-")[1].split("\\,");
            int n1 = codeStr[0].length();
            int n2 = codeStr[1].length();
            int n3 = codeStr[2].length();
            int n4 = codeStr[3].length();
            int n5 = codeStr[4].length();
            int n6 = codeStr[5].length();
            int n7 = codeStr[6].length();
            count = n1 * n2 * n3 * n4 * n5 * n6 * n7;
        }
        return count;
    }

    @Override
    public boolean validataRepeat(String code)
    {
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codeStr = code.split("-")[1].split("|");
            if (MathUtil.repeatChar(codeStr[0], 0, 9)
                    || MathUtil.repeatChar(codeStr[1], 0, 9)
                    || MathUtil.repeatChar(codeStr[2], 0, 9)
                    || MathUtil.repeatChar(codeStr[3], 0, 9)
                    || MathUtil.repeatChar(codeStr[4], 0, 9)
                    || MathUtil.repeatChar(codeStr[5], 0, 9)
                    || MathUtil.repeatChar(codeStr[6], 0, 9)) {
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
    
    public static void main(String a[]) {
        System.out.println("1,2,3,4,5,6,7".matches("^[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}"));
    }
    
    
    @Override
    public String changeToWZLContent(String content)
    {
    	String[] temp0 = content.split(",");//,分割生成的字符串组
    	String string = "";
    	for (int i = 0; i < temp0.length; i++) {
        	StringBuilder sb = new StringBuilder();
        	String sortString = sort(temp0[i]);
			String[] temp00 = sortString.split("");
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
    	String[] s = content.split("");
    	int[] nums = new int[s.length-1];
    	int temp = 0;
    	String use = "";
    	for (int i = 1; i < s.length; i++) {
			nums[i-1] = Integer.parseInt(s[i]);
		}
    	for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
			use += nums[i];
		}
    	return use;
    }

	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}
}
