package com.pong_game;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
	public Controller controller;
    
    public Scene createSettingsMenu(Scene scene, Stage stage, Circle ball, Rectangle slider1, Rectangle slider2, Controller controller) {
        this.ball = ball;
        this.slider1 = slider1;
        this.slider2 = slider2;
		// controller is added here to access the velocity variables
		this.controller = controller;

        AnchorPane root = new AnchorPane();
        this.root = root;
        // add elements to root
        Scene settingsScene = new Scene(root, Color.LIGHTGREY);
        
        // creates all of the input possibilities in settings
        createSettingsButton(scene, stage, root);
        createSaveButton();
        createResetButton();
        createSettingsLabel();
        createAllText();
        createRandomButtonColor();
        createRandomButtonSize();
        createRandomButtonSpeed();
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

        // gives these input possibilities to the SaveSettings class where everything is stored and processed
        this.properties = new SaveSettings(slider1Color, slider2Color, ballColor, slider1Size, slider2Size, ballSize, slider1Speed, slider2Speed, ballSpeed, playUntilInput);
        
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

    // creates moveable sliders for the user to customize the settings
    public Slider createSettingsSlider(int x, int y) {
        Slider slider = new Slider();
        slider.setLayoutX(x);
        slider.setLayoutY(y);
        root.getChildren().add(slider);
        return slider;
    }
    
    public void createAllText() {
        createTextNode("Random Settings", 531, 222, 18);
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
    
    public ColorPicker createColorPicker(int x, int y) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setLayoutX(x);
        colorPicker.setLayoutY(y);
        root.getChildren().add(colorPicker);
        return colorPicker;
    }

    // creates a choice box where the user can chose how many scores a player needs to win
    public ChoiceBox createChoiceBoxPlayUntil(int x, int y, int width) {
        ChoiceBox box = new ChoiceBox<>();
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setPrefWidth(width);
        box.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        root.getChildren().add(box);
        return box;
    }

    // saves all of the settings that were inputed by the user
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

    public void createRandomButtonColor() {
        Button resetSettings = new Button("Color");
        resetSettings.setLayoutX(568);
        resetSettings.setLayoutY(236);
        resetSettings.setOnAction(event -> {
            properties.randomizeColors();
            properties.readSettings();
            applySettings();
        });
        root.getChildren().add(resetSettings);
    }

    public void createRandomButtonSpeed() {
        Button resetSettings = new Button("Speed");
        resetSettings.setLayoutX(628);
        resetSettings.setLayoutY(236);
        resetSettings.setOnAction(event -> {
            properties.randomizeSpeed();
            properties.readSettings();
            applySettings();
        });
        root.getChildren().add(resetSettings);
    }

    public void createRandomButtonSize() {
        Button resetSettings = new Button("Size");
        resetSettings.setLayoutX(516);
        resetSettings.setLayoutY(236);
        resetSettings.setOnAction(event -> {
            properties.randomizeSize();
            properties.readSettings();
            applySettings();
        });
        root.getChildren().add(resetSettings);
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

    // applies the settings to the game nodes
    public void applySettings() {
        properties.setBallColor(ball);
        properties.setSlider1Color(slider1);
        properties.setSlider2Color(slider2);
		properties.setBallSize(ball);
		properties.setSlider1Size(slider1);
		properties.setSlider2Size(slider2);
		properties.setBallSpeed(controller);
        properties.setSlider1Speed(controller);
        properties.setSlider2Speed(controller);
        properties.setPlayUntil(controller);
    }
}
