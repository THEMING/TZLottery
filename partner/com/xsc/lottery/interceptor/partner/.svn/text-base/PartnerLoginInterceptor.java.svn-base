package com.xsc.lottery.interceptor.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xsc.lottery.action.partner.PartnerBaseAction;

@SuppressWarnings("serial")
public class PartnerLoginInterceptor extends AbstractInterceptor
{
protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
        PartnerBaseAction action = (PartnerBaseAction) invocation.getAction();
        if (null == action.getCurClient()) {
            return "login";
        }
        return invocation.invoke();
    }
}
