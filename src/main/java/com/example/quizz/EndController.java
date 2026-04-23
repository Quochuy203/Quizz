package com.example.quizz;

import com.example.quizz.model.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;
import java.io.IOException;

public class EndController {

    @FXML private Label lblTitle;
    @FXML private Label lblMessage;
    @FXML private Button btnMenuPrincipal;
    @FXML private Button btnQuitter;

    private int finalScore;

    @FXML
    public void initialize() {
        GameState state = SaveService.load();
        if (state != null) {
            finalScore = state.getQuizScore();
        } else {
            finalScore = 0;
        }

        lblTitle.setText("🎉 Victoire ! 🎉");
        lblMessage.setText("Tu as remporté la victoire avec un score de " + finalScore + " / 5 !\n\nLa bombe a été désamorcée. La ville est sauvée !");
    }

    @FXML
    protected void onMenuPrincipalClick() throws IOException {
        GameState state = SaveService.load();
        if (state != null) {
            state.setCurrentStep(0);
            state.setQuizScore(0);
            state.setDialogueIndex(0);
            SaveService.save(state);
        }
        HelloApplication.switchScene("hello-view.fxml");
    }

    @FXML
    protected void onQuitterClick() {
        GameState state = SaveService.load();
        if (state != null) SaveService.save(state);
        Platform.exit();
    }
}
