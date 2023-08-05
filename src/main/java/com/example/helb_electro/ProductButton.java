package com.example.helb_electro;

import javafx.scene.control.Button;

public class ProductButton {

    private Button button;

    public ProductButton(){
        this.button = new Button();
        this.button.setPrefHeight(50);
        this.button.setPrefWidth(100);
    }

    public Button getButton(){
        return this.button;
    }
}
