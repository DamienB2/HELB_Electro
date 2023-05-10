package com.example.helb_electro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainController {

    private int cpt = 0, cptCycle = 0;
    private int duration;
    private Parser parser;


    public MainController(){
        parser = new Parser();

        startTimeline();

    }

    //récupère les infos correctement, mais ne prends pas en compte le changement de temps entre chaque Component.
    //la variable duration ne modifie pas le temps.
    private void startTimeline() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String[] datas = parser.getComponentArray(cpt);
                duration = Integer.parseInt(datas[0]);

                if(duration == cptCycle){
                    for (String data: datas) {
                        System.out.print(data + "|");
                    }
                    System.out.println();
                    cptCycle = 0;
                    cpt++;
                }
                cptCycle++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
