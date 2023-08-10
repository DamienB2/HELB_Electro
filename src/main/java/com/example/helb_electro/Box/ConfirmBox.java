package com.example.helb_electro.Box;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmBox {

    private static final int WINDOW_WIDTH = 250;
    private static final int WINDOW_HEIGHT = 100;
    private static final int VBOX_PADDING = 20;
    static boolean answer;
    private static String title = "Alert !", message = "Do you want to clear the storage ?";


    public static boolean display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(WINDOW_WIDTH);
        window.setHeight(WINDOW_HEIGHT);


        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");

        yesButton.setOnAction(event -> {
            answer = true;
            window.close();
        });

        VBox layout = new VBox(VBOX_PADDING);
        layout.getChildren().addAll(label, yesButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
