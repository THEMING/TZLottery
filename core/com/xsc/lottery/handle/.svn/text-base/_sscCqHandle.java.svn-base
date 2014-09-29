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
import com.xsc.lottery.util.StringUtil;

@Component
public class _sscCqHandle extends BaseLotteryHandle {
	@Override
	public LotteryType getLotteryType() {
		return LotteryType.重庆时时彩;
	}

	@Override
	public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception {
		return null;
	}

	@Override
	public LotteryTerm getNextTerm(LotteryTerm term) {
		int ycminut = 3;
		int kjycm = 1;
		int djycm = 10;
		LotteryTerm nextTerm = new LotteryTerm();

		if (term.getTermNo().equals("110202120")) {
			return null;
		}

		nextTerm.setType(term.getType());
		Calendar start = term.getStopSaleTime();
		Calendar stop = Calendar.getInstance();
		Calendar open = Calendar.getInstance();
		Calendar sendPri = Calendar.getInstance();

		// 减去提前
		start.add(Calendar.MINUTE, ycminut);
		int hour = start.get(Calendar.HOUR_OF_DAY);
		int minute = start.get(Calendar.MINUTE);

		int code = Integer.parseInt(term.getTermNo().substring(6, 9));
		if (code >= 120) {
			nextTerm.setTermNo(DateUtil.toYYMMdd(start) + "001");
		} else {
			nextTerm.setTermNo((Integer.valueOf(term.getTermNo()) + 1) + "");
		}

		// 还原
		start.add(Calendar.MINUTE, -ycminut);
		stop.setTime(start.getTime());

		if (hour >= 10 && hour < 22) {
			stop.add(Calendar.MINUTE, 10);
		} else if ((hour >= 22 && (hour <= 23 && minute < 59))
				|| ((hour == 0) || (hour == 1 && minute < 55))) {
			stop.add(Calendar.MINUTE, 5);
		} else {// 第二天的期次
			stop.set(Calendar.HOUR_OF_DAY, 10);
			stop.set(Calendar.MINUTE, 0);
			stop.set(Calendar.SECOND, 0);
			stop.add(Calendar.MINUTE, -ycminut);
		}

		// 开奖时间
		open.setTime(stop.getTime());
		open.add(Calendar.MINUTE, kjycm + ycminut);
		// 派奖时间
		sendPri.setTime(stop.getTime());
		sendPri.add(Calendar.MINUTE, djycm + ycminut);

		nextTerm.setStartSaleTime(start);
		nextTerm.setStopSaleTime(stop);
		nextTerm.setOpenPrizeTime(open);
		nextTerm.setSendPrizeTime(sendPri);
		return nextTerm;
	}

