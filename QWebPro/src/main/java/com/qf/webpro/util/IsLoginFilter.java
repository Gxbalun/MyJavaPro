package com.qf.webpro.util;

import com.qf.webpro.bean.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证用户是否已经登录
 */
public class IsLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("验证用户是否已经登录");
        //如何判断用户是否已经登录? 用session中的acc键值判断
        HttpServletRequest request = (HttpServletRequest)servletRequest;
          HttpSession session = request.getSession();
          //在调用getAttribute()时,时刻想想你在哪儿set了 键的名字是什么?
        Admin admin = (Admin)session.getAttribute("admin");
          if(admin==null){//用户没有登录
              //跳转到登录页面
              HttpServletResponse response = (HttpServletResponse)servletResponse;
              response.sendRedirect("login.jsp");
          }else{
              filterChain.doFilter(servletRequest, servletResponse);
          }
    }

}
