package com.example.helb_electro;

import com.example.helb_electro.Box.ConfirmBox;
import com.example.helb_electro.Box.ProductInfoBox;
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
    private final int NUMBER_OF_LABEL = 8;

    private final ArrayList<Label> componentLabelList = new ArrayList<>();
    public final ArrayList<ProductButton> productButtonList = new ArrayList<>();
    private final VBox vBox;
    private GridPane gridPane;


    private Stage stage;
    private Scene scene;


    public ComboBox comboBox;

    public HELBVue(Stage stage){
        this.stage = stage;

        gridPane = new GridPane();
        gridPane.prefHeight(gridHeight);
        gridPane.prefWidth(gridWidth);
        gridPane.setHgap(HgapGrid);
        gridPane.setVgap(HgapGrid);

        addRowToGrid(rowNumber + 1, gridPane); // + 1 pour pouvoir avoir un place pour le label de la position
        addColToGrid(colNumber + 1, gridPane); // + 1 pour pouvoir avoir un place pour le label de la position
        addButtonToGrid(colNumber + 1, rowNumber + 1, gridPane);

        AnchorPane leftAnchorPane = new AnchorPane(gridPane);
        AnchorPane.setBottomAnchor(gridPane,0.0);
        AnchorPane.setLeftAnchor(gridPane,0.0);
        AnchorPane.setTopAnchor(gridPane,0.0);
        AnchorPane.setRightAnchor(gridPane,0.0);

        Label label = new Label("Optimisation");
        label.setUnderline(true);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(labelWidth);

        comboBox = new ComboBox<>();
        comboBox.setPrefWidth(CBWidth);
        comboBox.getItems().add("Temps");
        comboBox.getItems().add("Éco-score");
        comboBox.getItems().add("Prix");
        comboBox.getItems().add("Diversification");
        comboBox.setValue("Temps");

        vBox = new VBox(10,label, comboBox);
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

        for (int i = 0; i < NUMBER_OF_LABEL; i++) {
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

                    //Il y aurait 2 possibilités de faire ça. soit comme ici mais pour la partie ou on peut changer le 1 en A serait difficile.
                    //Une autre manière consisterais en récupérant soit le X ou le Y du productButton adjacent. cela permettrait d'avoir aussi les lettres depuis la même commande.
                    
                    if (row == 0 || col == 0) { //Si on remplis la ligne 0 ou la colonne 0 de la grid, on place des labels.
                        Label positionLabel = new Label();

                        if(col == 0){
                            positionLabel.setText(String.valueOf(row));
                        }else{
                            positionLabel.setText(String.valueOf(col));
                        }

                        gridPane.add(positionLabel, col, row);

                    } else { // sinon on place des productButton
                        ProductButton productButton = new ProductButton(col, row); // col est le X et row est le Y
                        productButtonList.add(productButton);
                        gridPane.add(productButton.getButton(), col, row);
                        cptButton++;
                    }


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
        int cptComponent = 0;

        for (Component component: componentList) {
            int[] colorTab = component.getRGBColor();

            Label labelToUpdate = componentLabelList.get(cptComponent);
            labelToUpdate.setText(component.getClass().getSimpleName());
            labelToUpdate.setStyle("-fx-background-color : rgb("+colorTab[0]+","+colorTab[1]+","+colorTab[2]+");");

            cptComponent++;
        }
        resetComponentVisual(componentList.size());
    }

    private void resetComponentVisual(int size) {

        //permet de retirer les composants qui ont été utilisés de la liste
        for (int i = size; i < NUMBER_OF_LABEL; i++) {
            Label labelToUpdate = componentLabelList.get(i);
            labelToUpdate.setText("Emplacement: "+i);
            labelToUpdate.setStyle("-fx-background-color : rgb("+255+","+255+","+255+");");
        }
    }

    public void updateProductList() {

        for (ProductButton productButton: productButtonList) {
            if(productButton.getAssignedProduct() != null){
                int[] colorTab = productButton.getAssignedProduct().getRGBColor();

                Button buttonToUpdate = productButton.getButton();
                buttonToUpdate.setText(productButton.getAssignedProduct().getClass().getSimpleName());
                buttonToUpdate.setStyle("-fx-background-color : rgb("+colorTab[0]+","+colorTab[1]+","+colorTab[2]+");");
            }else{

                Button buttonToUpdate = productButton.getButton();
                buttonToUpdate.setText("");
                buttonToUpdate.setStyle("-fx-background-color : rgb("+200+","+200+","+200+"); -fx-border-color: black;");
            }
        }

    }

    public boolean clearStorage(){
        return ConfirmBox.display();

    }

    public String showProductButtonInfo(ProductButton productButton){
        return ProductInfoBox.display(productButton);
    }
}
