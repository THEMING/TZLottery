package com.xsc.lottery.entity.business;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.xsc.lottery.entity.BaseObject;
/**
 * 活动序列号批次号
 * @author caipiao
 */
public class YouHuiMaBatch extends BaseObject {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 活动ID
	 */
	private Long activity_id;

	/**
	 * 卡号金额
	 */
	private Integer money;

	/**
	 * 生成序列号数量
	 */
	private Integer numbers;

	/**
	 * 有效日期-开始时间
	 */
	private Calendar startTime;

	/**
	 * 有效日期-结束时间
	 */
	private Calendar endTime;

	/**
	 * 已使用数量
	 */
	private Integer usedNumber;

	/**
	 * 已回收数量
	 */
	private Integer cancelNumber;

	/**
	 * 备注
	 */
	@Column(length = 255)
	private String remark;

	/**
	 * 创建日期
	 */
	private Calendar createTime;

	/**
	 * 是否有效
	 */
	@Column(length = 10)
	private Boolean valid;

	/**
	 * 活动配置
	 */
	@Column(length = 500)
	private String config;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Long activityId) {
		activity_id = activityId;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Integer getUsedNumber() {
		return usedNumber;
	}

	public void setUsedNumber(Integer usedNumber) {
		this.usedNumber = usedNumber;
	}

	public Integer getCancelNumber() {
		return cancelNumber;
	}

	public void setCancelNumber(Integer cancelNumber) {
		this.cancelNumber = cancelNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}


}
