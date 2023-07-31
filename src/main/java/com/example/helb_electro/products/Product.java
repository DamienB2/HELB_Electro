package com.example.helb_electro.products;

import com.example.helb_electro.components.Component;

import java.util.ArrayList;

public abstract class Product {

    private String ecoScore;
    private int fabricationTime;
    private int price;
    private int[] RGBTab = {0, 0, 0}; //default color value
    private Component component1, component2, component3;

    public Product(Component component1, Component component2){
        this.component1 = component1;
        this.component2 = component2;
        this.RGBTab = setRGBColor(component1.getRGBColor(), component2.getRGBColor());
    }

    public Product(Component component1, Component component2, Component component3) {
        this.component1 = component1;
        this.component2 = component2;
        this.component3 = component3;
        this.RGBTab = setRGBColor(component1.getRGBColor(), component2.getRGBColor());
    }

    private int[] setRGBColor(int[] rgbColorComponent1, int[] rgbColorComponent2) {

        //Additionne les couleurs des 2 composants pour faire la couleur du produit.
        RGBTab[0] = rgbColorComponent1[0] + rgbColorComponent2[0];
        RGBTab[1] = rgbColorComponent1[1] + rgbColorComponent2[1];
        RGBTab[2] = rgbColorComponent1[2] + rgbColorComponent2[2];

        //effectue une vérification après l'addition des couleurs des composants pour voir si la somme est plus grande que 255.
        for (int i = 0; i < RGBTab.length; i++) {
            if(RGBTab[i]> 255){
                RGBTab[i] = 255;
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

    public Component getComponentOfProductById(int id){
        Component componentToReturn = null;

        switch (id){
            case 1:
                componentToReturn =  component1;
                break;
            case 2:
                componentToReturn =  component2;
                break;
            case 3:
                componentToReturn =  component3;
                break;
        }

        return componentToReturn;
    }




}
