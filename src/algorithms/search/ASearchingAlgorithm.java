package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected Solution solution;
    protected HashMap<String,AState> states;
    protected AbstractQueue<AState> visited;

    abstract public Solution solve(ISearchable domain);

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return solution.getSolutionPath().size();
    }

    public Solution backTrackingToStartState(AState goalState){

        ArrayList<AState> solutionPath = new ArrayList<>();
        solutionPath.add(goalState);
        AState parentState = goalState.getCameFrom();
        while (parentState != null){
            solutionPath.add(parentState);
            parentState = parentState.getCameFrom();
        }
        Collections.reverse(solutionPath);
        solution =  new Solution(solutionPath);
        return solution;
    }
}
