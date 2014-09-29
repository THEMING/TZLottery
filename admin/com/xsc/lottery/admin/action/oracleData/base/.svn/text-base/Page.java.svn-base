package com.xsc.lottery.admin.action.oracleData.base;

import java.util.List;

public class Page<T>
{
    private List<T> result;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int totalPages;
    private int pageEndRow;
    private int pageStartRow;

    public List<T> getResult()
    {
        return result;
    }

    public void setResult(List<T> result)
    {
        this.result = result;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
        // 计算当前页开始行和结束行
        pageStartRow = (pageNo - 1) * pageSize + 1;
        pageEndRow = pageStartRow + pageSize - 1;

    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
        if (this.totalCount % pageSize == 0) {
            totalPages = this.totalCount / pageSize;
        }
        else {
            totalPages = this.totalCount / pageSize + 1;
        }
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public int getPageEndRow()
    {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow)
    {
        this.pageEndRow = pageEndRow;
    }

    public int getPageStartRow()
    {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow)
    {
        this.pageStartRow = pageStartRow;
    }

}
