package com.example.helb_electro;

import com.example.helb_electro.Strategy.Strategy;
import com.example.helb_electro.components.*;
import com.example.helb_electro.products.Product;
import com.example.helb_electro.products.ProductFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

public class MainController implements Observer{

    private int cpt = 0, cptCycle = 0;
    private int duration;
    private boolean componentListState = false;

    private final int MAX_COMPONANT = 8;


    private String componentTime;
    private String componentName;
    private String componentSpecification;
    private String componentColor;
    private String componentDefectivePercentage;
    //pour avoir le pourcentage on utilise la dernière info de la ligne qu'on reçoit car si on définis un emplacement comme pour les autres, la couleur et la déféctuosité seront mélangée

    private static final ArrayList<Component> ComponentList = new ArrayList<>();
    private Parser parser;
    private HELBVue helbVue;
    private Strategy strategy = new Strategy();


    public MainController(HELBVue vue){
        helbVue = vue;
        parser = new Parser();

        startTimeline();

        checkComboBoxStatus();

    }

    private void checkComboBoxStatus() {
        //permet de changer de stratégie.
        helbVue.comboBox.setOnAction(e ->{
            strategy.setStrategy(helbVue.comboBox.getValue().toString());
        });
    }

    //Start timeline permet de lire ligne par ligne le fichier data. Il stocke ensuite les données dans un tableau de strings qui seront ensuite affichées lorsque le temps reçus est atteint
    private void startTimeline() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                componentListState = checkComponentListSize();

                if(componentListState == false){
                    String[] datas = parser.getComponentArray(cpt);
                    componentTime = datas[0];
                    duration = Integer.parseInt(componentTime);

                    if(duration == cptCycle){
                        createComponent(datas);

                        cptCycle = 0;
                        cpt++;
                    }
                    cptCycle++;
                    System.out.println("cycle de comptage");

                    //retourne une list d'id pour le moment
                    ArrayList<Integer>componentNeededForTheNewProductList = strategy.getProductid(ComponentList);

                    if(componentNeededForTheNewProductList.size() >= 2){
                        //appel la factory pour créé un nouveau product
                        createProduct(componentNeededForTheNewProductList);

                        //delete les composants de la liste de composant
                        deleteComponantFromComponentList(componentNeededForTheNewProductList);
                    }




                }else{
                    System.out.println("cycle bloqué");
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void createProduct(ArrayList<Integer> componentNeededForTheNewProductList) {
        // /!\ IL FAUT CREER UN PRODUCT ICI.

        //Product newProduct = ProductFactory.getProduct();
    }

    private void createComponent(String[] datas){
        //assignation des variables pour avoir un accès plus simple au différentes données
        componentName = datas[1];
        componentSpecification = datas[2];
        componentColor = datas[3];
        componentDefectivePercentage = datas[datas.length-1];

        Component newComponent = ComponentFactory.getComponent(componentName,componentSpecification,componentColor,componentDefectivePercentage);
        newComponent.getinfo();
        addComponentToComponentList(newComponent);

    }

    private void addComponentToComponentList(Component newComponent) {
        ComponentList.add(newComponent);
        update();
    }

    private boolean checkComponentListSize() {
        if(ComponentList.size() == MAX_COMPONANT){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update() {
        helbVue.updateComponentList(ComponentList);
    }

    private void deleteComponantFromComponentList(ArrayList<Integer> testList) {
        //supprime les composants servant à la création d'un produit de la liste
        for (int id: testList) {
            //System.out.println("Suppression de composant à la position:"+id);
            ComponentList.remove(id);
        }

        //le update permet de remettre le visuel à zero lorsque l'on supprime des composants de la liste
        update();
    }
}
