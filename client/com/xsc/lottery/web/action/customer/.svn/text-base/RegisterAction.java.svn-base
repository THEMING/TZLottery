package com.xsc.lottery.web.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import alex.zhrenjie04.wordfilter.WordFilterUtil;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.listener.active.ActivityListener;
import com.xsc.lottery.util.Betvalidate;
import com.xsc.lottery.util.EmailUtil;
import com.xsc.lottery.util.Md5Util;
import com.xsc.lottery.web.action.LotteryClientBaseAction;
import com.xsc.lottery.web.action.json.AjaxResultStatusType;
import com.xsc.lottery.web.action.json.JsonMsgBean;
import com.xsc.lottery.web.interceptor.BindUrlInteceptor;
import com.xsc.lottery.web.security.jcaptcha.CaptchaServiceSingleton;

@Scope("prototype")
@Controller("customer.register")
@SuppressWarnings( { "serial" })
public class RegisterAction extends LotteryClientBaseAction
{
    @Autowired
    private ActivityListener activityListener;
    
    
    @Autowired
    private CustomerService customerService;

    private String nickname;// 用户名
    private String superior;// 推荐人
    private String password;// 密码

    private String repassword;

    private String name;// 真实姓名

    private String credentNo;// 身份证号

    private String email;// 电子邮箱

    private String validnum;// 验证码

    private String sysmsg = "";

    private String mobile;

    private Customer customer;
    private Customer superiorCus; // 推荐人
    private UserType useType;

    private String useOtherId;

    private String mobileNo;

    private String openId; //QQ openId
    
    public String index()
    {
    	//获取推荐人信息
    	String ref = (String) getSession().get(BindUrlInteceptor.SESSION_CUSTOMER_REFERENCE_KEY);
    	if(ref != null) {
    		//应该加错误处理
    		Long cid=Long.parseLong(ref);
    		superiorCus = customerService.findById(cid);
    	}
    	return "index";
    }
    public String cheacksuperior(){
   	 Map map=new HashMap();
    	customer = customerService.getUniqueCustomerByProperty("nickName", superior);
   	 if(null==customer)
   	{
   		 map.put("message", "推荐人不存在");
         setJsonString(JsonMsgBean.MapToJsonString(map));
    } else
    {
    	map.put("message", "推荐人存在");
        setJsonString(JsonMsgBean.MapToJsonString(map));
    }
   	return AJAXJSON;
   }
    public String reg()
    {
        customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
        if (customer != null) {
            sysmsg = "用户不可用,请重新填写!";
            return "index";
        }
        
        if(superiorCus == null) {
        	if(superior != null && !superior.isEmpty()) {
        		superiorCus = customerService.getUniqueCustomerByProperty("nickName", superior);
		        if (superiorCus == null) {
		        	sysmsg = "推荐人不存在，请核查推荐人用户名!";
		            return "index";
		        }
        	}
        }
        
        if (!Betvalidate.isName(nickname)
                && WordFilterUtil.filterHtml(nickname, '*').getLevel() > 0) {
            sysmsg = "用户不可用,请重新填写.|";
        }
        if (!Betvalidate.isPassword(password)) {
            sysmsg += "密码格式错误或为空!|";
        }
        if (!repassword.equals(password)) {
            sysmsg += "确认密码与密码不一至!|";
        }
        
        if (!Betvalidate.isEmail(email)) {
            sysmsg += "邮箱格式错误或为空!|";
        }
        if (!CaptchaServiceSingleton.getInstance().validateResponseForID(
                getRequest().getSession().getId(), validnum.toLowerCase())) {
            sysmsg += "验证码不正确!|";
        }
        if (!sysmsg.isEmpty()) {
            return "index";
        }
        customer = new Customer();
        customer.setNickName(nickname);
        if(superiorCus != null) {
        	customer.setSuperior(superiorCus);
        	customer.setSsuperior(superiorCus.getSuperior());
        }
        
        customer.setPassword(Md5Util.getMD5ofStr(password));
        customer.setEmail(email);
        customer.setUsrType(getUserType());
        System.out.println(getUserType());
        customer.setRegisterTime(Calendar.getInstance());
        customer.setLastLoginTime(Calendar.getInstance());
        Wallet wallet = new Wallet();
        wallet.setCustomer(customer);
        wallet.setBalance(new BigDecimal(0));
        customer.setWallet(wallet);
        customer = customerService.save(customer);
        customerService.saveWalletSummary(customer.getWallet());
        saveCurCustomer(customer); //save to session
        return "ok";
    }
    
