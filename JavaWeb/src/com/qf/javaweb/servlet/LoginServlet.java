package com.qf.javaweb.servlet;

import com.qf.javaweb.dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

// 注解方式配置Servlet
@WebServlet(name = "login", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求数据的解码格式
        req.setCharacterEncoding("utf-8");
        // 接收请求数据
        String account = req.getParameter("account");
        String pass = req.getParameter("pass");

        // 接收客户端ip
        String clientIp = req.getRemoteAddr();

        // 调用dao层，账号，密码验证，登录成功后保存登录日志 (日志表：id, account, ip, login_time)
        LoginDao ld = new LoginDao();
        resp.setContentType("text/html;charset=utf-8");
        try {
            int res = ld.clickLogin(account, pass, clientIp);

            PrintWriter out = resp.getWriter();
            if (res == 1) {
                out.print("<h1>登陆成功!!!</h1>");
            } else {
                out.print("<h1>账号不存在，或账号，或密码错误!</h1>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // 设置响应数据的解码格式
        // resp.setContentType("text/html;charset=utf-8");

        /*
         * 响应：使用HttpServletResponse对象实现响应 本质上从服务器向客户端响应数据，使用的是IO流 404(服务器响应) login
         * success(开发人员响应)
         */
        // PrintWriter out = resp.getWriter();
        // out.write(1); out.print(1); print的底层还是使用write，只是可以有多个类型的参数
        // out.print("登陆成功!!!");
        /*
         * 响应内容：有些内容是程序响应的，有些内容是服务器响应的
         *
         * 响应头，响应行，响应体 响应头：状态码(200:成功 ； 404：访问的资源文件不存在，或路径出错 ； 500：服务器端代码异常)
         */

    }
}
