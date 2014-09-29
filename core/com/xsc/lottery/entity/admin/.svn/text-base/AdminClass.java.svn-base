package com.xsc.lottery.entity.admin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 新闻/彩种类型 */
@SuppressWarnings("serial")
@Entity
@Table(name = "admin_class")
@SequenceGenerator(name = "S_AdminClass", allocationSize = 1, initialValue = 1, sequenceName = "S_AdminClass")
public class AdminClass extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;

    /** 文章/彩种类型 */
    private int classType;

    /** 文章/彩种类型名称 */
    private String className;

    /** 节点访问路径 */
    private String viewpath;

    /** 模版ID */
    private int modelId;

    /** 栏目页模板 */
    private String tplIndex;

    /** 内容页模板 */
    private String tplContent;
    
    /** 父结点ID */
    private Long parentId;

    /** 父结点路径 */
    private String parentPath;
    
    /** 结点深度 */
    private int depth;

    /** 子结点ID */
    private Long child;
    
    /** 排序ID */
    private Integer priority;

    /** 提示 */
    private String brTitle;

    /** 提示内容 */
    private String intro;

    /** 关键词 */
    private String metaKeywords;

    /** 关键词描述 */
    private String metaDescription;

    private String linkurl;
    private String classpicUrl;
    private String filesUrl;

    /** 添加时间 */
    private Date subTime;
    private int isingle;

    /** 审核标识 */
    private int ipass;

    /** 浏览权限（0开放--任何人浏览查看、1半开放--任可人可浏览3会员认证） */
    private int purviewType;

    /** 是否允许评论 */
    private String allowcomment;
    
    /** 工作流(审核流程定义) */
    private Integer workflowId;

    /** 文章总数 */
    private int artNum;

    /** 初稿数 状态 0 */
    private int newNum;
    
    /** 发布数 状态1 */
    private int onlineNum;
    
    /** 投稿数 状态2 */
    private int receivedNum;
    
    /** 退稿数 状态3 */
    private int sendbckNum;
    
    /** 站点ID */
    private int siteId;

    @Override
    public Long getId()
    {
        return id;
    }

    public int getClassType()
    {
        return classType;
    }

    public void setClassType(int classType)
    {
        this.classType = classType;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getViewpath()
    {
        return viewpath;
    }

    public void setViewpath(String viewpath)
    {
        this.viewpath = viewpath;
    }

    public int getModelId()
    {
        return modelId;
    }

    public void setModelId(int modelId)
    {
        this.modelId = modelId;
    }

    public String getTplIndex()
    {
        return tplIndex;
    }

    public void setTplIndex(String tplIndex)
    {
        this.tplIndex = tplIndex;
    }

    public String getTplContent()
    {
        return tplContent;
    }

    public void setTplContent(String tplContent)
    {
        this.tplContent = tplContent;
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

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public String getBrTitle()
    {
        return brTitle;
    }

    public void setBrTitle(String brTitle)
    {
        this.brTitle = brTitle;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getMetaKeywords()
    {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords)
    {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription()
    {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription)
    {
        this.metaDescription = metaDescription;
    }

    public String getLinkurl()
    {
        return linkurl;
    }

    public void setLinkurl(String linkurl)
    {
        this.linkurl = linkurl;
    }

    public String getClasspicUrl()
    {
        return classpicUrl;
    }

    public void setClasspicUrl(String classpicUrl)
    {
        this.classpicUrl = classpicUrl;
    }

    public String getFilesUrl()
    {
        return filesUrl;
    }

    public void setFilesUrl(String filesUrl)
    {
        this.filesUrl = filesUrl;
    }

    public Date getSubTime()
    {
        return subTime;
    }

    public void setSubTime(Date subTime)
    {
        this.subTime = subTime;
    }

    public int getIsingle()
    {
        return isingle;
    }

    public void setIsingle(int isingle)
    {
        this.isingle = isingle;
    }

    public int getIpass()
    {
        return ipass;
    }

    public void setIpass(int ipass)
    {
        this.ipass = ipass;
    }

    public int getPurviewType()
    {
        return purviewType;
    }

    public void setPurviewType(int purviewType)
    {
        this.purviewType = purviewType;
    }

    public String getAllowcomment()
    {
        return allowcomment;
    }

    public void setAllowcomment(String allowcomment)
    {
        this.allowcomment = allowcomment;
    }

    public Integer getWorkflowId()
    {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId)
    {
        this.workflowId = workflowId;
    }

    public int getArtNum()
    {
        return artNum;
    }

    public void setArtNum(int artNum)
    {
        this.artNum = artNum;
    }

    public int getNewNum()
    {
        return newNum;
    }

    public void setNewNum(int newNum)
    {
        this.newNum = newNum;
    }

    public int getOnlineNum()
    {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum)
    {
        this.onlineNum = onlineNum;
    }

    public int getReceivedNum()
    {
        return receivedNum;
    }

    public void setReceivedNum(int receivedNum)
    {
        this.receivedNum = receivedNum;
    }

    public int getSendbckNum()
    {
        return sendbckNum;
    }

    public void setSendbckNum(int sendbckNum)
    {
        this.sendbckNum = sendbckNum;
    }

    public int getSiteId()
    {
        return siteId;
    }

    public void setSiteId(int siteId)
    {
        this.siteId = siteId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
