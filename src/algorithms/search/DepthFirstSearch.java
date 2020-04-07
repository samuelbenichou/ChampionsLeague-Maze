package algorithms.search;

import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    protected Stack<AState> stack;

    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null) return null;

        stack = new Stack<>();
        states = new HashMap<>();
        AState goalState = DFS(domain);
        //System.out.println("before back tracking");
        return goalState != null ? backTrackingToStartState(goalState) : null ;
    }

    private AState DFS(ISearchable domain){
        stack.push(domain.getStartState());
        states.put(domain.getStartState().toString() , domain.getStartState());

        while (!stack.isEmpty()){

            AState currentState = stack.pop();

            if (currentState.equals(domain.getGoalState()))
                return currentState;

            if (!states.containsKey(currentState.toString()))
                states.put(currentState.toString() , currentState);

            for (AState s : domain.getAllPossibleStates(currentState)) {
                if (!states.containsKey(s.toString())) {
                    //System.out.println(s.toString());
                    stack.push(s);
                }
            }
        }
        return null;
    }
}
