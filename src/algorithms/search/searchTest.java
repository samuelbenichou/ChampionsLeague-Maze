package algorithms.search;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;

public class searchTest {

    public static void main(String[] args) {
        /*IMazeGenerator mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(5,5);
        maze.setStart(new Position(0,0));
        maze.setGoal(new Position(4,4));
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //ArrayList<AState> list = searchableMaze.getAllPossibleStates(new MazeState(new Position(2,2),null));
        solveProblem(searchableMaze,new BreadthFirstSearch());*/

        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(5,5);
        //maze.setStart(new Position(0,0));
        //maze.setGoal(new Position(4,4));
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //ArrayList<AState> list = searchableMaze.getAllPossibleStates(new MazeState(new Position(2,2),null));
        //solveProblem(searchableMaze,new BreadthFirstSearch());
        //solveProblem(searchableMaze,new BestFirstSearch());
        solveProblem(searchableMaze,new DepthFirstSearch());
    }

    private static void solveProblem(ISearchable domain , ISearchingAlgorithm searcher){
        Solution solution = searcher.solve(domain);
        System.out.println(searcher.getNumberOfNodesEvaluated());
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }

    }
}
