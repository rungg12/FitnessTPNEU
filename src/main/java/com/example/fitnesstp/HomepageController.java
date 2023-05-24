package com.example.fitnesstp;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import java.util.Random;

import static com.example.fitnesstp.WorkoutController.getNumberOfFilesInFolder;

/**
 * Der HomepageController ist für die Steuerung der Homepage-Funktionalitäten zuständig.
 * Er verwaltet die Anzeige von Übungsinformationen, Schaltflächen und Aktionen, die auf der Homepage ausgeführt werden können.
 * @author Laurin Rungg, Matthias Zelger
 */

public class HomepageController {
    Scene sceneAct;
    Pane background;
    Rectangle siteBar = new Rectangle();
    Rectangle mainView = new Rectangle();
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    WorkoutTimer workoutTimer;
    AddExercise addExercise;

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


        Exercise ex1 = getExercise();
        Text title1 = new Text(ex1.getName());
        title1.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        title1.setFill(Color.BLACK);
        title1.setTextAlignment(TextAlignment.CENTER);
        title1.setX(slider1.getX());
        title1.setY(slider1.getY() + title1.getLayoutBounds().getHeight() + slider1.getHeight()*0.1);
        title1.setWrappingWidth(slider1.getWidth());

        TextArea description1 = new TextArea(ex1.getDescription());
        description1.setPrefWidth(slider1.getWidth());
        description1.setPrefHeight(slider1.getHeight() * 0.6);
        description1.setLayoutX(slider1.getX());
        description1.setLayoutY(title1.getY() + title1.getLayoutBounds().getHeight() + slider1.getHeight() * 0.1);
        description1.setWrapText(true);
        description1.setFont(Font.font("Montserrat", FontWeight.BOLD, 15));
        description1.setEditable(false);


        Exercise ex2 = getExercise();
        Text title2 = new Text(ex2.getName());
        title2.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        title2.setFill(Color.BLACK);
        title2.setTextAlignment(TextAlignment.CENTER);
        title2.setX(slider2.getX());
        title2.setY(slider2.getY() + title2.getLayoutBounds().getHeight() + slider2.getHeight()*0.1);
        title2.setWrappingWidth(slider2.getWidth());

        TextArea description2 = new TextArea(ex2.getDescription());
        description2.setPrefWidth(slider2.getWidth());
        description2.setPrefHeight(slider2.getHeight() * 0.6);
        description2.setLayoutX(slider2.getX());
        description2.setLayoutY(title2.getY() + title2.getLayoutBounds().getHeight() + slider2.getHeight() * 0.1);
        description2.setWrapText(true);
        description2.setFont(Font.font("Montserrat", FontWeight.BOLD, 15));
        description2.setEditable(false);


        Exercise ex3 = getExercise();
        Text title3 = new Text(ex3.getName());
        title3.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        title3.setFill(Color.BLACK);
        title3.setTextAlignment(TextAlignment.CENTER);
        title3.setX(slider3.getX());
        title3.setY(slider3.getY() + title3.getLayoutBounds().getHeight() + slider3.getHeight()*0.1);
        title3.setWrappingWidth(slider1.getWidth());

        TextArea description3 = new TextArea(ex3.getDescription());
        description3.setPrefWidth(slider3.getWidth());
        description3.setPrefHeight(slider3.getHeight() * 0.6);
        description3.setLayoutX(slider3.getX());
        description3.setLayoutY(title3.getY() + title3.getLayoutBounds().getHeight() + slider3.getHeight() * 0.1);
        description3.setWrapText(true);
        description3.setFont(Font.font("Montserrat", FontWeight.BOLD, 15));
        description3.setEditable(false);

        Exercise ex4 = getExercise();
        Text title4 = new Text(ex4.getName());
        title4.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        title4.setFill(Color.BLACK);
        title4.setTextAlignment(TextAlignment.CENTER);
        title4.setX(slider4.getX());
        title4.setY(slider4.getY() + title4.getLayoutBounds().getHeight() + slider4.getHeight()*0.1);
        title4.setWrappingWidth(slider4.getWidth());

        TextArea description4 = new TextArea(ex4.getDescription());
        description4.setPrefWidth(slider4.getWidth());
        description4.setPrefHeight(slider4.getHeight() * 0.6);
        description4.setLayoutX(slider4.getX());
        description4.setLayoutY(title4.getY() + title4.getLayoutBounds().getHeight() + slider4.getHeight() * 0.1);
        description4.setWrapText(true);
        description4.setFont(Font.font("Montserrat", FontWeight.BOLD, 15));
        description4.setEditable(false);

        Text nameText = new Text("Fitness Fiesta");
        nameText.setFont(Font.font("Montserrat", FontWeight.BOLD, 160));
        nameText.setFill(Color.WHITE);
        nameText.setTextAlignment(TextAlignment.CENTER);
        nameText.setX(mainView.getX());
        nameText.setY(mainView.getY() + mainView.getHeight() / 2);
        nameText.setWrappingWidth(mainView.getWidth());

        background.getChildren().addAll(siteBar, mainView, slider1, slider2, slider3, slider4, nameText, title1, description1, title2, description2, title3, description3, title4, description4);

        createButtons();
    }

    public Exercise getExercise(){
        Gson gson = new Gson();
        Exercise exercise = null;
        Random random = new Random();

        int fileNumber = random.nextInt(getNumberOfFilesInFolder()) + 1;

        try (FileReader reader = new FileReader(new File(Objects.requireNonNull(getClass().getResource("/Exercises/" + fileNumber + ".json")).toURI()))) {
            exercise = gson.fromJson(reader, Exercise.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exercise;
    }



    public void createButtons() {
        int amountPictures = 4;

        //Images Importieren
        Image a = new Image("/images/AddWorkoutSymbol100x100.png"); //wird einmal als Image abgespeichert, damit man auf die Höhe und breite zugreifen kann
        imageView1 = new ImageView(new Image("/images/AddWorkoutSymbol100x100.png"));
        imageView2 = new ImageView(new Image("/images/WorkoutBegin100x100.png"));
        imageView3 = new ImageView(new Image("/images/timer100x100.png"));
        imageView4 = new ImageView(new Image("/images/exit100x100.png"));

        //Abstand zwischen Bildern, bzw zwischen oben und unten berechnen
        double distancePicToPic = (siteBar.getHeight()-a.getHeight()*amountPictures-1)/amountPictures;

        //Neue VBox mittig über das Rectangle, damit die Bilder in der Mitte angezeigt werden
        VBox buttonbox = new VBox();
        buttonbox.setLayoutX(siteBar.getX() + (siteBar.getWidth()-a.getWidth())/2);
        buttonbox.setLayoutY(siteBar.getX() + ((siteBar.getWidth()-a.getWidth())/2));

        buttonbox.setSpacing(distancePicToPic);

        buttonbox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);

        Text text1 = new Text("Add Workout");
        text1.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        text1.setFill(Color.WHITE);
        text1.setX(imageView1.getX()+200);
        text1.setY(imageView1.getY() + imageView1.getFitHeight() * 0.1);

        background.getChildren().addAll(buttonbox, text1);

        //Listener fuer das Klicken auf die Symbole
        imageView1.setOnMouseClicked(event -> addExercise =  new AddExercise(background, HomepageController.this));


        imageView2.setOnMouseClicked(event -> new WorkoutController(background, HomepageController.this));


        imageView3.setOnMouseClicked(event -> workoutTimer = new WorkoutTimer(background, this));

        imageView4.setOnMouseClicked(event -> Platform.exit());
    }
}