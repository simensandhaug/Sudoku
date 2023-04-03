package sudoku.game.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A group of 9 cells that form a row, column, or box in a Sudoku puzzle.
 */
public class CellRegion {

    private List<Cell> cells;

    /**
     * Constructs a new CellRegion object with the specified list of cells.
     * The list must contain exactly 9 cells, or an IllegalArgumentException is
     * thrown.
     *
     * @param cells The list of cells that belong to this region.
     * @throws IllegalArgumentException if the list does not contain exactly 9
     *                                  cells.
     */
    public CellRegion(List<Cell> cells) throws IllegalArgumentException {
        if (cells.size() != 9) {
            throw new IllegalArgumentException("A CellRegion must contain exactly 9 cells.");
        }
        this.cells = cells;
    }

    /**
     * Checks whether all cells in this region contain valid values.
     * A value is considered valid if it is unique within the region and is between
     * 1 and 9 (inclusive).
     *
     * @return true if all cells contain valid values, false otherwise.
     */
    public boolean isValid() {
        Set<Integer> uniqueValues = new HashSet<>();
        return cells.stream()
                .map(Cell::getValue)
                .allMatch(value -> value == 0 || uniqueValues.add(value));
    }

    /**
     * Checks whether all cells in this region contain valid and non-zero values.
     *
     * @return true if all cells contain valid and non-zero values, false otherwise.
     */
    public boolean isFinished() {
        return isValid() && cells.stream().noneMatch(cell -> cell.getValue() == 0);
    }

    /**
     * Gets the list of cells that belong to this region.
     *
     * @return The list of cells that belong to this region.
     */
    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return cells.toString();
    }
}
