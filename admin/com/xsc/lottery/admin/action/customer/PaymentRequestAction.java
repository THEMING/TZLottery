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
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CustomerService;

@SuppressWarnings({ "serial", "unused" })
@Scope("prototype")
@Controller("customer.paymentRequest")
public class PaymentRequestAction extends AdminBaseAction
{

    @Autowired
    private CustomerService customerService;

    private Page<PaymentRequest> page;

    private MoneyChannel[] moneyChannel = MoneyChannel.values();

    private String f_moneyChannel;

    private String f_name;

    private String f_numNo;

    private Calendar f_starTime;

    private Calendar f_endTime;

    private Calendar f_sTime;

    private Calendar f_eTime;

    private String f_statu;

    private UserType[] userTypes = UserType.values();

    private String f_userTypes;

    private String f_user;

    private Long prId;

    private int pageNo = 1;

    private int pageSize = 50;

    private int totalPages;

    private String chnId; // 模块ID

    public String index()
    {
    	try {
    		 page = new Page<PaymentRequest>();
    	        page.setPageNo(pageNo);
    	        page.setPageSize(pageSize);
    	        page.setAutoCount(true);
    	        page = customerService.getPaymentRequestPage(page, f_moneyChannel,
    	                f_name, f_numNo, f_starTime, f_endTime, f_statu, f_user,
    	                f_userTypes, f_sTime, f_eTime);
    	        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
    }

    public String tj()
    {
        try {
            f_name = URLDecoder.decode(f_name, "UTF-8");
            f_statu = URLDecoder.decode(f_statu, "UTF-8");
            f_user = URLDecoder.decode(f_user, "UTF-8");
            f_moneyChannel = URLDecoder.decode(f_moneyChannel, "UTF-8");
            f_userTypes = URLDecoder.decode(f_userTypes, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            f_name = "";
            f_statu = "";
            f_user = "";
            f_moneyChannel = "";
            f_userTypes = "";
        }
        List<PaymentRequest> list = customerService.getPaymentRequestList(
                f_moneyChannel, f_name, f_numNo, f_starTime, f_endTime,
                f_statu, f_user, f_userTypes, f_sTime, f_eTime);
        BigDecimal inMoney = new BigDecimal(0);// 成功金额
        BigDecimal notMoney = new BigDecimal(0);// 失败金额
        Map<String, String> nameMap = new HashMap<String, String>();
        for (PaymentRequest pr : list) {
            if (!pr.getChannel().equals(MoneyChannel.手动补单))
                if (pr.isFinish()) {
                    inMoney = inMoney.add(pr.getMoney());
                }
                else {
                    notMoney = notMoney.add(pr.getMoney());
                }
            nameMap.put(pr.getCustomer().getNickName(), pr.getCustomer()
                    .getNickName());
        }
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("inMoney", inMoney.toString());
        resultMap.put("notMoney", notMoney.toString());
        resultMap.put("count", nameMap.size() + "");
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }

    public String budan()
    {

        return null;
    }

    public String getF_moneyChannel()
    {
        return f_moneyChannel;
    }

    public void setF_moneyChannel(String fMoneyChannel)
    {
        f_moneyChannel = fMoneyChannel;
    }

    public Long getPrId()
    {
        return prId;
    }

    public void setPrId(Long prId)
    {
        this.prId = prId;
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

    public String getChnId()
    {
        return chnId;
    }

    public void setChnId(String chnId)
    {
        this.chnId = chnId;
    }

    public Page<PaymentRequest> getPage()
    {
        return page;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public MoneyChannel[] getMoneyChannel()
    {
        return moneyChannel;
    }

    public String getF_name()
    {
        return f_name;
    }

    public void setF_name(String fName)
    {
        f_name = fName;
    }

    public String getF_numNo()
    {
        return f_numNo;
    }

    public void setF_numNo(String fNumNo)
    {
        f_numNo = fNumNo;
    }

    public Calendar getF_starTime()
    {
        return f_starTime;
    }

    public void setF_starTime(Calendar fStarTime)
    {
        f_starTime = fStarTime;
    }

    public Calendar getF_endTime()
    {
        return f_endTime;
    }

    public void setF_endTime(Calendar fEndTime)
    {
        f_endTime = fEndTime;
    }

    public String getF_statu()
    {
        return f_statu;
    }

    public void setF_statu(String fStatu)
    {
        f_statu = fStatu;
    }

    public String getF_user()
    {
        return f_user;
    }

    public void setF_user(String fUser)
    {
        f_user = fUser;
    }

    public String getF_userTypes()
    {
        return f_userTypes;
    }

    public void setF_userTypes(String fUserTypes)
    {
        f_userTypes = fUserTypes;
    }

    public UserType[] getUserTypes()
    {
        return userTypes;
    }

    public Calendar getF_sTime()
    {
        return f_sTime;
    }

    public void setF_sTime(Calendar fSTime)
    {
        f_sTime = fSTime;
    }

    public Calendar getF_eTime()
    {
        return f_eTime;
    }

    public void setF_eTime(Calendar fETime)
    {
        f_eTime = fETime;
    }

}
