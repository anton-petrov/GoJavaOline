package edu.petrov.gojavaonline.module03.flowers;

/**
 * Created by anton on 06/03/16.
 */
public class Chamomile extends Flower {

    public Chamomile(String color) {
        super(color);
    }

    @Override
    public void smell() {
        System.out.println("This flower smells like CHAMOMILE!");
    }

    @Override
    public String name() {
        return "Chamomile";
    }
}
