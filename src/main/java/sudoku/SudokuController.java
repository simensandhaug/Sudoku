package sudoku;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SudokuController {
    @FXML
    private Pane sudokuPane;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private void handleNewGameButtonClicked() {
        //
    }

    @FXML
    public void initialize() throws IOException {
        sudokuGrid = new GridPane();
        sudokuPane.getChildren().add(sudokuGrid);

        SudokuFactory sudokuFactory = new SudokuFactory();
        Sudoku sudoku = sudokuFactory.createSudokuFromSDKFile("src/main/resources/sudoku.sdk");
        System.out.println(sudoku.getBoard());
    }

}
