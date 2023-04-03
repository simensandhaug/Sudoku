package sudoku.game.models;

/**
 * A single cell in a Sudoku puzzle.
 */
public class Cell {
    private final int row;
    private final int column;
    private int value;

    /**
     * Constructs a new Cell object with the specified row, column, and value.
     *
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     * @param value  The value of the cell.
     */
    public Cell(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    /**
     * Gets the row index of the cell.
     *
     * @return The row index of the cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of the cell.
     *
     * @return The column index of the cell.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the value of the cell.
     *
     * @return The value of the cell.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the cell.
     *
     * @param value The new value of the cell.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the value of the cell.
     *
     * @return A string representation of the value of the cell.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
