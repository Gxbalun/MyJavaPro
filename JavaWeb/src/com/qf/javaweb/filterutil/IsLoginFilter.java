package com.qf.javaweb.filterutil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断是否登录过滤器
 */
public class IsLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("acc");
        if(account != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.sendRedirect("login.jsp");
        }
    }
}
