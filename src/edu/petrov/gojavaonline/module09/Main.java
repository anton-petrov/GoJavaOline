package edu.petrov.gojavaonline.module09;

/**
 * Module 9
 * Реализовать шифрование текста с помощью алгоритма Цезаря.
 * Зашифровать и расшифровать текстовое представление коллекци обьектов из ДЗ из Модуля 3: ООП в Java
 */

public class Main {
    public static void main(String[] args) {
        final int mySuperSecretKey = 5;
        System.out.println(CaesarCipher.shift(CaesarCipher.shift('A', 3), -3));
        String plainText = "This is text!";
        String cipherText = CaesarCipher.crypt(plainText, mySuperSecretKey);
        plainText = CaesarCipher.decrypt(cipherText, mySuperSecretKey);

        System.out.println(plainText);
        System.out.println(cipherText);
    }
}
