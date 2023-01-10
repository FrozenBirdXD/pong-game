package com.pong_game;

import java.util.Random;
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

    Random rand = new Random();
    int randomY = rand.nextInt(700) + 50;

    public int slider1Y = 330;
    public int slider2Y = 330;
    public int sliderSpeed = 20;
    public int ballCenterX = 600;
    public int ballCenterY = randomY;
    public int velocityX = 6;
    public int velocityY = 6;
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;

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
        Image icon = new Image("pongIcon.png");
        stage.getIcons().add(icon);
        
        // creates start button
        /* Button startButton = new Button("start game");
        startButton.setDefaultButton(true);
        startButton.setLayoutX(554);
        startButton.setLayoutY(306);
        startButton.setPrefHeight(34); 
        startButton.setPrefWidth(90);
        startButton.setFont(Font.font("Veranda", 14)); */

        // create event handling for start button

        // creates a ball node
        // constructor: CenterX, CenterY, Radius, Color
        Circle ball = new Circle(ballCenterX, randomY, 20, Color.rgb(212,130,47));
        
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

        // creates winning text
        Text winner = new Text(480, 486, "");
        winner.setTextAlignment(TextAlignment.CENTER);  
        winner.setFont(Font.font("Veranda", 40));

        // creates (temporary) instructions
        // constructor: OriginX, OriginY, "Text"
        Text instruction = new Text(384, 486, "use 'w' and 's' for player 1 use 'numpad 8' and 'numpad 5' for player 2");
        instruction.setFont(Font.font("Veranda", 14));

        Text instruction2 = new Text(501, 550, "Game starts in 5 seconds!");
        instruction2.setFont(Font.font("Veranda", 18));

        Text instruction3 = new Text(482, 505, "Press Spacebar to remove instructions");
        instruction3.setFont(Font.font("Veranda", 14));
        

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
        root.getChildren().addAll(ball, slider1, slider2, scorePlayer1, scorePlayer2, colon, winner, instruction, instruction2, instruction3);

        // stage configs
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setWidth(1210);
        stage.setHeight(800);
        stage.show();

        EventHandler event = new EventHandler<KeyEvent>() {
        
            @Override
            public void handle(KeyEvent key) {
                switch (key.getCode()) {
                case W:
                    if (slider1Y - sliderSpeed > -20) {
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
                    if (slider2Y - sliderSpeed > -20) {
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
                case SPACE:
                    instruction.setOpacity(0);
                    instruction2.setOpacity(0);
                    instruction3.setOpacity(0);
                default:
                    break;
                }
            }
        };

        // key event handler
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event); 

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
                if (ballCenterY - ball.getRadius() <= 0 || ballCenterY + ball.getRadius() >= 770) {
                    velocityY =- velocityY;
                }
                
                // reset game when and update scores when point is scored
            
                if (ballCenterX - ball.getRadius() <= 0) {
                    Pong.this.scorePlayer2 += 1;
                    String scoreR1 = String.valueOf(Pong.this.scorePlayer2);
                    scorePlayer2.setText(scoreR1);
                    reset();
                    slider1.setY(slider1Y);
                    slider2.setY(slider2Y);
                    ball.setCenterX(ballCenterX);
                }

                if (ballCenterX + ball.getRadius() >= 1200) {
                    Pong.this.scorePlayer1 += 1;
                    String scoreR1 = String.valueOf(Pong.this.scorePlayer1);
                    scorePlayer1.setText(scoreR1);
                    reset();
                    slider1.setY(slider1Y);
                    slider2.setY(slider2Y);
                    ball.setCenterX(ballCenterX);
                }
                
                // ball movement
                ball.setCenterX(ballCenterX += velocityX);
                ball.setCenterY(ballCenterY += velocityY);

                if (Pong.this.scorePlayer1 == 5) {
                    winner.setText("Player 1 WINS!");
                    timer.cancel();
                }

                if (Pong.this.scorePlayer2 == 5) {
                    winner.setText("Player 2 WINS!");
                    timer.cancel();
                }
            }

        }, 5000, 40);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        slider1Y = 330;
        slider2Y = 330;
        ballCenterX = 600;
        
    }
}