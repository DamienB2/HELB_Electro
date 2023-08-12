package com.example.helb_electro.components;

public class Sensor extends Component{
    private String componentColor;

    public Sensor(String componentSpecification, String componentDefectivePercentage, String componentColor){
        super(componentSpecification, componentDefectivePercentage);
        this.setRGBColor(0, 255, 0);
        this.componentColor = componentColor;
    }
}
