package com.xsc.lottery.entity.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin_permissions")
@SequenceGenerator(name = "S_AdminPermissions", allocationSize = 1, initialValue = 1, sequenceName = "S_AdminPermissions")
public class AdminPermissions extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;
    
    /** 类型ID */
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private AdminChannel channel;

    /** 角色ID */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private AdminRole adminRole;

    /** 权限(VIEW,INPUT,CHECK) */
    private String operate_code;

    public AdminChannel getChannel()
    {
        return channel;
    }

    public void setChannel(AdminChannel channel)
    {
        this.channel = channel;
    }

    public String getOperate_code()
    {
        return operate_code;
    }

    public void setOperate_code(String operateCode)
    {
        operate_code = operateCode;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public AdminRole getAdminRole()
    {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole)
    {
        this.adminRole = adminRole;
    }

}
