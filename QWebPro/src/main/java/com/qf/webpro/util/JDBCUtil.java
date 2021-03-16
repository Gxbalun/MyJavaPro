package com.qf.webpro.util;

import java.sql.*;

public class JDBCUtil {

    public static String account = "root";
    public static String password = "092417";
    public static String url = "jdbc:mysql://127.0.0.1:3306/web_db?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";

    //驱动放在静态代码块中，只执行一次
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //建立数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,account,password);
    }

    /*
        执行增删改sql语句 传入sql，sql中的参数
     */
    public static void executeUpdateSql(String sql, Object[] obj,Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("set foreign_key_checks = 0");
        PreparedStatement ps = connection.prepareStatement(sql);
        if (obj != null){
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
        }
        //statement.executeUpdate("set foreign_key_checks = 1");
        ps.executeUpdate();
        ps.close();
    }
    /*
        执行新增，并返回主键
     */
    public static int executeUpdateSqlReturnKey(String sql, Object[] obj,Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        if (obj != null){
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
        }
        ps.executeUpdate();
        //获取刚刚保存的学生的主键
        ResultSet rs = ps.getGeneratedKeys();
        int key = 0;
        if (rs.next()){
            key = rs.getInt(1);
        }
        ps.close();
        return key;
    }
    /*
        执行查询sql语句   传入sql,sql中的参数，返回结果
     */
    public static ResultSet executeQuerySql(String sql,Object[] obj,Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("set foreign_key_checks = 0");
        PreparedStatement ps = connection.prepareStatement(sql);
        if (obj!=null){
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
        }
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    /*
        关闭数据库连接
     */
    public static void closeconnection(Connection connection,ResultSet rs) throws SQLException {
        if (connection != null){
            connection.close();
        }
        if (rs != null){
            rs.close();
        }
    }
}
