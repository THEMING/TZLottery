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
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.entity.enumerate.TicketSPStatus;
import com.xsc.lottery.entity.enumerate.TicketStatus;

/** 票 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_ticket")
@SequenceGenerator(name = "S_Ticket", allocationSize = 1, initialValue = 1, sequenceName = "S_Ticket")
public class Ticket extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Ticket")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private LotteryType type;

    /** 选号方式 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "play_type", nullable = false)
    private PlayType playType;

    /** 期号 */
    @Column(name = "term_no", nullable = false)
    private String termNo;

    /** 注单 */
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private PlanItem item;

    /** 注单 */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /** 票内容 */
    @Column(nullable = false)
    private String content;
    //例如：SPF|111209004=3/1/0,111209003=3/1/0,111209002=3/1|3*1
    
    /** 竞彩赔率 */
    private String ratio;  
    // 格式示例： 票内容 content="SPF|110812001=3/1,110812005=1,110812012=3/1/0|3*4"
    // 赔率 ratio = "        1.24/2.57|       4.30|        3.10/2.12/3.45"
    
    /** 金额 */
    @Column(nullable = false)
    private BigDecimal money;

    /** 单注金额---默认每注2元乐,透菜种追加3元.开乐彩普通投注为1元 */
    private BigDecimal oneMoney = new BigDecimal(2);

    /** 倍数 */
    private int multiple;

    /** 注数 */
    private int count;

    /** 票状态 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private TicketStatus status = TicketStatus.未送票;

    /** 票SP状态 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "spstatus", nullable = true)
    private TicketSPStatus spstatus;
    
    public TicketSPStatus getSpstatus() {
		return spstatus;
	}

	public void setSpstatus(TicketSPStatus spstatus) {
		this.spstatus = spstatus;
	}

	/** 送票时间 */
    private Calendar sendTicketTime;

    /** 确认时间 */
    private Calendar sureTime;

    /** 第三方流水号 */
    private String otherOrderID;

    /** 第三方返回结果消息 */
    private String otherMsg;
    
    /**我中了所需要的内容格式*/
    private String betContent;
    
    /**我中了所需要的相应的期次号(每张ticket中boutIndex的最大值)*/
    private String issue;

    /**竟彩篮球所需要的让分或者大小分(出票时间我中了给的)*/
    //  如果有多场比赛结果用"|"隔开   
    //  例如   180|175    1.5|-2.5
    private String ticketSpecial;
    
    public Ticket()
    {
    };

    public Ticket(Order order)
    {
        this.multiple = order.getMultiple();
        this.type = order.getType();
        this.termNo = order.getTerm().getTermNo();
    }

    public TicketStatus getStatus()
    {
        return status;
    }

    public void setStatus(TicketStatus status)
    {
        this.status = status;
    }

    public Calendar getSendTicketTime()
    {
        return sendTicketTime;
    }

    public void setSendTicketTime(Calendar sendTicketTime)
    {
        this.sendTicketTime = sendTicketTime;
    }

    public Calendar getSureTime()
    {
        return sureTime;
    }

    public void setSureTime(Calendar sureTime)
    {
        this.sureTime = sureTime;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public PlanItem getItem()
    {
        return item;
    }

    public void setItem(PlanItem item)
    {
        this.item = item;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getMultiple()
    {
        return multiple;
    }

    public void setMultiple(int multiple)
    {
        this.multiple = multiple;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public String getTermNo()
    {
        return termNo;
    }

    public void setTermNo(String termNo)
    {
        this.termNo = termNo;
    }

    public PlayType getPlayType()
    {
        return playType;
    }

    public void setPlayType(PlayType playType)
    {
        this.playType = playType;
    }

    public String getOtherOrderID()
    {
        return otherOrderID;
    }

    public void setOtherOrderID(String otherOrderID)
    {
        this.otherOrderID = otherOrderID;
    }

    public String getOtherMsg()
    {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg)
    {
        this.otherMsg = otherMsg;
    }

    public BigDecimal getOneMoney()
    {
        return oneMoney;
    }

    public void setOneMoney(BigDecimal oneMoney)
    {
        this.oneMoney = oneMoney;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
    
    public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getBetContent() {
		return betContent;
	}

	public void setBetContent(String betContent) {
		this.betContent = betContent;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getTicketSpecial() {
		return ticketSpecial;
	}

	public void setTicketSpecial(String ticketSpecial) {
		this.ticketSpecial = ticketSpecial;
	}
}
