package edu.petrov.gojavaonline.calculator;

/**
 * Created by anton on 05/04/16.
 */
public class Calculator {
    public static void main(String[] args) {
        BigInteger a = BigInteger.parseBigInteger("999999999999999");
        BigInteger b = BigInteger.parseBigInteger("999999999999999");

        System.out.println(b.add(a));
    }
}
