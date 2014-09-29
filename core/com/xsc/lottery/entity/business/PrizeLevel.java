package com.xsc.lottery.entity.business;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xsc.lottery.entity.BaseObject;

/** 彩期奖级 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business_prize_level")
@SequenceGenerator(name = "S_PrizeLevel", allocationSize = 1, initialValue = 1, sequenceName = "S_PrizeLevel")
public class PrizeLevel extends BaseObject
{
    @Id
    @GeneratedValue
    // (strategy=GenerationType.SEQUENCE, generator="S_PrizeLevel")
    private Long id;

    /** 级别 */
    @Column(name = "level_index")
    private int level;

    /** 彩期 */
    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    private LotteryTerm term;

    /** 中奖注数 */
    private int betNum;

    /** 奖级名称 */
    private String name;

    /** 奖金 */
    private BigDecimal prize;

    /** 追加中奖注数 */
    private int addBetNum;

    /** 追加奖金 */
    private BigDecimal addPrize;

    public PrizeLevel()
    {
    }

    public PrizeLevel(int level, LotteryTerm term, int betNum, String name,
            BigDecimal prize, BigDecimal addedPrize)
    {
        super();
        this.level = level;
        this.term = term;
        this.betNum = betNum;
        this.name = name;
        this.prize = prize;
        this.addPrize = addedPrize;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public BigDecimal getAllPrize()
    {
        return prize.add(addPrize);
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrize()
    {
        return prize;
    }

    public LotteryTerm getTerm()
    {
        return term;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getBetNum()
    {
        return betNum;
    }

    public void setBetNum(int betNum)
    {
        this.betNum = betNum;
    }

    public void setTerm(LotteryTerm term)
    {
        this.term = term;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrize(BigDecimal prize)
    {
        this.prize = prize;
    }

    public int getAddBetNum()
    {
        return addBetNum;
    }

    public void setAddBetNum(int addBetNum)
    {
        this.addBetNum = addBetNum;
    }

    public BigDecimal getAddPrize()
    {
        return addPrize;
    }

    public void setAddPrize(BigDecimal addPrize)
    {
        this.addPrize = addPrize;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
