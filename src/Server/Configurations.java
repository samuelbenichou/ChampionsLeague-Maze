package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class Configurations {

    static Properties prop;

    private static final String SOLVER_ALGORITHM = "Solver Algorithm";
    private static final String MAZE_GENERATOR = "Maze Generator";

    public static AMazeGenerator getMazeGenerator() {
        String generator = prop.getProperty("MazeGenerator");//getConfiguration(MAZE_GENERATOR);
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
        String algorithm = prop.getProperty("SearchingAlgorithm");//getConfiguration(MAZE_GENERATOR);
        switch (algorithm){
            case "Best first search" :
                return new BreadthFirstSearch();

            case "Breadth first search" :
                return new BreadthFirstSearch();

            default:
                return new DepthFirstSearch();
        }
    }
}
