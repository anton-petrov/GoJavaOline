package edu.petrov.gojavaonline.module04.task02;

/**
 * Created by anton on 04/03/16.
 */
public class TemperatureDegreeFormatException extends Exception {
    private String degreeValue;

    public TemperatureDegreeFormatException(String degreeValue) {
        this.degreeValue = degreeValue;
    }

    public String getDegreeValue() {

        return degreeValue;
    }
}