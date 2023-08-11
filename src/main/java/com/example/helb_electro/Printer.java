package com.example.helb_electro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Printer {


    public static void print(String productInfoString){

        DateTimeFormatter fileNameFormat = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        try{
            File ticket = new File("t_" + fileNameFormat.format(now) + ".txt");

            if(ticket.createNewFile()) {

                try{
                    FileWriter myWriter = new FileWriter(ticket.getName());

                    myWriter.write("--------------------\n");
                    myWriter.write("Date: " + dtf.format(now) + "\n");
                    myWriter.write(productInfoString + "\n");
                    myWriter.write("--------------------");

                    myWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                System.out.println("File already exist");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
