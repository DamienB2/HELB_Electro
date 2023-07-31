package com.example.helb_electro.Strategy;

import com.example.helb_electro.products.Product;

import java.util.ArrayList;

public interface IStrategy {
    ArrayList<Product> getSortedProductList(ArrayList<Product> allProductList);
}
