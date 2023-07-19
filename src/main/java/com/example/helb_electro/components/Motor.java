package com.example.helb_electro.components;

public class Motor extends Component {
    private String power;

    public Motor(String componentSpecification, String componentDefectivePercentage){
        this.power = componentSpecification;
        percentageInt = getPercentage(componentDefectivePercentage);
        defectiveness = setDefective(percentageInt);
        System.out.println("Nouveau moteur avec: "+power+". Est-t-il déficient: "+defectiveness);
    }



}
