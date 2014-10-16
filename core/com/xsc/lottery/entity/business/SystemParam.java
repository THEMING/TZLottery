package com.xsc.lottery.entity.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/**
 * 系统参数表
 * @author caipiao
 */
@Entity
@Table(name = "business_system_param",uniqueConstraints = { @UniqueConstraint(columnNames = { "name"}) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemParam extends BaseObject
{
	private static final long serialVersionUID = -5132333666588502261L;

	@Id
	@GeneratedValue
	private Long id;
	
	/*
	 * 参数描述
	 */
	private String description;
	
	/*
	 *参数名 
	 */
	private String name;
	
	/*
	 * 参数值
	 */
	private String value;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
