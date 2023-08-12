package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public class RemoteCar extends Product{

    public RemoteCar(ArrayList<Component> componentNeededForTheNewProductList){
        super(componentNeededForTheNewProductList);
        this.setPrice(30);
        this.setFabricationTime(5);
        this.setEcoScore("B");
    }
}
