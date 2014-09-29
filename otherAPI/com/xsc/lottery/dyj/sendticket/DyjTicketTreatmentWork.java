package com.xsc.lottery.dyj.sendticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.OrderQueue;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.OrderQueueService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.service.business.SupplierService;
import com.xsc.lottery.task.message.MessageTaskExcutor;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.LotteryTypeUtil;
import com.xsc.lottery.util.MD5;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.util.NetWorkUtil;
import com.xsc.lottery.util.ZLibUtils;

@Component("dyjTicketTreatmentWork")
public class DyjTicketTreatmentWork extends TicketTreatmentWork {
	private String wAgent = Configuration.getInstance().getValue("dyj.agent");
	private String dyjKey = Configuration.getInstance().getValue("dyj.key");
	private String url = Configuration.getInstance().getValue("dyj.url");

	@Autowired
	private LotteryHandleFactory lotteryHandleFactory;

	@Autowired
	private OrderQueueService orderQueueService;

	@Autowired
	private LotteryOrderService orderService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	public MessageTaskExcutor messageTaskExcutor;

	@Autowired
	private AdminMobileService adminMobileService;
	
	@Autowired
	private SmsLogService smsLogService;

	@Override
	public boolean allowed(LotteryTerm term) {
		return true;
	}

	@Override
	protected List<Ticket> takeTicket(Order order, List<PlanItem> planItems)
			throws Exception {
		return lotteryHandleFactory.getLotteryHandleFactory(order.getType())
				.getTicketByPlanItems(order, planItems, getTicketPlat());
	}

