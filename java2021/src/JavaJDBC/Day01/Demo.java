package JavaJDBC.Day01;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Demo {
    /*
        JDBC搭建步骤:
        1.导入mysql数据库驱动包，里面就是具体的连接实现
        2.加载数据库驱动
        3.建立与数据库的连接
        4.向数据库发送sql语句
        5.接受查询结果
        6.关闭与数据库的连接通道
     */
    public static void main(String[] args) {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//利用反射机制加载Driver类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //建立连接
        try {
            //?characterEncoding=utf-8" ?后都是参数，键=值&键=值
            String url = "jdbc:mysql://127.0.0.1:3306/java2021?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url,"root","092417");
            System.out.println(connection);
            //发送sql语句
            String name = "小明";
            String sex = "男";
            String phone = "1234567";
            int gradeId = 2;
            String date = "2021-1-20 18:02:34";
            int score = 88;
            //获得Statement,用于向数据库发送sql语句（静态的）
            Statement st = connection.createStatement();
            System.out.println(st);
            //execute(sql)  如果执行有返回结果则true,没有则false
            /*boolean b = st.execute("create table test(id int,name varchar(10))");
            System.out.println(b);*/
            int res = st.executeUpdate("insert into student(name,sex,phone,grade_id,score,reg_time)"+
                                    "values('"+name+"','"+sex+"','"+phone+"','"+gradeId+"','"+score+"','2021-1-20 18:02:34')");
            System.out.println(res);
            //关闭与数据库的连接通道
            st.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
