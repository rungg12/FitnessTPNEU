module com.example.fitnesstp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.fitnesstp to javafx.fxml;
    exports com.example.fitnesstp;
}