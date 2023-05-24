package com.example.fitnesstp;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.util.Objects;
import java.util.Random;


/**
 * Die WorkoutController-Klasse implementiert den Controller für die Workout-Anwendung.
 * @author David Erlacher, Laurin Rungg, Dominik Puner
 */

public class WorkoutController {
    private final Pane root;

    private Button nextExercise;
    private TextArea exerciseDescription;

    private Text exerciseName;

    private MediaView videoPlayer;
    private Media exerciseVideo;
    private MediaPlayer mediaPlayer;

    private Exercise exercise;


    /**
     * Erstellt eine neue Instanz des WorkoutControllers.
     *
     * @param root                Die Wurzel-Pane, auf der die Workout-Ansicht angezeigt wird.
     * @param homepageController Der Homepage-Controller, um zur Startseite zurückzukehren.
     */
    public WorkoutController(Pane root, HomepageController homepageController){
        root.getStylesheets().clear();
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/WorkoutStyle.css")).toExternalForm());

        this.root = root;
        this.root.setId("BackgroundPane");
        root.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.ESCAPE) {
                homepageController.createObjects();
            }
        });

        root.getChildren().clear();
        createObjects();
        setPositionsAndId();
    }

    private void createObjects(){
        nextExercise = new Button("next exercise");
        exerciseDescription = new TextArea();
        exerciseName = new Text();
        videoPlayer = new MediaView();

        root.getChildren().addAll(exerciseDescription, videoPlayer, nextExercise);

        nextExercise.setOnAction(actionEvent -> setExerciseVideo());
    }



    private void setPositionsAndId(){
        videoPlayer.setX(root.getWidth() * 0.0052083333333333);
        videoPlayer.setY(root.getHeight() * 0.0185185185185185);
        videoPlayer.setFitWidth(root.getWidth() * 0.8020833333333333);
        videoPlayer.setFitHeight(root.getHeight() * 0.5462962962962963);

        HBox buttons = new HBox(root.getWidth() * 0.0104166666666667, nextExercise, exerciseName);
        buttons.setLayoutX(root.getWidth() * 0.0104166666666667);
        buttons.setLayoutY(root.getHeight() * 0.65);
        buttons.setSpacing(root.getWidth() * 0.015625);

        nextExercise.setPrefWidth(root.getWidth() * 0.3489583333333333);
        nextExercise.setMinWidth(root.getWidth() * 0.3489583333333333);

        nextExercise.setPrefHeight(root.getHeight() * 0.0555555555555556);

        exerciseDescription.setPrefWidth(root.getWidth() * 0.9895833333333333);
        exerciseDescription.setMinWidth(Region.USE_PREF_SIZE);
        exerciseDescription.setPrefHeight(root.getHeight() * 0.2453703703703704);
        exerciseDescription.setLayoutX(root.getWidth() * 0.0052083333333333);
        exerciseDescription.setLayoutY(root.getHeight() * 0.745);
        root.getChildren().add(buttons);

        setExerciseVideo();
        exerciseDescription.setText(exercise.getDescription());
    }

    private void setExerciseVideo() {
        if(mediaPlayer != null){
            exerciseVideo = null;
            mediaPlayer.dispose();
            videoPlayer.setMediaPlayer(null);
        }
        Gson gson = new Gson();

        // Randomly select a file number
        Random random = new Random();
        int fileNumber = random.nextInt(getNumberOfFilesInFolder()) + 1;

        try (FileReader reader = new FileReader(new File(Objects.requireNonNull(getClass().getResource("/Exercises/" + fileNumber + ".json")).toURI()))) {
            exercise = gson.fromJson(reader, Exercise.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File videoFile = new File("src/main/resources/Videos/" + fileNumber + ".mp4");
            //um wartezeiten beim Klicken der Buttons zu verhindern
            if (videoFile.exists()) {
                exerciseVideo = new Media(videoFile.toURI().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        exerciseDescription.setWrapText(true);
        exerciseDescription.setFont(Font.font("Montserrat", FontWeight.BOLD, 25));
        exerciseDescription.setText(exercise.getDescription());
        exerciseDescription.setEditable(false);
        exerciseName.setText(exercise.getName());
        exerciseName.setFont(Font.font("Montserrat", FontWeight.BOLD, 40));
        exerciseName.setFill(Color.WHITE);
        exerciseName.setTextAlignment(TextAlignment.CENTER);

        if (exerciseVideo != null) {
            mediaPlayer = new MediaPlayer(exerciseVideo);
            mediaPlayer.setMute(true);
            videoPlayer.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

    public static int getNumberOfFilesInFolder() {
        String folderPath = "src/main/resources/Exercises";
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                int fileCount = 0;
                for (File file : files) {
                    if (file.isFile()) {
                        fileCount++;
                    }
                }
                return fileCount;
            }
        }
        return 0; //Falls der Ordner nicht exisitiert
    }

}