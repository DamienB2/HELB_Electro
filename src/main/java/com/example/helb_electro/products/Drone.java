package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

public class Drone extends Product{
    public Drone(Component component1, Component component2, Component component3) {
        super(component1, component2, component3);
        this.setPrice(60);
        this.setFabricationTime(12);
        this.setEcoScore("E");
    }
}
