package sudoku.game.models;

public class Sudoku {

    private Cell[][] board;

    public Sudoku(Cell[][] board) {
        this.board = board;
    }

    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Method to check if the board is filled correctly
     * 
     * @return true if the board is finished, false otherwise
     */
    public boolean isFinished() {
        // Check if the board is finished
        // A board is finished if all cells are filled and there are no conflicts

        // Check if all cells are filled
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].getValue() == 0) {
                    return false;
                }
            }
        }

        // Check if all rows are valid
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(i)) {
                return false;
            }
        }

        // Check if all columns are valid
        for (int i = 0; i < 9; i++) {
            if (!isValidColumn(i)) {
                return false;
            }
        }

        // Check if all 3x3 squares are valid
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValidSquare(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidRow(int row) {
        // Check if the given row is valid
        // A row is valid if it contains all numbers from 1 to 9 exactly once

        Cell[] cells = board[row];
        return isValidRegion(cells);
    }

    private boolean isValidColumn(int column) {
        // Check if the given column is valid
        // A column is valid if it contains all numbers from 1 to 9 exactly once

        Cell[] cells = new Cell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = board[i][column];
        }
        return isValidRegion(cells);
    }

    private boolean isValidSquare(int row, int column) {
        // Check if the given 3x3 square is valid
        // A 3x3 square is valid if it contains all numbers from 1 to 9 exactly once

        Cell[] cells = new Cell[9];
        int index = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                cells[index] = board[i][j];
                index++;
            }
        }
        return isValidRegion(cells);
    }

    /**
     * Checks if the given region is valid
     * 
     * @param cells The cells of the region
     * @return true if the region is valid, false otherwise
     */
    private boolean isValidRegion(Cell[] cells) {
        // Check if the given region is valid
        // A region is valid if it contains all cells from 1 to 9 exactly once

        // Check if all cells are present
        for (int i = 1; i <= 9; i++) {
            boolean found = false;
            for (int j = 0; j < 9; j++) {
                if (cells[j].getValue() == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        // Check if there are no duplicates
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (cells[i].getValue() == cells[j].getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

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
}
