package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

public class RemoteCar extends Product{

    public RemoteCar(Component component1, Component component2){
        super(component1, component2);
        this.setPrice(30);
        this.setFabricationTime(5);
        this.setEcoScore("B");
    }
}
