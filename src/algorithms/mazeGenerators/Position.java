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

    @Override
    public String toString() {
        return "{" + rowIndex + "," + columnIndex + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        Position position = (Position) obj;
        return (position.rowIndex == this.rowIndex) && (position.columnIndex == this.columnIndex);
    }
}
