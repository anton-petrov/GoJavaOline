package edu.petrov.gojavaonline.module04.task01;

/**
 * Created by anton on 04/03/16.
 */


public class Module041 {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(1, 2, 3);
        Circle circle = new Circle(10);
        Pryamougolnik pryamougolnik = new Pryamougolnik(8, 8);

        SquareCalculator.calcFigureSquare(circle);
    }
}
