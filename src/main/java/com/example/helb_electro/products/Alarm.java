package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

public class Alarm extends Product{

    public Alarm(Component component1, Component component2){
        super(component1, component2);
        this.setPrice(20);
        this.setFabricationTime(4);
        this.setEcoScore("C");
    }
}
