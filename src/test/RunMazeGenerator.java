package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Samuel on 3/(22)/2020.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
//        testMazeGenerator(new EmptyMazeGenerator());
//        testMazeGenerator(new SimpleMazeGenerator());
//        testMazeGenerator(new MyMazeGenerator());
        //-----------------------------------------------------------------------

        /*EmptyMazeGenerator a = new EmptyMazeGenerator();
        Maze empty = a.generate(10,10);
        //empty.print();
        //System.out.println(a.measureAlgorithmTimeMillis(10,10));
        SimpleMazeGenerator b = new SimpleMazeGenerator();
        Maze simple = b.generate(5,5);
        simple.print();
        //System.out.println(b.measureAlgorithmTimeMillis(1000,1000));
        /////////////////////////// /////////
        */


        System.out.println("--------------------------lior");
//        int num=127;
//        byte nb = (byte)num;
//        System.out.println(nb);

        MyMazeGenerator myMaze = new MyMazeGenerator();
        Maze maze = myMaze.generate(5,5);
        maze.print();
        System.out.println();
        //System.out.println(myMaze.measureAlgorithmTimeMillis(1000,1000));
        //System.out.println();
        maze.printMazeInfo();
        System.out.println();

        byte[] mazeinfo = maze.toByteArray();
        Maze maze2 = new Maze(mazeinfo);
        maze2.print();

        System.out.println();
        OutputStream outStr = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
        MyCompressorOutputStream mazeComp = new MyCompressorOutputStream();
        ArrayList<Byte> mazeBytesInfo = new ArrayList<>();
        try{
            mazeBytesInfo= mazeComp.writeForTest(mazeinfo);
        }
        catch (Exception e){
            System.out.println("exp");
        }

        MyDecompressorInputStream mazeDEcomp = new MyDecompressorInputStream();
        byte[] mazeinfoByte = mazeDEcomp.read(mazeBytesInfo);

        System.out.println();
        Maze maze3 = new Maze(mazeinfoByte);
        maze3.print();

    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(1000/*rows*/, 1000/*columns*/);

        // prints the maze
        //maze.print();

        // get the maze entrance
        Position startPosition = maze.getStartPosition();

        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}