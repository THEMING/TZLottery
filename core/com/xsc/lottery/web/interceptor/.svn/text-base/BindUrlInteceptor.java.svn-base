package com.xsc.lottery.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xsc.lottery.web.action.LotteryBaseAction;

@SuppressWarnings("serial")
public class BindUrlInteceptor extends AbstractInterceptor
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String SESSION_CUSTOMER_FROM_KEY = "customer_from";
	public static final String SESSION_CUSTOMER_REFERENCE_KEY = "customer_reference";
	public static final String SESSION_CUSTOMER_REFERER_KEY = "customer_domainType";
    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String remoteIp = "";
            try {
                remoteIp = request.getHeader("Cdn-Src-Ip");
                if (remoteIp == null) {
                    remoteIp = request.getHeader("x-forwarded-for");
                    if (remoteIp == null) {
                        remoteIp = request.getRemoteAddr();
                    }
                    else {
                        remoteIp = remoteIp.split("\\,")[0];
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                remoteIp = request.getRemoteAddr();
            }
            logger.info("IP:" + remoteIp + " ==> requestURI: "
                    + request.getRequestURI());
            ((LotteryBaseAction) invocation.getAction()).setRemoteIp(remoteIp);
            //System.out.println(invocation.getAction().getClass());
            
            String from = request.getParameter("from");
            String reference = request.getParameter("reference");
            String referer = request.getLocalName();
            String previous=request.getHeader("Referer");		//��ȡ�����ߵ�ַ
            if(from != null) {
            	ActionContext.getContext().getSession().
            			put(SESSION_CUSTOMER_FROM_KEY, from);
            }
            if(reference != null) {
            	ActionContext.getContext().getSession().
            			put(SESSION_CUSTOMER_REFERENCE_KEY, reference);
            }
            if(previous!= null)
            {
            	if(previous.indexOf("zhcw")!=-1);
            	{
            		ActionContext.getContext().getSession().
            		put(SESSION_CUSTOMER_REFERER_KEY, "unknown");     	
            	}
            }
            
            if(referer.equals("fucai.369cai.com"))
            {
            	ActionContext.getContext().getSession().
    			put(SESSION_CUSTOMER_REFERER_KEY, "fucai");     	
            }
            else if(referer.equals("jingcai.369cai.com"))
            {
            	ActionContext.getContext().getSession().
    			put(SESSION_CUSTOMER_REFERER_KEY, "jingcai"); 
            }
           
            else
            {
            	ActionContext.getContext().getSession().
    			put(SESSION_CUSTOMER_REFERER_KEY, "unknown");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return invocation.invoke();
    }

}
