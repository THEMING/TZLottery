package com.xsc.lottery.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;

/** 彩种配置控制类 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_term_type_config")
@SequenceGenerator(name = "S_TermTypeConfig", allocationSize = 1, initialValue = 1, sequenceName = "S_TermTypeConfig")
public class TermTypeConfig extends BaseObject
{

    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_TermTypeConfig")
    private Long id;

    /** 彩种 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false, unique = true)
    private LotteryType type;

    /** 停售 */
    private boolean stop;

    /** 出票点的出口 */
    @Enumerated(EnumType.ORDINAL)
    // , unique = true
    @Column(nullable = false)
    private SendTicketPlat outPoint;

    /** 彩种是否自动开奖 */
    private boolean autoOpen;

    /** 彩种是否自动兑奖 */
    private boolean autoCheckWin;

    public TermTypeConfig()
    {
    }

    /**
     * @param type
     *            彩种
     * @param stop
     *            停销
     * @param url
     *            送票URL
     * @param outPoint
     *            送票点
     * @param agent
     *            送票帐号
     * @param password
     *            送票密钥
     * @param autoOpen
     *            自动开奖
     * @param autoSend
     *            自动派奖
     */
    public TermTypeConfig(LotteryType type, boolean stop,
            SendTicketPlat outPoint, boolean autoOpen, boolean autoCheckWin)
    {
        super();
        this.type = type;
        this.stop = stop;
        this.outPoint = outPoint;
        this.autoOpen = autoOpen;
        this.autoCheckWin = autoCheckWin;
    }

    public LotteryType getType()
    {
        return type;
    }

    public boolean isStop()
    {
        return stop;
    }

    public SendTicketPlat getOutPoint()
    {
        return outPoint;
    }

    public Long getId()
    {
        return id;
    }

    public void setStop(boolean stop)
    {
        this.stop = stop;
    }

    public void setOutPoint(SendTicketPlat outPoint)
    {
        this.outPoint = outPoint;
    }

    public boolean isAutoOpen()
    {
        return autoOpen;
    }

    public void setAutoOpen(boolean autoOpen)
    {
        this.autoOpen = autoOpen;
    }

    public boolean isAutoCheckWin()
    {
        return autoCheckWin;
    }

    public void setAutoCheckWin(boolean autoCheckWin)
    {
        this.autoCheckWin = autoCheckWin;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

}
