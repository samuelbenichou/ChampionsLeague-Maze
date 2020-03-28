package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        if(row < 2 || col < 2)
            return null;
        return new Maze(row,col);
    }

}
