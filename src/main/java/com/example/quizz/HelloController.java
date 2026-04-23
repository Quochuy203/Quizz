package com.example.quizz;

import com.example.quizz.model.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import java.io.IOException;

public class HelloController {

    @FXML private Button btnNewGame;
    @FXML private Button btnLoad;
    @FXML private Button btnQuit;

    @FXML
    public void initialize() {
        boolean hasSave = SaveService.hasSave();
        btnLoad.setDisable(!hasSave);
    }

    @FXML
    protected void onNewGameClick() throws IOException {
        GameState state = new GameState();
        state.setCurrentStep(0);
        state.setQuizScore(0);
        SaveService.save(state);

        HelloApplication.switchScene("dialogue-view.fxml");
    }

    @FXML
    protected void onLoadClick() throws IOException {
        GameState state = SaveService.load();
        if (state == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sauvegarde");
            alert.setContentText("Aucune sauvegarde trouvée !");
            alert.showAndWait();
            return;
        }
        switch (state.getCurrentStep()) {
            case 0 -> HelloApplication.switchScene("dialogue-view.fxml");
            case 1 -> HelloApplication.switchScene("quiz-view.fxml");
            default -> HelloApplication.switchScene("dialogue-view.fxml");
        }
    }

    @FXML
    protected void onQuitClick() {
        GameState state = SaveService.load();
        if (state != null) SaveService.save(state);
        Platform.exit();
    }
}