package algorithms.mazeGenerators;

public interface IMazeGenerator {

    /**
     * Generate a maze with row*col dimensions
     * @param row
     * @param col
     * @return a new maze
     */
    Maze generate(int row,int col);

    /**
     * Measure time building maze
     * @param row
     * @param col
     * @return time
     */
    long measureAlgorithmTimeMillis(int row,int col);

}
