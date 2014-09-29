package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

/** 中奖公告 */
public class WinPrizeBean
{
    private String nickName;
    private String termNo;
    private String termType;
    private BigDecimal money;
    private String prizeName;

    public WinPrizeBean(String nickName, String termNo, String termType,
            BigDecimal money, String prizeName)
    {
        super();
        this.nickName = nickName;
        this.termNo = termNo;
        this.termType = termType;
        this.money = money;
        this.prizeName = prizeName;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getTermNo()
    {
        return termNo;
    }

    public void setTermNo(String termNo)
    {
        this.termNo = termNo;
    }

    public String getTermType()
    {
        return termType;
    }

    public void setTermType(String termType)
    {
        this.termType = termType;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public String getPrizeName()
    {
        return prizeName;
    }

    public void setPrizeName(String prizeName)
    {
        this.prizeName = prizeName;
    }
}
