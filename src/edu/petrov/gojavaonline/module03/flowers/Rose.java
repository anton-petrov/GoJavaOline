package edu.petrov.gojavaonline.module03.flowers;

/**
 * Created by anton on 06/03/16.
 */
public class Rose extends Flower {

    public Rose(String color) {
        super(color);
    }

    @Override
    public void smell() {
        System.out.println("This flower smells like ROSE!");
    }

    @Override
    public String name() {
        return "Rose";
    }
}
