package com.qf.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class Demo3Servlet extends HttpServlet {

    /*
    只有post表单提交，只需要重写doPost

    问题：数据是如何被封装到HttpServletRequest中的
    HttpServletRequest是接口，extends ServletRequest
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        //获取请求头数据
        System.out.println(req.getMethod());//获取请求方式
        System.out.println(req.getContextPath());//获得当前项目的根目录
        System.out.println(req.getContentLength());//请求数据长度
        System.out.println(req.getContentType());//数据提交方式
        System.out.println(req.getProtocol());//请求协议
        System.out.println(req.getRemoteAddr());//在服务器端获得客户端ip
        System.out.println(req.getServerName());//获得服务器名(域名/ip)
        System.out.println(req.getServerPort());//获得服务器端

        //请求头中的数据时
        System.out.println(req.getHeader("User-Agent"));
        Enumeration<String> headerNames = req.getHeaderNames();//获得所有的请求头名字
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.println(name+"::::"+req.getHeader(name));
        }
    }

}