	/**
	 * 大赢家送票
	 */
	@Override
	protected void deliveTicket(Ticket ticket) {
		logger.info("deliveTicket");
		if (StringUtils.isBlank(ticket.getOtherOrderID())) {
			ticket.setOtherOrderID(MathUtil.getSerialNumber(20));
		}
		ticket.setSendTicketTime(Calendar.getInstance());
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("OrderID=").append(ticket.getOtherOrderID()).append(
				"_");
		sendParam.append("LotID=").append(typeToDYJType(ticket.getType()))
				.append("_");
		sendParam.append("LotIssue=").append(changeIssueFormat(ticket.getType(), ticket.getTermNo())).append("_");
		sendParam.append("LotMoney=").append(ticket.getMoney().intValue())
				.append("_");
		sendParam.append("LotCode=").append(ticket.getContent()).append("_");
		sendParam.append("LotMulti=").append(ticket.getMultiple()).append("_");
		sendParam.append("OneMoney=").append(ticket.getOneMoney().intValue());
		String xml = "";
		try {
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam("101",
					sendParam.toString(), ticket.getOtherOrderID()), "GB2312");
			System.out.println(sendParam.toString());
			System.out.println(xml);
			if (StringUtils.isBlank(xml)) {
				// String supplierName = ticket.getOrder().getTicketThirdName();
				// int nCode =
				// supplierService.getSupplierByName(supplierName).getCode();
				// SetSupplierFalse(ticket.getType(), nCode);
				logger.info("blankxml");
				return;
			}

			Map<String, String> returnMap = parseResponse(xml);
			String xCode = returnMap.get("xCode");
			if ("0".equals(xCode)) {
				// 大赢家已保存
				ticket.setStatus(TicketStatus.出票中);
				// Supplier.setSuccess(ticket.getType());
			} else if ("1".equals(xCode)) {
				ticket.setStatus(TicketStatus.已出票);
				// Supplier.setSuccess(ticket.getType());
			} else if ("_10000".equals(xCode)) {
				ticket.setStatus(TicketStatus.出票失败);
				ticket.setOtherMsg("第三方出票返回错误参数");
				// String supplierName = ticket.getOrder().getTicketThirdName();
				// int nCode =
				// supplierService.getSupplierByName(supplierName).getCode();
				// SetSupplierFalse(ticket.getType(), nCode);
			} else {
				ticket.setStatus(TicketStatus.出票失败);
				ticket.setOtherMsg(returnMap.get("xMesg"));
				SystemWarningNotify.addWarningDescription("ID号："
						+ ticket.getId() + " 的票在大赢家出票失败,返回失败结果：" + xCode
						+ " , 原因:" + returnMap.get("xMesg"));

			}

		} catch (Exception e) {
			// ticket.setStatus(TicketStatus.出票失败);
			// ticket.setOtherMsg(e.getMessage());
			logger.info("=======请求参数  ========"
					+ buildRequestParam("101", sendParam.toString(), ticket
							.getOtherOrderID()));
			logger.info("=======接口返回结果========" + xml);
			logger.warn("票编号：" + ticket.getId() + "的票在出票中出现异常.");
			SystemWarningNotify.addWarningDescription("大赢家出票异常：" + e);
		}
	}

	protected void deliveTicket(List<Ticket> tickets) {
		logger.info("deliveTickets with size " + tickets.size());
	}

	/**
	 * 大赢家检票
	 */
	@Override
	protected void checkTicket(Ticket ticket) {
		logger.info("check ticket");

		StringBuilder sendParam = new StringBuilder();
		sendParam.append("OrderID=").append(ticket.getOtherOrderID());
		String xml = "";
		try {
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam("102",
					sendParam.toString(), ticket.getOtherOrderID()), "GB2312");

			if (StringUtils.isBlank(xml)) {
				return;
			}

			Map<String, String> returnMap = parseResponse(xml);
			String xCode = returnMap.get("xCode");
			if ("1".equals(xCode)) {
				ticket.setStatus(TicketStatus.已出票);
			} else if ("2002".equals(xCode)) {
				ticket.setStatus(TicketStatus.出票中);
			} else if ("2004".equals(xCode)) {
				logger.info("大赢家--查询结果为空");
				ticket.setStatus(TicketStatus.出票中);
			} else if ("198".equals(xCode)) {
				logger.info("大赢家--系统忙");
				ticket.setStatus(TicketStatus.出票中);
			} else {// 不在出票中,默认出票失败
				ticket.setStatus(TicketStatus.出票失败);
				ticket.setOtherMsg(returnMap.get("xMesg"));
			}

		} catch (Exception e) {
			ticket.setStatus(TicketStatus.出票中);
			System.out.println("=======请求参数  ========"
					+ buildRequestParam("102", sendParam.toString(), ticket
							.getOtherOrderID()));
			System.out.println("=======接口返回结果========" + xml);
			logger.warn("票编号：" + ticket.getId() + "的票在检票中出现异常.");
			SystemWarningNotify.addWarningDescription("大赢家检票异常：" + e);
		}
	}

	/**
	 * 大赢家批量检票
	 */
	@Override
	public void checkTicket(List<Ticket> tickets) {
		logger.info("check tickets with size " + tickets.size());

		if (tickets.size() < 1) {
			return;
		}

		Map<String, Ticket> ticketMap = new HashMap<String, Ticket>();
		Ticket lastTicket = tickets.get(tickets.size() - 1);
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("LotID=").append(typeToDYJType(lastTicket.getType()))
				.append("_");
		sendParam.append("OrderIDs=");
		for (int i = 0; i < tickets.size() - 1; i++) {
			Ticket ticket = tickets.get(i);
			sendParam.append(ticket.getOtherOrderID() + ",");
			ticketMap.put(ticket.getOtherOrderID(), ticket);
		}

		sendParam.append(lastTicket.getOtherOrderID());
		ticketMap.put(lastTicket.getOtherOrderID(), lastTicket);

		String xml = "";
		try {
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam("114",
					sendParam.toString(), lastTicket.getOtherOrderID()),
					"GB2312");

			if (StringUtils.isBlank(xml)) {
				logger.info("blankxml");
				return;
			}

			Map<String, String> returnMap = parseResponse(xml);

			String xCode = returnMap.get("xCode");
			logger.info("大赢家检票返回 xCode = " + xCode);
			if ("0".equals(xCode)) {
				// 本次查询成功
				String xValue = returnMap.get("xValue");
				String values[] = xValue.split("\\,");
				logger.info("大赢家检票返回 " + values.length);

				for (int i = 0; i < values.length; i++) {
					String ticket_orderId = values[i].split("_")[0];
					String code = values[i].split("_")[1];
					Ticket ticket = ticketMap.get(ticket_orderId);

					if (null == ticket) {
						continue;
					}

					if ("1".equals(code)) {
						ticket.setStatus(TicketStatus.已出票);
					} else if ("2002".equals(code)) {
						ticket.setStatus(TicketStatus.出票中);
					} else if ("198".equals(code)) {
						logger.info("大赢家--系统忙");
						ticket.setStatus(TicketStatus.出票中);
					}else if ("2004".equals(code)) {//网络原因居多 所以设为出票中
						logger.info("大赢家--不在查询列表，出票失败（出票中...code-2004）");
						ticket.setStatus(TicketStatus.出票中);
					}
					else {
						logger.info("大赢家--不在查询列表，出票失败");
						ticket.setStatus(TicketStatus.出票失败);

					}
				}
			} else if ("2004".equals(xCode)) {
				logger.info("大赢家--不在查询列表，出票失败（出票中...xcode-2004）");//网络原因居多 所以设为出票中

				for (Ticket ticket : tickets) {
					ticket.setStatus(TicketStatus.出票中);

				}
			}

		} catch (Exception e) {
			logger.info("大赢家检票异常.");
			SystemWarningNotify.addWarningDescription("大赢家检票异常：" + e);
		}
	}

	/**
	 * 大赢家查询竞彩玩法出票成功的票的赔率
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void checkTicketSP(Ticket ticket) {
		logger.info("大赢家check ticket SP");
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("OrderID=").append(ticket.getOtherOrderID());
		String xml = "";
		String transNO = ticket.getType() == LotteryType.竞彩篮球 ?"102":"120";
		try {
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam(transNO,
					sendParam.toString(), ticket.getOtherOrderID()), "GB2312");
			if (StringUtils.isBlank(xml)) {
				return;
			}
			if(ticket.getType() == LotteryType.竞彩足球)
			{
				Map<String, Object> returnMap = parseResponseForJCZQ(xml);
				String xCode = (String) returnMap.get("xCode");
				if ("0".equals(xCode)) {
					// 参照 Ticket.java中定义的赔率字段的格式
					Element xValue = (Element) returnMap.get("xValue");
					String ratio = null;
					List elements = xValue.element("Project").element("bill").elements();
					for (Iterator it = elements.iterator(); it.hasNext();) {
						Element match = (Element) it.next();
						String temp = match.getTextTrim();
						String spfs[] = temp.split("\\|");
						String oneRatio = null;
						for (String spf : spfs) {
							if (oneRatio == null) {
								oneRatio = spf.split("=")[1];
							} else {
								oneRatio = oneRatio + "/" + spf.split("=")[1];
							}
						}
						if (ratio == null) {
							ratio = oneRatio;
						} else {
							ratio = ratio + "|" + oneRatio;
						}

					}
					if (ratio != null && !ratio.equals("")) {
						ticket.setRatio(ratio);
					}
				} else {
					logger.info("三方票号为 " + ticket.getOtherOrderID()
							+ " 的竞彩赔率查询失败  ==>  " + returnMap.get("xCode") + " "
							+ returnMap.get("xMesg"));
				}
			}
			else if(ticket.getType() == LotteryType.竞彩篮球){
				Map<String, String> returnMap = parseResponse(xml);
				String xCode = (String) returnMap.get("xCode");
				if ("1".equals(xCode)) {
					// 参照 Ticket.java中定义的赔率字段的格式
					//20136947049281727162_99999999_1_SF|140813302=0(9.28),140813301=3(3.12)/0(4.7)|2*1
					String xValue = returnMap.get("xValue");
					xValue = xValue.split("\\_")[3];
					BASE64Decoder base64 = new BASE64Decoder();
					xValue = new String(ZLibUtils.decompress(base64.decodeBuffer(xValue)));
					logger.info("竞彩篮球第三方订单号"+ticket.getOtherOrderID()+"赔率为："+xValue);
					String ratio = null;
					String spfs[] = xValue.split("\\|");
					String oneRatio = "";
					String options[] = spfs[1].split("\\,");
					if(ticket.getPlayType() == PlayType.SF)
					{
						for (int i = 0; i < options.length; i++) {
							String temp[] = options[i].split("\\=");
							String option[] = temp[1].split("\\/");
							for (int j = 0; j < option.length; j++) {
								String tempStr = option[j];
								if(j<(option.length-1))
								{
									
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"))+"/";
								}
								else
								{
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"));
								}
							}
							if(i<(options.length-1))
							{
								oneRatio += "|";
							}
						}
					}
					else if(ticket.getPlayType() == PlayType.RFSF)
					{
						for (int i = 0; i < options.length; i++) {
							String temp[] = options[i].split("\\=");
							String option[] = temp[1].split("\\/");
							for (int j = 0; j < option.length; j++) {
								String tempStr = option[j];
								String rangfen = tempStr.split("\\_")[0];//选项的让分存储在哪？如何显示给用户
								logger.info(temp[0]+"让分："+rangfen);
								if(j<(option.length-1))
								{
									
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"))+"/";
								}
								else
								{
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"));
								}
							}
							if(i<(options.length-1))
							{
								oneRatio += "|";
							}
						}
					}
					else if(ticket.getPlayType() == PlayType.DXF)
					{
						for (int i = 0; i < options.length; i++) {
							String temp[] = options[i].split("\\=");
							String option[] = temp[1].split("\\/");
							for (int j = 0; j < option.length; j++) {
								String tempStr = option[j];
								String zongfen = tempStr.split("\\_")[0];//选项的预设总分存储在哪？如何显示给用户
								logger.info(temp[0]+"预设总分："+zongfen);
								if(j<(option.length-1))
								{
									
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"))+"/";
								}
								else
								{
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"));
								}
							}
							if(i<(options.length-1))
							{
								oneRatio += "|";
							}
						}
					}
					else if(ticket.getPlayType() == PlayType.SFC)
					{
						for (int i = 0; i < options.length; i++) {
							String temp[] = options[i].split("\\=");
							String option[] = temp[1].split("\\/");
							for (int j = 0; j < option.length; j++) {
								String tempStr = option[j];
								if(j<(option.length-1))
								{
									
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"))+"/";
								}
								else
								{
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"));
								}
							}
							if(i<(options.length-1))
							{
								oneRatio += "|";
							}
						}
					}
					else if(ticket.getPlayType() == PlayType.JL_HH)
					{
						for (int i = 0; i < options.length; i++) {
							String gameTypes = options[i].split("\\>")[0];
							String temp[] = options[i].split("\\=");
							String option[] = temp[1].split("\\/");
							for (int j = 0; j < option.length; j++) {
								String tempStr = option[j];
								if("RFSF".equals(gameTypes))
								{
									String rangfen = tempStr.split("\\_")[0];//选项的让分存储在哪？如何显示给用户
									logger.info(temp[0]+"让分："+rangfen);
								}
								else if("DXF".equals(gameTypes))
								{
									String zongfen = tempStr.split("\\_")[0];//选项的预设总分存储在哪？如何显示给用户
									logger.info(temp[0]+"预设总分："+zongfen);
								}
								if(j<(option.length-1))
								{
									
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"))+"/";
								}
								else
								{
									oneRatio += tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")"));
								}
							}
							if(i<(options.length-1))
							{
								oneRatio += "|";
							}
						}
					}
					ratio = oneRatio;
					if (ratio != null && !ratio.equals("")) {
						ticket.setRatio(ratio);
					}
				} else {
					logger.info("三方票号为 " + ticket.getOtherOrderID()
							+ " 的竞彩赔率查询失败  ==>  " + returnMap.get("xCode") + " "
							+ returnMap.get("xMesg"));
				}
			}
			
		} catch (Exception e) {
			System.out.println("=======请求参数  ========"
					+ buildRequestParam("119", sendParam.toString(), ticket
							.getOtherOrderID()));
			System.out.println("=======接口返回结果========" + xml);
			logger.warn("票编号：" + ticket.getId() + "的票在查询赔率时中出现异常.");
			SystemWarningNotify.addWarningDescription("大赢家查询赔率异常：" + e);
		}
	}
	
	public String getJiangjin(Ticket ticket) {
		String param = "";
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("OrderID=").append(ticket.getOtherOrderID());
		String xml = "";
		try {
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam("111",
					sendParam.toString(), ticket.getOtherOrderID()), "GB2312");
			Map<String, String> returnMap = parseResponse(xml);
			param = returnMap.get("xValue");
		} catch (Exception e) {
			System.out.println("=======接口返回结果========" + xml);
			logger.warn("票编号：" + ticket.getId() + "的获得开奖数据时出现异常.");
			SystemWarningNotify.addWarningDescription("大赢家出票异常：" + e);
			e.printStackTrace();
		}
		return param;
	}

	public static void main(String[] args) {

		String ss = "02130321_01 02 03 04 05";
		ss = ss.split("_")[1].replace(" ", ",");
		List<winTicketDis> list = new ArrayList<winTicketDis>();
		if (list != null) {
			System.out.println("notnull");
		} else {
			System.out.println("null");
		}
		// System.out.println(ss);
	}

	public void getOpenResult(LotteryTerm term) {
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("LotID=").append(typeToDYJType(term.getType()))
				.append("_").append("LotIssue=").append(
						changeIssueFormat(term.getType(), term.getTermNo()));

		System.out.println("sendParam = " + sendParam);
		String xml = "";
		try {
			System.out.println(buildRequestParam("110", sendParam.toString(),
					MathUtil.getSerialNumber(20)));
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam("110",
					sendParam.toString(), MathUtil.getSerialNumber(20)),
					"GB2312");
			logger.info("getOpenResult=========" + xml);
			Map<String, String> returnMap = parseResponse(xml);
			String xvalue = returnMap.get("xValue");
			if ("0".equals(returnMap.get("xCode"))
					&& xvalue.split("_").length > 1
					&& StringUtils.isNotBlank(xvalue)) {
				if(term.getType() == LotteryType.重庆时时彩)
				{
					xvalue =  xvalue.split("_")[1].replace("", ",");
					xvalue = xvalue.substring(1, xvalue.length()-1);
				}
				else
				{
					xvalue = xvalue.split("_")[1].replace(" ", ",");
				}
				System.out.println("获取的结果为：" + xvalue);
				term.setResult(xvalue);
			} else
				throw new Exception(term + "期获取开奖结果数据失败,返回失败结果："
						+ returnMap.get("xCode"));
		} catch (Exception e) {
			logger.info("==============获取结果失败===========" + e);

			if (term.getType().equals(LotteryType.老11选5)
					|| term.getType().equals(LotteryType.快乐扑克3) || term.getType().equals(LotteryType.上海11选5) || term.getType().equals(LotteryType.十一运夺金) || term.getType().equals(LotteryType.重庆时时彩)) {
				Calendar time = (Calendar) term.getOpenPrizeTime().clone();
				Calendar time2 = (Calendar) term.getOpenPrizeTime().clone();
				time.add(Calendar.MINUTE, 4);
				time2.add(Calendar.MINUTE, 5);
				if ((time.compareTo(Calendar.getInstance()) < 0)
						&& (time2.compareTo(Calendar.getInstance()) > 0)) {

					logger.warn("获取" + term.getType() + "第" + term.getTermNo()
							+ "结果连续失败，无法获取结果，获取结果结束");
					String str = "【一彩票网】第" + term.getTermNo() + "期  "
							+ term.getType() + "自动开奖失败，没有获取到结果！";
					List<AdminMobile> adminMobiles = adminMobileService
							.getAllAdminMobile();
					for (AdminMobile adminMobile : adminMobiles) {
						smsLogService.saveSmsLog(adminMobile.getMobile(), str, null,SmsLogType.WARN);
						//messageTaskExcutor.addNotifySM(adminMobile.getMobile(),str);
					}
				}

			} else {
				// 重复抓取 规则 开奖时间 下期结束时间-1秒
				Long s = lotteryHandleFactory.getLotteryHandleFactory(
						term.getType()).getNextTerm(term).getStopSaleTime()
						.getTimeInMillis();
				s = s - 60000l;
				if (System.currentTimeMillis() - s >= 0) {
					logger.info("=======接口返回结果========" + xml);
					logger.warn(term + "期获取开奖结果数据异常.==>" + e.getMessage());
					SystemWarningNotify.addWarningDescription("大赢家出票异常：" + e);
					return;
				}
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e1) {
				}
				getOpenResult(term);
			}
		}
	}

	public List<winTicketDis> getWinTicketByTerm(LotteryTerm term) {
		logger.info("===========大赢家获取派奖数据============");
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("LotID=").append(typeToDYJType(term.getType()))
				.append("_").append("LotIssue=").append(
						changeIssueFormat(term.getType(), term.getTermNo()));
		System.out.println("sendParam : " + sendParam);
		String xml = "";
		try {
			xml = NetWorkUtil.getHttpUrl(url, buildRequestParam("112",
					sendParam.toString(), MathUtil.getSerialNumber(20)),
					"GB2312");

			System.out.println("getWinTicketByTerm ======" + xml);
			Map<String, String> returnMap = parseResponse(xml);
			if ("0".equals(returnMap.get("xCode"))) {
				String param = returnMap.get("xValue");
				List<winTicketDis> list = new ArrayList<winTicketDis>();
				if (StringUtils.isNotBlank(param)) {

					for (String s : param.split(",")) {
						list.add(new winTicketDis(s.split("_")[0],
								new BigDecimal(s.split("_")[2]),
								new BigDecimal(s.split("_")[3])));
						System.out.println("中奖id 为：" + s.split("_")[0]);
					}

				}
				return list;
			}
			// else {
			//            	
			// try {
			// Thread.sleep(20000);
			// }
			// catch (InterruptedException e1) {
			// }
			//  	            
			// Calendar time = (Calendar) term.getOpenPrizeTime().clone();
			// time.add(Calendar.MINUTE, 4);
			// if (time.compareTo(Calendar.getInstance()) > 0) {
			// getOpenResult(term);
			// }
			// else {
			// logger.warn("获取结果连续失败，无法获取结果，获取结果结束");
			// String str = "第" + term.getTermNo() + "期  " + term.getType() +
			// "自动兑奖失败，没有获取到结果！" ;
			// List<AdminMobile> adminMobiles =
			// adminMobileService.getAllAdminMobile();
			// for (AdminMobile adminMobile : adminMobiles) {
			// messageTaskExcutor.addNotifySM(adminMobile.getMobile(), str);
			// }
			// }
			// SystemWarningNotify.addWarningDescription(term
			// + "期获取中奖数据失败,返回失败结果：" + returnMap.get("xCode"));
			// }

		} catch (Exception e) {
			System.out.println("=======接口返回结果========" + xml);
			logger.warn(term + "期获取中奖数据异常.==>" + e.getMessage());
			SystemWarningNotify.addWarningDescription("大赢家出票异常：" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SendTicketPlat getTicketPlat() {
		return SendTicketPlat.大赢家;
	}

	/**
	 * 生成出票端的参数字符串
	 */
	private String buildRequestParam(String wAction, String wParam, String msgID) {
		StringBuilder mysign = new StringBuilder();
		mysign.append(wAgent).append(wAction).append(msgID).append(wParam)
				.append(dyjKey);
		StringBuilder param = new StringBuilder();
		param.append("wAgent=").append(wAgent).append("&");
		param.append("wAction=").append(wAction).append("&");
		param.append("wMsgID=").append(msgID).append("&");
		param.append("wParam=").append(wParam).append("&");
		param.append("wSign=").append(MD5.digest(mysign.toString()));

		return param.toString();
	}

	private Map<String, String> parseResponse(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Element rootElem = DocumentHelper.parseText(xml).getRootElement();
			map.put("xMsgID", rootElem.elementText("xMsgID"));
			map.put("xCode", rootElem.elementText("xCode"));
			map.put("xMesg", rootElem.elementText("xMessage"));
			map.put("xSign", rootElem.elementText("xSign"));
			map.put("xValue", rootElem.elementText("xValue"));

			StringBuilder vlaStr = new StringBuilder();
			vlaStr.append(map.get("xMsgID")).append(map.get("xCode")).append(
					map.get("xValue")).append(dyjKey);
			if (!MD5.digest(vlaStr.toString()).equals(map.get("xSign"))) {
				throw new Exception("大赢家通信接口返回数据校验出错");
			}
		} catch (Exception e) {
			map.put("xCode", "_10000");
			logger.info(e.getMessage() + " : " + xml);
		}
		return map;
	}

	// 竞彩足球的返回格式不一样
	private Map<String, Object> parseResponseForJCZQ(String xml) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Element rootElem = DocumentHelper.parseText(xml).getRootElement();
			map.put("xMsgID", rootElem.elementText("xMsgID"));
			map.put("xCode", rootElem.elementText("xCode"));
			map.put("xMesg", rootElem.elementText("xMessage"));
			map.put("xSign", rootElem.elementText("xSign"));
			// FIXME
			map.put("xValue", rootElem.element("xValue"));

			String xValue = rootElem.element("xValue").asXML();
			if (xValue.indexOf("<xValue>") != -1
					&& xValue.indexOf("</xValue>") != -1) {
				xValue = xValue.substring(8, xValue.length() - 9);
			}

			StringBuilder vlaStr = new StringBuilder();
			vlaStr.append(map.get("xMsgID")).append(map.get("xCode")).append(
					xValue).append(dyjKey);
			if (!MD5.digest(vlaStr.toString()).equals(map.get("xSign"))) {
				throw new Exception("大赢家通信接口返回数据校验出错");
			}
		} catch (Exception e) {
			map.put("xCode", "_10000");
			logger.warn(e.getMessage() + " : " + xml);
		}
		return map;
	}

	// 值转化
	private String typeToDYJType(LotteryType type) {
		if (type.equals(LotteryType.双色球)) {
			return "51";
		} else if (type.equals(LotteryType.大乐透)) {
			return "23529";
		} else if (type.equals(LotteryType.福彩3d)) {
			return "52";
		} else if (type.equals(LotteryType.排列三)) {
			return "33";
		} else if (type.equals(LotteryType.排列五)) {
			return "35";
		} else if (type.equals(LotteryType.七乐彩)) {
			return "23528";
		} else if (type.equals(LotteryType.七星彩)) {
			return "10022";
		} else if (type.equals(LotteryType.重庆时时彩)) {
			return "10401";
		} else if (type.equals(LotteryType.上海时时乐)) {
			return "10202";
		} else if (type.equals(LotteryType.足彩14场)) {
			return "11";
		} else if (type.equals(LotteryType.足彩任9)) {
			return "19";
		} else if (type.equals(LotteryType.竞彩足球)) {
			return "42";
		}  else if (type.equals(LotteryType.竞彩篮球)) {
			return "43";
		} else if (type.equals(LotteryType.四场进球)) {
			return "18";
		} else if (type.equals(LotteryType.足彩6场半)) {
			return "16";
		} else if (type.equals(LotteryType.老11选5)) {
			return "23009";
		} else if (type.equals(LotteryType.快乐扑克3)) {
			return "20410";
		}else if (type.equals(LotteryType.十一运夺金)) {
			return "21406";
		} else if (type.equals(LotteryType.上海11选5)) {
			return "21409";
		}  else {
			return "51";
		}
	}

	public void testSP() {
		Ticket ticket = new Ticket();
		ticket.setOtherOrderID("11423819262364860129");
		checkTicketSP(ticket);
	}

	@Override
	protected List<OrderQueue> getOrderQueue() throws Exception {
		// TODO Auto-generated method stub
		List<OrderQueue> allList = orderQueueService
				.getAllOrderQueueListByPlat(1);
		return allList;
	}

	@Override
	public void putOrderToQueue(Order order) {
		OrderQueue orderQueue = new OrderQueue();
		orderQueue.setOrderId(order.getId());
		orderQueue.setStatus(0);
		orderQueue.setSendTicketPlat(1);
		orderQueueService.save(orderQueue);
	}

	public void putOrderToOtherQueue(Order order, Integer code) {
		OrderQueue orderQueue = new OrderQueue();
		orderQueue.setOrderId(order.getId());
		orderQueue.setStatus(0);
		orderQueue.setSendTicketPlat(code);
		orderQueueService.save(orderQueue);
	}

	@Override
	protected void deleteOrderQueue(List<OrderQueue> allList) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < allList.size(); i++) {
			OrderQueue orderQueue = allList.get(i);
			Order order = orderService.findById(orderQueue.getOrderId());

			int typeNum = LotteryTypeUtil.changeLotteryTypeToNum(order
					.getTerm().getType());
			int nCode = orderQueue.getSendTicketPlat();

			List<Supplier> sl = supplierService
					.getAllActiveSupplierList(typeNum);

			if (sl.size() > 0) {
				Supplier supplierActive = sl.get(0);
				if (nCode != supplierActive.getCode()) {
					orderQueue.setStatus(1);
					orderQueueService.save(orderQueue);
					// changeStatus(order.getId(),1);
					putOrderToOtherQueue(order, supplierActive.getCode());
					continue;
				}
			}

			take(order);
			Long orderId = order.getId();
			orderQueue.setStatus(1);
			orderQueueService.save(orderQueue);
		}
		orderQueueService.deleteAllStatus1(1);
	}

	private String changeIssueFormat(LotteryType type, String termNo) {
		if (type.equals(LotteryType.快乐扑克3)) {
			return termNo.substring(2);
		} else {
			return termNo;
		}
	}

	@Override
	protected void queryTerm(LotteryType type) {
		
	}

	// public static void main(String[] args)
	// {
	// DyjTicketTreatmentWork dyj = new DyjTicketTreatmentWork();
	//        
	// /*String xml =
	// "<?xml version=\"1.0\" encoding=\"gb2312\"?><ActionResult><xMsgID>10122010241686869443</xMsgID><xCode>0</xCode><xMessage>成功</xMessage><xSign>b2572a7ef3ba45c2061e825a486584e6</xSign><xValue>10122010241686869443_5.00_5.00_5.00</xValue></ActionResult>";
	// String param = "";
	// Map<String, String> returnMap = dyj.parseResponse(xml);
	// param = returnMap.get("xValue");
	// System.out.println(param);*/
	//        
	// //dyj.testSP();
	//        
	// }
}
