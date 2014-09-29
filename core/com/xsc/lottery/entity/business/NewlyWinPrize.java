package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayStatus;

/** 最新中奖 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_WinPrize")
@SequenceGenerator(name = "S_newlyWinPrize", allocationSize = 1, initialValue = 1, sequenceName = "S_newlyWinPrize")
public class NewlyWinPrize extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_newlyWinPrize")
    private Long id;

    /** 彩期 */
    private String term;

    /** 彩种 */
    private LotteryType type;

    /** 用户名 */
    private String useName;

    /** 消费金额 */
    private BigDecimal money;

    /** 中奖金额 */
    private BigDecimal winMoney;
    
    /** 退款金额 */
    private BigDecimal refundMoney;

    /** 方案编号 */
    private String planNo;

    /** 预留 */
    private String winPrize_type;

    /** 中奖时间 */
    private Calendar winTime;

    /** 中奖类型 */
    private PlayStatus playStatus;

    public NewlyWinPrize()
    {
    };

    public NewlyWinPrize(String term, LotteryType type, String useName,
            BigDecimal winMoney, String planNo, PlayStatus playStatus,
            Calendar winTime, BigDecimal money)
    {
        super();
        this.term = term;
        this.type = type;
        this.useName = useName;
        this.winMoney = winMoney;
        this.planNo = planNo;
        this.playStatus = playStatus;
        this.winTime = winTime;
        this.money = money;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public String getTerm()
    {
        return term;
    }

    public void setTerm(String term)
    {
        this.term = term;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public String getUseName()
    {
        return useName;
    }

    public void setUseName(String useName)
    {
        this.useName = useName;
    }

    public BigDecimal getWinMoney()
    {
        return winMoney;
    }

    public void setWinMoney(BigDecimal winMoney)
    {
        this.winMoney = winMoney;
    }

    public String getPlanNo()
    {
        return planNo;
    }

    public void setPlanNo(String planNo)
    {
        this.planNo = planNo;
    }

    public String getWinPrize_type()
    {
        return winPrize_type;
    }

    public void setWinPrize_type(String winPrizeType)
    {
        winPrize_type = winPrizeType;
    }

    public Calendar getWinTime()
    {
        return winTime;
    }

    public void setWinTime(Calendar winTime)
    {
        this.winTime = winTime;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public PlayStatus getPlayStatus()
    {
        return playStatus;
    }

    public void setPlayStatus(PlayStatus playStatus)
    {
        this.playStatus = playStatus;
    }

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

}
