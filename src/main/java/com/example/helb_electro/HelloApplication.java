package com.example.helb_electro;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("HELB-Electro");
        stage.setFullScreen(false);

        //créé un singleton pour HELBVue
        HELBVue vue = new HELBVue(stage);
        MainController controller = new MainController(vue);

    }

    public static void main(String[] args) {
        launch();
    }
}