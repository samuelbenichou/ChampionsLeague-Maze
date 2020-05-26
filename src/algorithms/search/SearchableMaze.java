package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze maze;

    public SearchableMaze(AState startState, AState goalState, Maze maze) {
        this.maze = maze;
    }

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return new MazeState(maze.getStartPosition(),null);
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition(),null);
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState currentState) {
        if (currentState == null || !(currentState instanceof MazeState)) return null;

        MazeState state = (MazeState) currentState;
        ArrayList<AState> allPossibleStates = new ArrayList<>();
        int row = state.getCurrentPosition().getRowIndex();
        int col = state.getCurrentPosition().getColumnIndex();

        boolean left = isMoveValid(row,col-1);
        if (left){
            allPossibleStates.add(new MazeState(new Position(row ,col-1),currentState,currentState.getCost()+10));
        }
        boolean right = isMoveValid(row,col+1);
        if (right){
            allPossibleStates.add(new MazeState(new Position(row ,col+1),currentState,currentState.getCost()+10));
        }
        boolean up = isMoveValid(row-1,col);
        if (up){
            allPossibleStates.add(new MazeState(new Position(row-1 ,col),currentState,currentState.getCost()+10));
        }
        boolean down = isMoveValid(row+1,col);
        if (down){
            allPossibleStates.add(new MazeState(new Position(row+1 ,col),currentState,currentState.getCost()+10));
        }

        if ((left || up) && isMoveValid(row-1,col-1)){
            allPossibleStates.add(new MazeState(new Position(row-1 ,col-1),currentState,currentState.getCost()+15));
        }
        if ((right || up) && isMoveValid(row-1,col+1)){
            allPossibleStates.add(new MazeState(new Position(row-1 ,col+1),currentState,currentState.getCost()+15));
        }
        if ((left || down) && isMoveValid(row+1,col-1)){
            allPossibleStates.add(new MazeState(new Position(row+1 ,col-1),currentState,currentState.getCost()+15));
        }
        if ((right || down) && isMoveValid(row+1,col+1)){
            allPossibleStates.add(new MazeState(new Position(row+1 ,col+1),currentState,currentState.getCost()+15));
        }
        return allPossibleStates;
    }

    private boolean isMoveValid(int row, int col){
        try{
            return (maze.getMaze()[row][col] == 0)? true : false;
        }catch (Exception e){
            return false;
        }
    }

}