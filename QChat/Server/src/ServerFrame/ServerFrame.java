package ServerFrame;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerFrame extends JFrame {

    boolean serverFlag = true;
    ServerSocket Sersocket;
    List<Socket> socketList = new ArrayList<>();
    JTextArea msgArea;
    public void createFrame(){
        setSize(500, 455);
        setTitle("多人聊天室服务器端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jp = new JPanel();

        msgArea = new JTextArea(25,48);
        msgArea.setEditable(true);


        add(jp);
        jp.add(msgArea);

        setVisible(true);
    }

    public void StartServerSocket(){
        try {
            Sersocket = new ServerSocket(8888);
            System.out.println("服务器端等待连接");
            //循环监听
            while (serverFlag){
                if (Sersocket.isClosed()){
                    serverFlag = false;
                    break;
                }
                Socket socket = Sersocket.accept();
                socketList.add(socket);
                System.out.println("客户端-"+socket.getInetAddress()+"-连接成功"+"目前共有"+socketList.size()+"位在线用户");

                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建一个内部类,开启一个线程,接收消息
    class ServerThread extends Thread{
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        boolean clientFlag = true;

        public ServerThread(Socket socket) {
            this.socket = socket;
            try {
                this.in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (clientFlag){
                if (socket.isClosed()){
                    clientFlag = false;
                    break;
                }
                try {
                    String msg = in.readUTF();
                    msgArea.append(msg);

                    //从服务器端向客户端发送消息
                    if (socketList.size() > 0){
                        Iterator<Socket> it = socketList.iterator();
                        while (it.hasNext()){
                            Socket s = it.next();
                            if (s.isClosed()){
                                it.remove();
                                continue;
                            }
                            //客户端Socket如果不关闭,就发送消息
                            out = new DataOutputStream(socket.getOutputStream());
                            out.writeUTF(msg);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ServerFrame().createFrame();
    }
}
