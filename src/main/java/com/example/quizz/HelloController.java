package com.example.quizz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Platform;

public class HelloController {
    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnQuit;

    @FXML
    protected void onNewGameClick() {
        System.out.println("Button Nouvelle Partie a été tapé!");
    }
    @FXML
    protected void onQuitClick() {
        System.out.println("En cours quitté ...");
        Platform.exit();
    }
}
