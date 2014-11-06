package com.xsc.lottery.action.partner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.entity.admin.AdminChannel;
import com.xsc.lottery.entity.admin.AdminRoleFunction;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SpreadChannel;
import com.xsc.lottery.entity.enumerate.CustomerType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.admin.AdminChannelService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.util.Md5Util;
import com.xsc.lottery.web.action.LotteryClientBaseAction;
import com.xsc.lottery.web.security.jcaptcha.CaptchaServiceSingleton;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("partnerLoginAct")
public class PartnerLoginAction extends LotteryClientBaseAction
{
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private AdminChannelService adminChannelService;
    
    public SimpleHibernateTemplate<SpreadChannel, Long> spreadChannelDao;
    
    @Autowired
	public void setSessionFactory(@Qualifier("sessionFactory")
	SessionFactory sessionfactory)
	{
		this.spreadChannelDao = new SimpleHibernateTemplate<SpreadChannel, Long>(sessionfactory, SpreadChannel.class);
	}
    
    private String nickname;
    private String password;
    private String mngunm;
    private String message = "";
    private Partner partner = null;
    
    private String oldpassword;
    private String repassword;
    
    private Customer customer;
    
    private List<AdminChannel> depthList;
    
    private List<AdminChannel> parentChannelList;
    
    private Long depth;// 节点ID
    
    private int loginType;
    
    public int getLoginType()
	{
		return loginType;
	}

	public void setLoginType(int loginType)
	{
		this.loginType = loginType;
	}

	public Long getDepth()
	{
		return depth;
	}

	public void setDepth(Long depth)
	{
		this.depth = depth;
	}

	public List<AdminChannel> getParentChannelList()
	{
		return parentChannelList;
	}

	public void setParentChannelList(List<AdminChannel> parentChannelList)
	{
		this.parentChannelList = parentChannelList;
	}

	public List<AdminChannel> getDepthList()
	{
		return depthList;
	}

