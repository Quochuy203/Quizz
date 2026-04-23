package com.example.quizz;

import com.example.quizz.model.GameState;
import com.example.quizz.model.QuizQuestion;
import com.google.gson.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.*;

public class QuizController {

    @FXML private Label lblScore;
    @FXML private Label lblQuestion;
    @FXML private Label lblFeedback;
    @FXML private Button btn1, btn2, btn3, btn4;
    @FXML private Button btnNext;

    private static final int SCORE_TO_WIN = 5;
    private int score = 0;
    private QuizQuestion currentQuestion;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @FXML
    public void initialize() {
        GameState state = SaveService.load();
        if (state != null) score = state.getQuizScore();
        updateScoreLabel();
        btnNext.setVisible(false);
        loadQuestion();
    }

    private void loadQuestion() {
        lblQuestion.setText("Chargement...");
        lblFeedback.setText("");
        setButtonsDisabled(false);
        btnNext.setVisible(false);

        new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://opentdb.com/api.php?amount=1&type=multiple"))
                        .GET()
                        .build();

                HttpResponse<String> response =
                        httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject result = json.getAsJsonArray("results").get(0).getAsJsonObject();

                String question = decodeHtml(result.get("question").getAsString());
                String correct = decodeHtml(result.get("correct_answer").getAsString());
                List<String> incorrect = new ArrayList<>();
                for (JsonElement e : result.getAsJsonArray("incorrect_answers")) {
                    incorrect.add(decodeHtml(e.getAsString()));
                }

                currentQuestion = new QuizQuestion(question, correct, incorrect);

                Platform.runLater(() -> displayQuestion(currentQuestion));

            } catch (Exception e) {
                Platform.runLater(() -> lblQuestion.setText("Erreur chargement. Réessayez."));
            }
        }).start();
    }

    private void displayQuestion(QuizQuestion q) {
        lblQuestion.setText(q.getQuestion());
        List<Button> buttons = List.of(btn1, btn2, btn3, btn4);
        List<String> answers = q.getAllAnswers();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(answers.get(i));
            buttons.get(i).setStyle(""); // reset style
        }
    }

    @FXML
    private void onAnswer1() { checkAnswer(btn1); }
    @FXML
    private void onAnswer2() { checkAnswer(btn2); }
    @FXML
    private void onAnswer3() { checkAnswer(btn3); }
    @FXML
    private void onAnswer4() { checkAnswer(btn4); }

    private void checkAnswer(Button clicked) {
        setButtonsDisabled(true);
        String selected = clicked.getText();
        boolean correct = selected.equals(currentQuestion.getCorrectAnswer());

        if (correct) {
            score++;
            clicked.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            lblFeedback.setText("✅ Correct !");
            lblFeedback.setStyle("-fx-text-fill: #4CAF50;");
        } else {
            clicked.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
            lblFeedback.setText("❌ Incorrect ! La bonne réponse est : " + currentQuestion.getCorrectAnswer());
            lblFeedback.setStyle("-fx-text-fill: #f44336;");
            // Mettre en vert la bonne réponse
            for (Button b : List.of(btn1, btn2, btn3, btn4)) {
                if (b.getText().equals(currentQuestion.getCorrectAnswer())) {
                    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                }
            }
        }

        updateScoreLabel();
        GameState state = SaveService.load();
        if (state == null) state = new GameState();
        state.setQuizScore(score);
        SaveService.save(state);

        btnNext.setVisible(true);
    }

    @FXML
    private void onNext() {
        if (score >= SCORE_TO_WIN) {
            try {
                GameState state = SaveService.load();
                if (state == null) state = new GameState();
                state.setCurrentStep(2);
                SaveService.save(state);
                HelloApplication.switchScene("end-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadQuestion();
        }
    }

    private void updateScoreLabel() {
        lblScore.setText(score + " / " + SCORE_TO_WIN);
    }

    private void setButtonsDisabled(boolean disabled) {
        btn1.setDisable(disabled);
        btn2.setDisable(disabled);
        btn3.setDisable(disabled);
        btn4.setDisable(disabled);
    }

    private String decodeHtml(String text) {
        return text.replace("&quot;", "\"")
                .replace("&#039;", "'")
                .replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&eacute;", "é")
                .replace("&egrave;", "è");
    }
}