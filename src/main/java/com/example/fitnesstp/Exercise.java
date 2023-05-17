package com.example.fitnesstp;

import java.io.Serial;
import java.io.Serializable;


public class Exercise implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    private final String name;
    private final String description;

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
}
