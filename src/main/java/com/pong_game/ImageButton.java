package com.pong_game;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton {

    private final String normal = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String pressed = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";

    public ImageButton(Image originalImage, double h, double w, Button sett) {

        ImageView image = new ImageView(originalImage);
        image.setFitHeight(h);
        image.setFitHeight(w);
        image.setPreserveRatio(true);
        sett.setGraphic(image);
        sett.setStyle(normal);

        sett.setOnMousePressed(event -> sett.setStyle(pressed));
        sett.setOnMouseReleased(event -> sett.setStyle(normal));
    }

}
