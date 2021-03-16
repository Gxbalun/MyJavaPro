package com.qf.javaweb.dao;

import com.qf.javaweb.bean.User;

import java.util.Date;
import java.sql.*;

public class LoginDao {

    public int clickLogin(String account, String pass, String clientIP) throws ClassNotFoundException, SQLException {
        // 导入mysql数据库驱动包
        // 加载数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = null;
        PreparedStatement psQuery = null;
        PreparedStatement psInsert = null;
        ResultSet res = null;
        try {
            // 与数据库建立连接
            // "jdbc:mysql://127.0.0.1:3306/chat_db?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC"
            String url = "jdbc:mysql://127.0.0.1:3306/web?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, "root", "092417");
            // 向数据库发送sql语句
            psQuery = connection.prepareStatement("select * from user where account = ? and pass= ?");
            psQuery.setString(1, account);
            psQuery.setString(2, pass);
            res = psQuery.executeQuery();
            if (res.next()) {
                psInsert = connection.prepareStatement("INSERT INTO LOGS (account ,ip , login_time) VALUES (?,?,?)");
                psInsert.setString(1, account);
                psInsert.setString(2, clientIP);
                psInsert.setObject(3, new Date());
                int result = psInsert.executeUpdate();
                System.out.println(result);
                return 1;
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (psQuery != null) {
                psQuery.close();
            }
            if (res != null) {
                res.close();
            }
            if (psInsert != null) {
                psInsert.close();
            }
        }
        return 0;
    }
}
