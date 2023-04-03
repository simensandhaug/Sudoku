package sudoku.game.models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CellTest {

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() throws IOException {
        Cell cell = new Cell(1, 2, 3);
        assertEquals(1, cell.getRow(), "Row should be 1");
        assertEquals(2, cell.getColumn(), "Col should be 2");
        assertEquals(3, cell.getValue(), "Value should be 3");

        assertThrows(IllegalArgumentException.class, () -> {
            new Cell(9, 9, 10);
            new Cell(-1, -1, -1);
        }, "Row and Column must be between 0 and 8 and value must be between 0 and 9");
    }
}
