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
public class AdminChannel extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;

    /** 父结点ID */
    private Long parentId;

    /** 父路径 */
    private String parentPath;

    /** 结点深度 */
    private int depth;
    
    /** 子结点ID */
    private Long child;

    /** 功能模块名称 */
    private String channelName;

    /** 功能模块简介 */
    private String description;

    /** 功能排序ID */
    private int priority;

    /** 功能模块类型 */
    private int channelType;

    /** 功能连接路径 */
    private String righturl;

    /** 左侧功能入口跳转链接路径 */
    private String lefturl;

    /** 是否显示 */
    private int ispass;
    
    /** 快捷标识 */
    private Integer ishandy;

    @Override
    public Long getId()
    {
        return id;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getParentPath()
    {
        return parentPath;
    }

    public void setParentPath(String parentPath)
    {
        this.parentPath = parentPath;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public Long getChild()
    {
        return child;
    }

    public void setChild(Long child)
    {
        this.child = child;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public int getChannelType()
    {
        return channelType;
    }

    public void setChannelType(int channelType)
    {
        this.channelType = channelType;
    }

    public String getRighturl()
    {
        return righturl;
    }

    public void setRighturl(String righturl)
    {
        this.righturl = righturl;
    }

    public String getLefturl()
    {
        return lefturl;
    }

    public void setLefturl(String lefturl)
    {
        this.lefturl = lefturl;
    }

    public int getIspass()
    {
        return ispass;
    }

    public void setIspass(int ispass)
    {
        this.ispass = ispass;
    }

    public Integer getIshandy()
    {
        return ishandy;
    }

    public void setIshandy(Integer ishandy)
    {
        this.ishandy = ishandy;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
