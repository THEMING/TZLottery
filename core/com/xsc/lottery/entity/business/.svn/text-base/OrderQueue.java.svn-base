package com.xsc.lottery.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
@SuppressWarnings("serial")
@Entity
@Table(name = "business_orderQueue")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@SequenceGenerator(name = "S_OrderQueue", allocationSize = 1, initialValue = 1, sequenceName = "S_OrderQueue")
public class OrderQueue  extends BaseObject
{
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
    private Long orderId;
	
	@Column
	private Integer status;
	
	//1代表大赢家，0代表我中了
	private Integer sendTicketPlat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSendTicketPlat() {
		return sendTicketPlat;
	}

	public void setSendTicketPlat(Integer sendTicketPlat) {
		this.sendTicketPlat = sendTicketPlat;
	}
}
