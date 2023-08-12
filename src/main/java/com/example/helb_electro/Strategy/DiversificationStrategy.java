package com.example.helb_electro.Strategy;

import com.example.helb_electro.products.Product;

import java.util.ArrayList;
import java.util.Collections;

public class DiversificationStrategy implements IStrategy{

    @Override
    public ArrayList<Product> getSortedProductList(ArrayList<Product> allProductList) {
        Collections.shuffle(allProductList);
        return allProductList;
    }
}
