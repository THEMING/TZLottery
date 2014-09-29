package com.xsc.lottery.entity.business;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;
@SuppressWarnings("serial")
@Entity
@Table(name = "business_filedownlod")
@SequenceGenerator(name = "S_Filedownlod", allocationSize = 1, initialValue = 1, sequenceName = "S_Filedownlod")
public class Filedownlod  extends BaseObject
{
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
    private String fileName;
	
	@Column(nullable = false)
    private long count;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
        this.id = id;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	
}
