package com.qf.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Demo2Servlet extends HttpServlet {

    /*
        一般在servlet中不直接使用service，
        因为http请求分为get和post。
        所以把两种请求分开处理，get请求调用deGet，post请求调用doPost
        客户端请求到服务器后，依然调用service，会调用弗雷
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");
    }
    */

    /*
    处理get请求
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
    }

    /*
    处理post请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受表单中提交的数据
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        System.out.println(account);
        System.out.println(password);
        //调用dao层处理

        //响应
        PrintWriter out = resp.getWriter();
        out.write("<h1>login success!</h1>");
    }
}
