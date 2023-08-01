package com.example.helb_electro.components;

public class ComponentFactory {


    public static Component getComponent(String componentName, String componentSpecification, String componentColor, String componentDefectivePercentage){

        switch (componentName){
            case "Moteur":
                return new Motor(componentSpecification, componentDefectivePercentage); //Crée un Moteur avec une puissance et un pourcentage de déféctuosité

            case "Capteur":
                return new Sensor(componentSpecification, componentDefectivePercentage, componentColor); //Crée un Capteur avec une distance, une couleur et un pourcentage de déféctuosité

            case "Batterie":
                return new Battery(componentSpecification, componentDefectivePercentage); //Crée une Batterie avec une taux de remplissage et un pourcentage de déféctuosité

        }
        return null;
    }
}
