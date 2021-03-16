package com.qf.javaweb.filterutil;

import javax.servlet.*;
import java.io.IOException;

/**
 * 统一编码过滤器
 */
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        //让本次过滤继续执行下去
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
