package com.xsc.lottery.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <pre>
 * 邮件记录信息
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-16
 */
@Entity
@Table(name = "business_email_log")
@NamedQueries({@NamedQuery(name="email.resetForSending",query="update EmailLog o set o.state='NOTSEND' where o.state='SENDING'")})
public class EmailLog
{
	/**
	 * <pre>
	 * 邮件状态
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0, 2014-9-16
	 */
	public static enum EmailState
	{
		NOTSEND("等待发送"), SENDING("正在发送"), SENDED("已发送");

		private String text;

		EmailState(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.text = text;
		}
	}

	/**
	 * <pre>
	 * 邮件记录类型
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0, 2014-9-16
	 */
	public static enum EmailType
	{
		NORMAL("普通"), NOTIFY("通知"), WARNING("告警"), PROMOTION("促销"), GETPWD("找回密码"), REGISTER("注册激活"), SUBSCRIBE("邮件订阅激活")
		,VALIDEMAIL("邮箱验证");

		private String text;

		EmailType(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.text = text;
		}
	}

	private static final long serialVersionUID = 3742228382782423527L;
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private EmailType type;

	@Column(length = 30)
	private String sendUserName;

	//发送者id 匹配的是AdminUser的id
	@Column(length = 10)
	private String storeId;

	//接收邮件的用户的nickName
	private String username;

	private String email;

	private String title;

	@Column(length = 5000)
	private String content;

	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private EmailState state;

	private Date sendTime;

	private Date successTime;

	private int retryCount;

	/**
	 * 发送级别值越大,越优先
	 */
	private int sendLevel;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public EmailType getType()
	{
		return type;
	}

	public void setType(EmailType type)
	{
		this.type = type;
	}

	public String getSendUserName()
	{
		return sendUserName;
	}

	public void setSendUserName(String sendUserName)
	{
		this.sendUserName = sendUserName;
	}

	public String getStoreId()
	{
		return storeId;
	}

	public void setStoreId(String storeId)
	{
		this.storeId = storeId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public EmailState getState()
	{
		return state;
	}

	public void setState(EmailState state)
	{
		this.state = state;
	}

	public Date getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(Date sendTime)
	{
		this.sendTime = sendTime;
	}

	public Date getSuccessTime()
	{
		return successTime;
	}

	public void setSuccessTime(Date successTime)
	{
		this.successTime = successTime;
	}

	public int getRetryCount()
	{
		return retryCount;
	}

	public void setRetryCount(int retryCount)
	{
		this.retryCount = retryCount;
	}

	public int getSendLevel()
	{
		return sendLevel;
	}

	public void setSendLevel(int sendLevel)
	{
		this.sendLevel = sendLevel;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
}