package com.example.fitnesstp;

import java.util.Date;
import java.util.List;

public class Workout {
    User authorOfWorkout;
    Date created;
    List<Exercise> exercises;

    Workout(User author, Date created){
        this.authorOfWorkout = author;
        this.created = created;
    }

    public User getAuthorOfWorkout() {
        return authorOfWorkout;
    }

    public Date getCreated() {
        return created;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void addExercicse(Exercise exerciseToAdd){
        exercises.add(exerciseToAdd);
    }

    public void removeExercise(Exercise exerciseToRemove){
        exercises.remove(exerciseToRemove);
    }
}
