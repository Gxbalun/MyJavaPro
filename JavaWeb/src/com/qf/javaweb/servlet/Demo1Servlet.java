package com.qf.javaweb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo1Servlet extends HttpServlet {
    /*
        Servlet生命周期
        servlet对象在 客户端第一次请求时 创建
        servlet对象在 服务器关闭时 销毁
    */


    /*
        无参构造方法
        在第一个客户端访问Servlet时调用，只调用一次，只创建了一个Servlet对象，servlet对象始终只有一个，单例(单个实例)
    */
    public Demo1Servlet() {
        System.out.println("无参构造方法");
    }

    /*
        在servlet对象创建后，如果我们需要完成一些初始化操作，可以在init方法中编写代码
        如果我们的类中没有初始化操作，可以不重写，但是服务器依旧会调用，会调用父类中的init
    */
    /*@Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }*/

    /*
        提供服务
        当客户端每次向服务器发送请求，都会调用service方法，
        HttpServletRequest  请求中的数据都被服务器封装到了HttpServletResponse对象中
        HttpServletResponse 向客户端做出相应
    */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");
    }

    /*
        Servlet对象销毁时调用，完成一些最终的操作(数据备份，输出日志)
    */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
