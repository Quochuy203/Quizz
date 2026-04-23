package com.example.quizz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/com/example/quizz/hello-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quizz: Escape Game");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(400);
        stage.setMinHeight(500);
        stage.show();
    }

    public static void switchScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("/com/example/quizz/" + fxmlPath)
        );
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}