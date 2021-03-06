package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {

    private int rowIndex;
    private int columnIndex;

    public Position(int row, int col) {
        this.rowIndex = row;
        this.columnIndex = col;
    }

    public Position(Position other) {
        this.rowIndex = other.rowIndex;
        this.columnIndex = other.columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
    public void setColumnIndex(int newCol){
        this.columnIndex=newCol;
    }
    public void setRowIndex(int newRow){
        this.rowIndex= newRow;
    }

    @Override
    public String toString() {
        return "{" + rowIndex + "," + columnIndex + '}';
    }

    public boolean equals(Position other) {
        return (other.rowIndex == rowIndex && other.columnIndex == columnIndex)? true: false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
