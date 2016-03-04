package edu.petrov.gojavaonline.module04.task02;

/**
 * Created by anton on 04/03/16.
 */
public enum TemperatureDegree {
    FAHRENHEIT,
    CELSIUS;

    private double value;


    public double convertValueFrom(double degree) {
        // convert degrees...
        switch (this) {
            // ...form celsius to fahrenheit
            case FAHRENHEIT:
                return (9.0 / 5.0) * degree + 32;
            // ...form fahrenheit to celsius
            case CELSIUS:
                return (degree - 32) * (5.0 / 9.0);
            default:
                return 0;
        }
    }

    public double getValue() {
        return convertValueFrom(value);
    }

    public void setValue(double value) {
        this.value = value;
    }
}
