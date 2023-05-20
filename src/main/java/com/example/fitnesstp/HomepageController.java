package com.example.fitnesstp;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;

import java.net.URL;
import java.util.Objects;

public class HomepageController {
    Scene sceneAct;
    Pane background;
    Rectangle siteBar = new Rectangle();
    Rectangle mainView = new Rectangle();
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    public HomepageController(Scene scene, Pane root){
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/Homestyle.css")).toExternalForm());

        sceneAct = scene;
        background = root;
    }



    public void createObjects(){
        background.getChildren().clear();
        background.setId("background");

        siteBar.setX(sceneAct.getWidth()*0.0156);
        siteBar.setY(sceneAct.getHeight()*0.0278);
        siteBar.setWidth(sceneAct.getWidth()*0.1);
        siteBar.setHeight(sceneAct.getHeight()*0.95);
        siteBar.setId("RectPurple");

        mainView.setX(siteBar.getX()+siteBar.getWidth() + sceneAct.getWidth()*0.04);
        mainView.setY(sceneAct.getHeight()*0.0278);
        mainView.setWidth(sceneAct.getWidth()-mainView.getX() - sceneAct.getWidth()*0.02);
        mainView.setHeight(sceneAct.getHeight()*0.5);
        mainView.setId("RectLightpurple");

        double sliderWidth = mainView.getWidth()/4 - mainView.getWidth()*0.02;
        double sliderSpace = (mainView.getWidth() - 4*sliderWidth)/3;

        Rectangle slider1 = new Rectangle();
        slider1.setX(mainView.getX());
        slider1.setY(mainView.getY()+mainView.getHeight() + sceneAct.getHeight()*0.04);
        slider1.setWidth(sliderWidth);
        slider1.setHeight((siteBar.getY()+siteBar.getHeight()) - slider1.getY());
        slider1.setId("slider");

        Rectangle slider2 = new Rectangle();
        slider2.setX(mainView.getX() + sliderWidth + sliderSpace);
        slider2.setY(mainView.getY()+mainView.getHeight() + sceneAct.getHeight()*0.04);
        slider2.setWidth(sliderWidth);
        slider2.setHeight((siteBar.getY()+siteBar.getHeight()) - slider2.getY());
        slider2.setId("slider");

        Rectangle slider3 = new Rectangle();
        slider3.setX(mainView.getX() + sliderWidth*2 + sliderSpace*2);
        slider3.setY(mainView.getY()+mainView.getHeight() + sceneAct.getHeight()*0.04);
        slider3.setWidth(sliderWidth);
        slider3.setHeight((siteBar.getY()+siteBar.getHeight()) - slider3.getY());
        slider3.setId("slider");

        Rectangle slider4 = new Rectangle();
        slider4.setX(mainView.getX() + sliderWidth*3 + sliderSpace*3);
        slider4.setY(mainView.getY()+mainView.getHeight() + sceneAct.getHeight()*0.04);
        slider4.setWidth(sliderWidth);
        slider4.setHeight((siteBar.getY()+siteBar.getHeight()) - slider4.getY());
        slider4.setId("slider");

        Text nameText = new Text("Fitness Fiesta");
        nameText.setFont(Font.font("Montserrat", FontWeight.BOLD, 160));
        nameText.setFill(Color.WHITE);
        nameText.setTextAlignment(TextAlignment.CENTER);
        nameText.setX(mainView.getX());
        nameText.setY(mainView.getY() + mainView.getHeight() / 2);
        nameText.setWrappingWidth(mainView.getWidth());

        background.getChildren().addAll(siteBar, mainView, slider1, slider2, slider3, slider4, nameText);

        createButtons();
    }



    public void createButtons() {
        int amountPictures = 4;

        //Images Importieren
        Image a = new Image("/images/AddWorkoutSymbol100x100.png"); //wird einmal als Image abgespeichert, damit man auf die Höhe und breite zugreifen kann
        imageView1 = new ImageView(new Image("/images/AddWorkoutSymbol100x100.png"));
        imageView2 = new ImageView(new Image("/images/AddWorkoutSymbol100x100.png"));
        imageView3 = new ImageView(new Image("/images/AddWorkoutSymbol100x100.png"));
        imageView4 = new ImageView(new Image("/images/exit100x100.png"));

        //Abstand zwischen Bildern, bzw zwischen oben und unten berechnen
        double distancePicToPic = (siteBar.getHeight()-a.getHeight()*amountPictures-1)/amountPictures;

        //Neue VBox mittig über das Rectangle, damit die Bilder in der Mitte angezeigt werden
        VBox buttonbox = new VBox();
        buttonbox.setLayoutX(siteBar.getX() + (siteBar.getWidth()-a.getWidth())/2);
        buttonbox.setLayoutY(siteBar.getX() + ((siteBar.getWidth()-a.getWidth())/2));

        buttonbox.setSpacing(distancePicToPic);

        buttonbox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);

        background.getChildren().addAll(buttonbox);

        //Listener fuer das Klicken auf die Symbole
        // Add event handlers to the image views
        imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                WorkoutController workoutController = new WorkoutController(sceneAct, background);
            }
        });

        imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Handle the click event for the second image view
                // Change the scene or perform any other desired action
            }
        });

        imageView3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Handle the click event for the third image view
                // Change the scene or perform any other desired action
            }
        });

        imageView4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
            }
        });
    }



}