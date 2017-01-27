package com.netcracker.java.YuliaShevchenko.lab1.mainclass;

import com.netcracker.java.YuliaShevchenko.lab1.controllers.MainController;

import org.apache.log4j.Logger;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Class Main.
 * Start application.
 */

public final class Main extends Application {

    /**
     * It is used to register error.
     */
    private static final Logger LOGGER
            = Logger.getLogger(Main.class);
    /**
     * Width window.
     */

    private final int width = 900;

    /**
     * Height window.
     */

    private final int height = 500;


    public static void main(String args[]) {
        launch (args);
    }

    /**
     * Method start(final Stage primaryStage).
     * open the main window.
     * @param primaryStage the scene of the main window.
     * @throws IOException appears when opening a file.
     */

    @Override
    public void start(final Stage primaryStage) throws IOException {
        LOGGER.info("start working");
        FXMLLoader mainfxmlLoader = new FXMLLoader();
        mainfxmlLoader.setLocation(getClass().getResource("/com/netcracker/java/YuliaShevchenko/lab1/view/main.fxml"));
        Parent root = mainfxmlLoader.load();
        primaryStage.setTitle("Task Manager");
        MainController mainController = mainfxmlLoader.getController();
        primaryStage.setScene(new Scene(root, this.width, this.height));
        primaryStage.setMinHeight(this.height);
        primaryStage.setMinWidth(this.width);
        primaryStage.show();
        primaryStage.setOnCloseRequest(closeEvent -> {
                mainController.getThread().close();
                LOGGER.info("end working");
        });
    }
}
