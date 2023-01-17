package com.pong_game;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsMenu extends GUI{

    public AnchorPane root;

    public Scene createSettingsMenu(Scene scene, Stage stage) {
        AnchorPane root = new AnchorPane();
        this.root = root;
        // add elements to root
        Scene settingsScene = new Scene(root, Color.LIGHTGREY);

        createSettingsButton(scene, stage, root);
        createSaveButton();
        createSettingsLabel(root);
        createAllText();
        createAllChoiceBox();
        createAllColorPicker();

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

    public void createSaveButton() {
        Button save = new Button("Save");
        save.setLayoutX(574);
        save.setLayoutY(655);
        root.getChildren().add(save);
    }

    public void createSettingsLabel(AnchorPane root) {
        Label label = new Label("Settings");
        label.setLayoutX(530);
        label.setLayoutY(112);
        label.setFont(Font.font("Veranda", 36));
        root.getChildren().add(label);
    }

    public void createTextNode(String text, int x, int y, int font) {
        Text textIn = new Text(x, y, text);
        textIn.setFont(Font.font("Veranda", font));
        root.getChildren().add(textIn);
    }

    public void createAllText() {
        createTextNode("Preset Difficulty", 531, 222, 18);
        createTextNode("Custom Settings", 528, 329, 18);
        createTextNode("Play Until Score", 497, 381, 18);
        createTextNode("Slider 1 Color", 464, 478, 14);
        createTextNode("Slider 2 Color", 464, 503, 14);
        createTextNode("Ball Color", 476, 528, 14);
        createTextNode("Background Color", 450, 555, 14);
        createTextNode("Slider 1 Movement Speed", 730, 478, 14);
        createTextNode("Slider 2 Movement Speed", 730, 504, 14);
        createTextNode("Ball Movement Speed", 742, 528, 14);
        createTextNode("Slider 1 Size", 157, 478, 14);
        createTextNode("Slider 2 Size", 157, 502, 14);
        createTextNode("Ball Size", 169, 527, 14);
    }

    public void createChoiceBox(int x, int y, int width) {
        ChoiceBox box = new ChoiceBox<>();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setPrefWidth(width);
        root.getChildren().add(box);
    }

    public void createAllChoiceBox() {
        createChoiceBox(519, 234, 150);
        createChoiceBox(917, 460, 150);
        createChoiceBox(917, 486, 150);
        createChoiceBox(917, 510, 150);
        createChoiceBox(258, 460, 150);
        createChoiceBox(258, 485, 150);
        createChoiceBox(258, 510, 150);
    }

    public void createColorPicker(int x, int y) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setLayoutX(x);
        colorPicker.setLayoutY(y);
        root.getChildren().add(colorPicker);
    }

    public void createAllColorPicker() {
        createColorPicker(579, 460);
        createColorPicker(579, 486);
        createColorPicker(579, 511);
        createColorPicker(579, 537);
    }
}
