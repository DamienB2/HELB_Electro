package com.example.helb_electro.components;

public class Motor extends Component {


    public Motor(String componentSpecification, String componentDefectivePercentage){
        super(componentSpecification, componentDefectivePercentage);
        this.setRGBColor(this.getClass().getSimpleName());
    }

    @Override
    public void getinfo() {
        System.out.println("New "+this.getClass().getSimpleName()+". Specification: "+getSpecification()+". Deficient: "+getDefectiveness());
    }
}
