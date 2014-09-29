/**
 * 
 */
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
 * todo 推广渠道
 * @author pengfangliang
 * @version 1.0 2014-06-21  18:15
 *
 */
@Entity
@Table(name = "business_spread_channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpreadChannel extends BaseObject
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 渠道名称
	 */
	private String name;
	
	/**
	 * 渠道地址
	 */
	private String url;
	
	/**
	 * 备注
	 */
	private String description;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 身份证号
	 */
	private String idCardNo;
	
	/**
	 * 联系电话
	 */
	private String linkPhone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * QQ
	 */
	private String QQ;
	
	/**
	 * 申请时间
	 */
	private Calendar createTime;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	

}
