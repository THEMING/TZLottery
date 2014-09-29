package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.xsc.lottery.entity.BaseObject;

/** 钱包 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_wallet")
@SequenceGenerator(name = "S_Wallet", allocationSize = 1, initialValue = 1, sequenceName = "S_Walletr")
public class Wallet extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Wallet")
    private Long id;

    @OneToOne(mappedBy = "wallet", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH })
    private Customer customer;

    /** 剩余金额 */
    private BigDecimal balance = BigDecimal.ZERO;

    /** 冻结金额 */
    private BigDecimal freezeMoney = BigDecimal.ZERO;

    /** MD5摘要 */
    private String summary;

    /** 0:正常状态1:冻结状态 **/
    private int status = 0;

    /** 历史中奖合计 */
    private BigDecimal historyPrize = BigDecimal.ZERO;

    @Version
    private int version;

    public Customer getCustomer()
    {
        return customer;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public BigDecimal getTotalMoney()
    {
        return balance.add(freezeMoney);
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public BigDecimal getFreezeMoney()
    {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney)
    {
        this.freezeMoney = freezeMoney;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public BigDecimal getHistoryPrize()
    {
        return historyPrize;
    }

    public void setHistoryPrize(BigDecimal historyPrize)
    {
        this.historyPrize = historyPrize;
    }

    public Long getId()
    {
        return id;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }
}
