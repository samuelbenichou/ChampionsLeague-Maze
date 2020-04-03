package algorithms.search;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    //protected LinkedTransferQueue<AState> visited;
    //protected Queue<AState> visited;

    public BreadthFirstSearch() {
        visited = new LinkedTransferQueue<>();
        states = new HashMap<>();
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null) return null;

        //visited = new LinkedTransferQueue<>();
        //states = new HashMap<>();
        AState goalState = BFS(domain);
        //System.out.println("before back tracking");
        return goalState != null ? backTrackingToStartState(goalState) : null ;

    }

    private AState BFS(ISearchable domain){
        visited.add(domain.getStartState());
        states.put(domain.getStartState().toString(),domain.getStartState());

        while (!visited.isEmpty()) {

            AState currentState = visited.poll();

            if (currentState.equals(domain.getGoalState()))
                return currentState;
            // Iterator through the possibles states of current state.
            for (AState neighbour : domain.getAllPossibleStates(currentState)) {
                if (!states.containsKey(neighbour.toString())){
                //if (!(visited.contains(neighbour))){
                    states.put(neighbour.toString(),neighbour);
                    visited.add(neighbour);
                }
            }
        }
        return null;
    }

}
