package com.example.fitnesstp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
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
    private final Label label;
    private Timeline tl;
    private LocalTime time;
    /**
     * @param label This is the label, where the remaining time is shown.
     */
    public Timer(Label label) {
        this.label = label;
    }

    /**
     * This method starts counting down for the given duration
     * @param duration How duration of the timer
     */
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
    public void stopTimer(){
        tl.stop();
    }
}
