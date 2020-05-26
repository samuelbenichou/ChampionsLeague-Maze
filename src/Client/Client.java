package Client;

import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.serverIP = IP;
        this.serverPort = port;
        this.clientStrategy = clientStrategy;
    }

    public void communicateWithServer(){
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(theServer.getInputStream(),theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}