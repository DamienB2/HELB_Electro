package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

public class Robot extends Product{
    

    public Robot(Component component1, Component component2){
        super(component1, component2);
        this.setPrice(40);
        this.setFabricationTime(6);
        this.setEcoScore("B");
    }


}
