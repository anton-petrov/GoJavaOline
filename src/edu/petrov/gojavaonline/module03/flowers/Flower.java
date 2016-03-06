package edu.petrov.gojavaonline.module03.flowers;

/**
 * Created by anton on 06/03/16.
 */
public abstract class Flower {
    String color;

    public Flower(String color) {
        this.color = color;
    }

    public abstract void smell();

    public String color() {
        return color;
    }

    public abstract String name();

    /**
     * Все цветы увядают одинаково.
     */
    public void fade() {
        System.out.println("This flower is now faded :(");
    }
}
