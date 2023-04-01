package sudoku.game.models;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import sudoku.game.interfaces.SudokuProvider;
import sudoku.util.FileHelper;

/**
 * A {@link SudokuProvider} that reads Sudoku boards from a file
 */
public class FileSudokuProvider implements SudokuProvider {

    private final Random random;
    private final List<String> sudokus;

    /**
     * Creates a new {@link FileSudokuProvider} that reads Sudoku boards from the
     * default file.
     * 
     * @throws IOException if the file cannot be read
     */
    public FileSudokuProvider() throws IOException {
        this("/sudoku/sudokus.txt", true);
    }

    /**
     * Creates a new {@link FileSudokuProvider} that reads Sudoku boards from the
     * given file.
     * 
     * @param path     The path to the file
     * @param resource Whether the file is a resource or not
     * @throws IOException if the file cannot be read
     */
    public FileSudokuProvider(String path, boolean resource) throws IOException {
        random = new Random();
        sudokus = FileHelper.readLines(path, resource);
    }

    /**
     * @return the sudokus read from the file
     */
    public List<String> getSudokus() {
        return sudokus;
    }

    @Override
    public Sudoku getNextSudoku() {
        return parseSudoku(sudokus.get(random.nextInt(sudokus.size())));
    }

    public Sudoku parseSudoku(String textForm) {
        Cell[][] board = new Cell[9][9];
        int i = 0;
        for (String row : textForm.split("/")) {
            int j = 0;
            for (String cell : row.split("")) {
                int value = Integer.parseInt(cell);
                board[i][j] = new Cell(j, i, value);
                j++;
            }
            i++;
        }
        return new Sudoku(board);
    }
}
