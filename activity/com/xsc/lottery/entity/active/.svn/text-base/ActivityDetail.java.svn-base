package com.xsc.lottery.entity.active;

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
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PaymentRequest;

@SuppressWarnings("serial")
@Entity
@Table(name = "activity_activity_detail")
public class ActivityDetail extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    
    @Enumerated(EnumType.ORDINAL)
    private ActivityDetailType actDetailType;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "payment_request_id")
    private PaymentRequest paymentRequest;
    
    
    private boolean finished = false;
    
    @Column(nullable = true)
    private BigDecimal addmoney;
    
    /**
     * 创建时间
     */
    private Calendar createTime;
    
    public BigDecimal getAddmoney() {
		return addmoney;
	}

	public void setAddmoney(BigDecimal addmoney) {
		this.addmoney = addmoney;
	}

	public ActivityDetail()
    {
        
    }
    
    public ActivityDetail(ActivityDetailType actDetailType, Activity activity)
    {
        this.actDetailType = actDetailType;
        this.activity = activity;
    }
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Activity getActivity()
    {
        return activity;
    }

    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }

    public ActivityDetailType getActDetailType()
    {
        return actDetailType;
    }

    public void setActDetailType(ActivityDetailType actDetailType)
    {
        this.actDetailType = actDetailType;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public PaymentRequest getPaymentRequest()
    {
        return paymentRequest;
    }

    public void setPaymentRequest(PaymentRequest paymentRequest)
    {
        this.paymentRequest = paymentRequest;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

	public Calendar getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Calendar createTime)
	{
		this.createTime = createTime;
	}
}
