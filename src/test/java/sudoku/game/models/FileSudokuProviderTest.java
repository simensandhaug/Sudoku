package sudoku.game.models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileSudokuProviderTest {

    private FileSudokuProvider provider;

    @BeforeEach
    public void setUp() throws IOException {
        provider = new FileSudokuProvider("/sudoku/test_sudokus.txt", true);
    }

    @Test
    @DisplayName("Test parseSudoku")
    public void testParseSudoku() throws IOException {
        String expected = "003020600/900305001/001806400/008102900/700000008/006708200/002609500/800203009/005010300";

        Sudoku sudoku = provider.parseSudoku(expected);

        assertEquals(expected, sudoku.toString(), "Sudoku should be parsed correctly");
    }

    @Test
    @DisplayName("Test getSudokus")
    public void testGetSudokus() throws IOException {
        List<String> sudokuStrings = provider.getSudokus();
        assertEquals(3, sudokuStrings.size(), "Sudokus should be read from file correctly");
        assertEquals("003020600/900305001/001806400/008102900/700000008/006708200/002609500/800203009/005010300",
                sudokuStrings.get(0), "Sudokus should be read from file correctly");
    }

}
