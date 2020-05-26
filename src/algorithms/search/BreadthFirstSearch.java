package algorithms.search;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        visited = new LinkedTransferQueue<>();
        states = new HashMap<>();
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null) return null;
        AState goalState = BFS(domain);
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
                    states.put(neighbour.toString(),neighbour);
                    visited.add(neighbour);
                }
            }
        }
        return null;
    }

}
