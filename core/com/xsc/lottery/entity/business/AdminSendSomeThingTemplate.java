package com.xsc.lottery.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.CredentType;
import com.xsc.lottery.entity.enumerate.SendTemplateType;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin_sendsomething_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_Template", allocationSize = 1, initialValue = 1, sequenceName = "S_Template")
public class AdminSendSomeThingTemplate extends BaseObject
{

	@Id
    @GeneratedValue
    private Long id;
	
	private String title;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(name = "sendTemplateType")
    private SendTemplateType sendTemplateType;
	
	private String content;
	
	private String description;
	
	@Override
	public Long getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public SendTemplateType getSendTemplateType()
	{
		return sendTemplateType;
	}

	public void setSendTemplateType(SendTemplateType sendTemplateType)
	{
		this.sendTemplateType = sendTemplateType;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

}
