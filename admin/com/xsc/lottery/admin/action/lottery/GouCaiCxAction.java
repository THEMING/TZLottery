package com.xsc.lottery.admin.action.lottery;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.Supplier;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderResult;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;
import com.xsc.lottery.service.business.SupplierService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.goucaiCx")
public class GouCaiCxAction extends AdminBaseAction
{
    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private LotteryPlanService planService;
    
    @Autowired
    private SupplierService supplierService; 

    private Page<Order> page;

    private Order order;

    private Long orderId;

    private String numberNo;

    private int pageNo = 1;

    private int pageSize = 50;

    private int totalPages;

    private String f_serch;// 用户名/订单号

    private String f_value;// 查询内容

    private String f_name;// 发起人

    private String f_serchTerm;

    private String f_serchTerm1;

    private Calendar f_stime;// 时间查询（开始时间）

    private Calendar f_etime;// 时间查询（结束时间）

    private String f_style;// 购彩方式

    private String f_Amount1;// 方案总额范围

    private String f_Amount2;// 方案总额范围

    private String f_winTaxMoney1;// 中奖金额范围

    private String f_winTaxMoney2;// 中奖金额范围

    private Calendar reg_stime;// 用户注册时间查询（开始时间）

    private Calendar reg_etime;// 用户注册时间查询（结束时间）

    private OrderStatus[] ostatus = OrderStatus.values();

    private String f_sstatu;// 出票状态

    private OrderResult[] oresult = OrderResult.values();

    private String f_rstatu;// 中奖状态

    private LotteryType[] types = LotteryType.values();

    private String f_type;// 彩种类型
    
    private UserType[] userTypes = UserType.values();
    
    private UserType  userType;
       
    private String ticketThirdName;//出票商
    
    private List<String> names;
    
    public String index()
    {
    	System.out.println("=============userType:"+userType);
    	getAllNames();
        page = new Page<Order>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getOrderPage(page, f_serch, f_value, f_type,
                f_sstatu, f_rstatu, f_style, f_stime, f_etime, f_serchTerm,
                f_serchTerm1, f_winTaxMoney1, f_winTaxMoney2, f_Amount1,
                f_Amount2, reg_stime, reg_etime, userType,ticketThirdName);

        return "list";
    }

    private void getAllNames(){
    	List<Supplier> all = supplierService.getAllSupplierList();
    	names = new ArrayList<String>();
    	for (int i = 0; i < all.size(); i++) {
    		String tempNameString = all.get(i).getName();
			if (names.contains(tempNameString)) {
				continue;
			}
			else {
				names.add(tempNameString);
			}
		}
    }
    
