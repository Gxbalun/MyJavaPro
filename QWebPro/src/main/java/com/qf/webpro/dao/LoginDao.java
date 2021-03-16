package com.qf.webpro.dao;

import com.qf.webpro.bean.Admin;
import com.qf.webpro.bean.LoginTimes;
import com.qf.webpro.util.JDBCUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    登录处理数据访问层
 */
public class LoginDao {

    public Admin checklogin(String account,String password) throws SQLException {
        Admin admin = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,account FROM admin where account = ? AND password = ?";
            Object[] obj = {account,password};
            resultSet = JDBCUtil.executeQuerySql(sql,obj,connection);
            while (resultSet.next()){
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setAccount(resultSet.getString("account"));
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        return admin;
    }

    public void addadminlog(int adminId,String ip) throws SQLException {
        Connection connection = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//设置为手动提交事务
            String sql = "INSERT INTO admin_logintime(admin_id,ip,logintime)" +
                    "VALUES(?,?,?)";
            Object[] obj = {adminId,ip,time};
            JDBCUtil.executeUpdateSql(sql,obj,connection);
            //手动提交事务
            connection.commit();
        }finally {
            JDBCUtil.closeconnection(connection,null);
        }
    }

    public List<LoginTimes> findLogintime() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        List<LoginTimes> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT\n" +
                    "\tadmin.account,\n" +
                    "\tal.ip,\n" +
                    "\tal.logintime \n" +
                    "FROM\n" +
                    "\tadmin_logintime al\n" +
                    "\tINNER JOIN admin ON al.admin_id = admin.id \n" +
                    "ORDER BY\n" +
                    "\tal.logintime DESC";
            resultSet = JDBCUtil.executeQuerySql(sql,null,connection);
            while (resultSet.next()){
                LoginTimes loginTimes = new LoginTimes();
                loginTimes.setAdmin_id(resultSet.getString("account"));
                loginTimes.setIp(resultSet.getString("ip"));
                loginTimes.setTime(resultSet.getString("logintime"));
                list.add(loginTimes);
            }
        }finally {
            JDBCUtil.closeconnection(connection,resultSet);
        }
        System.out.println(list);
        return list;
    }
}
