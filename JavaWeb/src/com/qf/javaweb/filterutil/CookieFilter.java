package com.qf.javaweb.filterutil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 减少登录页面java代码
 */

public class CookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //从cookies中拿出值
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Cookie[] cookies = request.getCookies();
        String account = "";
        String password = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("acccoke")){
                account = cookie.getValue();
            }
            if(cookie.getName().equals("pwdcoke")){
                password = cookie.getValue();
            }
        }
        servletRequest.setAttribute("account",account);
        servletRequest.setAttribute("password",password);
        //HttpServletResponse response = (HttpServletResponse)servletResponse;
        //response.sendRedirect("login.jsp");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
