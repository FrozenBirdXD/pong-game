package com.pong_game;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsMenu {
    
    public AnchorPane root;
    public SaveSettings properties;
    public Circle ball;
    public Rectangle slider1;
    public Rectangle slider2;
    
    public Scene createSettingsMenu(Scene scene, Stage stage, Circle ball, Rectangle slider1, Rectangle slider2) {
        this.ball = ball;
        this.slider1 = slider1;
        this.slider2 = slider2;
        AnchorPane root = new AnchorPane();
        this.root = root;
        // add elements to root
        Scene settingsScene = new Scene(root, Color.LIGHTGREY);
        
        createSettingsButton(scene, stage, root);
        createSaveButton();
        createResetButton();
        createSettingsLabel();
        createAllText();
        ChoiceBox presetDiff = createChoiceBox(539, 234, 100);
        ColorPicker slider1Color = createColorPicker(579, 460);
        ColorPicker slider2Color = createColorPicker(579, 486);
        ColorPicker ballColor = createColorPicker(579, 511);
        Slider slider1Size = createSettingsSlider(262, 465);
        Slider slider2Size = createSettingsSlider(262, 491);
        Slider ballSize = createSettingsSlider(262, 516);
        Slider slider1Speed = createSettingsSlider(910, 465);
        Slider slider2Speed = createSettingsSlider(910, 491);
        Slider ballSpeed = createSettingsSlider(910, 516);
        ChoiceBox playUntilInput = createChoiceBoxPlayUntil(628, 362, 15);

        this.properties = new SaveSettings(presetDiff, slider1Color, slider2Color, ballColor, slider1Size, slider2Size, ballSize, slider1Speed, slider2Speed, ballSpeed, playUntilInput);
        
        File file = new File("config.properties");
        // if the config.properties file exists but is empty, then the file will be filled with the default settings
        if (file.exists() && file.isFile()) {
            if (file.length() == 0) {
                properties.setDefaultSettings();
            }
        // if the config.properties file does not exist one will be created with the default settings
        } else if (!file.exists()) {
            properties.setDefaultSettings();
        }
        // reads the settings from the config.properties file
        properties.readSettings();
        
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
        // adds the settingsgear as the button icon
        Image settingsIcon = new Image("settingsIcon.png");
        new ImageButton(settingsIcon, 25, 25, settings);
        
        root.getChildren().add(settings);
    }
    
    public void createSettingsLabel() {
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

    public Slider createSettingsSlider(int x, int y) {
        Slider slider = new Slider();
        slider.setLayoutX(x);
        slider.setLayoutY(y);
        root.getChildren().add(slider);
        return slider;
    }
    
    public void createAllText() {
        createTextNode("Preset Difficulty", 531, 222, 18);
        createTextNode("Custom Settings", 528, 329, 18);
        createTextNode("Play Until Score", 497, 381, 18);
        createTextNode("Slider 1 Color", 464, 478, 14);
        createTextNode("Slider 2 Color", 464, 503, 14);
        createTextNode("Ball Color", 476, 528, 14);
        createTextNode("Slider 1 Movement Speed", 730, 478, 14);
        createTextNode("Slider 2 Movement Speed", 730, 504, 14);
        createTextNode("Ball Movement Speed", 742, 528, 14);
        createTextNode("Slider 1 Size", 157, 478, 14);
        createTextNode("Slider 2 Size", 157, 502, 14);
        createTextNode("Ball Size", 169, 527, 14);
    }
    
    public ChoiceBox createChoiceBox(int x, int y, int width) {
        ChoiceBox box = new ChoiceBox<>();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setPrefWidth(width);
        box.getItems().addAll("Easy", "Medium", "HARD");
        root.getChildren().add(box);
        return box;
    }
    
    public ColorPicker createColorPicker(int x, int y) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setLayoutX(x);
        colorPicker.setLayoutY(y);
        root.getChildren().add(colorPicker);
        return colorPicker;
    }
    
    public ChoiceBox createChoiceBoxPlayUntil(int x, int y, int width) {
        ChoiceBox box = new ChoiceBox<>();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setPrefWidth(width);
        box.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        root.getChildren().add(box);
        return box;
    }

    public void createSaveButton() {
        Button save = new Button("Save");
        save.setLayoutX(554);
        save.setLayoutY(655);
        save.setOnAction(event -> {
            properties.saveSettings();
            applySettings();
        });
        root.getChildren().add(save);
    }

    // resets all of the users settings to the default settings
    public void createResetButton() {
        Button resetSettings = new Button("Reset");
        resetSettings.setLayoutX(600);
        resetSettings.setLayoutY(655);
        resetSettings.setOnAction(event -> {
            properties.setDefaultSettings();
            properties.readSettings();
            applySettings();
        });
        root.getChildren().add(resetSettings);
    }

    public void applySettings() {
        properties.setBallColor(ball);
        properties.setSlider1Color(slider1);
        properties.setSlider2Color(slider2);
    }
}
