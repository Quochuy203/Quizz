package com.example.quizz;

import com.example.quizz.model.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import java.io.IOException;
import java.util.List;

public class DialogueController {

    @FXML private Label lblSpeaker;
    @FXML private Label lblText;
    @FXML private Button btnNext;
    @FXML private ImageView imgCharacter;
    @FXML private ImageView imgBackground;

    // Tous les dialogues d'introduction
    private final List<String[]> dialogues = List.of(
            new String[]{"Chef", "Écoute-moi bien. Une bombe a été placée quelque part en ville, et tout repose sur toi. Nous n'avons pas de temps à perdre. Chaque seconde compte."},
            new String[]{"Chef", "Voici la situation : tu vas devoir résoudre une série d'énigmes. Chacune te donnera des indices pour localiser la bombe. Le temps presse, mais nous avons encore une chance si tu agis rapidement et avec précision."},
            new String[]{"Chef", "Je sais que ce n'est pas facile, mais je crois en toi. Nous avons les outils nécessaires, et tu as l'intelligence pour déchiffrer ces énigmes. Chaque réponse correcte nous rapproche de la solution."},
            new String[]{"Chef", "Ne laisse pas la pression te faire trébucher. Résous les énigmes, trouve l'emplacement de la bombe, et nous pourrons la désamorcer avant qu'il ne soit trop tard. On compte sur toi. La ville compte sur toi."}
    );

    private int currentIndex = 0;
    private Timeline typewriterTimeline;
    private boolean isTyping = false;
    private String fullText = "";

    @FXML
    public void initialize() {
        GameState state = SaveService.load();
        if (state != null) {
            currentIndex = state.getDialogueIndex();
        }

        btnNext.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.SPACE) handleNext();
                });
            }
        });

        showDialogue(currentIndex);
    }

    private void showDialogue(int index) {
        if (index >= dialogues.size()) {
            goToQuiz();
            return;
        }
        String[] line = dialogues.get(index);
        lblSpeaker.setText(line[0]);
        fullText = line[1];
        startTypewriter(fullText);
    }

    private void startTypewriter(String text) {
        lblText.setText("");
        isTyping = true;
        btnNext.setText("Passer ▶");

        if (typewriterTimeline != null) typewriterTimeline.stop();

        final int[] i = {0};
        typewriterTimeline = new Timeline(
                new KeyFrame(Duration.millis(30), e -> {
                    if (i[0] < text.length()) {
                        lblText.setText(lblText.getText() + text.charAt(i[0]));
                        i[0]++;
                    } else {
                        isTyping = false;
                        btnNext.setText("Suivant ▶");
                        typewriterTimeline.stop();
                    }
                })
        );
        typewriterTimeline.setCycleCount(text.length());
        typewriterTimeline.play();
    }

    @FXML
    private void handleNext() {
        if (isTyping) {
            typewriterTimeline.stop();
            lblText.setText(fullText);
            isTyping = false;
            btnNext.setText("Suivant ▶");
        } else {
            currentIndex++;
            GameState state = SaveService.load();
            if (state == null) state = new GameState();
            state.setDialogueIndex(currentIndex);
            SaveService.save(state);

            showDialogue(currentIndex);
        }
    }

    private void goToQuiz() {
        try {
            GameState state = SaveService.load();
            if (state == null) state = new GameState();
            state.setCurrentStep(1);
            state.setDialogueIndex(0);
            SaveService.save(state);
            HelloApplication.switchScene("quiz-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}