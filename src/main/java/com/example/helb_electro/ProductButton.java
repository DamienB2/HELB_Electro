package com.example.helb_electro;

import com.example.helb_electro.products.Product;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class ProductButton {

    private final int RED = 200;
    private final int GREEN = 200;
    private final int BLUE = 200;
    private ArrayList<String> productHistory = new ArrayList<>();
    private Button button;
    private Product assignedProduct;

    public ProductButton(){
        this.button = new Button();
        this.button.setPrefHeight(50);
        this.button.setPrefWidth(100);
        this.button.setStyle("-fx-background-color : rgb("+RED+","+GREEN+","+BLUE+"); -fx-border-color: black;");


    }

    public Button getButton(){
        return this.button;
    }
    public void setAssignedProduct(Product assignedProduct){
        this.assignedProduct = assignedProduct;
        if(assignedProduct != null){
            productHistory.add(assignedProduct.getClass().getSimpleName());
        }
    }

    public Product getAssignedProduct(){
        return assignedProduct;
    }

    public ArrayList<String> getProductHistory(){
        return productHistory;
    }
}
