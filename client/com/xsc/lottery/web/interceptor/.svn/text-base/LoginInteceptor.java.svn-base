package com.xsc.lottery.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xsc.lottery.web.action.LotteryClientBaseAction;
import com.xsc.lottery.web.action.json.AjaxResultStatusType;
import com.xsc.lottery.web.action.json.JsonMsgBean;

@SuppressWarnings("serial")
public class LoginInteceptor extends AbstractInterceptor
{
    private String isAjax = "false";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
        LotteryClientBaseAction action = (LotteryClientBaseAction) invocation
                .getAction();
        if (null == action.getCurCustomer()) {
            if (Boolean.valueOf(isAjax)) {
                action.setJsonString(JsonMsgBean
                       .getResultStatusJsonStrByType(AjaxResultStatusType._00000));
                return LotteryClientBaseAction.AJAXJSON;
            } 
            else {
                return LotteryClientBaseAction.LOGIN;
            }
        }
        return invocation.invoke();
    }

    public void setIsAjax(String isAjax)
    {
        this.isAjax = isAjax;
    }
}
