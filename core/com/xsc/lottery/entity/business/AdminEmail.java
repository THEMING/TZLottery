package com.xsc.lottery.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

/** 邮件号列表 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class AdminEmail extends BaseObject {
	@Id
    @GeneratedValue
    private Long id;
 	
    /** 邮箱号码 */
    @Column(name = "email")
    private String email;
	
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/** 姓名 */
    @Column(name = "name")
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 是否有效 */
    @Column(name = "active")
    private  int active;
    
    public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdminEmail()
    {
    };
}
