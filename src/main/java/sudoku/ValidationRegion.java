package sudoku;

import java.util.ArrayList;
import java.util.Collection;

public class ValidationRegion {
    private Collection<Cell> cells = new ArrayList<Cell>();

    public boolean validate() {
        return cells.stream().distinct().count() == cells.size();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public Collection<Cell> getCells() {
        return cells;
    }
}
