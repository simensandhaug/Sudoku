package sudoku;

public class Cell {
    private int value;
    private String displayValue;
    private CellStatus status;

    public Cell(int value, CellStatus status) {
        this.value = value;
        this.displayValue = value == 0 ? " " : String.valueOf(value);
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public void setValue(int value) {
        this.value = value;
        this.displayValue = value == 0 ? " " : String.valueOf(value);
    }
}
