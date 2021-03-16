package chatclient.frame;

import chatclient.bean.user;
import chatclient.dao.LoginDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class LoginFrame extends JFrame {
    Socket socket;
    String account;
    String password;

    public void createFrame(){
        setSize(250,180);
        setTitle("登录");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel JPanelHead = new JPanel();
        JPanel JPanelCenter = new JPanel();
        JPanel JPanelBottem = new JPanel();

        JLabel JLabelHead = new JLabel("欢迎登陆");
        JLabel JLabelAccount = new JLabel(" 用户名:");
        JLabel JLabelPassword = new JLabel("     密码:");

        JTextField jtf = new JTextField(15);
        JTextField psw = new JPasswordField(15);

        JOptionPane jop = new JOptionPane();

        JButton jb1 = new JButton("注册");
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account = jtf.getText();
                password = psw.getText();
                int length1 = jtf.getText().length();
                if (length1 == 0) {
                    JOptionPane.showMessageDialog(null, "账号不能为空", "提示", JOptionPane.ERROR_MESSAGE);
                }
                int length2 = psw.getText().length();
                if (length2 == 0) {
                    JOptionPane.showMessageDialog(null, "密码不能为空", "提示", JOptionPane.ERROR_MESSAGE);
                }
                //连接数据库
                try{
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    String url = "jdbc:mysql://127.0.0.1:3306/qchat?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
                    Connection connection = DriverManager.getConnection(url,"root","092417");
                    PreparedStatement ps = connection.prepareStatement("insert into users(acount,password)value(?,?)");
                    ps.setString(1,account);
                    ps.setString(2,password);
                    int rs = ps.executeUpdate();
                    if (rs != 0){
                        JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.OK_OPTION);
                    }
                    if (connection != null){
                        connection.close();
                    }
                    if (ps != null){
                        ps.close();
                    }
                }catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null,"已存在该用户名", "警告",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton jb2 = new JButton("登录");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account = jtf.getText();
                password = psw.getText();
                int length1 = jtf.getText().length();
                if (length1 == 0){
                    JOptionPane.showMessageDialog(null,"账号不能为空","提示",JOptionPane.ERROR_MESSAGE);
                }
                int length2 = psw.getText().length();
                if (length2 == 0){
                    JOptionPane.showMessageDialog(null,"密码不能为空","提示",JOptionPane.ERROR_MESSAGE);
                }
                //连接数据库
                LoginDao loginDao = new LoginDao();
                try {
                    user us = loginDao.checklogin(account,password);
                    if (us != null){
                        //连接服务器，创建socket对象
                        try {
                            socket = new Socket("127.0.0.1",8888);
                            new ChatFrame(socket,account).createFrame();
                            dispose();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            JOptionPane.showMessageDialog(null,"无法连接服务器","警告",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"账号或密码输入错误", "警告",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanelHead.add(JLabelHead);
        JPanelCenter.add(JLabelAccount);
        JPanelCenter.add(jtf);
        JPanelCenter.add(JLabelPassword);
        JPanelCenter.add(psw);
        JPanelBottem.add(jb1);
        JPanelBottem.add(jb2);
        add(JPanelHead,BorderLayout.NORTH);
        add(JPanelCenter,BorderLayout.CENTER);
        add(JPanelBottem,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame().createFrame();
    }
}
