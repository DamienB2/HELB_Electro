package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;
import com.example.helb_electro.components.Motor;
import com.example.helb_electro.components.Sensor;

import java.util.ArrayList;

public class Robot extends Product{
    

    public Robot(ArrayList<Component> componentNeededForTheNewProductList){
        super(componentNeededForTheNewProductList);
        this.setPrice(40);
        this.setFabricationTime(6);
        this.setEcoScore("B");
    }
}
