package chatclient.frame;

public class ServerRun {
    public static void main(String[] args){
        ServerFrame serverFrame = new ServerFrame();
        serverFrame.createFrame();
        serverFrame.StartServerSocket();
    }
}
