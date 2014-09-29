package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.enumerate.BackMoneyStatus;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.MoneyChannel;

/** 提款申请 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_back_money_request")
@SequenceGenerator(name = "S_BackMoneyRequest", allocationSize = 1, initialValue = 1, sequenceName = "S_BackMoneyRequest")
public class BackMoneyRequest extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_BackMoneyRequest")
    private Long id;

    /** 用户 */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /** 提款用户名 */
    @Column(nullable = false)
    private String realName;

    /** 申请时间 */
    @Column(name = "apply_time", nullable = false)
    private Calendar applyTime;

    /** 返款时间 */
    @Column(name = "send_time")
    private Calendar sendTime;

    /** 银行 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bank", nullable = false)
    private Bank bank;

    /** 银行卡号 */
    @Column(name = "bank_no", nullable = false)
    private String bankCard;

    /** 用户提款金额 */
    @Column(name = "money", nullable = false)
    private BigDecimal money;

    /** 手续费 */
    @Column(name = "fee_money", nullable = false)
    private BigDecimal feeMoney;

    /** 开户行 */
    @Column(name = "open_space")
    private String openSpace;

    /** 支付渠道 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "channel", nullable = false)
    private MoneyChannel channel = MoneyChannel.支付宝;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AdminUser user;

    /** 状态 */
    private BackMoneyStatus status = BackMoneyStatus.待审核;
    
    /**提款失败原因的说明*/
    private String memo;
    
    /**城市对应的编码*/
    private String code;

    public BackMoneyRequest()
    {
    }

    public BackMoneyRequest(Customer customer, String realName, Bank bank,
            String bankCard, BigDecimal money, String openSpace, String code)
    {
        super();
        this.realName = realName;
        this.applyTime = Calendar.getInstance();
        this.realName = customer.getRealName();
        this.customer = customer;
        this.bank = bank;
        this.bankCard = bankCard;
        this.money = money;
        this.openSpace = openSpace;
        this.feeMoney = BigDecimal.ZERO;
        this.code = code;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Calendar getApplyTime()
    {
        return applyTime;
    }

    public void setApplyTime(Calendar applyTime)
    {
        this.applyTime = applyTime;
    }

    public Bank getBank()
    {
        return bank;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    public String getBankCard()
    {
        return bankCard;
    }

    public void setBankCard(String bankCard)
    {
        this.bankCard = bankCard;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public BigDecimal getFeeMoney()
    {
        return feeMoney;
    }

    public void setFeeMoney(BigDecimal feeMoney)
    {
        this.feeMoney = feeMoney;
    }

    public String getOpenSpace()
    {
        return openSpace;
    }

    public void setOpenSpace(String openSpace)
    {
        this.openSpace = openSpace;
    }

    public MoneyChannel getChannel()
    {
        return channel;
    }

    public void setChannel(MoneyChannel channel)
    {
        this.channel = channel;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public BackMoneyStatus getStatus()
    {
        return status;
    }

    public void setStatus(BackMoneyStatus status)
    {
        this.status = status;
    }

    public AdminUser getUser()
    {
        return user;
    }

    public void setUser(AdminUser user)
    {
        this.user = user;
    }

    public Calendar getSendTime()
    {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime)
    {
        this.sendTime = sendTime;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
