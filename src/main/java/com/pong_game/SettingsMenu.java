package com.pong_game;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingsMenu extends GUI{

    public Scene createSettingsMenu(Scene scene, Stage stage) {
        AnchorPane root = new AnchorPane();
        // add elements to root
        Scene settingsScene = new Scene(root, Color.LIGHTGREY);

        createSettingsButton(scene, stage, root);
        createSettingsLabel(root);

        return settingsScene;
    }

    public void createSettingsButton(Scene scene, Stage stage, AnchorPane root) {
        Button settings = new Button("");
        settings.setLayoutX(10);    
        settings.setLayoutY(5);
        settings.setOnAction(event -> {
            stage.setScene(scene);
            stage.show();
        });
        // creates an image with the settings gear
        Image settingsIcon = new Image("settingsIcon.png");

        // adds the settings gear as the icon for the settings button
        ImageButton sett = new ImageButton(settingsIcon, 25, 25, settings);

        root.getChildren().add(settings);
    }

    public void createSettingsLabel(AnchorPane root) {
        Label label = new Label("Settings");
        label.setLayoutX(536);
        label.setLayoutY(214);
        label.setFont(Font.font("Veranda", 36));
        root.getChildren().add(label);
    }
}
