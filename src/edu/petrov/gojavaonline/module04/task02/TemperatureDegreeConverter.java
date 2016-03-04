package edu.petrov.gojavaonline.module04.task02;

/**
 * Created by anton on 04/03/16.
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

    public static String convertAsString(String degreeValue) throws IncorrectDegreeFormatException {
        return String.format("%.2f%c", convertAsDouble(degreeValue), getDegreeLetter(degreeValue) == 'F' ? 'C' : 'F');
    }

    public static double convertFahrenheitToCelsius(double value) {
        return TemperatureDegree.CELSIUS.convertValueFrom(value);
    }

    public static double convertCelsiusToFahrenheit(double value) {
        return TemperatureDegree.FAHRENHEIT.convertValueFrom(value);
    }

    private static char getDegreeLetter(String degreeValue) throws IncorrectDegreeFormatException {
        if (degreeValue == null || degreeValue.length() < 2)
            throw new IncorrectDegreeFormatException();
        char degreeLetter = Character.toUpperCase(degreeValue.charAt(degreeValue.length() - 1));
        if (degreeLetter != 'F' && degreeLetter != 'C')
            throw new IncorrectDegreeFormatException();
        return degreeLetter;
    }

}
