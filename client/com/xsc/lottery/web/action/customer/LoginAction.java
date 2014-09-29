package com.xsc.lottery.web.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.Md5Util;
import com.xsc.lottery.util.NetWorkUtil;
import com.xsc.lottery.web.action.LotteryClientBaseAction;
import com.xsc.lottery.web.action.json.AjaxResultStatusType;
import com.xsc.lottery.web.action.json.JsonMsgBean;
import com.xsc.lottery.web.security.jcaptcha.CaptchaServiceSingleton;

@Scope("prototype")
@Controller("customer.login")
@SuppressWarnings( { "unused", "serial", "static-access" })
public class LoginAction extends LotteryClientBaseAction
{
    @Autowired
    private CustomerService customerService;
    
    private String nickName;
    private String nickname;
    private String password;
    private String mngunm;
    private String message = "";
    private Customer customer;
    private String  login="";
    private int num;
    private BigDecimal totalSumMoney1 = new BigDecimal(0);
    private BigDecimal totalSumMoney2 = new BigDecimal(0);
    //QQ Login Param
    private String _appID = "100226332";
    private String _appKey = "c7f9137f3adec83704df45ae16256af4";
    private String _RedirectURL = "www.yicp.com";
    private String _openId;
    
    public String code;
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String index()
    {
        customer = this.getCurCustomer();
        if (null == customer) {
            return "indexLogout";
        }
        else {
        	login="";
            return "indexLogin";
        }
    }
    
    //首页的登录与注销
    public String indexLogin()
    {
        Md5Util encoder = new Md5Util();
        Customer customer = null;
        if (nickname.split("@").length > 1) {
            customer = customerService.getUniqueCustomerByProperty("email", nickname);
        }
        else {
            customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
        }
        
        if (mngunm == null) {
            message = "验证码不能为空";
            return "indexLogout";
        }
        if (!CaptchaServiceSingleton.getInstance().validateResponseForID(
                getRequest().getSession().getId(), mngunm.toLowerCase())) {
            message = "验证码不正确!";
            return "indexLogout";
        }
        if (customer != null) {
            if (encoder.getMD5ofStr(password).equals(customer.getPassword())) {
                getSession().put(SESSION_CUSTOMER_KEY, customer);
                customer.setLastLoginTime(Calendar.getInstance());
                customer.setCustomerIp(this.getRequest().getRemoteAddr());
                customer.addLoginNum(1);
                customerService.update(customer);
            }
            else {
                message = "用户名或密码错误";
                return "indexLogout";
            }
        }
        else {
            message = "用户名或密码错误";
            return "indexLogout";
        }
        login="login";
        return "indexLogin";
    }
    
    public String getAccessCode()
    {
    	String szCode = "";
    	try
    	{
    		String szURL = "https://graph.qq.com/oauth2.0/authorize";
    		String szParam = "response_type=code&client_id=" + _appID 
    						+ "&redirect_uri=" + _RedirectURL;
    		String szReturn = NetWorkUtil.getHttpUrlByPostMethod(szURL, szParam );
    		szCode = szReturn.split("\\&")[0].split("=")[1];
    	}
	    catch(Exception e){
		}
	    return szCode;
    }
    
    public String GetToken(String szCode)
    {
    	String szToken = "";
    	try
    	{
    	    String szURL = "https://graph.qq.com/oauth2.0/token";
        	String szParam="grant_type=authorization_code&client_id="
        					+ _appID + "&client_secret=" + _appKey + "&redirect_uri="
        					+ _RedirectURL + "&code=" + szCode;
        	String szReturn = NetWorkUtil.getHttpUrlByPostMethod(szURL, szParam);
        	szToken = szReturn.split("\\&")[0].split("=")[1];
    	}
	    catch(Exception e){
		}   
	    return szToken;    	   	
    }
    public String GetOpenID(String szToken)
    {
    	String szOpenID = "";
    	try
    	{
    	    String szURL = "https://graph.qq.com/oauth2.0/me";
        	String szParam="access_token=" + szToken;
        	String szReturn = NetWorkUtil.getHttpUrlByPostMethod(szURL, szParam);
        	String[] szRList = szReturn.split(",");
        	szOpenID = szRList[1].split(":")[1];
        	szOpenID = szOpenID.split("}")[0];
        	szOpenID = szOpenID.replace("\"", "");
    	}
	    catch(Exception e){
		}   
	    return szOpenID;    	   	
    }
    
    private String getQQLoginErrorReturn()
    {
    	return "login";
    }
    private String getQQLoginSuccessReturn()
    {
    	if(state.equals("1"))
    		return "indexLogin";
    	else
    		return "customer";
    }
    
