package JavaJDBC.Day02;

import java.sql.*;
import java.util.ArrayList;

public class ResultDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//利用反射机制加载Driver类
        //建立连接    192.168.0.137
        String url = "jdbc:mysql://127.0.0.1:3306/qchat?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url,"root","092417");
        //发送sql语句
        String account = "川宝";
        int password = 123456;

        PreparedStatement ps = connection.prepareStatement("select acount,password from users/* where acount = ?*/");
        //ps.setString(1,account);

        //executeQuery()方法会将查询结果封装在ResultSet    可以是多条
        ResultSet rs = ps.executeQuery();
        //从ResultSet中将数据提取出来
        ArrayList list = new ArrayList<String>();
        while (rs.next()){  //rs.next()判断ResultSet中还有没有数据
            users us = new users();
            //rs.getInt(1);
            us.setAccount(rs.getString("acount"));
            us.setPassword(rs.getInt("password"));
            list.add(us);
        }
        System.out.println(list);
        ps.close();
        //关闭与数据库的连接通道
        connection.close();
    }
}

