package edu.petrov.gojavaonline.module04.task03;

import java.util.Scanner;

/**
 * Created by anton on 05/03/16.
 */

public class Main {
    private static Point2D getPointFromKeyboard(String promptCoordX, String promptCoordY) {
        Point2D result = new Point2D();
        Scanner userInput = new Scanner(System.in);
        System.out.print(promptCoordX);
        result.setX(userInput.nextDouble());
        System.out.print(promptCoordY);
        result.setY(userInput.nextDouble());
        return result;
    }

    public static void main(String[] args) {
        double x, y;
        Point2D pointA = getPointFromKeyboard("Enter X coordinate of point A: ", "Enter Y coordinate of point A: ");
        Point2D pointB = getPointFromKeyboard("Enter X coordinate of point B: ", "Enter Y coordinate of point B: ");

        System.out.format("Distance between points is %.3f\n", pointA.distanceTo(pointB));
        System.out.println("Buy!");
    }
}
