package com.example.fitnesstp;

import com.google.gson.Gson;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.Objects;

public class WorkoutController {
    private final Scene scene;
    private final Pane root;

    private Pane rightSide;
    private Button previousExercise;
    private Button nextExercise;
    private TextArea exerciseDescription;

    private MediaView videoPlayer;
    private MediaPlayer mediaPlayer;
    private Media exerciseVideo;

    private ScrollPane scrollPane;
    private VBox content;

    private Exercise exercise;
    private Rectangle testRec;

    public WorkoutController(Scene scene, Pane root, HomepageController homepageController){
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/Homestyle.css")).toExternalForm());

        this.scene = scene;
        this.root = root;

        root.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.ESCAPE) {
                homepageController.createObjects();
            }
        });

        root.getChildren().clear();
        createObjects();
        setPositionsAndId();
        addExerciseToList();
    }

    private void createObjects(){

        rightSide = new AnchorPane();
        previousExercise = new Button("previous exercise");
        nextExercise = new Button("next exercise");
        exerciseDescription = new TextArea();
        videoPlayer = new MediaView();
        scrollPane = new ScrollPane();
        content = new VBox();

        rightSide.getChildren().addAll(previousExercise, exerciseDescription, videoPlayer, nextExercise);
        root.getChildren().addAll(rightSide, scrollPane);

        testRec = new Rectangle();
    }



    private void setPositionsAndId(){

        scrollPane.setLayoutX(10.0);
        scrollPane.setLayoutY(10.0);
        scrollPane.setPrefHeight(1060.0);
        scrollPane.setPrefWidth(330.0);

        rightSide.setLayoutX(330.0);
        rightSide.setLayoutY(00.0);

        videoPlayer.setX(10);
        videoPlayer.setY(20);
        videoPlayer.setFitWidth(1540.0);
        videoPlayer.setFitHeight(590.0);

        HBox buttons = new HBox(20, previousExercise, nextExercise);
        buttons.setLayoutX(20);
        buttons.setLayoutY(620);
        previousExercise.setPrefHeight(60);
        previousExercise.setPrefWidth(670);
        nextExercise.setPrefWidth(670);
        nextExercise.setPrefHeight(60);

        exerciseDescription.setPrefWidth(1360);
        exerciseDescription.setPrefHeight(265);
        exerciseDescription.setLayoutX(20);
        exerciseDescription.setLayoutY(710);
        rightSide.getChildren().add(buttons);

        setExerciseVideo();
        exerciseDescription.setText(exercise.getDescription());
    }

    private void setExerciseVideo(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(new File(Objects.requireNonNull(getClass().getResource("/Exercises/Test.json")).toURI()))){
            exercise = gson.fromJson(reader, Exercise.class);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            File f = new File("src/main/resources/Videos/Test.mp4");
            exerciseVideo = new Media(f.toURI().toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer(exerciseVideo);
        videoPlayer.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    private void setupScrollPane(){

    }

    private void addExerciseToList(){
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        testRec.setWidth(200);
        testRec.setHeight(200);
        testRec.setStyle("-fx-background-color: red");

        content.getChildren().addAll(testRec);
        testRec.setId("RectLightpurple");
        testRec.setLayoutX(5);
        testRec.setLayoutY(5);

        scrollPane.setContent(content);
    }
}