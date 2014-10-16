package com.xsc.lottery.interceptor.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xsc.lottery.action.partner.PartnerBaseAction;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
public class PartnerLoginInterceptor extends AbstractInterceptor
{
protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
    	LotteryClientBaseAction action = (LotteryClientBaseAction) invocation.getAction();
        if (null == action.getCurCustomer()) {
            return "login";
        }
        return invocation.invoke();
    }
}
