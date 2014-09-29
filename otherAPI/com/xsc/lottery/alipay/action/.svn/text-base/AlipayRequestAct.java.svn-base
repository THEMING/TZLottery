package com.xsc.lottery.alipay.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.alipay.util.CheckURL;
import com.xsc.lottery.alipay.util.Payment;
import com.xsc.lottery.alipay.util.SignatureHelper;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.RechargeGift;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PaymentRequestService;
import com.xsc.lottery.service.business.SoftwareRegisterService;
import com.xsc.lottery.task.email.Email369TaskExcutor;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

/** 支付宝 */
@SuppressWarnings( { "serial", "unchecked" })
@Scope("prototype")
@Controller("Alipay.RequestAct")
public class AlipayRequestAct extends LotteryClientBaseAction
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());

    private BigDecimal deposit_money;
    private String notify_id;
    
    @Autowired
    private PaymentRequestService paymentRequestService;
    
    @Autowired
    public CustomerService customerService;
    
    @Autowired
    private SoftwareRegisterService softwareRegisterService;
    
    @Autowired
    private Email369TaskExcutor email369TaskExcutor;
    
    private Customer customer;

    private String actionmsg;

    private String paygateway = Configuration.getInstance().getValue("alipay.url");
    private String input_charset = Configuration.getInstance().getValue("alipay.CharSet");
    // 支付宝合作伙伴id (账户内提取)
    private String partner = Configuration.getInstance().getValue("alipay.partnerID"); 
    // 支付宝安全校验码(账户内提取)
    private String key = Configuration.getInstance().getValue("alipay.key");
    // 卖家支付宝帐户,例如：gwl25@126.com
    private String seller_email = Configuration.getInstance().getValue("alipay.sellerEmail");

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
            String paymethod = "directPay"; // 赋值:bankPay(网银);cartoon(卡通);
            // directPay(余额)
            PaymentRequest payreuqest = new PaymentRequest(MathUtil
                    .getSerialNumber(20), customer, deposit_money, Bank.支付宝,
                    MoneyChannel.支付宝, BigDecimal.ZERO);
            paymentRequestService.save(payreuqest);
            getResponse().sendRedirect(getAlipayRequestURL(payreuqest.getSerialNumber(),
                                    paymethod));
            return null;
        }
    }

    public String alipayResponse() throws Exception
    {
    	logger.info("---------alipayResponse-----------");
        // **********************************************************************************
        StringBuilder alipayNotifyURL = new StringBuilder(Configuration
                .getInstance().getValue("alipay.notify_query"));
        alipayNotifyURL.append("?");
        alipayNotifyURL.append("partner=").append(partner).append("&").append(
                "notify_id=").append(notify_id);
        // **********************************************************************************
        // 获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
        String isEffective = CheckURL.check(alipayNotifyURL.toString());
        isEffective = "true";
        Map<String, String> newParams = new HashMap<String, String>();
        Map<String, String[]> requestparams = getRequest().getParameterMap();
        for (Map.Entry<String, String[]> mapEntry : requestparams.entrySet()) {
            for (String value : mapEntry.getValue()) {
                String valueStr = "";
                valueStr += value.equals(mapEntry.getValue()[mapEntry
                        .getValue().length - 1]) ? value : value + ",";
                newParams.put(mapEntry.getKey(), valueStr);
            }
        }
        
        PrintWriter out = getResponse().getWriter();
        String mysign = SignatureHelper.sign(newParams, key);
        
        //logger.info("check URL");
        if (mysign.equals(getRequest().getParameter("sign"))
                && isEffective.equals("true")) {
            getResponse().setCharacterEncoding("utf-8");
            if (getRequest().getParameter("trade_status").equals(
                    "WAIT_BUYER_PAY")) {
                out.write("success");
                out.close();
                return null;
            }
            else if (getRequest().getParameter("trade_status").equals(
                    "TRADE_FINISHED")
                    || getRequest().getParameter("trade_status").equals(
                            "TRADE_SUCCESS")) {
                String orderID = getRequest().getParameter("out_trade_no");
                String get_total_fee = getRequest().getParameter("total_fee");
                
                try {
                    PaymentRequest payreuqest = paymentRequestService
                        .findByProperty("serialNumber", orderID).get(0);
                    customer = payreuqest.getCustomer();
                    if (!payreuqest.isFinish()) {
                    	logger.info("add money " + get_total_fee);
                        WalletLog walletLog = new WalletLog(BusinessType.收入,
                                new BigDecimal(get_total_fee), BigDecimal.ZERO,
                                BigDecimal.ZERO, BigDecimal.ZERO, "用户支付宝充值",
                                WalletLogType.账户充值, "");
                        customerService.addWalletLog(payreuqest.getCustomer()
                                .getWallet().getId(), walletLog);
                        payreuqest.setFinish(true);
                        paymentRequestService.update(payreuqest);
                        logger.info("alipayResponse -- write success!");
                        
                        //FIXME 
                        email369TaskExcutor.addNotifyPayment(payreuqest);
                        
                        // ------------------- 添加礼单（送软件注册码）
                        float giftThreshold = Float.parseFloat(Configuration.getInstance().getValue("giftThreshold"));
                        if(Float.parseFloat(get_total_fee) >= giftThreshold) {
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
    	                    	System.out.println(e.getMessage());
    	                    }
                        }
                        
                        out.write("success");
                    }
                }
                catch (Exception e) {
                    actionmsg = "error request!";
                    return ERROR;
                }
                return SUCCESS;
            }
            else {
                actionmsg = "error request!";
                customer = getCurCustomer();
                return ERROR;
            }
        }
        return SUCCESS;
    }

    /** 异步请求 */
    public String alipaySsynResponse() throws Exception
    {
    	logger.info("--------alipaySsynResponse notify------------");
        // **********************************************************************************
        StringBuilder alipayNotifyURL = new StringBuilder(Configuration
                .getInstance().getValue("alipay.notify_query"));
        alipayNotifyURL.append("?");
        alipayNotifyURL.append("partner=").append(partner).append("notify_id=")
                .append(notify_id);
        // **********************************************************************************
        // 获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
        String isEffective = CheckURL.check(alipayNotifyURL.toString());

        Map<String, String> newParams = new HashMap<String, String>();
        Map<String, String[]> requestparams = getRequest().getParameterMap();
        for (Map.Entry<String, String[]> mapEntry : requestparams.entrySet()) {
            for (String value : mapEntry.getValue()) {
                String valueStr = "";
                valueStr += value.equals(mapEntry.getValue()[mapEntry
                        .getValue().length - 1]) ? value : value + ",";
                newParams.put(mapEntry.getKey(), valueStr);
            }
        }
        PrintWriter out = getResponse().getWriter();
        String mysign = SignatureHelper.sign(newParams, key);
        if (mysign.equals(getRequest().getParameter("sign"))
                && isEffective.equals("true")) {
            if (getRequest().getParameter("trade_status").equals(
                    "WAIT_BUYER_PAY")) {
                out.write("success");
                out.close();
            }
            else if (getRequest().getParameter("trade_status").equals(
                    "TRADE_FINISHED")
                    || getRequest().getParameter("trade_status").equals(
                            "TRADE_SUCCESS")) {
                String orderID = getRequest().getParameter("out_trade_no");
                String get_total_fee = getRequest().getParameter("total_fee");
                PaymentRequest payreuqest = paymentRequestService
                        .findByProperty("serialNumber", orderID).get(0);
                customer = payreuqest.getCustomer();
                if (!payreuqest.isFinish()) {
                    payreuqest.setFinish(true);
                    WalletLog walletLog = new WalletLog(BusinessType.收入,
                            new BigDecimal(get_total_fee), BigDecimal.ZERO,
                            BigDecimal.ZERO, BigDecimal.ZERO, "用户支付宝充值",
                            WalletLogType.账户充值, "");
                    customerService.addWalletLog(payreuqest.getCustomer()
                            .getWallet().getId(), walletLog);
                    paymentRequestService.update(payreuqest);
                    
                    //FIXME 
                    email369TaskExcutor.addNotifyPayment(payreuqest);
                    
                    // ------------------- 添加礼单（送软件注册码）
                    float giftThreshold = Float.parseFloat(Configuration.getInstance().getValue("giftThreshold"));
                    if(Float.parseFloat(get_total_fee) >= giftThreshold) {
                    	RechargeGift gift = new RechargeGift();
	                    gift.setCustomer(customer);
	                    gift.setPrize(true);
	                    gift.setPaymentrequest(payreuqest);
	                    gift.setReceive(false);
	                    try {
	                    	softwareRegisterService.save(gift);
	                    }
	                    catch(Exception e) {
	                    	System.out.println(e.getMessage());
	                    }
                    }
                    
                    out.write("success");
                    out.close();
                }
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**  
     * ICBCB2C 中国工商银行 CMB 招商银行 CCB 中国建设银行 ABC 中国农业银行 SPDB 上海浦东发展银行
     * SPDBB2B 上海浦东发展银行(B2B) CIB 兴业银行 GDB 广东发展银行 SDB 深圳发展银行 CMBC
     * 中国民生银行 COMM 交通银行 POSTGC 邮政储蓄银行 CITIC 中信银行 CCBVISA 建行VISA VISA
     */
    private String getAlipayRequestURL(String orderID, String paymethod)
    {
        String service = "create_direct_pay_by_user"; // 快速付款交易服务（不可以修改）
        String sign_type = "MD5"; // 文件加密机制（不可以修改）
        String out_trade_no = orderID; // 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
        // partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
        String body = out_trade_no; // 商品描述，推荐格式：商品名称（订单编号：订单编号）
        String total_fee = String.valueOf(deposit_money); // String total_fee =
        // "0.01";//订单总价
        String payment_type = "1"; // 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
        String subject = "用户充值"; // 商品名称
        String show_url = Configuration.getInstance().getValue(
                "alipay.show_url"); // 根据集成的网站而定 例如：http://wow.alipay.com
        String notify_url = "http://" + getRequest().getHeader("Host")
                + "/customer/alipaySsyn369Response.htm"; // 通知接收URL(本地测试时，服务器返回无法测试)
        String return_url = "http://" + getRequest().getHeader("Host")
                + "/customer/alipayre369Response.htm"; // 支付完成后跳转返回的网址URL
        // 注意以上两个地址 要用 http://格式的完整路径
        /* 以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销 */
        String ItemUrl = Payment.CreateUrl(paygateway, service, sign_type,
                out_trade_no, input_charset, partner, key, show_url, body,
                total_fee, payment_type, seller_email, subject, notify_url,
                return_url, paymethod);
        
        return ItemUrl;
    }

    public BigDecimal getDeposit_money()
    {
        return deposit_money;
    }

    public void setDeposit_money(BigDecimal depositMoney)
    {
        deposit_money = depositMoney;
    }

    public void setNotify_id(String notify_id)
    {
        this.notify_id = notify_id;
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
