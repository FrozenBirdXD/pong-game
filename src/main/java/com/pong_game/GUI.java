package com.pong_game;

import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GUI extends Application {

    public boolean paused = true;

    public Controller controller;
    public SettingsMenu settingsMenu;
    public Stage stage;
    public Scene scene;
    public Circle ball;
    public Text instructions;
    public Set<KeyCode> keyPressed;

    public static void main(String[] args) {
        // long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        // System.out.println(beforeUsedMem);
        launch(args);   // launches start method
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
        // creates root node and scene 
        AnchorPane root = new AnchorPane();
        this.scene = new Scene(root, Color.LIGHTGRAY);

        this.stage = stage;
        this.ball = createBall();
        this.instructions = createInstructions();

        Rectangle slider1 = createSlider1();
        Rectangle slider2 = createSlider2();
        
        Text scorePlayer1Text = createScorePlayer1Text();
        Text scorePlayer2Text = createScorePlayer2Text();
        Text winner = createWinnerText();
        Text colon = createColon();

        Button settings = createSettingsButton();
        Button pause = createPauseButton();
        Button restartButton = createRestartButton();
        this.settingsMenu = new SettingsMenu();

        configsStage();
        sceneHandlers();

        // create a set that stores all of the keys that are pressed at any moment (thread-safe collection and cannot contain duplicates)
        this.keyPressed = new HashSet<KeyCode>();

        // creates controller object and starts the game
        this.controller = new Controller(ball, slider1, slider2, scorePlayer1Text, scorePlayer2Text, winner, keyPressed, instructions, restartButton);
        controller.startGame();

        // adds all the node to the AnchorPane
        root.getChildren().addAll(ball, slider1, slider2, scorePlayer1Text, scorePlayer2Text, colon, winner, instructions, settings, pause, restartButton);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Rectangle createSlider1() {
        // constructor: LeftCornerX, LeftCornerY, Width, Height
        Rectangle slider1 = new Rectangle(50, 330, 20, 140);
        slider1.setArcHeight(5.0);
        slider1.setArcWidth(5.0);
        slider1.setFill(Color.rgb(127, 144, 255));
        slider1.setStroke(Color.BLACK);
        slider1.setStrokeType(StrokeType.INSIDE);
        return slider1;
    }

    public Rectangle createSlider2() {
        // constructor: LeftCornerX, LeftCornerY, Width, Height
        Rectangle slider2 = new Rectangle(1130, 330, 20, 140);
        slider2.setArcHeight(5.0);
        slider2.setArcWidth(5.0);
        slider2.setFill(Color.rgb(127, 144, 255));
        slider2.setStroke(Color.BLACK);
        slider2.setStrokeType(StrokeType.INSIDE);
        return slider2;
    }

    public Text createScorePlayer1Text() {
        Text scorePlayer1Text = new Text(485, 98, "0");
        scorePlayer1Text.setFont(Font.font("Veranda", 60));
        scorePlayer1Text.setTextAlignment(TextAlignment.CENTER);
        scorePlayer1Text.setWrappingWidth(158.9366455078125);
        scorePlayer1Text.setOpacity(0.7);
        return scorePlayer1Text;
    }

    public Text createScorePlayer2Text() {
        Text scorePlayer2Text = new Text(554, 98, "0");
        scorePlayer2Text.setFont(Font.font("Veranda", 60));
        scorePlayer2Text.setTextAlignment(TextAlignment.CENTER);
        scorePlayer2Text.setWrappingWidth(158.9366455078125);
        scorePlayer2Text.setOpacity(0.7);
        return scorePlayer2Text;
    }

    public Button createRestartButton() {
        Button restartButton = new Button("New Game");
        restartButton.setFont(Font.font("Veranda", 18));
        restartButton.setLayoutX(544);
        restartButton.setLayoutY(387);
        restartButton.setVisible(false);
        restartButton.setOnAction(event -> {
            controller.restartButton();
        });
        return restartButton;
    }

    public Button createSettingsButton() {
        // adds a settings button
        Button settings = new Button("");
        settings.setLayoutX(10);    
        settings.setLayoutY(5);
        settings.setOnAction(event -> {
            paused = true;
            controller.setPaused(paused);
            stage.setScene(settingsMenu.createSettingsMenu(scene, stage));
            stage.show();
        });

        // adds the settingsgear as the button icon
        Image settingsIcon = new Image("settingsIcon.png");
        new ImageButton(settingsIcon, 25, 25, settings);
        return settings;
    }

    public void configsStage() {
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setWidth(1210);
        stage.setHeight(800);
        stage.show();

        Image icon = new Image("pongIcon.png");
        stage.getIcons().add(icon);
    }

    public Button createPauseButton() {
        // adds a pause button
        Button pause = new Button("");
        pause.setLayoutX(40);   
        pause.setLayoutY(-7);
        pause.setOnAction(event -> {
            paused = !paused;
            controller.setPaused(paused);
            instructions.setOpacity(0);
            ball.setOpacity(1);
        });

        // sets the pauseIcon as the icon for the pause button
        Image pauseIcon = new Image("pauseIcon.png");
        new ImageButton(pauseIcon, 50, 50, pause);
        return pause;
    }

    public Circle createBall() {
        // constructor: CenterX, CenterY, Radius, Color
        Circle ball = new Circle(600, 350, 20, Color.rgb(212, 130, 47));
        ball.setOpacity(0);
        return ball;
    }

    public Text createInstructions() {
        // constructor: OriginX, OriginY, "Text"
        Text instructions = new Text(524, 415, "     Instructions:\nP:                          Pause the game\nW/S:                     Move slider1\nnum8/num5:        Move slider2\n\n=> Press P to start the game!");
        instructions.setFont(Font.font("Veranda", 14));
        return instructions;
    }

    public void sceneHandlers() {
        // adds an event handler to the scene to listen for keys pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                
                if (key.getCode().equals(KeyCode.P)) {
                    paused = !paused;
                    controller.setPaused(paused);
                    instructions.setOpacity(0);
                    ball.setOpacity(1);
                } else {
                    keyPressed.add(key.getCode());
                }
            }
        });

        // adds an event handler to the scene to listen for keys released
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                keyPressed.remove(key.getCode());
            }
        });   

        // adds an event filter so that space and enter don't active the settings button
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.SPACE)) {
                event.consume();
            }
        });
    }

    public Text createWinnerText() {
        Text winner = new Text(480, 486, "");
        winner.setTextAlignment(TextAlignment.CENTER);  
        winner.setFont(Font.font("Veranda", 40));
        return winner;
    }

    public Text createColon() {
        Text colon = new Text(592, 98, ":");
        colon.setFont(Font.font("Veranda", 60));
        colon.setOpacity(0.7);
        return colon;
    }
}