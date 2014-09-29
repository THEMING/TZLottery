package com.xsc.lottery.admin.action.oracleData.base;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pagination implements Serializable
{
    private int rscount;// 记录总数
    private int pageno;// 当前页
    private int pagesize;// 页达大小

    public int getPageno()
    {
        return pageno;
    }

    public void setPageno(int pageno)
    {
        this.pageno = pageno;
    }

    public int getPagesize()
    {
        return pagesize;
    }

    public void setPagesize(int pagesize)
    {
        this.pagesize = pagesize;
    }

    public int getRscount()
    {
        return rscount;
    }

    public void setRscount(int rscount)
    {
        this.rscount = rscount;
    }
}