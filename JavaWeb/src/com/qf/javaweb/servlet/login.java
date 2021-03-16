package com.qf.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String account = req.getParameter("account");
        String password= req.getParameter("password");
        String sex = req.getParameter("sex");
        String like = req.getParameter("like");
        String job = req.getParameter("job");
        System.out.println("账号："+account+"\n密码："+password+"\n性别："+sex+"\n爱好："+like+"\n职业："+job);
        PrintWriter out = resp.getWriter();
        out.write("<h1>login success!</h1>");
    }
}
