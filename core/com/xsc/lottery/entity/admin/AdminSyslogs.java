package com.xsc.lottery.entity.admin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "admin_syslogs")
@SequenceGenerator(name = "S_AdminSyslogs", allocationSize = 1, initialValue = 1, sequenceName = "S_AdminSyslogs")
public class AdminSyslogs
{
    @Id
    @GeneratedValue
    private Long id;

    /** 操作日志 */
    private String logs;

    /** 管理用户名 */
    private String user;

    /** 管理用户名IP */
    private String ip;

    /** 管理用户名操作时间 */
    private Date optTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLogs()
    {
        return logs;
    }

    public void setLogs(String logs)
    {
        this.logs = logs;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Date getOptTime()
    {
        return optTime;
    }

    public void setOptTime(Date optTime)
    {
        this.optTime = optTime;
    }
}
