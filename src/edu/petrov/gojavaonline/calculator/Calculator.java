package edu.petrov.gojavaonline.calculator;

/**
 * Created by anton on 05/04/16.
 */

public class Calculator {

    private static String A = "2345678991";
    private static String B = "1122999009";


    private static java.math.BigInteger bigIntegerA = new java.math.BigInteger(A);
    private static java.math.BigInteger bigIntegerB = new java.math.BigInteger(B);

    private static BigInteger a = BigInteger.parseBigInteger(A);
    private static BigInteger b = BigInteger.parseBigInteger(B);

    public static void main(String[] args) {
        System.out.println(b.add(a));

        a = BigInteger.parseBigInteger(A);
        b = BigInteger.parseBigInteger(B);
        final int i = Integer.parseInt("234567899") - Integer.parseInt("234567899");
        System.out.println("OK = " + bigIntegerA.subtract(bigIntegerB));
        System.out.println("RESULT = " + a.subtract(b));
    }
}
