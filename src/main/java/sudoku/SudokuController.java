package sudoku;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class SudokuController {
    @FXML
    private Pane sudokuPane;

    Sudoku sudoku;

    @FXML
    public void initialize() throws IOException {
        GridPane sudokuGrid = new GridPane();
        sudokuPane.getChildren().add(sudokuGrid);

        SudokuFileSupport sudokuFileSupport = new SudokuFileSupport();
        sudoku = sudokuFileSupport.readSudoku("example.sdk");
        sudokuFileSupport.writeSudoku(sudoku);
        displaySudoku();
    }

    private void displaySudoku() {
        Board board = sudoku.getBoard();

        GridPane sudokuGrid = new GridPane();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField cell = new TextField();
                cell.getStyleClass().add("cell");
                int row = i;
                int col = j;
                cell.setPrefSize(30, 30);
                Integer value = board.getCell(i, j).getValue();
                cell.setText(value == 0 ? null : value.toString());
                if (value != 0) {
                    cell.setDisable(true);
                    cell.getStyleClass().add("cell:disabled");
                }
                cell.onKeyTypedProperty().set(e -> {
                    if (!Character.isDigit(e.getCharacter().charAt(0))) {
                        e.consume();
                    } else {
                        sudoku.getBoard().getCell(row, col).setValue(Integer.parseInt(e.getCharacter()));
                        cell.setText(e.getCharacter());
                    }
                });
                sudokuGrid.add(cell, i, j);
            }
        }
        sudokuPane.getChildren().clear();
        sudokuGrid.getStyleClass().add("sudokuGrid");
        sudokuPane.getChildren().add(sudokuGrid);
    }

    @FXML
    public void saveGame() {
        System.out.println("Save game");
    }

    @FXML
    public void backToMenu() throws IOException {
        System.out.println("Back to menu");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader.load();

        Stage stage = (Stage) sudokuPane.getScene().getWindow();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
    }

}
