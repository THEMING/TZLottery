package com.xsc.lottery.admin.action.customer;

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
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CustomerService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.financial")
public class FinancialMngAction extends AdminBaseAction
{
    @Autowired
    private CustomerService customerService;

    private Page<WalletLog> page;

    private Long id;

    private Long customerId;

    private int pageNo = 1;

    private int pageSize = 20;

    private int totalPages;

    private Calendar f_stime;

    private Calendar f_etime;

    private String f_name;

    private String f_type;

    private String f_numberNo;

    private WalletLogType[] types = WalletLogType.values();

    public String index()
    {
        if (customerId != null) {
            Customer customer = customerService.findById(customerId);
            f_name = customer.getNickName();
        }
        page = new Page<WalletLog>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getWalletLogPage(page, f_name, f_numberNo,
                f_stime, f_etime, f_type);
        return "list";
    }

    public String tj()
    {
        try {
            f_name = URLDecoder.decode(f_name, "UTF-8");
            f_type = URLDecoder.decode(f_type, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            f_name = "";
            f_type = "";
        }
        int sr = 0;
        int zc = 0;
        int dj = 0;
        int jd = 0;
        BigDecimal srmoney = new BigDecimal(0);
        BigDecimal zcmoney = new BigDecimal(0);
        BigDecimal jdmoney = new BigDecimal(0);
        List<WalletLog> list = customerService.getWalletLogList(f_name,
                f_stime, f_etime, f_type);
        for (WalletLog wl : list) {
            if (wl.getBusinessType().equals(BusinessType.支出)) {
                zc += 1;
                zcmoney = zcmoney.add(wl.getOutMoney());
            }
            if (wl.getBusinessType().equals(BusinessType.收入)) {
                sr += 1;
                srmoney = srmoney.add(wl.getInMoney());
            }
            jdmoney = jdmoney.add(wl.getOutFreezeMoney());
        }
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("sr", sr + "");
        resultMap.put("zc", zc + "");
        resultMap.put("srmoney", srmoney.toString());
        resultMap.put("zcmoney", zcmoney.toString());
        resultMap.put("jdmoney", jdmoney.toString());
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getF_name()
    {
        return f_name;
    }

    public void setF_name(String fName)
    {
        try {
            this.f_name = URLDecoder.decode(fName, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            this.f_name = fName;
        }
    }

    public String getF_type()
    {
        return f_type;
    }

    public void setF_type(String fType)
    {
        f_type = fType;
    }

    public Page<WalletLog> getPage()
    {
        return page;
    }

    public WalletLogType[] getTypes()
    {
        return types;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public String getF_numberNo()
    {
        return f_numberNo;
    }

    public void setF_numberNo(String fNumberNo)
    {
        f_numberNo = fNumberNo;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

}
