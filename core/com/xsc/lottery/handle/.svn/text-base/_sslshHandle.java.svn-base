package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.MathUtil;


@Component
public class _sslshHandle extends BaseLotteryHandle
{
    @Override
    public LotteryType getLotteryType()
    {
        return LotteryType.上海时时乐;
    }

    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
        return null;
    }

    // 返回 期号_截止时间
    public List<String> getTermCode(LotteryTerm term, int number)
    {
        List<String> list = new ArrayList<String>(number);
        list.add(term.getTermNo() + "_"
                + DateUtil.toyyyy_MM_dd_HH_mm(term.getStopSaleTime()));
        LotteryTerm newTerm = term;
        for (int i = 0; i < number; i++) {
            newTerm = getNextTerm(newTerm);
            list.add(newTerm.getTermNo() + "_"
                    + DateUtil.toyyyy_MM_dd_HH_mm(newTerm.getStopSaleTime()));
        }
        return list;
    }

    @Override
    public LotteryTerm getNextTerm(LotteryTerm term)
    {
        int ycminut = 8;
        int kjycm = 2;
        int djycm = 10;
        LotteryTerm nextTerm = new LotteryTerm();

        nextTerm.setType(term.getType());
        Calendar start = term.getStopSaleTime();
        Calendar stop = Calendar.getInstance();
        Calendar open = Calendar.getInstance();
        Calendar sendPri = Calendar.getInstance();

        // 减去提前
        start.add(Calendar.MINUTE, ycminut);
        int hour = start.get(Calendar.HOUR_OF_DAY);
        int minute = start.get(Calendar.MINUTE);

        int code = Integer.parseInt(term.getTermNo().substring(8, 10));

        // 还原
        start.add(Calendar.MINUTE, -ycminut);
        stop.setTime(start.getTime());
        if (((hour == 10 && minute == 30) || (hour >= 11))
                && ((hour == 21 && minute < 30) || (hour < 21))) {
            stop.add(Calendar.MINUTE, 30);
        } else {
            stop.add(Calendar.DAY_OF_MONTH, 1);
            stop.set(Calendar.HOUR_OF_DAY, 10);
            stop.set(Calendar.MINUTE, 30);
            stop.set(Calendar.SECOND, 0);
            stop.add(Calendar.MINUTE, -ycminut);
        }

        // 开奖时间
        open.setTime(stop.getTime());
        open.add(Calendar.MINUTE, kjycm + ycminut);
        // 派奖时间
        sendPri.setTime(stop.getTime());
        sendPri.add(Calendar.MINUTE, djycm + ycminut);

        if (code >= 23)
            nextTerm.setTermNo(DateUtil.toYYYYMMDD(stop) + "01");
        else
            nextTerm.setTermNo((Integer.valueOf(term.getTermNo()) + 1) + "");

        nextTerm.setStartSaleTime(start);
        nextTerm.setStopSaleTime(stop);
        nextTerm.setOpenPrizeTime(open);
        nextTerm.setSendPrizeTime(sendPri);
        return nextTerm;
    }

    @Override
    public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
            throws Exception
    {
        PlanItem item = new PlanItem();
        try {
            if (validataBetNum(code) && validataRepeat(code)) {
                throw new Exception("时时乐投注号码格式错误!");
            }
        } catch (Exception e) {
            throw new Exception("时时乐投注号码格式错误!");
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
        if (item.getPlayType().equals(PlayType.fs)) {
            formatNum = "1|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zx_hz)) {
            formatNum = "S1|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zs_hz)) {
            formatNum = "S3|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zs_bh)) {
            formatNum = "F3|" + item.getContent().replace(",", "");
        }
        if (item.getPlayType().equals(PlayType.zl_hz)) {
            formatNum = "S6|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zl_bh)) {
            formatNum = "F6|" + item.getContent().replace(",", "");
        }
        if (item.getPlayType().equals(PlayType.q2)) {
            formatNum = "2D|" + item.getContent() + ",-";
        }
        if (item.getPlayType().equals(PlayType.h2)) {
            formatNum = "2D|-," + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.q1)) {
            formatNum = "1D|" + item.getContent() + ",-,-";
        }
        if (item.getPlayType().equals(PlayType.h1)) {
            formatNum = "1D|-,-," + item.getContent();
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
        String formatNum = "";
        if (item.getPlayType().equals(PlayType.fs)) {
            formatNum = "1|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zx_hz)) {
            formatNum = "S1|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zs_hz)) {
            formatNum = "S3|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zs_bh)) {
            formatNum = "F3|" + item.getContent().replace(",", "");
        }
        if (item.getPlayType().equals(PlayType.zl_hz)) {
            formatNum = "S6|" + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.zl_bh)) {
            formatNum = "F6|" + item.getContent().replace(",", "");
        }
        if (item.getPlayType().equals(PlayType.q2)) {
            formatNum = "2D|" + item.getContent() + ",-";
        }
        if (item.getPlayType().equals(PlayType.h2)) {
            formatNum = "2D|-," + item.getContent();
        }
        if (item.getPlayType().equals(PlayType.q1)) {
            formatNum = "1D|" + item.getContent() + ",-,-";
        }
        if (item.getPlayType().equals(PlayType.h1)) {
            formatNum = "1D|-,-," + item.getContent();
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
    public List<PlanItem> validateUploadFile(String filePath, 
    		BigDecimal oneMoney, int multiple, BigDecimal totalMoney) throws Exception
    {
         throw new Exception("暂不提供!");
    }

    @Override
    public boolean validataBetNum(String result)
    {
        String[] codestr = result.split("-");

        if (PlayType.fs.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1]
                    .matches("^[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}");
        }
        if (PlayType.q2.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-9]{1,10}\\,[0-9]{1,10}");
        }
        if (PlayType.h2.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-9]{1,10}\\,[0-9]{1,10}");
        }
        if (PlayType.q1.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-9]{1,10}");
        }
        if (PlayType.h1.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-9]{1,10}");
        }
        //
        if (PlayType.zx_hz.equals(PlayType.valueOf(codestr[0]))) {
            if (Integer.parseInt(codestr[1]) >= 0
                    && Integer.parseInt(codestr[1]) <= 27) {
                return true;
            }
        }
        if (PlayType.zs_bh.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-9](,[0-9]){1,9}");
        }
        if (PlayType.zs_hz.equals(PlayType.valueOf(codestr[0]))) {
            if (Integer.parseInt(codestr[1]) >= 0
                    && Integer.parseInt(codestr[1]) <= 26) {
                return true;
            }
        }
        if (PlayType.zl_bh.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-9](,[0-9]){2,9}");
        }
        if (PlayType.zl_hz.equals(PlayType.valueOf(codestr[0]))) {
            if (Integer.parseInt(codestr[1]) >= 0
                    && Integer.parseInt(codestr[1]) <= 26) {
                return true;
            }
        }
        return false;
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

    /**
     * 兑奖
     */
    public void expiryDate(Order order)
    {

    }

    @Override
    public int validataBetCount(String code)
    {
        int count = 0;
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\,");
            int n1 = codestr[0].length();
            int n2 = codestr[1].length();
            int n3 = codestr[2].length();
            count = n1 * n2 * n3;
        }
        if (PlayType.zx_hz.equals(PlayType.valueOf(code.split("-")[0]))) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        if (i + j + k == Integer.parseInt(code.split("-")[1])) {
                            if (i + j + k == Integer
                                    .parseInt(code.split("-")[1]))
                                count++;
                        }
                    }
                }
            }
        }
        if (PlayType.zs_bh.equals(PlayType.valueOf(code.split("-")[0]))) {
            int n = code.split("-")[1].split("\\,").length;
            count = 2 * MathUtil.getCombinationCount(n, 2);
        }
        if (PlayType.zs_hz.equals(PlayType.valueOf(code.split("-")[0]))) {
            for (int i = 0; i < 10; i++) {
                for (int j = i; j < 10; j++) {
                    for (int k = j; k < 10; k++) {
                        if (i + j + k == Integer.parseInt(code.split("-")[1])) {
                            if ((i + j + k == Integer
                                    .parseInt(code.split("-")[1]))
                                    && (i == j || i == k || j == k)
                                    && !(i == j && j == k)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        if (PlayType.zl_bh.equals(PlayType.valueOf(code.split("-")[0]))) {
            int n = code.split("-")[1].split("\\,").length;
            count = MathUtil.getCombinationCount(n, 3);
        }
        if (PlayType.zl_hz.equals(PlayType.valueOf(code.split("-")[0]))) {
            for (int i = 0; i < 10; i++) {
                for (int j = i; j < 10; j++) {
                    for (int k = j; k < 10; k++) {
                        if (i + j + k == Integer.parseInt(code.split("-")[1])) {
                            if ((i + j + k == Integer
                                    .parseInt(code.split("-")[1]))
                                    && (!(i == j && i == k))
                                    && !(i == j || i == k || j == k))
                                count++;
                        }
                    }
                }
            }
        }
        if (PlayType.q2.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\,");
            int n1 = codestr[0].length();
            int n2 = codestr[1].length();
            count = n1 * n2;
        }
        if (PlayType.h2.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\,");
            int n1 = codestr[0].length();
            int n2 = codestr[1].length();
            count = n1 * n2;
        }
        if (PlayType.q1.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\,");
            count = codestr[0].length();
        }
        if (PlayType.h1.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\,");
            count = codestr[0].length();
        }

        return count;
    }

    @Override
    public boolean validataRepeat(String code)
    {
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codeStr = code.split("-")[1].split("\\,");
            if (MathUtil.repeatChar(codeStr[0], 0, 9)
                    || MathUtil.repeatChar(codeStr[1], 0, 9)
                    || MathUtil.repeatChar(codeStr[2], 0, 9))
                return true;

        }
        if (PlayType.zl_bh.equals(PlayType.valueOf(code.split("-")[0]))
                || PlayType.zs_bh.equals(PlayType.valueOf(code.split("-")[0]))) {
            String codeStr = code.split("-")[1];
            if (MathUtil.repeatString(codeStr, 0, 9))
                return true;
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
    	String[] temp0 = content.split("#");//,分割生成的字符串组
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
		// TODO Auto-generated method stub
		return null;
	}
}
