package algorithms.mazeGenerators;

import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class Maze implements Serializable{

    private int rows;
    private int columns;
    private int[][] maze;
    private Position start;
    private Position goal;
    private int indexByteArray = 0; //A global index for moving


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

    private int byteArrayToInt(byte[] byteArray) {
        int count = 0;
        count += (byteArray[indexByteArray])+(Byte.MAX_VALUE+1);
        indexByteArray++;
        while(byteArray[indexByteArray] != (byte) 127) {
            count += (byteArray[indexByteArray])+(Byte.MAX_VALUE+1);
            indexByteArray++;
        }
        indexByteArray++;
        return count;
    }

    public byte[] toByteArray() {
        ArrayList<Byte> dynamicBytes = new ArrayList<>();
        insertToByteArray(dynamicBytes, maze.length);
        insertToByteArray(dynamicBytes, maze[0].length);
        insertToByteArray(dynamicBytes, start.getRowIndex());
        insertToByteArray(dynamicBytes, start.getColumnIndex());
        insertToByteArray(dynamicBytes, goal.getRowIndex());
        insertToByteArray(dynamicBytes, goal.getColumnIndex());

        stream(maze).forEach(ints -> range(0, maze.length).mapToObj(j -> toUnsignedByte(ints[j])).forEachOrdered(dynamicBytes::add));
        byte[] byteArray = new byte[dynamicBytes.size()];
        range(0, byteArray.length).forEach(i -> byteArray[i] = dynamicBytes.get(i));

        return byteArray;
    }

    private void insertToByteArray(ArrayList<Byte> dynamicByte, int insert) {
        if (insert / 254 != 0) {
            int i = 0;
            while(i < insert / 254) {
                dynamicByte.add(toUnsignedByte(254));
                i++;
            }
        } else
            dynamicByte.add(toUnsignedByte(insert % 254)); // remainder
        dynamicByte.add((byte) 127); //delimiter
    }

    private byte toUnsignedByte(int from) {
        return (byte) (from-(Byte.MAX_VALUE+1));
    }
}
