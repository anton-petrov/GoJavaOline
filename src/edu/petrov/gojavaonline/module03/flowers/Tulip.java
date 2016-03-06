package edu.petrov.gojavaonline.module03.flowers;

/**
 * Created by anton on 06/03/16.
 */
public class Tulip extends Flower {

    public Tulip(String color) {
        super(color);
    }

    @Override
    public void smell() {
        System.out.println("This flower smells like TULIP!");
    }

    @Override
    public String name() {
        return "Tulip";
    }
}
