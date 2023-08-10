package com.example.helb_electro.Box;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticBox {
    private static final int WINDOW_WIDTH = 250;
    private static final int WINDOW_HEIGHT = 250;
    private static final int VBOX_PADDING = 10;
    private static String title = "Statistiques";


    public static void display(ArrayList<String> productHistory){
        Stage window = new Stage();
        VBox layout = new VBox(VBOX_PADDING);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(WINDOW_WIDTH);
        window.setHeight(WINDOW_HEIGHT);

        ArrayList<String> differentProductNameList = getDifferentProductList(productHistory);

        for (String productName: differentProductNameList) {
            int frequence = Collections.frequency(productHistory, productName);

            Label label = new Label();
            label.setText(productName + ": " + frequence);
            layout.getChildren().add(label);
        }


        Button closeButton = new Button("Close");

        closeButton.setOnAction(event -> {
            window.close();
        });

        layout.getChildren().add(closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    private static ArrayList<String> getDifferentProductList(ArrayList<String> productHistory) {
        ArrayList<String> differentProductList = new ArrayList<>();

        for (String productName: productHistory) {

            if(!differentProductList.contains(productName)){
                differentProductList.add(productName);
            }
        }
        return differentProductList;
    }
}
