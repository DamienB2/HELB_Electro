package com.example.helb_electro.products;


import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public class ProductFactory {

    public static Product getProduct(ArrayList<Component> componentNeededForTheNewProductList, String productName) {

        switch (productName){
            case("Robot"):
                return new Robot(componentNeededForTheNewProductList);

            case("Alarm"):
                return new Alarm(componentNeededForTheNewProductList);

            case("RemoteCar"):
                return new RemoteCar(componentNeededForTheNewProductList);

            case("Drone"):
                return new Drone(componentNeededForTheNewProductList);
        }
        return null;
    }
}
