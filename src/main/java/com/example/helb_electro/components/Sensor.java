package com.example.helb_electro.components;

public class Sensor extends Component{
    private String componentColor;

    public Sensor(String componentSpecification, String componentColor, String componentDefectivePercentage){
        super(componentSpecification, componentDefectivePercentage);
        this.setRGBColor(this.getClass().getSimpleName());
        this.componentColor = componentColor;
        this.setRGBColor(this.getClass().getSimpleName());
    }

    public String getComponentColor(){
        return componentColor;
    }
    @Override
    public void getinfo() {
        System.out.println("New "+this.getClass().getSimpleName()+". Specification: "+getSpecification()+". Color: "+getComponentColor()+". Deficient: "+getDefectiveness());
    }
}
