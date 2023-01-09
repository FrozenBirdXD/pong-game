package com.pong_game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class Controller extends GUI{

    public void startGame() {
        System.out.println("start the game");
    }

   /*  EventHandler KeyHandler = new EventHandler<KeyEvent>() {

        
        @Override
        public void handle(KeyEvent key) {
            switch (key.getCode()) {
            case W:
                slider1.setLayoutY(slider1Y);
                break;
            case S:
                System.out.println("Yay S");
                break;
            case NUMPAD8:
                System.out.println("Yay Up");
                break;
            case NUMPAD5:
                System.out.println("Yay Down");
                break;
            default:
                break;
            }
        }
        
    }; */

    public void moveSliderUp() {
        System.out.println("move the slider up");
    }

    public void moveSliderDown() {
        System.out.println("move the slider down");
    }
}
