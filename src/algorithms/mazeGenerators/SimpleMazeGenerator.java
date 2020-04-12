package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    
    @Override
    public Maze generate(int row, int cols) {
        if (row < 2 || cols < 2)
            return null;
        Maze simpleMaze = new Maze(row, cols);
        simpleMaze = new Maze(row, cols);
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
        //simpleMaze.print();

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

    /*
    @Override

    public Maze createPath(Maze emptyM){
        Position startP= emptyM.getStartPosition();
        Position exitP = emptyM.getGoalPosition();

        Position curentPos=new Position(startP.getRowIndex(), startP.getColumnIndex());
        Random rnd = new Random();
        int nextStep = 1+rnd.nextInt(4);
        String myPath="";

        int rowS=startP.getRowIndex();
        int colS=startP.getColumnIndex();
        int rowEX=exitP.getRowIndex();
        int colEX=exitP.getColumnIndex();
        int rowC = curentPos.getRowIndex();
        int colC = curentPos.getColumnIndex();

        while( (rowC!=rowEX) || (colC!= colEX) ){
                if( !(rowC==rowS && colC==colS) )
                    emptyM.setCell(rowC, colC, 2);    
                ////////
                System.out.println("");
                emptyM.print();
                //////////
                 //rowC = curentPos.getRowIndex();
                //colC = curentPos.getColumnIndex();
                nextStep = 1+rnd.nextInt(4);

                while( !checkIfLegalStep(emptyM, rowC, colC, nextStep) ){
                    nextStep = 1+rnd.nextInt(4);
                }
                myPath = myPath+(nextStep+"");
                if(nextStep==1){ //1=right
                    colC=colC+1;
                }
                else if(nextStep==2){ //2=left
                    colC=colC-1;
                }
                else if(nextStep==3){ //3=up    
                    rowC=rowC-1;
                }
                else{ //4=down  
                    rowC=rowC+1;
                }
        }//while
        emptyM.setCell(curentPos.getRowIndex(), curentPos.getColumnIndex(), 2);
        emptyM.setMazePath(myPath);
        //////////////////////////////////////////////////
        System.out.println("");
        emptyM.print();
        //////////////////////////////////////////
        return emptyM;
    }


    public boolean checkIfLegalStep(Maze emptyM, int rowCn, int colCn, int nextStep){
        boolean isLeg=true;
        int rowC=rowCn;
        int colC=colCn;

        int rowS=emptyM.getStartPosition().getRowIndex();
        int colS= emptyM.getStartPosition().getColumnIndex();
        int colNum = emptyM.getColNum();
        if(nextStep==1){ //1=right
            colC=colC+1;
            if( colC>=colNum|| (colC==colS && rowC==rowS) ){
                isLeg=false;
            }
        }
        else if(nextStep==2){ //2=left
            colC=colC-1;
            if(colC<0 || (colC==colS && rowC==rowS) ) {
                isLeg=false;
            }
        }
        else if(nextStep==3){ //3=up      
            rowC=rowC-1;
            if(rowC<0 ||  (colC==colS && rowC==rowS) ){
                isLeg=false;
            }
        }
        else{ //4=down  
            rowC=rowC+1;
            if( rowC>=emptyM.getRowNum() || (colC==colS && rowC==rowS) ){
                isLeg=false;
            }
        }
        return isLeg;
    }//func
    */

}//class

