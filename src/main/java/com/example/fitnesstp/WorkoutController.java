package com.example.fitnesstp;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.media.MediaPlayer;

public class WorkoutController {
    private final Workout workout;
    private MediaPlayer mediaPlayer;
    private ScrollPane scrollPane;
    private Button previousExercise;
    private Button nextExercise;
    private Exercise currentExercise;
    public WorkoutController(Workout workout){
        this.workout = workout;
    }

    private void createObjects(){
        currentExercise = workout.getExercises().get(0);
        mediaPlayer = new MediaPlayer(currentExercise.getExampleVideo());
        scrollPane = new ScrollPane();
        previousExercise = new Button("←");
        nextExercise = new Button("→");
    }


}
