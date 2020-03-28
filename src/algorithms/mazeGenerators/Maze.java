package algorithms.mazeGenerators;

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
        randomPosition();
    }

    public Maze(int rows, int columns, Position start, Position goal) {
        this.rows = rows;
        this.columns = columns;
        this.start = start;
        this.goal = goal;
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


    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public void setCell(int rowIndex,int colIndex,int value){
        maze[rowIndex][colIndex] = value;
    }
}
