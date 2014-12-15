package com.xsc.lottery.admin.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.customer")
public class CustomerAction extends AdminBaseAction
{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private CommunityService communityService;

    private Page<Customer> page;

    private Customer customer;

    private Long customerId;

    @SuppressWarnings("unused")
    private Long[] cusId;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String f_serch;

    private Calendar f_starTime;

    private Calendar f_endTime;

    private Calendar f_sTime;

    private Calendar f_eTime;

    private String f_orderserch;

    private String f_serchName;

    private UserType[] userTypes = UserType.values();
    private UserType  userType;

    private String f_userTypes;

    private int statu;

    private BigDecimal paymentMoeny = new BigDecimal(0);

    private BigDecimal backMoeny = new BigDecimal(0);

    private BigDecimal feeMoney = new BigDecimal(0);

    private BigDecimal outMoney = new BigDecimal(0);

    private Calendar gcTime;
    
    private Boolean isApply;
    
    private Boolean isPass;

    public String index()
    {
        page = new Page<Customer>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getLotteryCustomerPage(page, f_sTime, f_eTime,
                f_orderserch, f_serch, f_starTime, f_endTime, f_serchName, userType,null,null,null);
        return "list";
    }

    public String view()
    {
        customer = customerService.findById(customerId);
        List<BackMoneyRequest> backList = customerService
                .getBackMoneyRequestList(customer);
        List<Order> orderList = orderService.getOrder(customer, null);
        List<Order> orderList1 = orderService.getOrderByNotCustomer(customer);
        for (BackMoneyRequest b : backList) {
            backMoeny = backMoeny.add(b.getMoney());
            feeMoney = feeMoney.add(b.getFeeMoney());
        }

        paymentMoeny = customerService.getChongZhiSum(customer);

        for (Order o : orderList) {
            if (o.getCommunity() != null) {
                List<Part> listPart = communityService.getPartList(o
                        .getCommunity(), customer);
                for (Part p : listPart) {
                    if (o.getStatus().equals(OrderStatus.出票成功)
                            || o.getStatus().equals(OrderStatus.出票中))
                        outMoney = outMoney.add(p.getMoney());
                }
            }
            else {
                outMoney = outMoney.add(o.getOutAmount());
            }
        }
        for (Order o : orderList1) {
            List<Part> listPart = communityService.getPartList(
                    o.getCommunity(), customer);
            for (Part p : listPart) {
                if (o.getStatus().equals(OrderStatus.出票成功)
                        || o.getStatus().equals(OrderStatus.出票中))
                    outMoney = outMoney.add(p.getMoney());
            }
        }

        if (orderList != null && orderList.size() > 0) {
            gcTime = orderList.get(orderList.size() - 1).getBuildTime();
        }
        return "view";
    }

    public String dj()
    {
        //if (cusId != null && cusId.length > 0) {
        //    for (Long id : cusId) {
                Customer customer = customerService.findById(customerId);
                if (statu == 0 || statu == 1)
                    customer.getWallet().setStatus(statu);
                customerService.updateWallet(customer.getWallet());
        //    }
        //}
        return index();
    }

    public Calendar getGcTime()
    {
        return gcTime;
    }

    public void setStatu(int statu)
    {
        this.statu = statu;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Page<Customer> getPage()
    {
        return page;
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

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public String getF_serch()
    {
        return f_serch;
    }

    public void setF_serch(String fSerch)
    {
        f_serch = fSerch;
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

    public String getF_orderserch()
    {
        return f_orderserch;
    }

    public void setF_orderserch(String fOrderserch)
    {
        f_orderserch = fOrderserch;
    }

    public String getF_serchName()
    {
        return f_serchName;
    }

    public void setF_serchName(String fSerchName)
    {
        f_serchName = fSerchName;
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

    public BigDecimal getPaymentMoeny()
    {
        return paymentMoeny;
    }

    public BigDecimal getBackMoeny()
    {
        return backMoeny;
    }

    public BigDecimal getFeeMoney()
    {
        return feeMoney;
    }

    public BigDecimal getOutMoney()
    {
        return outMoney;
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

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserType getUserType() {
		return userType;
	}

	public Boolean getIsApply() {
		return isApply;
	}

	public void setIsApply(Boolean isApply) {
		this.isApply = isApply;
	}

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

}
