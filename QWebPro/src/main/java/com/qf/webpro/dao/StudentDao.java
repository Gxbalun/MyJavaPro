package com.qf.webpro.dao;

import com.qf.webpro.bean.Course;
import com.qf.webpro.bean.Grade;
import com.qf.webpro.bean.Student;
import com.qf.webpro.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    学生管理数据访问层
 */
public class StudentDao {

    /*
        查询所有的学生信息
     */
    public List<Student> findStudentList() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT\n" +
                    "\tstu.snum,\n" +
                    "\tstu.NAME stu_name,\n" +
                    "\tstu.sex,\n" +
                    "\tstu.birthday,\n" +
                    "\tstu.phone_num,\n" +
                    "\tstu.address,\n" +
                    "\tgrade.NAME grade_name,\n" +
                    "\tGROUP_CONCAT( c.NAME ) course_name,\n" +
                    "\tad.account,\n" +
                    "\tstu.Operating_time \n" +
                    "FROM\n" +
                    "\tstudent stu\n" +
                    "\tINNER JOIN grade ON stu.grade_id = grade.id\n" +
                    "\tINNER JOIN stu_cour sc ON sc.stu_num = stu.snum\n" +
                    "\tINNER JOIN course c ON sc.course_id = c.id\n" +
                    "\tINNER JOIN admin ad ON ad.id = stu.admin_id\n" +
                    "GROUP BY\n" +
                    "\tstu.snum";
            resultSet = JDBCUtil.executeQuerySql(sql,null,connection);
            while (resultSet.next()){
                Student student = new Student();
                student.setNum(resultSet.getInt("snum"));
                student.setName(resultSet.getString("stu_name"));
                student.setSex(resultSet.getString("sex"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setPhone_number(resultSet.getInt("phone_num"));
                student.setAddress(resultSet.getString("address"));
                student.setGradeName(resultSet.getString("grade_name"));
                student.setCourseName(resultSet.getString("course_name"));
                student.setAdmin(resultSet.getString("account"));
                student.setOperTime(resultSet.getTimestamp("Operating_time"));
                list.add(student);
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        return list;
    }

    public List<Grade> findGradeList() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Grade> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM grade";
            resultSet = JDBCUtil.executeQuerySql(sql,null,connection);
            while (resultSet.next()){
                Grade grade = new Grade();
                grade.setId(resultSet.getInt("id"));
                grade.setName(resultSet.getString("name"));
                list.add(grade);
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        return list;
    }

    public List<Course> findCourseList() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Course> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM course";
            resultSet = JDBCUtil.executeQuerySql(sql,null,connection);
            while (resultSet.next()){
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                list.add(course);
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        return list;
    }

    public void insertStudent(String name, String sex, String birthday, String phone, String address, String gradeId, String[] course, int adminId) throws SQLException {
        Connection connection = null;
        //数据库事务:就是一次对数据库操作过程的管理
        //当提交事务后,才是最终告诉数据库要执行sql语句
        //jdbc提供的executeUpdate() 执行后会自动的提交事务
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置为手动提交事务
            String sql = "INSERT INTO student(name,sex,birthday,phone_num,address,grade_id,admin_id,Operating_time)" +
                    "VALUES(?,?,?,?,?,?,?,?)";
            Object[] obj1 = {name,sex,birthday,phone,address,gradeId,adminId,new Date()};
            //将学生信息存入到学生表中，并获取保存的学生的主键
            int studentId = JDBCUtil.executeUpdateSqlReturnKey(sql,obj1,connection);
            //将学生选课关系，存储到选课表
            sql = "INSERT INTO stu_cour(stu_num,course_id)VALUES(?,?)";
            for (String cid : course){
                Object[] obj2 = {studentId,cid};
                JDBCUtil.executeUpdateSql(sql,obj2,connection);
            }
            //手动提交事务
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection, null);
        }
    }

    public void deleteStudentByNum(String[] nums) throws SQLException {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置为手动提交事务
            for (String num : nums){
                String sql1 = "delete from stu_cour where stu_num =?";
                Object[] obj = {num};
                JDBCUtil.executeUpdateSql(sql1,obj,connection);
                String sql2 = "delete from student where snum =?";
                JDBCUtil.executeUpdateSql(sql2,obj,connection);
            }
            //手动提交事务
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection,null);
        }
    }

    public Student findStudentByNum(String num) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        Student student = new Student();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT\n" +
                    "\tstu.snum,\n" +
                    "\tstu.NAME,\n" +
                    "\tstu.sex,\n" +
                    "\tstu.birthday,\n" +
                    "\tstu.phone_num,\n" +
                    "\tstu.address,\n" +
                    "\tstu.grade_id,\n" +
                    "\tGROUP_CONCAT( sc.course_id ) ck_courseid \n" +
                    "FROM\n" +
                    "\tstudent stu\n" +
                    "\tLEFT JOIN stu_cour sc ON stu.snum = sc.stu_num \n" +
                    "WHERE\n" +
                    "\tstu.snum = ? \n" +
                    "GROUP BY\n" +
                    "\tstu.snum";
            Object[] obj = {num};
            resultSet = JDBCUtil.executeQuerySql(sql,obj,connection);
            while (resultSet.next()){
                student.setNum(resultSet.getInt("snum"));
                student.setName(resultSet.getString("name"));
                student.setSex(resultSet.getString("sex"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setPhone_number(resultSet.getInt("phone_num"));
                student.setAddress(resultSet.getString("address"));
                student.setGradeId(resultSet.getInt("grade_id"));
                student.setCkCourseId(resultSet.getString("ck_courseid"));
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        return student;
    }

    //修改学生信息
    public void updateStudent(String num,String name, String sex, String birthday, String phone, String address, String gradeId, String[] course, int adminId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置为手动提交事务
            //1.将学生信息存入到学生表
            String sql1 = "UPDATE student SET NAME =?,sex =?,birthday =?,phone_num =?,address =?," +
                    "grade_id =?,admin_id =?,Operating_time =? WHERE snum =?";
            Object[] obj1 = {name,sex,birthday,phone,address,gradeId,adminId,new Date(),num};
            JDBCUtil.executeUpdateSql(sql1,obj1,connection);
            //2.删除原来的学生课程关系
            String sql2 = "DELETE FROM stu_cour WHERE stu_num=?";
            Object[] obj2 = {num};
            JDBCUtil.executeUpdateSql(sql2,obj2,connection);
            //3.重新保存学生选课关系
            String sql3 = "INSERT INTO stu_cour(stu_num,course_id)VALUES(?,?)";
            for (String cid : course){
                Object[] obj3 = {num,cid};
                JDBCUtil.executeUpdateSql(sql3,obj3,connection);
            }
            //手动提交事务
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection,null);
        }
    }
}
