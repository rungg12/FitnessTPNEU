package com.example.fitnesstp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * This is a Class to set a Timer in a Label, that updates the time in the given label and counts down for the duration of the set timer.
 * @author Dominik Puner, Laurin Rungg, Matthias Zelger, David Erlacher
 *
 */

public class Timer {
    private final Scene scene;
    private final Group root;
    private final Label label;
    private Button exit;
    private Button start;
    private Button stop;
    private Button pause;
    private Timeline tl;
    private LocalTime time;
    /**
     * @param label This is the label, where the remaining time is shown.
     */
    public Timer(Label label, Stage primaryStage) {
        this.label = label;
        this.root = new Group();
        scene = new Scene(root,1920,1080);
        primaryStage.setScene(scene);

        createObjects();
        addObjectsToRoot();
        setPositions();

        primaryStage.show();
    }
    private void createObjects(){
        start = new Button("START");
        stop = new Button("STOP");
        pause = new Button("PAUSE");
        exit = new Button("X");

        label.setPrefWidth(scene.getWidth() * 0.6);
        label.setPrefHeight(scene.getHeight() * 0.1);

        start.setPrefWidth(scene.getWidth() * 0.15);
        start.setPrefHeight(scene.getHeight() * 0.04);
    }
    private void addObjectsToRoot(){
        root.getChildren().add(start);
        root.getChildren().add(stop);
        root.getChildren().add(pause);
        root.getChildren().add(label);
        root.getChildren().add(exit);
    }
    private void setPositions(){
        label.setLayoutX(scene.getWidth() / 2 - label.getWidth());
        label.setLayoutY(0);
    }

    /**
     * This method starts counting down for the given duration
     * @param duration The duration of the timer
     */
    @FXML
    public void startTimer(LocalTime duration){
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        time = duration;
        label.setText(time.format(formatter));
        tl = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> {
            time = time.minusSeconds(1);
            label.setText(time.format(formatter));
            if(time == LocalTime.of(0,0,0))
                tl.stop();
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }

    /**
     * This method starts a timer, that counts upwards starting form 0 min and 0 sec
     */
    @FXML
    public void infinteTimer(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        if(Objects.equals(label.getText(), ""))
            time = LocalTime.of(0, 0, 0);
        else
            time = LocalTime.parse(label.getText());
        tl = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> {
            time = time.plusSeconds(1);
            label.setText(time.format(formatter));
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }

    /**
     * pauses the timer
     */
    @FXML
    public void stopTimer(){
        tl.stop();
    }
}
