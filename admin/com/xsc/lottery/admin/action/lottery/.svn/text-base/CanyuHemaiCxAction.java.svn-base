package com.xsc.lottery.admin.action.lottery;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryOrderService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.canyuHemaiCx")
public class CanyuHemaiCxAction extends AdminBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    private Page<Part> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String f_serch;// 用户名/订单号

    private String f_value;// 查询内容

    private String f_serchTerm;// 期次范围

    private String f_serchTerm1;// 期次范围

    private Calendar f_stime;// 时间查询（开始时间）

    private Calendar f_etime;// 时间查询（结束时间）

    private String f_okPart1;// 完成进度范围

    private String f_okPart2;// 完成进度范围

    private CommunityType[] statu = CommunityType.values();

    private String f_sstatu;// 出票状态

    private LotteryType[] types = LotteryType.values();

    private String f_type;// 彩种类型

    public String index()
    {
        page = new Page<Part>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getParticipateSale(page, f_serch, f_value,
                f_serchTerm, f_serchTerm1, f_stime, f_etime, f_okPart1,
                f_okPart2, f_sstatu, f_type);
        return "list";
    }

    public String tj()
    {
        try {
            f_serch = URLDecoder.decode(f_serch, "UTF-8");
            f_value = URLDecoder.decode(f_value, "UTF-8");
            f_type = URLDecoder.decode(f_type, "UTF-8");
            f_sstatu = URLDecoder.decode(f_sstatu, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            f_serch = "";
            f_value = "";
            f_type = "";
            f_sstatu = "";
        }
        List<Part> list = orderService.getPartList(f_serch, f_value,
                f_serchTerm, f_serchTerm1, f_stime, f_etime, f_okPart1,
                f_okPart2, f_sstatu, f_type);
        BigDecimal buymoney = new BigDecimal(0);// 购买金额
        BigDecimal zjmoney = new BigDecimal(0);// 中奖金额
        Map<String, String> resultMap = new HashMap<String, String>();
        for (Part p : list) {
            buymoney = buymoney.add(p.getMoney());
            System.out.println(p.getWinTaxMoney().toString());
            // if(p.getWinTaxMoney()!=null){
            // zjmoney=zjmoney.add(p.getWinTaxMoney());
            // }
        }
        resultMap.put("buymoney", buymoney.toString());
        resultMap.put("zjmoney", zjmoney.toString());
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public String getF_serch()
    {
        return f_serch;
    }

    public void setF_serch(String fSerch)
    {
        f_serch = fSerch;
    }

    public String getF_value()
    {
        return f_value;
    }

    public void setF_value(String fValue)
    {
        f_value = fValue;
    }

    public String getF_serchTerm()
    {
        return f_serchTerm;
    }

    public void setF_serchTerm(String fSerchTerm)
    {
        f_serchTerm = fSerchTerm;
    }

    public String getF_serchTerm1()
    {
        return f_serchTerm1;
    }

    public void setF_serchTerm1(String fSerchTerm1)
    {
        f_serchTerm1 = fSerchTerm1;
    }

    public Calendar getF_stime()
    {
        return f_stime;
    }

    public void setF_stime(Calendar fStime)
    {
        f_stime = fStime;
    }

    public Calendar getF_etime()
    {
        return f_etime;
    }

    public void setF_etime(Calendar fEtime)
    {
        f_etime = fEtime;
    }

    public String getF_okPart1()
    {
        return f_okPart1;
    }

    public void setF_okPart1(String fOkPart1)
    {
        f_okPart1 = fOkPart1;
    }

    public String getF_okPart2()
    {
        return f_okPart2;
    }

    public void setF_okPart2(String fOkPart2)
    {
        f_okPart2 = fOkPart2;
    }

    public String getF_sstatu()
    {
        return f_sstatu;
    }

    public void setF_sstatu(String fSstatu)
    {
        f_sstatu = fSstatu;
    }

    public String getF_type()
    {
        return f_type;
    }

    public void setF_type(String fType)
    {
        f_type = fType;
    }

    public Page<Part> getPage()
    {
        return page;
    }

    public CommunityType[] getStatu()
    {
        return statu;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

}
