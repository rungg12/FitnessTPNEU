package com.example.fitnesstp;

import java.io.Serializable;


/**
 * User - Class
 * @
 */

public class User implements Serializable {
    private String name;
    private final String password;

    public User(String username, String password){
        this.password = password;
        this.name = username;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

}
