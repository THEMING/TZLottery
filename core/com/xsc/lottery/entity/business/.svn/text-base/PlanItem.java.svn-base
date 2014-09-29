package com.xsc.lottery.entity.business;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.enumerate.PlayType;

/**
 * 方案内容
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_plan_item")
@SequenceGenerator(name = "S_PlanItem", allocationSize = 1, initialValue = 1, sequenceName = "S_PlanItem")
public class PlanItem extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_PlanItem")
    private Long id;

    /** 选号方式 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "play_type", nullable = false)
    private PlayType playType;

    /** 所属方案 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    /** 内容,如是文件上传,为null */
    private String content;
    
    private String betContent;
    private String issue; //lastmatchno
    
    @Transient
    private String comments;
    
    /** 上传文件的路径,不是文件上传,为null */
    @Column(name = "file_path")
    private String filePath;

    /** 注数 */
    @Column(name = "bet_count", nullable = false)
    private int betCount;

    /** 单注金额---默认每注2元乐,透菜种追加3元.开乐彩普通投注为1元 */
    private BigDecimal oneMoney = new BigDecimal(2);

    /** 中奖描述 */
    @OneToMany(mappedBy = "item")
    private List<WinDescribeTicket> winDescribeTicket;

    /** 拆票列表 , 实际是1对1*/
    @OneToMany(mappedBy = "item")
    private List<Ticket> Ticket;
    
    
    public PlanItem()
    {
        super();
    }

    public PlanItem(PlayType playType, String content)
    {
        super();
        this.playType = playType;
        this.content = content;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public List<Ticket> getTicket()
    {
        return Ticket;
    }

    public void setTicket(List<Ticket> ticket)
    {
        Ticket = ticket;
    }

    public PlayType getPlayType()
    {
        return playType;
    }

    public void setPlayType(PlayType playType)
    {
        this.playType = playType;
    }

    public Plan getPlan()
    {
        return plan;
    }

    public String getContent()
    {
        return content;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setPlan(Plan plan)
    {
        this.plan = plan;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public int getBetCount()
    {
        return betCount;
    }

    public void setBetCount(int betCount)
    {
        this.betCount = betCount;
    }
    
    public String getBetContent()
    {
    	return betContent;
    }
    
    public void setBetContent(String szValue)
    {
    	betContent = szValue;
    }
    
    public String getIssue()
    {
    	return issue;
    }
    
    public void setIssue(String szValue)
    {
    	issue = szValue;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<WinDescribeTicket> getWinDescribeTicket()
    {
        return winDescribeTicket;
    }

    public void setWinDescribeTicket(List<WinDescribeTicket> winDescribeTicket)
    {
        this.winDescribeTicket = winDescribeTicket;
    }

    public BigDecimal getOneMoney()
    {
        return oneMoney;
    }

    public void setOneMoney(BigDecimal oneMoney)
    {
        this.oneMoney = oneMoney;
    }

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
