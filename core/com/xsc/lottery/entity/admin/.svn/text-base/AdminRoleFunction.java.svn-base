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
@Table(name = "admin_role_function")
@SequenceGenerator(name = "S_AdminRoleFunction", allocationSize = 1, initialValue = 1, sequenceName = "S_AdminRoleFunction")
public class AdminRoleFunction extends BaseObject
{
    @Id
    @GeneratedValue
    //(strategy=GenerationType.SEQUENCE, generator="S_AdminPermissions")
    private Long id;

    /** 类型ID */
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private AdminChannel channel;

    /** 类型ID */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private AdminRole role;

    @Override
    public Long getId()
    {
        return id;
    }

    public AdminChannel getChannel()
    {
        return channel;
    }

    public void setChannel(AdminChannel channel)
    {
        this.channel = channel;
    }

    public AdminRole getRole()
    {
        return role;
    }

    public void setRole(AdminRole role)
    {
        this.role = role;
    }
}
