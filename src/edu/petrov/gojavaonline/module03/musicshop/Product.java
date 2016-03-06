package edu.petrov.gojavaonline.module03.musicshop;

/**
 * Created by anton on 06/03/16.
 */
public abstract class Product {
    int id;
    int price;

    public Product() {
    }

    public Product(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
