package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    private ArrayList<Position> frontierCells;

    /*
        We will use Prim Algorithm to generate the maze.
        it starts at random cell, and grows outward from that point.
    */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 2 || columns < 2)
            return null;
        int[][] maze = new int[rows][columns];

        // Start with a grid full of walls.
        mazeInitialisationWithWalls(maze);

        // Choose an arbitrary cell,mark it as the start Position.
        Position start = chooseArbitraryStartPosition(maze);

        // Create an empty set,Add the walls of the choosen cell to the wall list.
        frontierCells = new ArrayList<>();
        frontierCells.add(start);

        int count;
        Position current;

        //While there are walls in the list:
        while (frontierCells.size() != 0){

            // Select a cell at random, as long as it bridges the so-called “frontier” of the maze
            current = frontierCells.remove((int) (Math.random() * frontierCells.size()));
            count = 0;

            // Count how many neighbors have been already visited
            count = checkNeighborsCells(maze,current);

            if (count <= 1) { // Make the wall a passage
                maze[current.getRowIndex()][current.getColumnIndex()] = 0;

                // we’ll mark the neighbors of the formerly frontier cell, as “frontier” cells themselves and add them to the wall list.
                addValidAdjacentCells(maze, 0, current.getRowIndex(), current.getRowIndex()-1, current.getColumnIndex());
                addValidAdjacentCells(maze, 0, current.getColumnIndex(), current.getRowIndex(), current.getColumnIndex()-1);
                addValidAdjacentCells(maze, current.getRowIndex()+1, maze.length, current.getRowIndex()+1, current.getColumnIndex());
                addValidAdjacentCells(maze, current.getColumnIndex()+1, maze[0].length, current.getRowIndex(), current.getColumnIndex()+1);
            }
        }

        Position goal = chooseArbitraryGoalPosition(maze,start);
        Maze myMaze = new Maze(maze, start, goal);
        return myMaze;
    }

    /**
     * Add the neighboring walls of the cell to the wall list.
     * @param maze,pos,limit,row,col
     */
    private void addValidAdjacentCells(int[][] maze, int pos, int limit, int row, int col) {
        if (pos < limit && maze[row][col] == 1){
            Position tmp = new Position(row,col);
            frontierCells.add(tmp);
        }
    }

    /**
     * Check for each adjacents cell of current position if its already visited
     * @param maze
     * @param start
     * @return Number of valid Neighbors
     */
    private int checkNeighborsCells(int[][] maze,Position start){
        int numOfValidNeighbors = 0;
        if (start.getRowIndex()-1 >= 0 && maze[start.getRowIndex()-1][start.getColumnIndex()] == 0)
            numOfValidNeighbors++;
        if (start.getRowIndex()+1 < maze.length && maze[start.getRowIndex()+1][start.getColumnIndex()] == 0)
            numOfValidNeighbors++;
        if (start.getColumnIndex()-1 >= 0 && maze[start.getRowIndex()][start.getColumnIndex()-1] == 0)
            numOfValidNeighbors++;
        if (start.getColumnIndex()+1 < maze[0].length && maze[start.getRowIndex()][start.getColumnIndex()+1] == 0)
            numOfValidNeighbors++;
        return numOfValidNeighbors;
    }

    /**
     * pick a random valid cell,mark it as the Goal Position
     * @param maze
     * @param start
     * @return Goal Position
     */
    private Position chooseArbitraryGoalPosition(int [][] maze,Position start){
        Random r = new Random();
        int rowIndex = start.getRowIndex();
        int colIndex = start.getColumnIndex();
        while (start.getRowIndex() == rowIndex || start.getColumnIndex() == colIndex || maze[rowIndex][colIndex] == 1){
            rowIndex = r.nextInt(maze.length);
            if (rowIndex == 0 || rowIndex == maze.length-1){
                colIndex = r.nextInt(maze[0].length);
            }else{
                colIndex = ((int) Math.round(Math.random())) * (maze[0].length-1);
            }
        }
        maze[rowIndex][colIndex] = 0;
        Position goal = new Position(rowIndex,colIndex);
        return goal;
    }

    /**
     * Pick a cell,mark it as the start Position
     * @param maze
     * @return start Position
     */
    private Position chooseArbitraryStartPosition(int [][] maze){
        Random r = new Random();
        int rowIndex = r.nextInt(maze.length);
        int colIndex;
        if (rowIndex == 0 || rowIndex == maze.length-1){
            colIndex = r.nextInt(maze[0].length);
        }else{
            colIndex = ((int) Math.round(Math.random())) * (maze[0].length-1);
        }
        maze[rowIndex][colIndex] = 0;
        Position start = new Position(rowIndex,colIndex);
        return start;
    }

    /**
     * Initialize a grid full of walls.
     * @param maze
     */
    private void mazeInitialisationWithWalls(int[][] maze){
        for (int i = 0; i < maze.length ; i++){
            for (int j = 0; j < maze[i].length ; j++){
                maze[i][j] = 1;
            }
        }
    }

}//class
