package de.alexbleicher.kartenspiel.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class SpielGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        URL res = getClass().getResource("/Kartenspiel.fxml");
        Parent root = FXMLLoader.load(res);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
