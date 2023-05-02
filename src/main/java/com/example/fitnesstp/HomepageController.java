package com.example.fitnesstp;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class HomepageController {
    public Pane background;
    @FXML
    public Rectangle showVideo;

    public void initialize(){

    }

    @FXML
    public void openTimer(){

    }
    public void test(){
        System.out.println(background.getLayoutX() + " " + background.getLayoutY() + " " + background.getHeight() + " " + background.getWidth());
    }
}
