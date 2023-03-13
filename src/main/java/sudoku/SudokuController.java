package sudoku;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SudokuController {
    @FXML
    private Pane sudokuPane;

    @FXML
    private GridPane sudokuGrid;

    private Sudoku sudoku;

    private int DEFAULT_WINDOW_WIDTH = 800;
    private int DEFAULT_WINDOW_HEIGHT = 800;

    private int SUDOKU_WIDTH, SUDOKU_HEIGHT = 450;

    private TextField selectedCell;

    private int GRID_SIZE = 9;

    private void newSudoku() throws IOException {
        String sdkFilePath = getClass().getResource("/sudoku/files/example.sdk").getPath();

        sudoku = new Sudoku(sdkFilePath);
    }

    @FXML
    private void handleNewGameButtonClicked() {
        displaySudoku();
    }

    @FXML
    public void initialize() throws IOException {
        sudokuPane.setPrefHeight(DEFAULT_WINDOW_HEIGHT);
        sudokuPane.setPrefWidth(DEFAULT_WINDOW_WIDTH);
        sudokuPane.getChildren().clear();
        newSudoku();
        displaySudoku();
    }

    private TextField generateCellFXML(Cell c) {
        TextField cell = new TextField(c.getDisplayValue());
        CellStatus status = c.getStatus();

        cell.getStyleClass().add("cell");

        switch (status) {
            case GIVEN:
                cell.getStyleClass().add("cell-given");
                cell.setDisable(true);
                break;
            case TO_GUESS:
                cell.getStyleClass().add("cell-to-guess");
                break;
            case CORRECT:
                cell.getStyleClass().add("cell-correct");
                break;
            case FALSE:
                cell.getStyleClass().add("cell-false");
                break;
        }

        int cellSize = SUDOKU_WIDTH / GRID_SIZE;
        cell.setPrefHeight(cellSize);
        cell.setPrefWidth(cellSize);

        cell.setOnKeyTyped(event -> {
            if (Character.isDigit(event.getCharacter().charAt(0))) {
                c.setValue(Integer.parseInt(event.getCharacter()));
            } else {
                c.setValue(0);
            }
            cell.setText(c.getDisplayValue());
        });

        return cell;
    }

    private void displaySudoku() {
        sudokuGrid.getChildren().clear();
        sudokuPane.getChildren().clear();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Cell c = sudoku.getPuzzle()[i][j];
                sudokuGrid.add(generateCellFXML(c), i, j);
            }
        }
        sudokuPane.getChildren().add(sudokuGrid);
    }

}
