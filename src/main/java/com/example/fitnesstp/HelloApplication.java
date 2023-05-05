package com.example.fitnesstp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        int windowWidth = 1920;
        int windowHeight = 1080;

        Pane root = new Pane();
        Scene scene = new Scene(root, windowWidth, windowHeight);

        stage.setScene(scene);
        stage.requestFocus();
        stage.show();

        LoginController loginController = new LoginController(scene, root);
    }

    public static void main(String[] args) {
        launch();
    }
}