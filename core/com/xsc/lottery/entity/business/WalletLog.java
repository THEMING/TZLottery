package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.WalletLogType;

/** 钱包流水 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_wallet_log")
@SequenceGenerator(name = "S_WalletLog", allocationSize = 1, initialValue = 1, sequenceName = "S_WalletLog")
public class WalletLog extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_WalletLog")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    /** 交易类型 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "business_type", nullable = false)
    private BusinessType businessType;

    /** 发生交易后的当前金额(交易前余额) */
    private BigDecimal currentMoney;

    /** 发生交易后的当前冻结金额 */
    private BigDecimal currentFreezeMoney;

    /** 交易进入金额 */
    private BigDecimal inMoney;

    /** 交易出去金额 */
    private BigDecimal outMoney;

    /** 增加冻结金额 */
    private BigDecimal inFreezeMoney;

    /** 减去冻结金额 */
    private BigDecimal outFreezeMoney;

    /** 交易时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Calendar time;

    /** 描述 */
    private String description;

    private WalletLogType type;

    /** 交易编号 */
    private String serialNumber;
    
    private String pid;
    
    private String adid;
    
    private String cpsStaus="0";

    public WalletLog()
    {
    }

    /**
     * @param businessType
     *            交易类型
     * @param inMoney
     *            帐户增加金额
     * @param outMoney
     *            帐户减少金额
     * @param inFreezeMoney
     *            帐户增加冻结金额
     * @param outFreezeMoney
     *            帐户减少冻结金额
     * @param description
     *            描述
     */
    public WalletLog(BusinessType businessType, BigDecimal inMoney,
            BigDecimal outMoney, BigDecimal inFreezeMoney,
            BigDecimal outFreezeMoney, String description, WalletLogType type,
            String serialNumber)
    {
        super();
        this.businessType = businessType;
        this.inMoney = inMoney;
        this.outMoney = outMoney;
        this.inFreezeMoney = inFreezeMoney;
        this.outFreezeMoney = outFreezeMoney;
        this.time = Calendar.getInstance();
        this.description = description;
        this.type = type;
        this.serialNumber = serialNumber;
    }

    public BigDecimal getCurrentTotalMoney()
    {
        return currentMoney.add(currentFreezeMoney);
    }

    public BusinessType getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType)
    {
        this.businessType = businessType;
    }

    public BigDecimal getInMoney()
    {
        return inMoney;
    }

    public void setInMoney(BigDecimal inMoney)
    {
        this.inMoney = inMoney;
    }

    public BigDecimal getOutMoney()
    {
        return outMoney;
    }

    public void setOutMoney(BigDecimal outMoney)
    {
        this.outMoney = outMoney;
    }

    public Calendar getTime()
    {
        return time;
    }

    public void setTime(Calendar time)
    {
        this.time = time;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public Wallet getWallet()
    {
        return wallet;
    }

    public void setWallet(Wallet wallet)
    {
        this.wallet = wallet;
    }

    public BigDecimal getInFreezeMoney()
    {
        return inFreezeMoney;
    }

    public void setInFreezeMoney(BigDecimal inFreezeMoney)
    {
        this.inFreezeMoney = inFreezeMoney;
    }

    public BigDecimal getOutFreezeMoney()
    {
        return outFreezeMoney;
    }

    public void setOutFreezeMoney(BigDecimal outFreezeMoney)
    {
        this.outFreezeMoney = outFreezeMoney;
    }

    public BigDecimal getCurrentMoney()
    {
        return currentMoney;
    }

    public void setCurrentMoney(BigDecimal currentMoney)
    {
        this.currentMoney = currentMoney;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getCurrentFreezeMoney()
    {
        return currentFreezeMoney;
    }

    public void setCurrentFreezeMoney(BigDecimal currentFreezeMoney)
    {
        this.currentFreezeMoney = currentFreezeMoney;
    }

    public WalletLogType getType()
    {
        return type;
    }

    public void setType(WalletLogType type)
    {
        this.type = type;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCpsStaus() {
		return cpsStaus;
	}

	public void setCpsStaus(String cpsStaus) {
		this.cpsStaus = cpsStaus;
	}
}
