package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private String tempDirectoryPath = System.getProperty("/tmp"); //java.io.tmpdir
    private Lock m = new ReentrantLock(true);

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            Solution solution;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            //toClient.flush();

            Maze maze = (Maze) fromClient.readObject();
            String path = tempDirectoryPath+"maze"+maze.toString();
            m.lock();
            File f = new File(path);
            if (!f.exists()) { //we need to solve the problem
                m.unlock();
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                solution = Configurations.getSearchingAlgorithm().solve(searchableMaze);
                m.lock();
                final boolean newFile = f.createNewFile();
                FileOutputStream fout = new FileOutputStream(path);
                fout.flush();
                ObjectOutputStream oout = new ObjectOutputStream(fout);
                oout.flush();
                oout.writeObject(solution);
                oout.flush();
            } else { // the file exists, we don't need to solve again. only take what exists.
                FileInputStream fin = new FileInputStream(path);
                ObjectInputStream oin = new ObjectInputStream(fin);
                solution = (Solution) oin.readObject();
                fin.close();
                oin.close();
            }
            m.unlock();
            toClient.writeObject(solution);
            toClient.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFolderExist(String path){
        return Files.exists(Paths.get(path));
    }
}