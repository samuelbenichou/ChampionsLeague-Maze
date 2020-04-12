package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;


/**
 * An abstract class representing a state in a searchable problem.
 */
public abstract class AState implements Serializable {

    private int cost;
    private AState cameFrom;

    public AState(int weight, AState parent) {
        this.cost = weight;
        this.cameFrom = parent;
    }

    public AState(AState parent) {
        this.cost = 0;
        this.cameFrom = parent;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public int getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

}
