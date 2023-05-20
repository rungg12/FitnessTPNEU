package com.example.fitnesstp;

import com.google.gson.Gson;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.URISyntaxException;
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
        //setupScrollPane();
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
        rightSide.setLayoutX(350.0);
        rightSide.setLayoutY(10.0);

        previousExercise.setPrefHeight(60);
        previousExercise.setPrefWidth(570);
        previousExercise.setLayoutY(620);
        previousExercise.setLayoutX(10);

        nextExercise.setPrefWidth(570);
        nextExercise.setPrefHeight(60);
        nextExercise.setLayoutX(590);
        nextExercise.setLayoutY(620);

        exerciseDescription.setPrefWidth(previousExercise.getPrefWidth() + nextExercise.getWidth() + 10);
        exerciseDescription.setPrefHeight(300);
        exerciseDescription.setLayoutX(10);
        exerciseDescription.setLayoutX(scene.getHeight() - exerciseDescription.getHeight() - 10);
/*
        AnchorPane.setBottomAnchor(exerciseDescription, 10.0);
        AnchorPane.setLeftAnchor(exerciseDescription, 10.0);
        AnchorPane.setRightAnchor(exerciseDescription, 10.0);
        AnchorPane.setTopAnchor(exerciseDescription, 690.0);

 */

        AnchorPane.setTopAnchor(videoPlayer, 0.0);
        AnchorPane.setLeftAnchor(videoPlayer, 10.0);
        videoPlayer.setFitWidth(1540.0);
        videoPlayer.setFitHeight(590.0);
        setExerciseVideo();

        scrollPane.setLayoutX(10.0);
        scrollPane.setLayoutY(10.0);
        scrollPane.setPrefHeight(1060.0);
        scrollPane.setPrefWidth(330.0);

        testRec.setHeight(100);
        testRec.setWidth(330);


        System.out.println(scene.getHeight() + " " + scene.getWidth());
    }

    private void setExerciseVideo(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(new File(Objects.requireNonNull(getClass().getResource("/Exercises/test.json")).toURI()))){
            exercise = gson.fromJson(reader, Exercise.class);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            exerciseVideo = new Media(Objects.requireNonNull(getClass().getResource("/Videos/Test.mp4")).toExternalForm());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer(exerciseVideo);
        videoPlayer = new MediaView();
        videoPlayer.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }

    private void setupScrollPane(){

    }

    private void addExerciseToList(){

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        content.getChildren().addAll(testRec);
        testRec.setId("RectLightpurple");
        testRec.setLayoutX(5);
        testRec.setLayoutY(5);

        scrollPane.setContent(content);

    }

}