	public void setDepthList(List<AdminChannel> depthList)
	{
		this.depthList = depthList;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public void setOldpassword(String oldpassword)
    {
        this.oldpassword = oldpassword;
    }

    public void setRepassword(String repassword)
    {
        this.repassword = repassword;
    }

    public String getMessage()
    {
        return message;
    }

    public String index()
    {
//        partner = this.getCurClient();
    	customer = this.getCurCustomer();
        if(customer == null) {
            return "login";
        }
        return SUCCESS;
    }
    
 // 模版页面左则部
    public String left()
    {
    	parentChannelList = new ArrayList<AdminChannel>();
    	
    	
    	
        if (depth == null) {
//        	parentChannelList = adminChannelService.getAdminChannelDepthAsc(new Long(164));
        	AdminChannel ac = new AdminChannel();//基本信息
        	ac.setId(new Long(1));
        	ac.setRighturl("/partner/agent/agentInfo.aspx");
        	ac.setChannelName("基本信息");
        	parentChannelList.add(ac);
        }
        else if(new Long(1).equals(depth)){
        	AdminChannel ac = new AdminChannel();//基本信息
        	ac.setId(new Long(1));
        	ac.setRighturl("/partner/agent/agentInfo.aspx");
        	ac.setChannelName("基本信息");
        	parentChannelList.add(ac);
        }else if(new Long(2).equals(depth)){
        	AdminChannel ac = new AdminChannel();//注册用户
        	ac.setId(new Long(2));
        	ac.setRighturl("/partner/agent/register.aspx");
        	ac.setChannelName("注册用户");
        	parentChannelList.add(ac);
        	AdminChannel ac2 = new AdminChannel();//充值信息
        	ac2.setId(new Long(2));
        	ac2.setRighturl("/partner/agent/recharge.aspx");
        	ac2.setChannelName("充值信息");
        	parentChannelList.add(ac2);
        	AdminChannel ac3 = new AdminChannel();//购彩查询
        	ac3.setId(new Long(2));
        	ac3.setRighturl("/partner/agent/payment.aspx");
        	ac3.setChannelName("购彩查询");
        	parentChannelList.add(ac3);
        	AdminChannel ac4 = new AdminChannel();//佣金转账查询
        	ac4.setId(new Long(2));
        	ac4.setRighturl("/partner/agent/yongjinChongzhiChaxun.htm");
        	ac4.setChannelName("佣金转账查询");
        	parentChannelList.add(ac4);
        	AdminChannel ac5 = new AdminChannel();//佣金查询
        	ac5.setId(new Long(2));
        	ac5.setRighturl("/partner/agent/yongjin.htm");
        	ac5.setChannelName("佣金查询");
        	parentChannelList.add(ac5);
        	AdminChannel ac6 = new AdminChannel();//提款查询
        	ac6.setId(new Long(2));
        	ac6.setRighturl("/partner/agent/tikuanChaxun.htm");
        	ac6.setChannelName("提款查询");
        	parentChannelList.add(ac6);
        	AdminChannel ac7 = new AdminChannel();//报表查询
        	ac7.setId(new Long(2));
        	ac7.setRighturl("/partner/agent/agentManager.htm");
        	ac7.setChannelName("报表查询");
        	parentChannelList.add(ac7);
        }else if(new Long(4).equals(depth)){
        	AdminChannel ac = new AdminChannel();//我的客户
        	ac.setId(new Long(4));
        	ac.setRighturl("/oss/crmSystem/CRMManage.aspx");
        	ac.setChannelName("我的工作台");
        	parentChannelList.add(ac);
        	
        	AdminChannel ac2 = new AdminChannel();//我发过的邮件
        	ac2.setId(new Long(4));
        	ac2.setRighturl("/oss/crmSystem/mySendEmail.htm");
        	ac2.setChannelName("我发过的邮件");
        	parentChannelList.add(ac2);
        	
        	AdminChannel ac3 = new AdminChannel();//我发过的短信
        	ac3.setId(new Long(4));
        	ac3.setRighturl("/oss/crmSystem/mySendSms.htm");
        	ac3.setChannelName("我发过的邮件");
        	parentChannelList.add(ac3);
        }
        return "left";
    }
    
    public String exit()
    {
    	this.saveCurCustomer(null);
        return "login";
    }

    // 模版页面头部
    public String head()
    {
    	depthList = new ArrayList<AdminChannel>();
    	
    	AdminChannel ac = new AdminChannel();//用户中心
    	ac.setId(new Long(1));
    	ac.setChannelName("用户中心");
    	depthList.add(ac);
//    	AdminChannel ac2 = adminChannelService.findById(new Long(165));//推广管理
//    	depthList.add(ac2);
    	AdminChannel ac3 = new AdminChannel();//统计数据
    	ac3.setId(new Long(2));
    	ac3.setChannelName("统计数据");
    	depthList.add(ac3);
    	Customer c = this.getCurCustomer();
    	if(c.getCustomerType()!=null&&CustomerType.BusinessCustomer.equals(c.getCustomerType())){
    		AdminChannel ac4 = new AdminChannel();//crm系统 业务员专用
        	ac4.setId(new Long(4));
        	ac4.setChannelName("crm系统");
        	depthList.add(ac4);
    	}
    	
        return "head";
    }
    
    public String login()
    {
    	Md5Util encoder = new Md5Util();
//        partner = partnerService.getUniquePartnerByProperty("nickName", nickname);
    	
    	if(loginType==1){//用户名登录方式
    		customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
    	}else if(loginType==2){//代理id登录方式
    		try{
        		customer = customerService.findById(Long.parseLong(nickname));
        	}catch(Exception e){//类型转换异常 
        		
        	}
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
        	
        	if(!new Integer(1).equals(customer.getIsPass())){
        		message = "该用户不是CPS用户";
                return "login";
        	}
        	
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
        
        return SUCCESS;
    }
    
    public String password()
    {
    	customer = this.getCurCustomer();
        if(customer == null) {
            return "login";
        }
        return "password";
    }
    
    public String savepwd()
    {
        message = "";
        customer = this.getCurCustomer();
        if(Md5Util.getMD5ofStr(oldpassword).equals(customer.getPassword())) {
            if(password.equals(repassword)) {
                if (StringUtils.isEmpty(password)
                        || StringUtils.isEmpty(repassword)) {
                    message = "输入新密码错误!";
                }
                else {
                	customer.setPassword(Md5Util.getMD5ofStr(password));
                	customerService.update(customer);
                    message = "修改成功!";
                }
            }
            else {
                message = "新密码不一致!";
            }
        } 
        else {
            message = "原密码错误!";
        }
        return "password";
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

    public String getMngunm()
    {
        return mngunm;
    }

    public void setMngunm(String mngunm)
    {
        this.mngunm = mngunm;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