	// public static void main(String[] args) {
	// _sscCqHandle ssc = new _sscCqHandle();
	// LotteryTerm term=new LotteryTerm();
	// Calendar dd = Calendar.getInstance();
	// dd.set(Calendar.DAY_OF_MONTH, 13);
	// dd.set(Calendar.HOUR_OF_DAY, 22);
	// dd.set(Calendar.MINUTE, 58);
	// term.setStopSaleTime(dd);
	// term.setTermNo("110113108");
	// ssc.getNextTerm(term);
	// }
	// 返回 期号_截止时间
	public List<String> getTermCode(LotteryTerm term, int number) {
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
	public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
			throws Exception {
		PlanItem item = new PlanItem();
		try {
			if (validataBetNum(code) || validataRepeat(code)) {
				throw new Exception("时时彩投注号码格式错误!");
			}
		} catch (Exception e) {
			throw new Exception("时时彩投注号码格式错误!");
		}

		item.setBetCount(validataBetCount(code));
		if (PlayType.valueOf(code.split("-")[0]).equals(PlayType.pt_dx)) {
			item.setContent(StringUtil.formatNumbertoDX(code.split("-")[1]));
		} else {
			item.setContent(code.split("-")[1]);

		}
		item.setPlayType(PlayType.valueOf(code.split("-")[0]));
		item.setOneMoney(oneMoney);
		return item;
	}

	@Override
	protected List<Ticket> unpackTicket(Order order, PlanItem item) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		if (item.getPlayType().equals(PlayType.hz_3)) {
			List<String> returnStrLsit = chaipiao3x(item.getContent());
			for (String str : returnStrLsit) {
				Ticket ticket = new Ticket();
				String formatNum = "3D|-,-," + str;
				ticket.setCount(item.getBetCount());
				ticket.setMoney(item.getOneMoney().multiply(
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
			}
			return ticketList;
		}
		if (item.getPlayType().equals(PlayType.hz_2)) {
			List<String> returnStrLsit = chaipiao2x(item.getContent());
			for (String str : returnStrLsit) {
				Ticket ticket = new Ticket();
				String formatNum = "2D|-,-,-," + str;
				ticket.setCount(item.getBetCount());
				ticket.setMoney(item.getOneMoney().multiply(
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
			}
			return ticketList;
		}
		if (item.getPlayType().equals(PlayType.pt_dx)) {
			List<String> returnStrLsit = chaipiaoDX(item.getContent());
			for (String str : returnStrLsit) {
				Ticket ticket = new Ticket();
				String formatNum = "DD|" + str;
				ticket.setCount(item.getBetCount());
				ticket.setMoney(item.getOneMoney().multiply(
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
			}
			return ticketList;
		}
		Ticket ticket = new Ticket();
		String formatNum = "";
		if (item.getPlayType().equals(PlayType.pt_5)) {
			formatNum = "5D|" + item.getContent();
		}
		if (item.getPlayType().equals(PlayType.tx_5)) {
			formatNum = "5T|" + item.getContent();
		}
		if (item.getPlayType().equals(PlayType.pt_3)) {
			formatNum = "3D|-,-," + item.getContent();
		}
		if (item.getPlayType().equals(PlayType.pt_2)) {
			formatNum = "2D|-,-,-," + item.getContent();
		}
		if (item.getPlayType().equals(PlayType.zs_2)) {
			if (item.getContent().length() == 3) {
				formatNum = "Z2|" + item.getContent();
			} else {
				formatNum = "F2|" + item.getContent().replace(",", "");
			}
		}
		if (item.getPlayType().equals(PlayType.zs_hz_2)) {
			formatNum = "S2|" + item.getContent();
		}
		if (item.getPlayType().equals(PlayType.pt_1)) {
			formatNum = "1D|-,-,-,-," + item.getContent();
		}
		if (item.getPlayType().equals(PlayType.pt_dx)) {
			formatNum = "DD|" + item.getContent();
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
	protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		if (item.getPlayType().equals(PlayType.pt_1)) {
			List<String> returnStrLsit = chaipiao1X(item.getContent());
			for (String str : returnStrLsit) {
				Ticket ticket = new Ticket();
				String formatNum = "01-01-_,_,_,_," + str;
				ticket.setCount(item.getBetCount());
				ticket.setMoney(item.getOneMoney().multiply(
						BigDecimal.valueOf(order.getMultiple())));
				ticket.setMultiple(order.getMultiple());
				ticket.setItem(item);
				ticket.setOrder(order);
				ticket.setSendTicketTime(Calendar.getInstance());
				ticket.setTermNo(order.getTerm().getTermNo());
				ticket.setIssue(order.getTerm().getTermNo());
				ticket.setType(order.getType());
				ticket.setContent(formatNum);
				ticket.setBetContent(formatNum);
				ticket.setPlayType(item.getPlayType());
				ticket.setType(order.getType());
				ticketList.add(ticket);
			}
			
		} else if (item.getPlayType().equals(PlayType.pt_2)) {
			Ticket ticket = new Ticket();
			String formatNum = "";
			if(item.getBetCount() == 1)
			{
				formatNum = "02-01-_,_,_," + item.getContent();
			}else{
				String[] strs = item.getContent().split("\\,");
				String s = strs[0].replaceAll("", " ").trim()+","+strs[1].replaceAll("", " ").trim();
				formatNum = "02-02-_,_,_," + s;
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
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
		} else if (item.getPlayType().equals(PlayType.hz_2)) {
			Ticket ticket = new Ticket();
			String formatNum = "02-04-" + item.getContent();
			ticket.setCount(item.getBetCount());
			ticket.setMoney(item.getOneMoney().multiply(
		                new BigDecimal(item.getBetCount())).multiply(
		                BigDecimal.valueOf(order.getMultiple())));
			ticket.setMultiple(order.getMultiple());
			ticket.setItem(item);
			ticket.setOrder(order);
			ticket.setSendTicketTime(Calendar.getInstance());
			ticket.setTermNo(order.getTerm().getTermNo());
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
			
		} else if (item.getPlayType().equals(PlayType.zs_2)) {
			Ticket ticket = new Ticket();
			String formatNum = "";
			if (item.getBetCount() == 1) {
				formatNum = "06-01-_,_,_," + item.getContent();
			} else {
				formatNum = "06-02-" + item.getContent();
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
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
			
		}else if (item.getPlayType().equals(PlayType.zs_hz_2)) {
			Ticket ticket = new Ticket();
			String formatNum = "06-04-" + item.getContent();
			ticket.setCount(item.getBetCount());
			ticket.setMoney(item.getOneMoney().multiply(
		                new BigDecimal(item.getBetCount())).multiply(
		                BigDecimal.valueOf(order.getMultiple())));
			ticket.setMultiple(order.getMultiple());
			ticket.setItem(item);
			ticket.setOrder(order);
			ticket.setSendTicketTime(Calendar.getInstance());
			ticket.setTermNo(order.getTerm().getTermNo());
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
			
		}else if (item.getPlayType().equals(PlayType.pt_3)) {
			Ticket ticket = new Ticket();
			String formatNum = "";
			if (item.getBetCount() == 1) {
				formatNum = "03-01-_,_," + item.getContent();
			} else {
				String[] strs = item.getContent().split("\\,");
				String s = strs[0].replaceAll("", " ").trim()+","+strs[1].replaceAll("", " ").trim()+","+strs[2].replaceAll("", " ").trim();
				formatNum = "03-02-_,_," + s;
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
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
		} else if (item.getPlayType().equals(PlayType.hz_3)) {
			Ticket ticket = new Ticket();
			String formatNum = "03-04-" + item.getContent();
			ticket.setCount(item.getBetCount());
			ticket.setMoney(item.getOneMoney().multiply(
		                new BigDecimal(item.getBetCount())).multiply(
		                BigDecimal.valueOf(order.getMultiple())));
			ticket.setMultiple(order.getMultiple());
			ticket.setItem(item);
			ticket.setOrder(order);
			ticket.setSendTicketTime(Calendar.getInstance());
			ticket.setTermNo(order.getTerm().getTermNo());
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
			
		} else if (item.getPlayType().equals(PlayType.pt_5)) {
			String formatNum = "";
			if (item.getBetCount() == 1) {
				formatNum = "05-01-" + item.getContent();
			} else {
				String[] strs = item.getContent().split("\\,");
				String s = strs[0].replaceAll("", " ").trim()+","+
				strs[1].replaceAll("", " ").trim()+","+
				strs[2].replaceAll("", " ").trim()+","+
				strs[3].replaceAll("", " ").trim()+","+
				strs[4].replaceAll("", " ").trim();
				formatNum = "05-02-" + s;
			}
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
			ticket.setIssue(order.getTerm().getTermNo());
			ticket.setType(order.getType());
			ticket.setContent(formatNum);
			ticket.setBetContent(formatNum);
			ticket.setPlayType(item.getPlayType());
			ticket.setType(order.getType());
			ticketList.add(ticket);
		} else if (item.getPlayType().equals(PlayType.tx_5)) {
			List<String> returnStrLsit = chaipiao5X(item.getContent());
			for (String str : returnStrLsit) {
				Ticket ticket = new Ticket();
				String formatNum = "13-01-" + str;
				ticket.setCount(item.getBetCount());
				ticket.setMoney(item.getOneMoney().multiply(
						BigDecimal.valueOf(order.getMultiple())));
				ticket.setMultiple(order.getMultiple());
				ticket.setItem(item);
				ticket.setOrder(order);
				ticket.setSendTicketTime(Calendar.getInstance());
				ticket.setTermNo(order.getTerm().getTermNo());
				ticket.setIssue(order.getTerm().getTermNo());
				ticket.setType(order.getType());
				ticket.setContent(formatNum);
				ticket.setBetContent(formatNum);
				ticket.setPlayType(item.getPlayType());
				ticket.setType(order.getType());
				ticketList.add(ticket);
			}
			
		} else if (item.getPlayType().equals(PlayType.pt_dx)) {
			List<String> returnStrLsit = chaipiaoDXForWZL(item.getContent());
			for (String str : returnStrLsit) {
				Ticket ticket = new Ticket();
				String formatNum = "23-01-" + str;
				ticket.setCount(item.getBetCount());
				ticket.setMoney(item.getOneMoney().multiply(
						BigDecimal.valueOf(order.getMultiple())));
				ticket.setMultiple(order.getMultiple());
				ticket.setItem(item);
				ticket.setOrder(order);
				ticket.setSendTicketTime(Calendar.getInstance());
				ticket.setTermNo(order.getTerm().getTermNo());
				ticket.setIssue(order.getTerm().getTermNo());
				ticket.setType(order.getType());
				ticket.setContent(formatNum);
				ticket.setBetContent(formatNum);
				ticket.setPlayType(item.getPlayType());
				ticket.setType(order.getType());
				ticketList.add(ticket);
			}
		} 
		return ticketList;
	}

	@Override
	public List<PlanItem> validateUploadFile(String filePath,
			BigDecimal oneMoney, int multiple, BigDecimal totalMoney)
			throws Exception {
		throw new Exception("暂不提供!");
	}

	@Override
	public boolean validataBetNum(String result) {
		String[] codestr = result.split("-");
		if (PlayType.pt_5.equals(PlayType.valueOf(codestr[0]))
				|| PlayType.tx_5.equals(PlayType.valueOf(codestr[0]))) {
			return !codestr[1]
					.matches("^[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}");
		}
		if (PlayType.pt_3.equals(PlayType.valueOf(codestr[0]))) {
			return !codestr[1]
					.matches("^[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}");
		}
		if (PlayType.hz_3.equals(PlayType.valueOf(codestr[0]))) {
			if (Integer.parseInt(codestr[1]) >= 0
					&& Integer.parseInt(codestr[1]) <= 27) {
				return true;
			}
		}
		if (PlayType.pt_2.equals(PlayType.valueOf(codestr[0]))) {
			return !codestr[1].matches("^[0-9]{1,10}\\,[0-9]{1,10}");
		}
		if (PlayType.hz_2.equals(PlayType.valueOf(codestr[0]))) {
			if (Integer.parseInt(codestr[1]) >= 0
					&& Integer.parseInt(codestr[1]) <= 18) {
				return true;
			}
		}
		if (PlayType.zs_2.equals(PlayType.valueOf(codestr[0]))) {
			return !codestr[1].matches("^[0-9](,[0-9]){3,7}");
		}
		if (PlayType.zs_hz_2.equals(PlayType.valueOf(codestr[0]))) {
			if (Integer.parseInt(codestr[1]) >= 0
					&& Integer.parseInt(codestr[1]) <= 18) {
				return true;
			}
		}
		if (PlayType.pt_1.equals(PlayType.valueOf(codestr[0]))) {
			return !codestr[1].matches("^[0-9]{1,10}");
		}
		if (PlayType.pt_dx.equals(PlayType.valueOf(codestr[0]))) {
			return !codestr[1].matches("^[12][45]");
		}
		return false;
	}

	@Override
	public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
			List<PrizeLevel> prizeLevels) {
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
	public void expiryDate(Order order) {

	}

	@Override
	public int validataBetCount(String code) {
		int count = 0;
		if (PlayType.pt_5.equals(PlayType.valueOf(code.split("-")[0]))
				|| PlayType.tx_5.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			int n1 = codeStr[0].length();
			int n2 = codeStr[1].length();
			int n3 = codeStr[2].length();
			int n4 = codeStr[3].length();
			int n5 = codeStr[4].length();
			count = n1 * n2 * n3 * n4 * n5;
		}
		if (PlayType.pt_3.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			int n1 = codeStr[0].length();
			int n2 = codeStr[1].length();
			int n3 = codeStr[2].length();
			count = n1 * n2 * n3;
		}
		if (PlayType.hz_3.equals(PlayType.valueOf(code.split("-")[0]))) {
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
		if (PlayType.pt_2.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			int n1 = codeStr[0].length();
			int n2 = codeStr[1].length();

			count = n1 * n2;
		}
		if (PlayType.hz_2.equals(PlayType.valueOf(code.split("-")[0]))) {

			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (j + k == Integer.parseInt(code.split("-")[1])) {
						if (j + k == Integer.parseInt(code.split("-")[1]))
							count++;
					}
				}
			}

		}
		if (PlayType.zs_2.equals(PlayType.valueOf(code.split("-")[0]))) {
			int n = code.split("-")[1].split("\\,").length;
			count = MathUtil.getCombinationCount(n, 2);
		}
		if (PlayType.zs_hz_2.equals(PlayType.valueOf(code.split("-")[0]))) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					if ((j + k == Integer.parseInt(code.split("-")[1])))
						count++;
				}

			}
		}
		if (PlayType.pt_1.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			int n1 = codeStr[0].length();
			count = n1;
		}
		if (PlayType.pt_dx.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			int n1 = codeStr[0].length();
			int n2 = codeStr[1].length();
			count = n1 * n2;
		}
		return count;
	}

	@Override
	public boolean validataRepeat(String code) {
		if (PlayType.pt_5.equals(PlayType.valueOf(code.split("-")[0]))
				|| PlayType.tx_5.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			if (MathUtil.repeatChar(codeStr[0], 0, 9)
					|| MathUtil.repeatChar(codeStr[1], 0, 9)
					|| MathUtil.repeatChar(codeStr[2], 0, 9)
					|| MathUtil.repeatChar(codeStr[3], 0, 9)
					|| MathUtil.repeatChar(codeStr[4], 0, 9))
				return true;
		}
		if (PlayType.pt_3.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			if (MathUtil.repeatChar(codeStr[0], 0, 9)
					|| MathUtil.repeatChar(codeStr[1], 0, 9)
					|| MathUtil.repeatChar(codeStr[2], 0, 9))
				return true;

		}
		if (PlayType.pt_2.equals(PlayType.valueOf(code.split("-")[0]))) {
			String[] codeStr = code.split("-")[1].split("\\,");
			if (MathUtil.repeatChar(codeStr[0], 0, 9)
					|| MathUtil.repeatChar(codeStr[1], 0, 9))
				return true;

		}
		if (PlayType.zs_2.equals(PlayType.valueOf(code.split("-")[0]))
				|| PlayType.pt_1.equals(PlayType.valueOf(code.split("-")[0]))) {
			String codeStr = code.split("-")[1];
			if (MathUtil.repeatString(codeStr, 0, 9))
				return true;
		}
		return false;
	}

	//三星和值拆三星单式
	public List<String> chaipiao3x(String n) {
		List<String> returnStrLsit = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (i + j + k == Integer.parseInt(n)) {
						if (i + j + k == Integer.parseInt(n)) {
							returnStrLsit.add(i + "," + j + "," + k);
							count++;
						}
					}
				}
			}
		}
		return returnStrLsit;
	}

	//二星和值拆二星单式
	public List<String> chaipiao2x(String n) {
		List<String> returnStrLsit = new ArrayList<String>();
		int count = 0;

		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 10; k++) {
				if (j + k == Integer.parseInt(n)) {
					if (j + k == Integer.parseInt(n)) {
						returnStrLsit.add(j + "," + k);
						count++;
					}
				}
			}

		}
		return returnStrLsit;
	}
	//大小单双复式拆单式
	public List<String> chaipiaoDX(String n) {
		List<String> returnStrLsit = new ArrayList<String>();
		n = StringUtil.formatDXtoNumber(n);
		String[] str = n.split("\\,");
		String[] str1 = str[0].split("");
		String[] str2 = str[1].split("");
		for (int i = 1; i < str1.length; i++) {
			for (int k = 1; k < str2.length; k++) {
				returnStrLsit.add(str1[i] + "" + str2[k]);
			}

		}
		return returnStrLsit;
	}
	
	//大小单双复式拆单式
	public List<String> chaipiaoDXForWZL(String n) {
		List<String> returnStrLsit = new ArrayList<String>();
		n = StringUtil.formatDXtoNumber(n);
		String[] str = n.split("\\,");
		String[] str1 = str[0].split("");
		String[] str2 = str[1].split("");
		for (int i = 1; i < str1.length; i++) {
			for (int k = 1; k < str2.length; k++) {
				returnStrLsit.add(str1[i] + "," + str2[k]);
			}

		}
		return returnStrLsit;
	}

	//一星复式拆单式
	public List<String> chaipiao1X(String n) {
		List<String> returnStrLsit = new ArrayList<String>();
		String[] str = n.split("");
		for (int i = 1; i < str.length; i++) {
			returnStrLsit.add(str[i]);
		}
		return returnStrLsit;
	}
	
	//五星复式拆单式
	public List<String> chaipiao5X(String n) {
		List<String> returnStrLsit = new ArrayList<String>();
		String[] str = n.split("\\,");
		String[] str5 = str[0].split("");
		String[] str4 = str[1].split("");
		String[] str3 = str[2].split("");
		String[] str2 = str[3].split("");
		String[] str1 = str[4].split("");
		for (int i = 1; i < str5.length; i++)
		{
			for (int j = 1; j < str4.length; j++)
			{
				for (int k = 1; k < str3.length; k++)
				{
					for (int m = 1; m < str2.length; m++)
					{
						for (int h = 1; h < str1.length; h++)
						{
							returnStrLsit.add(str5[i]+","+str4[j]+","+str3[k]+","+str2[m]+","+str1[k]);
						}
					}
				}
			}	
		}
		return returnStrLsit;
	}

	@Override
	public void parseXML(Element element) throws Exception {
		throw new RuntimeException("暂不提供");
	}

	@Override
	public String changeToWZLContent(String content) {
		String[] temp0 = content.split("#");// ,分割生成的字符串组
		String string = "";
		for (int i = 0; i < temp0.length; i++) {
			String sortString = sort(temp0[i]);
			if (i == 0) {
				string += sortString;
			} else {
				string = string + "#" + sortString;
			}
		}
		return string;
	}

	@Override
	public String sort(String content) {
		String[] s = content.split(",");
		int[] nums = new int[s.length];
		int temp = 0;
		String use = "";
		String ss = "";
		for (int i = 0; i < s.length; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
			if (nums[i] < 10) {
				ss = "0" + nums[i];
			} else {
				ss = "" + nums[i];
			}
			if (i == nums.length - 1) {
				use = use + ss;
			} else {
				use = use + ss + ",";
			}
		}
		return use;
	}

	public static void main(String arges[]) {
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
