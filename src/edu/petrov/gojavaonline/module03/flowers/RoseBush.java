package edu.petrov.gojavaonline.module03.flowers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anton on 06/03/16.
 */
public class RoseBush {
    List<Flower> roses = new LinkedList();

    public void planRose(String color) {
        roses.add(new Rose(color));
    }
}
