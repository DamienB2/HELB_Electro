package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public class Alarm extends Product{

    public Alarm(ArrayList<Component> componentNeededForTheNewProductList){
        super(componentNeededForTheNewProductList);
        this.setPrice(20);
        this.setFabricationTime(4);
        this.setEcoScore("C");
    }
}
