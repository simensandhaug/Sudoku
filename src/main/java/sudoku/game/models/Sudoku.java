package sudoku.game.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Sudoku puzzle.
 */
public class Sudoku {

    private Cell[][] board;
    private List<CellRegion> regions;

    /**
     * Constructs a Sudoku puzzle with the given board.
     *
     * @param board The 9x9 grid of cells representing the Sudoku puzzle.
     */
    public Sudoku(Cell[][] board) {
        this.board = board;
        this.regions = generateRegions(board);
    }

    /**
     * Returns the 9x9 grid of cells representing the Sudoku puzzle.
     *
     * @return The Sudoku board.
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Generates and returns a list of CellRegion objects for all rows, columns, and
     * boxes.
     *
     * @return A list of CellRegion objects.
     */
    private List<CellRegion> generateRegions(Cell[][] board) {
        List<CellRegion> regions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            regions.add(new CellRegion(Arrays.asList(board[i])));
            regions.add(new CellRegion(getColumnCells(i, board)));
            if (i % 3 == 0) {
                for (int j = 0; j < 9; j += 3) {
                    regions.add(new CellRegion(getBoxCells(i, j, board)));
                }
            }
        }
        return regions;
    }

    /**
     * Returns a list of cells from the specified column.
     *
     * @param column The index of the column (0-8).
     * @param board  The Sudoku board.
     * @return A list of cells from the specified column.
     */
    private List<Cell> getColumnCells(int column, Cell[][] board) {
        return Arrays.stream(board)
                .map(row -> row[column])
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of cells from the specified 3x3 box.
     *
     * @param row    The starting row index (0-6, multiples of 3).
     * @param column The starting column index (0-6, multiples of 3).
     * @param board  The Sudoku board.
     * @return A list of cells from the specified 3x3 box.
     */
    private List<Cell> getBoxCells(int row, int column, Cell[][] board) {
        List<Cell> cells = new ArrayList<>();
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                cells.add(board[i][j]);
            }
        }
        return cells;
    }

    /**
     * Checks if the Sudoku puzzle is finished, i.e., all regions are complete and
     * valid.
     *
     * @return true if the puzzle is finished, false otherwise.
     */
    public boolean isFinished() {
        return regions.stream().allMatch(CellRegion::isFinished);
    }

    /**
     * Checks if the Sudoku puzzle is valid, i.e., all regions have no duplicate
     * numbers.
     *
     * @return true if the puzzle is valid, false otherwise.
     */
    public boolean isValid() {
        return regions.stream().allMatch(CellRegion::isValid);
    }

    /**
     * Checks if the cell at the given coordinates is valid in its row, column, and
     * box.
     * 
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return true if the cell is valid, false otherwise.
     */
    public boolean cellValid(int x, int y, int value) {
        Cell[][] boardCopy = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCopy[i][j] = new Cell(i, j, board[i][j].getValue());
            }
        }

        boardCopy[x][y].setValue(value);

        List<CellRegion> regions = generateRegions(boardCopy);

        return regions.stream().allMatch(CellRegion::isValid);
    }

    /**
     * Returns a string representation of the Sudoku puzzle.
     *
     * @return A string representation of the Sudoku puzzle.
     */
    @Override
    public String toString() {
        // Format the board to a string like this:
        // 200105003/054000710/010203080/602807304/000000000/105309806/020701060/081000240/700402001

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(board[i][j]);
            }
            if (i < 8) {
                builder.append("/");
            }
        }
        return builder.toString();
    }

    public void printRegions() {
        for (CellRegion region : regions) {
            System.out.println(region);
        }
    }

}