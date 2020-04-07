package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    AState getStartState();

    AState getGoalState();

    ArrayList<AState> getAllPossibleStates(AState s);

}
