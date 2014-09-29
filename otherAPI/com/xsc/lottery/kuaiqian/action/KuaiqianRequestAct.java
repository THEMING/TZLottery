package com.xsc.lottery.kuaiqian.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.RechargeGift;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.kuaiqian.util.MD5Util;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.service.business.SoftwareRegisterService;
import com.xsc.lottery.task.email.Email369TaskExcutor;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

/** 快钱 */

@SuppressWarnings( { "serial", "unchecked" })
@Scope("prototype")
@Controller("Kuaiqian.RequestAct")
public class KuaiqianRequestAct extends LotteryClientBaseAction
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String gateway = Configuration.getInstance().getValue("kuaiqian.url");
	private String key = Configuration.getInstance().getValue("kuaiqian.key");
	private String merchantAcctId = Configuration.getInstance().getValue("kuaiqian.merchantAcctId");
	
	@Autowired
    private PaymentRequestService paymentRequestService;
    
    @Autowired
    public CustomerService customerService;
    
    @Autowired
    private SoftwareRegisterService softwareRegisterService;
    
    @Autowired
    private Email369TaskExcutor email369TaskExcutor;
    
    private Customer customer;
    
    private BigDecimal deposit_money;
    
    private String actionmsg;
    /**
     * 支付宝网银充值
     */
    public String index() throws Exception
    {
        customer = getCurCustomer();
        if (deposit_money.intValue() < 2) {
            actionmsg = "充值金额不能小于2元";
            return INPUT;
        }
        else {
            String payType = "00"; 
            PaymentRequest payreuqest = new PaymentRequest(MathUtil
                    .getSerialNumber(20), customer, deposit_money, Bank.快钱,
                    MoneyChannel.快钱, BigDecimal.ZERO);
            paymentRequestService.save(payreuqest);
            getResponse().sendRedirect(getKuaiqianRequestURL(
            						payreuqest.getSerialNumber(),
            						payType));
            return null;
        }
    }
    
    public String kuaiqianResponse() throws Exception
    {
    	logger.info("enter kuaiqian response!");
    	
    	String version = getRequestParam("version");
		String language = getRequestParam("language");
		String signType = getRequestParam("signType");
		String payType = getRequestParam("payType");
    	String bankId = getRequestParam("bankId"); //获取银行代码
    	String orderId = getRequestParam("orderId"); //获取商户订单号
    	String orderTime = getRequestParam("orderTime"); //获取订单提交时间 如：20080101010101
    	String orderAmount = getRequestParam("orderAmount");
    	String dealId = getRequestParam("dealId"); //获取快钱交易号
    	String bankDealId = getRequestParam("bankDealId"); //获取银行交易号
    	String dealTime = getRequestParam("dealTime"); //获取在快钱交易时间
    	String payAmount = getRequestParam("payAmount"); //获取实际支付金额
    	String fee = getRequestParam("fee"); //获取交易手续费
    	String ext1 = getRequestParam("ext1");
    	String ext2 = getRequestParam("ext2");

    	String payResult = getRequestParam("payResult"); //获取处理结果, 10代表成功, 11代表 失败
    	String errCode = getRequestParam("errCode"); //获取错误代码

    	//获取加密签名串
    	String signMsg = getRequestParam("signMsg");
    	
    	String merchantSignMsgVal="";
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"merchantAcctId",merchantAcctId);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"version",version);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"language",language);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"signType",signType);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"payType",payType);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankId",bankId);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderId",orderId);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderTime",orderTime);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderAmount",orderAmount);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealId",dealId);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankDealId",bankDealId);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealTime",dealTime);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"payAmount",payAmount);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"fee",fee);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext1",ext1);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext2",ext2);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"payResult",payResult);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"errCode",errCode);
    	merchantSignMsgVal=appendParam(merchantSignMsgVal,"key",key);
    	
    	logger.info("merchantSignMsg = [" + merchantSignMsgVal + "]");
    	
    	String merchantSignMsg = MD5Util.md5Hex(merchantSignMsgVal.getBytes("gb2312")).toUpperCase();
    	
    	PrintWriter out = getResponse().getWriter();
    	
    	if(signMsg.toUpperCase().equals(merchantSignMsg.toUpperCase())) {
    		if(payResult.equals("10")) {
    			logger.info("kuaiqian return '10', success!");
    			
    			try {
                    PaymentRequest payreuqest = paymentRequestService
                        .findByProperty("serialNumber", orderId).get(0);
                    customer = payreuqest.getCustomer();
                    BigDecimal order_amount = new BigDecimal(orderAmount);
                    order_amount = order_amount.divide(BigDecimal.valueOf(100));
                    if (!payreuqest.isFinish()) {
                    	logger.info("add money " + order_amount);
                        WalletLog walletLog = new WalletLog(BusinessType.收入,
                        		order_amount, BigDecimal.ZERO,
                                BigDecimal.ZERO, BigDecimal.ZERO, "用户快钱充值",
                                WalletLogType.账户充值, "");
                        customerService.addWalletLog(payreuqest.getCustomer()
                                .getWallet().getId(), walletLog);
                        payreuqest.setFinish(true);
                        paymentRequestService.update(payreuqest);
                        logger.info("kuaiqianResponse -- deal success!");
                        
                        //FIXME 
                        email369TaskExcutor.addNotifyPayment(payreuqest);
                        
                        // ------------------- 添加礼单（送软件注册码）
                        float giftThreshold = Float.parseFloat(Configuration.getInstance().getValue("giftThreshold"));
                        if(order_amount.compareTo(BigDecimal.valueOf(giftThreshold)) >= 0) {
                        	logger.info("add gift");
                        	RechargeGift gift = new RechargeGift();
    	                    gift.setCustomer(customer);
    	                    gift.setPrize(true);
    	                    gift.setPaymentrequest(payreuqest);
    	                    gift.setReceive(false);
    	                    try {
    	                    	softwareRegisterService.save(gift);
    	                    }
    	                    catch(Exception e) {
    	                    	logger.info(e.getMessage());
    	                    }
                        }
                        out.write("success");
                    }
                }
                catch (Exception e) {
                    actionmsg = "error request!";
                    return ERROR;
                }
    		}
    		else {
    			logger.info("kuaiqian return 01, failed!");
    		}
    	}
    	else {
    		logger.info("kuaiqian return sign error!");
    	}
    	
    	out.write("<result>1</result>");
    	out.write("<redirecturl>" + "http://" + getRequest().getHeader("Host") + 
				"/customer/" + "</redirecturl>");
    	return null;
    }
    
    private String getRequestParam(String paramStr)
    {
    	String returnStr = getRequest().getParameter(paramStr);
    	if(null == returnStr) {
    		return "";
    	}
    	return returnStr.trim();
    }
    
    private String getKuaiqianRequestURL(String orderID, String payType) throws UnsupportedEncodingException
    {
		String bgUrl = "http://" + getRequest().getHeader("Host") + 
					"/customer/kuaiqianre369Response.htm";

    	String orderTime = DateUtil.toTimeStamp(Calendar.getInstance());
    	int orderAmount = deposit_money.multiply(BigDecimal.valueOf(100)).intValue();
    	
		StringBuilder sendParam = new StringBuilder();
		sendParam.append("inputCharset=1"); //1代表UTF-8; 2代表GBK; 3代表gb2312
		sendParam.append("&bgUrl=" + bgUrl); //回转地址
		sendParam.append("&version=v2.0"); //固定值
		sendParam.append("&language=1"); //1代表中文；2代表英文
		//sendParam.append("&payerName=");
		//sendParam.append("&payerContactType="); //1代表Email,当前固定为1
		//sendParam.append("&payerContact="); //email地址或手机号
		sendParam.append("&orderId=" + orderID); //订单号
		sendParam.append("&orderAmount=" + orderAmount); //以分为单位，必须是整型数字, 比方2，代表0.02元
		sendParam.append("&orderTime=" + orderTime);
		//sendParam.append("&productName="); //商品名称
		//sendParam.append("&productNum="); //商品数量
		//sendParam.append("&productId="); //商品代码
		//sendParam.append("&productDesc="); //商品描述
		//sendParam.append("&ext1="); //扩展字段1, 在支付结束后原样返回给商户
		//sendParam.append("&ext2="); //扩展字段2, 在支付结束后原样返回给商户
		sendParam.append("&payType=" + payType); //支付方式
		sendParam.append("&redoFlag=0"); //同一订单禁止重复提交标志,1代表同一订单号只允许提交1次；0表示同一订单号在没有支付成功的前提下可重复提交多次。
		//sendParam.append("&pid="); //快钱的合作伙伴的账户号, 如未和快钱签订代理合作协议，不需要填写本参数
		
		// 生成加密签名串
		// 请务必按照如下顺序和规则组成加密串！
		String signMsgVal = "";
		signMsgVal = appendParam(signMsgVal, "inputCharset", "1");
		signMsgVal = appendParam(signMsgVal, "bgUrl", bgUrl);
		signMsgVal = appendParam(signMsgVal, "version", "v2.0");
		signMsgVal = appendParam(signMsgVal, "language", "1");
		signMsgVal = appendParam(signMsgVal, "signType", "1");
		signMsgVal = appendParam(signMsgVal, "merchantAcctId", merchantAcctId);
		//signMsgVal = appendParam(signMsgVal, "payerName", "");
		//signMsgVal = appendParam(signMsgVal, "payerContactType", "");
		//signMsgVal = appendParam(signMsgVal, "payerContact", "");
		signMsgVal = appendParam(signMsgVal, "orderId", orderID);
		signMsgVal = appendParam(signMsgVal, "orderAmount", "" + orderAmount);
		signMsgVal = appendParam(signMsgVal, "orderTime", orderTime);
		//signMsgVal = appendParam(signMsgVal, "productName", "");
		//signMsgVal = appendParam(signMsgVal, "productNum", "");
		//signMsgVal = appendParam(signMsgVal, "productId", "");
		//signMsgVal = appendParam(signMsgVal, "productDesc", "");
		//signMsgVal = appendParam(signMsgVal, "ext1", "");
		//signMsgVal = appendParam(signMsgVal, "ext2", "");
		signMsgVal = appendParam(signMsgVal, "payType", payType);
		signMsgVal = appendParam(signMsgVal, "redoFlag", "0");
		//signMsgVal = appendParam(signMsgVal, "pid", "");
		signMsgVal = appendParam(signMsgVal, "key", key);
		String signMsg = MD5Util.md5Hex(signMsgVal.getBytes("gb2312")).toUpperCase();
		
		sendParam.append("&merchantAcctId=" + merchantAcctId);
		sendParam.append("&signType=1"); //1代表MD5签名,当前固定为1
		sendParam.append("&signMsg=" + signMsg);

		//System.out.println(gateway + "?" + sendParam.toString());
		return gateway + "?" + sendParam.toString();
    }
    
    private String appendParam(String returnStr, String paramId, String paramValue)
	{
		if (!returnStr.equals("")) {
			if (!paramValue.equals("")) {
				returnStr = returnStr + "&" + paramId + "=" + paramValue;
			}
		}
		else {
			if (!paramValue.equals("")) {
				returnStr = paramId + "=" + paramValue;
			}
		}
		return returnStr;
	}
    
    public BigDecimal getDeposit_money()
    {
        return deposit_money;
    }

    public void setDeposit_money(BigDecimal depositMoney)
    {
        deposit_money = depositMoney;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }

    public String getActionmsg()
    {
        return actionmsg;
    }
}
