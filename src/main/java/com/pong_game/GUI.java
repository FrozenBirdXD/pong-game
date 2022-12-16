package com.pong_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

    public static void main(String[] args) {
        // launches start method
        launch(args);  
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
        // creates root node and scene 
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, Color.LIGHTGRAY);

        // adds icon.png as the icon
        Image icon = new Image("Icon.png");
        stage.getIcons().add(icon);
        
        // creates start button
        Button startButton = new Button("start game");
        startButton.setDefaultButton(true);
        startButton.setLayoutX(554);
        startButton.setLayoutY(306);
        startButton.setPrefHeight(34); 
        startButton.setPrefWidth(90);
        startButton.setFont(Font.font("Veranda", 14));

        // creates a ball node
        // constructor: CenterX, CenterY, Radius, Color
        Circle ball = new Circle(600, 400, 20, Color.rgb(212,130,47));
        
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

        // creates (temporary) instructions
        // constructor: OriginX, OriginY, "Text"
        Text instruction = new Text(513, 486, "use 'w' and 's' for player 1 use '↑' and '↓' for player 2");
        instruction.setTextAlignment(TextAlignment.CENTER);
        instruction.setFont(Font.font("Veranda", 14));
        instruction.setWrappingWidth(174.936767578125);

        // creates scoreboard
        Text scorePlayer1 = new Text(485, 98, "0");
        scorePlayer1.setFont(Font.font("Veranda", 60));
        scorePlayer1.setTextAlignment(TextAlignment.CENTER);
        scorePlayer1.setWrappingWidth(158.9366455078125);
        scorePlayer1.setOpacity(0.7);

        Text scorePlayer2 = new Text(554, 98, "0");
        scorePlayer2.setFont(Font.font("Veranda", 60));
        scorePlayer2.setTextAlignment(TextAlignment.CENTER);
        scorePlayer2.setWrappingWidth(158.9366455078125);
        scorePlayer2.setOpacity(0.7);

        Text colon = new Text(592, 98, ":");
        colon.setFont(Font.font("Veranda", 60));
        colon.setOpacity(0.7);
        
        
        // adds all the node to the AnchorPane
        root.getChildren().addAll(startButton, ball, slider1, slider2, instruction, scorePlayer1, scorePlayer2, colon);

        // stage configs
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setWidth(1210);
        stage.setHeight(800);
        stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
     
    }

}