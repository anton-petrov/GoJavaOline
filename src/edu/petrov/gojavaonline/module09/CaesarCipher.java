package edu.petrov.gojavaonline.module09;

/**
 * Created by Anton on 28.03.2016.
 */

public class CaesarCipher {

    public static String crypt(String message, int key) {
        return caesarCipher(message, key);
    }

    public static String decrypt(String message, int key) {
        return caesarCipher(message, -key);
    }

    private static String caesarCipher(String message, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            result.append(shift(message.charAt(i), key));
        }
        return result.toString();
    }

    public static char shift(char c, int key) {
        return (char) (((int) c + key + (int) Character.MAX_VALUE) % (int) Character.MAX_VALUE);
    }

}