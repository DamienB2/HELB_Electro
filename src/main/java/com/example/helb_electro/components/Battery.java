package com.example.helb_electro.components;

public class Battery extends Component {

    private String fillPercentage;

    public Battery(String componentSpecification, String componentDefectivePercentage){
        this.fillPercentage = componentSpecification;
        percentageInt = getPercentage(componentDefectivePercentage);
        defectiveness = setDefective(percentageInt);
        System.out.println("Nouvelle Batterie avec: "+fillPercentage+". Est-t-il déficient: "+defectiveness);
    }
}
