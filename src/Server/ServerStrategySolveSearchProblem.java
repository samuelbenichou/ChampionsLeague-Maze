package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private static int numOfSavedMazes = 0;

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            Solution solution = null;
            boolean newMaze = true;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            ASearchingAlgorithm res = new BreadthFirstSearch();

            String path = System.getProperty("java.io.tmpdir");
            File tmpdir = new File(path );
            File[] fileList = tmpdir.listFiles();

            for (File file : fileList) {
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
                solution = res.solve(searchableMaze);
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