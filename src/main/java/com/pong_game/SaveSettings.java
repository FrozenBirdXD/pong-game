package com.pong_game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SaveSettings extends GUI{

    public ChoiceBox presetDiff;
    public ColorPicker slider1Color; 
    public ColorPicker slider2Color;
    public ColorPicker ballColor;
    public Slider slider1Size;
    public Slider slider2Size;
    public Slider ballSize;
    public Slider slider1Speed;
    public Slider slider2Speed;
    public Slider ballSpeed;
    public TextField playUntilInput;

    public SaveSettings(ChoiceBox presetDiff, ColorPicker slider1Color, ColorPicker slider2Color, ColorPicker ballColor, Slider slider1Size, Slider slider2Size, Slider ballSize, Slider slider1Speed, Slider slider2Speed, Slider ballSpeed, TextField playUntilInput) {
        this.presetDiff = presetDiff;
        this.slider1Color = slider1Color;
        this.slider2Color = slider2Color;
        this.ballColor = ballColor;
        this.slider1Size = slider1Size;
        this.slider2Size = slider2Size;
        this.ballSize = ballSize;
        this.slider1Speed = slider1Speed;
        this.slider2Speed = slider2Speed;
        this.ballSpeed = ballSpeed;
        this.playUntilInput = playUntilInput;
    }

    public void saveSettings() {
        Properties properties = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("difficulty", (String) presetDiff.getValue());
            properties.setProperty("slider 1 color hex", String.valueOf(slider1Color.getValue()));
            properties.setProperty("slider 2 color hex", String.valueOf(slider2Color.getValue()));
            properties.setProperty("ball color hex", String.valueOf(ballColor.getValue()));
            properties.setProperty("slider 1 size", String.valueOf(slider1Size.getValue()));
            properties.setProperty("slider 2 size", String.valueOf(slider2Size.getValue()));
            properties.setProperty("ball size", String.valueOf(ballSize.getValue()));
            properties.setProperty("slider 1 speed", String.valueOf(slider1Speed.getValue()));
            properties.setProperty("slider 2 speed", String.valueOf(slider2Speed.getValue()));
            properties.setProperty("ball speed", String.valueOf(ballSpeed.getValue()));
            // properties.setProperty playUntilInput

            // save settings to project root folder
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
    }

    public void setDefaultSettings() {
        Properties properties = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("difficulty", "Medium");
            properties.setProperty("slider 1 color hex", String.valueOf(Color.rgb(127, 144, 255)));
            properties.setProperty("slider 2 color hex", String.valueOf(Color.rgb(127, 144, 255)));
            properties.setProperty("ball color hex", String.valueOf(Color.rgb(212, 130, 47)));
            properties.setProperty("slider 1 size", String.valueOf(50.0));
            properties.setProperty("slider 2 size", String.valueOf(50.0));
            properties.setProperty("ball size", String.valueOf(50.0));
            properties.setProperty("slider 1 speed", String.valueOf(50.0));
            properties.setProperty("slider 2 speed", String.valueOf(50.0));
            properties.setProperty("ball speed", String.valueOf(50.0));
            // properties.setProperty playUntilInput

            // save settings to project root folder
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
    }

    public void readSettings() {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            // loads the config.properties file
            properties.load(input);

            // reads all of the settings and saves it to a string
            String difficulty = properties.getProperty("difficulty");
            String slider1Color = properties.getProperty("slider 1 color hex");
            String slider2Color = properties.getProperty("slider 2 color hex");
            String ballColor = properties.getProperty("ball color hex");
            String slider1Size = properties.getProperty("slider 1 size");
            String slider2Size = properties.getProperty("slider 2 size");
            String ballSize = properties.getProperty("ball size");
            String slider1Speed = properties.getProperty("slider 1 speed");
            String slider2Speed = properties.getProperty("slider 2 speed");
            String ballSpeed = properties.getProperty("ball speed");

            presetDiff.setValue(difficulty);
            this.slider1Color.setValue(Color.valueOf(slider1Color));
            this.slider2Color.setValue(Color.valueOf(slider2Color));    
            this.ballColor.setValue(Color.valueOf(ballColor));
            this.slider1Size.setValue(Double.valueOf(slider1Size));
            this.slider2Size.setValue(Double.valueOf(slider2Size));
            this.ballSize.setValue(Double.valueOf(ballSize));
            this.slider1Speed.setValue(Double.valueOf(slider1Speed));
            this.slider2Speed.setValue(Double.valueOf(slider2Speed));
            this.ballSpeed.setValue(Double.valueOf(ballSpeed));

        } catch (IOException io) {
            io.printStackTrace();
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
