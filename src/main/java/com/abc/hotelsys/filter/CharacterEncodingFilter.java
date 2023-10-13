package com.abc.hotelsys.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    private static final Logger logger = Logger.getLogger(CharacterEncodingFilter.class);

    String code = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        filterConfig 代表的是过滤器的配置文件（也就是xml文件会被解析成为一个filterConfig对象）
        code = filterConfig.getInitParameter("code");
        logger.debug("编码过滤器加载成功，编码识别为"+code);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(code);
        chain.doFilter(request,response); //执行下一个过滤器
    }

    @Override
    public void destroy() {
        logger.debug("编码过滤器卸载成功!");
    }

}
