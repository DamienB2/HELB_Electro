package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public class Drone extends Product{
    public Drone(ArrayList<Component> componentNeededForTheNewProductList) {
        super(componentNeededForTheNewProductList);
        this.setPrice(60);
        this.setFabricationTime(12);
        this.setEcoScore("E");
    }
}
