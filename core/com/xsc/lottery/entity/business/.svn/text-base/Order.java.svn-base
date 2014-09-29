package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderResult;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.PayStatus;
import com.xsc.lottery.entity.enumerate.PloyType;
import com.xsc.lottery.util.DateUtil;

/** 注单 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Order", allocationSize = 1, initialValue = 1, sequenceName = "S_Order")
public class Order extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_Order")
    private Long id;

    /** 方案 */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    /** 倍数 */
    @Column(nullable = false)
    private int multiple;

    /** 彩种 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private LotteryType type;

    /** 彩期 */
    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    private LotteryTerm term;

    /** 代购客户,合买则为null */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** 合买 一对一 */
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    /** 追号 一对一 */
    @ManyToOne
    @JoinColumn(name = "chase_id")
    private ChaseItem chaseItem;

    /** 创建时间 */
    @Column(nullable = false)
    private Calendar buildTime = Calendar.getInstance();

    /** 注单出票状态 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.待拆票;

    /** 注单结果 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_result", nullable = false)
    private OrderResult orderResult = OrderResult.未开奖;

    /** 注单付款情况 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pay_status", nullable = false)
    private PayStatus payStatus = PayStatus.已付款;

    /** 金额 */
    private BigDecimal amount;

    /** 出票金额 */
    @Column(name = "out_amount")
    private BigDecimal outAmount = BigDecimal.ZERO;

    /** 失败原因 */
    private String failCause;

    /** 中奖描述 */
    @OneToMany(mappedBy = "order")
    private List<WinDescribeOrder> orderWinDescribes;

    /** 中奖金额 */
    private BigDecimal winMoney = BigDecimal.ZERO;

    /** 税后金额 */
    @Column(name = "win_tax_money")
    private BigDecimal winTaxMoney = BigDecimal.ZERO;

    /** 第三方票务接口名字,表示该注单交到哪里去了 */
    @Column(name = "ticket_third_name")
    private String ticketThirdName;

    /** 活动 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ployType", nullable = false)
    private PloyType ployType = PloyType.常规;

    /** 活动关联字段（特定活动，表示的不同） */
    private int ployNumber = 0;

    /** 订单号 */
    @Transient
    public String numberNo;
    
    public String otherFrom;
    
    @Column(name = "source")
    public String source;
    
    @Column(name = "betmethod")
    public String method;
    
    public String getOtherFrom() {
		return otherFrom;
	}

	public void setOtherFrom(String otherFrom) {
		this.otherFrom = otherFrom;
	}

	/** 最后一场比赛 (for 竞彩足球 only)*/
    @ManyToOne
    @JoinColumn(name = "match_id")
    private MatchArrange lastMatch;
    
    /**
     * 出票成功时间
     */
    private Calendar successTime;
    
    public String getNumberNo()
    {
        return DateUtil.toTimeStamp1(buildTime) + id;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = DateUtil.toTimeStamp1(buildTime) + id;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public String getFailCause()
    {
        return failCause;
    }

    public void setFailCause(String failCause)
    {
        this.failCause = failCause;
    }

    public Order()
    {
    };

    /** 生成代购订单 */
    public Order(Plan plan, LotteryTerm term, Customer customer, int multiple)
    {
        super();
        this.plan = plan;
        this.term = term;
        this.customer = customer;
        this.multiple = multiple;

    }

    /** 追号生成订单 */
    public Order(ChaseItem chaseItem, LotteryTerm term)
    {
        super();
        this.chaseItem = chaseItem;
        this.plan = chaseItem.getPlan();
        this.term = term;
        this.type = chaseItem.getLotteryType();
        this.amount = chaseItem.getPlan().getMoney().multiply(
                new BigDecimal(chaseItem.getMultiple()));
        this.multiple = chaseItem.getMultiple();
        this.customer = chaseItem.getCustomer();
    }

    /** 合买满员生成订单 */
    public Order(Community community)
    {
        super();
        this.customer = community.getCustomer();
        this.community = community;
        this.plan = community.getPlan();
        this.term = community.getTerm();
        this.type = community.getPlan().getType();
        this.amount = community.getPlan().getMoney().multiply(
                new BigDecimal(community.getMultiple()));
        this.multiple = community.getMultiple();
        this.lastMatch = community.getLastMatch();
    }

    public Long getId()
    {
        return id;
    }

    public Plan getPlan()
    {
        return plan;
    }

    public LotteryTerm getTerm()
    {
        return term;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public Community getCommunity()
    {
        return community;
    }

    public Calendar getBuildTime()
    {
        return buildTime;
    }

    public OrderStatus getStatus()
    {
        return status;
    }

    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

    public void setPlan(Plan plan)
    {
        this.plan = plan;
    }

    public void setTerm(LotteryTerm term)
    {
        this.term = term;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setCommunity(Community community)
    {
        this.community = community;
    }

    public void setBuildTime(Calendar buildTime)
    {
        this.buildTime = buildTime;
    }

    public LotteryType getType()
    {
        return type;
    }

    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public ChaseItem getChaseItem()
    {
        return chaseItem;
    }

    public void setChase(ChaseItem chaseItem)
    {
        this.chaseItem = chaseItem;
    }

    public String getTicketThirdName()
    {
        return ticketThirdName;
    }

    public void setTicketThirdName(String ticketThirdName)
    {
        this.ticketThirdName = ticketThirdName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getMultiple()
    {
        return multiple;
    }

    public void setMultiple(int multiple)
    {
        this.multiple = multiple;
    }

    public OrderResult getOrderResult()
    {
        return orderResult;
    }

    public void setOrderResult(OrderResult orderResult)
    {
        this.orderResult = orderResult;
    }

    public PayStatus getPayStatus()
    {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus)
    {
        this.payStatus = payStatus;
    }

    public BigDecimal getWinMoney()
    {
        return winMoney;
    }

    public void setWinMoney(BigDecimal winMoney)
    {
        this.winMoney = winMoney;
    }

    public List<WinDescribeOrder> getOrderWinDescribes()
    {
        return orderWinDescribes;
    }

    public void setOrderWinDescribes(List<WinDescribeOrder> orderWinDescribes)
    {
        this.orderWinDescribes = orderWinDescribes;
    }

    public PloyType getPloyType()
    {
        return ployType;
    }

    public void setPloyType(PloyType ployType)
    {
        this.ployType = ployType;
    }

    public int getPloyNumber()
    {
        return ployNumber;
    }

    public void setPloyNumber(int ployNumber)
    {
        this.ployNumber = ployNumber;
    }

    public BigDecimal getOutAmount()
    {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount)
    {
        this.outAmount = outAmount;
    }

    public BigDecimal getWinTaxMoney()
    {
        return winTaxMoney;
    }

    public void setWinTaxMoney(BigDecimal winTaxMoney)
    {
        this.winTaxMoney = winTaxMoney;
    }
    
    public MatchArrange getLastMatch() {
		return lastMatch;
	}

	public void setLastMatch(MatchArrange lastMatch) {
		this.lastMatch = lastMatch;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Calendar getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Calendar successTime) {
		this.successTime = successTime;
	}

}
