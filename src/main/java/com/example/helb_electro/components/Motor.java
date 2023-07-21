package com.example.helb_electro.components;

public class Motor extends Component {

    public Motor(String componentSpecification, String componentDefectivePercentage){
        this.specification = componentSpecification;
        percentageInt = getPercentage(componentDefectivePercentage);
        defectiveness = setDefective(percentageInt);
    }

    @Override
    public void getinfo() {
        System.out.println("New "+this.getClass().getSimpleName()+". Specification: "+specification+". Deficient: "+defectiveness);
    }
}
