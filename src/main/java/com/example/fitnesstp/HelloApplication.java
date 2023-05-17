package com.example.fitnesstp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public void start(Stage stage) throws InterruptedException {

        int windowWidth = 1920;
        int windowHeight = 1080;

        Pane root = new Pane();
        root.setPrefSize(windowWidth, windowHeight);
        Scene scene = new Scene(root, windowWidth, windowHeight);

        stage.setTitle("Fitness Gott");

        stage.setWidth(1920);
        stage.setHeight(1080);

        stage.setScene(scene);

        stage.requestFocus();
        //stage.setRenderScaleX(1);
        //stage.setRenderScaleY(1);
        //stage.setForceIntegerRenderScale(true);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.show();

        //HomepageController homepageController = new HomepageController(scene, root);
        //LoginController loginController = new LoginController(scene, root, homepageController);
        WorkoutController workoutController = new WorkoutController(scene, root);




    }

    public static void main(String[] args) {
        launch();
    }
}