package com.example.helb_electro.components;

public class Component implements Icomponent{

    public boolean defectiveness;
    public int percentageInt;
    public String specification;


    //recoit un string avec le pourcentage depuis le data.txt et le transforme en int
    public int getPercentage(String percentage) {
        return Integer.parseInt(percentage.replace("d",""));
    }

    //Génère un nombre random entre 0 et 100 pour savoir si le composant sera défectueux ou pas
    public boolean setDefective(int percentage){
        int rand = (int) (Math.random()*100)+1;

        if(rand < percentage){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void getinfo() {
    }
}
