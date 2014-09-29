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

import org.hibernate.annotations.Index;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.MoneyChannel;

/** 冲值请求 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_payment_request")
@SequenceGenerator(name = "S_PaymentRequest", allocationSize = 1, initialValue = 1, sequenceName = "S_PaymentRequest")
public class PaymentRequest extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_PaymentRequest")
    private Long id;

    /** 冲值业务编号 */
    @Column(nullable = false, unique = true)
    @Index(name = "serialNumberIndex")
    private String serialNumber;

    /** 冲值帐号 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 冲值金额 */
    @Column(nullable = false)
    private BigDecimal money;

    /** 冲值结果 true 为已处理 false 等待处理 */
    private boolean finish = false;

    /** 充值的银行 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bank")
    private Bank bank;

    /** 支付渠道 */
    private MoneyChannel channel;

    /** 手续费 */
    private BigDecimal feeMoney;

    /** 冲值时间 */
    @Column(name = "pay_time")
    private Calendar payTime;

    /** 代购客户,合买则为null */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AdminUser user;

    private Calendar optionTime;

    public PaymentRequest()
    {
    };

    /**
     * @param serialNumber
     *            编号
     * @param customer
     *            客户
     * @param money
     *            金额
     */
    public PaymentRequest(String serialNumber, Customer customer,
            BigDecimal money, Bank bank, MoneyChannel channel,
            BigDecimal feeMoney)
    {
        super();
        this.serialNumber = serialNumber;
        this.customer = customer;
        this.money = money;
        this.finish = false;
        this.bank = bank;
        this.channel = channel;
        this.feeMoney = feeMoney;
        this.payTime = Calendar.getInstance();
    }

    public PaymentRequest(String serialNumber, Customer customer,
            BigDecimal money, MoneyChannel channel, AdminUser user)
    {
        super();
        this.serialNumber = serialNumber;
        this.customer = customer;
        this.money = money;
        this.finish = true;
        this.user = user;
        this.channel = channel;
        this.payTime = Calendar.getInstance();
    }

    public Calendar getOptionTime()
    {
        return optionTime;
    }

    public void setOptionTime(Calendar optionTime)
    {
        this.optionTime = optionTime;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public boolean isFinish()
    {
        return finish;
    }

    public void setFinish(boolean finish)
    {
        this.finish = finish;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Bank getBank()
    {
        return bank;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    public MoneyChannel getChannel()
    {
        return channel;
    }

    public void setChannel(MoneyChannel channel)
    {
        this.channel = channel;
    }

    public BigDecimal getFeeMoney()
    {
        return feeMoney;
    }

    public void setFeeMoney(BigDecimal feeMoney)
    {
        this.feeMoney = feeMoney;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public Calendar getPayTime()
    {
        return payTime;
    }

    public void setPayTime(Calendar payTime)
    {
        this.payTime = payTime;
    }

    public AdminUser getUser()
    {
        return user;
    }

    public void setUser(AdminUser user)
    {
        this.user = user;
    }

}
