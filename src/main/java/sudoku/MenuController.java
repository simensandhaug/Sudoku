package sudoku;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Pane pane;

    private void newGame() throws IOException {
        System.out.println("New game");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        loader.load();

        Stage stage = (Stage) pane.getScene().getWindow();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
    }

    @FXML
    public void newEasy() throws IOException {
        newGame();
    }

    @FXML
    public void newMedium() throws IOException {
        newGame();
    }

    @FXML
    public void newHard() throws IOException {
        newGame();
    }

    @FXML
    public void loadFileSelected() {
        System.out.println("Load file selected");
    }

    @FXML
    public void initialize() throws IOException {
        System.out.println("MenuController initialized");
    }
}
