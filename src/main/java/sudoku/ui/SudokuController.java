package sudoku.ui;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import sudoku.game.SudokuGame;
import sudoku.game.models.FileSudokuProvider;
import sudoku.ui.constants.Messages;

public class SudokuController {

    @FXML
    private GridPane grid;

    @FXML
    private Button saveGameButton;

    @FXML
    private HBox buttonContainer;

    @FXML
    private Button loadGameButton;

    private SudokuGame game;

    /**
     * Method that initializes the game and displays the board
     * 
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        try {
            game = new SudokuGame(new FileSudokuProvider());
        } catch (IOException e) {
            e.printStackTrace();
            handleCriticalError(Messages.ERROR_COULD_NOT_LOAD_FROM_FILE);
            return;
        }

        UIHelper.displayBoard(grid, game);
        UIHelper.addEventHandlers(grid, game);
    }

    /**
     * Method that displays a critical error message and exits the application
     * 
     * @param message the message to be displayed
     */
    private void handleCriticalError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
        System.exit(1);
    }

    /**
     * Method that displays a warning message
     * 
     * @param message the message to be displayed
     */
    private void handleWarning(String message) {
        Alert alert = new Alert(AlertType.WARNING, message);
        alert.show();
    }

    /**
     * Method that saves the game state to a file
     */
    @FXML
    public void saveGame() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showSaveDialog(new Stage());
            if (selectedFile == null)
                return;
            this.game.saveGame(selectedFile);
            UIHelper.displayBoard(grid, game);
            UIHelper.addEventHandlers(grid, game);
        } catch (IOException e) {
            e.printStackTrace();
            handleWarning(Messages.ERROR_COULD_NOT_SAVE_GAME);
        }

    }

    /**
     * Method that loads the game state from a file
     */
    @FXML
    public void loadGame() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile == null)
                return;
            this.game.loadGame(selectedFile);
            UIHelper.displayBoard(grid, game);
            UIHelper.addEventHandlers(grid, game);
        } catch (IOException e) {
            e.printStackTrace();
            handleWarning(Messages.ERROR_COULD_NOT_LOAD_FROM_FILE);
        }
    }
}
