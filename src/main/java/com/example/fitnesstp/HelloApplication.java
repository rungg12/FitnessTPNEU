package com.example.fitnesstp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FXML-Login-Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        scene.getStylesheets().add(getClass().getResource("ButtonCSS.css").toString());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}