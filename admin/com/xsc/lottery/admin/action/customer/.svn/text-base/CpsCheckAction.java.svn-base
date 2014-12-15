package com.xsc.lottery.admin.action.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.service.business.CustomerService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.cpscheck")
public class CpsCheckAction extends AdminBaseAction
{

    @Autowired
    private CustomerService customerService;

    private Page<Customer> page;

    private Customer customer;

    private Long customerId;

    @SuppressWarnings("unused")
    private Long[] cusId;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;
    
    private Integer statu;
    
    private Boolean isApply;
    
    private Boolean isPass;

    public String index()
    {
        page = new Page<Customer>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getLotteryCustomerPage(page, null, null,
        		null, null, null, null, null, null,true,0,null);
        return "list";
    }

    public String view()
    {
        customer = customerService.findById(customerId);
        return "view";
    }
    
    public String check()
    {
    	Customer customer = customerService.findById(customerId);
    	if(statu==1||statu==2)
    	{
    		customer.setIsPass(statu);
    		customerService.update(customer);
    	}
    	return index();
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

	public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
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
