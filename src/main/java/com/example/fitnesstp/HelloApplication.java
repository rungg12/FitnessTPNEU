package com.example.fitnesstp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public void start(Stage stage) {

        int windowWidth = 1920;
        int windowHeight = 1080;

        Pane root = new Pane();
        root.setPrefSize(windowWidth,windowHeight);
        Scene scene = new Scene(root, windowWidth, windowHeight);



        stage.setTitle("Test");
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);


        stage.requestFocus();
        stage.setRenderScaleX(1);
        stage.setRenderScaleY(1);
        stage.setForceIntegerRenderScale(true);
        //stage.setFullScreen(true);
        stage.show();

        LoginController loginController = new LoginController(scene, root);
        //HomepageController homepageController = new HomepageController(scene, root);
    }

    public static void main(String[] args) {
        launch();
    }
}