package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 用户提成*/

@SuppressWarnings("serial")
@Entity
@Table(name = "business_customer_commission")
public class CustomerCommission extends BaseObject
{ 
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false, unique = true)
	private String Name;
	
	/** 是否截止 */
	private boolean stop;
	
	/** 一级提成开启 */
    private boolean open1 = true;
    
    /** 一级推荐提成比例*/
    private BigDecimal ratio1 = BigDecimal.valueOf(0.002);
    
    /** 二级提成开启 */
    private boolean open2 = true;
    
    /** 二级推荐提成比例*/
    private BigDecimal ratio2 = BigDecimal.valueOf(0.001);
    
    /** 特殊用户（网站）的头部和尾部 */
    private String head;
    private String foot;
    
	public String getHead()
	{
		return head;
	}

	public void setHead(String head)
	{
		this.head = head;
	}

	public String getFoot()
	{
		return foot;
	}

	public void setFoot(String foot)
	{
		this.foot = foot;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public boolean isStop()
	{
		return stop;
	}

	public void setStop(boolean stop)
	{
		this.stop = stop;
	}

	public boolean isOpen1()
	{
		return open1;
	}

	public void setOpen1(boolean open1)
	{
		this.open1 = open1;
	}

	public BigDecimal getRatio1()
	{
		return ratio1;
	}

	public void setRatio1(BigDecimal ratio1)
	{
		this.ratio1 = ratio1;
	}

	public boolean isOpen2()
	{
		return open2;
	}

	public void setOpen2(boolean open2)
	{
		this.open2 = open2;
	}

	public BigDecimal getRatio2()
	{
		return ratio2;
	}

	public void setRatio2(BigDecimal ratio2)
	{
		this.ratio2 = ratio2;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}
}
