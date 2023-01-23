module com.example.fitnesstp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fitnesstp to javafx.fxml;
    exports com.example.fitnesstp;
}