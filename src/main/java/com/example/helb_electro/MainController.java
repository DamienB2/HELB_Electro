package com.example.helb_electro;

import com.example.helb_electro.Strategy.Strategy;
import com.example.helb_electro.components.*;
import com.example.helb_electro.products.Product;
import com.example.helb_electro.products.ProductFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

public class MainController implements Observer{

    private int cpt = 0, cptCycle = 0;
    private int cptProduct = 1;
    private int duration;
    private boolean componentListState = false;
    private boolean productListState = false;
    private boolean productCreationInProgress = false;
    private boolean confirmBoxActive = false;

    private final int MAX_COMPONANT;
    private final int MAX_PRODUCT;

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

        MAX_COMPONANT = helbVue.NUMBER_OF_LABEL;
        MAX_PRODUCT = helbVue.nbButton;

        startTimeline();

        checkComboBoxStatus();

    }

    private void checkComboBoxStatus() {
        //permet de changer de stratégie.
        helbVue.comboBox.setOnAction(e ->{
            strategy.setStrategy(helbVue.comboBox.getValue().toString());
        });

        for (ProductButton productButton : helbVue.productButtonList) {
            productButton.getButton().setOnAction(e -> {

                String result = helbVue.showProductButtonInfo(productButton);
                if(result == "Delete"){
                    productButton.setAssignedProduct(null);
                    update();
                }
            });
        }


    }

    //Start timeline permet de lire ligne par ligne le fichier data. Il stocke ensuite les données dans un tableau de strings qui seront ensuite affichées lorsque le temps reçus est atteint
    private void startTimeline() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                componentListState = checkComponentListSize();
                productListState = checkProductListSize();

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

                }else{
                    System.out.println("cycle bloqué");
                }

                if(productListState == false){
                    //retourne une list d'id pour le moment
                    ArrayList<String>infoNeededForTheNewProductList = strategy.getProductid(ComponentList);

                    if(infoNeededForTheNewProductList.size() > 0){
                        //appel la factory pour créé un nouveau product
                        createProduct(infoNeededForTheNewProductList);
                    }
                }else{
                    if(confirmBoxActive == false){
                        confirmBoxActive = true;

                        Platform.runLater(() -> {
                            boolean result = helbVue.clearStorage();

                            if(result == true){
                                clearProductStorage();
                                confirmBoxActive = false;
                            }
                        });
                    }

                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void clearProductStorage() {
        for (ProductButton productButton: helbVue.productButtonList) {
            productButton.setAssignedProduct(null);
            update();
        }
    }


    private void createProduct(ArrayList<String> infoNeededForTheNewProductList) {


        ArrayList<Component> componentNeededForTheNewProductList = new ArrayList<>();
        String productName;

        //permet de récupéré les composants dans la liste de composants pour la création du produit à partir des ids (positions)
        for (int i = 0; i < infoNeededForTheNewProductList.size() - 1; i++) {
            componentNeededForTheNewProductList.add(ComponentList.get(Integer.parseInt(infoNeededForTheNewProductList.get(i))));
        }

        productName = infoNeededForTheNewProductList.get(infoNeededForTheNewProductList.size() - 1);




        //Check pour savoir si un produit est en création.
        if(productCreationInProgress == false){
            productCreationInProgress = true;

            //Création du nouveau produit
            Product newProduct = ProductFactory.getProduct(componentNeededForTheNewProductList, productName);

            if(newProduct != null){
                //Création d'une timeline qui dure le temps de création du nouveau produit. A la fin de celle-ci, le nouveau produit est ajouté à la liste de produit et les composants sont retirés de la liste de droite.
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //permet d'avoir une indication du temps restant avant la création du produit
                        System.out.println(cptProduct+"/"+newProduct.getFabricationTime());
                        cptProduct++;
                    }
                }));
                timeline.setCycleCount(newProduct.getFabricationTime());
                timeline.setOnFinished(event -> {
                    addProductToTheProductList(newProduct);

                    //delete les composants de la liste de composant
                    deleteComponantFromComponentList(infoNeededForTheNewProductList);
                    productCreationInProgress = false;
                    cptProduct = 1;
                });
                timeline.play();
            }else{
                productCreationInProgress = false;
            }

        }

    }

    private void addProductToTheProductList(Product newProduct) {
        int location = findLocationForProduct();
        helbVue.productButtonList.get(location).setAssignedProduct(newProduct);
        System.out.println("New product !");
        update();
    }

    private int findLocationForProduct() {
        for (int i = 0; i < helbVue.productButtonList.size(); i++) {
            if(helbVue.productButtonList.get(i).getAssignedProduct() == null){
                return i;
            }
        }
        return -1;
    }

    private void createComponent(String[] datas){
        //assignation des variables pour avoir un accès plus simple au différentes données
        componentName = datas[1];
        componentSpecification = datas[2];
        componentColor = datas[3];
        componentDefectivePercentage = datas[datas.length-1];

        Component newComponent = ComponentFactory.getComponent(componentName,componentSpecification,componentColor,componentDefectivePercentage);
        addComponentToComponentList(newComponent);

    }

    private void addComponentToComponentList(Component newComponent) {
        ComponentList.add(newComponent);
        System.out.println("New component !");
        update();
    }

    private boolean checkComponentListSize() {
        if(ComponentList.size() == MAX_COMPONANT){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkProductListSize() {
        int nbNull = 0;
        for (int i = 0; i < helbVue.productButtonList.size(); i++) {
            if(helbVue.productButtonList.get(i).getAssignedProduct() == null)
                nbNull ++;
        }

        if(nbNull == 0){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public void update() {
        helbVue.updateComponentList(ComponentList);
        helbVue.updateProductList();
    }

    private void deleteComponantFromComponentList(ArrayList<String> infoList) {
        //Problème lors de la création d'un drone. si la liste n'est pas pleine, crash car la taille de la liste de composant varie dès que l'on retire un composant.
        //Si pour créer un drone il faut les composants situés en 0 2 7. lorsque l'on va supprimer le 0, le composant située en position 7 va descendre en position 6.
        //La position 7 sera alors vide et cela fera crash le programme

        //piste de solution, supprimer du plus grand vers le plus petit. comme ça, plus de problème ???


        //supprime les composants servant à la création d'un produit de la liste
        for (int i = 0; i < infoList.size() - 1; i++) {
            ComponentList.remove(Integer.parseInt(infoList.get(i)));
        }

        //le update permet de remettre le visuel à zero lorsque l'on supprime des composants de la liste
        update();
    }
}
