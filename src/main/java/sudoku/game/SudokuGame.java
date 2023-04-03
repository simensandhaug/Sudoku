package sudoku.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sudoku.game.interfaces.SudokuProvider;
import sudoku.game.models.Sudoku;
import sudoku.util.FileHelper;

/**
 * A class that controls the logic of a Sudoku game
 */
public class SudokuGame {

    private final SudokuProvider sudokuProvider;
    private Sudoku currentSudoku;

    /**
     * Creates a new Sudoku game logic controller using the given Sudoku provider
     * 
     * @param provider The Sudoku provider to use
     */
    public SudokuGame(SudokuProvider provider) {
        this.sudokuProvider = provider;
        this.newGame();
    }

    /**
     * Returns the current Sudoku board
     * 
     * @return The current Sudoku board
     */
    public Sudoku getCurrentSudoku() {
        return currentSudoku;
    }

    /**
     * Starts a new game by getting the next Sudoku board from the provider
     */
    public void newGame() {
        this.currentSudoku = sudokuProvider.getNextSudoku();
    }

    /**
     * Checks if the current game is finished
     * 
     * @return True if the current game is finished, false otherwise
     */
    public boolean isFinished() {
        return currentSudoku.isFinished();
    }

    /**
     * Saves the current game to a file
     * 
     * @param path The path to the file
     * @throws IOException if the file cannot be written to
     */
    public void saveGame(File file) throws IOException {
        String path = file.getAbsolutePath();
        List<String> lines = new ArrayList<>();
        lines.add(currentSudoku.toString());
        FileHelper.writeLines(path, lines);
    }

    /**
     * Loads a game from a file
     * 
     * @param path The path to the file
     * @throws IOException if the file cannot be read
     */
    public void loadGame(File file) throws IOException {
        String path = file.getAbsolutePath();
        List<String> lines = FileHelper.readLines(path, false);
        currentSudoku = sudokuProvider.parseSudoku(lines.get(0));
    }
}
