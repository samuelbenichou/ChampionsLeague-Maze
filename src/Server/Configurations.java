package Server;


import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class Configurations {

    static Properties prop = new Properties();

    private static final String FILE_NAME = "resources/config.properties";
    public static final String THREAD_POOL_SIZE = "ThreadPool Size";
    private static final String SOLVER_ALGORITHM = "Solver Algorithm";
    private static final String MAZE_GENERATOR = "Maze Generator";


    private static OutputStream getConfigurationsFile(){
        try {
            OutputStream output = new FileOutputStream(FILE_NAME);
            //OutputStream output = new FileOutputStream(System.getProperty("user.dir")+ "resources/config.properties");
            return output;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties loadConfigurationsFile(){
        try {

            FileInputStream input = new FileInputStream(FILE_NAME);
            Properties prop = new Properties();
            prop.load(input);
            input.close();
            return prop;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void setConfiguration(String conName , String conValue){
        try {

            Properties prop = loadConfigurationsFile();
            prop.setProperty(conName , conValue);
            OutputStream out = getConfigurationsFile();
            prop.store(out , null);
            out.close();
        } catch (IOException e){
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    private static String getConfiguration(String conName){
        Properties prop = loadConfigurationsFile();
        return prop.getProperty(conName);
    }

    public static void setDefaultConfigurations(){

        OutputStream out = getConfigurationsFile();
        Properties prop = new Properties();

        prop.setProperty(THREAD_POOL_SIZE , "5");
        prop.setProperty(SOLVER_ALGORITHM , "Best First Search");
        prop.setProperty(MAZE_GENERATOR , "MyMaze");


        try {
            out.flush();
            prop.store(out , null);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setThreadPoolSize(int num){
        if (num < 0)
            num = 5;

        setConfiguration(THREAD_POOL_SIZE , "" + num);
    }

    public static void setSolveAlgorithm(String algorithm){

        if (!algorithm.equals("Best first search") && !algorithm.equals("Breadth first search") &&
                !algorithm.equals("Depth first search"))
            algorithm = "Best first search";

        setConfiguration(SOLVER_ALGORITHM , algorithm);
    }

    public static void setMazeGenerator(String generator){

        if ( !generator.equals("MyMaze") && !generator.equals("SimpleMaze") && !generator.equals("EmptyMaze"))
            generator = "MyMaze";

        setConfiguration(MAZE_GENERATOR , generator);
    }

    public static int getNumOfThreadPool(){

        return Integer.valueOf(getConfiguration(THREAD_POOL_SIZE));
    }

    public static ASearchingAlgorithm getSearchingAlgorithm(){

        String algorithm = getConfiguration(SOLVER_ALGORITHM);

        if (algorithm.equals("Best first search"))
            return new BestFirstSearch();

        else if (algorithm.equals("Breadth first search"))
            return new BreadthFirstSearch();

        else
            return new DepthFirstSearch();
    }

    public static AMazeGenerator getMazeGenerator(){

        String generator = getConfiguration(MAZE_GENERATOR);

        if ( generator.equals("MyMaze") )
            return new MyMazeGenerator();

        else if (generator.equals("SimpleMaze"))
            return new SimpleMazeGenerator();

        else
            return new EmptyMazeGenerator();
    }


    /*public static AMazeGenerator getMazeGenerator() {
        String generator = prop.getProperty("MazeGenerator");
        switch (generator){
            case "MyMaze" :
                return new MyMazeGenerator();

            case "SimpleMaze" :
                return new SimpleMazeGenerator();

            default:
                return new EmptyMazeGenerator();
        }
    }

    public static ASearchingAlgorithm getSearchingAlgorithm(){
        String algorithm = prop.getProperty("SearchingAlgorithm");
        switch (algorithm){
            case "Best first search" :
                return new BreadthFirstSearch();

            case "Breadth first search" :
                return new BreadthFirstSearch();

            default:
                return new DepthFirstSearch();
        }
    }

    public static int getNumOfThreadPool(){
        String sum = prop.getProperty("NumberOfThreads");
        displayCharValues(sum);
        //int total = Integer.parseInt(sum);
        int total = 0;
        return total;
    }

    public static String displayCharValues(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append((int) c).append(",");
        }
        return sb.toString();}
        */

}