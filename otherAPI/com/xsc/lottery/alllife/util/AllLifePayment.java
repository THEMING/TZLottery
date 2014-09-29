package com.xsc.lottery.alllife.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hnapay.gateway.client.enums.CharsetTypeEnum;
import com.hnapay.gateway.client.java.ClientSignature;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.MD5;
import com.xsc.lottery.util.MathUtil;

public class AllLifePayment
{

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getPartnerID()
    {
        return partnerID;
    }

    public void setPartnerID(String partnerID)
    {
        this.partnerID = partnerID;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getSerialID()
    {
        return serialID;
    }

    public void setSerialID(String serialID)
    {
        this.serialID = serialID;
    }

    public String getSubmitTime()
    {
        return submitTime;
    }

    public void setSubmitTime(String submitTime)
    {
        this.submitTime = submitTime;
    }

    public String getFailureTime()
    {
        return failureTime;
    }

    public void setFailureTime(String failureTime)
    {
        this.failureTime = failureTime;
    }

    public String getBuyerMarked()
    {
        return buyerMarked;
    }

    public void setBuyerMarked(String buyerMarked)
    {
        this.buyerMarked = buyerMarked;
    }

    public String getPlatformID()
    {
        return platformID;
    }

    public void setPlatformID(String platformID)
    {
        this.platformID = platformID;
    }

    public String getCustomerIP()
    {
        return customerIP;
    }

    public void setCustomerIP(String customerIP)
    {
        this.customerIP = customerIP;
    }

    public String getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPayType()
    {
        return payType;
    }

    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getBorrowingMarked()
    {
        return borrowingMarked;
    }

    public void setBorrowingMarked(String borrowingMarked)
    {
        this.borrowingMarked = borrowingMarked;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getDirectFlag()
    {
        return directFlag;
    }

    public void setDirectFlag(String directFlag)
    {
        this.directFlag = directFlag;
    }

    public String getCouponFlag()
    {
        return couponFlag;
    }

    public void setCouponFlag(String couponFlag)
    {
        this.couponFlag = couponFlag;
    }

    public String getReturnUrl()
    {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl)
    {
        this.returnUrl = returnUrl;
    }

    public String getNoticeUrl()
    {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl)
    {
        this.noticeUrl = noticeUrl;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getSignType()
    {
        return signType;
    }

    public void setSignType(String signType)
    {
        this.signType = signType;
    }

    public String getSignMsg()
    {
        return signMsg;
    }

    public void setSignMsg(String signMsg)
    {
        this.signMsg = signMsg;
    }

    public String getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public String getOrderAmount()
    {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    private String url = Configuration.getInstance().getValue("alllifePay.url");
    private String partnerID = Configuration.getInstance().getValue(
            "alllifePay.merID");// 支付系统提供给商户的ID号 账户
    private String key = Configuration.getInstance().getValue("alllifePay.key");

    private String version = "2.6";// 必填，接口版本号，当前版本号为1.0.0;
    private String serialID = MathUtil.getSerialNumber(20);// 请求序列号
    private String submitTime = DateUtil.toTimeStamp(DateUtil.now());// 订单提交时间
                                                                     // 格式20101117020101
    private String failureTime = "";// 可选，订单失效时间
    private String buyerMarked = "";
    private String platformID = "";// 平台商ID 商户所属平台商编号

    private String customerIP = "";// 客户下单域名及IPwww.google.com[192.168.1.2]
    private String totalAmount;// 订单总金额

    private String type = "1000";// 交易类型 1000: 即时支付 00开头是担保支付 10开头是即时支付
    /**
     * ALL：商户签约后开通的所有付款方式（默认）ACCT_RMB：新生RMB账户支付 BANK_B2C：网银B2C BANK_B2B：网银B2B
     * BANK_B2C_LARGE：网银B2C大额 LARGE_DEBIT_CARD：借记卡大额 LARGE_CREDIT_CARD：信用卡大额
     * CHINA_E_CARD：易卡支付
     * */
    private String payType;// 付款方支付方式
    private String currencyCode = "1";// 交易币种
                                      // 1：人民币（默认）2：预付卡（选择用预付费卡支付时，可选）3：授信额度
    private String borrowingMarked = "0";// 选择的资金来源借贷标识0：无特殊要求（默认）1：只借记2：只贷记
    private String orgCode = "";// 目标资金机构代码 银行代码

    private String directFlag = "1";// 是否直连 0：非直连 （默认） 1：直连
    private String couponFlag = "1";// 是否可以使用新生提供的优惠券1：可用 （默认）0：不可用

    private String returnUrl;// 商户回调地址
    private String noticeUrl;// 商户通知地址 异步通知
    private String remark = "";// 填写英文或中文字符串，照原样返回给商户

    private String charset = "1";// 报文编码格式1 ：UTF-8;
    private String signType = "2";// 选择报文签名类型1：RSA 方式（推荐）2：MD5 方式
    private String signMsg;// 签名字符串

    private String orderDetails;// 订单明细信息 格式如下：

    private String orderID;
    private String orderAmount;

    private String stateCode;// 返回状态吗

    public AllLifePayment()
    {
        super();
    }

    public void requestInit(String totalAmount, String payType, Bank bank,
            String returnUrl, String noticeUrl, String buyerMarked)
    {
        this.totalAmount = totalAmount;
        this.payType = payType;
        this.orgCode = this.getBankCode(bank);
        this.orderID = serialID;
        this.orderAmount = totalAmount;
        this.returnUrl = returnUrl;
        this.noticeUrl = noticeUrl;
        this.buyerMarked = buyerMarked;
        this.directFlag = StringUtils.isEmpty(this.orgCode) ? "0" : "1";
        this.orderDetails = new StringBuilder(orderID).append(",").append(
                orderAmount).append(",").append("").append(",").append("彩票")
                .append(",").append("1").toString();
        this.signMsg = createRequestSignMD5();
    }

    public void responseInit(HttpServletRequest request)
    {
        this.orderID = request.getParameter("orderID");
        this.stateCode = request.getParameter("stateCode");
        this.orderAmount = request.getParameter("payAmount");
    }

    /***
     * 加密规则
     * **/
    private String createRequestSignMD5()
    {
        StringBuilder md5Str = new StringBuilder();
        md5Str.append("version=").append(this.version).append("&serialID=")
                .append(this.serialID).append("&submitTime=").append(
                        this.submitTime).append("&failureTime=").append(
                        this.failureTime).append("&customerIP=").append(
                        this.customerIP).append("&orderDetails=").append(
                        this.orderDetails).append("&totalAmount=").append(
                        this.totalAmount).append("&type=").append(this.type)
                .append("&buyerMarked=").append(this.buyerMarked).append(
                        "&payType=").append(this.payType).append("&orgCode=")
                .append(this.orgCode).append("&currencyCode=").append(
                        this.currencyCode).append("&directFlag=").append(
                        this.directFlag).append("&borrowingMarked=").append(
                        this.borrowingMarked).append("&couponFlag=").append(
                        this.couponFlag).append("&platformID=").append(
                        this.platformID).append("&returnUrl=").append(
                        this.returnUrl).append("&noticeUrl=").append(
                        this.noticeUrl).append("&partnerID=").append(
                        this.partnerID).append("&remark=").append(this.remark)
                .append("&charset=").append(this.charset).append("&signType=")
                .append(this.signType).append("&pkey=").append(key);
        try {
            return ClientSignature.genSignByMD5(md5Str.toString(),
                    CharsetTypeEnum.UTF8);
        }
        catch (Exception e) {
            e.printStackTrace();
            return MD5.digest(md5Str.toString());
        }

    }

    public boolean verifyCallback()
    {
        if (stateCode.equals("2"))
            return true;
        else
            return false;
    }

    public String getBankCode(Bank bank)
    {
        switch (bank) {
        case 工商银行:
            return "icbc";
        case 招商银行:
            return "cmb";
        case 农业银行:
            return "abc";
        case 建设银行:
            return "ccb";
        case 中国银行:
            return "boc";
        case 交通银行:
            return "comm";
        case 兴业银行:
            return "cib";
        case 民生银行:
            return "cmbc";
        case 光大银行:
            return "ceb";
        case 华夏银行:
            return "hxb";

        case 中信银行:
            return "ecitic";
 
        case 上海浦东发展银行:
            return "spdb";
        case 中国邮政储蓄:
            return "post";

        case 北京银行:
            return "bccb";
        }
        return "";
    }
}
