package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {

    private Position currentPosition;

    public MazeState(int weight, AState parent ,Position currentPosition) {
        super(weight, parent);
        this.currentPosition = new Position(currentPosition);
    }

    public MazeState(Position currentPosition,AState parent,int weight) {
        super(weight,parent);
        this.currentPosition = currentPosition;
    }

    public MazeState(Position currentPosition,AState parent) {
        super(parent);
        this.currentPosition = currentPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MazeState state1 = (MazeState) obj;

        return currentPosition != null ? currentPosition.equals(state1.currentPosition) : state1.currentPosition == null;
    }

    @Override
    public int hashCode() {
        return currentPosition != null ? currentPosition.hashCode() : 0;
    }

    @Override
    public String toString() {
        return currentPosition.toString();
    }
}
