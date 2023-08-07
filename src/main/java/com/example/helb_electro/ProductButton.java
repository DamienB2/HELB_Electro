package com.example.helb_electro;

import com.example.helb_electro.products.Product;
import javafx.scene.control.Button;

public class ProductButton {

    private Button button;
    private Product assignedProduct;

    public ProductButton(){
        this.button = new Button();
        this.button.setPrefHeight(50);
        this.button.setPrefWidth(100);

    }

    public Button getButton(){
        return this.button;
    }
    public void setAssignedProduct(Product assignedProduct){
        this.assignedProduct = assignedProduct;
    }

    public Product getAssignedProduct(){
        return assignedProduct;
    }
}
