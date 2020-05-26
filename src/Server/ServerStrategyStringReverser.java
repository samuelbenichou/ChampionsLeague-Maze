package Server;

import java.io.*;

public class ServerStrategyStringReverser implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
        BufferedWriter toClient = new BufferedWriter(new PrintWriter(outToClient));
        String clientCommand = fromClient.readLine();;

        try {
            toClient.write(new StringBuilder(clientCommand).reverse().toString()+"\n");
            toClient.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
