package sudoku.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sudoku.game.models.FileSudokuProvider;
import sudoku.game.models.Sudoku;
import sudoku.util.FileHelper;

public class SudokuGameTest {

    private SudokuGame game;
    private FileSudokuProvider provider;

    @BeforeEach
    public void setUp() throws IOException {
        // Set up a wordle game where the word is always "array"
        this.provider = new FileSudokuProvider();
        this.game = new SudokuGame(provider);
    }

    @Test
    @DisplayName("Test newGame creates a new Sudoku")
    public void testNewGame() throws IOException {
        Sudoku oldSudoku = game.getCurrentSudoku();
        game.newGame();
        Sudoku newSudoku = game.getCurrentSudoku();
        assertNotEquals(oldSudoku, newSudoku, "newGame should create a new Sudoku");
    }

    @Test
    @DisplayName("Test isFinished returns true when board is finished")
    public void testReadFromFile() throws IOException {
        String expected = FileHelper.readLines("/sudoku/test_sudoku_given.txt", true).get(0);
        assertEquals(expected,
                "040100050/107003960/520008000/000000017/000906800/803050620/090060543/600080700/250097100",
                "Sudoku should be read from file correctly");
    }

    @Test
    @DisplayName("Test isFinished returns true when board is finished")
    public void testIsFinished() throws IOException {
        String textFormatFinished = FileHelper.readLines("/sudoku/test_sudoku_completed.txt", true).get(0);
        Sudoku finishedSudoku = provider.parseSudoku(textFormatFinished);
        String textFormatUnfinished = FileHelper.readLines("/sudoku/test_sudoku_given.txt", true).get(0);
        Sudoku unfinishedSudoku = provider.parseSudoku(textFormatUnfinished);
        game.setCurrentSudoku(finishedSudoku);
        assertTrue(game.isFinished(), "isFinished should return true when board is finished");
        game.setCurrentSudoku(unfinishedSudoku);
        assertFalse(game.isFinished(), "isFinished should return false when board is unfinished");
    }

    @Test
    @DisplayName("Test saveGame saves the current game to a file")
    public void testSaveGame() throws IOException {
        String textFormat = FileHelper.readLines("/sudoku/test_sudoku_given.txt", true).get(0);
        Sudoku sudoku = provider.parseSudoku(textFormat);
        game.setCurrentSudoku(sudoku);
        File file = new File("test_save_game.txt");
        game.saveGame(file);
        String expected = FileHelper.readLines("test_save_game.txt", false).get(0);
        assertEquals(expected, textFormat, "saveGame should save the current game to a file");
        file.delete();
    }

    @Test
    @DisplayName("Test loadGame loads a game from a file")
    public void testLoadGame() throws IOException {
        String textFormat = FileHelper.readLines("/sudoku/test_sudoku_given.txt", true).get(0);
        File file = new File("test_load_game.txt");
        FileHelper.writeLines("test_load_game.txt", List.of(textFormat));
        game.loadGame(file);
        Sudoku loadedSudoku = game.getCurrentSudoku();
        Sudoku expectedSudoku = provider.parseSudoku(textFormat);
        assertEquals(expectedSudoku.toString(), loadedSudoku.toString(), "loadGame should load a game from a file");
        file.delete();
    }
}
