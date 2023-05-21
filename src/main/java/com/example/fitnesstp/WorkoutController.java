package com.example.fitnesstp;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class WorkoutController {
    private final Pane root;

    private Pane rightSide;
    private Button previousExercise;
    private Button nextExercise;
    private TextArea exerciseDescription;

    private Text exerciseName;

    private MediaView videoPlayer;
    private Media exerciseVideo;

    private ScrollPane scrollPane;
    private VBox content;

    private Exercise exercise;
    private Rectangle testRec;

    public WorkoutController(Pane root, HomepageController homepageController){
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/Homestyle.css")).toExternalForm());

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
        exerciseName = new Text();
        videoPlayer = new MediaView();
        scrollPane = new ScrollPane();
        content = new VBox();

        rightSide.getChildren().addAll(previousExercise, exerciseDescription, videoPlayer, nextExercise);
        root.getChildren().addAll(rightSide, scrollPane);

        testRec = new Rectangle();
        nextExercise.setOnAction(actionEvent -> setExerciseVideo());
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

        HBox buttons = new HBox(20, exerciseName,previousExercise, nextExercise);
        buttons.setLayoutX(20);
        buttons.setLayoutY(620);
        buttons.setSpacing(30);


        previousExercise.setPrefHeight(60);
        previousExercise.setPrefWidth(1000);
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
/*
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

 */

    private void setExerciseVideo() {
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
        exerciseName.setText(exercise.getName());
        exerciseName.setFont(Font.font("Montserrat", FontWeight.BOLD, 40));
        exerciseName.setFill(Color.WHITE);
        exerciseName.setTextAlignment(TextAlignment.CENTER);

        if (exerciseVideo != null) {
            MediaPlayer mediaPlayer = new MediaPlayer(exerciseVideo);
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