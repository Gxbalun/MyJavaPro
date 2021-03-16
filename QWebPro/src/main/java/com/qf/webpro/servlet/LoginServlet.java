package com.qf.webpro.servlet;

import com.qf.webpro.Test.log4jTest;
import com.qf.webpro.bean.Admin;
import com.qf.webpro.bean.LoginTimes;
import com.qf.webpro.dao.LoginDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/*
    登陆处理Servlet
 */
@WebServlet(name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(log4jTest.class);

    /*
        安全退出
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        HttpSession session = req.getSession();
        session.invalidate();
        if (mark.equals("logintime")){
            findlogintime(req,resp);
        }else if (mark.equals("logout")){
            resp.sendRedirect("login.jsp");
        }
    }

    /*
        登录
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接受请求数据
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            logger.debug("account="+account+",password="+password);
            if(account.length()==0){
                req.setAttribute("msg","账号不能为空！");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }else if(password.length() == 0){
                req.setAttribute("msg","密码不能为空！");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }/*else if(!(account.matches("[1][35789]\\d{9}") && password.matches("[A-z,0-9]{8,15}"))){
                req.setAttribute("msg","账号或密码格式错误！");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }*/else{
                //调用Dao层
                LoginDao loginDao = new LoginDao();
                Admin admin = loginDao.checklogin(account,password);
                //响应
                if (admin != null){
                    //记住账号密码
                    String rem = req.getParameter("remember");
                    logger.debug("rem="+rem);
                    if (rem != null) {
                        Cookie ac = new Cookie("zh", account);
                        Cookie pw = new Cookie("mm", password);
                        ac.setMaxAge(3600 * 24);
                        pw.setMaxAge(3600 * 24);
                        resp.addCookie(ac);
                        resp.addCookie(pw);
                    }
                    //保存管理员登录日志
                    //将管理员信息存入session中
                    String ip = req.getRemoteAddr();
                    HttpSession session = req.getSession();
                    session.setAttribute("admin",admin);
                    Admin ad = (Admin) session.getAttribute("admin");
                    loginDao.addadminlog(ad.getId(),ip);
                    //跳转到成功页面
                    resp.sendRedirect("success.jsp");
                }else{
                    req.setAttribute("msg","账号或密码错误");
                    req.getRequestDispatcher("login.jsp").forward(req,resp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.debug(e.getMessage());
            resp.sendRedirect("500.jsp");
        }
    }

    private void findlogintime(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LoginDao loginDao = new LoginDao();
        List<LoginTimes> loginTimesList = null;
        try {
            loginTimesList = loginDao.findLogintime();
            req.setAttribute("loginTimesList",loginTimesList);
            req.getRequestDispatcher("loginTime.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

}
