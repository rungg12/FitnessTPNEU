package com.example.fitnesstp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    /*@Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("HomepageStyle.css")).toString());

        stage.setTitle("Fitness-Programm");
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
        */
        @Override
        public void start(Stage primaryStage) throws IOException {
                //BorderPane root = new BorderPane();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML-Login-Register.fxml")));
                Scene scene = new Scene(root,1920,1080);
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.initStyle(StageStyle.DECORATED);
                primaryStage.setFullScreen(false);
                primaryStage.show();
        }

    public static void main(String[] args) {
        launch();
    }
}