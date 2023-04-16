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
     * @param row    The row index of the cell (0 <= row <= 8).
     * @param column The column index of the cell (0 <= column <= 8).
     * @param value  The value of the cell (0 <= value <= 9).
     * @throws IllegalArgumentException if the row, column, or value is invalid.
     */
    public Cell(int row, int column, int value) throws IllegalArgumentException {
        if (row < 0 || row > 8)
            throw new IllegalArgumentException("Row index must be between 0 and 8 (inclusive).");
        if (column < 0 || column > 8)
            throw new IllegalArgumentException("Column index must be between 0 and 8 (inclusive).");
        if (value < 0 || value > 9)
            throw new IllegalArgumentException("Value must be between 0 and 9 (inclusive).");
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
    public void setValue(int value) throws IllegalArgumentException {
        if (value < 0 || value > 9)
            throw new IllegalArgumentException("Value must be between 0 and 9 (inclusive).");
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
