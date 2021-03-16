package chatclient.dao;

import chatclient.bean.user;

import java.sql.*;


/*
    登录功能数据访问类
 */
public class LoginDao {
    public user checklogin(String account,String password) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        user us = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/qchat?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url,"root","092417");
            ps = connection.prepareStatement("select acount,password from users where acount = ? and password = ?");
            ps.setString(1,account);
            ps.setString(2,password);
            rs = ps.executeQuery();
            while (rs.next()){
                us = new user();
                us.setAcount(rs.getString("acount"));
                us.setPassword(rs.getString("password"));
            }
        }finally {
            if (connection != null){
                connection.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        }
        return us;
    }
}
