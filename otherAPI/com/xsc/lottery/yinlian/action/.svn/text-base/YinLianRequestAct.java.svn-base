package com.xsc.lottery.yinlian.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import client.IvrMerUtil;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

import domain.PreOrderTransReq;
import domain.PreOrderTransRsp;
import domain.TransNotifiReq;


/**
 * 银联语音支付
 */
@SuppressWarnings({"serial"})
@Scope("prototype")
@Controller("YinLian.RequestAct")
public class YinLianRequestAct extends LotteryClientBaseAction{

	private String nick;
	private String mobile;
	private BigDecimal money;
    private Customer customer;

    @Autowired
    public CustomerService customerService;
    @Autowired
    private PaymentRequestService paymentRequestService;
    
    //生成订单接口
    public String index() throws Exception {
    	Customer customer = new Customer();
    	customer = customerService.getCustomerOrName(nick);
    	JSONObject jsonObject = new JSONObject();
    	if(customer == null) {
    		jsonObject.put("code", "_0001");
    		jsonObject.put("message", "用户名不存在！");
    		setJsonString(jsonObject.toString());
    		System.out.println(jsonObject.toString());
    		return AJAXJSON;
    	}
    	try {
    		logger.info("用户:" + customer.getNickName() + " 发起语音充值,金额为："
    				+ money.divide(new BigDecimal(100)) + "元");
    		String[] s = IvrMerUtil.readPFX("/share/web/WEB-INF/classes/700000000000001.pfx", "000000");		
    		String serial_number = MathUtil.getSerialNumber(16);
    		PreOrderTransRsp preOrderTransRsp = new PreOrderTransRsp();
    		preOrderTransRsp = (PreOrderTransRsp)IvrMerUtil.ivrMerTool(getpreOrderTransReq(serial_number, money, mobile), s);
			if (preOrderTransRsp.getRespCode().equals("0000")) {
				PaymentRequest paymentpo = new PaymentRequest(serial_number, customer, money.divide(new BigDecimal(100)), Bank.语音支付,
	                    MoneyChannel.语音支付, BigDecimal.ZERO);
				customerService.savePaymentRequest(paymentpo);
				jsonObject.put("code", "_0000");
				jsonObject.put("message", "订单创建成功!");
				System.out.println(jsonObject.toString());
				return AJAXJSON;
			}else {
	    		logger.info("用户:" + customer.getNickName() + " 发起的语音充值失败：" + preOrderTransRsp.getRespDesc());
				jsonObject.put("code", "_0001");
				jsonObject.put("message", preOrderTransRsp.getRespDesc());
				System.out.println(jsonObject.toString());
				return AJAXJSON;
			}
		} catch (Exception e) {
    		logger.info("用户:" + customer.getNickName() + " 发起语音充值异常，异常信息为：" + e.getMessage());
    		jsonObject.put("code", "_0001");
    		jsonObject.put("message", e.getMessage());
    		setJsonString(jsonObject.toString());
    		System.out.println(jsonObject.toString());
    		return AJAXJSON;
		}
    }
    
