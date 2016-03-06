package edu.petrov.gojavaonline.module04.task01;

/**
 * Created by anton on 04/03/16.
 */


public class Main {
    public static void main(String[] args) {
        Figure triangle = new Triangle(1, 2, 3);
        Figure circle = new Circle(10);
        Figure rectangle = new Rectangle(8, 8);

        System.out.format("Triangle area is\t%.2f;\n", AreaCalculator.calcFigureArea(triangle));
        System.out.format("Circle area is\t\t%.2f;\n", AreaCalculator.calcFigureArea(circle));
        System.out.format("Rectangle area is\t%.2f;\n", AreaCalculator.calcFigureArea(rectangle));
    }
}
