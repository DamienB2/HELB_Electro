package com.example.helb_electro.products;


import com.example.helb_electro.components.Component;

public class ProductFactory {

    public static Product getProduct(Component component1, Component component2){

        if(component1.getClass().getSimpleName().equals("Battery") && component2.getClass().getSimpleName().equals("Sensor")){
            return new Alarm(component1, component2);

        } else if(component1.getClass().getSimpleName().equals("Battery") && component2.getClass().getSimpleName().equals("Motor")) {
            return new RemoteCar(component1, component2);

        } else if(component1.getClass().getSimpleName().equals("Sensor") && component2.getClass().getSimpleName().equals("Motor")) {
            return new Robot(component1, component2);

        }else{
            return null;
        }
    }

    public static Product getProduct(Component component1, Component component2, Component component3){
        return new Drone(component1, component2, component3);
    }
}
