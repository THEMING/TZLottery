package com.xsc.lottery.admin.action.basketball;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeOrder;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.JCLQTypes;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderResult;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.RaceStatus;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.wzl.sendticket.JclqBingoCheck;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("OpenPrize.JCLQ")
public class JCLQOpenPrize extends AdminBaseAction {
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LotteryOrderService lotteryOrderService;

	@Autowired
	private LotteryTermService lotteryTermService;

	@Autowired
	private MatchArrangeService matchArrangeService;

	//FIXME
	@Autowired 
    private CommunityService communityService;
	
	@Autowired 
    private JclqBingoCheck jclqBingoCheck;
	
	private String op_matchNo;
	private String op_matchNos; //批量开奖
	
	private String message;
	
	private Page<MatchArrange> page;
	private int pageNo = 1;
    private int pageSize = 10;
    
    
	public String index()
	{
		page = new Page<MatchArrange>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = matchArrangeService.getMatchPageForPrize(page, LotteryType.竞彩篮球);
		return SUCCESS;
	}
	
	public String openPrize() throws Exception 
	{
		message = "";
		if (op_matchNo == null || op_matchNo.equals("")) {
			message += "竞彩篮球开奖错误，未选择开奖场次!";
			return index();
		}

		MatchArrange openPrizeMatch = matchArrangeService.getMatchInfoByMatchNo(op_matchNo, LotteryType.竞彩篮球);
		if (null == openPrizeMatch) {
			message += "竞彩篮球开奖错误,所选场次" + op_matchNo + "不存在！";
			return index();
		}
		
		if (openPrizeMatch.getStatus() != RaceStatus.已停售 && 
				openPrizeMatch.getStatus() != RaceStatus.已开奖) {
			message += "竞彩篮球开奖错误，该场次未处于\"已停售\"状态！";
			return index();
		}
		
		if(!isMatchValidForOpenPrize(openPrizeMatch)) {
			return index();
		}
		
		/** 开奖 */
		List<Order> orderList = lotteryOrderService.getOrderByLastMatch(openPrizeMatch);
		
		
		String lastNo = "";		
		logger.info("竞彩篮球第" + openPrizeMatch.getBoutIndex() + "场开奖");
		if (!orderList.isEmpty()) {
			try {
				for (Order order : orderList) {
					Set<String> stringSet = new HashSet<String>();
					List<PlanItem> planItems = new ArrayList<PlanItem>();
					planItems = order.getPlan().getItems();
					for(int j=0; j<planItems.size(); j++)
					{
						List<Ticket> ticket = planItems.get(j).getTicket();
						for(int k=0; k<ticket.size(); k++)
						{
							String ticketContent = ticket.get(k).getContent();
							String[] allMatch = ticketContent.split("\\|")[1].split(",");
							for(int m=0; m<allMatch.length; m++)
							{
								String matchNo = allMatch[m].split("=")[0];
								//混合过关
								if(matchNo.indexOf(">") != -1)
								{
									matchNo = matchNo.split(">")[1];
								}
								stringSet.add(matchNo);
							}
						}
					}
					List<String> noResultList = setLastMatch(stringSet);
					lastNo = sortList(noResultList);
					if(lastNo != null)
					{
						MatchArrange lastMatch = matchArrangeService.getMatchInfoByMatchNo(lastNo, LotteryType.竞彩篮球);
						order.setLastMatch(lastMatch);
						lotteryOrderService.save(order);
						continue;
					}
					if (order.getStatus().equals(OrderStatus.出票成功)
							|| order.getStatus().equals(OrderStatus.部分出票成功)) {
						List<PlanItem> items = lotteryTermService
								.getPlanItemList(order.getPlan());
						openPrizeItems(items, order);
					}
				}
			} 
			catch (Exception e) {
				message += "场次" + op_matchNo + "开奖异常！" + e.getMessage();
				return AJAXJSON;
			}
		}
		openPrizeMatch.setStatus(RaceStatus.已开奖);
		matchArrangeService.save(openPrizeMatch);
		
		message += "场次" + op_matchNo + "开奖成功！";
		return index();
		//return AJAXJSON;
	}

