package algorithms.mazeGenerators;

import java.lang.*;

public abstract class AMazeGenerator implements IMazeGenerator {

    abstract public Maze generate(int row, int col);

    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        if (row == 0 || col == 0)
            return 0;
        long before = System.currentTimeMillis();
        generate(row,col);
        long after = System.currentTimeMillis();
        return (after-before);
    }

}
