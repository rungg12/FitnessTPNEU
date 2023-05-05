package com.example.fitnesstp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.Timer;

public class LoginController {
    private boolean register = false;
    private final Scene scene;
    private TextField username;
    private PasswordField password;
    private Button loginButton;
    private Button swapRegister;

    private PasswordField registerPassword;
    private TextField registerUsername;
    private PasswordField repeatPassword;
    private Button registerButton;
    private Button registerSwapRegister;

    private final Pane background;
    private AnchorPane loginPane;
    private AnchorPane registerPane;

    private HomepageController homepage;

    public LoginController(Scene scene, Pane root, HomepageController homepage){
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/LoginStyle.css")).toExternalForm());

        this.homepage = homepage;
        this.scene = scene;
        this.background = root;
        this.background.getChildren().removeAll();

        createObjects();
    }

    private void createObjects(){
        username = new TextField();
        password = new PasswordField();
        loginButton = new Button("LOGIN");
        loginButton.setOnAction(actionEvent -> {
            for (Node c : loginPane.getChildren()) {
                if(checkIfEmpty(c)){
                    try {
                        if (authenticate(username.getText(),password.getText())) {
                            homepage.createObjects();
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        loginPane = new AnchorPane();
        registerPane = new AnchorPane();
        swapRegister = new Button("register?");
        registerSwapper(swapRegister);

        registerSwapRegister = new Button("login?");
        registerSwapper(registerSwapRegister);
        registerButton = new Button("REGISTER");
        registerUsername = new TextField();
        registerPassword = new PasswordField();
        repeatPassword = new PasswordField();

        registerPane.getChildren().addAll(registerUsername, registerButton, registerPassword, registerSwapRegister, repeatPassword);
        loginPane.getChildren().addAll(username, loginButton, password, swapRegister);
        background.getChildren().addAll(loginPane, registerPane);
        registerPane.setVisible(false);
        background.setId("BackgroundPane");

        setSizesAndIDS();
        positionLogin();
        positionRegister();
    }

    public boolean authenticate(String username, String password) throws IOException, ClassNotFoundException, FileNotFoundException {
        // Deserialisation der Abgespeicherten User
        FileInputStream fileIn = new FileInputStream(username + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        User user = (User) in.readObject();
        in.close();
        fileIn.close();

        // Check if the username and password match
        return user.getName().equals(username) && user.getPassword().equals(password);
    }

    private void setSizesAndIDS(){

        loginPane.setPrefHeight(scene.getHeight() * 0.4);
        loginPane.setPrefWidth(scene.getWidth() * 0.260416666666666666666666666);
        loginPane.setId("AnchorPane");

        username.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        username.setPrefWidth(scene.getWidth() * 0.25);
        username.setPromptText("username");

        password.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        password.setPrefWidth(scene.getWidth() * 0.25);
        password.setPromptText("password");

        loginButton.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        loginButton.setPrefWidth(scene.getWidth() * 0.15625);

        swapRegister.setPrefWidth(scene.getWidth() * 0.05);
        swapRegister.setPrefHeight(scene.getHeight() * 0.02);
        swapRegister.setId("SwapRegister");

        registerPane.setPrefHeight(scene.getHeight() * 0.5);
        registerPane.setPrefWidth(scene.getWidth() * 0.260416666666666666666666666);
        registerPane.setId("AnchorPane");

        registerPassword.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        registerPassword.setPrefWidth(scene.getWidth() * 0.25);
        registerPassword.setPromptText("password");

        registerUsername.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        registerUsername.setPrefWidth(scene.getWidth() * 0.25);
        registerUsername.setPromptText("username");

        repeatPassword.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        repeatPassword.setPrefWidth(scene.getWidth() * 0.25);
        repeatPassword.setPromptText("repeat password");

        registerButton.setPrefHeight(scene.getHeight() * 0.06481481481481481481481481481481);
        registerButton.setPrefWidth(scene.getWidth() * 0.15625);

        registerSwapRegister.setPrefWidth(scene.getWidth() * 0.05);
        registerSwapRegister.setPrefHeight(scene.getHeight() * 0.02);
        registerSwapRegister.setId("SwapRegister");
    }

    private void registerSwapper(Button registerSwapRegister) {
        registerSwapRegister.setOnAction(actionEvent -> {
            register = !register;
            if(register){
                loginPane.setVisible(false);
                positionRegister();
                registerPane.setVisible(true);
            }
            else{
                registerPane.setVisible(false);
                positionLogin();
                loginPane.setVisible(true);
            }
        });
    }

    private void setPositions(AnchorPane loginPane, TextField username, PasswordField password) {
        loginPane.setLayoutX(scene.getWidth()/2 - loginPane.getPrefWidth()/2);
        loginPane.setLayoutY(scene.getHeight() * 0.17222222222222222222222222222222);

        AnchorPane.setLeftAnchor(username, 10.0);
        AnchorPane.setRightAnchor(username, 10.0);
        AnchorPane.setTopAnchor(username, 40.0);

        AnchorPane.setLeftAnchor(password, 10.0);
        AnchorPane.setRightAnchor(password, 10.0);
        AnchorPane.setTopAnchor(password, 150.0);
    }

    private void positionLogin(){
        setPositions(loginPane, username, password);

        AnchorPane.setLeftAnchor(loginButton, 100.0);
        AnchorPane.setRightAnchor(loginButton, 100.0);
        AnchorPane.setBottomAnchor(loginButton, 70.0);

        AnchorPane.setLeftAnchor(swapRegister, 390.0);
        AnchorPane.setRightAnchor(swapRegister, 20.0);
        AnchorPane.setBottomAnchor(swapRegister, 20.0);
    }

    private void positionRegister(){
        setPositions(registerPane, registerUsername, registerPassword);

        AnchorPane.setLeftAnchor(repeatPassword, 10.0);
        AnchorPane.setRightAnchor(repeatPassword, 10.0);
        AnchorPane.setTopAnchor(repeatPassword, 260.0);

        AnchorPane.setLeftAnchor(registerButton, 100.0);
        AnchorPane.setRightAnchor(registerButton, 100.0);
        AnchorPane.setBottomAnchor(registerButton, 70.0);

        AnchorPane.setLeftAnchor(registerSwapRegister, 390.0);
        AnchorPane.setRightAnchor(registerSwapRegister, 20.0);
        AnchorPane.setBottomAnchor(registerSwapRegister, 20.0);

    }

    private <T> boolean checkIfEmpty(T c){
        if(c.getClass().equals(TextField.class)) {
            if (((TextField) c).getText().equals("")) {
                ((TextField) c).setStyle("-fx-prompt-text-fill: red");
                ((TextField) c).setPromptText("Field cannot be empty");
                return false;
            }
        }
        else if(c.getClass().equals(PasswordField.class)){
            if (((PasswordField) c).getText().equals("")) {
                ((PasswordField) c).setStyle("-fx-prompt-text-fill: red");
                ((PasswordField) c).setPromptText("Field cannot be empty");
                return false;
            }
        }
        return true;
    }
}