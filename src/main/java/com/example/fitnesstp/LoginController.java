package com.example.fitnesstp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class LoginController {
    public Pane background;
    public AnchorPane register;
    public AnchorPane login;

    @FXML
    public void initialize(){
        login.setOpacity(1);
        register.setOpacity(0);
    }

    /**
     * This method checks, if the given Node has empty Text
     * @param c Textfield or PasswordField to check
     */

    private <T> void checkIfEmpty(T c){
        if(c.getClass().equals(TextField.class)) {
            if (((TextField) c).getText().equals("")) {
                ((TextField) c).setStyle("-fx-prompt-text-fill: red");
                ((TextField) c).setPromptText("Field cannot be empty");
            }
        }
        else if(c.getClass().equals(PasswordField.class)){
            if (((PasswordField) c).getText().equals("")) {
                ((PasswordField) c).setStyle("-fx-prompt-text-fill: red");
                ((PasswordField) c).setPromptText("Field cannot be empty");
            }
        }
    }
    @FXML
    public void login(){
        for(Node c : login.getChildren()){
            checkIfEmpty(c);
        }
    }
    @FXML
    public void register(){
        for(Node c : login.getChildren()){
            checkIfEmpty(c);
        }
    }
}