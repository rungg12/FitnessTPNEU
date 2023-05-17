module com.example.fitnesstp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens com.example.fitnesstp to javafx.fxml;
    exports com.example.fitnesstp;
}