package chatclient.frame;



import chatclient.util.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatFrame extends JFrame {

    Socket socket;
    DataInputStream in;//数据输入字符流
    DataOutputStream out;//数据输出字符流
    String account;
    JTextArea msgArea;

    public ChatFrame(Socket socket,String account){
        this.socket = socket;
        this.account = account;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFrame() {
        setSize(500, 450);
        setTitle("聊天大厅-"+account);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel JPanelHead = new JPanel();//面板1
        JPanelHead.setSize(500,380);
        JPanelHead.setBackground(Color.LIGHT_GRAY);

        JPanel JPanelBottem = new JPanel();//面板2
        JPanelBottem.setSize(500,70);
        JPanelBottem.setBackground(Color.gray);

        msgArea = new JTextArea(23,42);//文字框1
        msgArea.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(msgArea);

        JTextArea msg = new JTextArea(1,30);//文字框2
        msg.setBackground(Color.white);

        JOptionPane jOptionPane = new JOptionPane();

        JButton JButtonSend = new JButton("发送");
        JButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = msg.getText();//获得输入的内容
                if (s.length() == 0){
                    JOptionPane.showMessageDialog(null,"消息为空","提示",JOptionPane.ERROR_MESSAGE);
                }
                try {
                    //向服务器端发送消息s
                    out.writeUTF(account+":"+ DateUtil.dateToString("yyy-MM-dd HH:mm:ss")+"\n"+"说:"+s+"\n");
                    out.flush();
                    msg.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        //聊天窗口关闭窗口事件监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "您确定要退出吗?","操作提示",JOptionPane.OK_CANCEL_OPTION);
                if(res==0){
                    dispose();
                    new LoginFrame().createFrame();
                    //关闭客户端聊天窗口时,关闭客户端Socket
                    try {
                        socket.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        //启动一个线程，监听服务器是否向客户端发送了消息
        new ClientThread().start();

        JPanelHead.add(msgArea);
        JPanelBottem.add(msg);
        JPanelBottem.add(JButtonSend);
        add(JPanelHead,BorderLayout.NORTH);
        add(JPanelBottem,BorderLayout.SOUTH);

        setVisible(true);
    }
    //在客户端监听服务器端向客户端发送的消息
    class ClientThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    String msg = in.readUTF();
                    msgArea.append(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
