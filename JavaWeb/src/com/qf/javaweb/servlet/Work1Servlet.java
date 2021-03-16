package com.qf.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Work1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求信息
        String Text = req.getParameter("Text");
        System.out.println(Text);
        String Pass = req.getParameter("Pass");
        System.out.println(Pass);
        String Sex = req.getParameter("Sex");
        System.out.println(Sex);
        // 应该用数组接收
        String[] Habit = req.getParameterValues("Habit");
        for (String H : Habit) {
            System.out.println(H);
        }
        String Occupation = req.getParameter("Occupation");
        System.out.println(Occupation);

        // dao层处理

        // 请求响应

    }
}
