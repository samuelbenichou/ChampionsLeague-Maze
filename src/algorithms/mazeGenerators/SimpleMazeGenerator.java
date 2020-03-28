package algorithms.mazeGenerators;

import java.util.Random;


public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int cols) {
        if (row < 2 || cols < 2)
            return null;
        Maze simpleMaze = new Maze(row,cols);
        Random low = new Random();
        Random high = new Random();
        int rowIndex,colIndex;
        for (int i = 0 ;i<(row*cols)/2 ;i++){
            rowIndex = low.nextInt(row);
            colIndex = high.nextInt(cols);
            if (simpleMaze.getStartPosition().equals(new Position(rowIndex,colIndex)) || simpleMaze.getGoalPosition().equals(new Position(rowIndex,colIndex)))
                continue;
            simpleMaze.setCell(rowIndex,colIndex,1);
        }
        return simpleMaze;
    }

}
