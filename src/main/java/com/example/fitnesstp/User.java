package com.example.fitnesstp;

import java.util.List;

/**
 * User - Class
 * @
 */

public class User {
    private String name;
    private String password;
    private int age;
    private Double height;
    private Double weight;
    private char gender;
    private List<Workout> workouts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workoutToAdd){
        workouts.add(workoutToAdd);
    }

    public void removeWorkout(Workout workoutToRemove){
        workouts.remove(workoutToRemove);
    }
}
