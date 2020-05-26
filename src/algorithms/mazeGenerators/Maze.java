package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    //-----------------------------------------------------------------------------------------------------part B

    public Maze(byte[] mazeinfo){
        int pos=0;
        int num=0;
        int x=0;
        int y=0;
        for(int i=0; i<6; i++){
            while( mazeinfo[pos]!=-1 ){
                num = num + mazeinfo[pos];
                pos++;
            }
            pos++;
            if(i%2==0){ x=num; }
            else{ y=num; }

            if( i==0 ){
                rows = num;
            }
            else if( i==1 ){
                columns = num;
            }
            else if( i==3 ){
                start = new Position( x, y);
            }
            else if( i==5 ){
                goal = new Position( x , y);
            }
            num=0;
        }

        maze = new int[rows][columns];
        int currentSell =pos-1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                currentSell++;
                maze[i][j] = mazeinfo[currentSell];
            }
        }
    }

    public void printMazeInfo(){
        byte[] mazeinfo = toByteArray();
        for( int i=0; i< mazeinfo.length; i++){
            if( mazeinfo[i]==-1 ){
                System.out.print( mazeinfo[i]+"  " );
            }
            else{
                System.out.print( mazeinfo[i]+" " );
            }
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////
public byte[] toByteArray() {
    ArrayList<Byte> mazeBytesInfo = new ArrayList<>();
    mazeBytesInfo = conIntToByteArray(mazeBytesInfo, rows);
    mazeBytesInfo = conIntToByteArray(mazeBytesInfo, columns);
    mazeBytesInfo = conIntToByteArray(mazeBytesInfo, start.getRowIndex() );
    mazeBytesInfo = conIntToByteArray(mazeBytesInfo, start.getColumnIndex() );
    mazeBytesInfo = conIntToByteArray(mazeBytesInfo, goal.getRowIndex() );
    mazeBytesInfo = conIntToByteArray(mazeBytesInfo, goal.getColumnIndex());

    for(int i=0; i<rows; i++){
        for(int j=0; j<columns; j++){
            mazeBytesInfo.add( (byte)(maze[i][j]) );
        }
    }
    byte[] byteArray = new byte[mazeBytesInfo.size()+1];//+1 for the -1 symbol the end
    int i=0;
    for( i=0; i<mazeBytesInfo.size() ; i++){
        byteArray[i]= mazeBytesInfo.get(i);
    }
    byteArray[i]= (byte)-1;
    return byteArray;

}

    private ArrayList<Byte> conIntToByteArray( ArrayList<Byte> mazeBytesInfo, int num) {
        if(num > 127){
            int i=0;
            while( num>127){
                mazeBytesInfo.add( (byte)127 );
                num = num -127;
            }
            mazeBytesInfo.add( (byte)num );
            mazeBytesInfo.add( (byte)-1 );
        }
        else{
            mazeBytesInfo.add( (byte)num );
            mazeBytesInfo.add( (byte)-1 );
        }

        return mazeBytesInfo;
    }

}//class
