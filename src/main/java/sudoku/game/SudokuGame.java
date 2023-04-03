package sudoku.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sudoku.game.interfaces.SudokuProvider;
import sudoku.game.models.Sudoku;
import sudoku.util.FileHelper;

public class SudokuGame {

    private final SudokuProvider sudokuProvider;
    private Sudoku currentSudoku;

    /**
     * Creates a new sudoku game logic controller using the given
     * {@link SudokuProvider sudoku provider}
     * 
     * @param provider The {@link SudokuProvider sudoku provider} to use
     * 
     */
    public SudokuGame(SudokuProvider provider) {
        this.sudokuProvider = provider;
        this.newGame();
    }

    /**
     * @return the current {@link Sudoku sudoku board}
     */
    public Sudoku getCurrentSudoku() {
        return currentSudoku;
    }

    /**
     * Starts a new game
     */
    public void newGame() {
        this.currentSudoku = sudokuProvider.getNextSudoku();
    }

    /**
     * @return whether the current game is finished
     */
    public boolean isFinished() {
        return currentSudoku.isFinished();
    }

    /**
     * Saves the current game to the given file
     * 
     * @param path The path to the file
     * 
     * @throws IOException if the file cannot be written to
     */
    public void saveGame(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(currentSudoku.toString());
        FileHelper.writeLines(path, lines);
    }

    /**
     * Loads a game from the given file
     * 
     * @param path     The path to the file
     * @param resource Whether the file is a resource or not
     * 
     * @throws IOException if the file cannot be read
     */
    public void loadGame(String path) throws IOException {
        List<String> lines = FileHelper.readLines(path, true);
        currentSudoku = sudokuProvider.parseSudoku(lines.get(0));
    }

}
