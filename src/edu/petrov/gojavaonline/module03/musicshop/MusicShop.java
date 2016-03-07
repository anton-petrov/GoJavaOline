package edu.petrov.gojavaonline.module03.musicshop;

import java.util.HashMap;

/**
 * Created by anton on 06/03/16.
 */
public class MusicShop {
    HashMap<Integer, Product> goods = new HashMap();
    int funds;
    int currentId;

    public void add(Product product) {
        if (product != null) {
            goods.put(currentId++, product);
        }
    }

    public Product sell(int id) {
        Product soldProduct = goods.remove(id);
        if (soldProduct != null) {
            funds += soldProduct.price;
        }
        return soldProduct;
    }
}
