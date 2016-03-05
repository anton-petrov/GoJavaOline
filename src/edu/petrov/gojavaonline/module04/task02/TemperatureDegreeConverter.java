package edu.petrov.gojavaonline.module04.task02;

/**
 * Created by anton on 04/03/16.
 */

/**
 * @author anton
 * @version 1
 */

public class TemperatureDegreeConverter {

    /**
     * Depending on the degrees letter in degreeValue, converts Celsius to Fahrenheit or vice versa
     *
     * @param degreeValue Degrees value in the following format: [degrees][C|F];
     *                    Examples: 13C, 111F, 36.6c, -5f.
     * @return Converted degrees value as double
     * @throws IncorrectDegreeFormatException
     */
    public static double convertAsDouble(String degreeValue) throws IncorrectDegreeFormatException {

        if (degreeValue != null) {
            try {
                char degreeLetter = getDegreeLetter(degreeValue);
                double conversionValue = Double.parseDouble(degreeValue.substring(0, degreeValue.length() - 1));

                switch (Character.toUpperCase(degreeLetter)) {
                    case 'C':
                        conversionValue = convertCelsiusToFahrenheit(conversionValue);
                        break;
                    case 'F':
                        conversionValue = convertFahrenheitToCelsius(conversionValue);
                        break;
                }

                return conversionValue;

            } catch (Exception e) {
                throw new IncorrectDegreeFormatException();
            }
        }
        return 0;
    }

    /**
     * Depending on the degrees letter in degreeValue, converts Celsius to Fahrenheit or vice versa
     *
     * @param degreeValue Degrees value in the following format: [degrees][C|F];
     *                    Examples: 13C, 111F, 36.6c, -5f.
     * @return Converted degrees value as String. For example: for input value 67C it returns 152.60F
     * @throws IncorrectDegreeFormatException
     */
    public static String convertAsString(String degreeValue, int precision) throws IncorrectDegreeFormatException {
        return String.format("%." + precision + "f%c", convertAsDouble(degreeValue),
                getDegreeLetter(degreeValue) == 'F' ? 'C' : 'F');
    }

    public static String convertAsString(String degreeValue) throws IncorrectDegreeFormatException {
        return convertAsString(degreeValue, 2);
    }

    /**
     * Convert degree value from Fahrenheit to Celsius
     *
     * @param value degrees in Fahrenheit
     * @return value in deg. Celsius
     */
    public static double convertFahrenheitToCelsius(double value) {
        return TemperatureDegree.CELSIUS.convertValueFrom(value);
    }

    /**
     * Convert degree value from Celsius to Fahrenheit
     * @param value degrees in Celsius
     * @return value in deg. Fahrenheit
     */
    public static double convertCelsiusToFahrenheit(double value) {
        return TemperatureDegree.FAHRENHEIT.convertValueFrom(value);
    }

    /**
     *
     * @param degreeValue
     * @return Letter C(elsius) or letter F(ahrenheit)
     * @throws IncorrectDegreeFormatException
     */
    private static char getDegreeLetter(String degreeValue) throws IncorrectDegreeFormatException {
        if (degreeValue == null || degreeValue.length() < 2)
            throw new IncorrectDegreeFormatException();
        char degreeLetter = Character.toUpperCase(degreeValue.charAt(degreeValue.length() - 1));
        if (degreeLetter != 'F' && degreeLetter != 'C')
            throw new IncorrectDegreeFormatException();
        return degreeLetter;
    }

}
