package edu.petrov.gojavaonline.module03.flowers;

/**
 * Created by anton on 06/03/16.
 */
public class Aster extends Flower {

    public Aster(String color) {
        super(color);
    }

    @Override
    public void smell() {
        System.out.println("This flower smells like ASTER!");
    }

    @Override
    public String name() {
        return null;
    }
}
