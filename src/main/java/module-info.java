module com.example.quizz {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.example.quizz to javafx.fxml;
    opens com.example.quizz.model to com.google.gson;
    exports com.example.quizz;
}