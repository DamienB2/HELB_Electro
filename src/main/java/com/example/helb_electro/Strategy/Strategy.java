package com.example.helb_electro.Strategy;


import com.example.helb_electro.components.Battery;
import com.example.helb_electro.components.Component;
import com.example.helb_electro.components.Motor;
import com.example.helb_electro.components.Sensor;
import com.example.helb_electro.products.*;

import java.util.ArrayList;

public class Strategy{

    private IStrategy strategy = new TimeStrategy();
    private ArrayList<Product> allProductList = new ArrayList<>();

    public Strategy(){
        allProductList.add(new Robot(new Sensor("100m","noir","d0"), new Motor("100W","d0")));
        allProductList.add(new Alarm(new Battery("100%","d0"),new Sensor("100m","noir","d0")));
        allProductList.add(new RemoteCar(new Battery("100%","d0"),new Motor("100W","d0")));
        allProductList.add(new Drone(new Battery("100%","d0"),new Motor("100W","d0"),new Sensor("100m","noir","d0")));

    }

    public void setStrategy(String strategyString){
        if(strategyString == "Temps"){
            this.strategy = new TimeStrategy();

        } else if (strategyString == "Éco-score"){
            this.strategy = new ScoreStrategy();

        } else if (strategyString == "Prix"){
            this.strategy = new PriceStrategy();

        } else if (strategyString == "Diversification"){
            this.strategy = new DiversificationStrategy();

        }
    }

    public Product getProduct(ArrayList<Component> componentsList){
        //trie la liste allProductList pour la ranger en fonction de la stratégie choisie
        strategy.getSortedProductList(allProductList);

        ArrayList<Integer> idOfComponentForTheNewProductList= new ArrayList<>();

        fillComponentForTheProductList(idOfComponentForTheNewProductList, componentsList);

        for (int id :idOfComponentForTheNewProductList) {
            System.out.println(id);
        }



        return null; // pour le moment, return null pour les test. Devra return un product il me semble
    }

    private void fillComponentForTheProductList(ArrayList<Integer> idOfComponentForTheNewProductList, ArrayList<Component> componentsList) {
        int id = 0;

        for (Product product: allProductList) {
            idOfComponentForTheNewProductList.clear();

            //recherche une correspondance du premier composant du produit dans la liste de composant.
            id = seekForComponent(product.getComponentOfProductById(1),componentsList);

            //Si correspondance, ajout de la position du composant dans la liste à une liste de position
            if(id != -1){
                idOfComponentForTheNewProductList.add(id);

                //recherche une correspondance du deuxième composant du produit dans la liste de composant.
                id = seekForComponent(product.getComponentOfProductById(2),componentsList);

                //Si correspondance, ajout de la position du composant dans la liste à une liste de position
                if(id != -1){
                    idOfComponentForTheNewProductList.add(id);

                    //check si le produit est un drone. si oui, recherche une troisième correspondance.
                    if(product.getClass().getSimpleName().equals("Drone")){
                        id = seekForComponent(product.getComponentOfProductById(3),componentsList);

                        if(id != -1){
                            idOfComponentForTheNewProductList.add(id);
                        }
                    }
                }
            }
        }
    }


    private int seekForComponent(Component componentOfProduct, ArrayList<Component> componentsList) {
        int cpt = 0;
        for (Component component: componentsList) {
            if(componentOfProduct.getClass() == component.getClass()){
                return cpt;
            }
            cpt++;
        }
        return -1;
    }


}
