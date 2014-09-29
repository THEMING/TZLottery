package com.xsc.lottery.tenpay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.MathUtil;

public class TenpayShare
{
    /** 网关url地址 */
    private String gateUrl = Configuration.getInstance().getValue(
            "tenpay.Login_Url");

    /** 密钥 */
    private String key = Configuration.getInstance().getValue("tenpay.Key");

    private String chnId = Configuration.getInstance().getValue("tenpay.ChnId");

    private String chtype = "0";// chnid类型。0:商户号 1:财付通帐号

    /** 请求的参数 */
    @SuppressWarnings("unchecked")
    private SortedMap parameters;

    /**
     * 构造函数
     * 
     * @param request
     * @param response
     */
    @SuppressWarnings("unchecked")
    public TenpayShare()
    {
        this.parameters = new TreeMap();
    }

    /**
     *初始化函数。
     */
    public void setRequestParam(String redirect_url)
    {
        this.setParameter("sign_type", "md5");
        this.setParameter("sign_encrypt_keyid", "0");
        this.setParameter("service", "login");
        this.setParameter("input_charset", "UTF-8");
        this.setParameter("chnid", this.chnId);
        this.setParameter("chtype", chtype);
        this.setParameter("encode_type", "1");
        this.setParameter("attach", MathUtil.getSerialNumber(10));
        this.setParameter("tmstamp", String.valueOf(TenpayUtil
                .getUnixTime(new Date())));
        this.setParameter("redirect_url", redirect_url);// 登录成功之后的目的URL
    }

    /**
     * 获取参数值
     * 
     * @param parameter
     *            参数名称
     * @return String
     */
    public String getParameter(String parameter)
    {
        String s = (String) this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * 设置参数值
     * 
     * @param parameter
     *            参数名称
     * @param parameterValue
     *            参数值
     */
    @SuppressWarnings("unchecked")
    public void setParameter(String parameter, String parameterValue)
    {
        String v = "";
        if (null != parameterValue) {
            v = parameterValue.trim();
        }
        this.parameters.put(parameter, v);
    }

    /**
     * 返回所有的参数
     * 
     * @return SortedMap
     */
    @SuppressWarnings("unchecked")
    public SortedMap getAllParameters()
    {
        return this.parameters;
    }

    /**
     * 获取带参数的请求URL
     * 
     * @return String
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    public String getRequestURL() throws UnsupportedEncodingException
    {
        this.createSign();
        StringBuffer sb = new StringBuffer();
        String enc = TenpayUtil.getCharacterEncoding();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
        }
        // 去掉最后一个&
        String reqPars = sb.substring(0, sb.lastIndexOf("&"));
        return this.getGateUrl() + "?" + reqPars;
    }

    /**
     * 设置uri编码
     * 
     * @param uriEncoding
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    public void setResponseAndUriEncoding(Map params, String uriEncoding)
            throws UnsupportedEncodingException
    {
        // 编码转换
        String enc = TenpayUtil.getCharacterEncoding();
        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String k = (String) it.next();
            String v = ((String[]) params.get(k))[0];
            v = new String(v.getBytes(uriEncoding.trim()), enc);
            this.setParameter(k, v);
        }
    }

    /**
     * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     * 
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean isTenpaySign()
    {
        StringBuffer sb = new StringBuffer();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + this.getKey());
        // 算出摘要
        String enc = TenpayUtil.getCharacterEncoding();
        String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
        String tenpaySign = this.getParameter("sign").toLowerCase();
        return tenpaySign.equals(sign);
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    @SuppressWarnings("unchecked")
    private void createSign()
    {
        StringBuffer sb = new StringBuffer();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + this.getKey());
        String enc = TenpayUtil.getCharacterEncoding();
        String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
        this.setParameter("sign", sign);
    }

    /**
     *获取入口地址,不包含参数值
     */
    public String getGateUrl()
    {
        return gateUrl;
    }

    /**
     *设置入口地址,不包含参数值
     */
    public void setGateUrl(String gateUrl)
    {
        this.gateUrl = gateUrl;
    }

    /**
     *获取密钥
     */
    public String getKey()
    {
        return key;
    }

    /**
     *设置密钥
     */
    public void setKey(String key)
    {
        this.key = key;
    }
}
