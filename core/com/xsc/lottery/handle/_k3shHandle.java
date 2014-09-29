package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.xsc.lottery.dyj.sendticket.bingocheck.Klpk3BingoCheck;
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
public class _k3shHandle extends BaseLotteryHandle {

	@Override
	public LotteryType getLotteryType() {
		
		return LotteryType.广西快3;
	}
	
	@Override
	protected List<Ticket> unpackTicket(Order order, PlanItem item) {

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
		order.setType(LotteryType.广西快3);
		
		PlanItem item = new PlanItem();
		item.setPlayType(PlayType.hz);
		item.setBetCount(3);
		item.setContent("10");
		item.setOneMoney(new BigDecimal(2));
		
		_k3shHandle _klpk3Handle = new _k3shHandle();
		
		List<Ticket> tickets = _klpk3Handle.unpackTicket(order, item);
		
		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			System.out.println(ticket.getContent());
		}
	}


	@Override
	public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception {

        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
        term.setPrizeLevels(prizeLevels);
        return term;
	}

	@Override
	public LotteryTerm getNextTerm(LotteryTerm term) {
		
        LotteryTerm nextTerm = new LotteryTerm();
        //自动开奖
        nextTerm.setAutoOpen(true);
        if(term.getTermNo().equals("20110202020")) {
            return null;
        }

        nextTerm.setType(term.getType());
        Calendar start = (Calendar) term.getStopSaleTime().clone();
        nextTerm.setStartSaleTime(start);
        
        Calendar stop = Calendar.getInstance();
        Calendar open = Calendar.getInstance();
        Calendar sendPri = Calendar.getInstance();

   
        //计算期号
        int code = Integer.parseInt(term.getTermNo().substring(term.getTermNo().length() - 2, term.getTermNo().length()));
        if (code >= 78) {
        	start.add(Calendar.HOUR, 10);
            nextTerm.setTermNo(DateUtil.toYYYYMMDD(start) + "001");
            start.add(Calendar.HOUR, -10);
        }
        else {
            nextTerm.setTermNo((Long.valueOf(term.getTermNo()) + 1) + "");
        }

        //计算截止时间
        stop.setTime(start.getTime());
        if (code < 78) {
            stop.add(Calendar.MINUTE, 10);
        }
        else {// 第二天的期次
        	stop.add(Calendar.DAY_OF_MONTH, 1);
            stop.set(Calendar.HOUR_OF_DAY, 9);
            stop.set(Calendar.MINUTE, 35);
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
	        Klpk3BingoCheck dbc = new Klpk3BingoCheck();
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
	protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
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
	        String format = item.getContent();
	        if(item.getPlayType() == PlayType.sth_tx || item.getPlayType() == PlayType.slh_tx)
	        {
	        	format = "*";
	        }
	        if(item.getPlayType() == PlayType.ebt_ds || item.getPlayType() == PlayType.sbt_ds)
	        {
	        	format = format.replace("", ",");
	        	format = format.substring(1, format.length()-1);
	        }
	        if(item.getPlayType() == PlayType.eth_ds)
	        {
	        	String[] arr = format.split("");
	        	Arrays.sort(arr);
	        	format = "";
	        	for (String c : arr) {
	        		format += c;
				}
	        }
	        ticket.setContent(format);
	        ticket.setPlayType(item.getPlayType());
	        ticket.setType(order.getType());
	        ticket.setIssue(order.getTerm().getTermNo());
	        ticketList.add(ticket);
	        return ticketList;
	}

}
