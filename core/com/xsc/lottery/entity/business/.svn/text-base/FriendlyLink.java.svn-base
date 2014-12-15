package com.xsc.lottery.entity.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

@Entity
@Table(name="business_friendly_link")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name="S_FriendlyLink", allocationSize=1, initialValue=1, sequenceName="S_FriendlyLink")
public class FriendlyLink extends BaseObject
{

  private static final long serialVersionUID = -5322779296486721923L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String name;
  
  private String url;
  
  private Integer sort;
  
  /*
   * 0-网站首页  1-网站资讯首页  2-网站开奖首页 
   */
  private Integer type;

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getSort() {
    return this.sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

public Integer getType()
{
	return type;
}

public void setType(Integer type)
{
	this.type = type;
}
}