	public void openPrizeItems(List<PlanItem> items, Order order)
			throws Exception
	{
		logger.debug("开奖(PlanItem List)");
		BigDecimal winMoney = new BigDecimal(0);
		BigDecimal winTaxMoney = new BigDecimal(0);
		for (PlanItem item : items) {
			for (Ticket ticket : item.getTicket()) {
				if (ticket.getOrder().equals(order)
						&& ticket.getStatus() == TicketStatus.已出票) {
					try {
						//FIXME
						WinDescribeTicket winTicket = jclqBingoCheck.execute(ticket);

						if (winTicket != null) {
							winMoney = winMoney.add(winTicket.getMoney());
							winTaxMoney = winTaxMoney.add(winTicket
									.getTaxmoney());
							winTicket.setItem(item);
							lotteryOrderService.
								saveWinDescribeTicket(winTicket);
							WinDescribeOrder winDescribeOrder = new WinDescribeOrder();
							winDescribeOrder.setMoney(winTicket.getTaxmoney());
							winDescribeOrder.setNumber(winTicket.getNumber());
							winDescribeOrder.setOrder(order);
							lotteryOrderService
									.saveWinDescribeOrder(winDescribeOrder);
						}
					} 
					catch (Exception e) {
						e.printStackTrace();
						throw new Exception("竞彩篮球开奖计算时奖金错误!");
					}
				}
			}
		}
		lotteryOrderService.updateOrder(order.getId(), winMoney, winTaxMoney);
	}

	public String duiPrize() throws Exception 
	{
		message = "";
		if (op_matchNo == null || op_matchNo.equals("")) {
			message += "竞彩篮球兑奖错误，未选择兑奖场次!";
			return index();
		}
		
		MatchArrange duiPrizeMatch = matchArrangeService.getMatchInfoByMatchNo(op_matchNo, LotteryType.竞彩篮球);
		if (null == duiPrizeMatch) {
			message += "竞彩篮球兑奖错误,所选场次不存在！";
			return index();
		}
		
		if (duiPrizeMatch.getStatus() != RaceStatus.已开奖) {
			message += "竞彩篮球兑奖错误，该场次尚未开奖或已兑奖！";
			return index();
		}
		
		/** 开始兑奖 */
		List<Order> orderList = lotteryOrderService.getOrderByLastMatch(duiPrizeMatch);
		logger.debug("竞彩篮球第" + duiPrizeMatch.getBoutIndex() + "场兑奖");
		if (!orderList.isEmpty()) {
			try {
				for (Order order : orderList) {
					//FIXME
					if (order.getOrderResult().equals(OrderResult.已中奖)) {
			            if (order.getCommunity() == null)
			            	lotteryOrderService.paijiang(order.getTerm(), order);
			            else {
			                communityService.returnMoney(order.getCommunity(), order
			                        .getWinTaxMoney(), WalletLogType.账户返奖, order.getTerm().getType()
			                        .name()
			                        + "竞彩篮球合买返奖");
			            }
			            order.setOrderResult(OrderResult.已兑奖);
			            lotteryOrderService.save(order);
			        }
				}
			}
			catch (Exception e) {
				message += "场次" + op_matchNo + "兑奖异常，" + e.getMessage();
				return index();
			}
		}
		duiPrizeMatch.setStatus(RaceStatus.已兑奖);
		matchArrangeService.save(duiPrizeMatch);
		
		message = "场次" + op_matchNo + "兑奖成功！";
		return index();
	}
	
