package ClientFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

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
        JLabel JLabelAccount = new JLabel("     账号:");
        JLabel JLabelPassword = new JLabel("     密码:");

        JTextField jtf = new JTextField(15);
        JTextField psw = new JPasswordField(15);

        JOptionPane jop = new JOptionPane();

        JButton jb1 = new JButton("注册");
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
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                String url = "jdbc:mysql://127.0.0.1:3306/qchat?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
                try {
                    Connection connection = DriverManager.getConnection(url,"root","092417");
                    PreparedStatement ps = connection.prepareStatement("select acount,password from users where acount = ? and password = ?");
                    ps.setString(1,account);
                    ps.setString(2,password);
                    try{
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()){
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
                    }catch (Exception r){
                        r.printStackTrace();
                        JOptionPane.showMessageDialog(null,"账号或密码输入错误", "警告",JOptionPane.WARNING_MESSAGE);
                    }
                    ps.close();
                    connection.close();
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
