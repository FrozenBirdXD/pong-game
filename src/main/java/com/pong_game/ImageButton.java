package com.pong_game;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends GUI {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 2, 2, 2, 2;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 3 1 1 3;";

    public ImageButton(Image originalImage, double h, double w, Button sett) {

        ImageView image = new ImageView(originalImage);
        image.setFitHeight(h);
        image.setFitHeight(w);
        image.setPreserveRatio(true);
        sett.setGraphic(image);
        sett.setStyle(STYLE_NORMAL);

        sett.setOnMousePressed(event -> sett.setStyle(STYLE_PRESSED));
        sett.setOnMouseReleased(event -> sett.setStyle(STYLE_NORMAL));
    }

}
