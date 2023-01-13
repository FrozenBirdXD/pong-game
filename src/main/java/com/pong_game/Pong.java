package com.pong_game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
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

public class Pong extends Application {

    Random rand = new Random();
    int randomY = rand.nextInt(700) + 50;

    public int slider1Y = 330;
    public int slider2Y = 330;
    public int sliderSpeed = 10;
    public int ballCenterX = 600;
    public int ballCenterY = randomY;
    public int velocityX = 6;
    public int velocityY = 6;
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    public boolean paused = false;
    public boolean aPlayerWon = false;

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

        // adds pongIcon.png as the icon
        Image icon = new Image("pongIcon.png");
        stage.getIcons().add(icon);

        // creates a ball node
        // constructor: CenterX, CenterY, Radius, Color
        Circle ball = new Circle(ballCenterX, randomY, 20, Color.rgb(212, 130, 47));
        
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
        root.getChildren().addAll(ball, slider1, slider2, scorePlayer1, scorePlayer2, colon, winner, instruction, instruction3);

        // stage configs
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setWidth(1210);
        stage.setHeight(800);
        stage.show();

        // create a set that stores all of the keys that are pressed at any moment
        Set<KeyCode> keyPressed = new HashSet<KeyCode>();

        // add an event handler to the scene to listen for keys pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                
                if (key.getCode().equals(KeyCode.P)) {
                    paused = !paused;
                } else {
                    keyPressed.add(key.getCode());
                }
            }
        });

        // add an event handler to the scene to listen for keys released
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                keyPressed.remove(key.getCode());
            }
        });

        // ball movement, ball detection and main game mechanics
        AnimationTimer gameTicks = new AnimationTimer() {
            private long prevTime = 0;

            @Override
            public void handle(long now) {
                long dt = now - prevTime;

                // checks if the game is not paused and if the time difference from the animation update is bigger that the given value
                if (!paused && dt > 3e7) {
                    prevTime = now;
                    keyboardInput(keyPressed, slider1, slider2, instruction, instruction3);
                    ballCollision(ball, slider1, slider2);
                    scorePoint(ball, slider1, slider2, scorePlayer2, scorePlayer1);
                    ifPlayerWins(winner);
                    if (aPlayerWon) {
                        stop();
                    }
                    updateBallPosition(ball);
                }
            }
        };

        gameTicks.start();
        
        } catch(Exception e) {
            e.printStackTrace();
        }   
    }

    public void keyboardInput(Set keyPressed, Rectangle slider1, Rectangle slider2, Text instruction, Text instruction3) {
        if (keyPressed.contains(KeyCode.W)) {
            if (slider1Y - sliderSpeed > -20) {
                slider1Y -= sliderSpeed;
            }
            slider1.setY(slider1Y);
        } else if (keyPressed.contains(KeyCode.S)) {
            if (slider1Y + sliderSpeed < 631) {
                slider1Y += sliderSpeed;
            }
            slider1.setY(slider1Y);
        } else if (keyPressed.contains(KeyCode.NUMPAD8)) {
            if (slider2Y - sliderSpeed > -20) {
                slider2Y -= sliderSpeed;
            }
            slider2.setY(slider2Y);
        } else if (keyPressed.contains(KeyCode.NUMPAD5)) {
            if (slider2Y - sliderSpeed < 631) {
                slider2Y += sliderSpeed;
            }
            slider2.setY(slider2Y);
        } else if (keyPressed.contains(KeyCode.SPACE)) {
            instruction.setOpacity(0);
            instruction3.setOpacity(0);
        }
    }

    public void reset(Circle ball, Rectangle slider1, Rectangle slider2) {
        slider1Y = 330;
        slider2Y = 330;
        ballCenterX = 600;

        slider1.setY(slider1Y);
        slider2.setY(slider2Y);
        ball.setCenterX(ballCenterX);
        
    }

    public void ifPlayerWins(Text winner) {
        if (Pong.this.scorePlayer1 == 5) {
            winner.setText("Player 1 WINS!");
            aPlayerWon = true;
        }

        if (Pong.this.scorePlayer2 == 5) {
            winner.setText("Player 2 WINS!");
            aPlayerWon = true;
        }
    }

    // checks if the ball scored a point and resets the game
    public void scorePoint(Circle ball, Rectangle slider1, Rectangle slider2, Text scorePlayer2, Text scorePlayer1) {
        if (ballCenterX - ball.getRadius() <= 0) {
            Pong.this.scorePlayer2 += 1;
            String scoreR1 = String.valueOf(Pong.this.scorePlayer2);
            scorePlayer2.setText(scoreR1);
            reset(ball, slider1, slider2);
        }

        if (ballCenterX + ball.getRadius() >= 1200) {
            Pong.this.scorePlayer1 += 1;
            String scoreR1 = String.valueOf(Pong.this.scorePlayer1);
            scorePlayer1.setText(scoreR1);
            reset(ball, slider1, slider2);
        }
    }

    public void updateBallPosition(Circle ball) {
        ball.setCenterX(ballCenterX += velocityX);
        ball.setCenterY(ballCenterY += velocityY);
    }

    public void ballCollision(Circle ball, Rectangle slider1, Rectangle slider2) {
        // ball collision detection with slider 1
        if (ball.getBoundsInParent().intersects(slider1.getBoundsInParent())) {
            velocityX =- velocityX;
        }

        // ball collision detection with slider 2
        if (ball.getBoundsInParent().intersects(slider2.getBoundsInParent())) {
            velocityX =- velocityX;
        }

        // ball collision detection with boundaries
        if (ball.getCenterY() >= 745 || ball.getCenterY() <= 20) {
            velocityY =- velocityY;
        }
    }
}