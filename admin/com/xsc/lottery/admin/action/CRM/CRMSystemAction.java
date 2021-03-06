package com.xsc.lottery.admin.action.CRM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.common.Constants;
import com.xsc.lottery.entity.business.AdminSendSomeThingTemplate;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.entity.business.EmailLog.EmailType;
import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsLog.SmsSendType;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;
import com.xsc.lottery.entity.business.SmsLog.SmsSendState;
import com.xsc.lottery.entity.enumerate.CustomerType;
import com.xsc.lottery.entity.enumerate.SendTemplateType;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.admin.AdminUserService;
import com.xsc.lottery.service.business.AdminSendSomeThingTemplateService;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CpsReportService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.EmailLogService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.SmsLogService;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.service.business.WalletService;
import com.xsc.lottery.util.Base64;
import com.xsc.lottery.util.MD5;
import com.xsc.lottery.web.action.LotteryClientBaseAction;
import com.xsc.lottery.web.action.json.JsonMsgBean;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.CRMSystem")
public class CRMSystemAction extends LotteryClientBaseAction{
	
	@Autowired
    private CustomerService customerService;
	@Autowired
    private CpsReportService cpsReportService;
	@Autowired
	private SmsLogService smsLogService;
	@Autowired
    private CommunityService communityService;
	@Autowired
    private LotteryOrderService orderService;
	@Autowired
	private EmailLogService emailService;
	@Autowired
	private WalletService walletLogService;
	@Autowired
    public SysParamService sysParamService;
	@Autowired
	private LotteryOrderService lotteryOrderService;
	
	@Autowired
	private AdminSendSomeThingTemplateService adminSendSomeThingTemplateService;
	@Autowired
	private AdminUserService adminUserService;
	private Page<Customer> customerPage;
	private List<Map> customerListMap = null;
	private int pageNo = 1;
	private int pageSize = 50;
	private int totalNum = 0;
	private Calendar f_sTime;
	private Calendar f_eTime;
	private Date sTime;
	private Date eTime;
	private Calendar f_starTime;
	private String f_orderserch;
	private String f_serch;
	private UserType userType;
	private String f_serchName;
	private Calendar f_endTime;
	private UserType[] userTypes = UserType.values();
	private  List<AdminSendSomeThingTemplate> adminSendSomeThingTemplateList = new ArrayList<AdminSendSomeThingTemplate>();
	private  AdminSendSomeThingTemplate adminSendSomeThingTemplate = null;
	private  List<AdminSendSomeThingTemplate> smsTemplateList = new ArrayList<AdminSendSomeThingTemplate>();
	private  AdminSendSomeThingTemplate smsTemplate = null;
	private  List<AdminSendSomeThingTemplate> emailTemplateList = new ArrayList<AdminSendSomeThingTemplate>();
	private  AdminSendSomeThingTemplate emailTemplate = null;
	public SimpleHibernateTemplate<ArticleCategory, Long> categoryDao;
	public SimpleHibernateTemplate<AdminSendSomeThingTemplate, Long> adminSendSomeThingTemplateDao;
	private SendTemplateType[] sendTemplateTypeList = SendTemplateType.values();
    private SendTemplateType sendTemplateType;
    
    private String t_title;
    private String t_content;
    private String t_description;
    
    private Long templateId;
    
    private String smsArgument;
    
    private String phoneNums;
    
    private String phoneIds;
    
    private String smsContent;
	private String emails;
	private String emailArgument;
	private String emailIds;
	private String emailContent;
	private String emailTitle;
	private String nickNames;
	private BigDecimal outMoneySum = new BigDecimal(0);
	private BigDecimal paymentMoenySum = new BigDecimal(0);
	private Page<SmsLog> smsLogPage = new Page<SmsLog>();
	
	private SmsLogState[] smsLogStateList = SmsLogState.values();
    private SmsLogState smsLogState = null;
    private EmailState[] emailStatelist = EmailState.values();
    private EmailState emailState = null;
	private Page<EmailLog> emailLogPage;
	
	private BigDecimal sBalance = null;
	private BigDecimal eBalance = null;
	
	private String adminUserCheck = null;
	
	private String oper = null;
	
	private String customerIds = null;
	private Page<AdminSendSomeThingTemplate> templatePage;
	
