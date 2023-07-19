package com.example.helb_electro.components;

public class Sensor extends Component{

    private String distance;
    private String color;


    public Sensor(String componentSpecification, String componentColor, String componentDefectivePercentage){
        this.distance = componentSpecification;
        this.color = componentColor;
        percentageInt = getPercentage(componentDefectivePercentage);
        defectiveness = setDefective(percentageInt);
        System.out.println("Nouveau Capteur avec: "+distance+" de distance, un couleur "+color+". Est-t-il déficient: "+defectiveness);
    }
}
