package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.xsc.lottery.dyj.sendticket.bingocheck.L11x5BingoCheck;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.util.DateUtil;

@Component
public class _11x5shHandle extends BaseLotteryHandle {

	@Override
	public LotteryType getLotteryType() {
		// TODO Auto-generated method stub
		
		return LotteryType.上海11选5;
	}

	@Override
	protected List<Ticket> unpackTicket(Order order, PlanItem item) {

        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";//大赢家的投注格式
        String formatNumForWZL = "";//我中了的投注格式
        
        if(item.getPlayType().equals(PlayType.q1_zhix)) {
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "01-01-" + item.getContent();
			}else {
				formatNumForWZL = "01-02-" + item.getContent();
			}
        	formatNum = "R1|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_2)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "02-01-" + item.getContent();
			}else {
				formatNumForWZL = "02-02-" + item.getContent();
			}       
        	formatNum = "R2|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_3)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "03-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "03-02-" + item.getContent();
        	}   
        	formatNum = "R3|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_4)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "04-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "04-02-" + item.getContent();
        	}        
        	formatNum = "R4|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_5)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "05-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "05-02-" + item.getContent();
        	}        
        	formatNum = "R5|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_6)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "06-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "06-02-" + item.getContent();
        	}        
        	formatNum = "R6|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_7)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "07-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "07-02-" + item.getContent();
        	}        
        	formatNum = "R7|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.rx_8)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "08-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "08-02-" + item.getContent();
        	}        
        	formatNum = "R8|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.q2_zhix)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "09-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "09-02-" + item.getContent();
        	}  
        	formatNum = "Q2|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.q2_zux)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "11-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "11-02-" + item.getContent();
        	}      
        	formatNum = "Z2|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.q3_zhix)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "10-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "10-02-" + item.getContent();
        	}    
        	formatNum = "Q3|" + item.getContent();
        }
        else if (item.getPlayType().equals(PlayType.q3_zux)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "12-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "12-02-" + item.getContent();
        	}       
        	formatNum = "Z3|" + item.getContent();
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

	public static void main(String[] args) {
		LotteryTerm term = new LotteryTerm();
		term.setTermNo("123456");
		Order order = new Order();
		order.setMultiple(1);
		order.setTerm(term);
		order.setType(LotteryType.上海11选5);
		
		PlanItem item = new PlanItem();
		item.setPlayType(PlayType.q1_zhix);
		item.setBetCount(3);
		item.setContent("02,03,05");
		item.setOneMoney(new BigDecimal(2));
		
		_11x5shHandle _11x5shHandle = new _11x5shHandle();
		
		List<Ticket> tickets = _11x5shHandle.unpackTicketForWZL(order, item);
		
		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			System.out.println(ticket.getBetContent());
		}
	}
	@Override
	protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item) {
		
		List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";//大赢家的投注格式
        String formatNumForWZL = "";//我中了的投注格式
        if(item.getPlayType().equals(PlayType.q1_zhix)) {
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "01-01-" + item.getContent();
			}else {
				formatNumForWZL = "01-02-" + item.getContent();
			}
        }
        else if (item.getPlayType().equals(PlayType.rx_2)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "02-01-" + item.getContent();
			}else {
				formatNumForWZL = "02-02-" + item.getContent();
			}        
        }
        else if (item.getPlayType().equals(PlayType.rx_3)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "03-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "03-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.rx_4)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "04-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "04-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.rx_5)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "05-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "05-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.rx_6)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "06-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "06-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.rx_7)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "07-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "07-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.rx_8)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "08-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "08-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.q2_zhix)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "09-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "09-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.q2_zux)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "11-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "11-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.q3_zhix)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "10-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "10-02-" + item.getContent();
        	}        
        }
        else if (item.getPlayType().equals(PlayType.q3_zux)){
        	if (item.getBetCount() == 1) {
        		formatNumForWZL = "12-01-" + item.getContent();
        	}else {
        		formatNumForWZL = "12-02-" + item.getContent();
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
        ticket.setIssue(order.getTerm().getTermNo().substring(2, order.getTerm().getTermNo().length()));
        ticketList.add(ticket);
        return ticketList;

	}

	@Override
	public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception {

        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);

//        PrizeLevel(int level, LotteryTerm term, int betNum, String name,
//                BigDecimal prize, BigDecimal addedPrize)
//        q1_zhix("前一直选"), rx_2("任选二"),
//        rx_3("任选三"), rx_4("任选四"), rx_5("任选五"), rx_6("任选六"), rx_7("任选七"), rx_8("任选八"),
//        q2_zhix("前二直选"), q2_zux("前二组选"), q3_zhix("前三直选"), q3_zux("前三组选")
        prizeLevels.add(new PrizeLevel(1, term, 0, "前一", new BigDecimal(13), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(2, term, 0, "任二", new BigDecimal(6), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(3, term, 0, "任三", new BigDecimal(19), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(4, term, 0, "任四", new BigDecimal(78), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(5, term, 0, "任五", new BigDecimal(540), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(6, term, 0, "任六", new BigDecimal(90), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(7, term, 0, "任七", new BigDecimal(26), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(8, term, 0, "任八", new BigDecimal(9), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(9, term, 0, "前二直选", new BigDecimal(130), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(10, term, 0, "前二组选", new BigDecimal(65), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(11, term, 0, "前三直选", new BigDecimal(1170), BigDecimal.ZERO));
        prizeLevels.add(new PrizeLevel(12, term, 0, "前三组选", new BigDecimal(195), BigDecimal.ZERO));

        term.setPrizeLevels(prizeLevels);
        return term;
	}
	
//	public static void main(String[] args) {
//		_lsscHandle _lsscHandle = new _lsscHandle();
//		LotteryTerm term = new LotteryTerm();
//		Calendar stCalendar = Calendar.getInstance();
//		stCalendar.set(2012, 8, 27, 14, 13, 0);
//		term.setStopSaleTime(stCalendar);
//		term.setTermNo("12082723");
//		term.setType(LotteryType.老11选5);
//		
//		LotteryTerm lotteryTerm = _lsscHandle.getNextTerm(term);
//		
//		System.out.println(lotteryTerm.getStopSaleTime());
//
//	}

	@Override
	public LotteryTerm getNextTerm(LotteryTerm term) {
		
        LotteryTerm nextTerm = new LotteryTerm();
        //自动开奖
        nextTerm.setAutoOpen(true);
        if(term.getTermNo().equals("110202120")) {
            return null;
        }

        nextTerm.setType(term.getType());
        Calendar start = (Calendar) term.getStopSaleTime().clone();
        nextTerm.setStartSaleTime(start);

        Calendar stop = Calendar.getInstance();
        Calendar open = Calendar.getInstance();
        Calendar sendPri = Calendar.getInstance();

   

        int code = Integer.parseInt(term.getTermNo().substring(term.getTermNo().length() - 2, term.getTermNo().length()));
        if (code >= 90) {
        	start.add(Calendar.HOUR, 10);
            nextTerm.setTermNo(DateUtil.toYYYYMMDD(start) + "01");
            start.add(Calendar.HOUR, -10);
        }
        else {
            nextTerm.setTermNo((Integer.valueOf(term.getTermNo()) + 1) + "");
        }

    
        stop.setTime(start.getTime());

        if (code < 90) {
            stop.add(Calendar.MINUTE, 10);
        }
        else {// 第二天的期次
        	stop.add(Calendar.DAY_OF_MONTH, 1);
            stop.set(Calendar.HOUR_OF_DAY, 8);
            stop.set(Calendar.MINUTE, 56);
            stop.set(Calendar.SECOND, 30);
        }

        // 开奖时间
    	open.setTime(stop.getTime());

        open.add(Calendar.MINUTE, 4); //开奖直接兑奖？？
        
        open.add(Calendar.SECOND, 30); //截止后 4分半钟开奖
        // 派奖时间
    
        sendPri.setTime(stop.getTime());

        sendPri.add(Calendar.MINUTE, 5);

        
        nextTerm.setStopSaleTime(stop);
        nextTerm.setOpenPrizeTime(open);
        nextTerm.setSendPrizeTime(sendPri);
        
//		System.out.println("open = " + open.getTime());
//		System.out.println("sendPri = " + sendPri.getTime());
//		System.out.println("start = " + start.getTime());
//		System.out.println("stop = " + stop.getTime());
//		System.out.println("nextstop = " + nextTerm.getStopSaleTime().getTime());
        return nextTerm;

	}

	@Override
	public List<PlanItem> validateUploadFile(String filePath,
			BigDecimal oneMoney, int multiple, BigDecimal totalMoney)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validataBetNum(String result) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
			List<PrizeLevel> prizeLevels) {
		// TODO Auto-generated method stub
		 Map<String, PrizeLevel> bingoMap = new HashMap<String, PrizeLevel>();
	        for (PrizeLevel pr : prizeLevels) {
	            bingoMap.put(pr.getName(), pr);
	        }
	        String result = ticket.getOrder().getTerm().getResult();
	        L11x5BingoCheck dbc = new L11x5BingoCheck();
	        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
	        return list;
	}

	@Override
	public int validataBetCount(String code) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validataRepeat(String code) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getTermCode(LotteryTerm term, int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parseXML(Element root) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String sort(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeToWZLContent(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}

}
