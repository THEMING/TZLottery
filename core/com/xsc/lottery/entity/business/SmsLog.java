package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.partner.Partner;

/**
 * <pre>
 * 短信记录信息
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-6-17
 */
@Entity
@Table(name = "business_sms_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsLog extends BaseObject
{
	@Id
	@GeneratedValue
	private Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * <pre>
	 * 短信状态
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0, 2014-6-17
	 */
	public enum SmsLogState
	{
		/**
		 * 等待发送
		 */
		WAITING("等待发送"),
		/**
		 * 正在发送
		 */
		SENDING("正在发送"),
		/**
		 * 已发送
		 */
		SENDED("已发送"),

		FAILURE("发送失败");

		private String text;

		SmsLogState(String text)
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
	 * 短信记录类型
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0, 2014-6-17
	 */
	public enum SmsLogType
	{
		/**
		 * 普通
		 */
		COMMON("普通", 1),
		/**
		 * 通知
		 */
		NOTICE("通知", 2),
		/**
		 * 促销
		 */
		PROMOTION("促销", 3),
		/**
		 * 告警
		 */
		WARN("告警", 4),

		/**
		 * 手机验证
		 */
		VALID("手机验证", 5);

		private String text;

		private int value;

		SmsLogType(String text, int value)
		{
			this.text = text;
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
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
	 * 发送类型
	 */
	public enum SmsSendState
	{
		/**
		 * 立即发送
		 */
		IMMEDIATELY("立即发送", 1),
		/**
		 * 定时发送
		 */
		SCHEDULED("定时发送", 2);

		private String text;

		private int value;

		private SmsSendState(String text, int value)
		{
			this.text = text;
			this.value = value;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.text = text;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
		}

	}
	
	/**
	 * <pre>
	 * 短信发送终端类型
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0, 2014-6-17
	 */
	public static enum SmsSendType
	{
		EMAY("亿美短信接口"), SH81666("上海81666短信接口"), ZGYD10086("中国移动短信接口"), CSAP("彩搜短信接口"),YUN("云通讯平台");

		private String text;

		SmsSendType(String text)
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

	private static final long serialVersionUID = -5373578539418642184L;

	private Long customerId;

	/*
	 * 短信内容
	 */
	@Column(length = 500)
	private String content;

	/*
	 * 手机号
	 */
	@Column(length = 20)
	private String mobile;

	/*
	 * 重发次数
	 */
	private Integer retryCount;

	/*
	 * 发送时间
	 */
	private Calendar sendTime;

	/*
	 * 状态
	 */
	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private SmsLogState state;

	/*
	 * 成功时间
	 */
	private Calendar successTime;

	/*
	 * 类型
	 */
	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private SmsLogType type;

	/*
	 * 发送人 匹配的是AdminUser的id
	 */
	@Column(length = 20)
	private String userId;
	
	private String sendUserName;

	/*
	 * 发送人优先级
	 */
	@Column(length = 10)
	private Integer userPriority;

	/*
	 * 短信优先级
	 */
	@Column(length = 10)
	private Integer sendPriority;// 发送优先级，越大优先级越高，1~5，一般为3

	/*
	 * 发送类型
	 */
	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private SmsSendState sendState;// 发送类型

	/*
	 * 定时发送时间
	 */
	private Calendar scheduledTime;// 定时发送时间

	/*
	 * 短信内容
	 */
	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private SmsSendType smsSendType;
	
	/*
	 * 备注
	 */
	private String mark;
	
	/*接受短信的客户*/
	@ManyToOne
	@JoinColumn(name = "customerObjId")
	private Customer customer;

	public String getSendUserName()
	{
		return sendUserName;
	}

	public void setSendUserName(String sendUserName)
	{
		this.sendUserName = sendUserName;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public String getContent()
	{
		return this.content;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public Integer getRetryCount()
	{
		return this.retryCount;
	}

	public Calendar getSendTime()
	{
		return this.sendTime;
	}

	public SmsLogState getState()
	{
		return this.state;
	}

	public Calendar getSuccessTime()
	{
		return this.successTime;
	}

	public SmsLogType getType()
	{
		return this.type;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public void setRetryCount(Integer retryCount)
	{
		this.retryCount = retryCount;
	}

	public void setSendTime(Calendar sendTime)
	{
		this.sendTime = sendTime;
	}

	public void setState(SmsLogState state)
	{
		this.state = state;
	}

	public void setSuccessTime(Calendar successTime)
	{
		this.successTime = successTime;
	}

	public void setType(SmsLogType type)
	{
		this.type = type;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public Integer getSendPriority()
	{
		return sendPriority;
	}

	public void setSendPriority(Integer sendPriority)
	{
		this.sendPriority = sendPriority;
	}

	public SmsSendState getSendState()
	{
		return sendState;
	}

	public void setSendState(SmsSendState sendState)
	{
		this.sendState = sendState;
	}

	public Calendar getScheduledTime()
	{
		return scheduledTime;
	}

	public void setScheduledTime(Calendar scheduledTime)
	{
		this.scheduledTime = scheduledTime;
	}

	public Integer getUserPriority()
	{
		return userPriority;
	}

	public void setUserPriority(Integer userPriority)
	{
		this.userPriority = userPriority;
	}

	public SmsSendType getSmsSendType()
	{
		return smsSendType;
	}

	public void setSmsSendType(SmsSendType smsSendType)
	{
		this.smsSendType = smsSendType;
	}

	public Long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(Long customerId)
	{
		this.customerId = customerId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}