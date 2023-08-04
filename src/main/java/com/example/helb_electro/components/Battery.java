package com.example.helb_electro.components;

public class Battery extends Component {

    public Battery(String componentSpecification, String componentDefectivePercentage){
        super(componentSpecification, componentDefectivePercentage);
        this.setRGBColor(0, 0, 255);
    }
}
