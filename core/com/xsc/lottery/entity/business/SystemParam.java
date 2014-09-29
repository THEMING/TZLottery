package com.xsc.lottery.entity.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/**
 * 系统参数表
 * @author caipiao
 */
@Entity
@Table(name = "business_system_param")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemParam extends BaseObject
{
	private static final long serialVersionUID = -5132333666588502261L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String value;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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
