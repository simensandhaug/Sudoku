package sudoku;

public class SudokuFactory {
    public static Cell[][] generateEmptyGrid() {
        Cell[][] grid = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell(0, CellStatus.TO_GUESS);
            }
        }
        return grid;
    }

    public static Cell[][] generateFromDifficulty(Difficulty difficulty) {
        Cell[][] grid = generateEmptyGrid();
        switch (difficulty) {
            case EASY:
                generateEasy(grid);
                break;
            case MEDIUM:
                generateMedium(grid);
                break;
            case HARD:
                generateHard(grid);
                break;
        }
        return grid;
    }

    private static Cell[][] generateEasy(Cell[][] grid) {
        return grid;
    }

    private static Cell[][] generateMedium(Cell[][] grid) {
        return grid;
    }

    private static Cell[][] generateHard(Cell[][] grid) {
        return grid;
    }
}
