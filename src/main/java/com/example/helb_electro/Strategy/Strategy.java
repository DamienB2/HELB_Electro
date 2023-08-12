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
    private ArrayList<Component> componentsForRobot = new ArrayList<>();
    private ArrayList<Component> componentsForAlarm = new ArrayList<>();
    private ArrayList<Component> componentsForRemoteCar = new ArrayList<>();
    private ArrayList<Component> componentsForDrone = new ArrayList<>();


    private Sensor sensor = new Sensor("100m","d0","noir");
    private Motor motor = new Motor("100W","d0");
    private Battery battery = new Battery("100%","d0");


    public Strategy(){
        componentsForRobot.add(sensor);
        componentsForRobot.add(motor);
        allProductList.add(new Robot(componentsForRobot));

        componentsForAlarm.add(sensor);
        componentsForAlarm.add(battery);
        allProductList.add(new Alarm(componentsForAlarm));

        componentsForRemoteCar.add(battery);
        componentsForRemoteCar.add(motor);
        allProductList.add(new RemoteCar(componentsForRemoteCar));

        componentsForDrone.add(sensor);
        componentsForDrone.add(motor);
        componentsForDrone.add(battery);
        allProductList.add(new Drone(componentsForDrone));

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

    public ArrayList<String> getProductid(ArrayList<Component> componentsList){
        //trie la liste allProductList pour la ranger en fonction de la stratégie choisie
        strategy.getSortedProductList(allProductList);

        ArrayList<String> ComponentForTheNewProductList= new ArrayList<>();
        fillComponentForTheProductList(ComponentForTheNewProductList, componentsList);

        return ComponentForTheNewProductList;
    }

    private ArrayList<String> fillComponentForTheProductList(ArrayList<String> idOfComponentForTheNewProductList, ArrayList<Component> componentsList) {
        int id = 0;

        for (Product product: allProductList) {
            idOfComponentForTheNewProductList.clear();

            //recherche une correspondance du premier composant du produit dans la liste de composant.
            id = seekForComponent(product.getComponentOfProductById(0),componentsList);

            //Si correspondance, ajout de la position du composant dans la liste à une liste de position
            if(id != -1){
                idOfComponentForTheNewProductList.add(String.valueOf(id));

                //recherche une correspondance du deuxième composant du produit dans la liste de composant.
                id = seekForComponent(product.getComponentOfProductById(1),componentsList);

                //Si correspondance, ajout de la position du composant dans la liste à une liste de position
                if(id != -1){
                    idOfComponentForTheNewProductList.add(String.valueOf(id));

                    //check si le produit est un drone. si oui, recherche une troisième correspondance.
                    if(product.getClass().getSimpleName().equals("Drone")){
                        id = seekForComponent(product.getComponentOfProductById(2),componentsList);

                        if(id != -1){
                            idOfComponentForTheNewProductList.add(String.valueOf(id));
                            idOfComponentForTheNewProductList.add(product.getClass().getSimpleName());

                            return idOfComponentForTheNewProductList;
                        }
                    }

                    idOfComponentForTheNewProductList.add(product.getClass().getSimpleName());

                    return idOfComponentForTheNewProductList;
                }
            }
        }
        return null;
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
