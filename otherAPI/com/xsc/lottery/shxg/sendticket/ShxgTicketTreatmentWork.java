package com.xsc.lottery.shxg.sendticket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xg.client.comm.ParamVO;
import com.xg.client.comm.XXmlUtil;
import com.xg.client.crypter.Base64;
import com.xg.client.crypter.CompressBytes;
import com.xg.client.crypter.CreateDesKey;
import com.xg.client.crypter.CryptorHMAC;
import com.xg.client.crypter.CryptorXDES;
import com.xg.client.crypter.CryptorXRSA;
import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.OrderQueue;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrintLotteryTerm;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.entity.enumerate.TicketStatus;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.OrderQueueService;
import com.xsc.lottery.service.business.PrintLotteryTermService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.service.business.SupplierService;
import com.xsc.lottery.task.message.MessageTaskExcutor;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.util.NetWorkUtil;

@Component("shxgTicketTreatmentWork")
public class ShxgTicketTreatmentWork extends TicketTreatmentWork
{
	private String wAgent = Configuration.getInstance().getValue("shxg.agent");

	private String shxgKey = Configuration.getInstance().getValue("shxg.md5key");

	private String realName = Configuration.getInstance().getValue("shxg.realname");

	private String idCard = Configuration.getInstance().getValue("shxg.idcard");

	private String email = Configuration.getInstance().getValue("shxg.email");

	private final CryptorXRSA rsacryptor = new CryptorXRSA();

	private final CreateDesKey createkey = new CreateDesKey();

	@Autowired
	private LotteryHandleFactory lotteryHandleFactory;

	@Autowired
	private OrderQueueService orderQueueService;

	@Autowired
	private LotteryOrderService orderService;

	@Autowired
	private SmsLogService smsLogService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	public MessageTaskExcutor messageTaskExcutor;

	@Autowired
	private AdminMobileService adminMobileService;

	@Autowired
	private PrintLotteryTermService printTermService;

	@Override
	public boolean allowed(LotteryTerm term)
	{
		boolean flag = true;
		PrintLotteryTerm printTerm = printTermService.getPrintLotteryTerm(term.getOutPoint(), term.getType(), changeIssueFormat(term.getType(),term
				.getTermNo()));
		// 如果不存在 去同步
		if (printTerm == null)
		{
			//同步出票终端奖期
			queryTerm(term.getType());
			printTerm = printTermService.getPrintLotteryTerm(term.getOutPoint(), term.getType(), changeIssueFormat(term.getType(),term.getTermNo()));
			if (printTerm != null)
			{
				if (!printTerm.getStartSaleTime().before(Calendar.getInstance())
						|| !printTerm.getStopSaleTime().after(Calendar.getInstance()))
				{
					flag = false;
				}
			}
			else
			{
				flag = false;
			}
		
		}
		else
		{
			//如果存在   判断是否在销售时间内
			if (!printTerm.getStartSaleTime().before(Calendar.getInstance())|| !printTerm.getStopSaleTime().after(Calendar.getInstance()))
			{
				flag = false;
			}
		}
		return flag;
	}

	@Override
	protected List<Ticket> takeTicket(Order order, List<PlanItem> planItems) throws Exception
	{
		return lotteryHandleFactory.getLotteryHandleFactory(order.getType()).getTicketByPlanItems(order, planItems,
				getTicketPlat());
	}

