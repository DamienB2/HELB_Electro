package com.example.helb_electro;

import com.example.helb_electro.components.Component;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public final class HELBVue {
    private final int rowNumber = 4;
    private final int colNumber = 3;
    private final static int nbButton = 8;

    private final int HgapGrid = 20;

    private final int windowHeight = 400;
    private final int windowWidth = 600;
    private final int gridHeight = 400;
    private final int gridWidth = 500;
    private final int VboxHeight = 400;
    private final int VboxWidth = 100;
    private final int SPHeight = 400;
    private final int SPWidth = 600;
    private final double SPDividerPosition = 0.8;
    private final int rootHeight = 400;
    private final int rootWidth = 600;
    private final int CBWidth = 150;
    private final int labelWidth = 150;

    private static ArrayList<Label> componentLabelList = new ArrayList<>();
    private final VBox vBox;

    private Stage stage;
    private Scene scene;


    public Button buttonTest;

    public HELBVue(Stage stage){
        this.stage = stage;

        GridPane gridPane = new GridPane();
        gridPane.prefHeight(gridHeight);
        gridPane.prefWidth(gridWidth);
        gridPane.setHgap(HgapGrid);
        gridPane.setVgap(HgapGrid);

        addRowToGrid(rowNumber, gridPane);
        addColToGrid(colNumber, gridPane);
        addButtonToGrid(colNumber, rowNumber, gridPane);

        AnchorPane leftAnchorPane = new AnchorPane(gridPane);
        AnchorPane.setBottomAnchor(gridPane,0.0);
        AnchorPane.setLeftAnchor(gridPane,0.0);
        AnchorPane.setTopAnchor(gridPane,0.0);
        AnchorPane.setRightAnchor(gridPane,0.0);

        Label label = new Label();
        label.setText("Optimisation");
        label.setUnderline(true);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(labelWidth);

        ComboBox comboBox = new ComboBox<>();
        comboBox.setPrefWidth(CBWidth);
        comboBox.getItems().add("Temps");
        comboBox.getItems().add("Éco-score");
        comboBox.getItems().add("Prix");
        comboBox.getItems().add("Diversification");
        comboBox.setValue("Temps");

        buttonTest = new Button();
        buttonTest.setText("TEST");

        vBox = new VBox(10,label, comboBox, buttonTest);
        vBox.setPrefHeight(VboxHeight);
        vBox.setPrefWidth(VboxWidth);

        addComponentLabel(vBox);


        SplitPane splitPane = new SplitPane(leftAnchorPane, vBox);
        splitPane.setPrefSize(SPWidth, SPHeight);
        splitPane.setDividerPosition(0, SPDividerPosition);

        AnchorPane root = new AnchorPane(splitPane);
        root.setPrefSize(rootWidth, rootHeight);


        Scene scene = new Scene(root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    private void addComponentLabel(VBox vBox) {
        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, null);
        Border border = new Border(borderStroke);

        for (int i = 1; i <= 8; i++) {
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setPrefWidth(labelWidth);
            label.setText("Emplacement: "+i);
            label.setBorder(border);

            componentLabelList.add(label);
            vBox.getChildren().add(label);
        }
    }

    private void addButtonToGrid(int colNumber, int rowNumber, GridPane gridPane) {
        int cptButton = 0;

        for (int row = 0; row < rowNumber; row++) {
            for (int col = 0; col < colNumber; col++) {

                if(cptButton < nbButton){
                    Button button = new Button();
                    button.setPrefHeight(50);
                    button.setPrefWidth(100);
                    gridPane.add(button, col, row);

                    cptButton++;
                }
            }
        }
    }

    private void addColToGrid(int colNumber, GridPane gridPane) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setMinWidth(10);
        columnConstraints.setFillWidth(true);

        for (int i = 0; i < colNumber; i++) {
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    private void addRowToGrid(int rowNumber, GridPane gridPane) {

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(10);
        rowConstraints.setFillHeight(true);

        for (int i = 0; i < rowNumber; i++) {
            gridPane.getRowConstraints().add(rowConstraints);
        }

    }


    public void updateComponentList(ArrayList<Component> componentList){
        int cpt = 0;

        for (Component component: componentList) {
            int[] colorTab = component.getRGBColor();
            component.getinfo();

            Label labelToUpdate = componentLabelList.get(cpt);
            labelToUpdate.setText(component.getClass().getSimpleName());
            labelToUpdate.setStyle("-fx-background-color : rgb("+colorTab[0]+","+colorTab[1]+","+colorTab[2]+");");

            cpt++;
        }
    }

}
