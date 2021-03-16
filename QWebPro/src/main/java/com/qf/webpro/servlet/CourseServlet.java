package com.qf.webpro.servlet;

import com.qf.webpro.bean.Course;
import com.qf.webpro.dao.CourseDao;
import com.qf.webpro.dao.StudentDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "course",urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mark = req.getParameter("mark");
        switch (mark) {
            case "list" : findcourselist(req, resp);break;
            case "toAddCourse" : toAddCourse(req, resp);break;
            case "deleteCourse" : toDeleteCourse(req, resp);break;
            case "toUpdateCourse" : toUpdateCourse(req,resp);
        }
    }

    private void findcourselist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StudentDao studentDao = new StudentDao();
        List<Course> courseList = null;
        try {
            courseList = studentDao.findCourseList();
            req.setAttribute("coursrList",courseList);
            req.getRequestDispatcher("course/list.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    private void toAddCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("course/add.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    private void insertCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String reid = req.getParameter("reid");
            String name = req.getParameter("name");
            CourseDao courseDao = new CourseDao();
            if (reid == null){
                courseDao.insertCourse(reid,name);
            }else {
                courseDao.updateCourse(reid,name);
            }
            findcourselist(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    private void toDeleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CourseDao courseDao = new CourseDao();
        try {
            String id = req.getParameter("id");
            courseDao.deleteCourseById(id.split(","));
            findcourselist(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    private void toUpdateCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //查询年级，课程
        CourseDao courseDao = new CourseDao();
        try {
            String id = req.getParameter("id");
            Course course = courseDao.findCourseById(id);
            req.setAttribute("course",course);
            req.getRequestDispatcher("course/update.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("add")){
            insertCourse(req,resp);
        }
    }

}
