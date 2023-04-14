package sudoku.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import sudoku.game.SudokuGame;
import sudoku.ui.constants.Messages;

/**
 * A helper class that provides methods to generate and display the Sudoku board
 * UI
 */
public class UIHelper {

    /**
     * Generates a text field with the specified value and adds it to the grid
     * 
     * @param grid The FXML {@link GridPane grid} containing the text fields
     * @param game The {@link SudokuGame game} object containing the board state
     */
    public static void displayBoard(GridPane grid, SudokuGame game) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = generateTextField(game.getCurrentSudoku().getBoard()[i][j].getValue());
                // Add border-bottom and border-right classes to correct cells
                if (i == 2 || i == 5) {
                    textField.getStyleClass().add("border-bottom");
                }
                if (j == 2 || j == 5) {
                    textField.getStyleClass().add("border-right");
                }
                if (i == 2 && j == 2 || i == 2 && j == 5 || i == 5 && j == 2 || i == 5 && j == 5) {
                    textField.getStyleClass().remove("border-bottom");
                    textField.getStyleClass().remove("border-right");
                    textField.getStyleClass().add("border-bottom-right");
                }
                grid.add(textField, j, i);
            }
        }
    }

    /**
     * Adds event handlers to the text fields in the grid to update the state
     * 
     * @param grid The FXML {@link GridPane grid} containing the text fields
     * @param game The {@link SudokuGame game} object containing the board state
     */
    public static void addEventHandlers(GridPane grid, SudokuGame game) {
        grid.getChildren().forEach(node -> {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                int rowIndex = GridPane.getRowIndex(node);
                int colIndex = GridPane.getColumnIndex(node);

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    int value = newValue.isEmpty() ? 0 : Integer.parseInt(newValue);
                    game.getCurrentSudoku().getBoard()[rowIndex][colIndex].setValue(value);

                    if (!game.getCurrentSudoku().cellValid(rowIndex, colIndex, value)) {
                        if (!textField.getStyleClass().contains("invalid"))
                            textField.getStyleClass().add("invalid");

                        if (textField.getStyleClass().contains("valid"))
                            textField.getStyleClass().remove("valid");
                    } else {
                        if (!textField.getStyleClass().contains("valid"))
                            textField.getStyleClass().add("valid");

                        if (textField.getStyleClass().contains("invalid"))
                            textField.getStyleClass().remove("invalid");
                    }

                    if (game.isFinished()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, Messages.INFO_GAME_FINISHED);
                        alert.showAndWait();
                    }
                });

                textField.setTextFormatter(new TextFormatter<Integer>(c -> {
                    if (c.getControlNewText().matches("\\d?")) {
                        return c;
                    } else {
                        return null;
                    }
                }));
            }
        });
    }

    /**
     * Generates a text field with the specified value
     * 
     * @param value The value to be displayed in the text field
     * @return The generated FXML {@link TextField}
     */
    private static TextField generateTextField(int value) {
        TextField textField = new TextField(value == 0 ? "" : String.valueOf(value));
        textField.setPrefSize(50, 50);
        textField.setMaxSize(50, 50);
        textField.setMinSize(50, 50);
        textField.getStyleClass().add("cell");
        textField.setAlignment(Pos.CENTER); // Set text alignment to center

        if (value != 0) {
            textField.setEditable(false);
            textField.getStyleClass().add("prefilled"); // Apply the "prefilled" style class
        } else {
            textField.getStyleClass().add("editable"); // Apply the "editable" style class
            textField.getStyleClass().add("valid"); // Apply the "valid" style class
        }

        return textField;
    }

}