	public String openBatchPrize() throws Exception
	{
		String lastNo = "";	
		message = "";
		if(op_matchNos == null) {
			return index();
		}
		String matcheNos[] = op_matchNos.split("\\^");
		for(String matchNo : matcheNos) {
			MatchArrange openPrizeMatch = matchArrangeService.getMatchInfoByMatchNo(matchNo, LotteryType.竞彩篮球);
			if (null == openPrizeMatch) {
				message = "竞彩篮球开奖错误,所选场次"+ matchNo + "不存在！";
				return index();
			}
			if (openPrizeMatch.getStatus() != RaceStatus.已停售 && 
					openPrizeMatch.getStatus() != RaceStatus.已开奖) {
				message = "竞彩篮球开奖错误，场次" + matchNo + "未处于\"已停售\"状态！";
				return index();
			}
			
			if(!isMatchValidForOpenPrize(openPrizeMatch)) {
				continue;
			}
			
			/** 开奖 */
			List<Order> orderList = lotteryOrderService.getOrderByLastMatch(openPrizeMatch);
			logger.info("竞彩篮球第" + openPrizeMatch.getBoutIndex() + "场开奖");
			if (!orderList.isEmpty()) {
				try {
					for (Order order : orderList) {
						Set<String> stringSet = new HashSet<String>();
						List<PlanItem> planItems = new ArrayList<PlanItem>();
						planItems = order.getPlan().getItems();
						for(int j=0; j<planItems.size(); j++)
						{
							List<Ticket> ticket = planItems.get(j).getTicket();
							for(int k=0; k<ticket.size(); k++)
							{
								String ticketContent = ticket.get(k).getContent();
								String[] allMatch = ticketContent.split("\\|")[1].split(",");
								for(int m=0; m<allMatch.length; m++)
								{
									String matchNum = allMatch[m].split("=")[0];
									stringSet.add(matchNum);
								}
							}
						}
						List<String> noResultList = setLastMatch(stringSet);
						lastNo = sortList(noResultList);
						if(lastNo != null)
						{
							MatchArrange lastMatch = matchArrangeService.getMatchInfoByMatchNo(lastNo, LotteryType.竞彩篮球);
							order.setLastMatch(lastMatch);
							lotteryOrderService.save(order);
							continue;
						}
						
						if (order.getStatus().equals(OrderStatus.出票成功)
								|| order.getStatus().equals(OrderStatus.部分出票成功)) {
							List<PlanItem> items = lotteryTermService
									.getPlanItemList(order.getPlan());
							openPrizeItems(items, order);
						}
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
		            logger.error("openprizeerror", e);
					message += "场次" + matchNo + "开奖异常！" + e.getMessage();
					return index();
				}
			}
			openPrizeMatch.setStatus(RaceStatus.已开奖);
			matchArrangeService.save(openPrizeMatch);
			message = "开奖成功！";

		}
		return index();
	}
	
	public String duiBatchPrize()
	{
		message = "";
		if(op_matchNos == null) {
			return index();
		}
		String matcheNos[] = op_matchNos.split("\\^");
		for(String matchNo : matcheNos) {
			MatchArrange duiPrizeMatch = matchArrangeService.getMatchInfoByMatchNo(matchNo, LotteryType.竞彩篮球);
			if (null == duiPrizeMatch) {
				message += "竞彩篮球兑奖错误,所选场次不存在！";
				return index();
			}
			if (duiPrizeMatch.getStatus() != RaceStatus.已开奖) {
				message += "竞彩篮球兑奖错误，该场次尚未开奖或已兑奖！";
				return index();
			}
			
			/** 开始兑奖 */
			List<Order> orderList = lotteryOrderService.getOrderByLastMatch(duiPrizeMatch);
			logger.info("竞彩篮球第" + matchNo + "场兑奖");
			if (!orderList.isEmpty()) {
				try {
					for (Order order : orderList) {
						//FIXME
						if (order.getOrderResult().equals(OrderResult.已中奖)) {
				            if (order.getCommunity() == null)
				            	lotteryOrderService.paijiang(order.getTerm(), order);
				            else {
				                communityService.returnMoney(order.getCommunity(), order
				                        .getWinTaxMoney(), WalletLogType.账户返奖, order.getTerm().getType()
				                        .name()
				                        + "竞彩篮球合买返奖");
				            }
				            order.setOrderResult(OrderResult.已兑奖);
				            lotteryOrderService.save(order);
				        }
					}
				}
				catch (Exception e) {
					e.printStackTrace();
		            logger.error("duijiangprizeerror", e);
					message += "场次" + matchNo + "兑奖异常！" + e.getMessage();
					return index();
				}
			}
			duiPrizeMatch.setStatus(RaceStatus.已兑奖);
			matchArrangeService.save(duiPrizeMatch);
		}
		message = "兑奖成功！";
		return index();
	}
	
	public String cancel()
	{
		message = "";
		if(op_matchNos == null) {
			return index();
		}
		String matcheNos[] = op_matchNos.split("\\^");
		for(String matchNo : matcheNos) {
			MatchArrange cancelMatch = matchArrangeService.getMatchInfoByMatchNo(matchNo, LotteryType.竞彩篮球);
			if (null == cancelMatch) {
				message += "对不起,所选场次不存在！";
				return index();
			}
			if(cancelMatch.getStatus() != RaceStatus.已停售)
			{
				message += "对不起，所选场次状态不是已停售";
				return index();
			}
			cancelMatch.setSpecial(true);
			matchArrangeService.save(cancelMatch);
		}
		message += "设置状态成功";
		return index();
	}
	
	public boolean isMatchValidForOpenPrize(MatchArrange match) 
	{
		if(match.isSpecial())	return true;	//比赛取消的情况
		
		if(match.getWholeScore() == null)		//没有比分
			return false;
		/*
		String szPlayType = match.getPlayTypes();	//允许的玩法
		boolean bAllowSGSF = szPlayType.indexOf(JCLQTypes.typeToEn(JCLQTypes.单关胜负)) >= 0;
		if(bAllowSGSF && (match.getSfResult() == null))
			return false;
		
		boolean bAllowSGRFSF = szPlayType.indexOf(JCLQTypes.typeToEn(JCLQTypes.单关让分胜负)) >= 0;
		if(bAllowSGRFSF && (match.getRfsfResult() == null))
			return false;
		
		boolean bAllowSGDXF = szPlayType.indexOf(JCLQTypes.typeToEn(JCLQTypes.单关大小分)) >= 0;
		if(bAllowSGDXF && (match.getDxfResult() == null))
			return false;
		*/
		return true;
	}
	public String getOp_matchNo() {
		return op_matchNo;
	}

	public void setOp_matchNo(String op_matchNo) {
		this.op_matchNo = op_matchNo;
	}
	
	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public Page<MatchArrange> getPage()
	{
		return page;
	}

	public void setOp_matchNos(String op_matchNos)
	{
		this.op_matchNos = op_matchNos;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
	
	/**得到没有比赛结果的matchNo*/
	public List<String> setLastMatch(Set<String> set)
	{
		Iterator ite = set.iterator();
		List<String> noResultList = new ArrayList<String>();
		for(Object o : set)
		{
			String matchNo = (String) ite.next();
			MatchArrange matchArrange = matchArrangeService.getMatchInfoByMatchNo(matchNo, LotteryType.竞彩篮球);
			String resule = matchArrange.getWholeScore();
			if(resule == null || resule == "")
			{
				if(matchArrange.isSpecial() == false)
				{
					noResultList.add(matchNo);
				}			
			}
		}
		return noResultList;
	}
	
	/**对结果进行排序   得到最后的比赛编号*/
	public String sortList(List<String> list)
	{

		int jj = 0;

		int max = 0;
		if(list.size() == 0)
		{
			return null;
		}
		else 
		{
			max = Integer.parseInt(list.get(0));
			for(int j=1; j<list.size(); j++)
			{
				jj = Integer.parseInt(list.get(j));
				if(max < jj)
				{
					max=jj;
				}
			}

		}		
		return String.valueOf(max);
	}
}