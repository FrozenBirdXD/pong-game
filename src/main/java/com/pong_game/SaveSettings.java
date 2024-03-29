package com.pong_game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SaveSettings extends GUI{

    public ColorPicker slider1Color; 
    public ColorPicker slider2Color;
    public ColorPicker ballColor;
    public Slider slider1Size;
    public Slider slider2Size;
    public Slider ballSize;
    public Slider slider1Speed;
    public Slider slider2Speed;
    public Slider ballSpeed;
    public ChoiceBox playUntilInput;

    public SaveSettings(ColorPicker slider1Color, ColorPicker slider2Color, ColorPicker ballColor, Slider slider1Size, Slider slider2Size, Slider ballSize, Slider slider1Speed, Slider slider2Speed, Slider ballSpeed, ChoiceBox playUntilInput) {
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

    // saves the inputed settings to the config.properties file
    public void saveSettings() {
        Properties properties = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("slider 1 color hex", String.valueOf(slider1Color.getValue()));
            properties.setProperty("slider 2 color hex", String.valueOf(slider2Color.getValue()));
            properties.setProperty("ball color hex", String.valueOf(ballColor.getValue()));
            properties.setProperty("slider 1 size", String.valueOf(slider1Size.getValue()));
            properties.setProperty("slider 2 size", String.valueOf(slider2Size.getValue()));
            properties.setProperty("ball size", String.valueOf(ballSize.getValue()));
            properties.setProperty("slider 1 speed", String.valueOf(slider1Speed.getValue()));
            properties.setProperty("slider 2 speed", String.valueOf(slider2Speed.getValue()));
            properties.setProperty("ball speed", String.valueOf(ballSpeed.getValue()));
            properties.setProperty("play until x", String.valueOf(playUntilInput.getValue()));

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

    // resets all of the settings to the default
    public void setDefaultSettings() {
        Properties properties = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("slider 1 color hex", String.valueOf(Color.rgb(127, 144, 255)));
            properties.setProperty("slider 2 color hex", String.valueOf(Color.rgb(127, 144, 255)));
            properties.setProperty("ball color hex", String.valueOf(Color.rgb(212, 130, 47)));
            properties.setProperty("slider 1 size", String.valueOf(50.0));
            properties.setProperty("slider 2 size", String.valueOf(50.0));
            properties.setProperty("ball size", String.valueOf(50.0));
            properties.setProperty("slider 1 speed", String.valueOf(50.0));
            properties.setProperty("slider 2 speed", String.valueOf(50.0));
            properties.setProperty("ball speed", String.valueOf(50.0));
            properties.setProperty("play until x", String.valueOf(3));

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

    public void randomizeColors() {
        Properties properties = new Properties();
        OutputStream output = null;
        Random rand = new Random();

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("slider 1 color hex", String.valueOf(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
            properties.setProperty("slider 2 color hex", String.valueOf(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
            properties.setProperty("ball color hex", String.valueOf(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
            properties.setProperty("slider 1 size", String.valueOf(slider1Size.getValue()));
            properties.setProperty("slider 2 size", String.valueOf(slider2Size.getValue()));
            properties.setProperty("ball size", String.valueOf(ballSize.getValue()));
            properties.setProperty("slider 1 speed", String.valueOf(slider1Speed.getValue()));
            properties.setProperty("slider 2 speed", String.valueOf(slider2Speed.getValue()));
            properties.setProperty("ball speed", String.valueOf(ballSpeed.getValue()));
            properties.setProperty("play until x", String.valueOf(playUntilInput.getValue()));

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

    public void randomizeSpeed() {
        Properties properties = new Properties();
        OutputStream output = null;
        Random rand = new Random();

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("slider 1 color hex", String.valueOf(slider1Color.getValue()));
            properties.setProperty("slider 2 color hex", String.valueOf(slider2Color.getValue()));
            properties.setProperty("ball color hex", String.valueOf(ballColor.getValue()));
            properties.setProperty("slider 1 size", String.valueOf(slider1Size.getValue()));
            properties.setProperty("slider 2 size", String.valueOf(slider2Size.getValue()));
            properties.setProperty("ball size", String.valueOf(ballSize.getValue()));
            properties.setProperty("slider 1 speed", String.valueOf(rand.nextInt(101)));
            properties.setProperty("slider 2 speed", String.valueOf(rand.nextInt(101)));
            properties.setProperty("ball speed", String.valueOf(rand.nextInt(101)));
            properties.setProperty("play until x", String.valueOf(playUntilInput.getValue()));

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

    public void randomizeSize() {
        Properties properties = new Properties();
        OutputStream output = null;
        Random rand = new Random();

        try {
            output = new FileOutputStream("config.properties");

            // stores all of the user input in the config.properties file
            properties.setProperty("slider 1 color hex", String.valueOf(slider1Color.getValue()));
            properties.setProperty("slider 2 color hex", String.valueOf(slider2Color.getValue()));
            properties.setProperty("ball color hex", String.valueOf(ballColor.getValue()));
            properties.setProperty("slider 1 size", String.valueOf(rand.nextInt(101)));
            properties.setProperty("slider 2 size", String.valueOf(rand.nextInt(101)));
            properties.setProperty("ball size", String.valueOf(rand.nextInt(101)));
            properties.setProperty("slider 1 speed", String.valueOf(slider1Speed.getValue()));
            properties.setProperty("slider 2 speed", String.valueOf(slider2Speed.getValue()));
            properties.setProperty("ball speed", String.valueOf(ballSpeed.getValue()));
            properties.setProperty("play until x", String.valueOf(playUntilInput.getValue()));
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

    // reads the settings from the config.properties file and sets the value of the input possibilities
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

            // gives the string to the visible values of the input possibilities
            this.slider1Color.setValue(Color.valueOf(slider1Color));
            this.slider2Color.setValue(Color.valueOf(slider2Color));    
            this.ballColor.setValue(Color.valueOf(ballColor));
            this.slider1Size.setValue(Double.valueOf(slider1Size));
            this.slider2Size.setValue(Double.valueOf(slider2Size));
            this.ballSize.setValue(Double.valueOf(ballSize));
            this.slider1Speed.setValue(Double.valueOf(slider1Speed));
            this.slider2Speed.setValue(Double.valueOf(slider2Speed));
            this.ballSpeed.setValue(Double.valueOf(ballSpeed));
            this.playUntilInput.setValue(playUntilX);

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

    public void setBallColor(Circle ball) {
        ball.setFill(ballColor.getValue());
    }

    public void setSlider1Color(Rectangle slider1) {
        slider1.setFill(slider1Color.getValue());
    }

    public void setSlider2Color(Rectangle slider2) {
        slider2.setFill(slider2Color.getValue());
    } 

    public void setBallSize(Circle ball) {
        double radius = (50 + ballSize.getValue()) / 5;     // calculates the slider input to the ball radius
        ball.setRadius(radius);
    }

    public void setSlider1Size(Rectangle slider1) {
        double length = (400 + slider1Size.getValue() * 6) / 5;     // calculates the slider input to the length of slider 1
        slider1.setHeight(length);
    }

    public void setSlider2Size(Rectangle slider2) {
        double length = (400 + slider2Size.getValue() * 6) / 5;     // calculates the slider input to the length of slider 2
        slider2.setHeight(length);
    }

    public void setBallSpeed(Controller controller) {
        double speed = (50 + 3 * ballSpeed.getValue()) / 25;        // calculates the slider input to the velocity of the ball
        controller.velocityX = speed;
        controller.velocityY = speed;
    }

    public void setSlider1Speed(Controller controller) {
        double speed = (50 + slider1Speed.getValue()) / 10;     // calculates the slider input to the speed of slider 1 
        controller.slider1Speed = speed;
    }

    public void setSlider2Speed(Controller controller) {
        double speed = (50 + slider2Speed.getValue()) / 10;     // calculates the slider input to the speed of slider 2
        controller.slider2Speed = speed;
    }

    public void setPlayUntil(Controller controller) {
        int playUntil = Integer.valueOf(String.valueOf(playUntilInput.getValue()));
        controller.playUntil = playUntil;
    }
}
