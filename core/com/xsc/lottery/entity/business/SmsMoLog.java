package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/**
 * 接收上行短信记录
 * @author pengfangliang
 * @version 1.0 2014-06-18 21:39
 */

@Entity
@Table(name = "business_sms_mo_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsMoLog extends BaseObject {

	private static final long serialVersionUID = 1881220416608861567L;
	
	
	/**
	 *上行短信处理状态 
	 */
	public enum SmsMoLogState{
		
		NODO("未处理"),
		
		DONE("已处理");
		
		private String text;
		
		private SmsMoLogState(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String content;//上行短信内容
	
	private String mobile;//手机号码
	
	private String sendTime;//手机发送时间
	
	private String channelNumber;//用户的通讯通道
	
	private String addSerial;//附加码
	
	private String addSerialRev;//附加码2
	
	private SmsMoLogState state;//状态
	
	private Calendar receiveTime;//接收时间
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getAddSerial() {
		return addSerial;
	}

	public void setAddSerial(String addSerial) {
		this.addSerial = addSerial;
	}

	public String getAddSerialRev() {
		return addSerialRev;
	}

	public void setAddSerialRev(String addSerialRev) {
		this.addSerialRev = addSerialRev;
	}

	public SmsMoLogState getState() {
		return state;
	}

	public void setState(SmsMoLogState state) {
		this.state = state;
	}

	public Calendar getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Calendar receiveTime) {
		this.receiveTime = receiveTime;
	}
	
}