    public String tongj()
    {
    	System.out.println("======================进来了========================");
        try {
            f_serch = URLDecoder.decode(f_serch, "UTF-8");
            f_value = URLDecoder.decode(f_value, "UTF-8");
            f_type = URLDecoder.decode(f_type, "UTF-8");
            f_sstatu = URLDecoder.decode(f_sstatu, "UTF-8");
            f_style = URLDecoder.decode(f_style, "UTF-8");
            f_rstatu = URLDecoder.decode(f_rstatu, "UTF-8");
            ticketThirdName=URLDecoder.decode(ticketThirdName, "UTF-8");
          
        }
        catch (UnsupportedEncodingException e) {
            f_serch = "";
            f_value = "";
            f_type = "";
            f_sstatu = "";
            f_style = "";
            f_rstatu = "";
            ticketThirdName="";

        }
System.out.println("f_type = " + f_type);
        List<Order> list = orderService.getOrder(f_serch, f_value, f_type,
                f_sstatu, f_rstatu, f_style, f_stime, f_etime, f_serchTerm,
                f_serchTerm1, f_winTaxMoney1, f_winTaxMoney2, f_Amount1,
                f_Amount2, reg_stime, reg_etime,userType,ticketThirdName);
        BigDecimal outamount = new BigDecimal(0);// 出票成功金额
        BigDecimal failuramount = new BigDecimal(0);// 出票失败金额
        BigDecimal allamount = new BigDecimal(0);
        int successplan = 0; // 成功方案数
        int zjplan = 0; // 中奖方案数
        BigDecimal zjamount = new BigDecimal(0);// 税前
        BigDecimal zjtxtamount = new BigDecimal(0);// 税后
        Map<String, String> resultMap = new HashMap<String, String>();
        Map<String, String> nameMap = new HashMap<String, String>();
        for (Order o : list) {
        	allamount = allamount.add(o.getAmount());
            if (o.getStatus().equals(OrderStatus.出票成功)) {

                outamount = outamount.add(o.getOutAmount());

                successplan += 1;
            }
            
            if ( o.getStatus().equals(OrderStatus.部分出票成功)) {
                outamount = outamount.add(o.getOutAmount());
                
                failuramount = failuramount.add(o.getAmount().subtract(o.getOutAmount()));
                
                successplan += 1;
			}
            
            if (o.getStatus().equals(OrderStatus.出票失败)) {
                failuramount = failuramount.add(o.getAmount());
            }

            if (o.getOrderResult().equals(OrderResult.已中奖) || o.getOrderResult().equals(OrderResult.已兑奖)) {
                zjplan += 1;
                zjtxtamount = zjtxtamount.add(o.getWinTaxMoney());
                zjamount = zjamount.add(o.getWinMoney());
            }
            nameMap.put(o.getCustomer().getNickName(), o.getCustomer()
                    .getNickName());
        }
        int countplan = list.size();// 总方案
        resultMap.put("allamount", allamount.toString());
        resultMap.put("cplan", countplan + "");
        resultMap.put("outamount", outamount.toString());
        resultMap.put("failur", failuramount.toString());
        resultMap.put("suplan", successplan + "");
        resultMap.put("zjplan", zjplan + "");
        resultMap.put("shuiqian", zjamount.toString());
        resultMap.put("shuihou", zjtxtamount.toString());
        resultMap.put("count", nameMap.size() + "");
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        
        System.out.println("shuipian = " + zjamount.toString());
        System.out.println("shuihou = " + zjtxtamount.toString());
        System.out.println("zjplan = " + zjplan);
        return AJAXJSON;
    }

    public String detail()
    {
        if (orderId != null && orderId > 0l) {
            order = orderService.findById(orderId);
        }
        else {
            Plan plan = planService.getPlanBynumberNo(numberNo);
            order = orderService.getOrderByPlan(plan);
        }

        return "view";
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
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

    public String getF_name()
    {
        return f_name;
    }

    public void setF_name(String fName)
    {
        f_name = fName;
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

    public String getF_style()
    {
        return f_style;
    }

    public void setF_style(String fStyle)
    {
        f_style = fStyle;
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

    public String getF_winTaxMoney1()
    {
        return f_winTaxMoney1;
    }

    public void setF_winTaxMoney1(String fWinTaxMoney1)
    {
        f_winTaxMoney1 = fWinTaxMoney1;
    }

    public String getF_winTaxMoney2()
    {
        return f_winTaxMoney2;
    }

    public void setF_winTaxMoney2(String fWinTaxMoney2)
    {
        f_winTaxMoney2 = fWinTaxMoney2;
    }

    public String getF_sstatu()
    {
        return f_sstatu;
    }

    public void setF_sstatu(String fSstatu)
    {
        f_sstatu = fSstatu;
    }

    public String getF_rstatu()
    {
        return f_rstatu;
    }

    public void setF_rstatu(String fRstatu)
    {
        f_rstatu = fRstatu;
    }

    public String getF_type()
    {
        return f_type;
    }

    public void setF_type(String fType)
    {
        f_type = fType;
    }

    public Page<Order> getPage()
    {
        return page;
    }

    public OrderStatus[] getOstatus()
    {
        return ostatus;
    }

    public OrderResult[] getOresult()
    {
        return oresult;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

    public Calendar getReg_stime()
    {
        return reg_stime;
    }

    public void setReg_stime(Calendar regStime)
    {
        reg_stime = regStime;
    }

    public Calendar getReg_etime()
    {
        return reg_etime;
    }

    public void setReg_etime(Calendar regEtime)
    {
        reg_etime = regEtime;
    }

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserType[] getUserTypes() {
		return userTypes;
	}

	public String getTicketThirdName() {
		return ticketThirdName;
	}

	public void setTicketThirdName(String ticketThirdName) {
		this.ticketThirdName = ticketThirdName;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
    
}
