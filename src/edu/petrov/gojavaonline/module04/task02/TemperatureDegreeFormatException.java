package edu.petrov.gojavaonline.module04.task02;

/**
 * Created by anton on 04/03/16.
 */
class TemperatureDegreeFormatException extends IllegalArgumentException {
    private String degreeValue;

    TemperatureDegreeFormatException(String degreeValue) {
        this.degreeValue = degreeValue;
    }

    String getDegreeValue() {
        return degreeValue;
    }
}