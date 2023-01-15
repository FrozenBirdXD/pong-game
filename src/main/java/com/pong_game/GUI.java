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

    public static void main(String[] args) {
        // launches start method
        // long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        // System.out.println(beforeUsedMem);
        launch(args);  
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {

        // creates root node and scene 
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, Color.LIGHTGRAY);

        // adds pongIcon.png as the icon
        Image icon = new Image("pongIcon.png");
        stage.getIcons().add(icon);

        // creates a ball node
        // constructor: CenterX, CenterY, Radius, Color
        Circle ball = new Circle(600, 350, 20, Color.rgb(212, 130, 47));
        ball.setOpacity(0);
        
        // creates slider1 node (left)
        // constructor: LeftCornerX, LeftCornerY, Width, Height
        Rectangle slider1 = new Rectangle(50, 330, 20, 140);
        slider1.setArcHeight(5.0);
        slider1.setArcWidth(5.0);
        slider1.setFill(Color.rgb(127, 144, 255));
        slider1.setStroke(Color.BLACK);
        slider1.setStrokeType(StrokeType.INSIDE);
        
        // creates slider2 node (right)
        // constructor: LeftCornerX, LeftCornerY, Width, Height
        Rectangle slider2 = new Rectangle(1130, 330, 20, 140);
        slider2.setArcHeight(5.0);
        slider2.setArcWidth(5.0);
        slider2.setFill(Color.rgb(127, 144, 255));
        slider2.setStroke(Color.BLACK);
        slider2.setStrokeType(StrokeType.INSIDE);

        // creates winning text
        Text winner = new Text(480, 486, "");
        winner.setTextAlignment(TextAlignment.CENTER);  
        winner.setFont(Font.font("Veranda", 40));

        // creates (temporary) instructions
        // constructor: OriginX, OriginY, "Text"
        Text instructions = new Text(524, 415, "     Instructions:\nP:                          Pause the game\nW/S:                     Move slider1\nnum8/num5:        Move slider2\n\n=> Press P to start the game!");
        instructions.setFont(Font.font("Veranda", 14));
        
        // creates scoreboard
        Text scorePlayer1Text = new Text(485, 98, "0");
        scorePlayer1Text.setFont(Font.font("Veranda", 60));
        scorePlayer1Text.setTextAlignment(TextAlignment.CENTER);
        scorePlayer1Text.setWrappingWidth(158.9366455078125);
        scorePlayer1Text.setOpacity(0.7);

        Text scorePlayer2Text = new Text(554, 98, "0");
        scorePlayer2Text.setFont(Font.font("Veranda", 60));
        scorePlayer2Text.setTextAlignment(TextAlignment.CENTER);
        scorePlayer2Text.setWrappingWidth(158.9366455078125);
        scorePlayer2Text.setOpacity(0.7);

        Text colon = new Text(592, 98, ":");
        colon.setFont(Font.font("Veranda", 60));
        colon.setOpacity(0.7);

        // create a set that stores all of the keys that are pressed at any moment
        Set<KeyCode> keyPressed = new HashSet<KeyCode>();

        // creates controller object and starts the game
        Controller controller = new Controller(ball, slider1, slider2, scorePlayer1Text, scorePlayer2Text, winner, keyPressed, instructions);
        controller.startGame();

        // adds the settings menu
        SettingsMenu settingsMenu = new SettingsMenu();

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

        // creates an image with the settings gear
        Image settingsIcon = new Image("settingsIcon.png");

        // adds the settings gear as the icon for the settings button
        ImageButton sett = new ImageButton(settingsIcon, 25, 25, settings);

        // future me problem - new game button
        /* Button newGame = new Button("New Game");
        newGame.setLayoutX(70);
        newGame.setLayoutY(70);
        newGame.setOnAction(event -> {
            controller.newGame();
        }); */

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
        // creates an image with the pause icon
        Image pauseIcon = new Image("pauseIcon.png");

        // adds the pause icon as the icon for the button
        ImageButton pau = new ImageButton(pauseIcon, 50, 50, pause);

        // adds all the node to the AnchorPane
        root.getChildren().addAll(ball, slider1, slider2, scorePlayer1Text, scorePlayer2Text, colon, winner, instructions, settings, pause);

        // stage configs
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setWidth(1210);
        stage.setHeight(800);
        stage.show();

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
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}