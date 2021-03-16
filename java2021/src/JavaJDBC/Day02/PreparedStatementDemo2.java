package JavaJDBC.Day02;

import java.sql.*;

public class PreparedStatementDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//利用反射机制加载Driver类
        //建立连接    192.168.0.137
        String url = "jdbc:mysql://127.0.0.1:3306/qchat?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url,"root","092417");
        //发送sql语句
        String account = "1 or 1=1";//恶意攻击 sql注入
        int password = 123456;
        /*Statement st = connection.createStatement();
        int res = st.executeUpdate("delete from users where acount = "+account);
        System.out.println(res);
        st.close()*/;

        //PreparedStatement可以防止sql注入
        PreparedStatement ps = connection.prepareStatement("delete from users where acount = ?");
        ps.setString(1,account);
        int res = ps.executeUpdate();
        System.out.println(res);
        ps.close();

        //关闭与数据库的连接通道
        connection.close();
    }
}