    public String checkName()
    {
        Customer customer = customerService.getCustomerOrName(nickname);
        if (customer != null) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0000, "该用户名已注册!"));
        } 
        else {
            if (WordFilterUtil.filterHtml(nickname, '*').getLevel() > 0) {
                setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                	AjaxResultStatusType._0000, "用户名不可用!"));
            }
            else {
                setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                	AjaxResultStatusType._0000, "该用户名可用!"));
            }
        }
        return AJAXJSON;
    }

    public String checkSuperior()
    {
        Customer customer = customerService.getCustomerOrName(superior);
        if (customer != null) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0000, "恭喜，该推荐人正确!"));
        } 
        else {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
            		AjaxResultStatusType._0000, "该推荐人不存在，请核查!"));
        }
        return AJAXJSON;
    }
    
    public String callbackpwd()
    {
    	if(nickname == null || nickname.equals("")) {
    		return "pwd";
    	}
    	
        Customer customer = customerService.getCustomerOrName(nickname);
        if(customer != null) {
        	if(customer.getCredentNo() == null) {
        		sysmsg = "您注册后的资料完善中没有填写身份证号码，请联系客服!";
                return "pwd";
        	}
        	
            if(!customer.getCredentNo().equals(credentNo)) {
                sysmsg = "身份证错误，请联系客服!";
                return "pwd";
            }
            
            if(!customer.getEmail().equals(email)) {
                sysmsg = "邮件错误!";
                return "pwd";
            }
        }
        else {
            sysmsg = "用户名错误!";
            return "pwd";
        }
        
        try {
        	String newPwd = customer.getPassword().substring(0,6);
        	String title = "一彩票客服中心-密码重置";
        	String content = customer.getNickName()+"，&nbsp;您好：<br/><br/>&emsp;一彩票客服中心已经将您的密码重设为:";
        	content += newPwd;
        	content += "，&nbsp请牢记并及时更改您的密码！<br/>";
        	
        	customer.setPassword(Md5Util.getMD5ofStr(newPwd));
        	customerService.save(customer);
  
            EmailUtil.sendEmail(customer.getEmail(), title, content);
            logger.info("send email to " + customer.getEmail() + " ok!");
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        
        sysmsg = "一彩票提示您：已经给您发送邮件，请注意查收!";
        return "pwd";
    }

    public String checkpwd()
    {
        Customer customer = customerService.getCustomerOrName(nickname);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("num", "0");
        if (customer != null) {
            if (customer.getCredentNo() != credentNo) {
                resultMap.put("num", "3");
                resultMap.put("credenterr", "身份证错误!");
                return AJAXJSON;
            }
            if (customer.getEmail() != email) {
                resultMap.put("num", "1");
                resultMap.put("emailerr", "邮件错误!");
                return AJAXJSON;
            }
        } 
        else {
            resultMap.put("num", "2");
            resultMap.put("nameerr", "用户名错误!");
            return AJAXJSON;
        }
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }
    
    //qq创建新用户
    @SuppressWarnings("unchecked")
	public String qqZhuCe() 
    {
    	System.out.println("调用qqZhuCe...");
    	Map<String, String> map=new HashMap<String, String>();
    
    	Customer customer;
    	customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
        if (customer != null) {
            map.put("message", "0");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
        if (!Betvalidate.isName(nickname)
                && WordFilterUtil.filterHtml(nickname, '*').getLevel() > 0) {
            map.put("message", "1");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
        if (!Betvalidate.isPassword(password)) {
            map.put("message", "2");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
        if (!Betvalidate.isEmail(email)) {
            map.put("message", "3");
            setJsonString(JsonMsgBean.MapToJsonString(map));
            return AJAXJSON;
        }
		
		customer = new Customer();
    	
    	customer.setNickName(nickname);
    	customer.setPassword(Md5Util.getMD5ofStr(password));
    	customer.setEmail(email);
    	customer.setOpenId((String) getSession().get("openID"));
    	customer.setUsrType(getUserType());
        customer.setRegisterTime(Calendar.getInstance());
        customer.setLastLoginTime(Calendar.getInstance());
        Wallet wallet = new Wallet();
        wallet.setCustomer(customer);
        wallet.setBalance(new BigDecimal(0));
        customer.setWallet(wallet);
        customer = customerService.save(customer);
        customerService.saveWalletSummary(customer.getWallet());
        saveCurCustomer(customer); //save to session
        map.put("message", "成功");
        setJsonString(JsonMsgBean.MapToJsonString(map));
        return AJAXJSON;
    }
    
    //qq绑定用户
	public String qqBangDing() 
    {
//		Md5Util encoder = new Md5Util();
		System.out.println("调用qqBangDing...");
		System.out.println("调用...");
		System.out.println(openId);
    	Map<String, String> map=new HashMap<String, String>();
    	Customer customer = customerService.getCustomerOrName(nickname);
    	if(customer != null) {
    		System.out.println(customer.getOpenId());
    		if(customer.getOpenId() == null)
    		{
    			if (Md5Util.getMD5ofStr(password).equals(customer.getPassword())) 
    			{
    				getSession().put(SESSION_CUSTOMER_KEY, customer);
    				customer.setLastLoginTime(Calendar.getInstance());
    				customer.setCustomerIp(this.getRequest().getRemoteAddr());
    				customer.addLoginNum(1);
    				customer.setOpenId((String) getSession().get("openID"));
    				customerService.update(customer);
    				map.put("message", "成功");
    				setJsonString(JsonMsgBean.MapToJsonString(map));
    				System.out.println("调用成功...");
    				return AJAXJSON;
    			}
    			else {
    				map.put("message", "2");
    				setJsonString(JsonMsgBean.MapToJsonString(map));
    				System.out.println("调用2...");
    				return AJAXJSON;
    			}
    		}
    		else {
				map.put("message", "1");
				setJsonString(JsonMsgBean.MapToJsonString(map));
				System.out.println("调用1...");
				return AJAXJSON;
    		}
        }
        else {
        	map.put("message", "0");
            setJsonString(JsonMsgBean.MapToJsonString(map));
    		System.out.println("调用0...");
            return AJAXJSON;
        }
    }
    
    protected UserType getUserType() 
    {
    	String from = (String) getSession().get(
    			BindUrlInteceptor.SESSION_CUSTOMER_FROM_KEY);
    	if(from != null) {
    		if(from.equals("55tuan.com")) {
    			return UserType.新浪微博用户;
    		}
    	}
    	return UserType.本地注册用户;
    }
    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCredentNo()
    {
        return credentNo;
    }

    public void setCredentNo(String credentNo)
    {
        this.credentNo = credentNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getsysmsg()
    {
        return sysmsg;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setValidnum(String validnum)
    {
        this.validnum = validnum;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setRepassword(String repassword)
    {
        this.repassword = repassword;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }
    
    public String read()
    {
    	return "read";
    }
    
    public String getMobile()
    {
        return mobile;
    }

    public UserType getUseType()
    {
        return useType;
    }

    public String getUseOtherId()
    {
        return useOtherId;
    }

    public void setUseType(UserType useType)
    {
        this.useType = useType;
    }

    public void setUseOtherId(String useOtherId)
    {
        this.useOtherId = useOtherId;
    }

	public String getSuperior()
	{
		return superior;
	}

	public void setSuperior(String superior)
	{
		this.superior = superior;
	}

	public Customer getSuperiorCus()
	{
		return superiorCus;
	}

	public void setSuperiorCus(Customer superiorCus)
	{
		this.superiorCus = superiorCus;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}
