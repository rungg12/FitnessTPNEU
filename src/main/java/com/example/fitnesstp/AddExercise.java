package com.example.fitnesstp;

import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;



/**

 Die Klasse AddExercise ist für das Hinzufügen einer neuen Übung zum Workout zuständig.

 Sie bietet Funktionen zum Erstellen und Speichern von Workout-Übungen mit einem Namen,

 einer Beschreibung und einer zugehörigen Videodatei.
 @author Laurin Rungg
 */
public class AddExercise {

    Pane root;
    HomepageController homepageController;

    public AddExercise(Pane root, HomepageController homepageController) {
        root.getStylesheets().clear();
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/UploadStyle.css")).toExternalForm());

        this.root = root;
        root.setId("BackgroundPane");
        this.homepageController = homepageController;

        root.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.ESCAPE) {
                homepageController.createObjects();
            }
        });

        createObjects();
    }

    public void createObjects() {
        root.getChildren().clear();

        Text titleText = new Text("Add Workout");
        titleText.setFont(Font.font("Montserrat", FontWeight.BOLD, 50));
        titleText.setFill(Color.WHITE);

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");

        TextField descriptionTextField = new TextField();
        descriptionTextField.setPromptText("Description");

        Button selectButton = new Button("Select Video");
        selectButton.setPrefWidth(root.getWidth());
        selectButton.setMinWidth(root.getWidth());
        selectButton.setOnAction(event -> {
            String name = String.valueOf(findNextAvailableNumber());
            selectVideo(name);
        });

        Button saveButton = new Button("Save Workout");
        saveButton.setPrefWidth(root.getWidth());
        saveButton.setMinWidth(root.getWidth());
        saveButton.setOnAction(event -> {
            String name = nameTextField.getText();
            String description = descriptionTextField.getText();

            saveWorkout(name, description);
        });

        VBox vbox = new VBox(10);
        vbox.setPrefWidth(root.getWidth());
        vbox.setPrefHeight(root.getWidth() / 2);
        vbox.setLayoutY(0);
        vbox.setLayoutX(0);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(titleText, nameTextField, descriptionTextField, selectButton, saveButton);
        root.getChildren().add(vbox);
    }

    private void selectVideo(String name) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Video File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String fileName = name + ".mp4";
            uploadVideo(selectedFile, fileName);
        }
    }

    private void uploadVideo(File videoFile, String fileName) {
        try {
            Path destination = Path.of("src/main/resources/Videos/" + fileName);
            Files.copy(videoFile.toPath(), destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveWorkout(String name, String description) {
        Exercise exercise = new Exercise(name, description);
        Gson gson = new Gson();

        int nextNumber = findNextAvailableNumber();
        String fileName = nextNumber + ".json";

        try (FileWriter writer = new FileWriter("src/main/resources/Exercises/" + fileName)) {
            gson.toJson(exercise, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findNextAvailableNumber() {
        int nextNumber = 1;
        File exercisesDir = new File("src/main/resources/Exercises");

        if (exercisesDir.exists() && exercisesDir.isDirectory()) {
            File[] files = exercisesDir.listFiles();

            if (files != null) {
                while (fileExists(nextNumber, files)) {
                    nextNumber++;
                }
            }
        }

        return nextNumber;
    }

    private boolean fileExists(int number, File[] files) {
        String fileName = number + ".json";

        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return true;
            }
        }

        return false;
    }
}
