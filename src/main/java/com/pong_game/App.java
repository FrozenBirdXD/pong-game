package com.pong_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        // launches start method
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        // creates root node and scene
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root, Color.GRAY);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
     
    }

}