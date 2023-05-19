package com.example.helb_electro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class MainController {

    private int cpt = 0, cptCycle = 0;
    private int duration;
    private Parser parser;


    public MainController(HELBVue vue){
        parser = new Parser();

        startTimeline();

    }

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