	/**
	 * 新冠送票
	 */
	@Override
	protected void deliveTicket(Ticket ticket)
	{
		logger.info("deliveTicket");
		ParamVO outParamVo = null;
		if (StringUtils.isBlank(ticket.getOtherOrderID()))
		{
			ticket.setOtherOrderID(MathUtil.getSerialNumber(20));
		}
		ticket.setSendTicketTime(Calendar.getInstance());
		ParamVO inparamVO = new ParamVO("Req");
		inparamVO.setStringValue("@funcid", "8882");
		inparamVO.setStringValue("@agent", wAgent);
		inparamVO.setStringValue("@memo", "text");
		inparamVO.setStringValue("@lottid", typeToXGType(ticket.getType()));
		inparamVO.setStringValue("@period", changeIssueFormat(ticket.getType(), ticket.getTermNo()));
		inparamVO.setStringValue("@hmid", ticket.getOtherOrderID());
		// System.out.println("订单号："+String.valueOf(System.currentTimeMillis()));
		// inparamVO.setStringValue("@hmid",String.valueOf(System.currentTimeMillis()));
		inparamVO.setStringValue("@idcard", idCard);
		inparamVO.setStringValue("@realname", realName);
		inparamVO.setStringValue("@useraddress", email);
		inparamVO.setStringValue("@hmcount", "1");
		ParamVO tick = inparamVO.addXmlNode("t");
		String ticketStr = "1_" + ticket.getContent() + ":" + typeToPlayType(ticket.getType(), ticket.getPlayType())
				+ ":" + ticket.getMultiple();
		tick.addStringValue("@c", ticketStr);
		inparamVO.addLongValue("@timestamp", System.currentTimeMillis());
		try
		{
			outParamVo = httpXGRequest(inparamVO);
			if (outParamVo == null)
			{
				logger.info("新冠送票网络异常或签名检验未通过！");
				return;
			}
			String errcode = outParamVo.getStringValue("@errcode");
			String errdesc = outParamVo.getStringValue("@errdesc");
			if ("0000".equals(errcode))
			{
				// 新冠已保存
				ticket.setStatus(TicketStatus.出票中);
			}
			else
			{
				ticket.setStatus(TicketStatus.出票失败);
				ticket.setOtherMsg(errdesc);
				SystemWarningNotify.addWarningDescription("ID号：" + ticket.getId() + " 的票在新冠出票失败,返回失败结果：" + errcode
						+ " , 原因:" + errdesc);

			}

		}
		catch (Exception e)
		{

			logger.info("=======送票接口请求参数  ========"
					+ XXmlUtil.xml2String(inparamVO.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
			logger.info("=======送票接口返回结果========"
					+ XXmlUtil.xml2String(outParamVo.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
			logger.warn("票编号：" + ticket.getId() + "的票在出票中出现异常.");
			SystemWarningNotify.addWarningDescription("上海新冠出票异常：" + e);
		}
	}

	protected void deliveTicket(List<Ticket> tickets)
	{
		logger.info("deliveTickets with size " + tickets.size());
	}

	/**
	 * 新冠检票
	 */
	@Override
	protected void checkTicket(Ticket ticket)
	{
		logger.info("check ticket");
		ParamVO inparamVO = new ParamVO("Req");
		inparamVO.setStringValue("@funcid", "8883");
		inparamVO.setStringValue("@agent", wAgent);
		inparamVO.setStringValue("@memo", "text");
		inparamVO.setStringValue("@lottid", typeToXGType(ticket.getType()));
		inparamVO.setStringValue("@period", changeIssueFormat(ticket.getType(), ticket.getTermNo()));
		inparamVO.setStringValue("@hmid", ticket.getOtherOrderID());
		// inparamVO.setStringValue("@hmid","1394873743639");
		ParamVO outParamVo = null;
		try
		{
			outParamVo = httpXGRequest(inparamVO);
			if (outParamVo == null)
			{
				logger.info("新冠检票网络异常或签名检验未通过！");
				return;
			}
			String errcode = outParamVo.getStringValue("@errcode");
			String errdesc = outParamVo.getStringValue("@errdesc");
			if ("0000".equals(errcode))
			{
				ticket.setStatus(TicketStatus.已出票);
			}
			else if ("3200".equals(errcode))
			{
				logger.info("新冠--订单不存在");
				ticket.setStatus(TicketStatus.出票中);
			}
			else if ("3201".equals(errcode))
			{
				logger.info("新冠--未处理");
				ticket.setStatus(TicketStatus.出票中);
			}
			else if ("3202".equals(errcode))
			{
				ticket.setStatus(TicketStatus.出票失败);
				ticket.setOtherMsg(errdesc);
			}

		}
		catch (Exception e)
		{
			ticket.setStatus(TicketStatus.出票中);
			logger.info("=======检票接口请求参数  ========"
					+ XXmlUtil.xml2String(inparamVO.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
			logger.info("=======检票接口返回结果========"
					+ XXmlUtil.xml2String(outParamVo.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
			logger.warn("票编号：" + ticket.getId() + "的票在检票中出现异常.");
			SystemWarningNotify.addWarningDescription("大赢家检票异常：" + e);
		}
	}

	/**
	 * 新冠批量检票
	 */
	@Override
	public void checkTicket(List<Ticket> tickets)
	{
		logger.info("check tickets with size " + tickets.size());
		if (tickets.size() < 1)
		{
			return;
		}

		for (int i = 0; i < tickets.size(); i++)
		{
			Ticket ticket = tickets.get(i);
			checkTicket(ticket);
		}
	}

	/**
	 * 新冠查询竞彩玩法出票成功的票的赔率（没有竞彩）
	 */
	@Override
	protected void checkTicketSP(Ticket ticket)
	{
	}

	public String getJiangjin(Ticket ticket)
	{

		return null;
	}

	public void getOpenResult(LotteryTerm term)
	{
		ParamVO inparamVO = new ParamVO("Req");
		inparamVO.setStringValue("@funcid", "8301");
		inparamVO.setStringValue("@agent", wAgent);
		inparamVO.setStringValue("@memo", "text");
		ParamVO lottery = inparamVO.addXmlNode("lottery");
		lottery.setStringValue("@lottid", typeToXGType(term.getType()));
		lottery.setStringValue("@perdid", changeIssueFormat(term.getType(), term.getTermNo()));
		ParamVO outParamVo = null;
		try
		{
			outParamVo = httpXGRequest(inparamVO);
			if (outParamVo == null)
			{
				logger.info("新冠查询开奖号码网络异常或签名检验未通过！");
				return;
			}
			String errcode = outParamVo.getStringValue("@errcode");
			if ("0000".equals(errcode))
			{
				String codes = outParamVo.getXmlNode("lottery").getStringValue("@codes");
				System.out.println("新冠获取" + term.getType() + "第" + term.getTermNo() + "期的结果为：" + codes);
				term.setResult(codes);
			}
			else
			{

				throw new Exception("新冠获取" + term.getType() + "第" + term.getTermNo() + "期获取开奖结果数据失败,返回失败结果：" + errcode);

			}
		}
		catch (Exception e)
		{

			logger.info("==============获取结果失败===========" + e);

			if (term.getType().equals(LotteryType.广西快3))
			{
				Calendar time = (Calendar) term.getOpenPrizeTime().clone();
				Calendar time2 = (Calendar) term.getOpenPrizeTime().clone();
				time.add(Calendar.MINUTE, 4);
				time2.add(Calendar.MINUTE, 5);
				if ((time.compareTo(Calendar.getInstance()) < 0) && (time2.compareTo(Calendar.getInstance()) > 0))
				{

					logger.warn("获取新冠" + term.getType() + "第" + term.getTermNo() + "结果连续失败，无法获取结果，获取结果结束");
					String str = "【一彩票网】第" + term.getTermNo() + "期新冠  " + term.getType() + "自动开奖失败，没有获取到结果！";
					List<AdminMobile> adminMobiles = adminMobileService.getAllAdminMobile();
					for (AdminMobile adminMobile : adminMobiles)
					{
						smsLogService.saveSmsLog(adminMobile.getMobile(), str, null, SmsLogType.WARN);
						// messageTaskExcutor.addNotifySM(adminMobile.getMobile(),str);
					}
				}
			}
			else
			{
				// 重复抓取 规则 开奖时间 下期结束时间-1秒
				Long s = lotteryHandleFactory.getLotteryHandleFactory(term.getType()).getNextTerm(term)
						.getStopSaleTime().getTimeInMillis();
				s = s - 60000l;
				if (System.currentTimeMillis() - s >= 0)
				{
					logger.info("=======接口返回结果========"
							+ XXmlUtil.xml2String(outParamVo.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
					logger.warn(term + "期获取新冠" + term.getType() + "开奖结果数据异常.==>" + e.getMessage());
					SystemWarningNotify.addWarningDescription("新冠获取开奖号码异常：" + e);
					return;
				}
				try
				{
					Thread.sleep(60000);
				}
				catch (InterruptedException e1)
				{
				}
				getOpenResult(term);
			}

		}
	}

	public List<winTicketDis> getWinTicketByTerm(LotteryTerm term)
	{
		logger.info("===========新冠获取派奖数据============");
		ParamVO inparamVO = new ParamVO("Req");
		inparamVO.setStringValue("@funcid", "8887");
		inparamVO.setStringValue("@agent", wAgent);
		inparamVO.setStringValue("@type", "p");
		inparamVO.setStringValue("@lottid", typeToXGType(term.getType()));
		inparamVO.setStringValue("@perdid", changeIssueFormat(term.getType(), term.getTermNo()));
		inparamVO.setIntValue("@pagesize", 1000);
		ParamVO outParamVo = null;
		try
		{
			outParamVo = httpXGRequest(inparamVO);
			if (outParamVo == null)
			{
				logger.info("新冠查询开奖号码网络异常或签名检验未通过！");
				return null;
			}
			String errcode = outParamVo.getStringValue("@errcode");
			if ("0000".equals(errcode))
			{
				List<ParamVO> param = outParamVo.getXmlNodeList("q");
				List<winTicketDis> list = new ArrayList<winTicketDis>();
				if (param != null && param.size() != 0)
				{

					for (ParamVO p : param)
					{
						list.add(new winTicketDis(p.getStringValue("@applyid"), new BigDecimal(p
								.getStringValue("@bmoney")), new BigDecimal(p.getStringValue("@bmoney"))));
						System.out.println("中奖id 为：" + p.getStringValue("@applyid"));
					}

				}
				return list;
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public SendTicketPlat getTicketPlat()
	{
		return SendTicketPlat.新冠;
	}

	// 值转化
	private String typeToXGType(LotteryType type)
	{
		String xgType = "";
		if (type.equals(LotteryType.双色球))
		{
			xgType = "01";
		}
		else if (type.equals(LotteryType.福彩3d))
		{
			xgType = "05";
		}
		else if (type.equals(LotteryType.七乐彩))
		{
			xgType = "07";
		}
		else if (type.equals(LotteryType.广西快3))
		{
			xgType = "18";
		}
		return xgType;
	}

	// 值转化
	private String typeToPlayType(LotteryType type, PlayType playType)
	{
		String xgType = "";
		if (type.equals(LotteryType.双色球) || type.equals(LotteryType.七乐彩))
		{
			if (playType.equals(PlayType.ds))
			{
				xgType = "1:1";
			}
			else if (playType.equals(PlayType.fs))
			{
				xgType = "1:2";
			}
			else if (playType.equals(PlayType.dt))
			{
				xgType = "1:5";
			}
		}
		else if (type.equals(LotteryType.福彩3d))
		{
			if (playType.equals(PlayType.ds) || playType.equals(PlayType.zs_ds) || playType.equals(PlayType.zl_ds))
			{
				xgType = "1:1";
			}
			else if (playType.equals(PlayType.fs) || playType.equals(PlayType.zs_fs) || playType.equals(PlayType.zl_fs))
			{
				xgType = "1:2";
			}
			else if (playType.equals(PlayType.zs_bh) || playType.equals(PlayType.zl_bh))
			{
				xgType = "1:3";
			}
			else if (playType.equals(PlayType.zx_hz) || playType.equals(PlayType.zs_hz)
					|| playType.equals(PlayType.zl_hz))
			{
				xgType = "1:4";
			}
		}
		else if (type.equals(LotteryType.广西快3))
		{
			if (playType.equals(PlayType.hz))
			{
				xgType = "1:1";
			}
			else if (playType.equals(PlayType.sth_ds))
			{
				xgType = "2:1";
			}
			else if (playType.equals(PlayType.sth_tx))
			{
				xgType = "2:3";
			}
			else if (playType.equals(PlayType.eth_ds))
			{
				xgType = "3:1";
			}
			else if (playType.equals(PlayType.eth_fs))
			{
				xgType = "3:3";
			}
			else if (playType.equals(PlayType.sbt_ds))
			{
				xgType = "4:1";
			}
			else if (playType.equals(PlayType.ebt_ds))
			{
				xgType = "5:1";
			}
			else if (playType.equals(PlayType.slh_tx))
			{
				xgType = "6:3";
			}
		}
		return xgType;
	}

	@Override
	protected List<OrderQueue> getOrderQueue() throws Exception
	{
		// TODO Auto-generated method stub
		List<OrderQueue> allList = orderQueueService.getAllOrderQueueListByPlat(2);
		return allList;
	}

	@Override
	public void putOrderToQueue(Order order)
	{
		OrderQueue orderQueue = new OrderQueue();
		orderQueue.setOrderId(order.getId());
		orderQueue.setStatus(0);
		orderQueue.setSendTicketPlat(2);
		orderQueueService.save(orderQueue);
	}

	public void putOrderToOtherQueue(Order order, Integer code)
	{
		OrderQueue orderQueue = new OrderQueue();
		orderQueue.setOrderId(order.getId());
		orderQueue.setStatus(0);
		orderQueue.setSendTicketPlat(code);
		orderQueueService.save(orderQueue);
	}

	@Override
	protected void deleteOrderQueue(List<OrderQueue> allList) throws Exception
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < allList.size(); i++)
		{
			OrderQueue orderQueue = allList.get(i);
			Order order = orderService.findById(orderQueue.getOrderId());
			/*
			 * int typeNum = LotteryTypeUtil.changeLotteryTypeToNum(order.getTerm().getType()); int nCode =
			 * orderQueue.getSendTicketPlat(); List<Supplier> sl = supplierService.getAllActiveSupplierList(typeNum); if
			 * (sl.size() > 0) { Supplier supplierActive = sl.get(0); if (nCode != supplierActive.getCode()) {
			 * orderQueue.setStatus(1); orderQueueService.save(orderQueue); // changeStatus(order.getId(),1);
			 * putOrderToOtherQueue(order, supplierActive.getCode()); continue; } }
			 */
			take(order);
			orderQueue.setStatus(1);
			orderQueueService.save(orderQueue);
		}
		orderQueueService.deleteAllStatus1(2);
	}

	public static byte[] decompressBytes(byte input[])
	{
		int cachesize = 1024;
		Inflater decompresser = new Inflater();
		byte output[] = new byte[0];
		decompresser.reset();
		decompresser.setInput(input);
		ByteArrayOutputStream o = new ByteArrayOutputStream(input.length);
		try
		{
			byte[] buf = new byte[cachesize];
			int got;
			while (!decompresser.finished())
			{
				got = decompresser.inflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				o.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return output;
	}

	public ParamVO httpXGRequest(ParamVO inparamVO)
	{
		ParamVO returnPV = null;
		String outxmldata = XXmlUtil.xml2String(inparamVO.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim();
		logger.info("向新冠发送请求内容：" + outxmldata);
		String _outpasswds = createkey.createDesKey();
		String outpasswds = Base64.encodeS(rsacryptor.encryptE(_outpasswds.getBytes()));

		outpasswds = outpasswds.replaceAll("\\+", "%2B");

		CryptorXDES desEx = new CryptorXDES();
		desEx.setkey(_outpasswds.substring(0, 8));

		byte[] src = outxmldata.getBytes();
		byte[] zsrc = CompressBytes.compressBytes(src);

		String outmessage = new String(Base64.encode(desEx.encrypt(zsrc)));

		CryptorHMAC hmacEx = new CryptorHMAC();

		String outcheckor = Base64.encodeS(hmacEx.digestXMAC(shxgKey, outmessage + _outpasswds.substring(0, 8)));
		outmessage = outmessage.replaceAll("\\+", "%2B");
		outcheckor = outcheckor.replaceAll("\\+", "%2B");
		String shxgUrl = Configuration.getInstance().getValue("shxg.url");
		if (shxgUrl != null)
		{
			try
			{
				shxgUrl = shxgUrl + "?passwds=" + outpasswds + "&message=" + outmessage + "&checkor=" + outcheckor;
				logger.info("向新冠发送请求信息：" + shxgUrl);
				String result = NetWorkUtil.sendRequest(shxgUrl, null);
				logger.info("新冠响应信息：" + result);
				if (!StringUtils.isEmpty(result))
				{
					ParamVO invo = new ParamVO(XXmlUtil.string2Xml(result));
					String inpasswds = invo.getStringValue("@passwds");
					String incheckor = invo.getStringValue("@checkor");
					String inmessage = invo.getXmlRoot().getValue();
					if ((inpasswds.length() >= 1) && (incheckor.length() >= 1) && (inmessage.length() >= 1))
					{
						String _inpasswds = new String(rsacryptor.decryptE(Base64.decode(inpasswds)));
						String recvKey = _inpasswds.substring(0, 8);
						CryptorHMAC hmac = new CryptorHMAC();
						String newCheckor = new String(Base64.encode(hmac.digestXMAC(shxgKey, inmessage + recvKey)));
						CryptorXDES des = new CryptorXDES();
						des.setkey(recvKey);
						byte[] src1 = des.decrypt(Base64.decode(inmessage));
						byte[] zsrc1 = decompressBytes(src1);
						String inxmldata = new String(zsrc1).trim();
						logger.info("新冠响应内容：" + inxmldata);
						if (incheckor.equals(newCheckor))
						{
							// 正常
							invo = new ParamVO(XXmlUtil.string2Xml(inxmldata));
							returnPV = invo;
						}
						else
						{
							System.out.println("消息检查错误.");
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return returnPV;
	}

	private String changeIssueFormat(LotteryType type, String termNo)
	{
		if (type.equals(LotteryType.广西快3))
		{
			return termNo.substring(2);
		}
		else
		{
			return termNo;
		}
	}

	@Override
	protected void queryTerm(LotteryType type)
	{
		logger.info("queryTerm");
		ParamVO outParamVo = null;
		ParamVO inparamVO = new ParamVO("Req");
		inparamVO.setStringValue("@funcid", "8201");
		inparamVO.setStringValue("@agent", wAgent);
		inparamVO.setStringValue("@memo", "text");

		ParamVO lot = inparamVO.addXmlNode("lottery ");
		lot.addStringValue("@lottid", typeToXGType(type));
		try
		{
			outParamVo = httpXGRequest(inparamVO);
			if (outParamVo == null)
			{
				logger.info("新冠奖期查询网络异常或签名检验未通过！");
				return;
			}
			String errcode = outParamVo.getStringValue("@errcode");
			String errdesc = outParamVo.getStringValue("@errdesc");
			if ("0000".equals(errcode))
			{
				List<ParamVO> termList = outParamVo.getXmlNode("lottery").getXmlNode("periods").getXmlNodeList(
						"period ");
				for (ParamVO paramVO : termList)
				{
					try
					{
						PrintLotteryTerm printTerm = new PrintLotteryTerm();
						printTerm.setType(type);
						printTerm.setTermNo(paramVO.getStringValue("@id"));
						printTerm.setOutPoint(SendTicketPlat.新冠);
						printTerm.setTermStatus(TermStatus.销售中);
						printTerm.setStartSaleTime(DateUtil.parse(paramVO.getStringValue("@starttime"), "yyyyMMddHHmmss"));
						printTerm.setStopSaleTime(DateUtil.parse(paramVO.getStringValue("@endtime"), "yyyyMMddHHmmss"));
						printTerm.setOpenPrizeTime(DateUtil.parse(paramVO.getStringValue("@awardtime"), "yyyyMMddHHmmss"));
						printTermService.saveOrUpdate(printTerm);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						logger.error("保存出票终端奖期"+type+"第"+paramVO.getStringValue("@id")+"时异常",e);
					}
				}

			}
			else
			{

				SystemWarningNotify.addWarningDescription("玩法：" + type + " 在新冠查询奖期失败,返回失败结果：" + errcode + " , 原因:"
						+ errdesc);

			}

		}
		catch (Exception e)
		{

			logger.info("=======查询奖期接口请求参数  ========"
					+ XXmlUtil.xml2String(inparamVO.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
			logger.info("=======查询奖期接口返回结果========"
					+ XXmlUtil.xml2String(outParamVo.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
			logger.warn("玩法：" + type + "的查询奖期出现异常.");
			SystemWarningNotify.addWarningDescription("上海新冠查询奖期异常：" + e);
		}

	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
		/*
		 ShxgTicketTreatmentWork stt = new ShxgTicketTreatmentWork();
		 Ticket ticket = new Ticket(); ticket.setId(System.currentTimeMillis()); ticket.setType(LotteryType.双色球);
		 ticket.setPlayType(PlayType.ds); ticket.setTermNo("2014028"); ticket.setContent("01,02,03,04,05,06|01");
		 ticket.setCount(1); ticket.setMoney(new BigDecimal(2)); ticket.setMultiple(1); //stt.deliveTicket(ticket);
		 stt.checkTicket(ticket); System.out.println(ticket.getStatus());
		 stt.queryTerm(LotteryType.广西快3);
		*/
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 21, 0, 0);
		System.out.println(calendar.before(Calendar.getInstance()));

	}

}
