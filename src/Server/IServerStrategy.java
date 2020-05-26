package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException;
}
