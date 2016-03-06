package edu.petrov.gojavaonline.module03.flowers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anton on 06/03/16.
 */
public class Bouquet {
    List<Flower> flowers = new LinkedList();

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void show() {
        int i = 0;
        for (Flower flower : flowers) {
            System.out.println("The " + i++ + " flower is " + flower.name() + " " + flower.name());
        }
    }
}
