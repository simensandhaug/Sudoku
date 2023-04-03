package sudoku.game.models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SudokuTest {

    private Cell[][] board = new Cell[9][9];

    @Test
    @DisplayName("Invalid board size throws exception")
    public void testConstructor() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> new Sudoku(new Cell[10][10]), "Board must be 9x9");
    }

    @Test
    @DisplayName("Test generate regions returns 27 regions")
    public void testGenerateRegions() throws IOException {
        Sudoku sudoku = new Sudoku(board);
        assertEquals(27, sudoku.getRegions().size(), "There should be 27 regions in a Sudoku");
    }
}
