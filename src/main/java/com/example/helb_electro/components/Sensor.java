package com.example.helb_electro.components;

public class Sensor extends Component{
    private String componentColor;

    public Sensor(String componentSpecification, String componentDefectivePercentage, String componentColor){
        super(componentSpecification, componentDefectivePercentage);
        this.setRGBColor(0, 255, 0);
        this.componentColor = componentColor;
    }

    public String getComponentColor(){
        return componentColor;
    }
    @Override
    public void getinfo() {
        System.out.println("New "+this.getClass().getSimpleName()+". Specification: "+getSpecification()+". Color: "+getComponentColor()+". Deficient: "+getDefectiveness());
    }
}
