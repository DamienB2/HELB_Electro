package com.example.helb_electro;

import com.example.helb_electro.products.Product;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class ProductButton {

    private ArrayList<Product> productHistory = new ArrayList<>();
    private Button button;
    private Product assignedProduct;

    public ProductButton(){
        this.button = new Button();
        this.button.setPrefHeight(50);
        this.button.setPrefWidth(100);
        this.button.setStyle("-fx-background-color : rgb("+200+","+200+","+200+"); -fx-border-color: black;");


    }

    public Button getButton(){
        return this.button;
    }
    public void setAssignedProduct(Product assignedProduct){
        this.assignedProduct = assignedProduct;
        if(assignedProduct != null){
            productHistory.add(assignedProduct);
        }
    }

    public Product getAssignedProduct(){
        return assignedProduct;
    }

    public ArrayList<Product> getProductHistory(){
        return productHistory;
    }
}
