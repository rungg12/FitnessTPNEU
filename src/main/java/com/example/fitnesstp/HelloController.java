package com.example.fitnesstp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.time.LocalTime;

public class HelloController {
    @FXML
    private Pane login;

    @FXML
    public void initialize(){
        login.requestFocus();
    }


}