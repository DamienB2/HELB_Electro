package com.example.helb_electro.components;

public class Sensor extends Component{
    private String color;

    public Sensor(String componentSpecification, String componentColor, String componentDefectivePercentage){
        this.specification = componentSpecification;
        this.color = componentColor;
        percentageInt = getPercentage(componentDefectivePercentage);
        defectiveness = setDefective(percentageInt);
    }

    @Override
    public void getinfo() {
        System.out.println("New "+this.getClass().getSimpleName()+". Specification: "+specification+". Color: "+color+". Deficient: "+defectiveness);
    }
}
