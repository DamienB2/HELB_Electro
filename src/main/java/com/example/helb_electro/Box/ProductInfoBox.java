package com.example.helb_electro.Box;

import com.example.helb_electro.ProductButton;
import com.example.helb_electro.products.Product;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductInfoBox {

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 300;
    private static final int VBOX_PADDING = 10;
    private static final int RED_STATBUTTON = 100;
    private static final int GREEN_STATBUTTON = 100;
    private static final int BLUE_STATBUTTON = 255;
    private static final int RED_SELLBUTTON = 255;
    private static final int GREEN_SELLBUTTON = 100;
    private static final int BLUE_SELLBUTTON = 100;

    private static String productButtonStatus;
    private static String productInfoString;
    private static String answer;

    //CRASH SI MON BOUTON NE CONTIENT PAS DE PRODUCT
    public static String display(ProductButton productButton){
        answer = null;

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Emplacement: ( ; )");
        window.setWidth(WINDOW_WIDTH);
        window.setHeight(WINDOW_HEIGHT);


        Label statusLabel = new Label();
        statusLabel.setText("Status: " + getProductButtonStatus(productButton));

        Button statisticsButton = new Button();
        statisticsButton.setText("Voir les statistiques de cet emplacement.");
        statisticsButton.setStyle("-fx-background-color : rgb("+RED_STATBUTTON+","+GREEN_STATBUTTON+","+BLUE_STATBUTTON+"); -fx-border-color: blue;");

        statisticsButton.setOnAction(event -> {
            StatisticBox.display(productButton.getProductHistory());
        });

        Label productType = new Label("No info");
        if(productButton.getAssignedProduct() != null){
            productType.setText(getProductInfoString(productButton));
        }

        Button sellProductButton = new Button();
        sellProductButton.setText("Vendre Produit");
        sellProductButton.setStyle("-fx-background-color : rgb("+RED_SELLBUTTON+","+GREEN_SELLBUTTON+","+BLUE_SELLBUTTON+"); -fx-border-color: red;");


        sellProductButton.setOnAction(event -> {
            answer = "Delete";
            window.close();
        });


        VBox layout = new VBox(VBOX_PADDING);
        layout.getChildren().addAll(statusLabel, statisticsButton, productType,sellProductButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }


    //PATCH ICI QUAND LA CLASSE PRODUCT SERA REFAITE
    private static String getProductInfoString(ProductButton productButton) {

        //productInfoString = "";
        Product product = productButton.getAssignedProduct();

        productInfoString = "Type Produit: " + product.getClass().getSimpleName() +
                "\nPrix: " + product.getPrice() +
                "\nEco-Score: " + product.getEcoScore() +
                "\nDéfectuosité: " + product.getDefectiveness() +
                "\nSpécification 1: " + product.getComponentOfProductById(1).getSpecification() +
                "\nSpécification 2: " + product.getComponentOfProductById(2).getSpecification();
        //la partie spécification va changer car la forme de la classe product va aussi changer. Normalement on dervait recevoir une liste contenant tout les composants.
        return productInfoString;


    }

    private static String getProductButtonStatus(ProductButton productButton) {
        if(productButton.getAssignedProduct() != null){
            return productButtonStatus = "Occupé";
        }else{
            return productButtonStatus = "Libre";
        }
    }
}
