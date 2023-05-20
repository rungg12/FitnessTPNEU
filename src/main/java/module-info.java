module com.example.fitnesstp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens com.example.fitnesstp to com.google.gson;
    exports com.example.fitnesstp;
}