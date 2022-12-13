package com.pong_game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        // launches start method
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // creates root node and scene
        Group root = new Group();
        Scene scene = new Scene(root, Color.GRAY);
        stage.setScene(scene);


        // creates text node
        Text instructions = new Text();
        instructions.setText("use 'w' and 's' for player 1 \nuse '↑' and '↓' for player 2 ");
        instructions.setLayoutX(600);
        instructions.setLayoutY(300);
        instructions.setFont(Font.font("Veranda", 20));

        // creates slider1
        Rectangle slider1 = new Rectangle(140, 20, Color.PURPLE);
        slider1.setRotate(90);
        slider1.setX(20);
        slider1.setY(450);

        // creates slider2
        Rectangle slider2 = new Rectangle(140, 20, Color.PURPLE);
        slider2.setRotate(90);
        slider2.setX(1360);
        slider2.setY(450);

        // creates ball
        Circle ball = new Circle(30, Color.DARKBLUE);
        ball.setLayoutX(700);
        ball.setLayoutY(470);

        // add nodes to root
        root.getChildren().add(instructions);
        root.getChildren().add(slider1);
        root.getChildren().add(slider2);
        root.getChildren().add(ball);

        // icon
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);

        // stage configs
        stage.setHeight(1000);
        stage.setWidth(1500);
        stage.setResizable(false);
        stage.setTitle("Pong Game Stage");
        
        // makes stage visible
        stage.show();
    }

}