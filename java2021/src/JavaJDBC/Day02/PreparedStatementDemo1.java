package JavaJDBC.Day02;

import java.sql.*;

public class PreparedStatementDemo1 {
    /*
        JDBC搭建步骤:
        1.导入mysql数据库驱动包，里面就是具体的连接实现
        2.加载数据库驱动
        3.建立与数据库的连接
        4.向数据库发送sql语句
        5.接受查询结果
        6.关闭与数据库的连接通道
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//利用反射机制加载Driver类
        //建立连接
        String url = "jdbc:mysql://127.0.0.1:3306/java2021?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url,"root","092417");
        //发送sql语句
        String name = "小花";
        String sex = "女";
        String phone = "110120";
        int gradeId = 2;
        String date = "2021-1-20 18:02:34";
        int score = 98;
        /*//首先将sql语句预先编译到PreparedStatement的对象中，？是占位符，表示此处需要传值，并没有向数据库发送
        PreparedStatement ps = connection.prepareStatement("insert into student(name,sex,phone,grade_id,reg_time,score)values(?,?,?,?,?,?)");
        //然后通过指定的方法向sql语句中的占位符传值
        ps.setString(1,name);
        ps.setString(2,sex);
        ps.setString(3,phone);
        ps.setInt(4,gradeId);
            //ps.setString(5,date);
        ps.setDate(5,new Date(new java.util.Date().getTime()));
            //ps.setObject(5,new Date());
        ps.setInt(6,score);
        //最后向数据库大宋sql
        ps.executeUpdate();*/

        PreparedStatement ps = connection.prepareStatement("delete from student where name = ?");
        ps.setString(1,name);
        int res = ps.executeUpdate();
        ps.close();
        //关闭与数据库的连接通道
        connection.close();
    }
}
