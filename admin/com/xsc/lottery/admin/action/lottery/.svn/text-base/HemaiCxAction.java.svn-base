package com.xsc.lottery.admin.action.lottery;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.hemaiCx")
public class HemaiCxAction extends AdminBaseAction
{
    @Autowired
    private CommunityService communityService;

    @Autowired
    private LotteryOrderService lotteryOrderService;

    @Autowired
    private LotteryPlanService planService;

    private Page<Community> page;

    private List<Part> partList;

    private Community community;

    private String numberNo;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String f_serch;// 用户名/订单号

    private String f_value;// 查询内容

    private String f_serchTerm;

    private String f_serchTerm1;

    private Calendar f_stime;// 时间查询（开始时间）

    private Calendar f_etime;// 时间查询（结束时间）

    private String f_Amount1;// 方案总额范围

    private String f_Amount2;// 方案总额范围

    private String f_okPart1;// 完成进度范围

    private String f_okPart2;// 完成进度范围

    private CommunityType[] statu = CommunityType.values();

    private String f_sstatu;// 出票状态

    private LotteryType[] types = LotteryType.values();

    private String f_type;// 彩种类型

    public String index()
    {
        page = new Page<Community>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = communityService.getCommunityPage(page, f_serch, f_value,
                f_serchTerm, f_serchTerm1, f_stime, f_etime, f_Amount1,
                f_Amount2, f_okPart1, f_okPart2, f_sstatu, f_type);
        for (Community c : page.getResult()) {
            Order order = lotteryOrderService.getOrderByCommunity(c);
            c.setOrder(order);
        }
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
        List<Community> list = communityService.getCommunityList(f_serch,
                f_value, f_serchTerm, f_serchTerm1, f_stime, f_etime,
                f_Amount1, f_Amount2, f_okPart1, f_okPart2, f_sstatu, f_type);
        Map<String, String> resultMap = new HashMap<String, String>();
        int mynum = 0;
        int wmynum = 0;
        for (Community c : list) {
            if (c.getCommunityType().equals(CommunityType.已满员)) {
                mynum += 1;
            }
            if (c.getCommunityType().equals(CommunityType.未满员)
                    || c.getCommunityType().equals(CommunityType.已流产)) {
                wmynum += 1;
            }
        }
        resultMap.put("mynum", mynum + "");
        resultMap.put("wmynum", wmynum + "");
        resultMap.put("totle", list.size() + "");
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }

    public String detail()
    {
        if (!StringUtils.isEmpty(numberNo)) {
            Plan plan = planService.getPlanBynumberNo(numberNo);
            community = communityService.getCommunityByPlan(plan);
            Order order = lotteryOrderService.getOrderByCommunity(community);
            community.setOrder(order);
            partList = communityService.getPartByCommunity(community);
        }
        return "view";
    }

    public List<Part> getPartList()
    {
        return partList;
    }

    public Community getCommunity()
    {
        return community;
    }

    public void setPlanService(LotteryPlanService planService)
    {
        this.planService = planService;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = numberNo;
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

    public String getF_Amount1()
    {
        return f_Amount1;
    }

    public void setF_Amount1(String fAmount1)
    {
        f_Amount1 = fAmount1;
    }

    public String getF_Amount2()
    {
        return f_Amount2;
    }

    public void setF_Amount2(String fAmount2)
    {
        f_Amount2 = fAmount2;
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

    public Page<Community> getPage()
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

    public void setCommunityService(CommunityService communityService)
    {
        this.communityService = communityService;
    }

    public void setLotteryOrderService(LotteryOrderService lotteryOrderService)
    {
        this.lotteryOrderService = lotteryOrderService;
    }

}
