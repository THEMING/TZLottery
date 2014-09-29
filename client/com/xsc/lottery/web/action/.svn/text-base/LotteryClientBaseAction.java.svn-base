package com.xsc.lottery.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.service.business.CustomerService;

@SuppressWarnings("serial")
public class LotteryClientBaseAction extends LotteryBaseAction
{
    public static final String SESSION_CUSTOMER_KEY = "customer";
    public static final String SESSION_NUM_KEY = "num";
    public static final String SESSION_TOTAL1_KEY = "totalSumMoney1";
    public static final String SESSION_TOTAL2_KEY = "totalSumMoney2";
    
    @Autowired
    private CustomerService customerservice;
    
	//菜单栏导航
    protected int navIndex;

	public Customer getCurCustomer()
    {
        Customer customer = (Customer) getSession().get(SESSION_CUSTOMER_KEY);
        if (null != customer) {
            customer = customerservice.findById(customer.getId());
            saveCurCustomer(customer);
        }
        return customer;
    }

    protected void saveCurCustomer(Customer customer)
    {
        getSession().put(SESSION_CUSTOMER_KEY, customer);
    }
    
    public int getNavIndex() {
		return navIndex;
	}

	public void setNavIndex(int navIndex) {
		this.navIndex = navIndex;
	}
}
