package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    
    @Override
    public Maze generate(int row, int cols) {
        if (row < 2 || cols < 2)
            return null;
        Maze simpleMaze = new Maze(row, cols);
        Random low = new Random();
        Random high = new Random();
        int rowIndex, colIndex;
        for (int i = 0; i < (row * cols) / 2; i++) {
            rowIndex = low.nextInt(row);
            colIndex = high.nextInt(cols);
            if (simpleMaze.getStartPosition().equals(new Position(rowIndex, colIndex)) || simpleMaze.getGoalPosition().equals(new Position(rowIndex, colIndex)))
                continue;
            simpleMaze.setCell(rowIndex, colIndex, 1);
        }
        
        simpleMaze= createPath(simpleMaze);

        return simpleMaze;
    }
 
    public Maze createPath(Maze simpleMaze){

        int rowS=simpleMaze.getStartPosition().getRowIndex();
        int colS=simpleMaze.getStartPosition().getColumnIndex();
        int rowEX=simpleMaze.getGoalPosition().getRowIndex();
        int colEX=simpleMaze.getGoalPosition().getColumnIndex();

        int currPosCol= 0;
        int currPosRow= 0;
        int colNUM= simpleMaze.getColumns();
        int rowNUM= simpleMaze.getRows();
        //go for the sides
        if(colEX>colS ){
            currPosCol=colS+1; 
            while(currPosCol< (colEX+1) && currPosRow<colNUM){
                simpleMaze.setCell(rowS, currPosCol, 0);
                currPosCol++;
            }
            currPosCol--;
        }
        else if(colEX<colS){
            currPosCol=colS-1; 
            while(currPosCol> (colEX-1) && currPosRow>=0){
                simpleMaze.setCell(rowS, currPosCol, 0);
                currPosCol--;
            }
            currPosCol++;
        }
        if(currPosCol<0){
            currPosCol=0;
        }
        if(currPosCol>=colNUM){
            currPosCol=colNUM-1;
        }
        //go up or down
        if(rowEX>rowS){
            currPosRow=rowS+1; 
            while(currPosRow< (rowEX+1) && currPosRow<rowNUM){
                simpleMaze.setCell(currPosRow, currPosCol, 0);
                currPosRow++;
            }
        }
        else if(rowEX<rowS){
            currPosRow=rowS-1; 
            while(currPosRow> (rowEX-1) && currPosRow>=0){
                simpleMaze.setCell(currPosRow, currPosCol, 0);
                currPosRow--;
            }
        }
        return simpleMaze;
    }
}//class

