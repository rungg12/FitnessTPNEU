package com.example.fitnesstp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;

public class HelloController {
    @FXML
    public Pane login;
    public Pane background;
    public AnchorPane register;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    Timeline setPosLoginRegister;

    @FXML
    public void initialize(){
        startPositi();

    }

    public void startPositi(){

        setPosLoginRegister = new Timeline(new KeyFrame(Duration.millis(20), actionEvent -> {
            login.setLayoutX(background.getWidth()/2-login.getWidth()/2);
            login.setLayoutY(background.getHeight()/2-login.getHeight()/2);
            login.setLayoutX(background.getWidth()/2-login.getWidth()/2);
            login.setLayoutY(background.getHeight()/2-login.getHeight()/2);
        }));
        setPosLoginRegister.setCycleCount(Timeline.INDEFINITE);
        setPosLoginRegister.play();
    }
}