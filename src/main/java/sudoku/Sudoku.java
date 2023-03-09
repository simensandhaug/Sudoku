package sudoku;

public class Sudoku {
    private Grid grid;

    public Sudoku() {
        grid = new Grid();
    }

    public Cell[][] getGrid() {
        return grid.getGrid();
    }
}