	private String t_titleQuer;
	private String temIds;
	private String t_id;
	private String sendUserNameQuery;
	
	private List<Map> thePerformanceLi = null;
	
	private Boolean canSms;
	private Boolean canEmail;
	
	private int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	
	private String canEmailNum = "";
	private String canSmsNum = "";
	private String acceptCust = "";
	private String businessMan ="";
	private int totalPages;
	
	private String isOpened="";
    
	
	@Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.categoryDao = new SimpleHibernateTemplate<ArticleCategory, Long>(
                sessionfactory, ArticleCategory.class);
        
        this.adminSendSomeThingTemplateDao = new SimpleHibernateTemplate<AdminSendSomeThingTemplate, Long>(sessionfactory,
        		AdminSendSomeThingTemplate.class);
    }
	
	public String templateManager(){
		Map m = new HashMap();
		m.put("t_titleQuer", t_titleQuer);
		m.put("sendTemplateType",sendTemplateType);
		templatePage = new Page<AdminSendSomeThingTemplate>();
		templatePage.setPageNo(pageNo);
		templatePage.setPageSize(pageSize);
		templatePage.setAutoCount(true);	
		templatePage = adminSendSomeThingTemplateService.getAdminSendSomeThingTemplatePage(templatePage,m);
		return "template";
	}
	
	public String getThePerformance(){
		Map m = new HashMap();
		m.put("customer_type", 3);//业务员
		m.put("f_sTime", f_sTime);
		m.put("f_eTime", f_eTime);
		m.put("businessMan", businessMan );
//		m.put("pageSize", pageSize);
//		m.put("pageNo", pageNo);
		thePerformanceLi = customerService.getThePerformance(m);
		return SUCCESS;
	}
	
	public String dispatchCustomerToSomeOne(){
		
//		AdminUser au = null;
		Customer c = null;
		if(adminUserCheck!=null&&!"".equals(adminUserCheck)&&oper!=null&&!"".equals(oper)){//分配操作
			
			Map map=new HashMap();
//		   	au = adminUserService.getAdminUser(adminUserCheck);
		   	c = customerService.getCustomerOrName(adminUserCheck);
		   	if(null==c){//业务员没找到
		   		map.put("message", "业务员不存在");
		         setJsonString(JsonMsgBean.MapToJsonString(map));
		         return AJAXJSON;
		   	}
			
		   	if("adminUserAll".equals(oper)){//分配全部查询的客户给指定业务员 
		   		Map m = new HashMap();
				m.put("f_serch", f_serch);
				m.put("f_serchName", f_serchName);
				m.put("f_starTime", f_starTime);
				m.put("f_endTime", f_endTime);
				m.put("f_orderserch", f_orderserch);
				m.put("stratTime", f_sTime);
				m.put("endTime", f_eTime);
				m.put("usrType", userType);
				m.put("sBalance", sBalance);
				m.put("superior", c.getId());
				m.put("eBalance", eBalance);
				m.put("havenotDispath", true);
				customerService.updateCustomers(m);
			}else if("adminUserPage".equals(oper)||"adminUserSelected".equals(oper)){//分配当前页的客户给指定业务员 
				Map m = new HashMap();
				m.put("superior", c.getId());
				String[] cids = customerIds.split(",");
				m.put("customerIds", Arrays.asList(cids));
				customerService.updateCustomers(m);
			}
		}
		
		Map map = new HashMap();
		map.put("message", "分配成功");
        setJsonString(JsonMsgBean.MapToJsonString(map));
        return AJAXJSON;
	}
	
	public String dispatchCustomer(){//此方法具备分配和查询两个操作，当adminUserCheck不为空时为分配操作
		
		
		
		Map m = new HashMap();
		m.put("havenotDispath", true);
		m.put("sBalance", sBalance);
		m.put("eBalance", eBalance);
		customerPage = new Page<Customer>();
		customerPage.setPageNo(pageNo);
		customerPage.setPageSize(pageSize);
		customerPage.setAutoCount(true);		
		customerPage = customerService.getLotteryCustomerPage(customerPage, f_sTime, f_eTime,
                f_orderserch, f_serch, f_starTime, f_endTime, f_serchName, userType,null,null,m);
		
		return SUCCESS;
	}
	
	public String cheackAdminUser(){
	   	 Map map=new HashMap();
	   	 Customer c = customerService.getCustomerOrName(adminUserCheck);
//	   	AdminUser au = adminUserService.getAdminUser(adminUserCheck);
	   	 if(null==c)
	   	{
	   		 map.put("message", "业务员不存在");
	         setJsonString(JsonMsgBean.MapToJsonString(map));
	    }else if(null==c.getCustomerType()||!CustomerType.BusinessCustomer.equals(c.getCustomerType())){
	    	map.put("message", "该用户不是业务员类型");
	         setJsonString(JsonMsgBean.MapToJsonString(map));
	    } else
	    {
	    	map.put("message", "业务员存在");
	        setJsonString(JsonMsgBean.MapToJsonString(map));
	    }
	   	return AJAXJSON;
	   }
	
	public String delTemplate(){
		Map m = new HashMap();
		String[] temIdss = temIds.split(",");
		m.put("ids", Arrays.asList(temIdss));
		adminSendSomeThingTemplateService.deleteMany(m);
		Map map=new HashMap();
		map.put("message", "删除成功");
        setJsonString(JsonMsgBean.MapToJsonString(map));
		return AJAXJSON;
	}
	

	public String index() {
		smsTemplateList = adminSendSomeThingTemplateDao.createCriteria().add(Restrictions.eq("sendTemplateType", SendTemplateType.短信)).list();
		emailTemplateList = adminSendSomeThingTemplateDao.createCriteria().add(Restrictions.eq("sendTemplateType", SendTemplateType.邮件)).list();
		canEmailNum = sysParamService.getSysParamByName(Constants.CUSTOMER_RECEIVE_EMAIL_FROM_BUSSINESS).getValue();
		canSmsNum = sysParamService.getSysParamByName(Constants.CUSTOMER_RECEIVE_SMS_FROM_BUSSINESS).getValue();
//		AdminUser au = this.getCurAdminUser();
		Customer c = this.getCurCustomer();
		Map map = new HashMap();
//		map.put("adminUser", au);
		map.put("superior", c);
		map.put("sBalance", sBalance);
		map.put("eBalance", eBalance);
		map.put("canEmail", canEmail);
		map.put("canSms", canSms);
		
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		
		Map mm = customerService.getLotteryCustomerPageForBusinessMan( f_sTime, f_eTime,
                f_orderserch, f_serch, f_starTime, f_endTime, f_serchName, userType,null,null,map);
		customerListMap = (List<Map>) MapUtils.getObject(mm,"list");
		totalNum = MapUtils.getIntValue(mm,"total");
		totalPages = (int) Math.ceil(((double)totalNum)/((double)pageSize));
		
		//以下算充值总额和购彩总额
		outMoneySum = walletLogService.getRechargeMon(null, null, c);
		paymentMoenySum = lotteryOrderService.getSumMoneyByCustomer(null, null, c,null);
		
		return SUCCESS;
	}
	
	public String getMyPerformance(){
		return SUCCESS;
	}
	
	public String getMySendSms(){
		Map m = new HashMap();
		m.put("userId", this.getCurCustomer().getId());
		m.put("state", smsLogState);
		m.put("sendSTime", f_sTime);
		m.put("sendETime", f_eTime);
		m.put("acceptCust", acceptCust );
		smsLogPage.setPageNo(pageNo);
		smsLogPage.setPageSize(pageSize);
		smsLogPage.setAutoCount(true);
		smsLogPage = smsLogService.getSmsLogPageByMap(smsLogPage,m);
		
		return SUCCESS;
	}
	
	public String getMySendEmail(){
		Map m = new HashMap();
		m.put("storeId", this.getCurCustomer().getId());//storeId 为发送业务员id
		m.put("state", emailState);
		m.put("sendSTime", sTime);
		m.put("sendETime", eTime);
		m.put("acceptCust", acceptCust);
		m.put("isOpened", isOpened.equals("")?null:(isOpened.equals("1")?true:false));
		Page<EmailLog> page = new Page<EmailLog>();
		page.setAutoCount(true);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		emailLogPage = emailService.findEmailByMap(page, m);
		
		return SUCCESS;
	}
	
	public String getAllEmail(){
		Map m = new HashMap();
		m.put("state", emailState);
		m.put("sendSTime", sTime);
		m.put("sendETime", eTime);
		m.put("sendUserNameQuery", sendUserNameQuery);
		m.put("acceptCust", acceptCust);
		
		Page<EmailLog> page = new Page<EmailLog>();
		page.setAutoCount(true);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		emailLogPage = emailService.findEmailByMap(page, m);
		
		return SUCCESS;
	}
	
	public String addT() {
		AdminSendSomeThingTemplate astt = new AdminSendSomeThingTemplate();
		if(t_content!=null&&!"".equals(t_content)&&t_content.indexOf("【一彩票网】")==-1&&sendTemplateType!=null&&sendTemplateType==SendTemplateType.短信){
			t_content = "【一彩票网】"+t_content;
		}
		astt.setContent(t_content);
		astt.setDescription(t_description);
		astt.setTitle(t_title);
		astt.setSendTemplateType(sendTemplateType);
		if(t_id!=null&&!"".equals(t_id)){
			astt.setId(Long.parseLong(t_id));
		}
		adminSendSomeThingTemplateDao.save(astt);
		
		return templateManager();
	}
	
	public String getTemplate() {		
		Map map = new HashMap();		
		adminSendSomeThingTemplate = adminSendSomeThingTemplateDao.get(templateId);
		map.put("content", adminSendSomeThingTemplate.getContent());
		map.put("title", adminSendSomeThingTemplate.getTitle());
		setJsonString(JsonMsgBean.MapToJsonString(map));
		
		return AJAXJSON;
	}
	
	public String sendSMS(){
		
		String[] pns = phoneNums.split(",");
		String[] sas = smsArgument.split(",");
		String[] pis = phoneIds.split(",");
		
		for(int j=0;j<pns.length;j++){
			SmsLog sms = new SmsLog();
			
			for(int i=0;i<sas.length;i++){
				int ii = smsContent.indexOf("{"+(i+1)+"}");
				if(ii!=-1){
					smsContent = smsContent.replace("{"+(i+1)+"}", sas[i]);
				}
			}
			sms.setContent(smsContent);
			sms.setMobile(pns[j]);
			sms.setRetryCount(0);
			sms.setSendState(SmsSendState.IMMEDIATELY);
			sms.setState(SmsLogState.WAITING);
			 sms.setType(SmsLogType.COMMON);
			 sms.setSendTime(Calendar.getInstance());
			 sms.setSendPriority(3);
			 sms.setUserId(this.getCurCustomer().getId().toString());
			 sms.setSendUserName(this.getCurCustomer().getNickName());
			 sms.setSmsSendType(SmsSendType.EMAY);
			 Customer c = customerService.findById(Long.parseLong(pis[j]));
			 sms.setCustomer(c);
			 smsLogService.saveSmsLog(sms);
			 
			 c.setSmsAccept(getTheAccept(c.getSmsAccept()));
			 customerService.save(c);
			 
		}
		
		
		Map map = new HashMap();		
		
		map.put("msg", "批量发送成功");
		setJsonString(JsonMsgBean.MapToJsonString(map));
		
		
		
		return AJAXJSON;
	}
	
	public static String getTheAccept(String initAccept){
		if(initAccept==null||"".equals(initAccept)){
			initAccept = "0000000000000000000000000000000";
		}
		Calendar cal=Calendar.getInstance();//使用日历类
		  int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		  int i = initAccept.charAt(day-1);
		  StringBuffer sb = new StringBuffer(initAccept.substring(0, day-1)+(i-48+1)+initAccept.substring(day));
		  
		return sb.toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println(getTheAccept("0003000000000000000000000000000"));
	}
	
	public String sendEmail(){
		
		String[] es = emails.split(",");
		String[] eas = emailArgument.split(",");
		String[] eis = emailIds.split(",");
		String[] enns = nickNames.split(",");
		
		adminSendSomeThingTemplate = adminSendSomeThingTemplateDao.get(emailTemplate.getId());
		
		emailContent = adminSendSomeThingTemplate.getContent();
		
		for(int j=0;j<es.length;j++){
			
			for(int i=0;i<eas.length;i++){
				int ii = emailContent.indexOf("{"+(i+1)+"}");
				if(ii!=-1){
					emailContent = emailContent.replace("{"+(i+1)+"}", eas[i]);
				}
			}
			
			
			EmailLog emailLog = new EmailLog();
			
			emailLog.setTitle(emailTitle);
			emailLog.setStoreId(this.getCurCustomer().getId().toString());
			emailLog.setEmail(es[j]);
			emailLog.setSendLevel(5);
			emailLog.setType(EmailType.BUSINESS);
			emailLog.setState(EmailState.NOTSEND);//
			emailLog.setSendTime(new Date());
			emailLog.setRetryCount(0);
			emailLog.setUsername(enns[j]);
			emailLog.setSendUserName(this.getCurCustomer().getNickName());
			emailLog.setContent(emailContent);
			emailService.saveOrUpdate(emailLog);
			Customer c = customerService.getCustomerOrName(enns[j].substring(1, enns[j].length()-1));//因为enns[j] 的值是[*]的格式
			c.setEmailAccept(getTheAccept(c.getEmailAccept()));
			customerService.save(c);
		}
		
		
		Map map = new HashMap();		
		
		map.put("msg", "批量发送成功");
		setJsonString(JsonMsgBean.MapToJsonString(map));
		
		
		
		return AJAXJSON;
	}

	
	
	public String getIsOpened()
	{
		return isOpened;
	}

	public void setIsOpened(String isOpened)
	{
		this.isOpened = isOpened;
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public int getTotalNum()
	{
		return totalNum;
	}

	public void setTotalNum(int totalNum)
	{
		this.totalNum = totalNum;
	}

	public List<Map> getCustomerListMap()
	{
		return customerListMap;
	}

	public void setCustomerListMap(List<Map> customerListMap)
	{
		this.customerListMap = customerListMap;
	}

	public String getBusinessMan()
	{
		return businessMan;
	}

	public void setBusinessMan(String businessMan)
	{
		this.businessMan = businessMan;
	}

	public String getAcceptCust()
	{
		return acceptCust;
	}

	public void setAcceptCust(String acceptCust)
	{
		this.acceptCust = acceptCust;
	}

	public String getCanEmailNum()
	{
		return canEmailNum;
	}

	public void setCanEmailNum(String canEmailNum)
	{
		this.canEmailNum = canEmailNum;
	}

	public String getCanSmsNum()
	{
		return canSmsNum;
	}

	public void setCanSmsNum(String canSmsNum)
	{
		this.canSmsNum = canSmsNum;
	}

	public int getToday()
	{
		return today;
	}

	public void setToday(int today)
	{
		this.today = today;
	}

	public Boolean getCanSms()
	{
		return canSms;
	}

	public void setCanSms(Boolean canSms)
	{
		this.canSms = canSms;
	}

	public Boolean getCanEmail()
	{
		return canEmail;
	}

	public void setCanEmail(Boolean canEmail)
	{
		this.canEmail = canEmail;
	}

	public List getThePerformanceLi()
	{
		return thePerformanceLi;
	}

	public void setThePerformanceLi(List thePerformanceLi)
	{
		this.thePerformanceLi = thePerformanceLi;
	}

	public String getSendUserNameQuery()
	{
		return sendUserNameQuery;
	}

	public void setSendUserNameQuery(String sendUserNameQuery)
	{
		this.sendUserNameQuery = sendUserNameQuery;
	}

	public String getT_id()
	{
		return t_id;
	}

	public void setT_id(String t_id)
	{
		this.t_id = t_id;
	}

	public String getT_titleQuer()
	{
		return t_titleQuer;
	}

	public void setT_titleQuer(String t_titleQuer)
	{
		this.t_titleQuer = t_titleQuer;
	}

	public String getTemIds()
	{
		return temIds;
	}

	public void setTemIds(String temIds)
	{
		this.temIds = temIds;
	}

	public Page<AdminSendSomeThingTemplate> getTemplatePage()
	{
		return templatePage;
	}

	public void setTemplatePage(Page<AdminSendSomeThingTemplate> templatePage)
	{
		this.templatePage = templatePage;
	}

	public String getCustomerIds()
	{
		return customerIds;
	}

	public void setCustomerIds(String customerIds)
	{
		this.customerIds = customerIds;
	}

	public String getOper()
	{
		return oper;
	}

	public void setOper(String oper)
	{
		this.oper = oper;
	}

	public String getAdminUserCheck()
	{
		return adminUserCheck;
	}

	public void setAdminUserCheck(String adminUserCheck)
	{
		this.adminUserCheck = adminUserCheck;
	}

	public BigDecimal getsBalance()
	{
		return sBalance;
	}


	public void setsBalance(BigDecimal sBalance)
	{
		this.sBalance = sBalance;
	}


	public BigDecimal geteBalance()
	{
		return eBalance;
	}


	public void seteBalance(BigDecimal eBalance)
	{
		this.eBalance = eBalance;
	}


	public Date getsTime()
	{
		return sTime;
	}


	public void setsTime(Date sTime)
	{
		this.sTime = sTime;
	}


	public Date geteTime()
	{
		return eTime;
	}


	public void seteTime(Date eTime)
	{
		this.eTime = eTime;
	}


	public EmailState[] getEmailStatelist()
	{
		return emailStatelist;
	}


	public void setEmailStatelist(EmailState[] emailStatelist)
	{
		this.emailStatelist = emailStatelist;
	}


	public EmailState getEmailState()
	{
		return emailState;
	}


	public void setEmailState(EmailState emailState)
	{
		this.emailState = emailState;
	}


	public Page<EmailLog> getEmailLogPage()
	{
		return emailLogPage;
	}


	public void setEmailLogPage(Page<EmailLog> emailLogPage)
	{
		this.emailLogPage = emailLogPage;
	}


	public String getNickNames()
	{
		return nickNames;
	}


	public void setNickNames(String nickNames)
	{
		this.nickNames = nickNames;
	}


	public SmsLogState[] getSmsLogStateList()
	{
		return smsLogStateList;
	}


	public void setSmsLogStateList(SmsLogState[] smsLogStateList)
	{
		this.smsLogStateList = smsLogStateList;
	}


	public SmsLogState getSmsLogState()
	{
		return smsLogState;
	}


	public void setSmsLogState(SmsLogState smsLogState)
	{
		this.smsLogState = smsLogState;
	}


	public Page<SmsLog> getSmsLogPage()
	{
		return smsLogPage;
	}


	public void setSmsLogPage(Page<SmsLog> smsLogPage)
	{
		this.smsLogPage = smsLogPage;
	}


	public BigDecimal getOutMoneySum()
	{
		return outMoneySum;
	}


	public void setOutMoneySum(BigDecimal outMoneySum)
	{
		this.outMoneySum = outMoneySum;
	}


	public BigDecimal getPaymentMoenySum()
	{
		return paymentMoenySum;
	}


	public void setPaymentMoenySum(BigDecimal paymentMoenySum)
	{
		this.paymentMoenySum = paymentMoenySum;
	}


	public String getEmailTitle()
	{
		return emailTitle;
	}


	public void setEmailTitle(String emailTitle)
	{
		this.emailTitle = emailTitle;
	}


	public String getEmailContent()
	{
		return emailContent;
	}


	public void setEmailContent(String emailContent)
	{
		this.emailContent = emailContent;
	}


	public String getEmails()
	{
		return emails;
	}


	public void setEmails(String emails)
	{
		this.emails = emails;
	}


	public String getEmailArgument()
	{
		return emailArgument;
	}


	public void setEmailArgument(String emailArgument)
	{
		this.emailArgument = emailArgument;
	}


	public String getEmailIds()
	{
		return emailIds;
	}


	public void setEmailIds(String emailIds)
	{
		this.emailIds = emailIds;
	}


	public String getSmsContent()
	{
		return smsContent;
	}


	public void setSmsContent(String smsContent)
	{
		this.smsContent = smsContent;
	}


	public String getPhoneIds()
	{
		return phoneIds;
	}


	public void setPhoneIds(String phoneIds)
	{
		this.phoneIds = phoneIds;
	}


	public String getPhoneNums()
	{
		return phoneNums;
	}


	public void setPhoneNums(String phoneNums)
	{
		this.phoneNums = phoneNums;
	}


	public String getSmsArgument()
	{
		return smsArgument;
	}


	public void setSmsArgument(String smsArgument)
	{
		this.smsArgument = smsArgument;
	}


	public Long getTemplateId()
	{
		return templateId;
	}


	public void setTemplateId(Long templateId)
	{
		this.templateId = templateId;
	}


	public List<AdminSendSomeThingTemplate> getSmsTemplateList()
	{
		return smsTemplateList;
	}


	public void setSmsTemplateList(List<AdminSendSomeThingTemplate> smsTemplateList)
	{
		this.smsTemplateList = smsTemplateList;
	}


	public AdminSendSomeThingTemplate getSmsTemplate()
	{
		return smsTemplate;
	}


	public void setSmsTemplate(AdminSendSomeThingTemplate smsTemplate)
	{
		this.smsTemplate = smsTemplate;
	}


	public List<AdminSendSomeThingTemplate> getEmailTemplateList()
	{
		return emailTemplateList;
	}


	public void setEmailTemplateList(List<AdminSendSomeThingTemplate> emailTemplateList)
	{
		this.emailTemplateList = emailTemplateList;
	}


	public AdminSendSomeThingTemplate getEmailTemplate()
	{
		return emailTemplate;
	}


	public void setEmailTemplate(AdminSendSomeThingTemplate emailTemplate)
	{
		this.emailTemplate = emailTemplate;
	}


	public String getT_title()
	{
		return t_title;
	}


	public void setT_title(String t_title)
	{
		this.t_title = t_title;
	}


	public String getT_content()
	{
		return t_content;
	}


	public void setT_content(String t_content)
	{
		this.t_content = t_content;
	}


	public String getT_description()
	{
		return t_description;
	}


	public void setT_description(String t_description)
	{
		this.t_description = t_description;
	}


	public SendTemplateType[] getSendTemplateTypeList()
	{
		return sendTemplateTypeList;
	}


	public void setSendTemplateTypeList(SendTemplateType[] sendTemplateTypeList)
	{
		this.sendTemplateTypeList = sendTemplateTypeList;
	}


	public SendTemplateType getSendTemplateType()
	{
		return sendTemplateType;
	}


	public void setSendTemplateType(SendTemplateType sendTemplateType)
	{
		this.sendTemplateType = sendTemplateType;
	}


	public List<AdminSendSomeThingTemplate> getAdminSendSomeThingTemplateList()
	{
		return adminSendSomeThingTemplateList;
	}


	public void setAdminSendSomeThingTemplateList(List<AdminSendSomeThingTemplate> adminSendSomeThingTemplateList)
	{
		this.adminSendSomeThingTemplateList = adminSendSomeThingTemplateList;
	}


	public AdminSendSomeThingTemplate getAdminSendSomeThingTemplate()
	{
		return adminSendSomeThingTemplate;
	}


	public void setAdminSendSomeThingTemplate(AdminSendSomeThingTemplate adminSendSomeThingTemplate)
	{
		this.adminSendSomeThingTemplate = adminSendSomeThingTemplate;
	}


	public Page<Customer> getCustomerPage()
	{
		return customerPage;
	}


	public void setCustomerPage(Page<Customer> customerPage)
	{
		this.customerPage = customerPage;
	}


	public int getPageNo()
	{
		return pageNo;
	}


	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}


	public int getPageSize()
	{
		return pageSize;
	}


	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}


	public Calendar getF_sTime()
	{
		return f_sTime;
	}


	public void setF_sTime(Calendar f_sTime)
	{
		this.f_sTime = f_sTime;
	}


	public Calendar getF_eTime()
	{
		return f_eTime;
	}


	public void setF_eTime(Calendar f_eTime)
	{
		this.f_eTime = f_eTime;
	}


	public Calendar getF_starTime()
	{
		return f_starTime;
	}


	public void setF_starTime(Calendar f_starTime)
	{
		this.f_starTime = f_starTime;
	}


	public String getF_orderserch()
	{
		return f_orderserch;
	}


	public void setF_orderserch(String f_orderserch)
	{
		this.f_orderserch = f_orderserch;
	}


	public String getF_serch()
	{
		return f_serch;
	}


	public void setF_serch(String f_serch)
	{
		this.f_serch = f_serch;
	}


	public UserType getUserType()
	{
		return userType;
	}


	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}


	public String getF_serchName()
	{
		return f_serchName;
	}


	public void setF_serchName(String f_serchName)
	{
		this.f_serchName = f_serchName;
	}


	public Calendar getF_endTime()
	{
		return f_endTime;
	}


	public void setF_endTime(Calendar f_endTime)
	{
		this.f_endTime = f_endTime;
	}


	public UserType[] getUserTypes()
	{
		return userTypes;
	}


	public void setUserTypes(UserType[] userTypes)
	{
		this.userTypes = userTypes;
	}

}
