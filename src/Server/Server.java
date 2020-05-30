package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService executorService;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    public void stop() {
        stop = true;
    }

    private void runServer() {
        try {
            this.executorService = Executors.newFixedThreadPool(4);
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                    System.out.println("SocketTimeout - No clients pending! " + e.getMessage());
                }
            }
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleClient(Socket clientSocket) {
        try {
            System.out.println(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

