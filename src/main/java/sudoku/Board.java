package sudoku;

import java.util.ArrayList;
import java.util.Collection;

import sudoku.patterns.Pattern;

public class Board {
    Cell[][] cells = new Cell[9][9];
    Collection<ValidationRegion> validationRegions = new ArrayList<ValidationRegion>();
    Collection<Pattern> patterns = new ArrayList<Pattern>();

    public void addValidationRegion(ValidationRegion validationRegion) {
        validationRegions.add(validationRegion);
    }

    public void addPattern(Pattern pattern) {
        patterns.add(pattern);
    }

    public void solve() {
        patterns.forEach(pattern -> pattern.apply(this));
        // Brute Force
    }

    public boolean validate() {
        return validationRegions.stream().allMatch(ValidationRegion::validate);
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(int row, int column, Cell cell) {
        cells[row][column] = cell;
    }
}
