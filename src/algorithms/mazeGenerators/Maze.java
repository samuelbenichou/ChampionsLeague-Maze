package algorithms.mazeGenerators;

import java.util.Random;

public class Maze {

    private int rows;
    private int columns;
    private int[][] maze;
    private Position start;
    private Position goal;

    // Default Constructor
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        maze = new int[rows][columns];
        setRandomPosition();
    }

    public Maze(int[][] array ,Position start, Position goal) {
        maze = array;
        this.start = start;
        this.goal = goal;
        this.rows = array.length;
        this.columns = array[0].length;
    }

    public void print(){
        for (int i = 0;i<rows;i++){
            for (int j = 0;j<columns;j++){
                if (start.equals(new Position(i,j)))
                    System.out.print("S");
                else if(goal.equals(new Position(i,j)))
                    System.out.print("E");
                else
                    System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public void randomPosition(){
        int rowIndex = (int) Math.round(Math.random());
        int colIndex = (int) (Math.random()*columns);
        this.start = new Position(rowIndex * (rows-1),colIndex);
        while ((rowIndex* (rows-1)) == start.getRowIndex() || colIndex == start.getColumnIndex()){
            rowIndex = (int) Math.round(Math.random());
            colIndex = (int) (Math.random() *columns);
        }
        this.goal = new Position(rowIndex*(rows-1),colIndex);
    }

    private void setRandomPosition(){
        Random r = new Random();
        int rowIndex = r.nextInt(rows);
        int colIndex;
        if (rowIndex == 0 || rowIndex == maze.length-1){
            colIndex = r.nextInt(maze[0].length);
        }else{
            colIndex = ((int) Math.round(Math.random())) * (maze[0].length-1);
        }
        start = new Position(rowIndex,colIndex);
        while (start.getRowIndex() == rowIndex || start.getColumnIndex() == colIndex){
            rowIndex = r.nextInt(maze.length);
            if (rowIndex == 0 || rowIndex == maze.length-1){
                colIndex = r.nextInt(maze[0].length);
            }else{
                colIndex = ((int) Math.round(Math.random())) * (maze[0].length-1);
            }
        }
        goal = new Position(rowIndex,colIndex);
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setCell(int rowIndex, int colIndex, int value){
        maze[rowIndex][colIndex] = value;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }
}
