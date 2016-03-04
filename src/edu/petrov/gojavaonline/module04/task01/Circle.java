package edu.petrov.gojavaonline.module04.task01;

/**
 * Created by anton on 04/03/16.
 */
public class Circle extends Figure {
    double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double height) {
        this.radius = height;
    }

    @Override
    public double getSquare() {
        return radius * radius * Math.PI;
    }
}
