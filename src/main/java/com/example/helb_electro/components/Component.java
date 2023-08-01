package com.example.helb_electro.components;

public abstract class Component{

    private boolean defectiveness;
    private  String specification;

    private int[] RGBTab = {1, 1, 1}; //default color value


    private int maxDefectiveness = 100;
    private int minDefectiveness = 1;

    public Component(String specification, String percentageString){
        this.specification = specification;
        this.defectiveness = isDefective(percentageString);
    }


    public boolean getDefectiveness(){
        return defectiveness;
    }

    public String getSpecification(){
        return specification;
    }


    //Génère un nombre random entre 0 et 100 pour savoir si le composant sera défectueux ou pas
    public boolean isDefective(String percentageString){
        int percentage = Integer.parseInt(percentageString.replace("d",""));
        int rand = randomFunction(maxDefectiveness, minDefectiveness);

        return rand < percentage;
    }


    public abstract void getinfo();

    public int[] setRGBColor(int R, int G, int B) {
        RGBTab = new int[]{R, G, B};
        return RGBTab;
    }

    public int[] getRGBColor() {
        return RGBTab;
    }

    private int randomFunction (int max, int min){
        return (int) (Math.random()*max - min +1)+min;

    }
}
