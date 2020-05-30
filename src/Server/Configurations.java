package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.Properties;

public class Configurations {

    static Properties prop = new Properties();

    private static final String fileName = "resources/config.properties";

    private static OutputStream getConfigurationsFile(){
        try {
            OutputStream output = new FileOutputStream(fileName);
            return output;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AMazeGenerator getMazeGenerator() {
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
        //displayCharValues(sum);
        int total = 0;
        return total;
    }

    public static String displayCharValues(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append((int) c).append(",");
        }
        return sb.toString();}

}