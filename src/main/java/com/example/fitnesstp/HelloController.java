package com.example.fitnesstp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.time.LocalTime;

public class HelloController {
    @FXML
    private  Rectangle rectangle;

    @FXML
    public void initialize(){
        rectangle.setStyle("-fx-arc-width: 20px;");
        rectangle.setStyle("-fx-arc-height: 20px;");
        rectangle.setStyle("-fx-border-width: 0px;");
        rectangle.setStyle("-fx-opacity: 0.5;");
        rectangle.setStyle("-fx-fill: #dddddd;");

    }


}