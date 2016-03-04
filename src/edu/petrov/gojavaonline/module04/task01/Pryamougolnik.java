package edu.petrov.gojavaonline.module04.task01;

/**
 * Created by anton on 04/03/16.
 */


public class Pryamougolnik extends Figure {
    double width;
    double height;

    public Pryamougolnik(double width, int height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getSquare() {
        return width * height;
    }
}
