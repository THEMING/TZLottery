package com.xsc.lottery.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/** 手机号列表 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_mobile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class AdminMobile extends BaseObject {
 	@Id
    @GeneratedValue
    private Long id;
 	
    /** 手机号码 */
    @Column(name = "mobile")
    private String mobile;

	/** 姓名 */
    @Column(name = "name")
    private String name;

	/** 是否有效 */
    @Column(name = "active")
    private  int active;

    @Column(name = "content")
    private String content;
    
    @Column(name = "email")
    private String email;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
    
    
}
