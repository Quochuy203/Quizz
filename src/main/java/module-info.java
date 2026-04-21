module com.example.quizz {
    requires javafx.controls;
    requires javafx.fxml;
    // Nếu bạn dùng thư viện GSON để lưu game sau này thì thêm dòng dưới
    // requires com.google.gson;

    opens com.example.quizz to javafx.fxml;
    exports com.example.quizz;
}