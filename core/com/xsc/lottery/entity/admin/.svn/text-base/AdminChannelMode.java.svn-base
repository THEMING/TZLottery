package com.xsc.lottery.entity.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin_channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "S_AdminChannel", allocationSize = 1, initialValue = 1, sequenceName = "S_AdminChannel")
public class AdminChannelMode extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;

    private String cname;

    private String ename;

    private Long chId;

    @Override
    public Long getId()
    {
        return id;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getEname()
    {
        return ename;
    }

    public void setEname(String ename)
    {
        this.ename = ename;
    }

    public Long getChId()
    {
        return chId;
    }

    public void setChId(Long chId)
    {
        this.chId = chId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
