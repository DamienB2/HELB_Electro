package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public abstract class Product {

    private String ecoScore;
    private int fabricationTime;
    private int price;
    private int[] RGBTab = {0, 0, 0}; //default color value
    private final int MAX_COLOR_VALUE = 255;
    private ArrayList<Component> componentOfProductList = new ArrayList<>();
    private boolean defectiveness = false;


    public Product(ArrayList<Component> componentNeededForTheNewProductList) {
        this.componentOfProductList = componentNeededForTheNewProductList;
        this.RGBTab = setRGBColor(componentOfProductList);
        this.setDefectiveness();
    }


    private void setDefectiveness(){
        for (Component component: componentOfProductList) {
            if(component.getDefectiveness()){
                defectiveness = true;
            }
        }
    }
    public boolean getDefectiveness(){
        return defectiveness;
    }



    private int[] setRGBColor(ArrayList<Component> componentOfProductList) {

        for (Component component: componentOfProductList) {
            int[] componentRGBTab = component.getRGBColor();

            for (int i = 0; i < componentRGBTab.length; i++) {
                RGBTab[i] = RGBTab[i] + componentRGBTab[i];
            }
        }


        //effectue une vérification après l'addition des couleurs des composants pour voir si la somme est plus grande que 255.
        for (int i = 0; i < RGBTab.length; i++) {
            if(RGBTab[i]> MAX_COLOR_VALUE){
                RGBTab[i] = MAX_COLOR_VALUE;
            }
        }

        return RGBTab;
    }
    public int[] getRGBColor() {
        return RGBTab;
    }


    public void setPrice(int componentPrice){
        price = componentPrice;
    }
    public int getPrice(){
        return price;
    }


    public void setEcoScore(String componentEcoScore){
        ecoScore = componentEcoScore;
    }
    public String getEcoScore(){
        return ecoScore;
    }


    public void setFabricationTime(int componentFabricationTime){
        fabricationTime = componentFabricationTime;
    }
    public int getFabricationTime(){
        return fabricationTime;
    }


    public Component getComponentOfProductById(int id) {
        if(id < componentOfProductList.size()){
            return componentOfProductList.get(id);
        }else{
            return null;
        }

    }


}