    public String qqlogin(){
    	try{
    		String szCode = code;
	    	//获取Token
	    	String szToken = GetToken(szCode);
	    	if(szToken.equals(""))
	    	{
	            message = "获取Token错误！";
	            return getQQLoginErrorReturn();
	        }
	    	//获取OpenID
	    	String szOpenID = GetOpenID(szToken);
	    	if(szOpenID.equals(""))
	    	{
	            message = "获取OpenID错误！";
	            return getQQLoginErrorReturn();
	        }	    	
			getSession().put("openID", szOpenID);
	        customer = customerService.getCustomerByOpenId(szOpenID);
	    	if(customer!=null)
	    	{
                getSession().put(SESSION_CUSTOMER_KEY, customer);
                customer.setLastLoginTime(Calendar.getInstance());
                customer.setCustomerIp(this.getRequest().getRemoteAddr());
                customer.addLoginNum(1);
                customerService.update(customer);
                
	    		if(state.equals("1"))
	    		{//从首页登陆
	                login="login";
	        		return "indexLogin";
	    		}
	    		else
	    		{//从登陆窗口登陆
	    	        nickName = customer.getNickName();
	    	        calculateAllDetail();
	    	        getSession().put(SESSION_TOTAL1_KEY, totalSumMoney1);
	    	        getSession().put(SESSION_TOTAL2_KEY, totalSumMoney2);
	    	        num = customerService.count(customer);
	    	        getSession().put(SESSION_NUM_KEY, num);	    			
	                login="login";
	        		return "customer";
	    		}
	        }
	    	else{
	            message = "openId没有用户";
				//提示绑定或者注册
	            return "qqlogin";
			}
    	}catch(Exception e){
    		message = e.getMessage();
    		return getQQLoginErrorReturn();
    	}
    }
    
