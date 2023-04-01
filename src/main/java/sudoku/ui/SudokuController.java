package sudoku.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import sudoku.game.SudokuGame;
import sudoku.game.models.FileSudokuProvider;
import sudoku.ui.constants.Messages;

public class SudokuController {

    private static final String STATE_FILE_PATH = "game_state.txt";

    @FXML
    private TilePane grid;

    @FXML
    private Button saveGameButton;

    @FXML
    private Button loadGameButton;

    private SudokuGame game;

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

    private void handleCriticalError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
        System.exit(1);
    }

    @FXML
    public void saveGame() {
        try {
            this.game.saveGame(STATE_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.WARNING, Messages.ERROR_COULD_NOT_SAVE_GAME_STATE);
            alert.show();
        }

    }

    @FXML
    public void loadGame() {
        try {
            this.game.loadGame(STATE_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.WARNING, Messages.ERROR_COULD_NOT_LOAD_FROM_FILE);
            alert.show();
        }
    }
}
