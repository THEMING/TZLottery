package com.xsc.lottery.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter
{

    public void destroy()
    {
        // 过滤器销毁，一般是释放资源
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        
        /** 以下代码保留  做登录验证*/
        /*HttpSession session = request.getSession();
        
        if (session.getAttribute(LotteryClientBaseAction.SESSION_CUSTOMER_KEY) == null) {
            //String errors = "请先登陆，再访问此服务!";
            //request.setAttribute("errors", errors);
            request.getRequestDispatcher("/404.html").forward(request,
                    response);
        }
        else {
            arg2.doFilter(request, response);
        }*/
        
        request.getRequestDispatcher("/404.html").forward(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException
    {
        // 初始化操作，读取web.xml中过滤器配置的初始化参数，满足你提的要求不用此方法
    }
}
