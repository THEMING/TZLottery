package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.CredentType;
import com.xsc.lottery.entity.enumerate.CustomerStatus;
import com.xsc.lottery.entity.enumerate.CustomerType;
import com.xsc.lottery.entity.enumerate.RegChannel;
import com.xsc.lottery.entity.enumerate.RegSource;
import com.xsc.lottery.entity.enumerate.UserType;

@SuppressWarnings("serial")
@Entity
@Table(name = "business_customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Customer", allocationSize = 1, initialValue = 1, sequenceName = "S_Customer")
public class Customer extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "customerType")
    @Index(name = "customerTypeIndex")
    private CustomerType customerType;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "adminUser_id")
    private AdminUser adminUser;

    @Column
    private String realName;

    /** 证件类型 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "credentType")
    private CredentType credentType;

    /** 证件号码 */
    @Column
    private String credentNo;

    @Column(nullable = false)
    private String email;

    @Column
    private String mobileNo;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 发卡银行 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bank")
    private Bank bank;

    /** 银行支行 */
    private String subbranch;

    /** 开户名 */
    @Column(name = "bank_name")
    private String bankName;

    /** 银行账号 */
    private String bankNumber;

    /** 注册时间 */
    @Column(nullable = false)
    private Calendar registerTime;

    /** 最后登录时间 */
    private Calendar lastLoginTime;

    /** 登录次数 */
    private Integer loginNum = 0;

    /** 最后登录IP */
    private String customerIp;

    /** 第三方用户ID */
    private String user3_id;
    
    private String openId;

    private UserType usrType = UserType.本地注册用户;

    private int old = 0;

    @OneToOne(cascade = { CascadeType.ALL }, optional = false)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    /** 活动获得金额 单位 以 分 来计算 */
    private Long ployAccur = new Long(0);

    /** 活动消费金额 单位 以 分 来计算 */
    private Long ployConsumed = new Long(0);

    /** 用户累计中奖金额 */
    @Column(name = "all_win_money")
    private BigDecimal allWinMoney = BigDecimal.ZERO;
    
    /**
     * 是否绑定手机
     */
    private String  bound ="nobound";
    
    /**
     * 验证码
     */
    private String  yanzhenma ="";
    
    /**
     * 密保问题
     */
    private String  question ="";
    /** 上级推荐人*/
    @ManyToOne
    @JoinColumn(name = "superior_id")
    private Customer superior;
    
    /** 上上级推荐人*/
    @ManyToOne
    @JoinColumn(name = "ssuperior_id")
    private Customer ssuperior;
    
    /** 提成方案*/
    @ManyToOne
    @JoinColumn(name = "commission_id")
    private CustomerCommission commission;
    
    /**
     * 推广渠道
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id",nullable = true)
    private SpreadChannel channel;
    
    /**
     * 是否已申请推广
     */
    private Boolean isApply;
    
    /**
     * 审核是否通过  0-待审核  1-审核通过  2-审核未通过
     */
    private Integer isPass;
    
    /**
     * 密保答案
     */
    private String ask;
    
    /**
     * 用户状态
     */
    @Enumerated(EnumType.ORDINAL)
    private CustomerStatus status;
    
    /**
     * 注册来源
     */
    @Enumerated(EnumType.ORDINAL)
    private RegSource regSource;
    
    /**
     * 注册渠道
     */
    @Enumerated(EnumType.ORDINAL)
    private RegChannel regChannel;
    
    /**
     * 收到唤醒邮件的数目
     */
    private Integer wakeUpEmailNum = 0;
    
    /**
     * 用户被业务员发送邮件的次数 
     * 数据格式初始为31个0，对应每个月的31天
     * 当业务员发送了推销邮件 当天对应的位置加一 每个月一号清空*/
    private String emailAccept="0000000000000000000000000000000";
    
    /**
     * 用户被业务员发送短信的次数 
     * 数据格式初始为31个0，对应每个月的31天
     * 当业务员发送了推销短信 当天对应的位置加一 每个月一号清空*/
    private String smsAccept="0000000000000000000000000000000";//"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    
    public Integer getWakeUpEmailNum()
	{
		return wakeUpEmailNum;
	}

	public void setWakeUpEmailNum(Integer wakeUpEmailNum)
	{
		this.wakeUpEmailNum = wakeUpEmailNum;
	}

	public String getEmailAccept()
	{
		return emailAccept;
	}

	public void setEmailAccept(String emailAccept)
	{
		this.emailAccept = emailAccept;
	}

	public String getSmsAccept()
	{
		return smsAccept;
	}

	public void setSmsAccept(String smsAccept)
	{
		this.smsAccept = smsAccept;
	}

	public AdminUser getAdminUser()
	{
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser)
	{
		this.adminUser = adminUser;
	}

	public CustomerType getCustomerType()
	{
		return customerType;
	}

	public void setCustomerType(CustomerType customerType)
	{
		this.customerType = customerType;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public CustomerCommission getCommission() {
		return commission;
	}

	public void setCommission(CustomerCommission commission) {
		this.commission = commission;
	}

	/** 一级推荐提成比例*/
    private BigDecimal superRatio = BigDecimal.valueOf(0.002);
    
    /** 一级提成总金额*/
    private BigDecimal superCommission = BigDecimal.valueOf(0);
    
    /** 二级推荐提成比例*/
    private BigDecimal ssuperRatio = BigDecimal.valueOf(0.001);
    
    /** 二级提成总金额*/
    private BigDecimal ssuperCommission = BigDecimal.valueOf(0);
    
	public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public CredentType getCredentType()
    {
        return credentType;
    }

    public void setCredentType(CredentType credentType)
    {
        this.credentType = credentType;
    }

    public String getCredentNo()
    {
        return credentNo;
    }

    public String getCredentNoSecrecy()
    {
        return credentNo.substring(0, credentNo.length() - 6) + "******";
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

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public Calendar getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(Calendar registerTime)
    {
        this.registerTime = registerTime;
    }

    public Calendar getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Calendar lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Wallet getWallet()
    {
        return wallet;
    }

    public void setWallet(Wallet wallet)
    {
        this.wallet = wallet;
    }

    public Long getId()
    {
        return id;
    }

    public BigDecimal getAllWinMoney()
    {
        return allWinMoney;
    }

    public void setAllWinMoney(BigDecimal allWinMoney)
    {
        this.allWinMoney = allWinMoney;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPloyConsumed()
    {
        return ployConsumed;
    }

    public void setPloyConsumed(Long ployConsumed)
    {
        this.ployConsumed = ployConsumed;
    }

    public Long getPloyAccur()
    {
        return ployAccur;
    }

    public void setPloyAccur(Long ployAccur)
    {
        this.ployAccur = ployAccur;
    }

    public void addPloyAccur(BigDecimal ployAccur)
    {
        if (null == this.ployAccur)
            this.ployAccur = new Long(0);
        this.ployAccur = this.ployAccur
                + ployAccur.multiply(BigDecimal.valueOf(100)).longValue();
    }

    public void addPloyConsumed(BigDecimal ployAccur)
    {
        if (null == this.ployConsumed)
            this.ployAccur = new Long(0);
        this.ployConsumed = this.ployConsumed
                + ployAccur.multiply(BigDecimal.valueOf(100)).longValue();
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Bank getBank()
    {
        return bank;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    public String getBankNumber()
    {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber)
    {
        this.bankNumber = bankNumber;
    }

    public String getSubbranch()
    {
        return subbranch;
    }

    public void setSubbranch(String subbranch)
    {
        this.subbranch = subbranch;
    }

    public String getCustomerIp()
    {
        return customerIp;
    }

    public void setCustomerIp(String customerIp)
    {
        this.customerIp = customerIp;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public Integer getLoginNum()
    {
        return loginNum;
    }

    public void addLoginNum(Integer loginNum)
    {
        if (this.loginNum == null)
            this.loginNum = 0;
        this.loginNum += loginNum;
    }

    public UserType getUsrType()
    {
        return usrType;
    }

    public void setUsrType(UserType usrType)
    {
        this.usrType = usrType;
    }

    public void setLoginNum(Integer loginNum)
    {
        this.loginNum = loginNum;
    }

    public String getUser3_id()
    {
        return user3_id;
    }

    public void setUser3_id(String user3Id)
    {
        user3_id = user3Id;
    }

    public int getOld()
    {
        return old;
    }

    public void setOld(int old)
    {
        this.old = old;
    }
    
    public Customer getSuperior()
	{
		return superior;
	}

	public void setSuperior(Customer superior)
	{
		this.superior = superior;
	}

	public Customer getSsuperior()
	{
		return ssuperior;
	}

	public void setSsuperior(Customer ssuperior)
	{
		this.ssuperior = ssuperior;
	}

	public BigDecimal getSuperRatio()
	{
		return superRatio;
	}

	public void setSuperRatio(BigDecimal superRatio)
	{
		this.superRatio = superRatio;
	}

	public BigDecimal getSsuperRatio()
	{
		return ssuperRatio;
	}

	public void setSsuperRatio(BigDecimal ssuperRatio)
	{
		this.ssuperRatio = ssuperRatio;
	}

	public BigDecimal getSuperCommission() {
		return superCommission;
	}

	public void setSuperCommission(BigDecimal superCommission) {
		this.superCommission = superCommission;
	}

	public BigDecimal getSsuperCommission() {
		return ssuperCommission;
	}

	public void setSsuperCommission(BigDecimal ssuperCommission) {
		this.ssuperCommission = ssuperCommission;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getYanzhenma() {
		return yanzhenma;
	}

	public void setYanzhenma(String yanzhenma) {
		this.yanzhenma = yanzhenma;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public SpreadChannel getChannel() {
		return channel;
	}

	public void setChannel(SpreadChannel channel) {
		this.channel = channel;
	}

	public Boolean getIsApply() {
		return isApply;
	}

	public void setIsApply(Boolean isApply) {
		this.isApply = isApply;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public CustomerStatus getStatus()
	{
		return status;
	}

	public void setStatus(CustomerStatus status)
	{
		this.status = status;
	}

	public RegSource getRegSource()
	{
		return regSource;
	}

	public void setRegSource(RegSource regSource)
	{
		this.regSource = regSource;
	}

	public RegChannel getRegChannel()
	{
		return regChannel;
	}

	public void setRegChannel(RegChannel regChannel)
	{
		this.regChannel = regChannel;
	}
	
}
