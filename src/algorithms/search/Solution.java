package algorithms.search;

import java.util.ArrayList;
import java.io.Serializable;

public class Solution implements Serializable {

    private ArrayList<AState> solutionPath;

    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }

    public ArrayList<AState> getSolutionPath(){
        return solutionPath;
    }

}
