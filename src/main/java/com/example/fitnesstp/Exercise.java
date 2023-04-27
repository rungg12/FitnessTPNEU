package com.example.fitnesstp;

import javafx.scene.media.*;


public class Exercise {
    private final String name;
    private final Media exampleVideo;

    public Exercise(String name, Media exampleVideo) {
        this.name = name;
        this.exampleVideo = exampleVideo;
    }

    public Media getExampleVideo(){
        return this.exampleVideo;
    }
    public String getName(){
        return this.name;
    }
}
