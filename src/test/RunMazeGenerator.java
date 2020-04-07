package test;

import algorithms.mazeGenerators.*;

/**
 * Created by Samuel on 3/(22)/2020.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
        /*EmptyMazeGenerator a = new EmptyMazeGenerator();
        Maze empty = a.generate(10,10);
        //empty.print();
        //System.out.println(a.measureAlgorithmTimeMillis(10,10));
        SimpleMazeGenerator b = new SimpleMazeGenerator();
        Maze simple = b.generate(5,5);
        simple.print();
        //System.out.println(b.measureAlgorithmTimeMillis(1000,1000));
        /////////////////////////// /////////
        //Lior baru
        MyMazeGenerator myMaze = new MyMazeGenerator();
        //Maze maze = myMaze.generate(3,3);
        //maze.print();
        //System.out.println(myMaze.measureAlgorithmTimeMillis(1000,1000));*/
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);

        // prints the maze
        maze.print();

        // get the maze entrance
        Position startPosition = maze.getStartPosition();

        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}