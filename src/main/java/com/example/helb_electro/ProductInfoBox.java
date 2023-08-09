package com.example.helb_electro;

import com.example.helb_electro.products.Product;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductInfoBox {

    private static String productButtonStatus;
    private static String productInfoString;
    private static String answer;

    public static String display(ProductButton productButton){
        answer = null;

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        window.setWidth(300);
        window.setHeight(300);

        Label locationLabel = new Label();
        locationLabel.setText("Emplacement: ");

        Label statusLabel = new Label();
        statusLabel.setText("Status: " + getProductButtonStatus(productButton));

        Button statisticsButton = new Button();
        statisticsButton.setText("Voir les statistiques de cet emplacement.");

        Label productType = new Label();
        productType.setText(getProductInfoString(productButton));

        Button sellProductButton = new Button();
        sellProductButton.setText("Vendre Produit");

        sellProductButton.setOnAction(event -> {
            answer = "Delete";
            window.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(locationLabel, statusLabel, statisticsButton, productType,sellProductButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    private static String getProductInfoString(ProductButton productButton) {

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
