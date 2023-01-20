package com.pong_game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller extends GUI{
    
    Random rand = new Random();
    int randomY = rand.nextInt(700) + 50;
    
    public double velocityX = 8.0;
    public double velocityY = 8.0;
    public int ballCenterX = 600;
    public int ballCenterY = randomY;
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    public int slider1Y = 330;
    public int slider2Y = 330;
    public double slider1Speed = 10.0;
    public double slider2Speed = 10.0;
    public int playUntil = 3;
    public boolean aPlayerWon = false;

    // if the game is running
    public boolean running = true;
    
    public Circle ball;
    public Rectangle slider1;
    public Rectangle slider2;
    public Text scorePlayer1Text;
    public Text scorePlayer2Text;
    public Text winner;
    public Set keyPressed;
    public Text instructions;
    public Button restartButton;

    public Controller(Circle ball, Rectangle slider1, Rectangle slider2, Text scorePlayer1Text, Text scorePlayer2Text, Text winner, Set keyPressed, Text instructions, Button restartButton) {
        this.ball = ball;
        this.slider1 = slider1;
        this.slider2 = slider2;
        this.scorePlayer1Text = scorePlayer1Text;
        this.scorePlayer2Text = scorePlayer2Text;
        this.winner = winner;
        this.keyPressed = keyPressed;
        this.instructions = instructions;
        this.restartButton = restartButton;
    }

    // main game mechanics
    public void startGame() {
        // starts the seperate Threads for the movement of the sliders
        moveSlider1();
        moveSlider2();   

        // reads the settings from the config.properties file and applies them
        readSettings();

        // creates a thread for the game ticks
        AnimationTimer gameTicks = new AnimationTimer() {
            private long prevTime = 0;
            
            @Override
            public void handle(long now) {
                // stores the time difference between the last last and the current tick
                long dt = now - prevTime;
                
                // checks if the game is not paused and if the time difference from the animation update is bigger that the given value
                if (!paused && dt > 3e7) {
                    prevTime = now;
                    ballCollision();
                    scorePoint();
                    updateBallPosition();
                    ifPlayerWins();
                    if (aPlayerWon) {
                        // long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
                        // System.out.println(afterUsedMem);
                        ball.setOpacity(0);
                        winner.setOpacity(1);
                        stop();
                        stopSlidersThread();
                    }
                }
            }
        };
        
        gameTicks.start();
    }
    
    public void ballCollision() {
        // ball collision detection with slider 1
        if (ball.getBoundsInParent().intersects(slider1.getBoundsInParent())) {
            velocityX =- velocityX;
        }
        
        // ball collision detection with slider 2
        if (ball.getBoundsInParent().intersects(slider2.getBoundsInParent())) {
            velocityX =- velocityX;
        }
        
        // ball collision detection with boundaries
        if (ball.getCenterY() + ball.getRadius() >= 770 || ball.getCenterY() - ball.getRadius() <= 5) {
            velocityY =- velocityY;
        }
    }
    
    public void updateBallPosition() {
        ball.setCenterX(ballCenterX += velocityX);
        ball.setCenterY(ballCenterY += velocityY);
    }
    
    // checks if the ball scored a point and resets the game
    public void scorePoint() {
        if (ballCenterX - ball.getRadius() <= 0) {
            this.scorePlayer2 += 1;
            String scoreR1 = String.valueOf(this.scorePlayer2);
            scorePlayer2Text.setText(scoreR1);
            reset();
        }
        
        if (ballCenterX + ball.getRadius() >= 1200) {
            this.scorePlayer1 += 1;
            String scoreR1 = String.valueOf(this.scorePlayer1);
            scorePlayer1Text.setText(scoreR1);
            reset();
        }
    }
    
    // resets the sliders and the ball to its initial positions
    public void reset() {
        ballCenterX = 600;
        // sets the sliders in the middle no matter the height of the slider
        slider1Y = (int) (800 - slider1.getHeight()) / 2;
        slider2Y = (int) (800 - slider2.getHeight()) / 2;
        
        slider1.setY(slider1Y);
        slider2.setY(slider2Y);
        ball.setCenterX(ballCenterX);
    }
    
    public void ifPlayerWins() {
        if (this.scorePlayer1 == playUntil) {
            winner.setText("Player 1 WINS!");
            aPlayerWon = true;
            restartButton.setVisible(true);
        }
        
        if (this.scorePlayer2 == playUntil) {
            winner.setText("Player 2 WINS!");
            aPlayerWon = true;
            restartButton.setVisible(true);
        }
    }

    public void stopSlidersThread() {
        running = false;
    }

    public void moveSlider1() {
        // creates a new Thread for the movement of slider1
        Thread slider1Thread = new Thread(() -> {
            while (running) {   
                // while the game is paused this Thread sleeps
                while(paused) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (keyPressed.contains(KeyCode.W) && slider1Y - slider1Speed > -15) {
                    slider1Y -= slider1Speed;
                } else if (keyPressed.contains(KeyCode.S) && slider1Y + slider1.getHeight() + slider1Speed < 795) {
                    slider1Y += slider1Speed;
                }
                Platform.runLater(() -> slider1.setY(slider1Y));    // sets the sliders position in the main javafx thread
                try {
                    // Thread waits 20ms every time the loop is executed
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        slider1Thread.start();
    }

    public void moveSlider2() {
        // creates a new Thread for the movement of slider2
        Thread slider2Thread = new Thread(() -> {
            while (running) {
                // while the game is paused this Thread sleeps
                while(paused) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (keyPressed.contains(KeyCode.NUMPAD8) && slider2Y - slider2Speed > -15) {
                    slider2Y -= slider2Speed;
                } else if (keyPressed.contains(KeyCode.NUMPAD5) && slider2Y + slider2.getHeight() + slider2Speed < 795) {
                    slider2Y += slider2Speed;
                }
                Platform.runLater(() -> slider2.setY(slider2Y));    // sets the sliders position in the main javafx thread
                try {
                    // Thread waits 20ms every time the loop is executed
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        slider2Thread.start();
    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    // resets all of the game values/progress
    public void restartButton() {
        winner.setOpacity(0);
        this.scorePlayer1 = 0;
        this.scorePlayer2 = 0;
        String scoreR1 = String.valueOf(this.scorePlayer1);
        String scoreR2 = String.valueOf(this.scorePlayer2);
        scorePlayer1Text.setText(scoreR1);
        scorePlayer2Text.setText(scoreR2);
        aPlayerWon = false;
        running = true;
        ball.setOpacity(1);
        restartButton.setVisible(false);
        startGame();
    }

    // read the settings stored in the config.properties file and applies them
    public void readSettings() {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            // loads the config.properties file
            properties.load(input);

            // reads all of the settings and saves it to a string
            String slider1Color = properties.getProperty("slider 1 color hex");
            String slider2Color = properties.getProperty("slider 2 color hex");
            String ballColor = properties.getProperty("ball color hex");
            String slider1Size = properties.getProperty("slider 1 size");
            String slider2Size = properties.getProperty("slider 2 size");
            String ballSize = properties.getProperty("ball size");
            String slider1Speed = properties.getProperty("slider 1 speed");
            String slider2Speed = properties.getProperty("slider 2 speed");
            String ballSpeed = properties.getProperty("ball speed");
            String playUntilX = properties.getProperty("play until x");

            // applies the stored settings 
            ball.setFill(Paint.valueOf(ballColor));
            slider1.setFill(Paint.valueOf(slider1Color));
            slider2.setFill(Paint.valueOf(slider2Color));
            ball.setRadius((50 + Double.valueOf(ballSize)) / 5);
            slider1.setHeight((400 + Double.valueOf(slider1Size) * 6) / 5);
            slider2.setHeight((400 + Double.valueOf(slider2Size) * 6) / 5);
            double speed = (50 + 3 * Double.valueOf(ballSpeed)) / 25;
            this.velocityX = speed;
            this.velocityY = speed;
            this.slider1Speed = (50 + Double.valueOf(slider1Speed)) / 10;
            this.slider2Speed = (50 + Double.valueOf(slider2Speed)) / 10;
            this.playUntil = Integer.valueOf(playUntilX);

        } catch (IOException io) {
            System.out.println("config file not found");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
    }
}
