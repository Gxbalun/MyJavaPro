package com.qf.webpro.servlet;

import com.qf.webpro.bean.*;
import com.qf.webpro.dao.LoginDao;
import com.qf.webpro.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
    学生管理
 */
@WebServlet(name = "student",urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    /*
        get请求处理
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mark = req.getParameter("mark");
        switch (mark) {
            case "list" : findstudentlist(req, resp);break;
            case "toAddStudent" : toAddStudent(req, resp);break;
            case "deleteStudent" : toDeleteStudent(req, resp);break;
            case "toUpdateStudent" : toUpdateStudent(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("insert")){
            insertStudent(req,resp);
        }
    }

    public void findstudentlist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //调用dao层查询所有的学生信息
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = null;
        try {
            studentList = studentDao.findStudentList();
            req.setAttribute("studentList",studentList);
            req.getRequestDispatcher("student/list.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //添加学生
    private void toAddStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //查询年级，课程
        StudentDao studentDao = new StudentDao();
        try {
            List<Grade> gradeList = studentDao.findGradeList();
            List<Course> courseList = studentDao.findCourseList();
            req.setAttribute("gradeList",gradeList);
            req.setAttribute("courseList",courseList);
            req.getRequestDispatcher("student/add.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //新增保存学生
    private void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String num = req.getParameter("num");
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String birthday = req.getParameter("birthday");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String gradeId = req.getParameter("gradeId");
            String[] course = req.getParameterValues("course");
            HttpSession session = req.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            StudentDao studentDao = new StudentDao();
            if (num == null){
                studentDao.insertStudent(name,sex,birthday,phone,address,gradeId,course,admin.getId());
            }else{
                studentDao.updateStudent(num,name,sex,birthday,phone,address,gradeId,course,admin.getId());
            }
            findstudentlist(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //删除学生
    private void toDeleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StudentDao studentDao = new StudentDao();
        try {
            String num = req.getParameter("num");
            studentDao.deleteStudentByNum(num.split(","));
            findstudentlist(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //修改学生数据
    private void toUpdateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //查询年级，课程
        StudentDao studentDao = new StudentDao();
        try {
            String num = req.getParameter("num");
            List<Grade> gradeList = studentDao.findGradeList();
            List<Course> courseList = studentDao.findCourseList();
            Student student = studentDao.findStudentByNum(num);
            req.setAttribute("student",student);
            req.setAttribute("gradeList",gradeList);
            req.setAttribute("courseList",courseList);
            req.getRequestDispatcher("student/update.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }
}
