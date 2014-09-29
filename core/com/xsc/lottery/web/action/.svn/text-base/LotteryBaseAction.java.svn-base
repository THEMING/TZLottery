package com.xsc.lottery.web.action;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LotteryBaseAction extends ActionSupport
{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String REDIRECT = "redirect";
    public static final String FAILURE = "failure";
    public static final String VIEW = "view";
    public static final String INDEX = "index";
    public static final String AJAXJSON = "ajaxjson";
    public static final String LOGIN = "login";

    private String action = "index";
    private String jsonString;
    private String remoteIp;

    public HttpServletRequest getRequest()
    {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse()
    {
        return ServletActionContext.getResponse();
    }

    @SuppressWarnings("unchecked")
    protected String executeMethod(String method) throws Exception
    {
        logger.info("Execute Method = " + method);
        Class[] c = null;
        Method m = this.getClass().getMethod(method, c);
        Object[] o = null;
        String result = (String) m.invoke(this, o);
        logger.info("return : " + result);
        return result;
    }

    public String execute()
    {
        try {
            return this.executeMethod(action);
        }
        catch (Throwable e) {
            e.printStackTrace();
            logger.error("ActionError", e);
            this.addActionError(this.getText("error.message"));
            return ERROR;
        }
    }

    protected Map<String, Object> getSession()
    {
        return ServletActionContext.getContext().getSession();
    }

    public String getJsonString()
    {
        return jsonString;
    }

    public void setJsonString(String jsonString)
    {
        this.jsonString = jsonString;
    }

    public String getRemoteIp()
    {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp)
    {
        this.remoteIp = remoteIp;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

	public String getAction() {
		return action;
	}
    
}
