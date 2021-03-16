package com.qf.javaweb.filterutil;


import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 账号密码过滤器
 */
public class AccPwdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //得到输入的账号密码
        String account = servletRequest.getParameter("num");
        String password = servletRequest.getParameter("password");
        servletResponse.setContentType("text/html;charset=utf-8");
        if(account.length()==0){
            PrintWriter out = servletResponse.getWriter();
            out.print("账号不能为空！");

        }else if(password.length() == 0){
            PrintWriter out = servletResponse.getWriter();
            out.print("密码不能为空");

        }else if(account.matches("[1][35789]\\d{9}") && password.matches("[A-z,0-9]{8,15}")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            PrintWriter out = servletResponse.getWriter();
            out.print("密码或账号格式不正确！");
        }
    }
}
