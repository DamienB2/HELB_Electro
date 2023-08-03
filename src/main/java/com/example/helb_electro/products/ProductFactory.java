package com.example.helb_electro.products;


import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public class ProductFactory {

    public static Product getProduct(ArrayList<Component> componentNeededForTheNewProductList, String productName) {

        switch (productName){
            case("Robot"):
                return new Robot(componentNeededForTheNewProductList.get(0), componentNeededForTheNewProductList.get(1));

            case("Alarm"):
                return new Alarm(componentNeededForTheNewProductList.get(0), componentNeededForTheNewProductList.get(1));

            case("RemoteCar"):
                return new RemoteCar(componentNeededForTheNewProductList.get(0), componentNeededForTheNewProductList.get(1));

            case("Drone"):
                return new Drone(componentNeededForTheNewProductList.get(0), componentNeededForTheNewProductList.get(1), componentNeededForTheNewProductList.get(2));
        }
        return null;
    }
}
