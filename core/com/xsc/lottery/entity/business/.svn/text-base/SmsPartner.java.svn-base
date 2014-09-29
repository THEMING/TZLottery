package com.xsc.lottery.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/**
 * 短信合作商信息表
 * @author pengfangliang
 * @version 1.0 2014-06-19 10:24:36
 */
@Entity
@Table(name = "business_sms_partner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SmsPartner extends BaseObject
{
	/**
	 * <pre>
	 * 合作商状态
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0 2014-06-19 10:24:36
	 */
	public static enum SmsPartnerStatus
	{
		/**
		 * 打开
		 */
		OPEN("打开"),
		/**
		 * 关闭
		 */
		CLOSED("关闭");

		private String text;

		SmsPartnerStatus(String text)
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
	 * 短信发送终端类型
	 * </pre>
	 * @author pengfangliang
	 * @version 1.0 2014-06-19 10:24:36
	 */
	public static enum SmsSendType
	{
		EMAY("亿美短信接口"), SH81666("上海81666短信接口"), ZGYD10086("中国移动短信接口"), CSAP("彩搜短信接口");

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

	private static final long serialVersionUID = 1933860213831072480L;

	/**
	 * 短信合作商编号
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 合作商密钥
	 */
	@Column(length = 20)
	private String keyPass;

	@Column(length = 20)
	private String name;

	/**
	 * 合作商状态
	 */
	@Column(length = 10)
	@Enumerated(javax.persistence.EnumType.STRING)
	private SmsPartnerStatus status;

	/**
	 * 发送优先级
	 */
	@Column(length = 10)
	private Integer priority;

	/**
	 * 合作商创建时间
	 */
	private Date createTime;

	/**
	 * 短信终端类型
	 */
	@Column(length = 20)
	@Enumerated(javax.persistence.EnumType.STRING)
	private SmsSendType smsSendType;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getKeyPass()
	{
		return keyPass;
	}

	public void setKeyPass(String keyPass)
	{
		this.keyPass = keyPass;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public SmsPartnerStatus getStatus()
	{
		return status;
	}

	public void setStatus(SmsPartnerStatus status)
	{
		this.status = status;
	}

	public Integer getPriority()
	{
		return priority;
	}

	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public SmsSendType getSmsSendType()
	{
		return smsSendType;
	}

	public void setSmsSendType(SmsSendType smsSendType)
	{
		this.smsSendType = smsSendType;
	}
}
