package com.example.helb_electro;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Parser {
    private String file = "src/main/java/com/example/helb_electro/data.txt";
    private List<String[]> ComponentList = new ArrayList<>();

    public Parser(){
        readTextFile();
    }

    protected void readTextFile() {
        try(Stream<String> stream = Files.lines(Paths.get(file))){
            Object[] Text = stream.toArray();
            for (int i = 0; i < Text.length; i++) {
                String[] Component = Text[i].toString().split(",");
                ComponentList.add(Component);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public String[] getComponentArray(int cpt){
        String[] tabComponent = ComponentList.get(cpt);
        return tabComponent;
    }
}
