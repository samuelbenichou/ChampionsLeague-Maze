package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * Get the maze Parameters inside array and return a compress maze
     * @param inFromClient
     * @param outToClient
     * @throws IOException
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(outByteArray);
            int[] mazeSize = (int[])fromClient.readObject();
            AMazeGenerator mazeGenerator = Configurations.getMazeGenerator();
            //AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSize[0] , mazeSize[1]);
            compressor.write(maze.toByteArray());
            toClient.writeObject(outByteArray.toByteArray());
            toClient.flush();
            toClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
