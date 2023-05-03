package com.example.fitnesstp;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class HomepageController {
    Scene sceneAct;
    Pane background;

    public HomepageController(Scene scene, Pane root) {
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Homestyle.css")).toExternalForm());

        sceneAct = scene;
        background = root;
        background.getChildren().removeAll();
        background.setId("background");

        Rectangle siteBar = new Rectangle();
        siteBar.setX(sceneAct.getWidth() * 0.0156);
        siteBar.setY(sceneAct.getHeight() * 0.0278);
        siteBar.setWidth(sceneAct.getWidth() * 0.1);
        siteBar.setHeight(sceneAct.getHeight() * 0.95);
        siteBar.setId("RectPurple");

        Rectangle mainView = new Rectangle();
        mainView.setX(siteBar.getX() + siteBar.getWidth() + sceneAct.getWidth() * 0.04);
        mainView.setY(sceneAct.getHeight() * 0.0278);
        mainView.setWidth(sceneAct.getWidth() - mainView.getX() - sceneAct.getWidth() * 0.02);
        mainView.setHeight(sceneAct.getHeight() * 0.5);
        mainView.setId("RectLightpurple");

        Rectangle slider1 = new Rectangle();
        slider1.setX(siteBar.getX() + siteBar.getWidth() + sceneAct.getWidth() * 0.04);
        slider1.setY(mainView.getY() + mainView.getHeight() + sceneAct.getHeight() * 0.04);
        slider1.setWidth(sceneAct.getWidth() * 0.156);
        slider1.setHeight(sceneAct.getHeight() * 0.278);
        slider1.setId("slider");

        Rectangle slider2 = new Rectangle();
        slider2.setX(siteBar.getX() + siteBar.getWidth() + slider1.getWidth() + sceneAct.getWidth() * 0.04 * 2);
        slider2.setY(mainView.getY() + mainView.getHeight() + sceneAct.getHeight() * 0.04);
        slider2.setWidth(sceneAct.getWidth() * 0.156);
        slider2.setHeight(sceneAct.getHeight() * 0.278);
        slider2.setId("slider");

        background.getChildren().addAll(siteBar, mainView, slider1, slider2);
    }
}

