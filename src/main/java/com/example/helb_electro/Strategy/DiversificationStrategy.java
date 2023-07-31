package com.example.helb_electro.Strategy;

import com.example.helb_electro.products.Product;

import java.util.ArrayList;
import java.util.Collections;

public class DiversificationStrategy implements IStrategy{

    @Override
    public ArrayList<Product> getSortedProductList(ArrayList<Product> allProductList) {
        Collections.sort(allProductList, (product1, product2) -> product1.getFabricationTime() - product2.getFabricationTime());
        return allProductList;
    }
}
