package sudoku.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.TilePane;
import sudoku.game.SudokuGame;
import sudoku.ui.constants.Messages;

public class UIHelper {

    /**
     * Method that generates a text field with the specified value and adds it to
     * the grid
     * 
     * @param grid the FXML {@link TilePane grid} containing the text fields
     * @param game the {@link SudokuGame game} object containing the board state
     */
    public static void displayBoard(TilePane grid, SudokuGame game) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = generateTextField(game.getCurrentSudoku().getBoard()[i][j].getValue());
                grid.getChildren().add(textField);
            }
        }
    }

    /**
     * Method that adds event handlers to the text fields in the grid to update the
     * state
     * 
     * @param grid the FXML {@link TilePane grid} containing the text fields
     * @param game the {@link SudokuGame game} object containing the board state
     */
    public static void addEventHandlers(TilePane grid, SudokuGame game) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int finalI = i;
                int finalJ = j;
                TextField textField = (TextField) grid.getChildren().get(i * 9 + j);
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() == 0) {
                        game.getCurrentSudoku().getBoard()[finalI][finalJ].setValue(0);
                    } else {
                        game.getCurrentSudoku().getBoard()[finalI][finalJ].setValue(Integer.parseInt(newValue));
                    }

                    if (game.isFinished()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, Messages.INFO_GAME_FINISHED);
                        alert.show();
                    }
                });
            }
        }
    }

    /**
     * Method that generates a text field with the specified value
     * 
     * @param value the value to be displayed in the text field
     * @return the generated FXML {@link TextField}
     */
    private static TextField generateTextField(int value) {
        TextField textField = new TextField();
        textField.setPrefSize(50, 50);
        textField.setMaxSize(50, 50);
        textField.setMinSize(50, 50);
        textField.setText(String.valueOf(value));
        textField.getStyleClass().add("sudoku-cell");
        if (value != 0) {
            textField.setEditable(false);
        }
        if (value == 0) {
            textField.setText("");
        }

        textField.setTextFormatter(new TextFormatter<Integer>(c -> {
            if (c.getControlNewText().matches("\\d?")) {
                return c;
            } else {
                return null;
            }
        }));

        return textField;
    }
}
