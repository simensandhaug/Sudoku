package sudoku;

public class Grid {
    private Cell[][] grid;

    public Grid() {
        grid = SudokuFactory.generateEmptyGrid();
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
