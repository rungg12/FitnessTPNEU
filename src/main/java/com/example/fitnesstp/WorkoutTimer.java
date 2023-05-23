package com.example.fitnesstp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
        root.getStylesheets().clear();
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/TimerStyle.css")).toExternalForm());
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
        textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
        textField.setMinHeight(Region.USE_COMPUTED_SIZE);
        textField.setPrefWidth(root.getWidth() * 0.05208333333333333333333333333333);
        textField.setFont(new Font(18));

        // Bind the textField layout to the scene width and height
        textField.layoutXProperty().bind(root.widthProperty().multiply(0.5).subtract(textField.prefWidthProperty().multiply(0.5)));
        textField.layoutYProperty().bind(root.heightProperty().multiply(0.4).subtract(textField.prefHeightProperty().multiply(0.5)));

        // Create the buttons
        Button startButton = new Button("Starten");
        startButton.setPrefHeight(root.getHeight() * 0.0462962962962962962962962962963);
        startButton.setPrefWidth(root.getWidth() * 0.11979166666666666666666666666667);
        startButton.setMinWidth(root.getWidth() * 0.11979166666666666666666666666667);

        startButton.setFont(new Font(18));
        startButton.setOnAction(actionevent -> {
            if(textField.getText().matches("\\d{2}:\\d{2}:\\d{2}"))
                startTimer(LocalTime.parse(textField.getText()));
            else{
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), actionevent2 ->{
                    textField.setText("Ungültige eingabe");
                    textField.setStyle("-fx-text-fill: red; -fx-font-size: 20");
                }));
                timeline.setCycleCount(2000);
                timeline.play();
                timeline.setOnFinished(actionevent2 -> {
                    textField.setText("00:00:00");
                    textField.setStyle("-fx-text-fill: black");

                });
            }
        });

        Button pauseButton = new Button("Pausieren");
        pauseButton.setPrefHeight(root.getHeight() * 0.0462962962962962962962962962963);
        pauseButton.setPrefWidth(root.getWidth() * 0.11979166666666666666666666666667);
        pauseButton.setMinWidth(root.getWidth() * 0.11979166666666666666666666666667);
        pauseButton.setFont(new Font(18));
        pauseButton.setOnAction(actionevent -> pauseTimer());

        Button resetButton = new Button("Zurücksetzen");
        resetButton.setPrefHeight(50);
        resetButton.setPrefWidth(root.getWidth() * 0.15);
        resetButton.setMinWidth(root.getWidth() * 0.15);
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
            textField.setEditable(false);
        }
        else{
            tl.pause();
            textField.setEditable(true);
        }

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
        textField.setEditable(true);
        textField.setText("00:00:00");
        if(tl.getStatus().equals(Animation.Status.RUNNING))
            tl.stop();
    }
}
