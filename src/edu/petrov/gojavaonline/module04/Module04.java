package edu.petrov.gojavaonline.module04;

import java.util.Scanner;

/**
 * Created by anton on 04/03/16.
 */
public class Module04 {

    private static final String CONVERSION_ERROR_MESSAGE = "Error! Please, enter correct degrees value!";
    private static String GREETING_MESSAGE = "Welcome to temperature degrees conversion program!\n" +
            "Please, enter degrees which you want to convert, for example: 5C, 100F, 36.6c, etc.\n" +
            "To exit from program type 'q' or 'quit'.";


    public static void main(String[] args) {

        System.out.println(GREETING_MESSAGE);

        Scanner scanner = new Scanner(System.in);

        do {
            try {
                String degreeValue = "";
                System.out.print("> ");
                degreeValue = scanner.nextLine().toLowerCase();

                if (degreeValue.equals("q") || degreeValue.equals("quit"))
                    break;

                System.out.format("%s = %s\n", degreeValue.toUpperCase(), TemperatureDegreesConverter.convertToString(degreeValue));
            } catch (IncorrectDegreeFormatException e) {
                System.out.println(CONVERSION_ERROR_MESSAGE);
            }

        } while (true);
    }
}