    public String ajaxLogin()
    {
        Md5Util encoder = new Md5Util();
        Map map=new HashMap();
        Customer customer = null;
        if (nickname.split("@").length > 1) {
            customer = customerService.getUniqueCustomerByProperty("email", nickname);
        }
        else {
            customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
        }
        
        if (mngunm == null) {      
            map.put("message", "验证码不能为空");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
        if (!CaptchaServiceSingleton.getInstance().validateResponseForID(
                getRequest().getSession().getId(), mngunm.toLowerCase())) {
            map.put("message", "验证码不正确!");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
        if (customer != null) {
            if (encoder.getMD5ofStr(password).equals(customer.getPassword())) {
                getSession().put(SESSION_CUSTOMER_KEY, customer);
                customer.setLastLoginTime(Calendar.getInstance());
                customer.setCustomerIp(this.getRequest().getRemoteAddr());
                customer.addLoginNum(1);
                customerService.update(customer);
            }
            else {
                map.put("message", "用户名或密码错误");
                setJsonString(JsonMsgBean.MapToJsonString(map));
                return AJAXJSON;
            }
        }
        else {
            map.put("message", "用户名或密码错误");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
       
        map.put("message", "welocme");
        setJsonString(JsonMsgBean.MapToJsonString(map));
        return AJAXJSON;
    }

    public String indexLogout()
    {
        this.saveCurCustomer(null);
        return "indexLogout";
    }
    
    public String logout()
    {
        this.saveCurCustomer(null);
        return "logout";
    }
    
    //从登录页登录
    public String login()
    {
        Md5Util encoder = new Md5Util();
        Customer customer = null;
        if (nickname.split("@").length > 1) {
            customer = customerService.getUniqueCustomerByProperty("email", nickname);
        }
        else {
            customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
        }

        if(StringUtils.isBlank(mngunm)) {
        	message = "验证码不能为空";
            return "login";
        }
        
        if(!CaptchaServiceSingleton.getInstance().validateResponseForID(
                getRequest().getSession().getId(), mngunm.toLowerCase())) {
        	message = "验证码不正确!";
            return "login";
        }
        
        if(customer != null) {
            if (encoder.getMD5ofStr(password).equals(customer.getPassword())) {
                getSession().put(SESSION_CUSTOMER_KEY, customer);
                customer.setLastLoginTime(Calendar.getInstance());
                customer.setCustomerIp(this.getRequest().getRemoteAddr());
                customer.addLoginNum(1);
                customerService.update(customer);
            }
            else {
            	message = "用户名或密码错误";
                return "login";
            }
        }
        else {
        	message = "用户名或密码错误";
            return "login";
        }
        nickName = customer.getNickName();
        calculateAllDetail();
        getSession().put(SESSION_TOTAL1_KEY, totalSumMoney1);
        getSession().put(SESSION_TOTAL2_KEY, totalSumMoney2);
        num = customerService.count(customer);
        getSession().put(SESSION_NUM_KEY, num);
        return SUCCESS;
    }
    
    private void calculateAllDetail()
	{
		Page customerPage = new Page<Customer>();
		List<Customer> customer = customerService.getCustomerPageByNickName(customerPage, nickName).getResult();
		for(int i=0; i<customer.size(); i++)
		{
			totalSumMoney1 = new BigDecimal(0);
			totalSumMoney2 = new BigDecimal(0);
			Customer customerTemp = customer.get(i);
			BigDecimal sratio=customerTemp.getSuperRatio(); 
			BigDecimal sratio2=customerTemp.getSsuperRatio();
			
			List<WalletLog> list = customerService.getRecommendorsList(customerTemp, 
					"", null, null);
			for (WalletLog wl : list) {
				BigDecimal money = BigDecimal.ZERO;
	            if (WalletLogType.LOTTERY_IN.contains(wl.getType())) {
	            	money = money.add(wl.getOutMoney());
	            }
	            else if (WalletLogType.LOTTERY_OUT.contains(wl.getType())) {
	            	money = money.subtract(wl.getInMoney());
	            }
	            
	            if(wl.getWallet().getCustomer().getSuperior() == customerTemp) {
	            	totalSumMoney1 = totalSumMoney1.add(money.multiply(sratio));
	            }
	            else if(wl.getWallet().getCustomer().getSsuperior() == customerTemp) {
	            	totalSumMoney2 = totalSumMoney2.add(money.multiply(sratio2));
	            }
	        }			
			customerTemp.setSuperCommission(totalSumMoney1);
			customerTemp.setSsuperCommission(totalSumMoney2);
			customerService.save(customerTemp);
		}
	}
    
    
    //从软件登录
    public String softlogin()
    {
        Md5Util encoder = new Md5Util();
        Customer customer = null;
        if (nickname.split("@").length > 1) {
            customer = customerService.getUniqueCustomerByProperty("email", nickname);
        }
        else {
            customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
        }
        
        if (mngunm == null) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "验证码不能为空"));
            return AJAXJSON;
        }
        if (!CaptchaServiceSingleton.getInstance().validateResponseForID(
                getRequest().getSession().getId(), mngunm.toLowerCase())) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "验证码不正确"));
            return AJAXJSON;
        }
        
        if(customer != null) {
            if (encoder.getMD5ofStr(password).equals(customer.getPassword())) {
                getSession().put(SESSION_CUSTOMER_KEY, customer);
                customer.setLastLoginTime(Calendar.getInstance());
                customer.setCustomerIp(this.getRequest().getRemoteAddr());
                customer.addLoginNum(1);
                customerService.update(customer);
            }
            else {
                setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                        AjaxResultStatusType._0001, "用户名或密码错误"));
                return AJAXJSON;
            }
        }
        else {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "用户名或密码错误"));
            return AJAXJSON;
        }
        
        setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndBalance(AjaxResultStatusType._0000, customer.getWallet().getBalance()));
        return AJAXJSON;
    }
    
    //从QQ登录
    @SuppressWarnings("unchecked")
	public String CheckOpenID(String szOpenID)
    {
    	Customer customer = null;
    	Map map=new HashMap();
    	customer = customerService.getCustomerByOpenId(szOpenID);
    	if(customer != null){
    		getSession().put(SESSION_CUSTOMER_KEY, customer);
            customer.setLastLoginTime(Calendar.getInstance());
            customer.setCustomerIp(this.getRequest().getRemoteAddr());
            customer.addLoginNum(1);
            customerService.update(customer);
    	}else{
    		 map.put("message", "openId");
             setJsonString(JsonMsgBean.MapToJsonString(map));
             return AJAXJSON; 
    	}
    	return AJAXJSON;
    }
        
    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setMngunm(String mngunm)
    {
        this.mngunm = mngunm;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public String getMessage()
    {
        return message;
    }

    public Customer getCustomer()
    {
        return customer;
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public BigDecimal getTotalSumMoney1() {
		return totalSumMoney1;
	}

	public void setTotalSumMoney1(BigDecimal totalSumMoney1) {
		this.totalSumMoney1 = totalSumMoney1;
	}

	public BigDecimal getTotalSumMoney2() {
		return totalSumMoney2;
	}

	public void setTotalSumMoney2(BigDecimal totalSumMoney2) {
		this.totalSumMoney2 = totalSumMoney2;
	}
}
