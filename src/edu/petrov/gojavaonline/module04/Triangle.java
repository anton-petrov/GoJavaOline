package edu.petrov.gojavaonline.module04;

/**
 * Created by anton on 04/03/16.
 */
public class Triangle extends Figure {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle() {
    }

    /**
     * @param a is the first side of the triangle
     * @param b is the second side of the triangle
     * @param c is the third side of the triangle
     */
    public Triangle(double a, double b, double c) {
        sideA = a;
        sideB = b;
        sideC = c;
    }

    /**
     * Calculate square of the triangle
     *
     * @return triangle square
     */
    @Override
    public double getSquare() {
        double perimeter = sideA + sideB + sideC;
        return Math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC));
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }
}
