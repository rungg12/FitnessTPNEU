package com.example.fitnesstp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalTime;

public class HelloController {
    @FXML
    private Label label;
    Timer timer;

    @FXML
    public void startTimer(){
        timer = new Timer(label);
        timer.infinteTimer();
    }
    @FXML
    public void stopTimer(){
        timer.stopTimer();
    }
    @FXML
    public void startTimerTimer(){
        timer = new Timer(label);
        timer.startTimer(LocalTime.of(0,0,10));
    }

}