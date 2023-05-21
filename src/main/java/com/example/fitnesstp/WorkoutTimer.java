package com.example.fitnesstp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This is a Class to set a Timer in a TextField, that updates the time in the given textField and counts down for the duration of the set timer.
 * @author Dominik Puner, Laurin Rungg, Matthias Zelger, David Erlacher
 *
 */

public class WorkoutTimer {
    private final Pane root;
    private TextField textField;
    private Timeline tl;
    private LocalTime time;
    private Boolean isPaused = false;

    /**
     */
    public WorkoutTimer(Pane root, HomepageController hpc) {
        root.getChildren().clear();
        this.root = root;
        root.setId("BackgroundPane");
        createObjects();
        root.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.ESCAPE) {
                hpc.createObjects();
            }
        });
    }
    private void createObjects(){

        textField = new TextField("00:00:00");
        textField.setPrefHeight(30);
        textField.setPrefWidth(100);
        textField.setFont(new Font(18));

        // Bind the textField layout to the scene width and height
        textField.layoutXProperty().bind(root.widthProperty().multiply(0.5).subtract(textField.prefWidthProperty().multiply(0.5)));
        textField.layoutYProperty().bind(root.heightProperty().multiply(0.4).subtract(textField.prefHeightProperty().multiply(0.5)));

        // Create the buttons
        Button startButton = new Button("Starten");
        startButton.setPrefHeight(50);
        startButton.setPrefWidth(200);
        startButton.setMinWidth(200);

        startButton.setFont(new Font(18));
        startButton.setOnAction(actionevent -> startTimer(LocalTime.parse(textField.getText())));

        Button pauseButton = new Button("Pausieren");
        pauseButton.setPrefHeight(50);
        pauseButton.setPrefWidth(200);
        pauseButton.setMinWidth(200);
        pauseButton.setFont(new Font(18));
        pauseButton.setOnAction(actionevent -> pauseTimer());

        Button resetButton = new Button("Zurücksetzen");
        resetButton.setPrefHeight(50);
        resetButton.setPrefWidth(230);
        resetButton.setMinWidth(230);
        resetButton.setFont(new Font(18));
        resetButton.setOnAction(actionevent -> reset());

        // Create the hbox
        HBox hbox = new HBox();
        hbox.setPrefHeight(50);

        // Bind the hbox layout and width to the scene width and height
        hbox.layoutXProperty().bind(root.widthProperty().multiply(0.5).subtract(hbox.widthProperty().multiply(0.5)));
        hbox.layoutYProperty().bind(root.heightProperty().multiply(0.6).subtract(hbox.prefHeightProperty()));

        hbox.getChildren().addAll(startButton, resetButton, pauseButton);
        hbox.setSpacing(10);

        // Add the arc, textField and hbox to the root pane
        root.getChildren().addAll(textField, hbox);
    }
    private void pauseTimer(){
        if(isPaused){
            tl.play();
            textField.setEditable(true);
        }
        else{
            tl.pause();
        }
        System.out.println(isPaused);

    }
    /**
     * This method starts counting down for the given duration
     * @param duration The duration of the timer
     */
    public void startTimer(LocalTime duration){
        isPaused = false;
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        if(!duration.equals(LocalTime.of(0, 0, 0)))
            time = duration;
        else
            time = LocalTime.of(0,0,0);
        textField.setEditable(false);
        textField.setText(time.format(formatter));
        tl = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> {
            if(time != LocalTime.of(0,0,0)){
                time = time.minusSeconds(1);
                textField.setText(time.format(formatter));
            }
            else{
                tl.stop();
                textField.setEditable(true);
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }
    public void reset(){
        isPaused = false;
        textField.setText("00:00:00");
        if(tl.getStatus().equals(Animation.Status.RUNNING))
            tl.stop();
    }
}
