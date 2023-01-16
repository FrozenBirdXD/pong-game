package com.pong_game;

import java.util.Random;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller extends GUI{
    
    Random rand = new Random();
    int randomY = rand.nextInt(700) + 50;
    
    public int velocityX = 8;
    public int velocityY = 8;
    public int ballCenterX = 600;
    public int ballCenterY = randomY;
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    public int slider1Y = 330;
    public int slider2Y = 330;
    public int sliderSpeed = 10;
    public int playUntil = 1;
    public boolean aPlayerWon = false;
    
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

    public void startGame() {
        // main game mechanics
        AnimationTimer gameTicks = new AnimationTimer() {
            private long prevTime = 0;
            
            @Override
            public void handle(long now) {
                long dt = now - prevTime;
                
                // checks if the game is not paused and if the time difference from the animation update is bigger that the given value
                if (!paused && dt > 3e7) {
                    prevTime = now;
                    keyboardInput();
                    ballCollision();
                    scorePoint();
                    updateBallPosition();
                    ifPlayerWins();
                    if (aPlayerWon) {
                        // long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
                        // System.out.println(afterUsedMem);
                        ball.setOpacity(0);
                        stop();
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
        if (ball.getCenterY() >= 745 || ball.getCenterY() <= 20) {
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
    
    public void reset() {
        slider1Y = 330;
        slider2Y = 330;
        ballCenterX = 600;
        
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
    
    public void keyboardInput() {
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
        } else if (keyPressed.contains(KeyCode.ESCAPE)) {
            // goes to setting menu
        }
    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    // future me problem
    public void restartButton() {
        winner.setOpacity(0);
        this.scorePlayer1 = 0;
        this.scorePlayer2 = 0;
        String scoreR1 = String.valueOf(this.scorePlayer1);
        String scoreR2 = String.valueOf(this.scorePlayer2);
        scorePlayer1Text.setText(scoreR1);
        scorePlayer2Text.setText(scoreR2);
        aPlayerWon = false;
        ball.setOpacity(1);
        restartButton.setVisible(false);
        startGame();
    }
}