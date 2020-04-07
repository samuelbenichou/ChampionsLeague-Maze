package algorithms.mazeGenerators;

public class Position {

    private int rowIndex;
    private int columnIndex;


    public Position(int row, int col) {
        this.rowIndex = row;
        this.columnIndex = col;
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

}
