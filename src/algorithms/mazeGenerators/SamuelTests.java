package algorithms.mazeGenerators;

public class SamuelTests {

    public static void main(String[] args) {
        //EmptyMazeGenerator a = new EmptyMazeGenerator();
        //Maze empty = a.generate(10,10);
        //empty.print();
        //System.out.println(a.measureAlgorithmTimeMillis(10,10));

        SimpleMazeGenerator b = new SimpleMazeGenerator();
        Maze simple = b.generate(15,15);
        System.out.println("  ");
        simple.print();
        //System.out.println(b.measureAlgorithmTimeMillis(1000,1000));
        /////////////////////////// /////////
        
        //MyMazeGenerator myMaze = new MyMazeGenerator();
        //Maze maze = myMaze.generate(3,3);
        //maze.print();
        //System.out.println(myMaze.measureAlgorithmTimeMillis(1000,1000));
        

        //test for change lior
        
    }

}
