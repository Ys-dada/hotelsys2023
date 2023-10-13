package com.abc.hotelsys.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("登录认证过滤器加载成功!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String addr = req.getRequestURI();
        String target = addr.substring(addr.lastIndexOf("/")+1);
        logger.debug("you visit target:"+target);

        if(target.equals("hotelMgr")||target.equals("roomMgr")||target.equals("userMgr")){
            Object obj = req.getSession().getAttribute("loginedUser");
            if(obj==null){ //尚未登录
                logger.debug("login fail , try to login...");
                res.sendRedirect("securityMgr?task=toLogin");
                return;
            }
        }

        logger.debug("logging ok, goto page!");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        logger.debug("登录认证过滤器卸载成功!!");
    }

}
