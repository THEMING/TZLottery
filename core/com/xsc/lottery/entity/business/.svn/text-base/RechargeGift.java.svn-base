package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.SoftwareType;

/** 冲值请求 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_recharge_gift")
public class RechargeGift extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;

    /** 充值业务编号 */
    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "serialNumber")
    private PaymentRequest paymentrequest;
    
    /** 客户编号 */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    
    /** 本次充值是否有奖品 */
    @Column(name = "pirze")
    private boolean prize;

    
    /** 奖品是否已经被领取 */
    @Column(name = "receive")
    private boolean receive;

    /** 注册日期 */
    @Column(name = "registerTime", nullable = true)
    private Calendar registerTime;
    
    
    /** 软件编号 */
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private SoftwareType softType;

    /** 机器码 */
    @Column(name = "machineNo", nullable = true)
    private String machineNo;

    /** 软件注册码 */
    @Column(name = "registerNo", nullable = true)
    private String registerNo;

    public RechargeGift()
    {
    };
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Long getId()
    {
        return id;
    }

	public PaymentRequest getPaymentrequest() {
		return paymentrequest;
	}

	public void setPaymentrequest(PaymentRequest paymentrequest) {
		this.paymentrequest = paymentrequest;
	}

	public boolean isPrize() {
		return prize;
	}

	public void setPrize(boolean prize) {
		this.prize = prize;
	}

	public boolean isReceive() {
		return receive;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public Calendar getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Calendar registerTime) {
		this.registerTime = registerTime;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SoftwareType getSoftType() {
		return softType;
	}

	public void setSoftType(SoftwareType softType) {
		this.softType = softType;
	}
}
