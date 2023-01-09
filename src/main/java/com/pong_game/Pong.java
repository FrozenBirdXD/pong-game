package com.pong_game;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

public class Pong extends Application {

    public int slider1Y = 330;
    public int slider2Y = 330;
    public int sliderSpeed = 20;
    public int ballCenterX = 600;
    public int ballCenterY = 400;
    public int velocityX = 6;
    public int velocityY = 6;
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

        // create event handling for start button
        

        // creates a ball node
        // constructor: CenterX, CenterY, Radius, Color
        Circle ball = new Circle(ballCenterX, ballCenterY, 20, Color.rgb(212,130,47));
        
        
        
        // creates slider1 node (left)
        // constructor: LeftCornerX, LeftCornerY, Width, Height
        Rectangle slider1 = new Rectangle(50, slider1Y, 20, 140);
        slider1.setArcHeight(5.0);
        slider1.setArcWidth(5.0);
        slider1.setFill(Color.rgb(127, 144, 255));
        slider1.setStroke(Color.BLACK);
        slider1.setStrokeType(StrokeType.INSIDE);
        
        // creates slider2 node (right)
        // constructor: LeftCornerX, LeftCornerY, Width, Height
        Rectangle slider2 = new Rectangle(1130, slider2Y, 20, 140);
        slider2.setArcHeight(5.0);
        slider2.setArcWidth(5.0);
        slider2.setFill(Color.rgb(127, 144, 255));
        slider2.setStroke(Color.BLACK);
        slider2.setStrokeType(StrokeType.INSIDE);

        // creates (temporary) instructions
        // constructor: OriginX, OriginY, "Text"
        Text instruction = new Text(513, 486, "use 'w' and 's' for player 1 use 'numpad 8' and 'numpad 5' for player 2");
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

        EventHandler banana = new EventHandler<KeyEvent>() {
        
            @Override
            public void handle(KeyEvent key) {
                switch (key.getCode()) {
                case W:
                    if (slider1Y - sliderSpeed > -10) {
                        slider1Y -= sliderSpeed;
                    }
                    slider1.setY(slider1Y);
                    break;
                case S:
                    if (slider1Y + sliderSpeed < 631) {
                        slider1Y += sliderSpeed;
                    }
                    slider1.setY(slider1Y);
                    break;
                case NUMPAD8:
                    if (slider2Y - sliderSpeed > -10) {
                        slider2Y -= sliderSpeed;
                    }
                    slider2.setY(slider2Y);
                    break;
                case NUMPAD5:
                    if (slider2Y - sliderSpeed < 631) {
                        slider2Y += sliderSpeed;
                    }
                    slider2.setY(slider2Y);
                    break;
                default:
                    break;
                }
            }
        };

        // key event handler
        scene.addEventHandler(KeyEvent.KEY_PRESSED, banana); 

        // background ticks

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                // ball collision detection with slider 1
                if (slider1.getY() < ballCenterY + ball.getRadius() && slider1.getY() + 140 > ballCenterY - ball.getRadius() && slider1.getX() < ballCenterX + ball.getRadius() && slider1.getX() + 20 > ballCenterX - ball.getRadius()) {
                    velocityX =- velocityX;
                }

                // ball collision detection with slider 2
                if (slider2.getY() < ballCenterY + ball.getRadius() && slider2.getY() + 140 > ballCenterY - ball.getRadius() && slider2.getX() < ballCenterX + ball.getRadius() && slider2.getX() > ballCenterX - ball.getRadius()) {
                    velocityX =- velocityX;
                }

                // ball collision detection with boundaries
                if(ballCenterX - ball.getRadius() <= 0 || ballCenterX + ball.getRadius() >= 1200) {
                    velocityX =- velocityX;
                    
                }

                if(ballCenterY - ball.getRadius() <= 0 || ballCenterY + ball.getRadius() >= 770) {
                    velocityY =- velocityY;
                    
                }
                
                // ball movement
                ball.setCenterX(ballCenterX += velocityX);
                ball.setCenterY(ballCenterY += velocityY);
            }

        }, 500, 40);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}