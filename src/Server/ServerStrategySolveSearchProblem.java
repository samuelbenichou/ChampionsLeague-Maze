package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private String tempDirectoryPath = System.getProperty("/tmp"); //java.io.tmpdir
    private Lock m = new ReentrantLock(true);
    private static int numOfSavedMazes = 0;

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            Solution solution = null;
            //MyCompressorOutputStream compressor = new MyCompressorOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            ASearchingAlgorithm solveAlgorithm = Configurations.getSearchingAlgorithm();

            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File tmpdir = new File(tempDirectoryPath );
            File[] fList = tmpdir.listFiles();
            boolean newMaze = true;

            for (File file : fList) {
                if(file.getName().endsWith(".1")) {
                    FileInputStream inputMaze = new FileInputStream(file.getName());
                    ObjectInputStream objectIn = new ObjectInputStream(inputMaze);
                    SearchableMaze mazeInput = (SearchableMaze) objectIn.readObject();
                    if (mazeInput.equals(searchableMaze)) {
                        FileInputStream inputSolve = new FileInputStream(file.toString().substring(0, 2) + "2");
                        ObjectInputStream objectInSol = new ObjectInputStream(inputSolve);
                        solution = (Solution) objectInSol.readObject();
                        newMaze = false;
                        objectIn.close();
                        inputMaze.close();
                        break;
                    }
                    objectIn.close();
                    inputMaze.close();
                }
            }

            if(newMaze){
                solution = solveAlgorithm.solve(searchableMaze);
                String mazeSaves = "java.io.tmpdir/"+numOfSavedMazes+".1";
                File mazeSave = new File(mazeSaves);
                FileOutputStream fileOut = new FileOutputStream(mazeSave.getName());
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.flush();
                objectOut.writeObject(searchableMaze);
                objectOut.close();

                String solSaves = "java.io.tmpdir/"+numOfSavedMazes+".2";
                File solSave = new File(solSaves);
                FileOutputStream fileOutSol = new FileOutputStream(solSave.getName());
                ObjectOutputStream objectOutSol = new ObjectOutputStream(fileOutSol);
                objectOutSol.flush();
                objectOutSol.writeObject(solution);
                objectOutSol.close();

                numOfSavedMazes++;
            }

            toClient.writeObject(solution);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}