    //通知接口
	public String tongzhi() {
		String data = "";
		try {
			InputStream inputStream = getRequest().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String tempStr = "";
			while((tempStr=reader.readLine())!=null){
				data += tempStr;
			}
			reader.close();
			inputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			//String[] s = IvrMerUtil.readPFX("D:/javawork/BoCaiLottery/Test/tcp/test/700000000000001.pfx", "000000");
			
			//SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",java.util.Locale.US);
			//String date = sf.format(new Date());
			
			//String data = "MjY3MDk5NjMzMjE4|m6kM4qPMmED/tNYDYsIbCVvG4IuqwEgnI3xiOLfrcJG2Fl3ACBlljK5X1jJRJNzyFJbvIGcF/A0qWtvoJEifTk8jf5f3n4xdMKqly15h6RB6+Xv9o2Lx/PDGKzFTtWo/85TIQkNZtGeLWJjSUW4aNrjBLL4FUgzS2II57pieaeA=|tMyGMItWyYWR8iKrXM7HRENF5Asi9MnDWMBY94ktfcxuql+NjQRo0HN/CAUhStRaPHoeJiBzxTATQlDGdIP6GTEVS7UWrdJG1GasAnF2SBvfidN5iaZcB3eBcUSag5ipbB8P1+LxMK34oq02wU9Z4j8SP0CytyXik93s8lHHSoRvoa1hcA1Xmn7qUpm8+2TFvKGR+UoUwXbiw2/bqr55tdTAxhqa6bZNCufXqKXQ8xiMqYaeeP9bNfwHg1ZQ+jdkc07slybk5R6fBHwmQJYisekAlGaxiwzpugfTRzkJh/XmwprPJBajL8CsHAne8D6d48Y77HF99qZJx8QCOGQh1grn16il0PMYC+7BCfOKFYbEAY9aCnHVBruYhF+kV/wbPLbN6Fn4zY9EM/zqBhocxuqEC/4UAHGVhi/Q5rn9yWpZFAqNnoh57bDDFKm2OtIdh3BfR0lTyjgMLkRCStzcRJnDcwhbEMYQk7kwb7ORdP7d8sjVh7vuzHqFV6l8Vw9e/Eq45JK0hqDB2hEPCxRI56YTHjSs9PhRJntEZd6xPAK7bq/tDrF5gtgXDlrahcJtrSbrriUczc/O9pG5Qi61HiKX1j+oLGMl0NLR3UzC5qzJ0y065hvzTmSF80TVF75zkfsByHFy0JZxD5Wjx6fyw3J/XLxzIsCXkHvMMm/xhwCPo8iTMmCDfTJc2dFE52KL5+Uztiu0xniBnAGqToUIUlK53fSfFpQFR8ogVFzwJYX6zJspEVIXq7rGKc962NUZcs7aBFyijgvh4eyF1TORtQ==";
			System.out.println("data = " + data);
			TransNotifiReq transNotifiReq = new TransNotifiReq();
			transNotifiReq = IvrMerUtil.ivrMerNotifi(data);
			//取出对象值
			System.out.println("CupsRespCode:::::::::" + transNotifiReq.getCupsRespCode());
			System.out.println("CupsRespCode:::::::::" + transNotifiReq.getCupsRespDesc());
			System.out.println("CupsRespCode:::::::::" + transNotifiReq.getMerchantOrderId());
			String orderid = transNotifiReq.getMerchantOrderId();
			System.out.println("orderid = " + orderid);
			PaymentRequest paymentRequest = paymentRequestService.findByProperty("serialNumber", orderid).get(0);
			System.out.println("serialNumber = " + paymentRequest.getSerialNumber());
			customer = paymentRequest.getCustomer();
			System.out.println(customer.getNickName());
			BigDecimal mon = (new BigDecimal(transNotifiReq.getMerchantOrderAmt())).divide(BigDecimal.valueOf(100));
			//money = money.divide(BigDecimal.valueOf(100));
			if (transNotifiReq.getCupsRespCode().equals("00")) {
				if (!paymentRequest.isFinish()) {
					paymentRequest.setFinish(true);
					WalletLog walletLog = new WalletLog(BusinessType.收入,
							mon, BigDecimal.ZERO, BigDecimal.ZERO,
							BigDecimal.ZERO, "语音充值", WalletLogType.账户充值,
					"");
					customerService.addWalletLog(paymentRequest.getCustomer()
							.getWallet().getId(), walletLog);
					paymentRequestService.update(paymentRequest);
					// 页面提示用户冲值成功
					logger.info("用户:" + customer.getNickName()
							+ "语音支付重定向通知冲值成功!");
				}else {
					logger.info("用户:" + customer.getNickName() + "语音充值已经成功！银联重复发送通知！");
				}
				PrintWriter out = getResponse().getWriter();
				out.write("success");
				out.close();
			}else {
				logger.info("用户:" + customer.getNickName() + "语音充值失败：" + transNotifiReq.getCupsRespDesc());
				System.out.println("支付失败：" + transNotifiReq.getCupsRespDesc());
			}
		} catch (Exception e) {
			logger.info("用户:" + customer.getNickName() + " 语音充值异常，异常信息为：" + e.getMessage());
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
    public PreOrderTransReq getpreOrderTransReq(String orderid, BigDecimal money, String mobile) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss",java.util.Locale.US);
		String date = sf.format(new Date());
    	PreOrderTransReq preOrderTransReq = new PreOrderTransReq();
    	//父类属性
    	preOrderTransReq.setApplication("MGw.Req");
		preOrderTransReq.setVersion("1.0.0");
		preOrderTransReq.setSendTime(date);
		preOrderTransReq.setSendSeqId(date);

		//子类属性
		preOrderTransReq.setMerchantId("267099633218");
		preOrderTransReq.setMerchantOrderId(orderid);
		preOrderTransReq.setMerchantOrderAmt(money.toString());
		preOrderTransReq.setMerchantOrderCurrency("156");
		preOrderTransReq.setMerchantOrderTime(date);
		preOrderTransReq.setMobileNum(mobile);
		preOrderTransReq.setGwType("03");
		preOrderTransReq.setMsgExt("02");
		preOrderTransReq.setBackUrl("caipiao2.lashou.com:8000/customer/tongzhi.htm");
    	return preOrderTransReq;
    }
    
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static void main(String[] args) {
		YinLianRequestAct act = new YinLianRequestAct();
		act.tongzhi();
	}
}
