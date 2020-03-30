package algorithms.mazeGenerators;

public class SamuelTests {

    public static void main(String[] args) {
        EmptyMazeGenerator a = new EmptyMazeGenerator();
        Maze empty = a.generate(5,5);
        //empty.print();
        //System.out.println(a.measureAlgorithmTimeMillis(10,10));
        SimpleMazeGenerator b = new SimpleMazeGenerator();
        Maze simple = b.generate(10,10);
        simple.print();
        System.out.println(b.measureAlgorithmTimeMillis(1000,1000));
        /////////////////////////// /////////
        //Lior baru
    }

}
