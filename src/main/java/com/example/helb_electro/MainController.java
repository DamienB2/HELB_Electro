package com.example.helb_electro;

import com.example.helb_electro.components.Battery;
import com.example.helb_electro.components.Icomponent;
import com.example.helb_electro.components.Motor;
import com.example.helb_electro.components.Sensor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;

public class MainController {

    private int cpt = 0, cptCycle = 0;
    private int duration;

    private String[] datas;


    private String componentTime;
    private String componentName;
    private String componentSpecification;
    private String componentColor;
    private String componentDefectivePercentage;
    //pour avoir le pourcentage on utilise la dernière info de la ligne qu'on reçoit car si on définis un emplacement comme pour les autres, la couleur et la déféctuosité seront mélangée

    private static final ArrayList<Icomponent> ComponentList = new ArrayList<>();
    private Parser parser;


    public MainController(HELBVue vue){
        parser = new Parser();

        startTimeline();

    }

    //Start timeline permet de lire ligne par ligne le fichier data. Il stocke ensuite les données dans un tableau de strings qui seront ensuite affichées lorsque le temps reçus est atteint
    private void startTimeline() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                datas = parser.getComponentArray(cpt);
                componentTime = datas[0];
                duration = Integer.parseInt(componentTime);

                if(duration == cptCycle){
                    createComponent();

                    cptCycle = 0;
                    cpt++;
                }
                cptCycle++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private void createComponent(){
        //assignation des variables pour avoir un accès plus simple au différentes données
        componentName = datas[1];
        componentSpecification = datas[2];
        componentColor = datas[3];
        componentDefectivePercentage = datas[datas.length-1];

        switch (componentName){
            case "Moteur":
                new Motor(componentSpecification, componentDefectivePercentage); //Crée un Moteur avec une puissance et un pourcentage de déféctuosité
                break;
            case "Capteur":
                new Sensor(componentSpecification, componentColor, componentDefectivePercentage); //Crée un Capteur avec une distance, une couleur et un pourcentage de déféctuosité
                break;
            case "Batterie":
                new Battery(componentSpecification, componentDefectivePercentage); //Crée une Batterie avec une taux de remplissage et un pourcentage de déféctuosité
                break;
        }

    }
}
