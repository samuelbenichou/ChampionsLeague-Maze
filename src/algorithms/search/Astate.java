package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;


/**
 * An abstract class representing a state in a searchable problem.
 */
public abstract class AState implements Serializable {

    private int cost;
    private AState cameFrom;
    private final long serialVersionUID;

    public AState(int weight, AState parent) {
        this.cost = weight;
        this.cameFrom = parent;
        this.serialVersionUID =0;
    }

    public AState(AState parent) {
        this.cost = 0;
        this.cameFrom = parent;
        this.serialVersionUID =0;
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
