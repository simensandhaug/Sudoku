package sudoku.game.models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CellRegionTest {

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() throws IOException {
        List<Cell> validAmountOfCells = new ArrayList<Cell>(9);
        for (int i = 0; i < 9; i++) {
            validAmountOfCells.add(new Cell(0, 0, 0));
        }
        System.out.println(validAmountOfCells.size());
        List<Cell> invalidAmountOfCells = new ArrayList<Cell>(10);
        for (int i = 0; i < 10; i++) {
            invalidAmountOfCells.add(new Cell(0, 0, 0));
        }
        CellRegion region = new CellRegion(validAmountOfCells);
        assertThrows(IllegalArgumentException.class, () -> {
            new CellRegion(invalidAmountOfCells);
        }, "CellRegion must have 9 cells");

    }

    @Test
    @DisplayName("Test isValid")
    public void testIsValid() throws IOException {

        // Test valid cells from 1-9
        List<Cell> validCells = new ArrayList<Cell>(9);
        for (int i = 0; i < 9; i++) {
            validCells.add(new Cell(0, 0, i + 1));
        }
        CellRegion region = new CellRegion(validCells);
        assertTrue(region.isValid(), "Region should be valid");

        // Test valid cells from 1-5 and 4 empty cells

        List<Cell> validCellsNotFull = new ArrayList<Cell>(9);
        for (int i = 0; i < 5; i++) {
            validCellsNotFull.add(new Cell(0, 0, i + 1)); // Add 5 cells from 1-5
        }
        for (int i = 0; i < 4; i++) {
            validCellsNotFull.add(new Cell(0, 0, 0)); // Add 4 empty cells
        }
        CellRegion region1 = new CellRegion(validCellsNotFull);
        assertTrue(region1.isValid(), "Region should be valid");

        // Test invalid cells

        List<Cell> invalidCells = new ArrayList<Cell>(9);
        for (int i = 0; i < 8; i++) {
            invalidCells.add(new Cell(0, 0, i + 1));
        }
        invalidCells.add(new Cell(0, 0, 1));
        CellRegion region2 = new CellRegion(invalidCells);
        assertFalse(region2.isValid(), "Region should not be valid");
    }

    @Test
    @DisplayName("Test isFinished")
    public void testIsFinished() throws IOException {
        List<Cell> validCells = new ArrayList<Cell>(9);
        for (int i = 0; i < 9; i++) {
            validCells.add(new Cell(0, 0, i + 1));
        }
        CellRegion region = new CellRegion(validCells);
        assertTrue(region.isFinished(), "Region should be finished");

        List<Cell> invalidCells = new ArrayList<Cell>(9);
        for (int i = 0; i < 8; i++) {
            invalidCells.add(new Cell(0, 0, i + 1));
        }
        invalidCells.add(new Cell(0, 0, 0));
        CellRegion region2 = new CellRegion(invalidCells);
        assertFalse(region2.isFinished(), "Region should not be finished");
        assertTrue(region.isFinished(), "Region should be finished");
    }
}
