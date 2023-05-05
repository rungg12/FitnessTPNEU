package com.example.fitnesstp;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import java.net.URL;
import java.util.Objects;

public class HomepageController {
    Scene sceneAct;
    Pane background;
    Rectangle siteBar = new Rectangle();
    Rectangle mainView = new Rectangle();

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

        background.getChildren().addAll(siteBar, mainView, slider1, slider2, slider3, slider4);

        createButtons();
    }

    public void createButtons() {
        int amountPictures = 4;

        //Images Importieren
        Image a = new Image("/images/platzhalterKlein.png"); //wird einmal als Image abgespeichert, damit man auf die Höhe und breite zugreifen kann
        ImageView imageView1 = new ImageView(new Image("/images/platzhalterKlein.png"));
        ImageView imageView2 = new ImageView(new Image("/images/platzhalterKlein.png"));
        ImageView imageView3 = new ImageView(new Image("/images/platzhalterKlein.png"));
        ImageView imageView4 = new ImageView(new Image("/images/platzhalterKlein.png"));

        //Abstand zwischen Bildern, bzw zwischen oben und unten berechnen
        double distancePicToPic = (siteBar.getHeight()-a.getHeight()*amountPictures)/amountPictures;

        //Neue VBox mittig über das Rectangle, damit die Bilder in der Mitte angezeigt werden
        VBox buttonbox = new VBox();
        buttonbox.setLayoutX(siteBar.getX() + ((siteBar.getWidth()-a.getWidth())/2));
        buttonbox.setLayoutY(siteBar.getX() + ((siteBar.getWidth()-a.getWidth())/2));

        buttonbox.setSpacing(distancePicToPic);

        buttonbox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);

        background.getChildren().addAll(buttonbox);
    }
}