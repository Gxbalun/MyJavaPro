package com.qf.webpro.dao;

import com.qf.webpro.bean.Course;
import com.qf.webpro.bean.Student;
import com.qf.webpro.util.JDBCUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CourseDao {

    //添加课程
    public void insertCourse(String id, String name) throws SQLException {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO course(id,name)VALUES(?,?)";
            Object[] obj = {id,name};
            JDBCUtil.executeUpdateSql(sql,obj,connection);
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection,null);
        }
    }

    //删除课程
    public void deleteCourseById(String[] ids) throws SQLException {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置为手动提交事务
            for (String id : ids){
                Object[] obj = {id};
                String sql1 = "DELETE FROM stu_cour WHERE course_id = ?";
                JDBCUtil.executeUpdateSql(sql1,obj,connection);
                String sql2 = "delete from stu_cour where course_id =?";
                JDBCUtil.executeUpdateSql(sql2,obj,connection);
                String sql3 = "delete from course where id =?";
                JDBCUtil.executeUpdateSql(sql3,obj,connection);
            }
            //手动提交事务
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection,null);
        }
    }


    public Course findCourseById(String id) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        Course course = new Course();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name FROM course WHERE id = ?";
            Object[] obj = {id};
            resultSet = JDBCUtil.executeQuerySql(sql,obj,connection);
            while (resultSet.next()){
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        return course;
    }

    public void updateCourse(String id, String name) throws SQLException {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置为手动提交事务
            String sql = "UPDATE course SET name=? WHERE id=?";
            Object[] obj = {name,id};
            JDBCUtil.executeUpdateSql(sql,obj,connection);
            //手动提交事务
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection,null);
        }
    }
}
