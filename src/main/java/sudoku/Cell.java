package sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Cell {
    private int value;
    private Set<Integer> possibleValues = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private boolean isGiven;
    private Collection<ValidationRegion> validationRegions = new ArrayList<ValidationRegion>(3);

    public Cell(int value, boolean isGiven) {
        this.value = value;
        this.isGiven = isGiven;
    }

    public boolean validate() {
        return validationRegions.stream().allMatch(ValidationRegion::validate);
    }

    public void addValidationRegion(ValidationRegion validationRegion) {
        validationRegions.add(validationRegion);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public void removePossibleValue(int value) {
        possibleValues.remove(value);
    }

    public void addPossibleValue(int value) {
        possibleValues.add(value);
    }

    public boolean isGiven() {
        return isGiven;
    }

    public void setGiven(boolean isGiven) {
        this.isGiven = isGiven;
    }
}
